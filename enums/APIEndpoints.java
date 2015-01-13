/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aping.enums;

/**
 *
 * @author David_killa
 */
public enum APIEndpoints {
        BASEURI("https://identitysso.betfair.com/api/"), 
	BETTINGURI("https://api.betfair.com/exchange/betting/rest/v1.0/"),
	ACCOUNTURI("https://api.betfair.com/exchange/account/rest/v1.0");
	
	private String endPoint;
	
	private APIEndpoints(String operationName){
		this.endPoint = operationName;
	}

	public String getAPIEndpoint() {
		return endPoint;
	}
}
