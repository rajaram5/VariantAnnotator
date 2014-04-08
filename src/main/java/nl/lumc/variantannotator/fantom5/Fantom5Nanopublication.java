/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.lumc.variantannotator.fantom5;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.query.ResultSet;
import java.util.ArrayList;
import java.util.List;
import nl.lumc.variantannotator.pojo.Fantom5;
import nl.lumc.variantannotator.pojo.Fantom5CellType;
import nl.lumc.variantannotator.sparql.SPARQLQuery;
import nl.lumc.variantannotator.utils.FileOperation;
import org.apache.jena.iri.IRI;
import static org.apache.jena.riot.system.IRIResolver.iriFactory;

/**
 *
 * @author Rajaram
 * @version 0.1
 * @since 28-03-2014
 */
public class Fantom5Nanopublication {
    
    private String endpoint;
    
    public Fantom5Nanopublication (String endpoint) {
        this.endpoint = endpoint;
        
    }
    /**
     * <P>
     * Get the list of transcription start site (TSS) for a given genomic 
     * co-ordinates.
     * </P>
     * @param variantStart  Genomic start position.
     * @param variantEnd    Genomic end position.
     * @param variantChromosome Chromosome number.
     * 
     * @return  List of transcription start site (TSS).  
     */
    
    public List<Fantom5> getTSS (int variantStart, int variantEnd, 
            String variantChromosome) {     
        
        
        List<Fantom5> content = new ArrayList<> ();
        
        String query = FileOperation.getQueryAsString("fantom5TSS");
        
        ParameterizedSparqlString queryString = new 
        ParameterizedSparqlString(query);        
        queryString.setLiteral("?variantStart", variantStart);
        queryString.setLiteral("?variantEnd", variantEnd);
        
        IRI iriChromosome = iriFactory.create(
                ("http://rdf.biosemantics.org/data/genomeassemblies/hg19#chr"
                        +variantChromosome));
        
        queryString.setIri("?variantChromosome", iriChromosome);        
        
        SPARQLQuery sparqlQuery = new SPARQLQuery(endpoint);
        
        
        ResultSet results = sparqlQuery.exceuateQuery(queryString);
        
        while (results.hasNext()) {
            QuerySolution qs = results.next();
            Fantom5 data = new Fantom5();
            data.setTssStart(qs.getLiteral("cageStart").getInt());
            data.setTssEnd(qs.getLiteral("cageEnd").getInt());
            data.setAnnotation(qs.getResource("cageCluster").getURI()); 
            data.setOrientation(qs.getResource("cageOrientation").getURI());
            data.setChromosome(qs.getResource("cageChromosome").getURI());
            
            content.add(data);
        }
        
        return content;
    }
    
    /**
     * <P>
     * Get the list of transcription start site (TSS) for a given entrez 
     * gene ID.
     * </P>
     * @param geneID Entrez gene ID.
     * 
     * @return List of transcription start site (TSS).  
     */
    public List<Fantom5> getTSSOfEntrezID (int geneID) {     
        
        
        List<Fantom5> content = new ArrayList<> ();
        
        
        String query = FileOperation.
                getQueryAsString("fantom5TSSOfEntrezGeneID");
        
        ParameterizedSparqlString queryString = new 
        ParameterizedSparqlString(query);
        
        queryString.setIri("?geneID", 
                ("http://bio2rdf.org/geneid:"+String.valueOf(geneID)));
        
        SPARQLQuery sparqlQuery = new SPARQLQuery(endpoint);
        
        ResultSet results = sparqlQuery.exceuateQuery(queryString);
        
        while (results.hasNext()) {
            QuerySolution qs = results.next();
            Fantom5 data = new Fantom5();
            data.setTssStart(qs.getLiteral("cageStart").getInt());
            data.setTssEnd(qs.getLiteral("cageEnd").getInt());
            data.setAnnotation(qs.getResource("cageCluster").getURI());
            data.setOrientation(qs.getResource("cageOrientation").getURI());
            data.setChromosome(qs.getResource("cageChromosome").getURI());
            
            content.add(data);
        }
        
        return content;
    }
    
    /**
     * <P>
     * Get the list of top 10 cell types for the given cageCluster annotation 
     * URI.
     * </P>
     * @param annotationURI CageCluster annotation URI.
     * 
     * @return  List of top 10 cell types with a tpm value. 
     */
    public List<Fantom5CellType> getCellType (String annotationURI) {
        
        List<Fantom5CellType> content = new ArrayList<> ();     
        String query = FileOperation.getQueryAsString("fantom5CellType");  
        ParameterizedSparqlString queryString = new 
        ParameterizedSparqlString(query);
        
        queryString.setIri("?cageCluster", annotationURI);
        
        SPARQLQuery sparqlQuery = new SPARQLQuery(endpoint);
        //query = query.replace("#cageCluster", (annotationURI));            
        ResultSet results = sparqlQuery.exceuateQuery(queryString);
            
        while (results.hasNext()) {                
            QuerySolution qs = results.next();                
            Fantom5CellType data = new Fantom5CellType();                
            data.setUri(qs.getResource("cellType").getURI());                
            data.setId(qs.getResource("cellType").getLocalName());                
            data.setTpmValue(qs.getLiteral("tpmValue").getDouble()); 
            content.add(data);            
        }
        return content;
    }
    
    /**
     * <P>
     * Get tpm value for the given cellType and cageCluster annotation URI.
     * </P>
     * @param annotationURI CageCluster annotation URI. 
     * @param cellType  Cell type URI.
     * @return  Cell type tpm value 
     */
    public Fantom5CellType getTpmValue (String annotationURI, 
            String cellTypeURI) {
        
        Fantom5CellType content = new Fantom5CellType();    
        String query = FileOperation.getQueryAsString("fantom5TpmOfCellType");  
        ParameterizedSparqlString queryString = new 
        ParameterizedSparqlString(query);
        IRI iriAnnotation = iriFactory.create(annotationURI);
        IRI iriCellType = iriFactory.create(cellTypeURI);
        
        queryString.setIri("?cageCluster", iriAnnotation);
        queryString.setIri("?cellType", iriCellType);
        
        SPARQLQuery sparqlQuery = new SPARQLQuery(endpoint);
        //query = query.replace("#cageCluster", (annotationURI));            
        ResultSet results = sparqlQuery.exceuateQuery(queryString);
            
        while (results.hasNext()) {             
            QuerySolution qs = results.next(); 
            content.setTpmValue(qs.getLiteral("tpmValue").getDouble());
            content.setUri(qs.getResource("cellTypeURI").getURI());
            content.setId(qs.getResource("cellTypeURI").getLocalName());
        }
        return content;
    }
    
    
}
