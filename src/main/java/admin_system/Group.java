package admin_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import admin_system.bus.GroupChatBUS;
import admin_system.bus.UserAccountBUS;
import admin_system.dto.GroupChatDTO;
import admin_system.dto.UserAccountDTO;

public class Group extends JPanel {
    private JComboBox<String> comboBox1;
    private JTable groupList;
    private DefaultTableModel model;
    private GroupChatBUS groupBUS;
    private UserAccountBUS userBUS;

    public Group() {

        groupBUS = new GroupChatBUS();

        setLayout(new BorderLayout());
        

        // Content Panel with Titled Border
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Management Group",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                Color.BLUE
        ));

        // Tạo topPanel chứa Add button và Filter
        JPanel topPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField(20);
        JLabel searchLabel = new JLabel("Search by name:");
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(searchLabel);
        leftPanel.add(searchField);
        topPanel.add(leftPanel, BorderLayout.WEST);

        JLabel orderLabel = new JLabel("Order by:");
        comboBox1 = new JComboBox<>(new String[]{"ID", "Group Name", "Created at"});

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(orderLabel);
        rightPanel.add(comboBox1);
        topPanel.add(rightPanel, BorderLayout.EAST);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        // Khởi tạo JTable
        String[] columnNames = {"ID", "Group Name", "Created at"};
        model = new DefaultTableModel(columnNames, 0);
        groupList = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(groupList);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Lấy dữ liệu ban đầu theo "ID"
        updateTableData(groupBUS.getAllGroupChats("ID"));
        
        // Thêm nút cho bảng
        //addButton.addActionListener(e -> showAddDialog(model));

        // Tạo buttonPanel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton memberButton = new JButton("List member");
        JButton adminButton = new JButton("List admin");
        buttonPanel.add(memberButton);
        buttonPanel.add(adminButton);
        buttonPanel.setVisible(false);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Hiển thị buttonPanel khi chọn hàng
        groupList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && groupList.getSelectedRow() != -1) {
                buttonPanel.setVisible(true);
            }
        });

        // Thêm xử lý cho nút memberButton
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = groupList.getSelectedRow();
                if (selectedRow != -1) {
                    int groupID = (int) model.getValueAt(selectedRow, 0); // Lấy ID nhóm
                    List<Integer> adminIDs = groupBUS.getIDByPosition(groupID, "Admin"); // Lấy danh sách admin
                    showList(adminIDs, "Admin"); // Truyền danh sách admin vào showList
                }
            }
        });
        
        memberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = groupList.getSelectedRow();
                if (selectedRow != -1) {
                    int groupID = (int) model.getValueAt(selectedRow, 0); // Lấy ID nhóm
                    List<Integer> memberIDs = groupBUS.getIDByPosition(groupID, "Member"); // Lấy danh sách member
                    showList(memberIDs, "Member"); // Truyền danh sách member vào showList
                }
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy giá trị được chọn trong JComboBox
                String selectedOrder = (String) comboBox1.getSelectedItem();
                // Gọi hàm getAll với giá trị được chọn và cập nhật dữ liệu
                updateTableData(groupBUS.getAllGroupChats(selectedOrder));
            }
        });

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupName = searchField.getText().trim(); // trim để loại bỏ khoảng trắng của chuỗi
                if (!groupName.isEmpty()) {
                    // Gọi phương thức groupBUS.getByGroupName để lấy dữ liệu
                    List<GroupChatDTO> searchResults = groupBUS.getByGroupName(groupName);
                    updateTableData(searchResults); // Cập nhật bảng với kết quả tìm kiếm
                } else {
                    // Nếu ô tìm kiếm trống, hiển thị tất cả dữ liệu
                    updateTableData(groupBUS.getAllGroupChats("ID"));
                }
            }
        });

        add(contentPanel, BorderLayout.CENTER);
    }

    private void updateTableData(List<GroupChatDTO> groupChatDTOs) {
        // Xóa tất cả các dòng cũ trong model
        model.setRowCount(0);

        // Thêm dữ liệu mới vào model
        for (GroupChatDTO groupChat : groupChatDTOs) {
            Object[] row = {
                groupChat.getId(),
                groupChat.getGroupName(),
                groupChat.getCreatedAt()
            };
            model.addRow(row);
        }
    }

    private void showList(List<Integer> memberIDs, String position) {

        userBUS = new UserAccountBUS();

        // Tạo một dialog để hiển thị danh sách thành viên/admin
        JDialog groupMembersDialog = new JDialog();
        groupMembersDialog.setLayout(new BorderLayout());

        // Tạo dữ liệu cho JTable
        Object[][] data = new Object[memberIDs.size()][3];
        for (int i = 0; i < memberIDs.size(); i++) {
            int memberID = memberIDs.get(i);

            // Lấy thông tin từ UserAccountDTO thông qua userBUS
            UserAccountDTO user = userBUS.getUserById(memberID);

            // Nếu tìm thấy thông tin, thêm vào bảng
            if (user != null) {
                data[i][0] = user.getId();        // ID thành viên
                data[i][1] = user.getUsername(); // Username
                data[i][2] = position;           // Chức vụ (Admin/Member)
            }
        }

        // Tạo bảng và model
        String[] columnNames = {"ID", "Username", "Position"};
        DefaultTableModel groupModel = new DefaultTableModel(data, columnNames);
        JTable groupTable = new JTable(groupModel);

        // Thêm bảng vào scroll pane
        JScrollPane scrollPane = new JScrollPane(groupTable);
        groupMembersDialog.add(scrollPane, BorderLayout.CENTER);

        // Tạo nút đóng
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> groupMembersDialog.dispose());
        groupMembersDialog.add(closeButton, BorderLayout.SOUTH);

        // Cài đặt thuộc tính dialog
        groupMembersDialog.setSize(400, 300);
        groupMembersDialog.setLocationRelativeTo(this);
        groupMembersDialog.setVisible(true);
    }
}
