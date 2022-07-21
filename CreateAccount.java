package salesForce;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAccount{

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		// Open the browser
		String inputStr = "TESTRAP2";
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		driver.findElement(By.id("username")).sendKeys("hari.radhakrishnan@qeagle.com ");
		driver.findElement(By.id("password")).sendKeys("Tuna@123");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(2000);
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("Size" + windowHandles.size());
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		////button[text()='View All']
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("Sales");
		driver.findElement(By.xpath("(//p[@class='slds-truncate']/mark)[3]")).click();
		WebElement wbAccounts = driver.findElement(By.xpath("//span[text()='Accounts']"));
		driver.executeScript("arguments[0].click()", wbAccounts);
		driver.findElement(By.xpath("//div[@title='New']")).click();
		driver.findElement(By.xpath("//input[@name='Name']")).sendKeys(inputStr);
		//select ownership as public
		Thread.sleep(1000);
		WebElement wbOwnership = driver.findElement(By.xpath("//label[text()='Ownership']/following::button[1]"));
		driver.executeScript("arguments[0].click()", wbOwnership);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Public']")).click();
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		String toastMsg = driver.findElement(By.xpath("(//a[@class='forceActionLink'])[4]")).getAttribute("title");
		System.out.println(toastMsg);
		if(toastMsg.equals(inputStr))
			System.out.println("Account is created");
		else
			System.out.println("account not created");
		driver.close();
	}

}
