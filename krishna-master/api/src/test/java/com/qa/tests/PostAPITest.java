package com.qa.tests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

	public class PostAPITest extends TestBase {
		
			TestBase testbase;
			String serviceUrl;
			String appURL;
			String url;
			RestClient restclient;
			CloseableHttpResponse response;

			@BeforeMethod
			public void setup(){
				
			testbase= new TestBase();
			serviceUrl=prop.getProperty("URL");
			System.out.println("Service Url is---> " +serviceUrl);
			appURL=prop.getProperty("serviceURL");
			System.out.println("App url is---> " +appURL);
			url=serviceUrl+appURL;
			System.out.println("Actual url is --->"+url);			
			}
			
			@Test
			public void put() throws JsonGenerationException, JsonMappingException, IOException{
			restclient= new RestClient();
			//Adding values in headers
			
			HashMap<String,String> allheader= new HashMap();
			allheader.put("Content-Type", "application/json");
			allheader.put("name", "krishna");
			allheader.put("name", "krishna");
			allheader.put("name", "krishna");
			
			
			//restclient.postAPI(url)	;
			//jackson API:
			ObjectMapper mapper = new ObjectMapper();
			Users users = new Users("morpheus", "leader"); //expected users obejct
			
			//object to json file:
		//	mapper.writeValue(new File("/api/src/main/java/com/qa/data/user.json"), users);
			
			//java object to json in String:
			String usersJsonString = mapper.writeValueAsString(users);
			System.out.println(usersJsonString);
			
			response = restclient.postAPI(url, usersJsonString, allheader); //call the API
			
			//validate response from API:
			//1. status code:
			int statusCode = response.getStatusLine().getStatusCode();
			Assert.assertEquals(statusCode, 201);
			
			//2. JsonString:
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			JSONObject responseJson = new JSONObject(responseString);
			System.out.println("The response from API is:"+ responseJson);
			
			//json to java object:
			Users usersResObj = mapper.readValue(responseString, Users.class); //actual users object
			System.out.println(usersResObj);
			
			Assert.assertTrue(users.getName().equals(usersResObj.getName()));
			
			Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
			
			System.out.println(usersResObj.getId());
			System.out.println(usersResObj.getCreatedAt());
			
		}
				
		}
			
			
			
	




