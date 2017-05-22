/**
 * 
 */
package com.activenetwork.qa.awo.keywords.web;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.Awo;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.AdminDoHomePage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.MarketingSpotSummaryPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityListPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityPhotosPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolSelectFacilityPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolSelectProductPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppSelectRoleLocPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppSignInPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppUserPanel;
import com.activenetwork.qa.awo.pages.web.recgov.PhotoManagerCrossOverToRecPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHomePage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: These methods are for the web maintenance applications, including Photo Tool, Marketing Spot Manager and Admin.do
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Dec 12, 2012
 */
public class MaintenanceApplication extends Awo {
	
	private static MaintenanceApplication _instance = null;

	public static MaintenanceApplication getInstance() {
		if (null == _instance)
			_instance = new MaintenanceApplication();

		return _instance;
	}
	
	public String PAGETITLE_FACILITY_DETAILS_PAGE = "Facility details page";
	public String PAGETITLE_SITE_DETAILS_PAGE = "Site details page";
	public String PAGETITLE_ENTRANCE_DETAILS_PAGE = "Entrance details page";
	
	protected MaintenanceApplication() {
	}
	/**
	 * Invoke a new browser with the given URL and don't need to get build number. 
	 * @param url
	 */
	public void invokeURL(String url) {
		invokeURL(url, false, true);
	}

	/**
	 * Invoke a new browser with the given URL. 
	 * @param url
	 * @param getBuildNum
	 */
	public void invokeURL(String url, boolean getBuildNum) {
		invokeURL(url, getBuildNum, true);
	}
	
	/**
	 * Invoke a browser with the given URL. 
	 * @param url - the url to open
	 * @param getBuildNum - flag to control if need to get a build number	
	 * @param isNewBrowser - if need to get a build number 
	 */
	public void invokeURL(String url, boolean getBuildNum, boolean isNewBrowser) {
		invokeURL(url, WEB_APP, getBuildNum, isNewBrowser);
		browser.waitExists(WebMaintenanceAppSignInPage.getInstance());
	}
	
	/**
	 * Sign in web application by input user name and password, and click Log In button
	 * Starts from Sign In page, ends at role/location selection page or sign in page
	 * @param userName
	 * @param pw
	 */
	public void signIn(String userName, String pw) {
		WebMaintenanceAppSignInPage signInPg = WebMaintenanceAppSignInPage.getInstance();
		WebMaintenanceAppSelectRoleLocPage selectLocPg = WebMaintenanceAppSelectRoleLocPage.getInstance();
		PhotoToolSelectFacilityPage ptHomePg = PhotoToolSelectFacilityPage.getInstance();
		MarketingSpotSummaryPage marketingSpotSumPg = MarketingSpotSummaryPage.getInstance();
		AdminDoHomePage adminPg = AdminDoHomePage.getInstance();
		
		logger.info("Start to sign in Web Maintenance Applications with username=" + userName);
		signInPg.signIn(userName, pw);
		browser.waitExists(ptHomePg, marketingSpotSumPg, adminPg, selectLocPg, signInPg);
	}
	
	/**
	 * Select role/location and click Continue button on Role/Location selection page
	 * Starts from Role/Location selection page, ends at any web application home page or the selection page
	 * @param role
	 * @param loc
	 */
	public void selectRoleLocAndContinue(String role, String loc) {
		WebMaintenanceAppSelectRoleLocPage selectLocPg = WebMaintenanceAppSelectRoleLocPage.getInstance();
		PhotoToolSelectFacilityPage ptHomePg = PhotoToolSelectFacilityPage.getInstance();
		MarketingSpotSummaryPage marketingSpotSumPg = MarketingSpotSummaryPage.getInstance();
		AdminDoHomePage adminPg = AdminDoHomePage.getInstance();
		
		logger.info("Select the role=" + role + " and location=" + loc + " to continue...");	
		selectLocPg.selectRoleLocation(role, loc);
		selectLocPg.clickContinue();
		browser.waitExists(ptHomePg, marketingSpotSumPg, adminPg, selectLocPg);
	}
	
	/**
	 * Sign in web application.
	 * Starts with sign in page, ends at any web application home page or role/location selection page
	 * @param login
	 */
	public void signIn(LoginInfo login) {
		this.signIn(login.userName, login.password);	
		this.selectRoleLocAndContinue(login.role, login.location);
	}
	
