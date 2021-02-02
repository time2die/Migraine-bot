package org.MigraineBot.model.states;

import org.MigraineBot.Constants;

public class Last extends AOneNext {
    public Last(State next) {
        super(null);
    }

    @Override
    public State processRequest(long chatId, String text) {
        sendText(chatId, Constants.WaitRequest);
        return this;
    }
}
