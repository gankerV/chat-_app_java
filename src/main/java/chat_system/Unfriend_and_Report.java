package chat_system;

import swing.txtUser;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import chat_system.dao.UserAccountDAO;
import chat_system.dto.User;

public class Unfriend_and_Report extends javax.swing.JPanel {

    private String currentUserID;  // ID của người dùng hiện tại
    private User selectedUser;     // Thống tin người dùng bằn cần xóa quan hệ
    public Unfriend_and_Report( String currentUserID, User selectedUser) {    
        initComponents();

        this.currentUserID = currentUserID;  // Lưu lại currentUserID
        this.selectedUser = selectedUser;    // Lưu lại selectedUser

        txtUsername.setText("Username: " + selectedUser.getUsername());
        txtEmail.setText("Email: " + selectedUser.getEmail());
    }

    public void register() {
        txtUser.grabFocus();
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsername = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        unFriend_Button = new swing.MyButton();
        txtEmail = new javax.swing.JLabel();
        spam_Button = new swing.MyButton();
        block_Button = new swing.MyButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        txtUsername.setText("User Name");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(69, 68, 68));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Your Friend");

        unFriend_Button.setBackground(new java.awt.Color(255, 0, 0));
        unFriend_Button.setForeground(new java.awt.Color(40, 40, 40));
        unFriend_Button.setText("Unfriend");
        unFriend_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unFriend_ButtonActionPerformed(evt);
            }
        });

        txtEmail.setText("Email");

        spam_Button.setBackground(new java.awt.Color(0, 0, 0));
        spam_Button.setForeground(new java.awt.Color(255, 255, 255));
        spam_Button.setText("Report Spam");
        spam_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spam_ButtonActionPerformed(evt);
            }
        });

        block_Button.setBackground(new java.awt.Color(0, 0, 0));
        block_Button.setForeground(new java.awt.Color(255, 255, 255));
        block_Button.setText("Block");
        block_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                block_ButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Create Group");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmail)
                    .addComponent(txtUsername)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(unFriend_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spam_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(block_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(unFriend_Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(spam_Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(block_Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void unFriend_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unFriend_ButtonActionPerformed
        // TODO add your handling code here:
        try {
            int curUserID = Integer.parseInt(this.currentUserID);  // ID của người dùng hiện tại
            int selectedUserID = this.selectedUser.getId();  // ID của người bạn cần xóa quan hệ
            
            // Gọi phương thức trong DAO để xóa mối quan hệ bạn bè
            UserAccountDAO userDao = new UserAccountDAO();
            boolean isSuccess = userDao.unfriend(curUserID, selectedUserID);
            
            if (isSuccess) {
                // Nếu xóa thành công, thông báo cho người dùng
                JOptionPane.showMessageDialog(this, "Mối quan hệ bạn bè đã được xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Nếu xóa thất bại
                JOptionPane.showMessageDialog(this, "Không thể xóa mối quan hệ bạn bè.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xóa mối quan hệ bạn bè: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_unFriend_ButtonActionPerformed

    private void spam_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spam_ButtonActionPerformed
        // TODO add your handling code here:
        int reportedId = this.selectedUser.getId(); // ID của người bị báo cáo
        int reporterId = Integer.parseInt(this.currentUserID);   // ID của người báo cáo
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "Are you sure you want to report " + selectedUser.getUsername() + "?",
                "Confirm Report", javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            try {
                UserAccountDAO userDao = new UserAccountDAO();
                boolean alreadyReported = userDao.checkIfAlreadyReported(reporterId, reportedId);

                if (alreadyReported) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                            "You have already reported this user.", 
                            "Already Reported", 
                            javax.swing.JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean success = userDao.reportSpam(reporterId, reportedId);

                    if (success) {
                        javax.swing.JOptionPane.showMessageDialog(this, 
                                "Report submitted successfully.", 
                                "Success", 
                                javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, 
                                "Failed to submit the report. Please try again.", 
                                "Error", 
                                javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                        "An error occurred: " + e.getMessage(), 
                        "Error", 
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_spam_ButtonActionPerformed

    private void block_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_block_ButtonActionPerformed
        // TODO add your handling code here:
        int blockId = this.selectedUser.getId(); // ID của người bị chặn
        int userId = Integer.parseInt(this.currentUserID);    // ID của người thực hiện chặn

        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "Are you sure you want to block " + selectedUser.getUsername() + "?",
                "Confirm Block", javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            try {
                UserAccountDAO userDao = new UserAccountDAO();
                boolean alreadyBlocked = userDao.isBlockedUser(userId, blockId);

                if (alreadyBlocked) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "You have already blocked this user.",
                            "Already Blocked", javax.swing.JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean success = userDao.blockUser(userId, blockId);

                    if (success) {
                        javax.swing.JOptionPane.showMessageDialog(this,
                                "User has been successfully blocked.",
                                "Blocked", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        block_Button.setEnabled(false); // Disable block button after blocking
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this,
                                "Failed to block the user. Please try again.",
                                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "An error occurred: " + e.getMessage(),
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_block_ButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.MyButton block_Button;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private swing.MyButton spam_Button;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtUsername;
    private swing.MyButton unFriend_Button;
    // End of variables declaration//GEN-END:variables
}
