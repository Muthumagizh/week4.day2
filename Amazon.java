package week6.day3;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
//Amazon:
	// 1.Load the uRL https://www.amazon.in/
	public static void main(String[] args) throws IOException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notification");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// 2.search as oneplus 9 pro

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		// 3.Get the price of the first product
		String text = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
		System.out.println(text);
		driver.findElement(By.xpath("(//a[@role='button']//i)[2]")).click();
		// 4. Print the number of customer ratings for the first displayed product
		String text1 = driver.findElement(By.xpath("//div[@class='a-row a-spacing-medium']//span[1]")).getText();
		System.out.println(text1);

		// 5. Mouse Hover on the stars
		Actions builder = new Actions(driver);
		driver.findElement(By.xpath("(//i[@class='a-icon a-icon-popover'])[1]")).click();
		//builder.moveToElement(driver.findElement(By.xpath("(//i[@class='a-icon a-icon-popover'])[1]"))).perform();
		// 6. Get the percentage of ratings for the 5 star.
		String text2 = driver.findElement(By.xpath("(//span[@class='a-size-base']//a)[2]")).getText();
		System.out.println(text2);
		// 7. Click the first text link of the first image
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")).click();
		// 8. Take a screen shot of the product displayed
		Set<String> windowset = driver.getWindowHandles();
		List<String> windowlist = new ArrayList<String>(windowset);
		driver.switchTo().window(windowlist.get(1));
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/pic2.png");
		FileUtils.copyFile(screenshotAs, dst);
		// 9. Click 'Add to Cart' button
		driver.findElement(By.id("add-to-cart-button")).click();
		// 10. Get the cart subtotal and verify if it is correct.
		Set<String> winddoeset=driver.getWindowHandles();
		List<String> winddoelist=new LinkedList<String>(winddoeset);
		driver.switchTo().window(winddoelist.get(1));
		System.out.println(winddoelist.size());
		String text3 = driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
		System.out.println("price:"+text3);
		if (text.contains(text3)) {
			System.out.println("Price and Subtotal are matching");
		} else {
			System.out.println("Price and Subtotal are mismatching");
		}
	}
}
