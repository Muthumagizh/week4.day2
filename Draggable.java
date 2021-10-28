package week6.day3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Draggable {

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notification");
		WebDriverManager.chromedriver().setup();
				ChromeDriver driver=new ChromeDriver(options);				
				driver.get("https://jqueryui.com/draggable/");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				WebDriver frame = driver.switchTo().frame(0);
				WebElement findElement = driver.findElement(By.id("draggable"));
				Actions builder=new Actions(driver);
				builder.dragAndDropBy(findElement, 100, 10).perform();
				
	}

}
