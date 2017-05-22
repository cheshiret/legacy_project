package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.HuntAssignmentAttr;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.verification.Verification;

/**
 * @Description: Lottery Product Hunts page
 * 
 * @author Lesley Wang
 * @Date  Jan 14, 2014
 */
public class LicMgrLotteryHuntsPage extends LicMgrLotteryProductCommonPage {
	private static LicMgrLotteryHuntsPage _instance = null;
	private LicMgrLotteryHuntsPage() {}
	
	public static LicMgrLotteryHuntsPage getInstance() {
		if(_instance == null) {
			_instance = new LicMgrLotteryHuntsPage();
		}
		
		return _instance;
	}
	
	protected Property[] huntsListTable() {
		return Property.concatPropertyArray(this.table(), ".id", "AssignmentGrid");
	}
	
	protected Property[] assignHuntLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Assign Hunt");
	}
	
	protected Property[] assignIDLink(String id) {
		return Property.concatPropertyArray(this.a(), ".text", id);
	}
	
	protected Property[] filterStatus() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue", false));
	}
	
	protected Property[] goLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Go");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(huntsListTable());
	}
	
	public boolean isAssignHuntExist() {
		return browser.checkHtmlObjectExists(assignHuntLink());
	}
	
	public void clickAssignHunt() {
		browser.clickGuiObject(assignHuntLink());
	}
	
	public void clickAssignID(String id) {
		browser.clickGuiObject(assignIDLink(id));
	}
	
	public void selectFilterStatus(String status) {
		browser.selectDropdownList(filterStatus(), status);
	}
	
	public void selectFilterStatus(int index) {
		browser.selectDropdownList(filterStatus(), index);
	}
	
	public void clickFilterGo() {
		browser.clickGuiObject(this.goLink());
	}
	
	public void filterHuntAssignments(String status) {
		logger.info("Filter hunt assignment by status = " + status);
		if (StringUtil.notEmpty(status)) {
			this.selectFilterStatus(status);
		} else {
			this.selectFilterStatus(0);
		}
		this.clickFilterGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public Data<HuntAssignmentAttr> getHuntAssignmentInfo(String id) {
		IHtmlObject[] objs = browser.getHtmlObject(this.huntsListTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(0, id);
		if (rowIndex < 0) {
			throw new ErrorOnPageException("Can't find the hunt assignment with id = " + id);
		}
		
		Data<HuntAssignmentAttr> assignment = new Data<HuntAssignmentAttr>();
		HuntAssignmentAttr.AssignID.setValue(assignment, id);
		HuntAssignmentAttr.Status.setValue(assignment, table.getCellValue(rowIndex, 1));
		String[] huntInfo = table.getCellValue(rowIndex, 2).split("-");
		HuntAssignmentAttr.HuntCode.setValue(assignment, huntInfo[0].trim());
		HuntAssignmentAttr.HuntDes.setValue(assignment, huntInfo[1].trim());
		HuntAssignmentAttr.HuntSpecies.setValue(assignment, table.getCellValue(rowIndex, 3));
		HuntAssignmentAttr.HuntSpeciesSubType.setValue(assignment, table.getCellValue(rowIndex, 4));
		HuntAssignmentAttr.HuntWeapon.setValue(assignment, table.getCellValue(rowIndex, 5));
		HuntAssignmentAttr.HuntLoc.setValue(assignment, table.getCellValue(rowIndex, 6));
		HuntAssignmentAttr.HuntDatePeriod.setValue(assignment, table.getCellValue(rowIndex, 7));
		
		Browser.unregister(objs);
		return assignment;
	}
	
	@SuppressWarnings("unchecked")
	public void verifyHuntAssignmentInfo(Data<HuntAssignmentAttr>... assignmentInfos) {
		String assignID = "";
		for (Data<HuntAssignmentAttr> assignmentInfo : assignmentInfos) {
			assignID = HuntAssignmentAttr.AssignID.getStrValue(assignmentInfo);
			Data<HuntAssignmentAttr> actualInfo = this.getHuntAssignmentInfo(assignID);
			Verification.verifyData(actualInfo, assignmentInfo);
			logger.info("Successfully verify the hunt assignment info for id=" + assignID);
		}
	}
	
	public List<String> getAllHuntCodes() {
		IHtmlObject[] objs = browser.getHtmlObject(this.huntsListTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> huntCodes = new ArrayList<String>();
		for (int i = 1;  i < table.rowCount(); i++) {
			String hunt = table.getCellValue(i, 2);
			huntCodes.add(hunt.split("-")[0].trim());
		}
		Browser.unregister(objs);
		return huntCodes;
	}
	
	public boolean isHuntAssignmentExist(String id) {
		return browser.checkHtmlObjectExists(this.assignIDLink(id));
	}
	
	public void verifyHuntAssignmentExist(String[] ids, Boolean[] exp) {
		boolean result = true;
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			result &= MiscFunctions.compareResult("Hunt Assignment with ID="+id+" Exist", exp[i], this.isHuntAssignmentExist(id));
		}
		if (!result) {
			throw new ErrorOnPageException("Hunt Assignment Exist is wrong!");
		}
		logger.info("Successfully verify hunt assignment exist!");
	}
}
