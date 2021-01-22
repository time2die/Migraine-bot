package org.MigraineBot.tg;

import org.MigraineBot.model.Constants;
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

        switch (message.getText().toLowerCase()) {
            case "/help":
                MigraineBot.getInstance().sendMsg(message.getChatId(), Constants.HelpMessage);
                return;
            case "/reset":
                users.remove(message.getChatId());
                return;
        }

        var nUser = users.getOrDefault(message.getChatId(), new User(message.getChatId(), Flow.firstState()));
        nUser.processRequest(message.getChatId(), message.getText());
        users.put(message.getChatId(), nUser);

    }

}
