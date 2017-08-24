package com.servicenow.genericlibraries;

import org.openqa.selenium.WebElement;

public class TextBoxes {

    public static void enterTextValue(WebElement element,String textData,String fieldName)
    {
           try
           {
                  ReporterLogs.log("Entering " + fieldName, "info");
                  element.sendKeys(textData);
                  
           }
           catch(Exception e)
           {
                  ReporterLogs.log(e.getMessage());
           }
    }

}
