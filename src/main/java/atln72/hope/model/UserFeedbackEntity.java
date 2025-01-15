package atln72.hope.model;

import jakarta.persistence.*;

@Entity
@Table(name = "USERFEEDBACK")
public class UserFeedbackEntity {
    // Fields
    @Id
    @Column(name = "USERFEEDBACKID")
    private int userFeedbackId;

    @Column(name = "CONTENT", length = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name = "TOOLID", nullable = false, insertable = false, updatable = false)
    private StudentToolEntity studentTool;

    @ManyToOne
    @JoinColumn(name = "USERID", nullable = false, insertable = false, updatable = false)
    private UserAppEntity user;

    // Constructors
    public UserFeedbackEntity() {}

    public UserFeedbackEntity(int userFeedbackId, String content, StudentToolEntity studentTool, UserAppEntity user) {
        this.userFeedbackId = userFeedbackId;
        this.content = content;
        this.studentTool = studentTool;
        this.user = user;
    }

    // Getters and Setters
    public int getUserFeedbackId() {
        return userFeedbackId;
    }

    public void setUserFeedbackId(int userFeedbackId) {
        this.userFeedbackId = userFeedbackId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StudentToolEntity getStudentTool() {
        return studentTool;
    }

    public void setStudentTool(StudentToolEntity studentTool) {
        this.studentTool = studentTool;
    }

    public UserAppEntity getUser() {
        return user;
    }

    public void setUser(UserAppEntity user) {
        this.user = user;
    }
}