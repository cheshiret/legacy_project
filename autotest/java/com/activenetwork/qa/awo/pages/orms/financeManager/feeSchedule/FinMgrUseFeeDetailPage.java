/*
 * $Id: FinMgrGroupUseFeeDetailPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule;

import com.activenetwork.qa.awo.datacollection.legacy.feeData.D_GroupUseFee;
import com.activenetwork.qa.awo.datacollection.legacy.feeData.IncrementsOrRangeFeeData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * 
 * @author CGuo
 */
public class FinMgrUseFeeDetailPage extends FinanceManagerPage {

	/**
	 * Script Name   : FinMgrGroupUseFeeDetailPage
	 * Generated     : Jul 16, 2007 10:47:18 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2007/07/16
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrUseFeeDetailPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrUseFeeDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrUseFeeDetailPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrUseFeeDetailPage();
		}

		return _instance;
	}

	public boolean exists() {
		//		RegularExpression reg=new RegularExpression("\"FeeAdminFeeDetails.do\",%20"RefreshFeeDetails",%20"FeeWorker"",false);
		//2013.08.08 Quentin update this page mark
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Fee Schedule Details") && browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Apply");
	}

	public void selectAccountCode(String item) {
		if (item != null && item.length() > 0)
			browser.selectDropdownList(".id", "account_code", item);
	}
	
	public void selectAccountCode(int index) {
			browser.selectDropdownList(".id", "account_code", index);
	}
	
	public void selectFirstAccountCode(String item) {
		if (item != null && item.length() > 0)
			browser.selectDropdownList(".id", "account_code", 1);
	}
	

	public void selectLoop(String item) {
		if (item != null && item.length() > 0) {
			browser.selectDropdownList(".id", "assignment_loop", item);
			this.waitLoading();
		}
	}

	public void selectProductGroup(String item) {
		if (item != null && item.length() > 0) {
			browser.selectDropdownList(".id", "assignment_prodgroup", item);
			this.waitLoading();
		}
	}

	public void selectProduct(String item) {
		if (item != null && item.length() > 0) {
			browser.selectDropdownList(".id", "assignment_product", item);
			this.waitLoading();
		}
	}

	public void selectChannel(String item) {
		if (item != null && item.length() > 0)
			browser.selectDropdownList(".id", "conditions_channel", item);
	}

	public void selectCustomerType(String item) {
		if (item != null && item.length() > 0)
			browser.selectDropdownList(".id", "conditions_customer_type", item);
	}

	public void selectInOutState(String item) {
		if (item != null && item.length() > 0)
			browser.selectDropdownList(".id", "conditions_in_out_state", item);
	}

	public void selectSeason(String item) {
		if (item != null && item.length() > 0)
			browser.selectDropdownList(".id", "conditions_season", item);
	}

	public void selectFeeType(String item) {
		if (item != null && item.length() > 0)
			browser.selectDropdownList(".id", "fee_type", item);
	}

	public void selectProductCategory(String item) {
		if (item != null && item.length() > 0)
			browser.selectDropdownList(".id", "prd_grp_cat_id", item);
	}

	public void selectUnitOfStay(String item) {
		if (item != null && item.length() > 0)
			browser.selectDropdownList(".id", "unit_of_stay_type", item);
	}

	public void selectFullStayRequiredCheckBox() {
		browser.selectCheckBox(".id", "multi_unit_rate");
	}

	public void deselectFullStayRequiredCheckBox() {
		browser.unSelectCheckBox(".id", "multi_unit_rate");
	}

	/**
	 * Select attirbute type
	 * @param attrType
	 */
	public void selectAttrType(String attrType) {
	  	browser.selectDropdownList(".id", "attribute_type", attrType);
	  	waitLoading();
	}
	
	public void selectRateType(String type) {
		if (type == null || type.length() < 1)
			return;

		String value = "";
		if (type.equalsIgnoreCase("Family")) {
			value = "1";
		} else if (type.equalsIgnoreCase("Group"))
			value = "2";
		else
			throw new ItemNotFoundException("Unknown rate type \"" + type
					+ "\"");
		browser.selectRadioButton(".id", "rate_type_id", ".value", value);
		waitLoading();
	}

	public void setEffectiveDate(String text) {
		if (text != null && text.length() > 0)
			browser.setTextField(".id", "date_effective_ForDisplay", text);
	}

	public void setEndDate(String text) {
		if (text != null && text.length() > 0)
			browser.setTextField(".id", "date_end_ForDisplay", text);
	}

