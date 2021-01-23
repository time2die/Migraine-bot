package org.MigraineBot.model.states;

public interface State {
    State processRequest(long chatId, String text);
    void turnOn(long chatId) ;
}
