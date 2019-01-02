package com.agilitas.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * Set of helper methods to wrap common functions when working with WebDriver
 */
public class SeleniumUtils {
    private static final Logger LOGGER = LogManager.getLogger(SeleniumUtils.class);

    /**
     * Method that will see if an element is displayed every half a second for 5 seconds
     * If not will throw a NoSuchElementException
     * @param driver {@link WebDriver} the current WebDriver to use
     * @param selector {@link By} a CSS or XPATH selector for the WebElement
     */
    public static void waitForElementVisible(WebDriver driver, By selector) {
        for(int i=0; i<10; i++) {
            try {
                WebElement element = driver.findElement(selector);
                if(element.isDisplayed()) {
                    return;
                }
            } catch (NoSuchElementException e) {
                LOGGER.info("NoSuchElementException thrown on attempt #{} of 5", i);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException caught during driver sleep. Cleaning up", e);
                return;
            }
        }
        throw new NoSuchElementException(String.format("Gave up waiting for %s", selector));
    }
}
