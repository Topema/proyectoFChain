package com.tfg2018.ws.rest.ConsumedObjects;

import java.util.Map;

import com.tfg2018.ws.rest.object.Token;
/**
 *
 * @author Tomas
 */
public class CreateTokenStructure {
    private String address;
    private String tokenName;
    private Map<String, String> details;
    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the tokenName
     */
    public String getTokenName() {
        return tokenName;
    }

    /**
     * @param tokenName the tokenName to set
     */
    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    /**
     * @return the details
     */
    public Map<String, String> getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
    
    public Token getToken() {
    	return new Token(this.tokenName,this.details);
    }
}
