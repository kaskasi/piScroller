package de.fluchtwege.piscroller.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.Random;

import de.fluchtwege.piscroller.model.PiProvider;
import de.fluchtwege.piscroller.model.PiQuiz;

public class PiQuizViewModel extends BaseObservable {

    private final PiProvider piProvider = new PiProvider();
    private PiQuiz piQuiz;

    public PiQuizViewModel(PiQuiz piQuiz) {
        this.piQuiz = piQuiz;
    }

    public PiQuiz getPiQuiz() {
        return piQuiz;
    }

    public void onNext() {
        createPiQuiz();
        notifyChange();
    }

    private void createPiQuiz() {
        int numberOfDigitsOfPi = piProvider.getNumberOfDigitsOfPi();
        int digitPosition = new Random().nextInt(numberOfDigitsOfPi);
        char digitOfPi = piProvider.getDigitOfPi(digitPosition);
        piQuiz = new PiQuiz(digitPosition, digitOfPi);
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
        String digitOfPi = String.valueOf(piQuiz.getDigitOfPi());
        return piQuiz.getAnswer().equals(digitOfPi);
    }
}
