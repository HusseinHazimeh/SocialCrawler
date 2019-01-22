import java.util.ArrayList; 
import java.util.List;

import org.openqa.selenium.By;
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

public class GoogleScholarCralwler 
{
	 public static ArrayList<GoogleScholar_Author> authors_list = new ArrayList<GoogleScholar_Author>();
	 public static Connection con;
	 public static void main(String[] args) 
	 {
		crawl();
	 }
	 
	 public static void crawl()
	 {
		 	System.setProperty("webdriver.chrome.driver", "C:/Users/husseiny.hazimeh/Desktop/chromedriver_win32/chromedriver.exe");
	        WebDriver driver = new ChromeDriver();

	         driver.get("https://scholar.google.ch/citations?view_op=search_authors&hl=en&mauthors=label:economics&after_author=_HMCAIxS_v8J&astart=10");
	         
	         for(int page=0; page<=10; page++)
	         {
		         List<WebElement> rows = driver.findElements(By.className("gsc_oai_name"));
		         List<WebElement> affiliations = driver.findElements(By.className("gsc_oai_aff"));
		         List<WebElement> citations = driver.findElements(By.className("gsc_oai_cby"));
		         int n_of_rows = rows.size();
		         
		         for(int j=0; j<n_of_rows; j++)
		         {
		        	 GoogleScholar_Author gsa = new GoogleScholar_Author();
		        	 gsa.set_author_name(rows.get(j).getText());
		        	 gsa.set_affiliation(affiliations.get(j).getText());
		        	 gsa.set_n_of_citations(Integer.parseInt(citations.get(j).getText().replace(" ", "").replace("Citedby", "")));
		        	 List<WebElement> interests_parent = driver.findElements(By.className("gsc_oai_int"));
		        	 List<WebElement> interests_child = interests_parent.get(j).findElements(By.className("gsc_oai_one_int"));
		        	 ArrayList<String> interests_values = new ArrayList<String>();
		        	 
		        	 for(int k=0; k<interests_child.size(); k++)
		        	 {
		        		 interests_values.add(interests_child.get(k).getText());
		        	 }
		        	 
		        	 gsa.set_research_fields(interests_values);
		        	 gsa.set_profile_url(rows.get(j).findElement(By.tagName("a")).getAttribute("href"));
		        	 authors_list.add(gsa);
		         }
		         
		         driver.findElement(By.xpath(".//*[@class='gs_btnPR gs_in_ib gs_btn_half gs_btn_lsb gs_btn_srt gsc_pgn_pnx']")).click();
		         
		    }
	         driver.quit();
	         try {
				con = connectMe();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         for(int i=0;i<authors_list.size();i++)
	         {
//	        	 System.out.println(authors_list.get(i).get_author_name() 
//	        			 + " **affiliation:** " + authors_list.get(i).get_affiliation() 
//	        			 + " **n of citations:** " + authors_list.get(i).get_n_of_citations()
//	        			 + " **research field [0]:** " + authors_list.get(i).get_research_fields().get(0)
//	        			 + " **image url:** " + authors_list.get(i).get_profile_url());
//	        	 
	        	 insert_into_mysql(
	        			 authors_list.get(i).get_author_name(), authors_list.get(i).get_affiliation(), 
	        			 authors_list.get(i).get_research_fields(), 
	        			 authors_list.get(i).get_n_of_citations(), 
	        			 authors_list.get(i).get_profile_url(), 8);
	         }
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
	 

