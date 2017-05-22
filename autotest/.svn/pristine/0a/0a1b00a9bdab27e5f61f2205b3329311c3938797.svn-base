/*
 * Created on Jul 2, 2009
 *
 */
package com.activenetwork.qa.awo.keywords.orms.search;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.callManager.CallMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.callManager.CallMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.common.customer.OrmsCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.customer.OrmsCustomerSearchPage;
import com.activenetwork.qa.awo.pages.orms.operationManager.OpMgrTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.browser.IBrowser;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author QA
 *
 */
public class CustomerSearch {
	private static CustomerSearch _instance = null;

	List<List<String>> customerinfo = new ArrayList<List<String>>();

	protected static AutomationLogger logger = AutomationLogger.getInstance();

	public static CustomerSearch getInstance() {
		if (null == _instance)
			_instance = new CustomerSearch();

		return _instance;
	}

	/**For customer search
	 * From home page to customer search page
	 * @param  cust
	 * */
	public void customerSearch(Customer cust) {
		IBrowser browser = Browser.getInstance();
		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage
				.getInstance();
		CallMgrHomePage cmHmPg = CallMgrHomePage.getInstance();
		CallMgrTopMenuPage cmTopMenuPg = CallMgrTopMenuPage.getInstance();
		OpMgrTopMenuPage opmTopMenuPg = OpMgrTopMenuPage.getInstance();

		logger.info("search customer.");

		if (cmHmPg.exists())
			cmHmPg.clickCampingCall();

		//go to customer search page
		browser.waitExists(cmTopMenuPg, opmTopMenuPg);

		if (cmTopMenuPg.exists()){
			cmTopMenuPg.searchCustomers();
		}
		omCustSchPg.waitLoading();

		//set customer criteria and search customer
		omCustSchPg.searchCust(cust);
		omCustSchPg.waitLoading();
	}

	/** verify searched customer information in the customer search page match the search criteria
	 * @param cust
	 **/
	public void verifyCustomerInCustomerSearchPg(Customer cust) {
		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage.getInstance();
		List<String> customerinforow = new ArrayList<String>();
		int columnnum = 0;

		customerinfo.clear();
		customerinfo = omCustSchPg.retriveCustomerinfo();
		if(customerinfo.size() > 0){
			if (null != cust.hPhone && cust.hPhone.length()>0) {
				columnnum = omCustSchPg.getColNum("Phone");
				for (int i = 0; i < customerinfo.size(); i++) {//start point should from 0, as the first row has been removed when retrieve customer info
					customerinforow = customerinfo.get(i);
			
					if (cust.areacode == true) {
					  if(!customerinforow.get(columnnum).toString().equalsIgnoreCase(cust.hPhone)){
					    throw new ItemNotFoundException("The phone "
									+ customerinforow.get(columnnum)
									+ " doesn't match search criteria");
					  }
					} else {
						if (!customerinforow.get(columnnum).toString().substring(3).equalsIgnoreCase(cust.hPhone)){
						  throw new ItemNotFoundException("The phone "
								+ customerinforow.get(columnnum)
								+ " doesn't match search criteria");
						}
					}
				}
			}

			if (null != cust.fName && cust.fName.length()>0) {
				columnnum = omCustSchPg.getColNum("First Name");
				
				for (int i = 0; i < customerinfo.size(); i++) {
					customerinforow = customerinfo.get(i);
					if (!customerinforow.get(columnnum).toString().equalsIgnoreCase(cust.fName)){
					  throw new ItemNotFoundException("The first name "
							+ customerinforow.get(columnnum)
							+ " doesn't match search criteria");
					}
				}
			}


			if (null != cust.lName && cust.lName.length()>0) {
				columnnum = omCustSchPg.getColNum("Last Name");
				for (int i = 0; i < customerinfo.size(); i++) {
					customerinforow = customerinfo.get(i);
					if (!customerinforow.get(columnnum).toString().equalsIgnoreCase(cust.lName)){
					  throw new ItemNotFoundException("The last name "
							+ customerinforow.get(columnnum)
							+ " doesn't match search criteria");
					}
				}
			}

			if (null != cust.email && cust.email.length()>0) {
				columnnum = omCustSchPg.getColNum("Email");
				
				for (int i = 0; i < customerinfo.size(); i++) {
					customerinforow = customerinfo.get(i);
					if (!customerinforow.get(columnnum).toString().equalsIgnoreCase(cust.email)){
					  throw new ItemNotFoundException("The Email "
						+ customerinforow.get(columnnum)
						+ " doesn't match search criteria");
					}
				}  
			}

			if (null != cust.organization && cust.organization.length()>0) {
				columnnum = omCustSchPg.getColNum("Organization Name");
				
			    for(int i=0;i<customerinfo.size();i++) {
			      customerinforow = customerinfo.get(i);
					if (!customerinforow.get(columnnum).toString().equalsIgnoreCase(cust.organization))
					  throw new ItemNotFoundException("The Organization Name "
							+ customerinforow.get(columnnum)
							+ " doesn't match search criteria");
			    }
			}

			if (null != cust.zip && cust.zip.length()>0) {
				columnnum = omCustSchPg.getColNum("Address");
				
				for (int i = 0; i < customerinfo.size(); i++) {
			        customerinforow = customerinfo.get(i);
					String address = customerinforow.get(columnnum).toString();
					String zip[] = address.split(", ");
					if (!zip[zip.length - 1].equalsIgnoreCase(cust.zip)){
					  throw new ItemNotFoundException("The Zip "
							+ zip[zip.length - 1]
							+ " doesn't match search criteria");
					}				
				}	
			}
		} else {
			throw new ItemNotFoundException("No matched customer found");
		}
	}

