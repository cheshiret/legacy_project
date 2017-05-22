package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.hunts;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo.SubLocation;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntComponentsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case is used to verify hunt history record when add and edit a hunt
 * @Preconditions:
 * A specie has been added:
 *     code:13; description:Pet;
 *     sub type: code:CAT;description:Miaomiaojiao 
 * Weapon has been added:
 *           weapon1 = "AH1 - Add hunt test"[LM-->Admin(Configuration)--->Product Configuraion ----->Weapon]
 *           weapon2 = "AH2 - Edit hunt test"
 * Hunt Quota has been added for specie Pet:
 *       [Quota 1] description:quotaAddHunt;
 *       [Quota 2] description:quota_EditHunt; License Year:<current year>; 
 *        QuotaType:EditHuntQuota; AgeMin:2; AgeMax:9; ResidencyStatus:Resident; Quota:12; Hybrid:<checked>; Weighted:10
 * Hunt Location has been added for specie Pet:
 *       [Location1] code:HL1; description:HuntLocation1
 *       [Location2] code:HL2; description:HuntLocation2
 *                   subLocationCategory:cate_addHunt_2 value:value_addHunt_2
 * Hunt date period has been added for specie Pet:
 *       [DatePeriod1] code:HD1; description:HuntDatePeriod1
 *       [DatePeriod2] code:HD2; description:HuntDatePeriod2
 *        LicenseYear:<current year>;
 *        FromDate:Sep 02 <current_year>; ToDate:Dec 30 <current_year>; Category:Category_EditHunt
 * @Task#:Auto-1256
 * @author Pchen
 * @Date Sep 25, 2012
 */
/**
 * @Description:This case is used to verify edit a hunt
 * @Preconditions:
 * A specie has been added:
 *     code:13; description:Pet;
 *     sub type: code:CAT;description:Miaomiaojiao 
 * Weapon has been added:
 *           weapon1 = "AH1 - Add hunt test"[LM-->Admin(Configuration)--->Product Configuraion ----->Weapon]
 *           weapon2 = "AH2 - Edit hunt test"
 * Hunt Quota has been added for specie Pet:
 *       [Quota 1] description:quotaAddHunt;
 *       [Quota 2] description:quota_EditHunt; License Year:<current year>; 
 *                QuotaType:EditHuntQuota; AgeMin:2; AgeMax:9; ResidencyStatus:Resident; Quota:12; Hybrid:<checked>; Weighted:10
 * Hunt Location has been added for specie Pet:
 *       [Location1] code:HL1; description:HuntLocation1
 *       [Location2] code:HL2; description:HuntLocation2
 *                        subLocationCategory:cate_addHunt_2 value:value_addHunt_2
 * Hunt date period has been added for specie Pet:
 *       [DatePeriod1] code:HD1; description:HuntDatePeriod1
 *       [DatePeriod2] code:HD2; description:HuntDatePeriod2
 *                     LicenseYear:<current year>;
 *                     FromDate:Sep 02 <current_year>; ToDate:Dec 30 <current_year>; Category:Category_EditHunt
 * @Task#:Auto-1256
 * @author Pchen
 * @Date Sep 20, 2012
 */
public class EditHunt extends LicenseManagerTestCase{
    private HuntInfo hunt = new HuntInfo();
    private HuntInfo editHunt = new HuntInfo();
    private QuotaInfo quota = new QuotaInfo();
    private List<QuotaType> quotaType;
    private DatePeriodInfo datePeriod = new DatePeriodInfo();
    private List<SubLocation> subLocation = new ArrayList<SubLocation>();
    private LocationInfo location = new LocationInfo();
    private LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
    private LicMgrHuntComponentsSubPage huntCompPg = LicMgrHuntComponentsSubPage.getInstance();
    private HuntInfo huntInfoInDB = new HuntInfo();
    private String updateUser;
    private String facilityName;
    private String updateDate;
	@Override
	public void execute() {
		lm.clearHunt(hunt.getHuntCode(), schema);
		lm.loginLicenseManager(login);

		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		
        //Add a new hunt 
		lm.addHuntFromHuntListPage(null, null, null, hunt);
		
		//Update the hunt
		lm.gotoHuntDetailPgFromHuntsListPg(hunt.getHuntCode());
		this.updateHunt(editHunt);
		
		//Verify hunt info in hunt list page
		this.verifyHuntInfoInList();
		
		//Verify hunt info in hunt detail page
		lm.gotoHuntDetailPgFromHuntsListPg(hunt.getHuntCode());
		this.verifyHuntInfoInDetailPage();
		
		//Verify updateUser/updateTime/updateLocation information which are not shown on page in DB and the id information of quota/location/dateperiod
		huntInfoInDB = lm.getHuntInfoFromDB(hunt.getHuntCode(), schema);
		this.verifyHuntInfoInDB();
		
		//Clear test data
		lm.clearHunt(hunt.getHuntCode(), schema);
		lm.logOutLicenseManager();
	}