	public void setStartDate(String text) {
		if (text != null && text.length() > 0)
			browser.setTextField(".id", "date_start_ForDisplay", text);
	}

	//Family fee data start
	/**
	 * For family rate type
	 */
	public void setCustomerDurationQuantity(String text) {
		if (text != null && text.length() > 0)
			browser.setTextField(".id", "custom_duration_quantity", text);
	}

	/**
	 * For family rate type
	 * @param text
	 */
	public void setCustomerDurationRate(String text) {
		if (text != null && text.length() > 0)
			browser.setTextField(".id", "custom_duration_rate", text);
	}

	/**
	 * For family rate type
	 * @param fee
	 * @param week
	 */
	public void setFeeByDayOfWeek(String fee, String week) {
		if (fee != null && fee.length() > 0) {
			String idvalue = "day_" + week.substring(0, 3).toLowerCase();

			browser.setTextField(".id", idvalue, fee);
		}
	}

	/**
	 * For family rate type
	 * @param fee
	 * @param duration
	 */
	public void setFeeByDuration(String fee, String duration) {
		if (fee != null && fee.length() > 0) {
			String id = "duration_" + duration.toLowerCase();
			browser.setTextField(".id", id, fee);
		}
	}

	//Family fee data end

	public void setGroupBaseRate(String fee) {
		setFeeByDuration(fee, "daily");
	}

	public IHtmlObject[] getAdditionalOccupantFeesTable() {
		return browser.getTableTestObject(".id", "additional_occupant_fees");
	}

	/**
	 * Incremental type can be either "Increments" or "Range"
	 * @param type
	 */
	public void selectIncrementalType(String type) {
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		if (type == null || type.length() < 1)
			return;

		String value;
		if (type.matches("Increments?"))
			value = "1";
		else if (type.matches("Ranges?"))
			value = "2";
		else
			throw new ItemNotFoundException("Unknown incremental type \""
					+ type + "\"");

		browser.selectRadioButton(".id", "occ_incr_type_id", ".value", value);
		browser.waitExists(confirmDialogPg,this);
	}
	
	public void selectVehicleIncrementalType(String type) {
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		if (type == null || type.length() < 1)
			return;

		String value;
		if (type.matches("Increments?"))
			value = "1";
		else if (type.matches("Ranges?"))
			value = "2";
		else
			throw new ItemNotFoundException("Unknown incremental type \""
					+ type + "\"");

		browser.selectRadioButton(".id", "veh_incr_type_id", ".value", value);
		browser.waitExists(confirmDialogPg, this);
	}

	/**
	 * Click either "Add Increment" or "Add Range" for additional occupants
	 *
	 */
	public void clickAddOccupantsIncremental() {
		Property[] p1 = new Property[2];
		p1[0] = new Property(".class","Html.TABLE");
		p1[1] = new Property(".id", "additional_occupant_fees");
		IHtmlObject[] objs = browser.getHtmlObject(p1);
		
		Property[] p2 = new Property[2];
		RegularExpression reg = new RegularExpression("Add (Increment|Range)",false);
		p2[0] = new Property(".class", "Html.A");
		p2[1] = new Property(".text", reg);

		browser.clickGuiObject(p2, false, 0, objs[0]);
	}

	/**
	 * Click either "Remove Increment" or "Remove Range"
	 *
	 */
	public void clickRemoveOccupantsIncremental() {
		Property[] p1 = new Property[2];
		p1[0] = new Property(".class","Html.TABLE");
		p1[1] = new Property(".id", "additional_occupant_fees");
		IHtmlObject[] objs = browser.getHtmlObject(p1);
		
		Property[] p2 = new Property[2];
		RegularExpression reg = new RegularExpression("Remove (Increment|Range)", false);
		p2[0] = new Property(".class", "Html.A");
		p2[1] = new Property(".text", reg);

		browser.clickGuiObject(p2, false, 0, objs[0]);
	}

	/**
	 * Click either "Remove Increment" or "Remove Range" for additonal vehicle
	 *
	 */
	public void clickRemoveVehiclesIncremental() {
		Property[] p1 = new Property[2];
		p1[0] = new Property(".class","Html.TABLE");
		p1[1] = new Property(".id", "additional_vehicle_fees");
		IHtmlObject[] objs = browser.getHtmlObject(p1);
		
		Property[] p2 = new Property[2];
		RegularExpression reg = new RegularExpression("Remove (Increment|Range)", false);
		p2[0] = new Property(".class", "Html.A");
		p2[1] = new Property(".text", reg);

		browser.clickGuiObject(p2, false, 0, objs[0]);
	}

