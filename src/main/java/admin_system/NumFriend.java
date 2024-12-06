package admin_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class NumFriend extends JPanel {
    private JComboBox<String> comboBox1;
    private JTable accountList;


    public NumFriend() {

        setLayout(new BorderLayout());


        // Content Panel with Titled Border
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Management Number Friend",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                Color.BLUE
        ));

        // Tạo topPanel chứa Add button và Filter
        JPanel topPanel = new JPanel(new BorderLayout());

        JTextField searchNameField = new JTextField(15);
        JLabel searchNameLabel = new JLabel("Search by name:");
        JTextField searchDateField = new JTextField(15);
        JLabel searchDateLabel = new JLabel("Search by number direct friend:");

// Tạo panel để chứa các thành phần tìm kiếm trong cùng một cột (bên trái)
        JPanel leftPanel = new JPanel(new GridLayout(2, 2)); // 2 hàng, 2 cột
        leftPanel.add(searchNameLabel);
        leftPanel.add(searchNameField);
        leftPanel.add(searchDateLabel);
        leftPanel.add(searchDateField);
        topPanel.add(leftPanel, BorderLayout.WEST);

        JLabel orderLabel = new JLabel("Order by:");
        comboBox1 = new JComboBox<>(new String[]{"Họ tên", "Ngày tạo"});

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(orderLabel);
        rightPanel.add(comboBox1);
        topPanel.add(rightPanel, BorderLayout.EAST);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        // Khởi tạo JTable
        String[] columnNames = {"ID","Tên đăng nhập","Họ tên", "Ngày tạo", "Số lượng bạn bè", "Số lượng bạn bè chung"};
        Object[][] data = {{"1", "Xanh1","Nguyen Van Xanh", "2024-11-15","10","2"}};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        accountList = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(accountList);
        contentPanel.add(scrollPane, BorderLayout.CENTER);



        add(contentPanel, BorderLayout.CENTER);
    }

}
