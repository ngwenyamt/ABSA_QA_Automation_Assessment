// File: src/test/java/TestRunners/TestRunner.java
package TestRunners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/FeatureFiles/UserManagement/UserManagement.feature",
        glue = {"StepDefs"},
//        tags = "",
        plugin = {"json:target/cucumber.json", "html:target/cucumber-html-report.html"},
        monochrome = true
)
public class TestRunner {

}
