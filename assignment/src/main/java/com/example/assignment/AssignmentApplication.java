package com.example.assignment;

import com.example.assignment.initial.InitialCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssignmentApplication implements ApplicationRunner {


    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);
    }

    @Autowired
    public InitialCreator initialCreator;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initialCreator.initialSetup();
    }
}
