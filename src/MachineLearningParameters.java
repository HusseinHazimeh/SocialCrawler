import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

import com.graphscholar.classifierstack.Classifier;

public class MachineLearningParameters {
	
	private static ArffLoader loader = new ArffLoader();
	
	//get x parameter (affiliationscore in the arff training set)
	public double compare_by_organization(String text, String _text) throws IOException
	{
		
		OpenNLP onlp = new OpenNLP();
		String org = onlp.findOrganization(text);
		String _org = onlp.findOrganization(_text);
		
		System.out.println("org from gsa : " + org + " org from facebook : " + _org);
		
		CosineSimilarity cs = new CosineSimilarity();
		double x = cs.Cosine_Similarity_Score(org, _org);
		
		return x;
	}
	
	//get y parameter (locationscore in the arff training set)
	public double compare_by_location(String text, String _text) throws IOException
	{
		OpenNLP onlp = new OpenNLP();
		String loc = onlp.findLocation(text);
		String _loc = onlp.findLocation(_text);
		
		System.out.println("loc from gsa : " + loc + " loc from facebook: " + _loc);
		
		CosineSimilarity cs = new CosineSimilarity();
		double y = cs.Cosine_Similarity_Score(loc, _loc);
		
		return y;
	}
	
	//get z parameter (contentscore in the arff training set)
	public double compare_by_content(String content, String _content)
	{
		/**
		 * To be implemented
		 * 
		 * */
		return 0.0;
	}
	
	//get w parameter (namescore in the arff training set)
	public double compare_by_screenname(String name, String _name)
	{
		
		JaroWinklerDistance j = new JaroWinklerDistance();
    	double w = j.apply(name, _name);
    	DecimalFormat df = new DecimalFormat("#.#");
    	w = Double.valueOf(df.format(w));
    	
		return w;
	}
	
	public String getClass(double [] d, String algorithm) throws Exception{
		
		String _class = "";
		
		loader.setFile(new File("C:/Users/husseiny.hazimeh/Dropbox/data.arff")); 
		Instances dataset = loader.getDataSet();	
		dataset.setClassIndex(dataset.numAttributes()-1);
		
		Classifier c = new Classifier();
		
		switch(algorithm){
		case "svm":
			_class = c.SVM(dataset, d);
			break;
			
		case "bnc":
			_class = c.NBC(dataset, d);
			break;
			
		case "dt":
			_class = c.DecisionTree(dataset, d);
			break;
			
		default:
			_class = "undefined";
		}
		
		return _class;
	}
	
	
	//call this function in case you want to use the stanford nlp named entity recognizer 
			public double _compare_by_location (String a, String b)
			{
				StopWords sw = new StopWords();
				a = a.replace(",", "");
				a = a.replace("Professor", "");
				String _a = sw.get_clean_text(a);
				
				System.out.println("_b: " + b);
				
				String _b = b;
				String [] __a = _a.split(" ");
				String [] __b = _b.split(" ");
				
				StanfordNER sner = new StanfordNER();
				String loc = "";
				String _loc = "";
				
				//search for the location tag using stanford ner 
				for(int t = 0; t < __a.length; t++)
				{
					String l = sner.getTAG(__a[t]);
					
					if(l.toLowerCase().equals("location"))
					{
						loc = __a[t];
						break;
					}
				}
				
				//search for the location tag using stanford ner 
				for(int t = 0; t < __b.length; t++)
				{
					String _l = sner.getTAG(__b[t]);
					
					if(_l.toLowerCase().equals("location"))
					{
						_loc = __b[t];
						break;
					}
				}
				
				System.out.println("iam in compare with :: " + loc + " and : " + _loc);
				
				CosineSimilarity cs = new CosineSimilarity();
				double _x = cs.Cosine_Similarity_Score(loc, _loc);
				System.out.println("comparing " +loc + " and  " + _loc + " is: " + cs.Cosine_Similarity_Score(loc, _loc));
				return _x;
			}
}
