package com.tfg2018.ws.rest.fchain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tfg2018.ws.rest.object.FchainResponse;

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

public class FchainInterface {

	private CloseableHttpClient httpclient = null;
	private HttpPost httppost = null;
	private Object answer = null;

	public FchainInterface(String ip, String port, String login, String password) {
		connect(ip, port, login, password);
	}

	protected void connect(String ip, String port, String login, String password) {
		httppost = new HttpPost("http://" + ip + ":" + port);

		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(login, password);
		provider.setCredentials(AuthScope.ANY, credentials);

		httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
	}

	public Object executeRequest(StringEntity rpcEntity) throws Exception {
		if (httpclient != null && httppost != null) {
			httppost.setEntity(rpcEntity);
		} else {
			throw new Exception("Connection with blockchain failed");
		}
		CloseableHttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		String answer = EntityUtils.toString(entity);
		response.close();

		return translateResponse(answer);
	}

	private Object translateResponse(String answer) throws Exception {

		final Gson gson = new GsonBuilder().create();
		final FchainResponse fChainresponse = gson.fromJson(answer, FchainResponse.class);

		if (fChainresponse != null && fChainresponse.getError() == null) {
			return fChainresponse.getResult();
		} else if (fChainresponse != null && fChainresponse.getError() != null) {
			throw new Exception();
		} else {
			throw new Exception();
		}

	}

}
