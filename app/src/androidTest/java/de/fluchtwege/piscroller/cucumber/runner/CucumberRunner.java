package de.fluchtwege.piscroller.cucumber.runner;

import android.os.Bundle;

import cucumber.api.android.CucumberInstrumentationCore;

public class CucumberRunner extends android.support.test.runner.AndroidJUnitRunner {

	private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

	@Override
	public void onCreate(final Bundle bundle) {
		super.onCreate(bundle);

		instrumentationCore.create(bundle);
		start();
	}

	@Override
	public void onStart() {
		waitForIdleSync();
		instrumentationCore.start();
	}
}