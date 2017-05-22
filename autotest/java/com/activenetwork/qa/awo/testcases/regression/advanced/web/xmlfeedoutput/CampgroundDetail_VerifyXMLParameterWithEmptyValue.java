/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.xmlfeedoutput;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.web.xmloutput.CampgroundDetailXMLOutputPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Check the parameters with empty value in XML format of campground details page. 
 * Access the XML format page by the url like: 
 * http://[host URL]/campgroundDetails.do?contractCode=[ContractId]&parkId=[facilityId]&xml=true
 * @Preconditions:
 * 1. make sure the facility "2011 National Christmas Tree Lighting Ceremony" is been added by running support script, 
 * and the facility only has the required info.
 * 2. about the display of information link, please refer to the wiki page  http://wiki.reserveamerica.com/display/dev/Facility+External+Link+Support
 * @SPEC:
 * Check Campground Detail XML output parameter [TC:032833]
 * The new parameter "regionName" in XML feed output [TC:033640]
 * New and update data parameters of Campground Detail in XML [TC:032504]
 * @Task#: Auto-1295
 * 
 * @author Lesley Wang
 * @Date  Oct 31, 2012
 */
public class CampgroundDetail_VerifyXMLParameterWithEmptyValue extends
		WebTestCase {

	private FacilityData facilityFromXML = new FacilityData();
	private CampgroundDetailXMLOutputPage xmlPage;
	private String mailingCountry, clearCacheURL;
	private List<String> photoRealUrls, photoUrls;
	public void execute() {
		// Check park link setup. Make sure there is no park link set
		web.updateParkLinkDeleteInd(schema, bd.stateCode, StringUtil.EMPTY, StringUtil.EMPTY, "1");
		web.invokeURL(clearCacheURL);
		
		// Input the URL with xml=true, check the parameters with empty value
		xmlPage = CampgroundDetailXMLOutputPage.getInstance(url);
		web.openPageInXMLFormat(url);
		
		// 1. Get basic info and check the parameters should be empty except the required info: description, driving directions, important info, mailing address state and country
		facilityFromXML = xmlPage.getFacilityDetailsInfo();	
		this.verifyEmptyParameterValues(facilityFromXML);
		
		// 2. photo
		photoRealUrls = xmlPage.getCampgoundPhotoRealUrls();
		photoUrls = xmlPage.getCampgoundPhotoUrls();
		this.verifyEmptyParameters(photoRealUrls, photoUrls, "Photo");
		
		// 3. Amenity
		List<String> amenitiesDistances = xmlPage.getCampgroundAmenitiesDistances();
		List<String> amenitiesNames = xmlPage.getCampgroundAmenitiesNames();
		this.verifyEmptyParameters(amenitiesDistances, amenitiesNames, "Amenity");
		
		// 4. Alert, Note, Bulletin
		this.verifyNoteAlertBulletinParameters();
		
		// 5. Information Link
		List<String> infoLinks = xmlPage.getCampgroundInformationLinks();
		List<String> infoLinkTitles = xmlPage.getCampgroundInformationLinkTitles();
		this.verifyEmptyParameters(infoLinks, infoLinkTitles, "Information Link");
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		bd.park = "2011 National Christmas Tree Lighting Ceremony";
		bd.parkId = DataBaseFunctions.getFacilityID(bd.park, schema);
		bd.contractCode = "NRSO";
		bd.stateCode = "DC";
		bd.state = "District Of Columbia";
		mailingCountry = "United States";
		
		String raURL = TestProperty.getProperty(env + ".web.ra.url");
		clearCacheURL = raURL + TestProperty.getProperty("clearCache.Suffix");
		url =  raURL + "campgroundDetails.do?" + 
			"contractCode=" + bd.contractCode + "&parkId=" + bd.parkId + "&xml=true";
	}
	
	private void verifyEmptyParameterValues(FacilityData facility) {
		boolean result = true;
		// latitude and longitude
		result &= MiscFunctions.compareResult("latitude", "", facility.latitudeDegrees); // related Defect-38284 (fixed)
		result &= MiscFunctions.compareResult("longitude", "", facility.longitudeDegrees);
		
		// Overview description
		result &= MiscFunctions.compareResult("orientation description", "", facility.brochureGeography);
		result &= MiscFunctions.compareResult("recreation description", "", facility.brochureRecreation);
		result &= MiscFunctions.compareResult("facility description", "", facility.brochureFacilities);
		result &= MiscFunctions.compareResult("nearby attraction description", "", facility.brochureNearbyAttractions);
		
		// mailing address
		result &= MiscFunctions.compareResult("mailing address", "", facility.mailingAddress);
		result &= MiscFunctions.compareResult("mailing address city", "", facility.mailingCityTown);
		result &= MiscFunctions.compareResult("mailing address state", bd.state, facility.mailingState);
		result &= MiscFunctions.compareResult("mailing address country", mailingCountry, facility.mailingCountry);
		result &= MiscFunctions.compareResult("mailing address zip", "", facility.mailingZipCode);
		
		// phone numbers
		result &= MiscFunctions.compareResult("direct line phone number", "", facility.directLine);
		result &= MiscFunctions.compareResult("public line phone number", "", facility.publicLine);
		
		if (!result) {
			throw new ErrorOnPageException("The parameters' values are wrong! Please check logger error!");
		}
		logger.info("Correct values of the parameters in XML!");
	}
	
	private void verifyEmptyParameters(List<String> paraA, List<String> paraB, String meg) {
		boolean result = true;
		if (paraA.size() != 1 || paraB.size() != 1) {
			result &= false;
			logger.error(meg + " parameters' number is wrong! Expect is 1; actual is:" + paraA.size() + " or " + paraB.size());
		} 
		if (!paraA.get(0).isEmpty() || !paraB.get(0).isEmpty()) {
			result &= false;
			logger.error(meg + " parameters' values should be empty, but actual is:" + paraA.get(0)+ "; " + paraB.get(0));
		}
		if (!result) {
			throw new ErrorOnPageException(meg + " parameters are wrong! Please check logger error!");
		}
		logger.info("Correct values of " + meg + " Amenity parameters in XML!");
	}
	
	private void verifyNoteAlertBulletinParameters() {
		boolean result = true;
		
		result &= MiscFunctions.compareResult("alert", "", xmlPage.getCampgroundAlert());
		result &= MiscFunctions.compareResult("note  exist", false, xmlPage.isCampgroundNoteParameterExist());
		result &= MiscFunctions.compareResult("bulletin exist", true, xmlPage.isCampgroundBulletinParameterExist());
		result &= MiscFunctions.compareResult("bulletin sub compents exist", false, xmlPage.isCampgroundBulletinSubComponentsExist());
		if (!result) {
			throw new ErrorOnPageException("alert, note, bulletin parameters are wrong! Please check logger error!");
		}
		logger.info("Correct values of alert, note, bulletin parameters in XML!");
	}
}
