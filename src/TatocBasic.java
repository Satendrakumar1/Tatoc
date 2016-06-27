

import java.util.*;


import java.io.File;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
public class TatocBasic {

	public static void main(String[] args) {
		
		//File binaryPath=new File("/home/satendrakumar1/Downloads/firefox/firefox");
		//FirefoxBinary ffbinary= new FirefoxBinary(binaryPath);
		FirefoxProfile ffProfile=new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffProfile);
		
		driver.get("http://10.0.1.86");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("html/body/div[1]/ul/li[5]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/a[1]")).click();
		driver.findElement(By.cssSelector(".greenbox")).click();
		
		driver.switchTo().frame(driver.findElement(By.id("main")));
		driver.findElement(By.xpath("html/body/center/a[1]")).click();
		
		String col1= driver.findElement(By.id("answer")).getAttribute("class");
		System.out.println(col1);
		
		driver.switchTo().frame(driver.findElement(By.id("child")));
		String col2= driver.findElement(By.id("answer")).getAttribute("class");
		System.out.println(col2);
		driver.switchTo().defaultContent();
		while(!col1.equals(col2))
		{
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.id("main")));
			driver.findElement(By.xpath("html/body/center/a[1]")).click();  
			driver.switchTo().frame(driver.findElement(By.id("child")));
			col2= driver.findElement(By.id("answer")).getAttribute("class");
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("main")));
		driver.findElement(By.xpath("html/body/center/a[2]")).click();
		
		
		WebElement element = driver.findElement(By.id("dragbox")); 
        WebElement target = driver.findElement(By.id("dropbox"));
        (new Actions(driver)).dragAndDrop(element, target).perform();
        driver.findElement(By.cssSelector(".page>a")).click();
        
        String mainWindowHandle=driver.getWindowHandle();
        driver.findElement(By.xpath("html/body/div[1]/div[2]/a[1]")).click();
        Set s = driver.getWindowHandles();
        Iterator ite = s.iterator();
        while(ite.hasNext())
        {
             String popupHandle=ite.next().toString();
             if(!popupHandle.contains(mainWindowHandle))
             {
                   driver.switchTo().window(popupHandle);
             }
        }
        driver.findElement(By.cssSelector("#name")).sendKeys("Satendra Kumar");
        driver.findElement(By.cssSelector("#submit")).click();
        driver.switchTo().window( mainWindowHandle );
        driver.findElement(By.xpath("html/body/div[1]/div[2]/a[2]")).click();
        
        
        driver.findElement(By.xpath("html/body/div[1]/div[2]/a[1]")).click();
        String val= driver.findElement(By.xpath(".//*[@id='token']")).getText();
        
        String cookie=val.substring(7);
        System.out.println(cookie);
              
        Cookie name = new Cookie("Token", cookie);
	 	driver.manage().addCookie(name);
        driver.findElement(By.xpath("html/body/div[1]/div[2]/a[2]")).click();		
		
	
	}

}
