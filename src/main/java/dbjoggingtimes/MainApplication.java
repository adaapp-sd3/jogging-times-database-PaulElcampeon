package dbjoggingtimes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MainApplication {


    /*
    Prerequisite

    Java Runtime Environment
    Java Development Kit
    Java version has to be 1.8 or greater
    If you have an IDE such as Eclipse or Inteliji this can help in running the application


    Application

    The application should be run from the MainApplication class which is this class
    Once the application is running you can access the application by the following url http://localhost:8080


    Database

    This application uses an embedded relational database management system (H2)
    Access the database by the following url http://localhost:8080/h2
    Driver class: org.h2.Driver
    JDBC URL: jdbc:h2:~/dbJoggingApp1
    User name: sa
    Password:
    Then Click Connect

    **/

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
