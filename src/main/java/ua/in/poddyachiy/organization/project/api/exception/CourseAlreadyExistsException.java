package ua.in.poddyachiy.organization.project.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author spid
 * @since
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class CourseAlreadyExistsException extends RuntimeException {

    public CourseAlreadyExistsException() {
    }

    public CourseAlreadyExistsException(String message) {
        super(message);
    }
}
