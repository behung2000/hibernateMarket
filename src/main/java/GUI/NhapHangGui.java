package GUI;

import BLL.CategoryBLL;
import BLL.VegetableBLL;
import DAL.Category;
import DAL.Vegetable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class NhapHangGui extends JFrame{
    private JPanel jPanel1;
    private JTextField textField1;
    private JComboBox unitComboBox;
    private JComboBox categoryCombobox;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton resetButton;
    private JButton vegetablesWithCategoryButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JComboBox comboBox1;
    private JTextField textField5;
    private JButton searchButton;
    private JTable table1;
    private VegetableBLL vegetableBLL;
    private CategoryBLL categoryBLL;

    public NhapHangGui() {
        vegetableBLL = new VegetableBLL();
        categoryBLL = new CategoryBLL();
        initNhapHangGui();
    }

    private void initNhapHangGui() {
        setContentPane(jPanel1);
        setSize(700, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        setUpData();

        setCategoryCombobox();

        setResetButton();
        setVegetablesWithCategoryButton();
    }

    private void setUpData() {
        table1.setModel(toTableModel(vegetableBLL.getList()));
    }

    private TableModel toTableModel(List<Vegetable> list) {
        Vector header = new Vector<>();
        header.add("id");
        header.add("Name");
        header.add("Category");
        header.add("Unit");
        header.add("Amount");
        header.add("Image");
        header.add("Price");
        DefaultTableModel model = new DefaultTableModel(header, 0);
        for (Vegetable obj : list) {
            Vector row = new Vector();
            row.add(obj.getVegetableID());
            row.add(obj.getVegetableName());
            row.add(obj.getCatagory().getName());
            row.add(obj.getUnit());
            row.add(obj.getAmount());
            row.add(obj.getImage());
            row.add(obj.getPrice());
            model.addRow(row);
        }
        return model;
    }

    private void setResetButton() {
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUpData();
            }
        });
    }

    private void setCategoryCombobox() {
        categoryCombobox.setModel(new CategoryCombobox(categoryBLL.convertList1(categoryBLL.loadCategory())));
    }

    private void setVegetablesWithCategoryButton() {
        vegetablesWithCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category s = (Category) categoryCombobox.getModel().getSelectedItem();
                table1.setModel(toTableModel(vegetableBLL.getListWithCategory(s.getCatagoryID())));
            }
        });
    }
}
