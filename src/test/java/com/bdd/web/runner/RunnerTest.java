package com.bdd.web.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
/**
 * @author Karla Ccallo
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"json:target/build/cucumber.json"},
        publish = true,
        features = "src/test/resources/features",
        glue = "com.bdd.web.glue",
        stepNotifications = true,
        tags = "@HAPPY_PATH_CARRITO"
)
public class RunnerTest {
}

