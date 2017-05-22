package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.activity.confletter;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivityAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivitySession;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReceiptDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This test case was designed to verify request HF confirmation letter for order which contains multiply items,
 * If void one order item, new receipt generated, there is no request HF confirmation letter button on new receipt details page;
 * The request HF confirmation letter button should exist on old receipt details page
 * @Preconditions:
 * @LinkSetUp:
 *D_ASSIGN_FEATURE:id=6202
 *D_HF_ADD_CUST_PROFILE:id=3210
 *D_HF_ADD_FACILITY:id=60,70
 *D_HF_ADD_FACILITY_PRD:id=70,80
 *D_HF_ADD_ACTIVITY:id=450,460
 * @SPEC:
 * Conf Letter for Activity-Activity Order Section with all sessions at the same facility and facility product [TC:116486]
 * Conf Letter for Activity-Activity Order Section with all sessions at the same facility, at lest one session at a different facility [TC:116487]
 * Conf Letter for Activity-Order contain more than one order items [TC:116489]
 * @Task#: Auto-2228
 * 
 * @author Jane Wang
 * @Date  July 07, 2014
 */
public class MultiOrdItems extends LicenseManagerTestCase {
	private List<Data<ActivityAttr>> activities = new ArrayList<Data<ActivityAttr>>();
	private Data<ActivityAttr> activity01 = new Data<ActivityAttr>();
	private Data<ActivityAttr> activity02 = new Data<ActivityAttr>();
	private String switchLoc, salesLoc;
	private String host, username, password, emailSubject, parkName;
	private Properties props[] = null;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoSearchActivityPgFromCustDetailsPg();
		lm.addActivitiesToCart(activities);
		String ordNum01 = lm.processOrderCart(pay);
		String receiptNum01 = lm.getReceiptNumByOrderNum(schema, ordNum01);
		
		lm.gotoHomePage();
		lm.switchLocationInHomePage(switchLoc);
		lm.gotoReceiptDetailsPage(receiptNum01);
		lm.requestHFConfirmLetter("Email");
		emailSubject = "Receipt#: "+receiptNum01+" - "+parkName;
		props = lm.getEmailFromMailBox(host, username, password, emailSubject, OrmsConstants.CHECK_NOTIFICATION_IN_MAILBOX_TIMEDIFF);
		verifyActivityConfirmationLetterContent(ordNum01, cust, activities);
		
		lm.switchLocationInHomePage(salesLoc);
		lm.gotoActivityRegistrationOrderDetailPageFromTopMenu(ordNum01);
		lm.voidActivityOrderItemByName(activity02.stringValue(ActivityAttr.activityCode)+" - "+activity02.stringValue(ActivityAttr.activityName),  "24 - void", "Regression Test");
		lm.processOrderCart(pay);
		String receiptNum02 =lm.getReceiptNumByOrderNum(schema, ordNum01);
		
		lm.gotoReceiptDetailsPage(receiptNum02);
		verifyRequestHFConfLetterBtnStatus(false);
		
		lm.gotoReceiptDetailsPage(receiptNum01);
		verifyRequestHFConfLetterBtnStatus(true);		
		lm.requestHFConfirmLetter("Email");
		emailSubject = "Receipt#: "+receiptNum01+" - "+parkName;
		props = lm.getEmailFromMailBox(host, username, password, emailSubject, OrmsConstants.CHECK_NOTIFICATION_IN_MAILBOX_TIMEDIFF);
		verifyActivityConfirmationLetterContent(ordNum01, cust, activities);
		
		lm.voidActivityRegistrationOrderToCart(ordNum01, "24 - void", "Regression Test");
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/WAL-MART";
		switchLoc = "HF Administrator - Auto-Mississippi Department of Wildlife, Fisheries, and Parks";
		salesLoc = "HF HQ Role - Auto-WAL-MART";
		
		//customer info
		cust.lName = "Test-HFConfirmLetter01";
		cust.fName = "QA-HFConfirmLetter01";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "19790501";
		cust.residencyStatus = "Non Resident";
				
		//Set up activity info
		activity01.put(ActivityAttr.activityCode, "ACL01");
		activity01.put(ActivityAttr.activityName, "Activity for Confirmation Letter01");
		activity01.put(ActivityAttr.activityID, lm.getProductID("Product Name", activity01.stringValue(ActivityAttr.activityName), null, true, schema, PRDCAT_ACTIVITY));
		List<Data<ActivitySession>> sessions01 = new ArrayList<Data<ActivitySession>>();
		Data<ActivitySession> session01 = new Data<ActivitySession>();
		session01.put(ActivitySession.Date, "2014-1-1");
		session01.put(ActivitySession.StartTime, "8:00");
		session01.put(ActivitySession.StartTimeAPM, "AM");
		session01.put(ActivitySession.EndTime, "10:00");
		session01.put(ActivitySession.EndTimeAPM, "AM");
		session01.put(ActivitySession.Facility, "Main facility for confirmation letter");
		session01.put(ActivitySession.FacilityAddr, "happy road<br>sisi, DC 12389");
		session01.put(ActivitySession.Product, "Product for main facility");
		sessions01.add(session01);
		Data<ActivitySession> session02 = new Data<ActivitySession>();
		session02.put(ActivitySession.Date, "2014-2-1");
		session02.put(ActivitySession.StartTime, "2:00");
		session02.put(ActivitySession.StartTimeAPM, "PM");
		session02.put(ActivitySession.EndTime, "4:00");
		session02.put(ActivitySession.EndTimeAPM, "PM");
		session02.put(ActivitySession.Facility, "Main facility for confirmation letter");
		session02.put(ActivitySession.FacilityAddr, "happy road<br>sisi, DC 12389");
		session02.put(ActivitySession.Product, "Product for main facility");
		sessions01.add(session02);
		activity01.put(ActivityAttr.activitySessions, sessions01);
		
