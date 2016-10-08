package de.fluchtwege.piscroller.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.VisibleForTesting;

import de.fluchtwege.piscroller.model.PiProvider;
import de.fluchtwege.piscroller.model.PiQuiz;

public class PiQuizViewModel extends BaseObservable {

    private final PiProvider piProvider = new PiProvider();
    private final PiQuiz piQuiz;

    public PiQuizViewModel(PiQuiz piQuiz) {
        this.piQuiz = piQuiz;
    }

    public PiQuiz getPiQuiz() {
        return piQuiz;
    }

    public void onNext() {
        int nextPosition = findNextPosition();
        setupNextQuiz(nextPosition);
        notifyChange();
    }

    private int findNextPosition() {
        if (isNextPositionAvailable() && isAnswerCorrect()) {
            return piQuiz.getPosition() + 1;
        }
        return 0;
    }

    @VisibleForTesting
    public void setupNextQuiz(int nextPosition) {
        piQuiz.setPosition(nextPosition);
        piQuiz.setAnswer("");
    }

    @Bindable
    public boolean isAtStart() {
        return piQuiz.getPosition() == 0;
    }

    @Bindable
    public boolean isNextPositionAvailable() {
        return piQuiz.getPosition() < piProvider.getNumberOfDigitsOfPi();
    }

    @Bindable
    public CharSequence getAnswer() {
        return piQuiz.getAnswer();
    }

    @Bindable
    public void setAnswer(CharSequence answer) {
        piQuiz.setAnswer(answer);
        notifyChange();
    }

    @Bindable
    public String getDigitPosition() {
        return String.valueOf(piQuiz.getPosition());
    }

    @Bindable
    public boolean isAnswerEntered() {
        return !"".equals(piQuiz.getAnswer());
    }

    @Bindable
    public boolean isAnswerCorrect() {
        if (!isAnswerEntered()) {
            return false;
        }
        int position = piQuiz.getPosition();
        String digitOfPi = String.valueOf(piProvider.getDigitOfPi(position));
        return piQuiz.getAnswer().equals(digitOfPi);
    }
}
