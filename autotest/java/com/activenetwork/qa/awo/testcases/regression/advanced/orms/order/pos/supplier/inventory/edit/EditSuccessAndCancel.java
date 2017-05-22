package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.supplier.inventory.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**  
 * @Description: Eidt a new POS supplier.
 * @Preconditions:  assign AddPOSSupplier feature to role.
 * @SPEC:  Edit POS supplier
 *         Edit Supplier Attributes - FOB Point [TC:046252]
 *         Edit Supplier Attributes - Freight Terms [TC:043115]
 *         Edit Description - Website [TC:058505]
 *         Edit POS Supplier in Inventory Manager [TC:058716]
 * @Task#: Auto-972,AUTO-2191 
 * @author jwang8  
 * @Date  Apr 23, 2012    
 */
public class EditSuccessAndCancel extends InventoryManagerTestCase{

	private PosSupplier supplier = new PosSupplier();
	private InvMgrPOSSupplierSearchPage posSupplierSearchPg = InvMgrPOSSupplierSearchPage.getInstance();
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoPosSupplierSearchPageFromTopMenu();
		//Add POS supplier success and verify the records exist.
		supplier.id = im.addPosSupplier(supplier);
		im.gotoPOSSupplierDetailsPage(supplier.id);
		this.changeSupplierDateForEdit();
		supplier.id = im.editPosSupplier(supplier);
		im.gotoPOSSupplierDetailsPage(supplier.id);
		this.verifyEditSupplierInfo(supplier);
		im.gotoPOSSupplierSearchPageFromDetailsPage();
		//Cancel the process of adding POS suppler process and verify the records are not exist.
		supplier.name  = "againEdit" + DataBaseFunctions.getEmailSequence();
		im.gotoPOSSupplierDetailsPage(supplier.id);
		im.editPosSupplierCancel(supplier);
		posSupplierSearchPg.searchPosSupplierByName(supplier.name);
		posSupplierSearchPg.verifyPosSupplierNotExist(supplier);
		im.logoutInvManager();
	}
	
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		supplier.name = "AddSupp" + DataBaseFunctions.getEmailSequence();
		supplier.description = "AutoTest";
		supplier.webSite = "www.sbccorp.com";
		supplier.orderAddress.address = "ShanXi";
		supplier.orderAddress.city = "ShangLuo";
		supplier.orderAddress.state = "Alabama";
		supplier.orderAddress.zip = "12345";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
		supplier.paymentAddress.address = "ShanXi";
		supplier.paymentAddress.city = "ShangLuo";
		supplier.paymentAddress.state = "Alabama";
		supplier.paymentAddress.zip = "12345";
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
		supplier.fobPoint = "Destination";
		supplier.freightTerms = "Free Freight";
	}
	/**
	 * change data param.
	 */
	private void changeSupplierDateForEdit(){
		supplier.location = "MSHF";//All Agencies
		supplier.name = "EditSupp" + DataBaseFunctions.getEmailSequence();
		supplier.description = "EditAutoTest";
		supplier.webSite = "www.souhu.com";
		supplier.orderAddress.address = "EidtShanXi";
		supplier.orderAddress.city = "EditShangLuo";
		supplier.orderAddress.state = "Alaska";
		supplier.orderAddress.zip = "52631";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
		supplier.paymentAddress.address = "EidtShanXi";
		supplier.paymentAddress.city = "EditShangLuo";
		supplier.paymentAddress.state = "Alaska";
		supplier.paymentAddress.zip = "52631";
		supplier.paymentAddress.country = "United States";
		supplier.primPoc.lastName = "EditprimLast";
		supplier.primPoc.firstName = "EditprimFirst";
		supplier.primPoc.phone = "4088526314";
		supplier.primPoc.fax = "01252856";
		supplier.primPoc.email = "Editprim@sina.com";
		supplier.otherPoc.lastName = "EditotherLast";
		supplier.otherPoc.firstName = "EditotherFirst";
		supplier.otherPoc.phone = "4088859632";
		supplier.otherPoc.fax = "02163985";
		supplier.otherPoc.email = "Editother@sina.com";
		supplier.paymentTerms = "Pre-Paid";
		supplier.paymentMethod = "Other";
		supplier.shippingMethod = "Other";
		supplier.fobPoint = "Origin";
		supplier.freightTerms = "PPD & ADD";
	}
	
	/**
	 * verify the added supplier info success.
	 * @param supplier - the info of supplier.
	 */
	private void verifyEditSupplierInfo(PosSupplier supplier){
		InvMgrPOSSupplierDetailsPage posSupplierDetailPg = InvMgrPOSSupplierDetailsPage.getInstance();
		boolean pass = posSupplierDetailPg.compareSupplierDetailsInfo(supplier);
		if(!pass){
			 throw new ErrorOnPageException("Add pos supplier error");
		}else{
			logger.info("Edit pos supplier success");
		}
	}
}
