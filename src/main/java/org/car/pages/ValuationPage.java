package org.car.pages;

import org.car.utils.BrowserUtils;
import org.car.utils.ConfigurationReader;
import org.car.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class ValuationPage extends BasePage{
    public ValuationPage(){}
    @FindBy(id="onetrust-accept-btn-handler")
    private WebElement acceptCookies;
    @FindBy(id ="vehicleReg")
    private WebElement regInput;
    @FindBy(id ="Mileage")
    private WebElement mileageInput;
    @FindBy(id ="btn-go")
    private WebElement submitButton;
    @FindBy(id="e2e-valueDifferentVehicle")
    private WebElement evaluateDifferentVehicle;
    public void launchWebApp(){
        String url = ConfigurationReader.get("url");
        BrowserUtils.waitForPageToLoad(Duration.ofMinutes(2));
        Driver.get().get(url);
    }
    public void clickAcceptCookies() {
        acceptCookies.click();
    }
    public void fillRegInput(String regNumber){
        regInput.click();
        regInput.sendKeys(regNumber);
    }
    public void fillMileageInput(){
        mileageInput.sendKeys("20000");
    }
    public void clickSubmitButton(){
        submitButton.click();
    }
    public void clickEvaluateDifferentVehicle(){
        evaluateDifferentVehicle.click();
    }
}
