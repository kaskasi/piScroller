package de.fluchtwege.piscroller.ui.assertions;

import android.support.annotation.ColorRes;
import android.support.test.espresso.ViewInteraction;

import de.fluchtwege.piscroller.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static de.fluchtwege.piscroller.ui.matchers.Matchers.isEnabled;
import static de.fluchtwege.piscroller.ui.matchers.Matchers.withBackgroundColor;
import static org.hamcrest.Matchers.allOf;

public class CheckBackgroundColor implements Assertion {

    private @ColorRes int color;

    public CheckBackgroundColor(@ColorRes int color) {
        this.color = color;
    }

    @Override
    public void performAssertion() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.quiz_next), withText("Next"), isDisplayed()));
        appCompatTextView.check(matches(withBackgroundColor(color)));
    }
}
