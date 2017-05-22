/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.uppercaseoutput.off;


import java.io.File;
import java.util.Calendar;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 1. Purchase a vehicle.
 * 				 2. Go to receipt detail page. Request H&F Confirmation Letter.
 * 				 3. Check confirmation letter, following part should be upper case.
 * 				 #Title: user name
 *				 #Sales Location: location name/address
 *				 #Privilege orders: user name/address 
 * 				
 * @Preconditions: 1. In Admin Manager,assign rule's feature "RequestConfirmationLetter" to rule"HF HQ Role"
 * 					2. set 'TRUE' for UpperCase: <update X_PROP set value='false' where name like 'UppercaseOutput'>;
 * 					3. A vehicle product: V03 - ProcessFeeAdjustments
 * 					4. Customer: QA-RAFee2/TEST-RAFee2 
 * 				
 * @SPEC:  Report body provides Upper case Output configuration
 * @Task#: AUTO-1147(Auto-1272 TC:024171)
 * 
 * @author pzhu(Edit by jasmine)
 * @Date  July 23, 2012
 * 
 */
public class VehicleOrder extends LicenseManagerTestCase {


	private LoginInfo loginfFnm = new LoginInfo();
	private static final boolean NON_UPPER_CASE = false;

	private String pdfFileFolder;
	private String fullFileName;
	private String receiptNum;
	private String MDWFP;
	private BoatInfo vehicle = new BoatInfo();
	private String vehicleInfo ;
	private String totalFee;
	
	private static final String[] REPORT_LINE_STRING = {
		"QA-RAFee2 TEST-RAFee2",
		"Xian",
		"York AL 36925",
		"WAL-MART",
		"WAL-MART, Belden, Mississippi, 38826",
		"TEST-RAFee2, QA-RAFee2 \\(MDWFP\\)", //this line will be update in the code.
		"Xian",
		"York, AL, 36925"
		};
	private static final String SUCCESS_RESULT="11111111"; // means 8 strings of 'REPORT_LINE_STRING' are all found. 
	
	@Override
	public void execute() {
		
		//Set upper case configuration to 'true'.
		lm.updateUpperCaseConfigInDB(schema,NON_UPPER_CASE); 
		
		
		lm.loginLicenseManager(login);
	
			
		
		// make a register vehicle order.
		lm.registerVehicleToOrderCart(cust, vehicle);
		pay.belongOrderNum  = lm.processOrderCart(pay, false);
		if(pay.belongOrderNum.contains(" ")){
			pay.belongOrderNum = pay.belongOrderNum.split(" ")[0];
		}
		
		//pay.belongOrderNum = "9-15387";
		logger.info("New register vehicle order number is "+pay.belongOrderNum);
		
		//		
		lm.gotoVehicleOrderDetailPage(pay.belongOrderNum);
		this.MDWFP = this.getCustomerMDWFP();
		REPORT_LINE_STRING[5] = REPORT_LINE_STRING[5].replaceAll("MDWFP", this.MDWFP);//String got from report is : 'TEST-RAFEE2, QA-RAFEE2 \(814617841\)'
		
		this.receiptNum = lm.gotoReceiptPageFromVehicleOrder();
		logger.info("receiptNum is "+receiptNum);
		
		this.fullFileName = lm.getReceiptConfirmationLetterOnline(pdfFileFolder, receiptNum);
		//Add by Jasmine.
		this.verifyVehicleSaleOrderInfo(vehicleInfo, pay.belongOrderNum, this.receiptNum, totalFee);
		
		this.checkUpperCaseInConfirmationLetter();
		
		//this.verifyVehicleSaleOrderInfo(vehicleInfo, pay.belongOrderNum, this.receiptNum, totalFee);

		
		//log out LM
		lm.logOutLicenseManager();
	}
	/**
	 * 
	 */
	private String getCustomerMDWFP() {
		LicMgrVehicleOrderDetailsPage vehicleOrdDetailsPg = LicMgrVehicleOrderDetailsPage
		.getInstance();
		return vehicleOrdDetailsPg.getVehicleCustomerNum();
		
	}
/**
 * parser PDF.
 * @return
 */
   private List<String> parserPDF(){
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
		return  lines;
   }

