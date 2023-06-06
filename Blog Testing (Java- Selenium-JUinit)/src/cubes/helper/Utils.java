package cubes.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class Utils {
	
	// 'Content Field' WebElements (with iFrame)
	public static String contentLabel = "//label[text()='Content']";
	public static String iFrameLocator = "//iframe[@class='cke_wysiwyg_frame cke_reset']";
	public static String contentFieldLocator = "//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']";

	// 'Cancel' dugme, xPath
	public static String cancelButton = "//a[@class='btn btn-outline-secondary']";
	// 'Save' dugme, xPath
	public static String saveButton = "//button[@type='submit']";

	// 'Category' Name
	public static String categoryName = "Vladimir QA";

	// Photographies links
	public static String jpgSmall = "C:/Users/filmi/Desktop/Additonal Files/Photographies/PhotosJPG/blacklist.jpg";
	public static String jpgBig = "C:/Users/filmi/Desktop/Additonal Files/Photographies/PhotosJPG/bigJPG.jpg";
	public static String jpg30MB = "C:/Users/filmi/Desktop/Additonal Files/Photographies/PhotosJPG/30MB JPG.jpg";
	public static String pngSmall = "C:/Users/filmi/Desktop/Additonal Files/Photographies/PhotosPNG/Small PNG.png";
	public static String pngBigVertical = "C:/Users/filmi/Desktop/Additonal Files/Photographies/PhotosPNG/Big Vertical PNG.png";
	public static String gifSmall = "C:/Users/filmi/Desktop/Additonal Files/Photographies/PhotoGIF/GIF Small.gif";
	public static String gifBig = "C:/Users/filmi/Desktop/Additonal Files/Photographies/PhotoGIF/GIF Big.gif";
	public static String photoAVIF = "C:/Users/filmi/Desktop/Additonal Files/Photographies/PhotoAVIF/AVIF Format.avif";

	// Error Poruke za 'Add New Post' web lokaciju
	public static String emptyTitleErrorMessage = "This field is required.";
	public static String titleLessThan20CharErrorMessage = "Please enter at least 20 characters.";
	public static String emptyDescriptionErrorMessage = "This field is required.";
	public static String descriptionErrorMessageLessThan50Char = "Please enter at least 50 characters.";
	public static String emptyTagSelectedErrorMessage = "This field is required.";
	public static String emptyContentErrorMessage = "The content field is required.";
	public static String moreThan255CharErrorMessage = "Please enter no more than 255 characters.";
	public static String moreThan500CharErrorMessage = "Please enter no more than 500 characters.";
	public static String invalidPhotoFormatErrorMessage = "The photo must be an image.";

	// EDIT Pick 'Category'
	public static String categoryEditedNameDefault = "-- Choose Category --";
	public static String categoryNameVladimirQA = "Vladimir QA";

	// Hardcoded 'Title'
	public static String aWonderfulSerenity = "A wonderful serenity";

	// Edited harcoded 'Title'
	public static String farFarAway = "Far far away, behind the word mountains...";

	// Hardcoded 'Description'
	public static String hardcodedDescription = "A wonderful serenity has taken possession of my entire soul...";
	
	// 'TAG' Names
	public static String tagOne = "VladimirQA_NE_BRISI!";
	public static String tagTwo = "VladimirQA2_NE_BRISI!";
	public static String tagThree = "Default TAG NE BRISATI";
	
	// Action icon test
	public static String actionsIconTestPost = "Actions Icons Test VladimirQA";
	public static String viewPostTest = "View Test VladimirQA";
	public static String deletePostTest = "DeletePostTest VladimirQA";

	// Hardcoded 'Content'
	public static String hardcodecContent = "A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents.";

	// RANDOM GENERATED STRINGS:
	// RANDOM 'Title' String, manji od 20 karaktera, uz pomoc 'java.util.Random'
	public static String LESS_THAN_20_CHAR_RANDOM_STRING_LOOP = generateRandomStringLessThan20Loop();

	// RANDOM 'Title' String, manji od 20 karaktera, uz pomoc 'RandomStringUtils'
	public static final String LESS_THAN_20_CHAR_RANDOM_STRING = generateRandomStringLessThan20();

	// RANDOM 'Title'String, veci od 20 karaktera, uz pomoc 'RandomStringUtils'
	public static final String MORE_THAN_20_CHAR_RANDOM_STRING = generateRandomStringMoreThan20();

	// RANDOM 'Title' String, veci od 255 karaktera, uz pomoc 'RandomStringUtils'
	public static final String MORE_THAN_255_CHAR_RANDOM_STRING = generateRandomStringMoreThan255();

	// RANDOM 'Description' String, veci od 500 karaktera, uz pomoc
	// 'RandomStringUtils'
	public static final String MORE_THAN_500_CHAR_RANDOM_STRING = generateRandomStringMoreThan500();

	// RANDOM 'Description' String, manji od 50 karaktera, uz pomoc
	// 'RandomStringUtils'
	public static final String LESS_THAN_50_CHAR_RANDOM_STRING = generateRandomStringLessThan50();

	// HARDCODED STRINGS

	// 'Descriprion' String, veci od 50 karaktera
	public static final String DESCRIPTION_MORE_THAN_50 = "A wonderful serenity has taken possession of my entire soul.";

	// 'Content' od 100 reci
	public static final String CONTENT_100_WORDS = "A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my "
			+ "whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. "
			+ "I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents. "
			+ "I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. "
			+ "When, while the lovely valley teems with";

	// 'Content' od 1000 reci
	public static final String CONTENT_1000_WORDS = "A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which "
			+ "I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. "
			+ "I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents. "
			+ "I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. "
			+ "When, while the lovely valley teems with vapour around me, and the meridian sun strikes the upper surface of the impenetrable foliage of "
			+ "my trees, and but a few stray gleams steal into the inner sanctuary, I throw myself down among the tall grass by the trickling stream; and, "
			+ "as I lie close to the earth, a thousand unknown plants are noticed by me: when I hear the buzz of the little world among the stalks, "
			+ "and grow familiar with the countless indescribable forms of the insects and flies, then I feel the presence of the Almighty, "
			+ "who formed us in his own image, and the breath of that universal love which bears and sustains us, as it floats around us in an eternity "
			+ "of bliss; and then, my friend, when darkness overspreads my eyes, and heaven and earth seem to dwell in my soul and absorb its power, "
			+ "like the form of a beloved mistress, then I often think with longing, Oh, would I could describe these conceptions, could impress upon paper "
			+ "all that is living so full and warm within me, that it might be the mirror of my soul, as my soul is the mirror of the infinite God! "
			+ "O my friend -- but it is too much for my strength -- I sink under the weight of the splendour of these visions! "
			+ "A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. "
			+ "I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. "
			+ "I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents. "
			+ "I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. "
			+ "When, while the lovely valley teems with vapour around me, and the meridian sun strikes the upper surface of the impenetrable foliage of my "
			+ "trees, and but a few stray gleams steal into the inner sanctuary, I throw myself down among the tall grass by the trickling stream; and,"
			+ " as I lie close to the earth, a thousand unknown plants are noticed by me: when I hear the buzz of the little world among the stalks, "
			+ "and grow familiar with the countless indescribable forms of the insects and flies, then I feel the presence of the Almighty, who formed us "
			+ "in his own image, and the breath of that universal love which bears and sustains us, as it floats around us in an eternity of bliss; and then, "
			+ "my friend, when darkness overspreads my eyes, and heaven and earth seem to dwell in my soul and absorb its power, "
			+ "like the form of a beloved mistress, then I often think with longing, Oh, would I could describe these conceptions, could impress upon paper "
			+ "all that is living so full and warm within me, that it might be the mirror of my soul, as my soul is the mirror of the infinite God!"
			+ " O my friend -- but it is too much for my strength -- I sink under the weight of the splendour of these visions! A wonderful serenity has "
			+ "taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of "
			+ "existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite sense "
			+ "of mere tranquil existence, that I neglect my talents. I should be incapable of drawing a single stroke at the present moment; and yet "
			+ "I feel that I never was a greater artist than now. When, while the lovely valley teems with vapour around me, and the meridian sun strikes "
			+ "the upper surface of the impenetrable foliage of my trees, and but a few stray gleams steal into the inner sanctuary, "
			+ "I throw myself down among the tall grass by the trickling stream; and, as I lie close to the earth, a thousand unknown plants are noticed "
			+ "by me: when I hear the buzz of the little world among the stalks, and grow familiar with the countless indescribable forms of the insects "
			+ "and flies, then I feel the presence of the Almighty, who formed us in his own image, and the breath of that universal love which bears and "
			+ "sustains us, as it floats around us in an eternity of bliss; and then, my friend, when darkness overspreads my eyes, and heaven and earth "
			+ "seem to dwell in my soul and absorb its power, like the form of a beloved mistress, then I often think with longing, "
			+ "Oh, would I could describe these conceptions, could impress upon paper all that is living so full and warm within me, that it might be "
			+ "the mirror of my soul, as my soul is the mirror of the infinite God! O my friend -- but it is too much for my strength -- "
			+ "I sink under the weight of the splendour of these visions!A wonderful serenity has taken possession of my entire soul, "
			+ "like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, "
			+ "which was created for the bliss of souls";

	// METODE:

	// Generisanje nasumicnog String-a koji je manji od 20 karaktera, uz pomoc
	// 'RandomStringUtils'
	public static String generateRandomStringLessThan20() {
		return RandomStringUtils.randomAlphabetic(19);
	}

	// Generisannje nasumicnog String-a, koji je veci od 20 karaktera, uz pomoc
	// 'RandomStringUtils'
	public static String generateRandomStringMoreThan20() {
		return RandomStringUtils.randomAlphabetic(21);
	}

	// Generisanje nasumicnog String-a, koji je manji od 50 karaktera, uz pomoc
	// 'RandomStringUtils'
	public static String generateRandomStringLessThan50() {
		return RandomStringUtils.randomAlphabetic(44);
	}

	// Generisanje nasumicnog String-a koji je manji od 20 karaktera, uz pomoc
	// 'java.util.Random'
	public static String generateRandomStringLessThan20Loop() {
		Random random = new Random();
		int length = random.nextInt(20);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			char c = (char) (random.nextInt(26) + 'a');
			sb.append(c);
		}
		return sb.toString();
	}

	// Generisanje nasumicnog String-a, koji je veci od 255 karaktera, uz pomoc
	// 'RandomStringUtils'
	public static String generateRandomStringMoreThan255() {
		return RandomStringUtils.randomNumeric(256);
	}

	// Generisanje nasumicnog String-a, koji je veci od 500 karaktera, uz pomoc
	// 'RandomStringUtils'
	public static String generateRandomStringMoreThan500() {
		return RandomStringUtils.randomAlphabetic(501);
	}

	// Scroll do xPath-a WebElement-a
	public static void scrollTo(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Scroll do WebElement-a
	public static void scrollToWebElement(WebDriver driver, WebElement element) {
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// wait.until(ExpectedConditions.visibilityOf(element));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
