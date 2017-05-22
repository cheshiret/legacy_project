package com.activenetwork.qa.awo.pages.orms.common.customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram.LoyaltyProgramSchedule;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
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
 * @Date  Apr 24, 2014
 */
public class OrmsCustomerLoyaltyProgramRedeemPointsWidget extends DialogWidget {
	private static OrmsCustomerLoyaltyProgramRedeemPointsWidget _instance = null;
	
	private OrmsCustomerLoyaltyProgramRedeemPointsWidget() {
		super("Redeem Points");
	}
	
	public static OrmsCustomerLoyaltyProgramRedeemPointsWidget getInstance() {
		if(_instance == null) _instance = new OrmsCustomerLoyaltyProgramRedeemPointsWidget();
		return _instance;
	}
	
	private static final String PRODUCT_NAME_COL = "Product Name";
	private static final String DATE_COL = "Date";
	private static final String STAY_DATES_COL = "Stay Dates";
	private static final String BASE_PRICE_COL = "Base Price";
	private static final String APPLICABLE_DISCOUNT_COL = "Applicable Discount";
	private static final String REDEEM_POINTS_COL = "Redeem Points";
	private static final String POINTS_PER_UNIT_COL = "Points Per Unit";
	private static final String REDEEM_AMOUNT_COL = "Redeem Amount";
	private static final String APPLIED_REDEEM_AMOUNT_COL = "Applied Redeem Amount";
	private static final String APPLIED_REDEEM_POINTS_COL = "Applied Redeem Points";
	
