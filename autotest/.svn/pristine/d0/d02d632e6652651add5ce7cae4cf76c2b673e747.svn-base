/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.HashMap;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.TestData;

/**
 * @Description:this data collection is used for license manger 'add\edit print
 *                   document'
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date May 16, 2011
 */
public class LicMgrDocumentInfo extends TestData {
	
	public String id = "";
	
	/**Product Code*/
	public String prodCode = "";
	
	public String prodName = "";
	
	/**location name**/
	public String location= "";
	
	/**Product Type:Vehicle or Privilege*/
	public String prodType= "";

	public String status = "Active";

	public String printOrd = "";

	public String docTepl = "";//Document Template

	public String licYearFrom = "";

	public String licYearTo = "";

	public String effectiveFromDate = "";

	public String effectiveToDate = "";
	
	public String printFromDate = "";
	
	public String printToDate = "";

	public String fulfillMethod = "";

	public String equipType = "";
	
	public String[] equipTypes = new String[]{};
	
	public String purchaseType="";

	public String[] purchaseTypes = new String[]{};
	
	public String species="";
	
	public String huntSeason="";
	
	public String[] configVariables=new String[]{};
	
	public String createUser="";
	
	public String createLoc="";
	
	public String createDate="";
	
	public String lastUpdateUser="";
	
	public String lastUpdateLoc="";
	
	public String lastUpdateDate="";
	
	public String harvestDocument = "";
	
	public String copyFromYear = "";
	
	public String newLicneseYear = "";
	
	public String numOfYearsToAdd = "";
	
	public static final Map<String,Integer> purchaseTypesValueInDb = new HashMap<String,Integer>();
	
	public Map<String,Integer> fulfillmentMethods = new HashMap<String,Integer>();
	
	public LicMgrDocumentInfo(){
		purchaseTypesValueInDb.put("Original", 1);
		purchaseTypesValueInDb.put("Replacement", 2);
		purchaseTypesValueInDb.put("Transfer", 3);
		purchaseTypesValueInDb.put("Renewal", 4);
		purchaseTypesValueInDb.put("Corrected", 6);
	}
}
