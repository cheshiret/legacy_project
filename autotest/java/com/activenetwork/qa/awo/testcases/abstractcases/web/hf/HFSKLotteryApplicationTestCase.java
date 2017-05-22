package com.activenetwork.qa.awo.testcases.abstractcases.web.hf;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.HFLotteryProperty;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryLicenseYearsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryApplicationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @Task#: Auto-2110
 * 
 * @author SWang
 * @Date  Feb 14, 2014
 */
public abstract class HFSKLotteryApplicationTestCase extends HFSKWebTestCase{
	protected PrivilegeLotteryScheduleInfo schedule = new PrivilegeLotteryScheduleInfo();
	protected HFLotteryProduct lottery = new HFLotteryProduct();
	protected HuntInfo hunt = new HuntInfo();
	protected QuotaInfo quota = new QuotaInfo();
	protected LicenseYear licenseYear = new LicenseYear();
	protected LoginInfo login = new LoginInfo();
    
	protected HFAccountOverviewPage accountOverviewPg = HFAccountOverviewPage.getInstance();
	protected HFOrderHistoryPage orderHistoryPg = HFOrderHistoryPage.getInstance();
	protected HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage.getInstance();
	protected HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
	
	protected String salesLocation, adminLocation, lotteryOrderNum, privOrderNum, licenseNum;
	protected List<String> lotteryOrderNums = new ArrayList<String>();
	protected String lotteryAppDate, instructionalAwardMsgReg, instructionalAwardMsgStringReg, revokeReason, revokeNote, reverseReason, reverseNote, noResultsMsg;
	protected String acceptDeclineAwardMsg, acceptDeclineAwardMsg2, //lotteryAppAttrsRegx, orderHistoryAttrsRegx, recentOrderAttrsRegx, 
	label_PointType, label_ApplicationNum, label_BigGameDrawOrderNum, brand, label_ApplicationFee, label_Point, lable_huntCode;
	protected String lotteryDetails_lotteryApp, lotteryDetails_orderHistory, lotteryDetails_accountOverview, awardedHuntDetails, enteredHuntDetails, huntInfo_First, huntInfo_second, groupMemberInfo;
	protected boolean isEnteredLotteryCodeExist, isEnteredLotterySpeciesExist, isEnteredLotteryWeaponExist, isEnteredLotteryDatePeriodExist, isEnteredLotteryLocationExist,
	isAwardedLotteryCodeExist, isAwardedLotterSpeciesExist, isAwardedLotteryWeaponExist, isAwardedLotteryDatePeriodExist, isAwardedLotteryLocationExist;
	protected String enteredLotteryHuntAttris, awardedLotteryHuntAttris, acceptAwardPgTitle;
	protected TimeZone timeZone;

	protected HFSKLotteryApplicationTestCase() {
		super();

		//Login in license manager info
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "SK Contract";
		login.location = "SK Admin - Auto/SASK";
		salesLocation = "SK - Compliance Field Offices - Auto-Ministry of Environment - Big River(Store Loc)-Ministry of Environment - Big River(Station)";
		adminLocation = "SK Admin - Auto-SASK";

		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		lotteryAppDate = DateFunctions.formatDate(DateFunctions.getToday(timeZone), "E MMM dd yyyy");

		instructionalAwardMsgReg = "Congratulations, you have been awarded a Big Game Draw Licence\\!";
		acceptDeclineAwardMsg = WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HFSK_Member_Lottery_Accept_Deadline_Date).split("\\{")[0].trim();
		
		//Revoke 
		revokeReason = "201 - Incorrect Selection";
		revokeNote = "Automation Test";

		//Reverser
		reverseReason = "111 - Other (Please State Reason)";
		reverseNote = "Automation Test";

		noResultsMsg = "Your Big Game Draw applications will appear here.";

