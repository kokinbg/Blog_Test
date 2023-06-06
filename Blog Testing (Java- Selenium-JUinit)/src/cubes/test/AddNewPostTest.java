package cubes.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import cubes.helper.MyWebDriver;
import cubes.helper.Utils;
import cubes.loginPage.LoginPage;
import cubes.pages.posts.AddNewPostWebPage;
import cubes.pages.posts.PostListWebPage;

public class AddNewPostTest {

	// Driver
	private static WebDriver driver;

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
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// Klik na 'Add new post' dugme, sva polja ostavljam prazna, Klik na Cancel
	// dugme
	@Test
	public void tc01() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na Listi Postova!!", postListPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na Add Post web strani!", addPostPage.checkAddPostWebLocation());

		// Ubacujem prazan String u 'Title'
		addPostPage.inputTitle("");

		// Ubacujem prazan String u 'Description'
		addPostPage.inputDescription("");

		// Skrolujem do 'Cancel' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addPostPage.clickOnCancel();

		// Proveravam da li sam vracen na Post List web lokaciju
		assertTrue("Nisam na 'Post List' web strani!", postListPage.checkPostListWebLocation());
	}

	// Klik na 'Add new post' dugme, sva polja ostavljam prazna, klik na 'Save'
	// dugme
	@Test
	public void tc02() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post list' web strani!!", postListPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add Post' web strani!", addPostPage.checkAddPostWebLocation());

		// Ubacujem prazan String u 'Title'
		addPostPage.inputTitle("");

		// Ubacujem prazan String u 'Description'
		addPostPage.inputDescription("");

		// Ubacujem prazan String u 'Content'

		// Skrolujem do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addPostPage.clickOnSave();

		// Provera da li sam ostao na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addPostPage.checkAddPostWebLocation());

		// Error poruke - provera
		assertTrue("Title error poruka - greska!", addPostPage.emptyTitleMessageError("This field is required."));
		assertTrue("Description error poruka - greska!", addPostPage.emptyDescriptionError("This field is required."));
		assertTrue("TAG error poruka - greska!", addPostPage.emptyTagError("This field is required."));
		assertTrue("Content error poruka - greska!", addPostPage.emptyContentError("The content field is required."));
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, klik na
	// 'Cancel' dugme
	@Test
	public void tc03() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post list' web strani!!", postListPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add post' web strani!", addPostPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addPostPage.pickCategory(Utils.categoryName);

		// Skrolujem do 'Cancel' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addPostPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post list' web lokaciju
		assertTrue("Nisam se vratio na 'Post list' web stranu!", postListPage.checkPostListWebLocation());
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, klik na 'Save'
	// dugme
	@Test
	public void tc04() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post list' web strani!!", postListPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add post' web strani!", addPostPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addPostPage.pickCategory(Utils.categoryName);

		// Skrolujem do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addPostPage.clickOnSave();

		// Proveravam da li sam ostao na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji", addPostPage.checkAddPostWebLocation());

		// Error poruke - provera
		assertTrue("Title error poruka - greska!", addPostPage.emptyTitleMessageError(Utils.emptyTitleErrorMessage));
		assertTrue("Description error poruka - greska!",
				addPostPage.emptyDescriptionError(Utils.emptyDescriptionErrorMessage));
		assertTrue("TAG error poruka - greska!", addPostPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
		assertTrue("Content error poruka - greska!", addPostPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' manji od 20 karaktera, klik na 'Cancel' dugme
	@Test
	public void tc05() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post list' web strani!!", postListPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add post' web strani!", addPostPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addPostPage.pickCategory(Utils.categoryName);

		// Upisujem nevalidan Title, manji od 20 karaktera
		addPostPage.inputTitle(Utils.LESS_THAN_20_CHAR_RANDOM_STRING);

		// Skrolujem do 'Cancel' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addPostPage.clickOnCancel();
		addPostPage.clickOnCancel();
		// NAPOMENA: VELIKI ERROR! POTREBNO JE DVA PUTA KLIKNUTI NA CANCEL DA BI SE
		// VRATILI NA PRETHODNU STRANU!!!

		// Proveravam da li sam se vratio na 'Post list' web lokaciju
		assertTrue("Nisam na 'Post list' web strani!", postListPage.checkPostListWebLocation());
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' manji od 20 karaktera, klik na 'Save' dugme
	@Test
	public void tc06() {
		// Kreiram objekat 'Post List Page' web lokacije
		PostListWebPage postListPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'post list' web lokaciji!", postListPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add post' web strani!", addPostPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addPostPage.pickCategory(Utils.categoryName);

		// Upisujem Title, manji od 20 karaktera
		addPostPage.inputTitle(Utils.generateRandomStringLessThan20());

		// Skrolujem do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addPostPage.clickOnSave();
		addPostPage.clickOnSave();
		// NAPOMENA: VELIKI ERROR! POTREBNO JE DVA PUTA KLIKNUTI NA SAVE!!!

		// Proveravam da li sam ostao na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add Post' web lokaciji!", addPostPage.checkAddPostWebLocation());

		// Error poruke - provera
		assertTrue("Title error poruka - greska!",
				addPostPage.titleErrorMessageLessThan20Char(Utils.titleLessThan20CharErrorMessage));
		assertTrue("Description error poruka - greska!",
				addPostPage.emptyDescriptionError(Utils.emptyDescriptionErrorMessage));
		assertTrue("TAG error poruka - greska!", addPostPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
		assertTrue("Content error poruka - greska!", addPostPage.emptyContentError(Utils.emptyContentErrorMessage));

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste,
	// upisujem 'Title' veci od 20 karaktera, klik na 'Cancel' dugme
	@Test
	public void tc07() {
		// Kreiram objekat 'Post List Page' web lokacije
		PostListWebPage postListPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web strani!", postListPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Post List' web strani!", addPostPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addPostPage.pickCategory(Utils.categoryName);

		// Upisujem Title, veci od 20 karaktera
		addPostPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Skrolujem do 'Cancel' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addPostPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post list' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste,
	// upisujem 'Title' veci od 20 karaktera, klik na 'Save' dugme
	@Test
	public void tc08() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add Post' web lokaciji!", addPostPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa liste
		addPostPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addPostPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Skrolujem do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addPostPage.clickOnSave();

		// Proveravam da li sam ostao na istoj strani
		addPostPage.checkAddPostWebLocation();

		// Error poruke - provera
		assertTrue("Description error poruka - greska!",
				addPostPage.emptyDescriptionError(Utils.emptyDescriptionErrorMessage));
		assertTrue("TAG error poruka - greska!", addPostPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
		assertTrue("Content error poruka - greska!", addPostPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' manji od 20 karaktera, upisujem 'Description' manji od 50 karaktera,
	// klik na 'Cancel' dugme
	@Test
	public void tc09() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add Post' web lokaciji!", addPostPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addPostPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', manji od 20 karaktera
		addPostPage.inputTitle(Utils.LESS_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', manji od 50 karaktera
		addPostPage.inputDescription(Utils.LESS_THAN_50_CHAR_RANDOM_STRING);

		// Skrolujem do 'Cancel' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addPostPage.clickOnCancel();
		addPostPage.clickOnCancel();
		// NAPOMENA: VELIKI ERROR! POTREBNO JE DVA PUTA KLIKNUTI NA CANCEL DA BI SE
		// VRATILI NA PRETHODNU STRANU!!!

		// Proveravam da li sam se vratio na 'Post list' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste,
	// upisujem 'Title' manji od 20 karaktera, upisujem 'Description' manji od 50
	// karaktera, klik na 'Save' dugme
	@Test
	public void tc10() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add Post' web lokaciji!", addPostPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addPostPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', manji od 20 karaktera
		addPostPage.inputTitle(Utils.LESS_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', manji od 50 karaktera
		addPostPage.inputDescription(Utils.LESS_THAN_50_CHAR_RANDOM_STRING);

		// Skrolujem do XPatha 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		// NAPOMENA: VELIKI ERROR! POTREBNO JE DVA PUTA KLIKNUTI NA SAVE!!
		addPostPage.clickOnSave();
		addPostPage.clickOnSave();

		// Proveravam da li sam ostao na istoj strani
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addPostPage.checkAddPostWebLocation());

		// Error poruke - provera
		assertTrue("Title error poruka - greska!",
				addPostPage.titleErrorMessageLessThan20Char(Utils.titleLessThan20CharErrorMessage));
		assertTrue("Description error poruka - greska!",
				addPostPage.descriptionLessThan50Error(Utils.descriptionErrorMessageLessThan50Char));
		assertTrue("TAG error poruka - greska!", addPostPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
		assertTrue("Content error poruka - greska!", addPostPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera, upisujem 'Description' manji od 50 karaktera,
	// klik na 'Cancel' dugme
	@Test
	public void tc11() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add Post' web lokaciji!", addPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', manji od 50 karaktera
		addPostWebPage.inputDescription(Utils.LESS_THAN_20_CHAR_RANDOM_STRING);

		// Skrolujem do XPatha 'Cancel' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addPostWebPage.clickOnCancel();
		addPostWebPage.clickOnCancel();
		// NAPOMENA: VELIKI ERROR! POTREBNO JE DVA PUTA KLIKNUTI NA CANCEL DA BI SE
		// VRATILI NA PRETHODNU STRANU!!!

		// Proveravam da li sam se vratio na 'Post list' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera, upisujem 'Description' manji od 50 karaktera,
	// klik na 'Save' dugme
	@Test
	public void tc12() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji", addPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', manji od 50 karaktera
		addPostWebPage.inputDescription(Utils.LESS_THAN_50_CHAR_RANDOM_STRING);

		// Kreiram objekat Web Element 'Save Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement saveButton = addPostWebPage.getSaveButtonWebElement();

		// Skrolujem do 'Save' dugmeta preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		// NAPOMENA: VELIKI ERROR! POTREBNO JE DVA PUTA KLIKNUTI NA SAVE!!
		addPostWebPage.clickOnSave();
		addPostWebPage.clickOnSave();

		// Proveravam da li sam ostao na istoj strani
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addPostWebPage.checkAddPostWebLocation());

		// Error poruke - provera
		assertTrue("Description error poruka - greska!",
				addPostWebPage.descriptionLessThan50Error(Utils.descriptionErrorMessageLessThan50Char));
		assertTrue("TAG error poruka - greska!", addPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
		assertTrue("Content error poruka - greska!", addPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera, upisujem 'Description' veci od 50 karaktera,
	// klik na 'Cancel' dugme
	@Test
	public void tc13() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Kreiram objekat Web Element 'Cancel Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
		WebElement cancelButton = addPostWebPage.getCancelButtonWebElement();

		// Skrolujem do 'Cancel' web elementa preko xPatha
		Utils.scrollToWebElement(driver, cancelButton);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Cancel' dugme
//		addPostWebPage.clickOnCancel();
		cancelButton.click();

		// Proveravam da li sam se vratio na 'Post list' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera, upisujem 'Description' veci od 50 karaktera,
	// klik na 'Save' dugme
	@Test
	public void tc14() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji", addPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Kreiram objekat Web Element 'Save Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement saveButton = addPostWebPage.getSaveButtonWebElement();
//		// Skrolujem do 'Save' web elementa pozvanog preko gettera!
//		Utils.scrollToWebElement(driver, saveButton);

		// Skrolujem do 'Save' dugmeta preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addPostWebPage.clickOnSave();

		// Proveravam da li sam ostao na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addPostWebPage.checkAddPostWebLocation());

		// Error poruke
		assertTrue("TAG error poruka - greska!", addPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
		assertTrue("Content error poruka - greska!", addPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste,
	// upisujem 'Title' veci od 255 karaktera, upisujem 'Description' manji od 50
	// karaktera, klik na 'Cancel' dugme
	@Test
	public void tc15() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title' veci od 255 karaktera, tekst 'Title'-a, ucitavam sa
		// eksternog '.TXT' fajla
		try {
			addNewPostWebPage.addPostTitle("C:/Users/filmi/Desktop/Additonal Files/Doc/300 char.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Upisujem 'Description', manji od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.LESS_THAN_50_CHAR_RANDOM_STRING);

		// Kreiram objekat Web Element 'Cancel Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement cancelButton = addNewPostWebPage.getCancelButtonWebElement();

		// Skrolujem do 'Cancel' dugmeta preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();
		addNewPostWebPage.clickOnCancel();
		// NAPOMENA: VELIKI ERROR! POTREBNO JE DVA PUTA KLIKNUTI NA CANCEL DA BI SE
		// VRATILI NA PRETHODNU STRANU!!!

		// Proveravam da li sam se vratio na 'Post list' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 255 karaktera,
	// upisujem 'Description' manji od 50 karaktera, klik na 'Save' dugme
	@Test
	public void tc16() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title' veci od 255 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_255_CHAR_RANDOM_STRING);

		// Upisujem 'Description' veci od 500 karaktera
		addNewPostWebPage.inputDescription(Utils.MORE_THAN_500_CHAR_RANDOM_STRING);

		// Kreiram objekat Web Element 'Save Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
		WebElement saveButton2 = addNewPostWebPage.getSaveButtonWebElement();

		// Skrolujem do 'Save' web elementa preko xPatha
		Utils.scrollToWebElement(driver, saveButton2);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();
		addNewPostWebPage.clickOnSave();
		// NAPOMENA: VELIKI ERROR! POTREBNO JE DVA PUTA KLIKNUTI NA SAVE!!
		// DA BI TEST IZVRSIO SVE USLOVE I DA BI SE POJAVILE SVE ERROR PORUKE!!

		// Proveravam da li sam ostao na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke
		assertTrue("Title error poruka - greska!",
				addNewPostWebPage.titleMoreThan255CharErrorMessage(Utils.moreThan255CharErrorMessage));
		assertTrue("Description error poruka - greska!",
				addNewPostWebPage.descriptionMoreThan500CharErrorMessage(Utils.moreThan500CharErrorMessage));
		assertTrue("TAG error poruka - greska!", addNewPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
		assertTrue("Content error poruka - greska!",
				addNewPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 255 karaktera,
	// upisujem 'Description' veci od 500 karaktera, klik na 'Cancel' dugme
	@Test
	public void tc17() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title' veci od 255 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_255_CHAR_RANDOM_STRING);

		// Upisujem 'Description' veci od 500 karaktera
		addNewPostWebPage.inputDescription(Utils.MORE_THAN_500_CHAR_RANDOM_STRING);

		// Kreiram objekat Web Element 'Cancel Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement cancelButton = addNewPostWebPage.getCancelButtonWebElement();

		// Skrolam do 'Cancel Button' web elementa preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel'
		addNewPostWebPage.clickOnCancel();
		addNewPostWebPage.clickOnCancel();
		// NAPOMENA: VELIKI ERROR! POTREBNO JE DVA PUTA KLIKNUTI NA CANCEL DA BI SE
		// VRATILI NA PRETHODNU STRANU!!!

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 255 karaktera,
	// upisujem 'Description' veci od 500 karaktera, klik na 'Save' dugme
	@Test
	public void tc18() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat web strane 'Add New Post'
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web strani
		assertTrue("Nisam na 'Add New Post' web strani!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title' veci od 255 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_255_CHAR_RANDOM_STRING);

		// Upisujem 'Description' veci od 500 karaktera
		addNewPostWebPage.inputDescription(Utils.MORE_THAN_500_CHAR_RANDOM_STRING);

		// Kreiram objekat Web Element 'Save Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement saveButton = addNewPostWebPage.getSaveButtonWebElement();

		// Skrolujem do 'Save' dugmeta preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();
		addNewPostWebPage.clickOnSave();

		// Error poruke
		assertTrue("Title error poruka - greska!",
				addNewPostWebPage.titleMoreThan255CharErrorMessage(Utils.moreThan255CharErrorMessage));
		assertTrue("Description error poruka - greska!",
				addNewPostWebPage.descriptionMoreThan500CharErrorMessage(Utils.moreThan500CharErrorMessage));
		assertTrue("TAG error poruka - greska!", addNewPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' manji od 20 karaktera,
	// upisujem 'Description' manji od 50 karaktera, check 'important' radio button,
	// check jedan 'TAG', klik na 'Cancel' dugme
	@Test
	public void tc19() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Ubacujem 'Title' manji od 20 karaktera.
		addNewPostWebPage.inputTitle(Utils.LESS_THAN_20_CHAR_RANDOM_STRING);

		// Ubacujem 'Description' manji od 50 karaktera.
		addNewPostWebPage.inputDescription(Utils.LESS_THAN_50_CHAR_RANDOM_STRING);

		// Obelezavam 'Important' radio button.
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Obelezavam jedan 'TAG'
		addNewPostWebPage.pickTAG(Utils.tagOne);

		// Kreiram objekat Web Element 'Cancel Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement cancelButton = addNewPostWebPage.getCancelButtonWebElement();

		// Skrolujem do 'Cancel' dugmeta preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post list' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' manji od 20 karaktera,
	// upisujem 'Description' manji od 50 karaktera, check 'important' radio button,
	// check jedan 'TAG', klik na 'Save' dugme.
	@Test
	public void tc20() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Ubacujem 'Title' manji od 20 karaktera.
		addNewPostWebPage.inputTitle(Utils.LESS_THAN_20_CHAR_RANDOM_STRING);

		// Ubacujem 'Description' manji od 50 karaktera.
		addNewPostWebPage.inputDescription(Utils.LESS_THAN_50_CHAR_RANDOM_STRING);

		// Obelezavam 'Important' radio button.
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Obelezavam jedan 'TAG'
		addNewPostWebPage.pickTAG(Utils.tagOne);

		// Kreiram objekat Web Element 'Save Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement saveButton = addNewPostWebPage.getSaveButtonWebElement();

		// Skrolujem do 'Save' dugmeta preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam ostao na istoj strani
		addNewPostWebPage.checkAddPostWebLocation();

		// Error poruke - provera
		assertTrue("Title error poruka - greska!",
				addNewPostWebPage.titleErrorMessageLessThan20Char(Utils.titleLessThan20CharErrorMessage));
		assertTrue("Description error poruka - greska!",
				addNewPostWebPage.descriptionLessThan50Error(Utils.descriptionErrorMessageLessThan50Char));
		assertTrue("Content error poruka - greska!",
				addNewPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check jedan TAG, klik na 'Cancel' dugme
	@Test
	public void tc21() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Obelezavam jedan 'TAG'
		addNewPostWebPage.pickTAG(Utils.tagOne);

		// Kreiram objekat Web Element 'Cancel Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement cancelButton = addNewPostWebPage.getCancelButtonWebElement();

		// Skrolujem do 'Cancel' dugmeta preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post list' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' validne duzine, veci od 20 karaktera,
	// upisujem 'Description' validne duzine, veci od 50 karaktera, check
	// 'important' radio button, check jedan TAG, klik na 'Save' dugme
	@Test
	public void tc22() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title' validne duzine, veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description' validne duzine, veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Biram 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram jedan TAG
		addNewPostWebPage.pickTAG(Utils.tagOne);

		// Kreiram objekat Web Element 'Save Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement saveButton = addNewPostWebPage.getSaveButtonWebElement();

		// Skrolujem do 'Save' dugmeta preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam ostao na istoj strani
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke - provera
		assertTrue("Content error poruka - greska!",
				addNewPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check jedan TAG,
	// ubacujem JPG photo, klik na 'Cancel' dugme
	@Test
	public void tc23() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Biram 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram jedan TAG
		addNewPostWebPage.pickTAG(Utils.tagOne);

		// Uzimam fotografiju sa lokalnog uredjaja
		addNewPostWebPage.uploadPhoto(Utils.jpgSmall);

		// Kreiram objekat Web Element 'Cancel Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement cancelButton = addNewPostWebPage.getCancelButtonWebElement();

		// Skrolujem do 'Cancel' dugmeta preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post list' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste,
	// upisujem'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check dva 'TAG'-a,
	// ubacujem fotografiju, klik na 'Cancel' dugme
	@Test
	public void tc24() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Biram 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uzimam fotografiju sa lokalnog uredjaja
		addNewPostWebPage.uploadPhoto(Utils.jpgSmall);

		// Kreiram objekat Web Element 'Cancel Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement cancelButton = addNewPostWebPage.getCancelButtonWebElement();

		// Skrolujem do 'Cancel' dugmeta preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post list' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check dva 'TAG-a',
	// ubacujem fotografiju, klik na 'Save' dugme
	@Test
	public void tc25() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Biram 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uzimam fotografiju sa lokalnog uredjaja
		addNewPostWebPage.uploadPhoto(Utils.jpgSmall);

		// Kreiram objekat Web Element 'Save Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement saveButton = addNewPostWebPage.getSaveButtonWebElement();

		// Skrolujem do 'Save' dugmeta preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam ostao na istoj strani
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke - provera
		assertTrue("Content error poruka - greska!",
				addNewPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check dva TAG-a,
	// ubacujem fotografiju, unosim 'Content' od 100 reci, klik na 'Cancel' dugme
	@Test
	public void tc26() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Desvription', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Biram 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uploadujem fotografiju sa lokala
		addNewPostWebPage.uploadPhoto(Utils.jpgSmall);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.iFrameLocator));

		// Unostim sadrzaj u 'Content' polje
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Cancel' dugmeta, preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, unosim samo 'Content' od 100 reci, klik na
	// 'Cancel' dugme
	@Test
	public void tc27() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unostim sadrzaj u 'Content' polje
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Kreiram objekat Web Element 'Cancel Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement cancelButton = addNewPostWebPage.getCancelButtonWebElement();

		// Skrolujem do 'Cancel' dugmeta preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, unosim samo 'Content' od 100 reci, klik na
	// 'Save' dugme
	@Test
	public void tc28() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unostim sadrzaj u 'Content' polje
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Kreiram objekat Web Element 'Save Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement saveButton = addNewPostWebPage.getSaveButtonWebElement();

		// Skrolujem do 'Save' web elementa preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam ostao na istoj strani
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke - provera
		assertTrue("Title error poruka - greska!",
				addNewPostWebPage.emptyTitleMessageError(Utils.emptyTitleErrorMessage));
		assertTrue("Description error poruka - greska!",
				addNewPostWebPage.emptyDescriptionError(Utils.emptyDescriptionErrorMessage));
		assertTrue("TAG error poruka - greska!", addNewPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, unosim
	// 'Content' od 100 reci, klik na 'Cancel' dugme
	@Test
	public void tc29() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unostim sadrzaj u 'Content' polje
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Kreiram objekat Web Element 'Cancel Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement cancelButton = addNewPostWebPage.getCancelButtonWebElement();

		// Skrolujem do 'Cancel' web elementa preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, unosim
	// 'Content' od 100 reci, klik na 'Save' dugme
	@Test
	public void tc30() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unostim sadrzaj u 'Content' polje
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Kreiram objekat Web Element 'Save Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement saveButton = addNewPostWebPage.getSaveButtonWebElement();

		// Skrolujem do 'Save' web elementa preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam ostao na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke - provera
		assertTrue("Title error poruka - greska!",
				addNewPostWebPage.emptyTitleMessageError(Utils.emptyTitleErrorMessage));
		assertTrue("Description error poruka - greska!",
				addNewPostWebPage.emptyDescriptionError(Utils.emptyDescriptionErrorMessage));
		assertTrue("TAG error poruka - greska!", addNewPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' manji od 20 karaktera, unosim 'Content' od 100 reci, klik na 'Cancel'
	// dugme
	@Test
	public void tc31() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', manji od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.LESS_THAN_20_CHAR_RANDOM_STRING);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unostim sadrzaj u 'Content' polje
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Kreiram objekat Web Element 'Cancel Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement cancelButton = addNewPostWebPage.getCancelButtonWebElement();

		// Skrolujem do 'Cancel' web elementa preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' manji od 20 karaktera, unosim 'Content', klik na 'Save' dugme
	@Test
	public void tc32() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', manji od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.LESS_THAN_20_CHAR_RANDOM_STRING);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unostim sadrzaj u 'Content' polje
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Kreiram objekat Web Element 'Save Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement saveButton = addNewPostWebPage.getSaveButtonWebElement();

		// Skrolujem do 'Save' web elementa preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam ostao na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke - provera
		assertTrue("Title error poruka - greska!",
				addNewPostWebPage.titleErrorMessageLessThan20Char(Utils.titleLessThan20CharErrorMessage));
		assertTrue("Description error poruka - greska!",
				addNewPostWebPage.emptyDescriptionError(Utils.emptyDescriptionErrorMessage));
		assertTrue("TAG error poruka - greska!", addNewPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera, unosim 'Content' od 100 reci, klik na 'Cancel'
	// dugme
	@Test
	public void tc33() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unostim sadrzaj u 'Content' polje
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Kreiram objekat Web Element 'Cancel Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement cancelButton = addNewPostWebPage.getCancelButtonWebElement();

		// Skrolujem do 'Cancel' web elementa preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera, unosim 'Content' od 100 reci, klik na 'Save'
	// dugme
	@Test
	public void tc34() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unostim sadrzaj u 'Content' polje
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Kreiram objekat Web Element 'Save Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement saveButton = addNewPostWebPage.getSaveButtonWebElement();

		// Skrolujem do 'Save' web elementa preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam ostao na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke - provera
		assertTrue("Description error poruka - greska!",
				addNewPostWebPage.emptyDescriptionError(Utils.emptyDescriptionErrorMessage));
		assertTrue("TAG error poruka - greska!", addNewPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera, upisujem 'Description' manji od 50 karaktera,
	// unosim 'Content' od 100 reci,
	// klik na 'Cancel' dugme
	@Test
	public void tc35() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', manji od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.LESS_THAN_50_CHAR_RANDOM_STRING);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unostim sadrzaj u 'Content' polje
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Kreiram objekat Web Element 'Cancel Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement cancelButton = addNewPostWebPage.getCancelButtonWebElement();

		// Skrolujem do 'Cancel' web elementa preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' manji od 50 karaktera, unosim 'Content' od 100 reci,
	// klik na 'Save' dugme
	@Test
	public void tc36() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', manji od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.LESS_THAN_50_CHAR_RANDOM_STRING);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Kreiram objekat Web Element 'Save Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement saveButton = addNewPostWebPage.getSaveButtonWebElement();

		// Skrolam do 'Save Button' web elementa preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveram da li sam ostao na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke - provera
		assertTrue("Description error poruka - greska!",
				addNewPostWebPage.descriptionLessThan50Error(Utils.descriptionErrorMessageLessThan50Char));
		assertTrue("TAG error poruka - greska!", addNewPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
		assertTrue("Description error poruka - greska!",
				addNewPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, unosim 'Content' od 100 reci,
	// klik na 'Cancel' dugme
	@Test
	public void tc37() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Kreiram objekat Web Element 'Cancel Button' i pozivam njegove parametre iz
		// druge klase preko gettera istog
//		WebElement cancelButton = addNewPostWebPage.getCancelButtonWebElement();

		// Skrolujem do 'Cancel' web elementa preko xPatha
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, unosim 'Content', klik na 'Save'
	// dugme
	@Test
	public void tc38() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolam do 'Save' dugmeta, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam dali sam ostao na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke - provera
		assertTrue("TAG error poruka - greska!", addNewPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check jedan TAG, unosim 'Content', klik na 'Cancel' dugme
	@Test
	public void tc39() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram jedan TAG
		addNewPostWebPage.pickTAG(Utils.tagOne);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unostim sadrzaj u 'Content' polje
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolam do xPath-a 'Cancel' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel'
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check jedan TAG, unosim 'Content' od 100 reci, klik na 'Save' dugme
	@Test
	public void tc40() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Biram 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram jedan TAG
		addNewPostWebPage.pickTAG(Utils.tagOne);

		// Skrolam do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unostim sadrzaj u 'Content' polje
//		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);
		addNewPostWebPage.insertContent("Stefan");

		// Skrolujem do 'Save' web elementa
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam se vratio na 'Post List' web stranu
		assertTrue("Nisam na 'Post List' web lokaciji", postListWebPage.checkPostListWebLocation());

		// Uneti 'New Post Title' u search input polje
		postListWebPage.inputTitleInSearch(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Proveravam da li je 'New Post' prisutan na 'Post List' web lokaciji
		assertTrue("New Post se ne nealazi na 'Post List' web lokaciji!",
				postListWebPage.isPostOnTheList(Utils.MORE_THAN_20_CHAR_RANDOM_STRING));

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check dva TAG-a,
	// ubacujem JPG fotografiju malog formata, unosim 'Content od 100 reci', klik na
	// 'Cancel' dugme
	@Test
	public void tc41() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' iz padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Biram 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uzimam fotografiju sa lokalnog uredjaja
		addNewPostWebPage.uploadPhoto(Utils.jpgSmall);

		// Skrolujem do 'Content' polja, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unostim sadrzaj u 'Content' polje
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Cancel' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel'
		addNewPostWebPage.clickOnCancel();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check dva TAG-a,
	// ubacujem JPG fotografiju malog formata, unosim 'Content od 100 reci', klik na
	// 'Save' dugme
	@Test
	public void tc42() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Iportant' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uploadujem fotografuju sa lokala
		addNewPostWebPage.uploadPhoto(Utils.jpgSmall);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Save' web elementa, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save'
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Uneti 'New Post Title' u search input polje
		postListWebPage.inputTitleInSearch(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Proveravam da li je 'New Post' prisutan na 'Post List' web lokaciji
		postListWebPage.isPostOnTheList(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check tri TAG-a,
	// ubacujem JPG fotografiju velikog formata, unosim 'Content' od 100 reci, klik
	// na 'Cancel' dugme
	@Test
	public void tc43() {
		// Kreiram objekat 'Post List' web strane
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram tri TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);
		addNewPostWebPage.pickTAG(Utils.tagThree);

		// Uploadujem fotografiju sa lokala
		addNewPostWebPage.uploadPhoto(Utils.jpgBig);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Cancel' dugmeta, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check tri TAG-a,
	// ubacujem JPG fotografiju velikog formata, unosim 'Content' od 100 reci, klik
	// na 'Save' dugme
	@Test
	public void tc44() {
		// Kreiram objekat 'Post List' web strane
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram tri TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);
		addNewPostWebPage.pickTAG(Utils.tagThree);

		// Uploadujem fotografiju sa lokala
		addNewPostWebPage.uploadPhoto(Utils.jpgBig);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Save' dugmeta, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Uneti 'New Post Title' u search input polje
		postListWebPage.inputTitleInSearch(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Proveravam da li je 'Post' uspesno dodat na listu na 'Post List' web strani
		postListWebPage.isPostOnTheList(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check jedan TAG,
	// ubacujem PNG fotografiju malog formata, unosim 'Content' od 100 reci, klik na
	// 'Cancel' dugme
	@Test
	public void tc45() {
		// Kreiram objekat 'Post List' web strane
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram jedan TAG
		addNewPostWebPage.pickTAG(Utils.tagOne);

		// Uploadujem fotografiju sa lokala
		addNewPostWebPage.uploadPhoto(Utils.pngSmall);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Cancel' dugmeta, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check jedan TAG,
	// ubacujem PNG fotografiju malog formata, unosim 'Content' od 100 reci, klik na
	// 'Save' dugme
	@Test
	public void tc46() {
		// Kreiram objekat 'Post List' web strane
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram jedan TAG
		addNewPostWebPage.pickTAG(Utils.tagOne);

		// Uploadujem fotografiju sa lokala
		addNewPostWebPage.uploadPhoto(Utils.pngSmall);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Cancel' dugmeta, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Uneti 'New Post Title' u search input polje
		postListWebPage.inputTitleInSearch(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Proveravam da li je 'Post' uspesno dodat na listu na 'Post List' web strani
		postListWebPage.isPostOnTheList(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check dva TAG-a,
	// ubacujem PNG fotografiju velikog formata, unosim 'Content' od 100 reci, klik
	// na 'Cancel' dugme
	@Test
	public void tc47() {
		// Kreiram objekat 'Post List' web strane
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uploadujem fotografiju sa lokala
		addNewPostWebPage.uploadPhoto(Utils.pngBigVertical);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Cancel' dugmeta, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check dva TAG-a,
	// ubacujem PNG fotografiju velikog formata, unosim 'Content' od 100 reci, klik
	// na 'Save' dugme
	@Test
	public void tc48() {
		// Kreiram objekat 'Post List' web strane
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uploadujem fotografiju sa lokala
		addNewPostWebPage.uploadPhoto(Utils.pngBigVertical);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Save' dugmeta, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Uneti 'New Post Title' u search input polje
		postListWebPage.inputTitleInSearch(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Proveravam da li je 'Post' uspesno dodat na listu na 'Post List' web strani
		postListWebPage.isPostOnTheList(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check dva TAG-a,
	// ubacujem GIF fotografiju malog formata, unosim 'Content' od 100 reci, klik na
	// 'Cancel' dugme
	@Test
	public void tc49() {
		// Kreiram objekat 'Post List' web strane
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uploadujem fotografiju sa lokala
		addNewPostWebPage.uploadPhoto(Utils.gifSmall);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Cancel' dugmeta, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check dva TAG-a,
	// ubacujem GIF fotografiju malog formata, unosim 'Content' od 100 reci, klik na
	// 'Save' dugme
	@Test
	public void tc50() {
		// Kreiram objekat 'Post List' web strane
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uploadujem fotografiju sa lokala
		addNewPostWebPage.uploadPhoto(Utils.gifSmall);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Save' dugmeta, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Uneti 'New Post Title' u search input polje
		postListWebPage.inputTitleInSearch(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Proveravam da li je 'Post' uspesno dodat na listu na 'Post List' web strani
		postListWebPage.isPostOnTheList(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check dva TAG-a,
	// ubacujem GIF fotografiju velikog formata, unosim 'Content' od 100 reci, klik
	// na 'Cancel' dugme
	@Test
	public void tc51() {
		// Kreiram objekat 'Post List' web strane
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uploadujem fotografiju sa lokala
		addNewPostWebPage.uploadPhoto(Utils.gifBig);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Cancel' dugmeta, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.cancelButton));

		// Klik na 'Cancel' dugme
		addNewPostWebPage.clickOnCancel();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// 'Title' veci od 20 karaktera,
	// upisujem 'Description' veci od 50 karaktera, check 'important' radio button,
	// check dva TAG-a,
	// ubacujem GIF fotografiju velikog formata, unosim 'Content' od 100 reci, klik
	// na 'Save' dugme
	@Test
	public void tc52() {
		// Kreiram objekat 'Post List' web strane
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem validan 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.aWonderfulSerenity);

		// Upisujem validan 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.hardcodedDescription);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uploadujem fotografiju sa lokala
		addNewPostWebPage.uploadPhoto(Utils.gifBig);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.hardcodecContent);

		// Skrolujem do 'Save' dugmeta, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Uneti 'New Post Title' u search input polje
		postListWebPage.inputTitleInSearch(Utils.aWonderfulSerenity);

		// Proveravam da li je 'Post' uspesno dodat na listu na 'Post List' web strani
		postListWebPage.isPostOnTheList(Utils.aWonderfulSerenity);
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa liste, upisujem
	// VALIDAN 'Title' (veci od 20 karaktera),
	// upisujem VALIDAN 'Description' (veci od 50 karaktera), check 'important'
	// radio button, check dva TAG-a,
	// ubacujem 30MB tesku JPG fotografiju, unosim 'Content' od 100 reci, klik na
	// 'Save' dugme
	@Test
	public void tc53() {
		// Kreiram objekat 'Post List' web strane
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Upisujem 'Title', veci od 20 karaktera
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Upisujem 'Description', veci od 50 karaktera
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva TAG-a
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uploadujem fotografiju sa lokala
		addNewPostWebPage.uploadPhoto(Utils.jpg30MB);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content' od 100 reci
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skrolujem do 'Save' dugmeta, preko xPath-a
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Uneti 'New Post Title' u search input polje
		postListWebPage.inputTitleInSearch(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Proveravam da li je 'Post' uspesno dodat na listu na 'Post List' web strani
		postListWebPage.isPostOnTheList(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);
	}

	// Klik na 'Add New Post' dugme, uploadujem samo 'invalid' photo format, klik na
	// 'Save' dugme
	@Test
	public void tc54() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Uploadujem AVIF photo file
		addNewPostWebPage.uploadPhoto(Utils.photoAVIF);

		// Skroluje do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveram da li sam ostao na 'Add New Post' web lokaciji!
		assertTrue("Nisam na 'Add New Post web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke
		assertTrue("Title error poruka - greska!",
				addNewPostWebPage.emptyTitleMessageError(Utils.emptyTitleErrorMessage));
		assertTrue("Description error poruka - greska!",
				addNewPostWebPage.emptyDescriptionError(Utils.emptyDescriptionErrorMessage));
		assertTrue("TAG error poruka - greska!", addNewPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
		assertTrue("Photo error poruka - greska!",
				addNewPostWebPage.invalidPhotoFormatError(Utils.invalidPhotoFormatErrorMessage));
		assertTrue("Content error poruka - greska!",
				addNewPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

	// Klik na 'Add New Post' dugme, biram jednu 'Category' sa padajuce liste,
	// uploadujem 'invalid' photo format, klik na 'Save' dugme
	@Test
	public void tc55() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Uploadujem AVIF photo file
		addNewPostWebPage.uploadPhoto(Utils.photoAVIF);

		// Skroluje do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveram da li sam ostao na 'Add New Post' web lokaciji!
		assertTrue("Nisam na 'Add New Post web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke
		assertTrue("Title error poruka - greska!",
				addNewPostWebPage.emptyTitleMessageError(Utils.emptyTitleErrorMessage));
		assertTrue("Description error poruka - greska!",
				addNewPostWebPage.emptyDescriptionError(Utils.emptyDescriptionErrorMessage));
		assertTrue("TAG error poruka - greska!", addNewPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
		assertTrue("Photo error poruka - greska!",
				addNewPostWebPage.invalidPhotoFormatError(Utils.invalidPhotoFormatErrorMessage));
		assertTrue("Content error poruka - greska!",
				addNewPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

	// Klik na 'Add New Post' dugme, biram jednu 'Category' sa padajuce liste,
	// upisujem 'Title' validne duzine, uploadujem 'invalid' photo format, klik na
	// 'Save' dugme
	@Test
	public void tc56() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Unosim validan 'Title'
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Uploadujem AVIF photo file
		addNewPostWebPage.uploadPhoto(Utils.photoAVIF);

		// Skroluje do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveram da li sam ostao na 'Add New Post' web lokaciji!
		assertTrue("Nisam na 'Add New Post web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke);
		assertTrue("Description error poruka - greska!",
				addNewPostWebPage.emptyDescriptionError(Utils.emptyDescriptionErrorMessage));
		assertTrue("TAG error poruka - greska!", addNewPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
		assertTrue("Photo error poruka - greska!",
				addNewPostWebPage.invalidPhotoFormatError(Utils.invalidPhotoFormatErrorMessage));
		assertTrue("Content error poruka - greska!",
				addNewPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

	// Klik na 'Add New Post' dugme, biram jednu 'Category' sa padajuce liste,
	// unosim validan 'Title', unosim validan 'Description', uploadujem 'invalid'
	// photo format, klik na 'Save' dugme
	@Test
	public void tc57() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Unosim validan 'Title'
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Unosim validan 'Description'
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Uploadujem AVIF photo file
		addNewPostWebPage.uploadPhoto(Utils.photoAVIF);

		// Skroluje do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveram da li sam ostao na 'Add New Post' web lokaciji!
		assertTrue("Nisam na 'Add New Post web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke
		assertTrue("TAG error poruka - greska!", addNewPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
		assertTrue("Photo error poruka - greska!",
				addNewPostWebPage.invalidPhotoFormatError(Utils.invalidPhotoFormatErrorMessage));
		assertTrue("Content error poruka - greska!",
				addNewPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

	// Klik na 'Add New Post' dugme, biram jednu 'Category' sa padajuce liste,
	// unosim validan 'Title',
	// unosim validan 'description', check 'Important' radio button, uploadujem
	// 'invalid' photo format, klik na
	// 'Save' dugme
	@Test
	public void tc58() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu kategoriju sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Unostim validan 'Title'
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Unosim validan 'Description'
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Uploadujem AVIF photo file
		addNewPostWebPage.uploadPhoto(Utils.photoAVIF);

		// Skroluje do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveram da li sam ostao na 'Add New Post' web lokaciji!
		assertTrue("Nisam na 'Add New Post web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke
		assertTrue("TAG error poruka - greska!", addNewPostWebPage.emptyTagError(Utils.emptyTagSelectedErrorMessage));
		assertTrue("Photo error poruka - greska!",
				addNewPostWebPage.invalidPhotoFormatError(Utils.invalidPhotoFormatErrorMessage));
		assertTrue("Content error poruka - greska!",
				addNewPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

	// Klik na 'Add New Post' dugme, biram jednu 'Category' sa padajuce liste,
	// unosim VALIDAN 'Title', unosim VALIDAN 'description', biram 'Important' radio
	// button, biram jedan TAG, uploadujem 'invalid' photo format, klik na 'Save'
	// dugme
	@Test
	public void tc59() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Unosim validan 'Title'
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Unosim validan 'Description'
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Obelezavam jedan 'TAG'
		addNewPostWebPage.pickTAG(Utils.tagOne);

		// Uploadujem AVIF photo file
		addNewPostWebPage.uploadPhoto(Utils.photoAVIF);

		// Skroluje do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveram da li sam ostao na 'Add New Post' web lokaciji!
		assertTrue("Nisam na 'Add New Post web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke
		assertTrue("Photo error poruka - greska!",
				addNewPostWebPage.invalidPhotoFormatError(Utils.invalidPhotoFormatErrorMessage));
		assertTrue("Content error poruka - greska!",
				addNewPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

	// Klik na 'Add New Post' dugme, biram jednu 'Category' sa padajuce liste,
	// unosim validan 'Title', unosim validan 'description', obelezavam 'Important'
	// radio button, biram dva TAG-a, uploadujem 'invalid' photo format, klik na
	// 'Save' dugme
	@Test
	public void tc60() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Unosim validan 'Title'
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Unosim validan 'Description'
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva 'TAG-a'
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uploadujem AVIF photo file
		addNewPostWebPage.uploadPhoto(Utils.photoAVIF);

		// Skroluje do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveram da li sam ostao na 'Add New Post' web lokaciji!
		assertTrue("Nisam na 'Add New Post web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke
		assertTrue("Photo error poruka - greska!",
				addNewPostWebPage.invalidPhotoFormatError(Utils.invalidPhotoFormatErrorMessage));
		assertTrue("Content error poruka - greska!",
				addNewPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

	// Klik na 'Add New Post' dugme, biram jednu 'Category' sa padajuce liste,
	// unosim validan 'Title', unosim validan 'description',
	// obelezavam 'Important' radio button, biram jedan 'TAG', uploadujem 'invalid'
	// photo format, unosim 'Content', klik na 'Save' dugme
	@Test
	public void tc61() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Unosim validan 'Title'
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Unosim validan 'Description'
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram jedan 'TAG'
		addNewPostWebPage.pickTAG(Utils.tagOne);

		// Uploadujem AVIF photo file
		addNewPostWebPage.uploadPhoto(Utils.photoAVIF);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content'
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_100_WORDS);

		// Skroluje do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveram da li sam ostao na 'Add New Post' web lokaciji!
		assertTrue("Nisam na 'Add New Post web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Error poruke
		assertTrue("Photo error poruka - greska!",
				addNewPostWebPage.invalidPhotoFormatError(Utils.invalidPhotoFormatErrorMessage));
	}

	// Klik na 'Add new post' dugme, biram jednu 'Category' sa padajuce liste,
	// upisujem VALIDAN 'Title' veci od 20 karaktera, upisujem VALIDAN 'Description'
	// veci od 50 karaktera, check 'important' radio button, check dva TAG-a,
	// ubacujem JPG fotografiju veceg formata,
	// unosim VALIDAN 'Content' od 100 reci, klik na 'Save' dugme
	@Test
	public void tc62() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Unosim validan 'Title'
		addNewPostWebPage.inputTitle(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Unosim validan 'Description'
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram dva 'TAG-a'
		addNewPostWebPage.pickTAG(Utils.tagOne);
		addNewPostWebPage.pickTAG(Utils.tagTwo);

		// Uploadujem validnu JPG fotografiju
		addNewPostWebPage.uploadPhoto(Utils.jpgBig);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content'
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, Utils.CONTENT_1000_WORDS);

		// Skroluje do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveram da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);

		// Proveravam da li je post upisan na 'Post Listu'
		postListWebPage.isPostOnTheList(Utils.MORE_THAN_20_CHAR_RANDOM_STRING);
	}

	// Unosim u 'Content' uzastopne prazne Stringove
	@Test
	public void tc63() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Klik na 'Add New Post' dugme
		postListWebPage.clickOnAddNewPostButton();

		// Kreiram objekat 'Add New Post' web lokacije
		AddNewPostWebPage addNewPostWebPage = new AddNewPostWebPage(driver);

		// Proveravam da li sam na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Add New Post' web lokaciji!", addNewPostWebPage.checkAddPostWebLocation());

		// Biram jednu 'Category' sa padajuce liste
		addNewPostWebPage.pickCategory(Utils.categoryName);

		// Unosim validan 'Title'
		addNewPostWebPage.inputTitle("PRAZAN STRING CONTENT - TEST");

		// Unosim validan 'Description'
		addNewPostWebPage.inputDescription(Utils.DESCRIPTION_MORE_THAN_50);

		// Obelezavam 'Important' radio button
		addNewPostWebPage.checkIMPORTANTRadioButton();

		// Biram jedan 'TAG'
		addNewPostWebPage.pickTAG(Utils.tagOne);

		// Uploadujem validnu JPG fotografiju
		addNewPostWebPage.uploadPhoto(Utils.jpgBig);

		// Skrolujem do 'Content' polja
		Utils.scrollTo(driver, By.xpath(Utils.contentLabel));

		// Unosim 'Content'
		addNewPostWebPage.inputContent(Utils.iFrameLocator, Utils.contentFieldLocator, "      ");

		// Skroluje do 'Save' dugmeta
		Utils.scrollTo(driver, By.xpath(Utils.saveButton));

		// Klik na 'Save' dugme
		addNewPostWebPage.clickOnSave();

		// Proveram da li sam ostao na 'Add New Post' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Error poruke
		assertTrue("Content error poruka - greska!",
				addNewPostWebPage.emptyContentError(Utils.emptyContentErrorMessage));
	}

}
