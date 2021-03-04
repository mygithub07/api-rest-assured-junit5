
package com.test.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;

import java.net.MalformedURLException;
import java.util.List;

import com.test.support.Global;
import com.test.support.Helpers;


public class InitialIT  {
       //  private static final Logger logger = LoggerFactory.getLogger(InitialIT.class);
         String hubUrl = System.getProperty("hub.url");
         String sutUrl = System.getProperty("sut.url");
         List<WebElement> result;
         public static  ChromeDriver driver ;
         public static Helpers help;
         public static WebDriverWait wait;
         
       
         @Inject
       Global global;

        @Given("I log something")
        public void logSomething( )   {
        System.out.println("sample text");
       }
        
 

        @Given("I open the site {string}")
        public void OpenSite(String site) throws MalformedURLException {
                global.driver.get(site);
       }

       @When("I add  user with the following information {string} {string} {string} {string} {string} {string}")
        public void AddUser(String fn,String ln,String uname,String pwd, String em, String cellphone) throws MalformedURLException, InterruptedException {
              
                PageObjects.AddUser.click();
                
                global.wait.until(ExpectedConditions.visibilityOf(PageObjects.fName));
                PageObjects.fName.sendKeys(fn);
                Thread.sleep(1000);
                PageObjects.lName.sendKeys(ln);
               Thread.sleep(1000);
                PageObjects.uName.sendKeys(uname);
               Thread.sleep(1000);
                PageObjects.pWord.sendKeys(pwd);
               Thread.sleep(1000);
                PageObjects.customer.get(0).click();
               Thread.sleep(1000);
                PageObjects.role.get(0).click();
                Thread.sleep(1000);
                PageObjects.sales.click();
               Thread.sleep(1000);
                PageObjects.email.sendKeys(em);
               Thread.sleep(1000);
                PageObjects.mphone.sendKeys(cellphone);
               Thread.sleep(1000);
                PageObjects.save.click();
               Thread.sleep(1000);
       }
       
      
   

}
