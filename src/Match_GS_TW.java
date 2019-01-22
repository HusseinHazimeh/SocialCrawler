import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import weka.core.converters.ArffLoader;


@SuppressWarnings("unused")
public class Match_GS_TW {
	
	private WebDriver driver;
	private static ArffLoader loader = new ArffLoader();
	
	public Match_GS_TW(WebDriver driver)
	{
		this.driver = driver;
	}
	
//	public String Match(GoogleScholar_Author gsa, String algorithm) throws Exception
//	{
//		driver.manage().window().setSize(new Dimension(1024,768));
//		driver.get("https://www.twitter.com/login");
//	    driver.findElement(By.name("user[email]")).sendKeys("husseinyoussef.hazimeh@hes-so.com");
//	    driver.findElement(By.name("user[user_password]")).sendKeys("abc123+");
//	    driver.findElement(By.xpath(".//*[@class='gsc_prf_inta gs_ibl']")).click();
//		return "";
//	}
	
	public String Match(GoogleScholar_Author gsa, String algorithm) 
	{
		driver.manage().window().setSize(new Dimension(1024,768));
		driver.get("https://www.twitter.com/login"); //js-username-field email-input js-initial-focus
	    driver.findElement(By.xpath(".//*[@class='js-username-field email-input js-initial-focus']")).sendKeys("hassanhazimeh2009@gmail.com");;
	    driver.findElement(By.className("js-password-field")).sendKeys("abc123+");
	    driver.findElement(By.xpath(".//*[@class='submit EdgeButton EdgeButton--primary EdgeButtom--medium']")).click();
	    driver.get("https://twitter.com/search?f=users&vertical=default&q=" + gsa.get_author_name().replace(" ", "%20") + "&src=typd");
	    driver.manage().window().setSize(new Dimension(1024,1024));
	    
	    List<WebElement> profiles = driver.findElements(By.xpath(".//*[@class='fullname ProfileNameTruncated-link u-textInheritColor js-nav']"));
	    
	    System.out.println(profiles.size());
	    
	    ArrayList<String> al = new ArrayList<String>();
	    
	    for(int i=0; i<profiles.size();i++){
	    	  
	    	  String link = profiles.get(i).getAttribute("href").toString();
	    	  al.add(link);
	    }
	     
	    for(int t = 0; t < al.size(); t++) {
	    	   
	          System.out.println(al.get(t));
	          driver.get(al.get(t));

	          String location = driver.findElement(By.xpath(".//*[@class='ProfileHeaderCard-locationText u-dir']")).getText();
	          String bio1 = driver.findElement(By.xpath(".//*[@class='ProfileHeaderCard-bio u-dir']")).getText();
	          
	          System.out.println("profile: " + al.get(t) + " location: " + location + " bio: " + bio1);
	          
	          String loc = gsa.get_location();
	          String _loc = location;
	          
	          String bio = gsa.get_affiliation();
	          String _bio = bio;
	          
	          String screenname = gsa.get_author_name();
	          String _screenname = profiles.get(t).getText().toString();
	          
	          System.out.println("loc " + loc + " _loc " + _loc + " bio " + bio + " _bio " + _bio + "screenname " + screenname + " _screenname " + _screenname );

	      } 
	    return "";
	    
	}
	
	public static void main(String [] args)
	{
		System.setProperty("webdriver.chrome.driver", "C:/Users/husseiny.hazimeh/Desktop/chromedriver_win32/chromedriver.exe");
		Match_GS_TW m = new Match_GS_TW(new ChromeDriver());
		GoogleScholar_Author gsa = new GoogleScholar_Author();
		gsa.set_author_name("hussein hazimeh");
		m.Match(gsa, "svm");
	}
}
