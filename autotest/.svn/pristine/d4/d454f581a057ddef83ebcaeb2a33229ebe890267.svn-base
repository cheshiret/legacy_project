/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.Harvest;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.PrintDialogPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: It is License Details page for the purchased license
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  May 28, 2013
 */
public class HFLicenseDetailsPage extends HFMyAccountPanel {
	private static HFLicenseDetailsPage _instance = null;

	public static HFLicenseDetailsPage getInstance() {
		if (null == _instance)
			_instance = new HFLicenseDetailsPage();

		return _instance;
	}
	
	protected HFLicenseDetailsPage() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.getLicDetailsDivProperty());
	}
	
	/** Below methods are to get element's properties */
	private Property[] getPageTitleDivProperty() {
		return Property.toPropertyArray(".id", "pagetitle");
	}
	
	private Property[] getTopPrevLinkDivProperty() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "previousLinkTop");
	}
	
	private Property[] getBottomPrevLinkDivProperty() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "previousLinkBottom");
	}
	
	private Property[] getPrevLinkProperty() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Previous");
	}

	private Property[] getLicNameDivProperty() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "name");
	}

	private Property[] getDetailsTitleDivProperty() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "details_title");
	}

	private Property[] getOrdDetailsDivProperty() {
		return Property.toPropertyArray(".id", "orderDetails");
	}
	
	private Property[] getLicDetailsDivProperty() {
		return Property.toPropertyArray(".id", "licenseDetails");
	}
	
	private Property[] getLicInfoDivProperty(String reg) {
		return Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression(reg, false));
	}
	
	private Property[] getOrdInfoDivProperty() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "order_label");
	}
	
	private Property[] getHarvestReportInfoDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "title_label");
	}
	
	private Property[] getPrintBtnProperty() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Print This Page");
	}
	
	private Property[] getReplaceBtnProperty() {
		return Property.toPropertyArray(".class", "Html.A", ".className", "harvestReportBtn", ".text", new RegularExpression("Replace Lost (Licence|Permit)", false));
	}
	
	private Property[] getHarvestReportsDivProperty() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "harvestReports");
	}
	/**End of Property methods */
	
	public void clickPrevious() {
		browser.clickGuiObject(Property.atList(this.getTopPrevLinkDivProperty(), this.getPrevLinkProperty()), true, 0);
	}
	
	public void clickBottomPrevious() {
		browser.clickGuiObject(Property.atList(this.getBottomPrevLinkDivProperty(), this.getPrevLinkProperty()), true, 0);
	}
	
	public void clickPrintBtn() {
		browser.clickGuiObject(this.getPrintBtnProperty());
	}
	
	public void clickOrderNumber(String ordNum) {
		browser.clickGuiObject(".class", "Html.A", ".text", ordNum);
	}

	public String getPageTitleAndCaption() {
		return browser.getObjectText(this.getPageTitleDivProperty());
	}
	
	public String getLicName() {
		return browser.getObjectText(this.getLicNameDivProperty());
	}

	public void verifyLicName(String licName){
		String licNameFromUI = getLicName();
		if(licName.equals(licNameFromUI)){
			logger.info("Successfully verify Licence name:"+licName);
		}else throw new ErrorOnPageException("Failed to verify licence name", licName, licNameFromUI);
	}
	
	public String getLicDetailsTitle() {
		return browser.getObjectText(this.getDetailsTitleDivProperty());
	}
	
	private String getLicenseInfo(String reg, String replacedText) {
		String text = browser.getObjectText(Property.atList(this.getLicDetailsDivProperty(), 
				this.getLicInfoDivProperty(reg)));
		text = text.replace(replacedText, StringUtil.EMPTY).trim();
		return text;
	}
	
	public String getLicenseNum() {
		return this.getLicenseInfo("^Licence #: \\d+", "Licence #:");
	}
	
	public String getLicenseYear() {
		return this.getLicenseInfo("^Year: \\d+", "Year:");
	}
	
	public String getLicenseValidDates() {
		return this.getLicenseInfo("^Valid Dates:.*", "Valid Dates:");
	}
	
	public String getLicenseValidDate() {
		return this.getLicenseInfo("^Valid:.*", "Valid:");
	}
	
	public boolean isLicenseTypeExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getLicDetailsDivProperty(), 
				this.getLicInfoDivProperty("^Type:.*")));
	}
	
	public String getLicenseType() {
		return this.getLicenseInfo("^Type:.*", "Type:");
	}
	
	public String getLicenseStatus() {
		return this.getLicenseInfo("^Status:.*", "Status:");
	}
	
	public boolean isLicenseStatusExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getLicDetailsDivProperty(), 
				this.getLicInfoDivProperty("^Status:.*")));
	}
	
	public String getOrdDetailsTitle() {
		return browser.getObjectText(Property.atList(this.getOrdDetailsDivProperty(), this.getDetailsTitleDivProperty()));
	}
	
	public int getNumOfOrders() {
		IHtmlObject[] objs = browser.getHtmlObject(this.getOrdInfoDivProperty());
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public List<String> getOrderInfo() {
		return browser.getObjectsText(this.getOrdInfoDivProperty());
	}
	
	public boolean isReplaceLostLicBtnExist() {
		return browser.checkHtmlObjectExists(this.getReplaceBtnProperty());
	}
	
	public void clickReplaceLostLicBtn() {
		browser.clickGuiObject(this.getReplaceBtnProperty());
	}
	
	public boolean compareLicDetails(PrivilegeInfo privilege, String timeCode) {
		boolean result = true;
		result &= MiscFunctions.compareString("privilege name", privilege.name, this.getLicName());
		result &= MiscFunctions.compareString("License Number", privilege.privilegeId, this.getLicenseNum());
		result &= MiscFunctions.compareString("License Year", privilege.licenseYear, this.getLicenseYear());
		
		//Lesley[20140221]: update due to valid dates not shown in license details according to ui-options.xml
		String brand = MiscFunctions.getPLNameFromURL(browser.url());
		if (WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.HFWebDisplayValidDates, brand)) {
			if (StringUtil.isEmpty(privilege.validToDate)) {
				result &= MiscFunctions.matchString("License Valid Date", this.getLicenseValidDate(), privilege.validFromDate + ".* " + timeCode);
			} else {
				result &= MiscFunctions.matchString("License Valid Dates", this.getLicenseValidDates(), 
						privilege.validFromDate + ".* - " + privilege.validToDate + ".* " + timeCode); // " \\d\\d:\\d\\d [A|P]M " +
			}
		}		
		if (this.isLicenseTypeExist()) {
			result &= MiscFunctions.compareString("License Type", privilege.displaySubCategory, this.getLicenseType());
		} else {
			result &= MiscFunctions.compareResult("Display of license type", false, StringUtil.notEmpty(privilege.displaySubCategory));
		}
		if (this.isLicenseStatusExist()) {
			result &= MiscFunctions.compareString("License Status", privilege.status, this.getLicenseStatus());
		} else {
			result &= MiscFunctions.compareResult("Display of license status", false, StringUtil.notEmpty(privilege.status));
		}
		return result;
	}
	
	public void verifyLicDetails(PrivilegeInfo privilege, String timeCode) {
		if (!this.compareLicDetails(privilege, timeCode))
			throw new ErrorOnPageException("License Details section are wrong! Check logger info");
		logger.info("---Verify License Details section correctly!");
	}
	
	public boolean compareOrdDetails(OrderInfo... orders) {
		boolean result = true;
		if (orders.length != this.getNumOfOrders()) {
			result = false;
		} else {
			List<String> ordDetails = this.getOrderInfo();
			for (int i = 0; i < orders.length; i++) {
				OrderInfo order = orders[i];
				String expOrdInfo = order.orderType + " Order #: " + order.orderNum + " " + order.orderDate;
				result &= MiscFunctions.compareResult("#" + i + " order's info", expOrdInfo, ordDetails.get(i));
			}
		}
		return result;
	}
	
	public void verifyOrdDetails(OrderInfo... orders) {
		if (!this.compareOrdDetails(orders))
			throw new ErrorOnPageException("Order Details section on License Details page are wrong! Check logger info");
		logger.info("---Verify Order Details section on License Details page correctly!");
	}
	
	public boolean isHarvestReportsSecExist() {
		return browser.checkHtmlObjectExists(this.getHarvestReportsDivProperty());
	}
	
	public void printThisPage(boolean isPrint) {
		PrintDialogPage  printDialogPage=PrintDialogPage.getInstance();
		
		this.clickPrintBtn();
		printDialogPage.setDismissible(false);
		printDialogPage.waitLoading();
		if(isPrint){
			printDialogPage.clickPrint();
		}else{
			printDialogPage.clickCancel();
		}
		this.waitLoading();	
	}
	
	public String getHarvestReportsTitle() {
		return browser.getObjectText(Property.atList(this.getHarvestReportsDivProperty(), this.getDetailsTitleDivProperty()));
	}
	
	public int getNumOfHarvestReports() {
		IHtmlObject[] objs = browser.getHtmlObject(this.getHarvestReportInfoDivProp());
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public String getHarvestReportsText() {
		return browser.getObjectText(this.getHarvestReportsDivProperty());
	}

	private String generateExpHarvestReportsInfo(String description, Harvest... harvests) {
		String info = this.getHarvestReportsTitle();
		for (Harvest harvest : harvests) {
			info += "\\s*Tag number:\\s*" + harvest.harvestNum + "\\s*";
			if (StringUtil.isEmpty(harvest.confirmationNum)) {
				info += description;
			} else {
				info += harvest.dateOfKill + "\\s*" + 
						"Confirmation Number:\\s*" + harvest.confirmationNum + "\\s*" +
						"Reported Date:\\s*" + harvest.createdDateTime;
			}
		}
		return info;
	}
	
	public boolean compareHarvestReports(String description, Harvest... harvests) {
		String actualInfo = this.getHarvestReportsText();
		String expInfo = this.generateExpHarvestReportsInfo(description, harvests);
		return MiscFunctions.matchString("Harvest Reports info", actualInfo, expInfo);
	}
	
	public void clickHarvestNumber(String num) {
		browser.clickGuiObject(".class", "Html.A", ".text", num);
	}
	
	public String getHuntInfo() {
		return browser.getObjectText(Property.toPropertyArray(".class", "Html.DIV", ".className", "ordersHuntInfo"));
	}
	
	public void verifyHuntInfo(HuntInfo hunt) {
		String expInfo = "Hunt Information";
		//The display of the hunt information is configurable in related ui-options.xml,  <option name="huntInformation"> -> <option name="member-licenses" >
		expInfo += "\\s*Hunt Description: " + hunt.getDescription() + 
				"\\s*Hunt Code: " + hunt.getHuntCode() +
				"\\s*Species: " + hunt.getSpecie() + (StringUtil.notEmpty(hunt.getSpecieSubType()) ? " - "+hunt.getSpecieSubType() : "") + 
					(StringUtil.notEmpty(hunt.getWeaponDes()) ? "\\s*Weapon: "+hunt.getWeaponDes() : "");
		if (hunt.getDatePeriodInfo() != null) {
			expInfo += "\\s*Date Period: " + hunt.getDatePeriodInfo().getDatePeriodInfoForWeb(hunt.getLicYear()).replace("(", "\\(\\s*").replace(")", "\\)");
		}
		if (StringUtil.notEmpty(hunt.getHuntLocationInfo())) {
			expInfo += "\\s*Location: "+hunt.getHuntLocationInfo();
		}
		if (hunt.getLocationInfo() != null) {
			String subLocInfo = hunt.getLocationInfo().getAllSubLocationInfo();
			if (StringUtil.notEmpty(subLocInfo))
				expInfo += "\\s*Sub-Locations: " + subLocInfo.replace("(", "\\(\\s*").replace(")", "\\)");
		}
		expInfo += "\\s*# of Tags: " + hunt.getNumOfTagQty();
		if (hunt.getHuntParams() != null) {
			expInfo += "\\s*Additional Information\\s*" + hunt.getAllHuntParamInfo();
		}
		if (hunt.getLocationInfo() != null && StringUtil.notEmpty(hunt.getLocationInfo().getImage())) {
			expInfo += "\\s*Map of Hunt Location";
		}
		
		String actInfo = this.getHuntInfo();
		if (!actInfo.matches(expInfo)) {
			throw new ErrorOnPageException("The hunt info is wrong on license details page!", expInfo, actInfo);
		}
		logger.info("The hunt info is correct on license details page!");
	}
	
	public void clickHuntImageMapLink() {
		browser.clickGuiObject(".class", "Html.Span", ".id", "lottery-hunts-attribute-map-link");
	}
	
	public boolean isHuntImagePopDisplayed() {
		return browser.checkHtmlObjectDisplayed(Property.toPropertyArray(".class", "Html.DIV", ".className", "modalPopLite-mask", 
				".id", "modalPopLite-mask1"));
	}
	
	public boolean isHuntImageExist(String imgNm) {
		return browser.checkHtmlObjectExists(Property.atList(
				Property.toPropertyArray(".class", "Html.Div", ".id", new RegularExpression("lottery-hunt-image-inner-\\d+_\\d+", false)), 
				Property.toPropertyArray(".class", "Html.Img", ".src", new RegularExpression("/webphotos/qa-photos/huntLocImages/.*/"+imgNm, false))));
	}
	
	public void closeHuntImagePop() {
		browser.clickGuiObject(".class", "Html.A", ".id", new RegularExpression("lottery-hunt-image-close-\\d+_\\d+", false));
		this.waitLoading();
	}
	
	public void verifyHuntLocMapLinkOnLicDetailsPg(String img) {
		boolean result = true;
		// before click, no image popup shown
		if (this.isHuntImagePopDisplayed()) {
			result = false;
			logger.error("hunt location image pop should not be shown before click the image link.");
		}		
		// click image link and image popup shown
		this.clickHuntImageMapLink();
		if (!this.isHuntImagePopDisplayed()) {
			result = false;
			logger.error("hunt location image pop should be shown after click the image link.");
		}
		if (!this.isHuntImageExist(img)) {
			result = false;
			logger.error("hunt location image should exist:" + img);
		}
		// close image popup
		this.closeHuntImagePop();
		if (this.isHuntImagePopDisplayed()) {
			result = false;
			logger.error("hunt location image pop should not be shown after close it.");
		}
		
		if (!result) {
			throw new ErrorOnPageException("Hunt Location Image link is wrong on License Details page!");
		}
		logger.info("---Successfully verify Hunt Location Image link on License Details page!");
	}
}
