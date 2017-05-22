package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.feevalidation.feecalculationvalidation;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.SiteAttributes;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class VerifyFeeCalculatValidatForOvernightSiteFromSiteList extends InventoryManagerTestCase {
	SiteAttributes siteAttr_1 = new SiteAttributes();
	SiteAttributes siteAttr_2 = new SiteAttributes();
	FeeValidationData feeValidData = new FeeValidationData();
	FeeValidationData feeValidData_1 = new FeeValidationData();
	FeeValidationData feeValidData_2 = new FeeValidationData();
	FeeValidationData feeValidData_3 = new FeeValidationData();
	List<String> tier1DiscDetailRow = new ArrayList<String>();
	List<String> tier1DiscDetailRow_1 = new ArrayList<String>();
	List<String> tier1DiscDetailRow_2 = new ArrayList<String>();
	List<String> tier2DiscDetailRow = new ArrayList<String>();
	List<String> tier2DiscDetailRow_1 = new ArrayList<String>();
	List<String> tier2DiscDetailRow_2 = new ArrayList<String>();
	List<String> siteInfos = new ArrayList<String>();
	List<FeeValidationData> stayDates = new ArrayList<FeeValidationData>();
	List<String> feeTypes = new ArrayList<String>();
	List<String> rate = new ArrayList<String>();
	List<String> rateUnit = new ArrayList<String>();
	List<String> appliesTo = new ArrayList<String>();
	List<String> feeDates = new ArrayList<String>();
	String siteInfo, feeDatesAndUnitsNum = "";
	InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
	InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage
			.getInstance();
	public void execute(){
		//Login inventory manager
		im.loginInventoryManager(login);

		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.goToSiteListPage();
		this.getSiteInfo();
		
//		im.gotoSiteDetailPage(siteAttr.siteID, false, false);[Note: this method go to the detail page by search which will result in missing of 'Next' and 'Previous']
		invSitePg.turnToFirstPage();
		invSitePg.turnToSiteExistPage(siteAttr.siteID);
		invSitePg.clickSiteID(siteAttr.siteID);
		ajax.waitLoading();
		invSiteDetailPg.waitLoading();

		im.gotoCalculationFromSiteDetail();

		//Verify information in Fee calculation Validation page
		im.verifyFeeCalculationValidation(feeValidData, stayDates, true, false, siteInfos, feeTypes, rate, rateUnit, appliesTo, feeDates);

		im.logoutInvManager();
	}
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";
		inventory.facilityName="SANTEE";

		schema = TestProperty.getProperty(env+".db.schema.prefix")+"SC";
		//Stay Dates
		feeValidData_1.arriveDate = DateFunctions.getDateAfterToday(1, DataBaseFunctions.getContractTimeZone(schema));
		feeValidData_1.departureDate = DateFunctions.getDateAfterToday(2, DataBaseFunctions.getContractTimeZone(schema));
		stayDates.add(feeValidData_1);
		feeValidData_2.nightStay = "2";
		feeValidData_2.arriveDate = "";
		feeValidData_2.departureDate = DateFunctions.getDateAfterToday(3, DataBaseFunctions.getContractTimeZone(schema));
		stayDates.add(feeValidData_2);
		feeValidData_3.arriveDate = "";
		feeValidData_3.departureDate = DateFunctions.getDateAfterToday(4, DataBaseFunctions.getContractTimeZone(schema));
		feeValidData_3.nightStay = "3";
		stayDates.add(feeValidData_3);

		feeValidData.bookDate = DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), "MMM d, yyyy");
		feeDatesAndUnitsNum = DateFunctions.formatDate(feeValidData_1.arriveDate, "MMM d, yyyy")+" - "+DateFunctions.formatDate(feeValidData_2.departureDate, "MMM d, yyyy")+" ("+feeValidData_3.nightStay+" units)";

		//Type of use
		feeValidData.typeOfUse = "Overnight";

		//Rate Type
		feeValidData.rateType = "Family";

		//Discounts /Promotions
		feeValidData.disctTier1 = "Santee 17";
		feeValidData.disctTier1Items.add("");
		feeValidData.disctTier1Items.add("Santee 11");
		feeValidData.disctTier1Items.add("Santee 17");
		tier1DiscDetailRow.add("Use Fee");
		tier1DiscDetailRow.add("1.0");
		tier1DiscDetailRow.add("Percent");
		tier1DiscDetailRow.add(feeDatesAndUnitsNum);
		tier1DiscDetailRow_1.add("Attribute Fee");
		tier1DiscDetailRow_1.add("1.0");
		tier1DiscDetailRow_1.add("Percent");
		tier1DiscDetailRow_1.add(feeDatesAndUnitsNum);
		tier1DiscDetailRow_2.add("Transaction Fee");
		tier1DiscDetailRow_2.add("1.0");
		tier1DiscDetailRow_2.add("Percent");
		tier1DiscDetailRow_2.add(feeDatesAndUnitsNum);
		feeValidData.specificTier1DisctDetail.add(tier1DiscDetailRow);
		feeValidData.specificTier1DisctDetail.add(tier1DiscDetailRow_2);
		feeValidData.specificTier1DisctDetail.add(tier1DiscDetailRow_1);
		
		feeValidData.disctTier2 = "Santee 18";
		feeValidData.disctTier2Items.add("");
		feeValidData.disctTier2Items.add("Santee 18");
		tier2DiscDetailRow.add("Use Fee");
		tier2DiscDetailRow.add("10.0");
		tier2DiscDetailRow.add("Percent");
		tier2DiscDetailRow.add(feeDatesAndUnitsNum);
		tier2DiscDetailRow_1.add("Attribute Fee");
		tier2DiscDetailRow_1.add("10.0");
		tier2DiscDetailRow_1.add("Percent");
		tier2DiscDetailRow_1.add(feeDatesAndUnitsNum);
		tier2DiscDetailRow_2.add("Transaction Fee");
		tier2DiscDetailRow_2.add("10.0");
		tier2DiscDetailRow_2.add("Percent");
		tier2DiscDetailRow_2.add(feeDatesAndUnitsNum);
		feeValidData.specificTier2DisctDetail.add(tier2DiscDetailRow);
		feeValidData.specificTier2DisctDetail.add(tier2DiscDetailRow_2);
		feeValidData.specificTier2DisctDetail.add(tier2DiscDetailRow_1);
		
		//Site Info
		siteAttr.siteID = "1137"; 
		siteAttr.siteCode = "C-001";
		siteAttr.siteName = "SANT_PierCabins_C-001";
		siteAttr.parentLoop = "Pier Cabins";
		siteAttr.siteType = "Cabin";
		siteInfo = "Site Site ID "+siteAttr.siteID+" Site Code "+siteAttr.siteCode+" Site Name " +siteAttr.siteName+" Parent Loop "+siteAttr.parentLoop+" Site Type "+siteAttr.siteType;

		//Custom Fee info
		feeTypes.add("Use Fee");
		feeTypes.add("Santee 17 ( Tier 1 discount)");
		feeTypes.add("Santee 18 ( Tier 2 discount)");
		feeTypes.add("Accomodations Tax (tax)");
		feeTypes.add("Attribute Fee");
		feeTypes.add("Santee 17 ( Tier 1 discount)");
		feeTypes.add("Santee 18 ( Tier 2 discount)");
		feeTypes.add("Accomodations Tax (tax)");
		feeTypes.add("Transaction Fee");
		feeTypes.add("Santee 17 ( Tier 1 discount)");
		feeTypes.add("Santee 18 ( Tier 2 discount)");
		feeTypes.add("Accomodations Tax (tax)");
		rate.add("");
		rate.add("1.0% per stay");
		rate.add("10.0% per stay");
		rate.add("7.0%");
		rate.add("");
		rate.add("1.0% per stay");
		rate.add("10.0% per stay");
		rate.add("7.0%");
		rate.add("");
		rate.add("1.0% per stay");
		rate.add("10.0% per stay");
		rate.add("7.0%");
		rateUnit.add("Daily/Nightly");
		rateUnit.add("");
		rateUnit.add("");
		rateUnit.add("");
		rateUnit.add("Daily/Nightly");
		rateUnit.add("");
		rateUnit.add("");
		rateUnit.add("");
		rateUnit.add("");
		rateUnit.add("");
		rateUnit.add("");
		rateUnit.add("");
		appliesTo.add("");
		appliesTo.add("Use Fee");
		appliesTo.add("Use Fee");
		appliesTo.add("Use Fee");
		appliesTo.add("Picnic Table");
		appliesTo.add("Attribute Fee");
		appliesTo.add("Attribute Fee");
		appliesTo.add("Attribute Fee");
		appliesTo.add("Reservation");
		appliesTo.add("Transaction Fee/Reservation");
		appliesTo.add("Transaction Fee/Reservation");
		appliesTo.add("Transaction Fee/Reservation");
		feeDates.add(feeDatesAndUnitsNum);	
		feeDates.add(feeDatesAndUnitsNum);	
		feeDates.add(feeDatesAndUnitsNum);	
		feeDates.add(feeDatesAndUnitsNum);	
		feeDates.add(feeDatesAndUnitsNum);	
		feeDates.add(feeDatesAndUnitsNum);	
		feeDates.add(feeDatesAndUnitsNum);	
		feeDates.add(feeDatesAndUnitsNum);	
		feeDates.add(feeValidData.bookDate);	
		feeDates.add(feeValidData.bookDate);	
		feeDates.add(feeValidData.bookDate);	
		feeDates.add(feeValidData.bookDate);	
	}

	private void getSiteInfo(){
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		siteInfos.add(siteInfo);
		siteInfos.add(siteInfo);
		siteInfos.add(invSitePg.getSiteInfo(siteAttr.siteID, "Next"));
		siteInfos.add(invSitePg.getSiteInfo(siteAttr.siteID, "Last"));
	}
}
