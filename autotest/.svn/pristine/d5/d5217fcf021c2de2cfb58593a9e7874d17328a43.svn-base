package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: Assign Hunt Widget in license manager.
 *  Admin(drop down list) -> Lotteries -> Lottery Details page -> Hunts sub tab -> Assign Hunt
 * 
 * @author Lesley Wang
 * @Date  Jan 16, 2014
 */
public class LicMgrAssignHuntWidget extends DialogWidget {
	private static LicMgrAssignHuntWidget _instance = null;
	
	protected LicMgrAssignHuntWidget() {
		super("Assign Hunt");
	}
	
	public static LicMgrAssignHuntWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAssignHuntWidget();
		}
		
		return _instance;
	}
	
	protected Property[] assignmentGridDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "AssignmentGrid");
	}
	
	protected Property[] assignmentGrid() {
		return Property.concatPropertyArray(this.table(), ".id", new RegularExpression("AssignmentGrid|AssignmentGrid_LIST", false)); //Sara[2014213]
	}
	
	protected Property[] assignmentCheckbox() {
		return Property.concatPropertyArray(this.input("checkbox"), ".id", new RegularExpression("HuntView-\\d+\\.assign", false));
	}
	
	protected Property[] allCheckbox() {
		return Property.concatPropertyArray(this.input("checkbox"), ".value", "all");
	}
	
	private IHtmlObject[] getAssignmentTables() {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.assignmentGridDiv(), this.assignmentGrid()));
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Assignment Grid not found!");
		}
		return objs;
	}
	
	public void selectAllCheckBox() {
		browser.selectCheckBox(allCheckbox());
	}
	
	public void selectAssignmentCheckBox(int index) {
		browser.selectCheckBox(this.assignmentCheckbox(), index);
	}
	
	public void selectAssignmentCheckbox(String huntCode) {
		IHtmlObject[] objs = this.getAssignmentTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, new RegularExpression(huntCode+" -.*", false));
		this.selectAssignmentCheckBox(row - 1);
		Browser.unregister(objs);
	}
	
	public List<String> getAllSpecies() {
		IHtmlObject[] objs = this.getAssignmentTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> species = new ArrayList<String> ();
		species = table.getColumnValues(2);
		species.remove(0);
		Browser.unregister(objs);
		return species;
	}
	
	public boolean isHuntExist(HuntInfo hunt) {
		IHtmlObject[] objs = this.getAssignmentTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, hunt.getHuntCode() + " - " + hunt.getDescription());
		boolean isExist = row > 1;
		Browser.unregister(objs);
		return isExist;
	}
	
	public void verifyHuntExist(HuntInfo hunt, boolean exp) {
		if (exp != this.isHuntExist(hunt)) {
			throw new ErrorOnPageException("Hunt with Code=" + hunt.getHuntCode() + " exist wrongly! Expect is " + exp);
		}
		logger.info("Verify hunt with code=" + hunt.getHuntCode() + " exist correctly as " + exp);
	}
	
	public HuntInfo getHuntInfo(String huntCode) {
		IHtmlObject[] objs = this.getAssignmentTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, new RegularExpression(huntCode + ".*", false));
		if (row < 0) {
			throw new ErrorOnPageException("Can't find hunt with code=" + huntCode);
		}
		HuntInfo hunt = new HuntInfo();
		hunt.setHuntCode(huntCode);
		hunt.setDescription(table.getCellValue(row, 1).split("-")[1].trim());
		hunt.setSpecie(table.getCellValue(row, 2));
		hunt.setSpecieSubType(table.getCellValue(row, 3));
		hunt.setWeapon(table.getCellValue(row, 4));
		hunt.setHuntLocationInfo(table.getCellValue(row, 5));
		hunt.setHuntDatePeriodInfo(table.getCellValue(row, 6));
		Browser.unregister(objs);
		return hunt;
	}
	
	public void verifyHuntInfo(HuntInfo... hunts) {
		for (HuntInfo hunt : hunts) {
			boolean result = true;
			HuntInfo actHunt = this.getHuntInfo(hunt.getHuntCode());
			result &= MiscFunctions.compareString("Hunt Description", hunt.getDescription(), actHunt.getDescription());
			result &= MiscFunctions.compareString("Hunt Species", hunt.getSpecie(), actHunt.getSpecie());
			result &= MiscFunctions.compareString("Hunt Species sub type", hunt.getSpecieSubType(), actHunt.getSpecieSubType());
			result &= MiscFunctions.compareString("Hunt Weapon", hunt.getWeapon(), actHunt.getWeapon());
			result &= MiscFunctions.compareString("Hunt Location", hunt.getHuntLocationInfo(), actHunt.getHuntLocationInfo());
			result &= MiscFunctions.compareString("Hunt Date Period", hunt.getHuntDatePeriodInfo(), actHunt.getHuntDatePeriodInfo());
			if (!result) {
				throw new ErrorOnPageException("Hunt info is wrong for code=" + hunt.getHuntCode());
			}
			logger.info("Successfully verify hunt info for code=" + hunt.getHuntCode());
		}
	}
	
	public int getNumOfHunts() {
		IHtmlObject[] objs = this.getAssignmentTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		int num = table.rowCount() - 1;
		Browser.unregister(objs);
		return num;
	}
}
