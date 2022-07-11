package com.example.demo;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/threads")
	public String threads() {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		return threadSet.toString();
	}

	@GetMapping("/start")
	public String start() {
		System.out.println("Calling start");
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(() -> {
            System.out.println("Starting job");
            try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        });
        System.out.println("Ending start");

		return "Started";
	}

}
