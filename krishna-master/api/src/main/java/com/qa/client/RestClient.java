package com.qa.client;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class RestClient {
	
	
    //1.GET METHOD WITHOUT HEADERS--> for get we need one GET method and one URL (CHEKK IN POSTMAN)
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		
		//use of httpclients class from http client package
		//createDefault method  will create one client connection and will return one
		//closeblhttpclient class object which is abstract class	
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpGet httpget= new HttpGet(url);  // create GET request with the URl..it is just like putting URL in postman.
		CloseableHttpResponse response=httpclient.execute(httpget);
		return response;	
		}
			
	
	//GET METHOD WITH HEADERS
	 public CloseableHttpResponse get(String url, HashMap<String,String> headerMap) throws ClientProtocolException, IOException{
		
		//use of httpclients class from http client package
		//createDefault method  will create one client connection and will return one
		//closeblhttpclient class object which is abstract class	
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpGet httpget= new HttpGet(url);  // create GET request with the URl..it is just like putting URL in postman.
		for(Map.Entry<String, String> entry : headerMap.entrySet()){ // we have to send headers also 
	    	
	    	httpget.addHeader(entry.getKey(), entry.getValue());
	    }
		CloseableHttpResponse response=httpclient.execute(httpget);
		return response;	
		}
		

     
     //POSTMethod with headers/URL/
	  public CloseableHttpResponse postAPI(String url, String entitySet, HashMap<String,String> headerMap) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient=	HttpClients.createDefault();
		HttpPost httpPost=new HttpPost(url);
		httpPost.setEntity(new StringEntity(entitySet));
		for(Map.Entry<String,String> entry: headerMap.entrySet()){
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
			CloseableHttpResponse closeableresponse=	httpClient.execute(httpPost);  //hit krne k baad jo response aata hai. wo store krte isme
			return closeableresponse;
		}	
	}
