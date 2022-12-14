package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JButton nhapHangButton;
    private JButton kHButton;
    private JButton thongKeButton;
    private JPanel jPanel1;

    public Menu() {
        initMenu();
    }

    private void initMenu() {
        setContentPane(jPanel1);
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setUpKHButton();
        setUpNhapHangButton();
        setUpThongKe();
    }

    private void setUpKHButton() {
        kHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomersGui customersGui = new CustomersGui();
            }
        });
    }

    private void setUpNhapHangButton(){
        nhapHangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhapHangGui nhapHangGui = new NhapHangGui();
            }
        });
    }

    private void setUpThongKe() {
        thongKeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThongKeGui thongKeGui = new ThongKeGui();
            }
        });
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setVisible(true);
    }
}
