package cubes.test;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cubes.helper.MyWebDriver;
import cubes.loginPage.LoginPage;
import cubes.pages.categories.CategoryAddPage;
import cubes.pages.categories.CategoryListPage;

public class CategoryTest {
	
	// fields
	private static WebDriver driver;
	private String categoryName = "VladimirK";
	private String editedCategoryNameTest = "Vladimir TEST";
	private String editedCategoryName = "Vladimir QA";
	private String descriptionMoreThan500 = "The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds jog, flick quartz, vex nymphs. Waltz, bad nymph, for quick jigs vex! Fox nymphs grab quick-jived waltz. Brick quiz whangs jumpy veldt fox. Bright vixens jump; dozy fowl quack. Quick wafting zephyrs vex bold Jim. Quick zephyrs blow, vexing daft Jim. Sex-charged fop blew my junk TV quiz. How quickly daft jumping zebras vex. Two driven jocks help fax my big quiz. Quick, Baz, get my woven flax jodhpurs! \"Now fax quiz Jack!\" my brave ghost pled. Five quacking zephyrs jolt my wax bed. Flummoxed by job, kvetching W. zaps Iraq. Cozy sphinx waves quart jug of bad milk. A very bad quack might jinx zippy fowls. Few quips galvanized the mock jury box. Quick brown dogs jump over the lazy fox. The jay, pig, fox, zebra, and my wolves quack! Blowzy red vixens fight for a quick jump. Joaquin Phoenix was gazed by MTV for luck. A wizard’s job is to vex chumps quickly in fog. Watch \"Jeopardy!\", Alex Trebek's fun TV quiz game. Woven silk pyjamas exchanged for blue quartz. Brawny gods just flocked up to quiz and vex him. Adjusting quiver and bow, Zompyc[1] killed the fox. My faxed joke won a pager in the cable TV quiz show. Amazingly few discotheques provide jukeboxes. My girl wove six dozen plaid jackets before she quit. Six big devils from Japan quickly forgot how to waltz. Big July earthquakes confound zany experimental vow. Foxy parsons quiz and cajole the lovably dim wiki-girl. Have a pick: twenty six letters - no forcing a jumbled quiz! Crazy Fredericka bought many very exquisite opal jewels. Sixty zippers were quickly picked from the woven jute bag. A quick movement of the enemy will jeopardize six gunboats. All questions asked by five watch experts amazed the judge. Jack quietly moved up front and seized the big ball of wax.The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds jog, flick quartz, vex nymphs. Waltz, bad nymph, for quick jigs vex! Fox nymphs grab quick-jived waltz. Brick quiz whangs jumpy veldt fox. Bright vixens jump; dozy fowl quack. Quick wafting zephyrs vex bold Jim. Quick zephyrs blow, vexing daft Jim. Sex-charged fop blew my junk TV quiz. How quickly daft jumping zebras vex. Two driven jocks help fax my big quiz. Quick, Baz, get my woven flax jodhpurs! \"Now fax quiz Jack!\" my brave ghost pled. Five quacking zephyrs jolt my wax bed. Flummoxed by job, kvetching W. zaps Iraq. Cozy sphinx waves quart jug of bad milk. A very bad quack might jinx zippy fowls. Few quips galvanized the mock jury box. Quick brown dogs jump over the lazy fox. The jay, pig, fox, zebra, and my wolves quack! Blowzy red vixens fight for a quick jump. Joaquin Phoenix was gazed by MTV for luck. A wizard’s job is to vex chumps quickly in fog. Watch \"Jeopardy!\", Alex Trebek's fun TV quiz game. Woven silk pyjamas exchanged for blue quartz. Brawny gods just flocked up to quiz and vex him. Adjusting quiver and bow, Zompyc[1] killed the fox. My faxed joke won a";
	private String descriptionMinus50 = "Lorem ipsum dolor sit amet";
	private String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eget vehicula arcu. Vestibulum consequat dictum ultrices. Aliquam erat volutpat.";
	private String editedDescriptionCategoryTest = "The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds jog, flick quartz, vex nymphs. Waltz, bad nymph, for quick jigs vex! Fox nymphs grab quick-jived waltz. Brick quiz whangs jumpy veldt fox. Bright vixens jump; dozy fowl quack.";
	private String editedDescription = "One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections.";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = MyWebDriver.getDriver("firefox");

		LoginPage loginPage = new LoginPage(driver);
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

	// 'Add New Category', send empty fields, click Cancel
	@Test
	public void TC01() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddNewCategoryButton();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category Web Lokaciji!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName("");
		categoryAddPage.inputDescription("");
		categoryAddPage.clickOnCancel();

