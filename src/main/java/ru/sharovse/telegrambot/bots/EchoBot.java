package ru.sharovse.telegrambot.bots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class EchoBot extends TelegramLongPollingBot {
	static Logger log = LoggerFactory.getLogger(EchoBot.class);
	
	@Value("${bot.echo.token}")
	private String botToken;
	@Value("${bot.echo.user}")
	private String botUser;

	@Autowired
	public EchoBot(DefaultBotOptions options) {
		super(options);
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
	        SendMessage message = new SendMessage() 
	                .setChatId(update.getMessage().getChatId())
	                .setText(update.getMessage().getText());
	        try {
	            execute(message); 
	        } catch (TelegramApiException e) {
	            log.error("Error",e);
	        }
	    }
	}

	@Override
	public String getBotUsername() {
		return botUser;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}

}
