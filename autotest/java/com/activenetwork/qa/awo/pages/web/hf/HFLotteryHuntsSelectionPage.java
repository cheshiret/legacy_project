package com.activenetwork.qa.awo.pages.web.hf;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: 
 * @Preconditions:
 * @SPEC: 
 * @LinkSetUp:
 * @Task#: 
 * 
 * @author Lesley Wang
 * @Date  Feb 12, 2014
 */
public class HFLotteryHuntsSelectionPage extends HFHeaderBar {
	private static HFLotteryHuntsSelectionPage _instance = null;

	public static HFLotteryHuntsSelectionPage getInstance() {
		if (null == _instance)
			_instance = new HFLotteryHuntsSelectionPage();

		return _instance;
	}
	
	protected HFLotteryHuntsSelectionPage() {
	}
	
	protected static String HUNTSELECTION_PLEASESELECT = "-- Please Select --";
	
	
	/** Page Object Property Definition Begin */
	protected Property[] lotteryHuntsForm() {
		return Property.concatPropertyArray(this.form(), ".id", "LotteryHuntsKit_form");
	}
	
	protected Property[] previousPgLink() {
		return Property.concatPropertyArray(this.a(), ".id", "hunt-choices-return-detail");
	}
	
	protected Property[] addChoiceBtn() {
		return Property.toPropertyArray(".class", "Html.button", ".id", new RegularExpression("CHOICE-HUNTS-\\d+", false), ".text", "Add Choice");
	}
	
	protected Property[] removeChoiceBtn() {
		return Property.toPropertyArray(".class", "Html.button", 
				".className", "lottery-hunts-removehunt", ".text", new RegularExpression("Choice Added.*", false));
	}
	
