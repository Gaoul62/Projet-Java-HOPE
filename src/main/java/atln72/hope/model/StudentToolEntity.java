package atln72.hope.model;

import jakarta.persistence.*;

@Entity
@Table(name = "STUDENTTOOL")
public class StudentToolEntity {
    @Id
    @Column(name = "TOOLID", nullable = false)
    private Integer toolId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DOMAINNAME", nullable = false)
    private String domainName;

    @Column(name = "SIMPLEDESC", nullable = false)
    private String simpleDesc;

    @Column(name = "DETAILEDDESC", nullable = false)
    private String detailedDesc;

    @Column(name = "LINK", nullable = false)
    private String link;

    @Column(name = "ACCESSINSTRUCTION", nullable = false)
    private String accessInstruction;

    public StudentToolEntity() {
    }

    public StudentToolEntity(Integer toolId, String title, String domainName, String simpleDesc, String detailedDesc, String link, String accessInstruction) {
        this.toolId = toolId;
        this.title = title;
        this.domainName = domainName;
        this.simpleDesc = simpleDesc;
        this.detailedDesc = detailedDesc;
        this.link = link;
        this.accessInstruction = accessInstruction;
    }

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
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

    @Override
    public String toString() {
        return "StudentTool{" +
                "toolId=" + toolId +
                ", title='" + title + '\'' +
                ", domainName='" + domainName + '\'' +
                ", simpleDesc='" + simpleDesc + '\'' +
                ", detailedDesc='" + detailedDesc + '\'' +
                ", link='" + link + '\'' +
                ", accessInstruction='" + accessInstruction + '\'' +
                '}';
    }
}