		assertTrue("Nisam na List Category Web Lokaciji!", categoryListPage.checkListPageLocation());
	}

	// 'Add New Category', send empty fields, click Save
	@Test
	public void TC02() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddNewCategoryButton();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category Web Lokaciji!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName("");
		categoryAddPage.inputDescription("");
		categoryAddPage.clickOnSave();

		assertTrue("Missing Name Message Error", categoryAddPage.emptyCategoryNameErrorMessage("This field is required."));
		assertTrue("Missing Description Message Error", categoryAddPage.emptyCategoryDescriptionErrorMessage("This field is required."));
		assertTrue(categoryName, categoryAddPage.checkAddPageLocation());
	}

	// 'Add New Category', send Category name, leave empty Description, click on
	// Cancel
	@Test
	public void TC03() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddNewCategoryButton();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category Web Lokaciji!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(categoryName);
		categoryAddPage.inputDescription("");
		categoryAddPage.clickOnCancel();

		assertTrue("Nisam na Category List Page", categoryListPage.checkListPageLocation());
	}

	// 'Add New Category', send Category name, leave empty Description, click on
	// Save
	@Test
	public void TC04() {

		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddNewCategoryButton();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category Web Lokaciji!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(categoryName);
		categoryAddPage.inputDescription("");
		categoryAddPage.clickOnSave();

		assertTrue("Missing Description Message Error!",
				categoryAddPage.emptyCategoryDescriptionErrorMessage("This field is required."));
		assertTrue("Nisam na Add Category Web Lokaciji!", categoryAddPage.checkAddPageLocation());
	}

	// 'Add New Category', leave Category name empty, send Description, click on
	// Cancel
	@Test
	public void TC05() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddNewCategoryButton();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName("");
		categoryAddPage.inputDescription(description);
		categoryAddPage.clickOnCancel();

		assertTrue("Nisam na Category List web lokaciji!", categoryListPage.checkListPageLocation());

	}

	// 'Add New Category', leave Category name empty, send Description, click on
	// Save
	@Test
	public void TC06() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddNewCategoryButton();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName("");
		categoryAddPage.inputDescription(description);
		categoryAddPage.clickOnSave();

		assertTrue("Nisam na Add Category web lokaciji!", categoryAddPage.checkAddPageLocation());
		assertTrue("Nedostaje error poruka!", categoryAddPage.emptyCategoryNameErrorMessage("This field is required."));
	}

	// 'Add New Category', send Category name, send Description less than 50
	// characters, click on Cancel
	@Test
	public void TC07() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddNewCategoryButton();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(categoryName);
		categoryAddPage.inputDescription(descriptionMinus50);
		categoryAddPage.clickOnCancel();

		assertTrue("Nisam na List Category web strani!", categoryListPage.checkListPageLocation());

	}

	// 'Add New Category', send Category name, send Description less than 50
	// characters, click on Save
	@Test
	public void TC08() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddNewCategoryButton();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(categoryName);
		categoryAddPage.inputDescription(descriptionMinus50);
		categoryAddPage.clickOnSave();

		assertTrue("Missing: less than 50 characters, error!",
				categoryAddPage.lessThan50CharactersError("The description must be at least 50 characters."));
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());
	}

	// 'Add New Category', send Category name, send Description more than 50
	// characters, click on Cancel
	@Test
	public void TC09() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddNewCategoryButton();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(categoryName);
		categoryAddPage.inputDescription(description);
		categoryAddPage.clickOnCancel();

		assertTrue("Nisam na List Category web stranici!", categoryListPage.checkListPageLocation());
	}

	// 'Add New Category', send Category name, send Description more than 500
	// characters, click on Save
	@Test
	public void TC10() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddNewCategoryButton();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web stranici!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(categoryName);
		categoryAddPage.inputDescription(descriptionMoreThan500);
		categoryAddPage.clickOnSave();

		assertTrue("Missing: less than 50 characters, error!",
				categoryAddPage.moreThan500CharectersError("Please enter no more than 500 characters."));
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());
	}

	// 'Add New Category', send Category name, send Description more than 50 but less than 500
	// characters, click on Save
	@Test
	public void TC11() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddNewCategoryButton();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web stranici!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(categoryName);
		categoryAddPage.inputDescription(description);
		categoryAddPage.clickOnSave();

		assertTrue("Nisam na List Category web lokaciji!", categoryListPage.checkListPageLocation());
		assertTrue("Kategorija se ne pojavljuje na listi!", categoryListPage.isNewCategoryOnTheList(categoryName));
		assertTrue("Description se ne pojavljuje na listi!", categoryListPage.isDescriptionOnTheList(categoryName, description));
	}

	// Go to 'Edit category', don't change Name and Description, and click Cancel
	@Test
	public void TC12() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());

		categoryListPage.scrollTo(categoryName);
		categoryListPage.clickOnEditCategoryIcon(categoryName);

		assertTrue("Nisam na EDIT web strani!", categoryListPage.checkEditCategoryLocation());
		categoryListPage.clickOnCancelEditCategory();
		assertTrue("Nisam na Category list web stranici!", categoryListPage.checkListPageLocation());
	}

	// Go to 'Edit category', clear Name and Description input fields, and click Cancel
	@Test
	public void TC13() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());

		categoryListPage.scrollTo(categoryName);
		categoryListPage.clickOnEditCategoryIcon(categoryName);
		assertTrue("Nisam na EDIT web strani!", categoryListPage.checkEditCategoryLocation());

		categoryListPage.clearEditNameInputField();
		categoryListPage.clearEditDescriptionInputField();
		categoryListPage.clickOnCancelEditCategory();

		assertTrue("Nisam na Category list web stranici!", categoryListPage.checkListPageLocation());
	}

	// Go to 'Edit category', clear Name input fields, and click Save
	public void TC14() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());

		categoryListPage.scrollTo(categoryName);
		categoryListPage.clickOnEditCategoryIcon(categoryName);
		assertTrue("Nisam na EDIT web strani!", categoryListPage.checkEditCategoryLocation());

		categoryListPage.clearEditNameInputField();
		categoryListPage.clickOnSaveButtonEditCategory();

		assertTrue("Ocekivana greska se ne ispisuje!",
				categoryListPage.emptyNameErrorEditCategories("This field is required."));
		assertTrue("Nisam na EDIT web strani!", categoryListPage.checkEditCategoryLocation());
	}

	// Go to 'Edit category', clear Description input field, and click Save
	@Test
	public void TC15() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());

		categoryListPage.scrollTo(categoryName);
		categoryListPage.clickOnEditCategoryIcon(categoryName);
		assertTrue("Nisam na EDIT web strani!", categoryListPage.checkEditCategoryLocation());

		categoryListPage.clearEditDescriptionInputField();

		categoryListPage.clickOnSaveButtonEditCategory();

		assertTrue("Ocekivana greska se ne ispisuje!",
				categoryListPage.emptyDescriptionEditFieldErrorMessage("This field is required."));
		assertTrue("Nisam na EDIT web strani!", categoryListPage.checkEditCategoryLocation());
	}

	// Go to 'Edit category', clear Name&Description input fields, and click Save
	@Test
	public void TC16() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());

		categoryListPage.scrollTo(categoryName);
		categoryListPage.clickOnEditCategoryIcon(categoryName);
		assertTrue("Nisam na EDIT web strani!", categoryListPage.checkEditCategoryLocation());

		categoryListPage.clearEditNameInputField();
		categoryListPage.clearEditDescriptionInputField();

		categoryListPage.clickOnSaveButtonEditCategory();

		assertTrue("Ocekivana greska se ne ispisuje!",
				categoryListPage.emptyNameErrorEditCategories("This field is required."));
		assertTrue("Ocekivana greska se ne ispisuje!",
				categoryListPage.emptyDescriptionEditFieldErrorMessage("This field is required."));

		assertTrue("Nisam na EDIT web strani!", categoryListPage.checkEditCategoryLocation());
	}

	// Go to 'Edit category', Edit only Description input fields, and click Save
	@Test
	public void TC17() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());

		categoryListPage.scrollTo(categoryName);
		categoryListPage.clickOnEditCategoryIcon(categoryName);
		assertTrue("Nisam na EDIT web strani!", categoryListPage.checkEditCategoryLocation());

		categoryListPage.clearEditDescriptionInputField();
		categoryListPage.editDescriptionInputField(editedDescriptionCategoryTest);

		categoryListPage.clickOnSaveButtonEditCategory();

		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		assertTrue("Ime editovane kategorije nije na listi",
				categoryListPage.isEditedCategoryNameOnTheList(categoryName));
	}

	// Go to 'Edit category', Edit only Name input fields, and click Save
	@Test
	public void TC18() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());

		categoryListPage.scrollTo(categoryName);
		categoryListPage.clickOnEditCategoryIcon(categoryName);
		assertTrue("Nisam na EDIT web strani!", categoryListPage.checkEditCategoryLocation());

		categoryListPage.clearEditNameInputField();
		categoryListPage.editNameInputField(editedCategoryNameTest);

		categoryListPage.clickOnSaveButtonEditCategory();

		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		assertTrue("Ime editovane kategorije nije na listi",
				categoryListPage.isEditedCategoryNameOnTheList(editedCategoryNameTest));
	}

	// Go to 'Edit category', Edit Name&Description input fields, and click Save
	@Test
	public void TC19() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());

		categoryListPage.scrollTo(editedCategoryNameTest);
		categoryListPage.clickOnEditCategoryIcon(editedCategoryNameTest);
		assertTrue("Nisam na EDIT web strani!", categoryListPage.checkEditCategoryLocation());

		categoryListPage.clearEditNameInputField();
		categoryListPage.editNameInputField(editedCategoryName);

		categoryListPage.clearEditDescriptionInputField();
		categoryListPage.editDescriptionInputField(editedDescription);

		categoryListPage.clickOnSaveButtonEditCategory();

		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		assertTrue("Ime editovane kategorije nije na listi",
				categoryListPage.isEditedCategoryNameOnTheList(editedCategoryName));
	}

	// click on delete icon, click cancel
	@Test
	public void TC20() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkEditCategoryLocation());

		categoryListPage.scrollTo(editedCategoryName);
		categoryListPage.clickOnDeleteCategoryIcon(editedCategoryName);
		categoryListPage.clickOnCancelDeleteCategoryButton();

		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		assertTrue("Ime kategorije nije na listi", categoryListPage.isEditedCategoryNameOnTheList(editedCategoryName));
	}

	// click on delete icon, click X web element
	@Test
	public void TC21() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkEditCategoryLocation());

		categoryListPage.scrollTo(editedCategoryName);
		categoryListPage.clickOnDeleteCategoryIcon(editedCategoryName);
		categoryListPage.clickOnXWebElement();

		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		assertTrue("Ime kategorije nije na listi", categoryListPage.isEditedCategoryNameOnTheList(editedCategoryName));
	}

	// delete on delete icon, click delete
	@Test
	public void TC22() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());

		categoryListPage.scrollTo(editedCategoryName);
		categoryListPage.clickOnDeleteCategoryIcon(editedCategoryName);
		categoryListPage.clickOnDeleteCategoryButton();

		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		assertTrue("Kategorije NIJE obrisana! Nalazi se na listi!",
				!categoryListPage.categoryIsNotOnTheList(editedCategoryName));
	}

	// 'Add New Category', send empty fields, click Cancel
	@Test
	public void TC23() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddPostCategoryFromMenu();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category Web Lokaciji!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName("");
		categoryAddPage.inputDescription("");
		categoryAddPage.clickOnCancel();

		assertTrue("Nisam na List Category Web Lokaciji!", categoryListPage.checkListPageLocation());
	}

	// 'Add New Category', send empty fields, click Save
	@Test
	public void TC24() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddPostCategoryFromMenu();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category Web Lokaciji!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName("");
		categoryAddPage.inputDescription("");
		categoryAddPage.clickOnSave();

		assertTrue("Missing Name Message Error",
				categoryAddPage.emptyCategoryNameErrorMessage("This field is required."));
		assertTrue("Missing Description Message Error",
				categoryAddPage.emptyCategoryDescriptionErrorMessage("This field is required."));

		assertTrue(categoryName, categoryAddPage.checkAddPageLocation());
	}

	// 'Add New Category', send Category name, leave empty Description, click on
	// Cancel
	@Test
	public void TC25() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddPostCategoryFromMenu();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category Web Lokaciji!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(categoryName);
		categoryAddPage.inputDescription("");
		categoryAddPage.clickOnCancel();

		assertTrue("Nisam na Category List Page", categoryListPage.checkListPageLocation());
	}

	// 'Add New Category', send Category name, leave empty Description, click on
	// Save
	@Test
	public void TC26() {

		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddPostCategoryFromMenu();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category Web Lokaciji!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(editedCategoryName);
		categoryAddPage.inputDescription("");
		categoryAddPage.clickOnSave();

		assertTrue("Missing Description Message Error!",
				categoryAddPage.emptyCategoryDescriptionErrorMessage("This field is required."));
		assertTrue("Nisam na Add Category Web Lokaciji!", categoryAddPage.checkAddPageLocation());
	}

	// 'Add New Category', leave Category name empty, send Description, click on
	// Cancel
	@Test
	public void TC27() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddPostCategoryFromMenu();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName("");
		categoryAddPage.inputDescription(editedDescription);
		categoryAddPage.clickOnCancel();

		assertTrue("Nisam na Category List web lokaciji!", categoryListPage.checkListPageLocation());

	}

	// 'Add New Category', leave Category name empty, send Description, click on
	// Save
	@Test
	public void TC28() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddPostCategoryFromMenu();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName("");
		categoryAddPage.inputDescription(editedDescription);
		categoryAddPage.clickOnSave();

		assertTrue("Nisam na Add Category web lokaciji!", categoryAddPage.checkAddPageLocation());
		assertTrue("Nedostaje error poruka!", categoryAddPage.emptyCategoryNameErrorMessage("This field is required."));
	}

	// 'Add New Category', send Category name, send Description less than 50
	// characters, click on Cancel
	@Test
	public void TC29() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddPostCategoryFromMenu();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(editedCategoryName);
		categoryAddPage.inputDescription(descriptionMinus50);
		categoryAddPage.clickOnCancel();

		assertTrue("Nisam na List Category web strani!", categoryListPage.checkListPageLocation());

	}

	// 'Add New Category', send Category name, send Description less than 50
	// characters, click on Save
	@Test
	public void TC30() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddPostCategoryFromMenu();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(editedCategoryName);
		categoryAddPage.inputDescription(descriptionMinus50);
		categoryAddPage.clickOnSave();

		assertTrue("Missing: less than 50 characters, error!",
				categoryAddPage.lessThan50CharactersError("The description must be at least 50 characters."));
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());
	}

	// 'Add New Category', send Category name, send Description more than 50
	// characters, click on Cancel
	@Test
	public void TC31() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddPostCategoryFromMenu();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(editedCategoryName);
		categoryAddPage.inputDescription(editedDescription);
		categoryAddPage.clickOnCancel();

		assertTrue("Nisam na List Category web stranici!", categoryListPage.checkListPageLocation());
	}

	// 'Add New Category', send Category name, send Description more than 500 characters, click on Save
	@Test
	public void TC32() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddPostCategoryFromMenu();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web stranici!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(editedCategoryName);
		categoryAddPage.inputDescription(descriptionMoreThan500);
		categoryAddPage.clickOnSave();

		assertTrue("Missing: less than 50 characters, error!",
				categoryAddPage.moreThan500CharectersError("Please enter no more than 500 characters."));
		assertTrue("Nisam na Add Category web strani!", categoryAddPage.checkAddPageLocation());
	}

	// 'Add New Category', send Category name, send Description more than 50 but less than 500 characters, click on Save
	@Test
	public void TC33() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());
		categoryListPage.clickOnAddPostCategoryFromMenu();

		CategoryAddPage categoryAddPage = new CategoryAddPage(driver);
		assertTrue("Nisam na Add Category web stranici!", categoryAddPage.checkAddPageLocation());

		categoryAddPage.inputName(editedCategoryName);
		categoryAddPage.inputDescription(editedDescription);
		categoryAddPage.clickOnSave();

		assertTrue("Nisam na List Category web lokaciji!", categoryListPage.checkListPageLocation());
		assertTrue("Kategorija se ne pojavljuje na listi!", categoryListPage.isNewCategoryOnTheList(editedCategoryName));
		assertTrue("Description se ne pojavljuje na listi!", categoryListPage.isDescriptionOnTheList(editedCategoryName, editedDescription));
	}

	// click on view category
	@Test
	public void TC34() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertTrue("Nisam na Category List web stranici!", categoryListPage.checkListPageLocation());

		categoryListPage.scrollTo(editedCategoryName);
		categoryListPage.clickOnViewCategoryIcon(editedCategoryName);

		// cekam da se novi tab učita
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// organizujem sve tabove u listu
		List<String> tabs = new ArrayList<>(driver.getWindowHandles());

		// prebacujem fokus na drugi tab
		driver.switchTo().window(tabs.get(1));

		// čekam da se element ucita
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'" + editedCategoryName + "')]")));
		assertTrue("Nisam na View Category web stranici!", driver.getTitle().contains(editedCategoryName));

		assertTrue("Nisam na View Category web stranici!", categoryListPage.checkViewCategoryPage(editedCategoryName));

		// vracam se na prvi tab
		driver.switchTo().window(tabs.get(0));

		// čekam da se fokus prebaci na prvi tab
		wait.until(ExpectedConditions.titleContains("Post Categories"));
	}

}
