package org.MigraineBot.model.states;

public class YesNoRepeat extends AState {

    State yState;
    State nState;

    YesNoRepeat(String question, State yes, State no) {
        super();

        this.yState = yes;
        this.nState = no;
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
