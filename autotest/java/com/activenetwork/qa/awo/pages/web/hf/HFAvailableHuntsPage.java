package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.Timer;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Sep 6, 2013
 */
public class HFAvailableHuntsPage extends HFHeaderBar {
	private String speciesLabel = "Species:";
	private String weaponLabel = "Weapon:";
	private String datePeriodLabel = "Date Period:";
	private String locationLabel = "Location";
	private String subLocLabel = "Sub-Locations:";

	private static HFAvailableHuntsPage _instance = null;

	public static HFAvailableHuntsPage getInstance() {
		if (null == _instance)
			_instance = new HFAvailableHuntsPage();

		return _instance;
	}

	protected HFAvailableHuntsPage() {
	}


	public Property[] huntsGroupProp(){
		return Property.toPropertyArray(".id", "huntsGroup");
	}

	public Property[] huntQuotaProp(){
		return Property.toPropertyArray(".id", "huntQuota");
	}

	public Property[] pageTitleProp(){
		return Property.toPropertyArray(".id", "pagetitle");
	}

	public Property[] pageTitleDescProp(){
		return Property.toPropertyArray(".id", "titleDescription");
	}

	public Property[] privilegesProp(){
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "privileges");
	}

	public Property[] listDirectiveProp(){
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "listDirective");
	}

	protected Property[] attrValue() {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "attrValue");
	}

	protected Property[] huntMoreDetailsDiv() {
		return Property.toPropertyArray(".class", "Html.Div", ".id", "hunt_more_detail_div");
	}

	protected Property[] huntInfoDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "productHuntInfo");
	}

	protected Property[] huntMoreDetailsSpan() {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "lottery-hunts-showmore-arrow");
	}

	protected Property[] huntLocMapSpan() {
		return Property.toPropertyArray(".class", "Html.Span", ".id", "lottery-hunts-attribute-map-link");
	}

	protected Property[] huntGroup(String huntID){
		return Property.toPropertyArray(".id", "PrivilegeHuntSelectionKit_huntsGroup"+huntID+"_attrs");
	}

	protected Property[] huntAttrProp(String attrLabel){
		return Property.toPropertyArray(".class", "Html.Span", ".className", new RegularExpression("attr (last|first)\\s+", false), ".text", new RegularExpression("^"+attrLabel+".*", false));
	}

	protected Property[] availableHuntsFromProp(){
		return Property.toPropertyArray(".id", "PrivilegeHuntSelectionKit_form");
	}

	protected Property[] nextLinkProp(){
		return Property.toPropertyArray(".class", "Html.A", ".id", "LPrivilegeHuntSelectionKit_huntsList_next");
	}

	protected Property[] previousLinkProp(){
		return Property.toPropertyArray(".class", "Html.A", ".id", "LPrivilegeHuntSelectionKit_huntsList_previous");
	}

	protected Property[] listControlProp(){
		return Property.toPropertyArray(".className", "listControl_hdr");
	}

	protected Property[] mesErrorProp(String errorMes){
		return Property.toPropertyArray(".className", "msg error", ".text", new RegularExpression(errorMes, false));
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(huntsGroupProp());
	}

	public int getHuntQuota(String huntID){
		Property[] p = Property.toPropertyArray(".id", "huntsGroup"+huntID);
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p, huntQuotaProp()));

		do{
			if(objs.length<1 && checkNext()){
				gotoNext();
			}
			objs = browser.getHtmlObject(Property.atList(p, huntQuotaProp()));
		}while (objs.length<1 && checkNext());

		if(objs.length<1){
			throw new ObjectNotFoundException("Hunt (ID:"+huntID+" quota property can't be found.)");
		}

		String[] results = RegularExpression.getMatches(objs[0].text(), "\\d+");
		if(results.length<1){
			throw new ErrorOnPageException("Hunt (ID:"+huntID+" quota can't be found.)");
		}

		Browser.unregister(objs);
		return Integer.valueOf(results[0]);
	}

	public int[] getHuntQuotas(String[] huntIDs){
		int[] quotas = new int[huntIDs.length];
		for(int i=0; i<quotas.length; i++){
			quotas[i] = getHuntQuota(huntIDs[i]);
			gotoFirstPg();
		}
		return quotas;
	}

	public void verifyHuntQuota(String huntID, int expectedQuota){
		int actualQuota = getHuntQuota(huntID);
		if(!MiscFunctions.compareResult("Hunt (ID:"+huntID+") quota", expectedQuota, actualQuota)){
			throw new ErrorOnPageException("Hunt (ID:"+huntID+") quota is wrong! Please find details from previous logs.");
		}
	}

	public void verifyHuntQuota(String[] huntIDs, int[] expectedQuotas){
		boolean result = true;
		int[] actualQuotas = getHuntQuotas(huntIDs);
		for(int i=0; i<huntIDs.length; i++){
			result &= MiscFunctions.compareResult("Hunt (ID:"+huntIDs[i]+") quota", expectedQuotas[i], actualQuotas[i]);
		}

		if(!result){
			throw new ErrorOnPageException("Not all hunts' quota are correct. Please check details from previous logs.");
		}
	}

	public void clickSelect(String huntID){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("/privilegeHuntSelection\\.do\\?addToCart="+huntID, false));
	}

	public String getPageTitle(){
		return browser.getObjectText(pageTitleProp());
	}

	public boolean isPageTitleDescExist(){
		return browser.checkHtmlObjectExists(pageTitleDescProp());
	}

	/**
	 * Such as 
	 * HFSK LeftoverPriv006
	 * Licence Year: 2013
	 * @return
	 */
	public String getPrivileges(){
		return browser.getObjectText(privilegesProp());
	}

	public String getListDirective(){
		return browser.getObjectText(listDirectiveProp());
	}

	public IHtmlObject[] getHuntGroupObjs(String huntID){
		IHtmlObject[] objs = browser.getHtmlObject(huntGroup(huntID));
		if(objs.length<1){
			throw new ObjectNotFoundException("Hunt group can't be found when hunt ID="+huntID);
		}
		return objs;
	}


	public boolean isHuntExisting(String huntID){
		return browser.checkHtmlObjectExists(huntGroup(huntID));
	}

	protected IHtmlObject[] attr(String attrLabel, String huntID) {
		browser.searchObjectWaitExists(Property.atList(huntGroup(huntID), huntAttrProp(attrLabel)));
		return browser.getHtmlObject(Property.atList(huntGroup(huntID), huntAttrProp(attrLabel)));
	}

	private String getAttrValue(String attrLabel, String huntID) {
		IHtmlObject[] objs= browser.getHtmlObject(this.attrValue(), attr(attrLabel, huntID)[0]);
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find attr value when huntID="+huntID+" and attrLalbe="+attrLabel);
		}

		String value = objs[0].text();
		Browser.unregister(objs);
		return value;
	}

	private boolean isAttrSpanExist(String attrLabel, String huntID) {
		return browser.checkHtmlObjectExists(Property.atList(huntGroup(huntID), huntAttrProp(attrLabel)));
	}

	public String getSpecies(String huntID) {
		return this.getAttrValue(speciesLabel, huntID);
	}

	public boolean isSpeciesExist(String huntID) {
		return this.isAttrSpanExist(speciesLabel, huntID);
	}

	public String getWeapon(String huntID) {
		return this.getAttrValue(weaponLabel, huntID);
	}

	public boolean isWeaponExist(String huntID) {
		return this.isAttrSpanExist(weaponLabel, huntID);
	}

	public String getDatePeriod(String huntID) {
		return this.getAttrValue(datePeriodLabel, huntID);
	}

	public boolean isDatePeriodExist(String huntID) {
		return this.isAttrSpanExist(datePeriodLabel, huntID);
	}

	public String getLocation(String huntID) {
		return this.getAttrValue(locationLabel, huntID);
	}

	public boolean isLocationExist(String huntID) {
		return this.isAttrSpanExist(locationLabel, huntID);
	}

	public String getSubLoc(String huntID) {
		return this.getAttrValue(subLocLabel, huntID);
	}

	public boolean isSubLocExist(String huntID) {
		return this.isAttrSpanExist(subLocLabel, huntID);
	}

	public boolean isHuntMoreDetailsExist() {
		return browser.checkHtmlObjectExists(this.huntMoreDetailsDiv());
	}

	public String getHuntMoreDetailsTitle(String huntID) {
		return browser.getObjectText(Property.atList(huntGroup(huntID), this.huntMoreDetailsSpan()));
	}

	public String getHuntMoreDetails() {
		return browser.getObjectText(this.huntMoreDetailsDiv());
	}

	public boolean isHuntLocMapLinkExist() {
		return browser.checkHtmlObjectExists(this.huntLocMapSpan());
	}

	public String getHuntLocMapLinkText(String huntID) {
		return browser.getObjectText(Property.atList(huntGroup(huntID), this.huntLocMapSpan()));
	}

	public String getHuntDesAndCode(String huntID) {
		return browser.getObjectText(Property.atList(huntGroup(huntID), this.attrValue()));
	}

	public boolean checkHuntQuotaExists(String huntID){
		return browser.checkHtmlObjectExists(Property.atList(huntGroup(huntID), huntQuotaProp()));
	}

	public boolean compareHuntInfo(HuntInfo hunt) {
		logger.info("Verify attribute values for hunt ID:"+ hunt.getHuntId());
		boolean result = true;

		result &= MiscFunctions.compareString("hunt description and code", hunt.getDescription() + " [# " + hunt.getHuntCode() + "]", this.getHuntDesAndCode(hunt.getHuntId()));
		result &= MiscFunctions.compareString("species", hunt.getSpecie() + (StringUtil.notEmpty(hunt.getSpecieSubType()) ? " - "+hunt.getSpecieSubType() : ""), this.getSpecies(hunt.getHuntId()));

		// weapon info
		if (StringUtil.notEmpty(hunt.getWeapon())) {
			result &= MiscFunctions.compareString("weapon", hunt.getWeaponDes(), this.getWeapon(hunt.getHuntId()));
		} else {
			result &= MiscFunctions.compareResult("weapon attribute exist", false, this.isWeaponExist(hunt.getHuntId()));
		}

		// date period info
		String datePeriodInfo = hunt.getDatePeriodInfo().getDatePeriodInfoForWeb(hunt.getLicYear());
		if (StringUtil.notEmpty(hunt.getHuntDatePeriodInfo()) && StringUtil.notEmpty(datePeriodInfo)) {
			result &= MiscFunctions.compareString("date period", datePeriodInfo, this.getDatePeriod(hunt.getHuntId()));
		} else {
			result &= MiscFunctions.compareResult("date period exist", false, this.isDatePeriodExist(hunt.getHuntId()));
		}

		// location info
		if (StringUtil.notEmpty(hunt.getHuntLocationInfo())) {
			result &= MiscFunctions.compareString("location info", hunt.getHuntLocationInfo().replace("-", " - "), this.getLocation(hunt.getHuntId()));
		} else {
			result &= MiscFunctions.compareResult("location info exist", false, this.isLocationExist(hunt.getHuntId()));
		}

		// sub location info
		if (hunt.getLocationInfo() != null && hunt.getLocationInfo().getSubLocations() != null) {
			String subLocationInfo = hunt.getLocationInfo().getAllSubLocationInfo();
			result &= MiscFunctions.compareString("sub location info", subLocationInfo, this.getSubLoc(hunt.getHuntId()));
		} else {
			result &= MiscFunctions.compareResult("sub location info exist", false, this.isSubLocExist(hunt.getHuntId()));
		}

		//More details link exists or not
		result &= MiscFunctions.compareResult("additional information exist", (hunt.getHuntParams() == null) && StringUtil.isEmpty(hunt.getLocationInfo().getImage()), !this.isHuntMoreDetailsExist());


		//hunt location map link
		result &= MiscFunctions.compareResult("location map link", 
				hunt.getLocationInfo() != null && StringUtil.notEmpty(hunt.getLocationInfo().getImage()), 
				this.isHuntLocMapLinkExist());

		return result;
	}

	public String getAvailableHuntPgContent(){
		return browser.getObjectText(availableHuntsFromProp());
	}

	public void synHunts(String preValue){
		logger.info("previousValue:"+preValue);
		String currentValue; 
		long maxWaitTime=OrmsConstants.FILE_DIALOG_LONG_SLEEP*4;

		boolean isChanged=false;
		Timer time = new Timer();

		do{
			currentValue = this.getAvailableHuntPgContent();
			isChanged = preValue.equals(currentValue);
			logger.info("currentValue:"+currentValue);

		}while(time.diffLong() < maxWaitTime && isChanged);
		if(isChanged) {
			throw new ItemNotFoundException("Syn hunts timed out in "+maxWaitTime+" ms");
		}
	}

	public boolean checkPrevious(){
		return browser.checkHtmlObjectExists(previousLinkProp());
	}

	public void clickPrevious(){
		browser.clickGuiObject(previousLinkProp());
	}

	public boolean checkNext(){
		return browser.checkHtmlObjectExists(nextLinkProp());
	}

	public void clickNext(){
		browser.clickGuiObject(nextLinkProp());
	}

	public boolean gotoNext(){		
		boolean flag = this.checkNext();
		this.waitLoading();
		String value = this.getAvailableHuntPgContent();
		if(flag){
			this.clickNext();
			this.waitLoading();
			this.synHunts(value);
			browser.waitExists();
		}
		return flag;
	}

	public boolean gotoPrevious(){		
		boolean flag = this.checkPrevious();
		this.waitLoading();
		String value = this.getAvailableHuntPgContent();
		if(flag){
			this.clickPrevious();
			this.waitLoading();
			this.synHunts(value);
			browser.waitExists();
		}
		return flag;
	}

	public void gotoFirstPg(){
		if(checkPrevious()){
			gotoPrevious();
		}
	}

	public void gotoLastPg(){
		if(checkNext()){
			gotoNext();
		}
	}
	public String getListControlContent(){
		return browser.getObjectText(listControlProp());
	}

	public boolean isErrorMsgExist(String errorMes) {
		return browser.checkHtmlObjectExists(mesErrorProp(errorMes));
	}

	public void verifyErrorMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isErrorMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}
}
