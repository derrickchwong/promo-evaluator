package com.example.promoevaluator;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.promoevaluator.model.Campaign;
import com.example.promoevaluator.model.Order;

@SpringBootApplication
@RegisterReflectionForBinding({Order.class, Campaign.class})
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
