import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class StepDefinitions {
    @Given("^this test is successful$")
    public void thisTestIsSuccessful() throws Throwable {
        System.out.println("This test is successful!");
    }

    @And("^this test fails$")
    public void thisTestFails() throws Throwable {
        System.out.println("This test fails!");
        throw new PendingException();
    }
}
