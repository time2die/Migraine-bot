package org.MigraineBot.model.states;

import lombok.Setter;
import org.MigraineBot.tg.MigraineBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ButtonTextNext extends AState {
    @Setter
    State next;

    boolean firstTime = true;

    public ButtonTextNext(State next) {
        super();
        this.next = next;
    }

    @Override
    public State processRequest(long chatId, String text) {
        switch (text) {
            case "1":
                sendText(chatId, "Спасибо что выбрали утро");
                break;
            case "2":
                sendText(chatId, "Спасибо что выбрали день");
                break;
            case "3":
                sendText(chatId, "Спасибо что выбрали вечер");
                break;
            default:
                break;
        }

        return next;
    }

    @Override
    public void turnOn(long chatId) {
        if (firstTime) {
            MigraineBot.getInstance().sendSex(buildMessage(chatId));
            firstTime = false;
        }

    }


    public SendMessage buildMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("утро");
        inlineKeyboardButton1.setCallbackData("1");
        inlineKeyboardButton2.setText("день");
        inlineKeyboardButton2.setCallbackData("2");
        inlineKeyboardButton3.setText("вечер");
        inlineKeyboardButton3.setCallbackData("3");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();

        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        keyboardButtonsRow3.add(inlineKeyboardButton3);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage here = new SendMessage();
        here.setChatId(String.valueOf(chatId));
        here.setText("Выберите комфортное для вас время суток");
        here.setReplyMarkup(inlineKeyboardMarkup);
        return here;
    }
}
