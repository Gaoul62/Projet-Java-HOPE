package atln72.hope.model;

import jakarta.persistence.*;

@Entity
@Table(name = "USERFEEDBACK")
public class UserFeedbackEntity {
    @Id
    @Column(name = "FEEDBACKID", nullable = false)
    private Integer feedbackId;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "TOOLID", nullable = false)
    private StudentToolEntity studentTool;

    public UserFeedbackEntity() {
    }

    public UserFeedbackEntity(Integer feedbackId, String content, StudentToolEntity studentTool) {
        this.feedbackId = feedbackId;
        this.content = content;
        this.studentTool = studentTool;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
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
}