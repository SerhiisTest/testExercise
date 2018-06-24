package ua.in.poddyachiy.organization.project.api.service;

import ua.in.poddyachiy.organization.project.api.exception.CourseAlreadyExistsException;
import ua.in.poddyachiy.organization.project.api.exception.CourseNotFoundException;
import ua.in.poddyachiy.organization.project.api.exception.OrganizationNotFoundException;
import ua.in.poddyachiy.organization.project.entity.Course;

import java.util.List;

/**
 * @author spid
 * @since
 */
public interface ICourseService {

    /**
     * Gives Organization by  organizationId
     *
     * @param organizationId
     *
     * @return
     *
     * @throws OrganizationNotFoundException when organization not found
     */
    List<Course> getOrganizationCourses(Integer organizationId) throws OrganizationNotFoundException;
    /**
     * @param organizationId
     * @param courseId
     *
     * @return
     *
     * @throws OrganizationNotFoundException when organization not found
     * @throws CourseNotFoundException       when course not found
     */
    Course getCourseByOrganizationAndCourseId(Integer organizationId, Integer courseId) throws OrganizationNotFoundException, CourseNotFoundException;

    /**
     * Saves course to DB
     *
     * @param course
     *
     * @return
     *
     * @throws CourseAlreadyExistsException when course with same title already exists
     */
    Course saveCourse(Course course) throws CourseAlreadyExistsException;

    /**
     * @param organizationId
     * @param courseId
     *
     * @throws OrganizationNotFoundException when organization not found
     * @throws CourseNotFoundException       when course not found
     */
    void deleteCourse(Integer organizationId, Integer courseId) throws OrganizationNotFoundException, CourseNotFoundException;

    /**
     * @param organizationId
     * @param courseId
     *
     * @return
     *
     * @throws OrganizationNotFoundException when organization not found
     * @throws CourseNotFoundException       when course not found
     */
    Course incrementViewsNumber(Integer organizationId, Integer courseId) throws OrganizationNotFoundException, CourseNotFoundException;
}
