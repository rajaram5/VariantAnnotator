/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.lumc.variantannotator.sparql;

import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolutionMap;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

/**
 *
 * @author Rajaram
 * @version 0.1
 * @since 27-03-2014
 */
public class SPARQLQuery {
    
    private String endpoint;
    
    public SPARQLQuery (String sparqlEndpoint) {
        endpoint = sparqlEndpoint;
    }
    
    public ResultSet exceuateQuery ( String queryString ) {        

        com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
        
        
        
        QueryExecution qExe = QueryExecutionFactory.
                sparqlService(endpoint, query);
       
        
        ResultSet results = qExe.execSelect();
        
        return results;
    }
    
    public ResultSet exceuateQuery (ParameterizedSparqlString queryString) {        

       
        QueryExecution qExe = QueryExecutionFactory.sparqlService(endpoint, 
                queryString.toString());
        
        ResultSet results = qExe.execSelect();
        
        return results;
    }
    
}
