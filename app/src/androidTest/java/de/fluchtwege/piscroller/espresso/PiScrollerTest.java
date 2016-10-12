package de.fluchtwege.piscroller.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.fluchtwege.piscroller.R;
import de.fluchtwege.piscroller.espresso.assertions.CheckScrollerDigitBackground;
import de.fluchtwege.piscroller.espresso.fixtures.ScrollToPosition;
import de.fluchtwege.piscroller.ui.PiScrollerActivity;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class PiScrollerTest {

	@Rule
	public ActivityTestRule<PiScrollerActivity> mActivityTestRule = new ActivityTestRule<>(PiScrollerActivity.class);

	@Test
	public void Given_in_scroller_when_scroll_to_1000_digits() {
		int start = 0;
		ScrollToPosition scrollToPosition = new ScrollToPosition(start);
		for (int i = start; i <= 1000; i++) {
			scrollToPosition.setPosition(i);
			scrollToPosition.perform();
		}
	}

	@Test
	public void Given_in_scroller_when_scrolled_to_beginning_then_digits_with_even_position_have_beige_background() {
		new ScrollToPosition(0).perform();

		new CheckScrollerDigitBackground(R.color.beige, 2);
	}

	@Test
	public void Given_in_scroller_when_scrolled_to_beginning_then_digits_with_uneven_position_have_white_background() {
		new ScrollToPosition(0).perform();

		new CheckScrollerDigitBackground(R.color.white, 1);
	}

}