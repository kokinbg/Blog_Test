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
import cubes.pages.posts.PostListWebPage;

public class PostListActionsIconsTest {

	private static WebDriver driver;

	private String actionsIconTestPost = "Actions Icons Test VladimirQA";
	private String viewPostTest = "View Test VladimirQA";
	private String deletePostTest = "DeletePostTest VladimirQA";

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

	// Na 'Post Listi', klikni na 'View' ikonu, proveri funkcionalnost iste
	@Test
	public void tc01() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim naziv Title-a u Post Search
		postListWebPage.inputTitleInSearch(viewPostTest);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Klik na 'View Post' icon
		postListWebPage.clickOnView(viewPostTest);

		// Cekam da se 'new tab' ucita
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// Organizujem TAB-ove u Listu
		List<String> tabs = new ArrayList<>(driver.getWindowHandles());

		// Prebacujem fokus na drugi TAB
		driver.switchTo().window(tabs.get(1));

		// Cekam da se WebElement za koji zelim da proverim web lokaciju, ucita
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//a[@href='https://testblog.kurs-qa.cubes.edu.rs' and contains(text(), 'View Test VladimirQA')]")));

		// Proveravam da li sam na 'View Post' web lokaciji
		assertTrue("Nisam na 'View Post' web lokaciji!", driver.getTitle().contains(viewPostTest));

