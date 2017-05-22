package com.activenetwork.qa.awo.keywords.web;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.web.AttractionInfo;
import com.activenetwork.qa.awo.pages.web.lam.LamHomePage;
import com.activenetwork.qa.awo.pages.web.lam.LamLoginPage;
import com.activenetwork.qa.awo.pages.web.lam.LamMyListingsPage;
import com.activenetwork.qa.awo.pages.web.lam.LamNewListingStep1Page;
import com.activenetwork.qa.awo.pages.web.lam.LamNewListingStep2Page;
import com.activenetwork.qa.awo.pages.web.lam.LamNewListingStep3Page;
import com.activenetwork.qa.awo.pages.web.lam.LamNewListingStep4Page;
import com.activenetwork.qa.awo.pages.web.lam.LamPaymentPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.keyword.Keyword;

public class Lam extends Keyword {
	private static Lam _instance = null;

	public static Lam getInstance() {
		if (null == _instance)
			_instance = new Lam();

		return _instance;
	}

	protected Lam() {
	}
	
	/**
	 * Invoke a new browser with the given URL. 
	 * @param url - the url to open	 
	 */
	public void invokeURL(String url) {
	  	LamHomePage lamHmPg=LamHomePage.getInstance();
		logger.info("Invoke " + url);
		browser.closeAllBrowsers();
		browser.open(url);
		lamHmPg.waitLoading();
	}
	
	public void login(String user,String password) {
	  	LamHomePage hmPg=LamHomePage.getInstance();
	  	LamLoginPage loginPg=LamLoginPage.getInstance();
	  	LamMyListingsPage listingPg=LamMyListingsPage.getInstance();
	  	
	  	logger.info("Login to Local Attraction Service");
	  	hmPg.clickSignIn();
	  	
	  	loginPg.waitLoading();
	  	loginPg.setUserName(user);
	  	loginPg.setPassword(password);
	  	loginPg.clickSignIn();
	  	
	  	listingPg.waitLoading();
	}
	
	public void logout(){
		LamHomePage hmPg=LamHomePage.getInstance();
		
		logger.info("LogOut Local Attraction Service.");
		hmPg.clickSignOut();
		hmPg.waitLoading();		
	}
	
	public void invokeLam(String url,String user,String password) {
		  	invokeURL(url);
		   	login(user,password);
	}
	
	public String[] createNewListIntoPaymentPage(AttractionInfo info) {
	  	LamPaymentPage paymentPg=LamPaymentPage.getInstance();
	  	
	  	logger.info("Create a new Listing...");
	  	
	  	String msg="";
	  	
	  	gotoCreateNewListStep1Page();
	    setAttractionInfoOnStep1Page(info);
	  	gotoCreateNewListStep2Page();
	  	gotoCreateNewListStep3Page();
	  	setAttractionInfoOnStep3Page(info);
	  	msg = uploadLogoAndPic(info.logoFile, info.pictureFile);
	  	gotoCreateNewListStep4Page();	  	
	  	gotoMakePaymentPage();
	  	
	  	paymentPg.waitLoading();
	  	String listID=paymentPg.getNewListID();
	  	
	  	String[] toReturn;
	  	if(msg.length()>0) {
	  	  	toReturn=new String[2];
	  	  	toReturn[0]=listID;
	  	  	toReturn[1]=msg;
	  	} else {
	  	  	toReturn=new String[1];
	  	  	toReturn[0]=listID;
	  	}
	  	
	  	return toReturn;
	}
	
	
	/**
	 * Upload Logo and PIC on create new list step3 page, start and ends at step3 page
	 * @param logoFile: the URL of logo file
	 * @param picFile: the URL of picture file
	 * @return the string to indicate whether upload logo and pic failured
	 */
	public String uploadLogoAndPic(String logoFile, String picFile){
		LamNewListingStep3Page step3Pg=LamNewListingStep3Page.getInstance();
		String msg = "";
		
		boolean successful=step3Pg.uploadLogo(logoFile);
	  	if(!successful) {
	  	  	msg+="Logo upload failed";
	  	}
	  	successful=step3Pg.uploadPicture(picFile);
	  	if(!successful) {
	  	  	if(msg.length()>0) {
	  	  	  	msg+=";";
	  	  	}
	  	  	msg+="Logo upload failed";
	  	}
	  	
	  	return msg;
	}
	
	/**
	 * verify whether the google map displayed or not on create new list step2 and step4 pages
	 * 
	 */
	public void verifyGoogleMapDisplayOnCreateNewListPages(){		
		LamNewListingStep2Page step2Pg=LamNewListingStep2Page.getInstance();
		LamNewListingStep4Page step4Pg=LamNewListingStep4Page.getInstance();
		
		Browser.sleep(OrmsConstants.DYNAMIC_SLEEP_BEFORE_CHECK);
		if (step2Pg.exists()){
			step2Pg.waitForMapAPin();

			if(!step2Pg.verifyGoogleMapDisplay()){
				throw new ErrorOnPageException("Google map loading failed on 'step2: Verify Location and Target' page");
			}
		} 

		if (step4Pg.exists()){
			step4Pg.waitForMapAPin();
			if(!step4Pg.verifyGoogleMapDisplay()){
				throw new ErrorOnPageException("Google map loading failed on 'step4: Review and Save Creation' page");
			}
		}
	}
	
