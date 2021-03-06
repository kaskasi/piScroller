package de.fluchtwege.piscroller.espresso.assertions;

import android.support.test.espresso.ViewInteraction;

import de.fluchtwege.piscroller.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class CheckButtonText implements Assertion {

	private String text;

	public CheckButtonText(String text) {
		this.text = text;
	}

	@Override
	public void performAssertion() {
		ViewInteraction textView = onView(
				allOf(withId(R.id.quiz_next),
						isDisplayed()));
		textView.check(matches(withText(text)));

	}
}
