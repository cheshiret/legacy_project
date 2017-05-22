package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.supplier.inventory.add;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**  
 * @Description: Add a new POS supplier to verify the error message.
 * @Preconditions:  assign AddPOSSupplier feature to role.
 * @SPEC:  Add POS supplier
 * @Task#: Auto-972.
 * @author jwang8  
 * @Date  Apr 19, 2012    
 */
public class VerifyErrorMessage extends InventoryManagerTestCase{
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
	
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoPosSupplierSearchPageFromTopMenu();
		//First add a POS supplier for data prepare to next verify point.
		im.addPosSupplier(supplier);
		//verify the error message supplier name + the first 5 characters of the ordering Zip/Postal is not unique.
		String toReturn = im.addPosSupplier(supplier);
		runningResult &= this.verifyErrorMessage(errorMsg_nameAndZipNotUnique, toReturn);
		
		//Verify the error message missing supplier name has not been specified.
		supplier.name = "";
		toReturn = im.addPosSupplier(supplier);
	    runningResult &= this.verifyErrorMessage(errorMsg_missingName, toReturn);
		
	    //verify the error message Fax has been specified and contains a character other than a number.
		supplier.name = "Add" + DataBaseFunctions.getEmailSequence();
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
		supplier.orderAddress.state = "Alabama";
		
		//Verify the error message without ordering zip.
		supplier.orderAddress.zip = "";
		toReturn = im.addPosSupplier(supplier);
		runningResult &= this.verifyErrorMessage(errorMsg_missageOrderingZip, toReturn);
		supplier.orderAddress.zip = "23456";
		
		//Verify the error message without ordering address.
		supplier.orderAddress.address = "";
		toReturn = im.addPosSupplier(supplier);
		runningResult &= this.verifyErrorMessage(errorMsg_missingOrderingAddress, toReturn);
		//Need to check DEFECT-34308 if POS supplier can add success without nothing attribute can be selected.
		if(!runningResult){
			throw new ErrorOnPageException("Verfiy add supplier message error");
		}
		im.logoutInvManager();
	}

	
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/AutoWarehouse";
		
		supplier.location = "All Agencies";
		supplier.name = "Add" + DataBaseFunctions.getEmailSequence();
		supplier.description = "AutoTest";
		supplier.orderAddress.address = "ShanXi";
		supplier.orderAddress.city = "ShangLuo";
		supplier.orderAddress.state = "Alabama";
		supplier.orderAddress.zip = "12345";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
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
		supplier.status = OrmsConstants.ACTIVE_STATUS;
		
	}
	/**
	 * Verify error message.
	 * @param expectedMsg - expected message.
	 * @param actualMsg- actual message.
	 * @return
	 */
	private boolean verifyErrorMessage(String expectedMsg,String actualMsg){
		logger.info("Verify the display warning message are corrected");
		if(!actualMsg.matches(expectedMsg)){
			logger.error("Actual message doesn't match the expected. Actual message is :" + actualMsg + ", but the expected is :" + expectedMsg);
		    return  false;
		}
		logger.info("Error message - '" + actualMsg + "' is displayed correctly.");
		return true;
	}

}
