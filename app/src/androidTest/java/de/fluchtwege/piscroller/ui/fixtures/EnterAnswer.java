package de.fluchtwege.piscroller.ui.fixtures;

import android.support.test.espresso.ViewInteraction;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


public class EnterAnswer implements Fixture {

	private String answer;

	public EnterAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public void perform() {
		ViewInteraction appCompatEditText = onView(
				allOf(withClassName(is("android.support.v7.widget.AppCompatEditText")), isDisplayed()));
		appCompatEditText.perform(replaceText(answer), closeSoftKeyboard());

	}
}
