package de.fluchtwege.piscroller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.fluchtwege.piscroller.model.PiQuiz;
import de.fluchtwege.piscroller.viewmodel.PiQuizViewModel;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
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
    public void ViewModel_returns_true_if_answer_is_entered() {
        doReturn("3").when(piQuiz).getAnswer();
        assertTrue(piQuizViewModel.isAnswerEntered());

        doReturn("").when(piQuiz).getAnswer();
        assertFalse(piQuizViewModel.isAnswerEntered());
    }

    @Test
    public void ViewModel_returns_if_answer_is_correct() {
        doReturn('3').when(piQuiz).getDigitOfPi();

        doReturn("").when(piQuiz).getAnswer();
        assertFalse(piQuizViewModel.isAnswerCorrect());

        doReturn("4").when(piQuiz).getAnswer();
        assertFalse(piQuizViewModel.isAnswerCorrect());

        doReturn("3").when(piQuiz).getAnswer();
        assertTrue(piQuizViewModel.isAnswerCorrect());
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
        PiQuizViewModel piQuizViewModel = spy(new PiQuizViewModel(piQuiz));

        piQuizViewModel.onNext();

        verify(piQuizViewModel).notifyChange();
    }

    @Test
    public void ViewModel_creates_new_quiz_on_Next() {
        int initialPosition = 12;
        doReturn(initialPosition).when(piQuiz).getPosition();

        piQuizViewModel.onNext();

        PiQuiz newQuiz = piQuizViewModel.getPiQuiz();
        assertThat(newQuiz.getPosition(), not(initialPosition));
    }
}
