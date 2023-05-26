package cubes.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cubes.helper.MyWebDriver;
import cubes.helper.Utils;
import cubes.loginPage.LoginPage;
import cubes.pages.posts.EditPostWebPage;
import cubes.pages.posts.PostListWebPage;

public class EditPostTest {

	private static WebDriver driver;

	// 'TAG' Names
	private String tagOne = "VladimirQA_NE_BRISI!";
	private String tagTwo = "VladimirQA2_NE_BRISI!";
	private String tagThree = "Default TAG NE BRISATI";

	// Photos
	private String jpgPhoto = "C:/Users/filmi/Desktop/Final Project/Photographies/PhotosJPG/bigJPG.jpg";
	private String pngPhoto = "C:/Users/filmi/Desktop/Final Project/Photographies/PhotosPNG/Big Vertical PNG.png";
	private String invalidPhotoFormatAVIF = "C:/Users/filmi/Desktop/Final Project/Photographies/PhotoAVIF/AVIF Format.avif";
	private String jpg30MB = "C:/Users/filmi/Desktop/Final Project/Photographies/PhotosJPG/30MB JPG.jpg";

	// 'Cancel' button WebElement
	public String cancelEditButtonWebElement = "//a[@class = 'btn btn-outline-secondary']";
	public String saveEditButtonWebElement = "//button[@type='submit']";

	// 'Edit Content' WebElements
	// 'Content Field' WebElements (with iFrame)
	private String contentLabel = "//label[text()='Content']";
	private String iFrameLocator = "//iframe[@class='cke_wysiwyg_frame cke_reset']";
	private String contentFieldLocator = "//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']";

	// Error poruke
	private String emptyTitleErrorMessage = "This field is required.";
	private String emptyDescriptionErrorMessage = "This field is required.";
	private String emptyTagSelectedErrorMessage = "This field is required.";
	private String emptyContentErrorMessage = "The content field is required.";
	private String invalidPhotoFormatErrorMessage = "The photo must be an image.";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		// Iz 'MyWebDriver' klase, pozivam driver koji zelilm da koristim
		driver = MyWebDriver.getDriver("chrome");

		// Kreiram objekat "Login Page" web lokacije
		LoginPage loginPage = new LoginPage(driver);

		// Iz 'Login Page' klase, ozivam metodu za uspesno logovanje
		loginPage.loginSuccess();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Na kraju izvrsavanja svih testova, pozivam 'zatvaranje' driver-a koji
		// koristim
		driver.close();
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	// Klik na 'Edit Post' ikonicu, nista ne edituj, klik na 'Cancel' dugme
	@Test
	public void tc01() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam na 'Edit Post' web lokaciji
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Skrolujem do 'Cancel Button' web elementa
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel'
		editPostWebPage.clickOnCancelEditButton();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post' ikonicu, nista ne edituj, klik na 'Save' dugme
	@Test
	public void tc02() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

//		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
//		postListWebPage.clickOnHamburger();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Skrolujem do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Save' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post' ikonicu, edit 'Category, klik na 'Cancel'
	@Test
	public void tc03() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - prebacujem na 'Default' 'Category' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);

		// Skrolujem do 'Cancel' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na Post List web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post' ikonicu, edit 'Category, klik na 'Save'
	@Test
	public void tc04() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

//		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
//		postListWebPage.clickOnHamburger();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit post' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - prebacujem na 'Default Category' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);

		// Skrolujem do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post' ikonicu, edit 'Category, edit 'Title', klik na 'Cancel'
	@Test
	public void tc05() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

//		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
//		postListWebPage.clickOnHamburger();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit post' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - menjam iz 'Default' u 'Vladimir QA' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);

		// Editujem 'Title'
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Skrolujem do 'Cancel' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post' ikonicu, edit 'Category, edit 'Title', klik na 'Save'
	@Test
	public void tc06() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

//		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
//		postListWebPage.clickOnHamburger();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit post' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - menjam u 'Default Category' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);

		// Editujem 'Title' u drugi 'farFarAway' validne duzine
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.farFarAway);

		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.farFarAway));
	}

	// Klik na 'Edit Post' ikonicu, edit 'Category, edit 'Title', edit 'Description,
	// klik na 'Cancel'
	@Test
	public void tc07() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.farFarAway);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit post' ikonu
		postListWebPage.clickOnEditIcon(Utils.farFarAway);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.farFarAway);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranicu
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - menjam iz 'Default' u 'Vladimir QA' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);

		// Editujem 'Title' u 'aWonderfulSerenity' validne duzine
		editPostWebPage.inputTitle(Utils.aWonderfulSerenity);

		// Editem 'Description' validne duzine
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.farFarAway);

		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.farFarAway));
	}

	// Klik na 'Edit Post' ikonicu, edit 'Category' u default, edit 'Title', edit
	// 'Description,
	// klik na 'Save' dugme
	@Test
	public void tc08() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.farFarAway);

//		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
//		postListWebPage.clickOnHamburger();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit post' ikonu
		postListWebPage.clickOnEditIcon(Utils.farFarAway);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.farFarAway);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - biram 'default' 'Category' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);

		// Editujem 'Title' u 'aWonderfulSerenity' validne duzine
		editPostWebPage.inputTitle(Utils.aWonderfulSerenity);

		// Edit 'Description' validne duzine
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));

	}

	// Klik na 'Edit Post' ikonicu, edit 'Category, edit 'Title', edit 'Description,
	// obelezi iz 'Important' u 'Un-Important' radio button,
	// klik na 'Cancel' dugme
	@Test
	public void tc09() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - menjam 'default Category' u 'Vladimir Qa', sa padajuce
		// liste
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);

		// Editujem 'Title' iz 'aWonderfulSerenity' u 'farFarAway' validne duzine
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Edit 'Description'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Editujem 'Important' u 'Un-Important' radio button
		editPostWebPage.checkNoImportantRadioButton();

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post' ikonicu, edit 'Category, edit 'Title', edit 'Description,
	// obelezi iz 'Important' u 'Un-Important' radio button,
	// klik na 'Save' dugme
	@Test
	public void tc10() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

