import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, tags = "not @skip", features = "src/test/Resources/Features")
public class RunCukesTest {
}