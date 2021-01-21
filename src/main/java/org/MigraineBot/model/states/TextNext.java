package org.MigraineBot.model.states;

public class TextNext extends AOneNext {
    String text ;

    public TextNext(String text,State next) {
        super(next);
        this.text = text ;
    }

    @Override
    public State processRequest(long chatId, String text) {
        sendText(chatId, text);
        return this.next;
    }
}
