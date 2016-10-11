package de.fluchtwege.piscroller;

import org.junit.Test;

import java.util.Locale;

import de.fluchtwege.piscroller.viewmodel.PiDigitViewModel;

import static junit.framework.Assert.assertFalse;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PiDigitViewModelTest {

	@Test
	public void when_position_is_zero_then_pidigit_is_start_of_pi_with_decimal_separator() {
		Locale.setDefault(Locale.GERMANY);
		int position = 0;
		PiDigitViewModel viewModel = new PiDigitViewModel(position);

		String expected = viewModel.getPiDigit();
		assertThat(expected, is("3,"));
	}

	@Test
	public void when_position_is_greater_zero_then_viewmodel_returns_digit_of_pi_as_text() {
		PiDigitViewModel viewModel = new PiDigitViewModel(2);

		assertThat(viewModel.getPiDigit(), is("4"));
	}

	@Test
	public void when_position_is_zero_then_viewmodel_returns_empty() {
		PiDigitViewModel viewModel = new PiDigitViewModel(0);

		assertThat(viewModel.getPosition(), is(""));
	}

	@Test
	public void when_position_is_greater_zero_then_viewmodel_returns_position_of_pi_as_text() {
		PiDigitViewModel viewModel = new PiDigitViewModel(1);

		assertThat(viewModel.getPosition(), is("1"));
	}

	@Test
	public void when_position_is_zero_then_background_is_beige() {
		PiDigitViewModel viewModel = new PiDigitViewModel(0);
		assertTrue(viewModel.isBackgroundBeige());
	}

	@Test
	public void when_position_is_even_then_background_is_beige() {
		PiDigitViewModel viewModel = new PiDigitViewModel(2);
		assertTrue(viewModel.isBackgroundBeige());
	}

	@Test
	public void when_position_is_uneven_then_background_is_not_beige() {
		PiDigitViewModel viewModel = new PiDigitViewModel(1);
		assertFalse(viewModel.isBackgroundBeige());
	}
}