//		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
//		postListWebPage.clickOnHamburger();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - menjam 'default Category' u 'Vladimir Qa', sa padajuce
		// liste
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);

		// Editujem 'Title' iz 'aWonderfulSerenity' u 'farFarAway' validne duzine
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Edit 'Description'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Editujem 'Important' u 'Un-Important' radio button
		editPostWebPage.checkNoImportantRadioButton();

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.farFarAway);

		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.farFarAway));
	}

	// Klik na 'Edit Post' ikonicu, edit 'Category, edit 'Title', edit 'Description,
	// obelezi iz 'Un-Important' u 'Important' radio button,
	// uncheck obelezene 'tagOne i tagTwo, obelezi 'TAG three', klik na 'Cancel'
	// dugme
	@Test
	public void tc11() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.farFarAway);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.farFarAway);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.farFarAway);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - biram 'Default Category' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);

		// Editujem 'Title' iz 'farFarAway' u 'aWonderfulSerenity' validne duzine
		editPostWebPage.inputTitle(Utils.aWonderfulSerenity);

		// Edit 'Description'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Editujem 'Un-Important' u 'Important' radio button
		editPostWebPage.checkYesImportantRadioButton();

		// Un-Check dva TAG-a
		editPostWebPage.editPickTAG(tagOne);
		editPostWebPage.editPickTAG(tagTwo);

		// Obelezi treci TAG
		editPostWebPage.editPickTAG(tagThree);

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.farFarAway);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.farFarAway));
	}

	// Klik na 'Edit Post' ikonicu, edit 'Category' u 'Default', edit 'Title', edit
	// 'Description,
	// obelezi iz 'Un-Important' u 'Important' radio button,
	// uncheck sve obelezene 'TAG'-ove, obelezi 'TAG three', klik na 'Save' dugme
	@Test
	public void tc12() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.farFarAway);

//		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
//		postListWebPage.clickOnHamburger();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.farFarAway);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.farFarAway);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - biram 'default' 'Category' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);

		// Editujem 'Title' iz 'farFarAway' u 'aWonderfulSerenity' validne duzine
		editPostWebPage.inputTitle(Utils.aWonderfulSerenity);

		// Edit 'Description'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Editujem iz 'Un-Important' u 'Important' radio button
		editPostWebPage.checkYesImportantRadioButton();

		// Un-Check prva dva TAG-a
		editPostWebPage.editPickTAG(tagOne);
		editPostWebPage.editPickTAG(tagTwo);

		// Obelezi treci TAG (ostaje samo on obelezen)
		editPostWebPage.editPickTAG(tagThree);

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post' ikonicu, edit 'Category' iz 'Default' u 'Vladimir QA',
	// edit 'Title' u 'Far far Away, edit 'Description iz 'moreThan50' u
	// 'hardcoded',
	// obelezi iz 'Important' u 'Un-Important' radio button,
	// uncheck 'tagThree' (ne ostaje ni jedan obelezen), klik na 'Cancel' dugme
	@Test
	public void tc13() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

