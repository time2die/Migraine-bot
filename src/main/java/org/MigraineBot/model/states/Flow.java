package org.MigraineBot.model.states;

public class Flow {

        public static State firstState() {
            Last l = new Last(null) ;
            return l ;
        }

//    public static State firstState() {
//        Last l = new Last(null) ;
//        PreLast pl = new PreLast(l);
//
//        YesNoRepeat illToday = new YesNoRepeat("Болела ли сегодня голова ?");
//        YesNoRepeat needHelp = new YesNoRepeat("Сама прошла ?");
//        YesNoRepeat needPils = new YesNoRepeat("Принимали таблетки ?");
//
//        TextNext goodDay = new TextNext("Ну и славно",l);
//
//        IntAnswer painLevel = new IntAnswer("Оцените уровень боли от 1 до 10",1,10);
//        painLevel.setNext(goodDay);
//
//        illToday.setYState(needPils);
//        illToday.setNState(pl);
//
//        needPils.setNState(painLevel);
//        needPils.setYState(needHelp);
//
//        needHelp.setNState(goodDay);
//        needHelp.setYState(goodDay);
//
//        return illToday;
//        return new PreLast(new Last(null));
//    }

}
