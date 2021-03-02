package com.test.support;

import com.test.stepdefs.InitialIT;
import com.test.stepdefs.PageObjects;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import com.google.inject.Singleton;
import io.cucumber.guice.ScenarioScoped;

import org.openqa.selenium.support.ui.WebDriverWait;

// Scenario scoped it is used to show Guice
// what will be the shared classes/variables and instantiate them only in here
@ScenarioScoped
//@Singleton
      public class Global {
      public WebDriver driver;
       public WebDriverWait wait;
       public Helpers help;
       public PageObjects po;
      public Global() {
          //  driver = new DriverFactory().getManager();
         //   wait = new WebDriverWait(driver, 3000);
           // PageFactory.initElements(driver, this);
        //    PageFactory.initElements(driver, PageObjects.class );
              help = new Helpers();
      }

}
