package com.atguigu.p2p.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {
	
	private JsonHelper(){}
	
	public static String object2str(Object obj){
		String retStr = "";
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			retStr = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return retStr;
	}
	
	public static Object str2Object(String str,Class cls){
		Object retObj = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			System.out.println("********str2Object: "+str);
			 retObj = mapper.readValue(str, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retObj;
	}
}
