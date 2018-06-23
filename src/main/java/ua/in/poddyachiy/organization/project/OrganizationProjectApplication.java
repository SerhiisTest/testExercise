package ua.in.poddyachiy.organization.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ua.in.poddyachiy.organization.project.repo.OrganizationRepository;

@SpringBootApplication
@ComponentScan("ua.in.poddyachiy.organization.project")
public class OrganizationProjectApplication implements CommandLineRunner {

    @Autowired
    OrganizationRepository organizationRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrganizationProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
