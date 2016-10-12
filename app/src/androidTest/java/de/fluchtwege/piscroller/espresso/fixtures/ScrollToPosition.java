package de.fluchtwege.piscroller.espresso.fixtures;

import de.fluchtwege.piscroller.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class ScrollToPosition implements Fixture {
	private int position;

	public ScrollToPosition(int position) {
		this.position = position;
	}

	@Override
	public void perform() {
		onView(withId(R.id.piScroller))
				.perform(scrollToPosition(position));
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
