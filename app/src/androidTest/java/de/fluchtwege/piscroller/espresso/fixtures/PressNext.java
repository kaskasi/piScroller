package de.fluchtwege.piscroller.espresso.fixtures;

import android.support.test.espresso.ViewInteraction;

import de.fluchtwege.piscroller.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class PressNext implements Fixture {

	@Override
	public void perform() {
		ViewInteraction appCompatTextView = onView(
				allOf(withId(R.id.quiz_next), withText("Next"), isDisplayed()));
		appCompatTextView.perform(click());
	}
}
