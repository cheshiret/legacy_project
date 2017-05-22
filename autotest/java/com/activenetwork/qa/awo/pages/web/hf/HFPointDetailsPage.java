package com.activenetwork.qa.awo.pages.web.hf;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.HFLotteryProperty;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CustomerPoint;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: HF Web point details page. My Account -> Points -> specific point type
 * 
 * @author Lesley Wang
 * @Date  Dec 2, 2013
 */
public class HFPointDetailsPage extends HFMyAccountPanel {
	private static HFPointDetailsPage _instance = null;

	public static HFPointDetailsPage getInstance() {
		if (null == _instance)
			_instance = new HFPointDetailsPage();

		return _instance;
	}
	
	protected HFPointDetailsPage() {
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] pointForm() {
		return Property.concatPropertyArray(this.form(), ".id", "HFPointsHistoryKit_form");
	}
	
	protected Property[] h1() {
		return Property.toPropertyArray(".class", "h1");
	}
	
	protected Property[] dateFilterList() {
		return Property.concatPropertyArray(this.select(), ".id", "dateRange");
	}
	
	protected Property[] errorDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "errorList");
	}
	
	protected Property[] headerPageNavDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "listControl_hdr");
	}
	
	protected Property[] footerPageNavDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "listControl_ftr");
	}
	
	protected Property[] pointDetailsDateSpan() {
		return Property.concatPropertyArray(this.span(), ".className", "attrTitle");
	}
	
	protected Property[] pointDetailsAttrSpan(String label) {
		return Property.concatPropertyArray(this.span(), ".className", new RegularExpression("^attr\\s?(medium|wide)?$", false), 
				".text", new RegularExpression("^" + label + ".*", false));
	}
	
	protected Property[] previousPgLink() {
		return Property.concatPropertyArray(this.a(), ".className", "prevPage");
	}
	/** Page Object Property Definition End */
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.pointForm());
	}
	
	public String getPageHeader() {
		return browser.getObjectText(Property.atList(this.pageTitleDiv(), this.h1()));
	}
	
	public String getCaption() {
		return browser.getObjectText(Property.atList(this.pageTitleDiv(), this.captionSpan()));
	}
	
	public List<String> getDateFilterOptions() {
		return browser.getDropdownElements(this.dateFilterList());
	}
	
	public void selectDateFilter(String dateRange) {
		browser.selectDropdownList(this.dateFilterList(), dateRange);
	}
	
	public void filterByDateRange(String dateRange) {
		logger.info("Filter point details by " + dateRange);
		this.selectDateFilter(dateRange);
		this.waitLoading();
	}
	
	public boolean isNoPointDetailsMsgExist() {
		return browser.checkHtmlObjectExists(this.errorDiv());
	}
	
	public String getNoPointDetailsMsg() {
		return browser.getObjectText(this.errorDiv());
	}
	
	public boolean isHeaderPageNavExist() {
		return browser.checkHtmlObjectExists(this.headerPageNavDiv());
	}
	
	public boolean isFooterPageNavExist() {
		return browser.checkHtmlObjectExists(this.footerPageNavDiv());
	}
	
	public String getPointDetailsDate(int index) {
		List<String> texts = browser.getObjectsText(this.pointDetailsDateSpan());
		return texts.get(index);
	}
	
	private String getPointDetailsAttrValue(String label, int index) {
		IHtmlObject[] objs = browser.getHtmlObject(this.pointDetailsAttrSpan(label));
		if (objs.length < 1 || objs.length <= index) {
			throw new ObjectNotFoundException("Can't find the attribute with label=" + label + ", index=" + index);
		}
		String text = objs[index].text();
		text = text.split(":")[1].trim();
		return text;
	}
	
	public String getPointCurrentBalance(int index) {
		String label = RegularExpression.getMatches(WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HFSK_PointsHistory_Results_Item_CrtBalance), "[A-Za-z ]+:")[0].trim();
		if(StringUtil.notEmpty(label)){
			return getPointDetailsAttrValue(label, index);//"Current Pool Balance");
		}else return StringUtil.EMPTY;
	}
	
	public String getPointTrackingType(int index) {
		String label = RegularExpression.getMatches(WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HFSK_PointsHistory_Results_Item_TrackingType), "[A-Za-z ]+:")[0].trim();
		if(StringUtil.notEmpty(label)){
			return getPointDetailsAttrValue(label, index);//"Tracking Type");
		}else return StringUtil.EMPTY;
	}
	
	public String getPointAdjustment(int index) {
		String label = RegularExpression.getMatches(WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HFSK_PointsHistory_Results_Item_Adjustment), "[A-Za-z ]+:")[0].trim();
		if(StringUtil.notEmpty(label)){
			return getPointDetailsAttrValue(label, index);//"Adjustment"
		}else return StringUtil.EMPTY;
	
	}
	
	public String getPointPreBalance(int index) {
		String label = RegularExpression.getMatches(WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HFSK_PointsHistory_Results_Item_PrevBalance), "[A-Za-z ]+:")[0].trim();
		if(StringUtil.notEmpty(label)){
			return getPointDetailsAttrValue(label, index);//"Previous Pool Balance"
		}else return StringUtil.EMPTY; 
		
	}
	
	public String getPointComments(int index) {
		String label = RegularExpression.getMatches(WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HFSK_PointsHistory_Results_Item_Comments), "[A-Za-z ]+:")[0].trim();
		if(StringUtil.notEmpty(label)){
			return getPointDetailsAttrValue(label, index);//"Comments"
		}else return StringUtil.EMPTY;
		
	}
	
	public boolean comparePointHistoryDetails(CustomerPoint... points) {
		boolean result = true;
		for (int i = 0; i < points.length; i++) {
			CustomerPoint exp = points[i];
			result &= MiscFunctions.compareString("point detail change date", exp.dateOfChange, this.getPointDetailsDate(i));
			result &= MiscFunctions.compareString("point detail trakcing type", exp.trackingType, this.getPointTrackingType(i));
			String adjustment = StringUtil.isEmpty(exp.pointToAdd) ? exp.pointToDeduct : exp.pointToAdd;
			String attributes = WebConfiguration.getInstance().getUIOption(Conf.pl_UIOptions, UIOptions.HFSKPointDetailsAttributes, "hfsk");
			if(attributes.contains("adjustment")){
				result &= MiscFunctions.compareString("point detail adjustment", adjustment, this.getPointAdjustment(i));
			}
			result &= MiscFunctions.compareString("point detail current balance", exp.pointBalance, this.getPointCurrentBalance(i));
			if (exp.prevBalance != null) {
				result &= MiscFunctions.compareString("point detail previous balance", exp.prevBalance, this.getPointPreBalance(i));
			}
			if (StringUtil.notEmpty(exp.comments)) {
				result &= MiscFunctions.compareString("point detail comments", exp.comments, this.getPointComments(i));
			}
		}
		return result;
	}
	
	public void verifyPointHistoryDetails(CustomerPoint... points) {
		boolean result = this.comparePointHistoryDetails(points);
		if (!result) {
			throw new ErrorOnPageException("Point history Details page info is wrong! Check logger errors above!");
		}
		logger.info("---Successfully verify point history details info!");
	}
	
	public void clickTopPreviousPgLink() {
		browser.clickGuiObject(this.previousPgLink(), 0);
	}
	
	public void clickBottomPreviousPgLink() {
		browser.clickGuiObject(this.previousPgLink(), 1);
	}
 }
