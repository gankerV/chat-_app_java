/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_system.bus;

import chat_system.dao.UserAccountDAO;
import chat_system.dto.UserAccount;

/**
 *
 * @author tin75
 */
public class UserAccountBUS {
    private final UserAccountDAO userAccountDAO;

    public UserAccountBUS() {
        userAccountDAO = new UserAccountDAO();
    }

    public boolean registerUser(UserAccount user) {
        // Validate data here (optional)
        if (user.getUsername().length() < 3 || user.getPassword().length() < 6) {
            return false;  // Basic validation for username/password
        }

        // Call DAO layer to insert into database
        return userAccountDAO.addUser(user);
    }
    
    public boolean isUserExists(String email) {
        UserAccountDAO dao = new UserAccountDAO();
        return dao.isEmailExists(email); // Trả về true nếu username đã tồn tại
    }

}
