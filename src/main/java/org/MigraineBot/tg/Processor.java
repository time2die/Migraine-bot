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
//        if (message == null || !message.hasText()) {
//            return;
//        }
        var chatId = getChatId(update);

        if (message != null) {

            chatId = message.getChatId();
            Repo.CreateUser(chatId);

            switch (message.getText().toLowerCase()) {
                case "/help":
                    MigraineBot.getInstance().sendMsg(chatId, Constants.HelpMessage);
                    return;
                case "/reset":
                    reset(chatId);
                    return;
                case "/start":
                    start(chatId);
                    return;
            }
        }


        var nUser = users.get(chatId);
        if (nUser == null) {
            nUser = buildDefault(chatId);
        }

        if (message == null) {
            nUser.processRequest(chatId, update.getCallbackQuery().getData());
            users.put(chatId, nUser);
            return;
        }

        nUser.processRequest(chatId, message.getText());
        users.put(chatId, nUser);
    }

    public void reset(Long chatId) {
        users.remove(chatId);
        start(chatId);
    }

    public void start(Long chatId) {
        users.remove(chatId);
        var nUser = users.getOrDefault(chatId, buildDefault(chatId));
        users.put(chatId, nUser);
    }

    User buildDefault(Long chatId) {
        return new User(chatId, Flow.firstState());
    }

    private Long getChatId(Update u) {
        var m = u.getMessage();
        if (m != null) {
            return m.getChatId();
        }

        var cq = u.getCallbackQuery();
        if (cq != null && cq.getFrom() != null) {
            return cq.getFrom().getId().longValue();
        }

        return 0L;
    }

}
