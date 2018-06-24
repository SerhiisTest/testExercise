package ua.in.poddyachiy.organization.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.in.poddyachiy.organization.project.api.exception.ExamNotFoundException;
import ua.in.poddyachiy.organization.project.api.service.ICourseService;
import ua.in.poddyachiy.organization.project.api.service.IExamService;
import ua.in.poddyachiy.organization.project.entity.Course;
import ua.in.poddyachiy.organization.project.entity.Exam;
import ua.in.poddyachiy.organization.project.repo.ExamRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author spid
 * @since
 */
@Service
public class ExamService implements IExamService {

    private ICourseService courseService;
    private ExamRepository examRepository;


    @Autowired
    public ExamService(ICourseService courseService, ExamRepository examRepository) {
        this.courseService = courseService;
        this.examRepository = examRepository;
    }

    @Override
    public Exam getExamByOrganizationCourseAndExamId(Integer organizationId, Integer courseId, Integer examId) {
        // just for existence validation
        courseService.getCourseByOrganizationAndCourseId(organizationId, courseId);

        Optional<Exam> oExam = examRepository.findById(examId);

        if (!oExam.isPresent()) {
            throw new ExamNotFoundException(examId);
        }

        return oExam.get();
    }

    @Override
    public List<Exam> getExamsForCourse(Integer organizationId, Integer courseId) {

        Course course = courseService.getCourseByOrganizationAndCourseId(organizationId, courseId);

        return course.getExams();
    }

    @Override
    public void deleteExam(Integer organizationId, Integer courseId, Integer examId) {

        Exam examToDelete = getExamByOrganizationCourseAndExamId(organizationId, courseId, examId);

        examRepository.delete(examToDelete);
    }
}
