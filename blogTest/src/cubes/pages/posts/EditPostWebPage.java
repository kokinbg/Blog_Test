package cubes.pages.posts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditPostWebPage {

	//// FIELDS

	// Driver
	private WebDriver driver;
	// WebElements
	private WebElement deletePhotoButton;
	private WebElement chooseCategoryWebElementEdit;
	private WebElement inputTitleWebElementEdit;
	private WebElement inputDescriptionWebElementEdit;
	private WebElement importantRadioWebElementEdit;
	private WebElement noImportantRadioWebElementEdit;
	private WebElement selectNewPhotoWebElementEdit;
	private WebElement cancelButtonWebElementEdit;
	private WebElement saveButtonWebElementEdit;

	// Constructor
	public EditPostWebPage(WebDriver driver, String postTitle) {

		this.driver = driver;
		this.driver.manage().window().maximize();

		this.deletePhotoButton = driver.findElement(By.xpath("//button[@data-action='delete-photo']"));
		this.saveButtonWebElementEdit = driver.findElement(By.xpath("//button[@type='submit']"));
		this.cancelButtonWebElementEdit = driver.findElement(By.xpath("//a[@class='btn btn-outline-secondary']"));
		this.chooseCategoryWebElementEdit = driver.findElement(By.xpath("//select[@name='post_category_id']"));
		this.inputTitleWebElementEdit = driver.findElement(By.xpath("//input[@placeholder = 'Enter title']"));
		this.inputDescriptionWebElementEdit = driver.findElement(By.xpath("//textarea[@name = 'description']"));
		this.importantRadioWebElementEdit = driver.findElement(By.xpath("//label[@for='set-as-important']"));
		this.noImportantRadioWebElementEdit = driver.findElement(By.xpath("//label[@for='set-as-unimportant']"));
		this.selectNewPhotoWebElementEdit = driver.findElement(By.xpath("//input[@name='photo']"));

	}

	// Da li sam na 'Edit category' web lokaciji
	public boolean isOnEditWebPage(String webElementName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement editTitleWebElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Edit Post')]")));
		return editTitleWebElement.getText().equalsIgnoreCase(webElementName);
	}

	// Sa 'Choose category' padajuce liste, odaberi jednu kategoriju
	public void pickCategory(String categoryName) {
		Select categorySelect = new Select(chooseCategoryWebElementEdit);
		categorySelect.selectByVisibleText(categoryName);
	}

	// Edit 'Title'
	public void inputTitle(String title) {
		inputTitleWebElementEdit.clear();
		inputTitleWebElementEdit.sendKeys(title);
	}

	// Edit 'Description'
	public void inputDescription(String description) {
		inputDescriptionWebElementEdit.clear();
		inputDescriptionWebElementEdit.sendKeys(description);
	}

	// Check 'NO-Important' radio button
	public void checkNoImportantRadioButton() {
		noImportantRadioWebElementEdit.click();
	}

	// Check 'YES-Important' radio button
	public void checkYesImportantRadioButton() {
		importantRadioWebElementEdit.click();
	}

	// Klikni na TAG Checkbox
	public void editPickTAG(String checkboxLabel) {
		WebElement label = driver.findElement(By.xpath("//label[contains(text(), '" + checkboxLabel + "')]"));
		label.click();
	}

	// Edituj postojecu fotografiju
	public void editPhoto(String filePath) {
		selectNewPhotoWebElementEdit.sendKeys(filePath);
	}

	// Obrisi postojecu fotografiju
	public void deletePhoto() {
		deletePhotoButton.click();
	}

	// Da li je obrisana fotografija prisutna - slucaj kada imamo VISE photo 'src'
	// elemenata na stranici
	public boolean isPhotoOnPage(String photoSRC) {
		List<WebElement> photos = driver.findElements(By.xpath("//img[@src='" + photoSRC + "']"));
		return !photos.isEmpty();
	}

	// Da li fotografija nije prisutna - slucaj kada imamo JEDNU photo src na pageu
	public boolean isOnePhotoOnPage(String photoSrc) {
		WebElement photo = driver.findElement(By.xpath("//img[@src='" + photoSrc + "']"));
		return photo != null;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// Pokupi SRC sa fotografije na 'Edit Post' web strani!
	public String getPhotoSrc() {
		WebElement photo = driver.findElement(By.xpath("//div[@class='text-center']/img[1]"));
		return photo.getAttribute("src");
	}

	// Da li se slika nalazi na 'Edit Post' strani (poziva "src" iz prethodne
	// "getPhoto" metode i upisuje ovde)
	public boolean isPhotoOnEditPostPage() {
		String photoSrc = getPhotoSrc();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement photoElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@src='" + photoSrc + "']")));
		return photoElement != null;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////

	// Da li se uploadovana Photo, nalazi na 'Post Listi' kao thumbnail
	public boolean isPhotoThumbOnPostList(String postTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement thumbPhotoElement = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//td[contains(text(), '" + postTitle + "')]//ancestor::tr/td[2]/img[1]")));
		wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(thumbPhotoElement)));
		String thumbPhoto = thumbPhotoElement.getAttribute("src");
		return !thumbPhoto.isEmpty();
	}

	// Da li se uploadovana Photo nalazi na 'Edit Post' web lokaciji
	public boolean isPhotoOnEditPostPage(String photoSrc) {
		WebElement photo = driver.findElement(By.xpath("//img[@src='" + photoSrc + "']"));
		return photo != null;
	}

	// Edituj 'Content'
	public void editContent(String iFrameLocator, String contentLocator, String contentText) {

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

	// Klik na 'Cancel edit' button
	public void clickOnCancelEditButton() {
		cancelButtonWebElementEdit.click();
	}

	// Klik na 'Save edit' button
	public void clickOnSaveEditButton() {
		saveButtonWebElementEdit.click();
	}

	// EDIT PAGE - ERROR PORUKE
	// Prazno Title polje, error poruka
	public boolean emptyTitleError(String errorMessageTitle) {
		WebElement emptyTitleWebElement = driver.findElement(By.id("title-error"));
		return emptyTitleWebElement.getText().equalsIgnoreCase(errorMessageTitle);
	}

	// 'TAG' nije selektovan, error poruka
	public boolean emptyTagError(String emptyTagError) {
		WebElement emptyTagWebElement = driver.findElement(By.xpath("//span[@id='tag_id[]-error']"));
		return emptyTagWebElement.getText().equalsIgnoreCase(emptyTagError);
	}

	// Prazno 'Description' polje, error poruka
	public boolean emptyDescriptionError(String errorDescriptionMessage) {
		WebElement emptyDescriptionWebElement = driver.findElement(By.id("description-error"));
		return emptyDescriptionWebElement.getText().equalsIgnoreCase(errorDescriptionMessage);
	}

	// 'Content' nije unesen, error poruka
	public boolean emptyContentError(String emptyContentError) {
		WebElement emptyContentWebElement = driver.findElement(By.xpath("//div[.='The content field is required.']"));
		return emptyContentWebElement.getText().equalsIgnoreCase(emptyContentError);
	}

	// Invalid Photo Format, error poruka
	public boolean invalidPhotoFormatError(String invalidPhotoFormat) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement invalidPhotoFormatErrorField = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'The photo must be an image.')]")));
		return invalidPhotoFormatErrorField.getText().equalsIgnoreCase(invalidPhotoFormat);
	}

}
