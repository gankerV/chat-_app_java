package chat_system;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import chat_system.dao.MessageDAO;
import chat_system.dao.UserAccountDAO;
import chat_system.dto.User;
import chat_system.dto.UserAccount;

public class ChatHome extends javax.swing.JFrame {
    private int SelectedUserId = -1;
    DefaultListModel<User> listModel ;

    ChatClient chatClient= null;

    public ChatHome() { 
        initComponents();
        listModel = new DefaultListModel<>();
       
    }
    
    public void addEventBackLogin(ActionListener event) {
        cmdLogout.addActionListener(event);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        popupMenu2 = new java.awt.PopupMenu();
        popupMenu3 = new java.awt.PopupMenu();
        popupMenu4 = new java.awt.PopupMenu();
        jLabel1 = new javax.swing.JLabel();
        list_all_friend = new javax.swing.JButton();
        list_online = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Search_Button = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        profile_button = new javax.swing.JButton();
        group_button = new javax.swing.JButton();
        cmdLogout = new javax.swing.JButton();
        list_block = new javax.swing.JButton();
        list_request = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        List_result = new javax.swing.JList<>();
        inputMess = new swing.MyTextField();
        sentMess_Button = new swing.MyButton();
        remove_all_chat = new javax.swing.JButton();
        remove_row_chat = new javax.swing.JButton();
        list_offline = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayChatHistory = new javax.swing.JTextArea();
        searchWithUserButton = new javax.swing.JButton();
        searchAllButton = new javax.swing.JButton();

        popupMenu1.setLabel("popupMenu1");

        popupMenu2.setLabel("popupMenu2");

        popupMenu3.setLabel("popupMenu3");

        popupMenu4.setLabel("popupMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Friend List");

        list_all_friend.setBackground(new java.awt.Color(204, 204, 204));
        list_all_friend.setText("All");
        list_all_friend.setMaximumSize(new java.awt.Dimension(70, 20));
        list_all_friend.setMinimumSize(new java.awt.Dimension(70, 20));
        list_all_friend.setPreferredSize(new java.awt.Dimension(70, 20));
        list_all_friend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list_all_friendActionPerformed(evt);
            }
        });

        list_online.setBackground(new java.awt.Color(204, 204, 204));
        list_online.setText("Online");
        list_online.setPreferredSize(new java.awt.Dimension(70, 20));
        list_online.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list_onlineActionPerformed(evt);
            }
        });

        jLabel3.setText("Username");

        Search_Button.setBackground(new java.awt.Color(204, 204, 204));
        Search_Button.setText("Search");
        Search_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search_ButtonActionPerformed(evt);
            }
        });

        jToolBar1.setBackground(new java.awt.Color(255, 0, 0));
        jToolBar1.setRollover(true);

        profile_button.setBackground(new java.awt.Color(255, 153, 102));
        profile_button.setText("My profile");
        profile_button.setFocusable(false);
        profile_button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        profile_button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        profile_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profile_buttonActionPerformed(evt);
            }
        });
        jToolBar1.add(profile_button);

        group_button.setBackground(new java.awt.Color(255, 153, 102));
        group_button.setText("Group");
        group_button.setFocusable(false);
        group_button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        group_button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        group_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                group_buttonActionPerformed(evt);
            }
        });
        jToolBar1.add(group_button);

        cmdLogout.setBackground(new java.awt.Color(255, 153, 102));
        cmdLogout.setText("Log out");
        cmdLogout.setFocusable(false);
        cmdLogout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdLogout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLogoutActionPerformed(evt);
            }
        });
        jToolBar1.add(cmdLogout);

        list_block.setBackground(new java.awt.Color(204, 204, 204));
        list_block.setText("Block");
        list_block.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list_blockActionPerformed(evt);
            }
        });

        list_request.setBackground(new java.awt.Color(204, 204, 204));
        list_request.setText("Friend Request List");
        list_request.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list_requestActionPerformed(evt);
            }
        });

        List_result.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                onUserSelected(evt);
            }
        });
        jScrollPane1.setViewportView(List_result);

        sentMess_Button.setBackground(new java.awt.Color(255, 0, 0));
        sentMess_Button.setText("Send");
        sentMess_Button.addActionListener(e -> {
            String message = inputMess.getText(); // Lấy nội dung từ myTextField2
            if (!message.isEmpty()) {
                if (chatClient != null) { // Kiểm tra chatClient đã được khởi tạo
                    chatClient.sendMessage(message); // Gửi tin nhắn qua chatClient
                    inputMess.setText(""); // Xóa trường nhập liệu sau khi gửi
                } else {
                    JOptionPane.showMessageDialog(null, "Chưa kết nối đến người dùng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        remove_all_chat.setText("Remove history chat");
        remove_all_chat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_all_chatActionPerformed(evt);
            }
        });

        remove_row_chat.setText("Remove current chat");
        remove_row_chat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_row_chatActionPerformed(evt);
            }
        });

        list_offline.setBackground(new java.awt.Color(204, 204, 204));
        list_offline.setText("Offline");
        list_offline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list_offlineActionPerformed(evt);
            }
        });

        displayChatHistory.setColumns(20);
        displayChatHistory.setRows(5);
        jScrollPane2.setViewportView(displayChatHistory);

        searchWithUserButton.setBackground(new java.awt.Color(204, 204, 204));
        searchWithUserButton.setText("SearchChatUser");
        searchWithUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchWithUserButtonActionPerformed(evt);
            }
        });

        searchAllButton.setBackground(new java.awt.Color(204, 204, 204));
        searchAllButton.setText("SearchChatAll");
        searchAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAllButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(151, 151, 151)
                        .add(jLabel1))
                    .add(layout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 327, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(layout.createSequentialGroup()
                                    .add(Search_Button, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 243, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(layout.createSequentialGroup()
                                    .add(searchWithUserButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 159, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(searchAllButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 156, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, list_request, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(list_all_friend, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 81, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(list_online, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(list_offline, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(8, 8, 8)
                                    .add(list_block))
                                .add(jScrollPane1)))))
                .add(22, 22, 22)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(inputMess, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 373, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(sentMess_Button, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(remove_row_chat)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(remove_all_chat)
                        .add(40, 40, 40))
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane2)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel3)
                        .add(remove_all_chat)
                        .add(remove_row_chat)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(Search_Button)
                            .add(txtSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(searchWithUserButton)
                            .add(searchAllButton))
                        .add(13, 13, 13)
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(list_all_friend, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(list_online, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(list_block)
                            .add(list_offline))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(list_request)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1))
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 532, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 9, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(sentMess_Button, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(inputMess, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))))
                .add(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String currentUserID;
    public void setCurrentUserID(String id) {
        this.currentUserID = id;
    }


    private void list_onlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list_onlineActionPerformed
        // TODO add your handling code here:
        listModel.clear(); // Clear current list
        try {
            // Lấy ID người dùng hiện tại
            int userId = Integer.parseInt(this.currentUserID);

            // Lấy danh sách bạn bè online từ DAO
            UserAccountDAO userDao = new UserAccountDAO();
            List<User> onlineFriends = userDao.getOnlineFriends(userId);

            // Hiển thị danh sách lên List_result
            for (User user : onlineFriends) {
                listModel.addElement(user);
            }
            List_result.setModel(listModel);

            if (onlineFriends.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có bạn bè nào đang online.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi lấy danh sách bạn bè online: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID người dùng không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_list_onlineActionPerformed

    private void cmdLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLogoutActionPerformed
        // TODO add your handling code here:
        // Lấy thông tin người dùng (email hoặc ID) từ đối tượng hiện tại
        String id = currentUserID; // Biến này được đặt khi người dùng login thành công

        // Gọi DAO để cập nhật trạng thái ON_OFF thành false
        UserAccountDAO dao = new UserAccountDAO();
        boolean isUpdated = dao.updateUserStatus(id, false);

        if (isUpdated) {
            System.out.println("User ON_OFF status updated to FALSE.");

            // Đóng ChatHome
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (currentFrame != null) {
                currentFrame.setVisible(false);
                currentFrame.dispose();
            }

            // Mở lại UserMain
            Main userMain = new Main();
            userMain.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to logout. Please try again.", "Logout Failed", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cmdLogoutActionPerformed

    private void profile_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profile_buttonActionPerformed
        // TODO add your handling code here:
        if (currentUserID == null) {
            JOptionPane.showMessageDialog(this, "User ID is null. Please log in again.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = currentUserID;
        UserAccountDAO userDAO = new UserAccountDAO();
        UserAccount currentUser = userDAO.getUserByID(id); // Lấy user hiện tại

        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Update Profile", true);
        UpdateAccount updatePanel = new UpdateAccount(currentUser);
        dialog.getContentPane().add(updatePanel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
            
    }//GEN-LAST:event_profile_buttonActionPerformed

    private void group_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_group_buttonActionPerformed
        // TODO add your handling code here:
        // Tạo cửa sổ GroupChat và truyền ID
        Group_Chat groupChat = new Group_Chat(this.currentUserID);
        groupChat.setVisible(true);
    }//GEN-LAST:event_group_buttonActionPerformed

   
    private void list_blockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list_blockActionPerformed
        // TODO add your handling code here:
        listModel.clear(); // Clear current list

        try {
            UserAccountDAO userDao = new UserAccountDAO();
            List<User> blockedUsers = userDao.getBlockedUsers(Integer.parseInt(this.currentUserID));

            for (User user : blockedUsers) {
                listModel.addElement(user); // Thêm User vào model
            }

            List_result.setModel(listModel);

            // Renderer để hiển thị danh sách
            List_result.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                    if (value instanceof User user) {
                        c.setForeground(Color.RED); // Người bị block có màu đỏ
                        setText(user.getUsername() + " (" + user.getStatus() + ")");
                    }
                    return c;
                }
            });
            
            if (blockedUsers.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có người bị block nào.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching blocked users: " + e.getMessage());
        }
    }//GEN-LAST:event_list_blockActionPerformed

    private void list_all_friendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list_all_friendActionPerformed
        // TODO add your handling code here:
        listModel.clear(); // Clear current list
        try {
            // Sử dụng DAO để lấy danh sách người dùng
            UserAccountDAO userDao = new UserAccountDAO();
            List<User> userList = userDao.getAllUsersExcludingBlocked(Integer.parseInt(this.currentUserID));

            // Thêm trực tiếp đối tượng User vào model
            for (User user : userList) {
                listModel.addElement(user); // Thêm User thay vì chỉ username
            }

            // Gán model vào JList
            List_result.setModel(listModel);

            // Cài đặt renderer cho JList
            List_result.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    // Gọi renderer mặc định
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                    // Kiểm tra giá trị của item
                    if (value instanceof User user) {
                        if ("online".equalsIgnoreCase(user.getStatus())) {
                            c.setForeground(Color.GREEN); // Online: màu xanh lá
                        } else {
                            c.setForeground(Color.BLACK); // Offline: màu đen
                        }
                    }

                    return c;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from database: " + e.getMessage());
        }

    }//GEN-LAST:event_list_all_friendActionPerformed

    private void list_requestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list_requestActionPerformed
        // TODO add your handling code here:
        listModel.clear(); // Clear current list
        try {
            // Lấy ID người dùng hiện tại (giả sử currentUserID là String)
            int userId = Integer.parseInt(this.currentUserID);
    
            // Lấy danh sách yêu cầu kết bạn từ DAO
            UserAccountDAO userDao = new UserAccountDAO();
            List<User> friendRequests = userDao.getFriendRequests(userId);
    
            // Hiển thị danh sách lên List_result
            for (User user : friendRequests) {
                listModel.addElement(user);
            }
            List_result.setModel(listModel);
    
            if (friendRequests.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có yêu cầu kết bạn nào.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi lấy danh sách yêu cầu kết bạn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID người dùng không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_list_requestActionPerformed

    private void Search_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search_ButtonActionPerformed
        // TODO add your handling code here:
        // Lấy nội dung từ JTextField
        String searchText = txtSearch.getText().trim().toLowerCase(); // Chuyển về chữ thường để so sánh không phân biệt chữ hoa/thường

        // Tạo một DefaultListModel mới để chứa kết quả tìm kiếm
        DefaultListModel<User> filteredModel = new DefaultListModel<>();

        // Duyệt qua danh sách hiện tại
        for (int i = 0; i < listModel.size(); i++) {
            User user = listModel.getElementAt(i);
            if (user.getUsername().toLowerCase().contains(searchText)) { // So sánh với nội dung tìm kiếm
                filteredModel.addElement(user); // Thêm vào model kết quả
            }
        }

        // Cập nhật JList với model mới
        List_result.setModel(filteredModel);

        // Nếu không có kết quả
        if (filteredModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả phù hợp!");
        }
    }//GEN-LAST:event_Search_ButtonActionPerformed

    private void sentMess_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sentMess_ButtonActionPerformed
        // TODO add your handling code here:
        if (inputMess.getText().isEmpty()) return;

        try {
            String messageContent = inputMess.getText();
            int fromUserId = Integer.parseInt(this.currentUserID); // Lấy ID của người dùng hiện tại
            int toUserId = this.SelectedUserId; // Lấy ID của người đang chat cùng

            // Thêm tin nhắn vào cơ sở dữ liệu
            MessageDAO messageDao = new MessageDAO();
            messageDao.addMessage(fromUserId, toUserId, messageContent);
       

        // Ghi dữ liệu vào textArea ở phía trên
        try {
            Map<String, Object> messageDetails = messageDao.getLastMessageDetails(fromUserId, toUserId);

            if (messageDetails != null) {
                Timestamp sendAt = (Timestamp) messageDetails.get("SEND_AT");
                String username = (String) messageDetails.get("USERNAME");

                String formattedMessage = "[" + sendAt + "] " + username + ": " + messageContent + "\n";
                displayChatHistory.append(formattedMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy thông tin tin nhắn.");
        }

        
        // Xóa hết tin nhắn tại ô nhập tin nhắn
        inputMess.setText("");
        } catch (Exception e) {
        System.out.println("Error while sendding messeger");
        }
    }//GEN-LAST:event_sentMess_ButtonActionPerformed

    private void remove_all_chatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_all_chatActionPerformed
        // TODO add your handling code here:
        try {
            int fromUserId = Integer.parseInt(this.currentUserID); // ID của người dùng hiện tại
            int toUserId = this.SelectedUserId; // ID của người đang chat cùng
            
            if (toUserId == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một người để xóa lịch sử chat.");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa toàn bộ lịch sử chat không?",
                    "Xóa lịch sử chat",
                    JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                MessageDAO messageDao = new MessageDAO();
                boolean success = messageDao.deleteAllMessages(fromUserId, toUserId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Đã xóa toàn bộ lịch sử chat.");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa lịch sử chat.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xử lý.");
        }
    }//GEN-LAST:event_remove_all_chatActionPerformed

    private void remove_row_chatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_row_chatActionPerformed
        // TODO add your handling code here:
        try {
            int fromUserId = Integer.parseInt(this.currentUserID); // ID của người dùng hiện tại
            int toUserId = this.SelectedUserId; // ID của người đang chat cùng
            
            if (toUserId == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một người để xóa tin nhắn.");
                return;
            }
    
            MessageDAO messageDao = new MessageDAO();
            boolean success = messageDao.deleteLatestMessage(fromUserId, toUserId);
    
            if (success) {
                JOptionPane.showMessageDialog(this, "Đã xóa tin nhắn mới nhất.");
            } else {
                JOptionPane.showMessageDialog(this, "Không có tin nhắn nào để xóa hoặc xảy ra lỗi.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xử lý.");
        }
    }//GEN-LAST:event_remove_row_chatActionPerformed

    private void list_offlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list_offlineActionPerformed
        // TODO add your handling code here:
        listModel.clear(); // Clear current list
        try {
            // Lấy ID người dùng hiện tại
            int userId = Integer.parseInt(this.currentUserID);

            // Lấy danh sách bạn bè online từ DAO
            UserAccountDAO userDao = new UserAccountDAO();
            List<User> onlineFriends = userDao.getOfflineFriends(userId);

            // Hiển thị danh sách lên List_result
            for (User user : onlineFriends) {
                listModel.addElement(user);
            }
            List_result.setModel(listModel);

            if (onlineFriends.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có bạn bè nào đang offline.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi lấy danh sách bạn bè offline: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID người dùng không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_list_offlineActionPerformed

    private void searchWithUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchWithUserButtonActionPerformed
        // TODO add your handling code here:
        String keyword = txtSearch.getText();
        if (keyword.isEmpty()) return;

        try {
            MessageDAO messageDao = new MessageDAO();
            List<String> results = messageDao.searchMessagesWithUser(Integer.parseInt(this.currentUserID), this.SelectedUserId, keyword);
            displayChatHistory.setText(""); // Xóa nội dung hiện tại

            for (String message : results) {
                displayChatHistory.append(message + "\n");
            }

            if (!results.isEmpty()) {
                displayChatHistory.setCaretPosition(0); // Cuộn đến đầu đoạn chat
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm.");
        }
    }//GEN-LAST:event_searchWithUserButtonActionPerformed

    private void searchAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAllButtonActionPerformed
        // TODO add your handling code here:
        String keyword = txtSearch.getText();
        if (keyword.isEmpty()) return;

        try {
            MessageDAO messageDao = new MessageDAO();
            List<String> results = messageDao.searchMessagesWithAllUsers(Integer.parseInt(this.currentUserID), keyword);

            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả.");
                return;
            }

            // Tạo JDialog để hiển thị kết quả
            JDialog searchDialog = new JDialog(this, "Kết quả tìm kiếm", true);
            searchDialog.setSize(400, 300);
            searchDialog.setLocationRelativeTo(this);

            // Tạo JList hiển thị kết quả
            DefaultListModel<String> listModel_temp = new DefaultListModel<>();
            for (String result : results) {
                listModel_temp.addElement(result);
            }

            JList<String> resultList = new JList<>(listModel_temp);
            resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            resultList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    String selectedMessage = resultList.getSelectedValue();
                    if (selectedMessage != null) {
                        // Hiển thị đoạn chat tương ứng trong JTextArea
                        displayChatHistory.append(selectedMessage + "\n");
                        searchDialog.dispose(); // Đóng popup sau khi chọn
                    }
                }
            });

            // Thêm JList vào JScrollPane để cuộn
            JScrollPane scrollPane = new JScrollPane(resultList);
            searchDialog.add(scrollPane);

            // Hiển thị popup
            searchDialog.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm.");
        }
    }//GEN-LAST:event_searchAllButtonActionPerformed

    private void onUserSelected(javax.swing.event.ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting()) {
            return;
        }
    
        User selectedUser = List_result.getSelectedValue();
        
        if (selectedUser != null) {
            this.SelectedUserId = selectedUser.getId();
            jLabel3.setText(selectedUser.getUsername());
            try {
                MessageDAO messageDao = new MessageDAO();
                List<String> chatHistory = messageDao.getChatHistory(Integer.parseInt(this.currentUserID), selectedUser.getId());
                
                StringBuilder chatContent = new StringBuilder();
                for (String message : chatHistory) {
                    chatContent.append(message.toString()).append("\n");
                }
                
                displayChatHistory.setText(chatContent.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi tải lịch sử chat.");
            }

            try {
                UserAccountDAO userDao = new UserAccountDAO();
                int curUserID = Integer.parseInt(this.currentUserID);
                String curUsername = "You";
                int selectedUserID = selectedUser.getId();
                String selectedUserName = selectedUser.getUsername();
                
                // Kiểm tra người được chọn có bị block không
                if (userDao.isBlockedUser(curUserID, selectedUserID)) {
                    // Hiển thị cửa sổ xác nhận gỡ block
                    int choice = JOptionPane.showConfirmDialog(this,
                            "Bạn đã block người dùng này. Bạn có muốn gỡ block không?",
                            "Gỡ Block",
                            JOptionPane.YES_NO_OPTION);
    
                    if (choice == JOptionPane.YES_OPTION) {
                        userDao.unblockUser(curUserID, selectedUserID);
                        JOptionPane.showMessageDialog(this, "Đã gỡ block người dùng.");
                    }
                    return; // Không xử lý tiếp các trạng thái khác
                }
    
                // Tiếp tục kiểm tra trạng thái bạn bè nếu người dùng không bị block
                boolean isFriend = userDao.checkFriendship(curUserID, selectedUserID);
    
                if (isFriend) {
                    // kiểm tra đã tồn tại luồng xử lý người dùng này chưa
                    if (chatClient != null && chatClient.getReceiverId() == selectedUserID) {
                        JOptionPane.showMessageDialog(this,
                                "Đã kết nối với người dùng này. Không cần tạo lại kết nối!",
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
    
                    // Hủy ChatClient cũ (nếu có) để tạo mới
                    if (chatClient != null) {
                        chatClient.cancel(true); // Hủy thread cũ
                        chatClient = null; // Giải phóng tài nguyên
                    }

                    // Tạo một ChatClient mới
                    chatClient = new ChatClient("localhost", 12345, curUserID,curUsername, selectedUserID,selectedUserName);
                    chatClient.execute(); // Bắt đầu xử lý song song bằng SwingWorker
                    // Cửa sổ cho bạn bè
                    JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Friend", true);
                    Unfriend_and_Report friendPanel = new Unfriend_and_Report(this.currentUserID, selectedUser);
                    dialog.getContentPane().add(friendPanel);
                    dialog.pack();
                    dialog.setLocationRelativeTo(this);
                    dialog.setVisible(true);
                } else {
                    if (userDao.hasFriendRequest(selectedUserID, curUserID)) {
                        // Xử lý lời mời kết bạn
                        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Friend Request", true);
                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                        JLabel label = new JLabel("Người này đã gửi lời mời kết bạn cho bạn. Đồng ý hay không?");
                        JButton acceptButton = new JButton("Đồng ý");
                        JButton rejectButton = new JButton("Từ chối");
    
                        acceptButton.addActionListener(e -> {
                            try {
                                userDao.acceptFriendRequest(selectedUserID, curUserID);
                                JOptionPane.showMessageDialog(dialog, "Đã chấp nhận kết bạn.");
                                dialog.dispose();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(dialog, "Có lỗi xảy ra: " + ex.getMessage());
                            }
                        });
    
                        rejectButton.addActionListener(e -> {
                            try {
                                userDao.rejectFriendRequest(selectedUserID, curUserID);
                                JOptionPane.showMessageDialog(dialog, "Đã từ chối lời mời kết bạn.");
                                dialog.dispose();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(dialog, "Có lỗi xảy ra: " + ex.getMessage());
                            }
                        });
    
                        panel.add(label);
                        panel.add(acceptButton);
                        panel.add(rejectButton);
    
                        dialog.getContentPane().add(panel);
                        dialog.pack();
                        dialog.setLocationRelativeTo(this);
                        dialog.setVisible(true);
                    } else {
                        // Xử lý không phải bạn bè
                        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Not Friend", true);
                        Request_and_Report not_friendPanel = new Request_and_Report(this.currentUserID, selectedUser);
                        dialog.getContentPane().add(not_friendPanel);
                        dialog.pack();
                        dialog.setLocationRelativeTo(this);
                        dialog.setVisible(true);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Có lỗi xảy ra: " + e.getMessage(),
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private class ChatClient extends SwingWorker<Void, String> {
        private String serverAddress;
        private int serverPort;
        private int senderId;
        private String senderUsername;
        private int receiverId;
        private String receiverUsername;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
    
        public ChatClient(String serverAddress, int serverPort, int senderId, String senderUsername, int receiverId, String receiverUsername) {
            this.serverAddress = serverAddress;
            this.serverPort = serverPort;
            this.senderId = senderId;
            this.senderUsername = senderUsername;
            this.receiverId = receiverId;
            this.receiverUsername = receiverUsername;
        }
    
        public int getReceiverId() {
            return receiverId;
        }
    
        public String getSenderUsername() {
            return senderUsername;
        }
    
        public String getReceiverUsername() {
            return receiverUsername;
        }
    
        @Override
        protected Void doInBackground() throws Exception {
            try {
                // Kết nối tới server
                socket = new Socket(serverAddress, serverPort);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
    
                // Gửi senderId tới server ngay khi kết nối
                out.println("ID:" + senderId);
    
                // Lắng nghe tin nhắn từ server
                String incomingMessage;
                while ((incomingMessage = in.readLine()) != null) {
                    publish(incomingMessage); // Gửi tin nhắn đến UI để xử lý
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            }
            return null;
        }
    
        @Override
        protected void process(List<String> messages) {
            String receiverUsername= this.receiverUsername;
            for (String message : messages) {
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                String formattedMessage = "[" + currentTimestamp + "] " + receiverUsername + ": " + message + "\n";
                displayChatHistory.append(formattedMessage);
            }
        }
    
        public void sendMessage(String message) {
            String senderUsername= this.senderUsername;
            if (out != null) {
                String ServerformattedMessage = senderId + ":" + receiverId + ":" + message;  // Add sender and receiver ID
                out.println(ServerformattedMessage); // Send the message to server
    
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                String formattedMessage = "[" + currentTimestamp + "] " + senderUsername + ": " + message + "\n";
                displayChatHistory.append(formattedMessage);
            }
        }
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ChatHome().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<User> List_result;
    private javax.swing.JButton Search_Button;
    private javax.swing.JButton cmdLogout;
    private javax.swing.JTextArea displayChatHistory;
    private javax.swing.JButton group_button;
    private swing.MyTextField inputMess;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton list_all_friend;
    private javax.swing.JButton list_block;
    private javax.swing.JButton list_offline;
    private javax.swing.JButton list_online;
    private javax.swing.JButton list_request;
    private java.awt.PopupMenu popupMenu1;
    private java.awt.PopupMenu popupMenu2;
    private java.awt.PopupMenu popupMenu3;
    private java.awt.PopupMenu popupMenu4;
    private javax.swing.JButton profile_button;
    private javax.swing.JButton remove_all_chat;
    private javax.swing.JButton remove_row_chat;
    private javax.swing.JButton searchAllButton;
    private javax.swing.JButton searchWithUserButton;
    private swing.MyButton sentMess_Button;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    void init() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
