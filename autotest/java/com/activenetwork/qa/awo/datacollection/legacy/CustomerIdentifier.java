/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.util.AutomationLogger;

/**
 * @ScriptName CustomerIdentifier.java
 * @Date:Jan 12, 2011
 * @Description:this data collection is created for License Customer  
 * @author asun
 */
public class CustomerIdentifier implements Comparable<CustomerIdentifier>  {

	/** id in DB */
	public String id="";

	public String status="";

	public String verifyStatus=""; //Failed, Pending, Pending, Verified

	public String identifierType="";
	
	public String identifierTypeID="";
	
	public String identifierSecureID="";

	public String identifierNum="";

	public String state="";

	public List<String> states = new ArrayList<String>();
	
	public String country="";
	
	public List<String> countries = new ArrayList<String>();

	public String creationApp="";

	public String createDate="";

	public String createUser="";

	public String deletedInd="";

	public boolean isDeclarRequired = true; // For Web

	public String stateCode="";
	public String identTypeFullNm = ""; // Identifier Type name in DB
	public String identTypeShortNm = ""; // Identifier Type Short Name in DB
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}



	@Override
	public boolean equals(Object obj) {
		AutomationLogger logger = AutomationLogger.getInstance();
		
		if(!(obj instanceof CustomerIdentifier)){
			return false;
		}

		CustomerIdentifier custIdentifier=(CustomerIdentifier)obj;
		
		logger.info("Compare this (id=" + this.id + ") with the one id=" + custIdentifier.id);
		
		if(!this.id.equalsIgnoreCase(custIdentifier.id)){
			logger.error("Expect ID:" +custIdentifier.id);
			logger.error("Actual ID:" +this.id);
			return false;
		}
		if(!this.status.equalsIgnoreCase(custIdentifier.status)){
			logger.error("Expect Status:" +custIdentifier.status);
			logger.error("Actual Status:" +this.status);
			return false;
		}

		if(this.identifierType.equals("Customer #")){
			this.identifierType = "MDWFP #";
			if(!this.identifierType.equalsIgnoreCase(custIdentifier.identifierType)){
				logger.error("Expect Identifier Type:" +custIdentifier.identifierType);
				logger.error("Actual Identifier Type:" +this.identifierType);
				return false;
			}
		}

		if(!this.identifierNum.equalsIgnoreCase(custIdentifier.identifierNum)){
			logger.error("Expect Identifier Num:" +custIdentifier.identifierNum);
			logger.error("Actual Identifier Num:" +this.identifierNum);
			return false;
		}
		if(!this.state.equalsIgnoreCase(custIdentifier.state)){
			logger.error("Expect State:" +custIdentifier.state);
			logger.error("Actual State:" +this.state);
			return false;
		}
		if(!this.country.equalsIgnoreCase(custIdentifier.country)){
			logger.error("Expect Country:" +custIdentifier.country);
			logger.error("Actual Country:" +this.country);
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(CustomerIdentifier obj) {
		if(this.equals(obj)){
			return 0;
		}

		if(this.hashCode()>obj.hashCode()){
			return 1;
		}
		return -1;
	}


}
