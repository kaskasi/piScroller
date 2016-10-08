package de.fluchtwege.piscroller.ui.assertions;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import de.fluchtwege.piscroller.R;
import de.fluchtwege.piscroller.ui.matchers.Matchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static de.fluchtwege.piscroller.ui.matchers.Matchers.withTextColor;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class CheckDigitColor implements Assertion {

    @ColorRes
    private int color;

    public CheckDigitColor(@ColorRes int color) {
        this.color = color;
    }

    @Override
    public void performAssertion() {
        ViewInteraction appCompatEditText = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatEditText")), isDisplayed()));
        appCompatEditText.check(matches(withTextColor(color)));
    }
}
