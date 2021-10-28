package week6.day3;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notification");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		// 1)Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// WebDriver frame = driver.switchTo().frame(0);

		// 2) Mouseover on Brands and Search L'Oreal Paris
		WebElement findElement = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(findElement).perform();
		// 3) Click L'Oreal Paris
		driver.findElement(By.xpath("//div[@id='brandCont_Popular']/ul[1]/li[5]/a[1]/img[1]")).click();
		// 4) Check the title contains L'Oreal Paris(Hint-GetTitle)
		String title = driver.getTitle();
		System.out.println(title);
		// 5) Click sort By and select customer top rated
		driver.findElement(By.xpath("//span[text()='Sort By : popularity']")).click();
		// Select select=new Select(findElement2);
		driver.findElement(By.xpath("(//div[@class='control-indicator radio '])[3]")).click();
		// 6) Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("(//span[text()='Category'])")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		// driver.findElement(By.xpath("(//span[@class='filter-name '])[1]")).click();
		driver.findElement(By.xpath("(//span[text()='Hair Care'])[1]")).click();
		driver.findElement(By.xpath("(//div[@class='control-indicator checkbox '])[1]")).click();
		// 7) Click->Concern->Color Protection

		driver.findElement(By.xpath("(//span[text()='Concern'])")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		// 8)check whether the Filter is applied with Shampoo
		String string = driver.findElement(By.xpath("(//span[@class='filter-value'])")).getText();
		System.out.println(string);
		if (string.equalsIgnoreCase("shampoo")) {
			System.out.println(string);
		} else {
			System.out.println("not match");
		}
		// 9) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[@class='css-d5z3ro'][1]")).click();
		Set<String> windowset = driver.getWindowHandles();
		List<String> windowlist = new ArrayList<String>(windowset);
		// 10) GO to the new window and select size as 175ml
		driver.switchTo().window(windowlist.get(1));
		WebElement findElement2 = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select select = new Select(findElement2);
		select.selectByIndex(0);
		// 11) Print the MRP of the product
		String text = driver.findElement(By.xpath("//span[@class='css-1888qy']/following-sibling::span[1]")).getText();
		System.out.println(text);
		// 12) Click on ADD to BAG
		driver.findElement(By.xpath("//span[text()='ADD TO BAG'][1]")).click();
		// 13) Go to Shopping Bag
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		// 14) Print the Grand Total amount
		driver.switchTo().frame(0);
		String text2 = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		System.out.println(text2);
		// 15) Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		// 16) Click on Continue as Guest
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		// 17) Check if this grand total is the same in step 14
		String text3 = driver.findElement(By.xpath("(//div[@class='value'])[2]")).getText();
		if (text2.equalsIgnoreCase(text3)) {
			System.out.println("price is same" + text3);

		} else {
			System.out.println("different");
		}
		// 18) Close all windows
		driver.quit();

	}

}
