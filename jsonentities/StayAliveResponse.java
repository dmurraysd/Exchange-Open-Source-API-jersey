/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aping.jsonentities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David_killa
 */
@XmlRootElement
public class StayAliveResponse implements Serializable
{
    private String token;
    private String status;
    private String product; //appkey for exchange
    private String error;
    
    public StayAliveResponse() 
    {
        token = "";
        status = "";
        product = "";
        error = "";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String sessionToken) {
        this.token = sessionToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getError() {
        return error;
    }

    /**
     *
     * @param error
     */
    public void setError(String error) {
        this.error = error;
    }
    
    @Override
    public String toString() 
    {
        return "StayAliveResponse [token=" + token + ", status=" + status + 
                ", product=" + product + ", error=" +  error + "]";
    }
}
