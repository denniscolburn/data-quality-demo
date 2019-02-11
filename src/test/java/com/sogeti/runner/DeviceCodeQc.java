package com.sogeti.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = { "pretty", "html:target/cucumber/hadoop" },
        features = "classpath:features/",
        glue = "com.sogeti.steps",
        tags = {"~@wip", "@hadoop", "@device_code", "@data_quality"}
)
public class DeviceCodeQc {
}
