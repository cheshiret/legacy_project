/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.uppercaseoutput.off;



import java.io.File;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 1. Purchase a privilege(3 copys), reverse it, then purchase a boat, populate a voucher.
 * 				 2. Go to receipt detail page. Request H&F Confirmation Letter.
 * 				 3. Check confirmation letter, following part should be upper case.
 * 				 #Title: user name
 *				 #Sales Location: location name/address
 *				 #Privilege orders: user name/address 
 * 				
 * @Preconditions:1. In Admin Manager,assign rule's feature "RequestConfirmationLetter" to rule"HF HQ Role"
 * 					2. set 'TRUE' for UpperCase: <update X_PROP set value='false' where name like 'UppercaseOutput'>; 
 * 					3. A privilege product: 703-ProcessFeeAdjustments
 * 					4. A vehicle product: V03 - ProcessFeeAdjustments
 * 					5. Customer: QA-RAFee4/TEST-RAFee4
 * 				
 * @SPEC:  Report body provides Upper case Output configuration
 * @Task#: AUTO-1147
 * 
 * @author pzhu
 * @Date  July 25, 2012
 * 
 */
public class VoucherEarnedOrder extends LicenseManagerTestCase {


	private LoginInfo loginfFnm = new LoginInfo();
	
	private String pdfFileFolder;
	private String fullFileName;
	private String receiptNum;
	private Voucher voucher;
	private BoatInfo vehicle = new BoatInfo();
	private static final boolean NON_UPPER_CASE = false;
	private static String VOUCHER_EARNED = "VOUCHER EARNED";
	private static String VOUCHER_USER = "TEST-RAFee4, QA-RAFee4";
	

	@Override
	public void execute() {
		//Set upper case configuration to 'true'.
		lm.updateUpperCaseConfigInDB(schema,NON_UPPER_CASE); 
				
		
		lm.loginLicenseManager(login);
		
		// make a new privilege order.
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		pay.belongOrderNum =lm.processOrderCart(pay);
		logger.info("OrderNum is "+pay.belongOrderNum);
		
		
		//reverse this order, and purchase a vehicle. and get a voucher from this reverse order.
		//If we do not purchase any thing in this reverse order, we cannot get receipt.
		lm.gotoPrivilegeOrderDetailPage(pay.belongOrderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.addVehicleInOrderCartPage(cust, vehicle);
		this.voucher = lm.processOrderAndGetVoucherInfo();
		
	
		//Go to receipt page, get confirmation letter.
		lm.gotoPrivilegeOrderDetailPage(voucher.fromOrderNum);
		this.receiptNum = lm.gotoReceiptPageFromPrivilegeOrder();
		logger.info("receiptNum is "+receiptNum);
		
		this.fullFileName = lm.getReceiptConfirmationLetterOnline(pdfFileFolder, receiptNum);

		this.checkUpperCaseInConfirmationLetter();
		
		//log out LM
		lm.logOutLicenseManager();

	}
		
	
	/**
	 * 
	 */
	private void checkUpperCaseInConfirmationLetter() {
		logger.info("Enter into checkUpperCaseInConfirmationLetter--->>");
		
		File file = new File(this.fullFileName);
		
		if(!file.exists()) {
			logger.info("Can't find confirmation letter PDF file - " + this.fullFileName);
			throw new ItemNotFoundException("Can't find confirmation PDF file - " + this.fullFileName);
		}
		String content = PDFParser.retrievePDFContent(file);
		logger.info("Start of PDF content------>>");
		logger.info(content);
		logger.info("End of PDF content------<<");
		
		List<String> lines = PDFParser.getPDFContentInOrder(file.getAbsolutePath());
		/* check following part in report:
		 * 
			VOUCHER EARNED
			137707819 TEST-RAFee4, QA-RAFee4 ($40.00)
			
		 */
		boolean result = false;
		String line;
			
		for(int i=0; i<lines.size();i++)
		{
			line = lines.get(i);
			if(line.equals(VOUCHER_EARNED)) //go to 'VOUCHER EARNED' part.
			{
				//get the next text line below 'VOUCHER EARNED' 
				List<String> tmp= PDFParser.getPDFContentInRow(file.getAbsolutePath(), lines.get(i+1));
				if(tmp.get(0).contains(VOUCHER_USER))
				{
					logger.info("Found "+VOUCHER_USER+" in the text line "+tmp.get(0));
					result = true;
					break;
				}

			}
		}
		
		if(result)
		{	
			logger.info("Check "+VOUCHER_EARNED+" part of report succeed.");
		}else{
			throw new ErrorOnDataException("Cannot find VOUCHER USER info in the section "+VOUCHER_EARNED);	
		}
		
					
	}


	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";

		loginfFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginfFnm.password = TestProperty.getProperty("orms.fnm.pw");
		loginfFnm.contract = "MS Contract";
		loginfFnm.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilege.purchasingName = "703-ProcessFeeAdjustments";
		privilege.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		privilege.qty = "3";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "AUTO-1141";

		vehicle.hullIdSerialNum = "UPPERCASE"+Integer.toString((int)(Math.random()*100000))+"T"+Integer.toString((int)(Math.random()*100000)); //random value hull ID
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

		
		
		cust.lName = "TEST-RAFee4";
		cust.fName = "QA-RAFee4";
		cust.dateOfBirth = "Jun 01 1980";
		
		pay.payType = "Cash";
		
		//path to save ticket printed PDF file
		this.pdfFileFolder = logger.getLogPath();

	}
}

