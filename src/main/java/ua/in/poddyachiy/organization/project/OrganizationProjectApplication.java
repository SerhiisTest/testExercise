package ua.in.poddyachiy.organization.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ua.in.poddyachiy.organization.project")
public class OrganizationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationProjectApplication.class, args);
    }

}
