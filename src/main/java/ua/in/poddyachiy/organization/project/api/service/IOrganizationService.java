package ua.in.poddyachiy.organization.project.api.service;

import ua.in.poddyachiy.organization.project.api.exception.OrganizationNotFoundException;
import ua.in.poddyachiy.organization.project.entity.Organization;

/**
 * @author spid
 * @since
 */
public interface IOrganizationService {

    /**
     * Gets organization by id
     *
     * @param organizationId
     *
     * @return
     */
    Organization getOrganizationById(Integer organizationId) throws OrganizationNotFoundException;
}
