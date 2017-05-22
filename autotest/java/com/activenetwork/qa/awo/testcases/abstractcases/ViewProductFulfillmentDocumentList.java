/**
 * 
 */
package com.activenetwork.qa.awo.testcases.abstractcases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPrintDocumentPage;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:View Product Fulfillment Document List.DOC
 * @Task#:AUTO-486
 * @Defects:DEFECT-29688,DEFECT-30590
 * 
 * @author asun
 * @Date  Aug 19, 2011
 */
public abstract class ViewProductFulfillmentDocumentList extends LicenseManagerTestCase {

	protected final static int INITIAL_FILTERS=0; 
	protected final static int BUSSINESSRULE_UIREQUIREMENT=1;
	protected int action;
	protected String prodCode;
	protected String prodType;
	
	
	private String parkName;
	protected ILicMgrProductPrintDocumentPage prodPage;
	private LicMgrDocumentInfo doc0=new LicMgrDocumentInfo();
	private LicMgrDocumentInfo doc1=new LicMgrDocumentInfo();
	private LicMgrDocumentInfo doc2=new LicMgrDocumentInfo();
	private LicMgrDocumentInfo doc3=new LicMgrDocumentInfo();
	private LicMgrDocumentInfo doc4=new LicMgrDocumentInfo();
	private LicMgrDocumentInfo doc5=new LicMgrDocumentInfo();
	protected List<String> columns; 
	private String[] status=new String[]{"Active","Inactive"};
	private Date today;
	private int currentYear=Integer.parseInt(DateFunctions.getToday().split("/")[2]);
	private String[] ids;
	
	@Override
	public void execute() {
		lm.deleteAllProductDocFormDBForOneProduct(prodCode, prodType, parkName, schema);
		lm.loginLicenseManager(login);
		//Prepare data for testing;
		lm.addPrintDocumentForProduct(doc4,doc3,doc2,doc1,doc0);
		//Edit Print Order to generate an inactive record automatically by System,
		//All initial records Status should be Active
		doc0.id=this.getRecordIds(doc0)[0];
		doc0.printOrd=doc5.printOrd;
		lm.editPrintDocumentForProduct(doc0);
		this.verifyInitiallyFilters();
		doc0.status="Inactive";
		
		if(action==BUSSINESSRULE_UIREQUIREMENT){
		  	//Verify UI and Sorting
			ids=this.getRecordIds(doc0,doc5,doc2,doc3,doc4,doc1);
			this.verifySorting();
			this.verifyColumns(columns);
			this.verifyUIRequirements();
			//Verify Search
			this.verifySearch(true, "", "", "",doc1,doc2,doc3,doc4,doc5);
			this.verifySearch(false, "", "", "",doc0,doc1,doc2,doc3,doc4,doc5);
			this.verifySearch(false, "Active", "", "", doc1,doc2,doc3,doc4,doc5);
			this.verifySearch(false, "", "DocumentTest", "",doc0, doc1,doc2,doc3,doc4,doc5);
			this.verifySearch(false, "", "", "Phone Sales - CallManager",doc2,doc3,doc4);
			this.verifySearch(false, "Inactive", "", "All",doc0);
		}else if(action==INITIAL_FILTERS){

			//Verify initial filters
			doc5.id=this.getRecordIds(doc5)[0];
			this.verifyInitiallyFilters();
			//LicenseToYear
			this.upDateLicFromToYear(doc5.id,String.valueOf(currentYear-1),String.valueOf(currentYear-1),schema);
			this.verifyInitiallyFilters();
			this.upDateLicFromToYear(doc5.id,"","",schema);
			//Verify Effective To Date
			doc5.effectiveToDate=DateFunctions.getDateAfterToday(-2);
			doc5.effectiveFromDate=DateFunctions.getDateAfterToday(-8);
			lm.editPrintDocumentForProduct(doc5);
			this.verifyInitiallyFilters();
		}else{
			throw new ErrorOnDataException("Unknown action !");
		}
		
		lm.logOutLicenseManager();
	}
	
