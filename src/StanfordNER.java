import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
  
public class StanfordNER
{
 
 public static String identifyNER(String text,String model)
 {
	 @SuppressWarnings({ "unused", "rawtypes" })
	LinkedHashMap <String,LinkedHashSet<String>> map=new <String,LinkedHashSet<String>>LinkedHashMap();
	 String serializedClassifier =model;
	 String category = null;
	 System.out.println(serializedClassifier);
	 CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
	 List<List<CoreLabel>> classify = classifier.classify(text);
	 	for (List<CoreLabel> coreLabels : classify)
	 		{
	 			for (CoreLabel coreLabel : coreLabels)
	 			{
 
					 String word = coreLabel.word();
					 category = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
					 if(!"O".equals(category))
					 {
						 if(map.containsKey(category))
						 {
						 // key is already their just insert in arraylist
						 map.get(category).add(word);
						 }
						 else
						 {
						 LinkedHashSet<String> temp=new LinkedHashSet<String>();
						 temp.add(word);
						 map.put(category,temp);
						 }
					 //System.out.println(word+":"+category);
					 }
 
	 			}
		 
		 }
	 		return category;
 	}
 		public static void main(String args[])
		 {
			 String content="West Virginia University";
			 String [] a = content.split(" ");
			 for(int i = 0; i < a.length; i++){ 
				 System.out.println(a[i] + ": " + (new StanfordNER()).getTAG(a[i].replace(",", "")));
			 }
			 System.out.println((new StanfordNER()).getTAG(content));
		 }
 		 //return : tag [LOCATION, O, Organization]
		 public String getTAG(String keyword)
		 {
			 if(keyword.equals("Stanford")) 
				 return "Location";
			 else
			 {
				 String cat = identifyNER(keyword, "D:\\stanford-ner-2014-01-04\\stanford-ner-2014-01-04\\classifiers\\english.conll.4class.distsim.crf.ser.gz").toString();
			 	 return keyword.length() > 2 ? cat : "";
			 }
		 }
}