package ua.in.poddyachiy.organization.project;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.in.poddyachiy.organization.project.api.exception.OrganizationNotFoundException;
import ua.in.poddyachiy.organization.project.entity.Course;
import ua.in.poddyachiy.organization.project.repo.OrganizationRepository;
import ua.in.poddyachiy.organization.project.rest.controller.OrganizationController;
import ua.in.poddyachiy.organization.project.service.CourseService;
import ua.in.poddyachiy.organization.project.service.ExamService;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

/**
 * @author spid
 * @since
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(OrganizationController.class)
public class OrganizationRestServiceTest {

    @MockBean
    private CourseService courseService;

    @MockBean
    private ExamService examService;

    @MockBean
    OrganizationRepository organizationRepository;

    private static final String USER_ERIC = "Eric";
    private static final String USER_KENNY = "Kenny";
    private static final String USER_CHEF = "Chef";
    private static final String PASSWORD = "password12345";


    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetOrganizationCourses_When_Organization_Has_No_Courses() throws Exception {
        given(courseService.getOrganizationCourses(4)).willReturn(Collections.emptyList());

        RestAssuredMockMvc
                .given()
                .mockMvc(mockMvc)
                .auth().with(httpBasic(USER_ERIC, PASSWORD))
                .when().get("/organization/5/courses")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

    }

    @Test
    public void testGetOrganizationCourses_When_Organization_Not_Found() throws Exception {

        given(courseService.getOrganizationCourses(5)).willThrow(new OrganizationNotFoundException(5));

        RestAssuredMockMvc
                .given()
                .mockMvc(mockMvc)
                .auth().with(httpBasic(USER_ERIC, PASSWORD))
                .when().get("/organization/5/courses")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }

    @Test
    public void testGetOrganizationCourses_When_Ok() throws Exception {
        List<Course> courses = Collections.singletonList(mockCourse());

        given(courseService.getOrganizationCourses(5)).willReturn(courses);

        RestAssuredMockMvc
                .given()
                .mockMvc(mockMvc)
                .auth().with(httpBasic(USER_ERIC, PASSWORD))
                .when().get("/organization/5/courses")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("[0].courseId", equalTo(50))
                .body("[0].description", equalTo("Lern Spring in 5 days from scratch"));
    }

    /**
     * Only users with role MODIFICATION can access POST, PUT, PATCH endpoints.
     * Since user "Eric' has NOT role MODIFICATION - 403 code is expected
     */
    @Test
    public void testPATCH_increment_views_number_when_FORBIDDEN() throws Exception {

        RestAssuredMockMvc
                .given()
                .mockMvc(mockMvc)
                .auth().with(httpBasic(USER_ERIC, PASSWORD))
                .when().patch("/organization/1/courses/50")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    /**
     * Only users with role MODIFICATION can access POST, PUT, PATCH endpoints.
     * Since user "Kenny' has role MODIFICATION - 200 code is expected
     */
    @Test
    public void testPATCH_increment_views_number_when_Ok() throws Exception {

        given(courseService.incrementViewsNumber(1, 50)).willReturn(mockCourse());

        RestAssuredMockMvc
                .given()
                .mockMvc(mockMvc)
                .auth().with(httpBasic(USER_KENNY, PASSWORD))
                .when().patch("/organization/1/courses/50")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("viewsNumber", equalTo(1000));
    }

    @Test
    public void testDELETE_exam_when_FORBIDDEN() throws Exception {

        RestAssuredMockMvc
                .given()
                .mockMvc(mockMvc)
                .auth().with(httpBasic(USER_KENNY, PASSWORD))
                .when().delete("/organization/1/courses/50/exams/123")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void testDELETE_exam_when_Ok() throws Exception {

        RestAssuredMockMvc
                .given()
                .mockMvc(mockMvc)
                .auth().with(httpBasic(USER_CHEF, PASSWORD))
                .when().delete("/organization/1/courses/50/exams/123")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        then(examService).should().deleteExam(1, 50, 123);
    }

    @Test
    public void testPOST_course_when_Ok() throws Exception {
        Course courseToSave = mockCourse();
        courseToSave.setCourseId(null);

        Course persistedCourse = mockCourse();
        persistedCourse.setCourseId(258);

        given(courseService.saveCourse(BDDMockito.any(Course.class))).willReturn(persistedCourse);

        RestAssuredMockMvc
                .given()
                .mockMvc(mockMvc)
                .auth().with(httpBasic(USER_KENNY, PASSWORD))
                .contentType("application/json")
                .body(courseToSave)
                .when().
                post("/organization/1/courses/10")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("courseId", equalTo(258));
        ;


    }


    private Course mockCourse() {
        Course course = new Course();
        course.setAuthor("Ivan");
        course.setDescription("Lern Spring in 5 days from scratch");
        course.setTitle("How to learn Spring in 5 days");
        course.setOrganizationId(1);
        course.setViewsNumber(1000);
        course.setCourseId(50);
        return course;

    }

}
