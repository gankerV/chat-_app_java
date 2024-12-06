package admin_system.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import admin_system.dto.UserAccountDTO;
import connect_db.UtilityDAO;

public class UserAccountDAO {
    public List<UserAccountDTO> getAll() {
        List<UserAccountDTO> list = new ArrayList<>();
        UtilityDAO utilityDAO = new UtilityDAO();
        Connection conn = utilityDAO.getConnection();

        if (conn == null) {
            return list;
        }

        String query = "SELECT ID, USERNAME, PASSWORD, FULLNAME, ADDRESS, DATE_OF_BIRTH, GENDER, EMAIL, ON_OFF, CREATED_AT, BANNED FROM USER_ACCOUNT";

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
}
