package customers_frontend;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class CustomerController {

    private final CustomerView view;
    private final CustomerApi api;


    public void show(){
        view.showView();
    }

    public CustomerController() throws Exception {
        this.view = new CustomerView();
        this.api = new CustomerApi();

        initController();
        String jsonResponse = api.getCustomers();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Customer>>(){}.getType();
        List<Customer> customers = gson.fromJson(jsonResponse, listType);
        view.tableModel.setRowCount(0);
        for (Customer c : customers) {
            Object[] row = {c.getName(), c.getPhone(), c.getEmail()};
            view.tableModel.addRow(row);
        }
    }

    private void initController() {
        view.btnAdd.addActionListener(e -> {System.out.println("ADD CLICKED");
            try {
                addCustomer();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        view.btnEdit.addActionListener(e -> {
            try {
                editCustomer();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        view.btnDelete.addActionListener(e -> {
            try {
                deleteCustomer();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void addCustomer() throws IOException, InterruptedException {
        if (!view.validateInput()) {
            return;
        }

        String name = view.txtName.getText().trim();
        String phone = view.txtPhone.getText().trim();
        String email = view.txtEmail.getText().trim();

        Object[] rowData = {name, phone, email};
        view.tableModel.addRow(rowData);

        view.clearForm();
        JOptionPane.showMessageDialog(view, "Thêm thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);

        api.createCustomer(name,phone,email);
    }

    private void editCustomer() throws IOException, InterruptedException {
        int selectedRow = view.table.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, "Hãy chọn 1 khách hàng!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!view.validateInput()) {
            return;
        }

        int id = view.table.getSelectedRow()+1;
        String name = view.txtName.getText().trim();
        String phone = view.txtPhone.getText().trim();
        String email = view.txtEmail.getText().trim();

        // Cập nhật dữ liệu trong table
        view.tableModel.setValueAt(name, selectedRow, 0);
        view.tableModel.setValueAt(phone, selectedRow, 1);
        view.tableModel.setValueAt(email, selectedRow, 2);

        view.clearForm();

        JOptionPane.showMessageDialog(view, "Sửa thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);

        api.updateCustomer(id,name,phone,email);
    }

    private void deleteCustomer() throws IOException, InterruptedException {
        int selectedRow = view.table.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, "Chọn 1 khách hàng!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = view.table.getSelectedRow()+1;
        String name = view.tableModel.getValueAt(selectedRow, 0).toString();
        view.tableModel.removeRow(selectedRow);

        view.clearForm();
        JOptionPane.showMessageDialog(view, "Xoá thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);

        api.deleteCustomer(id);
    }
}