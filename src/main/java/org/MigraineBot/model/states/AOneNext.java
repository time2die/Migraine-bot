package org.MigraineBot.model.states;

import lombok.Setter;

abstract public class AOneNext extends AState {
    @Setter
    State next;

    public AOneNext(State next) {
        super();
        this.next = next;
    }

    @Override
    public State processRequest(long chatId, String text) {
        return next;
    }

    @Override
    public void turnOn(long chatId) {
    }

}
