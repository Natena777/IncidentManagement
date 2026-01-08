package org.example.incidentmanagement;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IncidentManagementApplication {
    static Logger logger = LoggerFactory.getLogger(IncidentManagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IncidentManagementApplication.class, args);
        logger.info("Swagger:  http://localhost:8080/swagger-ui/index.html");
        logger.info("Website:  http://localhost:8080/index.html");
        logger.info("Application Started At: {}", LocalDateTime.now(ZoneId.of("Asia/Tbilisi")));
 
    }


}
