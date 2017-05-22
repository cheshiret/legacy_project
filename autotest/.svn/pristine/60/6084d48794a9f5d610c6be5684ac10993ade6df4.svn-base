package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrChangeHistoryPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @ScriptName LicMgrViewChangeHistoryPage.java
 * @Date:Apr 6, 2011
 * @Description:
 * @author asun
 */
public class LicMgrProductViewChangeHistoryPage extends LicMgrProductCommonPage implements ILicMgrChangeHistoryPage {

	private static LicMgrProductViewChangeHistoryPage _instance = null;

	private LicMgrProductViewChangeHistoryPage() {
	}

	public static LicMgrProductViewChangeHistoryPage getInstance() {
		if (_instance == null) {
			_instance = new LicMgrProductViewChangeHistoryPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",
				new RegularExpression("ConfigurableChangeList_LIST|ChangeHistoryGrid_LIST",false));
	}

	@Override
	public void clickFirst() {
		browser.clickGuiObject(".class", "Html.A", ".text", "First");
	}

	@Override
	public void clickLast() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Last");
	}

	@Override
	public void clickNext() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Next");
	}

	@Override
	public void clickPrevious() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Previous");
	}

	@Override
	public boolean gotoNext() {
		boolean exists = browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Next");
		if(exists) {
			this.clickNext();
			waitLoading();
		}
		
		return exists;
	}
	
	/**
	 * get product ID
	 * 
	 * @Return String
	 */
	public String getProductID() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".text", new RegularExpression("^Product ID.*", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the product id DIV object");
		}
//		String id = objs[0].getProperty(".text").split("Product ID")[1].trim();
		Property[] p = new Property[1];
		p[0] = new Property(".class","Html.SPAN");
		IHtmlObject[] spanObjs= browser.getHtmlObject(p, objs[0]);
		if(spanObjs.length<1){
			throw new ObjectNotFoundException("Can't find the product ID span object");
		}
		String id = spanObjs[0].text();
		Browser.unregister(objs);
		return id.replaceAll("Product ID", StringUtil.EMPTY).trim();
	}

	/** get product code */
	public String getProductCode() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".text", new RegularExpression("^Code.*", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the product code DIV object");
		}
//		String code = objs[0].getProperty(".text").split("Code")[1].trim();
		Property[] p = new Property[1];
		p[0] = new Property(".class","Html.SPAN");
		IHtmlObject[] spanObjs= browser.getHtmlObject(p, objs[0]);
		if(spanObjs.length<1){
			throw new ObjectNotFoundException("Can't find the product Code span object");
		}
		String code = spanObjs[0].text();
		Browser.unregister(objs);
		return code.replaceAll("Code", StringUtil.EMPTY);
	}

	/** get product name */
	public String getProductName() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".text", new RegularExpression("^Name.*", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the product name DIV object");
		}
