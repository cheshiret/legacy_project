package com.activenetwork.qa.awo.testcases.abstractcases.web.hf;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryAddGroupMembersPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryApplicationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jun 6, 2014
 */
public abstract class HFABLotteryApplicationTestCase extends HFABWebTestCase{
	protected HFAccountOverviewPage accountOverviewPg = HFAccountOverviewPage.getInstance();
	protected HFOrderHistoryPage orderHistoryPg = HFOrderHistoryPage.getInstance();
	protected HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage.getInstance();
	protected HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
	protected HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
	protected HFLotteryAddGroupMembersPage addGroupMembersPg = HFLotteryAddGroupMembersPage.getInstance();
	
	protected HFLotteryProduct lottery = new HFLotteryProduct();
	protected HuntInfo hunt, hunt1, hunt2, hunt3, hunt4, hunt5, hunt6, hunt7;
	
	protected String lotteryOrderNum;
	protected String lotteryAppDate, instructionalAwardMsgReg, instructionalAwardMsgStringReg, revokeReason, revokeNote, reverseReason, reverseNote, noResultsMsg;
	protected String acceptDeclineAwardMsg, acceptDeclineAwardMsg2, //lotteryAppAttrsRegx, orderHistoryAttrsRegx, recentOrderAttrsRegx, 
	label_PointType, label_ApplicationNum, label_BigGameDrawOrderNum, brand, label_ApplicationFee;
	protected String lotteryDetails_lotteryApp, lotteryDetails_orderHistory, lotteryDetails_accountOverview, awardedHuntDetails, enteredHuntDetails, huntInfo, huntInfo_First, huntInfo_second, groupMemberInfo;
	protected boolean isEnteredLotteryCodeExist, isEnteredLotterySpeciesExist, isEnteredLotteryWeaponExist, isEnteredLotteryDatePeriodExist, isEnteredLotteryLocationExist,
	isAwardedLotteryCodeExist, isAwardedLotterSpeciesExist, isAwardedLotteryWeaponExist, isAwardedLotteryDatePeriodExist, isAwardedLotteryLocationExist;
	protected String enteredLotteryHuntAttris, awardedLotteryHuntAttris, acceptAwardPgTitle;
	protected TimeZone timeZone;

	protected HFABLotteryApplicationTestCase() {
		super();

		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		lotteryAppDate = DateFunctions.formatDate(DateFunctions.getToday(timeZone), "E MMM dd yyyy");

		instructionalAwardMsgReg = "Congratulations, you have been awarded a Big Game Draw Licence\\!";

		//Revoke 
		revokeReason = "201 - Incorrect Selection";
		revokeNote = "Automation Test";

		//Reverser
		reverseReason = "111 - Other (Please State Reason)";
		reverseNote = "Automation Test";

		noResultsMsg = "Your Big Game Draw applications will appear here.";

		//
		label_ApplicationNum = "Application #:";
		label_BigGameDrawOrderNum = "Draw & Undersubscribed Application Order #:";
		label_ApplicationFee = "Application Fee:";

		//Web configure from /opt/sites/${env}/uwppl/docs/brands/${brandname}/jsp/layout/ui-options.xml
		brand = "hfab";
		isEnteredLotteryCodeExist = true; //WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.EnteredLotteryHuntCodeExist, brand);
		isEnteredLotterySpeciesExist = true; //WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.EnteredLotteryHuntSpeciesExist, brand);
		isEnteredLotteryWeaponExist = false; 
		isEnteredLotteryDatePeriodExist = false;
		isEnteredLotteryLocationExist = false; 
		isAwardedLotteryCodeExist = true;  //WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.AwardedLotteryHuntCodeExist, brand);
		isAwardedLotterSpeciesExist = true; //WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.AwardedLotteryHuntSpeciesExist, brand);
		isAwardedLotteryWeaponExist = false; 
		isAwardedLotteryDatePeriodExist = false;
		isAwardedLotteryLocationExist = false;
	}
	
	protected String initializeEnteredLotteryHuntAttris(boolean withGroupMemberInfo, HuntInfo... hunts){
		if(StringUtil.notEmpty(cusNew.dateOfBirth) && !cusNew.dateOfBirth.matches("\\d+-\\d+-\\d+")){
			cusNew.dateOfBirth = cusNew.dateOfBirth.substring(0,4)+"-"+cusNew.dateOfBirth.substring(4,6)+"-"+cusNew.dateOfBirth.substring(6,8);
		}
		for(int i=0; i<hunts.length; i++){
			HuntInfo huntnfo = hunts[i];
			if(i==0){
				enteredLotteryHuntAttris = (i+1)+"st Choice";
			}else if(i==1){
				enteredLotteryHuntAttris += " "+(i+1)+"nd Choice";
			}else if(i==2){
				enteredLotteryHuntAttris += " "+(i+1)+"rd Choice";
			}else enteredLotteryHuntAttris += " "+(i+1)+"th Choice";
			enteredLotteryHuntAttris += ((isEnteredLotteryCodeExist && StringUtil.notEmpty(huntnfo.getHuntCode()))?"Code: "+huntnfo.getHuntCode():StringUtil.EMPTY)+
					((isEnteredLotterySpeciesExist && StringUtil.notEmpty(huntnfo.getSpecie()))?"Species: "+huntnfo.getSpecie():StringUtil.EMPTY)+
					((isEnteredLotteryWeaponExist && StringUtil.notEmpty(huntnfo.getWeapon()))?"Weapon: "+huntnfo.getWeapon():StringUtil.EMPTY)+
					((isEnteredLotteryDatePeriodExist && StringUtil.notEmpty(huntnfo.getHuntDatePeriodInfo()))?"Date Period: DPA (Jan 01-Dec 31)":StringUtil.EMPTY)+
					((isEnteredLotteryLocationExist && StringUtil.notEmpty(huntnfo.getHuntLocationInfo()))?"Hunt Location: "+huntnfo.getHuntLocationInfo():StringUtil.EMPTY);
			
		}
		if(withGroupMemberInfo){
			enteredLotteryHuntAttris += (StringUtil.notEmpty(cusNew.fName)?" Group Members "+cusNew.fName:StringUtil.EMPTY)+
					(StringUtil.notEmpty(cusNew.lName)?" "+cusNew.lName:StringUtil.EMPTY)+
					(StringUtil.notEmpty(cusNew.dateOfBirth)?", "+cusNew.dateOfBirth:StringUtil.EMPTY)+
					(StringUtil.notEmpty(cusNew.custNum)?", "+cusNew.custNum:StringUtil.EMPTY);
		}
		return MiscFunctions.regxBracket(enteredLotteryHuntAttris);
	}
	
	protected String initializeEnteredLotteryHuntAttris(HuntInfo... hunts){
		return initializeEnteredLotteryHuntAttris(false, hunts);
	}
}
