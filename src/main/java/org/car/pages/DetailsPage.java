package org.car.pages;

import org.car.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailsPage extends BasePage{
    public DetailsPage(){}

    @FindBy(id="EmailAddress")
    private WebElement emailAddressInput;
    @FindBy(id="Postcode")
    private WebElement postCodeInput;
    @FindBy(id="TelephoneNumber")
    private WebElement telephoneNumber;
    @FindBy(id="advance-btn")
    private WebElement advanceButton;

    public void fillEmailAddress(){
        emailAddressInput.sendKeys("test@hotmail.com");
    }
    public void fillPostcode(){
        postCodeInput.sendKeys("SW19 1JA");
    }
    public void fillTelephoneNumber(){
        telephoneNumber.sendKeys("07432123456");
    }
    public void clickAdvanceButton(){
        BrowserUtils.waitForClickablility(advanceButton, Duration.ofMinutes(1));
        advanceButton.click();
        emailAddressInput.clear();
        postCodeInput.clear();
        telephoneNumber.clear();

    }
    public boolean isOnDetailsPage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals("https://www.webuyanycar.com/vehicle/details");
    }
    public String getMake() {
        return getcarDetails().get("Manufacturer");
    }
    public String getModel() {
        return getcarDetails().get("Model");
    }
    public String getYear() {
        return getcarDetails().get("Year");
    }

    public Map<String, String> getcarDetails() {
        Map<String, String> carDetails = new HashMap<>();

        if (carDetails.isEmpty()) {
            try {
                List<WebElement> rows = driver.findElements(
                        By.xpath("//div[contains(@class,'details-vehicle-row')]"));

                for (WebElement row : rows) {
                    WebElement heading = row.findElement(
                            By.xpath(".//div[contains(@class,'heading')]"));
                    WebElement value = row.findElement(
                            By.xpath(".//div[contains(@class,'value')]"));

                    String headingText = heading.getAttribute("textContent");
                    if (headingText == null || headingText.isEmpty()) {
                        headingText = heading.getAttribute("innerHTML");
                    }
                    headingText = headingText.replace(":", "").trim();

                    String valueText = value.getAttribute("textContent");
                    if (valueText == null || valueText.isEmpty()) {
                        valueText = value.getAttribute("innerHTML");
                    }
                    valueText = valueText.trim();

                    if (!headingText.isEmpty() && !valueText.isEmpty()) {
                        carDetails.put(headingText, valueText);
                    }
                }
            } catch (Exception e) {
                System.out.println("Failed to get car details");
            }
        }
        return carDetails;
    }
}
