package org.example.incidentmanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IncidentManagementApplication {
    static Logger logger = LoggerFactory.getLogger(IncidentManagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IncidentManagementApplication.class, args);
        logger.info("Swagger:  http://localhost:8080/swagger-ui/index.html");
    }




}
