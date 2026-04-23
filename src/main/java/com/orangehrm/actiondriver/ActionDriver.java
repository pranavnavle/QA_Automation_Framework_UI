package com.orangehrm.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionDriver {

	public WebDriver driver;
	public WebDriverWait wait;

//	create constructor to initialize driver

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
//	Scroll to an element 
	public void scrollToAnElement() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
	}

//	add method for wait for element to be clickable
	public void waitElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("element is not clickable : " + e.getMessage());
		}
	}

//		Wait for Element to be visible 

	public void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		} catch (Exception e) {
			System.out.println("Element is not visible" + e.getMessage());
		}

	}

//	Method to click on Element
	public void click(By by) {
		try {
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("unable to click on element: " + e.getMessage());
		}
	}

//	Method to enter text into input field
	public void enterText(By by, String value) {
		try {
			driver.findElement(by).sendKeys(value);
		} catch (Exception e) {
			System.out.println("Unable to enter the value" + e.getMessage());
		}
	}

//	 method to get Text from input field 

	public String getText(By by) {

		waitForElementToBeVisible(by);
		try {
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("Unable to get the text : " + e.getMessage());
			return " ";
		}

	}

//	Method to write compare two text
	public void compareText(By by, String expectedText) {
		waitForElementToBeVisible(by);
		String actualText = driver.findElement(by).getText();
		if (expectedText.equals(actualText)) {
			System.out.println("text are matching");
		} else
			try {
				System.out.println("Text are not m,atching :" + actualText + "is not matching with " + expectedText);
			} catch (Exception e) {
				System.out.println("unable to compare text" + e.getMessage());
			}
	}
	
//		 Method to check element is displayed
	public boolean elementIsDisplayed(By by) {
		try {
			waitForElementToBeVisible(by);
			boolean isDisplay = driver.findElement(by).isDisplayed();
			if (isDisplay) {
				System.out.println("Element is visible");
				return isDisplay;
			} else {
				return isDisplay;
			}
		} catch (Exception e) {
			System.out.print("Element is not visible :" + e.getMessage());
			return false;
		}

	}
	
	
	
	

}
