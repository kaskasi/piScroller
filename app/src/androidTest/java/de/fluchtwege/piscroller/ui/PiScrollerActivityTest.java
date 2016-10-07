package de.fluchtwege.piscroller.ui;


import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.internal.util.Checks;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.fluchtwege.piscroller.R;

import static android.support.test.espresso.Espresso.getIdlingResources;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PiScrollerActivityTest {

	@Rule
	public ActivityTestRule<PiScrollerActivity> mActivityTestRule = new ActivityTestRule<>(PiScrollerActivity.class);

	@Test
	public void piScrollerActivityTest() {

		new OpenQuizFixture().perform();
		new EnterQuizAnswerFixture("3").perform();


		ViewInteraction appCompatTextView = onView(
				allOf(withId(R.id.quiz_next), withText("Next"), isDisplayed()));
		appCompatTextView.perform(click());

		pressBack();

		new OpenQuizFixture().perform();
		new EnterQuizAnswerFixture("1").perform();

		ViewInteraction textView = onView(
				allOf(withId(R.id.quiz_next), withText("Next"),
						childAtPosition(
								childAtPosition(
										withId(android.R.id.content),
										0),
								2),
						isDisplayed()));

		ViewInteraction appCompatEditText2 = onView(
				allOf(withClassName(is("android.support.v7.widget.AppCompatEditText")), isDisplayed()));

		appCompatEditText2.check(matches(withTextColor(Color.RED)));

	}

	public static Matcher<View> withTextColor(final int color) {
		Checks.checkNotNull(color);
		return new BoundedMatcher<View, EditText>(EditText.class) {
			@Override
			public boolean matchesSafely(EditText warning) {
				return color == warning.getCurrentTextColor();
			}
			@Override
			public void describeTo(Description description) {
				description.appendText("with text color: ");
			}
		};
	}

	private static Matcher<View> childAtPosition(
			final Matcher<View> parentMatcher, final int position) {

		return new TypeSafeMatcher<View>() {
			@Override
			public void describeTo(Description description) {
				description.appendText("Child at position " + position + " in parent ");
				parentMatcher.describeTo(description);
			}

			@Override
			public boolean matchesSafely(View view) {
				ViewParent parent = view.getParent();
				return parent instanceof ViewGroup && parentMatcher.matches(parent)
						&& view.equals(((ViewGroup) parent).getChildAt(position));
			}
		};
	}
}
