package api.CucumberRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;

import io.cucumber.junit.*;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/java/features",
plugin ="json:target/jsonReports/cucumber-report.json",
glue= {"api.StepDefinitions" , "web.Steps"},
monochrome = true,
dryRun = false,
tags={"@1Kosmos"})
public class TestRunner {

}
