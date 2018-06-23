package ua.in.poddyachiy.organization.project.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.in.poddyachiy.organization.project.entity.Course;

/**
 * @author spid
 * @since
 */
public interface CourseRepository extends CrudRepository<Course, Integer> {

    Course findCourseByTitle(String title);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Course  c set c.viewsNumber = c.viewsNumber +1 where c.courseId = ?1")
    void incrementViewsNumber(Integer courseId);
}
