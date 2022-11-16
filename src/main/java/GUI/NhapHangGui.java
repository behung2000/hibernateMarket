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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private Mess mess;

    public NhapHangGui() {
        vegetableBLL = new VegetableBLL();
        categoryBLL = new CategoryBLL();
        mess = new Mess();
        initNhapHangGui();
    }

    private void initNhapHangGui() {
        setContentPane(jPanel1);
        setSize(700, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        setClickTable();

        setUpData();

        setCategoryCombobox();
        setUnitComboBox();
        setComboBox1();

        setResetButton();
        setVegetablesWithCategoryButton();
        setSearchButton();
        setAddButton();
        setDeleteButton();
        setUpdateButton();
    }

    private void setUpData() {
        table1.setModel(toTableModel(vegetableBLL.getVegetableList()));
    }

    private void setClickTable() {
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Integer row = table1.getSelectedRow();
                if (row != null && row > -1) {
                    String name = table1.getModel().getValueAt(row, 1).toString();
                    String nameCategory = table1.getModel().getValueAt(row, 2).toString();
                    String unit = table1.getModel().getValueAt(row, 3).toString();
                    Integer amount = Integer.parseInt(table1.getModel().getValueAt(row, 4).toString());
                    String image = table1.getModel().getValueAt(row, 5).toString();
                    Double price = Double.parseDouble(table1.getModel().getValueAt(row, 6).toString());

                    textField1.setText(name);
                    categoryCombobox.setSelectedItem(nameCategory);
                    unitComboBox.setSelectedItem(unit);
                    textField2.setText(String.format("%s", amount));
                    textField3.setText(image);
                    textField4.setText(String.format("%s", price));
                }
            }
        });
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
        categoryCombobox.setModel(new CategoryCombobox(categoryBLL.convertList(categoryBLL.loadCategory())));
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

    private void setComboBox1() {
        comboBox1.addItem("id");
        comboBox1.addItem("Name");
    }

    private void setSearchButton() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer select = comboBox1.getSelectedIndex();
                if (select != null && select > -1) {
                    String textSearch = textField5.getText();
                    List<Vegetable> list = vegetableBLL.getSearch(textSearch, select);
                    if (!list.isEmpty()) {
                        table1.setModel(toTableModel(list));
                    }
                }
            }
        });
    }

    private void setUnitComboBox() {
        unitComboBox.addItem("Kg");
        unitComboBox.addItem("Bag");
        unitComboBox.addItem("per fruit");
        unitComboBox.addItem("bunch");
    }

    private Vegetable getTextField() {
        String name = textField1.getText();
        Category category = (Category) categoryCombobox.getModel().getSelectedItem();
        String unit = unitComboBox.getSelectedItem().toString();
        Integer amount = null;
        String image = textField3.getText();
        Double price = null;
        int i = 0;
        try {
            amount = Integer.parseInt(textField2.getText());
            i++;
            price = Double.parseDouble(textField4.getText());
        }
        catch (NumberFormatException e) {
            if (i==0) {
                mess.message("TextFiled", "amount must be number");
            }
            else {
                mess.message("TextFiled", "price must be number");
            }
            return null;
        }
        if(name.trim().length()==0 || name.trim().isEmpty() ||
                unit.trim().isEmpty() || unit.trim().length()==0 ||
                image.trim().isEmpty() || image.trim().length()==0) {
            mess.message("Text Field", "Some text filed is empty");
            return null;
        }
        return Vegetable.builder()
            .VegetableName(name)
            .catagory(category)
            .Unit(unit)
            .Amount(amount)
            .Image(image)
            .Price(price)
            .build();
    }

    private void setAddButton() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vegetable vegetable = getTextField();
                if (vegetable != null) {
                    vegetableBLL.save(vegetable);
                    setUpData();
                }
            }
        });
    }

    private void setDeleteButton() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer row = table1.getSelectedRow();
                if (row != null && row > -1) {
                    Integer id = Integer.parseInt(table1.getModel().getValueAt(row,0).toString());
                    vegetableBLL.delete(id);
                    setUpData();
                }
                else {
                    mess.message("Delete vegetable", "No select to vegetable");
                }
            }
        });
    }



    private void setUpdateButton() {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
