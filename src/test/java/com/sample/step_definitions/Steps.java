package com.sample.step_definitions;

import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;
import com.sample.context.TestContext;
import com.sample.pages.HomePage;
import com.sample.utilities.BrowserUtils;
import com.sample.utilities.Driver;
import com.sample.utilities.Waits;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class Steps {


    BrowserUtils browserutils;
    Waits waits ;
    TestContext testContext;

    HomePage homePage;


    public Steps(TestContext context){
        browserutils = new BrowserUtils();
        waits = new Waits();
        this.testContext = context;
        homePage = new HomePage();

    }

    @Given("I launch goibibo")
    public void iLaunchGoibibo()  {
        Driver.getDriver().get("https://goibibo.com/");
        homePage.CloseLoginPopup();
    }

    @When("I Enter from {string} and to {string} details")
    public void iEnterFromAndToDetails(String from, String to) {
        homePage.EnterFromCity(from);
        homePage.EnterToCity(to);
    }

    @And("I Enter traveller details")
    public void iEnterTravellerDetails() {
        homePage.SetDepartureDate();
        homePage.SelectTraveller();
    }

    @Then("Verify search results")
    public void verifySearchResults() throws Exception {
        homePage.SearchFlights();
    }
}
