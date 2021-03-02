package com.test.support;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class Helpers     {

        public  WebDriverWait wait;

         private final Logger logger = LoggerFactory.getLogger(Helpers.class);

         public List<WebElement> List1;
         public List<WebElement> getList1() { return this.List1; }
         public void setList1(List<WebElement> List1) { this.List1 = List1; }

         public     List<WebElement> List2;
         public List<WebElement> getList2() { return this.List2; }
        public void setList2(List<WebElement> List2) { this.List2 = List2; }

         public boolean switchWindow(String title, WebDriver d) {

        String currentWindow = d.getWindowHandle();
         Set<String> availableWindows = d.getWindowHandles();
         if (!availableWindows.isEmpty()) {
         for (String windowId : availableWindows) {
        if (d.switchTo().window(windowId).getTitle().equals(title)) {
        return true;
         } else {
                 d.switchTo().window(currentWindow);
                    }
                        }
                     }

             return false;
        }
         public  String guiceTest(){
          return ("guice test");
          
         }
}

