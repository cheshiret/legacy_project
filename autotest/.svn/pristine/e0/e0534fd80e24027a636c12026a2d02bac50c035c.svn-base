/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.testapi.interfaces.page.Loadable;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  Aug 19, 2011
 */
public interface ILicMgrProductPrintDocumentPage extends Loadable {
	
	public RegularExpression statusDDList=new RegularExpression("PrdFulfillmentDocListUISearchCriteria-\\d+\\.status",false);
	
	public RegularExpression docTemplateDDList=new RegularExpression("PrdFulfillmentDocListUISearchCriteria-\\d+\\.template", false);
	
	public RegularExpression equipTypeDDList= new RegularExpression("PrdFulfillmentDocListUISearchCriteria-\\d+\\.equipmentType", false);
	 
	public  void clickAddPrintDocument();

	public  void checkShowCurrentRecordsOnly();

	public  void uncheckShowCurrentRecordsOnly();

	public  void selectStatus(String status);

	public  void selectDocumentTemplate(String template);

	public  void selectEquipmentType(String type);

	public  void clickGo();

	public  void clickPrintDocumentID(String id);

	/**
	 * Get document assignment info(document assignment ID, equipment type, purchase type)
	 * @param document
	 * @return
	 *       document assignment ID, equipment type, purchase type
	 */
	public  List<String[]> getDocumentAssignmentInfo(LicMgrDocumentInfo document);

	/**
	 * Get document assignment ID
	 * @param document
	 * @return
	 */
	public  String getPrintDocumentAssignmentID(LicMgrDocumentInfo document);
	
	
	public  List<String> getDocumentColumnNames();
	
	public  List<String> getColumnValues(String columnName);
	
    public  List<String> getStatusDDListValues();
    
    public  List<String> getDocTemplatesDDLIstValues();
    
    public  List<String> getEquipmentTypeDDListValues();
    
    public  boolean  isShowCurrentlyOnlySelected();
    
    public  void clickPrintDocumentsTab();
    
    public  void searchPrintDocument(boolean currentOnly,String status,String template,String equipType);
}
