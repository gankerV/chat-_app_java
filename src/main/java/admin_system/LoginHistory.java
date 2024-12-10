package admin_system;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import admin_system.bus.UserAccountBUS;
import admin_system.bus.LoginHistoryBUS;
import admin_system.dto.LoginHistoryDTO;
import admin_system.dto.UserAccountDTO;

public class LoginHistory extends JPanel {

    private UserAccountBUS userBUS;
    private LoginHistoryBUS loginBUS;

    public LoginHistory() {

        userBUS = new UserAccountBUS();
        loginBUS = new LoginHistoryBUS();

        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Login History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Lấy dữ liệu lịch sử đăng nhập từ UserAccountBUS
        List<LoginHistoryDTO> loginHistoryList = loginBUS.viewAllLoginHistory(); // Lấy tất cả lịch sử đăng nhập

        // Nếu không có dữ liệu, hiển thị thông báo
        if (loginHistoryList == null || loginHistoryList.isEmpty()) {
            JLabel noDataLabel = new JLabel("No login history available.");
            noDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(noDataLabel, BorderLayout.CENTER);
            return;
        }

        // Cột trong bảng
        String[] columnNames = {"Thời gian", "Tên đăng nhập", "Họ tên"};

        // Tạo mảng dữ liệu cho bảng
        Object[][] data = new Object[loginHistoryList.size()][3];

        // Lặp qua danh sách lịch sử đăng nhập và lấy thông tin người dùng để điền vào bảng
        for (int i = 0; i < loginHistoryList.size(); i++) {
            LoginHistoryDTO history = loginHistoryList.get(i);

            // Lấy thông tin người dùng từ UserAccountBUS
            UserAccountDTO user = userBUS.getUserById(history.getUserId());

            // Nếu không tìm thấy người dùng, bỏ qua bản ghi này
            if (user != null) {
                data[i][0] = history.getLoginTime();  // Thời gian đăng nhập
                data[i][1] = user.getUsername();      // Tên đăng nhập
                data[i][2] = user.getFullname();      // Họ tên
            }
        }

        // Tạo DefaultTableModel và JTable
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable loginHistoryTable = new JTable(model);

        // Làm bảng không thể chỉnh sửa
        loginHistoryTable.setDefaultEditor(Object.class, null);

        // Đưa bảng vào JScrollPane để có thể cuộn
        JScrollPane scrollPane = new JScrollPane(loginHistoryTable);

        // Thêm các thành phần vào panel
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
