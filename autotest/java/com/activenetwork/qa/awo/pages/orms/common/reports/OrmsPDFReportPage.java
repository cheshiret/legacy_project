package com.activenetwork.qa.awo.pages.orms.common.reports;

import com.activenetwork.qa.awo.pages.ExternalWebPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;


public class OrmsPDFReportPage extends ExternalWebPage {
	
    private static OrmsPDFReportPage instance=null;
    
    
    private OrmsPDFReportPage(){
    	browser=null;
		attributeName="title";
		value=new RegularExpression(".*\\.reserveamerica\\.com.*fileName=report\\d+\\.pdf.*", false);
		timeout=Integer.parseInt(TestProperty.getProperty("page.loading.onlinereport.wait"));
    }
    
    public boolean exists() {
		browser=Browser.getInstance().getBrowser(attributeName, value);
		boolean exists= browser!=null;
		return exists;
	}
    
    public static OrmsPDFReportPage getInstance(){
    	if(instance==null){
    		instance=new OrmsPDFReportPage();
    	}
    	return instance;
    }

}