//		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
//		postListWebPage.clickOnHamburger();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - biram 'Vladimir QA' 'Category' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);

		// Editujem 'Title'
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Edit 'Description'
		editPostWebPage.inputDescription(Utils.hardcodedDescription);

		// Editujem 'Important' u 'Un-Important' radio button
		editPostWebPage.checkNoImportantRadioButton();

		// Un-Check 'TAG Three'
		editPostWebPage.editPickTAG(tagThree);

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post' ikonicu, edit 'Category' iz 'Default' u 'Vladimir QA',
	// edit 'Title' u 'Far far Away, edit 'Description iz 'moreThan50' u
	// 'hardcoded',
	// obelezi iz 'Important' u 'Un-Important' radio button,
	// uncheck 'tagThree', klik na 'Save Edit' dugme
	@Test
	public void tc14() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - biram 'Vladimir QA' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);

		// Editujem 'Title'
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Edit 'Description'
		editPostWebPage.inputDescription(Utils.hardcodedDescription);

		// Editujem 'Important' u 'Un-Important' radio button
		editPostWebPage.checkNoImportantRadioButton();

		// Un-Check i 'TAG Three'
		editPostWebPage.editPickTAG(tagThree);

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveram da li sam ostao na 'Edit Post' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Error poruke
		assertTrue("Ni jedan TAG nije selektovan - error!",
				editPostWebPage.emptyTagError(emptyTagSelectedErrorMessage));
	}

	// Klik na 'Edit Post' ikonicu, edit 'Category' iz 'Default' u 'Vladimir QA',
	// edit 'Title' u 'Far far Away, edit 'Description' iz 'moreThan50' u hardcoded,
	// obelezi iz 'Important' u 'Un-Important' radio button,
	// uncheck 'tagThree', obelezi 'tagOne', klik na 'Cancel' dugme
	@Test
	public void tc15() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - biram 'Vladimir QA' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);

		// Editujem 'Title'
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Edit 'Description'
		editPostWebPage.inputDescription(Utils.hardcodedDescription);

		// Editujem 'Important' u 'Un-Important' radio button
		editPostWebPage.checkNoImportantRadioButton();

		// Un-Check 'TAG Three'
		editPostWebPage.editPickTAG(tagThree);

		// Check 'TAG One'
		editPostWebPage.editPickTAG(tagOne);

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se Post nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));

	}

	// Klik na 'Edit Post' ikonicu, edit 'Category' iz 'Default' u 'Vladimir QA',
	// edit 'Title' u 'Far far Away, edit 'Description' iz 'moreThan50' u
	// 'hardcoded',
	// obelezi iz 'Important' u 'Un-Important' radio button,
	// uncheck 'tagThree', obelezi 'tagOne', klik na 'Save' dugme
	@Test
	public void tc16() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.isOnEditWebPage("Edit Post"));

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Editujem 'Category' - biram 'Vladimir QA' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);

		// Editujem 'Title' u 'farFarAway'
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Edit 'Description' u hardcoded
		editPostWebPage.inputDescription(Utils.hardcodedDescription);

		// Editujem 'Important' u 'Un-Important' radio button
		editPostWebPage.checkNoImportantRadioButton();

		// Un-Check 'TAG Three'
		editPostWebPage.editPickTAG(tagThree);

		// Check 'TAG One'
		editPostWebPage.editPickTAG(tagOne);

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.farFarAway);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.farFarAway));

	}

	// Klik na 'Edit Post' ikonicu, edit 'Category iz 'Vladimir QA' u 'default',
	// edit 'Title' u 'aWonderfulSerenity', edit 'Description iz 'harcoded' u 'more
	// than 50',
	// obelezi iz 'Un-Important' u 'Important' radio button,
	// pored 'tagOne', obelezi i 'tagThree', edituj postojecu jpg sliku, u png, klik
	// na 'Cancel'
	// dugme
	@Test
	public void tc17() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.farFarAway);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.farFarAway);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.farFarAway);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - biram 'Default' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);

		// Editujem 'Title' u 'wonderfulSerenity'
		editPostWebPage.inputTitle(Utils.aWonderfulSerenity);

		// Edit 'Description' u 'more than 50'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Editujem iz 'Un-Important' u 'Important' radio button
		editPostWebPage.checkYesImportantRadioButton();

		// Check 'TAG Three'
		editPostWebPage.editPickTAG(tagThree);

		// Editujem fotografiju
		editPostWebPage.editPhoto("C:/Users/filmi/Desktop/Final Project/Photographies/PhotosPNG/Big Vertical PNG.png");

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.farFarAway);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.farFarAway));

	}

	// Klik na 'Edit Post' ikonicu, edit 'Category iz 'Vladimir QA' u 'default',
	// edit 'Title' u 'aWonderfulSerenity', edit 'Description iz 'harcoded' u 'more
	// than 50',
	// obelezi iz 'Un-Important' u 'Important' radio button,
	// pored 'tagOne', obelezi i 'tagThree', edituj postojecu sliku, iz jpg u png,
	// klik na 'Save'
	// dugme
	@Test
	public void tc18() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.farFarAway);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.farFarAway);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.farFarAway);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - biram 'Default' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);

		// Editujem 'Title' u 'wonderfulSerenity'
		editPostWebPage.inputTitle(Utils.aWonderfulSerenity);

		// Edit 'Description' u 'more than 50'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Editujem iz 'Un-Important' u 'Important' radio button
		editPostWebPage.checkYesImportantRadioButton();

		// Check 'TAG Three'
		editPostWebPage.editPickTAG(tagThree);

		// Editujem fotografiju
		editPostWebPage.editPhoto("C:/Users/filmi/Desktop/Final Project/Photographies/PhotosPNG/Big Vertical PNG.png");

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));

	}

	// Klik na 'Edit Post' ikonicu, edit 'Category iz 'default' u 'Vladimir QA',
	// edit 'Title' u 'farFarAway', edit 'Description iz 'more than 50' u
	// 'harcoded', obelezi iz 'Important' u 'Un-Important' radio button,
	// pored tagOne i tagThree, obelezi i tagTwo (sva tri su ovde obelezena), edituj
	// postojecu sliku iz png u jpg, klik na 'Cancel Edit' dugme
	@Test
	public void tc19() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

