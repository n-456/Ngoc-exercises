package customers_frontend;

import javax.swing.*;

public class CustomerController {

    private final CustomerView view;
    private final CustomerApi api;


    public void show(){
        view.showView();
    }

    public CustomerController() {
        this.view = new CustomerView();
        this.api = new CustomerApi();

        initController();
    }

    private void initController() {

        System.out.println("initController");
        view.btnAdd.addActionListener(e -> {System.out.println("ADD CLICKED"); addCustomer();});
        view.btnEdit.addActionListener(e -> editCustomer());
        view.btnDelete.addActionListener(e -> deleteCustomer());
    }

    private void addCustomer() {
        if (!view.validateInput()) {
            System.out.print("odnwlewg???");
            return;
        }

        System.out.print("odnwlewg");
        String name = view.txtName.getText().trim();
        String phone = view.txtPhone.getText().trim();
        String email = view.txtEmail.getText().trim();

        Object[] rowData = {name, phone, email};
        view.tableModel.addRow(rowData);

        view.clearForm();
        JOptionPane.showMessageDialog(view, "Thêm thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // api.createCustomer(...)
    }

    private void editCustomer() {
        int selectedRow = view.table.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, "Hãy chọn 1 khách hàng!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!view.validateInput()) {
            return;
        }

        String name = view.txtName.getText().trim();
        String phone = view.txtPhone.getText().trim();
        String email = view.txtEmail.getText().trim();

        // Cập nhật dữ liệu trong table
        view.tableModel.setValueAt(name, selectedRow, 0);
        view.tableModel.setValueAt(phone, selectedRow, 1);
        view.tableModel.setValueAt(email, selectedRow, 2);

        view.clearForm();

        JOptionPane.showMessageDialog(view, "Sửa thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // api.updateCustomer(...)
    }

    private void deleteCustomer() {
        int selectedRow = view.table.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, "Chọn 1 khách hàng!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String name = view.tableModel.getValueAt(selectedRow, 0).toString();
        view.tableModel.removeRow(selectedRow);

        view.clearForm();
        JOptionPane.showMessageDialog(view, "Xoá thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // api.deleteCustomer(...)
    }
}