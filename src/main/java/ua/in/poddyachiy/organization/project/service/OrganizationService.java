package ua.in.poddyachiy.organization.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.in.poddyachiy.organization.project.api.exception.OrganizationNotFoundException;
import ua.in.poddyachiy.organization.project.api.service.IOrganizationService;
import ua.in.poddyachiy.organization.project.entity.Organization;
import ua.in.poddyachiy.organization.project.repo.OrganizationRepository;

import java.util.Optional;

/**
 * @author spid
 * @since
 */
@Service
public class OrganizationService implements IOrganizationService {

    private OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Organization getOrganizationById(Integer organizationId) {
        Optional<Organization> oOrganization = organizationRepository.findById(organizationId);

        if (!oOrganization.isPresent()) {
            throw new OrganizationNotFoundException(organizationId);
        }
        return oOrganization.get();
    }
}
