package week6.day3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sortable {
	public static void main(String[] args) {
		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notification");
		WebDriverManager.chromedriver().setup();
				ChromeDriver driver=new ChromeDriver(options);				
				driver.get("https://jqueryui.com/sortable/");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				WebDriver frame = driver.switchTo().frame(0);
				WebElement item1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
				Point item5 = driver.findElement(By.xpath("//li[text()='Item 5']")).getLocation();
				System.out.println(item5);
				Actions builder=new Actions(driver);
				builder.clickAndHold(item1).moveByOffset(11, 171).perform();
	}
}
