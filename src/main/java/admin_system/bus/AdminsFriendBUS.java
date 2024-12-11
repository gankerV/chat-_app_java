package admin_system.bus;

import java.util.List;

import admin_system.dao.AdminsFriendDAO;
import admin_system.dto.AdminsFriendDTO;

public class AdminsFriendBUS {
    private AdminsFriendDAO adminsFriendDAO;

    public AdminsFriendBUS() {
        this.adminsFriendDAO = new AdminsFriendDAO();
    }

    public List<AdminsFriendDTO> getFriendsByAdminId(int adminId, String orderBy, String searchUsername, int searchCommonFriend) {
        return adminsFriendDAO.getFriendsByAdminId(adminId, orderBy, searchUsername, searchCommonFriend);
    }
}
