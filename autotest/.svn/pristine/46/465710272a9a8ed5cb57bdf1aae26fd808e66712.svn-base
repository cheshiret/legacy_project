package com.activenetwork.qa.awo.pages.orms.common.customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Apr 16, 2014
 */
public class OrmsCustomerLoyaltyProgramSelectWidget extends DialogWidget {
	
	private static OrmsCustomerLoyaltyProgramSelectWidget _instance = null;
	
	private OrmsCustomerLoyaltyProgramSelectWidget() {
		super("Please select the programs in which you want to enroll.");
	}
	
	public static OrmsCustomerLoyaltyProgramSelectWidget getInstance() {
		if(_instance == null) {
			_instance = new OrmsCustomerLoyaltyProgramSelectWidget();
		}
		
		return _instance;
	}
	
	private Property[] loyaltyProgramsTable() {
		return Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^Program", false));
	}
	
	private Property[] loyaltyProgramCheckbox() {
		return Property.toPropertyArray(".value", "true");
	}
	
	private Property[] cardNumber() {
		return Property.toPropertyArray(".id", new RegularExpression("CustomerMembershipProgramEnrollment-\\d+\\.cardNumber", false));
	}
	
	private Property[] errorMsg() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	private IHtmlObject[] getLoyaltyProgramTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(loyaltyProgramsTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find loyalty program table object.");
		
		return objs;
	}
	
	private int getLoyaltyProgramRowIndex(String name) {
		IHtmlObject objs[] = this.getLoyaltyProgramTableObject();
		IHtmlTable table = (IHtmlTable)objs[1];
		
		int rowIndex = table.findRow(1, name);
		
		Browser.unregister(objs);
		return rowIndex;
	}
	
	public boolean isCheckboxExists() {
		return browser.checkHtmlObjectExists(loyaltyProgramCheckbox(), this.getWidget()[0]);
	}
	
	public void selectLoyaltyProgram(String name) {
		int index = this.getLoyaltyProgramRowIndex(name);
		if(index == -1) throw new ErrorOnPageException("Cannot find program - " + name);
		browser.selectCheckBox(loyaltyProgramCheckbox(), index, this.getWidget()[0]);
	}
	
	public boolean isCardNumberExists() {
		return browser.checkHtmlObjectExists(cardNumber());
	}
	
	public void setCardNumber(String name, String num) {
		IHtmlObject trObjs[] = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("(Program\\s+)?" + name, false));
		if(trObjs.length < 1) throw new ErrorOnPageException("Cannot find Loyalty Program - " + name);
		
		browser.setTextField(cardNumber(), num, true, 0, trObjs[trObjs.length - 1]);
		Browser.unregister(trObjs);
	}
	
	public void setCardNumber(String num) {
		browser.setTextField(cardNumber(), num);
	}
	
	public List<String> getAllLoyaltyProgramNames() {
		IHtmlObject objs[] = this.getLoyaltyProgramTableObject();
		IHtmlTable table = (IHtmlTable)objs[1];
		
		List<String> names = table.getColumnValues(1);
		Browser.unregister(objs);
		
		return names;
	}
	
	public IHtmlObject[] getAllCheckboxes() {
		IHtmlObject objs[] = browser.getCheckBox(loyaltyProgramCheckbox(), this.getWidget()[0]);
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find any Loyalty Program checkbox.");
		
		return objs;
	}
	
	public void verifyCheckboxesCount() {
		List<String> names = this.getAllLoyaltyProgramNames();
		IHtmlObject objs[] = this.getAllCheckboxes();
		
		if(!MiscFunctions.compareResult("Loyalty program names count compares with checkboxes count", names.size(), objs.length)) {
			throw new ErrorOnPageException("Not each loyalty program has a checkbox displayed in front of it.");
		}
		Browser.unregister(objs);
	}
	
	public void verifyAllCheckboxesUnselected() {
		IHtmlObject objs[] = this.getAllCheckboxes();
		
		boolean result = true;
		for(int i = 0; i < objs.length; i ++) {
			result &= !((ICheckBox)objs[i]).isSelected();
			if(!result) break;
		}
		
		if(!result) throw new ErrorOnPageException("Not all checkboxes are default displayed unselected.");
		logger.info("All checkboxes are default displayed unselected.");
	}
	
	public void verifyLoyaltyProgramNamesSorting() {
		List<String> names = this.getAllLoyaltyProgramNames();
		List<String> toSortNames = new ArrayList<String>();
		
		toSortNames.addAll(names);
		Collections.sort(toSortNames);
		
		if(!names.equals(toSortNames)) throw new ErrorOnPageException("Loyalty Program names are NOT sorted in alphabetical order.");
		logger.info("Loyalty Program names are sorted in alphabetical order.");
	}
	
	public boolean isErrorMsgExists() {
		return browser.checkHtmlObjectExists(errorMsg());
	}
	
	public String getErrorMsg() {
		return browser.getObjectText(errorMsg());
	}
	
	public void verifyErrorMsg(String expected) {
		String actual = this.getErrorMsg();
		if(!MiscFunctions.compareResult("Error message displayed on the top of Loyalty Program select widget", expected, actual))
			throw new ErrorOnPageException("Error message displayed on the top of Loyalty Program select widget is NOT correct.");
	}
}
