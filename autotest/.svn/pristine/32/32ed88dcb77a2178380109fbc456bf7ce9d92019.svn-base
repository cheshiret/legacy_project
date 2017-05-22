package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.huntchoiceselection;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify hunts attributes when apply as different applicant type
 * @Preconditions:
 * 1. The hunt "WLH2" is only for individual.
 * 2. The hunt "WLH3" is only for group.
 * 3. The hunt "WLH10" is for individual and group.
 * 4. All the hunts are assigned to the lottery "Lottery With Mult Hunts".
 * @SPEC: 
 * List Available Hunts - Hunts attributes [TC:056242]
 * List Available Hunts - Hunts listed based on applicant type [TC:056385]
 * List Available Hunts - Hunts list UI and sorting [TC:056286]
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1280
 * d_hf_add_species:id=40
 * d_hf_add_hunt_location:id=90
 * d_hf_add_date_period:id=60
 * d_hf_add_weapon:id=50
 * d_hf_add_privilege_prd:id=2630
 * d_hf_add_pricing:id=3822
 * d_hf_assi_pri_to_store:id=1850
 * d_hf_add_prvi_license_year:id=2740
 * d_hf_add_qty_control:id=1830
 * d_hf_add_hunt:id=490,500,570
 * d_hf_add_hunt_license_year:id=520,530,600
 * d_hf_assign_priv_to_hunt:id=370,380,450
 * d_hf_add_hunt_quota:id=210
 * d_hf_add_lottery_prd:id=200
 * d_hf_add_lottery_license_year:id=80
 * d_hf_assign_lottery_to_store:id=80
 * d_hf_add_lottery_quantity_control:id=80
 * d_hf_assi_hunts_to_lottery:id=90
 * @Task#: Auto-1773
 * 
 * @author Lesley Wang
 * @Date  Feb 14, 2014
 */
public class HuntsList_HuntAttributes extends HFSKWebTestCase {
	private HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
	private HuntInfo hunt_Indiv, hunt_Group, hunt_Both;
	private boolean isGroupMinShown, isGroupMaxShown, isSpeciesShown,
		isWeaponShown, isDPShown, isLocShown, isSubLocShown;
	private String indivType, groupType;
	
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		hf.gotoLotteryCategoriesListPg(cus);
		
		// Apply as Individual
		hf.gotoLotteryDetailsPgFromCatListPg(lottery.getDescription());
		hf.applyLotteryAsIndividualToAddHuntsPg();
		
		// Verify the hunt which setup as group only not displayed
		this.verifyHuntsListedBasedOnAppType(indivType, true, true, false);
		
		// Verify hunt attributes
		this.verifyHuntAttributes(hunt_Indiv, indivType);
		this.verifyHuntAttributes(hunt_Both, indivType);
		
		// Apply as group leader
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
		hf.applyLotteryAsGroupLeaderToAddHuntsPg();
		
		// Verify the hunt which setup as individual only not displayed
		this.verifyHuntsListedBasedOnAppType(groupType, true, false, true);
		
		// Verify hunt attributes
		this.verifyHuntAttributes(hunt_Group, groupType);
		this.verifyHuntAttributes(hunt_Both, groupType);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00048@webhftest.com";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4183";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.dateOfBirth = "1986-01-01";
		
		// Lottery info
		lottery.setDescription("Lottery With Mult Hunts");
		
		// Hunt info
		hunt_Indiv = new HuntInfo();
		hunt_Indiv.setDescription("Web Lottery Hunt 002");
		hunt_Indiv.setHuntCode("WLH2");
		hunt_Indiv.setMinAllowedOfGroup("1");
		hunt_Indiv.setMaxAllowedOfGroup("5");
		hunt_Indiv.setSpecie("Black Bear");
		
		hunt_Group = new HuntInfo();
		hunt_Group.setDescription("Web Lottery Hunt 003");
		hunt_Group.setHuntCode("WLH3");
		hunt_Group.setMinAllowedOfGroup("1");
		hunt_Group.setMaxAllowedOfGroup("5");
		hunt_Group.setSpecie("Black Bear");
		
		hunt_Both = new HuntInfo();
		hunt_Both.setDescription("Web Lottery Hunt 010");
		hunt_Both.setHuntCode("WLH10");
		hunt_Both.setMinAllowedOfGroup("1");
		hunt_Both.setMaxAllowedOfGroup("5");
		hunt_Both.setSpecie("Black Bear");
		hunt_Both.setWeapon("Lottery App Hunt Weapon");
		hunt_Both.setHuntDatePeriodInfo("DPA (Jan 1 - Dec 31)");
		
		LocationInfo.SubLocation subLocation = new LocationInfo.SubLocation("SubLoc Category1", "SubLoc CatValue1");
		LocationInfo.SubLocation subLocation2 = new LocationInfo.SubLocation("SubLoc Category2", "SubLoc CatValue2");
		List<LocationInfo.SubLocation> subLocations = new ArrayList<LocationInfo.SubLocation>();
		subLocations.add(subLocation);
		subLocations.add(subLocation2);
		
		LocationInfo loc = new LocationInfo();
		loc.setCode("SL2");
		loc.setDescription("HuntLocWithMultSubLoc");
		loc.setSubLocations(subLocations);
		hunt_Both.setLocationInfo(loc);

