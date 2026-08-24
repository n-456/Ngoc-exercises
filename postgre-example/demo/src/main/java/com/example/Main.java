package com.example;

import com.example.database.DatabaseHelper;
import com.example.dao.UserDAO;
import com.example.model.User;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== PostgreSQL Supabase + Java ===\n");

        // 1. Kiểm tra kết nối
        System.out.println("1. Testing Database Connection...");
        if (!DatabaseHelper.testConnection()) {
            System.out.println("Cannot connect to database. Exiting...");
            return;
        }
        System.out.println();

        // 2. Tạo bảng
        System.out.println("2. Creating Table...");
        UserDAO.createTable();
        System.out.println();

        // 3. Thêm users
        System.out.println("3. Inserting Users...");
        UserDAO.insertUser("john@example.com", "John Doe");
        UserDAO.insertUser("jane@example.com", "Jane Smith");
        UserDAO.insertUser("bob@example.com", "Bob Johnson");
        System.out.println();

        // 4. Lấy tất cả users
        System.out.println("4. Getting All Users...");
        List<User> users = UserDAO.getAllUsers();
        for (User user : users) {
            System.out.println("   " + user);
        }
        System.out.println();

        // 5. Lấy user theo ID
        System.out.println("5. Getting User by ID (ID=1)...");
        User user = UserDAO.getUserById(1);
        if (user != null) {
            System.out.println("   " + user);
        } else {
            System.out.println("   User not found!");
        }
        System.out.println();

        // 6. Cập nhật user
        System.out.println("6. Updating User (ID=1)...");
        UserDAO.updateUser(1, "John Updated");
        System.out.println();

        // 7. Xóa user
        System.out.println("7. Deleting User (ID=3)...");
        UserDAO.deleteUser(3);
        System.out.println();

        // 8. Lấy danh sách cuối cùng
        System.out.println("8. Final User List...");
        users = UserDAO.getAllUsers();
        for (User u : users) {
            System.out.println("   " + u);
        }

        System.out.println("\n=== Done! ===");
    }
}