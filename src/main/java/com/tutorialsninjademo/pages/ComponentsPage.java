package com.tutorialsninjademo.pages;

import com.aventstack.extentreports.Status;
import com.tutorialsninjademo.customlisteners.CustomListeners;
import com.tutorialsninjademo.utilities.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class ComponentsPage extends Utility {
    @CacheLookup
    @FindBy(xpath = "//h2[contains(text(),'Components')]")
    WebElement componentsText;

    public String getComponentsText() {
        Reporter.log("Getting components text " + componentsText.toString());
        CustomListeners.test.log(Status.PASS, "Get components text");
        return getTextFromElement(componentsText);
    }
}
