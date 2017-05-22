/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrEditSuspensionWiget.java
 * @Date:Feb 15, 2011
 * @Description:
 * @author asun
 */
public class LicMgrEditSuspensionWidget extends DialogWidget {
	
	private static LicMgrEditSuspensionWidget instance=null;
	
	private LicMgrEditSuspensionWidget(){}
	
	public static LicMgrEditSuspensionWidget getInstance(){
		if(instance==null){
			instance=new LicMgrEditSuspensionWidget();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		RegularExpression regx=new RegularExpression("^Edit Suspension.*Pop\\-Upclose$",false);
		boolean flag1=super.exists();
		boolean flag2=browser.checkHtmlObjectExists(".class", "Html.DIV",".text",regx);
		return flag1&&flag2;
	}
	
	public void setBeginDate(String beginDate){
		browser.setTextField(".id",new RegularExpression("SuspensionDetailView-\\d+\\.beginDate_ForDisplay",false),beginDate);
	}
	
	public void setEndDate(String endDate){
		RegularExpression regx=new RegularExpression("SuspensionDetailView-\\d+\\.endDate_ForDisplay",false);
		browser.setTextField(".id", regx, endDate);
	}
	
	public void setComments(String comm){
		RegularExpression regx=new RegularExpression("SuspensionDetailView-\\d+\\.comments",false);
	    browser.setTextArea(".id", regx, comm);
	}
	
	public void setSuspension(Suspension s){
		this.setBeginDate(s.beginDate);
		this.setEndDate(s.endDate);
		this.setComments(s.comment);
	}

	/**
	 * Get the error message displayed at the top of the widget
	 * @return
	 */
	public String getErrorMessage() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".className", "message msgerror");
		if(objs.length < 1) {
			throw new ErrorOnPageException("Can't find Error Message div object.");
		}
		String errorMsg = objs[0].getProperty(".text").trim();
		Browser.unregister(objs);
		
