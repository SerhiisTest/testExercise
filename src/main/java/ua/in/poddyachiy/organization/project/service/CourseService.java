package ua.in.poddyachiy.organization.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.in.poddyachiy.organization.project.api.exception.CourseAlreadyExistsException;
import ua.in.poddyachiy.organization.project.api.exception.CourseNotFoundException;
import ua.in.poddyachiy.organization.project.api.exception.OrganizationNotFoundException;
import ua.in.poddyachiy.organization.project.entity.Course;
import ua.in.poddyachiy.organization.project.entity.Organization;
import ua.in.poddyachiy.organization.project.repo.CourseRepository;

import java.util.List;
import java.util.Optional;

;

/**
 * @author spid
 * @since
 */
@Service
public class CourseService {

    private CourseRepository courseRepository;
    private OrganizationService organizationService;

    @Autowired
    public CourseService(CourseRepository courseRepository, OrganizationService organizationService) {
        this.courseRepository = courseRepository;
        this.organizationService = organizationService;
    }

    public List<Course> getOrganizationCourses(Integer organizationId) throws OrganizationNotFoundException {


        Organization organization = organizationService.getOrganizationById(organizationId);

        List<Course> courses = organization.getCourses();

        return courses;
    }

    public Course getCourseByOrganizationAndCourseId(Integer organizationId, Integer courseId) {
        organizationService.getOrganizationById(organizationId);

        Optional<Course> oCourse = courseRepository.findById(courseId);

        if (!oCourse.isPresent()) {
            throw new CourseNotFoundException(courseId);
        }

        return oCourse.get();
    }

    public Course saveCourse(Course course) {

        if (courseRepository.findCourseByTitle(course.getTitle()) != null) {
            throw new CourseAlreadyExistsException(String.format("Course with title [%s] already exist"));
        }

        return courseRepository.save(course);
    }

    public void deleteCourse(Integer organizationId, Integer courseId) throws OrganizationNotFoundException, CourseNotFoundException {
        // jost for existense validation
        getCourseByOrganizationAndCourseId(organizationId, courseId);

        courseRepository.deleteById(courseId);

    }

    public Course incrementViewsNumber(Integer organizationId, Integer courseId) throws OrganizationNotFoundException, CourseNotFoundException {
        // jost for existense validation
        getCourseByOrganizationAndCourseId(organizationId, courseId);
        courseRepository.incrementViewsNumber(courseId);
        return getCourseByOrganizationAndCourseId(organizationId, courseId);
    }
}
