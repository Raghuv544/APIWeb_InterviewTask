package web.Steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MyPediaSteps {
	public	WebDriver driver;

	@Given("open browser and enter url")
	public void open_browser_and_enter_url() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.mypedia.pearson.com/login");
	}


	@Then("checking language dropdown has at least {int} languages and verify")
	public void checking_language_dropdown_has_at_least_languages_and_verify(Integer int1) throws InterruptedException {
		driver.findElement(By.xpath("//*[@class='accountDetailsLangDropDown']")).click();
		List<WebElement> languages = driver.findElements(By.xpath("//*[@role='menuitem']"));
		boolean languageCountStatus = false;
		if (languages.size()>=int1) {
			languageCountStatus = true;
		} 
		Assert.assertTrue("DropDown has 3 Languages", languageCountStatus);
		
		String[] expLanguagesLabels = {"Continue","अग्रसर रहें","Continuar"};
		for (int i=languages.size()-1;i>=0;i--) {
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("(//button[@id='SI_0005']//div)[2]"))));
			languages.get(i).click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("(//button[@id='SI_0005']//div)[2]"))));
			String actlanguagesLabel = driver.findElement(By.xpath("(//button[@id='SI_0005']//div)[2]")).getText();
			Assert.assertEquals(actlanguagesLabel+" verify", expLanguagesLabels[i].toUpperCase(), actlanguagesLabel.toUpperCase());
			if(i!=0) {
				driver.findElement(By.xpath("//*[@class='accountDetailsLangDropDown']")).click();
				languages = driver.findElements(By.xpath("//*[@role='menuitem']"));//Loading the elements again to avoid the stale element exception
			}
			
		}
	}



	@When("click on setup parent support")
	public void click_on_setup_parent_support() {
		driver.findElement(By.linkText("Set up parent support")).click();
		driver.findElement(By.xpath("(//*[@class='acrCheckCodeButton'])[1]")).click();
	}

	@Then("filling the {string}, {string}, {string}, {string}, {string}, {string}")
	public void filling_the(String firstname, String lastname, String email, String cpusername , String cppassword, String cpassword) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//*[contains(@id,'undefined-undefined-Firstname')]"))));
		driver.findElement(By.xpath("//*[contains(@id,'undefined-undefined-Firstname')]")).sendKeys(firstname);
		driver.findElement(By.xpath("//*[contains(@id,'undefined-undefined-Lastname')]")).sendKeys(lastname);
		driver.findElement(By.xpath("//*[contains(@id,'undefined-undefined-Emailaddress')]")).sendKeys(email);
		driver.findElement(By.xpath("//*[contains(@id,'undefined-undefined-Createparentusername')]")).sendKeys(cpusername);
		driver.findElement(By.xpath("//*[contains(@id,'undefined-undefined-Createparentpassword')]")).sendKeys(cppassword);
		driver.findElement(By.xpath("//*[contains(@id,'undefined-undefined-Confirmpassword')]")).sendKeys(cpassword);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'CREATE ACCOUNT')]")));
	}

	


@Then("Close the Browser")
public void close_the_Browser() {
	driver.quit();
}

}