	/**
	 * update licYearFrom,licYearTo for a specified document records
	 * @param id
	 * @param linYearFrom
	 * @param linYearTo
	 */
	public void upDateLicFromToYear(String id, String linYearFrom,String linYearTo,String schema) {
	     String sql="update P_PRD_FULFILLMENT_DOC set LF_YEAR_FROM='"+linYearFrom+"',LF_YEAR_TO='"+linYearTo+"' where id="+id;
	     logger.info("update licYearFrom="+linYearFrom+",licYearTo="+linYearTo+" for document which id ="+id);
	     AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
	     db.resetSchema(schema);
	     db.executeUpdate(sql);
	}

	/**
	 * get document ids
	 * @param docs
	 * @return document ids
	 */
	private String[] getRecordIds(LicMgrDocumentInfo... docs) {
		String[] ids=new String[docs.length];
		prodPage.searchPrintDocument(false, "", "", "");
		for(int i=0;i<docs.length;i++){
			ids[i]=prodPage.getPrintDocumentAssignmentID(docs[i]);
		}
		return ids;
	}

	public void verifySearch(boolean currentOnly,String status,String docTemplate,String equipType,LicMgrDocumentInfo... docs) {
		prodPage.searchPrintDocument(currentOnly, status, docTemplate, equipType);
		
		logger.info("");
		int numOnpage=prodPage.getColumnValues("ID").size();
		if(numOnpage!=docs.length){
			throw new ActionFailedException("There should be "+docs.length+" records");
		}
		for(LicMgrDocumentInfo doc:docs){
			if(prodPage.getPrintDocumentAssignmentID(doc).trim().length()<1){
				throw new ActionFailedException("Search failed");
			}
		}
		
	}

	/**
	 * Verify UI Requirement in 'View Product Fulfillment Document List.DOC'
	 * 1.Verify Status Drop-down list
	 * 2.Verify Document Templates Drop-down list
	 * 3.Verify Equipment Type Drop-down list
	 */
	private void verifyUIRequirements(){
		this.verifyStatusDDList(status);
		this.verifyDocumentTemplatesDDList();
		this.verifyEquipmentTypeDDList();
	}
	
	
	/**
	 * Verify Sorting:
	 * First-Equipment Type by ASC,
	 * Second-LicYearFrom by ASC,
	 * Third-Purchase Type by ASC,
	 * Fourth-Print Order by ASC
	 */
	public void verifySorting() {
		logger.info("Verify Sorting.....");
		
		prodPage.searchPrintDocument(false, "", "", "");
		List<String> idsOnPage=prodPage.getColumnValues("ID");
		if(ids.length!=idsOnPage.size()){
			throw new ErrorOnPageException("There should be "+ids.length+" records.");
		}
		
		for(int i=0;i<ids.length;i++){
			if(!ids[i].equals(idsOnPage.get(i))){
				throw new ErrorOnPageException("Verify Sorting failed");
			}
		}
		logger.info("Verify Successfully.");
	}

	/**
	 * Verify types ddlist values
	 */
	public void verifyEquipmentTypeDDList(){
		List<String> typesInDb=this.getEquipmentType(schema);
		List<String> typesOnDDList=prodPage.getEquipmentTypeDDListValues();
		
		logger.info("Verify types ddlist values......");
		if(typesOnDDList.get(0).trim().length()>0){
			throw new ErrorOnPageException("The first option should be blank");
		}
		
		if(!typesOnDDList.get(1).equals("All")){
			throw new ErrorOnPageException("All should be the first option except blank one");
		}
		
		if(typesOnDDList.size()-2!=typesInDb.size()){
			throw new ErrorOnPageException("there should be "+typesInDb.size()+" options for select except blank one and 'All'");
		}
	
		for(int i=2;i<typesOnDDList.size();i++){
			if(!typesOnDDList.get(i).equals(typesInDb.get(i-2))){
				throw new ErrorOnPageException("Sort Error");
			}
		}
		logger.info("Verify successfully:equipment types drop-down list.");
	}
	
	/**
	 * get all Equipment types from DB
	 * @param schema
	 * @return
	 */
	public List<String> getEquipmentType(String schema){
		String sql="SELECT NAME FROM  D_EQUIPMENT_TYPE WHERE DELETED_IND=0 order by NAME";
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		logger.info("get all Equipment types form db.....");
		db.resetSchema(schema);
		List<String> list=db.executeQuery(sql, "NAME");
		return list;
	}
	
	
	
