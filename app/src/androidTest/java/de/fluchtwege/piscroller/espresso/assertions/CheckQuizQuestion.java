package de.fluchtwege.piscroller.espresso.assertions;

import android.support.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import de.fluchtwege.piscroller.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class CheckQuizQuestion implements Assertion {

	private String question;

	public CheckQuizQuestion(String question) {
		this.question = question;
	}

	@Override
	public void performAssertion() {
		ViewInteraction textView = onView(
				Matchers.allOf(withId(R.id.quiz_question),
						de.fluchtwege.piscroller.espresso.matchers.Matchers.childAtPosition(
								de.fluchtwege.piscroller.espresso.matchers.Matchers.childAtPosition(
										withId(android.R.id.content),
										0),
								0),
						isDisplayed()));
		textView.check(matches(withText(question)));

	}
}
