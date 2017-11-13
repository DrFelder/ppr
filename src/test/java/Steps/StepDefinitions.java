package Steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class StepDefinitions {

    private static WebDriver driver = null;

    @Before
    public void setup() {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            System.setProperty("webdriver.chrome.driver", "build/chromedriver/win/chromedriver.exe");
        } else if (OS.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "build/chromedriver/mac/chromedriver");
        } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
            System.setProperty("webdriver.chrome.driver", "build/chromedriver/linux/chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--no-first-run");
        options.addArguments("--disable-default-apps");
        driver = new ChromeDriver(options);
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
    }

    @When("^I am on the homepage$")
    public void iVisitTheHomepage() throws Throwable {
        driver.get("http://ppr.surreal.is/");
    }

    @Then("^the title should be \"([^\"]*)\"$")
    public void theTitleShouldBe(String arg0) throws Throwable {
        if (!driver.getTitle().equals(arg0)) {
            System.out.println("Failure! The title is '" + driver.getTitle() + "'!");
            throw new PendingException();
        }
    }
}
