package org.MigraineBot.model.states;

import org.MigraineBot.model.Constants;

public class PreLast extends AState {
    public PreLast(State next) {
        super(null);
    }

    @Override
    public State processRequest(long chatId, String text) {
        sendText(chatId, Constants.LastAnswer);
        return new LastState(null);
    }
}
