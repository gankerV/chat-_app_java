package admin_system.bus;

import java.util.List;

import admin_system.dao.GroupChatDAO;
import admin_system.dto.GroupChatDTO;

public class GroupChatBUS {
    private GroupChatDAO groupChatDAO;

    public GroupChatBUS() {
        this.groupChatDAO = new GroupChatDAO(); 
    }

    public List<GroupChatDTO> getAllGroupChats(String orderBy) {
        return groupChatDAO.getAllGroupChats(orderBy);
    }

    public List<GroupChatDTO> getByGroupName(String groupName) {
        return groupChatDAO.getByGroupName(groupName);
    }

    public List<Integer> getIDByPosition(int groupID, String position){
        return groupChatDAO.getIDByPosition(groupID, position);
    }
}


