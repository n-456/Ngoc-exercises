package com.example.dao;

import com.example.database.DatabaseHelper;
import com.example.model.User;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // CREATE - Tạo bảng
    public static void createTable() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DatabaseHelper.getConnection();
            stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY," +
                    "email VARCHAR(100) UNIQUE NOT NULL," +
                    "name VARCHAR(100) NOT NULL," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")";

            stmt.execute(sql);
            System.out.println("✓ Table 'users' created successfully!");

        } catch (SQLException e) {
            System.out.println("✗ Error creating table: " + e.getMessage());
        } finally {
            DatabaseHelper.close(null, stmt, conn);
        }
    }

    // INSERT - Thêm user mới
    public static boolean insertUser(String email, String name) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseHelper.getConnection();
            String sql = "INSERT INTO users (email, name) VALUES (?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, name);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✓ User added successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("✗ Error inserting user: " + e.getMessage());
        } finally {
            DatabaseHelper.close(pstmt, conn);
        }

        return false;
    }

    // SELECT - Lấy tất cả user
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseHelper.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users ORDER BY id DESC");

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                users.add(user);
            }

            System.out.println("✓ Retrieved " + users.size() + " users");

        } catch (SQLException e) {
            System.out.println("✗ Error retrieving users: " + e.getMessage());
        } finally {
            DatabaseHelper.close(rs, stmt, conn);
        }

        return users;
    }

    // SELECT - Lấy user theo ID
    public static User getUserById(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseHelper.getConnection();
            String sql = "SELECT * FROM users WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
            }

        } catch (SQLException e) {
            System.out.println("✗ Error retrieving user: " + e.getMessage());
        } finally {
            DatabaseHelper.close(rs, pstmt, conn);
        }

        return null;
    }

    // UPDATE - Cập nhật user
    public static boolean updateUser(int id, String name) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseHelper.getConnection();
            String sql = "UPDATE users SET name = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✓ User updated successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("✗ Error updating user: " + e.getMessage());
        } finally {
            DatabaseHelper.close(pstmt, conn);
        }

        return false;
    }

    // DELETE - Xóa user
    public static boolean deleteUser(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseHelper.getConnection();
            String sql = "DELETE FROM users WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✓ User deleted successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("✗ Error deleting user: " + e.getMessage());
        } finally {
            DatabaseHelper.close(pstmt, conn);
        }

        return false;
    }
}