/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.lumc.variantannotator.sparql;

import nl.lumc.variantannotator.sparql.SPARQLQuery;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import nl.lumc.variantannotator.utils.FileOperation;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 *
 * @author Rajaram
 * @version 0.1
 * @since 27-03-2014
 */
public class SPARQLQueryTest {
    
    @Test
    public void vaildQuery () {
                
        String endpoint = "http://ep.dbcls.jp/fantom5/sparql"; 
        String query = FileOperation.getQueryAsString("query1");
        
        SPARQLQuery test = new SPARQLQuery(endpoint);
        
        ResultSet results = test.exceuateQuery(query);
        
        assertTrue("The query is excepted to run a row",results.hasNext());
        
    }
    
    @Test
    public void queryResult () {
        
        String endpoint = "http://ep.dbcls.jp/fantom5/sparql"; 
        
        String query = FileOperation.getQueryAsString("query1");
        
        SPARQLQuery test = new SPARQLQuery(endpoint);
        
        ResultSet results = test.exceuateQuery(query);
        
        String cageCluster = "http://rdf.biosemantics.org/data/riken/fantom5"
                + "/data#chr17:26926005..26926070,-";
        
        while (results.hasNext()) {
            QuerySolution qs = results.next();
            assertEquals(26926005, qs.getLiteral("cageStart").getInt());
            assertEquals(26926070, qs.getLiteral("cageEnd").getInt());
            assertEquals(cageCluster, qs.getResource("cageCluster").getURI());
        }
    }
    
    
}
