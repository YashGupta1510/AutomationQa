
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		glue = "stepDefinitions",
		features = "src/test/java/features",
		plugin= {"pretty", "html:target/test-report.html"},
		tags = "@all"
		)
public class AmazonRunnerTest {
}