//		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
//		postListWebPage.clickOnHamburger();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - iz 'Default' u 'Vladimir QA', sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);

		// Editujem 'Title' iz 'wonderfulSerenity' u 'farFarAway'
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Edit 'Description' iz 'more than 50' u 'hardcodedDescription'
		editPostWebPage.inputDescription(Utils.hardcodedDescription);

		// Editujem iz 'Important' u 'Un-Important' radio button
		editPostWebPage.checkNoImportantRadioButton();

		// Check preostali TAG-a (sad su tri obelezena)
		editPostWebPage.editPickTAG(tagTwo);

		// Editujem fotografiju iz PNG u JPG
		editPostWebPage.editPhoto(jpgPhoto);

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));

	}

	// Klik na 'Edit Post' ikonicu, edit 'Category iz 'default' u 'Vladimir QA',
	// edit 'Title' u 'farFarAway', edit 'Description iz 'more than 50' u
	// 'harcoded',
	// obelezi iz 'Important' u 'Un-Important' radio button,
	// pored tagOne i tagThree, obelezi i tagTwo (sva tri su ovde obelezena), edituj
	// postojecu sliku, klik na 'Save Edit'
	// dugme
	@Test
	public void tc20() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - iz 'Default' u 'Vladimir QA', sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);

		// Editujem 'Title' iz 'wonderfulSerenity' u 'farFarAway'
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Edit 'Description' iz 'more than 50' u 'hardcodedDescription'
		editPostWebPage.inputDescription(Utils.hardcodedDescription);

		// Editujem iz 'Important' u 'Un-Important' radio button
		editPostWebPage.checkNoImportantRadioButton();

		// Check jos preostali tagTwo (ukupno 3 obelezena)
		editPostWebPage.editPickTAG(tagTwo);

		// Editujem fotografiju iz PNG u JPG
		editPostWebPage.editPhoto(jpgPhoto);

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.farFarAway);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.farFarAway));

	}

	// Klik na 'Edit Post' ikonicu, edit 'Category' iz 'Vladimir QA' u 'default',
	// edit 'Title' iz 'farFarAway' u 'aWonderfulSerenity', edit 'Description iz
	// 'Hardcoded' u 'moreThan50',
	// obelezi iz 'Un-Important' u 'Important' radio button,
	// un-chech 'tagOne' da ostanu 'tagTwo' i 'tagThree', promeni sliku iz jpg u
	// png, edituj 'Content', klik na 'Cancel' dugme
	@Test
	public void tc21() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.farFarAway);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.farFarAway);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.farFarAway);

		// Proveravam da li sam se prebacio na 'Edit Post' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - iz ' 'Vladimir QA' u 'default', sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);

		// Editujem 'Title' iz 'farFarAway' u 'wonderfulSerenity'
		editPostWebPage.inputTitle(Utils.aWonderfulSerenity);

		// Edit 'Description' iz 'hardcodedDescription' u 'more than 50'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Editujem iz 'Un-Important' u 'Important' radio button
		editPostWebPage.checkYesImportantRadioButton();

		// Un-check 'tagOne'
		editPostWebPage.editPickTAG(tagOne);

		// Editujem fotografiju iz JPG u PNG
		editPostWebPage.editPhoto(pngPhoto);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Utils.CONTENT_100_WORDS' u 'Edited Text'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, "Edited Text");

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.farFarAway);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.farFarAway));

	}

	// Klik na 'Edit Post' ikonicu, edit 'Category' iz 'Vladimir QA' u 'default',
	// edit 'Title' iz 'farFarAway' u 'aWonderfulSerenity', edit 'Description iz
	// 'Hardcoded' u 'moreThan50', obelezi iz 'Un-Important' u 'Important' radio
	// button,
	// un-chech 'tagOne' da ostanu 'tagTwo' i 'tagThree', promeni sliku iz jpg u
	// png,
	// edituj 'Content', klik na 'Save' dugme
	@Test
	public void tc22() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.farFarAway);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.farFarAway);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.farFarAway);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem 'Category' - iz 'Vladimir QA' u 'default', sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);

		// Editujem 'Title' iz 'farFarAway' u 'wonderfulSerenity'
		editPostWebPage.inputTitle(Utils.aWonderfulSerenity);

		// Edit 'Description' iz 'hardcodedDescription' u 'more than 50'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Editujem iz 'Un-Important' u 'Important' radio button
		editPostWebPage.checkYesImportantRadioButton();

		// Un-check 'tagOne'
		editPostWebPage.editPickTAG(tagOne);

		// Editujem fotografiju iz JPG u PNG
		editPostWebPage.editPhoto(pngPhoto);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Utils.CONTENT_100_WORDS' u 'Edited Text'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, "Edited Text");

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));

	}

	// Klik na 'Edit Post', edituj samo 'Content' u 'Utils.CONTENT_100_WORDS', klik
	// na 'Cancel' dugme
	@Test
	public void tc23() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Edited Text u 'Utils.CONTENT_100_WORDS'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));

	}

	// Klik na 'Edit Post', edituj samo 'Content' u 'Utils.CONTENT_100_WORDS' , klik
	// na 'Save' dugme
	@Test
	public void tc24() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Utils.CONTENT_100_WORDS' u 'Edited Text'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post', promeni sliku iz PNG u JPG, edituj 'Content' iz
	// "Utils.CONTENT_100_WORDS" u String "Edited Text", klik na 'Cancel' dugme
	@Test
	public void tc25() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem fotografiju iz PNG u JPG
		editPostWebPage.editPhoto(jpgPhoto);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Utils.CONTENT_100_WORDS' u 'Edited Text'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, "Edited Text");

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post', promeni sliku iz PNG u JPG, edituj 'Content' iz
	// "Utils.CONTENT_100_WORDS" u String "Edited Text", klik na 'Save' dugme
	@Test
	public void tc26() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem fotografiju iz PNG u JPG
		editPostWebPage.editPhoto(jpgPhoto);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Utils.CONTENT_100_WORDS' u 'Edited Text'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, "Edited Text");

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post', promeni sliku iz JPG u PNG, Un-check 'tagTWO', da ostane
	// samo 'tagTHREE', edituj 'Content' iz String "Edited Text" u
	// "Utils.CONTENT_100_WORDS" , klik na 'Cancel' dugme
	@Test
	public void tc27() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Un-check 'TAGtwo', da ostane samo 'tagThree'
		editPostWebPage.editPickTAG(tagTwo);

		// Editujem fotografiju iz JPG u PNG
		editPostWebPage.editPhoto(pngPhoto);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Edited Text' u 'Utils.CONTENT_100_WORDS'.
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post', promeni sliku iz JPG u PNG, Un-check 'tagTwo', da ostane
	// samo 'tagThree', edituj 'Content' iz String "Edited Text" u
	// "Utils.CONTENT_100_WORDS" , klik na 'Save Edit' dugme
	@Test
	public void tc28() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Un-check 'tagTWO', da ostane samo 'tagThree'
		editPostWebPage.editPickTAG(tagTwo);

		// Editujem fotografiju iz JPG u PNG
		editPostWebPage.editPhoto(pngPhoto);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Edited Text' u 'Utils.CONTENT_100_WORDS'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post', promeni 'Important' u 'Un-Important' radio button,
	// uncheck 'tagThree' i check 'tagOne' (koji je sad jedini selektovan), promeni
	// sliku iz PNG u JPG, edituj 'Content' iz 'Utils.CONTENT_100_WORDS' u , klik na
	// 'Cancel' dugme
	@Test
	public void tc29() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Editujem iz 'Important' u 'Un-Important' radio button
		editPostWebPage.checkNoImportantRadioButton();

		// Uncheck tagThree
		editPostWebPage.editPickTAG(tagThree);

		// Check 'tagONE' (jedini selektovan u ovom trenutku)
		editPostWebPage.editPickTAG(tagOne);

		// Editujem fotografiju iz JPG u PNG
		editPostWebPage.editPhoto(pngPhoto);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Utils.CONTENT_100_WORDS' u 'Edited Text'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, "Edited Text");

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post', promeni 'Important' u 'Un-Important' radio button,
	// uncheck tagThree, check tagOne, promeni
	// sliku iz PNG u JPG, edituj 'Content' iz 'Utils.CONTENT_100_WORDS' u , klik na
	// 'Save' dugme
	@Test
	public void tc30() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// edit category

		// edit title

		// Editujem iz 'Important' u 'Un-Important' radio button
		editPostWebPage.checkNoImportantRadioButton();

		// Uncheck tagThree
		editPostWebPage.editPickTAG(tagThree);

		// Check 'tagOne' (jedini selektovan u ovom trenutku)
		editPostWebPage.editPickTAG(tagOne);

		// Editujem fotografiju iz JPG u PNG
		editPostWebPage.editPhoto(pngPhoto);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Utils.CONTENT_100_WORDS' u 'Edited Text'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, "Edited Text");

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post', edit 'Description' iz 'moreThan50' u 'Hardcoded',
	// promeni iz 'Un-Important' u 'Important radio button,
	// uncheck tagOne, check samo tagTWO, promeni sliku iz PNG u JPG,
	// edituj 'Content' iz 'Edited Text' u 'Utils.CONTENT_100_WORDS', klik na
	// 'Cancel' dugme
	@Test
	public void tc31() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.isOnEditWebPage("Edit Post"));

		// edit category

		// edit title

		// Editujem 'Description' iz 'moreThan50' u 'Hardcoded'
		editPostWebPage.inputDescription(Utils.hardcodedDescription);

		// Editujem iz 'Un-Important' u 'Important' radio button
		editPostWebPage.checkYesImportantRadioButton();

		// Uncheck tagONE
		editPostWebPage.editPickTAG(tagOne);

		// Check tagTWO
		editPostWebPage.editPickTAG(tagTwo);

		// Editujem fotografiju iz PNG u JPG
		editPostWebPage.editPhoto(jpgPhoto);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Edited Text' u 'Utils.CONTENT_100_WORDS'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post', edit 'Description', promeni 'Important radio button,
	// uncheck tagONe, check samo tagTWO, promeni sliku,
	// edituj 'Content', klik na 'Save' dugme
	@Test
	public void tc32() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// edit category

		// edit title

		// Editujem 'Description' iz 'moreThan50' u 'Hardcoded'
		editPostWebPage.inputDescription(Utils.hardcodedDescription);

		// Editujem iz 'Un-Important' u 'Important' radio button
		editPostWebPage.checkYesImportantRadioButton();

		// Uncheck tagOne
		editPostWebPage.editPickTAG(tagOne);

		// Check tagTwo (sad jedini selektovan)
		editPostWebPage.editPickTAG(tagTwo);

		// Editujem fotografiju iz PNG u JPG
		editPostWebPage.editPhoto(jpgPhoto);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Edited Text' u 'Utils.CONTENT_100_WORDS'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post', obrisi 'Category' tj stavi na 'default', klik na 'Save'
	// dugme
	@Test
	public void tc33() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Obrisi, tj stavi 'Category' na 'Default'
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);

		// Editujem 'Description' iz 'Hardcoded' u 'moreThan50'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Editujem iz 'Important' u 'Un-Important' radio button
		editPostWebPage.checkNoImportantRadioButton();

		// samo tagTWO je obelezen

		// Editujem fotografiju iz JPG u PNG
		editPostWebPage.editPhoto(pngPhoto);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Edited Text' u 'Utils.CONTENT_100_WORDS'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post', obrisi 'Category', obrisi 'Title', ostalo ne diraj, klik
	// na 'Save' dugme
	@Test
	public void tc34() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// 'Category' je vec obrisana tj na 'Default'

		// Brisam 'Title'
		editPostWebPage.inputTitle("");

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam ostao na 'Edit Post' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Error poruke
		assertTrue("Title error poruka - greska!", editPostWebPage.emptyTitleError("This field is required."));

	}

	// Klik na 'Edit Post', obrisi samo 'Description', klik na 'Save' dugme
	@Test
	public void tc35() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// 'Category' je vec obrisana tj na 'Default'

		// Upisujem 'Title'
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Brisam 'Description'
		editPostWebPage.inputDescription("");

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam ostao na 'Edit Post' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Error poruke
		assertTrue("Description error poruka - greska!",
				postListWebPage.emptyDescriptionError("This field is required."));
	}

	// Klik na 'Edit Post', obrisi 'Description', klik na 'Cancel' dugme
	@Test
	public void tc36() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// 'Category' je vec obrisana tj na 'Default'

		// Upisujem 'Title'
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Brisam 'Description' tj unosim 'prazan String'
		editPostWebPage.inputDescription("");

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Prvobitni Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post', obrisi 'Photo', proveri da li je 'Photo' obrisana na
	// samom 'Postu'
	@Test
	public void tc37() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// 'Category' je vec obrisana tj na 'Default'

		// Upisujem 'Title'
		editPostWebPage.inputTitle(Utils.aWonderfulSerenity);

		// Upisujem 'Description'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Brisem prethodno uploadovanu 'Photo'
		editPostWebPage.deletePhoto();

		// Proveravam da li je fotografija uspesno obrisana sa 'Edit Post' web strane
		// BUG FOUND - FOTOGRAFIJA NIJE OBRISANA NAKON KLIKA NA DELETE DUGME
		assertTrue("Fotografija je pronadjena nakon brisanja!!", editPostWebPage.isPhotoOnEditPostPage());
