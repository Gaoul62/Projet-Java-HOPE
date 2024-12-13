package atln72.hope.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENTTOOL")
public class StudentToolEntity {
    /// Fields
    @Id
    @Column(name = "TOOLID")
    private int toolId;

    @Column(name = "TITLE", nullable = false, length = 50)
    private String title;

    @Column(name = "DOMAINNAME", nullable = false, length = 50)
    private String domainName;

    @Column(name = "SIMPLEDESC", nullable = false, length = 50)
    private String simpleDesc;

    @Column(name = "DETAILEDDESC", nullable = false, length = 500)
    private String detailedDesc;

    @Column(name = "LINK", nullable = false, length = 250)
    private String link;

    @Column(name = "ACCESSINSTRUCTION", nullable = false, length = 500)
    private String accessInstruction;

    /// Constructors
    public StudentToolEntity() {}
    
    public StudentToolEntity(int toolId, String title, String domainName, String simpleDesc, String detailedDesc, String link, String accessInstruction) {
        this.toolId = toolId;
        this.title = title;
        this.domainName = domainName;
        this.simpleDesc = simpleDesc;
        this.detailedDesc = detailedDesc;
        this.link = link;
        this.accessInstruction = accessInstruction;
    }

    /// Getters and Setters
    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
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
}
