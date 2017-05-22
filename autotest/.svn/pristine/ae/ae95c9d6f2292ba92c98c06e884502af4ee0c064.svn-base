package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddPriPrintDocumentFunction;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddPriPrintDocument extends SetupCase{
//	private String schema;
//	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrDocumentInfo document = new LicMgrDocumentInfo();
	private Object[] args = new Object[3];
	private AddPriPrintDocumentFunction func = new AddPriPrintDocumentFunction();
	
	public void executeSetup() {
		func.execute(args);
	}

	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_print_doc";
	}
	
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");
		
//		schema = TestProperty.getProperty(env + ".db.schema.prefix") + args[0].toString().split("Contract")[0].trim();
		
		document.prodCode = datasFromDB.get("privilegeCode");
		document.prodName = datasFromDB.get("privilegeName");
		document.prodType = datasFromDB.get("productType");
		document.docTepl = datasFromDB.get("docTepl");
		document.equipTypes = this.splitTextByComma(datasFromDB.get("equipTypes"));
		document.purchaseTypes = this.splitTextByComma(datasFromDB.get("purchaseTypes"));
		
		document.licYearFrom = this.getLicenseYearValue("licYearFrom", "NumOfYearsAfterCurrentFrom", true);
//		document.licYearFrom = datasFromDB.get("licYearFrom");
//		if(StringUtil.isEmpty(document.licYearFrom)) {
//			document.licYearFrom = String.valueOf(Integer.parseInt(lm.getFiscalYear(schema)) - 1);//last fiscal year	
//		}
		
		document.licYearTo = this.getLicenseYearValue("licYearTo", "NumOfYearsAfterCurrentTo", false);
//		document.licYearTo = datasFromDB.get("licYearTo");
//		if(StringUtil.isEmpty(document.licYearTo)) {
//			document.licYearTo = String.valueOf(Integer.parseInt(lm.getFiscalYear(schema)) + 1);//last fiscal year
//		} 
		
		document.effectiveFromDate = datasFromDB.get("effectiveFromDate");
		if(StringUtil.isEmpty(document.effectiveFromDate)) {
			document.effectiveFromDate = DateFunctions.formatDate(DateFunctions.getToday(),"EE MMM d yyyy");
		}
		document.effectiveToDate = datasFromDB.get("effectiveToDate");
		if(StringUtil.isEmpty(document.effectiveToDate)) {
			document.effectiveToDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(300),"EE MMM d yyyy");
		}
		document.createDate = datasFromDB.get("createDate");
		if(StringUtil.isEmpty(document.createDate)) {
			document.createDate = DateFunctions.formatDate(DateFunctions.getToday(),"EE MMM d yyyy");
		}
		document.createUser = DataBaseFunctions.getLoginUserName(TestProperty.getProperty("orms.fm.user"));
		document.printOrd = datasFromDB.get("printOrder");
		document.fulfillMethod = datasFromDB.get("fulFillMethod");
		document.configVariables = this.splitTextByComma(datasFromDB.get("configvariables"));
		document.harvestDocument = datasFromDB.get("harvestDocument");
		if (StringUtil.isEmpty(document.harvestDocument)) {
			document.harvestDocument = "No";
		} else if (document.harvestDocument.equalsIgnoreCase("Yes")) {
			document.species = datasFromDB.get("species");
			document.huntSeason = datasFromDB.get("huntSeason");
		}
		document.purchaseType = "";
		document.equipType = "";
		document.printFromDate = datasFromDB.get("printFromDate");
		document.printToDate = datasFromDB.get("printToDate");
		args[2] = document;
	}

	private String[] splitTextByComma(String text){
		String[] list;;
		if(text.contains(",")){
			list =  text.split(",", 0);
		}else{
			list = new String[]{text};
		}
		return list;
	}
	
	private String getLicenseYearValue(String licYearKey, String numOfYearsKey, boolean isLicYearFrom) {
		String licYear = datasFromDB.get(licYearKey);	
		String numOfYearsString = datasFromDB.get(numOfYearsKey);
		int numOfYears = StringUtil.isEmpty(numOfYearsString) ? 0 : Integer.parseInt(numOfYearsString);
		if(StringUtil.isEmpty(licYear)) {
			if (StringUtil.isEmpty(numOfYearsString)) {
				licYear = String.valueOf(DateFunctions.getCurrentYear() + (isLicYearFrom ? 0 : 1));//last fiscal year
			} else {
				licYear = String.valueOf(DateFunctions.getCurrentYear() + numOfYears);
			}
		} else if (licYear.equalsIgnoreCase("All")) {
			return licYear;
		} else {
			licYear = String.valueOf(Integer.parseInt(licYear) + numOfYears);	
		}
		return licYear;
	}
}
