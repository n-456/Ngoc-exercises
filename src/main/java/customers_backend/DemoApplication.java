package customers_backend;

import customers_backend.dao.CustomerDAO;
import customers_backend.database.DatabaseHelper;
import customers_backend.model.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

	}

//    public static void main(String[] args) {
//
//        System.out.println("=== PostgreSQL Supabase + Java ===\n");
//
//        // 1. Kiểm tra kết nối
//        System.out.println("1. Testing Database Connection...");
//        if (!DatabaseHelper.testConnection()) {
//            System.out.println("Cannot connect to database. Exiting...");
//            return;
//        }
//        System.out.println();
//
//        // 2. Tạo bảng
//        System.out.println("2. Creating Table...");
//        CustomerDAO.createTable();
//        System.out.println();
//
//        // 3. Thêm customers
//        System.out.println("3. Inserting customers...");
//        CustomerDAO.insertCustomer("John Doe","0281","john@example.com");
//        CustomerDAO.insertCustomer("Jane Smith","3957","jane@example.com");
//        CustomerDAO.insertCustomer("Bob Johnson","1928","bob@example.com");
//        System.out.println();
//
//        // 4. Lấy tất cả customers
//        System.out.println("4. Getting all customers...");
//        List<Customer> customers = CustomerDAO.getAllCustomers();
//        for (Customer customer : customers) {
//            System.out.println("   " + customer);
//        }
//        System.out.println();
//
//        // 5. Lấy customer theo ID
//        System.out.println("5. Getting customer by ID (ID=1)...");
//        Customer customer = CustomerDAO.getCustomerById(1);
//        if (customer != null) {
//            System.out.println("   " + customer);
//        } else {
//            System.out.println("   Customer not found!");
//        }
//        System.out.println();
//
//        // 6. Cập nhật customer
//        System.out.println("6. Updating customer (ID=1)...");
//        CustomerDAO.updateCustomer(1, "John Updated");
//        System.out.println();
//
//        // 7. Xóa customer
//        System.out.println("7. Deleting customer (ID=3)...");
//        CustomerDAO.deleteCustomer(3);
//        System.out.println();
//
//        // 8. Lấy danh sách cuối cùng
//        System.out.println("8. Final customer List...");
//        customers = CustomerDAO.getAllCustomers();
//        for (Customer c : customers) {
//            System.out.println("   " + c);
//        }
//
//        System.out.println("\n=== Done! ===");
//    }
}
