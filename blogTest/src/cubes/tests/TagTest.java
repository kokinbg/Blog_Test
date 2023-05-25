package cubes.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import cubes.helper.MyWebDriver;
import cubes.loginPage.LoginPage;
import cubes.pages.tag.TagsAddPage;
import cubes.pages.tag.TagsListPage;

public class TagTest {
	
	private static WebDriver driver;
	private String newTagName = "Test Tag Name Vladimir5";
	private String editedTagName = "EDITED Test Tag Name Vladimir5";

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

	// insert empty tag name and click save
	@Test
	public void tc01() {
		// prvo kreiram objekte stranice koje su mi potrebni
		TagsListPage tagsListPage = new TagsListPage(driver);
		tagsListPage.clickOnAddNewTag();

		// kada se otvorila ta strana onda kreiram tu stranu
		TagsAddPage tagsAddPage = new TagsAddPage(driver);

		// proveri mi da li se nalazim tj da li me je "bacilo" na tu stranu na koju sam
		// zeleo da odem
		assertTrue("Add tag - page open problem", tagsAddPage.isOnPage());

		// upisi prazan string i klikni na save
		tagsAddPage.insertName("");
		tagsAddPage.clickOnSave();

		assertTrue("Insert Tag error problem!", tagsAddPage.isErrorOnPage("This field is required."));

	}

	// insert empty tag name and click cancel
	@Test
	public void tc02() {
		TagsListPage tagsListPage = new TagsListPage(driver);
		tagsListPage.clickOnAddNewTag();

		TagsAddPage tagsAddPage = new TagsAddPage(driver);
		assertTrue("Add tag - page open problem", tagsAddPage.isOnPage());

		tagsAddPage.insertName("");
		tagsAddPage.clickOnCancel();

		assertTrue("Wrong page error", tagsListPage.isOnPage());
	}

	// insert Test tag name and click Cancel
	@Test
	public void tc03() {

		TagsListPage tagsListPage = new TagsListPage(driver);
		tagsListPage.clickOnAddNewTag();

		TagsAddPage tagsAddPage = new TagsAddPage(driver);
		assertTrue("Add tag - page open problem", tagsAddPage.isOnPage());

		tagsAddPage.insertName(newTagName);
		tagsAddPage.clickOnCancel();

		assertTrue("Wrong page error", tagsListPage.isOnPage());

	}

	// insert Test tag Test Tag Name Vladimir for Tag name and click Save
	@Test
	public void tc04() {
		TagsListPage tagsListPage = new TagsListPage(driver);
		tagsListPage.clickOnAddNewTag();

		TagsAddPage tagsAddPage = new TagsAddPage(driver);
		assertTrue("Add tag - page open problem", tagsAddPage.isOnPage());

		
		// RANDOM TAG KAD ZELIMO DA KREIRAMO!
//		Random random = new Random();
//		String newTagName = "Tag name "+random.nextInt(1000);
//		tagsAddPage.insertName("Test Tag Name Vladimir");

//		String newTagName = "Test Tag Name Vladimir5";

		tagsAddPage.insertName(newTagName);
		tagsAddPage.clickOnSave();

		assertTrue("Wrong page error", tagsListPage.isOnPage());
		assertTrue("New Tag Name missing!", tagsListPage.isTagNameElementOnPage(newTagName));

	}

	// Insert existing Test Tag Name Vladimir for tag name and click save and check
	@Test
	public void tc05() {

		TagsListPage tagsListPage = new TagsListPage(driver);
		tagsListPage.clickOnAddNewTag();

		TagsAddPage tagsAddPage = new TagsAddPage(driver);
		assertTrue("Add tag - page open problem", tagsAddPage.isOnPage());

//		String newTagName = "Test Tag Name Vladimir5";

		tagsAddPage.insertName(newTagName);
		tagsAddPage.clickOnSave();

		assertTrue("Wrong page error", tagsAddPage.isOnPage());
		assertTrue("Missing div error message", tagsAddPage.doubleTagError("The name has already been taken."));
	}

	// edit postojeceg taga, udjemo na polje i kliknemo Cancel
	@Test
	public void tc06() {
		TagsListPage tagsListPage = new TagsListPage(driver);

//		String tagName = "Test Tag Name Vladimir5";
		tagsListPage.clickOnEditButton(newTagName);

		TagsAddPage tagsAddPage = new TagsAddPage(driver);
		assertTrue("Nije Edit Tags strana! Ne pokazuje naziv trazenog TAGA u url!",newTagName.equalsIgnoreCase(tagsAddPage.getName()));

		tagsAddPage.clickOnCancel();
		assertTrue("Tags list open page problem", tagsListPage.isOnPage());
		assertTrue("New tag name missing!", tagsListPage.isTagNameElementOnPage(newTagName));

//		tagsEditTags.clickOnCancel();
//		
//		TagsListPage tegsListPage = new TagsListPage(driver);
//		assertTrue("Pogresna stranica - greska!", tagsListPage.isOnPage());
//		assertTrue("Promenjen TAG - Greska!", tagsListPage.isTagNameElementOnPage("Test Tag Name Vladimir2"));

	}

