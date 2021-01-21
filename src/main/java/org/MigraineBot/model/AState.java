package org.MigraineBot.model;

import org.MigraineBot.tg.MigraineBot;

abstract public class AState implements State {
    static private final MigraineBot bot = MigraineBot.getInstance();

    protected void sendText(String text){
        bot.sendSex(1);
    }

    State next;

    private AState() {
    }

    public AState(State next) {
        this.next = next;
    }

    abstract public State processRequest(String text);
}
