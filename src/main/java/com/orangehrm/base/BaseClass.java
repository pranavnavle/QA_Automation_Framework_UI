package com.orangehrm.base;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected Properties prop;
	protected WebDriver driver;

	public void setup() throws IOException {
		prop = new Properties();
		try (InputStream in = BaseClass.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (in == null) {
				throw new IOException(
						"config.properties not found on the classpath. In Eclipse, ensure src/main/resources is on the build path (Maven: Update Project).");
			}
			prop.load(in);
		}

		String browser = prop.getProperty("browser", "chrome").trim();

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}

		long implicitSeconds = Long.parseLong(prop.getProperty("ImplicitWait", "10").trim());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitSeconds));
		driver.manage().window().maximize();

		String url = prop.getProperty("url");
		if (url == null || url.isBlank()) {
			throw new IOException("Property 'url' is missing or empty in config.properties");
		}
		driver.get(url.trim());
	}
}
