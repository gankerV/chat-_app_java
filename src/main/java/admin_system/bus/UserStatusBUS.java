package admin_system.bus;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import admin_system.dao.UserStatusDAO;
import admin_system.dto.UserStatusDTO;

public class UserStatusBUS {
    private UserStatusDAO userStatusDAO;

    public UserStatusBUS() {
        this.userStatusDAO = new UserStatusDAO();
    }

    public List<UserStatusDTO> getUserStatusByTimes (Timestamp startTime,Timestamp endTime, String selectedOrder, String username, int logged){
        return userStatusDAO.getUserStatusByTimes(startTime,endTime,selectedOrder,username,logged);
    }

    public Map<Integer, Integer> getNumLoggedByTime(Timestamp startTime, Timestamp endTime) {
        return userStatusDAO.getNumLoggedByTime(startTime, endTime);
    }

}