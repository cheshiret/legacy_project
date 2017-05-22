package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.hunts;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntComponentsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify hunt history record when add and edit a hunt
 * @Preconditions:
 * Feature assign:
 *     Administrator-Mississippi Department of Wildlife, Fisheries, and Parks - Mississippi State Parks    
 *          HF Administrator  LicenseManager ViewHuntHistory
 * A specie has been added:
 *     code:13; description:Pet;
 *     sub type: code:CAT;description:Miaomiaojiao 
 * Weapon has been added:
 *           weapon1 = "AH1 - Add hunt test"[LM-->Admin(Configuration)--->Product Configuraion ----->Weapon]
 *           weapon2 = "AH2 - Edit hunt test"
 * Hunt Quota has been added for specie Pet:
 *       [Quota 1] description:quotaAddHunt;
 *       [Quota 2] description:quota_EditHunt;
 * Hunt Location has been added for specie Pet:
 *       [Location1] code:HL1; description:HuntLocation1
 *       [Location2] code:HL2; description:HuntLocation2
 * Hunt date period has been added for specie Pet:
 *       [DatePeriod1] code:HD1; description:HuntDatePeriod1
 *       [DatePeriod2] code:HD2; description:HuntDatePeriod2
 * @Task#:Auto-1258
 * @author Pchen
 * @Date Sep 25, 2012
 */
public class ViewHuntInfoChangeHistory extends LicenseManagerTestCase{
    private HuntInfo hunt = new HuntInfo();
    private HuntInfo editHunt = new HuntInfo();
    private LicMgrHuntHistoryPage history = LicMgrHuntHistoryPage.getInstance();
    private LicMgrHuntComponentsSubPage huntCompPg = LicMgrHuntComponentsSubPage.getInstance();
    private String createUser;
    private String facilityName;
    private String dateTime;
    private List<ChangeHistory> addHistoryList = new ArrayList<ChangeHistory>();
    private List<ChangeHistory> editHistoryList = new ArrayList<ChangeHistory>();
    private String object;
    private String action;
    
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
		lm.gotoHuntDetailPgFromHuntsListPg(hunt.getHuntCode());
		
		//Check add hunt history
		lm.gotoHuntHistoryPageFromDetailPage();		
		this.checkHistoryInfo(addHistoryList);
		
		//Go back to hunt detail page
		lm.gotoHuntDetailPageFromHistoryPage();
		this.updateHunt();
		
		
		//Go to hunt history detail page
		lm.gotoHuntHistoryPageFromDetailPage();
		this.checkHistoryInfo(editHistoryList);
		
		//Clear test data
		lm.clearHunt(hunt.getHuntCode(), schema);
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
		
		//hunt info
		hunt.setSpecie("Pet");
		hunt.setHuntCode("ViewHistory");
		hunt.setDescription("ForViewAddHuntHistory");
		hunt.setAllowedApplicants("Individual");
		hunt.setHuntQuotaDescription("quotaAddHunt");
		hunt.setWeapon("AH1 - Add hunt test");
		hunt.setHuntLocationInfo("HL1-HuntLocation1");
		hunt.setHuntDatePeriodInfo("HD1 - HuntDatePeriod1");
		hunt.setQuotaLimited(true);
		
		
		//hunt update
		editHunt.setDescription("ForViewEditHuntHistory");
		editHunt.setAllowedApplicants("Group");
		editHunt.setMinAllowedOfGroup("3");
		editHunt.setMaxAllowedOfGroup("10");
		editHunt.setHuntQuotaDescription("quota_EditHunt");
		editHunt.setWeapon("AH2 - Edit hunt test");
		editHunt.setSpecieSubType("CAT - Miaomiaojiao");
		editHunt.setHuntLocationInfo("HL2-HuntLocation2");
		editHunt.setHuntDatePeriodInfo("HD2 - HuntDatePeriod2");
		editHunt.setQuotaLimited(true);	
		
		//User info
		createUser = DataBaseFunctions.getLoginUserName(login.userName);
		createUser = createUser.replace(",", ", ");
		
		//object info
		object = "Hunt";
		
		dateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	
		this.setUpExpectHistoryInfo();
	}

	private void setUpExpectHistoryInfo() {
		action = "Add";
		ChangeHistory history=new ChangeHistory();
	    history.setChangeHistoryInfo(dateTime, object, action, "", "", "", createUser, facilityName);
		addHistoryList.add(history);
		
		//action info
		action = "Update";
		
		//description history
		ChangeHistory history1=new ChangeHistory();
	    history1.setChangeHistoryInfo(dateTime, object, action, "Description", hunt.getDescription(),editHunt.getDescription(),
	    		createUser, facilityName);
	    editHistoryList.add(history1);
	    
	    //Allow Individual history
	  	ChangeHistory history2=new ChangeHistory();
	  	history2.setChangeHistoryInfo(dateTime, object, action, "Allow Individual", "True", "False",
	  	    		createUser, facilityName);
	  	editHistoryList.add(history2);
	  	    
	  	//Allow Group history
	  	ChangeHistory history3=new ChangeHistory();
		history3.setChangeHistoryInfo(dateTime, object, action, "Allow Group", "False", "True",
		    		createUser, facilityName);
		editHistoryList.add(history3);
		
	  	//MinAllowedOfGroup history
	  	ChangeHistory history4=new ChangeHistory();
		history4.setChangeHistoryInfo(dateTime, object, action, "Min Group Size", "0", editHunt.getMinAllowedOfGroup(),
		    		createUser, facilityName);
		editHistoryList.add(history4);
		
	  	//MaxAllowedOfGroup history
	  	ChangeHistory history5=new ChangeHistory();
		history5.setChangeHistoryInfo(dateTime, object, action, "Max Group Size", "0", editHunt.getMaxAllowedOfGroup(),
		    		createUser, facilityName);
		editHistoryList.add(history5);
		
	  	//Lottery Quota history
        //DEFECT-37285	has been fixed  	
        ChangeHistory history6=new ChangeHistory();
		history6.setChangeHistoryInfo(dateTime, object, action, "Hunt Quota", lm.getQuotaIdByDesc(hunt.getHuntQuotaDescription(), schema), 
				lm.getQuotaIdByDesc(editHunt.getHuntQuotaDescription(), schema), createUser, facilityName);
		editHistoryList.add(history6);
		  

		//Date Period history
	  	ChangeHistory history7=new ChangeHistory();
		history7.setChangeHistoryInfo(dateTime, object, action, "Date Period", lm.getDatePeriodIdByCode(hunt.getHuntDatePeriodInfo().split("-")[0].trim(), schema),
    			lm.getDatePeriodIdByCode(editHunt.getHuntDatePeriodInfo().split("-")[0].trim(), schema), createUser, facilityName);
		editHistoryList.add(history7);
		
		//Species Sub Type history
	  	ChangeHistory history8=new ChangeHistory();
		history8.setChangeHistoryInfo(dateTime, object, action, "Species Sub Type", "", lm.getSpecieSubTypeIdByCode(editHunt.getSpecieSubType().split("-")[0].trim(), schema),
		    		createUser, facilityName);
		editHistoryList.add(history8);
		
		//Weapon history
	  	ChangeHistory history9=new ChangeHistory();
		history9.setChangeHistoryInfo(dateTime, object, action, "Weapon", lm.getWeaponIdByCode(hunt.getWeapon().split("-")[0].trim(), schema),
    			lm.getWeaponIdByCode(editHunt.getWeapon().split("-")[0].trim(), schema), createUser, facilityName);
		editHistoryList.add(history9);
		
		//Hunt Location history
	  	ChangeHistory history10=new ChangeHistory();
		history10.setChangeHistoryInfo(dateTime, object, action, "Hunt Location", lm.getLocationIdByCodeAndDesc(hunt.getHuntLocationInfo().split("-")[0].trim(), hunt.getHuntLocationInfo().split("-")[1].trim(), schema),
    			lm.getLocationIdByCodeAndDesc(editHunt.getHuntLocationInfo().split("-")[0].trim(), editHunt.getHuntLocationInfo().split("-")[1].trim(), schema), createUser, facilityName);
		editHistoryList.add(history10);
	}

	private void updateHunt() {
		huntCompPg.updateHuntsInfo(editHunt);
		huntCompPg.clickApply();
		ajax.waitLoading();
		huntCompPg.waitLoading();
	}
	
	private void checkHistoryInfo(List<ChangeHistory> historyList) {
		List<ChangeHistory> showHistory = history.getChangeListTableInfo();
		if(showHistory.size()<historyList.size()){
			throw new ErrorOnPageException("History List record size is not correct.");
		}
		//get actually history list info by expect history list size
		showHistory = showHistory.subList(0, historyList.size());
		for(int i=0; i<historyList.size(); i++){
			for(int j=0; j<showHistory.size(); j++){
				if(historyList.get(i).field.equals(showHistory.get(j).field)){
					if(!showHistory.get(j).equals(historyList.get(i))){
						throw new ErrorOnPageException("History record about "+ historyList.get(i).field + " is wrong.");
					}
					break;
				}else {
					if(j == showHistory.size()){
					//	System.out.println(showHistory.size()-1);
						throw new ErrorOnPageException("History record about "+ historyList.get(i).field + " should be exists in UI.");
					}
				}
			}
		}	
	}
}
