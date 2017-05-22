package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.facility.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrAddFacilityDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to check if it'll adjust the invalid  content during editing a new facility 
 * The work flow was add a new bulletin and then update it with invalid headline ,check the format;
 * @Task#:Auto-1153
 * @author Phoebe Chen
 * @Date  Jul 20, 2012
 */
public class EditFacilityWithInvalidNote extends InventoryManagerTestCase {
	private FacilityData facility = new FacilityData();
	@Override
	public void execute() {		
		im.deleteFacilityFromDBByName(facility.facilityName, schema);
		im.loginInventoryManager(login);
		//add facility
		facility.facilityID = im.addNewFacility(facility);
		
		//edit facility and verify
		this.setfacilityToInvalidInfo();
		im.editNewFacilityAndClickApply(facility);		
		this.verifyAdjustInfo();
		
		//clean up
		im.deleteFacilityFromDBByName(facility.facilityName, schema);
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		
		// New Facility Information
		facility.agency = "NPS";
		facility.region = "";
		facility.district = "KATM-9796";
		facility.project = "";
		facility.facilityName = "QA AUTO Park NOTE TEST EDIT";
		facility.description = "This is used for automation testing.";
		facility.productCategory = "Site";
		facility.mailingState = "Colorado";
		facility.brochureDescription = "This is used for automation testing---brochureDescription";
		facility.drivingDirection =  "This is used for automation testing---drivingDirection";
		facility.shortName = StringUtil.getRandomString(4, true);
		facility.status = "Non-Production";
		facility.mailingCountry = "United States";
		
		//latitude details info
		facility.latitudeDirection = "North";
		facility.latitudeDegrees = "99";
		facility.latitudeMinutes = "25";
		facility.latitudeSeconds = "11";
		
		//longitude details info	
		facility.longitudeDirection = "East";
		facility.longitudeDegrees = "126";
		facility.longitudeMinutes = "23";
		facility.longitudeSeconds = "21";
		
		facility.importantInfo = "This is used for automation testing---importantInfo";   	
	}
	
   
	
	private void setfacilityToInvalidInfo() {
		facility.brochureDescription = "<table><tr>brochure Description!</td><tr></table>";
		facility.drivingDirection = "<div>www.baidu.com</dav>";
		facility.importantInfo =  "<hr>IT IS GREATE</hrr>";		
	}
	
	private void setFacilityToExpectValue() {
		facility.brochureDescription = "<table><tr>brochure Description!</tr></table>";
		facility.drivingDirection = "<div>www.baidu.com</div>";
		facility.importantInfo =  "<hr />IT IS GREATE";		
	}
	

	private void verifyAdjustInfo() {
		InvMgrAddFacilityDetailsPage facilityDetailsPage = InvMgrAddFacilityDetailsPage
				.getInstance();
		this.setFacilityToExpectValue();
		if(!facilityDetailsPage.compareFacilityInfo(facility)){
			throw new ErrorOnDataException("information for new added facility is not correct.....");
		}else{			
			logger.info("New facility invalid infomation has adjust correctly!");
		}
		
	}
}
