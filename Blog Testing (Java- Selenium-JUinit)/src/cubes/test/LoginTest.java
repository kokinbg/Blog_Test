package cubes.test;

import static org.junit.Assert.*;

// POM - Page Oriented Model
// Page orijentisan prema stranicama
// Test strana - u njoj se nalaze SAMO testovi
// Kad imam vise testova, sve zajednicke testove izvucem u jednu klasu

// Metode @BeforeClass i @AfterClass se pozivaju samo po jednom. Na pocetku
// testa, odnosno na kraju testa!

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import cubes.helper.MyWebDriver;
import cubes.loginPage.LoginPage;

public class LoginTest {
	
	
	// fields
	private static WebDriver driver; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// kreiram driver koji se izvrsava na pocetku klase tj testa
		driver = MyWebDriver.getDriver("chrome");	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// na kraju testa, zatvaram driver koji smo kreirali
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// Try to login with leaving Empty email and password field"
	@Test
	public void tc01() {
		LoginPage loginPage = new LoginPage(driver);

		loginPage.insertEmail("");
		loginPage.insertPassword("");
		loginPage.clickOnLogin();

		
		// ASSERT je uvek vezan samo za TEST, nikada van test strane
		assertTrue("Email* is required field - Missing!", loginPage.checkEmailError("Email* is required field"));
		assertTrue("Password* is required field - Missing!", loginPage.checkEmailError("Password* is required field"));
	}

	// Login with incorrect Email format
	@Test
	public void tc02() {
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.login("", "");
		
		loginPage.insertEmail("abc123");
		loginPage.insertPassword("");
		loginPage.clickOnLogin();

		assertTrue("Please, enter the valid Email address - Missing!!", loginPage.checkEmailError("Please, enter the valid Email address"));
		assertTrue("Password* is required field - Missing!", loginPage.checkPasswordError("Password* is required field"));
	}

	// Try to login with invalid Email address and empty password
	@Test
	public void tc03() {
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.insertEmail("example@mail123.com");
		loginPage.insertPassword("");
		loginPage.clickOnLogin();	

		assertTrue("Password* is required field - Missing!", loginPage.checkPasswordError("Password* is required field"));
	}

	// Try to login with valid Email address and empty password
	@Test
	public void tc04() {
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.insertEmail("kursqa@cubes.edu.rs");
		loginPage.insertPassword("");
		loginPage.clickOnLogin();
		
		assertTrue("Password is required field - Missing!!", loginPage.checkPasswordError("Password* is required field"));
	}

//	// Try to login with empty Email address and incorrect password
////	<div class="invalid-feedback" role="alert">
////    <strong>These credentials do not match our records.</strong>
////<p id="email-error" class="error">Email* is required field</p></div>
	@Test
	public void tc05() {
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.insertEmail("");
		loginPage.insertPassword("abc");
		loginPage.clickOnLogin();
		
		assertTrue("Email* is required field - Missing!!", loginPage.checkEmailError("Email* is required field"));
		
	}

	// Try to login with empty Email address and valid password
	@Test
	public void tc06() {
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.insertEmail("");
		loginPage.insertPassword("cubesqa");
		loginPage.clickOnLogin();
		
		
		assertTrue("Email* is required field - Missing!!", loginPage.checkEmailError("Email* is required field"));
		
	}

	// Try to login with incorrect Email format and invalid password
	@Test
	public void tc07() {
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.insertEmail("abc123");
		loginPage.insertPassword("abcabc");
		loginPage.clickOnLogin();
		
		assertTrue("Nedostaje ocekivano obavestenje za neprvilan email", loginPage.checkEmailError("Please, enter the valid Email address"));
		
		
	}

//	Try to login with invalid Email address and invalid password
	@Test
	public void tc08() {
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.insertEmail("example@mail123.com");
		loginPage.insertPassword("abcabc");
		loginPage.clickOnLogin();
		
		assertTrue("Error poruka se ne prikazuje", loginPage.checkEmailUserError("These credentials do not match our records."));
	}


//	// Try to login with valid Email address and invalid password
	@Test
	public void tc09() {
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.insertEmail("kursqa@cubes.edu.rs");
		loginPage.insertPassword("abcabc");
		loginPage.clickOnLogin();
		
		assertTrue("Poruka o nepostojecim kredencijalima se ne ispisuje korektno!", loginPage.checkEmailUserError("These credentials do not match our records."));
		
		
	}
	
	
	// Try to login with incorrect Email address and valid password
	@Test
	public void tc10() {
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.insertEmail("abc123");
		loginPage.insertPassword("cubesqa");
		loginPage.clickOnLogin();
		
		assertTrue("Nepravilan email poruka - nedostaje!", loginPage.checkEmailError("Please, enter the valid Email address"));
		
	}

	// Try to login with invalid Email address and valid password
	@Test
	public void tc11() {
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.insertEmail("example@mail123.com");
		loginPage.insertPassword("cubesqa");
		loginPage.clickOnLogin();
		
		assertTrue("Poruka o 'nepostojecem korisniku' se ne ispisuje pravilno!", loginPage.checkEmailUserError("These credentials do not match our records."));
		
		
	}
	
	@Test
	public void tc12() {
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.insertEmail("kursqa@cubes.edu.rs");
		loginPage.insertPassword("cubesqa");
		loginPage.clickOnLogin();
		
//      loginPage.login("kursqa@cubes.edu.rs", "cubesqa");
//		loginPage.loginSuccess();
		
}

}