		//
		label_ApplicationNum = "Application #:";
		label_BigGameDrawOrderNum = "Big Game Draw Order #:";
		label_PointType = "WMZ 99 \\(retain pool status\\):";
		label_ApplicationFee = "Application Fee:";
		label_Point = WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HFSK_Member_Lottery_Hunt_Points).trim();
		lable_huntCode = WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HFSK_Member_Lottery_Hunt_Code);
		//Web configure from /opt/sites/${env}/uwppl/docs/brands/${brandname}/jsp/layout/ui-options.xml
		brand = "hfsk";
		isEnteredLotteryCodeExist = WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.EnteredLotteryHuntCodeExist, "hfsk");//UIOptionConfigurationUtil.isEnteredLotteryHuntCodeExist(brand);
		isEnteredLotterySpeciesExist = WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.EnteredLotteryHuntSpeciesExist, "hfsk");//UIOptionConfigurationUtil.isEnteredLotteryHuntSpeciesExist(brand);
		isEnteredLotteryWeaponExist = false; //config.isEnteredLotteryHuntWeaponExist(brand);
		isEnteredLotteryDatePeriodExist = false; //config.isEnteredLotteryHuntDatePeriedExist(brand);
		isEnteredLotteryLocationExist = false; //config.isEnteredLotteryHuntLocationExist(brand);
		isAwardedLotteryCodeExist = WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.AwardedLotteryHuntCodeExist, "hfsk");//UIOptionConfigurationUtil.isAwardedLotteryHuntCodeExist(brand);
		isAwardedLotterSpeciesExist = WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.AwardedLotteryHuntSpeciesExist, "hfsk");//UIOptionConfigurationUtil.isAwardedLotteryHuntSpeciesExist(brand);
		isAwardedLotteryWeaponExist = false; //config.isAwardedLotteryHuntWeaponExist(brand);
		isAwardedLotteryDatePeriodExist = false; //config.isAwardedLotteryHuntDatePeriedExist(brand);
		isAwardedLotteryLocationExist = false; //config.isAwardedLotteryHuntLocationExist(brand);
	}

	protected void initializeGroupMemberInfo(){
		if(StringUtil.notEmpty(cusNew.dateOfBirth) && !cusNew.dateOfBirth.matches("\\d+-\\d+-\\d+")){
			cusNew.dateOfBirth = cusNew.dateOfBirth.substring(0,4)+"-"+cusNew.dateOfBirth.substring(4,6)+"-"+cusNew.dateOfBirth.substring(6,8);
		}
		groupMemberInfo = cusNew.fName +" "+cusNew.lName +", "+cusNew.dateOfBirth+ ", "+cusNew.custNum;
	}
	
	protected String initializeEnteredLotteryHuntAttris(String huntCode, boolean withGroupMemberInfo){
		if(StringUtil.notEmpty(cusNew.dateOfBirth) && !cusNew.dateOfBirth.matches("\\d+-\\d+-\\d+")){
			cusNew.dateOfBirth = cusNew.dateOfBirth.substring(0,4)+"-"+cusNew.dateOfBirth.substring(4,6)+"-"+cusNew.dateOfBirth.substring(6,8);
		}
		enteredLotteryHuntAttris = "WMZ: "+(isEnteredLotteryCodeExist?(StringUtil.notEmpty(huntCode)?huntCode:hunt.getHuntCode()):StringUtil.EMPTY)+
				(isEnteredLotterySpeciesExist?"Species: Black Bear-Species Sub Type":StringUtil.EMPTY)+
				(isEnteredLotteryWeaponExist?"Weapon: Lottery App Hunt Weapon":StringUtil.EMPTY)+
				(isEnteredLotteryDatePeriodExist?"Date Period: DPA (Jan 01-Dec 31)":StringUtil.EMPTY)+
				(isEnteredLotteryLocationExist?"Hunt Location: LAH":StringUtil.EMPTY);
		if(withGroupMemberInfo){
			enteredLotteryHuntAttris += (StringUtil.notEmpty(cusNew.fName)?" Group Members "+cusNew.fName:StringUtil.EMPTY)+
					(StringUtil.notEmpty(cusNew.lName)?" "+cusNew.lName:StringUtil.EMPTY)+
					(StringUtil.notEmpty(cusNew.dateOfBirth)?", "+cusNew.dateOfBirth:StringUtil.EMPTY)+
					(StringUtil.notEmpty(cusNew.custNum)?", "+cusNew.custNum:StringUtil.EMPTY);
		}
		return MiscFunctions.regxBracket(enteredLotteryHuntAttris);
	}

	protected String initializeEnteredLotteryHuntAttris(String huntCode){
		return initializeEnteredLotteryHuntAttris(huntCode, false);
	}

	protected String initializeEnteredLotteryHuntAttris(boolean withGroupMemberInfo){
		return initializeEnteredLotteryHuntAttris(StringUtil.EMPTY, withGroupMemberInfo);
	}

	protected String initializeEnteredLotteryHuntAttris(){
		return initializeEnteredLotteryHuntAttris(false);
	}

	protected String initializeAwardedLotteryHuntAttris(){
		awardedLotteryHuntAttris = "WMZ: "+(isAwardedLotteryCodeExist?hunt.getHuntCode():StringUtil.EMPTY)+
				(isAwardedLotterSpeciesExist?"Species: Black Bear-Species Sub Type":StringUtil.EMPTY)+
				(isAwardedLotteryWeaponExist?"Weapon: Lottery App Hunt Weapon":StringUtil.EMPTY)+
				(isAwardedLotteryDatePeriodExist?"Date Period: DPA (Jan 01-Dec 31)":StringUtil.EMPTY)+
				(isAwardedLotteryLocationExist?"Hunt Location: LAH Group Members":StringUtil.EMPTY)
//				+
//				(StringUtil.notEmpty(cusNew.fName)?" Group Members "+cusNew.fName:StringUtil.EMPTY)+
//				(StringUtil.notEmpty(cusNew.lName)?" "+cusNew.lName:StringUtil.EMPTY)+
//				(StringUtil.notEmpty(cusNew.dateOfBirth)?", "+cusNew.dateOfBirth:StringUtil.EMPTY)+
//				(StringUtil.notEmpty(cusNew.custNum)?", "+cusNew.custNum:StringUtil.EMPTY
				;
		return MiscFunctions.regxBracket(awardedLotteryHuntAttris);
	}

	protected void initializeLotteryLicenseYearSellToDateTime() {
		licenseYear.sellToDate = licenseYear.sellFromDate;
		licenseYear.sellToTime = licenseYear.sellFromTime;
		licenseYear.sellToAmPm = licenseYear.sellFromAmPm;
	}

	protected void generateInstructionalMsg(){
		instructionalAwardMsgStringReg = "Awarded Draw Applications "+lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) "+lotteryAppDate+" Application #: "+lotteryOrderNum+" Status: "+AWARDED_STATUS+" "+instructionalAwardMsgReg+" Awarded Hunt.*";
	}

	protected void prepareLotteryAppInLM(boolean processLottery, boolean revokeLottery, int numOfOrder){
		LicMgrLotteryLicenseYearsPage licenseYearPg = LicMgrLotteryLicenseYearsPage.getInstance();
		lm.loginLicenseManager(login);

		//* add or update a valid license year to purchase for this Lottery Product
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lottery.getCode());
		lm.gotoLotteryProductLicenseYearsPage();
		licenseYear.id = licenseYearPg.getLicenseYearId(licenseYear.status, licenseYear.locationClass, licenseYear.licYear);
		licenseYear.sellFromTime = DateFunctions.getCurrentTimeFormated(false, 1, timeZone);
		if(StringUtil.isEmpty(licenseYear.id)){
			licenseYear.id = lm.addLotteryLicenseYear(licenseYear);
		}
		licenseYear.sellFromDate = DateFunctions.getDateAfterToday(-1, timeZone);
		licenseYear.id = lm.updatePrivilegeLotteryLicenseYear(licenseYear);

		//* deactivate previous Processing schedule, and add a new one
		lm.gotoLotterySchedulePage();
		lm.deactivateLotterySchedule(schedule.getLicenseYear(), schedule.getDescription());
		schedule.setId(lm.addLotterySchedule(schedule));

		//* make a lottery
		lm.switchLocationInHomePage(salesLocation);
		for(int i=1; i<=numOfOrder; i++){
			lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cus, lottery);
			lotteryOrderNums.add(lm.processOrderCart(pay));
		}
		lotteryOrderNum = lotteryOrderNums.get(0);
		
		//* goto Lottery details - License Year tab to update the Sell To Date before current time
		lm.switchLocationInHomePage(adminLocation);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lottery.getCode());
		this.initializeLotteryLicenseYearSellToDateTime();
		lm.updatePrivilegeLotteryLicenseYear(licenseYear);

		//* process lottery
		if(processLottery){
			lm.gotoLotterySchedulePage();
			lm.processLotterySchedule(schedule.getLicenseYear(), schedule.getDescription());
			lm.refreshProcessingResults();
		}

		if(revokeLottery){
			//Revoke lottery
			lm.gotoApplicationOrderDetailsPageFromTopMenu(lotteryOrderNum);
			lm.revokeAppOrderToAppOrderDetailsPg(revokeReason, revokeNote);
		}

		lm.logOutLicenseManager();
	}

	protected void prepareLotteryAppInLM(boolean revokeLottery, int numOfOrder){
		prepareLotteryAppInLM(true, revokeLottery, numOfOrder);
	}
	
	protected void prepareLotteryAppInLM(int numOfOrder){
		prepareLotteryAppInLM(false, numOfOrder);
	}
	
	protected void prepareLotteryAppInLM(boolean revokeLottery){
		prepareLotteryAppInLM(revokeLottery, 1);
	}
	
	protected void prepareLotteryAppInLM(){
		prepareLotteryAppInLM(false, 1);
	}
	
	protected void verifyInstructionalAwardMsg(Page expectedPg, String instrctionalMsg, boolean isInstructionalMesExist){
		String lotteriesDivContent = StringUtil.EMPTY;
		boolean result = true;

		if(expectedPg==accountOverviewPg){
			lotteriesDivContent = accountOverviewPg.getLotteriesDivContent();
			if(StringUtil.isEmpty(lotteriesDivContent)){
				result &= true;
			}else result &= MiscFunctions.compareResult("Instructional message in account overview page", isInstructionalMesExist, lotteriesDivContent.matches(instrctionalMsg));
		}else if (expectedPg==orderHistoryPg){
			result &= MiscFunctions.compareResult("Instrctional message in order history page", isInstructionalMesExist, orderHistoryPg.isMsgExist(instrctionalMsg));
		}else if(expectedPg==lotteryAppPg){
			result &= MiscFunctions.compareResult("Instrctional message in lottery application page", isInstructionalMesExist, lotteryAppPg.isMsgExist(lotteryOrderNum, instrctionalMsg));
		}else throw new ErrorOnPageException("No matched page is found.");

		if(!result){
			throw new ErrorOnPageException("Instructional message is wrong. Please find the details from previous logs.");
		}else logger.info("Successfully verify instructional message.");
	}

}
