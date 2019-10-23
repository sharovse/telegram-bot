package ru.sharovse.telegrambot;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import ru.sharovse.telegrambot.bots.CommandBot;
import ru.sharovse.telegrambot.bots.EchoBot;

@Component
public class ReesterBots {
	static Logger log = LoggerFactory.getLogger(ReesterBots.class);
	
	@Autowired
	EchoBot myBot1;
	
	@Autowired
	CommandBot commandBot; 
	
	@PostConstruct
	public void startBot() throws TelegramApiRequestException {
		final TelegramBotsApi botsApi = new TelegramBotsApi();
		botsApi.registerBot(myBot1);
		botsApi.registerBot(commandBot);
	}

}