	/**
	 * Click Log In button to select role/loc page
	 */
	public void signInToSelectRoleLocPg() {
		WebMaintenanceAppSignInPage signInPg = WebMaintenanceAppSignInPage.getInstance();
		WebMaintenanceAppSelectRoleLocPage selectLocPg = WebMaintenanceAppSelectRoleLocPage.getInstance();
		
		signInPg.clickLogIn();
		selectLocPg.waitLoading();
	}
	/**
	 * Go to 'SelectRoleLocationPage' from 'UserPanel' via clicking 'Change role/location' link in 'UserPanel' page
	 */
	public void changeRoleLocationFromUserPanelToRoleLocationSelectingPage(){
		WebMaintenanceAppUserPanel userPanel = WebMaintenanceAppUserPanel.getInstance();
		WebMaintenanceAppSelectRoleLocPage selectLocPg = WebMaintenanceAppSelectRoleLocPage.getInstance();
		
		logger.info("Go to 'SelectRoleLocationPage' from 'UserPanel' via clicking 'Change role/location' link in 'UserPanel' page");
		userPanel.clickChangeRoleLocationLink();
		selectLocPg.waitLoading();
	}
	
	/**
	 * Log Out
	 */
	public void logOut() {
		WebMaintenanceAppUserPanel topMenu = WebMaintenanceAppUserPanel.getInstance();
		WebMaintenanceAppSignInPage signInPg = WebMaintenanceAppSignInPage.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		
		logger.info("Log out from web maintenance application...");
		topMenu.clickLogOut();
		if(confirmDialogPg.exists()){
			confirmDialogPg.clickOK();
		}
		signInPg.waitLoading();
	}
	
	/**
	 * Log out from admin.do page. After log out, the UWP home page will be shown.
	 */
	public void logOutFromAdminDo() {
		WebMaintenanceAppUserPanel topMenu = WebMaintenanceAppUserPanel.getInstance();
		UwpHomePage uwp = UwpHomePage.getInstance();
		
		logger.info("Log out from admin.do...");
		topMenu.clickLogOut();
		uwp.waitLoading();
	}
	
	/**
	 * Get Page URL
	 */
	public String getPageUrl() {
		return browser.url();
	}
	
	/**
	 * Go to facility list page after selecting specific contract
	 * @param contract
	 */
	public void gotoFacilityListPage(String contract){
		PhotoToolSelectFacilityPage selectFacilityPg = PhotoToolSelectFacilityPage.getInstance();
		PhotoToolFacilityListPage facilityListPg = PhotoToolFacilityListPage.getInstance();
		
		logger.info("Go to facility list page via selecting contract.");
		selectFacilityPg.selectContract(contract);
		facilityListPg.waitLoading();
	}
	
	/**
	 * Go to facility photos page from facility list page
	 * @param state
	 * @param letterSelector
	 * @param facilityName
	 * @param parkID
	 */
	public String gotoFacilityPhotosPage(String contract, String state, String letterSelector, String parkID){
		PhotoToolFacilityPhotosPage campgroundPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
		PhotoToolFacilityListPage facilityListPg = PhotoToolFacilityListPage.getInstance();
		boolean existing = false;
		
		logger.info("Go to campground photos page from facility list page.");
		String searchResultContent = facilityListPg.filterFacility(contract, state, letterSelector);
		
		if(!StringUtil.isEmpty(parkID)){
			do{
				existing = facilityListPg.checkFacilityExisting(parkID);
				if(!existing){
					facilityListPg.navigatePage(true);
				}
			}while(!existing);
			facilityListPg.selectFacility(parkID);	
		}else facilityListPg.selectFirstFacility();

		campgroundPhotosPg.waitLoading();
		return searchResultContent;
	}
	
	public void gotoCampgroundPhotosPage(String parkID){
		PhotoToolFacilityPhotosPage campgroundPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
		PhotoToolFacilityListPage facilityListPg = PhotoToolFacilityListPage.getInstance();
		
		logger.info("Go to campground photos page from facility list page.");
		
		if(!StringUtil.isEmpty(parkID)){
			facilityListPg.selectFacility(parkID);	
		}else facilityListPg.selectFirstFacility();

		campgroundPhotosPg.waitLoading();
	}
	