	/**
	 * Verify Document Templates drop-down list
	 */
	public void verifyDocumentTemplatesDDList(){
		List<String> columnVelaus=prodPage.getColumnValues("Document Template");
	    List<String> ddListValues=prodPage.getDocTemplatesDDLIstValues();
	    
	    logger.info("Verify Document Templates drop-down list..");
	    if(ddListValues.get(0).trim().length()>0){
	    	throw new ErrorOnPageException("System should allow user to select a blank option");
	    }
	    ddListValues.remove(0);
	    for(String value:columnVelaus){
	    	if(!ddListValues.contains(value)){
	    		throw new ErrorOnPageException("'"+value+"' should be contained in dropdown list values.");
	    	}
	    }
	    
	}
	
	/**
	 * Verify status drop-down list
	 * @param status
	 */
	public void verifyStatusDDList(String[] status){
		List<String> statusOnPage=prodPage.getStatusDDListValues();
		
		logger.info("Verify status drop-down list....");
		if(statusOnPage.get(0).trim().length()>0){
			throw new ErrorOnPageException("System should allow user select a blank option");
		}
		statusOnPage.remove("");
		if(statusOnPage.size()!=status.length){
			throw new ErrorOnPageException("There should be "+status.length+" options for selected");
		}
		for(String option:status){
			if(!statusOnPage.contains(option)){
				throw new ErrorOnPageException("Can't find '"+option+"' !");
			}
		}
		logger.info("Verify status DD-List successfully ! ");
	}

	/**
	 * verify columns
	 */
	private void verifyColumns(List<String> columns) {
		List<String> columnsOnpage=prodPage.getDocumentColumnNames();
		
		logger.info("Verify columns.....");
		if(columns.size()!=columnsOnpage.size()){
			throw new ErrorOnPageException("there should be "+columns.size()+" columns");
		}
		
		for(int i=0;i<columnsOnpage.size();i++){
			if(!columnsOnpage.contains(columns.get(i))){
				throw new ErrorOnPageException("Can't find column '"+columns.get(i)+"'");
			}
		}
		logger.info("Verify columns successfully");
	}

	/**
	 * verify innitial filters.....
	 */
	private void verifyInitiallyFilters() {
		prodPage.clickPrintDocumentsTab();
		ajax.waitLoading();
		logger.info("Verify innitial filters");
		if(!prodPage.isShowCurrentlyOnlySelected()){
			throw new ErrorOnPageException("the 'Show Current Records Only' check box should be selected.");
		}
		//verify Status is Active
		List<String> status=prodPage.getColumnValues("Status");
	    for(String value:status){
	    	if(!value.equals("Active")){
	    		throw new ErrorOnPageException("All records should be 'Active'.");
	    	}
	    }
	    
	    //Verify  License/Fiscal Year From equal to "All" or License/Fiscal Year To equal to null/blank or License/Fiscal Year To greater than or equal to the Current Year, 
	    List<String> licFromYear=prodPage.getColumnValues((prodType.equalsIgnoreCase("Privilege")?"License":"Fiscal")+" Year From");
	    List<String> licToYear=prodPage.getColumnValues((prodType.equalsIgnoreCase("Privilege")?"License":"Fiscal")+" Year To");
	    for(int i=0;i<licFromYear.size();i++){
	    	if(!licFromYear.get(i).equals("All")&&licToYear.get(i).length()!=0&&Integer.parseInt(licToYear.get(i))<currentYear){
	    		throw new ErrorOnPageException("License/Fiscal Year From must equal to \"All\" or License/Fiscal Year To must equal to null/blank or License/Fiscal Year To must be greater than or equal to the Current Year");
	    	}
	    }
	    
	    //verify Effective to date
	    List<String> effectiveTo=prodPage.getColumnValues("Effective To Date");
	    for(int i=0;i<effectiveTo.size();i++){
	    	if(effectiveTo.get(i).trim().length()>0){
	    		Date toDate=DateFunctions.parseDateString(effectiveTo.get(i), "E MMM d yyyy");
	    	    if(toDate.compareTo(today)<0){
	    	    	throw new ErrorOnPageException(" Effective To Date must equal to null/blank or be greater than or equal to the Current Date");
	    	    }
	    	}
	    }
	    logger.info("Verify successfully!");
	    
	}
	
	
	
