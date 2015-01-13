/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aping.authentication;

import aping.enums.APIEndpoints;
import aping.apiclient.APIClient;
import aping.jsonentities.LoginResponse;
import aping.jsonentities.StayAliveResponse;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MultivaluedMap;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author David_killa
 */
public class Authentication 
{
    
    private static int LOGIN_URI = 0;
    private static int KEEPALIVE_URI = 1;
    private static int LOGOUT_URI = 2;
    private static final Logger LOG = Logger.getLogger(APIClient.class.getName());
    private static WebResource web_resource;
    private static String sessionToken;
    private APIClient exchangeClient;

    /**
     *
     * @param username
     * @param password
     * @param webResource
     */
    public Authentication(APIClient exchangeClient) 
    {
        sessionToken = "";
        this.setExchangeClient(exchangeClient);
    }

    
    
    public void Login(String username, String password) throws Exception 
    {
        if(exchangeClient == null)throw new Exception("\n** Exchange Client is null **");
        
        /*Encoding username & password using UTF-8 encoding*/
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        try {
            queryParams.add("username", URLEncoder.encode(username,"UTF-8"));
            queryParams.add("password", URLEncoder.encode(password,"UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(APIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*Response from API derived from returned JSON string*/
        LoginResponse loginResponse = new LoginResponse();

        /*Receiving client response by executing a request to the exchange API*/
        try
        {
            ClientResponse clientResponse = exchangeClient.makePermissionRequest(APIEndpoints.BASEURI.getAPIEndpoint(),"certlogin", queryParams);

            String statusResponse = String.valueOf(clientResponse.getStatus());

            /*Check if uri, request data & headers are correct
             *Also other http error or API errors*/
            if(clientResponse.getStatus() == 200)
            {
                loginResponse = clientResponse.getEntity(LoginResponse.class);
                LOG.log(Level.INFO, "Status code: {0}", loginResponse.getloginStatus());
            }
            else LOG.log(Level.INFO, "Status code:{0}", statusResponse + loginResponse.getloginStatus());
         }
        catch(Exception ex)
        {
            Logger.getLogger(APIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOG.log(Level.INFO, "\n\n**********End of Login*************\n");
        this.exchangeClient.setSessionToken(loginResponse.getSessionToken());
    }
    
    public void stayAlive() throws Exception
    {
        if(exchangeClient == null)throw new Exception("\n** Exchange Client is null **");
        
        StayAliveResponse stayAliveResponse = new StayAliveResponse();
         /*Receiving client response by executing a request to the exchange API
         */
        try
        {
            ClientResponse clientResponse = exchangeClient.makeRequest(APIEndpoints.BASEURI.getAPIEndpoint(), "keepAlive", null);
            String statusResponse = String.valueOf(clientResponse.getStatus());

            if(clientResponse.getStatus() == 200)
            {
                stayAliveResponse = clientResponse.getEntity(StayAliveResponse.class);
                LOG.log(Level.INFO, "Status code: {0}", stayAliveResponse.getStatus());
            }
            else 
            {
                LOG.log(Level.INFO, "Status code:{0}", statusResponse + stayAliveResponse.getStatus());
                LOG.log(Level.INFO, "Error:{0}", statusResponse + stayAliveResponse.getError());
            }
        }
        catch(Exception ex)
        {
            Logger.getLogger(APIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOG.log(Level.INFO, "\n\n**********End of KeepAlive*************\n");
    }
    
    public void logOut() throws Exception
    {
        if(exchangeClient == null)throw new Exception("\n** Exchange Client is null **");
        
        StayAliveResponse logOutResponse = new StayAliveResponse();
         /*Receiving client response by executing a request to the exchange API
         *Compulsory X-Authentication required
         *Optional x-application header and username/password
         */
        try
        {
            ClientResponse clientResponse = exchangeClient.makeRequest(APIEndpoints.BASEURI.getAPIEndpoint(), "logout", null);            
            String statusResponse = String.valueOf(clientResponse.getStatus());

            if(clientResponse.getStatus() == 200)
            {
                logOutResponse = clientResponse.getEntity(StayAliveResponse.class);
                LOG.log(Level.INFO, "Status code: {0}", logOutResponse.getStatus());
            }
            else 
            {
                LOG.log(Level.INFO, "Status code:{0}", statusResponse + logOutResponse.getStatus());
                LOG.log(Level.INFO, "Error:{0}", statusResponse + logOutResponse.getError());
            }
        }
        catch(Exception ex)
        {
            Logger.getLogger(APIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOG.log(Level.INFO, "\n\n**********End of Logout*************\n");
    }

    public APIClient getExchangeClient() {
        return exchangeClient;
    }

    public void setExchangeClient(APIClient exchangeClient) {
        this.exchangeClient = exchangeClient;
    }
    
}
