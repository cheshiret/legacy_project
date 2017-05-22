package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.hunt;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntSelectSpeciesWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddNewHuntPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify lenght restriction on code and description during adding a new hunt
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
 * Hunt date period has been added for specie Pet:
 *        code:HD1; description:HuntDatePeriod1
 *        LicenseYear:<current year>;
 *        FromDate:Sep 01 <current_year>; ToDate:Dec 31 <current_year>; Category:Category_AddHunt
 * @Task#:Auto-1255
 * @author Pchen
 * @Date Sep 21, 2012
 */
public class AddHuntWithExcessLength extends LicenseManagerTestCase{
    private HuntInfo hunt = new HuntInfo();
    private LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
    private LicMgrAddHuntSelectSpeciesWidget selectSpecieWiget = LicMgrAddHuntSelectSpeciesWidget.getInstance();
    private LicMgrAddNewHuntPage addHuntPg = LicMgrAddNewHuntPage.getInstance();
	@Override
	public void execute() {
		lm.clearHunt(hunt.getHuntCode().substring(0, 15), schema);
		lm.loginLicenseManager(login);
		
		//go to lotteries product page
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		
        //Add a new hunt 
	    //Begin to add a new hunt
		huntsListPg.clickAddHunt();
		//Select specie before add hunt
		selectSpecie(hunt.getSpecie());
		
		//Add hunt whose code is longer than 15 and description is longer than 50 
		addHuntPg.setUpHuntsInfo(hunt);
		this.verifySetLength();
		addHuntPg.clickOK();
		huntsListPg.waitLoading();
		
		hunt.setHuntCode(hunt.getHuntCode().substring(0, 15));
		hunt.setDescription(hunt.getDescription().substring(0, 50));
		this.verifyHuntInfoInList();
		
		lm.clearHunt(hunt.getHuntCode().substring(0, 15), schema);
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		//hunt info
		hunt.setSpecie("Pet");
		hunt.setHuntCode("CodeExcessLengthlongerlongerlongerlongerlonger");
		hunt.setDescription("DescriptionExcessLengthlongerlongerlongerlongerlongerlonger" + 
				"longerlongerlongerlongerlongerlongerlongerlongerlongerlonger");
		hunt.setHuntStatus("Active");
		hunt.setAllowedApplicants("Individual", "Group");
		hunt.setMinAllowedOfGroup("3");
		hunt.setMaxAllowedOfGroup("10");
		hunt.setHuntQuotaDescription("quotaAddHunt");
		hunt.setWeapon("AH1 - Add hunt test");
		hunt.setSpecieSubType("CAT - Miaomiaojiao");
		hunt.setHuntLocationInfo("HL1-HuntLocation1");
		hunt.setHuntDatePeriodInfo("HD1 - HuntDatePeriod1");	
	}
	
	private void selectSpecie(String specie) {
		selectSpecieWiget.waitLoading();
		selectSpecieWiget.selectSpecie(specie);
		selectSpecieWiget.clickOK();
		ajax.waitLoading();
	}
	/**
	 * Verify the excess length part is cut for code and description
	 */
	private void verifySetLength() {
		boolean passed = true;
		String codeSet = addHuntPg.getHuntCode();
		String descSet = addHuntPg.getDescription();
		passed &= MiscFunctions.compareResult("Check length and content for code:", hunt.getHuntCode().substring(0, 15), codeSet);
		passed &= MiscFunctions.compareResult("Check length and content for description:", hunt.getDescription().substring(0, 50), descSet);
		if(!passed){
			throw new ErrorOnPageException("Error message shown wrong, please check the log above!");
		}		
	}
	
	/**
	 * Verify the new added hunt information in hunt list 
	 */
	private void verifyHuntInfoInList() {
		if(!huntsListPg.checkHuntInfoInList(hunt)){
			throw new ErrorOnPageException("Hunt is not added correct for information in list is not correct, please check the log above!");
		}
	}
}
