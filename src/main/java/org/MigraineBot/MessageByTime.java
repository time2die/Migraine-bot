package org.MigraineBot;
import org.MigraineBot.tg.MigraineBot;
import org.MigraineBot.tg.Database;

import java.util.*;

public class MessageByTime extends TimerTask {

    Set <Long> id = new HashSet<>();
    Calendar  calendarSend = new GregorianCalendar();

    public MessageByTime() {
        calendarSend = new GregorianCalendar();
        calendarSend.add(Calendar.DAY_OF_YEAR, -1);
        id = Database.getAllChatId();
    }

    @Override
    public void run() {

        Calendar currentCalendar = new GregorianCalendar();

        if (currentCalendar.get(Calendar.DAY_OF_MONTH) > calendarSend.get(Calendar.DAY_OF_MONTH) &
                currentCalendar.get(Calendar.HOUR) == 22 &
                currentCalendar.get(Calendar.MINUTE) == 07){

            MigraineBot status = MigraineBot.getInstance();

            for (Long text : id)
            {
                status.sendMsg(text, "Hello");
            }

            calendarSend=Calendar.getInstance();

        }
    }
}