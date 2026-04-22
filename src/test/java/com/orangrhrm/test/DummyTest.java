package com.orangrhrm.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;

public class DummyTest extends BaseClass {

	@BeforeMethod
	public void getSetup() throws IOException {
		setup();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	@Test
	public void dummyTest() {
		String title = driver.getTitle();
		Assert.assertTrue(title != null && title.contains("OrangeHRM"),
				"Expected title to contain OrangeHRM but was: " + title);
		System.out.println("Test passed - Title is matching: " + title);
	
	}

}
