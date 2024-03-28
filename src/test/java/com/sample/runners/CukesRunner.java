package com.sample.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","rerun:target/failedrerun.txt" } ,
        features = "src/test/resources/features",
        glue = "com/sample/step_definitions",
        dryRun = false,
        tags = "@goibibo"
)

public class CukesRunner{
}
