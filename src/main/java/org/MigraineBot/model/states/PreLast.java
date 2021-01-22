package org.MigraineBot.model.states;

import org.MigraineBot.model.Constants;

public class PreLast extends TextNext {
    public PreLast(State next) {
        super(Constants.LastAnswer, new Last(null));
    }
}
