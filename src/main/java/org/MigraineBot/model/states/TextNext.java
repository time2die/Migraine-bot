package org.MigraineBot.model.states;

public class TextNext extends AOneNext {
    String text;

    public TextNext(String text, State next) {
        super(next);
        this.text = text;
    }

    @Override
    public State processRequest(long chatId, String t) {
        sendText(chatId, this.text);
        return this.next;
    }
}
