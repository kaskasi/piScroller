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
	public ActivityTestRule<PiScrollerActivity> mActivityTestRule = new ActivityTestRule<>(PiScrollerActivity.class);

	@Test
	public void In_quiz_when_correct_answer_entered_then_answer_has_green_textcolor() {
		new OpenQuiz().perform();

		new EnterAnswer("3").perform();

		new CheckDigitColor(R.color.correct_answer).performAssertion();
	}

	@Test
	public void In_quiz_when_wrong_answer_entered_then_answer_has_red_textcolor() {
		new OpenQuiz().perform();

		new EnterAnswer("4").perform();

		new CheckDigitColor(R.color.wrong_answer).performAssertion();
	}

	@Test
	public void In_quiz_then_button_text_is_next() {
		new OpenQuiz().perform();

		new CheckButtonText("Next").performAssertion();
	}

	@Test
	@Ignore
	public void In_quiz_when_answer_was_entered_thousand_times_then_button_text_is_restart_if_has_no_next_position() {
		new OpenQuiz().perform();

		PiProvider piProvider = new PiProvider();
		for (int i = 0; i < 1000; i++) {
			new EnterAnswer("" + piProvider.getDigitOfPi(i)).perform();
			new PressNext().perform();
		}

		new CheckButtonText("Restart").performAssertion();
	}

	@Test
	public void In_quiz_when_answer_entered_then_next_button_color_is_disabled() {
		new OpenQuiz().perform();

		new CheckQuizNextBackgroundColor(R.color.disabled).performAssertion();
	}

	@Test
	public void In_quiz_when_answer_entered_then_next_button_is_blue() {
		new OpenQuiz().perform();

		new EnterAnswer("4").perform();

		new CheckQuizNextBackgroundColor(R.color.blue).performAssertion();
	}

	@Test
	public void In_quiz_at_first_question_then_question_is_how_does_pi_start() {
		new OpenQuiz().perform();

		new CheckQuizQuestion("How does π start ?").performAssertion();
	}

	@Test
	public void In_quiz_when_answer_entered_and_when_next_pressed_then_question_is_the_nth_digit_of_pi() {
		new OpenQuiz().perform();

		new EnterAnswer("3").perform();
		new PressNext().perform();

		new CheckQuizQuestion("The 1. digt of π ?").performAssertion();
	}

}
