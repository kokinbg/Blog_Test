package cubes.pages.posts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PostListWebPage {

	// FIELDS
	private String url;
	private WebDriver driver;
	// web elements
	private WebElement addNewPostWebElement;
	private WebElement editIconWebElement;

	// CONSTRUCTOR
	public PostListWebPage(WebDriver driver) {
		this.url = "https://testblog.kurs-qa.cubes.edu.rs/admin/posts";
		this.driver = driver;
		this.driver.get(url);
		this.driver.manage().window().maximize();

		// Inicijalizacija web elementa:

		// 'Add New Post' button
		this.addNewPostWebElement = driver.findElement(By.xpath("//a[@class='btn btn-success']"));
	}

	// METHODS

	// Zatvori menu sa strane, klikom na 'hamburger'
	public void clickOnHamburger() {
		WebElement hamburgerMenu = driver.findElement(By.xpath("//a[@data-widget = 'pushmenu']"));
		hamburgerMenu.click();
	}

	// Da li sam na 'Post List' web lokaciji?
	public boolean checkPostListWebLocation() {
		return this.driver.getCurrentUrl().equalsIgnoreCase(url);
	}

	// Klikni na 'Add New Post' button
	public void clickOnAddNewPostButton() {
		addNewPostWebElement.click();
	}

	// Unosenje "Title-a' New Posta u 'Search' web element
	public void inputTitleInSearch(String postName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchPostWebElement = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@class='form-control form-control-sm']")));
		searchPostWebElement.clear();
		searchPostWebElement.sendKeys(postName);
	}

	// Da li se dodati Post, nalazi na 'Post Listi'
	public boolean isPostOnTheList(String newPostName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement postListItem = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//td[contains(text(), '" + newPostName + "')]")));
		return postListItem.getText().equalsIgnoreCase(newPostName);
	}

	// EDIT CATEGORY WEB PAGE:
	// Klik na 'Edit Post' ikonu
	public void clickOnEditIcon(String postTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.editIconWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//td[contains(text(),'" + postTitle + "')]//ancestor::tr/td[12]/div[1]/a[2]")));
//		wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(editIconWebElement)));
		this.editIconWebElement.click();
	}

	// Da li sam na 'Edit category' web lokaciji
	public boolean isOnEditWebPage(String webElementName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement editTitleWebElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Edit Post')]")));
		return editTitleWebElement.getText().equalsIgnoreCase(webElementName);
	}

	// 'Edit Category' - sa 'Choose category' padajuce liste, odaberi jednu
	// kategoriju
	public void pickCategory(String categoryName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement categorySelectField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='post_category_id']")));
		Select categorySelect = new Select(categorySelectField);
		categorySelect.selectByVisibleText(categoryName);
	}

	// Edit 'Title'
	public void editTitle(String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement titleFieldWebElement = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder = 'Enter title']")));
		titleFieldWebElement.clear();
		titleFieldWebElement.sendKeys(title);
	}

	// Edit 'Description'
	public void editDescription(String description) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement descriptionEditWebElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name = 'description']")));
		descriptionEditWebElement.clear();
		descriptionEditWebElement.sendKeys(description);
	}

	// Check 'NO-Important' radio button
	public void checkNoImportantRadioButton() {
		WebElement noImportantWebElement = driver.findElement(By.xpath("//label[@for='set-as-unimportant']"));
		noImportantWebElement.click();
	}

	// Check 'YES-Important' radio button
	public void checkYesImportantRadioButton() {
		WebElement yesImportantWebElement = driver.findElement(By.xpath("//label[@for='set-as-important']"));
		yesImportantWebElement.click();
	}

	// Klikni na TAG Checkbox
	public void editPickTAG(String checkboxLabel) {
		WebElement label = driver.findElement(By.xpath("//label[contains(text(), '" + checkboxLabel + "')]"));
		label.click();
	}

	// Edituj postojecu fotografiju
	public void editPhoto(String filePath) {
		WebElement editPhotoWebElement = driver.findElement(By.xpath("//input[@name='photo']"));
		editPhotoWebElement.sendKeys(filePath);
	}

	// Obrisi postojecu fotografiju
	public void deletePhoto() {
		WebElement deletePhotoButton = driver.findElement(By.xpath("//button[@data-action='delete-photo']"));
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
		WebElement photoElement = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@src='" + photoSrc + "']")));
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cancelEditButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class = 'btn btn-outline-secondary']")));
		cancelEditButton.click();
	}

	// Klik na 'Save edit' button
	public void clickOnSaveEditButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement saveEditButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
		saveEditButton.click();
	}

	// Klikni na 'View Post' web element - ikonu
	public void clickOnView(String postTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement viewIcon = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//td[contains(text(), '" + postTitle + "' )]//ancestor::tr/td[12]/div[1]/a[1]")));
		viewIcon.click();
	}

	// Klik na 'Disable Post' web element - ikonu
	public void clickOnDisableIcon(String postTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement disableIcon = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//td[contains(text(), '" + postTitle + "' )]//ancestor::tr/td[12]/div[2]/button[1]")));
		disableIcon.click();
	}

	// Klik na 'Cancel Disable' button, na pop up-u
	public void clickOnCancelDisableButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cancelDisable = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//form[@id = 'disable-modal']//button[contains(text(), 'Cancel')]")));
		cancelDisable.click();
	}

	// Klik na 'Disable' button, na pop up-u
	public void clickOnDisableButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement disableButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Disable')]")));
		disableButton.click();
	}

	// Klik na 'Enable Post' ikonu - web element
	public void clickOnEnableIcon(String postTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement enableleIcon = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//td[contains(text(), '" + postTitle + "' )]//ancestor::tr/td[12]/div[2]/button[1]")));
		enableleIcon.click();
	}

	// Klik na 'Enable' button, na pop up-u
	public void clickOnEnableButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement enableButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Enable')]")));
		enableButton.click();
	}

	// Klik na 'Cancel Enable' button, na pop up-u
	public void clickOnCancelEnableButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cancelEnableButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//form[@id='enable-modal']//button[contains(.,'Cancel')]")));
		cancelEnableButton.click();
	}

	// Klik na 'Unimportant' web element - ikonu
	public void clickOnUnimportantIcon(String postTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement unimportantIcon = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//td[contains(text(), '" + postTitle + "' )]//ancestor::tr/td[12]/div[2]/button[2]")));
		unimportantIcon.click();
	}

	// Klik na 'Set as Unimportant' button, na pop up-u
	public void clickOnSetAsUnimportantButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement setAsUnimportantButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Set as Unimportant')]")));
		setAsUnimportantButton.click();
	}

	// Klik na 'Important' web element - ikonu
	public void clickOnImportantIcon(String postTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement importantIcon = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//td[contains(text(), '" + postTitle + "' )]//ancestor::tr/td[12]/div[2]/button[2]")));
		importantIcon.click();
	}

	// Klik na 'Set as Important' button, na pop up-u
	public void clickOnSetAsImportantButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement setAsImportantButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Set as Important')]")));
		setAsImportantButton.click();
	}

	// Klik na 'Delete' web element - ikonu
	public void clickOnDeletePostIcon(String postTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(), '"
				+ postTitle + "' )]//ancestor::tr/td[12]/div[1]/button[@data-action='delete']")));
		deleteIcon.click();
	}

	// Klik na 'Cancel Delete' button, u pop up-u
	public void clickOnCancelDeletePostButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cancelDeleteButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//form[@id='delete-modal']//button[contains(.,'Cancel')]")));
		cancelDeleteButton.click();
	}

	// Klik na 'Delete' button, u pop up-u
	public void clickOnDeletePostButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement deleteButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Delete')]")));
		deleteButton.click();
	}

	// Proveri da li se Post pojavio na 'Post Listi', setovan kao 'Disabled'
	public boolean isPostDisabled(String postTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement statusDisabled = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), '" + postTitle
						+ "' )]//ancestor::tr/td[4]/span[contains(text(), \"disabled\")]")));
		return statusDisabled.getText().equalsIgnoreCase("disabled");
	}

	// Proveri da li se Post pojavio na 'Post Listi', setovan kao 'Enabled'
	public boolean isPostEnabled(String postTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement statusEnabled = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), '" + postTitle
						+ "' )]//ancestor::tr/td[4]/span[contains(text(), \"enabled\")]")));
		return statusEnabled.getText().equalsIgnoreCase("enabled");
	}

	// Proveri da li se Post pojavio na 'Post Listi', setovan kao 'Unimportant'
	public boolean isPostUnimportant(String postTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement statusUnimportant = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//td[contains(text(), '" + postTitle + "' )]//ancestor::tr/td[3]/span[contains(text(), \"No\")]")));
		return statusUnimportant.getText().equalsIgnoreCase("No");
	}

	// Proveri da li se Post pojavio na 'Post Listi', setovan kao 'Important'
	public boolean isPostImportant(String postTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement statusImportant = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//td[contains(text(), '" + postTitle + "' )]//ancestor::tr/td[3]/span[contains(text(), \"Yes\")]")));
		return statusImportant.getText().equalsIgnoreCase("Yes");
	}

	// SEARCH BOX WEB ELEMENTS
	//////////////////////////
	// Unesi 'Title Posta' u 'Post Title Search' input polje
	public void inputTitleInSearchBox(String postName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchBox = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='title']")));
		searchBox.clear();
		searchBox.sendKeys(postName);
	}

	// Odaberi 'Category' sa search boxa 'Category liste'
	public void pickOneCategoryInSearchBox(String categoryName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement categorySearchBox = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='post_category_id']")));
		Select select = new Select(categorySearchBox);
		select.selectByVisibleText(categoryName);
	}

	// Verifikuj Postove pod trazenom 'Kategorijom'
	public boolean verifyPostsByCategory(String categoryName) {
		// Čekanje da se pojavi lista postova sa zadatom 'Kategorijom'
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By categoryLocator = By.xpath("//td[contains(text(),'" + categoryName + "')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryLocator));

		// Pronalaženje svih redova koji sadrže zadati tag
		List<WebElement> rows = driver.findElements(By
				.xpath("//td[@data-column-label='Category']/a[contains(text(),'" + categoryName + "')]/ancestor::tr"));

		// Provera da li su svi postovi povezani sa zadatim tagom
		for (WebElement row : rows) {
			WebElement title = row.findElement(By.xpath(".//td[1]/a"));
			String postTitle = title.getText();
			if (!postTitle.contains(categoryName)) {
				return false;
			}
		}
		return true;
	}

	// Odaberi 'TAG' sa search boxa 'TAG liste'
	public void pickTAGInSearchBox(String tagName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement tagSearchBox = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='select2-selection__rendered']")));
		tagSearchBox.click();

		WebElement tagOption = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'" + tagName + "')]")));
		Actions actions = new Actions(driver);
		actions.moveToElement(tagOption).click().perform();
	}

	// Verifikuj Postove pod trazenom 'TAG-om'
	public boolean verifyPostsByTag(String tagName) {
		// Čekanje da se pojavi lista postova sa zadatim 'TAG-om'
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By tagLocator = By.xpath("//ul[@class='select2-selection__rendered']//li[contains(text(),'" + tagName + "')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(tagLocator));

		// Pronalaženje svih redova koji sadrže zadati tag
		List<WebElement> rows = driver.findElements(
				By.xpath("//td[@data-column-label='Tags']/a[contains(text(),'" + tagName + "')]//ancestor::tr"));

		// Provera da li su svi postovi povezani sa zadatim tagom
		for (WebElement row : rows) {
			List<WebElement> tags = row.findElements(By.xpath(".//td[@data-column-label='Tags']/a"));
			for (WebElement tag : tags) {
				if (!tag.getText().equals(tagName)) {
					return false;
				}
			}
		}
		return true;
	}

	///////////////////////////
	// EDIT PAGE - ERROR PORUKE
	///////////////////////////
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
		WebElement emptyContentWebElement = driver.findElement(By.xpath("//div[@class='invalid-feedback']"));
		return emptyContentWebElement.getText().equalsIgnoreCase(emptyContentError);
	}

	// Invalid Photo Format, error poruka
	public boolean invalidPhotoFormatError(String invalidPhotoFormat) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement invalidPhotoFormatErrorField = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'The photo must be an image.')]")));
		return invalidPhotoFormatErrorField.getText().equalsIgnoreCase(invalidPhotoFormat);
	}

