package admin_system.dto;

import java.sql.Timestamp;

public class ReportDTO {

    private int id;            // ID
    private int reporterId;    // REPORTER_ID
    private int reportedId;    // REPORTED_ID
    private String content;    // CONTENT
    private Timestamp reportAt; // REPORT_AT

    // Constructor
    public ReportDTO(int id, int reporterId, int reportedId, String content, Timestamp reportAt) {
        this.id = id;
        this.reporterId = reporterId;
        this.reportedId = reportedId;
        this.content = content;
        this.reportAt = reportAt;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    public int getReportedId() {
        return reportedId;
    }

    public void setReportedId(int reportedId) {
        this.reportedId = reportedId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getReportAt() {
        return reportAt;
    }

    public void setReportAt(Timestamp reportAt) {
        this.reportAt = reportAt;
    }

    // Override toString() method for better representation
    @Override
    public String toString() {
        return "ReportDTO{" +
               "id=" + id +
               ", reporterId=" + reporterId +
               ", reportedId=" + reportedId +
               ", content='" + content + '\'' +
               ", reportAt=" + reportAt +
               '}';
    }
}
