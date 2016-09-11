package de.fluchtwege.piscroller.model;

import java.io.Serializable;

public class PiQuiz implements Serializable {

    private final int position;
    private final char digitOfPi;
    private CharSequence answer;

    public PiQuiz(int position, char digitOfPi) {
        this.position = position;
        this.digitOfPi = digitOfPi;
        this.answer = "";
    }

    public char getDigitOfPi() {
        return digitOfPi;
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
}
