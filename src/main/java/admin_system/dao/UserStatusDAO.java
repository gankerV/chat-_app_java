package admin_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin_system.dto.UserStatusDTO;
import connect_db.UtilityDAO;

public class UserStatusDAO {
    public List<UserStatusDTO> getUserStatusByTimes(Timestamp startTime, Timestamp endTime, String selectedOrder, String username, int logged) {
        List<UserStatusDTO> userStatusList = new ArrayList<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();

        if (conn == null) {
            return null;
        }

        // Xây dựng câu truy vấn động dựa trên các điều kiện đầu vào
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("""
            SELECT UA.ID, UA.USERNAME, UA.CREATED_AT, 
                COUNT(LH.LOGIN_ID) AS NUM_LOGIN_TIMES,
                (SELECT COUNT(DISTINCT MU.TO_USER) FROM MESSAGE_USER MU WHERE MU.FROM_USER = UA.ID) AS NUM_CHAT_FRIENDS,
                (SELECT COUNT(DISTINCT MG.TO_GROUP) FROM MESSAGE_GROUP MG WHERE MG.FROM_USER = UA.ID) AS NUM_CHAT_GROUP
            FROM USER_ACCOUNT UA
            LEFT JOIN LOGIN_HISTORY LH ON UA.ID = LH.USER_ID
            WHERE LH.LOGIN_TIME BETWEEN ? AND ?
        """);

        if (username != null && !username.isEmpty()) {
            queryBuilder.append(" AND UA.USERNAME LIKE ?");
        }

        queryBuilder.append(" GROUP BY UA.ID");

        if (logged > 0) {
            queryBuilder.append(" HAVING COUNT(LH.LOGIN_ID) > ?");
        }

        if (selectedOrder != null && !selectedOrder.isEmpty()) {
            queryBuilder.append(" ORDER BY ");
            switch (selectedOrder) {
                case "username":
                    queryBuilder.append("UA.USERNAME");
                    break;
                case "createdAt":
                    queryBuilder.append("UA.CREATED_AT");
                    break;
                default:
                    queryBuilder.append("UA.ID");
            }
        }

        try (PreparedStatement stmt = conn.prepareStatement(queryBuilder.toString())) {
            // Set giá trị cho các tham số trong câu lệnh SQL
            int paramIndex = 1;
            stmt.setTimestamp(paramIndex++, startTime);
            stmt.setTimestamp(paramIndex++, endTime);

            if (username != null && !username.isEmpty()) {
                stmt.setString(paramIndex++, "%" + username + "%");
            }

            if (logged > 0) {
                stmt.setInt(paramIndex++, logged);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Lấy các thông tin từ ResultSet
                int id = rs.getInt("ID");
                String userUsername = rs.getString("USERNAME");
                Timestamp createdAt = rs.getTimestamp("CREATED_AT");
                int numLoginTimes = rs.getInt("NUM_LOGIN_TIMES");
                int numChatFriends = rs.getInt("NUM_CHAT_FRIENDS");
                int numChatGroup = rs.getInt("NUM_CHAT_GROUP");

                // Tạo đối tượng UserStatusDTO từ dữ liệu truy vấn được
                UserStatusDTO userStatus = new UserStatusDTO(id, userUsername, createdAt, numLoginTimes, numChatFriends, numChatGroup);
                userStatusList.add(userStatus);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Đảm bảo đóng kết nối khi không sử dụng nữa
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return userStatusList;
    }

    public Map<Integer, Integer> getNumLoggedByTime(Timestamp startTime, Timestamp endTime) {
        Map<Integer, Integer> monthlyLoginCounts = new HashMap<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();

        if (conn == null) {
            return null;
        }

        // SQL query to count the number of logins for each month in the given time range
        String query = """
            SELECT EXTRACT(MONTH FROM LH.LOGIN_TIME) AS LOGIN_MONTH, COUNT(LH.LOGIN_ID) AS NUM_LOGINS
            FROM LOGIN_HISTORY LH
            WHERE LH.LOGIN_TIME BETWEEN ? AND ?
            GROUP BY EXTRACT(MONTH FROM LH.LOGIN_TIME)
            ORDER BY LOGIN_MONTH
        """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Set the timestamp parameters for the query
            stmt.setTimestamp(1, startTime);
            stmt.setTimestamp(2, endTime);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int month = rs.getInt("LOGIN_MONTH");
                int loginCount = rs.getInt("NUM_LOGINS");

                // Store the result in the map with month as the key and login count as the value
                monthlyLoginCounts.put(month, loginCount);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Ensure the connection is closed
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return monthlyLoginCounts;
}

}
