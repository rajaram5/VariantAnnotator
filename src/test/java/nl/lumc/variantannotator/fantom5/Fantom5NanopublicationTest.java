/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.lumc.variantannotator.fantom5;

import java.util.List;
import nl.lumc.variantannotator.pojo.Fantom5;
import nl.lumc.variantannotator.pojo.Fantom5CellType;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Rajaram
 * @version 0.1
 * @since 28-03-2014
 */
public class Fantom5NanopublicationTest {
    
    private final String endpoint = "http://ep.dbcls.jp/fantom5/sparql";
    private final Fantom5Nanopublication test = new 
        Fantom5Nanopublication(endpoint);    
    
    @Test
    public void vaildVariantCoOrdinates () {
        
        int start = 31350184;
        int end = 31350187;
        String chromosome = "20"; 
        
        String annotationURI = "http://rdf.biosemantics.org/data/riken/"
                + "fantom5/data#chr20:31350184..31350200,+";
        
        
        long t1 = System.currentTimeMillis();        
        List<Fantom5> content = test.getTSS(start, end, chromosome);
        long t2 = System.currentTimeMillis();    
        System.out.println("Query: TSS for a genomic co-ordinates");
        System.out.println("Time taken for the query = " +(t2-t1));  
        
        assertEquals(1, content.size());
        assertEquals(annotationURI, content.get(0).getAnnotation());
        assertEquals(31350184, content.get(0).getTssStart());
        assertEquals(31350200, content.get(0).getTssEnd());
    }
    
    
    @Test
    public void vaildAnnotationCellType () {   
        
        String annotationURI = "http://rdf.biosemantics.org/data/"
                + "riken/fantom5/data#chr20:31350184..31350200,+"; 
        
        long t1 = System.currentTimeMillis();        
        List<Fantom5CellType> content = test.getCellType(annotationURI);
        long t2 = System.currentTimeMillis();   
        System.out.println("Query: Top 10 cell types");
        System.out.println("Time taken for the query = " +(t2-t1));    
        
        assertEquals(10, content.size());
        assertEquals("FF_11397-118D2", content.get(0).getId());
    }
    
    @Test
    public void vaildEntrezGeneID () {   
        
        int geneID = 23347; 
        
        long t1 = System.currentTimeMillis();        
        List<Fantom5> content = test.getTSSOfEntrezID(geneID);
        long t2 = System.currentTimeMillis(); 
        System.out.println("Query: TSS for a entrez gene id");
        System.out.println("Time taken for the query = " +(t2-t1));    
        
        assertEquals(4, content.size());
    }
    
}