	public void  wrapParameters(){
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
        
		parkName=login.location.split("/")[1];
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		TimeZone timeZone=DataBaseFunctions.getContractTimeZone(schema);
		today=DateFunctions.parseDateString(DateFunctions.getToday(timeZone));
		
		doc0.prodCode=prodCode;
		doc0.prodType=prodType;
		doc0.status="Active";
		doc0.printOrd="1";
		doc0.docTepl="DocumentTest";
		doc0.licYearFrom="All";
		doc0.effectiveFromDate=DateFunctions.getDateAfterToday(2);
		doc0.fulfillMethod="Fulfilled by Mail";
		doc0.equipType="All";
		doc0.purchaseType="Transfer";
		doc0.configVariables = new String[]{"auto"};
		
		doc1.prodCode=prodCode;
		doc1.prodType=prodType;
		doc1.status="Active";
		doc1.printOrd="3";
		doc1.docTepl="DocumentTest";
		doc1.licYearFrom=String.valueOf(currentYear+2);
		doc1.licYearTo=String.valueOf(currentYear+2);
		doc1.effectiveFromDate=DateFunctions.getDateAfterToday(-2);
		doc1.effectiveToDate=DateFunctions.getToday();
		doc1.fulfillMethod="Fulfilled by Mail";
		doc1.equipType="Touch Web-Pos - 01";
		doc1.purchaseType="Duplicate";
		doc1.configVariables = new String[]{"auto"};
		
		doc2.prodCode=prodCode;
		doc2.prodType=prodType;
		doc2.status="Active";
		doc2.printOrd="1";
		doc2.docTepl="DocumentTest";
		doc2.licYearFrom=String.valueOf(currentYear);
		doc2.licYearTo=String.valueOf(currentYear);
		doc2.effectiveFromDate=DateFunctions.getDateAfterToday(-2);
		doc2.effectiveToDate=DateFunctions.getToday();
		doc2.fulfillMethod="Fulfilled by Mail";
		doc2.equipType="Phone Sales - CallManager";
		doc2.purchaseType="Duplicate";
		doc2.configVariables = new String[]{"auto"};
		
		doc3.prodCode=prodCode;
		doc3.prodType=prodType;
		doc3.status="Active";
		doc3.printOrd="1";
		doc3.docTepl="DocumentTest";
		doc3.licYearFrom=String.valueOf(currentYear+1);
		doc3.licYearTo=String.valueOf(currentYear+1);
		doc3.effectiveFromDate=DateFunctions.getDateAfterToday(-2);
		doc3.effectiveToDate=DateFunctions.getToday();
		doc3.fulfillMethod="Fulfilled by Mail";
		doc3.equipType="Phone Sales - CallManager";
		doc3.purchaseType="Duplicate";
		doc3.configVariables = new String[]{"auto"};
		
		doc4.prodCode=prodCode;
		doc4.prodType=prodType;
		doc4.status="Active";
		doc4.printOrd="10";
		doc4.docTepl="DocumentTest";
		doc4.licYearFrom=String.valueOf(currentYear+1);
		doc4.licYearTo=String.valueOf(currentYear+1);
		doc4.effectiveFromDate=DateFunctions.getDateAfterToday(2);
		doc4.fulfillMethod="Fulfilled by Mail";
		doc4.equipType="Phone Sales - CallManager";
		doc4.purchaseType="Transfer";
		doc4.configVariables = new String[]{"auto"};
		
		doc5.prodCode=prodCode;
		doc5.prodType=prodType;
		doc5.status="Active";
		doc5.printOrd="10";
		doc5.docTepl="DocumentTest";
		doc5.licYearFrom="All";
		doc5.effectiveFromDate=DateFunctions.getDateAfterToday(2);
		doc5.fulfillMethod="Fulfilled by Mail";
		doc5.equipType="All";
		doc5.purchaseType="Transfer";
		doc5.configVariables = new String[]{"auto"};
		
		columns=new ArrayList<String>();
		columns.add("ID");
		columns.add("Status");
		columns.add("Document Template");
		columns.add("Equipment Type");
		columns.add("Purchase Type");
		columns.add("Effective From Date");
		columns.add("Effective To Date");
		columns.add("Print Order");
		columns.add("Fulfillment Method");
	}
}
