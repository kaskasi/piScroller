package de.fluchtwege.piscroller.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import de.fluchtwege.piscroller.model.PiProvider;
import de.fluchtwege.piscroller.model.PiQuiz;

public class PiQuizViewModel extends BaseObservable {

	private final PiProvider piProvider = new PiProvider();
	private final PiQuiz piQuiz;

	public PiQuizViewModel(PiQuiz piQuiz) {
		this.piQuiz = piQuiz;
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
	public boolean isQustionHowPiStarts() {
		return piQuiz.getPosition() == 0;
	}

	@Bindable
	public boolean isButtonTextNext() {
		return isNextPositionAvailable() && (!isAnswerEntered() || isAnswerCorrect());
	}

	@Bindable
	public boolean isButtonEnabled() {
		return isAnswerEntered();
	}

	@Bindable
	public boolean isAnswerColorGreen() {
		return isAnswerCorrect();
	}

	@Bindable
	public boolean isPossibleToEnterAnswer() {
		return !isAnswerEntered();
	}

	@Bindable
	public String getDigitPosition() {
		return String.valueOf(piQuiz.getPosition());
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

	private void setupNextQuiz(int nextPosition) {
		piQuiz.setPosition(nextPosition);
		piQuiz.setAnswer("");
	}

	public PiQuiz getPiQuiz() {
		return piQuiz;
	}

	private boolean isAnswerEntered() {
		return !"".equals(piQuiz.getAnswer());
	}

	private boolean isAnswerCorrect() {
		if (!isAnswerEntered()) {
			return false;
		}
		int position = piQuiz.getPosition();
		String digitOfPi = String.valueOf(piProvider.getDigitOfPi(position));
		return piQuiz.getAnswer().equals(digitOfPi);
	}

	private boolean isNextPositionAvailable() {
		return piQuiz.getPosition() < piProvider.getNumberOfDigitsOfPi();
	}
}
