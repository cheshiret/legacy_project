/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.xmlfeedoutput;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.pages.web.ra.RASiteDetailsPage;
import com.activenetwork.qa.awo.pages.web.ra.RASiteListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSiteSearchPanel;
import com.activenetwork.qa.awo.pages.web.xmloutput.CampSearchResultXMLOutputPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is for checking Campsite Search Result parameters In XML Format, including
 * 1. availabilityStatus and reservationChannel parameters are not shown when search without any date info
 * 2. input the url with  wrong siteType value and check the search criteria is ignored
 * 3. input the url without expfits but with campsite amenity parameters, and check the amenity parameters are ignored.
 * 4. sitePhoto parameters and its value 
 * Access the XML format page by the url like: 
 * http://[host URL]/campsiteSearch.do?submitSiteForm=true&search=site&contractCode=[ContractID]&parkId=[facilityID]&[search parameter]&[search parameter]&\ufffd&xml=true
 * 
 * @Preconditions:
 * The test data is from production.
 * 
 * @SPEC: 
 * Verify detailed Search parameters in Campsite Search Result [TC:032888]
 * New and update data parameters of Campsite Search Result in XML [TC:032696]
 * 
 * @Task#: Auto-1298
 * 
 * @author Lesley Wang
 * @Date  Nov 30, 2012
 */
public class CampsiteSearchResult_VerifySpecialResultParameters extends
		WebTestCase {
	private CampSearchResultXMLOutputPage xmlPage;
	private RASiteListPage searchResultPg = RASiteListPage.getInstance();
	private UwpSiteSearchPanel searchPanel = UwpSiteSearchPanel.getInstance();
	private RASiteDetailsPage details = RASiteDetailsPage.getInstance();
	private SiteInfoData searchResultSiteFromXML;
	private String xmlUrl;
	
	public void execute() {
		// input the URL1 without siteType, expsits or campsite amenity parameters
		web.openPageInXMLFormat(xmlUrl);
		String resultCount = xmlPage.getCampSearchResultCount();
		searchResultSiteFromXML = xmlPage.getFirstCampsiteSearchResultInfo();			
		
		// 1. Check there is no availableStatus or reservationChannel parameters when the url is without any date info
		this.verifyAvaiStatusAndResChannelParamsNotExist(searchResultSiteFromXML.onlineAvailability, searchResultSiteFromXML.resChannel);
		
		// 2. Check ignored parameters when the parameters' values are wrong	
		// input the URL with wrong siteType value and without expfits parameter but with some amenity parameters, and compare the xml page
		this.updateURL("&siteType=1&eqplen=100&maxpeople=100&hookup=1");
		web.openPageInXMLFormat(xmlUrl);	
		this.verifyXMLPageContent(resultCount, searchResultSiteFromXML);

		// Lesley[20130912]: the following code is not valid after RA changed to Unified Search mode
		// input the URL without xml parameters and check the ignored parameters in Html page
		this.gotoCampsiteSearchResultPage(url);
//		this.verifyIgnoredSearchCriteriaInHtmlPage(bd.siteType, bd.spotWith);
		
		// 3. Check sitePhoto value
		this.gotoCampsiteDetailFromCompsiteListPg(bd.siteNo);
		this.verifySitePhoto(searchResultSiteFromXML.sitePhoto);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		bd.contractCode ="NRSO";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		
		/* Booking Data Info. The info must match with the URL parameters */
		bd.parkId ="70806";  
		bd.spotWith = false;
		bd.siteType = "Any camping spot";
		bd.siteID = "88378";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);
		
		/* URL info */
		url =  TestProperty.getProperty(env + ".web.ra.url") + "campsiteSearch.do?submitSiteForm=true&search=site" + 
				"&contractCode=" + bd.contractCode + 
				"&parkId=" + bd.parkId;
		xmlUrl = url + "&xml=true";
		xmlPage = CampSearchResultXMLOutputPage.getInstance(xmlUrl);
	}
	
	/* Verify availableStatus and reservationChannel parameters don't exist if availability search is not requested. */
	private void verifyAvaiStatusAndResChannelParamsNotExist(String availStatus, String resChannel) {
		if (availStatus != null || resChannel != null) {
			throw new ErrorOnPageException("availableStatus and reservationChannel should not exist in XML!");
		}
		logger.info("Checkpoint 1 -- successfully verify availableStatus and reservationChannel parameters do not exist in XML!");
	}
	
	private void updateURL(String searchParam) {
		url += searchParam;
		xmlUrl = url + "&xml=true";
	}
	
	/* Verify result count and some site amenity parameters in xml page */
	private void verifyXMLPageContent(String count, SiteInfoData site) {
		String count2 = xmlPage.getCampSearchResultCount();
		SiteInfoData site2 = xmlPage.getFirstCampsiteSearchResultInfo();	
		boolean result = true;
		result &= MiscFunctions.compareString("result count", count, count2);
		result &= MiscFunctions.compareString("site equ length", site2.maxVehicleLength, site.maxVehicleLength);
		result &= MiscFunctions.compareString("site max num people", site2.maxNumPeople, site.maxNumPeople);
		result &= MiscFunctions.compareString("site max num people", site2.maxNumPeople, site.maxNumPeople);
		result &= MiscFunctions.compareString("site electric hookup", site2.electricityHookup, site.electricityHookup);
		
		if (!result) {
			throw new ErrorOnPageException("The Result count and amenity parameters should be the same in the two XML content!");
		}
		logger.info("Checkpoint 2.1 -- successfully verify the search result count and amenity parameters are the same in the two XML content");
	}
	
	private void gotoCampsiteSearchResultPage(String url) {
		web.invokeURL(url, false, false);
		searchResultPg.waitLoading();
	}
	
	/* Verify ignored site type and site amenity search criteria in Html Page */
	private void verifyIgnoredSearchCriteriaInHtmlPage(String siteType, boolean spotWith) {
		String siteTypeFromHtml = searchPanel.getSelectedSiteType();
		boolean spotWithFromHtml = searchPanel.isSpotWithChecked();
		
		boolean result = true;
		result &= MiscFunctions.compareString("site type", siteType, siteTypeFromHtml);
		result &= MiscFunctions.compareResult("site with amenity", spotWith, spotWithFromHtml);
		if (!result) {
			throw new ErrorOnPageException("The site type and amenity parameters should be ignored in Html Page!");
		}
		logger.info("Checkpoint 2.2 -- successfully verify the site type and amenity parameters are ignored in Html page!");
	}
	
	private void gotoCampsiteDetailFromCompsiteListPg(String siteCode) {
		searchResultPg.clickSiteNum(siteCode);
		details.waitLoading();
	}
	
	/* Verify site photo value */
	private void verifySitePhoto(String sitePhoto) {
		List<String> srcs = details.getCampsiteImagesSrc();
		if (srcs == null || srcs.size() < 1) { // no site photo
			if (!sitePhoto.isEmpty()) {
				throw new ErrorOnPageException("There is no site photo. The sitePhoto in XML shoudl be empty.");
			}
		} else {
			String photo = srcs.get(0);
			if (!photo.equals(sitePhoto)) {
				throw new ErrorOnPageException("Site Photo is wrong!", photo, sitePhoto);
			}
		}
		
		logger.info("Checkpoint 3 -- successfully verify site photo!");
	}
}
