package com.activenetwork.qa.awo.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class XMLAutoClient {
	
	private HttpClient httpClient;
	

	public XMLAutoClient()  {
//		HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
//
//	    DefaultHttpClient client = new DefaultHttpClient();
//
//	    SchemeRegistry registry = new SchemeRegistry();
//	    SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
//	    socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
//	    registry.register(new Scheme("https", socketFactory, 443));
//	    SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);
//	    httpClient = new DefaultHttpClient(mgr, client.getParams());
//
//	    // Set verifier      
//	    HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
	    httpClient=new DefaultHttpClient();
	}
	
	public String post(String url, String toPost){
		HttpPost post=new HttpPost(url);
		HttpEntity request;
		HttpResponse response;
		
		String responseContent = null;
		
		try {
			request = new StringEntity(toPost);
			post.setEntity(request);
			response=httpClient.execute(post);
			
			int code=response.getStatusLine().getStatusCode();
			if(code <200 || code >= 300) {
				try {
					LogDecrypting.process(TestProperty.getProperty("target_env"));
				} catch (IOException e) {
					AutomationLogger.getInstance().warn("Failed to decrypt verifone.log due to "+ e);
				}
				throw new IOException("HTTP Response Code: " + code);
			}
			responseContent = StringUtil.inputStreamToString(response.getEntity().getContent());
			
		} catch (UnsupportedEncodingException e) {
			throw new ActionFailedException(e);
		} catch (ClientProtocolException e) {
			throw new ActionFailedException(e);
		} catch (IOException e) {
			throw new ActionFailedException(e);
		}
		return responseContent;		
	}
	
	
	
	
}
