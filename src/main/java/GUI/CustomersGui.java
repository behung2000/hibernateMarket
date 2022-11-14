package GUI;

import BLL.CustomersBLL;
import hibernatebanhang.DAL.Customers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Vector;

public class CustomersGui extends JFrame{
    private JButton addButton;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JTable table1;
    private JPanel jPanel1;
    private JTextField textField3;

    private CustomersBLL customersBLL;

    public CustomersGui() {
        customersBLL = new CustomersBLL();
        initCustomersGui();
    }

    private void initCustomersGui() {
        setContentPane(jPanel1);
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setUpData();
    }

    private void setUpData() {
        List<Customers> customersList = customersBLL.getList();
        table1.setModel(toTableModel(customersList));
    }

    private TableModel toTableModel(List<Customers> customersList) {
        Vector header = new Vector<>();
        header.add("id");
        header.add("password");
        header.add("full name");
        header.add("address");
        header.add("city");
        DefaultTableModel model = new DefaultTableModel(header, 0);
        for (Customers customers : customersList) {
            Vector row = new Vector();
            row.add(customers.getCustomerId());
            row.add(customers.getPassword());
            row.add(customers.getFullname());
            row.add(customers.getAddress());
            row.add(customers.getCity());
            model.addRow(row);
        }
        return model;
    }
}
