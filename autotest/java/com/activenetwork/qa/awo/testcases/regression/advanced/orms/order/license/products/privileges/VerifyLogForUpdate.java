/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductViewChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Verify Log info by view 'Change history' and verify editing successfully.
 * @Preconditions:
 * @SPEC:Edit Privilege Product.doc
 * @Task#:AUTO-670
 * 
 * @author asun
 * @Date  Aug 3, 2011
 */
public class VerifyLogForUpdate extends LicenseManagerTestCase {

	private ChangeHistory history=new ChangeHistory();
	TimeZone timeZone;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		this.getProductIdAndAuth(history);
		history.newValue=this.changeAuthorizationQuantity();
		history.changeDate=DateFunctions.getToday("E MMM d yyyy hh:mm a", timeZone);
		this.verifyUpdateSuccessfully(privilege.code, history.newValue);
		this.verifyUpdateLog(history);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema=TestProperty.getProperty(env+".db.schema.prefix")+"MS";
		
		privilege.code="A8";
		history.field="Authorization Quantity";
		history.action="Update";
		history.object="Privilege Product";
		history.user="Test-Auto, QA-Auto";
		history.location=login.location.split("/")[1];
		
		timeZone=DataBaseFunctions.getContractTimeZone((TestProperty.getProperty(env+".db.schema.prefix")+"MS"));
	}
	
	public void verifyUpdateSuccessfully(String code,String newAuthorizationQuantity){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		lm.gotoPrivilegeDetailsPageFromProductListPage(code);
		
		logger.info("Verify Update.....");
		String newValueFromPage=privilegeDetailsPg.getAuthorizationQuantity();
		if(!newValueFromPage.equals(newAuthorizationQuantity)){
			throw new ActionFailedException("Update failed,AuthorizationQuantity should be '"+newAuthorizationQuantity+"'");
		}
		logger.info("Update successfully...");
	}
	
	public void verifyUpdateLog(ChangeHistory history){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		LicMgrProductViewChangeHistoryPage historyPage=LicMgrProductViewChangeHistoryPage.getInstance();
		
		logger.info("Verify log....");
		privilegeDetailsPg.clickChangeHistory();
		historyPage.waitLoading();
		List<ChangeHistory> list=historyPage.getChangeHistoryInfo();
		ChangeHistory historyOnPage=null;
		for(ChangeHistory ch : list) {
			if(ch.field.equals(history.field) && ch.oldValue.equals(history.oldValue) && ch.newValue.equals(history.newValue)) {
				historyOnPage = ch;
				break;
			}
		}
		
		if(!historyOnPage.equals(history)){
			throw new ErrorOnPageException("History record is wrong.");
		}
		
		String idOnpage=historyPage.getProductID();
		
		if(!history.prdId.equals(idOnpage)){
			throw new ErrorOnPageException("Id on page is wrong.", history.prdId, idOnpage);
		}
		
	}
	
	/**
	 * get privilege ID from Privilege Details page;
	 * @return
	 */
	public ChangeHistory getProductIdAndAuth(ChangeHistory history){
		
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		
		history.prdId=privilegeDetailsPg.getProductID();
		history.oldValue=privilegeDetailsPg.getAuthorizationQuantity();
		return history;
	}
	/**
	 * Change authorization auantity 
	 * @return
	 */
	public String changeAuthorizationQuantity(){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		LicMgrPrivilegesListPage privilegePage = LicMgrPrivilegesListPage.getInstance();
		String value="";
		String existValue=privilegeDetailsPg.getAuthorizationQuantity();
		List<String> values=privilegeDetailsPg.getAuthorizationQuantityElement();
		
		logger.info("Change authorization auantity..");
		for(String temp:values){
			if(!temp.equals(existValue)){
				value=temp;
				break;
			}
		}
		privilegeDetailsPg.selectAuthorizationQuantity(value);
		privilegeDetailsPg.clickOk();
		privilegePage.waitLoading();
		return value;
	}

}
