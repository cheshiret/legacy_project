package com.activenetwork.qa.awo.testcases.abstractcases;

import java.io.IOException;
import java.util.Properties;

import com.activenetwork.qa.awo.util.LogDecrypting;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.StringUtil;

public abstract class MSEquipmentTestCase extends LicenseManagerTestCase {
	private Properties validationCodes=new Properties();
	
	public MSEquipmentTestCase() {
		super();
		initValidationCode();
	}
	
	protected void parseReturnCode(String returnCode, String expectedCode){
		logger.info("Response code: "+returnCode+" - "+getCodeMsg(returnCode));
		
		if(!returnCode.equals(expectedCode)) {
			try {
				LogDecrypting.process(env);
			} catch (IOException e) {
				logger.warn("Failed to decrypt verifone.log due to "+ e,e);
			}
			throw new ActionFailedException("Get return code from server:"+returnCode+ ". The expected return code is "+expectedCode);
		
		}
		
		logger.info("-----The return code from server was correct as "+expectedCode);
	}
	
	protected String convertAbbreviations(String from) {
		String to = null;
		String[] fromStr = new String[]{"United States", "Florida", "New York", "Alabama", "Ohio", "Canada","Ontario","Mississippi"};
		String[] toStr = new String[]{"USA", "FL", "NY", "AL", "OH", "CAN", "ON","MS"};
		
		for(int i = 0; i < fromStr.length; i ++) {
			if(from.equals(fromStr[i])) {
				to = toStr[i];
				break;
			}
		}
		
		if(StringUtil.isEmpty(to))
			for(int i = 0; i < toStr.length; i ++) {
				if(from.equals(toStr[i])) {
					to = fromStr[i];
					break;
				}
			}
		
		if(StringUtil.isEmpty(to)) {
			logger.error("Unhandled abbreviations "+from);
			to = from;
		}
		
		return to;
	}
	
