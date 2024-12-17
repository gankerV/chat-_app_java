package chat_system;

import chat_system.dao.UserAccountDAO;
import chat_system.dto.User;
import chat_system.dto.UserAccount;
import java.awt.Color;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

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
import javax.swing.SwingUtilities;

public class ChatHome extends javax.swing.JFrame {
    DefaultListModel<User> listModel ;
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
        jScrollBar1 = new javax.swing.JScrollBar();
        jScrollBar2 = new javax.swing.JScrollBar();
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
        myTextField1 = new swing.MyTextField();
        myTextField2 = new swing.MyTextField();
        myButton1 = new swing.MyButton();

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

        myTextField1.setText("message");

        myTextField2.setText("TextField");

        myButton1.setBackground(new java.awt.Color(255, 0, 0));
        myButton1.setText("Send");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, list_request, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                        .add(list_all_friend, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 81, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(list_online, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 156, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(list_block))
                                    .add(jScrollPane1))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jScrollBar2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 327, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(layout.createSequentialGroup()
                                    .add(Search_Button, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 78, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 243, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                    .add(layout.createSequentialGroup()
                        .add(151, 151, 151)
                        .add(jLabel1)))
                .add(12, 12, 12)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(myTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 373, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(myButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(myTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 480, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabel3))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(Search_Button)
                            .add(txtSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(13, 13, 13)
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(list_all_friend, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(list_online, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(list_block))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(list_request)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollBar2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                            .add(jScrollPane1)))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(myTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jScrollBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(myButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(myTextField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .add(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void myTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myTextField1ActionPerformed

    private void myTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myTextField3ActionPerformed

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_myButton1ActionPerformed

    private void myButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myButton3ActionPerformed

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
            UserMain userMain = new UserMain();
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

    private void onUserSelected(javax.swing.event.ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting()) {
            return;
        }
    
        User selectedUser = List_result.getSelectedValue();
    
        if (selectedUser != null) {
            try {
                UserAccountDAO userDao = new UserAccountDAO();
                int curUserID = Integer.parseInt(this.currentUserID);
                int selectedUserID = selectedUser.getId();
    
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
    private javax.swing.JButton group_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollBar jScrollBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton list_all_friend;
    private javax.swing.JButton list_block;
    private javax.swing.JButton list_online;
    private javax.swing.JButton list_request;
    private swing.MyButton myButton1;
    private swing.MyTextField myTextField1;
    private swing.MyTextField myTextField2;
    private java.awt.PopupMenu popupMenu1;
    private java.awt.PopupMenu popupMenu2;
    private java.awt.PopupMenu popupMenu3;
    private java.awt.PopupMenu popupMenu4;
    private javax.swing.JButton profile_button;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    void init() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
