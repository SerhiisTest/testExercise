package ua.in.poddyachiy.organization.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author spid
 * @since
 */
@Entity
@Table(name = "EXAM")
public class Exam {

    @Id
    @Column(name = "EXAM_ID")
    Integer exam_id;

    @Column(name = "COURSE_ID")
    Integer courseId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "KNOWLEGDE_LEVEL")
    private String knowledgeLevel;

    @Column(name = "APPROXIMATE_DURATION_MINS")
    private Integer approximateDurationMins;

    public Integer getExam_id() {
        return exam_id;
    }

    public void setExam_id(Integer exam_id) {
        this.exam_id = exam_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKnowledgeLevel() {
        return knowledgeLevel;
    }

    public void setKnowledgeLevel(String knowledgeLevel) {
        this.knowledgeLevel = knowledgeLevel;
    }

    public Integer getApproximateDurationMins() {
        return approximateDurationMins;
    }

    public void setApproximateDurationMins(Integer approximateDurationMins) {
        this.approximateDurationMins = approximateDurationMins;
    }
}
