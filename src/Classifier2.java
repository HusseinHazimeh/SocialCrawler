 
import java.io.File;

import com.graphscholar.classifierstack.Classifier;
 
import weka.core.Instances;
import weka.core.converters.ArffLoader;  

public class Classifier2 {
	
	private static ArffLoader loader = new ArffLoader();
	
	 
	public static void main(String[] args) throws Exception{
		
		loader.setFile(new File("C:/Users/husseiny.hazimeh/Dropbox/data.arff")); 
		Instances dataset = loader.getDataSet();	
		dataset.setClassIndex(dataset.numAttributes()-1);
		
		Classifier c = new Classifier();
		
		System.out.println("j48 class result ::: " + c.DecisionTree(dataset, new double [] {0,0,0,1}));
		System.out.println("SVM class result ::: " + c.SVM(dataset, new double [] {1,1,0,1}));
		System.out.println("BNC class result ::: " + c.NBC(dataset, new double [] {1,0,0,1}));
		
	}
}
