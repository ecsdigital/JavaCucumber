package com.agilitas.example;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Dummy class for running cucumber tests in the IDE
 */
@RunWith(Cucumber.class)

@CucumberOptions(  monochrome = true,
        //tags = "@tags",
        features = "src/test/resources/",
        plugin = { "pretty","html:build/cucumber-html-reports",
                "json:build/cucumber-html-reports/cucumber.json" },
        glue = "com.agilitas.example" )

public class RunCukesTest {
    //Run this
}


