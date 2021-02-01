package org.MigraineBot;

import org.MigraineBot.db.Repo;
import org.MigraineBot.tg.Database;
import org.MigraineBot.tg.MigraineBot;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimerTask;

public class MessageByTime extends TimerTask {

    Calendar calendarSend;

    public MessageByTime() {
        calendarSend = new GregorianCalendar();
        calendarSend.add(Calendar.DAY_OF_YEAR, -1);
    }

    @Override
    public void run() {

//        if (timeToSend()) {

            MigraineBot status = MigraineBot.getInstance();

            for (Long text : Repo.GetUsers()) {
                status.sendMsg(text, "С некоторой переодичностью буду вас дергать");
            }

//            calendarSend = Calendar.getInstance();
//        }
    }

    public boolean timeToSend() {
        Calendar currentCalendar = new GregorianCalendar();

        return currentCalendar.get(Calendar.DAY_OF_MONTH) > calendarSend.get(Calendar.DAY_OF_MONTH) &&
                currentCalendar.get(Calendar.HOUR) == 22 &&
                currentCalendar.get(Calendar.MINUTE) == 07;
    }
}