package org.car.steps;

import io.cucumber.java.*;
import org.car.utils.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @Before
    public void setUpTest(){
        Driver.get().manage().window();
    }
    @After
    public void tearDownTest(Scenario scenario){
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach (screenshot,"image/png","screenshot");
        }
    }
    @AfterAll
    public static void tearDown(){
        Driver.closeDriver();
    }
}