	public String gotoCampgroundPhotosPage(String contract, String state, String letterSelector){
		return this.gotoFacilityPhotosPage(contract, state, letterSelector, "");
	}
	
	public void gotoProductPhotosPage(String contract, String state, String letterSelector, String parkID){
		this.gotoFacilityPhotosPage(contract, state, letterSelector, parkID);
		this.gotoProductPhotosTabFromFacilityPhotosPage();
	}
	
	public void gotoProductPhotosPage(String parkID){
		this.gotoFacilityPhotosPage("", "", "", parkID);
		this.gotoProductPhotosTabFromFacilityPhotosPage();
	}
	
	public void gotoProductPhotosTabFromFacilityPhotosPage(){
		PhotoToolFacilityPhotosPage campGroundPhotoPg = PhotoToolFacilityPhotosPage.getInstance();
		PhotoToolProductPhotosPage productPhotosPg = PhotoToolProductPhotosPage.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		PhotoToolSelectProductPage selectProductPg = PhotoToolSelectProductPage.getInstance();
		
		logger.info("Go to product photos page from facility photos page.");
		campGroundPhotoPg.clickProductPhotosTab();
		if(confirmDialogPg.exists()){
			confirmDialogPg.clickOK();
		}
		
		browser.waitExists(productPhotosPg, selectProductPg);
	}
	
	public void gotoFacilityPhotosPageFromProductPhotosPage(){
		PhotoToolFacilityPhotosPage campGroundPhotoPg = PhotoToolFacilityPhotosPage.getInstance();
		PhotoToolProductPhotosPage productPhotosPg = PhotoToolProductPhotosPage.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		
		logger.info("Go to facility photos page from product photos page.");
		productPhotosPg.clickFacilityPhotosTab();
		browser.waitExists(confirmDialogPg, campGroundPhotoPg);
		if(confirmDialogPg.exists()){
			confirmDialogPg.clickOK();
		}
		campGroundPhotoPg.waitLoading();
	}
	
	/**
	 * Select product from Product Select page to Product Photos page 
	 * @param prdID
	 * @param loop
	 * @param showCondition
	 * @author Lesley Wang
	 * Mar 21, 2013
	 */
	public String gotoProductPhotosPgFromSelectPrdPg(String prdID, String loop, String showCondition) {
		PhotoToolSelectProductPage selectProductPg = PhotoToolSelectProductPage.getInstance();
		PhotoToolProductPhotosPage productPhotosPg = PhotoToolProductPhotosPage.getInstance();
		boolean existing = false;
		
		logger.info("Select product from Product Select page to Product Photos page for prd id=" + prdID);
		String productListContent = selectProductPg.filterProduct(loop, showCondition);
		if(!StringUtil.isEmpty(prdID)){
			do{
				existing = selectProductPg.isProductExist(prdID);
				if(!existing){
					selectProductPg.navigatePage(true);
				}
			}while(!existing);
			selectProductPg.clickProductLink(prdID);	
		}else selectProductPg.clickFirstProductLink();
		productPhotosPg.waitLoading();
		
		return productListContent;
	}
	
	public String gotoProductPhotosPgFromSelectPrdPg(String prdID) {
		return this.gotoProductPhotosPgFromSelectPrdPg(prdID, "", "");
	}
	
	/**
	 * Go to Product Photos page from facility photos page
	 * @param prdID
	 * @param loop
	 * @param showCondition
	 * @author Lesley Wang
	 * Mar 21, 2013
	 */
	public String gotoPrdPhotosPgFromFacilityPhotosPage(String prdID, String loop, String showCondition) {
		PhotoToolSelectProductPage selectProductPg = PhotoToolSelectProductPage.getInstance();
		String searchResultContent = "";
		this.gotoProductPhotosTabFromFacilityPhotosPage();
		if (selectProductPg.exists()) {
			searchResultContent = this.gotoProductPhotosPgFromSelectPrdPg(prdID, loop, showCondition);
		}
		
		return searchResultContent;
	}
	
	public String gotoPrdPhotosPgFromFacilityPhotosPage(String prdID) {
		return this.gotoPrdPhotosPgFromFacilityPhotosPage(prdID, "", "");
	}
	
