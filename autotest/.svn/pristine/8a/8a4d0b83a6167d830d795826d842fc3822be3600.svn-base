package com.activenetwork.qa.awo.pages.web.hf;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.PrintDialogPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: HF Order Details page, view the page by click the order number on License Details page
 * 
 * @author Lesley Wang
 * @Date  Jun 5, 2013
 */
public class HFOrderDetailsPage extends HFMyAccountPanel {
	private static HFOrderDetailsPage _instance = null;
	
	public static HFOrderDetailsPage getInstance() {
		if (null == _instance)
			_instance = new HFOrderDetailsPage();

		return _instance;
	}
	
	protected HFOrderDetailsPage() {
	}
	
	protected Property[] ordItemTitleDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "order_item_title");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "hfOrderDetails");
	}
	
	public void clickPreviousTopLink() {
		browser.clickGuiObject(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "previousLinkTop"), 
				Property.toPropertyArray(".class", "Html.A", ".text", "Previous")), true, 0);
	}
	
	public void clickPreviousBottomLink() {
		browser.clickGuiObject(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "previousLinkBottom"), 
				Property.toPropertyArray(".class", "Html.A", ".text", "Previous")), true, 0);
	}
	
	public void clickLicenseNum(String licNum) {
		browser.clickGuiObject(".class", "Html.A", ".href", "/memberHFLicenseDetails.do?id="+licNum);
	}
	
	public void clickPrintButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Print This Page");
	}
	
	public String getPageTitleAndCaption() {
		return browser.getObjectText(".id", "pagetitle");
	}
	
	public String getOrderNum() {
		String text = browser.getObjectText(".class", "Html.Span", ".text", new RegularExpression("Order #: \\d-\\d+", false));
		return text.split(":")[1].trim();
	}
	
	public String getOrderDate() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "titleRowDetail");
	}
	
	public String getLicNameAndYear() {
//		return browser.getObjectText(".class", "Html.DIV", ".className", "order_item_title"); 
		return browser.getObjectText(Property.atList(this.ordItemTitleDiv(), this.span())); //Lesley[20140227] udpate due to UI change
	}
	
	public String getLicName() {
		String text = this.getLicNameAndYear();
		return text.split("\\(")[0].trim();
	}
	
	public String getLicYear() {
		String text = this.getLicNameAndYear();
		return text.split("\\(")[1].trim().replace(")", StringUtil.EMPTY);
	}
	
	public String getLicPriceText() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "order_item_price");
	}
	
	public String getLicTotalPrice() {
		String text = this.getLicPriceText();
		return text.split(":")[1].replace("$", StringUtil.EMPTY).trim();
	}
	
	public String getLicQty() {
		String text = this.getLicPriceText();
		return text.contains("@") ? StringUtil.getSubstring(text, "(", "@") : "1"; 
	}
	
	public String getLicPrice() {
		String text = this.getLicPriceText();
		return text.split("\\$")[1].replace("):", StringUtil.EMPTY).trim(); 
	}
	
	private String getLicDetails() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "hf_license");
	}
	
	protected Property[] licenseDIV() {
		return Property.concatPropertyArray(this.div(), ".className", "hf_license");
	}
	
	protected Property[] licenseNumLink() {
		return Property.concatPropertyArray(this.a(), ".href", new RegularExpression("/memberHFLicenseDetails\\.do\\?id=\\d+", false));
	}
	
	public String getLicNumLinkText() {
		return browser.getObjectText(Property.atList(licenseDIV(), licenseNumLink()));
	}
	
	public String getLicNum() {
		String text = this.getLicNumLinkText();
		return RegularExpression.getMatches(text, "\\d+")[0];
	}
	
	public String getValidDates() {
		String text = browser.getObjectText(Property.atList(licenseDIV(), span()));
		return text.replace(":", "").trim();
	}
	
	public String getLicValidFromDate() {
		String text = this.getLicDetails();
		String date = text.contains("-") ? StringUtil.getSubstring(text, ":", "-") : StringUtil.getSubString(text, ":");
		return date;
	}
	
	public String getLicValidToDate() {
		String text = this.getLicDetails();
		String date = text.contains("-") ? StringUtil.getSubString(text, "-") : StringUtil.EMPTY;
		return date;
	}
	
	public String getLicStatus() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "order_item_notice");
	}
	
	public boolean isLicNumLinkExist(String licNum) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", "/memberHFLicenseDetails.do?id="+licNum);
	}
	
	public String getFeeMsg() {
		return browser.getObjectText(".class", "Html.Div", ".className", "hf_notice");
	}

	public List<String> getLicDetailsInfo() {
		return browser.getObjectsText(Property.toPropertyArray(".id", "orderItem", ".className", "orderItem"));
	}
	
	public void verifyLicenseOrderDetails(boolean isValidDatesHide, String ordNum, String ordDate, PrivilegeInfo... privileges) {
		boolean result = true;
		// verify order num and date
		result &= MiscFunctions.compareString("order number", ordNum, this.getOrderNum());
		result &= MiscFunctions.compareString("order date", ordDate, this.getOrderDate());
		
		// verify license details
		List<String> expDetails = this.generateExpLicDetails(isValidDatesHide, privileges);
		List<String> actDetails = this.getLicDetailsInfo();
		for (int i = 0; i < expDetails.size(); i++) {
			result &= MiscFunctions.matchString("License Details for item#" + i , actDetails.get(i), expDetails.get(i));
		}
		
		if (!result) {
			throw new ErrorOnPageException(ordNum + " order details info is wrong!");
		}
		logger.info(ordNum + " order details info is correct!");
	}
	
	private List<String> generateExpLicDetails(boolean isValidDatesHide, PrivilegeInfo... privileges) {
		List<String> expDetails = new ArrayList<String>();
		String details = "";
		String pricInfo = "";
		for (int i = 0; i < privileges.length; i++) {
			PrivilegeInfo pri = privileges[i];
			if (StringUtil.notEmpty(pri.name)) {
				details = pri.name + "\\s*\\(" + pri.licenseYear + "\\)\\s*";
				pri.creationPrice = new DecimalFormat("0.00").format(Double.valueOf(pri.creationPrice));
				if (pri.qty.equals("1")) {
//					details += "Price:\\s*\\$" + pri.creationPrice; // Price: $8.60
					pricInfo = "Price:\\s*\\$" + pri.creationPrice; // Price: $8.60
				} else {
					Double totalPrice = Double.valueOf(pri.qty) * Double.valueOf(pri.creationPrice);
//					details += "Price\\s*\\(" + pri.qty + "\\s*@\\s*\\$" + pri.creationPrice + "\\):\\s*\\$" + new DecimalFormat("0.00").format(totalPrice); //Price (2 @ $8.60): $17.20
					pricInfo = "Price\\s*\\(" + pri.qty + "\\s*@\\s*\\$" + pri.creationPrice + "\\):\\s*\\$" + new DecimalFormat("0.00").format(totalPrice); //Price (2 @ $8.60): $17.20
				}
			}
			details += "\\s*Licence\\s*#\\s*" + pri.privilegeId; 
			if (!isValidDatesHide) { //Lesley[20131030]: update per defect 54237
				details +=  "\\s*:\\s*" + pri.validFromDate + ".*";
				if (StringUtil.notEmpty(pri.validToDate)) {
					details += "-" + pri.validToDate + ".*";
				}
			}	 
			details += "\\s*" + pri.status + "(\\s)?";	
			if (i + 1 == privileges.length || StringUtil.notEmpty(privileges[i+1].name)) {
				details += pricInfo;
				expDetails.add(details);
			}
		}
		return expDetails;
	}
	
	public void printThisPage(boolean isPrint) {
		PrintDialogPage  printDialogPage=PrintDialogPage.getInstance();
		
		this.clickPrintButton();
		printDialogPage.setDismissible(false);
		printDialogPage.waitLoading();
		if(isPrint){
			printDialogPage.clickPrint();
		}else{
			printDialogPage.clickCancel();
		}
		this.waitLoading();	
	}
	
	public String getHuntInfoByPrivNameAndLy(String privName, String ly) {
		return browser.getObjectText(Property.toPropertyArray(".class", "Html.DIV", ".className", "orderItem", ".text", 
						new RegularExpression("^"+privName+" \\("+ly+"\\).*", false)));
	}
	
	public void verifyHuntInfo(PrivilegeInfo...privs) {
		boolean result = true;
		
		for (int i = 0; i < privs.length; i++) {
			PrivilegeInfo priv = privs[i];
			String itemInfo = this.getHuntInfoByPrivNameAndLy(priv.name, priv.licenseYear);
			String expHuntInfo = priv.name + " \\(" + priv.licenseYear + "\\).*";
			for (int j = 0; j < priv.hunts.size(); j++) {
				HuntInfo hunt = priv.hunts.get(j);
				String info = "Hunt Information";
				//The display of the hunt information is configurable in related ui-options.xml,  <option name="huntInformation"> -> <option name="order-history" >
				info += "\\s*Hunt Description: " + hunt.getDescription() + 
							"\\s*Hunt Code: " + hunt.getHuntCode() +
							"\\s*Species: " + hunt.getSpecie() + (StringUtil.notEmpty(hunt.getSpecieSubType()) ? " - "+hunt.getSpecieSubType() : "") + 
							(StringUtil.notEmpty(hunt.getWeaponDes()) ? "\\s*Weapon: "+hunt.getWeaponDes() : "");
				if (hunt.getDatePeriodInfo() != null) {
					info += "\\s*Date Period: " + hunt.getDatePeriodInfo().getDatePeriodInfoForWeb(priv.licenseYear).replace("(", "\\(\\s*").replace(")", "\\)");
				}
				if (StringUtil.notEmpty(hunt.getHuntLocationInfo())) {
					info += "\\s*Location: "+hunt.getHuntLocationInfo();
				}
				if (hunt.getLocationInfo() != null) {
					String subLocInfo = hunt.getLocationInfo().getAllSubLocationInfo();
					if (StringUtil.notEmpty(subLocInfo))
						info += "\\s*Sub-Locations: " + subLocInfo.replace("(", "\\(").replace(")", "\\)");
				}
				info += "\\s*# of Tags: " + hunt.getNumOfTagQty();
				expHuntInfo += info + ".*";
			}
			result &= MiscFunctions.matchString("The hunt info of " + priv.name + " " + priv.licenseYear, itemInfo, expHuntInfo);
		}
		
		if (!result) {
			throw new ErrorOnPageException("Hunt Info on order details page is wrong!");
		}
		logger.info("Hunt Info  on order details page is correct!");
	}
}
