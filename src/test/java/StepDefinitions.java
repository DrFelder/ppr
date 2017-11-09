import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StepDefinitions {

    private static WebDriver driver = null;

    @Before
    public void setup() {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            System.setProperty("webdriver.chrome.driver", "build/chromedriver/win/chromedriver.exe");
        } else if (OS.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "build/chromedriver/mac/chromedriver");
        }
        // else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
        //     System.setProperty("webdriver.chrome.driver", "path");
        // }
        WebDriver driver = new ChromeDriver();
    }

    @After
    public void cleanup() {
        driver.close();
    }

    @Given("^this test is successful$")
    public void thisTestIsSuccessful() throws Throwable {
        System.out.println("This test is successful!");
    }

    @And("^this test fails$")
    public void thisTestFails() throws Throwable {
        System.out.println("This test fails!");
        throw new PendingException();
    }

    @When("^I visit \"([^\"]*)\"$")
    public void iVisit(String arg0) throws Throwable {
        driver.get(arg0);
        System.out.println(driver.getTitle());
        driver.close();
        driver.quit();
    }
}
