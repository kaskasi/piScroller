package de.fluchtwege.piscroller.ui;

import android.support.test.espresso.ViewInteraction;

import de.fluchtwege.piscroller.Fixture;
import de.fluchtwege.piscroller.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;


public class OpenQuizFixture implements Fixture {

	@Override
	public void perform() {
		ViewInteraction actionMenuItemView = onView(
				allOf(withId(R.id.menu_quiz), withContentDescription("Quiz"), isDisplayed()));
		actionMenuItemView.perform(click());
	}
}
