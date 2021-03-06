/*
 * Project Puerto Rico
 * Copyright (C) 2018  Thomas Pötzsch, Stephan Stroh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

import java.util.concurrent.TimeUnit;

public class StepDefinitions {

    private static WebDriver driver = null;

    private void waitForJS() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        try {
            Thread.sleep(300);
        } catch (InterruptedException ie) {
        }
    }

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
        waitForJS();
        System.out.println("This test is successful!");
    }

    @And("^this test fails$")
    public void thisTestFails() throws Throwable {
        waitForJS();
        System.out.println("This test fails!");
        throw new PendingException();
    }

    @When("^I visit \"([^\"]*)\"$")
    public void iVisit(String arg0) throws Throwable {
        waitForJS();
        driver.get(arg0);
    }

    @When("^I am on the homepage$")
    public void iVisitTheHomepage() throws Throwable {
        waitForJS();
        driver.get("http://localhost:8081/#/Home");
    }

    @Then("^the title should be \"([^\"]*)\"$")
    public void theTitleShouldBe(String arg0) throws Throwable {
        waitForJS();
        if (!driver.getTitle().equals(arg0)) {
            System.out.println("Failure! The title is '" + driver.getTitle() + "'!");
            throw new PendingException();
        }
    }

    @Given("^I am signed in with username \"(.*?)\" and password \"(.*?)\"$")
    public void iAmSignedIn(String arg0, String arg1) throws Throwable {
        waitForJS();
        driver.findElement(By.id("username")).sendKeys(arg0);
        driver.findElement(By.id("password")).sendKeys(arg1);
        driver.findElement(By.id("btnLogin")).click();
    }

    @Given("^I am on the \"(.*?)\" page$")
    public void iAmOnThePage(String url) throws Throwable {
        waitForJS();
        if (!driver.getCurrentUrl().split("\\?")[0].equals(url)) {
            System.out.println("I should be on the " + url + " page, even though I am on " + driver.getCurrentUrl());
            throw new PendingException();
        }
    }

    @When("^I enter \"(.*?)\" in the field \"(.*?)\"$")
    public void enterDataInField(String text, String id) {
        waitForJS();
        driver.findElement(By.id(id)).sendKeys(text);
    }

    @When("^I press the \"(.*?)\" button$")
    public void pressButton(String id) {
        waitForJS();
        driver.findElement(By.id(id)).click();
    }

    @Then("^I should see \"([^\"]*)\"$")
    public void iShouldSee(String string) throws Throwable {
        waitForJS();
        if (!driver.getPageSource().contains(string)) {
            throw new PendingException();
        }
    }

    @When("^I click on link having text \"([^\"]*)\"$")
    public void iClickOnLinkHavingText(String linkText) throws Throwable {
        waitForJS();
        driver.findElement(By.xpath("//a[contains(.,'" + linkText + "')]")).click();
    }

    @When("^I click on link having id \"([^\"]*)\"$")
    public void iClickOnLinkHavingId(String id) throws Throwable {
        waitForJS();
        driver.findElement(By.id(id)).click();
    }

    @When("^I click on button having text \"([^\"]*)\"$")
    public void iClickOnButtonHavingText(String buttonText) throws Throwable {
        waitForJS();
        driver.findElement(By.xpath("//button[contains(.,'" + buttonText + "')]"));
    }

    @And("^I should not see \"([^\"]*)\"$")
    public void iShouldNotSee(String string) throws Throwable {
        waitForJS();
        if (driver.getPageSource().contains(string)) {
            throw new PendingException();
        }
    }

    @And("^I wait for (\\d+) seconds$")
    public void iWaitForSeconds(int seconds) throws Throwable {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ie) {
        }
    }
}
