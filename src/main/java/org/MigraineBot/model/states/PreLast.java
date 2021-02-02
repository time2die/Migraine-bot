package org.MigraineBot.model.states;

import org.MigraineBot.Constants;

public class PreLast extends TextNext {
    public PreLast(State next) {
        super(Constants.LastAnswer, new Last(null));
    }
}
