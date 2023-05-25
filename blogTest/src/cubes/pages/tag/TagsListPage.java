package cubes.pages.tag;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class TagsListPage {

	// fieldovi
	private String url;
	private WebDriver driver;
	// ubacujem web elemente kao fieldove
	private WebElement addNewTagWebElement;
	private WebElement editTagWebElement;
	private WebElement deleteTagWebElement;
	private WebElement deleteTagXbuttonElement;

	// konstruktor
	public TagsListPage(WebDriver driver) {
		this.url = "https://testblog.kurs-qa.cubes.edu.rs/admin/tags";
		this.driver = driver;

		this.driver.get(url);
		this.driver.manage().window().maximize();
		// ovde u konstruktoru incijalizujemo nase web elemente
		this.addNewTagWebElement = driver.findElement(By.xpath("//a[@class='btn btn-success']"));
		this.deleteTagXbuttonElement = driver.findElement(By.xpath("//button[@class='close']"));
	}

	// Metode (funkcionalnosti)

	// Prve tri funkcionalnosti:
	// 1. otvori mi stranicu
	public void openPage() {
		driver.get(url);
	}

	// 2. proveri da li sam na zeljenoj stranici
	public boolean isOnPage() {
		return driver.getCurrentUrl().equalsIgnoreCase(url);
	}

	// 3. klikni na dugme
	public void clickOnAddNewTag() {
		addNewTagWebElement.click();
	}
	// tag element je na listi
	public boolean isTagNameElementOnPage(String tagName) {
	WebElement webElement = driver.findElement(By.xpath("//strong[text()='" + tagName + "']"));
	return webElement.getText().equalsIgnoreCase(tagName);
	}
		
	// tag element nije na listi
	public boolean tagNameIsNotOnTheList (String tagName) {
		List<WebElement> webElements = driver.findElements(By.xpath("//strong[text()='" + tagName + "']"));
    	return webElements.size() > 0;
//    	if (WebElements.size()==0) {
//			return true;
//		}
//		else {
//			return false;
//		}
	}

	// klikni na edit dugme
	public void clickOnEditButton(String tagName) {
		this.editTagWebElement = driver.findElement(By.xpath("//strong[text()='" + tagName + "']//ancestor::tr/td[5]/div[1]/a[2]"));
		editTagWebElement.click();
	}
	
	// polje error
	public boolean clickOnSaveButtonEmptyTag (String error) {
		WebElement errorWebElement = driver.findElement(By.id("name-error"));
		return errorWebElement.getText().equalsIgnoreCase(error);
	}
	
	//klik na delete button
	public void clickOnDeleteTagButton (String tagName) {
		this.deleteTagWebElement = driver.findElement(By.xpath("//strong[text()='"+tagName+"']//ancestor::tr/td[5]/div[1]/button[1]"));
		deleteTagWebElement.click();
	}
	
	// klik na cancel button
	public void clickOnCancelDeleteButton () {
		WebElement cancelDeleteButton = driver.findElement(By.xpath("//button[@class = 'btn btn-default']"));
        cancelDeleteButton.click();
	
	}
	
	// klik na X dugme
	public void clickOnXbutton () {
		deleteTagXbuttonElement.click();
	}
	
	// clickOnDialogTagDelete
	public void clickOnDialogTagDeleteButton () {
		WebElement dialogDeleteTagButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
		dialogDeleteTagButton.click();
	}

}
