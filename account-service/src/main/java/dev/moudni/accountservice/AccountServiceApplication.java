package dev.moudni.accountservice;

import dev.moudni.accountservice.entites.Account;
import dev.moudni.accountservice.enums.AccountType;
import dev.moudni.accountservice.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AccountRepository accountRepository){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				List<Account> accounts = List.of(
						Account.builder()
								.accountId(UUID.randomUUID().toString())
								.balance(100)
								.currency("MAD")
								.type(AccountType.CURRENT)
								.createdAt(LocalDate.now())
								.customerId(1L)
								.build(),

						Account.builder()
								.accountId(UUID.randomUUID().toString())
								.balance(300)
								.currency("EUR")
								.type(AccountType.CURRENT)
								.createdAt(LocalDate.now())
								.customerId(2L)
								.build()
				);
				accountRepository.saveAll(accounts);
			}
		};
	}
}
