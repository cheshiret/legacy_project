package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail.InvMgrFacilityDetailPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.TestProperty;

/**Note: pass on local
 * @Description:
 * 1: Prerequisite - facility don't have amenities and services info
 * 	1.1: brochure info equal to 84 chars. will not show more hyper link.
 * 	1.2: brochure info larger than 84 chars, will show more hyper link, and clickable, and the expand description match with inventory manager settings.
 * 2: prerequsiste - facility have amentities and services info
 *  2.1: brochure info equal to 84 chars, system still display more hyper link.
 *  2.2: brochure info equal to 84 chars, before click there is no amenities and service displayed.
 *  2.3: brochure info equal to 84 chars, after click more hyperlink, system will display the full description, and corresponding amenties info.
 * @Preconditions:
 * @SPEC: Story C
 * @Task#:
 * 
 * @author bzhang
 * @Date  Oct 10, 2011
 */
public class DescriptionValidation extends RecgovTestCase{
	
	private String schema, clearCacheUrl;
	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private String brochureFor151Chars,brochureFor152Chars;
	private String fIDFor151Chars, fIDFor152Chars;
	
	public void execute() {
		
		//Section1: Update environment to meet the prerequisite.
		im.loginInventoryManager(login);		
		//1:update facility with 84 chars brochure info
		im.gotoFacilityDetailPageById(fIDFor151Chars);
		this.modifyFacilityBrochureInfo(brochureFor151Chars);		
		//2:update facility with 85 chars brochure info
		im.gotoFacilityDetailPageById(fIDFor152Chars);
		this.modifyFacilityBrochureInfo(brochureFor152Chars);			
		//3: de-active amenities and service for the facilities in step1 and step2 above.
		im.updateAmenitiesAndServicesForFacility(fIDFor151Chars, schema, false);
		im.updateAmenitiesAndServicesForFacility(fIDFor152Chars, schema, false);
		im.logoutInvManager();	
		
		//Section2: Start testing different checkpoints.
		web.invokeURL(clearCacheUrl); //clear cache make the update in DB on Section1 refect on Web page.
		web.invokeURL(url, false, false);
		web.makeUnifiedSearch(bd);
		this.gotoViewAsListPage(bd);
		
		//Section3: verify description and amenity info for first facility.
		//4.1: verify park with 84 chars won't show more hyper link in the description section.
		this.verifyDescriptions(login.contractAbbrev, fIDFor151Chars, false, false);		
		//4.2 verify park description match with settings
		this.verifyDescriptionMatchWithSettings(login.contractAbbrev, fIDFor151Chars,brochureFor151Chars, false);		
		//4,3: verify amenities won't show for the first facility
		this.verifyAmenityInfo(login.contractAbbrev, fIDFor151Chars, "");
		
		//Section4: verify description and amentity info for second facility.
		//5.1: verify park with 85 chars will show more hyper link in the description section
		this.verifyDescriptions(login.contractAbbrev, fIDFor152Chars, true, false);
		//5.2: verify amenities won't show for the second facility before expand description
		this.verifyAmenityInfo(login.contractAbbrev, fIDFor152Chars, "");		
		//5.3: verify description DIV with More info is click-able.
		this.expandAndColapsDescription(login.contractAbbrev, fIDFor152Chars, true);

		//Section5: verify description and amenity info for second facility after expand description.
		//6.1: verify amenities won't show for the second facility after expand description
		this.verifyAmenityInfo(login.contractAbbrev, fIDFor152Chars, "");		
		//6.2: verify the description after expand
		this.verifyDescriptions(login.contractAbbrev, fIDFor152Chars, false, true);		
		//6.3: verify the description  match with Inventory manager Brochure info after expand.
		this.verifyDescriptionMatchWithSettings(login.contractAbbrev, fIDFor152Chars,brochureFor152Chars, true);
		
		//Section6:verify description for second facility after collapse.
		//7.1: verify click expanded description section will collapse the description section.
		this.expandAndColapsDescription(login.contractAbbrev, fIDFor152Chars, false);
		//7.2: verify description for second facility after collapse.
		this.verifyDescriptions(login.contractAbbrev, fIDFor152Chars, true, false); //verify more hyper link after collapse.
		
		//Section7: active two facilities amenities and servicies info
		im.updateAmenitiesAndServicesForFacility(fIDFor151Chars, schema, true);
		im.updateAmenitiesAndServicesForFacility(fIDFor152Chars, schema, true);
		
		web.invokeURL(clearCacheUrl, false, false);; //clear cache make the update in DB on Section7 refect on Web page.
		web.invokeURL(url, false, false);
		web.makeUnifiedSearch(bd);
		this.gotoViewAsListPage(bd);
		
		//Section8: verify description for both facility when the facility has amenities and services info.
		//8.1: verify although description less than 84 chars, there is still more hyper link if the facility has amenities and services.
		this.verifyDescriptions(login.contractAbbrev, fIDFor151Chars, true, false);		
		//8.2: verify amenities won't show before expend description for first facility
		this.verifyAmenityInfo(login.contractAbbrev, fIDFor151Chars, "");
		//8.3: verify amenities won't show before expend description for second facility
		this.verifyAmenityInfo(login.contractAbbrev, fIDFor152Chars, "");
		
		
		//Section9: verify amenities and services match with given values after expand.
		//14: verify amenities will show after expend description for second facility, and match with the given amenity and services info
		this.expandAndColapsDescription(login.contractAbbrev, fIDFor151Chars, true);
		this.verifyAmenityInfo(login.contractAbbrev, fIDFor151Chars, "Drinking Water Flush Toilets Grills Hot Water Trash collection");		
		//15: verify amenities will show after expend description for second facility, and match with the given amenity and services info
		this.expandAndColapsDescription(login.contractAbbrev, fIDFor152Chars, true);
		this.verifyAmenityInfo(login.contractAbbrev, fIDFor152Chars, "Amphitheater Drinking Water Hitching Racks Horseback Riding Trails Vault Toilets");
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		clearCacheUrl = TestProperty.getProperty(env + ".web.recgov.clearCache.url");
		
		bd.whereTextValue = "Mammoth Picnic Shelters";
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.contractCode = "NRSO";
		bd.parkId = "70920";
		
		//Inventory Mananger Login info.
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location="Administrator/NRRS";
		login.contractAbbrev = "NRSO";
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");

		fIDFor151Chars = "70920";   // facility name A: MAMMOTH PICNIC SHELTERS (KY); brochure info length is 151, 
		brochureFor151Chars = "Picnic shelters are located in the picnic area adjacent Picnic shelters are located in the picnic adjacent Picnic shelters are located in the picnicing";
		
		fIDFor152Chars = "70948";  //facility name: MAMMOTH CAVE NATIONAL PARK TOURS; brochure info length is 152
		brochureFor152Chars = "Picnic shelters are located in the picnic area adjacent Picnic shelters are located in the picnic adjacent Picnic shelters are located in the picnicin g";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
	}
		
