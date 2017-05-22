/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author ssong
 * @Date  Dec 15, 2011
 */
public class LicMgrVendorChangeStoreBondAssignmentWidget extends DialogWidget {
	private static LicMgrVendorChangeStoreBondAssignmentWidget _instance=null;
	
	protected LicMgrVendorChangeStoreBondAssignmentWidget() {
		super("Change Agent/Bond Assignments");
	}
	
	public static LicMgrVendorChangeStoreBondAssignmentWidget getInstance() {
		if(null==_instance) {
			_instance=new LicMgrVendorChangeStoreBondAssignmentWidget();
		}
		return _instance;
	}
	
	public void selectBond(String option,int index){
		Property[] pro = new Property[1];
		pro[0] = new Property(".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue",false));
		IHtmlObject[] objs = browser.getTableTestObject(".id","bondStoreassignmentgrid");
		browser.selectDropdownList(pro,option,true,index,objs[0]);
		Browser.unregister(objs);
	}
	
	/**
	 * The method used to select given bond for given agent
	 * @param agentId
	 * @param bondInfo
	 */
	public void assignBondToAgent(String agentId,String bondNum,String bondIssuer){
		IHtmlObject[] objs = browser.getTableTestObject(".id","bondStoreassignmentgrid");
		IHtmlTable grid = (IHtmlTable)objs[0];
		
		int rowNum = grid.findRow(0, agentId);
		this.selectBond("Bond #: "+bondNum+" Issuer: "+bondIssuer, rowNum-1);
		Browser.unregister(objs);
	}
	
	/**
	 * The method used to select given bond for given agent
	 * search agent by name
	 * @param agentId
	 * @param bondInfo
	 */
	public void assignBondToAgentByName(String agentName,String bondNum,String bondIssuer){
		IHtmlObject[] objs = browser.getTableTestObject(".id","bondStoreassignmentgrid");
		IHtmlTable grid = (IHtmlTable)objs[0];
		List<String> columnList = grid.getColumnValues(1);
		int rowNum = -1;
		for(int i=0;i<columnList.size();i++){
			if(columnList.get(i).contains(agentName)){
				rowNum = i;
				break;
			}
		}
		
		if(rowNum < 0){
			throw new ItemNotFoundException("Can't find given agent with name:"+agentName);
		}
		this.selectBond("Bond #: "+bondNum+" Issuer: "+bondIssuer, rowNum-1);
		Browser.unregister(objs);
	}
	
}
