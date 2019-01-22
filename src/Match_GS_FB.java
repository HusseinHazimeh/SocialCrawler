import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

import com.graphscholar.classifierstack.Classifier;

/**
 * @author Hussein Hazimeh April 2018,
 * Match a google scholar instance with a facebook instance by using a user predefined machine learning algorithm
 * 
 *
 */
@SuppressWarnings("unused")
public class Match_GS_FB {

		private WebDriver driver;
		
		
		public Match_GS_FB(WebDriver driver)
		{
			this.driver = driver;
		}
		
		public static void main(String args []) throws Exception{
			
			GoogleScholar_Author gsa = new GoogleScholar_Author();
			gsa.set_affiliation("Professor of Biomedical Data Sciences, and of Statistics, Stanford University");
			gsa.set_location("Stanford");
			gsa.set_author_name("Robert Tibshirani");
			Match_GS_FB m = new Match_GS_FB(null);
			System.out.println(m.Match(gsa, "svm"));
			
		}
				
		/**
		 * @param gsa object
		 * @return Facebook profile URL 
		 * @throws Exception
		 */
		public String Match(GoogleScholar_Author gsa, String algorithm) throws Exception
		{
			  //remove 
			  //System.setProperty("webdriver.chrome.driver", "C:/Users/husseiny.hazimeh/Desktop/chromedriver_win32/chromedriver.exe");
			  //driver = new ChromeDriver();
			  
	          driver.manage().window().setSize(new Dimension(1024,768));
			  driver.get("https://www.facebook.com/");
		      driver.findElement(By.name("email")).sendKeys("husseinyoussef.hazimeh@hes-so.com");
		      driver.findElement(By.name("pass")).sendKeys("abc123+");
		      driver.findElement(By.id("loginbutton")).click();
		      
		      driver.get("https://www.facebook.com/search/people/?q="+gsa.get_author_name().replace(" ", "%20"));
		      
		      driver.manage().window().setSize(new Dimension(1024,1024));
		      
		      List<WebElement> profiles = driver.findElements(By.className("_32mo"));
		      
		      ArrayList<String> hm = new ArrayList<String>();
		      
		      for(int i=0; i<profiles.size();i++){
		    	  
		    	  String link = profiles.get(i).getAttribute("href").toString();
		    	  //String screenname = profiles.get(i).getText();
		    	  hm.add(link);
		    	  //driver.get(link);
		    	  //System.out.println(profiles.get(i).getText());
		      }
		      
		      String _fbURL = "";
		      
		      double x = 0;
		      double y = 0;
		      double z = 0;
		      double w = 0;
		      
		      String TestVector = "";
		      
		      for (int t = 0; t < hm.size(); t++) {
		    	  
		          System.out.println(hm.get(t));
		          driver.get(hm.get(t));
		          
		          List<WebElement> occupations = driver.findElements(By.className("profileLink"));
		          
		          for(int j=0; j<occupations.size(); j++) {
		        	  
		        	  x = (new MachineLearningParameters()).compare_by_organization(gsa.get_affiliation(), occupations.get(j).getText());
		        	  DecimalFormat df = new DecimalFormat("#.#");
		        	  x = Double.valueOf(df.format(x));
		        	  
		        	  y = (new MachineLearningParameters()).compare_by_location(gsa.get_location(), occupations.get(j).getText()); 
		        	  
		        	  if(j == 0)
		        		  w = (new MachineLearningParameters()).compare_by_screenname(gsa.get_author_name(), occupations.get(j).getText());
		        	  
		        	      System.out.println("w score is : " + w);
		        		  double [] d = {x,y,z,w};
		        		  
		        		  String _class = (new MachineLearningParameters()).getClass(d, algorithm);
		        		  
		        		  System.out.println(_class);
		        		  
		        		  if(_class.toLowerCase().equals("match"))
		        		  {
		        			  TestVector = "Test vector = " + x + ", " + y + ", " + z + ", " + w;
		        			  _fbURL = hm.get(t);
		        			  break;
		        		  }
		        		  
		        	  System.out.println(occupations.get(j).getText());
		          } 
		      } 
			   
			  System.out.println(TestVector);
			  return _fbURL;
		}
}