	/**Click "Add Increment" or "Add Range" button for additonal vehicle */
	public void clickAddVehicelIncremental() {
		Property[] p1 = new Property[2];
		p1[0] = new Property(".class","Html.TABLE");
		p1[1] = new Property(".id", "additional_vehicle_fees");
		IHtmlObject[] objs = browser.getHtmlObject(p1);
		
		Property[] p2 = new Property[2];
		RegularExpression reg = new RegularExpression("Add (Increment|Range)", false);
		p2[0] = new Property(".class", "Html.A");
		p2[1] = new Property(".text", reg);

		browser.clickGuiObject(p2, false, 0, objs[0]);
	}

	/**
	 * Get number of occupants "remove incremental or Range" button
	 * @return
	 */
	public int getNumberOfOccupantsIncremental() {
		IHtmlObject[] obj=browser.getHtmlObject(".id", "additional_occupant_fees");
		
		Property[] p=new Property[2];
		RegularExpression reg = new RegularExpression("Remove (Increment|Range)", false);
		p[0]=new Property(".class","Html.A");
		p[1]=new Property(".text",reg);
		
		int number=browser.getHtmlObject(p,obj[0]).length-1;
		return number;
	}
	
	/**
	 * Get number of occupants "remove incremental or Range" button
	 * @return
	 */
	public int getNumberOfVehiclesIncremental() {
		IHtmlObject[] obj=browser.getHtmlObject(".id", "additional_vehicle_fees");
		
		Property[] p=new Property[2];
		RegularExpression reg = new RegularExpression("Remove (Increment|Range)", false);
		p[0]=new Property(".class","Html.A");
		p[1]=new Property(".text",reg);
		
		int number=browser.getHtmlObject(p,obj[0]).length-1;
		return number;
	}

	/**
	 * Set group increamental fees for additional occupants
	 * @param irs
	 */
	public void setAdditionalOccupantsIncrementalFees(IncrementsOrRangeFeeData[] irs) {
		if (irs != null && irs.length > 0) {
			int number = getNumberOfOccupantsIncremental();

			while (number < irs.length) {
				this.clickAddOccupantsIncremental();
				number++;
			}
			while (number > irs.length) {
				this.clickRemoveOccupantsIncremental();
				number--;
			}

			number = getNumberOfOccupantsIncremental();
			if (number != irs.length) {
				throw new ItemNotFoundException(
						"Failed to addjust the number of incrementals to "
								+ irs.length);
			}

			for (int i = 0; i < irs.length; i++) {

				RegularExpression reg1 = new RegularExpression("1_(1|2)_increment", false);
				browser.setTextField(".id", reg1,irs[i].occIncrementRange,i+1);
				
				RegularExpression reg2 = new RegularExpression("1_(1|2)_base_increment_rate", false);
				browser.setTextField(".id", reg2, irs[i].defaultRate,i+1);
				
				RegularExpression reg3 = new RegularExpression("1_(1|2)_mon_increment_rate", false);
				browser.setTextField(".id",reg3,irs[i].monRate,i+1);
				
				RegularExpression reg4 = new RegularExpression("1_(1|2)_tue_increment_rate", false);
				browser.setTextField(".id",reg4,irs[i].tuesRate,i+1);
				
				RegularExpression reg5 = new RegularExpression("1_(1|2)_wed_increment_rate", false);
				browser.setTextField(".id",reg5,irs[i].wedRate,i+1);
				
				RegularExpression reg6 = new RegularExpression("1_(1|2)_thu_increment_rate", false);
				browser.setTextField(".id",reg6,irs[i].thurRate,i+1);
				
				RegularExpression reg7 = new RegularExpression("1_(1|2)_fri_increment_rate", false);
				browser.setTextField(".id",reg7,irs[i].friRate,i+1);
				
				RegularExpression reg8 = new RegularExpression("1_(1|2)_sat_increment_rate", false);
				browser.setTextField(".id",reg8,irs[i].satRate,i+1);
				
				RegularExpression reg9 = new RegularExpression("1_(1|2)_sun_increment_rate", false);
				browser.setTextField(".id",reg9,irs[i].sunRate,i+1);
				
			}
		}
	}
	
