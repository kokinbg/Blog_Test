package cubes.pages.categories;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class CategoryListPage {

	// fields
	private String url;
	private WebDriver driver;
	// web elementi
	private WebElement addNewCategoryButtonWebElement;
	private WebElement pullDownMenu;
	private WebElement addPostCategoryWebElement;
	private WebElement viewCategoryIconWebElement;
	private WebElement editCategoryIconWebElement;
	private WebElement deleteCategoryIconWebElement;
	private WebElement cancelDeleteCategoryButtonWebElement;
	private WebElement xButtonWebElement;
	private WebElement confirmDeleteCategoryButtonWebElement;
	

	

	// constructor
	public CategoryListPage(WebDriver driver) {
		this.url = "https://testblog.kurs-qa.cubes.edu.rs/admin/post-categories";
		this.driver = driver;
		this.driver.get(url);
		this.driver.manage().window().maximize();
		// inicijalizacija web elemenata
		this.addNewCategoryButtonWebElement = driver.findElement(By.xpath("//a[@class='btn btn-success']"));
		
				
	}

	// methods

	// otvori trazeni url
	public void openPage() {
		driver.get(url);
	}

	// da li sam na Category List web strani
	public boolean checkListPageLocation() {
		return this.driver.getCurrentUrl().equalsIgnoreCase(url);
	}

	// klikni na Add New Category button
	public void clickOnAddNewCategoryButton() {
		addNewCategoryButtonWebElement.click();
	}

	// klikni na Add Post Category iz pull down menija
	public void clickOnAddPostCategoryFromMenu() {
		this.pullDownMenu = driver.findElement(By.xpath("//ul[@class='nav nav-pills nav-sidebar flex-column']/li[2]//i[@class='right fas fa-angle-left']"));
		pullDownMenu.click();

		this.addPostCategoryWebElement = driver.findElement(By.xpath("//a[contains(.,'Add Post Category')]"));
		addPostCategoryWebElement.click();
	}

	// proveri da li je nova kategorija dodata na listu
	public boolean isNewCategoryOnTheList(String newCategoryName) {
		WebElement webElement = driver.findElement(By.xpath("//strong[text()='" + newCategoryName + "']"));
		return webElement.getText().equalsIgnoreCase(newCategoryName);
	}
	
	// proveri da li se description nove kategorije pojavljuje na listi
	public boolean isDescriptionOnTheList (String newCategoryName, String categoryDescription) {
		WebElement descriptionWebElement = driver.findElement(By.xpath("//strong[text()= '"+newCategoryName+"']//ancestor::tr/td[3]"));
		String description = descriptionWebElement.getText().replace("...", "");
		return categoryDescription.contains(description);
	}

	// scroll do nekog elementa
	public void scrollTo(String newCategoryName) {
		WebElement element = driver.findElement(By.xpath("//strong[text()='" + newCategoryName + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
	// klikni na edit category icon
	public void clickOnEditCategoryIcon (String categoryName) {
		this.editCategoryIconWebElement = driver.findElement(By.xpath("//strong[text()='"+categoryName+"']//ancestor::tr/td[6]/div[1]/a[2]"));
		editCategoryIconWebElement.click();
	}
	
	// check edit category location
	public boolean checkEditCategoryLocation () {
		WebElement editPostCategoriesHeaderWebElement = driver.findElement(By.xpath("//h3[@class = 'card-title']"));
	    return editPostCategoriesHeaderWebElement.isDisplayed();
	}
	
	// edit page, clear Name input field
	public void clearEditNameInputField () {
		WebElement editNameInputFieldWebElement = driver.findElement(By.xpath("//input[@name='name']"));
		editNameInputFieldWebElement.clear();
	}
	
	// edit page, clear Description input field
	public void clearEditDescriptionInputField () {
		WebElement editDescriptionInputFieldWebElement = driver.findElement(By.xpath("//textarea[@name = 'description']"));
		editDescriptionInputFieldWebElement.clear();
	}
	
	// edit page, edit Name input field
	public void editNameInputField (String string) {
		WebElement editNameInputFieldWebElement = driver.findElement(By.xpath("//input[@name='name']"));
		editNameInputFieldWebElement.sendKeys(string);
	}
	
	// edit page, edit Description input field
	public void editDescriptionInputField (String string) {
		WebElement editDescriptionInputFieldWebElement = driver.findElement(By.xpath("//textarea[@name = 'description']"));
		editDescriptionInputFieldWebElement.sendKeys(string);
	}
	
	// edit page, click on save button
	public void clickOnSaveButtonEditCategory () {
		WebElement saveButtonEditCategoryWebElement = driver.findElement(By.xpath("//button[@type = 'submit']"));
		saveButtonEditCategoryWebElement.click();
	}
	
	// edit page click on cancel button
	public void clickOnCancelEditCategory () {
		WebElement cancelButtonEditCategoryWebElement = driver.findElement(By.xpath("//a[@class = 'btn btn-outline-secondary']"));
		cancelButtonEditCategoryWebElement.click();
	}
		
	// proveri da li je EDITOVANO ime kategorije dodato na listu
		public boolean isEditedCategoryNameOnTheList (String editedCategoryName) {
			WebElement nameWebElement = driver.findElement(By.xpath("//strong[text()='"+editedCategoryName+"']"));
			return nameWebElement.getText().equalsIgnoreCase(editedCategoryName);
	}

	// klikni na view category icon
	public void clickOnViewCategoryIcon(String categoryName) {
		this.viewCategoryIconWebElement = driver.findElement(By.xpath("//strong[text()='" + categoryName + "']//ancestor::tr/td[6]/div[1]/a[1]"));
		viewCategoryIconWebElement.click();
	}
	
	// proveri da li sam na view category web strani
	public boolean checkViewCategoryWebPage (String categoryName) {
		WebElement h2CategoryName = driver.findElement(By.xpath("//h2[contains(text(),'" + categoryName + "')]"));
		return h2CategoryName.getText().equalsIgnoreCase(categoryName);
	}
	
	// proveri da li sam na view category web strani 2
	public boolean checkViewCategoryPage(String categoryName) {
	    WebElement viewCategoryPage = driver.findElement(By.xpath("//a[contains(text(), '" + categoryName + "')]"));
	    return viewCategoryPage.getText().equalsIgnoreCase(categoryName);
	}


	// dohvati url sa kategorijama i proveri da li sam na pravoj strani
//	public boolean checkNewCategoriesUrl(String categoryName) {
//		this.viewCategoryIconWebElement = driver.findElement(By.xpath("//strong[text()='" + categoryName + "']//ancestor::tr/td[6]/div[1]/a[1]"));
//		viewCategoryIconWebElement.click();
//		String expectedUrl = driver.getCurrentUrl().replace(" ", "-");
//		return expectedUrl.contains("/posts/category-posts/") && expectedUrl.contains("/" + categoryName.toLowerCase().replace(" ", "-"));
//	}

	// klikni na delete category icon
	public void clickOnDeleteCategoryIcon(String categoryName) {
		this.deleteCategoryIconWebElement = driver.findElement(By.xpath("//strong[text()='" + categoryName + "']//ancestor::tr/td[6]/div[1]/button[1]"));
		deleteCategoryIconWebElement.click();
	}
	
	// klikni na Cancel delete Category button
	public void clickOnCancelDeleteCategoryButton () {
		this.cancelDeleteCategoryButtonWebElement = driver.findElement(By.xpath("//button[@class='btn btn-default']"));
		cancelDeleteCategoryButtonWebElement.click();
	}
	
	
	// klikni na X web element
	public void clickOnXWebElement () {
		this.xButtonWebElement = driver.findElement(By.xpath("//button[@class = 'close']"));
		xButtonWebElement.click();
	}
	
	// klikni na Delete Category button
	public void clickOnDeleteCategoryButton () {
		this.confirmDeleteCategoryButtonWebElement = driver.findElement(By.xpath("//button[@class = 'btn btn-danger']"));
		confirmDeleteCategoryButtonWebElement.click();
	}
	
	// edit page, empty name, error message
	public boolean emptyNameErrorEditCategories (String emptyNameError) {
		WebElement emptyNameEditInputField = driver.findElement(By.id("name-error"));
		return emptyNameEditInputField.getText().equalsIgnoreCase(emptyNameError);
	}
	
	// edit page, empty description, error message
	public boolean emptyDescriptionEditFieldErrorMessage (String emptyDescriptionError) {
		WebElement emptyDescriptionEditField = driver.findElement(By.id("description-error"));
		return emptyDescriptionEditField.getText().equalsIgnoreCase("This field is required.");
	}
	
	// kategorija NIJE na listi
	public boolean categoryIsNotOnTheList (String categoryName) {
		List<WebElement> webElements = driver.findElements(By.xpath("//strong[text()='"+categoryName+"']"));
		return webElements.size() > 0;
	}

}
