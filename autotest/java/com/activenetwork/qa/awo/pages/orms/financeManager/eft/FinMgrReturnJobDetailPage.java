package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTReturnJobInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @Description: This is EFT returns job search/list page:FM/LM --->EFT ---> Returns Jobs --->click jobId
 * @author pchen
 * @Date  August 15, 2012
 */
public class FinMgrReturnJobDetailPage extends FinMgrReturnsJobPage {
	static private FinMgrReturnJobDetailPage _instance = null;
	private String prefix = "EFTReturnJobView-\\d+\\.";

	protected FinMgrReturnJobDetailPage() {
	}

	static public FinMgrReturnJobDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrReturnJobDetailPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text",
				"EFT Returns Job Info");
	}
    /**
     * Get job id
     * @return
     */
	public String getJobId() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"id", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job ID DIV object.");
		}
		String jobId = objs[0].getProperty(".text").split("Job ID")[1].trim();
		Browser.unregister(objs);
		return jobId;
	}
	/**
	 * Get job status
	 * @return
	 */
	public String getJobStatus(){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"status:CB_TO_NAME", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job status DIV object.");
		}
		String jobStatus = objs[0].getProperty(".text").split("Job Status")[1].trim();
		Browser.unregister(objs);
		return jobStatus;
	}
	/**
	 * Get matching status
	 * @return
	 */
	public String getJobMatchingStatus(){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"matchStatus:CB_TO_NAME", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job matching status DIV object.");
		}
		String matchingStatus = objs[0].getProperty(".text").split("Matching Status")[1].trim();
		Browser.unregister(objs);
		return matchingStatus;
	}
    /**
     * Get location
     * @return
     */
    public String getLocation(){
    	IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"location", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job location DIV object.");
		}
		String matchingStatus = objs[0].getProperty(".text").split("Location")[1].trim();
		Browser.unregister(objs);
		return matchingStatus;
    }
    /**
     * Get transmission file name
     * @return
     */
    public String getFileName(){
    	IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"fileName", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job file name DIV object.");
		}
		String matchingStatus = objs[0].getProperty(".text").split("Filename")[1].trim();
		Browser.unregister(objs);
		return matchingStatus;
    }
    
    /**
     * Get the transaction number in the return job
     * @return
     */
    public String getTransNum(){
    	IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"numberOfTrans", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job transaction number DIV object.");
		}
		String matchingStatus = objs[0].getProperty(".text").split("# Returns")[1].trim();
		Browser.unregister(objs);
		return matchingStatus;
    }
    
    /**
     * Get the correction number in the return job
     * @return
     */
    public String getCorrectionNum(){
    	IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"numberOfCorrection", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job correction number DIV object.");
		}
		String matchingStatus = objs[0].getProperty(".text").split("# Corrections")[1].trim();
		Browser.unregister(objs);
		return matchingStatus;
    }
    /**
     * Get the start time of eft return job
     * @return
     */
    public String getRunStartDateTime(){
    	IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"startDate:DATE_TIME2", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job run start time DIV object.");
		}
		String matchingStatus = objs[0].getProperty(".text").split("Run Start Date/Time")[1].trim();
		Browser.unregister(objs);
		return matchingStatus;
    }
    /**
     * Get the end time of eft return job
     * @return
     */
    public String getRunEndDateTime(){
    	IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"endDate:DATE_TIME2", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job run end time object.");
		}
		String matchingStatus = objs[0].getProperty(".text").split("Run End Date/Time")[1].trim();
		Browser.unregister(objs);
		return matchingStatus;
    }
    /**
     * Get the run location of eft return job
     * @return
     */
    public String getRunLocation(){
    	IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"runLocation", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job run location DIV object.");
		}
		String matchingStatus = objs[0].getProperty(".text").split("Run Location")[1].trim();
		Browser.unregister(objs);
		return matchingStatus;
    }
    
    /**
     * Get the run user of eft return job
     * @return
     */
    public String getRunUser(){
    	IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(prefix+"runUser", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find return Job run location DIV object.");
		}
		String matchingStatus = objs[0].getProperty(".text").split("Run User")[1].trim();
		Browser.unregister(objs);
		return matchingStatus;
    }
    
    /**
     * Get return job all summary information on the page
     * @return
     */
    public EFTReturnJobInfo getEFTReturnJobSummaryOnPage(){
    	EFTReturnJobInfo returnInfo = new EFTReturnJobInfo();
    	returnInfo.setJobId(getJobId());
    	returnInfo.setJobStatus(getJobStatus());
    	returnInfo.setMatchingStatus(getJobMatchingStatus());
    	returnInfo.setLocation(getLocation());
    	returnInfo.setFileName(getFileName());
    	returnInfo.setReturnsNum(getTransNum());
    	returnInfo.setCorrectionsNum(getCorrectionNum());
    	returnInfo.setRunStartDateTime(getRunStartDateTime());
    	returnInfo.setRunEndDateTime(getRunEndDateTime());
    	returnInfo.setRunLocation(getRunLocation());
    	returnInfo.setRunUser(getRunUser());
    	return returnInfo;
    }
	/**
	 * Click back button
	 */
	public void clickBack(){
		browser.clickGuiObject(".class", "Html.A",".text","Back");
	}

}
