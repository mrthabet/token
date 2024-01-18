package token;
import java.io.IOException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.junit.Test;





public class token {   
	private WebDriver driver;

@BeforeEach
public static void main(String[] args) throws IOException {

    // قم بتنزيل أحدث إصدار من ChromeDriver تلقائيًا
    io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

    // إنشاء متصفح Chrome
    WebDriver driver = new ChromeDriver();
    driver.get("https://iso.digitaltransformationinstitute.org:11102/");
}

@AfterEach
public void tearDown() throws Exception {
    driver.quit()
}

@Test
public void testScreenshot() throws Exception {
    // Create a new WebDriver instance
    driver.get("https://iso.digitaltransformationinstitute.org:11102/");

    // Click on the "Create Framework" button
    driver.findElement(By.id("create-framework-button")).click();

    // Enter the framework name
    driver.findElement(By.id("framework-name")).sendKeys("My Framework");

    // Enter the framework description
    driver.findElement(By.id("framework-description")).sendKeys("This is my framework");

    // Select the framework level
    int numberOfLevels = getNumberOfLevels(driver.getCurrentUrl());
    for (int i = 1; i <= numberOfLevels; i++) {
        driver.findElement(By.xpath("//*[@id=\"framework-levels\"]/tbody/tr[" + i + "]/td[1]/input")).sendKeys("Level " + i);
    }

    // Select the framework team
    driver.findElement(By.id("framework-team")).sendKeys("My Team");

    // Get the number of team members
    int numberOfTeamMembers = getNumberOfLevels(driver.getCurrentUrl());
    for (int i = 1; i <= numberOfTeamMembers; i++) {
        driver.findElement(By.xpath("//*[@id=\"framework-team-members\"]/tbody/tr[" + i + "]/td[1]/input")).sendKeys("Team Member " + i);
    }

    // Click on the "Save" button
    driver.findElement(By.id("save-framework-button")).click();

    // Get the framework URL
    String frameworkUrl = getFrameworkUrl(driver.getCurrentUrl());

    // Check if the framework is listed in the data table
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frameworks-list\"]/tbody/tr[1]/td[2]/a")));
    String frameworkNameInDataTable = driver.findElement(By.xpath("//*[@id=\"frameworks-list\"]/tbody/tr[1]/td[2]/a")).getText();
    assertEquals("My Framework", frameworkNameInDataTable);

    // Navigate to the framework
    driver.get(frameworkUrl);

    // Take a screenshot
  // driver.saveScreenshot("screenshot-framework.png");
}

private void assertEquals(String string, String frameworkNameInDataTable) {
	// TODO Auto-generated method stub
	
}

private static int getNumberOfLevels(String url) throws Exception {
    WebDriver driver = new ChromeDriver();
    driver.get(url);

    // Get the number of levels
    int numberOfLevels = driver.findElements(By.xpath("//*[@id=\"framework-levels\"]/tbody/tr")).size();

    // Close the browser
    driver.quit();

    
}



}
