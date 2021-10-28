package week6.day3;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {
	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notification");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		// 1. Launch https://www.snapdeal.com/
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// 2. Go to Mens Fashion
		driver.findElement(By.xpath("(//span[@class='catText'])[6]")).click();
		// 3. Go to Sports Shoes
		driver.findElement(By.xpath("//span[text()='Sports Shoes'][1]")).click();
		// 4. Get the count of the sports shoes
		String text = driver.findElement(By.xpath("//span[@class='category-count']")).getText();
		System.out.println(text);
		// 5. Click Training shoes
		driver.findElement(By.xpath("(//div[@class='child-cat-name '])[2]")).click();
		// 6. Sort by Low to High
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();

		driver.findElement(By.xpath("//li[@data-sorttype='plth']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(1000);
		Actions builder = new Actions(driver);
		//builder.moveToElement(driver.findElement(By.xpath("//span[@class='btn-yes js-yesFeedback']"))).perform();

		List<WebElement> findElements = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		Set<WebElement> sorted = new LinkedHashSet<WebElement>(findElements);
		Thread.sleep(1000);
		System.out.println(sorted.size());
		for (WebElement webElement : sorted) {
			String x = webElement.getText();
			System.out.println(x);
		}
		// 7. Check if the items displayed are sorted correctly
		
		// 8.Select the price range (900-1200)
		Thread.sleep(1000);
		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("900");
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("1200");
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
		Thread.sleep(1000);
		// 9.Filter with color Navy
		driver.findElement(By.xpath("//input[@id='Color_s-Black']/following-sibling::label[1]")).click();
		// 10 verify the all applied filters
		String text1=driver.findElement(By.xpath("//a[@class='clear-filter-pill']")).getText();
		String text2=driver.findElement(By.xpath("//a[@data-key='Color_s|Color']")).getText();
		System.out.println(text1+text2);
		// 11. Mouse Hover on first resulting Training shoes
		builder.moveToElement(driver.findElement(By.xpath("//p[@class='product-title']"))).perform();
		// 12. click QuickView button
		driver.findElement(By.xpath("//div[@class='clearfix row-disc']/div")).click();
		// 13. Print the cost and the discount percentage
		String text3 = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println(text3);
		String text4 = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println(text4);
		// 14. Take the snapshot of the shoes.
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/pic3.png");
		FileUtils.copyFile(screenshotAs, dst);
		
		// 15. Close the current window
		driver.close();
		// 16. Close the main window
		driver.quit();
	}
}
