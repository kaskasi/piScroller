package de.fluchtwege.piscroller.espresso.matchers;

import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class Matchers {

	public static Matcher<View> withTextColor(@ColorRes final int color) {

		return new BoundedMatcher<View, EditText>(EditText.class) {
			@Override
			public boolean matchesSafely(EditText editText) {
				@ColorInt int colorInt = editText.getResources().getColor(color);
				return colorInt == editText.getCurrentTextColor();
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("with text color: ");
			}
		};
	}

	public static Matcher<View> withBackgroundColor(@ColorRes final int color) {

		return new BoundedMatcher<View, TextView>(TextView.class) {
			@Override
			public boolean matchesSafely(TextView textView) {
				@ColorInt int colorInt = textView.getResources().getColor(color);
				ColorDrawable cd = (ColorDrawable) textView.getBackground();
				int colorCode = cd.getColor();
				return colorCode == colorInt;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("is Enabled: ");
			}
		};
	}

	public static Matcher<View> isEnabled(final boolean isEnabled) {

		return new BoundedMatcher<View, TextView>(TextView.class) {
			@Override
			public boolean matchesSafely(TextView textView) {
				boolean isClickable = textView.isClickable();
				return isEnabled == isClickable;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("is Enabled: ");
			}
		};
	}

	public static Matcher<View> childAtPosition(
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
