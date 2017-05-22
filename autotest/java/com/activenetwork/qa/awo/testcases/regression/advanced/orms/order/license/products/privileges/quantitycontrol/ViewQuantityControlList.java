/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.quantitycontrol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: this test case needs a available privilege. and no other cases use the privilege for testing Quantity control
 * @Preconditions:
 * @SPEC:View Privilege location Class Settings List.DOC
 * @Task#:AUTO-675
 * 
 * @author asun
 * @Date  Aug 9, 2011
 */
public class ViewQuantityControlList extends LicenseManagerTestCase {

	private String[] expectedColumns;
	private QuantityControlInfo[] quantityControls=new QuantityControlInfo[4];
	private List<String> ids=new ArrayList<String>();
	
	@Override
	public void execute() {
		
		lm.deleteQuantityControlsFromDB(privilege.code, schema);
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeQuantityControlPgFromTopMenue(privilege.code);
		
		//prepare data for testing.
		ids=lm.addPrivilegeQuantityControl(quantityControls);
		lm.deactivatePrivilegeQuantityControl(ids.get(ids.size()-1));
		//do verification
		lm.gotoPrivilegeQuantityControlPgFromTopMenue(privilege.code);
		this.verifyQuantityControlAssociatedWithCurrectPrivilege(ids, privilege.code, schema);
		this.verifyAllRecordsStatus("Active");
	    this.verifyColumns(expectedColumns);
	    this.verifySortingByLocationClass();
	    this.verifyStatusAndLocClassDDList();
	    String[] idsArray=ids.toArray(new String[ids.size()]);
	    
	    this.verifySearch(false, "Active", "",idsArray[0],idsArray[1],idsArray[2]);
	    this.verifySearch(false, "Active", "All",idsArray[0]);
	    this.verifySearch(false, "Inactive", quantityControls[3].locationClass,idsArray[3]);
	    this.verifySearch(true, null, null,idsArray[0],idsArray[1],idsArray[2]);
	    lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema=TestProperty.getProperty(env+".db.schema.prefix")+"MS";
		
		privilege.code="A1";
		//Add 'Min Quantity Per Transaction' for PCR2998;
		expectedColumns=new String[]{"ID","Status","Location Class","Multi-Sales Allowance","Min Quantity Per Transaction","Max Quantity Per Transaction","Max Allowed","Replacement Max Allowed"};
	    
		QuantityControlInfo q1=new QuantityControlInfo();
		q1.locationClass="All";
		q1.multiSalesAllowance="Yes (Within Same License Year only)";
		q1.maxQuantityPerTran="2";
		q1.maxAllowed="3";
		q1.replacementMaxAllowed="9";
		this.quantityControls[0]=q1;
		
		QuantityControlInfo q2=new QuantityControlInfo();
		q2.locationClass="01 - MDWFP Headquarters";
		q2.multiSalesAllowance="No Multiple Allowed";
		q2.replacementMaxAllowed="9";
		this.quantityControls[1]=q2;
		
		QuantityControlInfo q3=new QuantityControlInfo();
		q3.locationClass="03 - Lakes Offices";
		q3.multiSalesAllowance="No Multiple Allowed";
		q3.replacementMaxAllowed="9";
		this.quantityControls[2]=q3;
		
		QuantityControlInfo q4=new QuantityControlInfo();
		q4.locationClass="02 - MDWFP District Office";
		q4.multiSalesAllowance="No Multiple Allowed";
		q4.replacementMaxAllowed="9";
		this.quantityControls[3]=q4;
	}
	
	/**
	 * verify search
	 * @param currentOnly
	 * @param status
	 * @param locClass
	 */
	public void verifySearch(boolean currentOnly,String status,String locClass,String... expectedIds){
		LicMgrPrivilegeQuantityControlPage quantityPage=LicMgrPrivilegeQuantityControlPage.getInstance();
		
		quantityPage.search(currentOnly, status, locClass);
		
		List<String> idsFromPage=quantityPage.getQuantityControlIDs();
		Collections.sort(idsFromPage);
		
		if(idsFromPage.size()!=expectedIds.length){
			throw new ErrorOnPageException("Verify Search failed");
		}
		for(int i=0;i<expectedIds.length;i++){
			if(!idsFromPage.contains(expectedIds[i])){
				throw new ErrorOnPageException("Verify Search failed");
			}
		}
	
	}
	
	/**
	 * Verify status and location class drop down list 
	 */
	public void verifyStatusAndLocClassDDList(){
		LicMgrPrivilegeQuantityControlPage quantityPage=LicMgrPrivilegeQuantityControlPage.getInstance();
		
		logger.info("Verify status and location class drop down list.....");
		
		quantityPage.uncheckShowCurrentRecordsOnly();
		List<String> locClass=quantityPage.getLocationClassValuesForDropDownList();
		List<String> locClassCodes=new ArrayList<String>();
		
		if(!locClass.contains("")){
			throw new ErrorOnPageException("the location class drop-down list should contain a blank option.");
		}
		locClass.remove("");
	
		//Verify All is the first option other than blank option
		if(!locClass.get(0).equals("All")){
			throw new ErrorOnPageException("'All' should be the first option.");
		}
	    //Verify location class sorting
		locClass.remove("All");
		for(String option:locClass){
			locClassCodes.add(option.split("-")[0].trim());
		}
		Collections.sort(locClass);
		for(int i=0;i<locClass.size();i++){
			if(!locClass.get(i).matches(locClassCodes.get(i)+" - .*")){
				throw new ErrorOnPageException("Verify sorting failed.");
			}
		}
		//verify status 
		String defaultStatus=quantityPage.getStatus();
		
		if(!defaultStatus.trim().equals("Active")){
			throw new ErrorOnPageException();
		}
		
		List<String> status=quantityPage.getStatusForDDList();
		if(!status.contains("All")||!status.contains("Active")||!status.contains("Inactive")){
			throw new ErrorOnPageException("Status should contains ['All','Active','Inactive']");
		}
		
	}
	
