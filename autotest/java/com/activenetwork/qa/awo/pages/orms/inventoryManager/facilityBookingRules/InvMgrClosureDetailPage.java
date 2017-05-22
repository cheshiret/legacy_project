/*
 * $Id: InvMgrClosureDetailPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * TODO: enter class description
 * 
 * @author CGuo,jdu
 */
public class InvMgrClosureDetailPage extends InvMgrCommonTopMenuPage {

	/**
	 * Script Name   : InvMgrClosureDetailPage
	 * Generated     : Jun 14, 2005 5:13:02 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/06/14
	 */

	private static InvMgrClosureDetailPage _instance = null;

	public static InvMgrClosureDetailPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrClosureDetailPage();
		}

		return _instance;
	}

	public InvMgrClosureDetailPage() {

	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.INPUT.text",".id","ClosureScheduleView.startDate_ForDisplay");
	}
	
	/**
	 * Select Closure Type
	 * @param type
	 */
	public void selectClosureType(String type) {
		browser.selectDropdownList(".id", "ClosureScheduleView.closureTypeId",type);
	}

	/**
	 * Set Start Date
	 * @param date
	 */
	public void setStartDate(String date) {
		browser.setTextField(".id", "ClosureScheduleView.startDate_ForDisplay",date, 0, IText.Event.LOSEFOCUS);
	}

	/**
	 * Set End Date
	 * @param date
	 */
	public void setEndDate(String date) {
		browser.setTextField(".id", "ClosureScheduleView.endDate_ForDisplay",date, 0, IText.Event.LOSEFOCUS);
	}

	/**Select Active Checkbox*/
	public void selectActiveCheckBox() {
		browser.selectCheckBox(".id", "ClosureScheduleView.active");
	}

	/**Unselect Active Checkbox*/
	public void deselectActiveCheckBox() {
		browser.unSelectCheckBox(".id", "ClosureScheduleView.active");
	}

	/**
	 * Select Occurence Pattern
	 * @param pattern
	 */
	public void selectOccurencePattern(String pattern) {
		browser.selectDropdownList(".id","ClosureScheduleView.closurePatternID", pattern);
	}

	/**
	 * Input comments
	 * @param comments
	 */
	public void setComments(String comments) {
		browser.setTextArea(".id", "ClosureScheduleView.description", comments);
	}

	/**
	 * Input notification date
	 * @param date
	 */
	public void setNotificationDate(String date) {
		browser.setTextField(".id","ClosureScheduleView.notificationDate_ForDisplay", date, 0, IText.Event.LOSEFOCUS);
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**Click Cancel Button*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**Click CLosure*/
	public void clickClosure() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Closures");
	}
	
	/**Click remove closure*/
	public void clickRemoveClosure(){
		browser.clickGuiObject(".class", "Html.A", ".text", "RemoveClosure");
	}

	/**
	 * Add Closure
	 * @param closure
	 * @return
	 * @throws PageNotFoundException
	 */
	public String addClosure(Closure closure) throws PageNotFoundException {
		this.fillClosureDetails(closure);
		this.clickOK();

		waitLoading();
		String toReturn = getClosureID();
		this.clickClosure();

		return toReturn;
	}

	/**
	 * Get Closure ID 
	 * @return
	 */
	public String getClosureID() {
		String line = browser.getObjectText(".class","Html.DIV",".text",new RegularExpression("^Closure ID\\d+",false));

		return line.replaceAll("Closure ID", "").trim();
	}

	public void selectProductCategory(String product){
		if(StringUtil.isEmpty(product)){
			browser.selectDropdownList(".id", new RegularExpression("ClosureScheduleView.productCategory", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("ClosureScheduleView.productCategory", false), product);
		}
	}
	
	/**
	 * Fill Closure Details in the detail page
	 * @param closure
	 */
	public void fillClosureDetails(Closure closure) {
		ConfirmDialogPage confirmPage = ConfirmDialogPage.getInstance();
		
	  if(null!=closure.type && closure.type.length()>0){
		this.selectClosureType(closure.type);
		this.waitLoading();
	  }
	  
	  if(null != closure.productCategory){
			this.selectProductCategory(closure.productCategory);
			this.waitLoading();
	  }
	  
	  if(null!=closure.startDate && closure.startDate.length()>0){
		  this.setStartDate(closure.startDate);
		  browser.waitExists(this, confirmPage);
	  }
	  
	  if(null!=closure.endDate && closure.endDate.length()>0){
		  this.setEndDate(closure.endDate);
		  browser.waitExists(this, confirmPage);
	  }

	  if (closure.status.equalsIgnoreCase("active")) {
		  this.selectActiveCheckBox();
	  } else
		  this.deselectActiveCheckBox();

	  if(StringUtil.notEmpty(closure.occurencePattern)) {
		  this.selectOccPatterns(closure.occurencePattern);
		  this.waitLoading();
		  if(StringUtil.notEmpty(closure.dayOfMonth) && this.isDayOfMonthExisted()){
			  this.setDayOfMonth(closure.dayOfMonth);
		  }else if(closure.weekDays!=null&&closure.weekDays.length>0){
			  this.selectWeekDays(closure.weekDays);
		  }
	  }
	  
	  if(null!=closure.comment && closure.comment.length()>0){
	    this.setComments(closure.comment);
	  }
	  
	  if(null!=closure.notificationDate && closure.notificationDate.length()>0){
	    this.setNotificationDate(closure.notificationDate);
	  }	
	}

	/**
	 * Verify the closure item is active or not
	 * @return
	 * @throws PageNotFoundException
	 */
	public boolean isActive() throws PageNotFoundException {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.INPUT.checkbox", ".id", "ClosureScheduleView.active");
		if (objs.length < 1) {
			Browser.unregister(objs);
			throw new PageNotFoundException("Failed to find the active checkbox.");
		}
//		boolean toReturn = (Boolean.parseBoolean(objs[0].getProperty(".value")));
		boolean toReturn = ((ICheckBox) objs[0]).isSelected();
		Browser.unregister(objs);
		return toReturn;
	}
	
	public void clickClosureSite(){
	    browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("^Closure(s)? Sites",false));
	}
	
	/**
	 * Get the warning message from closure detail page
	 * @return warning message
	 */
	public String getWarningMessage() {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		String warningMes = obj[0].getProperty(".text").toString();
		Browser.unregister(obj);
		return warningMes;
	}
	
	/**
	 * Check button 'View Change Request Items' exist or not
	 * @return
	 */
	public boolean checkViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", rex);
	}
	
	/** Click the button 'View Change Request Items'*/
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}

	public void clickApply() {
		browser.clickGuiObject(".class","Html.A",".text","Apply");
		
	}
	public List<String> getClosureTypeValues() {
		return browser.getDropdownElements(".id", "ClosureScheduleView.closureTypeId");
	}
	
	public List<String> getPrdCategoryValues() {
		return browser.getDropdownElements(".id", "ClosureScheduleView.productCategory");
	}

	public List<String> getOccPatternsValues() {
		return browser.getDropdownElements(".id", "ClosureScheduleView.closurePatternID");
	}
	
	public boolean isDayOfMonthExisted() {
		return browser.checkHtmlObjectDisplayed(".class", "Html.INPUT.TEXT", ".id", "ClosureScheduleView.dayOfMonth");
	}
	
	public boolean isSundayCheckboxExisted() {
		return browser.checkHtmlObjectDisplayed(".class", "Html.INPUT.checkbox", ".id", "patternWeekday0");
	}
	
	public String getClosureType() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Closure Type.*", false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find TR object for closure type on page.");
		String value = objs[0].text().replaceAll("Closure Type", "").trim();
		Browser.unregister(objs);
		return value;
	}
	
	public String getProductCategory() {
		return browser.getDropdownListValue(".id", "ClosureScheduleView.productCategory");
	}
	
	public String getStartDate() {
		return browser.getTextFieldValue(".id", "ClosureScheduleView.startDate_ForDisplay");
	}
	
	public String getEndDate() {
		return browser.getTextFieldValue(".id", "ClosureScheduleView.endDate_ForDisplay");
	}
	
	public String getAffRes() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Affected Reservations.*", false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find TR object for Affected Reservations on page.");
		IHtmlObject[] inputObjs = browser.getHtmlObject(".class", "Html.INPUT.TEXT", objs[0]);
		if(inputObjs.length<1)
			throw new ItemNotFoundException("Could not find DIV object under Affected Reservations TR object on page.");
		String value = inputObjs[0].getAttributeValue("value");
		Browser.unregister(objs);
		return value;
	}	
	
	public String getApplication() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Application.*", false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find TR object for Application on page.");
		IHtmlObject[] inputObjs = browser.getHtmlObject(".class", "Html.INPUT.TEXT", objs[0]);
		if(inputObjs.length<1)
			throw new ItemNotFoundException("Could not find DIV object under Application TR object on page.");
		String value = inputObjs[0].getAttributeValue("value");
		Browser.unregister(objs);
		return value;
	}	
	
	public String getComments() {
		return browser.getTextAreaValue(".id", "ClosureScheduleView.description");
	}
	
	public String getNotificationDate() {
		return browser.getTextFieldValue(".id", "ClosureScheduleView.notificationDate_ForDisplay");
	}
	
	public String getCreateDate() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Create Date.*", false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find TR object for Create Date on page.");
		String value = objs[0].text().replaceAll("Create Date", "").trim();
		Browser.unregister(objs);
		return value;
	}	
	
	public boolean isClosureTypeDropdownListExisted() {
		return browser.checkHtmlObjectDisplayed(".class", "Html.INPUT.selected", ".id", "selectClosureType");
		}
		
		public boolean isProdCategoryDropdownListEditable() {
			IHtmlObject[] objs = browser.getDropdownList(".id", "ClosureScheduleView.productCategory");
			if(objs.length<1)
				throw new ItemNotFoundException("Could not get product category drop down on page.");
			boolean disabled = Boolean.parseBoolean(((ISelect)objs[0]).getAttributeValue("isDisabled"));
			Browser.unregister(objs);
			return !disabled;
		}
		
		public boolean isStartDateTextFieldEditable() {
			return browser.checkHtmlObjectEnabled(".class", "Html.INPUT.Text", ".id", "ClosureScheduleView.startDate_ForDisplay");
		}
		
		public boolean isEndDateTextFieldEditable() {
			return browser.checkHtmlObjectEnabled(".class", "Html.INPUT.Text", ".id", "ClosureScheduleView.endDate_ForDisplay");
		}
		
		public boolean isActiveCheckboxEditable() {
			return browser.checkHtmlObjectEnabled(".class", "Html.INPUT.checkbox", ".id", "ClosureScheduleView.active");
		}
		
		public boolean isAffResEditable() {
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Affected Reservations.*", false));
			if(objs.length<1)
				throw new ItemNotFoundException("Could not find TR object for Affected Reservations on page.");
			IHtmlObject[] inputObjs = browser.getHtmlObject(".class", "Html.INPUT.TEXT", objs[0]);
			if(inputObjs.length<1)
				throw new ItemNotFoundException("Could not find DIV object under Affected Reservations TR object on page.");
			boolean disabled = Boolean.parseBoolean(inputObjs[0].getAttributeValue("isDisabled"));
			Browser.unregister(objs);
			Browser.unregister(inputObjs);
			return !disabled;
		}
		
		public boolean isApplicationEditable() {
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Application.*", false));
			if(objs.length<1)
				throw new ItemNotFoundException("Could not find TR object for Application on page.");
			IHtmlObject[] inputObjs = browser.getHtmlObject(".class", "Html.INPUT.TEXT", objs[0]);
			if(inputObjs.length<1)
				throw new ItemNotFoundException("Could not find DIV object under Application TR object on page.");
			boolean disabled = Boolean.parseBoolean(inputObjs[0].getAttributeValue("isDisabled"));
			Browser.unregister(objs);
			Browser.unregister(inputObjs);
			return !disabled;
		}
		
		public boolean isOccPatternsDropdownListEditable() {
			return browser.checkHtmlObjectEnabled(".class", "Html.INPUT.selected", ".id", "ClosureScheduleView.closurePatternID");
		}
		
		public boolean isDayOfMonthEditable() {
			return browser.checkHtmlObjectEnabled(".class", "Html.INPUT.TEXT", ".id", "ClosureScheduleView.dayOfMonth");
		}
		
		public boolean isCommentsTextAreaEditable() {
			return browser.checkHtmlObjectEnabled(".class", "Html.TEXTAREA", ".id", "ClosureScheduleView.description");
		}
		
		public boolean isNotificationDateEditable() {
			return browser.checkHtmlObjectEnabled(".class", "Html.INPUT.text", ".id", "ClosureScheduleView.notificationDate_ForDisplay");
		}
		
		public boolean isCreateDateEditable() {
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Create Date.*", false));
			if(objs.length<1)
				throw new ItemNotFoundException("Could not find TR object for Create Date on page.");
			IHtmlObject[] inputObjs = browser.getHtmlObject(".class", "Html.INPUT.text", objs[0]);
			boolean editable = false;
			if(inputObjs.length>0) {
				editable = true;
			}
			Browser.unregister(objs);
			Browser.unregister(inputObjs);
			return editable;
		}
		
		public void clickClosureSlip(){
		    browser.clickGuiObject(".class", "Html.A", ".text", "Closure Slips");
		}
		
		public void selectOccPatterns(String occPattern) {
			browser.selectDropdownList(".id", "ClosureScheduleView.closurePatternID", occPattern);
		}
		
		public void setDayOfMonth(String value){
			browser.setTextField(".id", "ClosureScheduleView.dayOfMonth", value);
		}
		
		public void selectWeekDays(String...days){
			this.unselectAllWeekDays();
			for(String day:days){
				selectWeekDay(day);
			}
		}
		
		public void unselectAllWeekDays(){
			IHtmlObject[] objs = browser.getCheckBox(".id",new RegularExpression("patternWeekday\\d+",false));
			for(int i=0;i<objs.length;i++){
				((ICheckBox)objs[i]).deselect();
			}
		}
		
		public void selectWeekDay(String day){
			int value;
			if(StringUtil.notEmpty(day)){
				switch(day)
				{
				case "Sun":value=0;
					break;
				case "Mon":value=1;
					break;
				case "Tue":value=2;
					break;
				case "Wed":value=3;
					break;
				case "Thu":value=4;
					break;
				case "Fri":value=5;
					break;
				case "Sat":value=6;
					break;
				default:value=-1;
				}
				browser.selectCheckBox(".id","patternWeekday"+value);
			}
		}
		
		public String getOccPattern() {
			return browser.getDropdownListValue(".id", "ClosureScheduleView.closurePatternID");
		}
		
		public String getDayOfMonth() {
			return browser.getTextFieldValue(".id", "ClosureScheduleView.dayOfMonth");
		}
}
