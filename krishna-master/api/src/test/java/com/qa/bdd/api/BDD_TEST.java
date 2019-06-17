package com.qa.bdd.api;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class BDD_TEST {

	
	
	@Test
	public void number_of_circuits_2017() {
		
		given().
		
		when().
			
		get("http://ergast.com/api/f1/2017/circuits.json").
		
		then().
		
		//validation starts here
	    assertThat().
	    
	  //we are validating here first status code
	    statusCode(200).  
		
	    and(). 
	    
	    //secondly we are validating here status code
		body("MRData.CircuitTable.Circuits.circuitId" , hasSize(20)).
	
	    and().
	    
	    header("content-length", equalTo("4551"));
	}
	
}
