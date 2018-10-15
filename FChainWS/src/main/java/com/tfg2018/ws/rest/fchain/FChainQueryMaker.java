package com.tfg2018.ws.rest.fchain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class FChainQueryMaker {

    private CloseableHttpClient httpclient = null;
    private HttpPost httppost = null;
    private Object answer = null;
    
    public FChainQueryMaker(String ip, String port,String login, String password) {
    	connect(ip,port,login,password);
    }
    
    protected void connect (String ip, String port, String login, String password) {
    	httppost = new HttpPost("http://" + ip + ":" + port);

        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(login, password);
        provider.setCredentials(AuthScope.ANY, credentials);

        httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
    }
    
    
}