	protected Property[] nextLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Next");
	}
	
	protected Property[] previousLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Previous");
	}
	
	protected Property[] h1() {
		return Property.toPropertyArray(".class", "Html.h1");
	}
	
	protected Property[] h2() {
		return Property.toPropertyArray(".class", "Html.h2");
	}
	
	protected Property[] instructionDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "hunts-instruction");
	}
	
	protected Property[] manualHuntOptionalDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "hunts-manualHuntOptional");
	}
	
	protected Property[] pointNoteP() {
		return Property.toPropertyArray(".class", "Html.p", ".className", "info");
	}
	
	protected Property[] lotteryAttributesDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "privileges");
	}
	
	protected Property[] filtersDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "leftSideFilters");
	}

	protected Property[] filterTitleDiv() {
		return Property.concatPropertyArray(this.div(), ".className", new RegularExpression("filterTitle( selectable)?", false));
	}
	
	protected Property[] filterCategoryLI() {
		return Property.concatPropertyArray(this.li(), ".className", "filterCategory BLOCK selectable");
	}
	
	protected Property[] filterCategoryLI(String text) {
		return Property.concatPropertyArray(this.filterCategoryLI(), ".text", text);
	}
	
	protected Property[] filterItemLI() {
		return Property.concatPropertyArray(this.li(), ".className", new RegularExpression("filterItem.*", false));
	}
	
	protected Property[] filterItemLink(String option) {
		return Property.concatPropertyArray(this.a(), ".title", option);
	}
	
	protected Property[] filterItemNumSpan() {
		return Property.concatPropertyArray(this.span(), ".className", "dim");
	}
	
	protected Property[] selectedFilterItemLI() {
		return Property.concatPropertyArray(this.li(), ".className", new RegularExpression("filterItem first( last)? selected", false));
	}
	
	protected Property[] headerPaginationDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "listControl_hdr");
	}
	
	protected Property[] msgSuccess(){
		return Property.concatPropertyArray(div(), ".className", "msg success");
	}
	
	protected Property[] footerPaginationDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "listControl_ftr");
	}
	
	protected Property[] huntListInstructionDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "listDirective");
	}
	
	protected Property[] showAllLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Show All");
	}
	
	protected Property[] showByPageLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Show By Page");
	}
	
	protected Property[] pagePickerList_Header() {
		return Property.concatPropertyArray(this.select(), ".id", "LLotteryHuntsKit_catList_selector_hdr");
	}
	
	protected Property[] pagePickerList_Footer() {
		return Property.concatPropertyArray(this.select(), ".id", "LLotteryHuntsKit_catList_selector_ftr");
	}
	
	protected Property[] clearSpeciesFilterLink() {
		return Property.concatPropertyArray(this.a(), ".title", "Clear Species Filter");
	}
	
	protected Property[] clearSpeciesTypeFilterLink() {
		return Property.concatPropertyArray(this.a(), ".title", "Species Type");
	}
	
	protected Property[] clearWeaponFilterLink() {
		return Property.concatPropertyArray(this.a(), ".title", "Clear Weapon Filter");
	}
	
	protected Property[] clearHuntLocationFilterLink() {
		return Property.concatPropertyArray(this.a(), ".title", "Clear Location Filter");
	}
	
	protected Property[] huntCodeSpan() {
		return Property.concatPropertyArray(this.span(), ".className", "huntCode");
	}
	
	protected Property[] huntCodeSpan(String code) {
		return Property.concatPropertyArray(this.huntCodeSpan(), ".text", "["+code+"]");
	}
	
	protected Property[] huntAttrsDiv() {
		return Property.concatPropertyArray(this.div(), ".id", new RegularExpression("LotteryHuntsKit_catList\\d+_attrs", false));
	}
	
	protected Property[] huntAttrsDiv(String huntDes) {
		return Property.concatPropertyArray(this.huntAttrsDiv(), ".text", new RegularExpression("^"+huntDes, false));
	}
	
	protected Property[] huntAttrsDL() {
		return Property.toPropertyArray(".class", "Html.dl", ".className", "lotteryHuntsAttr");
	}
	
	protected Property[] moreDetailsLink() {
		return Property.concatPropertyArray(this.span(), ".className", "lottery-hunts-showmore-arrow", ".text", "More Details");
	}
	
	protected Property[] moreDetailsDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "hunt_more_detail_div");
	}
	
	protected Property[] hiddenMoreDetailsDiv() {
		return Property.concatPropertyArray(this.moreDetailsDiv(), ".className", "hiddenText");
	}
	
	protected Property[] huntParamDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "huntParameter");
	}
	
	protected Property[] huntQuotaDiv() {
		return Property.concatPropertyArray(this.div(), ".text", new RegularExpression("^Quota Details.*", false));
	}
	
	protected Property[] huntLocMapSpan() {
		return Property.concatPropertyArray(this.span(), ".id", "lottery-hunts-attribute-map-link");
	}
	
	protected Property[] huntLocMapPop() {
		return Property.concatPropertyArray(this.div(), ".id", new RegularExpression("modalPopLite-mask\\d+", false));
	}
	
	protected Property[] huntLocMapImg(String src) {
		return Property.concatPropertyArray(this.img(), ".src", new RegularExpression(".*"+src+"$", false));
	}
	
	protected Property[] huntLocMapCloseLink() {
		return Property.concatPropertyArray(this.a(), ".id", new RegularExpression("lottery-hunt-image-close-\\d+_\\d+", false));
	}
	
	protected Property[] huntWidgetOpened() {
		return Property.concatPropertyArray(this.div(), ".className", "pullouts side_bottom rounded borders pullout-opened");
	}
	
	protected Property[] huntWidgetClosed() {
		return Property.concatPropertyArray(this.div(), ".className", "pullouts side_bottom rounded borders pullout-closed");
	}
	
	protected Property[] huntWidgetOpenCloseDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "pullout-button");
	}
	
	protected Property[] huntsWidgetDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "lottery-hunts-widget");
	}
	
	protected Property[] huntWidgetErrorMsgSpan() {
		return Property.concatPropertyArray(this.span(), ".id", "widget-choice-info");
	}
	
	protected Property[] b() {
		return Property.toPropertyArray(".class", "Html.b");
	}
	
	protected Property[] removeChoicesLink() {
		return Property.concatPropertyArray(this.a(), ".id", "hunt-removeChoices");
	}
	
	protected Property[] huntWidgetItemDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "hunt_widget_item");
	}
	
	protected Property[] huntWidgetItemDiv(String huntDes) {
		return Property.concatPropertyArray(this.div(), ".className", "hunt_widget_item", ".text", new RegularExpression(huntDes+".*", false));
	}

	protected Property[] huntWidgetSubmitBtn() {
		return Property.concatPropertyArray(this.input("submit"), ".id", "lotteryHuntsChoices");
	}
	
	protected Property[] huntWidgetItemOrderList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression("hunts-order-selector\\d+", false), 
				".className", "hunt_widget_item_dropdown");
	}
	
	protected Property[] huntWidgetPointOrderList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression("hunts-order-selector\\d+", false), 
				".className", "hunt_widget_item_dropdown_points");
	}
	
	protected Property[] huntWidgetDesLink(String title) {
		return Property.concatPropertyArray(this.a(), ".title", title);
	}
	
	protected Property[] huntWidgetDesLink() {
		return Property.concatPropertyArray(this.a(), ".id", new RegularExpression("widget-hunt-description-.*", false));
	}
	
	protected Property[] huntWidgetPointTypeLink() {
		return Property.concatPropertyArray(this.a(), ".id", "widget-hunt-description-pointType");
	}
	
	protected Property[] huntWidgetRemoveChoiceLink() {
		return Property.concatPropertyArray(this.a(), ".id", "hunt-removeChoice");
	}
	
	protected Property[] huntChoiceDDL(){
		return Property.toPropertyArray(".id", new RegularExpression("manHuntChoiceDD\\d+", false));
	}
	
	protected Property[] huntChoiceDDL(int index){
		return Property.toPropertyArray(".id", "manHuntChoiceDD"+index);
	}
	
	protected Property[] huntChoiceTitle(){
		return Property.concatPropertyArray(div(), ".className", "manHuntChoiceLbl left");
	}
	
	protected Property[] errorMsg(String msg){
		return Property.concatPropertyArray(div(), ".className", "msg error manHuntErr", ".text", new RegularExpression(msg, false));
	}
	
	protected Property[] msgSuccess(String msg){
		return Property.concatPropertyArray(div(), ".className", "msg success", ".text", new RegularExpression(msg, false));
	}
	
	protected Property[] continueBtn(){
		return Property.toPropertyArray(".className", "huntChoicesSubmit");
	}
	
	/** Page Object Property Definition End */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(lotteryHuntsForm());
	}
	
	public void clickNavigationLink() {
		browser.clickGuiObject(previousPgLink());
	}
	
	/** Pagination Controls */
	public boolean checkPreviousLinkExist() {
		return browser.checkHtmlObjectExists(this.previousLink());
	}
	
	public boolean checkFooterPreviousLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.footerPaginationDiv(), previousLink()));
	}
	
	public void clickPrevious() {
		browser.clickGuiObject(this.previousLink());
	}
	
	public void gotoPreviousPg() {
		this.clickPrevious();
		this.waitLoading();
	}
	
	public void gotoFirstPg() {
		while (this.checkPreviousLinkExist()) {
			this.gotoPreviousPg();
		}
	}

	public void clickNext() {
		browser.clickGuiObject(nextLink());
	}
	
	public boolean checkNextLinkExist() {
		return browser.checkHtmlObjectExists(nextLink());
	}
	
	public boolean checkFooterNextLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.footerPaginationDiv(), nextLink()));
	}
	
	public void gotoNextPg() {
		this.clickNext();
		this.waitLoading();
	}
	
	public String getHeaderPaginationText() {
		return browser.getObjectText(this.headerPaginationDiv());
	}
	
	public String getFooterPaginationText() {
		return browser.getObjectText(this.footerPaginationDiv());
	}
	
	public String getHuntListInstruction() {
		return browser.getObjectText(this.huntListInstructionDiv());
	}
	
	public String getmanualHuntOptional() {
		return browser.getObjectText(this.manualHuntOptionalDiv());
	}
	
	public boolean isManualHuntOptionalExist() {
		return browser.checkHtmlObjectExists(this.manualHuntOptionalDiv());
	}
	
	public boolean checkHeaderShowAllLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.headerPaginationDiv(), this.showAllLink()));
	}
	
	public boolean checkFooterShowAllLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.footerPaginationDiv(), this.showAllLink()));
	}

	public boolean checkHeaderShowByPageLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.headerPaginationDiv(), this.showByPageLink()));
	}
	
	public boolean checkFooterShowByPageLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.footerPaginationDiv(), this.showByPageLink()));
	}
	
	public boolean checkHeaderPagePickerListExist() {
		return browser.checkHtmlObjectExists(this.pagePickerList_Header());
	}
	
	public boolean checkFooterPagePickerListExist() {
		return browser.checkHtmlObjectExists(this.pagePickerList_Footer());
	}
	
	public void gotoLastPgFromPgPicker() {
		List<String> pages = browser.getDropdownElements(this.pagePickerList_Header());
		browser.selectDropdownList(this.pagePickerList_Header(), pages.size()-1);
		this.waitLoading();
	}
	
	public void showAllHunts() {
		browser.clickGuiObject(this.showAllLink());
		this.waitLoading();
	}
	
	public void showByPage() {
		browser.clickGuiObject(this.showByPageLink());
		this.waitLoading();
	}
	/** Pagination Controls END */
	
	public String getPageHeader() {
		return browser.getObjectText(Property.atList(this.pageTitleDiv(), this.h1()));
	}
	
	public String getPageSubHeader() {
		return browser.getObjectText(Property.atList(this.pageTitleDiv(), this.h2()));
	}
	
	public boolean isPageSubHeaderExist(){
		return browser.checkHtmlObjectExists(Property.atList(this.pageTitleDiv(), this.h2()));
	}
	
	public String getInstruction() {
		return browser.getObjectText(this.instructionDiv());
	}
	
	public boolean isInstructionExist() {
		return browser.checkHtmlObjectExists(this.instructionDiv());
	}
	
	public String getPointNote() {
		return browser.getObjectText(this.pointNoteP());
	}
	
	public boolean checkPointNoteExist() {
		return browser.checkHtmlObjectExists(this.pointNoteP());
	}
	
	public String getLotteryAttributes() {
		return browser.getObjectText(this.lotteryAttributesDiv());
	}
	
	public String getLotteryName(){
		return browser.getObjectText(Property.atList(lotteryAttributesDiv(), h2()));
	}
	
	public String getFilterTitle() {
		return browser.getObjectText(this.filterTitleDiv());
	}
	
	public boolean isFilterCategoryExist(String category) {
		return browser.checkHtmlObjectExists(this.filterCategoryLI(category));
	}
	
	public boolean isSpeciesFilterCategoryExist() {
		return this.isFilterCategoryExist("Species");
	}
	
	public boolean isSubSpeciesFilterCategoryExist() {
		return this.isFilterCategoryExist("Species Type");
	}
	
	public boolean isWeaponFilterCategoryExist() {
		return this.isFilterCategoryExist("Weapon");
	}
	
	public boolean isHuntLocationFilterCategoryExist() {
		return this.isFilterCategoryExist("Hunt Location");
	}
	
	public boolean isFilterOptionLinkExist(String option) {
		return browser.checkHtmlObjectExists(Property.atList(this.filterItemLI(), this.filterItemLink(option)));
	}
	
	public String getFilterItemNum(String option) {
		String text = browser.getObjectText(Property.atList(this.filterItemLI(), this.filterItemLink(option), this.filterItemNumSpan()));
		return text.replaceAll("\\(|\\)", "");
	}
	
	public void clickFilterItemLink(String item) {
		browser.clickGuiObject(Property.atList(this.filterItemLI(), this.filterItemLink(item)));
	}
	
	public void filterHunts(String item) {
		this.clickFilterItemLink(item);
		this.waitLoading();
	}
	
	public String getSelectFilterItem() {
		return browser.getObjectText(this.selectedFilterItemLI());
	}
	
	public String getNumOfAvailHunts() {
		String text = browser.getObjectText(this.headerPaginationDiv());
		text = RegularExpression.getMatches(text, "\\d+-\\d+ of \\d+")[0];
		return text.split("of")[1].trim();
	}
	
	public String getClearSpeciesFilterLinkText() {
		return browser.getObjectText(this.clearSpeciesFilterLink());
	}
	
	public void clearSpeciesFilter() {
		browser.clickGuiObject(this.clearSpeciesFilterLink());
		this.waitLoading();
	}
	
	public String getClearSpeciesTypeFilterLinkText() {
		return browser.getObjectText(this.clearSpeciesTypeFilterLink());
	}
	
	public void clearSpeciesTypeFilter() {
		browser.clickGuiObject(this.clearSpeciesTypeFilterLink());
		this.waitLoading();
	}
	
	public String getClearWeaponFilterLinkText() {
		return browser.getObjectText(this.clearWeaponFilterLink());
	}

	public void clearWeaponFilter() {
		browser.clickGuiObject(this.clearWeaponFilterLink());
		this.waitLoading();
	}
	
	public String getClearHuntLocFilterLinkText() {
		return browser.getObjectText(this.clearHuntLocationFilterLink());
	}

	public void clearHuntLocFilter() {
		browser.clickGuiObject(this.clearHuntLocationFilterLink());
		this.waitLoading();
	}
	
	public boolean isHuntExist(String huntCode) {
		this.showAllHunts();
		return browser.checkHtmlObjectExists(this.huntCodeSpan(huntCode));
	}
	
	public boolean isHuntExistByHuntDesc(String huntDesc) {
		this.showAllHunts();
		return browser.checkHtmlObjectExists(this.huntAttrsDiv(huntDesc));
	}
	
	public int getHuntIndex(String huntDesc) {
		IHtmlObject[] objs = browser.getHtmlObject(this.huntAttrsDiv());
		int index = -1;
		for (int i = 0; i < objs.length; i++) {
			if (objs[i].text().startsWith(huntDesc)) {
				index = i;
				break;
			}
		}
		Browser.unregister(objs);
		logger.info(huntDesc + " index is " + index);
		return index;
	}
	
	public String getHuntCode(String huntDes) {
		String code = browser.getObjectText(Property.atList(this.huntAttrsDiv(huntDes), this.huntCodeSpan()));
		return code.replaceAll("\\[|\\]", "");
	}
	
	public String getHuntAttributes(String huntDes) {
		return browser.getObjectText(Property.atList(this.huntAttrsDiv(huntDes), this.huntAttrsDL()));
	}
	
	public boolean checkMoreDetailsLinkExist(String huntDes) {
		return browser.checkHtmlObjectExists(Property.atList(this.huntAttrsDiv(huntDes), this.moreDetailsLink()));
	}
	
	public boolean checkMoreDetailsHidden(String huntDes) {
		return browser.checkHtmlObjectExists(Property.atList(this.huntAttrsDiv(huntDes), this.hiddenMoreDetailsDiv()));
	}
	
	public void clickMoreDetailsLink(String huntDes) {
		browser.clickGuiObject(Property.atList(this.huntAttrsDiv(huntDes), this.moreDetailsLink()));
	}
	
	public String getHuntParameters(String huntDes) {
		return browser.getObjectText(Property.atList(this.huntAttrsDiv(huntDes), this.huntParamDiv()));
	}
	
	public boolean checkHuntParamExist(String huntDes) {
		return browser.checkHtmlObjectExists(Property.atList(this.huntAttrsDiv(huntDes), this.huntParamDiv()));
	}
	
	public boolean checkHuntQuotaInfoExist(String huntDes) {
		return browser.checkHtmlObjectExists(Property.atList(this.huntAttrsDiv(huntDes), this.huntQuotaDiv()));
	}
	
	private IHtmlObject[] getHuntQuotaTable(String huntDes) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.huntAttrsDiv(huntDes), this.huntQuotaDiv(), this.table()));
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find quota info for " + huntDes);
		}
		return objs;
	}
	
	private String getHuntQuotaInfo(String huntDes, int row, int col) {
		IHtmlObject[] objs = this.getHuntQuotaTable(huntDes);
		IHtmlTable table = (IHtmlTable)objs[0];
		String value = table.getCellValue(row, col);
		Browser.unregister(objs);
		return value;
	}
	
	public String getHuntQuotaLicenseYear(String huntDes, int row) {
		return this.getHuntQuotaInfo(huntDes, row+1, 0);
	}
	
	public String getHuntTotalQuota(String huntDes, int row) {
		return this.getHuntQuotaInfo(huntDes, row+1, 1);
	}
	
	public boolean checkHuntLocMapLinkExist(String huntDes) {
		return browser.checkHtmlObjectExists(Property.atList(this.huntAttrsDiv(huntDes), this.huntLocMapSpan()));
	}

	public boolean checkHuntLocMapDisplayed(String huntDes) {
		return browser.checkHtmlObjectDisplayed(Property.atList(this.huntAttrsDiv(huntDes), this.huntLocMapPop()));
	}
	
	public boolean checkHuntLocMapImageExist(String huntDes, String img) {
		return browser.checkHtmlObjectExists(Property.atList(this.huntAttrsDiv(huntDes), this.huntLocMapImg(img)));
	}
	
	public void clickHuntLocMapLink(String huntDes) {
		browser.clickGuiObject(Property.atList(this.huntAttrsDiv(huntDes), this.huntLocMapSpan()));
	}
	
	public void closeHuntLocMapPop(String huntDes) {
		browser.clickGuiObject(Property.atList(this.huntAttrsDiv(huntDes), this.huntLocMapCloseLink()));
	}

	public void addFirstChoice() {
		browser.clickGuiObject(addChoiceBtn());
		browser.waitExists(this.removeChoiceBtn());
	}
	
	public void clickAddChoice(String huntDes) {
		browser.clickGuiObject(Property.atList(this.huntAttrsDiv(huntDes), this.addChoiceBtn()));
	}
	
	private void addHuntChoice(String huntDes) {
		this.clickAddChoice(huntDes);
		browser.waitExists(this.huntAttrsDiv(huntDes), this.removeChoiceBtn());
	}
	
	public void addHuntChoices(String... huntDescs) {
		for (String huntDes : huntDescs) {
			if (this.isHuntExistByHuntDesc(huntDes)) {
				logger.info("Add Hunt Choice - " + huntDes);
				this.addHuntChoice(huntDes);
				this.waitLoading();
			} else {
				logger.warn("Hunt doesn't exist - " + huntDes);
			}
		}
	}
	
	public void clickRemoveChoice(String huntDes) {
		browser.clickGuiObject(Property.atList(this.huntAttrsDiv(huntDes), this.removeChoiceBtn()));
	}
	
	public boolean checkRemoveChoiceBtnExist(String huntDes) {
		return browser.checkHtmlObjectExists(Property.atList(this.huntAttrsDiv(huntDes), this.removeChoiceBtn()));
	}
	
	public void verifyRemoveChoiceBtnExist(String huntDes, boolean exp) {
		String msg = exp ? "exist" : "not exist";
		if (exp != this.checkRemoveChoiceBtnExist(huntDes)) {
			throw new ErrorOnPageException("Remove choice button for " + huntDes + " should " + msg);
		}
		logger.info("Successfully verify Remove choice button for " + huntDes + " " + msg);
	}
	
	private void removeHuntChoice(String huntDes) {
		this.clickRemoveChoice(huntDes);
		browser.waitExists(this.huntAttrsDiv(huntDes), this.addChoiceBtn());
	}
	
	public void removeHuntChoice(String... huntDescs) {
		for (String huntDes : huntDescs) {
			if (this.isHuntExistByHuntDesc(huntDes)) {
				logger.info("Remove Hunt Choice - " + huntDes);
				this.removeHuntChoice(huntDes);
				this.waitLoading();
			} else {
				logger.warn("Hunt doesn't exist - " + huntDes);
			}
		}
	}
	
	public void clickHuntWidgetOpenCloseBtn() {
		browser.clickGuiObject(this.huntWidgetOpenCloseDiv());
	}
	
	public void openHuntWidget() {
		if (this.checkHuntWidgetClosed()) {
			this.clickHuntWidgetOpenCloseBtn();
			browser.waitExists(this.huntWidgetOpened());
		}
	}
	
	public void closeHuntWidget() {
		if (this.checkHuntWidgetOpened()) {
			this.clickHuntWidgetOpenCloseBtn();
			browser.waitExists(this.huntWidgetClosed());
		}
	}
	
	public boolean checkHuntWidgetOpened() {
		return browser.checkHtmlObjectExists(this.huntWidgetOpened());
	}
	
	public boolean checkHuntWidgetClosed() {
		return browser.checkHtmlObjectExists(this.huntWidgetClosed());
	}
	
	public String getHuntWidgetTitle() {
		return browser.getObjectText(this.huntWidgetOpenCloseDiv());
	}
	
	public String getHuntWidgetHeader() {
		return browser.getObjectText(Property.atList(this.huntsWidgetDiv(), this.b()));
	}
	
	public boolean checkHuntWidgetRemoveChoicesLinkExist() {
		return browser.checkHtmlObjectExists(this.removeChoicesLink());
	}
	
	public String getHuntWidgetText() {
		return browser.getObjectText(this.huntsWidgetDiv());
	}
	
	public String getHuntWidgetErrorMsg() {
		return browser.getObjectText(this.huntWidgetErrorMsgSpan());
	}
	
	public void verifyHuntWidgetErrorMsg(String msg) {
		String actual = this.getHuntWidgetErrorMsg();
		if (!msg.equals(actual)) {
			throw new ErrorOnPageException("Hunt widget error message is wrong!", msg, actual);
		}
		logger.info("Successfully verify hunt widget error message as " + msg);
 	}
	
	public boolean checkHuntWidgetItemExist() {
		return browser.checkHtmlObjectExists(this.huntWidgetItemDiv());
	}
	
	public void clickHuntWidgetSubmitBtn() {
		browser.clickGuiObject(this.huntWidgetSubmitBtn());
	}
	
	public boolean checkHuntWidgetSubmitBtnEnabled() {
		return browser.checkHtmlObjectEnabled(this.huntWidgetSubmitBtn());
	}
	
	public void verifyHuntWidgetSubmitBtnEnabled(boolean exp) {
		String msg = exp ? "enabled" : "disabled";
		if (exp != this.checkHuntWidgetSubmitBtnEnabled()) {
			throw new ErrorOnPageException("Hunt widget submit button should be " + msg);
		}
		logger.info("Successfully verify hunt widget submit button " + msg);
	}
	
	public int getNumOfAddedHunts() {
		IHtmlObject[] objs = browser.getHtmlObject(this.huntWidgetItemDiv());
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public List<String> getHuntWidgetItemOrderOptions() {
		return browser.getDropdownElements(this.huntWidgetItemOrderList());
	}
	
	public String getHuntWidgetItemOrder(int index) {
		return browser.getDropdownListValue(this.huntWidgetItemOrderList(), index);
	}

	private IHtmlObject[] getHuntWidgetItemDivs(String huntDes) {
		IHtmlObject[] objs = browser.getHtmlObject(this.huntWidgetItemDiv(huntDes));
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find hunt widget item " + huntDes);
		}
		return objs;
	}
	
	public String getHuntWidgetItemOrder(String huntDes) {
		IHtmlObject[] objs = this.getHuntWidgetItemDivs(huntDes);
		String value = browser.getDropdownListValue(this.huntWidgetItemOrderList(), 0, objs[0]);
		Browser.unregister(objs);
		return value;
	}
	
	public void selectHuntWidgetItemOrder(String huntDes, int optionIndex) {
		IHtmlObject[] objs = this.getHuntWidgetItemDivs(huntDes);
		browser.selectDropdownList(this.huntWidgetItemOrderList(), optionIndex, false, objs[0]);
		Browser.unregister(objs);
	}
	
	public void clickHuntWidgetItem(String huntDes) {
		browser.clickGuiObject(this.huntWidgetDesLink(huntDes));
	}
	
	public void clickHuntWidget() {
		browser.clickGuiObject(huntsWidgetDiv());
	}
	
	public void reorderHuntChoices(String... huntDescs) {
		for (int i = 0; i < huntDescs.length; i++) {
			String huntDes = huntDescs[i];
			this.selectHuntWidgetItemOrder(huntDes, i);
			this.clickHuntWidget(); //remove focus
			this.waitLoading();
		}
	}
	
	public boolean checkHuntWidgetPointOrderListEnabled() {
		return browser.checkHtmlObjectEnabled(this.huntWidgetPointOrderList());
	}
	
	public String getHuntWidgetPointOrder(int index) {
		return browser.getDropdownListValue(this.huntWidgetPointOrderList(), index);
	}
	
	public String getHuntWidgetItemDesc(String title) {
		return browser.getObjectText(this.huntWidgetDesLink(title));
	}
	
	public List<String> getHuntWidgetItemsDesc() {
		return browser.getObjectsText(this.huntWidgetDesLink());
	}
	
	public String getHuntWidgetPointType() {
		return browser.getObjectText(this.huntWidgetPointTypeLink());
	}
	
	public void clickContinueBtn(){
		browser.clickGuiObject(continueBtn());
	}
	
	public boolean checkHuntWidgetRemoveChoiceLinkExist(String huntDes) {
		return browser.checkHtmlObjectExists(Property.atList(this.huntWidgetItemDiv(huntDes), this.huntWidgetRemoveChoiceLink()));
	}
	
	public void removeHuntChoicesFromHuntWidget(String huntDes) {
		browser.clickGuiObject(Property.atList(this.huntWidgetItemDiv(huntDes), this.huntWidgetRemoveChoiceLink()));
		this.waitLoading();
	}
	
	public void removeAllHuntChoicesFromHuntWidget() {
		browser.clickGuiObject(this.removeChoicesLink());
		browser.waitExists(this.huntWidgetClosed());
	}
	
	public List<String> getHuntChoices(int index){
		return browser.getDropdownElements(huntChoiceDDL(index));
	}
	
	public void selectHuntChoices(String huntChoice, int index){
		browser.selectDropdownList(huntChoiceDDL(index), huntChoice);
	}
	
	public List<String> getHuntChoices(){
		return getHuntChoices(0);
	}
	
	public String getHuntChoice(int index){
		return browser.getDropdownListValue(huntChoiceDDL(), index);
	}
	
	public String getHuntChoice(){
		return getHuntChoice(0);
	}
	
	public int getNumOfHuntChoiceDDL(){
		IHtmlObject[] objs = browser.getHtmlObject(huntChoiceDDL());
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find any hunt choice drop down list.");
		}
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public List<String> getHuntChoicesTitle(){
		return browser.getObjectsText(huntChoiceTitle());
	}
	
	public boolean isErrorMsgExist(String msg) {
		return browser.checkHtmlObjectExists(errorMsg(msg));
	}
	
	public void verifyErrorMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isErrorMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}
	
	public void waitForSuccessMsg(){
		browser.searchObjectWaitExists(msgSuccess(), SLEEP);
	}
	
	public boolean isSuccessMsgExist(String msg) {
		return browser.checkHtmlObjectExists(msgSuccess(msg));
	}
	
	public void setupHuntChoices(List<String> huntChoices){
		for(int i=0; i<huntChoices.size(); i++){
			if(StringUtil.notEmpty(huntChoices.get(i))){
				selectHuntChoices(huntChoices.get(i), i);
			}
		}
	}
	
	public void setupHuntChoices(HuntInfo... hunts){
		for(int i=0; i<hunts.length; i++){
			HuntInfo hunt = hunts[i];
			if(null==hunt || StringUtil.isEmpty(hunt.getHuntCode())){
				selectHuntChoices(HUNTSELECTION_PLEASESELECT, i);
			}else
				selectHuntChoices(hunt.getHuntCode() +" "+ hunt.getDescription(), i);
		}
	}
	
	public void setupHuntChoicesWithContinueBtn(HuntInfo... hunts){
		setupHuntChoices(hunts);
		clickContinueBtn();
		waitForSuccessMsg();
	}
	
	public void verifyHuntChoices(HuntInfo...hunts){
		String huntChoice = StringUtil.EMPTY;
		boolean result = true;
		
		for(int i=0; i<hunts.length; i++){
			HuntInfo hunt = hunts[i];
			huntChoice = getHuntChoice(i);
			if(null==hunt || StringUtil.isEmpty(hunt.getHuntCode())){
				result &= MiscFunctions.compareResult("Hunt Choice", HUNTSELECTION_PLEASESELECT, huntChoice);
			}else result &= MiscFunctions.compareResult("Hunt Choice", hunts[i].getHuntCode()+" "+hunts[i].getDescription(), huntChoice);
		}
		if(!result){
			throw new ErrorOnPageException("Failed to verify all hunt choices info. Please check the details from previous logs.");
		}else logger.info("Successfylly verify all hunt choices info.");
	}
	
	public void verifyLotteryName(String lotteryName){
		String lotteryNameFromUI = getLotteryName();
		if(lotteryName.equalsIgnoreCase(lotteryNameFromUI)){
			logger.info("Successfully verify lottery name:"+lotteryName+" in Lottery Hunts selection page.");
		}else throw new ErrorOnPageException("Failed to verify lottery name in Lottery Hunts Selection page.", lotteryName, lotteryNameFromUI);
	}
}
