package de.fluchtwege.piscroller;

import org.junit.Test;

import de.fluchtwege.piscroller.model.PiProvider;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PiProviderTest {


	@Test
	public void given_a_position_n_then_piprovider_provides_nth_digit_of_pi() {
		PiProvider piProvider = new PiProvider();

		assertThat(piProvider.getDigitOfPi(1), is('1'));
		assertThat(piProvider.getDigitOfPi(2), is('4'));
		assertThat(piProvider.getDigitOfPi(3), is('1'));
		assertThat(piProvider.getDigitOfPi(4), is('5'));
		assertThat(piProvider.getDigitOfPi(5), is('9'));
	}

	@Test
	public void given_pi_provider_then_piprovider_provides_pi_to_1000_digits_after_decimal_symbol() {
		PiProvider piProvider = new PiProvider();

		assertThat(piProvider.getNumberOfDigitsOfPi(), is(1001));
	}
}