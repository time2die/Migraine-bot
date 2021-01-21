package org.MigraineBot.model.states;

import org.MigraineBot.tg.MigraineBot;

abstract public class AState implements State {
    static private final MigraineBot bot = MigraineBot.getInstance();

    protected void sendText(long chatId, String text) {
        //todo fix me
    }

    protected AState() {
    }


    @Override
    abstract public State processRequest(long chatId, String text);
}
