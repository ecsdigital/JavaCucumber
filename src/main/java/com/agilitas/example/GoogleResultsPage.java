package com.agilitas.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GoogleResultsPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleResultsPage.class);
    @FindBy(xpath = "//div[contains(@class,'rc')]//h3")
    public List<WebElement> results;

    public boolean hasResultTitled(String expectedTitle) {
        LOGGER.info("Found {} search results",results.size());
        boolean matchingTitle = false;
                for(WebElement result :results)
                {
                    String resultTitle = result.getText();
                    LOGGER.info("Looking at {}", resultTitle);
                    if (resultTitle.equalsIgnoreCase(expectedTitle)) {
                        LOGGER.info("{} matches {}", resultTitle, expectedTitle);
                        return true;
                    }
                }

        LOGGER.warn("Didn't get a match for "+expectedTitle);
        return false;
    }
}
