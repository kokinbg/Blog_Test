package cubes.pages.tag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TagsEditTags {
	
	// polja
	private String url;
	private WebDriver driver;
	// dajemo u polju naziv web elementa koji trazimo
	private WebElement editTagWebElement;
	
	public TagsEditTags (WebDriver driver) {
		this.url = "https://testblog.kurs-qa.cubes.edu.rs/admin/tags/add";
		this.driver = driver;
		this.driver.get(url);
		this.driver.manage().window().maximize();
		//kreiram i inicijalizujem WEB ELEMENT koji trazim
	//	this.editTagWebElement = driver.findElement(By.xpath("//strong[text()='"+tagName+"']"));		
		
	}
	
	// METODE
	
	// idi na zeljenu stranicu
	public void openPage () {
		driver.get(url);
	}
	
	// proveri da li sam na zeljenoj stranici
	public boolean isOnPage () {
		return driver.getCurrentUrl().equalsIgnoreCase(url);
	}
	
	// klikni na edit dugme
	public void clickOnEditButton (String tagName) {
		this.editTagWebElement = driver.findElement(By.xpath("//strong[text()='"+tagName+"']//ancestor::tr/td[5]/div[1]/a[2]"));
		editTagWebElement.click();
	}

}
