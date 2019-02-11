package com.sogeti.steps;

import com.sogeti.helpers.ConfigHelper;
import com.sogeti.helpers.StringHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class DataQualitySteps {

    private ConfigHelper queries;
    private ConfigHelper env;
    HashMap<String, Object> queryVars = new HashMap<String, Object>();
    private Statement hiveStatement;
    private Connection hiveConnection;

    @Before("@data_quality")
    public void setup(Scenario scenario) {

        String queriesYaml = System.getProperty("user.dir") + "/src/test/resources/config/queries.yaml";
        queries = new ConfigHelper(queriesYaml);

        String envYaml = System.getProperty("user.dir") + "/src/test/resources/config/env.yaml";
        env = new ConfigHelper(envYaml);

        HashMap<String, String> envData = (HashMap<String, String>) env.getConfiguration("dev");

        String hiveUrl = (String) envData.get("hive_url");
        String hiveUsername = (String) envData.get("hive_username");
        String hivePassword = (String) envData.get("hive_password");

        try {
            hiveConnection = DriverManager.getConnection(hiveUrl, hiveUsername, hivePassword);
            hiveStatement = hiveConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @After("@hadoop")
    public void teardown() {
        try {
            hiveConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @When("^I look for intervals with null \"([^\"]*)\" for authority \"([^\"]*)\"$")
    public void i_look_for_intervals_with_null_for_authority(String column, String authority) throws Throwable {

        String query = (String) queries.get("Get the number of intervals with null device codes");
        queryVars.put("<<authority>>", authority);
        queryVars.put("<<max_summary_dttm>>", "2001-01-01 00:00:00");
        String formattedQuery = StringHelper.formatString(query, queryVars);
        System.out.println(formattedQuery);

    }

    @Then("^I should not find any intervals with null \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_should_not_find_any_intervals_with_null_for(String column, String authority) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Then step!");
    }
}
