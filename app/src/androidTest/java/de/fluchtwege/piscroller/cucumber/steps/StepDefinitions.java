package de.fluchtwege.piscroller.cucumber.steps;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.fluchtwege.piscroller.R;
import de.fluchtwege.piscroller.ui.PiScrollerActivity;
import de.fluchtwege.piscroller.espresso.assertions.CheckDigitColor;
import de.fluchtwege.piscroller.espresso.fixtures.EnterAnswer;
import de.fluchtwege.piscroller.espresso.fixtures.OpenQuiz;

@SuppressWarnings("JUnitTestCaseWithNoTests")
public class StepDefinitions extends ActivityInstrumentationTestCase2<PiScrollerActivity> {

	private Activity mActivity;

	public StepDefinitions() {
		super(PiScrollerActivity.class);
	}

	public StepDefinitions(PiScrollerActivity activity) {
		super(PiScrollerActivity.class);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		mActivity = getActivity();
		assertNotNull(mActivity);
	}

	@After
	public void tearDown() throws Exception {
		getActivity().finish();
		super.tearDown();
	}

	@Given("^I have a PiScroller$")
	public void I_have_a_PiScrollerActivity() {
		assertNotNull(getActivity());
	}

	@When("^I open the Quiz$")
	public void I_open_the_quiz() { new OpenQuiz().perform(); }

	@When("^I input answer (\\S+)$")
	public void I_input_answer(final String answer) {
		new EnterAnswer(answer).perform();
	}

	@Then("^I should (true|false) text color is green$")
	public void I_should_see_textcolor_is_green(final boolean isTextColorGreen) {
		new CheckDigitColor(R.color.green, isTextColorGreen).performAssertion();
	}
}
