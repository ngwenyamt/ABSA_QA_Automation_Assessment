package TestRunner;

import com.intuit.karate.Results;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TestRunner {

    @Test
    public void testParallel() {
        // Run the Karate tests in parallel and generate a Cucumber JSON report
        Results results = com.intuit.karate.Runner.path("src/test/java/FeatureFiles/PetFinderAPI.feature")
                .outputCucumberJson(true) // Enable Cucumber JSON output
//                .tags("") // Uncomment and specify tags to filter tests
                .reportDir("target/cucumber-reports") // Set the report directory
                .parallel(1); // Run tests in parallel with 1 thread

        // Assert that there are no test failures
        assertEquals(results.getErrorMessages(), 0, results.getFailCount());
        // Generate the Cucumber report
        generateReport(results.getReportDir());
    }

    public static void generateReport(String karateOutputPath) {
        // Collect all JSON files from the specified directory
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[]{"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));

        // Configure the Cucumber report
        Configuration config = new Configuration(new File("target"), "ABSA Assessment QA Automation");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        // Generate the Cucumber report
        reportBuilder.generateReports();
    }
}