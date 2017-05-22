/*
 * Created on Jul 23, 2009
 * If the delivery method of report is online, and then it will open new window named "ResMgrOnlineReport"
 */
package com.activenetwork.qa.awo.pages.orms.common.reports;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * A general Orms online report page for PDF,Excel and DHTML reports
 * 
 * @author QA
 */
public class OrmsOnlineReportProcessingPage extends OrmsPage {
	private String attributeName;
	private Object value;
	private static OrmsOnlineReportProcessingPage instance = null;

	public static OrmsOnlineReportProcessingPage getInstance() {
		if (instance == null) {
			instance = new OrmsOnlineReportProcessingPage();
		}
		return instance;
	}

	protected OrmsOnlineReportProcessingPage(){
		browser=null;
		attributeName="url";
//		value=new RegularExpression(".*\\.reserveamerica\\.com.*fileName=report\\d+\\.pdf",false);
		value=new RegularExpression(".*/(jreport/dhtmljsp/(dhtml|index)|desk/order/jsp/ProcessReportWait)\\.jsp\\?.*",false);
		timeout=Integer.parseInt(TestProperty.getProperty("page.loading.onlinereport.wait"));
	}

	public boolean exists() {
		browser=Browser.getInstance().getBrowser(attributeName, value);
		boolean exists= browser!=null;
		
		return exists;
	}

	/** Close jreport page*/
	public void close() {
		browser.close();
	}
	
//	/**
//	 * Get a DHTML report parser object. 
//	 * User needs to make sure the DHTML report page exists before using this object
//	 * @return
//	 */
//	public OrmsDHTMLReport getDHTMLReportParser() {
//		return new OrmsDHTMLReport(browser);
//	}
}
