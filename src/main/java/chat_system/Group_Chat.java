package chat_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import chat_system.dao.GroupChatDAO;
import chat_system.dao.MessageDAO;
import chat_system.dto.GroupChat;
import chat_system.dto.User;

public class Group_Chat extends javax.swing.JFrame {
    private final String userId;
    private int SelectedGroupId = -1;
    DefaultListModel<GroupChat> listModel ;
    DefaultListModel<User> listModel_2;

    ChatGroup chatGroup = null;
    public Group_Chat(String userId) {
        this.userId = userId;
        initComponents();
        jLabel5.setText("User ID: " + userId);
        listModel = new DefaultListModel<>();
        listModel_2 = new DefaultListModel<>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        popupMenu2 = new java.awt.PopupMenu();
        popupMenu3 = new java.awt.PopupMenu();
        popupMenu4 = new java.awt.PopupMenu();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        changeName_group = new javax.swing.JButton();
        admin_Button = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        List_member = new javax.swing.JList<>();
        txtNewName = new swing.MyTextField();
        member_Button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        List_group = new javax.swing.JList<>();
        inputMess = new swing.MyTextField();
        sendMess_Button = new swing.MyButton();
        ls_gr_Button = new javax.swing.JButton();
        remove_all_Button = new javax.swing.JButton();
        remove_row_Button = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ChatHistory = new javax.swing.JTextArea();

        popupMenu1.setLabel("popupMenu1");

        popupMenu2.setLabel("popupMenu2");

        popupMenu3.setLabel("popupMenu3");

        popupMenu4.setLabel("popupMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Group's name");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setText("Group's name");

        changeName_group.setBackground(new java.awt.Color(0, 0, 0));
        changeName_group.setForeground(new java.awt.Color(255, 255, 255));
        changeName_group.setText("Sava change name");
        changeName_group.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeName_groupActionPerformed(evt);
            }
        });

        admin_Button.setBackground(new java.awt.Color(153, 153, 153));
        admin_Button.setText("Admin");
        admin_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_ButtonActionPerformed(evt);
            }
        });

        List_member.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                onMemberSelected(evt);
            }
        });
        jScrollPane2.setViewportView(List_member);

        member_Button.setBackground(new java.awt.Color(153, 153, 153));
        member_Button.setText("Member");
        member_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                member_ButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(65, 65, 65)
                        .add(jLabel5))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(16, 16, 16)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 164, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jPanel1Layout.createSequentialGroup()
                                    .add(member_Button, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 81, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(admin_Button, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, changeName_group, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, txtNewName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)))))
                .add(20, 35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtNewName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(changeName_group)
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(member_Button)
                    .add(admin_Button))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 176, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(302, 302, 302))
        );

        List_group.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                onGroupSelected(evt);
            }
        });
        jScrollPane1.setViewportView(List_group);

        sendMess_Button.setBackground(new java.awt.Color(255, 0, 0));
        sendMess_Button.setText("Send");
        sendMess_Button.addActionListener(e -> {
            String message = inputMess.getText(); // Lấy nội dung từ myTextField2
            if (!message.isEmpty()) {
                if (chatGroup != null) { // Kiểm tra chatGroup đã được khởi tạo
                    chatGroup.sendMessage(message); // Gửi tin nhắn qua chatGroup
                    inputMess.setText(""); // Xóa trường nhập liệu sau khi gửi
                } else {
                    JOptionPane.showMessageDialog(null, "Chưa kết nối đến người dùng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ls_gr_Button.setBackground(new java.awt.Color(153, 153, 153));
        ls_gr_Button.setText("Group List");
        ls_gr_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ls_gr_ButtonActionPerformed(evt);
            }
        });

        remove_all_Button.setText("Remove history chat");
        remove_all_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_all_ButtonActionPerformed(evt);
            }
        });

        remove_row_Button.setText("Remove current chat");
        remove_row_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_row_ButtonActionPerformed(evt);
            }
        });

        ChatHistory.setColumns(20);
        ChatHistory.setRows(5);
        jScrollPane3.setViewportView(ChatHistory);

        ls_gr_Button.setBackground(new java.awt.Color(153, 153, 153));
        ls_gr_Button.setText("Group List");
        ls_gr_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ls_gr_ButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(16, 16, 16)
                        .add(ls_gr_Button))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 133, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(layout.createSequentialGroup()
                            .add(inputMess, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 340, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(sendMess_Button, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jScrollPane3))
                    .add(layout.createSequentialGroup()
                        .add(jLabel3)
                        .add(47, 47, 47)
                        .add(remove_row_Button)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(remove_all_Button)))
                .add(26, 26, 26)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(ls_gr_Button)
                            .add(jLabel3)
                            .add(remove_all_Button)
                            .add(remove_row_Button))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 459, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(inputMess, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                    .add(sendMess_Button, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 509, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 544, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ls_gr_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ls_gr_ButtonActionPerformed
        listModel.clear(); // Clear current list
        try {
            // TODO add your handling code here:
            // Lấy danh sách group của user từ DAO
            GroupChatDAO groupChatDAO = new GroupChatDAO();
            List<GroupChat> groupList = groupChatDAO.getGroupsByUserId(Integer.parseInt(this.userId));
            
            // Thêm từng group vào model
            for (GroupChat group : groupList) {
                listModel.addElement(group);
            }
            
            // Cập nhật model cho JList
            List_group.setModel(listModel);
        } catch (SQLException ex) {
            Logger.getLogger(Group_Chat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_ls_gr_ButtonActionPerformed

    private void changeName_groupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeName_groupActionPerformed
        // TODO add your handling code here:
        // Lấy tên nhóm mới từ JTextField
        String newGroupName = txtNewName.getText();  // groupNameTextField là JTextField chứa tên mới

        // Kiểm tra nếu tên nhóm không rỗng
        if (newGroupName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên nhóm không thể trống!");
            return;
        }

        // Lấy ID nhóm được chọn, ví dụ từ JList hoặc JComboBox
        int selectedId = this.SelectedGroupId;  // Cần cài đặt hàm này để lấy ID của nhóm được chọn

        // Tạo đối tượng GroupChatDAO để gọi phương thức cập nhật
        GroupChatDAO groupChatDAO = new GroupChatDAO();

        // Cập nhật tên nhóm
        boolean isUpdated = groupChatDAO.updateGroupName(selectedId, newGroupName);

        // Hiển thị thông báo cho người dùng
        if (isUpdated) {
            JOptionPane.showMessageDialog(this, "Tên nhóm đã được cập nhật thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật tên nhóm.");
        }
    }//GEN-LAST:event_changeName_groupActionPerformed

    private void member_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_member_ButtonActionPerformed
        // TODO add your handling code here:
        listModel_2.clear();
        int groupId = this.SelectedGroupId;

        if (groupId != -1) {
            // Tạo đối tượng GroupChatDAO để lấy danh sách thành viên
            GroupChatDAO groupChatDAO = new GroupChatDAO();
            try {
                // Lấy danh sách thành viên từ GroupChatDAO
                List<User> members = groupChatDAO.getMembersByGroupId(groupId);

                // Hiển thị danh sách thành viên (ví dụ trong một JList)
                
                for (User user : members) {
                    listModel_2.addElement(user);
                }
                List_member.setModel(listModel_2);  // Giả sử memberList là JList hiển thị thành viên
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi lấy danh sách thành viên.");
            }
        }
    }//GEN-LAST:event_member_ButtonActionPerformed

    private void admin_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_ButtonActionPerformed
        // TODO add your handling code here:
        listModel_2.clear();
        int groupId = this.SelectedGroupId;

        if (groupId != -1) {
            // Tạo đối tượng GroupChatDAO để lấy danh sách admin
            GroupChatDAO groupChatDAO = new GroupChatDAO();
            try {
                // Lấy danh sách admin từ GroupChatDAO
                List<User> admins = groupChatDAO.getAdminsByGroupId(groupId);
    
                // Hiển thị danh sách admin (ví dụ trong một JList)
                for (User user : admins) {
                    listModel_2.addElement(user);
                }
                List_member.setModel(listModel_2);  // Giả sử adminList là JList hiển thị admin
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi lấy danh sách quản trị viên.");
            }
        }
    }//GEN-LAST:event_admin_ButtonActionPerformed

    private void remove_row_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_row_ButtonActionPerformed
        // TODO add your handling code here:
        try {
            int groupId = this.SelectedGroupId; // Lấy ID nhóm hiện tại
            if (groupId == -1) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy nhóm hiện tại.");
                return;
            }
    
            MessageDAO messageDao = new MessageDAO();
            messageDao.deleteLatestMessageInGroup(groupId);
            loadGroupChatHistory(); // Cập nhật lại hiển thị lịch sử chat
            JOptionPane.showMessageDialog(this, "Đã xóa tin nhắn mới nhất.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa tin nhắn.");
        }
    }//GEN-LAST:event_remove_row_ButtonActionPerformed

    private void remove_all_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_all_ButtonActionPerformed
        // TODO add your handling code here:
        try {
            int groupId = this.SelectedGroupId; // Lấy ID nhóm hiện tại (cần viết hàm này)
            if (groupId == -1) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy nhóm hiện tại.");
                return;
            }
            
            MessageDAO messageDao = new MessageDAO();
            messageDao.deleteAllMessagesInGroup(groupId);
            ChatHistory.setText(""); 
            JOptionPane.showMessageDialog(this, "Đã xóa toàn bộ tin nhắn trong nhóm.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa toàn bộ tin nhắn.");
        }
    }//GEN-LAST:event_remove_all_ButtonActionPerformed

    private void sendMess_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMess_ButtonActionPerformed
        // TODO add your handling code here:
        String message = inputMess.getText();
        if (message.isEmpty()) return;

        try {
            int groupId = this.SelectedGroupId; // Lấy ID nhóm hiện tại
            int userId = Integer.parseInt(this.userId);  // Lấy ID người dùng hiện tại

            if (groupId == -1 || userId == -1) {
                JOptionPane.showMessageDialog(this, "Lỗi xác định thông tin nhóm hoặc người dùng.");
                return;
            }

            // Lấy thời gian hiện tại
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());

            MessageDAO messageDao = new MessageDAO();
            messageDao.addMessageToGroup(userId, groupId, message);

            String formattedMessage = "[" + currentTime + "] [Bạn]: " + message;
            ChatHistory.append(formattedMessage + "\n");

            inputMess.setText(""); // Xóa ô nhập tin nhắn
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi gửi tin nhắn.");
        }
    }//GEN-LAST:event_sendMess_ButtonActionPerformed


    private void loadGroupChatHistory() {
        try {
            if (this.SelectedGroupId == -1) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy nhóm hiện tại.");
                return;
            }

            MessageDAO messageDao = new MessageDAO();
            List<String> messages = messageDao.getGroupChatHistory(this.SelectedGroupId);
            
            ChatHistory.setText(""); // Xóa toàn bộ văn bản cũ
            for (String message : messages) {
                ChatHistory.append(message + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải lịch sử chat.");
        }
    }
    
    private void onGroupSelected(javax.swing.event.ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting()) {
            return;
        }
        GroupChat selectedGroup = List_group.getSelectedValue();
        jLabel3.setText(selectedGroup.getName());
        jLabel5.setText(selectedGroup.getName());
        txtNewName.setText("");
        this.SelectedGroupId = selectedGroup.getId();

        loadGroupChatHistory();
        // kiểm tra đã tồn tại luồng xử lý người dùng này chưa
        if (chatGroup != null && chatGroup.getGroupId() == this.SelectedGroupId) {
            JOptionPane.showMessageDialog(this,
                    "Đã kết nối với người dùng này. Không cần tạo lại kết nối!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Hủy ChatClient cũ (nếu có) để tạo mới
        if (chatGroup != null) {
            chatGroup.cancel(true); // Hủy thread cũ
            chatGroup = null; // Giải phóng tài nguyên
        }

        // Tạo một ChatClient mới
        chatGroup = new ChatGroup("localhost", 12345, Integer.parseInt(this.userId),"You", this.SelectedGroupId);
        chatGroup.execute(); // Bắt đầu xử lý song song bằng SwingWorker        
    }

    private void onMemberSelected(javax.swing.event.ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting()) {
            return;
        }
    
        User selectedUser = List_member.getSelectedValue();
    
        if (selectedUser != null) {
            try {
                GroupChatDAO groupDao = new GroupChatDAO();
                int selectedUserID = selectedUser.getId();
    
                // Kiểm tra người dùng hiện tại có phải admin không
                int currentUserId = Integer.parseInt(this.userId); 
                if (groupDao.isUserAdmin(this.SelectedGroupId, currentUserId)) {
                    // Người dùng hiện tại là admin, hiển thị popup với hai lựa chọn
                    Object[] options = {"Gán quyền Admin", "Xóa thành viên"};
                    int choice = JOptionPane.showOptionDialog(this,
                            "Bạn muốn thực hiện hành động nào với thành viên này?",
                            "Quản lý thành viên",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
    
                    if (choice == 0) {
                        // Gán quyền admin
                        if (groupDao.isUserAdmin(this.SelectedGroupId, selectedUserID)){
                            JOptionPane.showMessageDialog(this, "Thành viên này đã là admin của group rồi");
                        }
                        else {
                            int confirmAdmin = JOptionPane.showConfirmDialog(this,
                                "Bạn có chắc chắn muốn thêm thành viên này làm admin không?",
                                "thêm admin",
                                JOptionPane.YES_NO_OPTION);
    
                            if (confirmAdmin == JOptionPane.YES_OPTION) {
                                boolean success = groupDao.assignAdminToMember(this.SelectedGroupId, selectedUserID);
                                if (success) {
                                    JOptionPane.showMessageDialog(this, "Đã gán quyền admin cho thành viên.");
                                } else {
                                    JOptionPane.showMessageDialog(this, "Lỗi khi gán quyền admin.");
                                }
                            }
                        }   
                    } else if (choice == 1) {
                        // Xóa thành viên
                        if (groupDao.isUserAdmin(this.SelectedGroupId, selectedUserID)){
                            JOptionPane.showMessageDialog(this, "Thành viên này cũng là admin của group nên không thể xóa.");
                        }
                        else{
                            int confirmDelete = JOptionPane.showConfirmDialog(this,
                                "Bạn có chắc chắn muốn xóa thành viên này khỏi nhóm không?",
                                "Xóa thành viên",
                                JOptionPane.YES_NO_OPTION);
    
                            if (confirmDelete == JOptionPane.YES_OPTION) {
                                groupDao.removeMemberFromGroup(this.SelectedGroupId, selectedUserID);
                                JOptionPane.showMessageDialog(this, "Đã xóa thành viên.");
                            }
                        }
                    }
                } else {
                    // Người dùng hiện tại không phải admin
                    JOptionPane.showMessageDialog(this, "Bạn không có quyền chỉnh sửa thành viên.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi xử lý.");
            }
        }
    }
    
    public class ChatGroup extends SwingWorker<Void, String> {
        private String serverAddress;
        private int serverPort;
        private int senderId;
        private String senderUsername;
        private int groupId;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
    
        public ChatGroup(String serverAddress, int serverPort, int senderId, String senderUsername, int groupId) {
            this.serverAddress = serverAddress;
            this.serverPort = serverPort;
            this.senderId = senderId;
            this.senderUsername = senderUsername;
            this.groupId = groupId;
        }
    
        public int getGroupId() {
            return groupId;
        }
    
        public String getSenderUsername() {
            return senderUsername;
        }
    
        @Override
        protected Void doInBackground() throws Exception {
            try {
                // Kết nối tới server
                socket = new Socket(serverAddress, serverPort);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
    
                // Gửi senderId và xác định là nhóm ngay khi kết nối
                out.println("ID:" + senderId);
                out.println("chatGroup:true");
    
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
            for (String message : messages) {
                //message đang có dạng username : messageContent
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                String formattedMessage = "[" + currentTimestamp + "] " + message + "\n";

                ChatHistory.append(formattedMessage);
            }
        }
    
        public void sendMessage(String message) {
            String senderUsername = this.senderUsername;
            if (out != null) {
                String serverFormattedMessage = senderId + ":" + groupId + ":" + message; // Add sender and group ID
                out.println(serverFormattedMessage); // Send the message to server
    
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                String formattedMessage = "[" + currentTimestamp + "] " + senderUsername + ": " + message + "\n";
                ChatHistory.append(formattedMessage);
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Group_Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

         /* Create and display the form */
         java.awt.EventQueue.invokeLater(() -> {
            String userID = "";
        
             new Group_Chat(userID).setVisible(true);
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ChatHistory;
    private javax.swing.JList<GroupChat> List_group;
    private javax.swing.JList<User> List_member;
    private javax.swing.JButton admin_Button;
    private javax.swing.JButton changeName_group;
    private swing.MyTextField inputMess;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton ls_gr_Button;
    private javax.swing.JButton member_Button;
    private java.awt.PopupMenu popupMenu1;
    private java.awt.PopupMenu popupMenu2;
    private java.awt.PopupMenu popupMenu3;
    private java.awt.PopupMenu popupMenu4;
    private javax.swing.JButton remove_all_Button;
    private javax.swing.JButton remove_row_Button;
    private swing.MyButton sendMess_Button;
    private swing.MyTextField txtNewName;
    // End of variables declaration//GEN-END:variables
}
