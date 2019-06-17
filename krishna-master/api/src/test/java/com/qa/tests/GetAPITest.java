package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	
	TestBase testbase;
	String serviceUrl;
	String appURL;
	String url;
	RestClient restclient;
	CloseableHttpResponse response;
	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException{
		
	 testbase= new TestBase();
		 serviceUrl=prop.getProperty("URL");
		 System.out.println("Service Url is---> " +serviceUrl);
		 appURL=prop.getProperty("serviceURL");
		 System.out.println("App url is---> " +appURL);
		url=serviceUrl+appURL;
		System.out.println("Actual url is --->"+url);			
	}
	
	
    @Test
	public void getTest() throws ClientProtocolException, IOException{
    	restclient= new RestClient();
        response= restclient.get(url);
    	
    	
    //a. getting status code
  		int status=response.getStatusLine().getStatusCode();
    					
    	System.out.print("Status code is --->"+  status);;
    	
    	Assert.assertEquals(status, 200, "Status code is not 200");
    	
    //b. JSON string
    			
    	String responseString=EntityUtils.toString(response.getEntity(),"UTF-8");
    					
    					
    	JSONObject responseJSON= new JSONObject(responseString);
    	
    	//single value assertion
    	//per page
    	String per_Page_Value   =TestUtil.getValueByJPath(responseJSON, "/per_page");
    	System.out.println("per page value is--> "+  per_Page_Value);
    	
    	Assert.assertEquals(Integer.parseInt(per_Page_Value), 3);  //converting it into intger
    	
    	//total value
    	String totalValue   =TestUtil.getValueByJPath(responseJSON, "/total");
    	System.out.println("Total value is--> "+  totalValue);
    	Assert.assertEquals(Integer.parseInt(totalValue), 12);
    					
    	System.out.println(responseJSON);;
    	
    	
    	//getting all values from json array
    	 String lastname=TestUtil.getValueByJPath(responseJSON, "/data[0]/last_name");
    	 String id=TestUtil.getValueByJPath(responseJSON, "/data[0]/id");
    	 String avatar=TestUtil.getValueByJPath(responseJSON, "/data[0]/avatar");
    	 String firstname=TestUtil.getValueByJPath(responseJSON, "/data[0]/first_name");
    	 
    	 System.out.println(firstname);
    	 System.out.println(lastname);
    	 System.out.println(id);
    	 System.out.println(avatar);
    	 
    	 
    					
    	       	//getting all headers
    	Header[] headersArray= response.getAllHeaders();	
    			
    	HashMap<String,String> allheader= new HashMap<String,String>();
    			
    			for (Header header: headersArray){
    				allheader.put(header.getName(), header.getValue());
    				
    				System.out.println(allheader);
    				
	}
		
		
		
}}