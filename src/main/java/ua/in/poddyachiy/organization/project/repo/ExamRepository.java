package ua.in.poddyachiy.organization.project.repo;

import org.springframework.data.repository.CrudRepository;
import ua.in.poddyachiy.organization.project.entity.Exam;

/**
 * @author spid
 * @since
 */
public interface ExamRepository extends CrudRepository<Exam, Integer> {
}
