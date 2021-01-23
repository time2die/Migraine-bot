package org.MigraineBot.model.states;

import lombok.Setter;
@Setter
public class YesNoRepeat extends AState {

    String question ;
    State yState;
    State nState;

    YesNoRepeat(String question) {
        super();
        this.question = question;
    }

    @Override
    public State processRequest(long chatId, String text) {
        switch (text.toLowerCase()) {
            case "да":
                return yState;
            case "нет":
                return nState;
            default:
                sendText(chatId, "Используйте да\\нет\n");
                return this;
        }
    }

    @Override
    public void turnOn(long chatId) {
        sendText(chatId, question);
    }
}