	/**
	 * Change facility from campground photos page to facility list page
	 */
	public void changeFacility(Page startPg){
		PhotoToolFacilityPhotosPage campgroundPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
		 PhotoToolProductPhotosPage productPhotoPg = PhotoToolProductPhotosPage.getInstance();
		PhotoToolFacilityListPage facilityListPg = PhotoToolFacilityListPage.getInstance();
		
		logger.info("Change facility from campground photos page to facility list page.");
		if(startPg == campgroundPhotosPg){
			campgroundPhotosPg.clickChangeFacilityLink();
		}else if(startPg == productPhotoPg){
			productPhotoPg.clickChangeFacilityLink();
		}else new ErrorOnDataException("Can't find matched page.");
		
		facilityListPg.waitLoading();
	}
	
	public void changeFacility(){
		PhotoToolFacilityPhotosPage campgroundPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
		this.changeFacility(campgroundPhotosPg);
	}
	
	/**
	 * Change site from product photos page to product list page
	 */
	public void changeProductFromProductPhotosPgToProductListPg(){
		PhotoToolSelectProductPage productListPg = PhotoToolSelectProductPage.getInstance();
	    PhotoToolProductPhotosPage productPhotoPg = PhotoToolProductPhotosPage.getInstance();
		
		logger.info("Change site from product photos page to product list page.");
		productPhotoPg.clickChangeSiteLink();
		productListPg.waitLoading();
	}
	
	/**
	 * 
	 * @param uploadPhotoPath: the path of uploaded photo
	 * @param deletedUsedLocalFilePath: the path of photo will be deleted
	 * @param photoDeses: photo descriptions
	 * @param photoDesIndexes: indexes of photo need to update
	 * @param isDeletingAllPhotos: true: delete all photos
	 * @param deletedPhotoIndexes: indexes of photo need to delete
	 * @param actuallyDelete: true: click ok when confirm dialog displays; false: click cancel
	 * @param displayOrders: photo display orders
	 * @param displayOrderIndexes: indexes of photo need to update displaying orders
	 * @return
	 */
	private String updateCampgroundPhotos(String uploadPhotoPath, String deletedUsedLocalFilePath, String[] photoDeses, Integer[] photoDesIndexes, boolean isDeletingAllPhotos, Integer[] deletedPhotoIndexes, boolean actuallyDelete, String[] displayOrders, Integer[] displayOrderIndexes){
		PhotoToolFacilityPhotosPage campGroundPhotoPg = PhotoToolFacilityPhotosPage.getInstance();
		ConfirmDialogPage confimDialog = ConfirmDialogPage.getInstance();
		logger.info("Update campground photo....");
		
		String removePhotoMes = "";
		
		//Browse photo
		if(!StringUtil.isEmpty(uploadPhotoPath)){
			campGroundPhotoPg.setPhotoPath(uploadPhotoPath);
			campGroundPhotoPg.waitLoading();
		}
		
		//Delete used local file
		if(!StringUtil.isEmpty(deletedUsedLocalFilePath)){
			FileUtil.deleteFile(deletedUsedLocalFilePath);
		}
		
		//Set photo description
		if(photoDeses!=null && photoDeses.length>0){
			campGroundPhotoPg.setPhotoDescriptions(photoDeses, photoDesIndexes);
		}
		
		//Delete photo
		if(isDeletingAllPhotos){
			campGroundPhotoPg.selectAllRemoveThisPhotoCheckBox();
		}else if(deletedPhotoIndexes!=null && deletedPhotoIndexes.length>0){
			campGroundPhotoPg.selectRemoveThisPhotoCheckBoxes(deletedPhotoIndexes);
		}
		
		//Update display order
		if(displayOrders!=null && displayOrders.length>0){
			campGroundPhotoPg.selectDisplayOrders(displayOrders, displayOrderIndexes);
		}

		//Save changes
		campGroundPhotoPg.clickSaveChangesButton();
		Browser.sleep(OrmsConstants.DYNAMIC_SLEEP_BEFORE_CHECK);

		//Get remove photo message
		if(confimDialog.exists()){
			removePhotoMes  = confimDialog.text();
			if(actuallyDelete){
				confimDialog.clickOK();
			}else{
				confimDialog.clickCancel();
			}
		}
		
		campGroundPhotoPg.waitForProgressBarDisapear();
		campGroundPhotoPg.waitLoading();
		
		logger.info("Remove photo message:"+removePhotoMes);
		return removePhotoMes;
	}
	
