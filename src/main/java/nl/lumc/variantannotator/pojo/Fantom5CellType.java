/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.lumc.variantannotator.pojo;

/**
 *
 * @author rajaram
 */
public class Fantom5CellType {
    
    private String uri;
    private String id;
    private double tpmValue;

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the tpmValue
     */
    public double getTpmValue() {
        return tpmValue;
    }

    /**
     * @param tpmValue the tpmValue to set
     */
    public void setTpmValue(double tpmValue) {
        this.tpmValue = tpmValue;
    }
    
}
