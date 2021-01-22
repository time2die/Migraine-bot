package org.MigraineBot.model.states;

import org.w3c.dom.Text;

public class Flow {

    public static State firstState() {
        Last l = new Last(null) ;
        PreLast pl = new PreLast(l);

        YesNoRepeat illToday = new YesNoRepeat("Болела ли сегодня голова ?");
        YesNoRepeat needHelp = new YesNoRepeat("Сама прошла ?");
        YesNoRepeat needPils = new YesNoRepeat("Принимали таблетки ?");

        TextNext goodDay = new TextNext("Ну и славно",l);

        illToday.setYState(needPils);
        illToday.setNState(pl);

        needPils.setNState(needHelp);

        needHelp.setNState(goodDay);
        needHelp.setYState(goodDay);

        return illToday;
//        return new PreLast(new Last(null));
    }

}
