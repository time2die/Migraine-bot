package org.MigraineBot.model.states;

import lombok.Setter;

public class IntAnswer extends AState {

    String question;
    Integer min;
    Integer max;

    @Setter
    State next;

    IntAnswer(String question, Integer min, Integer max) {
        super();
        this.question = question;
        this.max = max;
        this.min = min;
    }

    @Override
    public State processRequest(long chatId, String text) {

        int value = 0;

        try {
            value = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            sendText(chatId, buildWarn());
            return this;
        }

        if (value > max || value < min) {
            sendText(chatId, buildWarn());
            return this;
        }

        return next;
    }

    @Override
    public void turnOn(long chatId) {
        sendText(chatId, question);
    }

    public String buildWarn() {
        return "Необходимо использовать значения от " + min + " до" + max;
    }
}