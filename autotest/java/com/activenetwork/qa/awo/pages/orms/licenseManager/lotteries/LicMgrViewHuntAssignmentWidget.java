package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.HuntAssignmentAttr;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: View Hunt Assignment Details widget in License Manager.
 * Admin(drop down list) -> Lotteries -> Lottery Details page -> Hunts sub tab -> Click assign id
 * 
 * @author Lesley Wang
 * @Date  Jan 16, 2014
 */
public class LicMgrViewHuntAssignmentWidget extends DialogWidget {
	private static LicMgrViewHuntAssignmentWidget _instance = null;
	
	protected LicMgrViewHuntAssignmentWidget() {
		super("View Hunt Assignment Details");
	}
	
	public static LicMgrViewHuntAssignmentWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrViewHuntAssignmentWidget();
		}
		
		return _instance;
	}
	
	protected Property[] assignIDField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression("HuntPrivilegeProductAssignmentView-\\d+\\.id", false));
	}
	
	protected Property[] statusList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression("HuntPrivilegeProductAssignmentView-\\d+\\.status", false));
	}
	
	protected Property[] huntField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression("HuntPrivilegeProductAssignmentView-\\d+\\.hunt.name", false));
	}

	protected Property[] creationDateTimeSpan() {
		return Property.concatPropertyArray(this.span(), ".id", new RegularExpression("HuntPrivilegeProductAssignmentView-\\d+\\.createDate:LOCAL_TIME", false));
	}
	
	protected Property[] creationUserSpan() {
		return Property.concatPropertyArray(this.span(), ".id", new RegularExpression("HuntPrivilegeProductAssignmentView-\\d+\\.createUser", false));
	}
	
	protected Property[] modifiedDateTimeSpan() {
		return Property.concatPropertyArray(this.span(), ".id", new RegularExpression("HuntPrivilegeProductAssignmentView-\\d+\\.updateDate:LOCAL_TIME", false));
	}
	
	protected Property[] modifiedUserSpan() {
		return Property.concatPropertyArray(this.span(), ".id", new RegularExpression("HuntPrivilegeProductAssignmentView-\\d+\\.updateUser", false));
	}
	
	public String getAssignID() {
		return browser.getTextFieldValue(this.assignIDField());
	}
	
	public String getStatus() {
		return browser.getDropdownListValue(this.statusList(), 0);
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(this.statusList(), status);
	}
	
	public void selectStatus(boolean isActive) {
		String status = isActive ? "Active" : "Inactive";
		this.selectStatus(status);
	}
	
	public String getHunt() {
		return browser.getTextFieldValue(this.huntField());
	}
	
	public String getHuntCode() {
		return this.getHunt().split("-")[0].trim();
	}
	
	public String getHuntDes() {
		return this.getHunt().split("-")[1].trim();
	}
	
	public String getCreationDateTime() {
		String text = browser.getObjectText(this.creationDateTimeSpan());
		text = text.replace("Creation Date/Time", "").trim();
		return text;
	}
	
	public String getCreationUser() {
		String text = browser.getObjectText(this.creationUserSpan());
		text = text.replace("Creation User", "").trim();
		return text;
	}
	
	public String getModifiedDateTime() {
		String text = browser.getObjectText(this.modifiedDateTimeSpan());
		text = text.replace("Last Modified Date/Time", "").trim();
		return text;
	}
	
	public String getModifiedUser() {
		String text = browser.getObjectText(this.modifiedUserSpan());
		text = text.replace("Last Modified User", "").trim();
		return text;
	}
	
	public void verifyHuntAssignmentDetails(Data<HuntAssignmentAttr> huntAssignment) {
		boolean result = true;
		result &= MiscFunctions.compareString("Hunt Assignment ID", HuntAssignmentAttr.AssignID.getStrValue(huntAssignment), 
				this.getAssignID());
		result &= MiscFunctions.compareString("Hunt Assignment Status", HuntAssignmentAttr.Status.getStrValue(huntAssignment), 
				this.getStatus());
		result &= MiscFunctions.compareString("Hunt code", HuntAssignmentAttr.HuntCode.getStrValue(huntAssignment), 
				this.getHuntCode());
		result &= MiscFunctions.compareString("Hunt description", HuntAssignmentAttr.HuntDes.getStrValue(huntAssignment), 
				this.getHuntDes());
		result &= MiscFunctions.compareString("Hunt Assignment Creation User", HuntAssignmentAttr.CreationUser.getStrValue(huntAssignment).replace(", ", ","), 
				this.getCreationUser().replace(", ", ","));
		result &= MiscFunctions.compareString("Hunt Assignment Modified User", HuntAssignmentAttr.ModifiedUser.getStrValue(huntAssignment).replace(", ", ","), 
				this.getModifiedUser().replace(", ", ","));
		
		String dateTime = HuntAssignmentAttr.CreationDateTime.getStrValue(huntAssignment);
		dateTime = DateFunctions.formatDate(dateTime, "EEE MMM dd yyyy");
		String actualDateTime = this.getCreationDateTime();
		result &= MiscFunctions.startWithString("Hunt Assignment Creation Date Time", actualDateTime, dateTime);
		
		dateTime = HuntAssignmentAttr.ModifiedDateTime.getStrValue(huntAssignment);
		actualDateTime = this.getModifiedDateTime();
		if (StringUtil.isEmpty(dateTime)) {
			result &= MiscFunctions.compareString("Hunt Assignment Modified Date Time", dateTime, actualDateTime);
		} else {
			dateTime = DateFunctions.formatDate(dateTime, "EEE MMM dd yyyy");
			result &= MiscFunctions.startWithString("Hunt Assignment Modified Date Time", actualDateTime, dateTime);
		}
		
		if(!result) {
			throw new ErrorOnDataException("The actual Hunt Assignment doesn't match expected.");
		}else {
			logger.info("Verify Hunt Assignment Successfully.");		
		}
	}
	
//	private boolean compareDateTime(String msg, String actual, String exp) {
////		SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy hh:mm aa zz");
////		Date actualDate = null;
////		try {
////			actualDate = formatter.parse(actualProductAssignment.creationDateTime + " EDT");
////		} catch (ParseException e) {
////			throw new ErrorOnDataException(e);
////		}
////		if((Long.parseLong(creationTimeInCase) - actualDate.getTime())/1000 > 6000) {
////			result &= false;
////			logger.error("Product-Store Assignment Creation Date/Time doesn't match.");
////		} else {
////			logger.info("Product-Store Assignment Creation Date/Time is correct!");
////		}
//		boolean result = true;
//		if (StringUtil.isEmpty(exp)) {
//			result &= StringUtil.isEmpty(actual);
//		} else if (StringUtil.isEmpty(actual)) {
//			result &= false;
//		} else {
//			if (DateFunctions.diffMinBetween(exp, actual) > 5) {
//				result &= false;
//			}
//		}
//		if (!result) {
//			logger.error(msg + " is wrong. Exp is " + exp + "; Actual is " + actual);
//		} else {
//			logger.info(msg + " is correct as " + exp);
//		}
//		return result;
//	}
}
