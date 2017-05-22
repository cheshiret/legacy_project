/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.certification;


import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:Verify error message for no result, verify columns ,verify certifications display
 * @Preconditions:
 * @SPEC:View Customer Certification.doc
 * @Task#:AUTO-711
 * 
 * @author asun
 * @Date  Aug 16, 2011
 */
public class ViewCustomerCertifications extends LicenseManagerTestCase {
	private String msg="No Certifications were found for the selected Customer";
	private String[] columns;
	private Certification c0=new Certification();
	private Certification c1=new Certification();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, cust.identifier.identifierType, cust.identifier.identifierNum);
		lm.removeAllCustomerCertifications(true);
	    this.verifyWarningMessage(msg);
	    this.verifyColumnNames(columns);
	    
	    //verify certifications list
	    lm.addCustomerCertification(c0,c1);
	    lm.manageCertificationForCustomer("Deactivate", null, c1);
	    c1.status="InActive";
        this.verifyViewCertificationsList(c0,c1);
	    lm.logOutLicenseManager();
	}
	
	/**
	 * Verify View Certifications
	 * @param certifications
	 */
	public void verifyViewCertificationsList(Certification... certifications){
		LicMgrCustomerCertificationPage certificationPage = LicMgrCustomerCertificationPage.getInstance();
		boolean isExisting=true;
		
		logger.info("Verify View Certifications...");
		for(Certification c:certifications) {
			isExisting=certificationPage.checkCertificationRecordExists(c);
		    if(!isExisting){
		    	throw new ErrorOnPageException("the certification which num="+c.num+" is disaplayed wrongly");
		    }
		}
		logger.info("Verify successfully.");
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		cust.customerClass="Individual";
		cust.identifier.identifierType="NON-US DL Number";
		                                
		cust.identifier.identifierNum="AUTO12345";
		
		columns=new String[]{"ID","Status","Certification Type","Certification #","Effective From","Effective To","Creation Date/Time","Creation User"};
	    
		c0.num="Auto000000";
		c0.status="Active";
		c0.type="Trapper Certification";
		c0.effectiveFrom=DateFunctions.getDateAfterToday(-1);
		c0.effectiveTo="";
		
		c1.num="Auto000001";
		c1.status="Active";
		c1.type="Trapper Certification";
		c1.effectiveFrom=DateFunctions.getDateAfterToday(-5);
		c1.effectiveTo=DateFunctions.getDateAfterToday(10);
	}
	
	public void verifyColumnNames(String[] columns){
		LicMgrCustomerCertificationPage certificationPage = LicMgrCustomerCertificationPage.getInstance();
		
		List<String> columnsOnPage=certificationPage.getColumnNames();
		logger.info("Verify column names...");
		if(columnsOnPage.size()!=columns.length){
			throw new ErrorOnPageException("There should be "+columns.length+" columns.");
		}
		
		for(int i=0;i<columns.length;i++){
			if(!columns[i].equals(columnsOnPage.get(i))){
				throw new ErrorOnPageException("Can't find column:"+columns[i]);
			}
		}
		
		logger.info("Verify columns successfully");
	}
	
	/***
	 * verify error message when there is not any certification
	 * @param msg
	 */
	public void verifyWarningMessage(String msg){
		LicMgrCustomerCertificationPage certificationPage = LicMgrCustomerCertificationPage.getInstance();
		
		certificationPage.clickCertificationsTab();
		ajax.waitLoading();
		String msgOnpage=certificationPage.getWarnMsg();
		logger.info("verify warning message.");
		if(msgOnpage==null || msgOnpage.length()<1){
			throw new ErrorOnPageException("no message found !");
		}
		
		if(!msgOnpage.equals(msg)){
			throw new ErrorOnPageException("The Right message should be '"+msg+"'");
		}
		logger.info("Verify message successfully.");
	}
}
