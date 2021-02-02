package org.MigraineBot.model.states;

public class TextNext extends AOneNext {
    String text;

    public TextNext(String text, State next) {
        super(next);
        this.text = text;
    }

    @Override
    public State processRequest(long chatId, String t) {
        this.next.processRequest(chatId, t);
        return this.next;
    }

    @Override
    public void turnOn(long chatId) {
        sendText(chatId, this.text);
    }
}
