package de.fluchtwege.piscroller.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.fluchtwege.piscroller.ui.fixtures.ScrollToPosition;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class PiScrollerTest {

    @Rule
    public ActivityTestRule<PiScrollerActivity> mActivityTestRule = new ActivityTestRule<>(PiScrollerActivity.class);

    @Test
    public void Scroller_can_scroll_to_1000_digits_of_pi() {
        int start = 0;
        ScrollToPosition scrollToPosition = new ScrollToPosition(start);
        for (int i = start; i <= 1000; i++) {
            scrollToPosition.setPosition(i);
            scrollToPosition.perform();
        }
    }
}