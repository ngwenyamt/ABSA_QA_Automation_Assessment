package StepDefs.UserManagement;

import MainClasses.MainClass_BDD;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * This class contains the hooks to be executed before and after each scenario.
 */
public class WebHooks extends MainClass_BDD {

    // Method to be executed before each scenario
    @Before()
    public void BeforeScenario() throws MalformedURLException {
        launchBrowser();
    }
    // Method to be executed after each scenario
    @After()
    public void afterScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            // Add code to handle scenario failure if needed
        }
//        closeBrowser(); // method to close the browser after each scenario
    }
}