/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoicingJobInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author szhou
 * 
 */
public class FinMgrInvoicingJobsDetailPage extends FinMgrInvoicingJobsPage {
	static private FinMgrInvoicingJobsDetailPage _instance = null;

	protected FinMgrInvoicingJobsDetailPage() {
	}

	static public FinMgrInvoicingJobsDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrInvoicingJobsDetailPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text",
				"EFT Invoicing Job Info");
	}
	
	public void clickInvoiceTab() {
		browser.clickGuiObject(".id", new RegularExpression(
				"EFTInvoicingJobDetailsTabs.*", false), ".text", "Invoices");
	}


	public void clickRemittanceTab() {
		browser.clickGuiObject(".id", new RegularExpression(
				"EFTInvoicingJobDetailsTabs.*", false), ".text", "Remittances");
	}

	/**
	 * Get invoicing job id
	 * @return
	 */
	public String getInvoicingJobId() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("EFTInvoiceJobView-\\d+\\.id", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Invoice Job ID DIV object.");
		}
		
		String jobId = objs[0].getProperty(".text").split("Job ID")[1].trim();
		Browser.unregister(objs);
		
		return jobId;
	}
	
	
	/**
	 * Get invoice date
	 * @return
	 */
	public String getInvoiceDate() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("EFTInvoiceJobView-\\d+\\.invoiceDate:DATE", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Invoice Date DIV object.");
		}
		
		String date = objs[0].getProperty(".text").split("Invoice Date")[1].trim();
		Browser.unregister(objs);
		
		return date;
	}
	
	/**
	 * Get period end date
	 * @return
	 */
	public String getPeriodEndDate() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("EFTInvoiceJobView-\\d+\\.periodDate:DATE", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Period End Date DIV object.");
		}
		
		String date = objs[0].getProperty(".text").split("Period End Date")[1].trim();
		Browser.unregister(objs);
		
		return date;
	}
	
	public EFTInvoicingJobInfo getEFTInvoiceJobInfo(){
		EFTInvoicingJobInfo info = new EFTInvoicingJobInfo();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^EFT Invoicing Job Info",false));
		
		String allText = objs[0].text();
		String label, nextlabel;
		
		label = "Job ID";nextlabel="Status";
		info.setJobID(allText.substring(allText.indexOf(label)+label.length(), allText.indexOf(nextlabel)).trim());
		
		label = nextlabel;nextlabel="Location";
		info.setStatus(allText.substring(allText.indexOf(label)+label.length(), allText.indexOf(nextlabel)).trim());
		
		label = nextlabel;nextlabel="Invoice Frequency";
		info.setLocation(allText.substring(allText.indexOf(label)+label.length(), allText.indexOf(nextlabel)).trim());
		
		label = nextlabel;nextlabel="Invoice Date";
		info.setFrequency(allText.substring(allText.indexOf(label)+label.length(), allText.indexOf(nextlabel)).trim());
		
		label = nextlabel;nextlabel="Period End Date";
		info.setInvoiceDate(allText.substring(allText.indexOf(label)+label.length(), allText.indexOf(nextlabel)).trim());
		
		label = nextlabel;nextlabel="Run Start Date/Time";
		info.setPeriodDate(allText.substring(allText.indexOf(label)+label.length(), allText.indexOf(nextlabel)).trim());
		
		label = nextlabel;nextlabel="Run End Date/Time";
		info.setRunStartTime(allText.substring(allText.indexOf(label)+label.length(), allText.indexOf(nextlabel)).trim());
		
		label = nextlabel;nextlabel="Run Location";
		info.setRunEndTime(allText.substring(allText.indexOf(label)+label.length(), allText.indexOf(nextlabel)).trim());
		
		label = nextlabel;nextlabel="Run User";
		info.setRunLocation(allText.substring(allText.indexOf(label)+label.length(), allText.indexOf(nextlabel)).trim());
		
		label = nextlabel;
		info.setRunUser(allText.substring(allText.indexOf(label)+label.length()));
		
		Browser.unregister(objs);
		return info;
	}

}
