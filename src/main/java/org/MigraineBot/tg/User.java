package org.MigraineBot.tg;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.MigraineBot.model.states.State;

public class User implements State {
    private User() {
    }

    public User(long id, State state) {
        this.id = id;
        this.state = state;
    }

    @Getter
    @ToString.Include
    @EqualsAndHashCode.Include
    long id;

    @Getter
    @ToString.Include
    @EqualsAndHashCode.Include
    State state;

    @Override
    public State processRequest(long chatId, String text) {
        state = state.processRequest(chatId, text);
        return this;
    }
}
