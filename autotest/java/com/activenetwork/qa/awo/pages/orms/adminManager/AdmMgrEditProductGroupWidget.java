package com.activenetwork.qa.awo.pages.orms.adminManager;

import com.activenetwork.qa.awo.datacollection.legacy.ProductGroup;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: It is the Edit Product Group page. The widget will be shown after click one product group id in Admin Manager
 * 
 * @author Lesley Wang
 * @Date  July 15, 2013
 */
public class AdmMgrEditProductGroupWidget extends DialogWidget {
	private static AdmMgrEditProductGroupWidget instance=null;
	
	private AdmMgrEditProductGroupWidget() {
		super("Edit Product Group");
	}
	
	public static AdmMgrEditProductGroupWidget getInstance(){
		if(instance==null){
			instance=new AdmMgrEditProductGroupWidget();
		}
		return instance;
	}
	
	protected Property[] getPrdGrpDialogProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "Dialog001");
	}
	
	protected Property[] getPrdGrpCatListProp() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", new RegularExpression("ProductGroupView-\\d+\\.prdGrpCatID", false));
	}
	
	protected Property[] getPrdGrpCodeFieldProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", "ProductGroupView.productGroupCode");
	}
	
	protected Property[] getPrdGrpNameFieldProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", "ProductGroupView.productGroupName");
	}
	
	protected Property[] getPrdGrpDescFieldProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", "ProductGroupView.productGroupDesc");
	}
	
	protected Property[] getPrdGrpIconListProp() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", new RegularExpression("ProductGroupView-\\d+\\.iconLabel", false));
	}
	
	protected Property[] getPrdGrpActiveIndListProp() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue", false));
	}
	
	public void selectPrdGrpCategory(String item) {
		browser.selectDropdownList(this.getPrdGrpCatListProp(), item, true);
	}
	
	public void setPrdGrpCode(String value) {
		browser.setTextField(this.getPrdGrpCodeFieldProp(), value, true, 0);
	}
	
	public void setPrdGrpName(String value) {
		browser.setTextField(this.getPrdGrpNameFieldProp(), value, true, 0);
	}
	
	public void setPrdGrpDescription(String value) {
		browser.setTextField(this.getPrdGrpDescFieldProp(), value, true, 0);
	}
	
	public void selectPrdGrpIcon(int index) {
		browser.selectDropdownList(this.getPrdGrpIconListProp(), index, true);
	}
	
	public void selectPrdGrpIcon(String item) {
		browser.selectDropdownList(this.getPrdGrpIconListProp(), item, true);
	}
	
	private IHtmlObject[] getPrdGrpDialog() {
		IHtmlObject[] objs = browser.getHtmlObject(this.getPrdGrpDialogProp());
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find product group dialog");
		}
		return objs;
	}
	
	public void selectPrdGrpActiveInd(boolean isActive) {
		String item = isActive ? "Active" : "Inactive";
		IHtmlObject[] objs = this.getPrdGrpDialog();
		browser.selectDropdownList(this.getPrdGrpActiveIndListProp(), item, true, objs[0]);
		Browser.unregister(objs);
	}
	
	public void setPrdocutGroupDetails(ProductGroup prdGrp) {
		if (prdGrp.getPrdGrpCatgory() != null) {
			this.selectPrdGrpCategory(prdGrp.getPrdGrpCatgory());
			ajax.waitLoading();
		}
		if (prdGrp.getPrdGrpCode() != null)
			this.setPrdGrpCode(prdGrp.getPrdGrpCode());
		if (prdGrp.getPrdGrpName() != null)
			this.setPrdGrpName(prdGrp.getPrdGrpName());
		if (prdGrp.getPrdGrpDescription() != null)
			this.setPrdGrpDescription(prdGrp.getPrdGrpDescription());
		if (StringUtil.isEmpty(prdGrp.getPrdGrpIcon())) {
			this.selectPrdGrpIcon(0);
			ajax.waitLoading();
		} else {
			this.selectPrdGrpIcon(prdGrp.getPrdGrpIcon());
			ajax.waitLoading();
		}
		this.selectPrdGrpActiveInd(prdGrp.isPrdGrpActive());
		ajax.waitLoading();
	}
}
