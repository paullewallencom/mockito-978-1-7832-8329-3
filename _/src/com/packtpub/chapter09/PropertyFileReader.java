package com.packtpub.chapter09;

import java.util.HashMap;
import java.util.Map;

public class PropertyFileReader {
    
	public static Map<String, String> readConfig(){
		//mocking property file reading and adding dealy 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new HashMap<String, String>();
	}
}
