/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.lumc.variantannotator.pojo;

/**
 *
 * @author Rajaram
 * @version 0.1
 * @since 27-03-2014
 */
public class Fantom5 {
    
    private String annotation;
    private int tssStart;
    private int tssEnd;
    private String chromosome;
    private String orientation;
   

    /**
     * @return the annotation
     */
    public String getAnnotation() {
        return annotation;
    }

    /**
     * @param annotation the annotation to set
     */
    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    /**
     * @return the tssStart
     */
    public int getTssStart() {
        return tssStart;
    }

    /**
     * @param tssStart the tssStart to set
     */
    public void setTssStart(int tssStart) {
        this.tssStart = tssStart;
    }

    /**
     * @return the tssEnd
     */
    public int getTssEnd() {
        return tssEnd;
    }

    /**
     * @param tssEnd the tssEnd to set
     */
    public void setTssEnd(int tssEnd) {
        this.tssEnd = tssEnd;
    }

    /**
     * @return the chromosome
     */
    public String getChromosome() {
        return chromosome;
    }

    /**
     * @param chromosome the chromosome to set
     */
    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    /**
     * @return the orientation
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * @param orientation the orientation to set
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
    
}
