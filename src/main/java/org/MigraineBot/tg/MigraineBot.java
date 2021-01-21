package org.MigraineBot.tg;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MigraineBot extends TelegramLongPollingBot {

    private static volatile MigraineBot instance;

    public static MigraineBot getInstance() {
        MigraineBot localInstance = instance;
        if (localInstance == null) {
            synchronized (MigraineBot.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MigraineBot();
                }
            }
        }
        return localInstance;
    }

    Database stat;

    public MigraineBot() {
        this.stat = new Database();
        System.out.printf("MIGRAINE_BOT_NAME:%s\nMIGRAINE_BOT_TOKEN:%s\n", getBotUsername(), getBotToken());
    }


    @Override
    public String getBotUsername() {
        return System.getenv("MIGRAINE_BOT_NAME");
    }

    @Override
    public String getBotToken() {
        return System.getenv("MIGRAINE_BOT_TOKEN");

    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        stat.setIdName(message.getFrom().getId(), message.getFrom().getUserName());
        stat.setChatId(update.getMessage().getChatId());

        if (message == null || !message.hasText()) {
            return;
        }

        if (message.getText().equals("/help")) {
            sendMsg(message, "Hy!");
        } else {
            sendMsg(message, "Hy");
        }

    }


    protected void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Long ChatId, String text) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(ChatId.toString());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}