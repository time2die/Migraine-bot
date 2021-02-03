package org.MigraineBot.model.states;

import org.MigraineBot.db.Repo;

public class ReadAnswers extends AOneNext {

    public ReadAnswers(State next) {
        super(next);
    }

    @Override
    public State processRequest(long chatId, String t) {
        return this.next;
    }

    @Override
    public void turnOn(long chatId) {
        var aa = Repo.Answers(chatId, 1488);
        if (aa.isEmpty()) {
            sendText(chatId, "Вы еще ничего не возвращали");
            return;
        }
        if (aa.size() > 5) {
            aa = aa.subList(0, 5);
        }

        var res = new StringBuffer();

        res.append("Ваши ответы по частоте повторения\n");
        for (int i = 1; i < 6; i++) {
            res.append(i);
            res.append(". ");
            res.append(aa.get(i - 1));
            res.append("\n");
        }
        sendText(chatId, res.toString());

        next.turnOn(chatId);
    }
}
