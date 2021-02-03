package org.MigraineBot;

import org.MigraineBot.db.Repo;
import org.MigraineBot.tg.MigraineBot;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimerTask;

public class MessageByTime extends TimerTask {

    Calendar lastTime = new GregorianCalendar();

    @Override
    public void run() {
        if (timeToSend()) {
            lastTime = new GregorianCalendar();
            MigraineBot status = MigraineBot.getInstance();

            for (Long userId : Repo.GetUsers()) {
                MigraineBot.getInstance().reset(userId);
            }

        }
    }

    public boolean timeToSend() {
        Calendar currentCalendar = new GregorianCalendar();
        var minute = currentCalendar.get(Calendar.MINUTE);
        return minute == 0 && lastTime.get(Calendar.MINUTE) != minute;
//        return currentCalendar.get(Calendar.DAY_OF_MONTH) > calendarSend.get(Calendar.DAY_OF_MONTH) &&
//                currentCalendar.get(Calendar.HOUR) == 22 &&
//                currentCalendar.get(Calendar.MINUTE) == 07;
    }
}