package com.example.demospringboot;

import com.example.demospringboot.domain.Address;
import com.example.demospringboot.domain.Employee;
import com.example.demospringboot.repository.AddressRepository;
import com.example.demospringboot.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoSpringBootApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoSpringBootApplication.class, args);


    }

    @Bean
    public CommandLineRunner mappingDemo(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        return args -> {

            // create a new book
            Address address = new Address("Tokio","Ukraine","000");

            // save the book

            addressRepository.save(address);

            employeeRepository.save(new Employee("Sergey","asfsaf@gmail",address));
            employeeRepository.save(new Employee("gena","denchik@gmail",address));
            employeeRepository.save(new Employee("Sergey","рябчик@gmail",address));

            // create and save new pages
        };
    }

}
