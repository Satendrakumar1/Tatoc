import java.util.*;
import java.io.File;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import java.sql.*;
import org.openqa.selenium.JavascriptExecutor;
import java.lang.ClassNotFoundException;

import javax.swing.text.Document;
public class TatocAdvanced {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		
		
	
		WebDriver driver = new FirefoxDriver();
		driver.get("http://10.0.1.86");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("html/body/div[1]/ul/li[5]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/a[2]")).click();
		
		
		WebElement mnEle=driver.findElement(By.cssSelector(".menutitle"));
		WebElement sbEle=driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/span[5]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(mnEle).perform();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/span[5]")).click();
		
		
        String dburl="jdbc:mysql://10.0.1.86/tatoc";
        String username="tatocuser";
        String password="tatoc01";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(dburl,username,password);
        String txt=driver.findElement(By.cssSelector("#symboldisplay")).getText();
        Statement stm=con.createStatement();
        ResultSet rs=stm.executeQuery("select id from identity where symbol= '"+txt+"'");
        int id=0;
        while(rs.next())
        {
        	id=rs.getInt(1);
        	System.out.print(id);
        }
        rs.close();
        ResultSet rs1= stm.executeQuery("select name,passkey from credentials where id='"+id+"'");
        while(rs1.next())
        {
        	String name=rs1.getString(1);
        	driver.findElement(By.cssSelector("#name")).sendKeys(name);
        	System.out.println(name);
        	String pwd=rs1.getString(2);
        	driver.findElement(By.cssSelector("#passkey")).sendKeys(pwd);
        	System.out.print(pwd);
        	
        }
        rs1.close();
        con.close();
        driver.findElement(By.cssSelector("#submit")).click();
        
     
        JavascriptExecutor js=(JavascriptExecutor) driver;
        Thread.sleep(1000);
    	js.executeScript("document.getElementsByClassName('video')[0].getElementsByTagName('object')[0].playMovie()");	
        Thread.sleep(2000);
    	double time =(double) js.executeScript("return document.getElementsByClassName('video')[0].getElementsByTagName('object')[0].getTotalTime()");
		int tym = (int) time;
		Thread.sleep(tym*1000+1000);
		driver.findElement(By.linkText("Proceed")).click();
	}

}
