package org.shirokuma;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/org/shirokuma/features",
        glue = {"org.shirokuma.steps", "org.shirokuma.hooks"},
        plugin = {"json:target/destination/cucumber.json"},
        tags = "@LoginBlibliFeature"
)
public class Runner {



}
