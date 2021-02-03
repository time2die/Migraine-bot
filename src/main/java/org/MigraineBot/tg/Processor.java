package org.MigraineBot.tg;

import org.MigraineBot.Constants;
import org.MigraineBot.db.Repo;
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

        var chatId = message.getChatId();

        Repo.CreateUser(chatId);

        switch (message.getText().toLowerCase()) {
            case "/help":
                MigraineBot.getInstance().sendMsg(chatId, Constants.HelpMessage);
                return;
            case "/reset":
                reset(chatId);
                return;
            case "/start":
                users.remove(chatId);
                var nUser = users.getOrDefault(chatId, new User(chatId, Flow.firstState()));
                users.put(chatId, nUser);
                return;
        }


        var nUser = users.get(chatId);
        if (nUser == null) {
            nUser = buildDefault(chatId);
        }
        nUser.processRequest(chatId, message.getText());
        users.put(chatId, nUser);

    }

    public void reset(Long chatId) {
        users.remove(chatId);
    }

    User buildDefault(Long chatId) {
        return new User(chatId, Flow.firstState());
    }

}
