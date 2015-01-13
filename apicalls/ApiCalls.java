/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aping.apicalls;

import aping.containers.EventTypeResultContainer;
import aping.enums.APIEndpoints;
import aping.enums.ApiNgOperation;
import aping.exceptions.APINGException;
import aping.apiclient.APIClient;
import aping.enums.MarketProjection;
import aping.enums.MarketSort;
import aping.enums.MatchProjection;
import aping.enums.OrderProjection;
import aping.jsonentities.EventTypeResult;
import aping.jsonentities.MarketBook;
import aping.jsonentities.MarketCatalogue;
import aping.jsonentities.MarketFilter;
import aping.jsonentities.PlaceExecutionReport;
import aping.jsonentities.PlaceInstruction;
import aping.jsonentities.PriceProjection;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author David_killa
 */
public class ApiCalls {
    
    protected final String FILTER = "filter";
    protected final String LOCALE = "locale";
    protected final String SORT = "sort";
    protected final String MAX_RESULT = "maxResults";
    protected final String MARKET_IDS = "marketIds";
    protected final String MARKET_ID = "marketId";
    protected final String INSTRUCTIONS = "instructions";
    protected final String CUSTOMER_REF = "customerRef";
    protected final String MARKET_PROJECTION = "marketProjection";
    protected final String PRICE_PROJECTION = "priceProjection";
    protected final String MATCH_PROJECTION = "matchProjection";
    protected final String ORDER_PROJECTION = "orderProjection";
    protected final String locale = Locale.getDefault().toString();
    private APIClient exchangeClient;
    private static final Logger LOG = Logger.getLogger(ApiCalls.class.getName());

    public ApiCalls(APIClient exchangeClient) 
    {
        this.exchangeClient = exchangeClient;
    }
    
    
    
    
    
    public List<EventTypeResult> listEventTypes(MarketFilter filter) throws APINGException 
    {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(FILTER, filter);
        queryParams.put(LOCALE, locale);
        List<EventTypeResult> container = null;
        ClientResponse clientResponse = exchangeClient.makeRequest(APIEndpoints.BETTINGURI.getAPIEndpoint(),
                                                                    ApiNgOperation.LISTEVENTTYPES.getOperationName(), queryParams);
        if(clientResponse.getStatus() == 200)
        {
            //EventTypeResultContainer container = clientResponse.getEntity(EventTypeResultContainer.class);
            //EventTypeResultContainer container = clientResponse.getEntity(new GenericType<EventTypeResultContainer>() {});
            container = clientResponse.getEntity(new GenericType<List<EventTypeResult>>() {});
        }
        String g = "h";// = Integer.toString(clientResponse.getStatus());
        Logger.getLogger(APIClient.class.getName()).log(Level.SEVERE,g);
        /*if(container.getError() != null)
            throw container.getError().getData().getAPINGException();*/

        return container;//container.getResult();

    }
    
    public List<MarketCatalogue> listMarketCatalogue(MarketFilter filter, Set<MarketProjection> marketProjection,
                                                     MarketSort sort, String maxResult) throws APINGException {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(LOCALE, locale);
        queryParams.put(FILTER, filter);
        queryParams.put(SORT, sort);
        queryParams.put(MAX_RESULT, maxResult);
        queryParams.put(MARKET_PROJECTION, marketProjection);

        List<MarketCatalogue> container = null;
        
        ClientResponse clientResponse = exchangeClient.makeRequest(APIEndpoints.BETTINGURI.getAPIEndpoint(),
                                                                    ApiNgOperation.LISTMARKETCATALOGUE.getOperationName(), queryParams);
        if(clientResponse.getStatus() == 200)
        {
            //EventTypeResultContainer container = clientResponse.getEntity(EventTypeResultContainer.class);
            //EventTypeResultContainer container = clientResponse.getEntity(new GenericType<EventTypeResultContainer>() {});
            container = clientResponse.getEntity(new GenericType<List<MarketCatalogue>>() {});
            Logger.getLogger(APIClient.class.getName()).log(Level.SEVERE,"******in");
        }
        
        return container;

    }
    
    public List<MarketBook> listMarketBook(List<String> marketIds, PriceProjection priceProjection, OrderProjection orderProjection,
                                           MatchProjection matchProjection, String currencyCode) throws APINGException {
        
        Map<String, Object> queryParams = new HashMap<String, Object>();//<String, Object>();
        queryParams.put(LOCALE, locale);
        queryParams.put(MARKET_IDS, marketIds);
        queryParams.put(PRICE_PROJECTION, priceProjection);
        queryParams.put(ORDER_PROJECTION, orderProjection);
        queryParams.put(MATCH_PROJECTION, matchProjection);
        
        List<MarketBook> container = null;
        
        ClientResponse clientResponse = exchangeClient.makeRequest(APIEndpoints.BETTINGURI.getAPIEndpoint(),
                                                                    ApiNgOperation.LISTMARKETBOOK.getOperationName(), queryParams);
        if(clientResponse.getStatus() == 200)
        {
            //EventTypeResultContainer container = clientResponse.getEntity(EventTypeResultContainer.class);
            //EventTypeResultContainer container = clientResponse.getEntity(new GenericType<EventTypeResultContainer>() {});
            container = clientResponse.getEntity(new GenericType<List<MarketBook>>() {});
        }
        String g = "h";// = Integer.toString(clientResponse.getStatus());
        Logger.getLogger(APIClient.class.getName()).log(Level.SEVERE,g);

        /*if(container.getError() != null)
            throw container.getError().getData().getAPINGException();*/

        return container;
    }
    
    public PlaceExecutionReport placeOrders(String marketId, List<PlaceInstruction> instructions, String customerRef) throws APINGException {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(LOCALE, locale);
        queryParams.put(MARKET_ID, marketId);
        queryParams.put(INSTRUCTIONS, instructions);
        queryParams.put(CUSTOMER_REF, customerRef);
        
        PlaceExecutionReport container = null;
        
        ClientResponse clientResponse = exchangeClient.makeRequest(APIEndpoints.BETTINGURI.getAPIEndpoint(),
                                                                    ApiNgOperation.PLACORDERS.getOperationName(), queryParams);
        if(clientResponse.getStatus() == 200)
        {
            //EventTypeResultContainer container = clientResponse.getEntity(EventTypeResultContainer.class);
            //EventTypeResultContainer container = clientResponse.getEntity(new GenericType<EventTypeResultContainer>() {});
            container = clientResponse.getEntity(PlaceExecutionReport.class);
        }
       

        return container;

    }
    
}
