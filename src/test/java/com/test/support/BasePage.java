
/******Old way of Injecting*********
 
package com.test.support;

import org.openqa.selenium.support.PageFactory;
import com.test.support.Global;

public class BasePage {

         protected Global global;
        
        // Initiate the Page Factory and create as abstract class, so
         // you can use for all the other Page Objects
         public BasePage(Global global) {
         this.global = global;
       //  PageFactory.initElements(global.driver, this);
         
         }
}
*/