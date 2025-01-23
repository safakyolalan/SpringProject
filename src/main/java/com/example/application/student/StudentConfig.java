package com.example.application.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
           Student mariam=new Student("Lamine","lamine@gmail.com",LocalDate.of(2000,Month.JANUARY,5));
            Student alex=new Student("Alex","souza@gmail.com",LocalDate.of(1999,Month.JANUARY,5));
            repository.saveAll(List.of(mariam,alex));

        };
    }
}
