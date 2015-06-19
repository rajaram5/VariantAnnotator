package nl.lumc.variantannotator.utils;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static org.slf4j.LoggerFactory.getLogger;

import java.net.URL;
import java.util.ArrayList;


/**
 *
 * @author Eelke van der Horst
 * @author Mark Thompson 
 * @author Kees Burger
 * @author Rajaram Kaliyaperumal
 * @author Reinout van Schouwen
 * 
 * @serial 05-11-2013
 * @version 0.2
 */
public class FileOperation {
    
    private static final org.slf4j.Logger LOGGER
            = getLogger(FileOperation.class);   
    
    /* <P>
     * To get the content of the file stored in the test resources package.
     * </P>
     * @param fileLocation The name of the test nanopub fixture.
     * @return  File content as a string object. 
     */
    public static String getQueryAsString(String fileName)  {
        
        InputStream is = FileOperation.class.
                getResourceAsStream(fileName);
        String content = "";
        try {
            content = CharStreams.toString
        (new InputStreamReader(is, Charsets.UTF_8));        
        } catch (IOException ex) {
            LOGGER.warn("NanopublicationFileOperation failed ",ex);
        
        }        
        return content;
    }
    
}
