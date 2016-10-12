package de.fluchtwege.piscroller.cucumber.test;

import cucumber.api.CucumberOptions;

@CucumberOptions(features = "features",
		glue = {"de.fluchtwege.piscroller.cucumber.steps"},
		format = {"pretty"}
)

class CucumberTestCase {
}