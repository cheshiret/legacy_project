/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

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
 * @Description: HF Web License Categories List page 
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Feb 25, 2013
 */
public class HFLicenseCategoriesListPage extends HFProductCategoriesListPage {
	private static HFLicenseCategoriesListPage _instance = null;

	public static HFLicenseCategoriesListPage getInstance() {
		if (null == _instance)
			_instance = new HFLicenseCategoriesListPage();

		return _instance;
	}
	
	protected HFLicenseCategoriesListPage() {
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
		return Property.toPropertyArray(".class", "Html.span", ".className", "item", ".text", licYear);
	}
	
	protected Property[] licYearFilterItemLink(String licYear) {
		return Property.toPropertyArray(".class", "Html.A", ".title", licYear);
	}
	
	protected Property[] categoryLink(String catName) {
		return Property.toPropertyArray(".class", "Html.A", ".text", catName, ".href", new RegularExpression("/categoryProductsList.do\\?catId=\\d+", false));
	}
	
	protected Property[] clearAllFiltersDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".title", "Clear All Filters");
	}
	
	protected Property[] privilegeItemProp(){
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "privilege_item");
	}
	
	protected Property[] privilegeItemProp(String privNm){
		return Property.concatPropertyArray(div(), ".className", "privilege_item", ".text", new RegularExpression("^"+privNm+".*", false));
	}
	
	protected Property[] privilegeOutfitterNmSpanProp(String outfitterNm){
		return Property.concatPropertyArray(span(), ".className", "productCode", ".text", " ["+outfitterNm+"] ");
	}
	
	protected Property[] privItem(String privInfo){
		return Property.concatPropertyArray(div(), ".className", "privilege_item", ".text", new RegularExpression(privInfo+".*", false));
	}
	
	protected Property[] privilegeOutfitterNmSpanProp(){
		return Property.concatPropertyArray(span(), ".className", "productCode");
	}
	
	protected Property[] privName(String privName){
		return Property.concatPropertyArray(a(), ".text", new RegularExpression(privName+".*", false));
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return super.exists();
	}
	
	public void clickSeeLicensesBtn() {
		browser.clickGuiObject(".classname", "see_privileges", ".text", "See Licences");
	}
	
	public void clickLicenseName(String name) {
//		browser.clickGuiObject(".class", "Html.A", ".title", name);
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(name+".*", false));
	}
	
	public void clickUnlockedLic(String licName, String licCode, String huntDes, String huntQty) {
		String ulLicNm = this.generatePrivNmLinkTextReg(licCode, licName, huntDes, huntQty);
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(ulLicNm, false));
	}
	
	public boolean isPrivilegeItemExist(String priInfo) {
//		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression(priInfo, false));
		return browser.checkHtmlObjectExists(privItem(priInfo)); //Sara[20140218], Privilege name is changed to DIV from A
	}
	
	public boolean isPrivilegeNameExist(String priInfo) {
//		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression(priInfo, false));
		return browser.checkHtmlObjectExists(privName(priInfo)); //Sara[20140218], Privilege name is changed to DIV from A
	}
	
	private String generatePrivNmLinkTextReg(String priCode, String priName, String huntDes, String huntTagQty) {
		String priInfo = priName;
		if (StringUtil.notEmpty(priCode))
			priInfo += ".*" + priCode;
		if (StringUtil.notEmpty(huntDes))
			priInfo += ".*" + huntDes;
		if (StringUtil.notEmpty(huntTagQty))
			priInfo += ".*" + huntTagQty+"(\\s+\\))?";//Sara[20140218], HFMO HuntLic001[#MOF] - HFMO Hunt001(# of tags: 1 )
		logger.info("priv info:" + priInfo);
		return priInfo;
	}
	
	public boolean isPrivilegeExist(String priCode, String priName, String huntDes, String huntTagQty) {
		String priInfo = this.generatePrivNmLinkTextReg(priCode, priName, huntDes, huntTagQty);
		return this.isPrivilegeItemExist(priInfo);
	}
	
	public void verifyPrivilegeExist(String priCode, String priName, String huntDes, String huntTagQty, boolean exp) {
		boolean actual = this.isPrivilegeExist(priCode, priName, huntDes, huntTagQty);
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
	
	public void filterPrivilege(String category, String type, String licYear) {
		if (StringUtil.notEmpty(licYear)) {
			if (this.isLicYearFilterLinkExist(licYear)) {
				this.clickLicYearFilter(licYear);
				this.waitLoading();
			}
		}
		if (StringUtil.notEmpty(category)) {
			this.clickFilterItemLink(category);
			this.waitLoading();
		}
		if (StringUtil.notEmpty(type)) {
			if (this.isFilterItemLinkExist(type)) {
				this.clickFilterItemLink(type);
			}
			this.waitLoading();
		}
	}
	
	public void clickCategoryNm(String category) {
		browser.clickGuiObject(this.categoryLink(category.toUpperCase()));
	}
	
	public void clickClear() {
		browser.clickGuiObject(this.clearAllFiltersDiv());
	}
	
	public void clearSearch() {
		logger.info("Clear Search...");
		this.clickClear();
		this.waitLoading();
	}
	
	public IHtmlObject[] getSubcategoryBasedOnCategory(String category, String subCategory){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "group", ".id", new RegularExpression("\\d+", false), ".text", new RegularExpression("^"+category+".*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "subcategory", ".text", new RegularExpression("^"+subCategory+".*", false));
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find any subcategory objects when category="+category+" and sub category="+subCategory);
		}
		
		return objs;
	}
	
	public IHtmlObject[] getPrivObjesBasedOnSubCategory(String category, String subCategory){
		IHtmlObject[] objs1 = getSubcategoryBasedOnCategory(category, subCategory);
		IHtmlObject[] objs2 = browser.getHtmlObject(privilegeItemProp(), objs1[0]);
		if(objs2.length<1){
			throw new ObjectNotFoundException("Can't find any privilege item when category="+category+" and sub category="+subCategory);
		}
		
		Browser.unregister(objs1);
		return objs2;
	}
	
	public List<String> getPrivBasedOnSubCategory(String category, String subCategory){
		IHtmlObject[] objs = getPrivObjesBasedOnSubCategory(category, subCategory);
		List<String> priv = new ArrayList<String>();
		for(IHtmlObject obj: objs){
			priv.add(obj.text());
		}
		
		Browser.unregister(objs);
		return priv;
	}
	
	public void filterPrivAndVerifyPrivInCategoryListPg(PrivilegeInfo[] privs, Boolean[] exps) {
		boolean result = true;
		for (int i = 0; i < privs.length; i++) {
			PrivilegeInfo priv = privs[i];
			this.filterPrivilege(priv.displayCategory, priv.displaySubCategory, priv.licenseYear);
			this.verifyPrivilegeExist(priv.name, exps[i]);
			this.clearSearch();
		}
		
		if (!result) {
			throw new ErrorOnPageException("privileges are displayed wrongly in category list page!");
		}
		logger.info("Verify privielges in category list page correctly!");
	}
	
	public void filterPrivAndVerifyPrivExistInCategoryListPg(PrivilegeInfo... infos) {
		PrivilegeInfo[] privs = infos;
		Boolean[] exps = new Boolean[infos.length];
		for (int i = 0; i < infos.length; i++) {
			exps[i] = true;
		}
		this.filterPrivAndVerifyPrivInCategoryListPg(privs, exps);
	}
	
	/** Get the outfitter names for the same privilege name */
	public String[] getPrivOutfitterNms(String privNm) {
		IHtmlObject[] objs = browser.getHtmlObject(this.privilegeItemProp(privNm));
		IHtmlObject[] outfitterObjs = null;
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find the object of the privilege " + privNm);
		}
		String[] outfitterNms = new String[objs.length];
		for (int i = 0; i < objs.length; i++) {
			outfitterObjs = browser.getHtmlObject(this.privilegeOutfitterNmSpanProp(), objs[i]);
			if (outfitterObjs.length < 1) {
				throw new ObjectNotFoundException("Can't find the object of the privilege outfitter name");
			}
			outfitterNms[i] = outfitterObjs[0].text().replace("[", StringUtil.EMPTY).replace("]", StringUtil.EMPTY).trim();
		}
		Browser.unregister(objs, outfitterObjs);
		return outfitterNms;
	}
	
	public boolean isPrivOutfitterNmLinkExist(String outfitterNm) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression(".*"+outfitterNm+".*", false));
	}
}
