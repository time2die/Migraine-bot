package org.MigraineBot.model.states;

import org.MigraineBot.model.Constants;

public class LastState extends AState {
    public LastState(State next) {
        super(null);
    }

    @Override
    public State processRequest(long chatId, String text) {
        sendText(chatId, Constants.WaitRequest);
        return this;
    }
}