//	// Da li je 'New Post' uspesno dodat na listu - petlja
//		public void proveriDaLiJePostDodatNaListuu(String naslovPosta) {
//			// sačekaj da se lista postova pojavi
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='card-title']")));
//
//			boolean postPronađen = false;
//			do {
//				// pretraži trenutnu stranicu za post sa datim naslovom
//				List<WebElement> postovi = driver
//						.findElements(By.xpath("//tr[contains(@class, 'odd') or contains(@class, 'even')]"));
//				for (WebElement post : postovi) {
//					String naslov = post.findElement(By.xpath(".//td[contains(text(),'" + naslovPosta + "')]/following-sibling::td[1]")).getText();
//					if (naslov.equals(naslovPosta)) {
//						postPronađen = true;
//						break;
//					}
//				}
//
//				// ako post nije pronađen, skroluj do dugmeta Next i klikni na njega da bi se
//				// prikazala sledeća strana
//				if (!postPronađen) {
//					try {
//						WebElement nextDugme = driver.findElement(By.xpath("//li[@id='entities-list-table_next']"));
//						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextDugme);
//						Thread.sleep(500);
//						nextDugme.click();
//					} catch (NoSuchElementException | InterruptedException e) {
//						// ako Next dugme ne postoji, završi pretragu
//						break;
//					}
//				}
//			} while (!postPronađen);
//
//			// proveri da li je post pronađen
//			Assert.assertTrue(postPronađen);
//		}

}
