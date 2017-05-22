package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: HF Lottery Categories list page
 * @Preconditions:
 * @SPEC: 
 * @LinkSetUp:
 * @Task#: 
 * 
 * @author Lesley Wang
 * @Date  Feb 12, 2014
 */
public class HFLotteryCategoriesListPage extends HFProductCategoriesListPage {
	private static HFLotteryCategoriesListPage _instance = null;

	public static HFLotteryCategoriesListPage getInstance() {
		if (null == _instance)
			_instance = new HFLotteryCategoriesListPage();

		return _instance;
	}
	
	protected HFLotteryCategoriesListPage() {
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] lotteryStatusFilter() {
		return Property.concatPropertyArray(this.li(), ".text", new RegularExpression("(Draw|Lottery) Status", false));
	}
	
	protected Property[] lotteryItem(){
		return Property.concatPropertyArray(div(), ".className", "privilege_item");
	}
	
	protected Property[] lotteryName(String name){
		return Property.concatPropertyArray(a(), ".text", name);
	}
	
	protected Property[] licYearFilterItemLI(String licYear) {
		return Property.toPropertyArray(".id", "item_" + licYear);
	}
	
	protected Property[] licYearFilterItemLink(String licYear) {
		return Property.concatPropertyArray(a(), ".title", licYear);
	}
	
	protected Property[] filterPanelDiv() {
		return Property.concatPropertyArray(div(), ".id", "leftSideFilters");
	}
	
	protected Property[] filterItemLink(String title) {
		return Property.concatPropertyArray(a(), ".title", title);
	}
	/** Page Object Property Definition End */
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(this.lotteryStatusFilter());
	}
	
	public void clickLotteryName(String name) {
		browser.clickGuiObject(lotteryName(name));
	}
	
	public boolean isLotteryNameExist(String name){
		return browser.checkHtmlObjectExists(Property.atList(lotteryItem(), lotteryName(name)));
	}
	
	public void verifyLotteryNameExist(String name, boolean existed){
		boolean resultFromUI = isLotteryNameExist(name);
		if(existed==resultFromUI){
			logger.info("Successfully verify lottery name:"+name+(existed?" exists":" doesn't exist"));
		}else throw new ErrorOnPageException("Failed to verify lottery name:"+name+(existed?" exists":" doesn't exist"));
	}
	
	public boolean isLicYearFilterLinkExist(String licYear) {
		return browser.checkHtmlObjectExists(Property.atList(this.licYearFilterItemLI(licYear), this.licYearFilterItemLink(licYear)));
	}
	
	public void clickLicYearFilter(String licYear) {
		browser.clickGuiObject(Property.atList(this.licYearFilterItemLI(licYear), this.licYearFilterItemLink(licYear)), true, 0);
	}
	
	public void clickFilterItemLink(String text) {
		browser.clickGuiObject(Property.atList(this.filterPanelDiv(), this.filterItemLink(text)), true, 0);
	}
	
	public boolean isFilterItemLinkExist(String text) {
		return browser.checkHtmlObjectExists(Property.atList(this.filterPanelDiv(), this.filterItemLink(text)));
	}
	
	public void filterPrivilege(String category, String type, String licYear) {
		if (StringUtil.notEmpty(licYear)) {
			if (this.isLicYearFilterLinkExist(licYear)) {
				this.clickLicYearFilter(licYear);
				this.waitLoading();
			}
		}
		if (StringUtil.notEmpty(category)) {
			if (this.isFilterItemLinkExist(category)) {
				this.clickFilterItemLink(category);
			}
			this.waitLoading();
		}
		if (StringUtil.notEmpty(type)) {
			if (this.isFilterItemLinkExist(type)) {
				this.clickFilterItemLink(type);
			}
			this.waitLoading();
		}
	}
}
