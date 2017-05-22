package com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram.LoyaltyProgramSchedule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram.LoyaltyProgramSchedule.Exclusion;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date April 21, 2014
 */
public class InvMgrEarnScheduleDetailPage extends
		InvMgrLoyaltyProgramDetailsCommonPage {
	private static InvMgrEarnScheduleDetailPage _instance = null;

	private InvMgrEarnScheduleDetailPage() {
	}

	public static InvMgrEarnScheduleDetailPage getInstance() {
		if (_instance == null)
			_instance = new InvMgrEarnScheduleDetailPage();
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(earnScheduleDetailTable());
	}

	private Property[] earnScheduleDetailTable() {
		return Property.toPropertyArray(".class", "Html.TABLE", ".text",
				new RegularExpression("Earn Schedule(\\s)?Schedule ID.*", false));
	}

	private Property[] productCategory() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"LoyaltyFeatureBaseScheduleView-\\d+\\.prdGrpCat", false));
	}

	private Property[] earnGroup() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"LoyaltyFeatureBaseScheduleView-\\d+\\.earnGroupID", false));
	}

	private Property[] feeType() {
		return Property
				.toPropertyArray(
						".id",
						new RegularExpression(
								"LoyaltyFeatureFeeScheduleApplicableFeeTypeView-\\d+\\.feeType",
								false));
	}

	private Property[] add() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add");
	}
	
	private Property[] apply() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Apply");
	}
	
	private Property[] ok() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK");
	}
	
	private Property[] productGroup() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.groupID", false));
	}
	
	private Property[] areaLoop() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.loopID", false));
	}
	
	private Property[] product() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"LoyaltyFeatureBaseScheduleView-\\d+\\.prdID", false));
	}
	
	private Property[] salesChannel() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.condition.salesChannel", false));
	}
	
	private Property[] customerType() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.condition.custTypeID", false));
	}
	
	private Property[] customerPass() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.condition.passTypeID", false));
	}
	
	private Property[] season() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.condition.seasonID", false));
	}
	
	private Property[] inOutState() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.condition.outOfState", false));
	}
	
	private Property[] effectiveDate() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"LoyaltyFeatureBaseScheduleView-\\d+\\.effectDate_ForDisplay", false));
	}
	
	private Property[] inventoryStart() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"LoyaltyFeatureBaseScheduleView-\\d+\\.startDate_ForDisplay", false));
	}
	
	private Property[] inventoryEnd() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"LoyaltyFeatureBaseScheduleView-\\d+\\.endDate_ForDisplay", false));
	}
	
	private Property[] exclusionTable() {
		return Property.toPropertyArray(".id", "exclusionsSectionPanelBar");
	}
	
	private Property[] exclusionFromDate() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureScheduleExclusionView-\\d+\\.fromDate_ForDisplay", false));
	}
	
	private Property[] exclusionToDate() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureScheduleExclusionView-\\d+\\.toDate_ForDisplay", false));
	}
	
	private Property[] exclusionProductGroup() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureScheduleExclusionView-\\d+\\.prdGrpId", false));
	}
	
	private Property[] exclusionProduct() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureScheduleExclusionView-\\d+\\.prdId", false));
	}
	
	private Property[] remove() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Remove");
	}
	
	private Property[] perValue() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"LoyaltyFeatureBaseScheduleView-\\d+\\.methodConfig\\.perValue", false));
	}
	
	private Property[] points() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.methodConfig\\.points", false));
	}
	
	private Property[] anyDayPoint() {
		return Property.toPropertyArray(".id", new RegularExpression(
				"LoyaltyFeatureBaseScheduleView-\\d+\\.methodConfig\\.anyDayPoint", false));
	}
	
	private Property[] mondayPoint() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.methodConfig\\.mondayPoint", false));
	}
	
	private Property[] tuesdayPoint() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.methodConfig\\.tuesdayPoint", false));
	}
	
	private Property[] wednesdayPoint() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.methodConfig\\.wednesdayPoint", false));
	}
	
	private Property[] thursdayPoint() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.methodConfig\\.thursdayPoint", false));
	}
	
	private Property[] fridayPoint() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.methodConfig\\.fridayPoint", false));
	}
	
	private Property[] saturdayPoint() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.methodConfig\\.saturdayPoint", false));
	}
	
	private Property[] sundayPoint() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureBaseScheduleView-\\d+\\.methodConfig\\.sundayPoint", false));
	}
	
	public void selectProductCategory(String category) {
		browser.selectDropdownList(productCategory(), category);
	}

	public void selectEarnGroup(String earn) {
		browser.selectDropdownList(earnGroup(), earn);
	}

	public void selectFeeType(List<String> types) {
		IHtmlObject[] objs = null;
		IHtmlTable grid = null;

		for (int i = 0; i < types.size(); i++) {
			objs = browser.getTableTestObject(".id",
					new RegularExpression(("dyable_\\d+"), false), ".text",
					new RegularExpression(("Fee type.*"), false));
			grid = (IHtmlTable) objs[0];
			
			if (i > 0) {
				browser.clickGuiObject(add(), false, 0, grid);
				ajax.waitLoading();
			}
			browser.selectDropdownList(feeType(), types.get(i), false, i, null);
		}
		Browser.unregister(objs);
	}
	
	private int getRemoveCountInExclusionSection() {
		IHtmlObject exclusionTableObjs[] = browser.getTableTestObject(exclusionTable());
		if(exclusionTableObjs.length < 1) throw new ItemNotFoundException("Cannot find Exclusion table object.");
		
		IHtmlObject objs[] = browser.getHtmlObject(remove(), exclusionTableObjs[0]);
		int count = (objs != null) ? objs.length : 0;
		
		Browser.unregister(objs);
		return count;
	}
	
	public void setExclusions(List<Exclusion> exclusions) {
		IHtmlObject[] objs = null;
		IHtmlTable table = null;
		
		//get existing record count
		int existingCount = this.getRemoveCountInExclusionSection();
		int absCount = Math.abs(exclusions.size() - existingCount);
		
		for(int i = 0; i < absCount; i ++) {
			objs = this.getExclusionTableObject();
			table = (IHtmlTable) objs[0];
			
			if(exclusions.size() > existingCount) {
				browser.clickGuiObject(add(), false, 0, table);
			} else if(exclusions.size() < existingCount) {
				browser.clickGuiObject(remove(), false, 0, table);
			}
			ajax.waitLoading();
		}
		
		
		for (int i = 0; i < exclusions.size(); i++) {
			if(!StringUtil.isEmpty(exclusions.get(i).getFromDate())) setExclusionFromDate(exclusions.get(i).getFromDate(), i);
			if(!StringUtil.isEmpty(exclusions.get(i).getToDate())) setExclusionToDate(exclusions.get(i).getToDate(), i);
			if(!StringUtil.isEmpty(exclusions.get(i).getProductGroup())) { 
				selectExclusionProductGroup(exclusions.get(i).getProductGroup(), i);
				ajax.waitLoading();
			}
			if(!StringUtil.isEmpty(exclusions.get(i).getProduct())) {
				selectExclusionProduct(exclusions.get(i).getProduct(), i);
				ajax.waitLoading();
			}
		}
		Browser.unregister(objs);
	}
	
	public void selectProductGroup(String group) {
		browser.selectDropdownList(productGroup(), group);
	}
	
	public void selectAreaLoop(String loop) {
		browser.selectDropdownList(areaLoop(), loop);
	}
	
	public void selectProudct(String proudct) {
		browser.selectDropdownList(product(), proudct);
	}
	
	public void selectSalesChannel(String channel) {
		browser.selectDropdownList(salesChannel(), channel);
	}
	
	public void selectCustomerType(String type) {
		browser.selectDropdownList(customerType(), type);
	}
	
	public void selectCustomerPass(String pass) {
		browser.selectDropdownList(customerPass(), pass);
	}
	
	public void selectSeason(String season) {
		browser.selectDropdownList(season(), season);
	}
	
	public void selectInOutState(String state) {
		browser.selectDropdownList(inOutState(), state);
	}
	
	public void setEffectiveDate(String date) {
		browser.setCalendarField(effectiveDate(), date);
	}
	
	public void setInventoryStartDate(String date) {
		browser.setCalendarField(inventoryStart(), date);
	}
	
	public void setInventoryEndDate(String date) {
		browser.setCalendarField(inventoryEnd(), date);
	}
	
	private IHtmlObject[] getExclusionTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(exclusionTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Exclusion table object.");
		
		return objs;
	}
	
	public void setExclusionFromDate(String fromDate, int i) {
		browser.setCalendarField(exclusionFromDate(), fromDate, false, i, null);
	}
	
	public void setExclusionToDate(String toDate, int i) {
		browser.setCalendarField(exclusionToDate(), toDate, false, i, null);
	}
	
	public void selectExclusionProductGroup(String group, int i) {
		browser.selectDropdownList(exclusionProductGroup(), group, false, i, null);
	}
	
	public void selectExclusionProduct(String product, int i) {
		browser.selectDropdownList(exclusionProduct(), product, false, i, null);
	}
	
	public void setPerValue(String value) {
		browser.setTextField(perValue(), value);
	}
	
	public void setPoints(String point) {
		browser.setTextField(points(), point);
	}
	
	public void setAnyDayPoint(String point) {
		browser.setTextField(anyDayPoint(), point);
	}
	
	public void setMondayPoint(String mPoint) {
		browser.setTextField(mondayPoint(), mPoint);
	}
	
	public void setTuesdayPoint(String tPoint) {
		browser.setTextField(tuesdayPoint(), tPoint);
	}
	
	public void setWednesdayPoint(String wPoint) {
		browser.setTextField(wednesdayPoint(), wPoint);
	}
	
	public void setThursdayPoint(String thPoint) {
		browser.setTextField(thursdayPoint(), thPoint);
	}
	
	public void setFridayPoint(String fPoint) {
		browser.setTextField(fridayPoint(), fPoint);
	}
	
	public void setSaturdayPoint(String saPoint) {
		browser.setTextField(saturdayPoint(), saPoint);
	}
	
	public void setSundayPoint(String suPoint) {
		browser.setTextField(sundayPoint(), suPoint);
	}
	
	public void  clickApply(){
		browser.clickGuiObject(apply());
	}
	
	public void  clickOk(){
		browser.clickGuiObject(ok());
	}
	
	public void setupEarnSchedule(LoyaltyProgramSchedule earnSchedule){
		if(!StringUtil.isEmpty(earnSchedule.getEarnGroup())){
			selectEarnGroup(earnSchedule.getEarnGroup());
		}

		selectFeeType(earnSchedule.getFeeTypes());
		if(!StringUtil.isEmpty(earnSchedule.getProductGroup())) {
			selectProductGroup(earnSchedule.getProductGroup());
			ajax.waitLoading();
		}
		if(!StringUtil.isEmpty(earnSchedule.getArea())) {
			selectAreaLoop(earnSchedule.getArea());
			ajax.waitLoading();
		}
		selectProudct(earnSchedule.getProduct());
		ajax.waitLoading();
		
		if(!StringUtil.isEmpty(earnSchedule.getSaleChannel())) selectSalesChannel(earnSchedule.getSaleChannel());
		if(!StringUtil.isEmpty(earnSchedule.getCustomerType())) selectCustomerType(earnSchedule.getCustomerType());
		if(!StringUtil.isEmpty(earnSchedule.getCustomerPass())) selectCustomerPass(earnSchedule.getCustomerPass());
		if(!StringUtil.isEmpty(earnSchedule.getSeason())) selectSeason(earnSchedule.getSeason());
		if(!StringUtil.isEmpty(earnSchedule.getInoutState())) selectInOutState(earnSchedule.getInoutState());
		
		setEffectiveDate(earnSchedule.getEffectiveDate());
		if(!StringUtil.isEmpty(earnSchedule.getInventoryStartDate())) 
			setInventoryStartDate(earnSchedule.getInventoryStartDate());
		
		if(!StringUtil.isEmpty(earnSchedule.getInventoryEndDate())) 
			setInventoryEndDate(earnSchedule.getInventoryEndDate());
		
		if(earnSchedule.getExclusions().size() > 0) {
			setExclusions(earnSchedule.getExclusions());
		}
		
		if(!StringUtil.isEmpty(earnSchedule.getPerValue())) 
			setPerValue(earnSchedule.getPerValue());
		
		//Per $ Value or Per Stay
		if(!StringUtil.isEmpty(earnSchedule.getPoints())) setPoints(earnSchedule.getPoints());
		
		//Per Unit
		if(!StringUtil.isEmpty(earnSchedule.getAnyDayPoint())) setAnyDayPoint(earnSchedule.getAnyDayPoint());
		if(!StringUtil.isEmpty(earnSchedule.getMondayPoint())) setMondayPoint(earnSchedule.getMondayPoint());
		if(!StringUtil.isEmpty(earnSchedule.getTuesdayPoint())) setTuesdayPoint(earnSchedule.getTuesdayPoint());
		if(!StringUtil.isEmpty(earnSchedule.getWednesdayPoint())) setWednesdayPoint(earnSchedule.getWednesdayPoint());
		if(!StringUtil.isEmpty(earnSchedule.getThursdayPoint())) setThursdayPoint(earnSchedule.getThursdayPoint());
		if(!StringUtil.isEmpty(earnSchedule.getFridayPoint())) setFridayPoint(earnSchedule.getFridayPoint());
		if(!StringUtil.isEmpty(earnSchedule.getSaturdayPoint())) setSaturdayPoint(earnSchedule.getSaturdayPoint());
		if(!StringUtil.isEmpty(earnSchedule.getSundayPoint())) setSundayPoint(earnSchedule.getSundayPoint());
	}
	
	public String getEarnScheduleID()  {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",".id", "scheduleIDLabel");
		String temp = objs[0].getProperty(".text").toString();
		String scheduleID = temp.split("Schedule ID")[1].trim();

		Browser.unregister(objs);
		return scheduleID;
	}
}
