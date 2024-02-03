package dev.amary.accountservice;

import dev.amary.accountservice.clients.CustomerRestClient;
import dev.amary.accountservice.entities.BankAccount;
import dev.amary.accountservice.enums.AccountType;
import dev.amary.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient){

		return args -> {

			customerRestClient.allCustomers().forEach(c -> {

				BankAccount bankAccount1 = BankAccount.builder()
									.accountId(UUID.randomUUID().toString())
									.balance(25900)
									.type(AccountType.CURRENT_ACCOUNT)
									.currency("XOF")
									.createdAt(LocalDate.now())
									.customerId(c.getId())
									.build();

				BankAccount bankAccount2 = BankAccount.builder()
									.accountId(UUID.randomUUID().toString())
									.balance(50000)
									.type(AccountType.SAVING_ACCOUNT)
									.currency("XOF")
									.createdAt(LocalDate.now())
									.customerId(c.getId())
									.build();


				bankAccountRepository.save(bankAccount1);
				bankAccountRepository.save(bankAccount2);

			});

		};

	}

}
