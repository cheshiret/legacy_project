package com.activenetwork.qa.awo.pages.orms.licenseManager.docfulfillment;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DocumentFulfillmentInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @Description: Document Fulfillment License Inventory Details widget
 * 
 * @author Lesley Wang
 * @Date  Sep 11, 2013
 */
public class LicMgrDocFulfillInventoryDetailsWidget extends DialogWidget {
	private static LicMgrDocFulfillInventoryDetailsWidget instance = null;

	// The widget doesn't have title
	private LicMgrDocFulfillInventoryDetailsWidget() {
		super();
	}
	
	public static LicMgrDocFulfillInventoryDetailsWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrDocFulfillInventoryDetailsWidget();
		}
		return instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] privInventoryDetailsDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "DocumentFulfillmentPrintUI", 
				".text", new RegularExpression("^(Licence|License) Inventory Details.*", false)); // use .text property to differ from Print widget
	}
	
	protected Property[] privInventoryQtyTypeList() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("DocumentFulfillPrivilegeInventoryDetails-\\d+\\.qtyType", false));
	}
	
	protected Property[] privInventoryStartNumField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("DocumentFulfillPrivilegeInventoryDetails-\\d+\\.startNum", false));
	}
	
	protected Property[] privInventoryAutoFillCheckBox() {
		return Property.toPropertyArray(".class", "Html.INPUT.checkbox", ".id", new RegularExpression("DocumentFulfillPrivilegeInventoryDetails-\\d+\\.autoFill", false));
	}
	
	protected Property[] privInventoryAutoFillNumField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("DocumentFulfillPrivilegeInventoryDetails-\\d+\\.autoFillNum", false));
	}

	protected Property[] privInventoryEndNumField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("DocumentFulfillPrivilegeInventoryDetails-\\d+\\.endNum", false));
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(this.privInventoryDetailsDiv());
	}
	
	public void selectInvQtyType(String type) {
		browser.selectDropdownList(this.privInventoryQtyTypeList(), type);
	}
	
	public void setStartNum(String num) {
		browser.setTextField(this.privInventoryStartNumField(), num, true, 0, IText.Event.LOSEFOCUS);
	}
	
	public void selectAutoFill() {
		browser.selectCheckBox(this.privInventoryAutoFillCheckBox());
	}
	
	public void unSelectAutoFill() {
		browser.unSelectCheckBox(this.privInventoryAutoFillCheckBox(), 0);
	}
	
	public void setAutoFillNum(String text) {
		browser.setTextField(this.privInventoryAutoFillNumField(), text);
	}
	
	public void setEndNum(String num) {
		browser.setTextArea(this.privInventoryEndNumField(), num);
	}
	
	public void setInventoryDetails(DocumentFulfillmentInfo docFulfillInfo) {
		this.selectInvQtyType(docFulfillInfo.getInventoryQtyType());
		this.setStartNum(docFulfillInfo.getInventoryStartNum());
		ajax.waitLoading();
		if (docFulfillInfo.getInventoryQtyType().equalsIgnoreCase("Range")) {
			if (docFulfillInfo.isInventoryAutoFill()) {
				this.selectAutoFill();
				ajax.waitLoading();
				if (docFulfillInfo.getInventoryAutoFillNum() != null) {
					this.setAutoFillNum(docFulfillInfo.getInventoryAutoFillNum());
				}
			} else {
				this.unSelectAutoFill();
				ajax.waitLoading();
				this.setEndNum(docFulfillInfo.getInventoryEndNum());
			}
		}
	}
}
