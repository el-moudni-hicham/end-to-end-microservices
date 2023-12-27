package dev.moudni.accountservice.feign;

import dev.moudni.accountservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "customer-service") //, configuration = FeignInterceptor.class
public interface CustomerRestClientFeign {
    @GetMapping("/api/customer/customers/{id}")
    @CircuitBreaker(name = "customerService",fallbackMethod = "getDefaultCustomer")
    Customer getCustomerById(@PathVariable("id") Long id);

    @GetMapping("/api/customer/customers")
    @Retry(name = "retryAllCustomers", fallbackMethod = "getDefaultCustomers")
    List<Customer> allCustomers();
    default Customer getDefaultCustomer(Long id,Exception e){
        return Customer.builder()
                .id(id).email("noname@gmail.com")
                .firstName("defaultFirstName")
                .lastName("defaultLastName")
                .build();
    }
    default List<Customer> getDefaultCustomers(){
        return List.of();
    }
}
