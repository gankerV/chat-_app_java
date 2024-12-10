package admin_system.bus;

import java.util.List;

import admin_system.dao.UserAccountDAO;
import admin_system.dto.UserAccountDTO;

public class UserAccountBUS {
    private UserAccountDAO userAccountDAO;

    public UserAccountBUS() {
        userAccountDAO = new UserAccountDAO();
    }

    // Lấy danh sách tất cả người dùng
    public List<UserAccountDTO> getAll(String orderBy) {
        return userAccountDAO.getAll(orderBy);
    }

    public UserAccountDTO getUserById(int userId) {
        return userAccountDAO.getUserById(userId);
    }
    
    // Lưu người dùng vào cơ sở dữ liệu
    public boolean saveUser(UserAccountDTO userAccount) {
        return userAccountDAO.saveUser(userAccount);
    }

    public boolean updateUser(UserAccountDTO userAccount) {
        return userAccountDAO.updateUser(userAccount);
    }

    public boolean deleteUser(int id) {
        return userAccountDAO.deleteUser(id);
    }

    public boolean banUser(int id, boolean isBanned ) {
        return userAccountDAO.banUser(id,isBanned);
    }


    public List<UserAccountDTO> viewFriends(int id) {
        return userAccountDAO.viewFriends(id);
    }

}

    