		// Vracam se na prvi TAB
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0));

		// Cekam da se fokus vrati na web lokaciju prvog TAB-a
		wait.until(ExpectedConditions.titleContains("Posts"));
	}

	// Klik na 'Disable' post, Cancel
	@Test
	public void tc02() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Action Icons' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim naziv Title-a u Post Search
		postListWebPage.inputTitleInSearch(actionsIconTestPost);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Disable Post' icon
		postListWebPage.clickOnDisableIcon(actionsIconTestPost);

		// Klikni na 'Cancel' button, u pop-up prozoru
		postListWebPage.clickOnCancelDisableButton();

		// Proveravam da li sam se vratio na 'Post List' web lokaciju
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(actionsIconTestPost);

		// Proveri da li se post nalazi na 'Post Listi'
		assertTrue("Disabled post se ne nalazi na 'Post Listi'!", postListWebPage.isPostOnTheList(actionsIconTestPost));

		// Proveri da li je setovanje vraceno na 'Enabled'
		assertTrue("Post nije disable-ovan!", postListWebPage.isPostEnabled(actionsIconTestPost));
	}

	// Klik na 'Disable' post, Confirm
	@Test
	public void tc03() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Action Icons' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim naziv Title-a u Post Search
		postListWebPage.inputTitleInSearch(actionsIconTestPost);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Disable Post' icon
		postListWebPage.clickOnDisableIcon(actionsIconTestPost);

		// Klikni na 'Disable' button, u pop-up prozoru
		postListWebPage.clickOnDisableButton();

		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(actionsIconTestPost);

		// Proveri da li se 'Disabled' post nalazi na 'Post Listi'
		assertTrue("Disabled post se ne nalazi na 'Post Listi'!", postListWebPage.isPostOnTheList(actionsIconTestPost));

		// Proveri da li je setovan kao 'Disabled'
		assertTrue("Post nije disable-ovan!", postListWebPage.isPostDisabled(actionsIconTestPost));

	}

	// Klik na 'Enable' post, Cancel
	@Test
	public void tc04() {
		// Kreiram objekat 'Post List Action Icons' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Action IconsPost List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim naziv Title-a u Post Search
		postListWebPage.inputTitleInSearch(actionsIconTestPost);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Enable Post' icon
		postListWebPage.clickOnEnableIcon(actionsIconTestPost);

		// Klikni na 'Cancel enable' button, u pop-up prozoru
		postListWebPage.clickOnCancelEnableButton();
		
		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(actionsIconTestPost);

		// Proveri da li je post ostao na 'Post Listi'
		assertTrue("Enabled post se ne nalazi na 'Post Listi'!", postListWebPage.isPostOnTheList(actionsIconTestPost));

		// Proveri da li je setovan kao 'Disabled'
		assertTrue("Post nije disable-ovan!", postListWebPage.isPostDisabled(actionsIconTestPost));
	}

	// Klik na 'Enable' post, Confirm
	@Test
	public void tc05() {
		// Kreiram objekat 'Post List Action Icons' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Action IconsPost List' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim naziv Title-a u Post Search
		postListWebPage.inputTitleInSearch(actionsIconTestPost);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Enable Post' icon
		postListWebPage.clickOnEnableIcon(actionsIconTestPost);

		// Klikni na 'Enable' button, u pop-up prozoru
		postListWebPage.clickOnEnableButton();
		
		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(actionsIconTestPost);

		// Proveri da li se 'Enabled' post nalazi na 'Post Listi'
		assertTrue("Enabled post se ne nalazi na 'Post Listi'!", postListWebPage.isPostOnTheList(actionsIconTestPost));

		// Proveri da li je setovan kao 'Enabled'
		assertTrue("Post nije disable-ovan!", postListWebPage.isPostEnabled(actionsIconTestPost));
	}

	// Set 'Post' kao 'Unimportant', Confirm
	@Test
	public void tc06() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Action Icons' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim naziv Title-a u Post Search
		postListWebPage.inputTitleInSearch(actionsIconTestPost);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Unimportant' icon
		postListWebPage.clickOnUnimportantIcon(actionsIconTestPost);

		// Klikni na 'Set as Unimportant' button, u pop-up prozoru
		postListWebPage.clickOnSetAsUnimportantButton();
		
		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(actionsIconTestPost);

		// Proveri da li se 'Unimportant' post nalazi na 'Post Listi'
		assertTrue("Unimportant post se ne nalazi na 'Post Listi'!",
				postListWebPage.isPostOnTheList(actionsIconTestPost));

		// Proveri da li je setovan kao 'Unimportant'
		assertTrue("Post nije setovan kao 'Unimportant'!", postListWebPage.isPostUnimportant(actionsIconTestPost));
	}

	// Set 'Post' kao 'Important', Confirm
	@Test
	public void tc07() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Action Icons' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim naziv Title-a u Post Search
		postListWebPage.inputTitleInSearch(actionsIconTestPost);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Important' icon
		postListWebPage.clickOnImportantIcon(actionsIconTestPost);

		// Klikni na 'Set as Important' button, u pop-up prozoru
		postListWebPage.clickOnSetAsImportantButton();
		
		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(actionsIconTestPost);

		// Proveri da li se 'Important' post nalazi na 'Post Listi'
		assertTrue("Enabled post se ne nalazi na 'Post Listi'!", postListWebPage.isPostOnTheList(actionsIconTestPost));

		// Proveri da li je setovan kao 'Important'
		assertTrue("Post nije disable-ovan!", postListWebPage.isPostImportant(actionsIconTestPost));
	}

	// Delete post, cancel delete
	@Test
	public void tc08() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Action Icons' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim naziv Title-a u Post Search
		postListWebPage.inputTitleInSearch(deletePostTest);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Delete' icon
		postListWebPage.clickOnDeletePostIcon(deletePostTest);

		// Klikni na 'Cancel Delete' button, u pop-up prozoru
		postListWebPage.clickOnCancelDeletePostButton();
		
		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(deletePostTest);

		// Proveri da li se post nalazi na 'Post Listi'
		assertTrue("Post se ne nalazi na 'Post Listi'!", postListWebPage.isPostOnTheList(deletePostTest));

	}

	// Delete post, confirm delete
	@Test
	public void tc09() {
		// Kreiram objekat 'Post List' web lokacije
		PostListWebPage postListWebPage = new PostListWebPage(driver);

		// Proveravam da li sam na 'Post List Action Icons' web lokaciji
		assertTrue("Nisam na 'Post List' web lokaciji!", postListWebPage.checkPostListWebLocation());

		// Zatvori 'Push-menu' sa strane da bi sve sekcije na listi bile vidljive
		postListWebPage.clickOnHamburger();

		// Unosim naziv Title-a u Post Search
		postListWebPage.inputTitleInSearch(deletePostTest);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Klik na 'Delete' icon
		postListWebPage.clickOnDeletePostIcon(deletePostTest);

		// Klikni na 'Delete' button, u pop-up prozoru
		postListWebPage.clickOnDeletePostButton();
		
		// Unosim u 'Search Post' polje, 'Title' posta
		postListWebPage.inputTitleInSearch(deletePostTest);

		// Proveri da li se 'Obrisani post' post nalazi na 'Post Listi'
		assertTrue("Obrisani post se nalazi na 'Post Listi'!", postListWebPage.isPostOnTheList(deletePostTest));

	}

}
