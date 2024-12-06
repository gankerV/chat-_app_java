package admin_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Report extends JPanel {
    private JComboBox<String> comboBox1;
    private JTable accountList;


    public Report() {

        setLayout(new BorderLayout());


        // Content Panel with Titled Border
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Management Report",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                Color.BLUE
        ));

        // Tạo topPanel chứa Add button và Filter
        JPanel topPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField(20);
        JLabel searchLabel = new JLabel("Search:");
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(searchLabel);
        leftPanel.add(searchField);
        topPanel.add(leftPanel, BorderLayout.WEST);

        JLabel orderLabel = new JLabel("Order by:");
        comboBox1 = new JComboBox<>(new String[]{"Tên đăng nhập", "Ngày tạo"});

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(orderLabel);
        rightPanel.add(comboBox1);
        topPanel.add(rightPanel, BorderLayout.EAST);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        // Khởi tạo JTable
        String[] columnNames = {"ID","Người tố cáo","Người bị tố cáo", "Ngày tạo", "Nội dung"};
        Object[][] data = {{"1", "Xanh1","Tín1", "2024-11-15","Spam tin nhắn"}};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        accountList = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(accountList);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Thêm nút cho bảng
        //addButton.addActionListener(e -> showAddDialog(model));

        // Tạo buttonPanel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton banButton = new JButton("Ban action");
        buttonPanel.add(banButton);
        buttonPanel.setVisible(false);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Hiển thị buttonPanel khi chọn hàng
        accountList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && accountList.getSelectedRow() != -1) {
                buttonPanel.setVisible(true);
            }
        });

        add(contentPanel, BorderLayout.CENTER);
    }

}
