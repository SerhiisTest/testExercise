package ua.in.poddyachiy.organization.project.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author spid
 * @since
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException() {
    }

    public CourseNotFoundException(String message) {
        super(message);
    }

    public CourseNotFoundException(Integer courseId) {
        super(String.format("Course with id [%s] not found", courseId));
    }
}
