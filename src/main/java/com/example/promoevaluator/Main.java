package com.example.promoevaluator;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.promoevaluator.model.demo.event.OrderCancelled;
import com.example.promoevaluator.model.demo.event.OrderCreated;
import com.example.promoevaluator.model.demo.event.OrderEvent;
import com.example.promoevaluator.model.demo.event.OrderItemQuantityUpdated;

@SpringBootApplication
@RegisterReflectionForBinding({OrderEvent.class, OrderCreated.class, OrderCancelled.class, OrderItemQuantityUpdated.class})
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
