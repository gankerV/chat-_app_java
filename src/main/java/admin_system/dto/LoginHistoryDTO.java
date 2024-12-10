package admin_system.dto;

import java.sql.Timestamp;

public class LoginHistoryDTO {
    private int loginId;        // LOGIN_ID
    private int userId;         // USER_ID (Tham chiếu đến bảng USER_ACCOUNT)
    private Timestamp loginTime; // LOGIN_TIME

    // Constructor
    public LoginHistoryDTO(int loginId, int userId, Timestamp loginTime) {
        this.loginId = loginId;
        this.userId = userId;
        this.loginTime = loginTime;
    }

    // Getter and Setter methods
    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "LoginHistoryDTO{" +
               "loginId=" + loginId +
               ", userId=" + userId +
               ", loginTime=" + loginTime +
               '}';
    }
}
