package org.car.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"
                , "html:target/cucumber.html"
                , "summary"
                , "me.jvt.cucumber.report.PrettyReports:target/cucumber-html-reports"
        }
        ,features = {"src/main/resources"}
        ,glue = {"org/car/steps"}
        ,snippets = CucumberOptions.SnippetType.CAMELCASE
        ,dryRun=false
        ,tags = "@e2e"
)
public class CukesRunner {
}
