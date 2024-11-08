package org.car.pages;

import org.car.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
public abstract class BasePage {
    protected WebDriver driver;
    public BasePage() {
        driver = Driver.get();
        PageFactory.initElements(driver, this);
    }
}
