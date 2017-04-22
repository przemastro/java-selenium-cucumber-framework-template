import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * I tried to use junit, but I couldn't find working junit-cucumber versions.
 * So I used my favourite testng framework.
 */
@CucumberOptions(plugin = "html:target/html-report",
        features = "src/test/java/features/",
        glue = "steps",
        tags= "@smokeTest")
public class Runner extends AbstractTestNGCucumberTests {
}