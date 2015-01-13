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
public class LoginResponse implements Serializable
{
    private String sessionToken;
    private String loginStatus;
    public LoginResponse() 
    {
        sessionToken = "";
        loginStatus = "";
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getloginStatus() {
        return loginStatus;
    }

    public void setloginStatus(String status) {
        this.loginStatus = status;
    }

    @Override
    public String toString() 
    {
        return "LoginResponse [sessionToken=" + sessionToken + ", status=" + loginStatus + "]";
    }
}
