package de.fluchtwege.piscroller.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.text.DecimalFormatSymbols;

import de.fluchtwege.piscroller.model.PiProvider;

public class PiDigitViewModel extends BaseObservable {

	private final char decimalSeparator = new DecimalFormatSymbols().getDecimalSeparator();

	private final PiProvider piProvider = new PiProvider();
	private final int position;


	public PiDigitViewModel(int position) {
		this.position = position;
	}

	@Bindable
	public String getPosition() {
		if (position == 0) {
			return "";
		}
		return "" + position;
	}

	@Bindable
	public String getPiDigit() {
		char digitOfPi = piProvider.getDigitOfPi(position);
		if (position == 0) {
			return "" + digitOfPi + decimalSeparator;
		}
		return "" + digitOfPi;
	}

	@Bindable
	public boolean isBackgroundBeige() {
		return position % 2 == 0;
	}
}
