package dev.amary.customerservice;

import dev.amary.customerservice.config.GlobalConfig;
import dev.amary.customerservice.entities.Customer;
import dev.amary.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){

		return args -> {

			List<Customer> customerList = List.of(
					 Customer.builder()
							.firstname("melo")
							.lastname("amary")
							.email("meless@amary.dev")
							.build(),

					Customer.builder()
							.firstname("mela")
							.lastname("david")
							.email("david@amary.dev")
							.build()

			);


			customerRepository.saveAll(customerList);

		};

	}

}
