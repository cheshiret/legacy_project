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
 * @Description:This case is used to verify Add a new hunt, the date period is new added during adding the hunt
 * @Preconditions:
 * A specie has been added:
 *     code:13; description:Pet;
 *     sub type: code:CAT;description:Miaomiaojiao 
 * Weapon has been added:
 *           weapon = "AH1 - Add hunt test"[LM-->Admin(Configuration)--->Product Configuraion ----->Weapon]
 * Hunt Quota has been added for specie Pet:
 *        description:quotaAddHunt; License Year:<current year>; 
 *        QuotaType:QuotaTest; AgeMin:10; AgeMax:60; ResidencyStatus:Resident; Quota:8; Hybrid:<checked>; Weighted:1
 * Hunt Location has been added for specie Pet:
 *        code:HL1; description:HuntLocation1
 *        subLocationCategory:cate_addHunt_1 value:value_addHunt_1
 * Category has been added:
 *        CategoryName:Category_AddHunt
 * @Task#:Auto-1255
 * @author Pchen
 * @Date Sep 20, 2012
 */
public class AddHunt_NewDatePeriod extends LicenseManagerTestCase{
    private HuntInfo hunt = new HuntInfo();
    private List<QuotaType> quotaType = new ArrayList<QuotaType>();
    private QuotaInfo quota = new QuotaInfo();
    private DatePeriodInfo datePeriod = new DatePeriodInfo();
    private List<SubLocation> subLocation = new ArrayList<SubLocation>();
    private LocationInfo location = new LocationInfo();
    private LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
    private LicMgrHuntComponentsSubPage huntCompPg = LicMgrHuntComponentsSubPage.getInstance();
    private HuntInfo huntInfoInDB = new HuntInfo();
    private String createUser;
    private String facilityName;
    private String createDate;
	@Override
	public void execute() {
		lm.clearHunt(hunt.getHuntCode(), schema);
		lm.clearDatePeriod(datePeriod.getCode(), datePeriod.getDescription(), schema);
		lm.loginLicenseManager(login);

		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		
        //Add a new hunt 
		lm.addHuntFromHuntListPage(null, null, datePeriod, hunt);
		
		//Verify hunt info in hunt list page
		this.verifyHuntInfoInList();
		
		//Verify hunt info in hunt detail page
		lm.gotoHuntDetailPgFromHuntsListPg(hunt.getHuntCode());
		this.verifyHuntInfoInDetailPage();
		
		//Verify dateTime/location/user/account information which are not shown on page in DB
		huntInfoInDB = lm.getHuntInfoFromDB(hunt.getHuntCode(), schema);
		this.verifyHuntInfoInDB();
		
		//Clear test data
		lm.clearHunt(hunt.getHuntCode(), schema);
		lm.clearDatePeriod(datePeriod.getCode(), datePeriod.getDescription(), schema);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		//DatePeriod info
		datePeriod.setCode("DPN");
		datePeriod.setDescription("dp_AddHunt_new_added");		
		DatePeriodLicenseYearInfo dpLYInfo= new DatePeriodLicenseYearInfo();
		dpLYInfo.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));
		List<Dates> dateList = new ArrayList<Dates>(); 
		//from, to, category
		dateList.add(dpLYInfo.new Dates("Sep 01 " + String.valueOf(DateFunctions.getCurrentYear()),
				"Dec 31 " + String.valueOf(DateFunctions.getCurrentYear()),
                "Category_AddHunt"));
		dpLYInfo.setDates(dateList);
		List<DatePeriodLicenseYearInfo> dpLYList = new ArrayList<DatePeriodLicenseYearInfo>();
		dpLYList.add(dpLYInfo);
		datePeriod.setLicenseYears(dpLYList);
		
		//hunt info
		hunt.setSpecie("Pet");
		hunt.setHuntCode("AddHuntNewDP");
		hunt.setDescription("ForAddHuntNewDatePeriodCase");
		hunt.setHuntStatus("Active");
		hunt.setAllowedApplicants("Individual", "Group");
		hunt.setMinAllowedOfGroup("3");
		hunt.setMaxAllowedOfGroup("10");
		hunt.setHuntQuotaDescription("quotaAddHunt");
		hunt.setWeapon("AH1 - Add hunt test");
		hunt.setSpecieSubType("CAT - Miaomiaojiao");
		hunt.setHuntLocationInfo("HL1-HuntLocation1");
		hunt.setHuntDatePeriodInfo(datePeriod.getCode() +" - " + datePeriod.getDescription());	
		hunt.setQuotaLimited(true);
		
		//Quota info
		quota.setDescription(hunt.getHuntQuotaDescription());
		quota.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));	
		quotaType.add(new QuotaType("QuotaTest", "10", "60", "Resident", "8", true, "1", "7"));
		//quotaType, ageMin, ageMax, residencyStatus, quota, isHybrid, Weighted, Random
		quota.setQuotaTypes(quotaType);
		
		//Location info
		//category, value
		subLocation.add(new SubLocation("cate_addHunt_1","value_addHunt_1"));
		location.setSubLocations(subLocation);
		
		//User info
		createUser = TestProperty.getProperty("orms.pm.user");
		//Create date info
		createDate = DateFunctions.getToday("yyyy-MM-dd", DataBaseFunctions.getContractTimeZone(schema));		
	}

	/**
	 * Verify the new added hunt information in hunt list 
	 */
	private void verifyHuntInfoInList() {
		if(!huntsListPg.checkHuntInfoInList(hunt)){
			throw new ErrorOnPageException("Hunt is not added correct for information in list is not correct, please check the log above!");
		}
	}
	
	private void verifyHuntInfoInDetailPage() {
		if(!huntCompPg.checkHuntInfoInDetailPage(hunt, quota, datePeriod, location)){
			throw new ErrorOnPageException("Hunt is not added correct for information in detail page is not correct, please check the log above!");
		}
		
	}
	//Verify dateTime/location/user/account information which are not shown on page in DB
	private void verifyHuntInfoInDB() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("hunt createUser:", createUser, 
				huntInfoInDB.getCreateUser());
		passed &= MiscFunctions.compareResult("hunt createDate:", createDate, 
				huntInfoInDB.getCreateDate());
		passed &= MiscFunctions.compareResult("hunt createLocation:", facilityName, 
				huntInfoInDB.getCreateLocation());
		passed &= MiscFunctions.compareResult("hunt AccountResident:", facilityName, 
				huntInfoInDB.getResidentAccount());
		if(!passed){
			throw new ErrorOnPageException("DateTime/location/user/account information in db may not correct, please check the log above!");
		}
	}
}
