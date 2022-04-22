package com.capitalone.interview;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-html-report.html", "json:target/cucumber-local-results.json"},
        features = {"src/test/resources/feature-files"},
        glue = "classpath:com/capitalone/interview/stepdefinitions",
        monochrome = true
)
public class CucumberRunner {
}
