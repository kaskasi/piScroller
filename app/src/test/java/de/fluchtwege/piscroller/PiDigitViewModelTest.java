package de.fluchtwege.piscroller;

import org.junit.After;
import org.junit.Test;

import de.fluchtwege.piscroller.viewmodel.PiDigitViewModel;

import static junit.framework.Assert.assertFalse;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PiDigitViewModelTest {

	PiDigitViewModel piDigitViewModel;

	@After
	public void tearDown() {
		piDigitViewModel = null;
	}

	@Test
	public void ViewModel_returns_digit() {
		piDigitViewModel = new PiDigitViewModel(0, "3");

		assertThat(piDigitViewModel.getPiDigit(), is("3"));
	}

	@Test
	public void ViewModel_returns_position() {
		piDigitViewModel = new PiDigitViewModel(1, "4");

		assertThat(piDigitViewModel.getPosition(), is("1"));
	}

	@Test
	public void ViewModel_returns_empty_text_for_position_zero() {
		piDigitViewModel = new PiDigitViewModel(0, "3");

		assertThat(piDigitViewModel.getPosition(), is(""));
	}

	@Test
	public void ViewModel_retuns_even_for_position_zero() {
		piDigitViewModel = new PiDigitViewModel(0, "3");
		assertTrue(piDigitViewModel.isPositionEven());
	}

	@Test
	public void ViewModel_returns_if_position_is_even() {
		piDigitViewModel = new PiDigitViewModel(1, "1");
		assertFalse(piDigitViewModel.isPositionEven());

		piDigitViewModel = new PiDigitViewModel(2, "4");
		assertTrue(piDigitViewModel.isPositionEven());

	}

}
