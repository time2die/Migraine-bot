package org.MigraineBot.tg;

import lombok.*;
import org.MigraineBot.model.State;
import lombok.ToString;

public class User {
    private User() {}

    public User( long id, State state) {
        this.id = id ;
        this.state = state ;
    }

    @Getter
    @ToString.Include
    @EqualsAndHashCode.Include
    long id;

    @Getter
    @ToString.Include
    @EqualsAndHashCode.Include
    State state;
}
