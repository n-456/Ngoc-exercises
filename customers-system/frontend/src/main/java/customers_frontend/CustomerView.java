package customers_frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerView extends JFrame {
    JTextField txtName, txtPhone, txtEmail;
    JTable table;
    DefaultTableModel tableModel;

    JButton btnAdd;
    JButton btnEdit;
    JButton btnDelete;
    JButton btnClear;

    public CustomerView() {
        setTitle("Customer Management");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setLayout(new BorderLayout(15, 15));


        // 1. FORM BÊN TRÁI
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panelForm.add(new JLabel("Name:"), gbc);

        // Phone
        gbc.gridy = 1;
        panelForm.add(new JLabel("Phone:"), gbc);

        // Email
        gbc.gridy = 2;
        panelForm.add(new JLabel("Email:"), gbc);

        // TextField
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtName = new JTextField(15);
        panelForm.add(txtName, gbc);

        gbc.gridy = 1;

        txtPhone = new JTextField(15);
        panelForm.add(txtPhone, gbc);

        gbc.gridy = 2;

        txtEmail = new JTextField(15);
        panelForm.add(txtEmail, gbc);

        add(panelForm, BorderLayout.WEST);


        // 2. TABLE
        String[] columnNames = {"Name", "Phone", "Email"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        // 3. BUTTONS
        JPanel panelBottom = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 5)
        );

        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        panelBottom.add(btnAdd);
        panelBottom.add(btnEdit);
        panelBottom.add(btnDelete);
        panelBottom.add(btnClear);

        add(panelBottom, BorderLayout.SOUTH);

        // Ban đầu chưa chọn dòng
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);


        // 4. CLICK VÀO TABLE
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow >= 0) {
                // Lấy dữ liệu từ table đưa lên form
                txtName.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtPhone.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtEmail.setText(tableModel.getValueAt(selectedRow, 2).toString());
                // Cho phép Edit/Delete
                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);
            } else {
                btnEdit.setEnabled(false);
                btnDelete.setEnabled(false);
            }
        });

        // 5. ADD
//        btnAdd.addActionListener(e -> {
//            if (!validateInput()) {
//                return;
//            }
//            String name = txtName.getText().trim();
//            String phone = txtPhone.getText().trim();
//            String email = txtEmail.getText().trim();
//
//            Object[] rowData = {name, phone, email};
//            tableModel.addRow(rowData);
//
//            clearForm();
//            JOptionPane.showMessageDialog(this, "Thêm thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
//        });

        // 6. EDIT
//        btnEdit.addActionListener(e -> {
//            int selectedRow = table.getSelectedRow();
//
//            if (selectedRow < 0) {
//                JOptionPane.showMessageDialog(this, "Hãy chọn 1 khách hàng!", "Warning", JOptionPane.WARNING_MESSAGE);
//                return;
//            }
//
//            if (!validateInput()) {
//                return;
//            }
//
//            String name = txtName.getText().trim();
//            String phone = txtPhone.getText().trim();
//            String email = txtEmail.getText().trim();
//
//            // Cập nhật dữ liệu trong table
//            tableModel.setValueAt(name, selectedRow, 0);
//            tableModel.setValueAt(phone, selectedRow, 1);
//            tableModel.setValueAt(email, selectedRow, 2);
//
//            clearForm();
//
//            JOptionPane.showMessageDialog(this, "Sửa thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
//        });


        // 7. DELETE
//        btnDelete.addActionListener(e -> {
//            int selectedRow = table.getSelectedRow();
//
//            if (selectedRow < 0) {
//                JOptionPane.showMessageDialog(this, "Chọn 1 khách hàng!", "Warning", JOptionPane.WARNING_MESSAGE);
//                return;
//            }
//
//            String name = tableModel.getValueAt(selectedRow, 0).toString();
//            tableModel.removeRow(selectedRow);
//
//            clearForm();
//            JOptionPane.showMessageDialog(this, "Xoá thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
//        });
       // 8. CLEAR
        btnClear.addActionListener(e -> {
            clearForm();
        });
    }

    // KIỂM TRA INPUT
    public boolean validateInput() {
        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập đủ thông tin!", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void clearForm() {
        txtName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");

        table.clearSelection();

        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);

        txtName.requestFocus();
    }


    public void showView() {
        SwingUtilities.invokeLater(() -> {
            this.setVisible(true);
        });
    }
}
