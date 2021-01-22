package org.MigraineBot.model.states;

public class Flow {

    public static State firstState() {
        PreLast pl = new PreLast(new Last(null));
        YesNoRepeat illToday = new YesNoRepeat("Болела ли сегодня голова?", pl, new TextNext("Живи там хорошо не возвращайся никогда", pl));


        return new PreLast(new Last(null));
    }

}
