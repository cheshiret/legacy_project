/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductAgentAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrProductAssignedStoresWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrProductStoreWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductStoreAssignmentPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyAgentAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductStoreAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrProductInactiveAssignmentsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreProductAssignmentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleProductStoreAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:Basic regression cases to cover assign and unassign store to product...
 * @Preconditions:
 * @SPEC:Assign Store to Product.doc/Unassign Store to Product.doc
 * @Task#:AUTO-754
 * @Defects:
 * Notice: consumable_code = 'P01' is production data so do not need to insert into datapool.
 * 
 * @author asun
 * @Date  Sep 27, 2011
 */
public class AssignAndUnassignStoreToProduct extends LicenseManagerTestCase {

	private StoreInfo store=new StoreInfo();
	private String vehicle_code;
	private String consumable_code;
	private String supply_code;
	private String privilege_code;
	private LicMgrPrivilegeProductStoreAssignmentsPage privilegeAssignPage=LicMgrPrivilegeProductStoreAssignmentsPage.getInstance();
	private LicMgrVehicleProductStoreAssignmentsPage vehicleAssignPage=LicMgrVehicleProductStoreAssignmentsPage.getInstance();
	private LicMgrConsumableProductStoreAssignmentPage consumableAssignPage=LicMgrConsumableProductStoreAssignmentPage.getInstance();
	private LicMgrSupplyAgentAssignmentsPage supplyAssignPage=LicMgrSupplyAgentAssignmentsPage.getInstance();
	private String assignmentId;
	
	@Override
	public void execute() {
		this.inactivateAllProductForStore(store.storeName,schema);
		
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		
		//verify assign store to product sussfully
		lm.gotoStoreProductAssignmentPage(store.storeName,store.belongedVendorName);
		//assign store to privilege
		lm.assginOrUnAssignProductToStoreInStoreDetailsPage(true,privilege_code,"Privilege");
		this.verifyAssignOrUnassignInAssignmentPage(true, privilege_code,"Privilege",null);
		//assign store to Vehicle
		lm.assginOrUnAssignProductToStoreInStoreDetailsPage(true,vehicle_code,"Vehicle");
		this.verifyAssignOrUnassignInAssignmentPage(true, vehicle_code,"Vehicle",null);
		//assign store to consumable
		lm.assginOrUnAssignProductToStoreInStoreDetailsPage(true,consumable_code,"Consumable");
		this.verifyAssignOrUnassignInAssignmentPage(true, consumable_code,"Consumable",null);
		//assign store to Supply
		lm.assginOrUnAssignProductToStoreInStoreDetailsPage(true,supply_code,"Supply");
		this.verifyAssignOrUnassignInAssignmentPage(true, supply_code,"Supply",null);
		//Verify in Product Details page
		this.verifyAssignOrUnassginInProductDetailsPage(true, privilege_code, "Privilege", store, privilegeAssignPage);	
		this.verifyAssignOrUnassginInProductDetailsPage(true, vehicle_code, "Vehicle", store, vehicleAssignPage);
		this.verifyAssignOrUnassginInProductDetailsPage(true, consumable_code, "Consumable", store, consumableAssignPage);
		this.verifyAssignOrUnassginInProductDetailsPage(true, supply_code, "Supply", store, supplyAssignPage);
		
		
		//verify unassign store to product sussfully
		lm.gotoStoreProductAssignmentPage(store.storeName,store.belongedVendorName);
		//unassign store to privilege
		assignmentId=lm.assginOrUnAssignProductToStoreInStoreDetailsPage(false,privilege_code,"Privilege");
		this.verifyAssignOrUnassignInAssignmentPage(false, privilege_code,"Privilege",assignmentId);
		//unassign store to Vehicle
		assignmentId=lm.assginOrUnAssignProductToStoreInStoreDetailsPage(false,vehicle_code,"Vehicle");
		this.verifyAssignOrUnassignInAssignmentPage(false, vehicle_code,"Vehicle",assignmentId);
		//assign store to consumable
		assignmentId=lm.assginOrUnAssignProductToStoreInStoreDetailsPage(false,consumable_code,"Consumable");
		this.verifyAssignOrUnassignInAssignmentPage(false, consumable_code,"Consumable",assignmentId);
		//assign store to Supply
		assignmentId=lm.assginOrUnAssignProductToStoreInStoreDetailsPage(false,supply_code,"Supply");
		this.verifyAssignOrUnassignInAssignmentPage(false, supply_code,"Supply",assignmentId);
		//Verify in Product Details page
		this.verifyAssignOrUnassginInProductDetailsPage(false, privilege_code, "Privilege", store, privilegeAssignPage);	
		this.verifyAssignOrUnassginInProductDetailsPage(false, vehicle_code, "Vehicle", store, vehicleAssignPage);
		this.verifyAssignOrUnassginInProductDetailsPage(false, consumable_code, "Consumable", store, consumableAssignPage);
		this.verifyAssignOrUnassginInProductDetailsPage(false, supply_code, "Supply", store, supplyAssignPage);
		
		
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
	 
		schema=DataBaseFunctions.getSchemaName("MS", env);
		store.belongedVendorName="AUTOBASIC";
		store.storeName="AlexTest";
		store.locationClass="15-Active Phone Sales";
		
		privilege_code="A1";
		vehicle_code="I01";
		consumable_code="P01";
		supply_code="001";
	}
	
