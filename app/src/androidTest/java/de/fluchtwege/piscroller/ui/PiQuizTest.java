package de.fluchtwege.piscroller.ui;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.fluchtwege.piscroller.R;
import de.fluchtwege.piscroller.model.PiProvider;
import de.fluchtwege.piscroller.ui.assertions.CheckButtonEnabled;
import de.fluchtwege.piscroller.ui.assertions.CheckQuizNextBackgroundColor;
import de.fluchtwege.piscroller.ui.assertions.CheckButtonText;
import de.fluchtwege.piscroller.ui.assertions.CheckDigitColor;
import de.fluchtwege.piscroller.ui.assertions.CheckQuizQuestion;
import de.fluchtwege.piscroller.ui.fixtures.EnterAnswer;
import de.fluchtwege.piscroller.ui.fixtures.OpenQuiz;
import de.fluchtwege.piscroller.ui.fixtures.PressNext;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PiQuizTest {

	@Rule
	public ActivityTestRule<PiScrollerActivity> testRule = new ActivityTestRule<>(PiScrollerActivity.class);

	@Test
	public void Given_open_quiz_when_correct_answer_entered_then_answer_has_green_textcolor() {
		new OpenQuiz().perform();

		new EnterAnswer("3").perform();

		new CheckDigitColor(R.color.green).performAssertion();
	}

	@Test
	public void Given_open_quiz_when_wrong_answer_entered_then_answer_has_red_textcolor() {
		new OpenQuiz().perform();

		new EnterAnswer("4").perform();

		new CheckDigitColor(R.color.red).performAssertion();
	}

	@Test
	public void Given_open_quiz_then_button_text_is_next() {
		new OpenQuiz().perform();

		String next = testRule.getActivity().getString(R.string.next);
		new CheckButtonText(next).performAssertion();
	}

	@Test
	public void Given_open_quiz_when_wrong_answer_is_entered_then_button_text_is_restart() {
		new OpenQuiz().perform();

		new EnterAnswer("4").perform();

		String next = testRule.getActivity().getString(R.string.restart);
		new CheckButtonText(next).performAssertion();
	}

	@Test
	@Ignore
	public void Given_open_quiz_when_answer_was_entered_thousand_times_then_button_text_is_restart() {
		new OpenQuiz().perform();

		PiProvider piProvider = new PiProvider();
		for (int i = 0; i < 1001; i++) {
			new EnterAnswer("" + piProvider.getDigitOfPi(i)).perform();
			new PressNext().perform();
		}

		String restart = testRule.getActivity().getString(R.string.restart);
		new CheckButtonText(restart).performAssertion();
	}

	@Test
	public void Given_open_quiz_when_answer_entered_then_next_button_color_is_grey() {
		new OpenQuiz().perform();

		new CheckQuizNextBackgroundColor(R.color.grey).performAssertion();
	}

	@Test
	public void Given_open_quiz_when_answer_entered_then_next_button_is_blue() {
		new OpenQuiz().perform();

		new EnterAnswer("3").perform();

		new CheckQuizNextBackgroundColor(R.color.blue).performAssertion();
	}

	@Test
	public void Given_open_quiz_then_question_is_how_does_pi_start() {
		new OpenQuiz().perform();

		String questionStart = testRule.getActivity().getString(R.string.quiz_start_question);
		new CheckQuizQuestion(questionStart).performAssertion();
	}

	@Test
	public void Given_open_quiz_when_answer_entered_and_when_next_pressed_then_question_is_the_nth_digit_of_pi() {
		new OpenQuiz().perform();

		new EnterAnswer("3").perform();
		new PressNext().perform();

		String question = testRule.getActivity().getString(R.string.quiz_question);
		question = String.format(question, "" + 1);
		new CheckQuizQuestion(question).performAssertion();
	}

	@Test
	public void Given_open_quiz_then_next_button_is_disabled() {
		new OpenQuiz().perform();

		new CheckButtonEnabled(false).performAssertion();
	}

	@Test
	public void Given_open_quiz_when_answer_entered_then_next_button_is_enabled() {
		new OpenQuiz().perform();

		new EnterAnswer("3").perform();

		new CheckButtonEnabled(true).performAssertion();
	}

}
