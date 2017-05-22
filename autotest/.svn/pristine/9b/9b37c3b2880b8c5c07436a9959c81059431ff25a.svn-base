package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.purchaseorder;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:Log in to a warehouse location, verify the drop down list.
 * @Preconditions:Need a warehouse location.(warehouse name is AutoWarehouse)
 * feature 'SearchWarehouse' should be assigned.
 * feature 'AddWarehouse' should be assigned.
 * feature 'SearchPOSProductSetup' should be assigned.
 * feature 'SearchPOSProductInventory' should be assigned.
 * feature 'ReconcilePOSProductPhysicalInventory' should be assigned.
 * feature 'SearchPOSPurchaseOrders' should be assigned.
 * feature 'SearchPOSSupplier' should be assigned.
 * feature 'POSProductPrintBarcode' should be assigned.
 * @SPEC:step 5 of TC036511
 * @Task#:Auto-1110
 * 
 * @author nding1
 * @Date  Jun 27, 2012
 */
public class LoginWarehouseLoc extends InventoryManagerTestCase {
	private List<String> expectList = new ArrayList<String>();
    public void execute(){
    	// login inventory manager
    	im.loginInventoryManager(login);

		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		List<String> featureList = topMenu.getTopDropDownListElements();
    	this.verifyFeatureList(featureList);
    	im.logoutInvManager();
    }
    
    public void wrapParameters(Object[] args) {
    	login.url = AwoUtil.getOrmsURL(env);
 	    login.contract = "MS Contract";
 	    login.location="Administrator/AutoWarehouse";// IMPORTANT:check point, don't change this data.

    	expectList.add("Warehouse Details");
    	expectList.add("POS Product Setup");// IMPORTANT:feature 'SearchPOSProductSetup' should be assigned.
    	expectList.add("POS Inventory Management");// IMPORTANT:feature 'SearchPOSProductInventory' should be assigned.
    	expectList.add("POS Physical Inventory Reconciliation");// IMPORTANT:feature 'ReconcilePOSProductPhysicalInventory' should be assigned.
    	expectList.add("POS Purchase Order");// IMPORTANT:feature 'SearchPOSPurchaseOrders' should be assigned.
    	expectList.add("POS Supplier Setup");// IMPORTANT:feature 'SearchPOSSupplier' should be assigned.
    	expectList.add("POS Product Print Barcode");// IMPORTANT:feature 'POSProductPrintBarcode' should be assigned.
    }
    
    /**
     * Verify options in the drop down list is correct or not.
     * @param featureList
     * @return
     */
    private void verifyFeatureList(List<String> featureList){
    	boolean result = true;
 
   		expectList.remove(expectList.size()-1);

    	if(expectList.size() != featureList.size()){
    		throw new ErrorOnPageException("The drop down list should contains "+expectList.size()+" options, but there are "+featureList.size()+" options.");
    	}
    	
    	for(int i=0; i<expectList.size(); i++){
    		if(!featureList.contains(expectList.get(i))){
    			result &= false;
    			logger.error("---The drop down list should contains "+expectList.get(i)+", but actual element is "+featureList.get(i));
    		}
    	}
    	if(!result){
    		throw new ErrorOnPageException("---Not all of the check points are passed.");
    	}
    }
}
