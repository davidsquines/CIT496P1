import java.io.*;
import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;	
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.junit.Assert;
import java.util.List;
public class htmlUnitTest {				

	public static void main(String[] args) {

		// Creating a new instance of the HTML unit driver
		WebDriver driver = new HtmlUnitDriver(true); //enable JavaScript
		              
		// Navigate to local Jekyll site
		driver.get("http://localhost:4000");					
          
		// This code will test and print the page title
		String pageTitle = driver.getTitle();
		try{
			Assert.assertNotEquals(pageTitle, "");
		}catch (AssertionError e){
			System.out.println("Page must have a title. Please Fix");
			throw e;
		}
		Assert.assertFalse(pageTitle.matches("^\\s*$")); // uses regexp
		System.out.println("Page title is: " + pageTitle);

		// This code will check the HTML source for Jekyll 404 errors
		String pageSource = driver.getPageSource();
		try{
			Assert.assertFalse(pageSource.contains("404.html"));
		}catch(AssertionError e){
			System.out.println("404 Error found. Please fix fix this error");
			Assert.assertFalse(pageSource.contains("404.html"));
			throw e;
		}	
		Assert.assertFalse(pageSource.contains("requested page could not be found")); 

		//Create a test that is specific to YOUR Jekyll site her
		List<WebElement> usernames = driver.findElements(By.className("username"));
		for(WebElement element: usernames){

			String name = element.getText();
			Assert.assertEquals("davidsquines",name);
			System.out.print(name + " ");
		}
		System.out.println();
		driver.quit();			
	}
}	
