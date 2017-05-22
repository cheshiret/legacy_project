/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: HF Web License Category Product List page 
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Feb 25, 2013
 */
public class HFLicenseCategoryPrdListPage extends HFHeaderBar {
	private static HFLicenseCategoryPrdListPage _instance = null;

	public static HFLicenseCategoryPrdListPage getInstance() {
		if (null == _instance)
			_instance = new HFLicenseCategoryPrdListPage();

		return _instance;
	}
	
	protected HFLicenseCategoryPrdListPage() {
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] filterPanelDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "leftSideFilters");
	}
	
	protected Property[] filterItemLink(String title) {
		return Property.toPropertyArray(".class", "Html.A", ".title", title);
	}
	
	protected Property[] licYearFilterItemLI(String licYear) {
		return Property.toPropertyArray(".id", "item_" + licYear);
	}
	
	protected Property[] licYearFilterItemSpan(String licYear) {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "item", ".text", licYear);
	}
	
	protected Property[] licYearFilterItemLink(String licYear) {
		return Property.toPropertyArray(".class", "Html.A", ".title", licYear);
	}
	
	protected Property[] clearAllFiltersDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".title", "Clear All Filters");
	}
	
	protected Property[] findOtherPrivsLink() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Find Other Licences", false));
	}
	
	protected Property[] privilegeOutfitterNmSpanProp() {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "productCode");
	}
	
	protected Property[] privilegeAttrSpanProp(String attrLabel) {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "attribute", ".text", new RegularExpression("^"+attrLabel+".*", false));
	}
	
	protected Property[] nextLink() {
		return Property.toPropertyArray(".class", "Html.A", ".id", "LCategoryPrivilegesKit_privListLayout_next");
	}
	
	protected Property[] privDiv(String privName) {
		return Property.concatPropertyArray(this.div(), ".className", "groupcard", ".text", new RegularExpression("^" + privName + ".*", false));
	}
	
	protected Property[] notPurchaseInfoDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "notForPurchaseInfo");
	}
	
	protected Property[] contractUsLink() {
		return Property.concatPropertyArray(this.a(), ".href", "/contact.do");
	}
	
	protected Property[] buyPrivilegeLink() {
		return Property.concatPropertyArray(this.a(), ".className", "buy_product");
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.Form", ".id", "CategoryPrivilegesKit_form");
	}
	
	public boolean isPrivilegeExist(String priInfo) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression(priInfo, false));
	}
	
	private String generatePrivNmLinkTextReg(String priCode, String priName, String huntDes, String huntTagQty) {
		String priInfo = priName;
		if (StringUtil.notEmpty(priCode))
			priInfo += ".*" + priCode;
		if (StringUtil.notEmpty(huntDes))
			priInfo += ".*" + huntDes;
		if (StringUtil.notEmpty(huntTagQty))
			priInfo += ".*" + huntTagQty;
		logger.info("priv info:" + priInfo);
		return priInfo;
	}
	
	public boolean isNextLinkExist() {
		return browser.checkHtmlObjectExists(nextLink());
	}
	
	public void gotoNextPage() {
		browser.clickGuiObject(nextLink());
		this.waitLoading();
	}
	
	public boolean isPrivilegeExist(String priCode, String priName, String huntDes, String huntTagQty) {
		String priInfo = this.generatePrivNmLinkTextReg(priCode, priName, huntDes, huntTagQty);
		return this.isPrivilegeExist(priInfo);
	}
	
	public void verifyPrivilegeExist(String priCode, String priName, String huntDes, String huntTagQty, boolean exp) {
		boolean actual = this.isPrivilegeExist(priCode, priName, huntDes, huntTagQty);
		while (!actual && this.isNextLinkExist()) {
			this.gotoNextPage();
			actual = this.isPrivilegeExist(priCode, priName, huntDes, huntTagQty);
		}
		String msg = exp ? "" : "NOT";
		if (exp != actual) {
			throw new ErrorOnPageException(priName + " should " + msg + " exist!");
		}
		logger.info("Verify " + priName + " do " + msg + " exist!");	
	}
	
	public void verifyPrivilegeExist(String priName, boolean exp) {
		this.verifyPrivilegeExist(StringUtil.EMPTY, priName, StringUtil.EMPTY, StringUtil.EMPTY, exp);
	}
	
	public String getPrivilegeLinkText(String priCode, String priName, String huntDes, String huntTagQty) {
		String priInfo = this.generatePrivNmLinkTextReg(priCode, priName, huntDes, huntTagQty);
		return browser.getObjectText(".class", "Html.A", ".text", new RegularExpression(priInfo, false));
	}
	
	public void verifyPrivilegeLinkText(String priCode, String priName, String huntDes, String huntTagQty, String exp) {
		String actual = this.getPrivilegeLinkText(priCode, priName, huntDes, huntTagQty);
		if (!exp.equals(actual)) {
			throw new ErrorOnPageException("Privilege link text is wrong!", exp, actual);
		}
		logger.info("Verify privilege link text correctly: " + exp);	
	}
	
	public boolean isFilterItemLinkExist(String text) {
		return browser.checkHtmlObjectExists(Property.atList(this.filterPanelDiv(), this.filterItemLink(text)));
	}
	
	public void clickFilterItemLink(String text) {
		browser.clickGuiObject(Property.atList(this.filterPanelDiv(), this.filterItemLink(text)), true, 0);
	}
	
	public boolean isLicYearFilterLinkExist(String licYear) {
		return browser.checkHtmlObjectExists(Property.atList(this.licYearFilterItemLI(licYear), this.licYearFilterItemLink(licYear)));
	}
	
	public void clickLicYearFilter(String licYear) {
		browser.clickGuiObject(Property.atList(this.licYearFilterItemLI(licYear), this.licYearFilterItemLink(licYear)), true, 0);
	}
	
	public void filterPrivilege(String type, String price, String licYear) {
		if (StringUtil.notEmpty(licYear)) {
			if (this.isLicYearFilterLinkExist(licYear)) {
				this.clickLicYearFilter(licYear);
				this.waitLoading();
			}
		}
		if (StringUtil.notEmpty(type)) {
			if (this.isFilterItemLinkExist(type)) {
				this.clickFilterItemLink(type);
			}
			this.waitLoading();
		}
		if (StringUtil.notEmpty(price)) {
			this.clickFilterItemLink(price);
			this.waitLoading();
		}
	}

	public void clearSearch() {
		logger.info("Clear Search...");
		browser.clickGuiObject(this.clearAllFiltersDiv());
		this.waitLoading();
	}
	
	public void filterPrivAndVerifyPrivInCategoryListPg(PrivilegeInfo[] privs, Boolean[] exps) {
		boolean result = true;
		for (int i = 0; i < privs.length; i++) {
			PrivilegeInfo priv = privs[i];
			this.filterPrivilege(priv.displaySubCategory, StringUtil.EMPTY, priv.licenseYear);
			this.verifyPrivilegeExist(priv.name, exps[i]);
			this.clearSearch();
		}
		
		if (!result) {
			throw new ErrorOnPageException("privileges are displayed wrongly in category product list page!");
		}
		logger.info("Verify privielges in category product list page correctly!");
	}
	
	public void clickPrivName(String name) {
		browser.clickGuiObject(".class", "Html.A", ".text", name);
	}
	
	public void clickPrivName(String name, String authPrivNum) {
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".text", name, ".href", new RegularExpression("/privilegePurchaseDetails\\.do\\?privId=\\d+&authPriv="+authPrivNum, false)));
	}
	
	public void clickFindOtherPrivs() {
		browser.clickGuiObject(this.findOtherPrivsLink());
	}
	
	private String getOutfitterName(IHtmlObject top) {
		IHtmlObject[] objs = browser.getHtmlObject(privilegeOutfitterNmSpanProp(), top);
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find the outfitter name object");
		}
		String name = objs[0].text().replace("[", StringUtil.EMPTY).replace("]", StringUtil.EMPTY).trim();
		Browser.unregister(objs);
		return name;
	}
	
	private String getPrivAttrValue(IHtmlObject top, String attrLabel) {
		IHtmlObject[] objs = browser.getHtmlObject(privilegeAttrSpanProp(attrLabel), top);
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find the privilege attribute object: " + attrLabel);
		}
		String value = objs[0].text().split(":")[1].trim();
		Browser.unregister(objs);
		return value;
	}
	
	private String getOutfitterPermitNum(IHtmlObject top) {
		return this.getPrivAttrValue(top, "Outfitter Permit #");
	}
	
	private String getAuthPrivNum(IHtmlObject top) {
		return this.getPrivAttrValue(top, "Auth. #");
	}

	private String getPrivLicenseYear(IHtmlObject top) {
		return this.getPrivAttrValue(top, "Licence Year");
	}
	
	private String getPrivPrice(IHtmlObject top) {
		String price = this.getPrivAttrValue(top, "Price");
		price = price.replace("$", StringUtil.EMPTY);
		price = new DecimalFormat("0.00").format(Double.valueOf(price));
		return price;
	}
	
	public List<PrivilegeInfo> getOutfitterPrivilegesInfo(String privNm) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "privInfo", ".text", new RegularExpression("^"+privNm+".*", false)));
		if (objs.length < 1) { 
			throw new ObjectNotFoundException("Can't find the privilege info for " + privNm);
		}
		List<PrivilegeInfo> privs = new ArrayList<PrivilegeInfo>();
		for (int i = 0; i < objs.length; i++) {
			PrivilegeInfo priv = new PrivilegeInfo();
			priv.name = privNm;
			priv.outfitterNm = this.getOutfitterName(objs[i]);
			priv.outfitterPermitNum = this.getOutfitterPermitNum(objs[i]);
			priv.authPrivNum = this.getAuthPrivNum(objs[i]);
			priv.licenseYear = this.getPrivLicenseYear(objs[i]);
			priv.creationPrice = this.getPrivPrice(objs[i]);
			privs.add(priv);
		}
		return privs;
	}
	
	public boolean isPrivOutfitterNmLinkExist(String outfitterNm) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression(".*"+outfitterNm+".*", false));
	}
	
	public boolean isNotPurchaseInfoExist(String privName) {
		return browser.checkHtmlObjectExists(Property.atList(this.privDiv(privName), this.notPurchaseInfoDiv()));
	}
	
	public String getNotPurchaseInfoMsg(String privName) {
		return browser.getObjectText(Property.atList(this.privDiv(privName), this.notPurchaseInfoDiv()));
	}
	
	public boolean isContactUsLinkExistInMsg(String privName) {
		return browser.checkHtmlObjectExists(Property.atList(this.privDiv(privName), this.notPurchaseInfoDiv(), this.contractUsLink()));
	}
	
	public boolean isBuyPrivilegeLinkExist(String privName) {
		return browser.checkHtmlObjectExists(Property.atList(this.privDiv(privName), this.buyPrivilegeLink()));
	}
	
}
