package com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.LoterrySchExclusion;
import com.activenetwork.qa.awo.datacollection.legacy.LotterySchedule;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.InvalidDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


public class LotteryScheduleDetailsPage extends InvMgrCommonTopMenuPage {
	private String prefix = "(Slip|Site|Permit|Ticket)LotteryScheduleView\\.";

	private static LotteryScheduleDetailsPage _instance = null;

	public static LotteryScheduleDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new LotteryScheduleDetailsPage();
		}

		return _instance;
	}

	protected LotteryScheduleDetailsPage() {
	}

	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text",
//				new RegularExpression("Lottery Schedule.*Lottery Schedule ID",false));
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id","LotteryScheduleDetailTab");
	}

	public void setAppStartDate(String sDate,String prd_category) {
		String id = "LotteryScheduleView.applicationStartDate_date_ForDisplay";

		id= prd_category+id;

		browser.setTextField(".id",id,sDate,0,IText.Event.LOSEFOCUS);
	}

	public void selectAppStartDateHour(String hour,String prd_category) {
		String id = "LotteryScheduleView.applicationStartDate_hour";
		id= prd_category+id;

		browser.selectDropdownList(".id",id,hour);
	}

	public void selectAppStartDateMinute(String min,String prd_category) {
		String id = "LotteryScheduleView.applicationStartDate_minute";
		id= prd_category+id;

		browser.selectDropdownList(".id",id,min);
	}

	public void selectAppStartDateAmPm(boolean isAm,String prd_category) {
		String id = "LotteryScheduleView.applicationStartDate_ampm";
		id= prd_category+id;

		if (isAm) {
			browser.selectDropdownList(".id",id, "AM");
		} else {
			browser.selectDropdownList(".id",id, "PM");
		}
	}

	public void setAppEndDate(String eDate,String prd_category) {
		String id = "LotteryScheduleView.applicationEndDate_date_ForDisplay";
		id= prd_category+id;

		browser.setTextField(".id",id,eDate,0,IText.Event.LOSEFOCUS);
	}

	public void selectAppEndDateHour(String hour,String prd_category) {
		String id = "LotteryScheduleView.applicationEndDate_hour";
		id= prd_category+id;

		browser.selectDropdownList(".id",
				id, hour);
	}

	public void selectAppEndDateMinute(String min,String prd_category) {
		String id = "LotteryScheduleView.applicationEndDate_minute";
		id= prd_category+id;

		browser.selectDropdownList(".id",id, min);
	}

	public void selectAppEndDateAmPm(boolean isAm,String prd_category) {
		String id = "LotteryScheduleView.applicationEndDate_ampm";
		id= prd_category+id;

		if (isAm) {
			browser.selectDropdownList(".id",
					id, "AM");
		} else {
			browser.selectDropdownList(".id",
					id, "PM");
		}
	}

	public void selectCallCenter(boolean call) {
		if (call) {
			browser.selectCheckBox(".id", "CallCenter_checked");
		} else {
			browser.unSelectCheckBox(".id", "CallCenter_checked");
		}
	}

	public void setCallStartDate(String sDate) {
		browser.setTextField(".id",
				"LotteryApplicationPeriodView.startDate_3_date_ForDisplay",
				sDate,0,IText.Event.LOSEFOCUS);
	}

	public void selectCallStartDateHour(String hour) {
		browser.selectDropdownList(".id",
				"LotteryApplicationPeriodView.startDate_3_hour", hour);
	}

	public void selectCallStartDateMinute(String min) {
		browser.selectDropdownList(".id",
				"LotteryApplicationPeriodView.startDate_3_minute", min);
	}

	public void selectCallStartDateAm(boolean am) {
		if (am) {
			browser.selectDropdownList(".id",
					"LotteryApplicationPeriodView.startDate_3_ampm", "AM");
		} else {
			browser.selectDropdownList(".id",
					"LotteryApplicationPeriodView.startDate_3_ampm", "PM");
		}
	}

	public void setCallEndDate(String eDate) {
		browser
				.setTextField(
						".id",
						"LotteryApplicationPeriodView.endDate_3_date_ForDisplay",
						eDate,0,IText.Event.LOSEFOCUS);
	}

	public void selectCallEndDateHour(String hour) {
		browser.selectDropdownList(".id",
				"LotteryApplicationPeriodView.endDate_3_hour", hour);
	}

	public void selectCallEndDateMinute(String min) {
		browser.selectDropdownList(".id",
				"LotteryApplicationPeriodView.endDate_3_minute", min);
	}

	public void selectCallEndDateAm(boolean am) {
		if (am) {
			browser.selectDropdownList(".id",
					"LotteryApplicationPeriodView.endDate_3_ampm", "AM");
		} else {
			browser.selectDropdownList(".id",
					"LotteryApplicationPeriodView.endDate_3_ampm", "PM");
		}
	}

	public void selectField(boolean field) {
		if (field) {
			browser.selectCheckBox(".id", "FieldMgr_checked");
		} else {
			browser.unSelectCheckBox(".id", "FieldMgr_checked");
		}
	}

	public void setFieldStartDate(String date) {
		browser.setTextField(".id",
				"LotteryApplicationPeriodView.startDate_4_date_ForDisplay",
				date,0,IText.Event.LOSEFOCUS);
	}

	public void selectFieldStartDateHour(String hour) {
		browser.selectDropdownList(".id",
				"LotteryApplicationPeriodView.startDate_4_hour", hour);
	}

	public void selectFieldStartDateMinute(String min) {
		browser.selectDropdownList(".id",
				"LotteryApplicationPeriodView.startDate_4_minute", min);
	}

	public void selectFieldStartDateAm(boolean am) {
		if (am) {
			browser.selectDropdownList(".id",
					"LotteryApplicationPeriodView.startDate_4_ampm", "AM");
		} else {
			browser.selectDropdownList(".id",
					"LotteryApplicationPeriodView.startDate_4_ampm", "PM");
		}
	}

	public void setFieldEndDate(String date) {
		browser.setTextField(".id",
				"LotteryApplicationPeriodView.endDate_4_date_ForDisplay", date,0,IText.Event.LOSEFOCUS);
	}

	public void selectFieldEndDateHour(String hour) {
		browser.selectDropdownList(".id",
				"LotteryApplicationPeriodView.endDate_4_hour", hour);
	}

	public void selectFieldEndDateMinute(String min) {
		browser.selectDropdownList(".id",
				"LotteryApplicationPeriodView.endDate_4_minute", min);
	}

	public void selectFieldEndDateAm(boolean am) {
		if (am) {
			browser.selectDropdownList(".id",
					"LotteryApplicationPeriodView.endDate_4_ampm", "AM");
		} else {
			browser.selectDropdownList(".id",
					"LotteryApplicationPeriodView.endDate_4_ampm", "PM");
		}
	}

	public void selectWeb(boolean web) {
		if (web) {
			browser.selectCheckBox(".id", "Web_checked");
		} else {
			browser.unSelectCheckBox(".id", "Web_checked");
		}
	}

	public void setWebStartDate(String date) {
		browser.setTextField(".id",
				"LotteryApplicationPeriodView.startDate_2_date_ForDisplay",
				date,0,IText.Event.LOSEFOCUS);
	}

	public void selectWebStartDateHour(String hour) {
		browser.selectDropdownList(".id",
				"LotteryApplicationPeriodView.startDate_2_hour", hour);
	}

	public void selectWebStartDateMinute(String min) {
		browser.selectDropdownList(".id",
				"LotteryApplicationPeriodView.startDate_2_minute", min);
	}

	public void selectWebStartDateAm(boolean am) {
		if (am) {
			browser.selectDropdownList(".id",
					"LotteryApplicationPeriodView.startDate_2_ampm", "AM");
		} else {
			browser.selectDropdownList(".id",
					"LotteryApplicationPeriodView.startDate_2_ampm", "PM");
		}
	}

	public void setWebEndDate(String date) {
		browser.setTextField(".id",
				"LotteryApplicationPeriodView.endDate_2_date_ForDisplay", date,0,IText.Event.LOSEFOCUS);
	}

	public void selectWebEndDateHour(String hour) {
		browser.selectDropdownList(".id",
				"LotteryApplicationPeriodView.endDate_2_hour", hour);
	}

	public void selectWebEndDateMinute(String min) {
		browser.selectDropdownList(".id",
				"LotteryApplicationPeriodView.endDate_2_minute", min);
	}

	public void selectWebEndDateAm(boolean am) {
		if (am) {
			browser.selectDropdownList(".id",
					"LotteryApplicationPeriodView.endDate_2_ampm", "AM");
		} else {
			browser.selectDropdownList(".id",
					"LotteryApplicationPeriodView.endDate_2_ampm", "PM");
		}

	}

	public void setExcutePeriod(String ePeriod,String prd_category) {
		String id = "LotteryScheduleView.executionDateTime_date_ForDisplay";
		id= prd_category+"LotteryScheduleView.executionDateTimeStr_date_ForDisplay";

		browser.setCalendarField(".id",id,ePeriod);
	}

	public void selectExcutePeriodHour(String hour,String prd_category) {
		String id = "LotteryScheduleView.executionDateTime_hour";
		id= prd_category+"LotteryScheduleView.executionDateTimeStr_hour";

		browser.selectDropdownList(".id",id, hour);
	}

	public void selectExcutePeriodMinute(String min,String prd_category) {
		String id = "LotteryScheduleView.executionDateTime_minute";
		id= prd_category+"LotteryScheduleView.executionDateTimeStr_minute";

		browser.selectDropdownList(".id",id, min);
	}

	public void selectExcutePeriodAmPm(boolean am,String prd_category) {
		String id = "LotteryScheduleView.executionDateTime_ampm";
		id= prd_category+"LotteryScheduleView.executionDateTimeStr_ampm";

		if (am) {
			browser.selectDropdownList(".id",id, "AM");
		} else {
			browser.selectDropdownList(".id",id, "PM");
		}
	}

	public void setFreezeDuration(String duration,String prd_category) {
		String id = "LotteryScheduleView.freezeDurationInDaysStr";
		id= prd_category+id;

		browser.setTextField(".id", id,
				duration);
	}

	public String getFreezeDuration(String prd_category) {
		String id = "LotteryScheduleView.freezeDurationInDaysStr";
		id= prd_category+id;

		String duration = browser.getTextFieldValue(".id",id);
		return duration;
	}

	private boolean checkInventoryStartDateExist(String id){
		return browser.checkHtmlObjectExists("id", id);
	}
	public void setInventoryStartDate(String sDate,String prd_category) {
		String id = "LotteryScheduleView.invStartDate_ForDisplay";
		id= prd_category+id;
		if(this.checkInventoryStartDateExist(id)){
			browser.setCalendarField(".id",id,sDate);
		}
//		this.moveFocusOutOfDateComponent();
	}

	public String getInventorStartDate(String sDate,String prd_category){
		String id = "LotteryScheduleView.invStartDate_ForDisplay";
		id= prd_category+id;

		return browser.getTextFieldValue(".id",id);
	}
	
	public boolean checkInventoryEndDateExist(String id){
		return browser.checkHtmlObjectExists("id", id);
	}
	
	public void setInventoryEndDate(String eDate,String prd_category) {
		String id = "LotteryScheduleView.invEndDate_ForDisplay";
		id= prd_category+id;
		if(this.checkInventoryEndDateExist(id)){
			browser.setCalendarField(".id",id, eDate);
		}
	}
	
	public String setInvalidInventoryEndDate(String eDate,String prd_category){
		String alertMsg = "";
		if (!StringUtil.isEmpty(eDate)) {
			String id = "LotteryScheduleView.invEndDate_ForDisplay";
			id= prd_category+id;
			
			alertMsg = setDateAndGetMessage(Property.toPropertyArray(".id", id), eDate);
		}
		return alertMsg;
	}

	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickSpaces(){
		browser.clickGuiObject(".class", "Html.LABEL", ".text", "Lottery Schedule ID");
	}
	
	public void clickAppSpaces(){
		browser.clickGuiObject(".class", "Html.TD",".className", "label_section", 0);
	}
	
	public void clickInvPeriodSpaces(){
		browser.clickGuiObject(".class", "Html.TD",".className", "label_section", 2);
	}

	public boolean checkRemoveButtonExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Remove");
	}
	
	public void removeAllExclusions(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text","Remove");
		if(objs.length>0){
			for(int i=0;i<objs.length;i++){
				if(objs[i].isEnabled()){
					objs[i].click();
					browser.waitExists();
				}
			}
		}
		Browser.unregister(objs);
		
	}

	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	public void setupLotteryScheduleInfo(LotterySchedule lotterySch,String prd_category) {
		ConfirmDialogPage confirmPg = ConfirmDialogPage.getInstance();
		this.unselectCall();
		this.unselectField();
		this.unselectWeb();
		
		// Set the Application Start Date
		if(null != lotterySch.appStartDate){
			this.setAppStartDate(lotterySch.appStartDate,prd_category);
			Object pages = browser.waitExists(this,confirmPg);
			if(confirmPg ==pages){
				confirmPg.dismiss();
				this.waitLoading();
			}
		}		
		if(null != lotterySch.appStartDateHour){
			this.selectAppStartDateHour(lotterySch.appStartDateHour,prd_category);
		}		
		if(null != lotterySch.appStartDateMinute){
			this.selectAppStartDateMinute(lotterySch.appStartDateMinute,prd_category);
		}		
		this.selectAppStartDateAmPm(lotterySch.appStartDateAM,prd_category);		

		// Set the Application End Date
		if(null != lotterySch.appEndDate){
			this.setAppEndDate(lotterySch.appEndDate,prd_category);
			Object pages = browser.waitExists(this,confirmPg);
			if(confirmPg ==pages){
				confirmPg.dismiss();
				this.waitLoading();
			}
		}	
		if(null != lotterySch.appEndDateHour){
			this.selectAppEndDateHour(lotterySch.appEndDateHour,prd_category);
		}		
		if(null != lotterySch.appEndDateMinute){
			this.selectAppEndDateMinute(lotterySch.appEndDateMinute,prd_category);
		}		
		this.selectAppEndDateAmPm(lotterySch.appEndDateAM,prd_category);
		
		// Set the sales channel
		this.selectCallCenter(lotterySch.callCenter);
		if (lotterySch.callCenter) {
			if(lotterySch.isUpdateCallCenter) {
				if(null != lotterySch.callStartDate){
					this.setCallStartDate(lotterySch.callStartDate);
					Object pages = browser.waitExists(this,confirmPg);
					if(confirmPg ==pages){
						confirmPg.dismiss();
						this.waitLoading();
					}
				}
				if(null != lotterySch.callStartDateHour){
					this.selectCallStartDateHour(lotterySch.callStartDateHour);
				}
				if(null != lotterySch.callStartDateMin){
					this.selectCallStartDateMinute(lotterySch.callStartDateMin);
				}				
				this.selectCallStartDateAm(lotterySch.callStartDateAm);

				if(null != lotterySch.callEndDate){
					this.setCallEndDate(lotterySch.callEndDate);
					Object pages = browser.waitExists(this,confirmPg);
					if(confirmPg ==pages){
						confirmPg.dismiss();
						this.waitLoading();
					}
				}
				if(null != lotterySch.callEndDateHour){
					this.selectCallEndDateHour(lotterySch.callEndDateHour);
				}
				if(null != lotterySch.callEndDateMin){
					this.selectCallEndDateMinute(lotterySch.callEndDateMin);
				}				
				this.selectCallEndDateAm(lotterySch.callEndDateAm);
			}
		}

		this.selectField(lotterySch.field);
		if (lotterySch.field) {
			if(lotterySch.isUpdateFeild) {
				if(null != lotterySch.fieldStartDate){
					this.setFieldStartDate(lotterySch.fieldStartDate);
					Object pages = browser.waitExists(this,confirmPg);
					if(confirmPg ==pages){
						confirmPg.dismiss();
						this.waitLoading();
					}
				}
				if(null != lotterySch.fieldStartDateHour){
					this.selectFieldStartDateHour(lotterySch.fieldStartDateHour);
				}
				if(null != lotterySch.fieldStartDateMin){
					this.selectFieldStartDateMinute(lotterySch.fieldStartDateMin);
				}				
				this.selectFieldStartDateAm(lotterySch.fieldStartDateAm);

				if(null != lotterySch.fieldEndDate){
					this.setFieldEndDate(lotterySch.fieldEndDate);
					Object pages = browser.waitExists(this,confirmPg);
					if(confirmPg ==pages){
						confirmPg.dismiss();
						this.waitLoading();
					}
				}
				if(null != lotterySch.fieldEndDateHour){
					this.selectFieldEndDateHour(lotterySch.fieldEndDateHour);
				}
				if(null != lotterySch.fieldEndDateMin){
					this.selectFieldEndDateMinute(lotterySch.fieldEndDateMin);
				}				
				this.selectFieldEndDateAm(lotterySch.fieldEndDateAm);
			}
		}

		this.selectWeb(lotterySch.web);
		if (lotterySch.web) {
			if(lotterySch.isUpdateWeb) {
				if(null != lotterySch.webStartDate){
					this.setWebStartDate(lotterySch.webStartDate);
					Object pages = browser.waitExists(this,confirmPg);
					if(confirmPg ==pages){
						confirmPg.dismiss();
						this.waitLoading();
					}
				}
				if(null != lotterySch.webStartDateHour){
					this.selectWebStartDateHour(lotterySch.webStartDateHour);
				}
				if(null != lotterySch.webStartDateMin){
					this.selectWebStartDateMinute(lotterySch.webStartDateMin);
				}				
				this.selectWebStartDateAm(lotterySch.webStartDateAm);

				if(null != lotterySch.webEndDate){
					this.setWebEndDate(lotterySch.webEndDate);
					Object pages = browser.waitExists(this,confirmPg);
					if(confirmPg ==pages){
						confirmPg.dismiss();
						this.waitLoading();
					}
				}
				if(null != lotterySch.webEndDateHour){
					this.selectWebEndDateHour(lotterySch.webEndDateHour);
				}
				if(null != lotterySch.webEndDateMin){
					this.selectWebEndDateMinute(lotterySch.webEndDateMin);
				}				
				this.selectWebEndDateAm(lotterySch.webEndDateAm);
			}
		}

		// Set the execute date
		if(null != lotterySch.executeDate){
			this.setExcutePeriod(lotterySch.executeDate,prd_category);
			Object pages = browser.waitExists(this,confirmPg);
			if(confirmPg ==pages){
				confirmPg.dismiss();
				this.waitLoading();
			}
		}		
		if(null != lotterySch.executeHour){
			this.selectExcutePeriodHour(lotterySch.executeHour,prd_category);
		}
		if(null != lotterySch.executeMin){
			this.selectExcutePeriodMinute(lotterySch.executeMin,prd_category);
		}
		
		this.selectExcutePeriodAmPm(lotterySch.executeAM,prd_category);
		
		// Set the inventory date
		if (null != lotterySch.invStartDate) {
			logger.info("Set inventory start date as:"+lotterySch.invStartDate);
			this.setInventoryStartDate(lotterySch.invStartDate,prd_category);
			Object pages = browser.waitExists(this,confirmPg);
			if(confirmPg ==pages){
				confirmPg.dismiss();
				this.waitLoading();
			}
		}
		if (null != lotterySch.invEndDate) {
			logger.info("Set inventory end date as:"+lotterySch.invEndDate);
			this.setInventoryEndDate(lotterySch.invEndDate,prd_category);
			Object pages = browser.waitExists(this,confirmPg);
			if(confirmPg ==pages){
				confirmPg.dismiss();
				this.waitLoading();
			}
		}
		
		// set freeze duration
		if(null != lotterySch.freezeDuration){
			this.setFreezeDuration(lotterySch.freezeDuration,prd_category);
			this.clickAppSpaces();
			ajax.waitLoading();
		}
		
		if(this.checkCustomerAcceptanceDeadlineExists(prd_category)){
			if(null != lotterySch.customerAcceptFromDate){
				setCustomerAcceptanceFromDate(lotterySch.customerAcceptFromDate, prd_category);
			}
			if(null != lotterySch.customerAcceptToDate){
				setCustomerAcceptanceToDate(lotterySch.customerAcceptToDate, prd_category);
			}
		}
		
		if (this.checkRemoveButtonExist()) {
			this.removeAllExclusions();
		}

		if (lotterySch.exclusions.size() != 0) {
			this.setExclusions(lotterySch.exclusions);
		}

		// select or unselect check box of add to waiting list
		this.selectAddToWaitingList(lotterySch.isAddToWaitList);
		if(null != lotterySch.waitList){
			this.selectWaitingList(lotterySch.waitList);
		}
		
		// set applicable season
		if(checkApplicableSeasonExist()){
			if(StringUtil.notEmpty(lotterySch.applicableSeason)){
				this.setApplicableSeason(lotterySch.applicableSeason);
			}else{
				lotterySch.applicableSeason = this.getApplicableSeason();
			}
		}
		
		// Application Level Messaging
		if(StringUtil.notEmpty(lotterySch.appHeaderMsg)){
			this.setAppHeaderMsg(lotterySch.appHeaderMsg);
		}
		
		if(StringUtil.notEmpty(lotterySch.appBodyMsg)){
			this.setLotteryTermsConditions(lotterySch.appBodyMsg);
		}

		if(StringUtil.notEmpty(lotterySch.preferencesSectionMessage)){
			this.setPrefsMsg(lotterySch.preferencesSectionMessage);
		}

		if(StringUtil.notEmpty(lotterySch.confirmationPageMessage)){
			this.setConfirmMsg(lotterySch.confirmationPageMessage);
		}
		
		// Lottery Results Messaging
		if(StringUtil.notEmpty(lotterySch.enteredStatus)){
			this.setEnteredStatus(lotterySch.enteredStatus);
		}
		
		if(StringUtil.notEmpty(lotterySch.awardedUnconfirmedState)){
			this.setAwardedUnconfirmedState(lotterySch.awardedUnconfirmedState);
		}
		
		if(StringUtil.notEmpty(lotterySch.unsuccessfulState)){
			this.setUnsuccessfulState(lotterySch.unsuccessfulState);
		}
		
		if(StringUtil.notEmpty(lotterySch.successfulState)){
			this.setSuccessfulState(lotterySch.successfulState);
		}
	}

	public String getErrorMessage() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		String errorMessage = objs[0].getProperty(".text");

		Browser.unregister(objs);
		return errorMessage;
	}
	
	public boolean checkErrorMessageIsExists(){
		return browser.checkHtmlObjectExists(".id", "NOTSET");
	}

	// Click Add button to add a Participating Location
	public void clickAddButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}

	// Set the Exclusions Start date (all index from 1)
	public void setExclusionsStartDate(String startDate, int index) {
		browser.setTextField(".name",
				"LotteryInventoryExclusionView.startDate_ForDisplay",
				startDate, index,IText.Event.LOSEFOCUS);
	}

	// Set the Exclusions End date (all index from 1)
	public void setExclusionsEndDate(String endDate, int index) {
		browser.setTextField(".name",
				"LotteryInventoryExclusionView.endDate_ForDisplay", endDate,
				index,IText.Event.LOSEFOCUS);
	}

	/**
	 * Select Participating Location in drop-down list (all index from 1)
	 * 
	 * @param index
	 *            --- option in drop-down list
	 * @param listIndex
	 *            --- the order of the drop-down lists
	 * @return the content of the selected
	 */
	public String selectParticipatingLocation(int optionIndex, int listIndex) {
		IHtmlObject[] objs = browser.getDropdownList(".id",
				"LotteryInventoryExclusionView.participateLocation");
		ISelect list = (ISelect) objs[listIndex];
		list.select(optionIndex);
		String result = list.getSelectedText();

		Browser.unregister(objs);
		return result;
	}

	public void selectParticipatingLocation(String item, int listIndex) {
		IHtmlObject[] objs = browser.getDropdownList(".id",
				"LotteryInventoryExclusionView.participateLocation");
		ISelect list = (ISelect) objs[listIndex];
		list.select(item);

		Browser.unregister(objs);
	}

	/**
	 * Click remove button according to the button order(all index from 1)
	 * 
	 * @param index
	 *            --- the order of the Remove buttons
	 */
	public void clickExclusionsRemove(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", index);
	}
	
	public boolean checkCustomerAcceptanceDeadlineExists(String prd_category){
		String id = "LotteryScheduleView.acceptanceDeadlineInDays";
		id = prd_category+"LotteryScheduleView.acceptanceStartDateTime_date_ForDisplay";
		
		return browser.checkHtmlObjectExists(".id",id);
	}

	/**
	 * Set the Customer Acceptance Deadline
	 */
	public void setCustomerAcceptanceDeadline(String acceptanceDate) {
		browser.setTextField(".id",
				"LotteryScheduleView.acceptanceDeadlineInDays", acceptanceDate);
	}
	
	public void setCustomerAcceptanceFromDate(String fromDate, String prd_category){
		String id = "LotteryScheduleView.acceptanceStartDateTime_date_ForDisplay";
		id= prd_category+id;

		browser.setCalendarField(".id", id,fromDate);
	}
	
	public void setCustomerAcceptanceToDate(String toDate, String prd_category){
		String id = "LotteryScheduleView.acceptanceEndDateTime_date_ForDisplay";
		id= prd_category+id;

		browser.setCalendarField(".id", id,toDate);
	}

	public void setExclusions(List<LoterrySchExclusion> list) {
		for (int i = 0; i < list.size(); i++) {
			ConfirmDialogPage confirmPg = ConfirmDialogPage.getInstance();
			this.clickAddButton();
			this.selectParticipatingLocation(list.get(i).participatingLocation,
					i + 1);
			this.setExclusionsStartDate(list.get(i).ExclusionsStartDate, i+1);
			Object pages = browser.waitExists(this,confirmPg);
			if(confirmPg ==pages){
				confirmPg.dismiss();
				this.waitLoading();
			}
			this.setExclusionsEndDate(list.get(i).ExclusionsEndDate, i+1);
			pages = browser.waitExists(this,confirmPg);
			if(confirmPg ==pages){
				confirmPg.dismiss();
				this.waitLoading();
			}
		}

	}
	
	public void unselectField(){
		browser.unSelectCheckBox(".id", "FieldMgr_checked");
	}
	
	public void unselectCall(){
		browser.unSelectCheckBox(".id", "CallCenter_checked");
	}
	
	public void unselectWeb(){
		browser.unSelectCheckBox(".id", "Web_checked");
	}
	
	public String getLotteryScheduleID(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Lottery Schedule\\W*Lottery Schedule ID.*",false));
		String schedule = objs[0].text().split("ID")[1].trim();
		return schedule;
	}

	public String getAppStartDate(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"applicationStartDate_date_ForDisplay", false));
	}

	public String getAppStartDateHour(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"applicationStartDate_hour", false));
	}

	public String getAppStartDateMin(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"applicationStartDate_minute", false));
	}

	public String getAppStartDateAmPm(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"applicationStartDate_ampm", false));
	}

	public String getAppEndDate(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"applicationEndDate_date_ForDisplay", false));
	}

	public String getAppEndDateHour(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"applicationEndDate_hour", false));
	}

	public String getAppEndDateMin(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"applicationEndDate_minute", false));
	}

	public String getAppEndDateAmPm(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"applicationEndDate_ampm", false));
	}

	public boolean isCallCenterSelected(){
		return browser.isCheckBoxSelected(".id", "CallCenter_checked");
	}
	
	public boolean isFieldSelected(){
		return browser.isCheckBoxSelected(".id", "FieldMgr_checked");
	}
	
	public boolean isWebSelected(){
		return browser.isCheckBoxSelected(".id", "Web_checked");
	}

	private RegularExpression getPrefixForAppDate(String salsChannel, String startOrEnd){
		RegularExpression rex = new RegularExpression("", false);
		if("Call Center".equalsIgnoreCase(salsChannel)){
			rex = new RegularExpression("LotteryApplicationPeriodView."+startOrEnd+"Date_3_", false);
		} else if("Field".equalsIgnoreCase(salsChannel)){
			rex = new RegularExpression("LotteryApplicationPeriodView."+startOrEnd+"Date_4_", false);
		} else if("Web".equalsIgnoreCase(salsChannel)){
			rex = new RegularExpression("LotteryApplicationPeriodView."+startOrEnd+"Date_2_", false);
		} else {
			throw new InvalidDataException("No such sales channel.");
		}
		return rex;
	}

	public String getSalesChannelStartDate(String salsChannel) {
		RegularExpression rex = this.getPrefixForAppDate(salsChannel, "start");
		return browser.getTextFieldValue(".id", rex +"date_ForDisplay");
	}

	public String getSalesChannelStartDateHour(String salsChannel) {
		RegularExpression rex = this.getPrefixForAppDate(salsChannel, "start");
		return browser.getDropdownListValue(".id", rex +"hour");
	}

	public String getSalesChannelStartDateMinute(String salsChannel) {
		RegularExpression rex = this.getPrefixForAppDate(salsChannel, "start");
		return browser.getDropdownListValue(".id", rex +"minute");
	}

	public String getSalesChannelStartDateAm(String salsChannel) {
		RegularExpression rex = this.getPrefixForAppDate(salsChannel, "start");
		return browser.getDropdownListValue(".id", rex +"ampm");
	}

	public String getSalesChannelEndDate(String salsChannel) {
		RegularExpression rex = this.getPrefixForAppDate(salsChannel, "end");
		return browser.getTextFieldValue(".id", rex +"date_ForDisplay");
	}

	public String getSalesChannelEndDateHour(String salsChannel) {
		RegularExpression rex = this.getPrefixForAppDate(salsChannel, "end");
		return browser.getDropdownListValue(".id", rex +"hour");
	}

	public String getSalesChannelEndDateMinute(String salsChannel) {
		RegularExpression rex = this.getPrefixForAppDate(salsChannel, "end");
		return browser.getDropdownListValue(".id", rex +"minute");
	}

	public String getSalesChannelEndDateAm(String salsChannel) {
		RegularExpression rex = this.getPrefixForAppDate(salsChannel, "end");
		return browser.getDropdownListValue(".id", rex +"ampm");
	}

	public String getExcutePeriod() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"executionDateTimeStr_date_ForDisplay", false));
	}

	public String getExcutePeriodHour() {
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"executionDateTimeStr_hour", false));
	}

	public String getExcutePeriodMin() {
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"executionDateTimeStr_minute", false));
	}
	
	public String getExcutePeriodAmpm() {
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"executionDateTimeStr_ampm", false));
	}

	public String getFreezeDuration() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"freezeDurationInDaysStr", false));
	}

	public String getReleaseDate() {
		return browser.getTextFieldValue(".id", new RegularExpression("releaseDateInput", false));
	}
	
	public String getCustomerAcceptanceFromDate(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"acceptanceStartDateTime_date_ForDisplay", false));
	}
	
	public String getCustomerAcceptanceToDate(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"acceptanceEndDateTime_date_ForDisplay", false));
	}

	public void selectAddToWaitingList(boolean isSelect){
		if(isSelect){
			browser.selectCheckBox(".id", new RegularExpression(prefix+"addToWaitingList", false));
		} else {
			browser.unSelectCheckBox(".id", new RegularExpression(prefix+"addToWaitingList", false));
		}
	}
	
	public boolean isSelectAddToWaitingList(){
		return browser.isCheckBoxSelected(".id", new RegularExpression(prefix+"addToWaitingList", false));
	}
	
	public void selectWaitingList(String waitingList){
		if(!StringUtil.isEmpty(waitingList)){
			browser.selectDropdownList(".id", new RegularExpression(prefix+"waitingListID", false), waitingList);
		}
	}
	
	public String getWaitingList(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"waitingListID", false));
	}
	
	public String getInvStartDate() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"invStartDate_ForDisplay", false));
	}

	public String getInvEndDate() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"invEndDate_ForDisplay", false));
	}
	
	// Application Level Messaging -- start
	public void setAppHeaderMsg(String appHeaderMsg){
		if(null != appHeaderMsg){
			browser.setTextArea(".id", new RegularExpression(prefix+"applicationHeaderMessage", false), appHeaderMsg);
		}
	}

	public String getAppHeaderMsg(){
		return browser.getTextAreaValue(".id", new RegularExpression(prefix+"applicationHeaderMessage", false));
	}
	
	public void setLotteryTermsConditions(String message){
		if(null != message){
			browser.setTextArea(".id", new RegularExpression(prefix+"applicationBodyMessage", false), message);
		}
	}

	public String getLotteryTermsConditions(){
		return browser.getTextAreaValue(".id", new RegularExpression(prefix+"applicationBodyMessage", false));
	}
	
	public void setPrefsMsg(String prefsMsg){
		if(null != prefsMsg){
			browser.setTextArea(".id", new RegularExpression(prefix+"lotteryPrefsMessage", false), prefsMsg);
		}
	}

	public String getPrefsMsg(){
		return browser.getTextAreaValue(".id", new RegularExpression(prefix+"lotteryPrefsMessage", false));
	}
	
	public void setConfirmMsg(String message){
		if(null != message){
			browser.setTextArea(".id", new RegularExpression(prefix+"confirmationMessage", false), message);
		}
	}

	public String getConfirmMsg(){
		return browser.getTextAreaValue(".id", new RegularExpression(prefix+"confirmationMessage", false));
	}
	// Application Level Messaging -- end
	
	// Lottery Results Messaging -- start
	public void setEnteredStatus(String status){
		if(null != status){
			browser.setTextArea(".id", new RegularExpression(prefix+"lotteryEnteredMessage", false), status);
		}
	}

	public String getEnteredStatus(){
		return browser.getTextAreaValue(".id", new RegularExpression(prefix+"lotteryEnteredMessage", false));
	}

	public void setAwardedUnconfirmedState(String state){
		if(null != state){
			browser.setTextArea(".id", new RegularExpression(prefix+"awardedUnconfirmedMessage", false), state);
		}
	}

	public String getAwardedUnconfirmedState(){
		return browser.getTextAreaValue(".id", new RegularExpression(prefix+"awardedUnconfirmedMessage", false));
	}

	public void setSuccessfulState(String state){
		if(null != state){
			browser.setTextArea(".id", new RegularExpression(prefix+"lotterySuccessfulMessage", false), state);
		}
	}

	public String getSuccessfulState(){
		return browser.getTextAreaValue(".id", new RegularExpression(prefix+"lotterySuccessfulMessage", false));
	}

	public void setUnsuccessfulState(String state){
		if(null != state){
			browser.setTextArea(".id", new RegularExpression(prefix+"lotteryUnsuccessfulMessage", false), state);
		}
	}

	public String getUnsuccessfulState(){
		return browser.getTextAreaValue(".id", new RegularExpression(prefix+"lotteryUnsuccessfulMessage", false));
	}
	// Lottery Results Messaging -- end
	
	public void setApplicableSeason(String season){
		if(null != season){
			if(season.equals(StringUtil.EMPTY)){
				season = "Please Select";
			}
			browser.selectDropdownList(".id", new RegularExpression(prefix+"seasonScheduleID", false), season);
		}
	}
	
	public List<String> getApplicableSeasonElement(){
		return browser.getDropdownElements(".id", new RegularExpression(prefix+"seasonScheduleID", false));
	}
	
	private boolean checkApplicableSeasonExist(){
		return browser.checkHtmlObjectExists(".class","Html.SELECT",".id", new RegularExpression(prefix+"seasonScheduleID", false));
	}
	
	public boolean isApplicableSeasonEditable(){
		return browser.checkHtmlObjectEnabled(".class","Html.SELECT",".id", new RegularExpression(prefix+"seasonScheduleID", false));
	}
	
	public String getApplicableSeason(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"seasonScheduleID", false));
	}
	
	public void verifyScheduleDetailInfo(LotterySchedule expectSchedule){
		boolean result = true;
		logger.info("Verify lottery schedule detail info.");
		
		// Application Period
		result &= MiscFunctions.compareResult("App start date", expectSchedule.appStartDate, this.getAppStartDate());
		result &= MiscFunctions.compareResult("Hour of App start date", expectSchedule.appStartDateHour, this.getAppStartDateHour());
		result &= MiscFunctions.compareResult("Minute of App start date", expectSchedule.appStartDateMinute, this.getAppStartDateMin());
		result &= MiscFunctions.compareResult("AmPm of App start date", (expectSchedule.appStartDateAM?"AM":"PM"), this.getAppStartDateAmPm());

		result &= MiscFunctions.compareResult("App end date", expectSchedule.appEndDate, this.getAppEndDate());
		result &= MiscFunctions.compareResult("Hour of App end date", expectSchedule.appEndDateHour, this.getAppEndDateHour());
		result &= MiscFunctions.compareResult("Minute of App end date", expectSchedule.appEndDateMinute, this.getAppEndDateMin());
		result &= MiscFunctions.compareResult("AmPm of App end date", (expectSchedule.appEndDateAM?"AM":"PM"), this.getAppEndDateAmPm());
		
		// sales channel
		result &= MiscFunctions.compareResult("Is Call Center Selected", expectSchedule.callCenter, this.isCallCenterSelected());
		result &= MiscFunctions.compareResult("Is Field Selected", expectSchedule.field, this.isFieldSelected());
		result &= MiscFunctions.compareResult("Is Web Selected", expectSchedule.web, this.isWebSelected());
		
		if(expectSchedule.callCenter){
			result &= MiscFunctions.compareResult("App start date of Call Center", expectSchedule.callStartDate, this.getSalesChannelStartDate(OrmsConstants.SALESCHAN_NAME_CALLCENTER));
			result &= MiscFunctions.compareResult("Hour of App start date of Call Center", expectSchedule.callStartDateHour, this.getSalesChannelStartDateHour(OrmsConstants.SALESCHAN_NAME_CALLCENTER));
			result &= MiscFunctions.compareResult("Minute of App start date of Call Center", expectSchedule.callStartDateMin, this.getSalesChannelStartDateMinute(OrmsConstants.SALESCHAN_NAME_CALLCENTER));
			result &= MiscFunctions.compareResult("AmPm of App start date of Call Center", (expectSchedule.callStartDateAm?"AM":"PM"), this.getSalesChannelStartDateAm(OrmsConstants.SALESCHAN_NAME_CALLCENTER));

			result &= MiscFunctions.compareResult("App end date of Call Center", expectSchedule.callEndDate, this.getSalesChannelEndDate(OrmsConstants.SALESCHAN_NAME_CALLCENTER));
			result &= MiscFunctions.compareResult("Hour of App end date of Call Center", expectSchedule.callEndDateHour, this.getSalesChannelEndDateHour(OrmsConstants.SALESCHAN_NAME_CALLCENTER));
			result &= MiscFunctions.compareResult("Minute of App end date of Call Center", expectSchedule.callEndDateMin, this.getSalesChannelEndDateMinute(OrmsConstants.SALESCHAN_NAME_CALLCENTER));
			result &= MiscFunctions.compareResult("AmPm of App end date of Call Center", (expectSchedule.callEndDateAm?"AM":"PM"), this.getSalesChannelEndDateAm(OrmsConstants.SALESCHAN_NAME_CALLCENTER));
		} else {
			// should not select call center
			result &= MiscFunctions.compareResult("Existence of App start date of Call Center", false, this.isCallCenterSelected());
		}

		if(expectSchedule.field){
			result &= MiscFunctions.compareResult("App start date of Field", expectSchedule.fieldStartDate, this.getSalesChannelStartDate(OrmsConstants.SALESCHAN_NAME_FIELD));
			result &= MiscFunctions.compareResult("Hour of App start date of Field", expectSchedule.fieldStartDateHour, this.getSalesChannelStartDateHour(OrmsConstants.SALESCHAN_NAME_FIELD));
			result &= MiscFunctions.compareResult("Minute of App start date of Field", expectSchedule.fieldStartDateMin, this.getSalesChannelStartDateMinute(OrmsConstants.SALESCHAN_NAME_FIELD));
			result &= MiscFunctions.compareResult("AmPm of App start date of Field", (expectSchedule.fieldStartDateAm?"AM":"PM"), this.getSalesChannelStartDateAm(OrmsConstants.SALESCHAN_NAME_FIELD));

			result &= MiscFunctions.compareResult("App end date of Field", expectSchedule.fieldEndDate, this.getSalesChannelEndDate(OrmsConstants.SALESCHAN_NAME_FIELD));
			result &= MiscFunctions.compareResult("Hour of App end date of Field", expectSchedule.fieldEndDateHour, this.getSalesChannelEndDateHour(OrmsConstants.SALESCHAN_NAME_FIELD));
			result &= MiscFunctions.compareResult("Minute of App end date of Field", expectSchedule.fieldEndDateMin, this.getSalesChannelEndDateMinute(OrmsConstants.SALESCHAN_NAME_FIELD));
			result &= MiscFunctions.compareResult("AmPm of App end date of Field", (expectSchedule.fieldEndDateAm?"AM":"PM"), this.getSalesChannelEndDateAm(OrmsConstants.SALESCHAN_NAME_FIELD));
		} else {
			// should not select field
			result &= MiscFunctions.compareResult("Existence of App start date of Field", false, this.isFieldSelected());
		}

		if(expectSchedule.web){
			result &= MiscFunctions.compareResult("App start date of Web", expectSchedule.webStartDate, this.getSalesChannelStartDate(OrmsConstants.SALESCHAN_NAME_WEB));
			result &= MiscFunctions.compareResult("Hour of App start date of Web", expectSchedule.webStartDateHour, this.getSalesChannelStartDateHour(OrmsConstants.SALESCHAN_NAME_WEB));
			result &= MiscFunctions.compareResult("Minute of App start date of Web", expectSchedule.webStartDateMin, this.getSalesChannelStartDateMinute(OrmsConstants.SALESCHAN_NAME_WEB));
			result &= MiscFunctions.compareResult("AmPm of App start date of Web", (expectSchedule.webStartDateAm?"AM":"PM"), this.getSalesChannelStartDateAm(OrmsConstants.SALESCHAN_NAME_WEB));

			result &= MiscFunctions.compareResult("App end date of Web", expectSchedule.webEndDate, this.getSalesChannelEndDate(OrmsConstants.SALESCHAN_NAME_WEB));
			result &= MiscFunctions.compareResult("Hour of App end date of Web", expectSchedule.webEndDateHour, this.getSalesChannelEndDateHour(OrmsConstants.SALESCHAN_NAME_WEB));
			result &= MiscFunctions.compareResult("Minute of App end date of Web", expectSchedule.webEndDateMin, this.getSalesChannelEndDateMinute(OrmsConstants.SALESCHAN_NAME_WEB));
			result &= MiscFunctions.compareResult("AmPm of App end date of Web", (expectSchedule.webEndDateAm?"AM":"PM"), this.getSalesChannelEndDateAm(OrmsConstants.SALESCHAN_NAME_WEB));
		} else {
			// should not select web
			result &= MiscFunctions.compareResult("Existence of App end date of Web", false, this.isWebSelected());
		}
		
		// Execution Period
		result &= MiscFunctions.compareResult("Execution Period date", expectSchedule.executeDate, this.getExcutePeriod());
		result &= MiscFunctions.compareResult("Hour of Execution Period date", expectSchedule.executeHour, this.getExcutePeriodHour());
		result &= MiscFunctions.compareResult("Minute of Execution Period date", expectSchedule.executeMin, this.getExcutePeriodMin());
		result &= MiscFunctions.compareResult("AmPm of Execution Period date", (expectSchedule.executeAM?"AM":"PM"), this.getExcutePeriodAmpm());

		result &= MiscFunctions.compareResult("Freeze Duration", expectSchedule.freezeDuration, this.getFreezeDuration());
		result &= MiscFunctions.compareResult("Is Add Unsuccessful Applicants to a Waiting List", expectSchedule.isAddToWaitList, this.isSelectAddToWaitingList());
		if(expectSchedule.isAddToWaitList){
			if(StringUtil.EMPTY.equals(expectSchedule.waitList)){
				expectSchedule.waitList = "Please Select";// default value
			}
			result &= MiscFunctions.compareResult("Waiting List", expectSchedule.waitList, this.getWaitingList());
		}

		// Customer Acceptance Period
		if(null != expectSchedule.customerAcceptFromDate){
			result &= MiscFunctions.compareResult("Customer Acceptance Start Date", expectSchedule.customerAcceptFromDate, this.getCustomerAcceptanceFromDate());
			result &= MiscFunctions.compareResult("Customer Acceptance End Date", expectSchedule.customerAcceptToDate, this.getCustomerAcceptanceToDate());
		}
		
		// Applicable Season
		if(StringUtil.EMPTY.equals(expectSchedule.applicableSeason)){
			expectSchedule.applicableSeason = "Please Select";// default value
		}
		result &= MiscFunctions.compareResult("Applicable Season", expectSchedule.applicableSeason, this.getApplicableSeason());
		
		// Application Level Messaging
		result &= MiscFunctions.compareResult("App Header Msg", expectSchedule.appHeaderMsg, this.getAppHeaderMsg());
		result &= MiscFunctions.compareResult("Lottery Terms and Conditions", expectSchedule.appBodyMsg, this.getLotteryTermsConditions());
		result &= MiscFunctions.compareResult("Preferences Section Message", expectSchedule.preferencesSectionMessage, this.getPrefsMsg());
		result &= MiscFunctions.compareResult("Confirmation Page Message", expectSchedule.confirmationPageMessage, this.getConfirmMsg());

		// Lottery Results Messaging
		result &= MiscFunctions.compareResult("Entered Status", expectSchedule.enteredStatus, this.getEnteredStatus());
		result &= MiscFunctions.compareResult("Awarded/Unconfirmed State", expectSchedule.awardedUnconfirmedState, this.getAwardedUnconfirmedState());
		result &= MiscFunctions.compareResult("Successful State", expectSchedule.successfulState, this.getSuccessfulState());
		result &= MiscFunctions.compareResult("Unsuccessful State", expectSchedule.unsuccessfulState, this.getUnsuccessfulState());
		
		if(!result){
			throw new ErrorOnPageException("--Check logs above.");
		}
	}
}
