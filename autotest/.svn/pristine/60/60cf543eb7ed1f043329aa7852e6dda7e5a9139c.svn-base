package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.StatusReasonInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrSystemConfigurationPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 *
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 *
 * @author qchen
 * @Date  Dec 16, 2011
 */
public class LicMgrVendorStatusReasonMgtConfigurationPage extends LicMgrSystemConfigurationPage {

	private static LicMgrVendorStatusReasonMgtConfigurationPage _instance = null;

	protected  LicMgrVendorStatusReasonMgtConfigurationPage(){}

	public static LicMgrVendorStatusReasonMgtConfigurationPage getInstance(){
		if(null == _instance ){
			_instance = new LicMgrVendorStatusReasonMgtConfigurationPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".id", "vendorStatusReasonList");
	}

	public String getStatusReasonID(String status, String reason){
		IHtmlTable table = this.getTableObject("vendorStatusReasonList");
		String statusReasonID = "";
		for(int i=1; i<table.rowCount(); i++){
			if(table.getCellValue(i, 1).equals(status) && table.getCellValue(i, 2).equals(reason)){
				statusReasonID = table.getCellValue(i, 0);
				break;
			}
		}
		return statusReasonID;
	}

	public List<String> getStatusReasonInfoByID(String id){
		return this.getTableRowValue("vendorStatusReasonList", "ID", id);
	}

	public void verifyVendorStatusReasonInfo(StatusReasonInfo expectStatusReason){
		boolean result = true;
		List<String> actualStatusReasonInfo = this.getStatusReasonInfoByID(expectStatusReason.id);

		logger.info("Verify expected vendor status reason info(ID#=" + expectStatusReason.id + ") with actual value.");
		if(!actualStatusReasonInfo.get(0).equals(expectStatusReason.id)){
			result &= false;
			logger.error("Expect status reason id should be " + expectStatusReason.id
					+ ", but actually is " + actualStatusReasonInfo.get(0));
		}

		if(!actualStatusReasonInfo.get(1).equals(expectStatusReason.status)){
			result &= false;
			logger.error("Expect status reason status should be " + expectStatusReason.status
					+ ", but actually is " + actualStatusReasonInfo.get(1));
		}

		if(!actualStatusReasonInfo.get(2).equals(expectStatusReason.reason)){
			result &= false;
			logger.error("Expect reason should be " + expectStatusReason.reason
					+ ", but actually is " + actualStatusReasonInfo.get(2));
		}

		if(!actualStatusReasonInfo.get(3).equals(expectStatusReason.systemStatusReason)){
			result &= false;
			logger.error("Expect system status reason should be " + expectStatusReason.systemStatusReason
					+ ", but actually is " + actualStatusReasonInfo.get(3));
		}

		if(!actualStatusReasonInfo.get(4).trim().equals(expectStatusReason.applicationCheckID)){
			result &= false;
			logger.error("Expect application check ID should be " + expectStatusReason.applicationCheckID
					+ ", but actually is " + actualStatusReasonInfo.get(4).trim());
		}

		if(!actualStatusReasonInfo.get(5).equals(expectStatusReason.creationUser)){
			result &= false;
			logger.error("Expect creation user should be " + expectStatusReason.creationUser
					+ ", but actually is " + actualStatusReasonInfo.get(5));
		}

		if(!actualStatusReasonInfo.get(6).equals(expectStatusReason.creationLocation)){
			result &= false;
			logger.error("Expect creation location should be " + expectStatusReason.creationLocation
					+ ", but actually is " + actualStatusReasonInfo.get(6));
		}

		String expectedCreationDateTime = DateFunctions.formatDate(expectStatusReason.creationDateTime, "E MMM d yyyy");
		String actualCreationDateTime = DateFunctions.formatDate(actualStatusReasonInfo.get(7), "E MMM d yyyy");
		if(!actualCreationDateTime.contains(expectedCreationDateTime)){
			result &= false;
			logger.error("Expect creation date time should be " + expectedCreationDateTime
					+ ", but actually is " + actualCreationDateTime);
		}

		if(!result){
			throw new ErrorOnPageException("Vendor status reason info are not correct, please check error log.");
		} else {
			//TODO: Finish it
		}

	}
}
