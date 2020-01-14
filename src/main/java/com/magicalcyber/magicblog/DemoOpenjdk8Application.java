package com.magicalcyber.magicblog;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoOpenjdk8Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoOpenjdk8Application.class, args);
	}

	@RestController
	class HelloController {

		private final Logger logger = LoggerFactory.getLogger(HelloController.class);

		@GetMapping("/hello")
		ResponseEntity<String> hello() {
			long begin = System.nanoTime();

			logger.info("Begin process");
			// do some long process
			int beginChar = 97;
			int endChar = 122;
			int maxSize = 100;

			Random random = new Random();
			String data = "";
			for (int i = 0; i < 10000; i++) {
				data += random.ints(beginChar, endChar).limit(maxSize)
						.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
			}

			logger.info("Process : " + data.length() + " size");
			long end = System.nanoTime();
			logger.info("Used time: " + (TimeUnit.NANOSECONDS.toMillis(end - begin)) + " millisecs");

			return ResponseEntity.ok("success");
		}
	}

}
