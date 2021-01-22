package org.MigraineBot.model.states;

import lombok.Setter;
@Setter
public class YesNoRepeat extends AState {


    State yState;
    State nState;

    YesNoRepeat(String question) {
        super();
    }

    @Override
    public State processRequest(long chatId, String text) {
        switch (text) {
            case "да":
                return yState;
            case "нет":
                return nState;
            default:
                sendText(chatId, "Используйте да\\нет\n");
                return this;
        }
    }
}
