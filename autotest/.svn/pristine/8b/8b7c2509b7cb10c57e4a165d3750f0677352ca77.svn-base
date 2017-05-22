/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: HF License Product Details page.
 * @Preconditions: 
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 11, 2013
 */
public class HFLicensePurchaseDetailsPage extends HFHeaderBar {
	private String licQtyListReg = "ApurchaseDetails_\\d+";
	private String numOfTagLabel = "# of Tags:";
	private String speciesLabel = "Species:";
	private String weaponLabel = "Weapon:";
	private String datePeriodLabel = "Date Period:";
	private String locationLabel = "Location";
	private String subLocLabel = "Sub-Locations:";
	
	private static HFLicensePurchaseDetailsPage _instance = null;

	public static HFLicensePurchaseDetailsPage getInstance() {
		if (null == _instance)
			_instance = new HFLicensePurchaseDetailsPage();

		return _instance;
	}
	
	protected HFLicensePurchaseDetailsPage() {
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] huntInfoDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "productHuntInfo");
	}
	
	protected Property[] h4() {
		return Property.toPropertyArray(".class", "Html.h4");
	}
	
	protected Property[] attrValue() {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "attrValue");
	}
	
	protected Property[] attr(String attrLabel) {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "attr", ".text", new RegularExpression("^"+attrLabel+".*", false));
	}
	
	protected Property[] huntMoreDetailsSpan() {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "lottery-hunts-showmore-arrow");
	}
	
	protected Property[] huntMoreDetailsDiv() {
		return Property.toPropertyArray(".class", "Html.Div", ".id", "hunt_more_detail_div");
	}
	
	protected Property[] huntParametersSpan() {
		return Property.toPropertyArray(".class", "Html.Span", ".id", "Parameters");
	}
	
	protected Property[] huntLocMapSpan() {
		return Property.toPropertyArray(".class", "Html.Span", ".id", "lottery-hunts-attribute-map-link");
	}
	
	protected Property[] huntImageModalPopChildDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "modalPopLite-child-1", ".id", new RegularExpression("lottery-hunt-image-div-\\d+", false));
	}
	
	protected Property[] huntImageCloseLink() {
		return Property.toPropertyArray(".class", "Html.A", ".id", new RegularExpression("lottery-hunt-image-close-\\d+", false));
	}
	
	protected Property[] huntImageInnerDiv() {
		return Property.toPropertyArray(".class", "Html.Div", ".id", new RegularExpression("lottery-hunt-image-inner-\\d+", false));
	}
	
	protected Property[] huntLocImg(String src) {
		return Property.toPropertyArray(".class", "Html.Img", ".src", new RegularExpression("/webphotos/qa-photos/huntLocImages/.*/"+src.replace(".", "\\."), false));
	} 
	
	protected Property[] huntQuotaProp(){
		return Property.toPropertyArray(".id", "huntQuota");
	}
	
	protected Property[] h2() {
		return Property.toPropertyArray(".class", "Html.h2");
	}
	
	protected Property[] findOtherItemsLinkProp(){
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Find Other (Items|Licences)", false));
	}
	
	protected Property[] pageTitleDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "pagetitle");
	}
	
	protected Property[] outfitterNameSpan() {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "productCode");
	}
	
	protected Property[] licenseYearSelectedLi() {
		return Property.toPropertyArray(".class", "Html.LI", ".className", new RegularExpression("filterItem (first )?(last )?selected", false));
	}
	
	protected Property[] outfitterAttr(String attrLabel) {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "outfitter", ".text", new RegularExpression("^"+attrLabel+".*", false));
	}
	
	protected Property[] priceAttr() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "price", ".text", new RegularExpression("^Price.*", false));
	}
	
	protected Property[] purchaseDetailsAttrsDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "PrivilegePurchaseDetailsKit_purchaseDetails_attrs");
	}
	
	protected Property[] attrFieldViewDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "attributeField VIEW");
	}
	
	protected Property[] notPurchaseInfoDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "notForPurchaseInfo");
	}
	
	protected Property[] contractUsLink() {
		return Property.concatPropertyArray(this.a(), ".href", "/contact.do");
	}
	
	protected Property[] purchasedDetailsTopDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "purchaseDetails");
	}
	
	protected Property[] addToCartBtn() {
		return Property.toPropertyArray(".id", "submitForm_submitForm", ".text", "Add to Cart");
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.Form", ".id", "PrivilegePurchaseDetailsKit_form");
	}
	
	public void clickAddToCart() {
		browser.clickGuiObject(addToCartBtn());
	}
	
	public void selectLicenseQty(String qty) {
		browser.selectDropdownList(".id", new RegularExpression(licQtyListReg, false), qty);
	}
	
	public boolean isLicenseQtyTextDisplayed(){
		return browser.checkHtmlObjectDisplayed(Property.atList(purchaseDetailsAttrsDiv(), attrFieldViewDiv()));
	}
	
	public String getLicenseQtyText(){
		return browser.getObjectText(Property.atList(purchaseDetailsAttrsDiv(), attrFieldViewDiv()));
	}
	
	public void verifyLilcenseQtyText(String theExpected){
		String theActual = getLicenseQtyText();
		if(!MiscFunctions.compareResult("License qty", theExpected, theActual)){
			throw new ErrorOnPageException("License qty is wrong! Please check the details from previous logs.");
		}
	}
	
	public boolean isLicYearSelected(String licYear) {
		return browser.checkHtmlObjectExists(".id", "item_" + licYear, ".className", new RegularExpression("filterItem.*selected", false));
	}
	
	public void clickLicenseYear(String licYear) {
		browser.clickGuiObject(".class", "Html.A", ".title", licYear);
	}
	
	/** Hunt Information Section */
	public String getHuntInfoSecTitle() {
		return browser.getObjectText(Property.atList(this.huntInfoDiv(), this.h4()));
	}
	
	public String getHuntDesAndCode() {
		return browser.getObjectText(Property.atList(this.huntInfoDiv(), this.attrValue()));
	}
	
	private String getAttrValue(String attrLabel) {
		return browser.getObjectText(Property.atList(this.attr(attrLabel), this.attrValue()));
	}
	
	private boolean isAttrSpanExist(String attrLabel) {
		return browser.checkHtmlObjectExists(this.attr(attrLabel));
	}
	
	public String getNumOfTags() {
		return this.getAttrValue(numOfTagLabel);
	}
	
	public boolean isNumOfTagsExist() {
		return this.isAttrSpanExist(numOfTagLabel);
	}
	
	public String getSpecies() {
		return this.getAttrValue(speciesLabel);
	}

	public boolean isSpeciesExist() {
		return this.isAttrSpanExist(speciesLabel);
	}
	
	public String getWeapon() {
		return this.getAttrValue(weaponLabel);
	}

	public boolean isWeaponExist() {
		return this.isAttrSpanExist(weaponLabel);
	}
	
	public String getDatePeriod() {
		return this.getAttrValue(datePeriodLabel);
	}

	public boolean isDatePeriodExist() {
		return this.isAttrSpanExist(datePeriodLabel);
	}
	
	public String getLocation() {
		return this.getAttrValue(locationLabel);
	}

	public boolean isLocationExist() {
		return this.isAttrSpanExist(locationLabel);
	}
	
	public String getSubLoc() {
		return this.getAttrValue(subLocLabel);
	}

	public boolean isSubLocExist() {
		return this.isAttrSpanExist(subLocLabel);
	}
	
	public boolean isHuntMoreDetailsExist() {
		return browser.checkHtmlObjectExists(this.huntMoreDetailsDiv());
	}
	
	public String getHuntMoreDetailsTitle() {
		return browser.getObjectText(Property.atList(this.huntInfoDiv(), this.huntMoreDetailsSpan()));
	}
	
	public String getHuntMoreDetails() {
		return browser.getObjectText(this.huntMoreDetailsDiv());
	}
	
	public List<String> getHuntParams() {
		List<String> params = browser.getObjectsText(this.huntParametersSpan());
		for (int i=0; i < params.size(); i++) {
			params.set(i, params.get(i).replace(";", ""));
		}
		return params;
	}
	
	public String getHuntParameters() {
		List<String> info = browser.getObjectsText(this.huntParametersSpan());
		String allParamInfo = "";
		for (String param : info) {
			allParamInfo += param;
		}
		return allParamInfo;
	}
	
	public boolean isHuntLocMapLinkExist() {
		return browser.checkHtmlObjectExists(this.huntLocMapSpan());
	}
	
	public String getHuntLocMapLinkText() {
		return browser.getObjectText(this.huntLocMapSpan());
	}
	
	public boolean compareHuntInfo(HuntInfo hunt) {
		boolean result = true;
		result &= MiscFunctions.compareString("hunt description and code", hunt.getDescription() + " [# " + hunt.getHuntCode() + "]", this.getHuntDesAndCode());
		if(StringUtil.notEmpty(hunt.getNumOfTagQty())){
			result &= MiscFunctions.compareString("num of tags", hunt.getNumOfTagQty(), this.getNumOfTags());	
		}
		result &= MiscFunctions.compareString("species", hunt.getSpecie() + (StringUtil.notEmpty(hunt.getSpecieSubType()) ? " - "+hunt.getSpecieSubType() : ""), this.getSpecies());
		// weapon info
		if (StringUtil.notEmpty(hunt.getWeapon())) {
			result &= MiscFunctions.compareString("weapon", hunt.getWeaponDes(), this.getWeapon());
		} else {
			result &= MiscFunctions.compareResult("weapon attribute exist", false, this.isWeaponExist());
		}
		// date period info
		String datePeriodInfo = hunt.getDatePeriodInfo().getDatePeriodInfoForWeb(hunt.getLicYear()).replace("(", "\\(\\s*").replace(")", "\\)");
		if (StringUtil.notEmpty(hunt.getHuntDatePeriodInfo()) && StringUtil.notEmpty(datePeriodInfo)) {
			result &= MiscFunctions.matchString("date period", this.getDatePeriod(), datePeriodInfo);
		} else {
			result &= MiscFunctions.compareResult("date period exist", false, this.isDatePeriodExist());
		}
		// location info
		if (StringUtil.notEmpty(hunt.getHuntLocationInfo())) {
			result &= MiscFunctions.compareString("location info", hunt.getHuntLocationInfo().replace("-", " - "), this.getLocation());
		} else {
			result &= MiscFunctions.compareResult("location info exist", false, this.isLocationExist());
		}
		// sub location info
		if (hunt.getLocationInfo() != null && hunt.getLocationInfo().getSubLocations() != null) {
			String subLocationInfo = hunt.getLocationInfo().getAllSubLocationInfo();
			result &= MiscFunctions.compareString("sub location info", subLocationInfo, this.getSubLoc());
		} else {
			result &= MiscFunctions.compareResult("sub location info exist", false, this.isSubLocExist());
		}
		// hunt parameters
		if (hunt.getHuntParams() != null) {
			result &= MiscFunctions.compareResult("hunt parameters number", hunt.getHuntParams().size(), this.getHuntParams().size());
			List<String> exp = hunt.getAllHuntParamsInfo();
			Collections.sort(exp);
			List<String> actual = this.getHuntParams();
			Collections.sort(actual);
			if(exp.equals(actual)) {
				result &= true;
				logger.info("hunt parameters info are correct as " + exp.toString());
			} else {
				result &= false;
				logger.error("hunt parameters info are wrong. Exp: " + exp.toString() + "; Actual: " + actual.toString());
			}
		} 
		
		//More details link exists or not
		result &= MiscFunctions.compareResult("additional information exist", (hunt.getHuntParams() != null && hunt.getHuntParams().size()>0) || StringUtil.notEmpty(hunt.getLocationInfo().getImage()), this.isHuntMoreDetailsExist());
		
		
		// hunt location map link
		result &= MiscFunctions.compareResult("location map link", 
				hunt.getLocationInfo() != null && StringUtil.notEmpty(hunt.getLocationInfo().getImage()), 
				this.isHuntLocMapLinkExist());
		
		return result;
	}
	
	public void clickHuntMoreInfoSpan() {
		browser.clickGuiObject(huntMoreDetailsSpan());
	}
	
	public void clickHuntImageMapLink() {
		browser.clickGuiObject(this.huntLocMapSpan());
	}
	
	public boolean isHuntImagePopDisplayed() {
		return browser.checkHtmlObjectDisplayed(this.huntImageModalPopChildDiv());
	}
	
	public boolean isHuntImageExist(String imgNm) {
		return browser.checkHtmlObjectExists(Property.atList(this.huntImageInnerDiv(), this.huntLocImg(imgNm)));
	}
	
	public void closeHuntImagePop() {
		browser.clickGuiObject(this.huntImageCloseLink());
		this.waitLoading();
	}
	
	public boolean checkHuntQuotaExists(){
		return browser.checkHtmlObjectExists(huntQuotaProp());
	}
	
	public String getHuntQuota(){
		return browser.getObjectText(huntQuotaProp());
	}
	
	public void verifyHuntQuota(String theExpected){
		String theActual = getHuntQuota();
		if(!MiscFunctions.compareResult("Hunt quota in Licence Details page", theExpected, theActual)){
			throw new ErrorOnPageException("Hunt quota is wrong in Licence Details page. Please find details from previous log.");
		}
	}
	
	public void clickFindOtherItems() {
		browser.clickGuiObject(this.findOtherItemsLinkProp());
	}
	
	public String getPrivName() {
		return browser.getObjectText(Property.atList(this.pageTitleDiv(), this.h2()));
	}
	
	public String getPrivOutfitterName() {
		String text = browser.getObjectText(Property.atList(this.pageTitleDiv(), this.outfitterNameSpan()));
		text = text.replace("[", StringUtil.EMPTY).replace("]", StringUtil.EMPTY);
		return text;
	}
	
	public String getPrivLicenseYear() {
		return browser.getObjectText(this.licenseYearSelectedLi());
	}
	
	public String getPrivOutfitterPermitNum() {
		return browser.getObjectText(Property.atList(this.outfitterAttr("Outfitter Permit #"), this.attrValue()));
	}
	
	public String getAuthPrivNum() {
		return browser.getObjectText(Property.atList(this.outfitterAttr("Auth. #"), this.attrValue()));
	}
	
	public String getPrivPrice() {
		String price = browser.getObjectText(Property.atList(this.priceAttr(), this.attrValue()));
		price = price.replace("$", StringUtil.EMPTY);
		return price;
	}
	
	public PrivilegeInfo getPrivInfo() {
		PrivilegeInfo priv = new PrivilegeInfo();
		priv.name = this.getPrivName();
		priv.outfitterNm = this.getPrivOutfitterName();
		priv.licenseYear = this.getPrivLicenseYear();
		priv.outfitterPermitNum = this.getPrivOutfitterPermitNum();
		priv.authPrivNum = this.getAuthPrivNum();
		priv.creationPrice = this.getPrivPrice();
		priv.qty = this.getLicenseQtyText();
		return priv;
	}
	
	public void verifyPrivilegeDetails(PrivilegeInfo expPriv) {
		PrivilegeInfo actualPriv = this.getPrivInfo();
		boolean result = true;
		result &= MiscFunctions.compareString("Privilege name", expPriv.name, actualPriv.name);
		result &= MiscFunctions.compareString("Privilege outfitter name", expPriv.outfitterNm, actualPriv.outfitterNm);
		result &= MiscFunctions.compareString("Privilege license year", expPriv.licenseYear, actualPriv.licenseYear);
		result &= MiscFunctions.compareString("Privilege outfitter permit number", expPriv.outfitterPermitNum, actualPriv.outfitterPermitNum);
		result &= MiscFunctions.compareString("Authorized privilege number", expPriv.authPrivNum, actualPriv.authPrivNum);
		result &= MiscFunctions.compareResult("Privilege price", expPriv.creationPrice, actualPriv.creationPrice);
		result &= MiscFunctions.compareString("Privilege quantity", expPriv.qty, actualPriv.qty);
		if (!result) {
			throw new ErrorOnPageException("Privilege info is wrong! Check logger error above!");
		}
		logger.info("Privilege info is correct!");
	}
	
	public void verifyPrivilegeName(String privName){
		String fromUI = getPrivName();
		if(privName.equals(fromUI)){
			logger.info("Successfully verify privilege name-"+privName);
		}else throw new ErrorOnPageException("Failed to verify privilege name", privName, fromUI);
	}
	
	public boolean isNotPurchaseInfoExist() {
		return browser.checkHtmlObjectExists(this.notPurchaseInfoDiv());
	}
	
	public String getNotPurchaseInfoMsg() {
		return browser.getObjectText(this.notPurchaseInfoDiv());
	}
	
	public boolean isContactUsLinkExistInMsg() {
		return browser.checkHtmlObjectExists(Property.atList(this.notPurchaseInfoDiv(), this.contractUsLink()));
	}
	
	public boolean isPriceInfoExist() {
		return browser.checkHtmlObjectExists(this.priceAttr());
	}
	
	public boolean isPurchaseDetailsInfoExist() {
		return browser.checkHtmlObjectExists(this.purchasedDetailsTopDiv());
	}
	
	public boolean isAddToCartBtnExist() {
		return browser.checkHtmlObjectExists(this.addToCartBtn());
	}
}
