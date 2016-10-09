package de.fluchtwege.piscroller.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class PiDigitViewModel extends BaseObservable {
	private final int position;
	private final String piDigit;

	public PiDigitViewModel(int position, String piDigit) {
		this.position = position;
		this.piDigit = piDigit;
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
		return piDigit;
	}

	@Bindable
	public boolean isPositionEven() {
		return position % 2 == 0;
	}
}
