package ua.in.poddyachiy.organization.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author spid
 * @since
 */
@Entity
@Table(name = "ORGANIZATION")

public class Organization {

    @Id
    @GeneratedValue
    private Integer orgId;

    private String name;

    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGANIZATION_ID")
    private List<Course> courses;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
