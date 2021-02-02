package org.MigraineBot;

import org.MigraineBot.db.Repo;
import org.MigraineBot.tg.Database;
import org.MigraineBot.tg.MigraineBot;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimerTask;

public class MessageByTime extends TimerTask {

    public MessageByTime() {
    }

    @Override
    public void run() {
        if (timeToSend()) {
            MigraineBot status = MigraineBot.getInstance();

            for (Long text : Repo.GetUsers()) {
                status.sendMsg(text, Constants.CronMessage);
            }

        }
    }

    public boolean timeToSend() {
        Calendar currentCalendar = new GregorianCalendar();
        var minute = currentCalendar.get(Calendar.MINUTE);
        return minute % 15 == 0 ;
//        return currentCalendar.get(Calendar.DAY_OF_MONTH) > calendarSend.get(Calendar.DAY_OF_MONTH) &&
//                currentCalendar.get(Calendar.HOUR) == 22 &&
//                currentCalendar.get(Calendar.MINUTE) == 07;
    }
}