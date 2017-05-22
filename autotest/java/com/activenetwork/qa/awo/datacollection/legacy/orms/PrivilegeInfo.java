package com.activenetwork.qa.awo.datacollection.legacy.orms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @author ssong
 * @date Feb 14, 2011
 */
public class PrivilegeInfo {

	public String code = "";

	public String name = "";// name when creating/edit a privilege
	
	public String legalName = "";

	public String status = "";
	/** inventory type **/
	public String invType = "";
	
	public String purchasingName = "";// the name when purchasing privilege

	public String privilegeId = "";// the privilege number after purchasing privilege

	public String licenseYear = "";

	public String qty = "1"; // for the privilege quantity

	public String inventoryNum = ""; // for purchase privilege with inventory
	
	public String inventoryQty=""; //for purchase privilege with inventory
	
	public String inventoryQtyFrom="";
	
	public String inventoryQtyTo="";
	
	public String inventoryQtyType=""; //for purchase privilege with privileges
	
	public boolean useDefaultInventory=false;//for purchase privilege with inventory

	public String operateReason = "";
	
	public String createLocation = "";
	
	public String createUser = "Test-Auto, QA-Auto";
	
	public String createDate = "";
	
	public String updateDate = "";
	
	public String changeAction = "";
	
	public String changeField = "";
	
	public String changeValue = "";
	
	public String oldValue = "";

	public String operateNote = "QA Automation";

	public PrivilegeBusinessRule[] rules;

	public LicenseYear licYear = new LicenseYear();
	
	public LicMgrDocumentInfo document = new LicMgrDocumentInfo();

	public String validFromDate = DateFunctions.getToday();
	
	public String validFromTime = "";
	
	public String validFromAmPm = "";
	
	public String validToDate = "";

	public QuestionInfo[] privilegeQuestions = null; // questions of privilege privilegeInfo
	
	//For seal num options page
	public boolean purchseChoiceIsEmail = true;
	public String sealNum = "";
	
	/***************these attributes will be used when creating a new privilege product*************/
	public String productGroup = "";
	
	public String huntsRequired = "";
	
	public String validFromDateCalculation = "";
	
	public String promptIndicator = "";
	
	public String validToDateCalculation = "";
	
	public String validDaysYears = "";
	
	public String dateUnitOfValidToDate = "";
	
	public String renewalDays = "";
	
	public String validToAge = "";
	
	public String[] validDatePrinting;
	
	public String[] customerClasses;
	
	public String authorizationQuantity = "";
	
	public String displayCategory = "";
	
	public String displaySubCategory = "";
	
	public String reportCategory = "";
	
	public String displayOrder = "";
	
	public String allocationType = ""; // Allocation Type
	
	public String extensionType = ""; // Extension Type
	
	public String allocationPriv = ""; // Allocation Privilege
	
	public String numOfDuplicates = "";//Need privilege number duplicates in privilege detail page.
	
	public String numOfReprints = "";//Need privilege number reprint in privilege detail page.
	
	public String creationPrice = "";//Need privilege creation price in privilege detail page.
	
	public boolean isLandowner = false;// default
	
	public String priceNote = "";
	
	public boolean allowGeneralPublic = false;
	
	public boolean allowQuickSale = false;//Jane[2014-4-21]:This default value will be used in setup scripts
	
	public String consecutiveDaysInd = "";
	
	public String defaultToBlank = "";
	
	public String fulfillmentMethod = "";//Jane[2014-4-24]:Added for privilege inventory 
	
	public String purchaseAuth = "";
	
	public String purchaseAuthType = "";
	
	public String authQtyPerLY = "";	
	
	public boolean checkBusiRuleInSaleFlow = false;
	
	public String hunterHostLicenceType = ""; //Sara[20140611] Add for authorization
	
	public String hunterHostAppendLicence = "";
	
	public String requiredHunterHostPrivilege = "";
	
	public String maxHuntersAllowed = "";
	/******************************************************************************/
	// if isLandowner is true, need to setup landownerDeclaration info
	public HashMap<String, String> landownerDeclaration = new HashMap<String, String>();
	
	//For privilege search.
	public String searchBy = "";
	
	public String searchValue = "";
	
	public String searchDateType = "";
	
    public String searchZip = "";
    
    public String searchCounty = "";
    
    public String searchState = "";
    
    public String searchCountry = "";
    
    public String searchReStatus = "";
    
	public int index = 0; //Use for identifying the privilege license year or document index in Batch Edit License Year page
	
	//For privilege search in customer privilege page
	public String licenseYearForSearch = "";
	//isActive,isExpired,isInvalid,isReversed,isRevoked,isTransferred,isVoided
	public String searchStatus[] = new String[]{};
	
	public Customer cust=null;
	
	public Customer authorizedCust = null; // Lesley[20130923]: the customer who is authorized to purchase a guided license	
	public String outfitterNm = ""; // Lesley[20130923]: outfitter name, which is the business name of the business customer associated with the Authorized Privilege
	public String outfitterPermitNum = ""; // Lesley[20130923]: outfitter permit number: which is the business number  of the business customer associated with the Authorized Privilege
	public String authPrivNum = ""; // Lesley[20130923]: the Authorized Privilege instance number
	
	public List<HuntInfo> hunts = null;
	public void setHuntInfo(HuntInfo...huntInfos) {
		this.hunts = new ArrayList<HuntInfo> ();
		for (HuntInfo hunt : huntInfos) {
			this.hunts.add(hunt);
		}
	}
	
//	public String AvaViaAppOnly;
	@Override
	public String toString() {
		return "name="+this.purchasingName+",type="+this.invType+",year="+this.licenseYear+",qty="+this.qty;
	}
	
	//Quentin[20140528]
	public boolean isPartnerLicence = false;
	public List<String> associatedPartnerLicences = new ArrayList<String>();//at most 2 options
	
	//Quentin[20140528] if a privilege is setup as 'Partner Licence'=true, when purchasing it in add item page, you have to search and select an primary privilege number then go to cart
	public String primaryPrivilegeNum = "";
	public String primaryPrivilegeName = "";
	public String primaryPrivilegeHolder = "";
}
