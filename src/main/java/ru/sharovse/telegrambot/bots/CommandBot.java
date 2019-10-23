package ru.sharovse.telegrambot.bots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import ru.sharovse.telegrambot.bots.commands.HelloCommand;
import ru.sharovse.telegrambot.bots.commands.HelpCommand;
import ru.sharovse.telegrambot.services.Emoji;

@Component
public class CommandBot extends TelegramLongPollingCommandBot {
	static Logger log = LoggerFactory.getLogger(CommandBot.class);

	@Value("${bot.command.token}")
	private String botToken;

	@Autowired
	public CommandBot(
			DefaultBotOptions options, 
			@Value("${bot.command.user}") String botUsername,
			HelloCommand helloCommand
			) {
		super(options, botUsername);
		HelpCommand helpCommand = new HelpCommand(this);

		register(helloCommand);
		register(helpCommand);
		
		registerDefaultAction((absSender, message) -> {
            SendMessage commandUnknownMessage = new SendMessage();
            commandUnknownMessage.setChatId(message.getChatId());
            commandUnknownMessage.setText("Команда '" + message.getText() + "' не известна. Может help " + Emoji.AMBULANCE);
            try {
                absSender.execute(commandUnknownMessage);
            } catch (TelegramApiException e) {
                log.error("", e);
            }
            helpCommand.execute(absSender, message.getFrom(), message.getChat(), new String[] {});
        });
	}


	@Override
	public void processNonCommandUpdate(Update update) {
		if (update.hasMessage()) {
			Message message = update.getMessage();
			if (message.hasText()) {
				SendMessage echoMessage = new SendMessage();
				echoMessage.setChatId(message.getChatId());
				echoMessage.setText("Это ваше сообшение:\n" + message.getText());
				try {
					execute(echoMessage);
				} catch (TelegramApiException e) {
					log.error("", e);
				}
			}
		}
	}

	@Override
	public String getBotToken() {
		return botToken;
	}

}
