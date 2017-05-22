/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.xmlfeedoutput;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.web.xmloutput.CampSearchResultXMLOutputPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is for checking Campground Search Result In XML Format when search result with empty parameter value
 * 
 * Access the XML format page by the url like: 
 * http://[host URL]/campgroundSearch.do?[search parameter]&[search parameter]&\ufffd&xml=true
 * @Preconditions:
 * 1. Make sure the facility 72337 has no photo, no lattitude/longitude info
 * If the facility setting is changed, please change a facility found by the following sql:
 * No lattitude/longitude: select * from d_loc_coordinates where lat_dec=0 and long_dec=0;
 * No facility photo: find there is no folder named after the facility id in the following directory: 
 * /opt/sites/qa3/webphotos/qa-photos/[contractCode]
 * 
 * 2. Make sure the facility 70528 has no sites With Amps, sites with pets allowed, sites with sewer hookup, sites with water hookup, sites with waterfront
 * find the matched facility by the sql:
 * select distinct d.id, d.* from d_loc d, p_prd p where d.level_num=40 and d.id=p.park_id and p.park_id not in (
 		select p.park_id from p_prd p, p_prd_attr attr where p.prd_id=attr.prd_id and attr.attr_id in (32, 218, 220, 33, 24));
 *
 * @SPEC: 
 * Verify detailed parameters in Campground Search Result output in XML format [TC:032835]
 * New and update data parameters of Campground Search Result in XML [TC:032698]
 * @Task#: Auto-1297
 * 
 * @author Lesley Wang
 * @Date  Nov 28, 2012
 */
public class CampgroundSearchResult_XMLParamWithEmptyValue extends WebTestCase {
	private CampSearchResultXMLOutputPage xmlPage;
	private FacilityData searchResultFacilityFromXML;
	private FacilityData expFacility = new FacilityData();
	private String xmlUrl, hostSiteURL ;
	
	public void execute() {
		// Input the URL with xml=true, check the page in XML format and get the XML content
		web.openPageInXMLFormat(xmlUrl);
		
		// Checkpoint 1: verify only these search criteria are shown: pname, count, result type ,same as the URL parameters
		this.verifySearchCriteriaExist();
		
		// Checkpoint 2: verify search result with empty parameter values
		searchResultFacilityFromXML = xmlPage.getFirstSearchResultFacilityInfo();	
		this.verifySearchResultInfo(expFacility, searchResultFacilityFromXML); // Blocked by defect-38862
		
		// Checkpoint 3: verify search result parameters with N value: sitesWithAmps, sitesWithWaterHookup, sitesWithSewerHookup, sitesWithPetsAllowed, sitesWithWaterfront.
		this.updateXmlUrl();
		web.openPageInXMLFormat(xmlUrl);
		this.verifySitesWithParameters();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		
		/* Facility Info */
		expFacility.contract = "NRSO";
		expFacility.facilityID = "72337";
		expFacility.facilityName = DataBaseFunctions.getFacilityName(expFacility.facilityID, schema);
		expFacility.shortName = web.getFacilityShortName(schema, expFacility.facilityID);
		expFacility.stateCode = "AK";
		expFacility.facilityPhotoSrc = "/images/nophoto.jpg";
		
		/* URL info */
		hostSiteURL = TestProperty.getProperty(env + ".web.ra.url") + "campgroundSearch.do?";
		xmlUrl =  hostSiteURL +  "pname=" + expFacility.facilityName + "&xml=true";
		xmlPage = CampSearchResultXMLOutputPage.getInstance(xmlUrl);
	}

