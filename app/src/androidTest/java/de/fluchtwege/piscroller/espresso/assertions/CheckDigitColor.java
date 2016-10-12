package de.fluchtwege.piscroller.espresso.assertions;

import android.support.annotation.ColorRes;
import android.support.test.espresso.ViewInteraction;

import de.fluchtwege.piscroller.espresso.matchers.Matchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class CheckDigitColor implements Assertion {

	@ColorRes
	private int color;
	private boolean isTextColor;

	public CheckDigitColor(@ColorRes int color, boolean isTextColor) {
		this.color = color;
		this.isTextColor = isTextColor;
	}

	@Override
	public void performAssertion() {
		ViewInteraction appCompatEditText = onView(
				allOf(withClassName(is("android.support.v7.widget.AppCompatEditText")), isDisplayed()));
		if (isTextColor) {
			appCompatEditText.check(matches(Matchers.withTextColor(color)));
		} else {
			appCompatEditText.check(matches(org.hamcrest.Matchers.not(Matchers.withTextColor(color))));
		}
	}
}