		// Web Configuration settings
		String brand = "hfsk";
		isGroupMinShown = WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.LotteryHuntGroupMinVisiable, brand);//UIOptionConfigurationUtil.isLotteryHuntGroupMinVisiable(brand);
		isGroupMaxShown = WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.LotteryHuntGroupMaxVisiable, brand);//UIOptionConfigurationUtil.isLotteryHuntGroupMaxVisiable(brand);
		isSpeciesShown = WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.LotteryHuntSpeciesVisiable, brand);//UIOptionConfigurationUtil.isLotteryHuntSpeciesVisiable(brand);
		isWeaponShown = WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.LotteryHuntWeaponVisiable, brand);//UIOptionConfigurationUtil.isLotteryHuntWeaponVisiable(brand);
		isDPShown = WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.LotteryHuntDatePeriodVisiable, brand);//UIOptionConfigurationUtil.isLotteryHuntDatePeriodVisiable(brand);
		isLocShown = WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.LotteryHuntLocationVisiable, brand);//UIOptionConfigurationUtil.isLotteryHuntLocationVisiable(brand);
		isSubLocShown = WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.LotteryHuntSubLocationsVisiable, brand);//UIOptionConfigurationUtil.isLotteryHuntSubLocationsVisiable(brand);
		
		indivType = "Individual";
		groupType = "Group Leader";
	}

	private void verifyHuntsListedBasedOnAppType(String appType, boolean isHuntBothExist, boolean isHuntIndivExist, boolean isHuntGroupExist) {
		boolean result = true;
		result &= MiscFunctions.compareResult("hunt displayed which setup for individual and group", isHuntBothExist, huntsPg.isHuntExist(hunt_Both.getHuntCode()));
		result &= MiscFunctions.compareResult("hunt displayed which setup for individual", isHuntIndivExist, huntsPg.isHuntExist(hunt_Indiv.getHuntCode()));
		result &= MiscFunctions.compareResult("hunt displayed which setup for group", isHuntGroupExist, huntsPg.isHuntExist(hunt_Group.getHuntCode()));
		if (!result) {
			throw new ErrorOnPageException("Hunts are not listed based on applicant type - " + appType);
		}
		logger.info("Successfully verify hunts listed based on applicant type - " + appType);
	}
	
	/** Verify hunt attributes. The display of attributes are configurable in /opt/sites/qa3/uwppl/docs/brands/hfsk/jsp/layout/ui-options.xml 
	 * <option name="lottery">
	 * 		<option name="hunts">
	 * 			<option name="desc" visible="true"/>
	 *			<option name="code" visible="true"/>
	 *			<option name="groupMin" visible="false"/>
	 *			<option name="groupMax" visible="false"/>
	 *			<option name="species" visible="true"/>
	 *			<option name="weapon" visible="false"/>
	 *			<option name="datePeriod" visible="false"/>
	 *			<option name="location" visible="true"/>
	 *			<option name="moreDetails" visible="true"/>
	 *			<option name="sublocations" visible="false"/>
	 * */
	private void verifyHuntAttributes(HuntInfo hunt, String appType) {
		boolean result = true;
		// Hunt Description and Code
		result &= MiscFunctions.compareString("hunt code", hunt.getHuntCode(), huntsPg.getHuntCode(hunt.getDescription()));
		
		// Hunt Attributes
		String huntAttrs = huntsPg.getHuntAttributes(hunt.getDescription());
		result &= MiscFunctions.compareResult("Group Minimum attribute shown", appType.equals(groupType) && isGroupMinShown, 
					huntAttrs.contains("Group Minimum: "+hunt.getMinAllowedOfGroup()));
		result &= MiscFunctions.compareResult("Group Maximum attribute shown", appType.equals(groupType) && isGroupMaxShown, 
				huntAttrs.contains("Group Maximum: "+hunt.getMaxAllowedOfGroup()));
		result &= MiscFunctions.compareResult("Species attribute shown", isSpeciesShown, 
				huntAttrs.contains("Species: "+hunt.getSpecie()));
		result &= MiscFunctions.compareResult("Weapon attribute shown", isWeaponShown && StringUtil.notEmpty(hunt.getWeapon()), 
				huntAttrs.contains("Weapon: "+hunt.getWeapon()));
		result &= MiscFunctions.compareResult("Date Period attribute shown", isDPShown && StringUtil.notEmpty(hunt.getHuntDatePeriodInfo()), 
				huntAttrs.contains("Date Period: "+hunt.getHuntDatePeriodInfo()));
		
		LocationInfo expLoc = hunt.getLocationInfo();
		String expLocLable = "Hunt Location: ";
		if (expLoc != null) {
			String expLocString = expLocLable + expLoc.getCode() + " - " + expLoc.getDescription();
			String expSubLocString = expLoc.getAllSubLocationInfo();
			if (isLocShown && isSubLocShown) {
				result &= MiscFunctions.containString("Hunt Location attribute", huntAttrs, expLocString + " "  +expSubLocString);
			} else if (isLocShown) {
				result &= MiscFunctions.containString("Hunt Location attribute", huntAttrs, expLocString);
			} else if (isSubLocShown) {
				result &= MiscFunctions.containString("Hunt Location attribute", huntAttrs, expLocLable + expSubLocString);
			} else {
				result &= MiscFunctions.compareResult("Hunt Location attribute shown", false, huntAttrs.contains(expLocLable));
			}
		} else {
			result &= MiscFunctions.compareResult("Hunt Location attribute shown", false, huntAttrs.contains(expLocLable));
		}
		
		if (!result) {
			throw new ErrorOnPageException("Hunt attributes are displayed wrongly for hunt - " + hunt.getHuntCode());
		}
		logger.info("Successfully verify hunt attributes for hunt - " + hunt.getHuntCode());
	}
}