	/** Verify search criteria exist */
	private void verifySearchCriteriaExist() {
		boolean result = true;
		result &= verifyParamNotExist("pstate", xmlPage.getCampgroundSearchState());
		result &= MiscFunctions.compareString("pname value", expFacility.facilityName, xmlPage.getCampgroundSearchParkName());
		result &= verifyParamNotExist("siteType", xmlPage.getCampSearchSiteType());
		result &= verifyParamNotExist("range", xmlPage.getCampSearchDateRange());
		result &= verifyParamNotExist("enddate", xmlPage.getCampSearchEndDate());
		result &= verifyParamNotExist("lengthOfStay", xmlPage.getCampSearchCriteriaLengthOfStay());
		result &= verifyParamNotExist("amenity", xmlPage.getCampgroundSearchCriteriaAmenity());
		result &= verifyParamNotExist("eqplen", xmlPage.getCampgroundSearchCriteriaEqpLen());
		result &= verifyParamNotExist("maxpeople", xmlPage.getCampgroundSearchCriteriaMaxPeople());
		result &= verifyParamNotExist("hookup", xmlPage.getCampgroundSearchCriteriaHookup());
		result &= verifyParamNotExist("ada", xmlPage.getCampgroundSearchCriteriaAda());
		result &= verifyParamNotExist("waterfront", xmlPage.getCampgroundSearchWaterFront());
		result &= MiscFunctions.compareString("resultType", "campgrounds", xmlPage.getCampSearchResultType());
		
		if (!result) {
			throw new ErrorOnPageException("The shown search criteria parameters are wrong!");
		}
		logger.info("checkpoint 1 -- successfully verify shown search criteria parameters in XML");
	}
	
	/** Verify search result with empty parameters values*/
	private void verifySearchResultInfo(FacilityData exp, FacilityData actu) {
		boolean result = true;
		result &= MiscFunctions.compareString("Contract", exp.contract, actu.contract);
		result &= MiscFunctions.compareString("Facility state", exp.stateCode, actu.stateCode);
		result &= MiscFunctions.compareString("agencyIcon", "", xmlPage.getCampgroundSearchResultAgencyIcon());
		result &= MiscFunctions.compareString("agencyName", "", actu.agency);
		result &= MiscFunctions.compareString("regionName", "", actu.region);
		result &= MiscFunctions.compareString("Facility ID", exp.facilityID, actu.facilityID);
		result &= MiscFunctions.compareString("Facility Name", exp.facilityName, actu.facilityName);
		result &= MiscFunctions.compareString("Facility Short name", exp.shortName, actu.shortName);
		result &= MiscFunctions.compareString("facility latitude", "", actu.latitudeDegrees); //Blocked by defect-38862
		result &= MiscFunctions.compareString("facility longitude", "", actu.longitudeDegrees);//Blocked by defect-38862
		result &= MiscFunctions.compareString("facilityPhoto", exp.facilityPhotoSrc, actu.facilityPhotoSrc);
		result &= MiscFunctions.compareResult("availabilityStatus", false, actu.isAvailable);
		result &= MiscFunctions.compareString("reservationChannel", "", actu.status);
	
		if (!result) {
			throw new ErrorOnPageException("Search Result Info is wrong in XML!");
		}
		logger.info("Checkpoint 2 -- successfully verify search result info in XML");
	}
	
	private void updateXmlUrl() {
		String facilityID = "70528";
		String facilityName = DataBaseFunctions.getFacilityName(facilityID, schema);
		xmlUrl = hostSiteURL + "pstate=CA&pname=" + facilityName + "&siteType=2001&xml=true";
		xmlPage = CampSearchResultXMLOutputPage.getInstance(xmlUrl);
	}
	
	/** Verify search result sites with parameters values in XML */
	private void verifySitesWithParameters() {
		boolean result = true;
		result &= MiscFunctions.compareString("sitesWithAmps", "N", xmlPage.getCampgroundSearchResultSitesWithAmps());
		result &= MiscFunctions.compareString("sitesWithPetsAllowed", "N", xmlPage.getCampgroundSearchResultSitesWithPetsAllowed());
		result &= MiscFunctions.compareString("sitesWithSewerHookup", "N", xmlPage.getCampgroundSearchResultSitesWithSewerHookup());
		result &= MiscFunctions.compareString("sitesWithWaterHookup", "N", xmlPage.getCampgroundSearchResultSitesWithWaterHookup());
		result &= MiscFunctions.compareString("sitesWithWaterfront", "", xmlPage.getCampgroundSearchResultSitesWithWaterfront());
		
		if (!result) {
			throw new ErrorOnPageException("Search Result sitesWith parameters are wrong in XML!");
		}
		logger.info("Checkpoint 3 -- successfully verify search result sitesWith parameters in XML");
	}
	
	private boolean verifyParamNotExist(String meg, String value) {
		if (value != null) {
			logger.error(meg + "should not be shown in xml!");
			return false; 
		} 
		return true;
	}
}
