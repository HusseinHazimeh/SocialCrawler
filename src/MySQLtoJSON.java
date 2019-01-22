import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

@SuppressWarnings("unused")
public class MySQLtoJSON { 

	private static FileWriter file; 
//    private static JSONObject o;
//    private static JSONArray list;
//    private static JSONObject o2;
//	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		file = new FileWriter("d:\\GoogleScholarDataSet.json");
    //	try{
//			  Connection conn = GoogleScholarCralwler.connectMe();
//			  String query = "SELECT * FROM googlescholar";
//		      Statement st = conn.createStatement();
//		      ResultSet rs = st.executeQuery(query);
//		      o = new JSONObject();
//		    	  file.write("["); 
//		      rs.beforeFirst();
//		      list = new JSONArray();
//		      while (rs.next())
//		      {
//			        o2 = new JSONObject();
//			        o2.put("ID: ", rs.getString("GSID"));
//			        o2.put("Author Name: ", rs.getString("GSAN"));
//			        o2.put("Author Affiliation: ", rs.getString("GSAA"));
//			        o2.put("Author Interests", rs.getString("GSAI"));
//			        o2.put("Author Citations", rs.getInt("GSAC"));
//			        o2.put("Author Domain", rs.getInt("GSDomain"));
//			        list.add(o2);
//			         
//			  }
//			        o.put("Authors: ", list);
//			        file.write(o.toJSONString() + ","); 
//			        System.out.println(o.toJSONString() + ","); 
//			        file.flush(); 
//		      st.close();
//    	}
//    	catch(SQLException e){System.out.println(e.getMessage());}
    	 }
}