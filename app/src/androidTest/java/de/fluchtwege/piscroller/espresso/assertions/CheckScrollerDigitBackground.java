package de.fluchtwege.piscroller.espresso.assertions;

import android.support.annotation.ColorRes;
import android.support.test.espresso.ViewInteraction;
import android.view.View;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsInstanceOf;

import de.fluchtwege.piscroller.R;
import de.fluchtwege.piscroller.model.PiProvider;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class CheckScrollerDigitBackground implements Assertion {

	@ColorRes
	private int color;

	private int position;

	public CheckScrollerDigitBackground(@ColorRes int color, int position) {
		this.color = color;
		this.position = position;
	}

	@Override
	public void performAssertion() {
		String digitOfPi = String.valueOf(new PiProvider().getDigitOfPi(position));

		ViewInteraction textView = onView(
				Matchers.allOf(withId(R.id.digit), withText(digitOfPi),
						de.fluchtwege.piscroller.espresso.matchers.Matchers.childAtPosition(
								de.fluchtwege.piscroller.espresso.matchers.Matchers.childAtPosition(
										IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
										0),
								1),
						isDisplayed()));
		textView.check(matches(de.fluchtwege.piscroller.espresso.matchers.Matchers.withBackgroundColor(color)));
	}
}
