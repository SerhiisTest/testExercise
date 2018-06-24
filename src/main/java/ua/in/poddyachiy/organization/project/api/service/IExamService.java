package ua.in.poddyachiy.organization.project.api.service;

import ua.in.poddyachiy.organization.project.api.exception.CourseNotFoundException;
import ua.in.poddyachiy.organization.project.api.exception.ExamNotFoundException;
import ua.in.poddyachiy.organization.project.api.exception.OrganizationNotFoundException;
import ua.in.poddyachiy.organization.project.entity.Exam;

import java.util.List;

/**
 * @author spid
 * @since
 */
public interface IExamService {

    /**
     * Gets exam by:
     *
     * @param organizationId
     * @param courseId
     * @param examId
     *
     * @throws OrganizationNotFoundException when organization not found
     * @throws CourseNotFoundException       when course not found
     * @throws ExamNotFoundException         when exam not found
     */
    Exam getExamByOrganizationCourseAndExamId(Integer organizationId, Integer courseId, Integer examId) throws OrganizationNotFoundException, CourseNotFoundException, ExamNotFoundException;

    /**
     * @param organizationId
     * @param courseId
     *
     * @return
     *
     * @throws OrganizationNotFoundException when organization not found
     * @throws CourseNotFoundException       when course not found
     */
    List<Exam> getExamsForCourse(Integer organizationId, Integer courseId) throws OrganizationNotFoundException, CourseNotFoundException;
    /**
     * @param organizationId
     * @param courseId
     * @param examId
     *
     * @throws OrganizationNotFoundException when organization not found
     * @throws CourseNotFoundException       when course not found
     * @throws ExamNotFoundException         when exam not found
     */
    void deleteExam(Integer organizationId, Integer courseId, Integer examId) throws OrganizationNotFoundException, CourseNotFoundException, ExamNotFoundException;
}
