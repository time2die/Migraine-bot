package org.MigraineBot.tg;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;

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

    Status stat;

    public MigraineBot() {
        this.stat = new Status();

        System.out.printf("MIGRAINE_BOT_NAME:%s\nMIGRAINE_BOT_TOKEN:%s\n",getBotUsername(),getBotToken());
    }


    @Override
    public String getBotUsername() {
        return System.getenv("MIGRAINE_BOT_NAME");
    }

    @Override
    public String getBotToken() {
        String token = System.getenv("MIGRAINE_BOT_TOKEN");
        return token;

    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        stat.setIdName(message.getFrom().getId(), message.getFrom().getUserName());
        stat.incCount(update.getMessage().getChatId(), message.getFrom().getId());

        if (message == null || !message.hasText()) {
            return;
        }

        if (message.getText().equals("/help")) {
            sendMsg(message, "Cry, bitch");
        }
        else {
            sendMsg(message, "Hy");
        }

    }



    protected void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void sendMsg(Long ChatId, String text) {

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

    public SendMessage sendSex(long chatId) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Male");
        inlineKeyboardButton1.setCallbackData("Button \"Male\" has been pressed");
        inlineKeyboardButton2.setText("Female");
        inlineKeyboardButton2.setCallbackData("Button \"Female\" has been pressed");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage here = new SendMessage();
        here.setChatId(String.valueOf(chatId));
        here.setText("Indicate your gender:");
        here.setReplyMarkup(inlineKeyboardMarkup);

        return here;
    }


    public synchronized void sendAllStatistic() {

        ArrayList<Long> str = stat.getChatsId();
        for (int i = 0; i < str.size(); i++) {
            sendMsg(str.get(i), stat.getCount(str.get(i)));
        }
    }
}

