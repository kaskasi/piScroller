package de.fluchtwege.piscroller;

import org.junit.Test;

import de.fluchtwege.piscroller.model.PiQuiz;
import de.fluchtwege.piscroller.viewmodel.PiQuizViewModel;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class PiQuizViewModelTest {

	@Test
	public void when_answer_is_entered_then_viewmodel_notifies_view() {
		PiQuiz quiz = new PiQuiz(0);
		PiQuizViewModel viewModel = spy(new PiQuizViewModel(quiz));

		viewModel.setAnswer("3");

		verify(viewModel).notifyChange();
	}

	@Test
	public void when_answer_entered_then_viewmodel_returns_answer() {
		String answer = "3";
		PiQuiz quiz = new PiQuiz(0);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		viewModel.setAnswer(answer);

		assertThat(viewModel.getAnswer().toString(), is(answer));
	}

	@Test
	public void then_button_is_not_enabled() {
		PiQuiz quiz = new PiQuiz(0);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		assertFalse(viewModel.isButtonEnabled());
	}

	@Test
	public void when_answer_entered_then_button_is_enabled() {
		PiQuiz quiz = new PiQuiz(0);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		viewModel.setAnswer("3");

		assertTrue(viewModel.isButtonEnabled());
	}

	@Test
	public void when_correct_answer_entered_then_answer_color_is_green() {
		PiQuiz quiz = new PiQuiz(0);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		viewModel.setAnswer("3");

		assertTrue(viewModel.isAnswerColorGreen());
	}

	@Test
	public void when_wrong_answer_entered_then_answer_color_is_not_green() {
		PiQuiz quiz = new PiQuiz(0);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		viewModel.setAnswer("4");

		assertFalse(viewModel.isAnswerColorGreen());
	}

	@Test
	public void when_correct_answer_entered_then_button_text_is_next() {
		PiQuiz quiz = new PiQuiz(0);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		viewModel.setAnswer("3");

		assertTrue(viewModel.isButtonTextNext());
	}

	@Test
	public void when_wrong_answer_entered_then_button_text_is_restart() {
		PiQuiz quiz = new PiQuiz(0);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		viewModel.setAnswer("4");

		assertFalse(viewModel.isButtonTextNext());
	}

	@Test
	public void when_position_is_thousand_then_button_text_is_restart() {
		int initialPosition = 1001;
		PiQuiz quiz = new PiQuiz(initialPosition);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		assertFalse(viewModel.isButtonTextNext());
	}

	@Test
	public void then_it_is_possible_to_enter_answer() {
		PiQuiz quiz = new PiQuiz(0);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		assertTrue(viewModel.isPossibleToEnterAnswer());
	}

	@Test
	public void when_answer_is_entered_then_it_is_not_possible_to_enter_answer() {
		PiQuiz quiz = new PiQuiz(0);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		viewModel.setAnswer("3");

		assertFalse(viewModel.isPossibleToEnterAnswer());
	}

	@Test
	public void when_answer_is_entered_when_next_is_pressed_then_it_is_possible_to_enter_answer() {
		PiQuiz quiz = new PiQuiz(0);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		viewModel.setAnswer("3");
		viewModel.onNext();

		assertTrue(viewModel.isPossibleToEnterAnswer());
	}

	@Test
	public void when_next_is_pressed_then_viewmodel_notifies_view_() {
		PiQuiz quiz = new PiQuiz(0);
		PiQuizViewModel piQuizViewModel = spy(new PiQuizViewModel(quiz));

		piQuizViewModel.onNext();

		verify(piQuizViewModel).notifyChange();
	}

	@Test
	public void when_correct_answer_is_entered_when_next_is_pressed_then_quiz_position_is_incremented() {
		int initialPosition = 1;
		PiQuiz quiz = new PiQuiz(initialPosition);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		viewModel.setAnswer("1");
		viewModel.onNext();

		final PiQuiz nextQuiz = viewModel.getPiQuiz();
		assertThat(nextQuiz.getPosition(), is(initialPosition + 1));
	}

	@Test
	public void when_wrong_answer_is_entered_when_restart_is_pressed_then_quiz_position_is_zero() {
		int initialPosition = 1;
		PiQuiz quiz = new PiQuiz(initialPosition);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		viewModel.setAnswer("2");
		viewModel.onNext();

		final PiQuiz nextQuiz = viewModel.getPiQuiz();
		assertThat(nextQuiz.getPosition(), is(0));
	}

	@Test
	public void when_position_is_thousand_when_correct_answer_is_entered_when_restart_is_pressed_then_quiz_position_is_zero() {
		int initialPosition = 1001;
		PiQuiz quiz = new PiQuiz(initialPosition);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		viewModel.setAnswer("9");
		viewModel.onNext();

		final PiQuiz nextQuiz = viewModel.getPiQuiz();
		assertThat(nextQuiz.getPosition(), is(0));
	}

	@Test
	public void when_position_is_zero_then_question_is_how_pi_starts() {
		int initialPosition = 0;
		PiQuiz quiz = new PiQuiz(initialPosition);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		assertTrue(viewModel.isQustionHowPiStarts());
	}

	@Test
	public void then_viewmodel_returns_digit_position_as_text() {
		int initialPosition = 1;
		PiQuiz quiz = new PiQuiz(initialPosition);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		assertThat(viewModel.getDigitPosition(), is("" + initialPosition));
	}

	@Test
	public void then_viewmodel_returns_pi_quiz() {
		int initialPosition = 1;
		PiQuiz quiz = new PiQuiz(initialPosition);
		PiQuizViewModel viewModel = new PiQuizViewModel(quiz);

		assertThat(viewModel.getPiQuiz().getPosition(), is(initialPosition));
	}
}
