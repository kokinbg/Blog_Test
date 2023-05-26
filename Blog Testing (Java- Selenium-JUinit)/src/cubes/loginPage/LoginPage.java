package cubes.loginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	// Polja
	private String url; // url ka strani koju testiramo
	private WebDriver driver; // driver tj browser koji koristimo
	// Web elements koji se koriste na toj strani koju testiramo
	private WebElement emailWebElement;
	private WebElement passwordWebElement;
	private WebElement loginButtonWebElement;

	// Konstruktor, koji kao parametar ima driver - u zagradi
	public LoginPage(WebDriver driver) {
		this.url = "https://testblog.kurs-qa.cubes.edu.rs/login";
		this.driver = driver;

		this.driver.get(url);
		this.driver.manage().window().maximize();

		this.emailWebElement = this.driver.findElement(By.name("email"));
		this.passwordWebElement = this.driver.findElement(By.name("password"));
		this.loginButtonWebElement = this.driver.findElement(By.xpath("//button[@type='submit']"));
	}

	// Metode postpuno svaka redom koja nam treba da bismo testirali tu stranicu
	// U ovom slucaju, 1. idemo na stranicu, url 2. Unesti username 3. Unesi password
	public void openPage() {
		this.driver.get(url); // get(url), znaci,  POKRENI url

	}

	public void insertEmail(String email) {
		emailWebElement.clear();
		emailWebElement.sendKeys(email);
	}

	public void insertPassword(String password) {
		passwordWebElement.clear();
		passwordWebElement.sendKeys(password);
	}

	public void clickOnLogin() {
		loginButtonWebElement.click();
	}
	
	public boolean checkEmailError (String emailError) {
		WebElement emailErrorElement = driver.findElement(By.id("email-error"));
		return emailErrorElement.getText().equalsIgnoreCase(emailError);	
	}
	
	public boolean checkPasswordError (String passwordError) {
		WebElement passwordErrorElement = driver.findElement(By.id("password-error"));
		return passwordErrorElement.getText().equalsIgnoreCase(passwordError);
	}
	
	public boolean checkEmailUserError (String missingUser) {
		WebElement emailUserError = driver.findElement(By.xpath("//div[@class='invalid-feedback']/strong"));
		return emailUserError.getText().equalsIgnoreCase(missingUser);
	}
	
	public void login (String email, String password) {
		insertEmail (email);
		insertPassword(password);
		clickOnLogin();
	}
	
	public void loginSuccess() {
		login("kursqa@cubes.edu.rs", "cubesqa");
	}

}
