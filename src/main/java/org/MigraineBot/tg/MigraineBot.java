package org.MigraineBot.tg;


import org.MigraineBot.Constants;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MigraineBot extends TelegramLongPollingBot {

    private static volatile MigraineBot instance;
    private static final Processor processor = new Processor();

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


    public MigraineBot() {
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
        try {
            Message message = update.getMessage();

            if (message != null && message.getFrom().getId().longValue() != message.getChatId() && message.getText().startsWith("/")) {
                sendMsg(message.getChatId(), Constants.OnlyDirectMessage);
                return;
            }

            processor.process(update);

        } catch (Exception e) {
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

    public void sendSex(SendMessage sm) {
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void reset(Long chatId) {
        processor.reset(chatId);
    }
}