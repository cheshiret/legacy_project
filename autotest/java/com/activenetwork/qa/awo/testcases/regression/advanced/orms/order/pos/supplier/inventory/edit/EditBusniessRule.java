package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.supplier.inventory.edit;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**  
 * @Description: Eidt a new POS supplier.
 * @Preconditions:  assign AddPOSSupplier feature to role.
 * @SPEC:  Edit POS supplier
 * @Task#: Auto-972.
 * @author jwang8  
 * @Date  Apr 24, 2012    
 */

public class EditBusniessRule extends InventoryManagerTestCase{
	private InvMgrPOSSupplierDetailsPage supplierDetailPg = InvMgrPOSSupplierDetailsPage.getInstance();
	private PosSupplier supplier = new PosSupplier();
	private String switchContract = "MS Contract";
	private String switchLocation = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
	private ArrayList<String> paymentTerms = new ArrayList<String>();
	private ArrayList<String> paymentMethod = new ArrayList<String>();
	private ArrayList<String> shippingMethod = new ArrayList<String>();
	
	public void execute() {
		//verify the business rule in warehouse location.
		im.loginInventoryManager(login);
		im.gotoPosSupplierSearchPageFromTopMenu();
		supplier.id = im.addPosSupplier(supplier);
		im.gotoPOSSupplierDetailsPage(supplier.id);
		this.verifyEditBusniessruleInWarehouse();
		im.switchToContract(switchContract, switchLocation);
		
		//verify the business rule in contract location.
		im.gotoPosSupplierSearchPageFromTopMenu();
		supplier.name = "Edit" + DataBaseFunctions.getEmailSequence();
		supplier.id = im.addPosSupplier(supplier);
		im.gotoPOSSupplierDetailsPage(supplier.id);
		this.verifyPosSupplierAttribute(paymentTerms, paymentMethod, shippingMethod);
		this.verifyEditBusinessRuleInContract();
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/AutoWarehouse";
		
		supplier.name = "Edit" + DataBaseFunctions.getEmailSequence();
		supplier.description = "AutoTest";
		supplier.orderAddress.address = "ShanXi";
		supplier.orderAddress.city = "ShangLuo";
		supplier.orderAddress.state = "Alabama";
		supplier.orderAddress.zip = "12345";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
		supplier.paymentAddress.address = "JiangSu";
		supplier.paymentAddress.city = "HangZhou";
		supplier.paymentAddress.state = "Alabama";
		supplier.paymentAddress.zip = "23456";
		supplier.paymentAddress.country = "United States";
		supplier.primPoc.lastName = "primLast";
		supplier.primPoc.firstName = "primFirst";
		supplier.primPoc.phone = "4088456789";
		supplier.primPoc.fax = "01236985";
		supplier.primPoc.email = "prim@sina.com";
		supplier.otherPoc.lastName = "otherLast";
		supplier.otherPoc.firstName = "otherFirst";
		supplier.otherPoc.phone = "4088748596";
		supplier.otherPoc.fax = "02174856";
		supplier.otherPoc.email = "other@sina.com";
		supplier.paymentTerms = "Due on Receipt";
		supplier.paymentMethod = "Cash";
		supplier.shippingMethod = "UPS";
		
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
	}
	/**
	 * verify edit business rule in ware house location.
	 */
	public void verifyEditBusniessruleInWarehouse(){
		if(!supplierDetailPg.checkEidtSupplierBusniessRuleDisabled()){
			throw new ErrorOnPageException("In warehouse POS supplier should be unedited");
		}else{
			logger.info("Edit POS supplier busniess rule success");
		}
	}
	/**
	 * verify edit business in contract level.
	 */
	public void verifyEditBusinessRuleInContract(){
		if(!supplierDetailPg.checkEidtSupplierBusniessRuleAbled()){
			throw new ErrorOnPageException("In warehouse POS supplier should be edit");
		}else{
			logger.info("Edit POS supplier busniess rule success");
		}
	}
	
	/**
	 * verify Pos supplier attribute.
	 * @param payTermsList
	 * @param payMethodList
	 * @param shippingMethodList
	 */
	private void verifyPosSupplierAttribute(ArrayList<String> payTermsList,ArrayList<String> payMethodList,ArrayList<String> shippingMethodList){
		boolean pass = true;
		InvMgrPOSSupplierDetailsPage supplierDetailPg = InvMgrPOSSupplierDetailsPage.getInstance();
		pass &= supplierDetailPg.checkPaymentTermsList(payTermsList);
		pass &= supplierDetailPg.checkPaymentMethodList(payMethodList);
		pass &= supplierDetailPg.checkShippingMethodList(shippingMethodList);
		if(!pass){
			throw new ErrorOnPageException("Verfiy Pos supplier attribute  error");
		}else{
			logger.info("erfiy Pos supplier attribute success");
		}
	}
}
