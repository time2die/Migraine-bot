package org.MigraineBot.model;

public class LastState extends AState {
    public LastState(State next) {
        super(next);
    }

    @Override
    public State processRequest(String text) {
        sendText(Constants.WaitRequest);
        return this;
    }
}
