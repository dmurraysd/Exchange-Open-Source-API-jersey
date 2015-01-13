/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aping.apiclient;

import aping.jsonentities.EventTypeResult;
import aping.jsonentities.MarketFilter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.ws.rs.core.MultivaluedMap;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 * Jersey REST client generated for REST resource:Accounts Service
 * [accounts/ClientLogin]<br>
 * USAGE:
 * <pre>
 *        APIClient client = new APIClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author David_killa
 */
public class APIClient {
    
    private WebResource webResource;
    private Client client;
    private String sessionToken;
    private  static String BASE_URI = "https://identitysso.betfair.com/api/";
    private  static String LOGIN_URI = "certlogin";
    private  static String KEEPALIVE_URI = "keepAlive";
    private  static String LOGOUT_URI = "logout";
    private  static String APPKEY = "Application-Key-Here";
    private  static final Logger LOG = Logger.getLogger(APIClient.class.getName());
    
    public APIClient() 
    {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig(); // SSL configuration
        config.getClasses().add(JacksonJsonProvider.class);
        config.getProperties().put(com.sun.jersey.client.urlconnection.HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new com.sun.jersey.client.urlconnection.HTTPSProperties(getHostnameVerifier(), getSSLContext()));
        /*DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);*/
        client = Client.create(config);
        client.addFilter(new LoggingFilter());
        webResource = client.resource(BASE_URI);
    }

    public ClientResponse makePermissionRequest(String baseURI, String path, Map<String, Object> queryParams)
    {
        webResource = client.resource(baseURI).path(path);
        return webResource.header("X-Application", APPKEY).header("X-Authentication", sessionToken)
                .type(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED)
                .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(ClientResponse.class,queryParams);
    }
    
    public ClientResponse makeRequest(String baseURI, String path, Map<String, Object> params)
    {
        webResource = client.resource(baseURI).path(path);
        return webResource.header("X-Application", APPKEY).header("X-Authentication", sessionToken)
                .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(ClientResponse.class, params);
    }
    
    public WebResource getWebResource() {
        
        return webResource;
    }

    public void setWebResource(WebResource webResource) {
        this.webResource = webResource;
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
    
    public String getBASE_URI() {
        return BASE_URI;
    }

    public void setBASE_URI(String BASE_URI) {
        this.BASE_URI = BASE_URI;
    }
    
    public void setWebResourcePath(int type)
    {
        String path = ""; 
        if(type == 0)path = LOGIN_URI;
        else if(type == 1)path = KEEPALIVE_URI;
        else path = LOGOUT_URI;
        webResource = client.resource(BASE_URI).path(path);
    }
    
    public void close() {
        client.destroy();
    }

    public void setUsernamePassword(String username, String password) {
        client.addFilter(new com.sun.jersey.api.client.filter.HTTPBasicAuthFilter(username, password));
    }

    private HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                return true;
            }
        };
    }

    private SSLContext getSSLContext() {
        javax.net.ssl.TrustManager x509 = new javax.net.ssl.X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                return;
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                return;
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        SSLContext ctx = null;
        KeyManager[] keyManagers;
        try 
        {
            ctx = SSLContext.getInstance("SSL");
            keyManagers = getKeyManagers("pkcs12", new FileInputStream(new File("C:\\ExchangeSslCerts\\client-2048.p12")), "khjgjhg6gffyg");
            ctx.init(keyManagers, new javax.net.ssl.TrustManager[]{x509}, new SecureRandom());
            
        } catch (Exception ex) 
        {
            Logger.getLogger(APIClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return ctx;
    }

    private static KeyManager[] getKeyManagers(String keyStoreType, FileInputStream keyStoreFile, String keyStorePassword) throws Exception 
    {
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(keyStoreFile, keyStorePassword.toCharArray());
        KeyManagerFactory keymf;
        keymf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keymf.init(keyStore, keyStorePassword.toCharArray());
        return keymf.getKeyManagers();
    }

   
    
}