	/**
	 * verify specific customer whether can be found
	 * @param initialcustomerinfo
	 * */
	public void verifySpecificCustomer(List<String> initiallcustomerinfo) {
		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage
				.getInstance();
		logger.info("Verify specific customer");
		
		List<String> customerinforow = null;
		customerinfo.clear();
		customerinfo = omCustSchPg.retriveCustomerinfo();
		
		if(customerinfo.size()>=1){//customerinfo.size()>1
			for (int i = 0; i <customerinfo.size(); i++) {
				if(customerinfo.get(i)!=null){
					customerinforow = customerinfo.get(i);
				}
				if (initiallcustomerinfo.toString().replaceAll(", ", "").equalsIgnoreCase(
						customerinforow.subList(1, customerinforow.size() - 2)
								.toString().replaceAll(", ", ""))) {
					logger.info("Success to find the customer");
					break;
				} 
				else if (i == customerinfo.size() - 1) {
					throw new ItemNotFoundException("Fail to find the customer");
				}
			}
		}
		else{
		  throw new ItemNotFoundException("No matched customer found");
		}
	}

	/**update customer info
	 * From customer search page to customer detail page
	 * @param  cust
	 * */
	public void updateCustomerInfo(Customer cust) {
		OrmsCustomerDetailsPage omCustDetailPg = OrmsCustomerDetailsPage
				.getInstance();
		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage
				.getInstance();
		logger.info("Update Customer Info.");

		omCustSchPg.gotoCustDetail(cust.lName);
		omCustDetailPg.waitLoading();
		omCustDetailPg.setCustInfo(cust);
		omCustDetailPg.clickApply();
		omCustDetailPg.waitLoading();
		omCustDetailPg.clickOK();
		omCustSchPg.waitLoading();
		/*
		 * the customer search result will not refresh automatically after update the customer information, 
		 * so need to click the search button again
		 */
		//omCustSchPg.clickFindExistingCust();
		//omCustSchPg.waitExists();
	}

	/**back to customer search page
	 * From customer detail page to customer search page 
	 * */
	public void gobackCustomerSearchPage() {

		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage
				.getInstance();
		OrmsCustomerDetailsPage omCustDetailPg = OrmsCustomerDetailsPage
				.getInstance();
		omCustDetailPg.clickCustomerSearch();
		omCustSchPg.waitLoading();

	}

	/**
	 * Verify each column after search customer.
	 * @param searchBy
	 * @param expectValue
	 * @return
	 */
	public boolean verifyEachColumn(String searchBy, String expectValue){
		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage.getInstance();
		
		logger.info("Verify "+searchBy+" column value after search customer.");
		List<String> valueList = new ArrayList<String>();
		
		if(searchBy.startsWith("AddressInfo")){
			valueList = omCustSchPg.getColunmValueByName("Address");
		} else {
			valueList = omCustSchPg.getColunmValueByName(searchBy);
		}
		
		boolean result = true;
		if (valueList.size() > 0) {
			for(String actualValue:valueList){
				if("Customer Type".equals(searchBy) || "Customer Pass".equals(searchBy)){
					// if value is All, it will be displayed as blank.
					if("All".equalsIgnoreCase(expectValue)){
						expectValue = StringUtil.EMPTY;
					}
				}
				
				// Address info displayed like: 1453 KERNERSVILLE WAYS, UNION CROSS, NC, USA, 27284
				if(searchBy.startsWith("AddressInfo")){
					String[] address = actualValue.split(",");
					if(searchBy.equals("AddressInfo - Address")){
						actualValue = address[0].trim();
					} else if(searchBy.equals("AddressInfo - City/Town")){
						actualValue = address[1].trim();
					} else if(searchBy.equals("AddressInfo - State")){
						actualValue = address[2].trim();
					} else if(searchBy.equals("AddressInfo - Country")){
						actualValue = address[3].trim();
					} else if(searchBy.equals("AddressInfo - Zip")){
						actualValue = address[4].trim();
					}
				}

				if("Last Name".equals(searchBy) || "First Name".equals(searchBy)){
					if(!actualValue.equalsIgnoreCase(expectValue) &&
							!actualValue.startsWith(expectValue)){
						logger.error(searchBy+" is not correct. Expect is:"+expectValue+", but actual is:"+actualValue);
						result &= false;
					}
				} else {
					result &= MiscFunctions.compareResult(searchBy, expectValue, actualValue.trim());
				}
			}
		} else {
			throw new ErrorOnPageException("There isn't any record need to be verified!");
		}
		return result;
	}

}
