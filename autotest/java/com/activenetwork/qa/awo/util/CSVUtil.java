package com.activenetwork.qa.awo.util;


import com.activenetwork.qa.testapi.util.TestProperty;
import com.activenetwork.qa.awo.util.AwoUtil;


/**
 * 
 * @Description: CSV utility class 
 * @Preconditions: With test property settings

  
 * @author tchen
 * @Date  Sep 29, 2014
 */


public class CSVUtil extends AwoUtil {
	//TestProperty.load(AwoUtil.TEST_PROPERTY);
	
	public static void initTestProperty() {
		TestProperty.load(AwoUtil.TEST_PROPERTY);
		if(TestProperty.getProperty("target_env").equalsIgnoreCase("live")) {
			//load the production sanity test information
			loadLiveInformation();
		}
		
		TestProperty.putProperty("property.folder", PROPERTY_PATH);
		
	}
	
	public static boolean containsField () {
		boolean containsfields = TestProperty.getBooleanProperty("csv.contain.field",true);
		if (containsfields) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean returnResult () {
		boolean returnresult = TestProperty.getBooleanProperty("csv.result.return",true);
		if (returnresult) {
			return true;
		}
		else {
			return false;
		}			
	}
	
	public static String fieldType() {
		String type = TestProperty.getProperty("csv.field.type");
		if (type.contains("col")) {
			return "column";
		}
		else {
			return "row";
		}					
	}
	
	
}

