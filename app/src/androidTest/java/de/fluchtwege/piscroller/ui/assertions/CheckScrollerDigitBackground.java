package de.fluchtwege.piscroller.ui.assertions;

import android.support.annotation.ColorRes;
import android.support.test.espresso.ViewInteraction;
import android.view.View;

import org.hamcrest.core.IsInstanceOf;

import de.fluchtwege.piscroller.R;
import de.fluchtwege.piscroller.model.PiProvider;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static de.fluchtwege.piscroller.ui.matchers.Matchers.childAtPosition;
import static de.fluchtwege.piscroller.ui.matchers.Matchers.withBackgroundColor;
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
				allOf(withId(R.id.digit), withText(digitOfPi),
						childAtPosition(
								childAtPosition(
										IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
										0),
								1),
						isDisplayed()));
		textView.check(matches(withBackgroundColor(color)));
	}
}
