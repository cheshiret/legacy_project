/*
 * Created on Nov 4, 2009
 *
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author vzhao you can access this page from tour details or tickets page by
 *         click on Tour Allocation link
 * 
 */
public class InvMgrTourAllocationPage extends InventoryManagerPage {
	private static InvMgrTourAllocationPage _instance = null;

	public static InvMgrTourAllocationPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrTourAllocationPage();
		}
		return _instance;
	}

	protected InvMgrTourAllocationPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "allocationTypeIndividual");
	}

	public void selectAllocationCategroy(String categroy) {
		browser.selectDropdownList(".id", "allocationCategory", categroy);
	}

	public void selectadvOrAdvAndTicketCat(int index) {
		browser.selectRadioButton(".id", "advOrAdvAndTicketCat", index);
	}

	public void unSelectAllocationTypeIndividual() {
		browser.unSelectCheckBox(".id", "allocationTypeIndividual");
	}
	
	public void unSelectAllocationTypeOrganization() {
		browser.unSelectCheckBox(".id", "allocationTypeOrganization");
	}

	public void selectAllocationTypeIndividual() {
		browser.selectCheckBox(".id", "allocationTypeIndividual");
	}

	public void selectAllocationTypeOrganization() {
		browser.selectCheckBox(".id", "allocationTypeOrganization");
	}

	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickApplyButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	/**
	 * get the error messages
	 * 
	 * @return
	 */
	public List<String> getErrorMessages() {
		List<String> list = null;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".id", new RegularExpression("^V-\\d+", false));
		if (objs.length > 0) {
			logger.info("Get error message ~!");
			list = new ArrayList<String>();
			for (IHtmlObject obj : objs) {
				String msg = obj.getProperty(".text");
				list.add(msg);
			}
		}
		Browser.unregister(objs);
		return list;
	}

	// for test page class
	public static void main(String[] args) {
		InvMgrTourAllocationPage page = InvMgrTourAllocationPage.getInstance();
		List<String> list = page.getErrorMessages();
		for (String msg : list) {
			System.out.println(msg);
		}
	}
}