		return errorMsg;
	}
	
	public String getSuspensionID() {
		return browser.getTextFieldValue(".id", new RegularExpression("SuspensionDetailView-\\d+\\.id:ZERO_TO_NEW", false));
	}
	
	public String getSuspensionStatus() {
		return browser.getTextFieldValue(".id", new RegularExpression("SuspensionDetailView-\\d+\\.status:CB_TO_NAME", false));
	}
	
	public String getSuspensionType() {
		return browser.getTextFieldValue(".id", new RegularExpression("SuspensionDetailView-\\d+\\.suspensionType:CB_TO_NAME", false));
	}
	
	public String getBeginDate() {
		return browser.getTextFieldValue(".id", new RegularExpression("SuspensionDetailView-\\d+\\.beginDate_ForDisplay", false));
	}
	
	public String getEndDate() {
		return browser.getTextFieldValue(".id", new RegularExpression("SuspensionDetailView-\\d+\\.endDate_ForDisplay", false));
	}
	
	public String getDatePosted() {
		return browser.getTextFieldValue(".id", new RegularExpression("SuspensionDetailView-\\d+\\.postDate", false));
	}
	
	public String getComments() {
		String comments = "";
		IHtmlObject[] objs= browser.getTextArea(".id", new RegularExpression("SuspensionDetailView-\\d+\\.comments", false));
		if(objs.length>0){
			comments = objs[0].text();
		}else throw new ObjectNotFoundException("Comments object can't be found.");

		Browser.unregister(objs);
		return comments;
//		return browser.getTextFieldValue(".id", new RegularExpression("SuspensionDetailView-\\d+\\.comments", false));
	}
	
	public String getCreationDateTime() {
		return browser.getTextFieldValue(".id", new RegularExpression("SuspensionDetailView-\\d+\\.creationInfo.date", false));
	}
	
	public String getCreationUser() {
		return browser.getTextFieldValue(".id", new RegularExpression("SuspensionDetailView-\\d+\\.creationInfo.userName", false));
	}
	
	/**
	 * Get the customer identifier types
	 * @return
	 */
	public String[] getIdentifiersTypes() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "susp_detail_ident_list");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find Customer identifier table object.");
		}
		
		String identifierTypes[] = ((IHtmlTable)objs[0]).getColumnValues(0).toArray(new String[0]);
		
		Browser.unregister(objs);
		return identifierTypes;
	}
	
	/**
	 * Get the customer identifier numbers
	 * @return
	 */
	public String[] getIdentifiersNums() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "susp_detail_ident_list");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find Customer identifier table object.");
		}
		
		String identifierNums[] = ((IHtmlTable)objs[0]).getColumnValues(1).toArray(new String[0]);
		
		Browser.unregister(objs);
		return identifierNums;
	}
	
	public Suspension getSuspensionInfo() {
		Suspension suspension = new Suspension();
		suspension.id = this.getSuspensionID();
		suspension.status = this.getSuspensionStatus();
		suspension.type = this.getSuspensionType();
		suspension.beginDate = this.getBeginDate();
		suspension.endDate = this.getEndDate();
		suspension.datePosted = this.getDatePosted();
		suspension.comment = this.getComments();
		suspension.creationDateTime = this.getCreationDateTime();
		suspension.creationUser = this.getCreationUser();
		suspension.identifiersTypes = this.getIdentifiersTypes();
		suspension.identifiersNums = this.getIdentifiersNums();
		
		return suspension;
	}
	
	/**
	 * Compare actual suspension detail info with expected
	 * @param expectedSuspension
	 * @return
	 */
	public boolean compareSuspensionInfo(Suspension expectedSuspension) {
		Suspension actualSuspension = this.getSuspensionInfo();
		
		boolean result = true;
		if(!actualSuspension.id.equals(expectedSuspension.id)) {
			logger.error("Suspension ID doesn't match. Actual value is: " + actualSuspension.id + " but Expected value is: " + expectedSuspension.id);
			result &= false;
		}
		if(!actualSuspension.status.equals(expectedSuspension.status)) {
			logger.error("Suspension Status doesn't match. Actual value is: " + actualSuspension.status + " but Expected value is: " + expectedSuspension.status);
			result &= false;
		}
		if(!actualSuspension.type.equals(expectedSuspension.type)) {
			logger.error("Suspension Type doesn't match. Actual value is: " + actualSuspension.type + " but Expected value is: " + expectedSuspension.type);
			result &= false;
		}
		if(DateFunctions.compareDates(actualSuspension.beginDate, expectedSuspension.beginDate) != 0) {
			logger.error("Suspension Begin Date doesn't match. Actual value is: " + actualSuspension.beginDate + " but Expected value is: " + expectedSuspension.beginDate);
			result &= false;
		}
		if(DateFunctions.compareDates(actualSuspension.endDate, expectedSuspension.endDate) != 0) {
			logger.error("Suspension End Date doesn't match. Actual value is: " + actualSuspension.endDate + " but Expected value is: " + expectedSuspension.endDate);
			result &= false;
		}
		if(DateFunctions.compareDates(actualSuspension.datePosted, expectedSuspension.datePosted) != 0) {
			logger.error("Suspension Date Posted doesn't match. Actual value is: " + actualSuspension.datePosted + " but Expected value is: " + expectedSuspension.datePosted);
			result &= false;
		}
		if(!actualSuspension.comment.equals(expectedSuspension.comment)) {
			logger.error("Suspension Comment doesn't match. Actual value is: " + actualSuspension.comment + " but Expected value is: " + expectedSuspension.comment);
			result &= false;
		}
		if(DateFunctions.compareDates(actualSuspension.creationDateTime, expectedSuspension.creationDateTime) != 0) {
			logger.error("Suspension Creation Date Time doesn't match. Actual value is: " + actualSuspension.creationDateTime + " but Expected value is: " + expectedSuspension.creationDateTime);
			result &= false;
		}
		if(!actualSuspension.creationUser.equals(expectedSuspension.creationUser)) {
			logger.error("Suspension Creation User doesn't match. Actual value is: " + actualSuspension.creationUser + " but Expected value is: " + expectedSuspension.creationUser);
			result &= false;
		}
		
		return result;
	}
}
