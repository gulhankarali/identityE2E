package org.car.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.car.pages.DetailsPage;
import org.car.pages.ValuationPage;
import org.car.utils.BrowserUtils;
import org.car.utils.CarDataParser;
import org.car.utils.RegistrationFinder;
import org.car.utils.SaveTestResults;
import org.junit.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class StepDefs {
    ValuationPage valuationPage = new ValuationPage();
    DetailsPage detailsPage = new DetailsPage();

    @Given("I am on the main page for car valuation")
    public void i_am_on_the_main_page_for_car_valuation() throws InterruptedException {
        valuationPage.launchWebApp();
        valuationPage.clickAcceptCookies();
    }
    @When("I enter car registrations number in the input file and mileage")
    public void iEnterCarRegistrationsNumberInTheInputFileAndMilage() throws IOException {
        List<String> regNumberList = RegistrationFinder.registrationList("src/main/resources/files/car_input.txt");
        Map<String, String[]> expectedValues = CarDataParser.parseCarData("src/main/resources/files/car_output.txt");

        for (String regNumber: regNumberList) {
            BrowserUtils.waitForPageToLoad(Duration.ofMinutes(1));
            valuationPage.fillRegInput(regNumber);
            valuationPage.fillMileageInput();
            valuationPage.clickSubmitButton();
            if (detailsPage.isOnDetailsPage()){
                BrowserUtils.waitForPageToLoad(Duration.ofMinutes(3));
                detailsPage.fillEmailAddress();
                detailsPage.fillPostcode();
                detailsPage.fillTelephoneNumber();

                String trimmedRegNumber = regNumber.replaceAll("\\s+", "");
                String[] expectedResults = expectedValues.get(trimmedRegNumber);

                Assert.assertEquals("Mismatch in registration number",
                        expectedResults[0].replaceAll("\\s+", ""),
                        regNumber.replaceAll("\\s+", ""));
                Assert.assertEquals("Mismatch in make for registration number: "+regNumber,expectedResults[1],detailsPage.getMake());
                Assert.assertEquals("Mismatch in model for registration number: "+regNumber,expectedResults[2],detailsPage.getModel());
                Assert.assertEquals("Mismatch in year for registration number: "+regNumber,expectedResults[3],detailsPage.getYear());
                SaveTestResults saveTestResults = new SaveTestResults("src/main/resources/files/passed_regs.txt");
                saveTestResults.logRegistration(regNumber);
                saveTestResults.close();
                detailsPage.clickAdvanceButton();
            } else {
                    SaveTestResults saveTestResults = new SaveTestResults("src/main/resources/files/failed_regs.txt");
                    saveTestResults.logRegistration(regNumber);
                    saveTestResults.close();
            }
            BrowserUtils.waitForPageToLoad(Duration.ofMinutes(2));
            valuationPage.launchWebApp();
            BrowserUtils.waitForPageToLoad(Duration.ofMinutes(2));
            valuationPage.clickEvaluateDifferentVehicle();
        }
    }
    @Then("I can verify passed and failed registration numbers")
    public void iCanVerifyPassedAndFailedRegistrationNumbers() {
//        TODO
    }
}
