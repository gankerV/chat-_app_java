package admin_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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

import admin_system.bus.UserAccountBUS;
import admin_system.dto.UserAccountDTO;
public class NewRegistration extends JPanel {
    private JComboBox<String> comboBox1;
    private JTable accountList;
    private DefaultTableModel model;
    private UserAccountBUS userBUS;
    private String selectedOrder;
    private String username;
    
    public NewRegistration() {

        userBUS = new UserAccountBUS();
        
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Management New Registration",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                Color.BLUE
        ));

        // Tạo topPanel chứa Search field và Order by
        JPanel topPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField(10);
        JLabel searchLabel = new JLabel("Search:");

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(searchLabel);
        leftPanel.add(searchField);

        topPanel.add(leftPanel, BorderLayout.WEST);

        JLabel orderLabel = new JLabel("Order by:");
        comboBox1 = new JComboBox<>(new String[]{"Username", "Created at"});
        selectedOrder = comboBox1.getItemAt(0); // mặc định kiểu sắp xếp
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(orderLabel);
        rightPanel.add(comboBox1);
        topPanel.add(rightPanel, BorderLayout.EAST);

        contentPanel.add(topPanel,BorderLayout.NORTH);

        // tạo fillter chọn khoảng ngày và chart
        JPanel timePanel = new JPanel(new BorderLayout());

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

        timePanel.add(datePanel,BorderLayout.WEST);

        JPanel chartPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton chartButton = new JButton("Chart");
        chartPanel.add(chartButton);
        timePanel.add(chartPanel,BorderLayout.EAST);

        contentPanel.add(timePanel, BorderLayout.CENTER);

        // Khởi tạo JTable
        String[] columnNames = {"ID", "Username", "Name", "Email", "Status", "Banned"};
        model = new DefaultTableModel(columnNames, 0);
        accountList = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(accountList);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy giá trị được chọn trong JComboBox
                selectedOrder = (String) comboBox1.getSelectedItem();
            }
        });

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = searchField.getText().trim(); // trim để loại bỏ khoảng trắng của chuỗi
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
                    List<UserAccountDTO> users = userBUS.getUserByTimes(startTime, endTime,selectedOrder,username);

                    // Update table
                    updateTableData(users);

                } catch (IllegalArgumentException | ParseException ex) {
                    ex.printStackTrace();
                    // Nếu có lỗi về định dạng ngày giờ, thông báo cho người dùng
                    System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm:ss.");
                }
            }
        });
        
        chartButton.addActionListener(e -> {
            showChartPanel();
        });
    }

    private void updateTableData(List<UserAccountDTO> userDTOs) {
        // Xóa tất cả các dòng cũ trong model
        model.setRowCount(0);

        // Thêm dữ liệu mới vào model
        for (UserAccountDTO user : userDTOs) {
            Object[] row = {
                user.getId(),
                user.getUsername(),
                user.getFullname(),
                user.getEmail(),
                user.isOnOff() ? "Online" : "Offline",
                user.isBanned() ? "Yes" : "No"
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
        JFrame chartFrame = new JFrame("Registration Chart");
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
                    // Hiển thị biểu đồ với năm người dùng nhập
                    showBarChart(chartContainer, year);
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

    private void showBarChart(JPanel chartContainer, int year) {
        // Dữ liệu giả lập cho biểu đồ
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Random random = new Random();

        for (int month = 1; month <= 12; month++) {
            int value = random.nextInt(50); // Số lượng người đăng ký ngẫu nhiên
            dataset.addValue(value, "Registrations", "Month " + month);
        }

        // Tạo biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "New Registrations in " + year,
                "Month",
                "Number of Registrations",
                dataset
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
