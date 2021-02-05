package org.MigraineBot.model.states;

public class TextNext extends AOneNext {
    String text;

    public TextNext(String text, State next) {
        super(next);
        this.text = text;
    }

    @Override
    public State processRequest(long chatId, String t) {
        return this.next.processRequest(chatId, t);
    }

    @Override
    public void turnOn(long chatId) {
        sendText(chatId, this.text);
        this.next.turnOn(chatId);
    }
}