	/**
	 * Verify quantity control associated with privilege Id
	 * @param ids
	 * @param code
	 * @param schema
	 */
	public void verifyQuantityControlAssociatedWithCurrectPrivilege(List<String> ids,String code,String schema){
		String sqlForLoc="Select distinct(PRIVILEGE_PRD_ID) from P_PRD_LOC_CLASS_CONFIG where ID in ("+ids.toString().replace("[", "").replace("]", "")+")";
		String sqlForPRD="select PRD_ID from P_PRD where PRD_CD='"+code+"' and PRODUCT_CAT_ID=10";
		
		logger.info("Verify quantity control associated with privilege Id");
		AwoDatabase db =AwoDatabase.getInstance();
		db.resetSchema(schema);
		List<String> listFromLoc=db.executeQuery(sqlForLoc, "PRIVILEGE_PRD_ID");
		List<String> listFromPRD=db.executeQuery(sqlForPRD, "PRD_ID");
		if(listFromLoc.size()!=1||listFromPRD.size()!=1){
			throw new ErrorOnDataException("there should be only one id");
		}
		
		if(!listFromLoc.get(0).equals(listFromPRD.get(0))){
			throw new ErrorOnDataException("the privilege id associated with those quantity controls should be "+listFromPRD.get(0));
		}
		logger.info("Success:Verify quantity control associated with privilege Id");
	}
	
	public void verifyAllRecordsStatus(String statusOption){
		LicMgrPrivilegeQuantityControlPage quantityPage=LicMgrPrivilegeQuantityControlPage.getInstance();
	    
		List<String> list=quantityPage.getColumnValues("Status");
		
		if(statusOption.equalsIgnoreCase("All")&&(!list.contains("Active")||!list.contains("Inactive"))){
			throw new ErrorOnPageException("All active and inactive records should be displayed.");
		}else if(statusOption.equalsIgnoreCase("Active")&&list.contains("Inactive")){
			throw new ErrorOnPageException("All records should be 'Active'");
		}else if(statusOption.equalsIgnoreCase("Inactive")&&list.contains("Active")){
			throw new ErrorOnPageException("All records should be 'Inactive'");
		}
	}
	
	
	/**
	 * verify columns 
	 * @param expectedColumns
	 */
	public void verifyColumns(String[] expectedColumns){
		LicMgrPrivilegeQuantityControlPage quantityPage=LicMgrPrivilegeQuantityControlPage.getInstance();
	    List<String> list=quantityPage.getColumnNames();
	    logger.info("Verify columns.....");
	    if(expectedColumns.length!=list.size()){
	    	throw new ErrorOnPageException("There should be "+expectedColumns.length+" columns");
	    }
	    
	    for(int i=0;i<expectedColumns.length;i++){
	    	if(!list.contains(expectedColumns[i])){
	    		throw new ErrorOnPageException("Can't find the column:"+expectedColumns[i]);
	    	}
	    }
	    logger.info("verify successfully.");
	} 

	/**
	 * verify sorting by location calss
	 */
	public void verifySortingByLocationClass(){
		LicMgrPrivilegeQuantityControlPage quantityPage=LicMgrPrivilegeQuantityControlPage.getInstance();
	    
		List<String> locationClasses=quantityPage.getAllLocationClass();
		List<String> locCalssForSort=new ArrayList<String>();
		
		logger.info("Verify sorting by location class...");
		
		if(!locationClasses.get(0).equals("All")){
			throw new ErrorOnPageException("the record which location class is 'All' should be the first record");
		}
		
		locationClasses.remove("All");
		for(String value:locationClasses){
			locCalssForSort.add(value.split("-")[0].trim());
		}
		
		Collections.sort(locCalssForSort);
		for(int i=0;i<locCalssForSort.size();i++){
			if(!locationClasses.get(i).matches(locCalssForSort.get(i)+".*")){
				throw new ErrorOnPageException("sorting failed in record");
			}
		}
		
//		quantityPage.clickLocationClass();
//		locationClasses=quantityPage.getAllLocationClass();
//		int size=locCalssForSort.size();
//		if(locationClasses.get(size-1).equals("All")){
//			throw new ErrorOnPageException("the record which location class is 'All' should be the last record");
//		}
//		for(int i=0;i<locCalssForSort.size();i++){
//			if(!locCalssForSort.get(i).equals(locationClasses.get(size-1))){
//				throw new ErrorOnPageException("sorting failed in record");
//			}
//		}
		logger.info("Verify sorting successfully");
	}
}
