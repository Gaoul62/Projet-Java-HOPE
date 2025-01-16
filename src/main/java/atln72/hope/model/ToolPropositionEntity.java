package atln72.hope.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TOOLPROPOSITION")
public class ToolPropositionEntity {
    // Fields
    @Id
    @Column(name = "TOOLPROPOSITIONID")
    private int toolPropositionId;

    @Column(name = "VALIDATIONSTATUS", nullable = false, length = 25)
    private String validationStatus;

    @Column(name = "TITLE", length = 50)
    private String title;

    @Column(name = "DOMAINNAME", length = 50)
    private String domainName;

    @Column(name = "SIMPLEDESC", length = 50)
    private String simpleDesc;

    @Column(name = "DETAILEDDESC", length = 500)
    private String detailedDesc;

    @Column(name = "LINK", length = 250)
    private String link;

    @Column(name = "ACCESSINSTRUCTION", length = 500)
    private String accessInstruction;

    @ManyToOne
    @JoinColumn(name = "USERID", nullable = false, insertable = false, updatable = false)
    private UserAppEntity user;

    @ManyToOne
    @JoinColumn(name = "ADMINID", nullable = false, insertable = false, updatable = false)
    private UserAppEntity admin;

    // Constructors
    public ToolPropositionEntity() {
        this.validationStatus = "PENDING";
    }

    public ToolPropositionEntity(int toolPropositionId, String validationStatus, String title, String domainName, String simpleDesc, String detailedDesc, String link, String accessInstruction, UserAppEntity user, UserAppEntity admin) {
        this.toolPropositionId = toolPropositionId;
        this.validationStatus = validationStatus;
        this.title = title;
        this.domainName = domainName;
        this.simpleDesc = simpleDesc;
        this.detailedDesc = detailedDesc;
        this.link = link;
        this.accessInstruction = accessInstruction;
        this.user = user;
        this.admin = admin;
    }

    // Getters and Setters
    public int getToolPropositionId() {
        return toolPropositionId;
    }

    public void setToolPropositionId(int toolPropositionId) {
        this.toolPropositionId = toolPropositionId;
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getSimpleDesc() {
        return simpleDesc;
    }

    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
    }

    public String getDetailedDesc() {
        return detailedDesc;
    }

    public void setDetailedDesc(String detailedDesc) {
        this.detailedDesc = detailedDesc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAccessInstruction() {
        return accessInstruction;
    }

    public void setAccessInstruction(String accessInstruction) {
        this.accessInstruction = accessInstruction;
    }

    public UserAppEntity getUser() {
        return user;
    }

    public void setUser(UserAppEntity user) {
        this.user = user;
    }

    public UserAppEntity getAdmin() {
        return admin;
    }

    public void setAdmin(UserAppEntity admin) {
        this.admin = admin;
    }
}
