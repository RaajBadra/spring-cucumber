package com.test.sampleapp.integration;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Created by Raja.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources"})
public class CucumberIntegrationIT {
}
