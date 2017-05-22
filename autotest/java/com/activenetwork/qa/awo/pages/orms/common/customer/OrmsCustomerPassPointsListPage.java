package com.activenetwork.qa.awo.pages.orms.common.customer;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Customer.PointInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Apr 28, 2014
 */
public class OrmsCustomerPassPointsListPage extends OrmsCustomerPassDetailsCommonPage {
	
	private static OrmsCustomerPassPointsListPage _instance = null;
	
	private OrmsCustomerPassPointsListPage() {}
	
	public static OrmsCustomerPassPointsListPage getInstance() {
		if(_instance == null) _instance = new OrmsCustomerPassPointsListPage();
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(pointType());
	}
	
	private Property[] pendingPoints() {
		return Property.toPropertyArray("", "");
	}
	
	private Property[] earnedPoints() {
		return Property.toPropertyArray("", "");
	}
	
	private Property[] notRedeemable() {
		return Property.toPropertyArray("", "");
	}
	
	private Property[] customerName() {
		return Property.toPropertyArray(".id", "MembershipPassView.customer.name");
	}
	
	private Property[] customerPhone() {
		return Property.toPropertyArray(".id", "MembershipPassView.customer.phone");
	}
	
	private Property[] customerEmail() {
		return Property.toPropertyArray(".id", "MembershipPassView.customer.email");
	}
	
	private Property[] customerPhoneContactPreference() {
		return Property.toPropertyArray(".id", "MembershipPassView.customer.phoneContactPref:CB_TO_NAME");
	}
	
	private Property[] customerPreferenceContactTime() {
		return Property.toPropertyArray(".id", "MembershipPassView.customer.preferedContactTime:CB_TO_NAME");
	}
	
	private Property[] pointType() {
		return Property.toPropertyArray(".id", "LoyaltyPointTrackingSearchCriteria.pointType");
	}
	
	private Property[] status() {
		return Property.toPropertyArray(".id", "LoyaltyPointTrackingSearchCriteria.status");
	}
	
	private Property[] trackingType() {
		return Property.toPropertyArray(".id", "LoyaltyPointTrackingSearchCriteria.transactionType");
	}
	
	private Property[] location() {
		return Property.toPropertyArray(".id", "LoyaltyPointTrackingSearchCriteria.locationName");
	}
	
