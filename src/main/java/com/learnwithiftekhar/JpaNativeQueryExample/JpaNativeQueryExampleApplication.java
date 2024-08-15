package com.learnwithiftekhar.JpaNativeQueryExample;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootApplication
public class JpaNativeQueryExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaNativeQueryExampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(BookRepository repository) {
		return args -> {
			repository
					.findBooksByTitleThatContainsWord("Men")
					.forEach(System.out::println);


			System.out.println("@@@@@");

			Pageable pageable = PageRequest.of(0,5);

			repository.search("tale", pageable).forEach(System.out::println);


			repository.updatePrice(50, 1L);
			repository.findById(1L).ifPresent(System.out::println);
		};
	}
}
