package com.kms.task;

import com.kms.task.service.MainDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KmsHomeTaskApplication {

	@Autowired
	private static MainDisplayService mainDisplayService = null;

	public KmsHomeTaskApplication(MainDisplayService mainDisplayService) {
		KmsHomeTaskApplication.mainDisplayService = mainDisplayService;
	}

	public static void main(String[] args) {
		SpringApplication.run(KmsHomeTaskApplication.class, args);
		mainDisplayService.mainDisplay();
	}
}
