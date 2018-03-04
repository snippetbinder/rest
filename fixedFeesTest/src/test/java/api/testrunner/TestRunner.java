package api.testrunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "json:target/testReport.json" },features="Features"
				,monochrome=true)

public class TestRunner {

}


//tags= {"@tag21"},