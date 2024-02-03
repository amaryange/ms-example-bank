package dev.amary.accountservice.clients;

import dev.amary.accountservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/api/customers/{id}")
    @CircuitBreaker(name="customerService", fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);

    @CircuitBreaker(name="customerService", fallbackMethod = "getAllCustomers")
    @GetMapping("/api/customers")
    List<Customer> allCustomers();

    default Customer getDefaultCustomer(Long id, Exception exception){

        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstname("Not Available");
        customer.setLastname("Not Available");
        customer.setEmail("Not Available");

        return customer;

    }

    default List<Customer> getAllCustomers(Exception e){
        return List.of();
    }

}
