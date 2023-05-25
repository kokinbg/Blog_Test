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
import cubes.pages.posts.PostListWebPage;

public class PostListSearchBoxTest {

	private static WebDriver driver;

	private String titleSearch = "Search_Box_Test Vladimir QA";
	private String categoryPick = "Vladimir QA";
	private String tagPick = "VladimirQA_NE_BRISI!";

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

	// U 'Title' search box, unosim 'Title' jednog posta, proveravam da li je pozvan
	@Test
	public void tc01() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim 'Title' posta u 'Title search box'
		postListWebPage.inputTitleInSearchBox(titleSearch);

		// Proveravam da li se 'Post' nalazi na 'Post Listi'
		assertTrue("Post se ne nalazi na Post Listi!", postListWebPage.isPostOnTheList(titleSearch));

	}

	// Biram sa 'Category' liste jednu od Kategorija, proveravam pozvane postove pod
	// tom 'Kategorijom'
	@Test
	public void tc02() {

		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Odaberi jednu 'Category' sa 'Category liste'
		postListWebPage.pickOneCategoryInSearchBox(categoryPick);

		// Verifikacija da su svi izlistani postovi povezani iskljucivo sa odabranom
		// 'Kategorijom'
		assertTrue("Nisu svi postovi na Post Listi sa odabranom kategorijom 'Vladimir QA'!",
				postListWebPage.verifyPostsByCategory(categoryPick));
	}

	// Sa 'TAG' liste, biram jedan 'Tag', proveravam pozvane postove pod tim
	// 'Tag-om'
	@Test
	public void tc03() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Biram jedan 'TAG' iz 'TAG box' padajuceg menija
		postListWebPage.pickTAGInSearchBox(tagPick);

		// Verifikacija izlistanih postovi povezanih iskljucivo sa odabranim 'TAG-om'
		postListWebPage.verifyPostsByTag(tagPick);

	}

	// Proveravam postojanje jednog posta po pretrazi sledecih kriterijuma:'Title', 'Category' i 'TAG'
	// Unosim u 'Title' search box title jednog posta, biram sa 'Category' liste
	// jednu od 'Kategorija', sa 'TAG' liste biram jedan 'Tag', proveravam post
	// pozvan po tim kriterijumima na 'Post Listi'
	@Test
	public void tc04() {
		// Kreiram objekat web strane 'Post List Page'
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Page' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim 'Title' posta u 'Title search box'
		postListWebPage.inputTitleInSearchBox(titleSearch);

		// Biram 'Category' posta sa liste
		postListWebPage.pickOneCategoryInSearchBox(categoryPick);

		// Biram 'TAG' posta sa liste
		postListWebPage.pickTAGInSearchBox(tagPick);

		// Proveravam da li se 'Post' nalazi na 'Post Listi'
		assertTrue("Trazeni Post nije na Post Listi!!", postListWebPage.isPostOnTheList(titleSearch));

	}

}
