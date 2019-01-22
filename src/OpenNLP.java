import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class OpenNLP {
 
    public static void main(String[] args) {
     
        try {
            System.out.println("-------Finding entities belonging to category : organization------");
            String org = new OpenNLP().findOrganization("Professor of Biomedical Data Sciences, and of Statistics, Stanford University");
            System.out.println(org);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
     
        try {
            System.out.println("-------Finding entities belonging to category : place name------");
            String loc = new OpenNLP().findLocation("Beirut");
            System.out.println(loc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
    public String findLocation(String text) throws IOException {
    	
    	String location = "";
    	
        InputStream is = new FileInputStream("C:/Users/husseiny.hazimeh/Desktop/en-ner-location.bin");
 
        TokenNameFinderModel model = new TokenNameFinderModel(is);
        is.close();
 
        NameFinderME nameFinder = new NameFinderME(model);
 
        String[] sentence = text.split(" ");
 
        Span nameSpans[] = nameFinder.find(sentence);
 
        for(Span s: nameSpans){
          
            for(int index=s.getStart();index<s.getEnd();index++){

            	location += sentence[index]+" ";
            }
             
        }
        
        return location;
    }
    
    
    public String findOrganization(String text) throws IOException {
    	
    	String organization = "";
    	
        InputStream is = new FileInputStream("C:/Users/husseiny.hazimeh/Desktop/en-ner-organization.bin");
 
        TokenNameFinderModel model = new TokenNameFinderModel(is);
        is.close();


        NameFinderME nameFinder = new NameFinderME(model);


        String[] sentence = text.split(" ");
 
        Span nameSpans[] = nameFinder.find(sentence);


        for(Span s: nameSpans){
            
            for(int index=s.getStart();index<s.getEnd();index++){

            	organization += sentence[index]+" ";
            }
            
        }
        
        return organization;
    }
    
}