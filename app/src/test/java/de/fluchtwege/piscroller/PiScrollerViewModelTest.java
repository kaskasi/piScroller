package de.fluchtwege.piscroller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Locale;

import de.fluchtwege.piscroller.model.PiProvider;
import de.fluchtwege.piscroller.viewmodel.PiScrollerViewModel;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class PiScrollerViewModelTest {

    @Mock
    PiProvider piProvider;

    @InjectMocks
    PiScrollerViewModel viewModel;

    @Before
    public void setup() {
        Locale.setDefault(Locale.GERMANY);
    }

    @Test
    public void ViewModel_returns_nth_digit_of_pi() {
        int position = 4;

        doReturn('5').when(piProvider).getDigitOfPi(position);

        String expected = viewModel.getDigitOfPi(position);
        assertThat(expected, is("5"));
    }

    @Test
    public void ViewModel_returns_a_decimal_separator_if_specified_position_is_0() {
        int position = 0;

        doReturn('3').when(piProvider).getDigitOfPi(position);

        String expected = viewModel.getDigitOfPi(position);
        assertThat(expected, is("3,"));
    }

    @Test
    public void ViewModel_returns_number_of_digits_of_pi() {
        int numberOfDigits = 1001;//
        doReturn(numberOfDigits).when(piProvider).getNumberOfDigitsOfPi();

        assertThat(viewModel.getNumberOfDigitsOfPi(), is(numberOfDigits));
    }


}
