/*
 * Created on Jan 28, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.resourceManager.recipient;


import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResourceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResMgrRecipientListDetailsPage extends ResourceManagerPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	private ResMgrRecipientListDetailsPage() {
	}

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	private static ResMgrRecipientListDetailsPage instance = null;

	/**
	 * @return The unique instance of this class.
	 */
	public static ResMgrRecipientListDetailsPage getInstance() {
		if (null == instance) {
			instance = new ResMgrRecipientListDetailsPage();
		}
		return instance;
	}

	/**
	 * Check whether the specific page mark displayed
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",new RegularExpression("Enter Recipient Information",false));
	}
	
	public String getRecipientListId()
	{
		String text = browser.getObjectText(".class","Html.SPAN",".text",new RegularExpression("^Recipient List ID.*",false));
	  	return RegularExpression.getMatches(text, "\\d+")[0];
	}
	
	public String getFacilityName()
	{
		String text = browser.getObjectText(".class","Html.SPAN",".text",new RegularExpression("^Facility Name.*",false));
	  	return text.replaceAll("Facility Name", "").trim();
	}
	
	public void selectReportGroup(String group)
	{
	  	browser.selectDropdownList(".id","_SchedulerReportGroupId",group);
	}
	
	public String getReportGroup()
	{
	  	return browser.getDropdownListValue(".id","_SchedulerReportGroupId",0);
	}
	
	public void selectReport(String report)
	{
	  	browser.selectDropdownList(".id","_SchedulerReportId",report);
	}
	
	public String getReport()
	{
	  	return browser.getDropdownListValue(".id","_SchedulerReportId",0);
	}
	
	public void setNewRecipient(String recipient)
	{
	  	browser.setTextField(".id","__SchedulerEmailManuBaseRecipients",recipient);
	}

	public String[] getRecipientEmail()
	{
	  	IHtmlObject[] objs = browser.getHtmlObject(".class","Html.SELECT",".id","__SchedulerEmailAutoChosenRecipients");
	  	String text = objs[0].getProperty(".text").toString();
	  	Browser.unregister(objs);

	  	return text.split(" ");
	}
	
	/**
	 * This method used to add a manual recipient
	 *
	 */
	public void clickAddNew()
	{
	  	IHtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".text","Add>>");
	  	((ILink)objs[0]).click();
	  	waitLoading();
	  	Browser.unregister(objs);
	}
	/**
	 * This method used to select recipient from list
	 * @param recipient
	 */
	public void selectFromList(String recipient)
	{
	  	browser.selectDropdownList(".id","__SchedulerEmailAutoBaseRecipients",recipient);
	}
	
	public String getRecipientFromList()
	{
	  	IHtmlObject[] objs = browser.getHtmlObject(".class","Html.SELECT",".id","__SchedulerEmailAutoChosenRecipients");
	  	String text = objs[1].getProperty(".text").toString();
	  	Browser.unregister(objs);
	  	return text;
	}
	/**
	 * This method used to add a recipient from list
	 *
	 */
	public void clickAddFromList()
	{
	  	IHtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".text","Add>>");
	  	((ILink)objs[1]).click();
	  	waitLoading();
	  	Browser.unregister(objs);
	}
	
	public void setFaxTo(String faxTo)
	{
	  	browser.setTextField(".id","__SchedulerFaxTo",faxTo);
	}
	
	
	public boolean checkFaxToExists(String toName)
	{
	  	return browser.checkHtmlObjectExists(".class","Html.INPUT.text",".value",toName);
	}
	
	public void setFaxFrom(String faxFrom)
	{
	  	browser.setTextField(".id","__SchedulerFaxFrom",faxFrom);
	}
	
	public boolean checkFaxFromExists(String fromName)
	{
	  	return browser.checkHtmlObjectExists(".class","Html.INPUT.text",".value",fromName);
	}
	
	public void setFaxNum(String faxNum)
	{
	  	browser.setTextField(".id","__SchedulerFaxNumber",faxNum);
	}
	
	public boolean checkFaxNumExists(String faxNum)
	{
	  	return browser.checkHtmlObjectExists(".class","Html.INPUT.text",".value",faxNum);
	}
	
	public void clickAddFaxRecipient()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Add Fax Recipient");
	}
	
	public void clickSave()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Save");
	}
	
	public void clickOk()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	/**
	 * This method used to enter all recipient information
	 * @param rd-ReportData
	 */
	public void setupRecipientInfo(ReportData rd)
	{
	  	if(rd.group!=null&&!rd.group.equals("")){
	  		selectReportGroup(rd.group);
	  		this.waitLoading();
	  	}
	  	if(rd.reportName!=null&&!rd.reportName.equals("")){
	  		selectReport(rd.reportName);
	  		this.waitLoading();
	  	}
	  	if(rd.recipient_name!=null&&!rd.recipient_name.equals("")){
	  		setNewRecipient(rd.recipient_name);
	  		clickAddNew();
	  	}
	  	if(rd.recipientFromList!=null&&!rd.recipientFromList.equals("")){
	  	  selectFromList(rd.recipientFromList);
	  	  clickAddFromList();
	  	}
	  	if(rd.faxTo!=null&&!rd.faxTo.equals("")){
	  	  setFaxTo(rd.faxTo);
	  	}
	  	if(rd.faxFrom!=null&&!rd.faxFrom.equals("")){
	  	  setFaxFrom(rd.faxFrom);
	  	}
	  	if(rd.faxNum!=null&&!rd.faxNum.equals("")){
	  	  setFaxNum(rd.faxNum);
	  	  clickAddFaxRecipient();
	  	  waitLoading();
	  	}
	  	
	  	clickSave();
	}
	
	/**
	 * This method used to verify recipient detail information correct
	 * @param rd
	 */
	public void verifyDetailInfo(ReportData rd)
	{
	  	logger.info("Start to Verify Recipient Detail.");
	  	
	  	if(!rd.recipientListId.equalsIgnoreCase(this.getRecipientListId())){
	  	  throw new ErrorOnPageException("Recipient List ID "+this.getRecipientListId()+" not Correct.");
	  	}
	  	if(!rd.park.equalsIgnoreCase(this.getFacilityName())){
	  	  throw new ErrorOnPageException("Facility Name "+this.getFacilityName()+" not Correct.");
	  	}
	  	if(!rd.group.equalsIgnoreCase(this.getReportGroup())){
	  	  throw new ErrorOnPageException("Report Group "+this.getReportGroup()+" not Correct.");
	  	}
	  	if(!rd.reportName.equalsIgnoreCase(this.getReport())){
	  	  throw new ErrorOnPageException("Report Name "+this.getReport()+" not Correct.");
	  	}
	  	if(rd.recipient_name!=null&&!rd.recipient_name.equals("")){
	  	  if(!rd.recipient_name.equalsIgnoreCase(this.getRecipientEmail()[0])){
	  	    throw new ErrorOnPageException("Recipient Email "+this.getRecipientEmail()[0]+" not Correct.");
	  	  }
	  	}
	  	if(rd.recipientFromList!=null&&!rd.recipientFromList.equals("")){
	  	  if(!rd.recipientFromList.equalsIgnoreCase(this.getRecipientFromList())){
	  	    throw new ErrorOnPageException("Recipient List "+this.getRecipientFromList()+" not Correct.");
	  	  }
	  	}
	  	if(rd.faxTo!=null&&!rd.faxTo.equals("")){
	  	  if(!this.checkFaxToExists(rd.faxTo)){
	  	    throw new ErrorOnPageException("Fax To Name not Correct.");
	  	  }
	  	}
	  	if(rd.faxFrom!=null&&!rd.faxFrom.equals("")){
	  	  if(!this.checkFaxFromExists(rd.faxFrom)){
	  	    throw new ErrorOnPageException("Fax From Name not Correct.");
	  	  }
	  	}
	  	if(rd.faxNum!=null&&!rd.faxNum.equals("")){
	  	  if(!this.checkFaxNumExists(rd.faxNum)){
	  	    throw new ErrorOnPageException("Fax Number not Correct.");
	  	  }
	  	}
	  	
	  	logger.info("Recipient Details Info Correct.");
	}
}
