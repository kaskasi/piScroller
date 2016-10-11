package de.fluchtwege.piscroller.viewmodel;

import android.databinding.BaseObservable;

import java.text.DecimalFormatSymbols;

import de.fluchtwege.piscroller.model.PiProvider;

public class PiScrollerViewModel extends BaseObservable {

    private final PiProvider piProvider = new PiProvider();

    public int getNumberOfDigitsOfPi() {
        return piProvider.getNumberOfDigitsOfPi();
    }
}