	private void initValidationCode() {	 
		validationCodes.put("0000", "Successful");
		
		// this is the error code and message copied from Orms class: com.reserveamerica.licensing.verifone.common.VerifoneValidationCode
		validationCodes.put("9000", "System error.");
		validationCodes.put("9002", "Unrecognized field code.");
		validationCodes.put("9003", "Field data in the Request does not meet type requirements of Message Version Transaction Field. Version: , Transaction: , Field Code: {2}");
		validationCodes.put("9004", "Field data in the Response does not meet type requirements of Message Version Transaction Field. Version: , Transaction: , Field Code: {2}");
		validationCodes.put("9010", "Product  cannot be purchased. Reason: Business rule  violated.");
		validationCodes.put("9011", " cannot be found in the system.");
		validationCodes.put("9012", "Mandatory field not provided.");
		validationCodes.put("9013", "Invalid value specified for field.");
		validationCodes.put("9014", "Missing field code mapping for property.");

		// From ORMS H&F Interface - Process Message Spec.
		validationCodes.put("0001", "There is no Standard code assigned to the 2 character field code.");
		validationCodes.put("0002", "There is more than one Standard code assigned to the 2 character field code.");
		validationCodes.put("0004", "Cannot determine Project ID or Project ID is not present or Project ID is empty.");
		validationCodes.put("0006", "Register ID is not provided or is empty.");
		validationCodes.put("0007", "Register ID is not a Register ID for the Contract or Register ID is not Active.");
		validationCodes.put("0008", "The hash of the Password provided for the Register and the hash retrieved from VeriCenter for the Register do not match.");
		validationCodes.put("0014", "Cannot determine Transaction Set or Transaction Set is not present or Transaction Set is empty.");
		validationCodes.put("0015", "Transaction Set is not exactly 4 digits or the first digit is 0.");
		validationCodes.put("0016", "Transaction Code does not map to ORMS H&F Interface Request Processing Types.");
		validationCodes.put("0017", "Cannot determine Processing Date/Time or Processing Date/Time is not present or Processing Date/Time is empty.");
		validationCodes.put("0018", "Processing Date/Time is not a valid date/time 24hr stamp (YYYYMMDDHHMMSS).");
		validationCodes.put("0022", "Duplicate request rejected. Duplicate Request Handling indicator for the Front-End system is \"Reject All\".");
		validationCodes.put("0023", "ATP Counter is present and is not an integer or is less than 0.");
		validationCodes.put("0024", "ATP Timestamp is present and is not a valid date/time stamp in 24h clock (YYYYMMDDHHMMSS).");
		validationCodes.put("0025", "Reprint Counter is present and is not an integer or is less than 0; or Reprint Counter is present and Register ID is not provided or is not empty.");
		validationCodes.put("0026", "There is no Short code assigned to the last 3 characters of the field code.");
		validationCodes.put("0028", "There is no transaction to which the Reprint Count can be applied. No error message sent to the Front-end.");
		validationCodes.put("0029", "There is no document to which the Reprint Count can be applied. No error message sent to the Front-end.");
		validationCodes.put("0033", "There is no XML element assigned to field code.");
	  // From ORMS H&F Interface - Validate Message Spec.
		validationCodes.put("0106", "Request does not contain mandatory field or the field value is empty.");
		validationCodes.put("0108", "Response does not contain mandatory field or the field value is empty.");
		validationCodes.put("0109", "Field data in the Request does not meet minimum length requirements of Message Version Transaction Field.");
		validationCodes.put("0110", "Field data in the Response does not meet minimum length requirements of Message Version Transaction Field.");
		validationCodes.put("0111", "Field data in the Request does not meet maximum length requirements of Message Version Transaction Field.");
		validationCodes.put("0112", "Field data in the Response does not meet maximum length requirements of Message Version Transaction Field.");
		  // From ORMS H&F Interface - Process Piggyback of Parameters Request Spec.
		validationCodes.put("0201", "No Group of Parameters for Piggyback is available for the terminal.");
		validationCodes.put("0203", "Next Parameter Value is greater than or equals to the number of parameters in the Group of Parameters for Piggyback.");

		// From ORMS H&F Interface - Process Broadcast Message Request Spec.
		validationCodes.put("0301", "No Broadcast Message is available for the terminal.");
		  // From ORMS H&F Interface - Process Void Request Spec.
		validationCodes.put("0402", "Reason Code is not present or is null/empty.");
		validationCodes.put("0403", "Reason Code is not a Void/Reverse Reason Code");
		validationCodes.put("0404", "Void or Reversal cannot be determined from the Void/Reverse Reason Code.");
		validationCodes.put("0405", "There is no Request Record available for Void/reversal.");
		validationCodes.put("0406", "System determines that the most recent request record (system, time) is in an Error status.");
		validationCodes.put("0407", "The most recent request record (system, time) is in Processing Status.");
		validationCodes.put("0408", "Not all orders were successfully voided/reversed.");
		 
		// Proces Sale Request
		validationCodes.put("0511", "No Vehicle found with the Certificate of Number and Serial Number ID provided.");
		validationCodes.put("0517", "Add Customer Profile failed.");
		validationCodes.put("0520", "Customer Identifier being used in the purchase is not of Identifier Type that is configured as a proof or residency or to be acceptable as a proof of Residency if supported by an additional proof of Residency.");
		validationCodes.put("0521", "Customer Identifier being used in the purchase is acceptable as a proof of Residency if supported by an additional proof of Residency, but Additional Proof of Residency value is not provided.");
		validationCodes.put("0524", "Number of Product Loops is not provided in the request or Number of Product Loops does not match number of items in the List of Products to Purchase.");
		validationCodes.put("0525", "Product Code is not present or is empty.");
		validationCodes.put("0536", "Processing of the cart failed.");
		 
		// Proces Sale Request - Privilege
		validationCodes.put("0603", "The Identifier information failed the validation/verification.");
		validationCodes.put("0604", "One or more Product and License/Fiscal Year combination in the List of Privileges to Purchase where Replacement indicator is set to 'N' is not in the List of Privilege Product Available for Original Sale");
		validationCodes.put("0605", "One or more Product and License/Fiscal year combination in the List of Privileges to Purchase where Replacement Indicator is set to 'Y' is not in the List of Privilege Products Available for Replacement Sale.");
		validationCodes.put("0620", "The sale cannot be processed."); // Third party suspension
		validationCodes.put("0621", "The customer does not have the required Identifier(s) on file to purchase the privilege.");
		validationCodes.put("0654", "Product  cannot be purchased.");

		// Update Customer Info & Identifiers
		validationCodes.put("0701", "First Name, Last Name or DOB does not match existing Customer Profile");
		validationCodes.put("0702", "Edit Customer Profile failed.");
		validationCodes.put("0703", "For at least one Customer Identifier Type in request for which the same customer identifier type exist in the customer profile, the information in the request(Identifier Type ID, Identifier Number, Identifier State and Identifier Contry if applicable) does not match the corresponding information in Customer Profile.");
		validationCodes.put("0705", "No Customer Profiles found for any of the identifiers provided");
		validationCodes.put("0706", "Identifier of Customer # Type are not provided.");
		validationCodes.put("0707", "Identifier Type ID is not present or empty or Identifier Number is not present or empty.");
		validationCodes.put("0708", "Customer Identifier Type ID is not valid .");
		validationCodes.put("0709", "State/Province is required and is not present or empty for .");
		validationCodes.put("0710", "Country of Identifier Issuance is required and not present or empty for .");
		validationCodes.put("0711", "State/Province provided in the request is not associated with the Country provided in the request for ");
		validationCodes.put("0712", "The Date of Birth entered is not valid.");
		validationCodes.put("0713", "The Date of Birth cannot be more than 150 years in the past.");
		validationCodes.put("0714", "Date of Birth is a future date.");
		validationCodes.put("0715", "Attribute ID  is not a valid Customer Attribute ID for the Contract and Customer Class.");
		validationCodes.put("0716", "No Customer Profile found for provided Customer # identifier.");
		validationCodes.put("0717", "Multiple Customer Profiles found matching one or more identifiers in the request.");

		  
		//Update Customer Education
		validationCodes.put("0751", "Education Type ID is not present or empty or Education Number is not present or empty");
		validationCodes.put("0752", "Education Type ID is not valid.");
		validationCodes.put("0753", "State/Province is required and is not present or empty.");
		validationCodes.put("0754", "Country is required and not present or empty.");
		validationCodes.put("0755", "State/Province is not associated with the specified Country.");
		  
		// Proces Sale Request - Vehicle Registrations
		validationCodes.put("0803", "There is more than one vehicle registration product in the List of Vehicle Registrations Renewals/Duplicates where the Replacement Indicator is set to 'N'.");
		validationCodes.put("0804", "No Valid To Date retrieved for the renewal.");
		validationCodes.put("0805", "No Vehicle Product available for renewal.");
		validationCodes.put("0807", "List of Vehicle Products Available for Renewal has more than one product code applicable to the license/fiscal year from the request.");
		validationCodes.put("0809", "There is more than one vehicle registration products in the the List of Vehicle Registrations Renewals/Duplicates where the Replacement Indicator is set to 'Y'.");
		validationCodes.put("0810", "No Vehicle Product available for duplicate.");
		validationCodes.put("0850", "Customer identifier in request");

		// Process Completion Transaction Request
		validationCodes.put("0901", "Receipt number is not provided or receipt number is not a receipt number of a unique receipt for the contract.");
		validationCodes.put("0902", "Receipt number is not a receipt number of a unique Receipt Status Processing Record for the contract.");
		validationCodes.put("0903", "Receipt Status Processing Record is not in status 'Pending'");
		validationCodes.put("0905", "Void/Reversal is not successful.");
		validationCodes.put("0906", "Base Convenience Fee is neither zero nor a positive decimal number.");
		validationCodes.put("0907", "Total Convenience Fee is neither zero nor a positive decimal number.");
		//  public static final VerifoneString ERROR_0908 = new VerifoneString( 908L, "VERIFONE_0908", "Total Convenience Fee is less than Base Convenience Fee.");
		validationCodes.put("0909", "Payment Amount is not provided or does not match Receipt Amount plus Convenience Fee.");
		validationCodes.put("0910", "There is no applicable Convenience Product with corresponding Transaction Fee Schedule found (Base Convenience Fee or Convenience Fee are not zero).");
		validationCodes.put("0911", "There is more than one applicable Convenience Product with corresponding Transaction Fee Schedule found (Base Convenience Fee or Convenience Fee are not zero).");
		validationCodes.put("0913", "Rate Type of the Transaction Fee Schedule of the Convenience Product determined above is 'Flat' and Base Convenience Fee does not equal to Total Convenience Fee.");
		validationCodes.put("0914", "Rate Type of the Transaction Fee Schedule of the Convenience Product determined above is 'Percent' and Base Convenience Fee does not equal zero.");
		validationCodes.put("0917", "Payment was not processed.");
		  
		// Proces Sale Request - Vehicle Inspections
		validationCodes.put("0952", "Product code  and Fiscal year  combination provided in the request is not in the list determined above.");
		validationCodes.put("0955", "County of the vehicle is not valid county for the state of the contract.");
		 
		// Process EFT Invoice Report Request
		validationCodes.put("0961", "No EFT Invoice is available for the terminal.");
		  
		// Process Supply Order Request
		validationCodes.put("0971", "Default VeriFone Supply Order Kit is not on the list of Supplies Products available.");
		validationCodes.put("0972", "Supplies Product being ordered has reached the Daily Maximum Quantity allowed for the Product for the Sales Location.");
		  
		// Proces Sale Request - POS
		validationCodes.put("0982", "Variable Product Price is less than the sum of Vendor Fee and Transaction Fee in the Pricing Record for Product, License Year, Location Class and Purchase Type Original.");
		  
		// Proces Sale Request - Subscriptions
		validationCodes.put("0985", "One of the following is provided and one not: Subscriber's First Name and Last Name");		  
		  
	}
	
	public String getCodeMsg(String code) {
		if(StringUtil.isEmpty(code)) {
			return "";
		}
		try {
			return validationCodes.getProperty(code);
		} catch(Exception e) {
			logger.warn("Failed to get msg for code#"+code+" due to "+e.getMessage());
			return "unknown";
		}
	}
}
