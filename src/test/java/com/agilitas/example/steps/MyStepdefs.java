package com.agilitas.example.steps;

import com.agilitas.example.GoogleResultsPage;
import com.agilitas.example.SeleniumUtils;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import static org.junit.Assert.assertTrue;

/**
 * Some Cucumber step definitions for the example project
 * Created by Tom on 14/04/2017.
 */
public class MyStepdefs implements En {
    private WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(MyStepdefs.class);

    @Before
    public void setUpDriver() throws Exception {
        String browser = System.getProperty("browser");
        ChromeOptions options = new ChromeOptions();
        if(browser==null) {
            LOGGER.info("No broswer JVM argument passed in, defaulting to Chrome");
            driver = new ChromeDriver(options);
        } else {
            switch (browser) {
                case "IE":
                    driver = new InternetExplorerDriver();
                    break;
                case "Chrome":
                    driver = new ChromeDriver(options);
                    break;
                case "Firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new UnsupportedOperationException(String.format("Sorry I don't know how to create a driver for your command line argument %s", browser));
            }
        }
    }
    @After
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public MyStepdefs() {
        Given("^I've opened the google home page$", () -> {
            driver.get("https://www.google.com");
            SeleniumUtils.waitForElementVisible(driver, By.cssSelector("input[title='Search']"));
        });

        When("^I search for '(.*)'$", (String searchTerm) -> {
            WebElement searchBox = driver.findElement(By.cssSelector("input[title='Search']"));
            searchBox.sendKeys(searchTerm);
            searchBox.sendKeys(Keys.ENTER);
            //Wait for the search results to come back
            SeleniumUtils.waitForElementVisible(driver, By.cssSelector("#resultStats"));
        });
        Then("^there should be a result titled '(.*)'$", (String expectedResultTitle) -> {
            GoogleResultsPage resultsPage = PageFactory.initElements(driver, GoogleResultsPage.class);
            assertTrue("Couldn't find expected result title in results", resultsPage.hasResultTitled( expectedResultTitle));
        });
    }
}
