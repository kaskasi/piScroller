package de.fluchtwege.piscroller.viewmodel;

import android.databinding.BaseObservable;

import java.text.DecimalFormatSymbols;

import de.fluchtwege.piscroller.model.PiProvider;

public class PiScrollerViewModel extends BaseObservable {

    private final PiProvider piProvider = new PiProvider();
    private final char decimalSeparator = new DecimalFormatSymbols().getDecimalSeparator();

    public String getDigitOfPi(int position) {
        String digitOfPi = String.valueOf(piProvider.getDigitOfPi(position));
        if (position == 0) {
            digitOfPi = digitOfPi.replace("3", "3" + decimalSeparator);
        }
        return digitOfPi;
    }

    public int getNumberOfDigitsOfPi() {
        return piProvider.getNumberOfDigitsOfPi();
    }
}
