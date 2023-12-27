
package dev.moudni.customerservice;

import dev.moudni.customerservice.entites.Customer;
import dev.moudni.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository){
        return args -> {
            List<Customer> customers = List.of(
                 Customer.builder()
                         .firstName("hicham")
                         .lastName("el moudni")
                         .email("him@gmail.com")
                         .build(),
                 Customer.builder()
                         .firstName("ayoub")
                         .lastName("barka")
                         .email("you@gmail.com")
                         .build()
            );
            customerRepository.saveAll(customers);
        };
    }
}
