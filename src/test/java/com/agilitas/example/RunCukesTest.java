package com.agilitas.example;

import org.junit.runner.RunWith;

/**
 * Dummy class for running cucumber tests in the IDE
 */
@RunWith(io.cucumber.junit.Cucumber.class)

@io.cucumber.junit.CucumberOptions(monochrome = true,
        //tags = "@tags",
        features = "src/test/resources/",
        plugin = { "pretty","html:build/reports/cucumber.html"},
        glue = "com.agilitas.example" )

public class RunCukesTest {
    //Run this
}


