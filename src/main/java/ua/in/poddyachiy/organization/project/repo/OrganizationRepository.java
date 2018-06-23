package ua.in.poddyachiy.organization.project.repo;

import org.springframework.data.repository.CrudRepository;
import ua.in.poddyachiy.organization.project.entity.Organization;

/**
 * @author spid
 * @since
 */
public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
}