	/**
	 * Goto create new listing step1 page, start from My Listings page ends at create new listing step1 page.
	 */
	public void gotoCreateNewListStep1Page(){
		LamMyListingsPage listingPg=LamMyListingsPage.getInstance();
	  	LamNewListingStep1Page step1Pg=LamNewListingStep1Page.getInstance();
	
	  	logger.info("Create a new Listing...");

	  	listingPg.clickMyListing();
	  	listingPg.waitLoading();
	  	listingPg.clickCreateNewListing();
	  	step1Pg.waitLoading();
	}
	
	/**
	 * set attraction info on create new listing step1 page, start and end at step1 page
	 * @param info
	 */
	public void setAttractionInfoOnStep1Page(AttractionInfo info){
		LamNewListingStep1Page step1Pg=LamNewListingStep1Page.getInstance();

		step1Pg.clearAndStartOver();
		step1Pg.setAttractionName(info.attractionName);
		step1Pg.setPhoneNumber(info.phone);
		step1Pg.selectCategory(info.category);
		step1Pg.setStreetName(info.streetName);
		step1Pg.setCityRegion(info.city);
		step1Pg.selectStateProvince(info.state);
		step1Pg.setZipCode(info.zip);
	}
		
	/**
	 * Goto create new listing step2 page, start from step1 page ends at step2 page
	 */
	public void gotoCreateNewListStep2Page(){
		LamNewListingStep1Page step1Pg=LamNewListingStep1Page.getInstance();
		LamNewListingStep2Page step2Pg=LamNewListingStep2Page.getInstance();
		step1Pg.clickNextStep();
		Object page = browser.waitExists(step2Pg,step1Pg);
	  	if(page == step1Pg) {  
	  	  	throw new PageNotFoundException(step1Pg.getErrorMessage());
	  	}
	}
	
	/**
	 * Goto create new listing step3 page, start from step2 page ends at step3 page
	 */
	public void gotoCreateNewListStep3Page(){
		LamNewListingStep2Page step2Pg=LamNewListingStep2Page.getInstance();
		LamNewListingStep3Page step3Pg=LamNewListingStep3Page.getInstance();
		step2Pg.clickNextStep();
		step3Pg.waitLoading();
	}
	
	/**
	 * set attraction info on create new listing step3 page, start and end at step3 page
	 * @param info
	 */
	public void setAttractionInfoOnStep3Page(AttractionInfo info){
		LamNewListingStep3Page step3Pg=LamNewListingStep3Page.getInstance();
		step3Pg.setSloganOrTagline(info.slogan);
	  	step3Pg.setDescription(info.description);
	  	step3Pg.selectAcceptSpellingAsIs(info.acceptSpellingAsIs);
	  	//TODO upload PIC info
	  	step3Pg.setLinkDescription(info.linkDescription);
	  	step3Pg.setLinkUrl(info.linkAddress);
	}
	
	/**
	 * Goto create new listing step4 page, start from step3 page ends at step4 page
	 */
	public void gotoCreateNewListStep4Page(){
		LamNewListingStep3Page step3Pg=LamNewListingStep3Page.getInstance();
		LamNewListingStep4Page step4Pg=LamNewListingStep4Page.getInstance();
		step3Pg.clickNextStep();
		Object page = browser.waitExists(step4Pg,step3Pg);
	  	if(page == step3Pg) {
	  	  	throw new PageNotFoundException(step3Pg.getErrorMessage());
	  	}
	}
	
	/**
	 * Goto create payment page, start from step4 page ends at payment page
	 */
	public void gotoMakePaymentPage(){
		LamNewListingStep4Page step4Pg=LamNewListingStep4Page.getInstance();
		LamPaymentPage paymentPg=LamPaymentPage.getInstance();
		
		step4Pg.clickSaveCreation();
		paymentPg.waitLoading();
	}
	
	/**
	 * verify whether create new list successful or not on by getting the new created listID, start and ends at payment page
	 */
	public void verifyCreateNewListSuccessful(){
		LamPaymentPage paymentPg=LamPaymentPage.getInstance();
		String listID=paymentPg.getNewListID();
		if (listID == null || listID == ""){
			throw new ErrorOnPageException("create new attraction list failured");
		}
	}
	
	/**
	 * set the payment info on LAM payment page, start from payment page ends at login page
	 * @param pay
	 */
	public void makePayment(Payment pay){
		LamPaymentPage paymentPg=LamPaymentPage.getInstance();
		LamLoginPage loginPg=LamLoginPage.getInstance();
		paymentPg.setPaymentInfo(pay);
		paymentPg.clickAcceptBtn();
		loginPg.waitLoading();		
	}
}
