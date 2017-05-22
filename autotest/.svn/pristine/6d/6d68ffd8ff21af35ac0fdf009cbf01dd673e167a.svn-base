package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.facility.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrAddFacilityDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to check if it'll adjust the invalid  content during adding a new facility 
 * The work flow was add a new bulletin with invalid headline ,check the format;
 * @Task#:Auto-1153
 * @author Phoebe Chen
 * @Date  Jul 20, 2012
 */
public class AddFacilityWithInvalidNote extends InventoryManagerTestCase {
	private FacilityData facility = new FacilityData();
	@Override
	public void execute() {
		im.deleteFacilityFromDBByName(facility.facilityName, schema);
		im.loginInventoryManager(login);
		//add facility
		facility.facilityID = im.addNewFacility(facility);
		
		//verify the facility is  added correctly
		im.gotoFacilityDetailPageById(facility.facilityID);
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
		facility.facilityName = "QA AUTO Park NOTE TEST ADD";
		facility.shortName = StringUtil.getRandomString(4, true);
		facility.status = "Non-Production";
		facility.description = "QA facility description";
		facility.productCategory = "Site";
		facility.mailingCountry = "United States";
		facility.mailingState = "Colorado";
		facility.brochureDescription = "<i>brochure Description!</i></i>";
		facility.drivingDirection =  "<blockquote>IT IS GREATE</BLOCKQUOTE>";
		
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
		
		facility.importantInfo = "<ft>with wrong tab</front>"; 
		
	}
	
	private void setFacilityToExpectValue() {
		facility.brochureDescription = "<i>brochure Description!</i>";
		facility.drivingDirection = "<blockquote>IT IS GREATE</blockquote>";
		facility.importantInfo =  "with wrong tab";
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
