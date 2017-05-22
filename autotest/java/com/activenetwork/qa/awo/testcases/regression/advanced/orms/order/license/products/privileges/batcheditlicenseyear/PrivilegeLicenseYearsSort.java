package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.batcheditlicenseyear;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrBatchEditLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * 1. Check Privilege License Years columns
 * 2. Check Privilege product's category sort
 * 3. Check Privilege License Years records sort
 * @Preconditions:
 * 1. Have two Display Categories(BELYDisplayCategory1 and bELYDisplayCategory2) via configuration
 * 2. Have one Display sub-Category(BELYDisplaySubCategory) via configuration
 * 3.Privilege
 * 3.1 Code(a1B), Name(BatchEditLicenseYearTest1), Display Category(BELYDisplayCategory1) and Display Sub-Category (BELYDisplaySubCategory)
 * 3.1.1 Active License Year: locationClass = "01 - MDWFP Headquarters"
 * 3.1.2 Active License Year: locationClass = "04 - Commercial Agent";
 * 3.1.3 Active License Year: locationClass = "All";
 * 3.2 Code(B1a), Name(BatchEditLicenseYearTest2), Display Category(BELYDisplayCategory1) and Display Sub-Category (BELYDisplaySubCategory)
 *       Active License Year: locationClass = "All";
 * 3.3 Code(clD), Name(BatchEditLicenseYearTest3), Display Category(bELYDisplayCategory2) and Display Sub-Category (BELYDisplaySubCategory)
 *       Active License Year: locationClass = "All";
 * @SPEC: Edit License Year for Privileges in Batch
 * @Task#: AUTO-726
 * 
 * @author SWang
 * @Date  Aug 23, 2011
 */
public class PrivilegeLicenseYearsSort extends LicenseManagerTestCase{
	private LicMgrBatchEditLicenseYearPage batchEditLicenseYearPg = LicMgrBatchEditLicenseYearPage.getInstance();
	private String[] privilegeLicenseYearColumns;
	//Privilege parameters
	private PrivilegeInfo privilege1 = new PrivilegeInfo();
	private PrivilegeInfo privilege2 = new PrivilegeInfo();
//	private PrivilegeInfo privilege3 = new PrivilegeInfo();
	private List<PrivilegeInfo> privilegeList = new ArrayList<PrivilegeInfo>();
	//License year parameters
	private LicenseYear ly1 = new LicenseYear();
	private LicenseYear ly2 = new LicenseYear();
	private LicenseYear ly3 = new LicenseYear();
	private List<LicenseYear> lyList = new ArrayList<LicenseYear>();
	//Sorting parameters
	private List<String> privilegeCodes, privilegeLYLocationClass;

	public void execute() {
		//login in 
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();

		//Check privileges and add active privilege license years
		this.checkAndAddPrivileges();
		this.checkAndAddPrivilegeLicenseYears();

		//Check Privilege License Years columns
		lm.gotoBatchEditLicenseYearPg();
		this.checkPrivilegeLicenseYearColumns();

		//Check Privilege product's category sort
		this.checkPrivilegeProductCategorySort();

		//Check Privilege License Years records sort
		this.checkPrivilegeLicenseYearRecordsSort();

		//Log out 
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//Login information
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";

		//Privileges information
		privilege.code = "B1a";
		privilege.name = "BatchEditLicenseYearTest2";
		privilege.status = "Active";
		privilege.customerClasses = new String[]{"Individual"};
		privilege.displayCategory = "BELYDisplayCategory1";
		privilege.displaySubCategory = "BELYDisplaySubCategory";
//		privilege.reportCategory = "rachel test";
		privilege.displayOrder = "0";

		privilege1.code = "a1B";
		privilege1.name = "BatchEditLicenseYearTest1";
		privilege1.status = "Active";
		privilege1.customerClasses = new String[]{"Individual"};
		privilege1.displayCategory = "BELYDisplayCategory1";
		privilege1.displaySubCategory = "BELYDisplaySubCategory";
//		privilege1.reportCategory = "rachel test";
		privilege1.displayOrder = "0";

		privilege2.code = "c1D";
		privilege2.name = "BatchEditLicenseYearTest3";
		privilege2.status = "Active";
		privilege2.customerClasses = new String[]{"Individual"};
		privilege2.displayCategory = "bELYDisplayCategory2";
		privilege2.displaySubCategory = "BELYDisplaySubCategory";
//		privilege2.reportCategory = "rachel test";
		privilege2.displayOrder = "0";

		privilegeList.add(privilege);
		privilegeList.add(privilege1);
		privilegeList.add(privilege1);
		privilegeList.add(privilege1);
		privilegeList.add(privilege2);

		//Privileges License Years information
		ly1.status = "Active"; 
		ly1.licYear = String.valueOf(DateFunctions.getCurrentYear());
		ly1.locationClass = "All";
		ly1.sellFromDate = DateFunctions.getDateAfterToday(3);
		ly1.sellFromTime = "12:00";
		ly1.sellFromAmPm = "AM";
		ly1.sellToDate = DateFunctions.getDateAfterToday(5);
		ly1.sellToTime = "11:59";
		ly1.sellToAmPm = "PM";

		ly2.status = "Active"; 
		ly2.licYear = String.valueOf(DateFunctions.getCurrentYear());
		ly2.locationClass = "01 - MDWFP Headquarters";
		ly2.sellFromDate = DateFunctions.getDateAfterToday(3);
		ly2.sellFromTime = "12:00";
		ly2.sellFromAmPm = "AM";
		ly2.sellToDate = DateFunctions.getDateAfterToday(5);
		ly2.sellToTime = "11:59";
		ly2.sellToAmPm = "PM";

		ly3.status = "Active"; 
		ly3.licYear = String.valueOf(DateFunctions.getCurrentYear());
		ly3.locationClass = "04 - Commercial Agent";
		ly3.sellFromDate = DateFunctions.getDateAfterToday(3);
		ly3.sellFromTime = "12:00";
		ly3.sellFromAmPm = "AM";
		ly3.sellToDate = DateFunctions.getDateAfterToday(5);
		ly3.sellToTime = "11:59";
		ly3.sellToAmPm = "PM";

		lyList.add(ly1);
		lyList.add(ly1);
		lyList.add(ly2);
		lyList.add(ly3);
		lyList.add(ly1);

		//Columns information
		privilegeLicenseYearColumns = new String[]{"Code","Name","Location Class","Sell From Date/Time","Sell To Date/Time","Valid From Date/Time","Valid To Date/Time"};

		//Sort information
		privilegeCodes= new ArrayList<String>();
		privilegeCodes.add(privilege.code);
		privilegeCodes.add(privilege1.code);
		
		privilegeLYLocationClass = new ArrayList<String>();
		privilegeLYLocationClass.add(ly1.locationClass);
		privilegeLYLocationClass.add(ly2.locationClass);
		privilegeLYLocationClass.add(ly3.locationClass);
	}