	private Property[] loyaltyCard() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureRedeemData-\\d+\\.passInfo", false));
	}
	
	private Property[] earnedPoints() {
		return Property.toPropertyArray(".id", "earnedpointsLable");
	}
	
	private Property[] orderNum() {
		return Property.toPropertyArray(".id", "LoyaltyFeatureRedeemData.ordNumber");
	}
	
	private Property[] area() {
		return Property.toPropertyArray(".id", "LoyaltyFeatureRedeemData.areaName");
	}
	
	private Property[] siteNumName() {
		return Property.toPropertyArray(".id", "LoyaltyFeatureRedeemData.siteName");
	}
	
	private Property[] redeemMethod() {
		return Property.concatPropertyArray(span(), ".className", "inputwithrubylabel", ".text", new RegularExpression("Redeem Method", false));
	}
	
	private Property[] productCheckbox() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureRedeemUnitData-\\d+\\.selected", false));
	}
	
	private Property[] redeemPointsListTable() {
		return Property.toPropertyArray(".id", "redeemPointsList");
	}
	
	private Property[] pointsBalance() {
		return Property.toPropertyArray(".id", "LoyaltyFeatureRedeemData.balancePoints");
	}
	
	private Property[] errorMsg() {
		return Property.concatPropertyArray(div(), ".className", "message msgerror");
	}
	
	public void selectLoyaltyCard(String programName, String cardNum) {
		browser.selectDropdownList(loyaltyCard(), cardNum + " - " + programName);
	}
	
	public String getLoyaltyCard() {
		return browser.getDropdownListValue(loyaltyCard(), 0);
	}
	
	private String getAttributeValueByName(Property properties[], String name) {
		String text = browser.getObjectText(properties);
		String value = text.replace(name, StringUtil.EMPTY);
		
		return value;
	}
	
	public int getEarnedPoints() {
		return Integer.parseInt(getAttributeValueByName(earnedPoints(), "Earned Points"));
	}
	
	public String getOrderNum() {
		return getAttributeValueByName(orderNum(), "Order #");
	}
	
	public String getArea() {
		return getAttributeValueByName(area(), "Area");
	}
	
	public String getSiteNumName() {
		return getAttributeValueByName(siteNumName(), "Site#(Name)");
	}
	
	public String getRedeemMethod() {
		return getAttributeValueByName(redeemMethod(), "Redeem Method");
	}
	
	public void selectProduct(String name) {
//		browser.selectCheckBox(productCheckbox());
		//TODO to enhance this implementation, specific select
		IHtmlObject objs[] = browser.getCheckBox(productCheckbox());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find any to-redeem checkbox object.");
		for(int i = 0; i < objs.length; i ++) {
			((ICheckBox)objs[i]).select();
			ajax.waitLoading();
			
			if(i < objs.length - 1) {
				objs = browser.getCheckBox(productCheckbox());
			}
		}
		
		Browser.unregister(objs);
	}
	
	public void unselectProduct(String name) {
		//TODO to enhance this implementation, specific select
		IHtmlObject objs[] = browser.getCheckBox(productCheckbox());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find any to-redeem checkbox object.");
		for(int i = 0; i < objs.length; i ++) {
			if(((ICheckBox)objs[i]).isSelected()) {
				((ICheckBox)objs[i]).deselect();
				ajax.waitLoading();
			}
			if(i < objs.length - 1) {
				objs = browser.getCheckBox(productCheckbox());
			}
		}
		
		Browser.unregister(objs);
	}
	
	public int getPointsBalance() {
		return Integer.parseInt(getAttributeValueByName(pointsBalance(), "Points Balance"));
	}
	
	public boolean isErrorMsgExists() {
		return browser.checkHtmlObjectExists(errorMsg(), getWidget()[0]);
	}
	
	public String getErrorMsg() {
		return browser.getObjectText(errorMsg());
	}
	
	public void clickRedeem() {
		clickButtonByText("Redeem");
	}
	
	private IHtmlObject[] getRedeemPointsListTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(redeemPointsListTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Redeem Poins list table object.");
		
		return objs;
	}
	
	private List<List<String>> getRedeemPointsRecords() {
		IHtmlObject objs[] = this.getRedeemPointsListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		List<List<String>> records = new ArrayList<List<String>>();
		for(int i = 1; i < table.rowCount(); i ++) {
			records.add(table.getRowValues(i));
		}
		
		Browser.unregister(objs);
		return records;
	}
	
	private List<String> getProductNameColumnValues() {
		IHtmlObject objs[] = this.getRedeemPointsListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int colIndex = table.findColumn(0, PRODUCT_NAME_COL);
		List<String> values = table.getColumnValues(colIndex);
		values.remove(0);
		
		Browser.unregister(objs);
		return values;
	}
	
	private List<String> getDateColumnValues() {
		IHtmlObject objs[] = this.getRedeemPointsListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int colIndex = table.findColumn(0, DATE_COL);
		if(colIndex < 1) {
			colIndex = table.findColumn(0, STAY_DATES_COL);
		}
		List<String> values = table.getColumnValues(colIndex);
		values.remove(0);
		
		Browser.unregister(objs);
		return values;
	}
	
	private List<String> getApplicableDiscountColumnValues() {
		IHtmlObject objs[] = this.getRedeemPointsListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int colIndex = table.findColumn(0, APPLICABLE_DISCOUNT_COL);
		List<String> values = table.getColumnValues(colIndex);
		values.remove(0);
		
		Browser.unregister(objs);
		return values;
	}
	
	private List<String> getRedeemPointsColumnValues() {
		IHtmlObject objs[] = this.getRedeemPointsListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int colIndex = table.findColumn(0, REDEEM_POINTS_COL);
		if(colIndex < 0) {
			colIndex = table.findColumn(0, POINTS_PER_UNIT_COL);
		}
		List<String> values = table.getColumnValues(colIndex);
		values.remove(0);
		
		Browser.unregister(objs);
		return values;
	}
	
	private List<String> getBasePriceColumnValues() {
		IHtmlObject objs[] = this.getRedeemPointsListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int colIndex = table.findColumn(0, BASE_PRICE_COL);
		List<String> values = table.getColumnValues(colIndex);
		values.remove(0);
		
		Browser.unregister(objs);
		return values;
	}
	
	public boolean compareRedeemPointsInfo(LoyaltyProgram program, LoyaltyProgramSchedule redeemSchdl, Object product, String ordNum) {
		boolean result = true;
		//Loyalty Card section
		result &= MiscFunctions.compareResult("Loyalty Card #", program.getCardNumber() + " - " + program.getName(), this.getLoyaltyCard());
		result &= MiscFunctions.compareResult("Earned Points", program.getEarnedPoints(), this.getEarnedPoints());
		
		//Order section
		result &= MiscFunctions.compareResult("Order #", ordNum, this.getOrderNum());
		
		SiteInfoData site = null;
		POSInfo pos = null;
		if(product instanceof SiteInfoData) {
			site = (SiteInfoData)product;
			result &= MiscFunctions.compareResult("Site Area", site.area, this.getArea());
			result &= MiscFunctions.compareResult("Site #(Name)", site.siteNumber + "-" + site.siteName, this.getSiteNumName());
		}
		
		//Redeem Points section
		String redeemMethod = "";
		if(redeemSchdl.getRedeemMethod().equalsIgnoreCase("$ Value")) {
			redeemMethod = redeemSchdl.getRedeemMethod() + "(1$=" + redeemSchdl.getPoints() + " Points)";
		} else {
			redeemMethod = redeemSchdl.getRedeemMethod();
		}
		result &= MiscFunctions.compareResult("Redeem Method", redeemMethod, this.getRedeemMethod());
		
		List<String> dateValues = product instanceof SiteInfoData ? this.getDateColumnValues() : null;
		List<String> applicableDiscountValues = this.getApplicableDiscountColumnValues();
		List<String> redeemPointsValues = this.getRedeemPointsColumnValues();
		List<String> basePriceValues = new ArrayList<String>();
		List<String> expectedRedeemPoints = new ArrayList<String>();
		
		String applicableDiscount = "";
		if(redeemSchdl.getRedeemMethod().contains("Percentage")) {//includes 'Unit/Percentage' and 'Percentage'
			applicableDiscount = redeemSchdl.getPercentageValue() + "%";
		} else if(redeemSchdl.getRedeemMethod().contains("Flat")) {//'Unit/Flat' and 'Flat'
			applicableDiscount =  "$" + new BigDecimal(redeemSchdl.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else if(redeemSchdl.getRedeemMethod().equalsIgnoreCase("$ Value")) {
			basePriceValues = this.getBasePriceColumnValues();
		}
		
		String redeemPoints = "";
		if(redeemSchdl.getRedeemMethod().equalsIgnoreCase("Percentage") || redeemSchdl.getRedeemMethod().equalsIgnoreCase("Flat")) {
			redeemPoints = redeemSchdl.getEquivalentPoints();
		} else if(redeemSchdl.getRedeemMethod().equalsIgnoreCase("$ Value")) {
			double basePrice = 0;
			for(int i = 0; i < basePriceValues.size(); i ++) {
				basePrice = Double.parseDouble(basePriceValues.get(i).substring(1));
				int expectedRedeemPoint = new BigDecimal((basePrice / 1) * Integer.parseInt(redeemSchdl.getPoints())).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				expectedRedeemPoints.add(String.valueOf(expectedRedeemPoint));
			}
		} else if(redeemSchdl.getRedeemMethod().equalsIgnoreCase("Unit/Flat") || redeemSchdl.getRedeemMethod().equalsIgnoreCase("Unit/Percentage")) {
			redeemPoints = redeemSchdl.getAnyDayPoint();
		}
		
		for(int i = 0; i < applicableDiscountValues.size(); i ++) {
			if(product instanceof SiteInfoData) {
				if(redeemSchdl.getRedeemMethod().equalsIgnoreCase("Percentage") || redeemSchdl.getRedeemMethod().equalsIgnoreCase("Flat")) {
					String dates[] = dateValues.get(i).split("-");
					result &= MiscFunctions.compareResult("Row - " + (i + 1) + " Stay Dates - Start", site.arrivalDate, dates[0].trim());
					result &= MiscFunctions.compareResult("Row - " + (i + 1) + " Stay Dates - End", DateFunctions.getDateAfterGivenDay(site.departDate, -1), dates[1].trim());
				} else {
					result &= MiscFunctions.compareResult("Row - " + (i + 1) + " Date", DateFunctions.getDateAfterGivenDay(site.arrivalDate, i), dateValues.get(i));
				}
			} else if(product instanceof POSInfo) {
				pos = (POSInfo)product;
				List<String> productNameValues = this.getProductNameColumnValues();
				result &= MiscFunctions.compareResult("Row - " + (i + 1) + " Product Name", pos.product, productNameValues.get(i));
			}
			
			if(redeemSchdl.getRedeemMethod().equalsIgnoreCase("$ Value")) {
				result &= MiscFunctions.compareResult("Row - " + (i + 1) + " Applicable Discount", basePriceValues.get(i), applicableDiscountValues.get(i));
				result &= MiscFunctions.compareResult("Row - " + (i + 1) + " Redeem Points", expectedRedeemPoints.get(i), redeemPointsValues.get(i));
			} else {
				result &= MiscFunctions.compareResult("Row - " + (i + 1) + " Applicable Discount", applicableDiscount, applicableDiscountValues.get(i));
				result &= MiscFunctions.compareResult("Row - " + (i + 1) + " Redeem Points", redeemPoints, redeemPointsValues.get(i));
			}
		}
		
		return result;
	}
	
	public void verifyRedeemPointsInfo(LoyaltyProgram program, LoyaltyProgramSchedule redeemSchdl, Object product, String ordNum) {
		if(!compareRedeemPointsInfo(program, redeemSchdl, product, ordNum))
			throw new ErrorOnPageException("Redeem Points info is NOT correct.");
	}
}
