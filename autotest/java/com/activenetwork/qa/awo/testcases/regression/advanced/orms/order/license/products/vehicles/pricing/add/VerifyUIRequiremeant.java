/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.pricing.add;

import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddProductPricingWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrEditVehicleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclePricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddProductPricingTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:Verify UI requirement in this Spec
 * @Preconditions:
 * @SPEC:Add Product Pricing.DOC
 * @Task#:Auto-666
 * 
 * @author asun
 * @Date  Aug 1, 2011
 */
public class VerifyUIRequiremeant extends LicMgrAddProductPricingTestCase {
	
	private String[] iTypes;
	private String[] tTypes;
	private String[] rTypes;
//	private VehicleRTI vehicle = new VehicleRTI();//updated by pzhu
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		LicMgrProductPage prodPage =lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		//veridy 'Inspection'
		pricing.prodCode="I7";//Producut Group="Inspection"
		checkAndAddProduct(prodPage);
		lm.gotoProductPricingPageFromListPage(pricing.prodType,pricing.prodCode);
		this.VerifyCommonUIRequirement();
		this.verifyUIForVehicleGroup("Inspection", iTypes);
		backToVehicleListPage();
		
		//verify 'Registration'
		pricing.prodCode="RE1";
		checkAndAddProduct(prodPage);
		lm.gotoProductPricingPageFromListPage(pricing.prodType,pricing.prodCode);
		this.verifyUIForVehicleGroup("Registration", rTypes);
		backToVehicleListPage();
		
		//verify 'Title'
		pricing.prodCode="T01";
		checkAndAddProduct(prodPage);
		lm.gotoProductPricingPageFromListPage(pricing.prodType,pricing.prodCode);
		this.verifyUIForVehicleGroup("Title", tTypes);
		
		lm.logOutLicenseManager();
	}
	
	protected void newVehicleProduct(){
		vehicle.setPrdCode(pricing.prodCode);
		vehicle.setPrdName("Vehicle" + pricing.prodCode);
		if(pricing.prodCode.equals("RE1")){
			vehicle.setPrdGroup("Registration");
			vehicle.setVehicleType("Dealer");
			vehicle.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
			vehicle.setValidMonths("1");
			vehicle.setAdvanceRenewalDays("1");
			vehicle.setLateRenewal("1");
			vehicle.setLateRenewUnit("Day");
		}else if(pricing.prodCode.equals("T01")){
			vehicle.setPrdGroup("Title");
			vehicle.setVehicleType("Boat");
		}else{
			vehicle.setPrdGroup("Inspection");
			vehicle.setVehicleType("Boat");
		}
		
		
		HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
		custClass.put("Individual", true);
		
		vehicle.setCustClass(custClass);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		pricing.prodType="Vehicle";
		
		rTypes=new String[]{"Original","Transfer","Renewal","Duplicate"};
		tTypes=new String[]{"Original","Transfer","Corrected","Duplicate"};
		iTypes=new String[]{"Original"};
		
		this.pricingPage=LicMgrVehiclePricingPage.getInstance();
	}
	
	
	
	public void verifyUIForVehicleGroup(String group,String[] purchaseTypes){
		LicMgrVehiclePricingPage pricingPage=LicMgrVehiclePricingPage.getInstance();
		LicMgrAddProductPricingWidget addWidget=LicMgrAddProductPricingWidget.getInstance();
		
		logger.info("Verify UI when inventory type is 'None'");

		if(!addWidget.exists()){
			pricingPage.clickAddProductPricing();
			addWidget.waitLoading();
		}
		List<String> list=addWidget.getPurchaseTypes();
		
		if(group.equalsIgnoreCase("Inspection")){
			if(list.size()!=1||!list.get(0).equals(purchaseTypes[0])){
				throw new ErrorOnPageException("Product is of Product Group 'Inspection', the only valid option shall be 'Original'.");
			}
		}else{	
			for(String type:purchaseTypes){
				if(!list.contains(type)){
					throw new ErrorOnPageException("Purcahse type '"+type+"' is not found.");
				}
			}
		}
		addWidget.clickCancel();
		pricingPage.waitLoading();
	}
	
	public void backToVehicleListPage(){
		LicMgrEditVehicleDetailsPage page=LicMgrEditVehicleDetailsPage.getInstance();
		LicMgrVehiclesListPage vehicleListPage = LicMgrVehiclesListPage.getInstance();
		
		logger.info("back to vehicle list page from vehicle details page..");
		page.clickCancel();
		vehicleListPage.waitLoading();
	}
}
