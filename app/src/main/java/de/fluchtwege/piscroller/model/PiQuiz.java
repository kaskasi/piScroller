package de.fluchtwege.piscroller.model;

import java.io.Serializable;

public class PiQuiz implements Serializable {

    private int position;
    private CharSequence answer;

    public PiQuiz(int position) {
        this.position = position;
        this.answer = "";
    }

    public int getPosition() {
        return position;
    }

    public CharSequence getAnswer() {
        return answer;
    }

    public void setAnswer(CharSequence answer) {
        this.answer = answer;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
