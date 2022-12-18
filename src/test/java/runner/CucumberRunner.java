package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/"
        , glue = "com.hepsiburada.qa.tests",
        plugin = {"pretty", "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/reports/index.html"}, monochrome = true)
public class CucumberRunner extends AbstractTestNGCucumberTests {

}