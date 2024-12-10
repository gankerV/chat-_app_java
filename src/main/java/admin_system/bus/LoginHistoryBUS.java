package admin_system.bus;

import java.util.List;

import admin_system.dao.LoginHistoryDAO;
import admin_system.dto.LoginHistoryDTO;
public class LoginHistoryBUS {

    private LoginHistoryDAO loginHistoryDAO;

    public LoginHistoryBUS() {
        this.loginHistoryDAO = new LoginHistoryDAO(); // Khởi tạo DAO
    }

    public List<LoginHistoryDTO> viewLoginHistory(int id) {
        return loginHistoryDAO.viewLoginHistory(id);
    }

    public List<LoginHistoryDTO> viewAllLoginHistory() {
        return loginHistoryDAO.viewAllLoginHistory();
    }
}
