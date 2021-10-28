package week6.day3;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {
	// Myntra
	// 1) Open https://www.myntra.com/
	public static void main(String[] args) throws IOException, InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notification");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// 2) Mouse hover on MeN
		WebElement findElement = driver.findElement(By.xpath("(//div[@class='desktop-navLink']/a)[1]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(findElement).perform();

		// 3) Click Jackets
		driver.findElement(By.xpath("(//a[@class='desktop-categoryLink'])[6]")).click();
		// 4) Find the total count of item
		String text = driver.findElement(By.xpath("//span[@class='title-count']")).getText();

		System.out.println(text);
		String total = text.substring(3, 7);
		int a = Integer.parseInt(total);
		System.out.println(a);
		// 5) Validate the sum of categories count matches
		String text1 = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
		System.out.println(text1);
		String total1 = text1.substring(1, 5);
		int a1 = Integer.parseInt(total1);
		System.out.println(a1);
		String text2 = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		System.out.println(text2);
		String total2 = text2.substring(1, 3);
		int a2 = Integer.parseInt(total2);
		System.out.println(a2);

		if (a == (a1 + a2)) {
			System.out.println("total matches");

		} else {
			System.out.println("total incorrect");
		}
		// 6) Check jackets
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[1]")).click();
		// 7) Click + More option under BRAND
		driver.findElement(By.xpath("(//div[@class='brand-more'])[1]")).click();
		// 8) Type Duke and click checkbox
		driver.findElement(By.xpath("(//input[@class='FilterDirectory-searchInput'])[1]")).sendKeys("Duke");
		driver.findElement(By.xpath("//label[text()='Duke']")).click();
		// 9) Close the pop-up x
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		// 10) Confirm all the Coats are of brand Duke
		driver.findElement(By.xpath("//span[text()='56']")).click();
		List<WebElement> brand = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		for (WebElement webElement : brand) {
			String text3 = webElement.getText();
			if (!text3.equalsIgnoreCase("Duke")) {
				System.out.println("brand mismatch");
				break;
			}

		}
		System.out.println("all brand is Duke");

		// Hint : use List
//	11) Sort by Better Discount
		builder.moveToElement(
				driver.findElement(By.xpath("//span[@class='myntraweb-sprite sort-downArrow sprites-downArrow']")))
				.perform();
		driver.findElement(By.xpath("//label[text()='Better Discount']")).click();
		Thread.sleep(1000);
//	12) Find the price of first displayed item
		String text31 = driver.findElement(By.xpath("(//div[@class='product-price']//span)[1]")).getText();
//	Click on the first product
		System.out.println("price of first product:" + text31);
		// 13) Take a screen shot
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/pic1.png");
		FileUtils.copyFile(screenshotAs, dst);
		// 14) Click on WishList Now
		driver.findElement(By.xpath("//span[text()='Wishlist']")).click();

//	15) Close Browser

		driver.quit();
	}
}
