package ua.in.poddyachiy.organization.project.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.in.poddyachiy.organization.project.entity.Course;
import ua.in.poddyachiy.organization.project.entity.Exam;
import ua.in.poddyachiy.organization.project.service.CourseService;
import ua.in.poddyachiy.organization.project.service.ExamService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(path = "/organization")
public class OrganizationController {

    private CourseService courseService;
    private ExamService examService;

    @Autowired
    public OrganizationController(CourseService courseService, ExamService examService) {
        this.courseService = courseService;
        this.examService = examService;
    }

    @GetMapping("/{orgId}/courses")
    public ResponseEntity<List<Course>> getCoursesList(@PathVariable("orgId") Integer organizationId) {

        List<Course> courses = courseService.getOrganizationCourses(organizationId);

        return courses.size() > 0 ? ResponseEntity.ok(courses) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{orgId}/courses/{courseId}")
    public Course getCourse(@PathVariable("orgId") Integer organizationId,
                            @PathVariable("courseId") Integer courseId) {

        return courseService.getCourseByOrganizationAndCourseId(organizationId, courseId);
    }


    @PostMapping("/{orgid}/courses/{courseId}")
    public Course postCourse(@PathVariable("orgid") Integer organizationId,
                             @PathVariable("courseId") Integer courseId,
                             @RequestBody Course course) {
        return courseService.saveCourse(course);
    }


    @DeleteMapping("/{orgid}/courses/{courseId}")
    public ResponseEntity deleteCourse(@PathVariable("orgid") Integer organizationId,
                                       @PathVariable("courseId") Integer courseId) {

        courseService.deleteCourse(organizationId, courseId);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/{orgid}/courses/{courseId}")
    public ResponseEntity<Course> increaseViewsNumber(@PathVariable("orgid") Integer organizationId,
                                                      @PathVariable("courseId") Integer courseId
    ) {

        Course updatedCourse = courseService.incrementViewsNumber(organizationId, courseId);

        return ResponseEntity.ok(updatedCourse);
    }

    @GetMapping("/{orgid}/courses/{courseId}/exams")
    public ResponseEntity<List<Exam>> getCourseExams(@PathVariable("orgid") Integer organizationId,
                                                     @PathVariable("courseId") Integer courseId) {

        List<Exam> exams = examService.getExamsForCourse(organizationId, courseId);

        return exams.size() > 0 ? ResponseEntity.ok(exams) : ResponseEntity.noContent().build();

    }


    @GetMapping("/{orgid}/courses/{courseId}/exams/{examId}")
    public Exam getExam(@PathVariable("orgid") Integer organizationId,
                        @PathVariable("courseId") Integer courseId,
                        @PathVariable("examId") Integer examId) {

        return examService.getExamByOrganizationCourseAndExamId(organizationId, courseId, examId);
    }

    @DeleteMapping("/{orgid}/courses/{courseId}/exams/{examId}")
    public ResponseEntity deleteExam(@PathVariable("orgid") Integer organizationId,
                                     @PathVariable("courseId") Integer courseId,
                                     @PathVariable("examId") Integer examId) {

        examService.deleteExam(organizationId, courseId, examId);
        return ResponseEntity.noContent().build();
    }


}