//		String name = objs[0].getProperty(".text").split("Name")[1].trim();
		Property[] p = new Property[1];
		p[0] = new Property(".class","Html.SPAN");
		IHtmlObject[] spanObjs= browser.getHtmlObject(p, objs[0]);
		if(spanObjs.length<1){
			throw new ObjectNotFoundException("Can't find the product Name span object");
		}
		String name = spanObjs[0].text();
		Browser.unregister(objs);
		return name.replaceAll("Name", StringUtil.EMPTY);
	}

	public String getProductStatus() {
		String status = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".text", new RegularExpression(
						"^Status.*", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the product status DIV object");
		}
//		status = objs[0].getProperty(".text");
		
		Property[] p = new Property[1];
		p[0] = new Property(".class","Html.SPAN");
		IHtmlObject[] spanObjs= browser.getHtmlObject(p, objs[0]);
		if(spanObjs.length<1){
			throw new ObjectNotFoundException("Can't find the product Status span object");
		}
		status = spanObjs[0].text();
		
//		status = status.substring(status.indexOf(" ")).trim();
		Browser.unregister(objs);
		return status.replaceAll("Status", StringUtil.EMPTY);
	}

	public List<ChangeHistory> getChangeHistoryInfo() {
		List<ChangeHistory> list = new ArrayList<ChangeHistory>();
		IHtmlObject[] objs = null;
		IHtmlTable table = null;
		do{
			objs = browser.getTableTestObject(".id", new RegularExpression( "ChangeHistoryGrid_LIST|ConfigurableChangeList_LIST", false));
			if (objs.length < 1) {
				throw new ObjectNotFoundException("Can't find the history table object with id='" + "ChangeHistoryGrid'");
			}
			
			table = (IHtmlTable) objs[0];
			for (int i = 1; i < table.rowCount(); i++) {
				ChangeHistory history = new ChangeHistory();
				history.changeDate = table.getCellValue(i, 0);
				history.object = table.getCellValue(i, 1);
				history.action = table.getCellValue(i, 2);
				history.field = table.getCellValue(i, 3);
				history.oldValue = table.getCellValue(i, 4);
				history.newValue = table.getCellValue(i, 5);
				history.user = table.getCellValue(i, 6);
				history.location = table.getCellValue(i, 7);
				list.add(history);
			}
		}while(this.gotoNext());
		
		Browser.unregister(objs);
		return list;
	}

	public List<String> getColumnValue(String colName) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"ConfigurableChangeList_LIST");
		IHtmlTable table = (IHtmlTable) objs[0];

		int col = table.findColumn(0, colName);
		List<String> value = table.getColumnValues(col);

		Browser.unregister(objs);
		return value;
	}

	public List<String> getRowInfo(String value, String colName) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"ConfigurableChangeList_LIST");
		IHtmlTable table = (IHtmlTable) objs[0];

		int colNum = table.findColumn(0, colName);
		int row = table.findRow(colNum, value);
		List<String> values = table.getRowValues(row);

		Browser.unregister(objs);
		return values;
	}

	public List<String> getLastRow() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"ConfigurableChangeList_LIST");
		IHtmlTable table = (IHtmlTable) objs[0];

		List<String> values = table.getRowValues(table.rowCount() - 1);

		Browser.unregister(objs);
		return values;
	}

	public boolean comparePrivilegeChangeHistoryInfo(PrivilegeInfo privilege) {
		boolean result = true;
		List<ChangeHistory> histories = this.getChangeHistoryInfo();
		for(int i = 0; i < histories.size(); i ++) {
			if(DateFunctions.compareDates(DateFunctions.formatDate(histories.get(i).changeDate, "E MMM dd yyyy"), privilege.updateDate) !=0 ) {
				logger.error("The expected Change Date is " + privilege.updateDate + ", but actual value is " + histories.get(i).changeDate);
				result &= false;
			}
			if(!histories.get(i).object.equals("Privilege Product")) {
				logger.error("The expected Object is Privilege Product, but actual value is " + histories.get(i).object);
				result &= false;
			}
			if(histories.get(i).action.equals("Add")) {
				if(histories.get(i).field.trim().length() > 0) {
					logger.error("The expected Field is null, but actual value is " + histories.get(i).field.trim());
					result &= false;
				}
				if(histories.get(i).oldValue.trim().length() > 0) {
					logger.error("The expected Old Value is null, but actual value is " + histories.get(i).oldValue.trim());
					result &= false;
				}
				if(histories.get(i).newValue.trim().length() > 0) {
					logger.error("The expected New Value is null, but actual value is " + histories.get(i).newValue.trim());
					result &= false;
				}
			} else if(histories.get(i).action.equals("Update")) {
				//including all privilege product attributes
				if(histories.get(i).field.equals("Display Order")) {
					if(Integer.parseInt(histories.get(i).newValue.trim()) != Integer.parseInt(privilege.displayOrder)) {
						logger.error("The expected New Value is " + privilege.displayOrder + ", but actual value is " + histories.get(i).newValue.trim());
						result &= false;
					}
				}
				if(histories.get(i).field.equals("Authorization Quantity")) {
					if(!histories.get(i).newValue.equals(privilege.authorizationQuantity)) {
						logger.error("The expected New Value is " + privilege.authorizationQuantity + ", but actual value is " + histories.get(i).newValue);
						result &= false;
					}
				}
				if(histories.get(i).field.equals("Valid Date Printing")) {
					for(String printing : privilege.validDatePrinting) {
						if(!histories.get(i).newValue.contains(printing)) {
							logger.error("The expected New Value is " + printing + ", but actual value is " + histories.get(i).newValue);
							result &= false;
						}
					}
				}
				if(histories.get(i).field.equals("Display Category")) {
					if(!histories.get(i).newValue.equals(privilege.displayCategory)) {
						logger.error("The expected New Value is " + privilege.displayCategory + ", but actual value is " + histories.get(i).newValue);
						result &= false;
					}
				}
				if(histories.get(i).field.equals("Display Sub-Category")) {
					if(!histories.get(i).newValue.equals(privilege.displaySubCategory)) {
						logger.error("The expected New Value is " + privilege.displaySubCategory + ", but actual value is " + histories.get(i).newValue);
						result &= false;
					}
				}
				if(histories.get(i).field.equals("Name")) {
					if(!histories.get(i).newValue.equals(privilege.name)) {
						logger.error("The expected New Value is " + privilege.name + ", but actual value is " + histories.get(i).newValue);
						result &= false;
					}
				}
				if(histories.get(i).field.equals("Inventory Type")) {
					if(!histories.get(i).newValue.equals(privilege.invType)) {
						logger.error("The expected New Value is " + privilege.invType + ", but actual value is " + histories.get(i).newValue);
						result &= false;
					}
				}
				if(histories.get(i).field.equals("Valid From Date Calculation")) {
					if(!histories.get(i).newValue.equals(privilege.validFromDateCalculation)) {
						logger.error("The expected New Value is " + privilege.validFromDateCalculation + ", but actual value is " + histories.get(i).newValue);
						result &= false;
					}
				}
				if(histories.get(i).field.equals("Prompt Indicator")) {
					if(!histories.get(i).newValue.equals(privilege.promptIndicator)) {
						logger.error("The expected New Value is " + privilege.promptIndicator + ", but actual value is " + histories.get(i).newValue);
						result &= false;
					}
				}
				if(histories.get(i).field.equals("Valid To Date Calculation")) {
					if(!histories.get(i).newValue.equals(privilege.validToDateCalculation)) {
						logger.error("The expected New Value is " + privilege.validToDateCalculation + ", but actual value is " + histories.get(i).newValue);
						result &= false;
					}
				}
				if(histories.get(i).field.equals("Renewal Days")) {
					if(Integer.parseInt(histories.get(i).newValue.trim()) != Integer.parseInt(privilege.renewalDays)) {
						logger.error("The expected New Value is " + privilege.renewalDays + ", but actual value is " + histories.get(i).newValue);
						result &= false;
					}
				}
				if(histories.get(i).field.equals("Valid Days/Years")) {
					if(Integer.parseInt(histories.get(i).newValue.trim()) != Integer.parseInt(privilege.validDaysYears)) {
						logger.error("The expected New Value is " + privilege.validDaysYears + ", but actual value is " + histories.get(i).newValue);
						result &= false;
					}
				}
				if(histories.get(i).field.equals("Date Unit")) {
					if(!histories.get(i).newValue.equals(privilege.dateUnitOfValidToDate)) {
						logger.error("The expected New Value is " + privilege.dateUnitOfValidToDate + ", but actual value is " + histories.get(i).newValue);
						result &= false;
					}
				}
				if(histories.get(i).field.equals("Customer Class")) {
					for(String clazz : privilege.customerClasses) {
						if(!histories.get(i).newValue.contains(clazz)) {
							logger.error("The expected New Value is " + clazz + ", but actual value is " + histories.get(i).newValue);
							result &= false;
						}
					}
				}
				if(histories.get(i).field.equals("Report Category")) {
					if(!histories.get(i).newValue.equals(privilege.reportCategory)) {
						logger.error("The expected New Value is " + privilege.reportCategory + ", but actual value is " + histories.get(i).newValue);
						result &= false;
					}
				}
				//TODO need to added more attributes
			} else {
				logger.error("Unknown Action - " + histories.get(i).action);
				result &= false;
			}
			
			if(!histories.get(i).user.replace(" ", "").equals(privilege.createUser.replace(" ", ""))) {
				logger.error("The expected User is " + privilege.createUser + ", but actual value is " + histories.get(i).user);
				result &= false;
			}
			if(!histories.get(i).location.equals(privilege.createLocation)) {
				logger.error("The expected Location is " + privilege.createLocation + ", but actual value is " + histories.get(i).location);
				result &= false;
			}
		}
		
		return result;
	}
	
	public void compareRecordInfo(PrivilegeInfo privilege, boolean isLast) {
		List<String> records = null;
		if (isLast) {
			records = this.getLastRow();
			if (!"Add".equals(records.get(2).trim())) {
				throw new ErrorOnPageException("Action is not equal to 'Add'.");
			}
			if (!"".equals(privilege.createDate)
					&& DateFunctions.compareDates(privilege.createDate, records
							.get(0).substring(0, 15).trim()) != 0) {
				throw new ErrorOnPageException("Date is not equal to "
						+ privilege.createDate);
			}

		} else {
			records = this.getRowInfo(privilege.name, "New Value");
			if (DateFunctions.compareDates(privilege.updateDate, records.get(0)
					.substring(0, 15).trim()) != 0) {
				throw new ErrorOnPageException("Date is not equal to "
						+ privilege.updateDate);
			}
			if (!"Update".equals(records.get(2).trim())) {
				throw new ErrorOnPageException(
						"Action is not equal to 'Update'.");
			}
			if (!privilege.changeField.equals(records.get(3).trim())) {
				throw new ErrorOnPageException("Change field is not equal to "
						+ privilege.changeField);
			}
			if (!privilege.oldValue.equals(records.get(4).trim())) {
				throw new ErrorOnPageException("Old value is not equal to "
						+ privilege.oldValue);
			}
			if (!privilege.changeValue.equals(records.get(5).trim())) {
				throw new ErrorOnPageException("New value is not equal to "
						+ privilege.changeValue);
			}
		}

		if (!"Privilege Product".equals(records.get(1).trim())) {
			throw new ErrorOnPageException(
					"Log Object is not equal to 'Privilege Product'.");
		}
		if (!privilege.createUser.equals(records.get(6).trim())) {
			throw new ErrorOnPageException("User is not equal to "
					+ privilege.createUser);
		}
		if (!privilege.createLocation.equals(records.get(7).trim())) {
			throw new ErrorOnPageException("Location is not equal to "
					+ privilege.createLocation);
		}
	}
	
	public void clickReturnToProductDetailButton(){
		browser.clickGuiObject(".class","Html.A", ".text", "Return to Product Details");
	}

	
}
