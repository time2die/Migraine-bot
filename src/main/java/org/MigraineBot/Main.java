package org.MigraineBot;

import org.MigraineBot.db.Repo;
import org.MigraineBot.tg.MigraineBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {

        new Repo();

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(MigraineBot.getInstance());

        } catch (TelegramApiException e) {
            e.printStackTrace();

        }

        Calendar today = Calendar.getInstance();

        Timer timer = new Timer();
        timer.schedule(new MessageByTime(), today.getTime(), TimeUnit.MILLISECONDS.convert(30, TimeUnit.SECONDS));
    }
}
