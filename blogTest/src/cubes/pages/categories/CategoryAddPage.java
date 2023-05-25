package cubes.pages.categories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryAddPage {

	// fields
	private String url;
	private WebDriver driver;
	// web elementi
	private WebElement inputNameWebElement;
	private WebElement inputDescriptionWebElement;
	private WebElement saveButtonWebElement;
	private WebElement cancelButtonWebElement;

	// constructor
	public CategoryAddPage(WebDriver driver) {
		this.url = "https://testblog.kurs-qa.cubes.edu.rs/admin/post-categories/add";
		this.driver = driver;
		//this.driver.get(url);
		this.driver.manage().window().maximize();

		this.inputNameWebElement = this.driver.findElement(By.xpath("//input[@placeholder = 'Enter name']"));
		this.inputDescriptionWebElement = this.driver.findElement(By.xpath("//textarea[@placeholder = 'Enter description']"));
		this.saveButtonWebElement = this.driver.findElement(By.xpath("//button[@type = 'submit']"));
		this.cancelButtonWebElement = this.driver.findElement(By.xpath("//a[@class='btn btn-outline-secondary']"));
	}

	// methods:

	// idi na zeljenu web stranu
	public void openPage() {
		this.driver.get(url);
	}

	// da li sam na add category web strani
	public boolean checkAddPageLocation() {
		return this.driver.getCurrentUrl().equalsIgnoreCase(url);
	}
	
	
	// input name
	public void inputName(String name) {
		inputNameWebElement.clear();
		inputNameWebElement.sendKeys(name);
	}

	// input description
	public void inputDescription(String description) {
		inputDescriptionWebElement.clear();
		inputDescriptionWebElement.sendKeys(description);
	}

	// clear name
	public void clearInputName(String name) {
		inputNameWebElement.clear();
		inputNameWebElement.sendKeys(name);
		inputNameWebElement.clear();
	}

	// clear description
	public void clearInputDescription(String description) {
		inputDescriptionWebElement.clear();
		inputDescriptionWebElement.sendKeys(description);
		inputDescriptionWebElement.clear();
	}

	// click on save
	public void clickOnSave() {
		saveButtonWebElement.click();
	}

	// click on cancel
	public void clickOnCancel() {
		cancelButtonWebElement.click();
	}


	// empty category name error message
	public boolean emptyCategoryNameErrorMessage(String errorMessageName) {
		WebElement emptyCategoryNameWebElement = driver.findElement(By.id("name-error"));
		return emptyCategoryNameWebElement.getText().equalsIgnoreCase(errorMessageName);
	}

	// empty category description error message
	public boolean emptyCategoryDescriptionErrorMessage(String errorMessageDescription) {
		WebElement emptyCategoryDescriptionWebElement = driver.findElement(By.id("description-error"));
		return emptyCategoryDescriptionWebElement.getText().equalsIgnoreCase(errorMessageDescription);
	}

	// less than 50 characters in Description
	public boolean lessThan50CharactersError(String lessThan50) {
		WebElement lessThan50CharactersErrorMessage = driver.findElement(By.xpath("//div[@class = 'invalid-feedback']"));
		return lessThan50CharactersErrorMessage.getText().equalsIgnoreCase(lessThan50);
	}
	
	// more than 500 characters in Description
	public boolean moreThan500CharectersError(String moreThan500) {
		WebElement moreThan500CharactersErrorMessage = driver.findElement(By.id("description-error"));
		return moreThan500CharactersErrorMessage.getText().equalsIgnoreCase(moreThan500);
	}

	// provera da li se otvara url sa novom kategorijom
	public String checkCategoryUrl() {
		return inputNameWebElement.getAttribute("value");
	}

}
