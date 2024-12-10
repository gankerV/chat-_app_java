package admin_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import admin_system.bus.ReportBUS;
import admin_system.bus.UserAccountBUS;
import admin_system.dto.ReportDTO;
import admin_system.dto.UserAccountDTO;

public class Report extends JPanel {
    private JComboBox<String> comboBox1;
    private JTable accountList;
    private DefaultTableModel model;
    private UserAccountBUS userBUS;
    private ReportBUS reportBUS;

    public Report() {

        userBUS = new UserAccountBUS();
        reportBUS = new ReportBUS();
        
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
        JTextField searchNameField = new JTextField(15);
        JLabel searchNameLabel = new JLabel("Search for reporter:");
        JTextField searchDateField = new JTextField(15);
        JLabel searchDateLabel = new JLabel("Search for time:");

        // Tạo panel để chứa các thành phần tìm kiếm trong cùng một cột (bên trái)
        JPanel leftPanel = new JPanel(new GridLayout(2, 2)); // 2 hàng, 2 cột
        leftPanel.add(searchNameLabel);
        leftPanel.add(searchNameField);
        leftPanel.add(searchDateLabel);
        leftPanel.add(searchDateField);
        topPanel.add(leftPanel, BorderLayout.WEST);


        JLabel orderLabel = new JLabel("Order by:");
        comboBox1 = new JComboBox<>(new String[]{"ID","Reporter", "Reported at"});

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(orderLabel);
        rightPanel.add(comboBox1);
        topPanel.add(rightPanel, BorderLayout.EAST);

        contentPanel.add(topPanel, BorderLayout.NORTH);


        // Khởi tạo JTable
        String[] columnNames = {"ID", "Reporter", "Reported", "Content", "Created at", "ReporterID", "ReportedID"};
        model = new DefaultTableModel(columnNames, 0);
        accountList = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(accountList);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        updateTableData(reportBUS.getAllReports("ID"));

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

        // Hoàn thiện giao diện
        add(contentPanel, BorderLayout.CENTER);

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Lấy giá trị được chọn trong JComboBox
                String selectedOrder = (String) comboBox1.getSelectedItem();
                // Gọi hàm getAll với giá trị được chọn và cập nhật dữ liệu
                updateTableData(reportBUS.getAllReports(selectedOrder));
            }
        });


        searchNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reporterName = searchNameField.getText().trim();
                if (!reporterName.isEmpty()) {
                    // Gọi hàm tìm kiếm theo tên người báo cáo

                    updateTableData(reportBUS.getByReporter(reporterName));
                } else {
                    // Nếu ô tìm kiếm trống, hiển thị tất cả dữ liệu
                    updateTableData(reportBUS.getAllReports("ID"));
                }
            }
        });
        
        searchDateField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateText = searchDateField.getText().trim();
                if (!dateText.isEmpty()) {
                    try {
                        // Chuyển đổi chuỗi thành Timestamp (giả sử định dạng là yyyy-MM-dd)
                        Timestamp reportTime = Timestamp.valueOf(dateText);
                        // Gọi hàm tìm kiếm theo thời gian báo cáo
                        updateTableData(reportBUS.getByReportTime(reportTime));
                    } catch (IllegalArgumentException ex) {
                        ex.printStackTrace();
                        // Nếu có lỗi về định dạng ngày giờ, thông báo cho người dùng
                        System.out.println("Invalid date format. Please use yyyy-MM-dd hh-mm-ss.");
                    }
                }else {
                    // Nếu ô tìm kiếm trống, hiển thị tất cả dữ liệu
                    updateTableData(reportBUS.getAllReports("ID"));
                }
            }
        });

        banButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = accountList.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy ID người dùng từ bảng (cột thứ 2 là ID người bị tố cáo)
                    int reportedUserId = (int) model.getValueAt(selectedRow, 6); 
        
                    // Thực hiện hành động cấm người dùng
                    boolean success = userBUS.banUser(reportedUserId, true);
        
                    // Hiển thị thông báo cho người dùng
                    if (success) {
                        JOptionPane.showMessageDialog(null, "User banned successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to ban user.");
                    }
                }
            }
        });
    }

    private void updateTableData(List<ReportDTO> reprotDTOs) {
        // Xóa tất cả các dòng cũ trong model
        model.setRowCount(0);

        for (int i = 0; i < reprotDTOs.size(); i++){
            ReportDTO report = reprotDTOs.get(i);

            UserAccountDTO userReporter = userBUS.getUserById(report.getReporterId());
            UserAccountDTO userReported = userBUS.getUserById(report.getReportedId());

            // Nếu không tìm thấy người dùng, bỏ qua bản ghi này
            if (userReporter != null && userReported != null) {
                Object[] row = {
                    report.getId(),
                    userReporter.getUsername(),
                    userReported.getUsername(),
                    report.getReportAt(),
                    report.getContent(),
                    report.getReporterId(),    
                    report.getReportedId()
                };
                model.addRow(row);
            }
        }
        // ẩn 2 cột ID cuối 
        accountList.getColumnModel().getColumn(5).setMaxWidth(0);
        accountList.getColumnModel().getColumn(5).setMinWidth(0);
        accountList.getColumnModel().getColumn(5).setPreferredWidth(0);

        accountList.getColumnModel().getColumn(6).setMaxWidth(0);
        accountList.getColumnModel().getColumn(6).setMinWidth(0);
        accountList.getColumnModel().getColumn(6).setPreferredWidth(0);
    }
}
