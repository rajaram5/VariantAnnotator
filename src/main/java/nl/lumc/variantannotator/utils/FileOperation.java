package nl.lumc.variantannotator.utils;

import static com.google.common.io.Files.readLines;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import static org.slf4j.LoggerFactory.getLogger;

import java.net.URL;
import java.nio.charset.StandardCharsets;
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

    /**
     * <P>
     * To get the content of the file stored in the test resources package.
     * </P>
     * @param fileName The name of the test nanopub fixture.
     * @return  File content as a string object. 
     */
    public static String getFilePath(String fileName)  {
        URL fileURL = FileOperation.
                class.getResource(fileName);
               
        return fileURL.getPath();
    }
    
    
    public static void writeFile (ArrayList<String> content, String filePath) {
        
        try{
            // Create file 
            FileWriter fstream = new FileWriter(filePath);
            try (BufferedWriter out = new BufferedWriter(fstream)) {
                int currentRow = 0;
                
                for (String row:content) {
                    out.write(row);
                    if (currentRow < content.size())
                        out.write("\n");
                    currentRow++;
                }
            }
        }catch (IOException e){//Catch exception if any            
            System.err.println("Error: " + e.getMessage());            
        }  
    }
    
    public static ArrayList<String> getContent(String filePath) {
        
         ArrayList<String> content = new  ArrayList<> ();
        
        try{
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(filePath);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
            // Print the content on the console
            content.add(strLine);
            }
            //Close the input stream
            in.close();    
        }catch (IOException e){//Catch exception if any 
            System.err.println("Error: " + e.getMessage());  
        }        
        return content;
      }
    
    public static String getContentStr(String filePath) {
        
         String content = "";
         
         ArrayList<String> fileContent = getContent(filePath);
         
         int noOfRows = 0;
        
        for (String row:fileContent) {
            
            content +=row;
            noOfRows++;
            if (noOfRows < fileContent.size()) {
                content +="\n";
            }           
            
            
        }       
        return content;
      }
    
    
    /* <P>
     * To get the content of the file stored in the test resources package.
     * </P>
     * @param fileLocation The name of the test nanopub fixture.
     * @return  File content as a string object. 
     */
    public static String getQueryAsString(String fileName)  {
        URL fileURL = FileOperation.
                class.getResource(fileName);
        File npFile;
        String content = "";
        try {
            npFile = new File(fileURL.toURI());
            for (String fileLine : readLines(npFile, StandardCharsets.UTF_8)) {
                content += fileLine;
            }          
        } catch (IOException | URISyntaxException ex) {
            LOGGER.warn("NanopublicationFileOperation failed ",ex);
        
        }        
        return content;
    }
    
}
