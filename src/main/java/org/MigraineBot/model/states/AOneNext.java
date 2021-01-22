package org.MigraineBot.model.states;

abstract public class AOneNext extends AState {
    State next;

    public AOneNext(State next) {
        super();
        this.next = next;
    }

    @Override
    public State processRequest(long chatId, String text) {
        return next;
    }
}
