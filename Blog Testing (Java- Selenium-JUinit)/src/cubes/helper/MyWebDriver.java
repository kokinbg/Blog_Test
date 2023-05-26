package cubes.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyWebDriver {

	public static WebDriver getDriver(String browser) {

		WebDriver webDriver;

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.firefox.driver", "C:/Users/filmi/Desktop/Final Project/WebDriverFinalProject/geckodriver.exe");
			webDriver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:/Users/filmi/Desktop/Final Project/WebDriver/chromedriver.exe");
			webDriver = new ChromeDriver();

		} else {
			System.setProperty("webdriver.chrome.driver", "C:/Users/filmi/Desktop/Final Project/WebDriver/chromedriver.exe");
			webDriver = new ChromeDriver();
		}

		// Povecaj prozor na max
		webDriver.manage().window().maximize();
//		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
//		jsExecutor.executeScript("document.body.style.zoom='50%'");

		return webDriver;
	}
}