		activity02.put(ActivityAttr.activityCode, "ACL02");
		activity02.put(ActivityAttr.activityName, "Activity for Confirmation Letter02");
		activity02.put(ActivityAttr.activityID, lm.getProductID("Product Name", activity02.stringValue(ActivityAttr.activityName), null, true, schema, PRDCAT_ACTIVITY));
		List<Data<ActivitySession>> sessions02 = new ArrayList<Data<ActivitySession>>();
		Data<ActivitySession> session03 = new Data<ActivitySession>();
		session03.put(ActivitySession.Date, "2014-5-1");
		session03.put(ActivitySession.StartTime, "10:00");
		session03.put(ActivitySession.StartTimeAPM, "AM");
		session03.put(ActivitySession.EndTime, "11:00");
		session03.put(ActivitySession.EndTimeAPM, "AM");
		session03.put(ActivitySession.Facility, "Main facility for confirmation letter");
		session03.put(ActivitySession.FacilityAddr, "happy road<br>sisi, DC 12389");
		session03.put(ActivitySession.Product, "Product for main facility");
		sessions02.add(session03);
		Data<ActivitySession> session04 = new Data<ActivitySession>();
		session04.put(ActivitySession.Date, "2014-6-1");
		session04.put(ActivitySession.StartTime, "2:00");
		session04.put(ActivitySession.StartTimeAPM, "PM");
		session04.put(ActivitySession.EndTime, "4:00");
		session04.put(ActivitySession.EndTimeAPM, "PM");
		session04.put(ActivitySession.Facility, "Sub facility for confirmation letter");
		session04.put(ActivitySession.FacilityAddr, "happy road<br>titi, MS 1356");
		session04.put(ActivitySession.Product, "Product for sub facility");
		sessions02.add(session04);
		activity02.put(ActivityAttr.activitySessions, sessions02);
		
		activities.add(activity01);
		activities.add(activity02);
		
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		parkName = "Mississippi Department of Wildlife, Fisheries, and Parks";
	}
	
	private void verifyRequestHFConfLetterBtnStatus(boolean expectedValue) {
		OrmsReceiptDetailsPage receiptDetailsPg = OrmsReceiptDetailsPage.getInstance();
		
		boolean uiValue = receiptDetailsPg.isRequestHFConfLetterEnabled();
		if(uiValue != expectedValue)
			throw new ErrorOnPageException("Request H&F Conf Letter button status is not correct on page.", expectedValue, uiValue);
		logger.info("---Verify Request H&F Conf Letter button status successfully.");
	}
	
	private void verifyActivityConfirmationLetterContent(String ordNum, Customer cust, List<Data<ActivityAttr>> activities) {
		logger.info("Verify activity confirmation letter content.");
		boolean passed = true;
		String emailContent = props[0].getProperty("text");
		logger.info(emailContent);
		
		if(!emailContent.contains(ordNum)) {
			passed = false;
			logger.error("Could not get order number from mail content. ---"+ordNum);
		}
		
		String custName = "NAME: "+cust.fName.toUpperCase()+" "+cust.lName.toUpperCase();
		if(!emailContent.contains(custName)) {
			passed = false;
			logger.error("Could not get customer name from mail content. ---"+custName);
		}
		
		for(Data<ActivityAttr> a:activities) {
			String activityInfo = a.stringValue(ActivityAttr.activityCode) + " - " + a.stringValue(ActivityAttr.activityName);
			String facility = StringUtil.EMPTY;
			String sessionInfo = StringUtil.EMPTY;
			ArrayList<Data<ActivitySession>> sessions = (ArrayList<Data<ActivitySession>>)(a.get(ActivityAttr.activitySessions));
			for(Data<ActivitySession> s:sessions) {
				String date = DateFunctions.formatDate(s.stringValue(ActivitySession.Date), "EEE MMM dd yyyy");
				if(StringUtil.notEmpty(facility) && facility.equalsIgnoreCase(s.stringValue(ActivitySession.Facility))) {
					sessionInfo += date + " " + s.stringValue(ActivitySession.StartTime) + " " + s.stringValue(ActivitySession.StartTimeAPM) + " - " + s.stringValue(ActivitySession.EndTime) + " " + s.stringValue(ActivitySession.EndTimeAPM)+" CDT<br>";
					continue;
				}
				facility = s.stringValue(ActivitySession.Facility);
				if(StringUtil.notEmpty(sessionInfo))
					sessionInfo += "<br>";
				sessionInfo += facility+"<br>"
				+ s.stringValue(ActivitySession.FacilityAddr)+"<br>"
				+ s.stringValue(ActivitySession.Product)+"<br><br>"
				+ date + " " + s.stringValue(ActivitySession.StartTime) + " " + s.stringValue(ActivitySession.StartTimeAPM) + " - " + s.stringValue(ActivitySession.EndTime) + " " + s.stringValue(ActivitySession.EndTimeAPM)+" CDT<br>";
			}
			if(!emailContent.contains(activityInfo)
			|| !emailContent.contains(sessionInfo)) {
				passed = false;
				logger.error("Could not get activity/session info from mail content. ---"+activityInfo+"\n"+sessionInfo);
			}
		}
		
		if(!passed)
			throw new ErrorOnPageException("Failed to verify confirmation letter content.");
		logger.info("---Verify confirmation letter content successfully.");
	}
}
