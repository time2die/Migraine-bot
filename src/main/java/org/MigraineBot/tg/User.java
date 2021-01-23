package org.MigraineBot.tg;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.MigraineBot.model.states.State;

public class User {
    private User() {
    }

    public User(long id, State state) {
        this.id = id;
        this.state = state;
        state.turnOn(id);
    }

    @Getter
    @ToString.Include
    @EqualsAndHashCode.Include
    long id;

    @Getter
    @ToString.Include
    @EqualsAndHashCode.Include
    State state;

    public User processRequest(long chatId, String text) {
        state = state.processRequest(chatId, text);
        state.turnOn(chatId);
        return this;
    }
}
