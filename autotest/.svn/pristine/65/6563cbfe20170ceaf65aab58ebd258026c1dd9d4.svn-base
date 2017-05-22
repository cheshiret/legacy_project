package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.supplier.inventory.add;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**  
 * @Description: Add a new POS supplier to verify the error message.
 * @Preconditions:  assign AddPOSSupplier feature to role.
 * @SPEC:  Add POS supplier
 *         Supplier Attributes - FOB Point [TC:044993]
 *         Supplier Attributes - Freight Terms [TC:058485]
 *         Supplier Attributes - Shipping Method [TC:058488]
 * @Task#: Auto-972,AUTO-2191 
 * @author jwang8  
 * @Date  Apr 20, 2012    
 */
public class VerifyBusniessRule extends InventoryManagerTestCase {
	
	private List<String> paymentTerms = new ArrayList<String>();
	private List<String> paymentMethod = new ArrayList<String>();
	private List<String> shippingMethod = new ArrayList<String>();
	private List<String> fobPoint = new ArrayList<String>();
	private List<String> freightTerms = new ArrayList<String>();
	private InvMgrPOSSupplierDetailsPage supplierDetailPg = InvMgrPOSSupplierDetailsPage.getInstance();
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoPosSupplierSearchPageFromTopMenu();
		this.gotoAddSupplierDetailPage();
		//Verify the POS supplier attribute.
		//verify payment terms list element info
		List<String> paymentTermsLists = supplierDetailPg.getPaymentTermsList();
		this.attributeInfo("Payment Terms Drop down list elements", paymentTerms, paymentTermsLists);
		
		//verify payment method list element info
		List<String> paymentMethodLists = supplierDetailPg.getPaymentMethodList();
		this.attributeInfo("Payment Method Drop down list elements", paymentMethod, paymentMethodLists);
		
		//verify shipping method list element info
		List<String> shippingMethodLists = supplierDetailPg.getShippingMethodList();
		this.attributeInfo("Payment Method Drop down list elements", shippingMethod, shippingMethodLists);
		
		//verify fob point list element info
		List<String> fobPointLists = supplierDetailPg.getFobPointListElements();
		this.attributeInfo("FOB Point Drop down list elements", fobPoint, fobPointLists);
		
		//verify freight terms list element info
		List<String> FreightTerms = supplierDetailPg.getFreightTermsListElements();
		this.attributeInfo("Freight Terms Point Drop down list elements", freightTerms, FreightTerms);
		im.logoutInvManager();
	}
	
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/AutoWarehouse";
		
		paymentTerms.add("");
		paymentTerms.add("Due on Receipt");
		paymentTerms.add("Pre-Paid");
		paymentTerms.add("Net 10");
		paymentTerms.add("Net 15");
		paymentTerms.add("Net 30");
		paymentTerms.add("Net 60");
		
		shippingMethod.add("");
		shippingMethod.add("UPS");
		shippingMethod.add("Fedex");
		shippingMethod.add("Other");
		shippingMethod.add("Cheapest & Best");
		
		paymentMethod.add("");
		paymentMethod.add("Cash");
		paymentMethod.add("Credit Card");
		paymentMethod.add("Cheque");
		paymentMethod.add("Other");
		
		fobPoint.add("");
		fobPoint.add("Origin");
		fobPoint.add("Destination");
		
		freightTerms.add("");
		freightTerms.add("PPD & ADD");
		freightTerms.add("Free Freight");
	}
	
	private void gotoAddSupplierDetailPage(){
		InvMgrPOSSupplierSearchPage supplierSarchPg = InvMgrPOSSupplierSearchPage.getInstance();
		
		logger.info("Go to add supplier detail page.");
		supplierSarchPg.clickAddSupplier();
		ajax.waitLoading();
		supplierDetailPg.waitLoading();
	}
	
	private void attributeInfo(String attributeName,List<String> expListInfo,List<String> actListInfo){
		boolean pass = MiscFunctions.compareListString(attributeName, expListInfo, actListInfo);
		if(!pass){
			throw new ErrorOnPageException("Verfiy Pos supplier attribute info not correct.");
		}else{
			logger.info("Verfiy Pos supplier attribute info is correct.");
		}
	}
}
