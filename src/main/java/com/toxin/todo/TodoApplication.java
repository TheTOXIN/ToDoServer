package com.toxin.todo;

import com.toxin.todo.util.Initializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@EventListener
	public void initConstants(ApplicationReadyEvent event) {
		event.getApplicationContext().getBean(Initializer.class).init();
	}

}
