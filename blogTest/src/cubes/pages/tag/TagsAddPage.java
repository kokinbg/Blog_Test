package cubes.pages.tag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TagsAddPage {

	// POLJA
	private String url;
	private WebDriver driver;

	// web elementi

	// web element moze da se inicijalizuje i na ovaj nacin:
	// Da ga umesto u konstruktoru, inicijalizujemo u polju uz pomoc anotacije
	// @FindBy()
	// Vazi za Web Element neposredno ispod
	// @FindBy(xpath = "//input[@class='form-control ']\"")
	private WebElement tagNameWebElement;

	private WebElement buttonSaveWebElement;
	private WebElement buttonCancelWebElement;

	// KONSTRUKTOR
	public TagsAddPage(WebDriver driver) {
		this.url = "https://testblog.kurs-qa.cubes.edu.rs/admin/tags/add";
		this.driver = driver;

		// ovde u konstruktoru incijalizujemo nase web elemente
		this.tagNameWebElement = driver.findElement(By.xpath("//input[@class='form-control ']"));
		this.buttonSaveWebElement = driver.findElement(By.xpath("//button[@class = 'btn btn-primary']"));
		this.buttonCancelWebElement = driver.findElement(By.xpath("//a[@class ='btn btn-outline-secondary']"));
		// Ukoliko koristimo u polju @FindBy(), dodajemo jos liniju koda ovde:
		// PageFactory.initElements(driver, this);
	}

	// METODE
	public void openPage() {
		this.driver.get(url);
	}

	public boolean isOnPage() {
		return this.driver.getCurrentUrl().equalsIgnoreCase(url);
	}

	public void clickOnSave() {
		buttonSaveWebElement.click();
	}

	public void clickOnCancel() {
		buttonCancelWebElement.click();
	}

	public void insertName(String name) {
		tagNameWebElement.clear();
		tagNameWebElement.sendKeys(name);
	}

	// obrisi mi polje
	public void clearName(String name) {
		tagNameWebElement.clear();
		tagNameWebElement.sendKeys(name);
		tagNameWebElement.clear();
	}

	// daj mi tekst koji se tu nalazi
	public String getName() {
		// kada je input polje u pitanju, ne moze getText metoda vec getAttribute pa
		// njegov 'value'
		// System.out.println(tagNameWebElement.getAttribute("value"));
		return tagNameWebElement.getAttribute("value");
	}

	public boolean isErrorOnPage(String error) {
		WebElement errorWebElement = driver.findElement(By.id("name-error"));
		return errorWebElement.getText().equalsIgnoreCase(error);
	}

	public boolean doubleTagError(String error) {
		WebElement doubleTagErrorWebElement = driver.findElement(By.xpath("//div[@class ='invalid-feedback']/div[1]"));
		return doubleTagErrorWebElement.getText().equalsIgnoreCase(error);
	}

}