	public String updateCampgroundPhotos(String uploadPhotoPath, String[] photoDeses, Integer[] photoDesIndexes, Integer[] deletedPhotoIndexes, boolean actuallyDelete, String[] displayOrders, Integer[] displayOrderIndexes){
		return this.updateCampgroundPhotos(uploadPhotoPath, StringUtil.EMPTY, photoDeses, photoDesIndexes, false, deletedPhotoIndexes, actuallyDelete, displayOrders, displayOrderIndexes);
	}
	
	public String reOrderPhoto(String[] displayOrders, Integer[]  displayOrderIndexes){
		return this.updateCampgroundPhotos(StringUtil.EMPTY, StringUtil.EMPTY, null, null, false, null, true, displayOrders, displayOrderIndexes);
	}
	
	public String updatePhotoDescription(String[] photoDeses, Integer[] photoDesIndexes){
		return this.updateCampgroundPhotos(StringUtil.EMPTY, StringUtil.EMPTY, photoDeses, photoDesIndexes, false, null, true, null, null);
	}
	
	public String uploadPhotoAndUpdateDesc(String fileLocation, String[] photoDeses, Integer[] photoDesIndexes){
		return this.updateCampgroundPhotos(fileLocation, StringUtil.EMPTY, photoDeses, photoDesIndexes, false, null, true, null, null);
	}
	
	public String uploadPhotoAndUpdateDesc(String fileLocation, String deletedUsedLocalFilePath, String[] photoDeses, Integer[] photoDesIndexes){
		return this.updateCampgroundPhotos(fileLocation, deletedUsedLocalFilePath, photoDeses, photoDesIndexes, false, null, true, null, null);
	}
	
	public String reloadPhoto(String uploadPhotoPath, String deletedUsedLocalFilePath){
		return this.updateCampgroundPhotos(uploadPhotoPath, deletedUsedLocalFilePath, null, null, false, null, true, null, null);
	}
	
	public String reloadPhoto(String uploadPhotoPath){
		return this.updateCampgroundPhotos(uploadPhotoPath, StringUtil.EMPTY, null, null, false, null, true, null, null);
	}
	
	public String reloadMultiPhotos(String uploadPhotoPath, int numOfPhotos){
		String returnString = "";
		for(int i=0; i<numOfPhotos; i++){
			returnString = this.reloadPhoto(uploadPhotoPath);
		}
		return returnString;
	}
	
	public String removePhoto(Integer[] deletedPhotoIndexes, boolean actuallyDelete){
		return this.updateCampgroundPhotos(StringUtil.EMPTY, StringUtil.EMPTY, null, null, false, deletedPhotoIndexes, actuallyDelete, null, null);
	}
	
	public String removePhoto(Integer[] deletedPhotoIndexes){
		return this.removePhoto(deletedPhotoIndexes, true);
	}
	
	public String removeAllPhotos(){
		return this.updateCampgroundPhotos("", "", null, null, true, null, true, null, null);
	}
	
	public void gotoPrdPhotosPgFromSelectFacilityPg(FacilityData facility, String firstLetter, String prdID, String loop, String showCondition) {
		logger.info("Go to product photos page from select facility page...");
		this.gotoFacilityListPage(facility.contract);
		this.gotoFacilityPhotosPage(StringUtil.EMPTY, facility.stateCode, firstLetter, facility.facilityID);
		this.gotoPrdPhotosPgFromFacilityPhotosPage(prdID, loop, showCondition);
	}
	
	public void gotoPrdPhotosPgFromSelectFacilityPg(FacilityData facility, String prdID) {
		this.gotoPrdPhotosPgFromSelectFacilityPg(facility, "", prdID, "", "");
	}
	
