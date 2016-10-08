package de.fluchtwege.piscroller.ui.assertions;

import android.support.test.espresso.ViewInteraction;

import de.fluchtwege.piscroller.R;
import de.fluchtwege.piscroller.ui.matchers.Matchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static de.fluchtwege.piscroller.ui.matchers.Matchers.childAtPosition;
import static org.hamcrest.Matchers.allOf;

public class CheckQuizQuestion implements Assertion {

    private String question;

    public CheckQuizQuestion(String question) {
        this.question = question;
    }

    @Override
    public void performAssertion() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.quiz_question),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText(question)));

    }
}
