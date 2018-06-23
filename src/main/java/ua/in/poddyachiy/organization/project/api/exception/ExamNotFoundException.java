package ua.in.poddyachiy.organization.project.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author spid
 * @since
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExamNotFoundException extends RuntimeException {

    public ExamNotFoundException() {
    }

    public ExamNotFoundException(String message) {
        super(message);
    }

    public ExamNotFoundException(Integer examId) {
        super(String.format("Exam with id [%s] not found", examId));
    }
}