	private void updateHunt(HuntInfo hunt) {
		huntCompPg.updateHuntsInfo(hunt);
		huntCompPg.clickOK();
		huntsListPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		//hunt info
		hunt.setSpecie("Pet");
		hunt.setHuntCode("EditHunt");
		hunt.setDescription("ForEditHuntCase");
		hunt.setAllowedApplicants("Individual", "Group");
		hunt.setMinAllowedOfGroup("3");
		hunt.setMaxAllowedOfGroup("10");
		hunt.setHuntQuotaDescription("quotaAddHunt");
		hunt.setWeapon("AH1 - Add hunt test");
		hunt.setSpecieSubType("");
		hunt.setHuntLocationInfo("HL1-HuntLocation1");
		hunt.setHuntDatePeriodInfo("HD1 - HuntDatePeriod1");
		hunt.setQuotaLimited(true);
		
		
		//hunt update
		editHunt.setHuntCode(hunt.getHuntCode());
		editHunt.setSpecie(hunt.getSpecie());
		editHunt.setDescription("ForViewEditHuntHistory");
		editHunt.setHuntStatus("Active");
		editHunt.setAllowedApplicants("Individual", "Group");
		editHunt.setMinAllowedOfGroup("2");
		editHunt.setMaxAllowedOfGroup("13");
		editHunt.setHuntQuotaDescription("quota_EditHunt");
		editHunt.setWeapon("AH2 - Edit hunt test");
		editHunt.setSpecieSubType("CAT - Miaomiaojiao");
		editHunt.setHuntLocationInfo("HL2-HuntLocation2");
		editHunt.setHuntDatePeriodInfo("HD2 - HuntDatePeriod2");
		editHunt.setQuotaLimited(true);
		
		//Quota info
		quota.setDescription(editHunt.getHuntQuotaDescription());
		quota.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));	
		quotaType = new ArrayList<QuotaType>();
		quotaType.add(new QuotaType("EditHuntQuota", "2", "9", "Resident", "12", true, "10", "2"));
		quota.setQuotaTypes(quotaType);
		
		
		//DatePeriod info
		DatePeriodLicenseYearInfo dpLYInfo= new DatePeriodLicenseYearInfo();
		dpLYInfo.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));
		List<Dates> dateList = new ArrayList<Dates>(); 
		//from, to, category
		dateList.add(dpLYInfo.new Dates("Sep 02 " + String.valueOf(DateFunctions.getCurrentYear()),
				"Dec 30 " + String.valueOf(DateFunctions.getCurrentYear()),
                "Category_EditHunt"));
		dpLYInfo.setDates(dateList);
		List<DatePeriodLicenseYearInfo> dpLYList = new ArrayList<DatePeriodLicenseYearInfo>();
		dpLYList.add(dpLYInfo);
		datePeriod.setLicenseYears(dpLYList);
		
		//Location info
		//category, value
		subLocation.add(new SubLocation("cate_addHunt_2","value_addHunt_2"));
		location.setSubLocations(subLocation);
		
		//User info
		updateUser = TestProperty.getProperty("orms.pm.user");
		//Update date info
		updateDate = DateFunctions.getToday("yyyy-MM-dd", DataBaseFunctions.getContractTimeZone(schema));		
	}

	/**
	 * Verify the new added hunt information in hunt list 
	 */
	private void verifyHuntInfoInList() {
		if(!huntsListPg.checkHuntInfoInList(editHunt)){
			throw new ErrorOnPageException("Hunt is not added correct for information in list is not correct, please check the log above!");
		}
	}
	
	private void verifyHuntInfoInDetailPage() {
		if(!huntCompPg.checkHuntInfoInDetailPage(editHunt, quota, datePeriod, location)){
			throw new ErrorOnPageException("Hunt is not added correct for information in detail page is not correct, please check the log above!");
		}
	}
	//Verify dateTime/location/user/account information which are not shown on page in DB
	private void verifyHuntInfoInDB() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("hunt updateUser:", updateUser, 
				huntInfoInDB.getCreateUser());
		passed &= MiscFunctions.compareResult("hunt updateDate:", updateDate, 
				huntInfoInDB.getCreateDate());
		passed &= MiscFunctions.compareResult("hunt updateLocation:", facilityName, 
				huntInfoInDB.getCreateLocation());
		passed &= MiscFunctions.compareResult("hunt quota id:", lm.getQuotaIdByDesc(editHunt.getHuntQuotaDescription(), schema), 
				huntInfoInDB.getQuotaId());
		passed &= MiscFunctions.compareResult("hunt location id:", 
				lm.getLocationIdByCodeAndDesc(editHunt.getHuntLocationInfo().split("-")[0].trim(),
				                              editHunt.getHuntLocationInfo().split("-")[1].trim(), schema),
				huntInfoInDB.getLocationId());
		passed &= MiscFunctions.compareResult("hunt date period id:", lm.getDatePeriodIdByCode(editHunt.getHuntDatePeriodInfo().split("-")[0].trim(), schema),
				huntInfoInDB.getDatePeriodId());
		if(!passed){
			throw new ErrorOnPageException("Update dateTime/location/user information/dateperiodId/locationId/quotaId in db may not correct, please check the log above!");
		}
	}
}