	private void checkUpperCaseInConfirmationLetter() {

		
		List<String> lines = this.parserPDF();
		if(null == lines || lines.size()<1){
			throw new ErrorOnPageException("No PDF file content exist");
		}
		/* check following part in report:
		 * 
			QA-RAFee2 TEST-RAFee2
			Xian
			York AL 36925
			
			
			WAL-MART
			WAL-MART, Belden, Mississippi, 38826
			
			TEST-RAFee2, QA-RAFee2 (814617841)
			Xian
			York, AL, 36925
			
		 */
		int result = 0;
		/**
		 * we use first 7 bit of variable 'result'.
		 * if all comparing pass(7 values of array REPORT_LINE_STRING), 'result' should be '01111111'.
		 * if only the comparing with REPORT_LINE_STRING[0], not pass. 'result' should be '01111110'.
		 * if only the comparing with REPORT_LINE_STRING[1], not pass. 'result' should be '01111101'. 
		 * if only the comparing with REPORT_LINE_STRING[6], not pass. 'result' should be '01011111'.
		 * */
		for(int i=0; i<REPORT_LINE_STRING.length; i++)//7 strings
		{
			for(String line: lines)
			{
				if(line.equals(REPORT_LINE_STRING[i]))
				{
					result |= 1<<i;
					break;
				}
			}
		}	
		
		
		logger.info("Check result is -->"+Integer.toBinaryString(result)+", 0 means cannot find the coresponding string.");
	
		String notFound="";
		
		if(!Integer.toBinaryString(result).equals(SUCCESS_RESULT))
		{
			for(int j=0; j<REPORT_LINE_STRING.length; j++)
			{
				if(((result>>j)&(0x01))==0)
				{
					notFound += "["+REPORT_LINE_STRING[j]+"]";
				}
			}
						
			throw new ErrorOnDataException("Cannot find following strings on report: "+notFound);
		}else{
			logger.info("Found all strings in report body.");
		}


	}
	/**
	 * check the confirm letter info. Add by Jasmine.
	 * @param lists
	 * @param info
	 * @return
	 */
	private boolean checkInfo(List<String> lists,String info){
		boolean isEqual = false;
		for(String line: lists){
			if(line.toUpperCase().equals(info.toUpperCase())){
				isEqual = true;
				logger.info(""+info+" exist in confirm letter");
				break;
			}
		}
		return isEqual;
	}
	
	private void verifyVehicleSaleOrderInfo(String vehicleInfo,String orderNum,String receiptNum,String totalFee){
		List<String> array = this.parserPDF();
		boolean isPass = true;
		isPass &= this.checkInfo(array, orderNum);
		isPass &= this.checkInfo(array, receiptNum);
		if(!totalFee.contains(".")){
			totalFee = "$" + totalFee+".00";
		}
		isPass &= this.checkInfo(array, totalFee);
		isPass &= this.checkInfo(array, vehicleInfo);
		if(!isPass){
			throw new ErrorOnPageException("Privilege Confirm letter error");
		}
	}
	
	/*
	 * get last date of give month of year.
	 * yearNum - the number of current e.g this year is 2012.
	 * month -  the number of current e.g OCT is 9.
	 */
	private String getLastDateOfMonth(int yearNum,int monthNum){
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.YEAR, yearNum);
		calendar.set(calendar.MONTH, monthNum);
		int day = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
		String date = DateFunctions.formatDate(String.valueOf(calendar.get(calendar.MONTH)+1)+"/"+String.valueOf(day)+"/"+String.valueOf(calendar.get(calendar.YEAR)),"MMM d yyyy");
		return date;
	}
	/**
	 * get last date of Next month.
	 * @param year
	 * @return
	 */
	private String getLastDateOfNextMonth(int year){
		Calendar calendar = Calendar.getInstance();
		int nextMonth = calendar.get(calendar.MONTH)+1;
		String lastDay = this.getLastDateOfMonth(year, nextMonth);
		return lastDay;
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


		
		cust.lName = "TEST-RAFee2";
		cust.fName = "QA-RAFee2";
		cust.dateOfBirth = "Jun 01 1980";
		
		pay.payType = "Cash";
		
		//path to save ticket printed PDF file
		this.pdfFileFolder = logger.getLogPath();
		vehicleInfo = "\\("+lm.getFiscalYear(schema)+"\\) "+vehicle.registration.product+" \\(Original\\); Valid: "+DateFunctions.getToday("MMM d yyyy")+" to "+"\\("+this.getLastDateOfNextMonth(DateFunctions.getCurrentYear())+"\\)";
		String vehicleCode = vehicle.registration.product.split("-")[0].trim();
		totalFee = "20"/*lm.getCustPriceByPrdCodeAndNotToAllState(schema, vehicleCode, ORIGINAL_PURCHASE_TYPE_ID)*/;
				
	}
}

