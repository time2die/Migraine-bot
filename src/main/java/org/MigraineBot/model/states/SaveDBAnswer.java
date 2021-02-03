package org.MigraineBot.model.states;

import org.MigraineBot.db.Repo;

public class SaveDBAnswer extends AOneNext {
    String text;
    Long qNumber;

    public SaveDBAnswer(String text, Long qNumber) {
        super(null);
        this.qNumber = qNumber;
        this.text = text;
    }

    @Override
    public State processRequest(long chatId, String t) {
        Repo.SafeAnswer(chatId, qNumber, t);
        return this.next;
    }

    @Override
    public void turnOn(long chatId) {
        sendText(chatId, this.text);
    }
}
