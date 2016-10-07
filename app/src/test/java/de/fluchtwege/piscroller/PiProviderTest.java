package de.fluchtwege.piscroller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import de.fluchtwege.piscroller.model.PiProvider;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PiProviderTest {

    @InjectMocks
    PiProvider piProvider;

    @Test
    public void PiProvider_provides_nth_digit_of_pi() {
        String tenDigitsOfPi = "";
        for (int i = 0; i < 10; i++) {
            tenDigitsOfPi = tenDigitsOfPi + piProvider.getDigitOfPi(i);
        }

        assertThat(tenDigitsOfPi, is("3141592653"));
    }

    @Test
    public void PiProvider_provides_1000_digits_of_pi() {
        assertThat(piProvider.getNumberOfDigitsOfPi(), is(1000));
    }
}