package admin_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import admin_system.dto.LoginHistoryDTO;
import admin_system.dto.UserAccountDTO;
import connect_db.UtilityDAO;

public class UserAccountDAO {
    public List<UserAccountDTO> getAll(String orderBy) {
        List<UserAccountDTO> list = new ArrayList<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();
    
        if (conn == null) {
            return list;
        }
    
        // Map `orderBy` thành các cột trong cơ sở dữ liệu
        String orderByColumn;
        switch (orderBy) {
            case "FullName":
                orderByColumn = "FULLNAME";
                break;
            case "UserName":
                orderByColumn = "USERNAME";
                break;
            case "Status":
                orderByColumn = "ON_OFF";
                break;
            case "Created at":
                orderByColumn = "CREATED_AT";
                break;
            default: // Mặc định là "ID"
                orderByColumn = "ID";
                break;
        }
    
        // Thêm câu lệnh ORDER BY vào truy vấn
        String query = "SELECT ID, USERNAME, PASSWORD, FULLNAME, ADDRESS, DATE_OF_BIRTH, GENDER, EMAIL, ON_OFF, CREATED_AT, BANNED " +
                       "FROM USER_ACCOUNT ORDER BY " + orderByColumn;
    
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
    
            while (rs.next()) {
                int id = rs.getInt("ID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String fullname = rs.getString("FULLNAME");
                String address = rs.getString("ADDRESS");
                Date dateOfBirth = rs.getDate("DATE_OF_BIRTH");
                String gender = rs.getString("GENDER");
                String email = rs.getString("EMAIL");
                boolean onOff = rs.getBoolean("ON_OFF");
                Timestamp createdAt = rs.getTimestamp("CREATED_AT");
                boolean banned = rs.getBoolean("BANNED");
    
                UserAccountDTO userAccount = new UserAccountDTO(id, username, password, fullname, address, dateOfBirth, gender, email, onOff, createdAt, banned);
                list.add(userAccount);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public boolean saveUser(UserAccountDTO userAccount) {
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();

        if (conn == null) {
            return false;
        }

        String query = "INSERT INTO USER_ACCOUNT (USERNAME, PASSWORD, FULLNAME, ADDRESS, DATE_OF_BIRTH, GENDER, EMAIL, ON_OFF, CREATED_AT, BANNED) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userAccount.getUsername());
            pstmt.setString(2, userAccount.getPassword());
            pstmt.setString(3, userAccount.getFullname());
            pstmt.setString(4, userAccount.getAddress());
            if (userAccount.getDateOfBirth() != null) {
                pstmt.setDate(5, new java.sql.Date(userAccount.getDateOfBirth().getTime()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }
            pstmt.setString(6, userAccount.getGender());
            pstmt.setString(7, userAccount.getEmail());
            pstmt.setBoolean(8, userAccount.isOnOff());
            pstmt.setTimestamp(9, userAccount.getCreatedAt());
            pstmt.setBoolean(10, userAccount.isBanned());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateUser(UserAccountDTO userAccount) {
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();
    
        if (conn == null) {
            return false;
        }
    
        String query = "UPDATE USER_ACCOUNT SET " +
                       "USERNAME = ?, PASSWORD = ?, FULLNAME = ?, ADDRESS = ?, DATE_OF_BIRTH = ?, GENDER = ?, EMAIL = ?, " +
                       "ON_OFF = ?, CREATED_AT = ?, BANNED = ? " +
                       "WHERE ID = ?";
    
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userAccount.getUsername());
            pstmt.setString(2, userAccount.getPassword());
            pstmt.setString(3, userAccount.getFullname());
            pstmt.setString(4, userAccount.getAddress());
            if (userAccount.getDateOfBirth() != null) {
                pstmt.setDate(5, new java.sql.Date(userAccount.getDateOfBirth().getTime()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }
            pstmt.setString(6, userAccount.getGender());
            pstmt.setString(7, userAccount.getEmail());
            pstmt.setBoolean(8, userAccount.isOnOff());
            pstmt.setTimestamp(9, userAccount.getCreatedAt());
            pstmt.setBoolean(10, userAccount.isBanned());
            pstmt.setInt(11, userAccount.getId()); // Điều kiện cập nhật dựa trên ID
    
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean deleteUser(int id) {
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();
    
        if (conn == null) {
            return false;
        }
    
        String query = "DELETE FROM USER_ACCOUNT WHERE ID = ?";
    
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id); // Thiết lập ID của người dùng cần xóa
    
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean banUser(int id, boolean isBanned) {
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();
    
        if (conn == null) {
            return false;
        }
    
        String query = "UPDATE USER_ACCOUNT SET " +
                       "BANNED = ? " +
                       "WHERE ID = ?";
    
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setBoolean(1, isBanned);
            pstmt.setInt(2, id); // Thiết lập ID của người dùng cần xóa
            
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public List<LoginHistoryDTO> viewLoginHistory(int id) {
        List<LoginHistoryDTO> loginHistoryList = new ArrayList<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();

        if (conn == null) {
            return loginHistoryList;
        }

        String query = "SELECT LOGIN_ID, USER_ID, LOGIN_TIME FROM LOGIN_HISTORY WHERE USER_ID = ? ORDER BY LOGIN_TIME DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id); // Set USER_ID parameter

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int loginId = rs.getInt("LOGIN_ID");
                int userId = rs.getInt("USER_ID");
                Timestamp loginTime = rs.getTimestamp("LOGIN_TIME");

                LoginHistoryDTO loginHistoryDTO = new LoginHistoryDTO(loginId, userId, loginTime);
                loginHistoryList.add(loginHistoryDTO);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return loginHistoryList;
    }

    public List<UserAccountDTO> viewFriends(int id) {
        List<UserAccountDTO> friendsList = new ArrayList<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();
    
        if (conn == null) {
            return friendsList;
        }
    
        String query = "SELECT UA.ID, UA.USERNAME, UA.FULLNAME " +
                       "FROM USER_ACCOUNT UA " +
                       "JOIN USER_FRIEND UF ON UA.ID = UF.FRIEND_ID " +
                       "WHERE UF.ID = ?"; // Lấy danh sách bạn bè cho người dùng có ID = ?
    
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
    
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int friendId = rs.getInt("ID");
                    String username = rs.getString("USERNAME");
                    String fullname = rs.getString("FULLNAME");
    
                    // Tạo đối tượng UserAccountDTO cho bạn bè và thêm vào danh sách
                    UserAccountDTO friend = new UserAccountDTO();
                    friend.setId(friendId);
                    friend.setUsername(username);
                    friend.setFullname(fullname);
    
                    friendsList.add(friend);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
        return friendsList;
    }
    

}
