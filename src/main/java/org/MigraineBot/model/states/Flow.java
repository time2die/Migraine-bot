package org.MigraineBot.model.states;

import org.MigraineBot.model.Constants;

public class Flow {

    public static State buildFlow() {
        PreLast pl = new PreLast(new Last(null));
        YesNoRepeat illToday = new YesNoRepeat("Болела ли сегодня голова?", pl, new TextNext("Живи там хорошо не возвращайся никогда",pl));
        return null;
    }

    public  static State firstState() {
        return new PreLast(new Last(null)) ;
    }

}