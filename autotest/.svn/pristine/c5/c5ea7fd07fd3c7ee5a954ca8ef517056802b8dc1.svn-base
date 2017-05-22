package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDuplicateRegistrationListWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Get Vehicle Registration List for Duplicate / Duplicate Vehicle Registration 
 * @Preconditions: 
* 				 A Vehicle product: V03-ProcessFeeAdjustments
 * 				 Duplicate pricing for this vehicle.
 * @SPEC:  Get Vehicle Registration List for Duplicate / Duplicate Vehicle Registration
 * @Task#: AUTO-999
 * 
 * @author pzhu
 * @Date  Jun 4, 2012
 * 
 */
public class DuplicateVehicleRegistration extends LicenseManagerTestCase {


	private LoginInfo loginfFnm = new LoginInfo();
	OrmsOrderSummaryPage omOrdSumPg = OrmsOrderSummaryPage.getInstance();
	

	private Customer[] cust = new Customer[1];
	private String transType = "Duplicate Registration";
	
	private boolean HnF = true;//For hunting and fishing
	private String TOTAL_PRICE = "";
	private BoatInfo vehicle = new BoatInfo();
	
	@Override
	public void execute() {
		
		lm.loginLicenseManager(login);

		// make first order of register vehicle.
		lm.registerVehicleToOrderCart(cust[0], vehicle);
		lm.processOrderCart(pay);
	
		//search vehicle registration from main page.
		lm.gotoVehiclesDetailsPgBySerialNum(vehicle.hullIdSerialNum);
		
		//Check point 1: check registration list contains "V03 - ProcessFeeAdjustments"
		this.checkRegistrationInstance(); 

		//Now we stay in page: LicMgrVehicleRegistrationWidget
		//Select product and complete duplication.
		this.processDuplicationToOrderCartPg();
		lm.processOrderToOrderSummary(pay);
		
		//Check point 2: 
		this.checkTransaction(transType);
		this.checkHullIDExist(vehicle.hullIdSerialNum);
		this.checkTotalPrice(TOTAL_PRICE);
		lm.finishOrder();
		
		//log out LM
		lm.gotoHomePage();
		lm.logOutLicenseManager();
	}
	
	private void checkTotalPrice(String price) {
		OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		//Check total price.
		String totalPriceOnPage = lmOrdSumPg.getTotalPrice(HnF);
		if (!(StringUtil.refactAmountValue(totalPriceOnPage)
				.equalsIgnoreCase(StringUtil.refactAmountValue(price)))){
			throw new ErrorOnPageException("Check total price failed, we expect "+price+", but on page is "+totalPriceOnPage);
		}
	}

	private void checkHullIDExist(String hullIdSerialNum) {
		//get Boat serial number in "Receipt Item" part.
		RegularExpression objhullId = new RegularExpression("^Boat+\\s.*"+hullIdSerialNum,false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TD", ".text", objhullId);
		if(objs.length<1){
			throw new ErrorOnPageException("Cannot find vehicle Serial number in Receipt Item!!!!!");
		}
		Browser.unregister(objs);		
	}

	private void checkTransaction(String type) {
		OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		String transType = lmOrdSumPg.getTransactionsInfo();
		if(!transType.contains(type)){
			throw new ErrorOnPageException("Transaction type failed, we expect "+type+" ,but "+transType+" on the page(Transaction(s)).");
		}
	}

	private void checkRegistrationInstance() {
		LicMgrVehicleDetailPage detailPg = LicMgrVehicleDetailPage
		.getInstance();
		LicMgrVehicleDuplicateRegistrationListWidget registrationPg = LicMgrVehicleDuplicateRegistrationListWidget
		.getInstance();
		
		detailPg.clickRegistration();
		ajax.waitLoading();
		registrationPg.waitLoading();
		
		List<String> prdList = registrationPg.getAllRegistrationInstance();
		boolean found = false;
		for(String prd: prdList){
			if ((prd.replaceAll("\\s+", ""))
					.equalsIgnoreCase(vehicle.registration.product
							.replaceAll("\\s+", ""))){
				found = true;
				break;
			}
		}
		
		if(!found){
			throw new ErrorOnPageException("Get Vehicle Registration List for Duplicate failed!! Cannot find "+vehicle.registration.product+" on the list page.");
			//Get Vehicle Registration List for Duplicate failed.
		}
	}
	
	private void processDuplicationToOrderCartPg() {
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		LicMgrVehicleDuplicateRegistrationListWidget registrationPg = LicMgrVehicleDuplicateRegistrationListWidget
		.getInstance();
		
		registrationPg.selectRegistrationProduct(vehicle.registration.product);
		registrationPg.clickOK();
		ajax.waitLoading();
		ormsOrderCartPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		loginfFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginfFnm.password = TestProperty.getProperty("orms.fnm.pw");
		loginfFnm.contract = "MS Contract";
		loginfFnm.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "VehOrd"+Integer.toString((int)(Math.random()*100000))+"T"+Integer.toString((int)(Math.random()*100000)); //random value hull ID
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "2012";
		vehicle.feet = "2";  //this value should be equal to 'AddVehicleProduct.datapool', record 26
		vehicle.inches = "2";//this value should be equal to 'AddVehicleProduct.datapool', record 26
		vehicle.hullMaterial = "Other";
		vehicle.boatUse = "Other";//this value should be equal to 'AddVehicleProduct.datapool', record 26
		vehicle.propulsion = "Other";
		vehicle.fuelType = "Other";
		vehicle.typeOfBoat = "Other";
		//this value should be equal to 'AddVehicleProduct.datapool', record 26
		//there are two blank spaces between CD(V03) and name(ProcessFeeAdjustments)
		vehicle.registration.product = "V03 - ProcessFeeAdjustments";

		cust[0] = new Customer();
		cust[0].lName = "TEST-RAFee6";
		cust[0].fName = "QA-RAFee6";
		cust[0].dateOfBirth = "Jun 01 1980";
	
		pay.payType = "Cash";
		TOTAL_PRICE = "$20.00";//Duplicate fee: (State Fee+Vendor Fee+Transaction Fee)=(5+10+5)=20// refer to 'AddPricing.datapool', record 145
	}
}