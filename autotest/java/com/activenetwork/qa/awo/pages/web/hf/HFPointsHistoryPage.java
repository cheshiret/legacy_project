package com.activenetwork.qa.awo.pages.web.hf;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.HFLotteryProperty;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CustomerPoint;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: HF Web points history page. My Account -> Points
 * 
 * @author Lesley Wang
 * @Date  Sep 29, 2013
 */
public class HFPointsHistoryPage extends HFMyAccountPanel {
	private static HFPointsHistoryPage _instance = null;

	public static HFPointsHistoryPage getInstance() {
		if (null == _instance)
			_instance = new HFPointsHistoryPage();

		return _instance;
	}
	
	protected HFPointsHistoryPage() {
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] h1() {
		return Property.toPropertyArray(".class", "h1");
	}
	
	protected Property[] infoMsgDIV() {
		return Property.concatPropertyArray(div(), ".className", "willappear");
	}
	
	protected Property[] pointItemDIV() {
		return Property.concatPropertyArray(div(), ".className", "groupcard VIEW");
	}
	
	protected Property[] pointItemDIV(String pointTypeDes) {
		return Property.concatPropertyArray(div(), ".className", "groupcard VIEW", ".text", new RegularExpression("^" + pointTypeDes + ".*", false));
	}
	
	protected Property[] nameLink() {
		return Property.concatPropertyArray(a(), ".className", "nameLink");
	}
	
	protected Property[] currentBalanceDIV() {
		return Property.concatPropertyArray(div(), ".className", "details crtbalance");
	}
	
	protected Property[] detailsDIV(String label) {
		return Property.concatPropertyArray(span(), ".className", "attr", ".text", new RegularExpression("^" + label + ".*", false));
	}
	
	protected Property[] detailsValueSpan() {
		return Property.concatPropertyArray(span(), ".className", "attrValue");
	}
	
	protected Property[] selectedPointsLink() {
		return Property.concatPropertyArray(this.pointsLink(), ".className", "accountside in");
	}
	
	protected Property[] pointTypeLink(String point) {
		return Property.concatPropertyArray(this.a(), ".text", point);
	}
	/** Page Object Property Definition END */
	
	
	@Override
	public boolean exists() {
//		return browser.getObjectText(Property.atList(this.pageTitleDIV(), this.h1())).equalsIgnoreCase("Points");
		return browser.checkHtmlObjectExists(this.selectedPointsLink());
	}
	
	public String getPageCaption() {
		return browser.getObjectText(Property.atList(this.pageTitleDiv(), this.captionSpan()));
	}
	
	public String getNoPointsMsg() {
		return browser.getObjectText(this.infoMsgDIV());
	}
	
	public boolean isNoPointsMsgExist() {
		return browser.checkHtmlObjectExists(this.infoMsgDIV());
	}
	
	private List<String> getAllPointTypeDescr() {
		return browser.getObjectsText(this.nameLink());
	}
	
	private List<String> getAllPointInfoValue(String label) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.detailsDIV(label), this.detailsValueSpan()));
		List<String> texts = new ArrayList<String>();
		if(objs.length>0) {
			for(int i=0; i<objs.length; i++){
				texts.add(objs[i].text());
			}
		} 
		
		Browser.unregister(objs);
		return texts;
	}
	
	private List<String> getAllPointBalance() {
		String label = WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HFSK_Points_Results_Item_CrtBalance);
		return this.getAllPointInfoValue(RegularExpression.getMatches(label, "[A-Za-z ]+:")[0].trim()); //"Current Pool Balance");
	}
	
	private List<String> getAllPointAction() {
		String label = WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HFSK_Points_Results_Item_TrackingType);
		return this.getAllPointInfoValue(RegularExpression.getMatches(label, "[A-Za-z ]+:")[0].trim()); //"Tracking Type");
	}
	
	private List<String> getAllPointPreviousBalance() {
		String label = WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HFSK_Points_Results_Item_PrevBalance);
		return this.getAllPointInfoValue(RegularExpression.getMatches(label, "[A-Za-z ]+:")[0].trim()); //"Previous Pool Balance");
	}
	
	private List<String> getAllPointDate() {
		String label = WebConfiguration.getInstance().getPropertiesValue(Conf.HFLottery_prop, HFLotteryProperty.HFSK_Points_Results_Item_Date);
		return this.getAllPointInfoValue(RegularExpression.getMatches(label, "[A-Za-z ]+:")[0].trim()); //"Date");
	}
	
	public CustomerPoint[] getAllPointsInfo() {
		
		List<String> pointsDescr = this.getAllPointTypeDescr();
		List<String> pointsBalance = this.getAllPointBalance();
		List<String> pointsAction = this.getAllPointAction();
		List<String> pointsPrevBalance = this.getAllPointPreviousBalance();
		List<String> pointsDate = this.getAllPointDate();
		
		CustomerPoint[] points = new CustomerPoint[pointsDescr.size()];
		for (int i = 0; i < points.length; i++) {
			CustomerPoint point = new CustomerPoint();
			point.pointTypeDescr = pointsDescr.get(i);
			point.pointBalance = pointsBalance.get(i);
			point.trackingType = pointsAction.get(i);
			point.prevBalance = pointsPrevBalance.get(i);
			point.dateOfChange = pointsDate.get(i);
			points[i] = point;
		}
		return points;
	}
	
	public void clickPointType(String pointDes) {
		browser.clickGuiObject(this.pointTypeLink(pointDes));
	}
	
	private String getPointInfoValue(String pointTypeDes, String label) {
		return browser.getObjectText(Property.atList(this.pointItemDIV(pointTypeDes), this.detailsDIV(label), this.detailsValueSpan()));
	}
	
	public String getPointCurrentBalance(String pointTypeDes) {
		return this.getPointInfoValue(pointTypeDes, "Current Pool Balance");
	}
	
	public String getPointPreBalance(String pointTypeDes) {
		return this.getPointInfoValue(pointTypeDes, "Previous Pool Balance");
	}
	
	public String getPointTrackingType(String pointTypeDes) {
		return this.getPointInfoValue(pointTypeDes, "Tracking Type");
	}
	
	public String getPointDate(String pointTypeDes) {
		return this.getPointInfoValue(pointTypeDes, "Date");
	}
	
	public CustomerPoint getPointInfo(String pointTypeDes) {
		CustomerPoint point = new CustomerPoint();
		point.pointTypeDescr = pointTypeDes;
		point.pointBalance = this.getPointCurrentBalance(pointTypeDes);
		point.trackingType = this.getPointTrackingType(pointTypeDes);
		point.prevBalance = this.getPointPreBalance(pointTypeDes);
		point.dateOfChange = this.getPointDate(pointTypeDes);
		return point;
	}
	
	public void verifyPointInfo(CustomerPoint expPoint) {
		CustomerPoint actPoint = this.getPointInfo(expPoint.pointTypeDescr);
		
		boolean result = true;
		result &= MiscFunctions.compareString("point current balance", expPoint.pointBalance, actPoint.pointBalance);
		result &= MiscFunctions.compareString("point tracking type", expPoint.trackingType, actPoint.trackingType);
		result &= MiscFunctions.compareString("point previous balance", expPoint.prevBalance, actPoint.prevBalance);
		result &= MiscFunctions.compareString("point date", expPoint.dateOfChange, actPoint.dateOfChange);
		
		if (!result) {
			throw new ErrorOnPageException("Point info is wrong! Check logger error!");
		}
		logger.info("Point info is correct!");
	}
}
