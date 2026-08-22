package customers_frontend;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerApi api = new CustomerApi();
            try {
                api.getCustomer();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            CustomerController controller = new CustomerController();
            controller.show();
        });
    }
}