	/**
	 * Set additional vehicles incrementtal fee 
	 * @param irs
	 */
	public void setAdditionalVehiclesIncrementalFees(
		IncrementsOrRangeFeeData[] irs) {
	if (irs != null && irs.length > 0) {
		int number = getNumberOfVehiclesIncremental();

		while (number < irs.length) {
			this.clickAddVehicelIncremental();
			number++;
		}
		while (number > irs.length) {
			this.clickRemoveVehiclesIncremental();
			number--;
		}

		number = getNumberOfVehiclesIncremental();
		if (number != irs.length) {
			throw new ItemNotFoundException(
					"Failed to addjust the number of incrementals to "
							+ irs.length);
		}

		for (int i = 0; i < irs.length; i++) {
			RegularExpression reg1 = new RegularExpression("2_(1|2)_increment", false);
			browser.setTextField(".id", reg1 ,irs[i].vehiIncrementRange,i+1);
			
			RegularExpression reg2 = new RegularExpression("2_(1|2)_base_increment_rate", false);
			browser.setTextField(".id", reg2 ,irs[i].defaultRate,i+1);
			
			RegularExpression reg3 = new RegularExpression("2_(1|2)_mon_increment_rate", false);
			browser.setTextField(".id",reg3,irs[i].monRate,i+1);
			
			RegularExpression reg4 = new RegularExpression("2_(1|2)_tue_increment_rate", false);
			browser.setTextField(".id",reg4,irs[i].tuesRate,i+1);
			
			RegularExpression reg5 = new RegularExpression("2_(1|2)_wed_increment_rate", false);
			browser.setTextField(".id",reg5,irs[i].wedRate,i+1);
			
			RegularExpression reg6 = new RegularExpression("2_(1|2)_thu_increment_rate", false);
			browser.setTextField(".id",reg6,irs[i].thurRate,i+1);
			
			RegularExpression reg7 = new RegularExpression("2_(1|2)_fri_increment_rate", false);
			browser.setTextField(".id",reg7,irs[i].friRate,i+1);
			
			RegularExpression reg8 = new RegularExpression("2_(1|2)_sat_increment_rate", false);
			browser.setTextField(".id",reg8,irs[i].satRate,i+1);
			
			RegularExpression reg9 = new RegularExpression("2_(1|2)_sun_increment_rate", false);
			browser.setTextField(".id",reg9,irs[i].sunRate,i+1);
			
		}
	}
}
	
	/**
	 * setup group fee information
	 * @param guf
	 */
	public void setupGroupUseFee(D_GroupUseFee guf) {
		this.selectLoop(guf.loop);
		this.selectProductGroup(guf.productGroup);
		this.selectProduct(guf.product);
        
		setEffectiveDate(guf.effectDate);
		this.setStartDate(guf.startDate);
		this.setEndDate(guf.endDate);
		this.selectSeason(guf.season);
		this.selectChannel(guf.salesChannel);
		this.selectInOutState(guf.inOutState);//new add
		this.selectCustomerType(guf.customerType);

		this.selectUnitOfStay(guf.unitOfStay);
		if(Boolean.valueOf(TestProperty.getProperty("forceOperation"))) {//support script
			this.selectAccountCode(1);
		} else {//normal regression cases
			if(!StringUtil.isEmpty(guf.acctCode)) {
				this.selectAccountCode(guf.acctCode);
			} else {
				this.selectAccountCode(1);
			}
		}
		if(null != guf.attrType && guf.attrType.length() > 0) {
		  	this.selectAttrType(guf.attrType);
		}
		
		this.selectRateType("Group");
		
		this.setGroupBaseRate(guf.baseRate);
		this.setFeeByDayOfWeek(guf.monRate, "Monday");
		this.setFeeByDayOfWeek(guf.tuesRate, "Tuesday");
		this.setFeeByDayOfWeek(guf.wedRate, "Wednsday");
		this.setFeeByDayOfWeek(guf.thurRate, "Thursday");
		this.setFeeByDayOfWeek(guf.friRate, "Friday");
		this.setFeeByDayOfWeek(guf.satRate, "Saturday");
		this.setFeeByDayOfWeek(guf.sunRate, "Sunday");

		this.selectIncrementalType(guf.occupantIncrementOrRange);
		this.setAdditionalOccupantsIncrementalFees(guf.ir);
		
		this.selectVehicleIncrementalType(guf.vehicleIncrementOrRange);
		this.setAdditionalVehiclesIncrementalFees(guf.ivehicle);

	}

	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public String getFeeSchID() {
		RegularExpression reg = new RegularExpression("Fee Schedule ID", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", reg);
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		String[] tokens = RegularExpression.getMatches(text, "\\d+");

		return tokens[0];
	}

}