	private void checkAndAddPrivileges(){
		LicMgrPrivilegesListPage privilegeListPg = LicMgrPrivilegesListPage.getInstance();
		for(int i=0; i<privilegeList.size(); i++){
			if(!privilegeListPg.verifyPrivilegeProductExist(privilegeList.get(i))){
				lm.addPrivilegeProduct(privilegeList.get(i));
			}
		}
	}

	private void checkAndAddPrivilegeLicenseYears(){
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
		.getInstance();
		for(int i=0; i<privilegeList.size(); i++){
			lm.gotoPrivilegeDetailsPageFromProductListPage(privilegeList.get(i).code);
			lm.gotoPrivilegeLicenseYearPage();
			if(!licenseYearPg.verifyLicenseYearExist(lyList.get(i))){
				lm.addLicenseYear(lyList.get(i));
			}
			lm.gotoPrivilegeListPgFromGivePage(licenseYearPg);
		}
	}

	private void checkPrivilegeLicenseYearColumns(){
		String[] privilegeLicenseYearColumnsFromUI = batchEditLicenseYearPg.getPrivilegeLicenseYearColumns();

		if(privilegeLicenseYearColumnsFromUI.length != privilegeLicenseYearColumns.length){
			throw new ErrorOnDataException("The expected and actual length of privilege license year columns are different.");
		}

		for(int i=0; i<privilegeLicenseYearColumns.length; i++){
			if(!privilegeLicenseYearColumnsFromUI[i].equals(privilegeLicenseYearColumns[i])){
				throw new ErrorOnDataException("The expected and actual value of privilege license year columns are different for conlumn "+privilegeLicenseYearColumns[i]);
			}
		}
	}

	private void checkPrivilegeProductCategorySort(){
		batchEditLicenseYearPg.gotoPrivilegeLicenseYearGridWithYearToEdit(ly1.licYear);
		List<String> categories = batchEditLicenseYearPg.getPrivilegeDisplayCategory();
		boolean displayCategorySort = batchEditLicenseYearPg.verifyBatchEditLicenseYearPageSort(categories, 0);
		if(!displayCategorySort){
			throw new ErrorOnDataException("Display catrgory sorts incorrectly.");
		}

	}

	/**
	 * check Privilege LicenseYear Records Sorting
	 * Two check points:
	 *  -- privilegeCodes sorting
	 *  -- privilegeLYLocationClass sorting
	 */
	private void checkPrivilegeLicenseYearRecordsSort(){
		List<PrivilegeInfo> privilegeListUnderDisplayCategory = 
			batchEditLicenseYearPg.getBatchEditRecordsViaDisplayCategory(privilege.displayCategory);
		//A integer list for verify privilegeCodes sorting
		List<Integer> rowNumList0 = new ArrayList<Integer>();
		//A integer list for verify privilegeLYLocationClass sorting
		List<Integer> rowNumList1= new ArrayList<Integer>();
		
		for(int i=0; i+1<privilegeListUnderDisplayCategory.size(); i++){
			PrivilegeInfo privilege = new PrivilegeInfo();
			if(privilegeCodes.contains(privilege.code)){
				rowNumList0.add(i);
			}
			if(privilegeLYLocationClass.contains(privilege.licYear.locationClass)){
				rowNumList1.add(i);
			}			
		}
		
		if(!verifySorting(rowNumList0)){
			throw new ErrorOnDataException("Privilege License Year privilegeCodes sort incorrectly");
		}else{
			logger.info("Verify License Year privilegeCodes sorting successfully");
		}
		
		if(!verifySorting(rowNumList1)){
			throw new ErrorOnDataException("Privilege License Year privilegeLYLocationClass sort incorrectly");
		}else{
			logger.info("Verify License Year privilegeLYLocationClass sorting successfully");
		}
	}
	
	private boolean verifySorting(List<Integer> rowNumList){
		boolean result = true;
		for(int j = 0; j < rowNumList.size() - 1; j ++) {
			if(rowNumList.get(j) > rowNumList.get(j + 1)) {
				result &= false;
			}		
		}
		return result;
	}
}
