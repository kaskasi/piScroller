package de.fluchtwege.piscroller.ui;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.fluchtwege.piscroller.R;
import de.fluchtwege.piscroller.ui.assertions.CheckBackgroundColor;
import de.fluchtwege.piscroller.ui.assertions.CheckButtonEnabled;
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
    public void Correct_answer_has_green_textcolor() {
        new OpenQuiz().perform();

        new EnterAnswer("3").perform();

        new CheckDigitColor(R.color.correct_answer).performAssertion();
    }

    @Test
    public void Wrong_answer_has_red_textcolor() {
        new OpenQuiz().perform();

        new EnterAnswer("4").perform();

        new CheckDigitColor(R.color.wrong_answer).performAssertion();
    }

    @Test
    public void Next_button_is_not_enabled_without_input() {
        new OpenQuiz().perform();

        new CheckButtonEnabled(false).performAssertion();
    }

    @Test
    public void Next_button_is_enabled_after_input() {
        new OpenQuiz().perform();

        new EnterAnswer("4").perform();

        new CheckButtonEnabled(true).performAssertion();
    }

    @Test
    public void Next_button_is_greyed_out_before_input() {
        new OpenQuiz().perform();

        new CheckBackgroundColor(R.color.disabled).performAssertion();
    }

    @Test
    public void Next_button_is_not_greyed_out_after_input() {
        new OpenQuiz().perform();

        new EnterAnswer("4").perform();

        new CheckBackgroundColor(R.color.colorPrimary).performAssertion();
    }

    @Test
    public void Quiz_begins_with_question_how_pi_starts() {
        new OpenQuiz().perform();

        new CheckQuizQuestion("How does π start ?").performAssertion();
    }

    @Test
    public void Quiz_continues_with_question_after_digit_of_pi() {
        new OpenQuiz().perform();

        new EnterAnswer("3").perform();

        new PressNext().perform();

        new CheckQuizQuestion("What is the 1. digt of π").performAssertion();
    }

}
