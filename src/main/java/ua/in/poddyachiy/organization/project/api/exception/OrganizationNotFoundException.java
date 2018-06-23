package ua.in.poddyachiy.organization.project.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author spid
 * @since
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrganizationNotFoundException extends RuntimeException {

    public OrganizationNotFoundException() {
    }

    public OrganizationNotFoundException(String message) {
        super(message);
    }

    public OrganizationNotFoundException(Integer organizationId) {
        super(String.format("Organization with id [%s] not found", organizationId));
    }
}