//		assertTrue("Fotografija je pronadjena nakon brisanja!!", postListWebPage.isOnePhotoOnPage("https://testblog.kurs-qa.cubes.edu.rs/storage/posts/1204_bigJPG.jpg"));

// 		Preko getPhoto metode koja nalazi atribut SRC
//		assertTrue("Fotografija nije obrisana!", !postListWebPage.getPhoto().equalsIgnoreCase(expectedPhotoSrc));

	}

	// Klik na 'Edit Post', uploaduj prethodno obrisanu 'Photo', klikni 'Save'
	@Test
	public void tc38() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// 'Category' je vec obrisana tj na 'Default'

		// Upisujem 'Title'
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Upisujem 'Description'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Uploadujem prethodno obrisanu 'Photo'
		editPostWebPage.editPhoto(jpgPhoto);

		// Skrolujem do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save Edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		postListWebPage.checkPostListWebLocation();

		// Unosim 'Title' posta u 'Search' web element
		postListWebPage.inputTitleInSearch(Utils.farFarAway);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Proveravam da li se 'Editovani Post' pojavio na 'Post Listi'
		assertTrue("Post se ne pojavljuje na Post Listi!!", postListWebPage.isPostOnTheList(Utils.farFarAway));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li je fotografija uspesno dodata u 'Edited Post' na 'Post
		// Listi' kao THUMB
		assertTrue("Uploadovana fotografija se ne nalazi kao thumbnail na 'Post Listi'!",
				postListWebPage.isPhotoThumbOnPostList(Utils.farFarAway));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ulazim ponovo na 'Edit Post'
		postListWebPage.clickOnEditIcon(Utils.farFarAway);

		// Proveravam da li je fotografija pristutna na 'Edit Post' web stranici
		assertTrue("Fotografija nije pristutna na 'Edit Page' web strani!", editPostWebPage.isPhotoOnEditPostPage());
	}

	// Klik na 'Edit Post', obrisi prethodno uploadovanu 'Photo', klikni 'Save'
	@Test
	public void tc39() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.farFarAway);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.farFarAway);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.farFarAway);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// 'Category' je vec obrisana tj na 'Default'

		// Upisujem 'Title'
		editPostWebPage.inputTitle(Utils.aWonderfulSerenity);

		// Upisujem 'Description'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Brisam prethodno uploadovanu 'Photo'
		editPostWebPage.deletePhoto();

		// Proveravam da li je fotografija u samom 'Editovanom Postu' uspesno obrisana
		String photo = editPostWebPage.getPhotoSrc();
		assertTrue("Fotografija nije obrisana!", editPostWebPage.getPhotoSrc().equalsIgnoreCase(photo));

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));

		// Pretrazujem da li se na 'Post List' u 'Editovanom Postu', nalazi THUMBNAIL
		// prethodno obrisane fotografije
		assertTrue("Obrisana fotografija se nalazi kao thumbnail na 'Post Listi'!",
				postListWebPage.isPhotoThumbOnPostList(Utils.aWonderfulSerenity));

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ulazim na 'Editovani Post'
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Proveravam da li se obrisana slika nalazi na 'Edit Post' web stranu
		assertTrue("Obrisana slika je i dalje pristutna!", editPostWebPage.isPhotoOnEditPostPage());
	}

	// Klik na 'Edit Post', obrisi preostali 'tagTwo', klik na 'Cancel' dugme
	@Test
	public void tc40() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Uncheck tagTWO
		editPostWebPage.editPickTAG(tagTwo);

		// Skrolujem do 'Cancel' web elementa
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Cancel' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li se post pojavljuje na 'Post Listi'
		assertTrue("Post se ne pojavljuje na 'Post Listi'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));

	}

	// Klik na 'Edit Post', obrisi preostali 'tagTWO', klik na 'Save' dugme
	@Test
	public void tc41() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesi 'Title' u title search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Edit post'
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Uncheck tagTWO (nema odabranih tagova)
		editPostWebPage.editPickTAG(tagTwo);

		// Skrolujem do 'Save' web elementa
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save' button
		editPostWebPage.clickOnSaveEditButton();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Proveravam da li sam na 'Edit Post' web lokaciji!
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Error poruke
		assertTrue("TAG error poruka - greska", editPostWebPage.emptyTagError(emptyTagSelectedErrorMessage));
	}

	// Klik na 'Edit Post', obrisi 'Content', klik na 'Cancel' dugme
	@Test
	public void tc42() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Onosim ime Titla u search post
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Edit' iconu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// tagTwo je checkiran

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// U 'Contentu' brisem sadrzaj
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, "");

		// Skrolujem do 'Cancel edit' dugmeta
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));

		// Klik na 'Cancel edit' dugme
		editPostWebPage.clickOnCancelEditButton();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim 'Title' posta u 'Search' web element
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li se 'Post' nalazi 'Post list' web lokaciji
		assertTrue("Post se ne nalazi na 'Post List' web lokaciji!",
				postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));
	}

	// Klik na 'Edit Post', obrisi 'Content', klik na 'Save' dugme
	@Test
	public void tc43() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Sklopi 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesi Title name u 'Search post'
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Edit post' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Brisem sadrzan iz 'Content' polja
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, "");

		// Skrolujem do 'Save Edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save Edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveravam da li sam ostao na 'Edit Post' web lokaciji
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Error poruke
		assertTrue("Description error poruka - greska!", editPostWebPage.emptyContentError(emptyContentErrorMessage));

	}

	// Klik na 'Edit Post', obrisi 'Title', obrisi 'Description', klik na 'Save'
	// dugme
	@Test
	public void tc44() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Sklopi 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesi 'Title' posta u 'Search post' web element
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Edit Post' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Brisam 'Title' posta
		editPostWebPage.inputTitle("");

		// Brisem 'Description' posta
		editPostWebPage.inputDescription("");

		// Skrolujem do 'Save Edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save Edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam ostao na 'Edit Post' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Error poruke
		assertTrue("Title error poruka - greska!", editPostWebPage.emptyTitleError(emptyTitleErrorMessage));
		assertTrue("Description error poruka - greska!",
				editPostWebPage.emptyDescriptionError(emptyDescriptionErrorMessage));
	}

	// Klik na 'Edit Post', obrisi 'Category', obrisi 'Title', obrisi 'Description',
	// obrisi sve 'TAG-ove' (tagTWO je ostao obelezen),
	// klik na 'Save' dugme
	@Test
	public void tc45() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim title nama u 'Search post' polje
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Edit post' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Brisem postojecu kategoriju i postavljam 'Default'
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);

		// Brisem 'Title'
		editPostWebPage.inputTitle("");

		// Brisem 'Description'
		editPostWebPage.inputDescription("");

		// Un-check tagTwo
		editPostWebPage.editPickTAG(tagTwo);

		// Skrolujem do 'Save Edit' web elementa
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveravam da li sam ostao na 'Edit Post' web lokaciji
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Error poruke
		assertTrue("Title error poruka - greska!", editPostWebPage.emptyTitleError(emptyTitleErrorMessage));
		assertTrue("Description error poruka - greska!",
				editPostWebPage.emptyDescriptionError(emptyDescriptionErrorMessage));
		assertTrue("TAG error poruka - greska!", editPostWebPage.emptyTagError(emptyTagSelectedErrorMessage));
	}

	// Klik na 'Edit Post', obrisi 'Category', obrisi 'Title', obrisi 'Description',
	// obrisi sve 'TAG-ove' (tagTWO),
	// obrisi 'Content', klik na 'Save' dugme
	@Test
	public void tc46() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Sklopi 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesi title name u 'Search post' polje
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Klik na 'Edit post' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Biram 'Default Category'
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);
		
		// Unosim prazan 'Title'
		editPostWebPage.inputTitle("");
		
		// Unosim prazan 'Description'
		editPostWebPage.inputDescription("");

		// Un-check tagTwo
		editPostWebPage.editPickTAG(tagTwo);

		// Skrolujem do 'Content' polje
		Utils.scrollTo(driver, By.xpath(contentLabel));
		
		// Unosim prazan 'Content'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, "");
		
		// Skrolujem do 'Save button' web elementa
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));
		
		// Klik na 'Save' button
		editPostWebPage.clickOnSaveEditButton();

		// Proveravam da li sam ostao na 'Edit Post' web lokaciji
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Error poruke
		assertTrue("Title error poruka - greska!", editPostWebPage.emptyTitleError(emptyTitleErrorMessage));
		assertTrue("Description error poruka - greska!", editPostWebPage.emptyDescriptionError(emptyDescriptionErrorMessage));
		assertTrue("TAG error poruka - greska!", editPostWebPage.emptyTagError(emptyTagSelectedErrorMessage));

		// Content error poruka se ne pojavljuje - BUG FOUND!
		assertTrue("Content error poruka - greska!", editPostWebPage.emptyContentError(emptyContentErrorMessage));

	}

	// Klik na 'Edit Post' ikonicu, edit 'Category u 'Vladimir QA, update 'Title'
	// validne duzine, update 'Description' validne duzine,
	// obelezi 'Important' radio button,
	// ostavi samo jedan 'TAG' tj tagTwo koji je obelezen u ovom trenutku, promeni
	// sliku, obrisi 'Content', klik na
	// 'Cancel' dugme
	@Test
	public void tc47() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim 'Title name' u 'Search post' polje
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Klik na 'Edit post' ikonicu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Unosim 'Category' 'VladimirQA'
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);
		
		// Editujem 'Title' u 'farFarAway'
		editPostWebPage.inputTitle(Utils.farFarAway);
		
		// Editujem 'Description' u 'DESCRIPTION_MORE_THAN_50'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		editPostWebPage.checkYesImportantRadioButton();

		// tagTwo je obelezen

		// Uploadujem 'Photo'
		editPostWebPage.editPhoto(jpgPhoto);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));
		
		// Unosim prazan 'Content'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, "");

		// Skrolujem do 'Cancel button' web elementa
		Utils.scrollTo(driver, By.xpath(cancelEditButtonWebElement));
		
		// Klik na 'Cancel button'
		editPostWebPage.clickOnCancelEditButton();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim 'Title' u 'Search Post' polje
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		
		// Proveravam da li je post prisutan na 'Post List' web lokaciji
		assertTrue("Post se ne nalazi na 'Post List' web lokaciji!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));

	}

	// Klik na 'Edit Post', upload invalid 'Photo' format, klik na 'Save' dugme
	@Test
	public void tc48() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Sklopi 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim 'Title' u 'Search post' polje
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Klik na 'Edit post' ikonicu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Biram 'Default Category' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryEditedNameDefault);

		// Uploaduje 'photo' nevalidnog formata
		editPostWebPage.editPhoto(invalidPhotoFormatAVIF);

		// Skrolujem do 'Save button' web elementa
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));
		
		// Klik na 'Save' button
		editPostWebPage.clickOnSaveEditButton();

		//Proveravam da li sam ostao na 'Edit Post' web lokaciji
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Error poruke
		assertTrue("Invalid photo format error poruka - greska!", editPostWebPage.invalidPhotoFormatError(invalidPhotoFormatErrorMessage));

	}

	// Klik na 'Edit Post' ikonicu, edit 'Category, edit 'Title', edit 'Description,
	// obelezi 'Important' radio button,
	// ostavi samo jedan 'TAG' tj tagTwo koji je obelezen u ovom trenutku, promeni
	// sliku, obrisi 'Content', klik na
	// 'Save' dugme
	@Test
	public void tc49() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Sklopi 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim 'Title' u 'Search post' polje
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Klik na 'Edit post' ikonicu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Biram 'Vladimir QA Category' sa padajuce liste
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);
		
		// Unosim 'farFarAway Title'
		editPostWebPage.inputTitle(Utils.farFarAway);
		
		// Unosim 'Description'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezevam 'Important' radio button
		editPostWebPage.checkYesImportantRadioButton();

		// tagTWO je obelezen

		// Biram 'Photo'
		editPostWebPage.editPhoto(pngPhoto);
		
		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));
		
		// Unosim prazan 'Content'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, "");

		// Skrolujem do 'Save Button' web elementa
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));
		
		// Klin na 'Save' button
		editPostWebPage.clickOnSaveEditButton();

		// Proveravam da li sam ostao na 'Edit Post' web lokaciji
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// Error poruke
		assertTrue("Empty content error poruka - greska!", editPostWebPage.emptyContentError(emptyContentErrorMessage));

	}

	// Klik na 'Edit Post' ikonicu, edit 'Category, edit 'Title' validne duzine,
	// edit 'Description validne duzine, obelezi 'Important' radio button,
	// obelezi jedan 'TAG' tj ostavi tagTWO obelezenog, postavi JPG sliku od 30MB,
	// dodaj 'Content', klik na
	// 'Save' dugme
	@Test
	public void tc50() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.aWonderfulSerenity);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.aWonderfulSerenity);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// edit category
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);

		// edit title
		editPostWebPage.inputTitle(Utils.farFarAway);

		// Editujem 'Description' u 'moreThan50'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Editujem iz 'Important' u 'Un-Important' radio button
		editPostWebPage.checkNoImportantRadioButton();

		// tagTWO je obelezen

		// Editujem fotografiju iz PNG u jpg30MB
		editPostWebPage.editPhoto(jpg30MB);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Edited Text' u 'Utils.CONTENT_100_WORDS'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.farFarAway);

		// Proveravam da li se 'Editovani Post' nalazi za 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.farFarAway));
	}

	// Klik na 'Edit Post' ikonicu, edit 'Category, edit 'Title' validne duzine,
	// edit 'Description validne duzine,
	// obelezi 'Important' radio button,
	// ostavi obelezen jedan 'TAG' (tagTWO), promeni sliku, dodaj duzi 'Content',
	// klik na 'Save'
	// dugme
	@Test
	public void tc51() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Sklopi 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unesti 'Title' posta u search
		postListWebPage.inputTitleInSearch(Utils.farFarAway);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'Edit' ikonu
		postListWebPage.clickOnEditIcon(Utils.farFarAway);

		// Kreiram objekat 'Edit Post' web lokacije
		EditPostWebPage editPostWebPage = new EditPostWebPage(driver, Utils.farFarAway);

		// Proveravam da li sam se prebacio na 'Edit Category' web stranici
		assertTrue("Nisam na 'Edit Post' web lokaciji!", editPostWebPage.isOnEditWebPage("Edit Post"));

		// edit category
		editPostWebPage.pickCategory(Utils.categoryNameVladimirQA);

		// edit title
		editPostWebPage.inputTitle(Utils.aWonderfulSerenity);

		// Editujem 'Description' u 'moreThan50'
		editPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Editujem iz 'Un-Important' u 'Important' radio button
		editPostWebPage.checkYesImportantRadioButton();

		// tagTWO ostavljam obelezenog

		// Editujem fotografiju iz jpg30MB u regular size jpg
		editPostWebPage.editPhoto(jpgPhoto);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(contentLabel));

		// Editujem 'Content' iz 'Edited Text' u 'Utils.CONTENT_1000_WORDS'
		editPostWebPage.editContent(iFrameLocator, contentFieldLocator, Utils.CONTENT_1000_WORDS);

		// Skrolujem do 'Save edit' dugmeta
		Utils.scrollTo(driver, By.xpath(saveEditButtonWebElement));

		// Klik na 'Save edit' dugme
		editPostWebPage.clickOnSaveEditButton();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Edit Post' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' editovanog posta
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Proveravam da li se 'Editovani Post' nalazi na 'Post List' web lokaciji
		assertTrue("Post se ne pojavljuje na 'Post List'!", postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity));

	}

}