	private Property[] search() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("^Search$", false));
	}
	
	private Property[] addRemovePoints() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add/Remove Points");
	}
	
	private Property[] pointsListTable() {
		return Property.toPropertyArray(".id", "pointsGrid_LIST");
	}
	
	private Property[] ok() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK");
	}
	
	public int getPendingPoints() {
		String text = browser.getTextFieldValue(pendingPoints());
		return Integer.parseInt(text);
	}
	
	public int getEarnedPoints() {
		String text = browser.getTextFieldValue(earnedPoints());
		return Integer.parseInt(text);
	}
	
	public int getNotRedeemablePoints() {
		String text = browser.getTextFieldValue(notRedeemable());
		return Integer.parseInt(text);
	}
	
	public String getCustomerName() {
		return browser.getTextFieldValue(customerName());
	}
	
	public String getCustomerPhone() {
		return browser.getTextFieldValue(customerPhone());
	}
	
	public String getCustomerEmail() {
		return browser.getTextFieldValue(customerEmail());
	}
	
	public String getCustomerPhoneContactPreference() {
		return browser.getTextFieldValue(customerPhoneContactPreference());
	}
	
	public String getCustomerPreferenceContactTime() {
		return browser.getTextFieldValue(customerPreferenceContactTime());
	}
	
	public void selectPointType(String type) {
		browser.selectDropdownList(pointType(), type);
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(status(), status);
	}
	
	public void selectTrackingType(String type) {
		browser.selectDropdownList(trackingType(), type);
	}
	
	public void setLocation(String location) {
		browser.setTextField(location(), location);
	}
	
	public void clickSearch() {
		browser.clickGuiObject(search());
	}
	
	public void clickAddRemovePoints() {
		browser.clickGuiObject(addRemovePoints());
	}
	
	public void searchPointByPointType(String type) {
		this.selectPointType(type);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private IHtmlObject[] getPointsListTable() {
		IHtmlObject objs[] = browser.getTableTestObject(pointsListTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Points list table object.");
		
		return objs;
	}
	
	private static final String DATE_TIME_COL = "Date & Time";
	private static final String TRACKING_TYPE_COL = "Tracking Type";
	private static final String TRACKING_DETAILS_COL = "Tracking Details";
	private static final String PLUS_MINUS_POINTS_COL = "±Points";
	private static final String PENDING_POINTS_COL = "Pending Points";
	private static final String EARNED_POINTS_COL = "Earned Points";
	private static final String STATUS_COL = "Status";
	private static final String EARN_DATE_COL = "Earn Date";
	private static final String ORDER_NUM_COL = "Order #";
	private static final String LOCATION_COL = "Location";
	private static final String USER_COL = "User";
	
	public int getPendingPoints(String ordNum) {
		IHtmlObject objs[] = getPointsListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int colIndex = table.findColumn(0, ORDER_NUM_COL);
		int col = table.findColumn(0, PENDING_POINTS_COL);
		int row = table.findRow(colIndex, ordNum);
		String pendingPoints = table.getCellValue(row, col);
		
		Browser.unregister(objs);
		return Integer.parseInt(pendingPoints);
	}
	
	public int getPendingPoints(String pointType, String ordNum) {
		this.searchPointByPointType(pointType);
		return this.getPendingPoints(ordNum);
	}
	
	public void verifyPendingPoints(String pointType, String ordNum, int expected) {
		if(!MiscFunctions.compareResult(pointType + " Pending Points of Order: " + ordNum, expected, this.getPendingPoints(pointType, ordNum)))
			throw new ErrorOnPageException(pointType + " Pending Points of Order: " + ordNum + " is NOT correct.");
	}
	
	public List<PointInfo> getPointsInfo(String transaction, String ordNum) {
		IHtmlObject objs[] = getPointsListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int colIndex = table.findColumn(0, ORDER_NUM_COL);
		int rows[] = table.findRows(colIndex, ordNum);
		
		List<PointInfo> points = new ArrayList<PointInfo>();
		int trackingTypeColIndex = 0;
		int trackingDetailsColIndex = 0;
		int plusMinusPointsColIndex = 0;
		int pendingPointsColIndex = 0;
		int earnedPointsColIndex = 0;
		int statusColIndex = 0;
		int earnDateColIndex = 0;
		int orderNumColIndex = 0;
		int locationColIndex = 0;
		int userColIndex = 0;
		
		if(rows != null && rows.length > 0) {
			trackingTypeColIndex = table.findColumn(0, TRACKING_TYPE_COL);
			trackingDetailsColIndex = table.findColumn(0, TRACKING_DETAILS_COL);
			plusMinusPointsColIndex = table.findColumn(0, PLUS_MINUS_POINTS_COL);
			pendingPointsColIndex = table.findColumn(0, PENDING_POINTS_COL);
			earnedPointsColIndex = table.findColumn(0, EARNED_POINTS_COL);
			statusColIndex = table.findColumn(0, STATUS_COL);
			earnDateColIndex = table.findColumn(0, EARN_DATE_COL);
			orderNumColIndex = table.findColumn(0, ORDER_NUM_COL);
			locationColIndex = table.findColumn(0, LOCATION_COL);
			userColIndex = table.findColumn(0, USER_COL);
		}
		
		for(int i = 0; i < rows.length; i ++) {
			List<String> values = table.getRowValues(rows[i]);
			
			if((!StringUtil.isEmpty(transaction) && values.get(trackingDetailsColIndex).equalsIgnoreCase(transaction)) || StringUtil.isEmpty(transaction)) {
				PointInfo point = new Customer().new PointInfo();
				point.setTrackingType(values.get(trackingTypeColIndex));
				point.setTrackingDetails(values.get(trackingDetailsColIndex));
				point.setAddingpoints(values.get(plusMinusPointsColIndex));
				point.setPendingPoints(values.get(pendingPointsColIndex));
				point.setEarnedPoints(values.get(earnedPointsColIndex));
				point.setStatus(values.get(statusColIndex));
				point.setOrderNum(ordNum);
				point.setLocation(values.get(locationColIndex));
				point.setUser(values.get(userColIndex));
				
				points.add(point);
			}
		}
		
		return points;
	}
	
	public List<PointInfo> getPointsInfo(String ordNum) {
		return getPointsInfo(null, ordNum);
	}
	
	public PointInfo getPointInfo(String ordNum) {
		return getPointsInfo(ordNum).get(0);
	}
	
	public boolean comparePointInfo(PointInfo expected) {
		PointInfo actual = getPointInfo(expected.getOrderNum());
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Tracking Type", expected.getTrackingType(), actual.getTrackingType());
		result &= MiscFunctions.compareResult("Tracking Details", expected.getTrackingDetails(), actual.getTrackingDetails());
		result &= MiscFunctions.compareResult("±Points", expected.getAddingpoints(), actual.getAddingpoints());
		result &= MiscFunctions.compareResult("Pending Points", expected.getPendingPoints(), actual.getPendingPoints());
		if(!StringUtil.isEmpty(expected.getEarnedPoints())) {
			result &= MiscFunctions.compareResult("Earned Points", expected.getEarnedPoints(), actual.getEarnedPoints());
		}
		result &= MiscFunctions.compareResult("Status", expected.getStatus(), actual.getStatus());
		result &= MiscFunctions.compareResult("Order Number", expected.getOrderNum(), actual.getOrderNum());
		result &= MiscFunctions.compareResult("Location", expected.getLocation(), actual.getLocation());
		result &= MiscFunctions.compareResult("User", expected.getUser().replace(", ", ","), actual.getUser().replace(", ", ","));
		
		return result;
	}
	
	public void verifyPointInfo(PointInfo expected) {
		if(!comparePointInfo(expected)) throw new ErrorOnPageException("Point Info is NOT correct, please refer above log for details.");
	}
	
	public boolean comparePointInfo(List<PointInfo> expecteds) {
		return comparePointInfo(expecteds, false);
	}
	
	public boolean comparePointInfo(List<PointInfo> expecteds, boolean sumAddingPoints) {
		List<PointInfo> actuals = getPointsInfo(expecteds.get(0).getOrderNum());
		
		if(expecteds.size() != actuals.size()) return false;
		
		int sumOfAddingPoints = 0;
		boolean result = true;
		for(int i = 0; i < expecteds.size(); i ++) {
			result &= MiscFunctions.compareResult("Tracking Type - row " + (i + 1), expecteds.get(i).getTrackingType(), actuals.get(i).getTrackingType());
			result &= MiscFunctions.compareResult("Tracking Details - row" + (i + 1), expecteds.get(i).getTrackingDetails(), actuals.get(i).getTrackingDetails());
			if(sumAddingPoints) {
				sumOfAddingPoints += Integer.parseInt(actuals.get(i).getAddingpoints().substring(1));//remove prefix + or -
			} else {
				result &= MiscFunctions.compareResult("±Points - row" + (i + 1), expecteds.get(i).getAddingpoints(), actuals.get(i).getAddingpoints());
			}
			result &= MiscFunctions.compareResult("Pending Points - row" + (i + 1), expecteds.get(i).getPendingPoints(), actuals.get(i).getPendingPoints());
			if(!StringUtil.isEmpty(expecteds.get(i).getEarnedPoints())) {
				result &= MiscFunctions.compareResult("Earned Points - row" + (i + 1), expecteds.get(i).getEarnedPoints(), actuals.get(i).getEarnedPoints());
			}
			result &= MiscFunctions.compareResult("Status - row" + (i + 1), expecteds.get(i).getStatus(), actuals.get(i).getStatus());
			result &= MiscFunctions.compareResult("Order Number - row" + (i + 1), expecteds.get(i).getOrderNum(), actuals.get(i).getOrderNum());
			result &= MiscFunctions.compareResult("Location - row" + (i + 1), expecteds.get(i).getLocation(), actuals.get(i).getLocation());
			result &= MiscFunctions.compareResult("User - row" + (i + 1), expecteds.get(i).getUser().replace(", ", ","), actuals.get(i).getUser().replace(", ", ","));
		}
		
		String prefix = actuals.get(0).getAddingpoints().substring(0, 1);
		if(sumAddingPoints) {
			result &= MiscFunctions.compareResult("Sum of Adding Points", expecteds.get(0).getAddingpoints(), prefix + sumOfAddingPoints);
		}
		
		return result;
	}
	
	public void verifyPointInfo(List<PointInfo> expecteds) {
		verifyPointInfo(expecteds, false);
	}
	
	public void verifyPointInfo(List<PointInfo> expecteds, boolean sumAddingPoints) {
		if(!comparePointInfo(expecteds, sumAddingPoints)) throw new ErrorOnPageException("Points Info are NOT correct, please refer above log for details.");
	}
}
