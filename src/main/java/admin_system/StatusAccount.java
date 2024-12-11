package admin_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import admin_system.bus.UserStatusBUS;
import admin_system.dto.UserStatusDTO;

public class StatusAccount extends JPanel {
    private JComboBox<String> comboBox1;
    private JTable accountList;
    private String selectedOrder = "";
    private String username = "";
    private int loggedin = 0;
    private UserStatusBUS userStatusBUS;
    private DefaultTableModel model;

    public StatusAccount() {

        userStatusBUS = new UserStatusBUS();

        setLayout(new BorderLayout());

        // Content Panel with Titled Border
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Management Status Account",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                Color.BLUE
        ));

        // Tạo topPanel chứa Add button và Filter
        JPanel topPanel = new JPanel(new BorderLayout());

        JTextField searchNameField = new JTextField(15);
        JLabel searchNameLabel = new JLabel("Search by name:");
        JTextField searchLoggedField = new JTextField(15);
        JLabel searchLoggedLabel = new JLabel("Search by logged in:");

        // Panel trái chứa các trường tìm kiếm
        JPanel leftPanel = new JPanel(new GridLayout(2, 2)); // 2 hàng, 2 cột
        leftPanel.add(searchNameLabel);
        leftPanel.add(searchNameField);
        leftPanel.add(searchLoggedLabel);
        leftPanel.add(searchLoggedField);
        topPanel.add(leftPanel, BorderLayout.WEST);

        // Panel phải chứa "Order by"
        JLabel orderLabel = new JLabel("Order by:");
        comboBox1 = new JComboBox<>(new String[]{"Username", "Created at"});

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(orderLabel);
        rightPanel.add(comboBox1);
        topPanel.add(rightPanel, BorderLayout.EAST);

        contentPanel.add(topPanel, BorderLayout.NORTH); // Thêm topPanel vào contentPanel

        // Panel cho chọn khoảng thời gian và chart
        JPanel timePanel = new JPanel(new BorderLayout());

        // Panel chọn ngày
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel startDateLabel = new JLabel("Start Date:");
        JTextField startDateField = new JTextField(10);
        JLabel endDateLabel = new JLabel("End Date:");
        JTextField endDateField = new JTextField(10);
        JButton filterButton = new JButton("Filter");

        datePanel.add(startDateLabel);
        datePanel.add(startDateField);
        datePanel.add(endDateLabel);
        datePanel.add(endDateField);
        datePanel.add(filterButton);

        timePanel.add(datePanel, BorderLayout.WEST);

        contentPanel.add(timePanel, BorderLayout.CENTER); // Thêm timePanel vào contentPanel
        add(contentPanel, BorderLayout.CENTER);

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy giá trị được chọn trong JComboBox
                selectedOrder = (String) comboBox1.getSelectedItem();
            }
        });

        // Panel chứa nút Chart
        JPanel chartPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton chartButton = new JButton("Chart");
        chartPanel.add(chartButton);
        timePanel.add(chartPanel, BorderLayout.EAST);

        contentPanel.add(timePanel, BorderLayout.CENTER); // Thêm timePanel vào contentPanel
        add(contentPanel, BorderLayout.CENTER);

        // Khởi tạo JTable
        String[] columnNames = {"ID", "Username", "Created at", "Number logged", "Number people chat with", "Number group chat with"};
        model = new DefaultTableModel(columnNames, 0);
        accountList = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(accountList);

        contentPanel.add(scrollPane, BorderLayout.SOUTH); // Thêm scrollPane vào contentPanel

        searchNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = searchNameField.getText().trim(); // trim để loại bỏ khoảng trắng của chuỗi
            }
        });

        searchLoggedField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String loggedText = searchLoggedField.getText().trim();
                    loggedin = loggedText.isEmpty() ? 0 : Integer.parseInt(loggedText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for logged in.");
                }
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse input dates
                    String startDateText = startDateField.getText().trim();
                    String endDateText = endDateField.getText().trim();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Timestamp startTime = new Timestamp(dateFormat.parse(startDateText).getTime());
                    Timestamp endTime = new Timestamp(dateFormat.parse(endDateText).getTime());

                    // Fetch user list
                    List<UserStatusDTO> userList = userStatusBUS.getUserStatusByTimes(startTime, endTime, selectedOrder, username, loggedin);

                    // Update table
                    updateTableData(userList);

                } catch (IllegalArgumentException | ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy-MM-dd HH:mm:ss.");
                }
            }
        });

        chartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showChartPanel();
            }
        });
        
    }

    private void updateTableData(List<UserStatusDTO> userList) {
        // Xóa tất cả các dòng cũ trong model
        model.setRowCount(0);

        // Thêm dữ liệu mới vào model
        for (UserStatusDTO user : userList) {
            Object[] row = {
                user.getId(),
                user.getUsername(),
                user.getCreatedAt(),
                user.getNumLogged(),
                user.getNumChatUser(),
                user.getNumChatGroup()
            };
            model.addRow(row);
        }
    }

    private void showChartPanel() {
        // Tạo panel chứa JTextField để nhập năm
        JPanel inputPanel = new JPanel();
        JLabel yearLabel = new JLabel("Enter Year:");
        JTextField yearField = new JTextField(10);
    
        inputPanel.add(yearLabel);
        inputPanel.add(yearField);
    
        // Tạo JFrame để chứa inputPanel
        JFrame chartFrame = new JFrame("Logged in Chart");
        chartFrame.setLayout(new BorderLayout());
        chartFrame.setSize(800, 600);
        chartFrame.setLocationRelativeTo(null);
    
        // Thêm inputPanel vào phía trên (North) của chartFrame
        chartFrame.add(inputPanel, BorderLayout.NORTH);
    
        // Tạo ChartPanel trống để chứa biểu đồ (sẽ được cập nhật sau khi người dùng nhập năm)
        JPanel chartContainer = new JPanel();
        chartFrame.add(chartContainer, BorderLayout.CENTER);
    
        // Thêm listener khi người dùng nhập vào trường năm (yearField)
        yearField.addActionListener(e -> {
            String yearInput = yearField.getText();
            if (!yearInput.isEmpty()) {
                try {
                    int year = Integer.parseInt(yearInput); // Chuyển đổi năm từ chuỗi sang số nguyên
    
                    // Create startTime and endTime based on the user input year
                    LocalDateTime startOfYear = LocalDateTime.of(year, 1, 1, 0, 0, 0, 0);
                    LocalDateTime endOfYear = LocalDateTime.of(year, 12, 31, 23, 59, 59, 999999999);
                    Timestamp startTime = Timestamp.valueOf(startOfYear);
                    Timestamp endTime = Timestamp.valueOf(endOfYear);
    
                    // Gọi hàm từ userStatusBUS để lấy số lần đăng nhập trong năm
                    Map<Integer, Integer> loggedMap = userStatusBUS.getNumLoggedByTime(startTime, endTime);
    
                    // Hiển thị biểu đồ với năm người dùng nhập
                    showBarChart(chartContainer, loggedMap, year);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(chartFrame, "Please enter a valid year.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(chartFrame, "Please enter a year.", "Empty Input", JOptionPane.WARNING_MESSAGE);
            }
        });
    
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.setVisible(true);
    }
    
    private void showBarChart(JPanel chartContainer, Map<Integer, Integer> loggedMap, int year) {
        // Dữ liệu cho biểu đồ
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
        // Mảng chứa số lượng đăng ký mỗi tháng
        for (int month = 1; month <= 12; month++) {
            int loginCount = loggedMap.getOrDefault(month, 0); // Get login count for each month (0 if not available)
            dataset.addValue(loginCount, "Logged", "Month " + month);
        }
    
        // Tạo biểu đồ cột
        JFreeChart barChart = ChartFactory.createBarChart(
                "Logged in of User " + year,  // Tiêu đề biểu đồ
                "Month",  // Trục X
                "Number of Logged",  // Trục Y
                dataset  // Dữ liệu
        );
    
        // Tạo ChartPanel từ biểu đồ
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
    
        // Xóa biểu đồ hiện tại trong chartContainer nếu có
        chartContainer.removeAll();
        chartContainer.add(chartPanel, BorderLayout.CENTER);
        chartContainer.revalidate();
        chartContainer.repaint();
    }
    

}

    
