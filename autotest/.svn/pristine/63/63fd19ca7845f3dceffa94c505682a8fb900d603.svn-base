package com.activenetwork.qa.awo.testcases.abstractcases;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public abstract class  RATestCase extends WebTestCase {
	
	protected RATestCase(){
		url = TestProperty.getProperty(env + ".web.ra.url");
		
		if (MiscFunctions.isRAUnifiedSearchOpen()) {// Lesley[08/16/2013]: update due to RA Unified Search in QA1/3 in 3.04.03
			bd.isUnifiedSearch = true;
			bd.lookFor = "Any camping spot";
		}
	}
}
