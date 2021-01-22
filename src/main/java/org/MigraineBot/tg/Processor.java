package org.MigraineBot.tg;

import org.MigraineBot.model.states.Flow;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

public class Processor {

    Map<Long, User> users = new HashMap<>();

    public void process(Update update) {
        Message message = update.getMessage();
        if (message == null || !message.hasText()) {
            return;
        }

        users.getOrDefault(message.getChatId(), new User(message.getChatId(), Flow.firstState()))
                .processRequest(message.getChatId(), message.getText());
    }

}
