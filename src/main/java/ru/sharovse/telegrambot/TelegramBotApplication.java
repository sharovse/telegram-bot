package ru.sharovse.telegrambot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegramBotApplication {

	@Value("${var}")
	String var;
	
	public static void main(String[] args) {
		SpringApplication.run(TelegramBotApplication.class, args);
	}

}
