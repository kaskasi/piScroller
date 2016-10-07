package de.fluchtwege.piscroller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.fluchtwege.piscroller.model.PiProvider;
import de.fluchtwege.piscroller.model.PiQuiz;
import de.fluchtwege.piscroller.viewmodel.PiQuizViewModel;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PiQuizViewModelTest {

	@Mock
	PiQuiz piQuiz;

	@InjectMocks
	PiQuizViewModel piQuizViewModel;

	@Test
	public void ViewModel_returns_if_answer_is_entered() {
		doReturn("3").when(piQuiz).getAnswer();
		assertTrue(piQuizViewModel.isAnswerEntered());

		doReturn("").when(piQuiz).getAnswer();
		assertFalse(piQuizViewModel.isAnswerEntered());
	}

	@Test
	public void ViewModel_returns_if_answer_is_correct() {
		doReturn("").when(piQuiz).getAnswer();
		assertFalse(piQuizViewModel.isAnswerCorrect());

		doReturn("4").when(piQuiz).getAnswer();
		assertFalse(piQuizViewModel.isAnswerCorrect());

		doReturn("3").when(piQuiz).getAnswer();
		assertTrue(piQuizViewModel.isAnswerCorrect());
	}

	@Test
	public void ViewModel_returns_next_position_is_available_when_provider_has_more_digits() {
		doReturn(0).when(piQuiz).getPosition();
		assertTrue(piQuizViewModel.isNextPositionAvailable());

		doReturn(999).when(piQuiz).getPosition();
		assertTrue(piQuizViewModel.isNextPositionAvailable());

		doReturn(1000).when(piQuiz).getPosition();
		assertFalse(piQuizViewModel.isNextPositionAvailable());
	}

	@Test
	public void ViewModel_notifies_view_when_answer_is_entered() {
		String answer = "3";
		PiQuizViewModel piQuizViewModel = spy(new PiQuizViewModel(piQuiz));

		piQuizViewModel.setAnswer(answer);

		verify(piQuizViewModel).notifyChange();
	}

	@Test
	public void ViewModel_returns_answer() {
		String answer = "3";

		doReturn(answer).when(piQuiz).getAnswer();

		assertThat(piQuizViewModel.getAnswer().toString(), is(answer));
	}

	@Test
	public void ViewModel_notifies_view_when_new_quiz_is_created() {
		int initialPosition = 12;
		CharSequence rightAnswer = "1";
		doReturn(initialPosition).when(piQuiz).getPosition();
		doReturn(rightAnswer).when(piQuiz).getAnswer();

		PiQuizViewModel piQuizViewModel = spy(new PiQuizViewModel(piQuiz));
		piQuizViewModel.onNext();

		verify(piQuizViewModel).notifyChange();
	}

	@Test
	public void ViewModel_increments_quiz_position_on_next() {
		int initialPosition = 1;
		CharSequence rightAnswer = "1";
		doReturn(initialPosition).when(piQuiz).getPosition();
		doReturn(rightAnswer).when(piQuiz).getAnswer();

		PiQuizViewModel piQuizViewModel = spy(new PiQuizViewModel(piQuiz));
		piQuizViewModel.onNext();

		verify(piQuizViewModel).setupNextQuiz(initialPosition + 1);
	}

	@Test
	public void ViewModel_resets_quiz_on_next_when_answer_is_wrong() {
		int initialPosition = 1;
		CharSequence wrongAnswer = "4";
		doReturn(initialPosition).when(piQuiz).getPosition();
		doReturn(wrongAnswer).when(piQuiz).getAnswer();

		PiQuizViewModel piQuizViewModel = spy(new PiQuizViewModel(piQuiz));
		piQuizViewModel.onNext();

		verify(piQuizViewModel).setupNextQuiz(0);
	}

	@Test
	public void ViewModel_resets_quiz_on_next_if_next_position_is_not_available() {
		int numberOfDigits = 1000;
		CharSequence rightAnswer = "1";
		doReturn(numberOfDigits).when(piQuiz).getPosition();
		doReturn(rightAnswer).when(piQuiz).getAnswer();

		PiQuizViewModel piQuizViewModel = spy(new PiQuizViewModel(piQuiz));
		piQuizViewModel.onNext();

		verify(piQuizViewModel).setupNextQuiz(0);
	}

	@Test
	public void ViewModel_returns_if_quiz_is_at_start() {
		doReturn(0).when(piQuiz).getPosition();
		assertTrue(piQuizViewModel.isAtStart());

		doReturn(1).when(piQuiz).getPosition();
		assertFalse(piQuizViewModel.isAtStart());
	}

	@Test
	public void ViewModel_returns_digit_positions_as_text() {
		doReturn(0).when(piQuiz).getPosition();
		assertThat(piQuizViewModel.getDigitPosition(), is("0"));
	}

	@Test
	public void ViewModel_returns_pi_quiz() {
		doReturn(3).when(piQuiz).getPosition();
		assertThat(piQuizViewModel.getPiQuiz().getPosition(), is(3));
	}
}
