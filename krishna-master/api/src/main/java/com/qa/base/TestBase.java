package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public static Properties prop;
	
	public TestBase(){
		
		prop=new Properties();
		
		try {
			FileInputStream ip=new FileInputStream("C:\\Users\\krishna.yadav\\Desktop\\API\\krishna-master\\api\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
	
	
		
	