	/**
	 * verify description display with more hyper link or not.
	 * @param contractCode
	 * @param facilityID
	 * @param withMoreLink
	 * @param isDescriptionDetail  true-the description DIV after expand, false - the description DIV before expand
	 */
	private void verifyDescriptions(String contractCode, String facilityID, boolean withMoreLink, boolean isDescriptionDetail){
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		String facilityDescription = "";
		
		if(!isDescriptionDetail){
			facilityDescription = searchResult.getParkDescription(contractCode, facilityID);
		}else{
			facilityDescription = searchResult.getParkDescriptionDetail(contractCode, facilityID);
		}

		if(withMoreLink){
			if(facilityDescription.endsWith("...[more]")) {
				logger.info("Verify description info for contract Code:" + contractCode + "; facilityID:" + facilityID + " successful");
			}else{
				throw new ErrorOnDataException("Verify description info for contract Code:" + contractCode + "; facilityID:" + facilityID + " failed");
			}
		}else{
			if(facilityDescription.endsWith("...[more]")) {
				throw new ErrorOnDataException("Verify description info for contract Code:" + contractCode + "; facilityID:" + facilityID + " failed");
			}else{
				logger.info("Verify description info for contract Code:" + contractCode + "; facilityID:" + facilityID + " successful");
			}
		}
	}
	
	/**
	 * update the facility brochure info in Inventory manager
	 * @param info
	 */
	private void modifyFacilityBrochureInfo(String info){
		InvMgrFacilityDetailPage inFacilityDetailPg = InvMgrFacilityDetailPage.getInstance();
		InvMgrHomePage InvHmPg = InvMgrHomePage.getInstance();
		logger.info("update facility brochure info based on given info...");
		
		inFacilityDetailPg.modifyBrochureInfo(info);
		inFacilityDetailPg.setGeographyInfo(" ");
		inFacilityDetailPg.setRecreationInfo(" ");
		inFacilityDetailPg.setFacilitiesInfo(" ");
		inFacilityDetailPg.setNearbyAttractionsInfo(" ");
		inFacilityDetailPg.clickApply();
		inFacilityDetailPg.waitLoading();
		inFacilityDetailPg.clickOK();
		InvHmPg.waitLoading();
	}
	
	/**
	 * expand or collapse the park description DIV
	 * 
	 * @param contractCode
	 * @param facilityID
	 * @param expand, true - expand, false - collapse
	 */
	private void expandAndColapsDescription(String contractCode, String facilityID, boolean expand){
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		if(expand){
			searchResult.clickParkDescription(contractCode, facilityID);
		}else{
			searchResult.clickParkDescriptionDetail(contractCode, facilityID);
		}
		Browser.sleep(DYNAMIC_SLEEP_BEFORE_CHECK); //sleep 5 seconds waiting for more to display. can't find a good method to handle the progress ICON
		searchResult.waitLoading();
	}
	
	/**
	 * verify the description text displayed on the page match with the given expect text.
	 * @param contractCode
	 * @param facilityID
	 * @param expectString
	 * @param afterExpand  true-description after expand, false-description before expand
	 */
	private void verifyDescriptionMatchWithSettings(String contractCode, String facilityID, String expectString, boolean afterExpand){
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		String desText = "";
		if (!afterExpand){
			desText = searchResult.getParkDescription(contractCode, facilityID);
		}else{
			desText = searchResult.getParkDescriptionDetail(contractCode, facilityID);
		}
		
		if(!desText.equalsIgnoreCase(expectString)){
			throw new ErrorOnDataException("The description is wrong.", expectString ,desText );
		}
	}
	
	/**
	 * verify amenity and services info match with the given amentity and services.
	 * @param contractCode
	 * @param facilityID
	 */
	private void verifyAmenityInfo(String contractCode, String facilityID, String amenitys){
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		String ectualInfo = searchResult.getAmenitiesAndActivities(contractCode, facilityID);
		
		if (!ectualInfo.equalsIgnoreCase(amenitys)){
			if(amenitys.length() == 0){
				throw new ErrorOnPageException("The system should not display amenity and service info for the given ContractCode: " + contractCode + ", facilityID=" + facilityID);
			}else{
				throw new ErrorOnDataException("The current amenities info is:" + ectualInfo + ";\n while the expect amenities info should be:" + amenitys);
			}			
		}
		logger.info("verify amentiy info successful for facilityID = " + facilityID);
	}
	

}
