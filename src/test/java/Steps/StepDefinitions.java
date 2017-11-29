package Steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
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
        driver.get("http://ppr.surreal.is:8080/");
    }

    @Then("^the title should be \"([^\"]*)\"$")
    public void theTitleShouldBe(String arg0) throws Throwable {
        if (!driver.getTitle().equals(arg0)) {
            System.out.println("Failure! The title is '" + driver.getTitle() + "'!");
            throw new PendingException();
        }
    }

    @Given("^I am signed in with username \"(.*?)\" and password \"(.*?)\"$")
    public void iAmSignedIn(String arg0, String arg1) throws Throwable{
        driver.findElement(By.id("username")).sendKeys(arg0);
        driver.findElement(By.id("password")).sendKeys(arg1);
        driver.findElement(By.id("btnLogin")).click();
    }

    @Given("^I am on the \"(.*?)\" page$")
    public void iAmOnThePage(String arg0) throws Throwable{
        //driver.get(baseUrl+"/#!" + arg0);
    }

    @When("^I enter \"(.*?)\" in the field \"(.*?)\"$")
    public void enterDataInField(String arg0, String arg1){
        driver.findElement(By.id("txt"+arg0)).sendKeys(arg1);
    }

    @When("^I press the \"(.*?)\" button$")
    public void pressButton(String arg0){
        driver.findElement(By.id("btn"+arg0)).click();
    }

    @Then("^I receive a \"(.*?)\" message$")
    public void receiveMessage(String arg0){
        //TODO
    }

    @Then("^I should see \"([^\"]*)\"$")
    public void iShouldSee(String string) throws Throwable {
        if (!driver.getPageSource().contains(string)) {
            throw new PendingException();
        }
    }

    @When("^I click on link having text \"([^\"]*)\"$")
    public void iClickOnLinkHavingText(String linkText) throws Throwable {
        driver.findElement(By.linkText(linkText)).click();
    }
}
