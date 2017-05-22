package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.supplier.inventory.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
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
 * @Date  Apr 23, 2012    
 */
public class EditErrorMessage extends InventoryManagerTestCase{
	private PosSupplier supplier = new PosSupplier();
	private boolean runningResult = true;
	private String errorMsg_missingName = "Supplier Name is required. Please specify the Supplier Name.";//Message1 in spec.
	private String errorMsg_nameAndZipNotUnique = "Supplier Name must be unique within the selected Location Level. Please re-enter the Supplier Name.";//Message2 in spec.
	private String errorMsg_invalidFax = "Primary\\s*POC Fax must be a valid fax number and must only contain numbers, spaces, brackets, dashes or an x \\(to denote extension number\\). Please re-enter the Primary\\s*POC Fax.";//Message5 in spec.
	private String errorMsg_invalidPhone = "Primary\\s*POC Phone must be a valid fax number and must only contain numbers, spaces, brackets, dashes or an x \\(to denote extension number\\). Please re-enter the Primary\\s*POC Phone.";//Message4 in Spec.
	private String errorMsg_missingOrderingAddress = "Ordering Address is a required field, and must be specified.";//Message 12.
	private String errorMsg_missageOrderingCity = "City/Town \\(Ordering Address\\) is a required field, and must be specified.";//Message 13
	private String errorMsg_missageOrderingState = "State/Province \\(Ordering Address\\) is a required field, and must be specified.";//Message 14
	private String errorMsg_missageOrderingZip = "Zip/Postal Code \\(Ordering Address\\) is a required field, and must be specified.";//Message 15
	private String initName = "Edit" + DataBaseFunctions.getEmailSequence();
	
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoPosSupplierSearchPageFromTopMenu();
		//First add a POS supplier for data prepare to next verify point.
		im.addPosSupplier(supplier);
		
		//verify the error message supplier name + the first 5 characters of the ordering Zip/Postal is not unique.
		supplier.name ="Edit" + DataBaseFunctions.getEmailSequence();
		supplier.id = im.addPosSupplier(supplier);
		im.gotoPOSSupplierDetailsPage(supplier.id);
		supplier.name = initName;
		String toReturn = im.addPosSupplier(supplier);
		runningResult &= this.verifyErrorMessage(errorMsg_nameAndZipNotUnique, toReturn);
		
		//Verify the error message missing supplier name has not been specified.
		this.changeSupplierDateForEdit();
		supplier.name = "";
		toReturn = im.addPosSupplier(supplier);
	    runningResult &= this.verifyErrorMessage(errorMsg_missingName, toReturn);
	    
	  //verify the error message Fax has been specified and contains a character other than a number.
		supplier.name = "Edit" + DataBaseFunctions.getEmailSequence();
		supplier.primPoc.fax = "qwert";
		toReturn = im.addPosSupplier(supplier);
		runningResult &= this.verifyErrorMessage(errorMsg_invalidFax, toReturn);
		
		//Verify the error message Primary POC Phone has been specified and contains a character other than a number.
		supplier.primPoc.fax = "01236985";
		supplier.primPoc.phone = "qwert";
		toReturn = im.addPosSupplier(supplier);
		runningResult &= this.verifyErrorMessage(errorMsg_invalidPhone, toReturn);
		
		//Verify the error message without ordering city.
		supplier.primPoc.phone = "4088456789";
		supplier.orderAddress.city = "";
		toReturn = im.addPosSupplier(supplier);
		runningResult &= this.verifyErrorMessage(errorMsg_missageOrderingCity, toReturn);
		
		//Verify the error message without ordering state.
		supplier.orderAddress.city = "ShangLuo";
		supplier.orderAddress.state = "";
		toReturn = im.addPosSupplier(supplier);
		runningResult &= this.verifyErrorMessage(errorMsg_missageOrderingState, toReturn);
		
		//Verify the error message without ordering zip.
		supplier.orderAddress.state = "Alaska";
		supplier.orderAddress.zip = "";
		toReturn = im.addPosSupplier(supplier);
		runningResult &= this.verifyErrorMessage(errorMsg_missageOrderingZip, toReturn);
		
		//Verify the error message without ordering address.
		supplier.orderAddress.zip = "23456";
		supplier.orderAddress.address = "";
		toReturn = im.addPosSupplier(supplier);
		runningResult &= this.verifyErrorMessage(errorMsg_missingOrderingAddress, toReturn);
		
		if(!runningResult){
			throw new ErrorOnPageException("Verfiy add supplier message incorrectly.");
		}else{
			logger.info("All the error messages are correct.");
		}
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		supplier.name =initName;
		supplier.description = "AutoTest";
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
	}
	
	private void changeSupplierDateForEdit(){
		supplier.location = "MSHF";//All Agencies
		supplier.name = "Edit" + DataBaseFunctions.getEmailSequence();
		supplier.description = "EditAutoTest";
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
	}
	
	/**
	 * Verify error message.
	 * @param expectedMsg - expected message.
	 * @param actualMsg- actual message.
	 * @return
	 */
	private boolean verifyErrorMessage(String expectedMsg,String actualMsg){
		boolean pass = true;
		logger.info("Verify the display warning message are corrected");
		if(!actualMsg.matches(expectedMsg)){
			logger.error("Actual message doesn't match the expected. Actual message is :" + actualMsg + ", but the expected is :" + expectedMsg);
		    pass &= false;
		}else{
			logger.info("Error message - '" + actualMsg + "' is displayed correctly.");
		}
		return pass;
	}

}
