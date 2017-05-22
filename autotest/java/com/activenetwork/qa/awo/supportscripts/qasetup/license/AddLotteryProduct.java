package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct.SalesFlowDisplaySetting;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddLotteryProductFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 4, 2013
 */
public class AddLotteryProduct extends SetupCase {
	
	private LoginInfo login = new LoginInfo();
	private HFLotteryProduct lottery = new HFLotteryProduct();
	
	@Override
	public void executeSetup() {
		new AddLotteryProductFunction().execute(new Object[] {login, lottery});
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_lottery_prd";
	}
	
	@Override
	public void readDataFromDatabase() {
		//login info
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = datasFromDB.get("Contract");
		login.location = datasFromDB.get("Location");
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		//lottery product info
		lottery.setCode(datasFromDB.get("Code"));
		lottery.setDescription(datasFromDB.get("Description"));
		lottery.setLegalName(datasFromDB.get("Legal_Name"));
		lottery.setStatus(datasFromDB.get("Status"));
		String species = datasFromDB.get("Species");
		if(!StringUtil.isEmpty(species)) {
			lottery.setSpecies(species.split(","));
		}
		String classes = datasFromDB.get("Customer_Class");
		if(!StringUtil.isEmpty(classes)) {
			lottery.setCustomerClass(classes.split(","));
		}
		lottery.setMinChoices(datasFromDB.get("Min_Num_Choices"));
		lottery.setMaxChoices(datasFromDB.get("Max_Num_Choices"));
		lottery.setAlgorithm(datasFromDB.get("Algorithm"));
		lottery.setDisplayCategory(datasFromDB.get("Display_Category"));
		lottery.setDisplaySubCategory(datasFromDB.get("Display_Sub_Category"));
		lottery.setReportCategory(datasFromDB.get("Report_Category"));
		lottery.setPricingNote(datasFromDB.get("Pricing_Note"));
		lottery.setPointTypeForPurchase(datasFromDB.get("Point_Type_For_Purchase"));
		String temp = datasFromDB.get("Display_Point_Code_In_Sales_Fl");
		if(StringUtil.notEmpty(temp)){
			lottery.setDisplayPointCodeInSalesFlow(temp.equalsIgnoreCase("Yes"));
		}
		String tmp= datasFromDB.get("Allow_Points_And_Hunts_In_App");
		if(StringUtil.notEmpty(tmp)){
			lottery.setAllowPointsAndHuntsInOneApplication(tmp.equalsIgnoreCase("Yes"));
		}
		
		String pointsAllowed = datasFromDB.get("POINTS_ALLOWED_FOR");
		if(!StringUtil.isEmpty(pointsAllowed)) {
			lottery.setPointsAllowedFor(pointsAllowed.split(";"));
		}
		lottery.setMinPointsAllowed(datasFromDB.get("min_allowed"));
		lottery.setMaxPointsAllowed(datasFromDB.get("max_allowed"));
		lottery.setNotesForPointTypeForPurchaseInSalesFlow(datasFromDB.get("Notes_For_Point_Type_For_Purch"));
		
		String displaySettings = datasFromDB.get("Sales_Flow_Display_Settings");
		String displaySettingsDisplayOrders = datasFromDB.get("Sales_Flow_Settings_Dis_Ord");
		if(!StringUtil.isEmpty(displaySettings) && !StringUtil.isEmpty(displaySettingsDisplayOrders)) {
			String settings[] = displaySettings.split(",");
			String displayOrders[] = displaySettingsDisplayOrders.split(",");
			if(settings.length != displayOrders.length) {
				throw new ErrorOnDataException("Sales_Flow_Display_Settings length is not correct with Sales_Flow_Display_Settings_Display_Order");
			}
			
			List<SalesFlowDisplaySetting> settingsList = new ArrayList<SalesFlowDisplaySetting>();
			for(int i = 0; i < settings.length; i ++) {
				SalesFlowDisplaySetting setting = new SalesFlowDisplaySetting(settings[i], displayOrders[i]);
				settingsList.add(setting);
			}
			
			lottery.setDisplaySetting(settingsList);
		}
	}
}
