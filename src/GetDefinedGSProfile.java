import java.util.ArrayList; 
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author hussein.hazimeh 02.March.2018
 * Get scholarly data from google scholar 
 *
 */

public class GetDefinedGSProfile 
{
	 public static ArrayList<GoogleScholar_Author> authors_list = new ArrayList<GoogleScholar_Author>();
	 public static Connection con;
	 
	 public static void main(String[] args) 
	 {
		 //GetDefinedGSProfile o = new GetDefinedGSProfile();
		 //o.crawl("https://scholar.google.ch/citations?user=CfBSrbQAAAAJ&hl=en&oi=ao", "Hussein Hazimeh");
	 }
	 
	 public GoogleScholar_Author crawl(String URL, String Name, String algorithm) throws Exception
	 {
		 	System.setProperty("webdriver.chrome.driver", "C:/Users/husseiny.hazimeh/Desktop/chromedriver_win32/chromedriver.exe");
	        WebDriver driver = new ChromeDriver();

	         driver.get(URL);
	         driver.manage().window().setSize(new Dimension(1024,768));
	         
	             List<WebElement> rows = driver.findElements(By.className("gsc_prf_il"));
		         List<WebElement> citations = driver.findElements(By.className("gsc_rsb_std"));
		          
		         	 GoogleScholar_Author gsa = new GoogleScholar_Author();
		        	 gsa.set_author_name(Name);
		        	 gsa.set_affiliation(rows.get(0).getText());
		        	 gsa.set_n_of_citations(Integer.parseInt(citations.get(1).getText()));
		        	 gsa.set_location("l");
		        	 List<WebElement> interests_parent = driver.findElements(By.xpath(".//*[@class='gsc_prf_inta gs_ibl']"));
		        	 ArrayList<String> interests_values = new ArrayList<String>();
		        	 
		        	 for(int k=0; k<interests_parent.size(); k++)
		        	 {
		        		 interests_values.add(interests_parent.get(k).getText());
		        	 }
		        	 
		        	 gsa.set_research_fields(interests_values);
		        	 gsa.set_profile_url(URL);
		        	 
		        	 System.out.println(gsa.get_n_of_citations());
		        	 
		        	 Match_GS_FB mgsfb = new Match_GS_FB(driver);
		        	 gsa.set_fb_url(mgsfb.Match(gsa, algorithm));
		        	 
		        	 Match_GS_TW mgstw = new Match_GS_TW(driver);
		        	 gsa.set_tw_url(mgstw.Match(gsa, algorithm));
		        	 
		        	 return gsa;
		         
	        // try {
				//con = connectMe();
			//} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
//	           insert_into_mysql(
//	        			 authors_list.get(i).get_author_name(), authors_list.get(i).get_affiliation(), 
//	        			 authors_list.get(i).get_research_fields(), 
//	        			 authors_list.get(i).get_n_of_citations(), 
//	        			 authors_list.get(i).get_profile_url(), 8);
	         
	 }
	 
	 public static void insert_into_mysql(String GSAN, String GSAA, ArrayList<String> GSAI, int GSAC, String GSAP, int Domain)
	 {
		    try {
				 String query = " insert into googlescholar (GSAN, GSAA, GSAI, GSAC, GSAP, GSDomain)"
					        + " values (?, ?, ?, ?, ?, ?)";
					      java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
					      
					      preparedStmt.setString (1, GSAN);
					      preparedStmt.setString(2, GSAA);
					      StringBuilder sb = new StringBuilder();
					      for (String s : GSAI)
					      {
					          sb.append(s);
					          sb.append(",");
					      }
					      preparedStmt.setString(3, sb.toString());
					      preparedStmt.setInt(4, GSAC);
					      preparedStmt.setString(5, GSAP);
					      preparedStmt.setInt(6, Domain);
					      
					      preparedStmt.addBatch();
					      
					      preparedStmt.clearParameters();
				  
					      preparedStmt.executeBatch();
					      
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 public static Connection connectMe() throws SQLException
	 {
			String url = "jdbc:mysql://localhost/SocialEye?useUnicode=true&character_set_server=utf8mb4";
			String username = "root";
			String password = "";
	
			System.out.println("Connecting database...");
	
			Connection connection = DriverManager.getConnection(url, username, password);
			    System.out.println("Database connected!");
			return connection;
	}
}
	 