	/**
	 * 
	 * @param uploadPhotoPath: the path of uploaded photo
	 * @param deletedUsedLocalFilePath: the path of photo will be deleted
	 * @param photoDeses: photo descriptions
	 * @param photoDesIndexes: indexes of photo need to update
	 * @param isDeletingAllPhotos: true: delete all photos
	 * @param deletedPhotoIndexes: indexes of photo need to delete
	 * @param actuallyDelete: true: click ok when confirm dialog displays; false: click cancel
	 * @param displayOrders: photo display orders
	 * @param displayOrderIndexes: indexes of photo need to update displaying orders
	 * @return
	 */
	private String updateProductPhotos(String uploadPhotoPath, String deletedUsedLocalFilePath, String[] photoDeses, Integer[] photoDesIndexes, boolean isDeletingAllPhotos, Integer[] deletedPhotoIndexes, boolean actuallyDelete, String[] displayOrders, Integer[] displayOrderIndexes){
		PhotoToolProductPhotosPage prdPhotoPg = PhotoToolProductPhotosPage.getInstance();
		ConfirmDialogPage confimDialog = ConfirmDialogPage.getInstance();
		logger.info("Update product photo....");
		
		String removePhotoMes = "";
		
		//Browse photo
		if(!StringUtil.isEmpty(uploadPhotoPath)){
			prdPhotoPg.setPhotoPath(uploadPhotoPath);
			prdPhotoPg.waitLoading();
		}
		
		//Delete used local file
		if(!StringUtil.isEmpty(deletedUsedLocalFilePath)){
			FileUtil.deleteFile(deletedUsedLocalFilePath);
		}
		
		//Set photo description
		if(photoDeses!=null && photoDeses.length>0){
			prdPhotoPg.setPhotoDescriptions(photoDeses, photoDesIndexes);
		}
		
		//Delete photo
		if(isDeletingAllPhotos){
			prdPhotoPg.selectAllRemoveThisPhotoCheckBox();
		}else if(deletedPhotoIndexes!=null && deletedPhotoIndexes.length>0){
			prdPhotoPg.selectRemoveThisPhotoCheckBoxes(deletedPhotoIndexes);
		}
		
		//Update display order
		if(displayOrders!=null && displayOrders.length>0){
			prdPhotoPg.selectDisplayOrders(displayOrders, displayOrderIndexes);
		}

		//Save changes
		prdPhotoPg.clickSaveChangesButton();
		confimDialog.setDismissible(false);
		browser.waitExists(confimDialog, prdPhotoPg);
		
		//Get remove photo message
		if(confimDialog.exists()){
			removePhotoMes  = confimDialog.text();
			if(actuallyDelete){
				confimDialog.clickOK();
			}else{
				confimDialog.clickCancel();
			}
		}
		
		prdPhotoPg.waitForProgressBarDisapear();
		prdPhotoPg.waitLoading();
		
		logger.info("Remove product photo message:"+removePhotoMes);
		return removePhotoMes;
	}
	
	/** Update Product Photos */
	public String updateProductPhotos(String uploadPhotoPath, String[] photoDeses, Integer[] photoDesIndexes, Integer[] deletedPhotoIndexes, boolean actuallyDelete, String[] displayOrders, Integer[] displayOrderIndexes){
		return this.updateProductPhotos(uploadPhotoPath, StringUtil.EMPTY, photoDeses, photoDesIndexes, false, deletedPhotoIndexes, actuallyDelete, displayOrders, displayOrderIndexes);
	}
	
	/** Reorder Product Photos */
	public void reOrderProductPhoto(String[] displayOrders, Integer[]  displayOrderIndexes){
		this.updateProductPhotos(StringUtil.EMPTY, StringUtil.EMPTY, null, null, false, null, true, displayOrders, displayOrderIndexes);
	}
	
	/** Update First Product Photo Description */
	public void updateFirstPrdPhotoDes(String des) {
		String[] photoDeses = new String[] {des};
		Integer[] photoDesIndexes = new Integer[] {0};
		this.updateProductPhotos(StringUtil.EMPTY, StringUtil.EMPTY, photoDeses, photoDesIndexes, false, null, true, null, null);
	}
	
	/** Update Product Photos Description */
	public void updateProductPhotoDescription(String[] photoDeses, Integer[] photoDesIndexes){
		this.updateProductPhotos(StringUtil.EMPTY, StringUtil.EMPTY, photoDeses, photoDesIndexes, false, null, true, null, null);
	}
	
	/** Upload Product Photo and update photo Description */
	public void uploadProductPhotoAndUpdateDesc(String fileLocation, String[] photoDeses, Integer[] photoDesIndexes){
		this.updateProductPhotos(fileLocation, StringUtil.EMPTY, photoDeses, photoDesIndexes, false, null, true, null, null);
	}
	