	// edit postojeceg taga, udjemo na polje, obrisemo TAG i kliknemo Cancel
	@Test
	public void tc07() {
		TagsListPage tagsListPage = new TagsListPage(driver);
//		String tagName = "Test Tag Name Vladimir5";
		tagsListPage.clickOnEditButton(newTagName);

		TagsAddPage tagsAddPage = new TagsAddPage(driver);
		assertTrue("Nije Edit Tags strana! Ne pokazuje naziv trazenog TAGA u url!",newTagName.equalsIgnoreCase(tagsAddPage.getName()));

		tagsAddPage.insertName("");
		tagsAddPage.clickOnCancel();

		assertTrue("Tags list open page - problem!", tagsListPage.isOnPage());
		assertTrue("My TAG name missing!", tagsListPage.isTagNameElementOnPage(newTagName));
	}

	// edit postojeceg taga, udjemo na polje, editujemo tag i kliknemo Cancel
	@Test
	public void tc08() {
		TagsListPage tagsListPage = new TagsListPage(driver);
//		String tagName = "Test Tag Name Vladimir5";
		tagsListPage.clickOnEditButton(newTagName);

		TagsAddPage tagsAddPage = new TagsAddPage(driver);
		assertTrue("Nije Edit Tags strana! Ne pokazuje naziv trazenog TAGA u url!",newTagName.equalsIgnoreCase(tagsAddPage.getName()));

		tagsAddPage.insertName("Test Tag Name Vl");
		tagsAddPage.clickOnCancel();

		assertTrue("Tags list open page - problem!", tagsListPage.isOnPage());
		assertTrue("My TAG name missing!", tagsListPage.isTagNameElementOnPage(newTagName));
	}

	// edit "Test Tag Name Vladimir5", clear text, click save
	@Test
	public void tc09() {
		TagsListPage tagsListPage = new TagsListPage(driver);
//		String tagName = "Test Tag Name Vladimir5";

		tagsListPage.clickOnEditButton(newTagName);

		TagsAddPage tagsAddPage = new TagsAddPage(driver);
		assertTrue("Nije Edit Tags strana! Ne pokazuje naziv trazenog TAGA u url!",newTagName.equalsIgnoreCase(tagsAddPage.getName()));

		tagsAddPage.insertName("");
		tagsAddPage.clickOnSave();

		assertTrue("This field is required - missing", tagsAddPage.isErrorOnPage("This field is required."));

	}

	// edit "Test Tag Name Vladimir5", leave text, click save
	@Test
	public void tc10() {
		TagsListPage tagsListPage = new TagsListPage(driver);
//		String tagName = "Test Tag Name Vladimir5";

		tagsListPage.clickOnEditButton(newTagName);

		TagsAddPage tagsAddPage = new TagsAddPage(driver);
		assertTrue("Nije Edit Tags strana! Ne pokazuje naziv trazenog TAGA u url!", newTagName.equalsIgnoreCase(tagsAddPage.getName()));

		tagsAddPage.clickOnSave();

		assertTrue("This field is required - missing", tagsListPage.isOnPage());
		assertTrue("", tagsListPage.isTagNameElementOnPage(newTagName));

	}
	
	// editujemo postojeci tag i kliknemo save
	@Test
	public void tc11 () {
		TagsListPage tagsListPage = new TagsListPage(driver);
		
		
		tagsListPage.clickOnEditButton(newTagName);
		
		TagsAddPage tagsAddPage = new TagsAddPage(driver);
		assertTrue("Nije edit strana - ne pokazuje tag u URL-u!", newTagName.equalsIgnoreCase(tagsAddPage.getName()));
		
		tagsAddPage.insertName(editedTagName);
		tagsAddPage.clickOnSave();
		
		assertTrue(editedTagName, tagsListPage.isOnPage());
		assertTrue("", tagsListPage.isTagNameElementOnPage(editedTagName));
 	}
	
	// klikni na delete, pa na cancel
	@Test
	public void tc12 () {
		TagsListPage tagsListPage = new TagsListPage(driver);
		tagsListPage.clickOnDeleteTagButton(editedTagName);
		
		// kucamo prvo: Thread.sleep(4000);
		// da bi funkcionisalo mora da se pretvori u:
		
		// try/catch su exceptioni
		// U 'try' bloku ide kod koji je "rizican"
		// U 'catch' ide kod tekst, koji se nastavlja ako 'try' pukne
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tagsListPage.clickOnCancelDeleteButton();
		// potvrdi da se ime nije promenilo na listi
		
	}
	
	// klikni na delete, pa na x
	@Test
	public void tc13 () {
		TagsListPage tagsListPage = new TagsListPage(driver);
		tagsListPage.clickOnDeleteTagButton(editedTagName);
		tagsListPage.clickOnXbutton();
		// potrvdi da sam na zeljenoj strani
		assertTrue("Nije na potrebnoj web adresi!", tagsListPage.isOnPage());
		// potvrdi da se ime nije promenilo na listi
		assertTrue("Element nije na listi!", tagsListPage.isTagNameElementOnPage(editedTagName));
	}
	
	
	// klikni na delete pa na delete 
	@Test
	public void tc14 () {
		TagsListPage tagsListPage = new TagsListPage(driver);
		tagsListPage.clickOnDeleteTagButton(editedTagName);
		tagsListPage.clickOnDialogTagDeleteButton();
		//potrvdi da sam na zeljenoj strani
		assertTrue("Nismo na potrebnoj web adresi!", tagsListPage.isOnPage());
		// potvrdi da se obrisani tag ne nalazi na listi
		assertTrue("TAG nije obrisan!", !tagsListPage.tagNameIsNotOnTheList(editedTagName));
	
	}
}
