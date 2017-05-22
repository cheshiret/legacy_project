package com.activenetwork.qa.testdriver.selenium;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.JavascriptExecutor;


public class OpenUrl {
    public static void main(String []args){
        		String url = "http://www.baidu.com";
        		String url2 = "http://www.google.com";
        		//Chrome part
        		
        		//Fix the Launch Chrome issue You are using an unsupported command-line flag: --disable-web-security. Stability and security will suffer.
//    			System.setProperty("webdriver.chrome.driver",
//    					//"D:\\AWOWorkspace\\functest4_selenium_driver\\bin\\com\\activenetwork\\qa\\testdriver\\selenium\\chromedriver.exe");
//    					"C:\\Users\\tchen\\Desktop\\chromedriver_win32\\chromedriver.exe");
//    			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//    			ChromeOptions options = new ChromeOptions();
//
//    			options.addArguments("test-type");
//
//    			capabilities.setCapability("chrome.binary",
//    					//"D:\\AWOWorkspace\\functest4_selenium_driver\\bin\\com\\activenetwork\\qa\\testdriver\\selenium\\chromedriver.exe");
//    					"C:\\Users\\tchen\\Desktop\\chromedriver_win32\\chromedriver.exe");
//    			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//
//              ChromeDriver driver = new ChromeDriver(options);
                
              //IE Part
              WebDriver driver = new InternetExplorerDriver();
              driver.get(url);
              
              


    	
         
              //get
//              driver.get(url);
             
              //navigate
//              driver.navigate().to(url);                     

              
              //get title
//              String title = driver.getTitle();

             //get current url
//              String currentUrl = driver.getCurrentUrl();
             
             //print title and url
//              System.out.println(title+"\n"+currentUrl);

              //Return All Handle
              Set<String> winhandles1 = driver.getWindowHandles();
    
              //Return Current Handle
              String winhandle1 = driver.getWindowHandle();

              //Iterator<String> it = winhandles1.iterator();
//              while (it.hasNext()) {  
//            	  String str = it.next();  
//            	  System.out.println(str);  
//            	}

              
              
              //force to open a window
              ((JavascriptExecutor)driver).executeScript("window.open();");
              
              Set<String> winhandles2 = driver.getWindowHandles();
              winhandles2.removeAll(winhandles1);

              //Return Page Source
//              String getpagesource = driver.getPageSource();
//              System.out.println(winhandle1+"\n"+getpagesource);
              
              
              String winhandle2 = ((String)winhandles2.toArray()[0]);
              driver.switchTo().window(winhandle2);
              driver.get(url2);
              driver.switchTo().window(winhandle1);
              System.out.println(winhandle1+"\n"+winhandle2);
//              WebDriver window = driver.switchTo().window(it.next());
              //close。 Close the current window, quit the browser if it is the last window currently open.
              driver.close();
              // quit， Quit this driver, closing every associated windows              
              driver.quit();            
              //close the webdriver process
              System.exit(-1);


    }
	    public boolean switchToWindow(WebDriver driver,String windowTitle){  
	        boolean flag = false;  
	        try {  
	            String currentHandle = driver.getWindowHandle();  
	            Set<String> handles = driver.getWindowHandles();  
	            for (String s : handles) {  
	                if (s.equals(currentHandle))  
	                    continue;  
	                else {  
	                    driver.switchTo().window(s);  
	                    if (driver.getTitle().contains(windowTitle)) {  
	                        flag = true;  
	                        System.out.println("Switch to window: "  
	                                + windowTitle + " successfully!");  
	                        break;  
	                    } else  
	                        continue;  
	                }  
	            }  
	        } catch (NoSuchWindowException e) {  
	            System.out.println("Window: " + windowTitle  
	                    + " cound not found!" + e.fillInStackTrace().toString());  
	            flag = false;  
	        }  
	        return flag;  
	    } 

}