	/** Upload Product Photo and update photo Description and display order */
	public void uploadProductPhotoAndUpdateDesc(String fileLocation, String[] photoDeses, Integer[] photoDesIndexes, String[] displayOrders, Integer[] ordersIndexs){
		this.updateProductPhotos(fileLocation, StringUtil.EMPTY, photoDeses, photoDesIndexes, false, null, true, displayOrders, ordersIndexs);
	}
	
	/** Upload Product Photo, delete the photo from the location,  and update photo Description */
	public void uploadProductPhotoAndUpdateDesc(String fileLocation, String deletedUsedLocalFilePath, String[] photoDeses, Integer[] photoDesIndexes){
		this.updateProductPhotos(fileLocation, deletedUsedLocalFilePath, photoDeses, photoDesIndexes, false, null, true, null, null);
	}
	
	/** Upload Product Photo, no change photo description or display order */
	public String uploadProductPhoto(String uploadPhotoPath){
		return this.updateProductPhotos(uploadPhotoPath, StringUtil.EMPTY, null, null, false, null, true, null, null);
	}
	
	/** Remove Product Photo */
	public String removeProductPhoto(Integer[] deletedPhotoIndexes, boolean actuallyDelete){
		return this.updateProductPhotos(StringUtil.EMPTY, StringUtil.EMPTY, null, null, false, deletedPhotoIndexes, actuallyDelete, null, null);
	}
	
	public String removeProductPhoto(Integer[] deletedPhotoIndexes){
		return this.removeProductPhoto(deletedPhotoIndexes, true);
	}
	
	public String removeAllProductPhotos(){
		return this.updateProductPhotos("", "", null, null, true, null, true, null, null);
	}
	
	/**
	 * Upload the same photo up to specific numbers
	 * @param num
	 * @param fileLocation
	 * @return
	 */
	public int uploadProductPhotosUpToSpecificNum(int num, String fileLocation) {
		PhotoToolProductPhotosPage prdPhotoPg = PhotoToolProductPhotosPage.getInstance();
		
		int totalPhotoNum = prdPhotoPg.getNumOfUploadedPrdPhotos();
		while (totalPhotoNum < num) {
			this.uploadProductPhoto(fileLocation);
			prdPhotoPg.waitLoading();
			totalPhotoNum++;
		}
		logger.info(totalPhotoNum + " product photos have been uploaded!");
		return totalPhotoNum;
	}
	
	/**
	 * This method starts from cross over facility, site or entrance details, page, end at facility photo or product photo page
	 * @param actualValue
	 */
	public void crossOverVerification(String pageName, String actualValue){
		PhotoToolFacilityPhotosPage campgroundPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
		PhotoManagerCrossOverToRecPage photoManagerCOTRecPg = PhotoManagerCrossOverToRecPage.getInstance();

		logger.info("Verify facility details or site details or entrance details page displays after click 'See Facility Details' or 'See Site Details' or 'See Entrance Details' link in photo manager.");
		photoManagerCOTRecPg.waitLoading();
		
		if(pageName.equals(PAGETITLE_FACILITY_DETAILS_PAGE)){
			photoManagerCOTRecPg.waitForFacilityDetailsPageLoad();
			photoManagerCOTRecPg.verifyFacilityName(actualValue);
		}else if (pageName.equals(PAGETITLE_SITE_DETAILS_PAGE)){
			photoManagerCOTRecPg.waitForSiteDetailsPageLoad();
			photoManagerCOTRecPg.VerifySiteNum(actualValue);
		}else if (pageName.equals(PAGETITLE_ENTRANCE_DETAILS_PAGE)){
			photoManagerCOTRecPg.waitForEntranceDetailsPageLoad();
			photoManagerCOTRecPg.VerifyEntranceName(actualValue);	
		}else throw new PageNotFoundException("Can't find matched page.");
		
		photoManagerCOTRecPg.closeBrowser();
		campgroundPhotosPg.waitLoading();
	}

	
//	public void addNewSpot(String spotName){
//		MarketingSpotSummaryPage marketingSpotSummaryPg = MarketingSpotSummaryPage.getInstance();
//		if(marketingSpotSummaryPg.isMarketingSpotsExisting(spotName)){
//			logger.info("");
//		}
//	}
}