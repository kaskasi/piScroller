package de.fluchtwege.piscroller;

import org.junit.Test;

import de.fluchtwege.piscroller.viewmodel.PiScrollerViewModel;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PiScrollerViewModelTest {

	@Test
	public void then_viewmodel_returns_number_of_digits_of_pi() {
		PiScrollerViewModel viewModel = new PiScrollerViewModel();

		assertThat(viewModel.getNumberOfDigitsOfPi(), is(1001));
	}

}
