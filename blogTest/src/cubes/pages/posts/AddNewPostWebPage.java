package cubes.pages.posts;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.io.BufferedReader;
import java.io.FileInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AddNewPostWebPage {

	// Fields
	private String url;
	private WebDriver driver;

	// Web Elements
	private WebElement chooseCategoryWebElement;
	private WebElement inputTitleWebElement;
	private WebElement inputDescriptionWebElement;
	private WebElement importantRadioWebElement;
	private WebElement selectNewPhotoWebElement;
	private WebElement cancelButtonWebElement;
	private WebElement saveButtonWebElement;

	// Constructor
	public AddNewPostWebPage(WebDriver driver) {
		this.url = "https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add";
		this.driver = driver;
		this.driver.manage().window().maximize();

//		this.saveButtonWebElement = driver.findElement(By.xpath("//button[@type='submit']"));
		this.cancelButtonWebElement = driver.findElement(By.xpath("//a[@class='btn btn-outline-secondary']"));
		this.chooseCategoryWebElement = driver.findElement(By.xpath("//select[@name='post_category_id']"));
		this.inputTitleWebElement = driver.findElement(By.xpath("//input[@placeholder = 'Enter title']"));
		this.inputDescriptionWebElement = driver.findElement(By.xpath("//textarea[@name = 'description']"));
		this.importantRadioWebElement = driver.findElement(By.xpath("//label[@for='set-as-important']"));
		this.selectNewPhotoWebElement = driver.findElement(By.xpath("//input[@name='photo']"));

	}

	// METODE ZA 'ADD NEW POST' WEB STRANU

	// Sa 'Choose category' padajuce liste, odaberi jednu kategoriju
	public void pickCategory(String categoryName) {
		Select categorySelect = new Select(chooseCategoryWebElement);
		categorySelect.selectByVisibleText(categoryName);
	}

	// Unesi 'Title'
	public void inputTitle(String title) {
		inputTitleWebElement.clear();
		inputTitleWebElement.sendKeys(title);
	}

	// Unesi 'Description'
	public void inputDescription(String description) {
		inputDescriptionWebElement.clear();
		inputDescriptionWebElement.sendKeys(description);
	}

	// Odaberi 'Important' radio button
	public void checkIMPORTANTRadioButton() {
		importantRadioWebElement.click();
	}

	// Klikni na TAG Checkbox
	public void pickTAG (String checkboxLabel) {
		WebElement label = driver.findElement(By.xpath("//label[contains(text(), '" + checkboxLabel + "')]"));
		label.click();
	}

	// Uploaduj fotografiju
	public void uploadPhoto(String filePath) {
		selectNewPhotoWebElement.sendKeys(filePath);
	}

	// Unost teksta u 'Content' polje (iFrame)
	public void inputContent(String iFrameLocator, String contentLocator, String contentText) {
		
		// Nalazim iFrame
		WebElement iFrame_WebELement = driver.findElement(By.xpath(iFrameLocator));

		// Prebacujem fokus na iFrame
		driver.switchTo().frame(iFrame_WebELement);

		// Nalazim u iFrame-u, polje za unost teksta
		WebElement contentField = driver.findElement(By.xpath(contentLocator));

		// Unosim content u prethodno nadjeno polje
		contentField.clear();
		contentField.sendKeys(contentText);

		// Vracam fokus na glavni sadrzaj
		driver.switchTo().defaultContent();
	}
	
	// Unost teksta u 'Content' polje (iFrame) - (drugi nacin)
	public void insertContent (String content) {
		driver.switchTo().frame(0);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.tagName("p")));
		driver.findElement(By.tagName("p")).clear();
		driver.findElement(By.tagName("p")).sendKeys(content);
		driver.switchTo().defaultContent();
		
	}

	// klik na Save button
	public void clickOnSave() {
		WebElement saveButtonWebElement = driver.findElement(By.xpath("//button[@type='submit']"));
//		this.saveButtonWebElement = driver.findElement(By.xpath("//button[@type='submit']"));
		saveButtonWebElement.click();
	}

	// klik na Cancel button
	public void clickOnCancel() {
		cancelButtonWebElement.click();
	}

	// GETTER za 'Cancel' button
	public WebElement getCancelButtonWebElement() {
		return cancelButtonWebElement;
	}

	// GETTER za 'Save' button
	public WebElement getSaveButtonWebElement() {
		return saveButtonWebElement;
	}

	// POMOCNE METODE

	// Da li sam na 'Add Post' web strani
	public boolean checkAddPostWebLocation() {
		return this.driver.getCurrentUrl().equalsIgnoreCase(url);
	}

	// Ucitavanje teksta sa eksternog .txt fajla
	public static String loadTextFromFile(String filePath) throws IOException {
		FileInputStream inputStream = new FileInputStream(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String line;
		StringBuilder stringBuilder = new StringBuilder();

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
		}

		String tekstIzFajla = stringBuilder.toString();

		reader.close();
		inputStream.close();

		return tekstIzFajla;
	}

	// Metoda za unos text-a sa eksternog TXT fajla
	public void addPostTitle(String titleFilePath) throws IOException {
		String titleText = loadTextFromFile(titleFilePath);
		inputTitleWebElement.sendKeys(titleText);
	}
	

	// ERROR PORUKE

	// Prazno Title polje, error poruka
	public boolean emptyTitleMessageError(String errorMessageTitle) {
		WebElement emptyTitleWebElement = driver.findElement(By.id("title-error"));
		return emptyTitleWebElement.getText().equalsIgnoreCase(errorMessageTitle);
	}

	// Prazno 'Description' polje, error poruka
	public boolean emptyDescriptionError(String errorDescriptionMessage) {
		WebElement emptyDescriptionWebElement = driver.findElement(By.id("description-error"));
		return emptyDescriptionWebElement.getText().equalsIgnoreCase(errorDescriptionMessage);
	}

	// U 'Description' polje, uneto manje od 50 karaktera, error poruka
	public boolean descriptionLessThan50Error(String errorDescriptionLessThan50CharMessage) {
		WebElement descriptionWebElement = driver.findElement(By.id("description-error"));
		return descriptionWebElement.getText().equalsIgnoreCase(errorDescriptionLessThan50CharMessage);
	}

	// 'TAG' nije selektovan, error poruka
	public boolean emptyTagError(String emptyTagError) {
		WebElement emptyTagWebElement = driver.findElement(By.xpath("//span[@id='tag_id[]-error']"));
		return emptyTagWebElement.getText().equalsIgnoreCase(emptyTagError);
	}

	// 'Content' nije unesen, error poruka
	public boolean emptyContentError(String emptyContentError) {
		WebElement emptyContentWebElement = driver.findElement(By.xpath("//div[.='The content field is required.']"));
		return emptyContentWebElement.getText().equalsIgnoreCase(emptyContentError);
	}

	// Uneto manje od 20 karaktera u 'Title' polje, error poruka!
	public boolean titleErrorMessageLessThan20Char(String lessThan20Error) {
		WebElement lessThan20CharError = driver.findElement(By.id("title-error"));
		return lessThan20CharError.getText().equalsIgnoreCase(lessThan20Error);
	}
	
	// U 'Title' polje, uneto vise od 255 karaktera, error poruka
	public boolean titleMoreThan255CharErrorMessage (String moreThan255Error) {
		WebElement moreThan255CharError = driver.findElement(By.xpath("//span[contains(text(), 'Please enter no more than 255 characters.')]"));
		return moreThan255CharError.getText().equalsIgnoreCase(moreThan255Error);
	}
	
	// U 'Description' uneto vise od 500 karaktera, error poruka
	public boolean descriptionMoreThan500CharErrorMessage (String moreThan500Char) {
		WebElement moreThan500CharError = driver.findElement(By.id("description-error"));
		return moreThan500CharError.getText().equalsIgnoreCase(moreThan500Char);
	}
	
	// Invalid Photo Format, error poruka
	public boolean invalidPhotoFormatError (String invalidPhotoFormat) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement invalidPhotoFormatErrorField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'The photo must be an image.')]")));
		return invalidPhotoFormatErrorField.getText().equalsIgnoreCase(invalidPhotoFormat);
	}
	
	

}
