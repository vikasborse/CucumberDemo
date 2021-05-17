package autoapp.automation.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
                    features = "src/test/resources/features",
                    glue = {"autoapp/automation/stepDef", "autoapp/automation/utility"},
                    tags = {"@cart"},
                    plugin = {"pretty", "html:target/cucumber-html-report","json:cucumber.json"}
                )
public class TestRunner {
}
