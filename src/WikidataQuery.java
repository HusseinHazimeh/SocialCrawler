 

import org.eclipse.rdf4j.model.Statement; 
import org.eclipse.rdf4j.query.GraphQuery;
import org.eclipse.rdf4j.query.GraphQueryResult; 
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository; 

/**
 * @author hussein.hazimeh 06.March.2018
 *
 */
public class WikidataQuery {

    public static void main(String[] args) throws NoClassDefFoundError, ClassNotFoundException{
    	  
    	SPARQLRepository sparqlRepository = new SPARQLRepository("https://query.wikidata.org/sparql");
    	sparqlRepository.initialize();
    	RepositoryConnection sparqlConnection = sparqlRepository.getConnection();
  
//    	String query = "SELECT ?link ?linkLabel ?picture ?age WHERE {	"
//    			+ "?link wdt:P31 wd:Q5 ;    "
//    			+ "p:P39 [ ps:P39 wd:Q19546 ; pq:P580 ?startTime ] .	"
//    			+ "OPTIONAL { ?link wdt:P569 ?dateOfBirth }	"
//    			+ "OPTIONAL { ?link wdt:P18 ?picture }	"
//    			+ "OPTIONAL { ?link wdt:P570 ?dateOfDeath }	"
//    			+ "BIND(YEAR(?dateOfDeath) - YEAR(?dateOfBirth) as ?age)	"
//    			+ "SERVICE wikibase:label { bd:serviceParam wikibase:language 'fr,en' }} "
//    					+ "ORDER BY DESC(?startTime)";
    	
    	String queryString =" CONSTRUCT   { <http://www.wikidata.org/entity/Q15840423> ?predicate ?object} "
    			+ " WHERE       { <http://www.wikidata.org/entity/Q15840423> ?predicate ?object}";
    	
    			//TupleQuery tupleQuery = sparqlConnection.prepareTupleQuery(QueryLanguage.SPARQL, query);
    			//tupleQuery.evaluate(new SPARQLResultsTSVWriter(System.out));
    	
    	GraphQuery query = sparqlConnection.prepareGraphQuery(queryString);
    	
    	try (GraphQueryResult result = query.evaluate()) {
	    	while (result.hasNext())
	    	{
	    			  Statement st = result.next();
	    			  String st2 = st.toString();
	    			   
	    			  if(st2.endsWith(">)"))
	    				  st2 = st2.replace(")", " .");
	    			  
	    			  if(st2.indexOf("/P") < 0)
	    				  continue;
	    			  
	    			  st2 = st2.replaceAll(" ", "");
	    			  System.out.println(st2.replace("(", "<").replace(",", "> <").replace(")","> ."));
	    	}
    	}
    }
}
