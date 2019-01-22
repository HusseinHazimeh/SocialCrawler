  

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.*;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.http.HTTPRepository;

/**
 * GraphDB queries
 */
public class GraphDB {
	
	private StringBuilder result = new StringBuilder();
    
	public String query(String sparql_query) throws Exception {
        HTTPRepository repository = new HTTPRepository("http://160.98.21.145:7200/repositories/GraphScholar");

        RepositoryConnection connection = repository.getConnection();
         
        try 
        {

            TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL,
                     sparql_query);
            
            TupleQueryResult tupleQueryResult = tupleQuery.evaluate();
    
            while (tupleQueryResult.hasNext()) 
            {
                BindingSet bindingSet = tupleQueryResult.next();

                 for (Binding binding : bindingSet)
                 {
                    // Each Binding contains the variable name and the value for this result row
                	 String name = binding.getName();
                     Value value = binding.getValue();
                     result.append(name);
                     result.append(" = ");
                     result.append(value + "\n");
                }
            }
            
            tupleQueryResult.close();
        } finally {
           connection.close();
        }

//        for(int i= 0 ; i< result.size() ; i++ )
        	//System.out.println(result.toString());

        return result.toString();
        
    }

    public static void main(String[] args) throws Exception {
        //new GraphDB().query("select ?p ?s ?o where {    ?p ?s ?o .	<http://www.wikidata.org/entity/Q15840423> ?s ?o .} limit 100 ");
    }
}