	public void verifyAssignOrUnassignInAssignmentPage(boolean isAssgin,String prodCode,String prodType,String assignmentId){
		LicMgrStoreProductAssignmentsPage storeAssignPage=LicMgrStoreProductAssignmentsPage.getInstance();
		LicMgrProductInactiveAssignmentsWidget inactiveAssignWidget=LicMgrProductInactiveAssignmentsWidget.getInstance();
		String exceptionMsg="The store named "+store.storeName+" should be "+(isAssgin?"Assgined":"Unassgin");
	    
		logger.info("Verify "+(isAssgin?"Assign":"Unassgin")+" Succussfully");
		if(!isAssgin) {
			storeAssignPage.uncheckShowProductsAssignedToStoresWithTheSameLocationClassOnly();
			storeAssignPage.clickGo();
			ajax.waitLoading();
			storeAssignPage.waitLoading();
		}
		boolean assigned=storeAssignPage.isAssigned(prodCode);
		if(assigned!=isAssgin){
			throw new ErrorOnPageException(exceptionMsg);
		}
		
		if(!isAssgin){
			storeAssignPage.clickViewInactiveAssignments();
			ajax.waitLoading();
			inactiveAssignWidget.waitLoading();
			inactiveAssignWidget.selectProducts(prodType);
			inactiveAssignWidget.clickGo();
			ajax.waitLoading();
			if(!inactiveAssignWidget.checkAssignmentExist(assignmentId)){
				throw new ErrorOnPageException("the assignment which assignment id is"+assignmentId+" should be displayed");
			}
			inactiveAssignWidget.clickOK();
		}

		logger.info("Verify successfully.");
	}

	/**
	 * @param privilegeCode
	 * @param string
	 */
	private void verifyAssignOrUnassginInProductDetailsPage(boolean isAssgin,String prodCode, String prodType,StoreInfo store,ILicMgrProductAgentAssignmentsPage page) {
		LicMgrProductAssignedStoresWidget agentWidget=LicMgrProductAssignedStoresWidget.getInstance();
		LicMgrProductStoreWidget storeWidget=LicMgrProductStoreWidget.getInstance();
		String exceptionMsg="The store named "+store.storeName+" should be "+(isAssgin?"Assgined":"Unassgin");
		
		lm.gotoProductSearchListPageFromTopMenu(prodType);
	    lm.gotoProductDetailsPageFromProductListPage(prodType, prodCode, "Agent Assignments");
	 
	    String num=page.getNumberOfAgentsActivelyAssignedByLocationName(store.locationClass);
	    if(isAssgin||Integer.parseInt(num)!=0&&!isAssgin){
	       page.clickNumberOfAgentsActivelyAssignedByLocationName(store.locationClass);
	       agentWidget.waitLoading();
		   boolean isExisting=agentWidget.checkAgentExisting(store.storeName,store.belongedVendorName);
		   if(isExisting!=isAssgin){
		      throw new ErrorOnPageException(exceptionMsg);
		   }
		   agentWidget.clickOK();
		   ajax.waitLoading();
	    }
	   
	    
	    //Verify on LicMgrProductStoreWidget
	    page.clickNumberOfAgentsInLocationClassByLocationName(store.locationClass);
	    ajax.waitLoading();
	    storeWidget.waitLoading();
	    boolean isAssginedToLocClass=storeWidget.isStoreAssigned(store.storeName,store.belongedVendorName);
	    if(isAssginedToLocClass!=isAssgin){
	    	throw new ErrorOnPageException(exceptionMsg);
	    }
	    storeWidget.clickOK();
	    ajax.waitLoading();
	}

	
	
	public void inactivateAllProductForStore(String storeName,String schema){
		String sql="update P_PRD_LOC set ACTIVE_IND=0 where LOC_ID in (select id from D_LOC where NAME='"+storeName+"')";
	    
		logger.info("inactive all product assignment for store which storename="+storeName);
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		db.executeUpdate(sql);
	}

}
