package ru.sharovse.telegrambot.bots.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class HelloCommand extends BotCommand {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	public HelloCommand() {
		super("hello", "Поздоровайтесь");
	}

	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
		String userName = chat.getUserName();
        if (userName == null || userName.isEmpty()) {
            userName = user.getFirstName() + " " + user.getLastName();
        }

        StringBuilder messageTextBuilder = new StringBuilder("Привет ").append(userName);
        if (arguments != null && arguments.length > 0) {
            messageTextBuilder.append("\n");
            messageTextBuilder.append("Спасибо за ваши теплые слова:\n");
            messageTextBuilder.append(String.join(" ", arguments));
        }

        SendMessage answer = new SendMessage();
        answer.setChatId(chat.getId().toString());
        answer.setText(messageTextBuilder.toString());

        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            log.error("", e);
        }
		
	}

	
}
