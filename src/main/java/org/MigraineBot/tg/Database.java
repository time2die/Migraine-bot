package org.MigraineBot.tg;

import java.util.*;

public class Database {
    private static Set<Long> allChatId;

    private Map<Integer, String> idName;


    public Database() {
        this.allChatId = new HashSet<Long>();
        this.idName = new HashMap<Integer, String>();
    }

    public synchronized void setIdName(int userId, String userName){
        idName.put(userId, userName);
    }


    public synchronized void setChatId(long chatId) {
        allChatId.add(chatId);
    }


    public static Set<Long> getAllChatId() {
        return allChatId;
    }

    public Map<Integer, String> getIdName() {
        return idName;
    }
}
