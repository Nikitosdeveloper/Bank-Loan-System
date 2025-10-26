package com.busir.gardarian.bankloansystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankLoanSystemApplication {
	private static final Logger logger = LoggerFactory.getLogger(BankLoanSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BankLoanSystemApplication.class, args);
		logger.info("Bank Loan System successfully started!");
		logger.info("Port: 8080");
	}

}
