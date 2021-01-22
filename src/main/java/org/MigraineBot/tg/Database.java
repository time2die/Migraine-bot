package org.MigraineBot.tg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Database {
    private static Set<Long> allChatId = new HashSet<Long>();

    private Map<Integer, String> idName;


    public Database() {
        this.idName = new HashMap<Integer, String>();
    }

    public synchronized void setIdName(int userId, String userName) {
        idName.put(userId, userName);
    }


    public synchronized void addChatId(long chatId) {
        allChatId.add(chatId);
    }


    public static Set<Long> chatIds() {
        return new HashSet<>(allChatId);
    }

    public Map<Integer, String> getIdName() {
        return idName;
    }
}
