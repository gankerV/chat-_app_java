package admin_system.bus;
import java.util.List;

import admin_system.dao.UserAccountDAO;
import admin_system.dto.UserAccountDTO;

public class UserAccountBUS {
    public List<UserAccountDTO> getAll(){
        UserAccountDAO userAccountDAO = new UserAccountDAO();
        
        return userAccountDAO.getAll();
    }
}
