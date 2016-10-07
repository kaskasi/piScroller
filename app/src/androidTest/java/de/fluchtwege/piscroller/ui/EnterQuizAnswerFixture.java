package de.fluchtwege.piscroller.ui;

import android.support.test.espresso.ViewInteraction;

import de.fluchtwege.piscroller.Fixture;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


public class EnterQuizAnswerFixture implements Fixture {

	private String answer;

	public EnterQuizAnswerFixture(String answer) {
		this.answer = answer;
	}

	@Override
	public void perform() {
		ViewInteraction appCompatEditText = onView(
				allOf(withClassName(is("android.support.v7.widget.AppCompatEditText")), isDisplayed()));
		appCompatEditText.perform(replaceText(answer), closeSoftKeyboard());

	}
}
