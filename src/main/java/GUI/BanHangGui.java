package GUI;

import BLL.CategoryBLL;
import BLL.VegetableBLL;
import DAL.Category;
import DAL.Customers;
import DAL.Vegetable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class BanHangGui extends JFrame{
    private JPanel jPanel1;
    private JComboBox SearchSPComboBox;
    private JButton resetButtonSP;
    private JButton searchButtonSP;
    private JTextField searchSPTextField;
    private JTable SPTable;
    private JTable table2;
    private JButton orderButton;
    private JButton delete1Button;
    private JComboBox categorytComboBox;
    private JButton addButton;
    private JLabel labelCustomer;
    private VegetableBLL vegetableBLL;
    private CategoryBLL categoryBLL;
    private Mess mess;

    public BanHangGui(Customers customers) {
        vegetableBLL = new VegetableBLL();
        categoryBLL = new CategoryBLL();
        mess = new Mess();
        init(customers);
    }

    private void init(Customers customers) {
        labelCustomer.setText(String.format("id = %s, full name: %s", customers.getCustomerId(), customers.getFullname()));
        setContentPane(jPanel1);
        setSize(1200, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        initLoandData();
        initButton();
        initCombobox();
        initTable();
    }

    private void initButton() {
        setResetButtonVegetable();
        setSearchButtonVegatable();

    }

    private void initCombobox() {
        setComboBoxVegatable();
    }

    private void initTable() {

    }

    private void initLoandData() {
        setUpLoadDataVegatable();
    }


//Vegatable

    /**
     * Set up data vegatable
     */
    private void setUpLoadDataVegatable() {
        SPTable.setModel(toTableModelVegetable(vegetableBLL.getVegetableList()));
    }

    /**
     * Set List vegatable to table model
     * @param list
     * @return TableModel
     */
    private TableModel toTableModelVegetable(List<Vegetable> list) {
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

    /**
     * Button reset vegatable
     */
    private void setResetButtonVegetable() {
        resetButtonSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUpLoadDataVegatable();
            }
        });
    }

    /**
     * Cobobox vegatable
     */
    private void setComboBoxVegatable() {
        SearchSPComboBox.addItem("id");
        SearchSPComboBox.addItem("Name");
        categorytComboBox.setModel(new CategoryCombobox(categoryBLL.convertList(categoryBLL.loadCategory())));

        categorytComboBox.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Category s = (Category) categorytComboBox.getModel().getSelectedItem();
                    SPTable.setModel(toTableModelVegetable(vegetableBLL.getListWithCategory(s.getCatagoryID())));
                }
                catch (ClassCastException ex) {
                    mess.message("Vegetables with category", "againt choose combobox category !!!");
                }
            }
        });
    }

    /**
     * Set searchButton vegatable
     */
    private void setSearchButtonVegatable() {
        searchButtonSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer select = SearchSPComboBox.getSelectedIndex();
                if (select != null && select > -1) {
                    String textSearch = searchSPTextField.getText();
                    List<Vegetable> list = vegetableBLL.getSearch(textSearch, select);
                    if (!list.isEmpty()) {
                        SPTable.setModel(toTableModelVegetable(list));
                    }
                }
            }
        });
    }

}
