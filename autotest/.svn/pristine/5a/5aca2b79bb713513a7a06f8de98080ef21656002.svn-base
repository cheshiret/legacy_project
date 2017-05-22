package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.intersatecontact;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InterstateContactInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.interstatecontact.LicMgrInterstateContactListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * Script Name   : Add Interstate Contact
 * Generated     : 
 * Description   : Interstate Contact related specs happy path AUTO-998
 * Original Host : 
 * working flow  : random select a state-->create new interstate contact-->verify in DB
 * -->search new interstate contact--> verify in UI list-->edit this contact-->verify in DB
 * 
 * "CreateModifyInterStateContact" feature need to assign.
 *				
 * 
 * 
 * @since  2012/05/28
 * @author pzhu
 */

public class AddContact extends LicenseManagerTestCase {
	private String schema = "";
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();

	private String[] stateInfo = null;// [0]: name; [1]: id
	private int STATE_NAME = 0;
	private int STATE_ID = 1;
	private String COUNTRY_USA = "2";
	private InterstateContactInfo contact = new InterstateContactInfo();
	private InterstateContactInfo searchResult;
	private List<String> emailsForAdd = new ArrayList<String>();
	private List<String> emailsForEdit = new ArrayList<String>();
	private String ACTIVE = "Active";
	private String userID = "";
	private String userfName = "";
	private String userlName = "";
	private String LOCATION = "WAL-MART";
	private String locationID = "";
	private String createDate = DateFunctions.getToday("MM/dd/yyyy");
	LicMgrInterstateContactListPage interstateContactPg = LicMgrInterstateContactListPage
			.getInstance();

	public void execute() {
		lm.loginLicenseManager(login);

		// get operator/location information
		this.getOperateUser();
		this.getLocationID();

		// randomly select one State to add interstate contact
		stateInfo = randomSelectStateFromDB();
		// clear state contact of the selected State//a state only have one active interstate contact at one time
		clearInterstateContact(stateInfo[STATE_ID]);

		// prepare contact info for adding
		contact.setState(stateInfo[STATE_NAME]);
		contact.setEmails(emailsForAdd);

		lm.gotoInterstateContactPageFromHome();

		// Test step 1: Add Interstate contact
		String errorMsg = lm.addInterstateContact(contact);
		if (!StringUtil.isEmpty(errorMsg)) {
			throw new TestCaseFailedException(
					"Add interstate contact errror, -->" + errorMsg);
		}
		// Check point 1: check new created contact in DB
		this.checkAddedContactInDB();
		logger.info("Check point 1: check new created contact in DB -->> success!!!");


		
		// Test step 2: search Interstate contact
		interstateContactPg.searchInterstateContact(contact.getState(),this.ACTIVE);
		searchResult = interstateContactPg.getFirstRecodeOfInterstateContact();
		// Check point 2: search result(searchResult) should be same as (contact)
		this.checkSearchResult();
		logger.info("Check point 2: search result(searchResult) should be same as (contact) -->> success!!!");

		
		// Test step 3: edit Interstate contact
		lm.editInterstateContact(contact.getId(),emailsForEdit);
		//check point 3: check edit result
		this.checkEditContactInDB();
		logger.info("check point 3: check edit result -->> success!!!");
		
		lm.logOutLicenseManager();

	}

	/**
	 * 
	 */
	private void checkEditContactInDB() {

		this.db.resetSchema(this.schema);
		logger.info("Changed schema to -->>" + this.schema);
		String[] colNames = { "id", "emails", "create_user", "create_loc",
				"create_date", "modified_user", "modified_date" };
		String query = "select id, emails, create_user, create_loc, create_date, modified_user, modified_date from D_INTERSTATE_CONTACT "
				+ "where state_province = "
				+ stateInfo[STATE_ID]
				+ " and active_ind=1 and delete_ind=0";
		logger.info("Execute query: " + query);

		List<String[]> result = this.db.executeQuery(query, colNames);

		contact.setId(result.get(0)[0]);// get new added contact ID

		// check emails
		boolean finalResult = true;
		boolean found = false;
		for (String s : result.get(0)[1].split(";")) {

			found = false;
			for (String ss : emailsForEdit) {
				if (s.equalsIgnoreCase(ss)) {
					found = true;
					break;
				}

			}
			finalResult &= found;

		}
		if (!finalResult) {
			throw new TestCaseFailedException(
					"Emails of interstate contact error, should be "
							+ emailsForAdd.toString()
							+ ", but search result is " + result.get(0)[1]);
		}

		// check create user ID
		if (!result.get(0)[2].equalsIgnoreCase(this.userID)) {
			throw new TestCaseFailedException(
					"user ID of interstate contact error, should be "
							+ this.userID + ", but search result is "
							+ result.get(0)[2]);
		}

		// check create location ID
		if (!result.get(0)[3].equalsIgnoreCase(this.locationID)) {
			throw new TestCaseFailedException(
					"Location ID of interstate contact error, should be "
							+ this.locationID + ", but search result is "
							+ result.get(0)[3]);
		}

		// check create date
		if (!DateFunctions.formatDate(result.get(0)[4], "yyyy-MM-dd hh:mm:ss.S",
				"MM/dd/yyyy").equalsIgnoreCase(this.createDate)) {
			throw new TestCaseFailedException(
					"Create date of interstate contact error, should be "
							+ this.createDate + ", but search result is "
							+ result.get(0)[4]);
		}

		
	}

	/**
	 * 
	 */
	private void checkAddedContactInDB() {

		this.db.resetSchema(this.schema);
		logger.info("Changed schema to -->>" + this.schema);
		String[] colNames = { "id", "emails", "create_user", "create_loc",
				"create_date", "modified_user", "modified_date" };
		String query = "select id, emails, create_user, create_loc, create_date, modified_user, modified_date from D_INTERSTATE_CONTACT "
				+ "where state_province = "
				+ stateInfo[STATE_ID]
				+ " and active_ind=1 and delete_ind=0";
		logger.info("Execute query: " + query);

		List<String[]> result = this.db.executeQuery(query, colNames);

		contact.setId(result.get(0)[0]);// get new added contact ID

		// check emails
		boolean finalResult = true;
		boolean found = false;
		for (String s : result.get(0)[1].split(";")) {

			found = false;
			for (String ss : emailsForAdd) {
				if (s.equalsIgnoreCase(ss)) {
					found = true;
					break;
				}

			}
			finalResult &= found;

		}
		if (!finalResult) {
			throw new TestCaseFailedException(
					"Emails of interstate contact error, should be "
							+ emailsForAdd.toString()
							+ ", but search result is " + result.get(0)[1]);
		}

		// check create user ID
		if (!result.get(0)[2].equalsIgnoreCase(this.userID)) {
			throw new TestCaseFailedException(
					"user ID of interstate contact error, should be "
							+ this.userID + ", but search result is "
							+ result.get(0)[2]);
		}

		// check create location ID
		if (!result.get(0)[3].equalsIgnoreCase(this.locationID)) {
			throw new TestCaseFailedException(
					"Location ID of interstate contact error, should be "
							+ this.locationID + ", but search result is "
							+ result.get(0)[3]);
		}

		// check create date
		if (!DateFunctions.formatDate(result.get(0)[4], "yyyy-MM-dd hh:mm:ss.S",
				"MM/dd/yyyy").equalsIgnoreCase(this.createDate)) {
			throw new TestCaseFailedException(
					"Create date of interstate contact error, should be "
							+ this.createDate + ", but search result is "
							+ result.get(0)[4]);
		}

	}

	/**
 * 
 */
	private void checkSearchResult() {

		// check ID
		if (!searchResult.getId().equalsIgnoreCase(contact.getId())) {
			throw new TestCaseFailedException(
					"ID of interstate contact error, should be "
							+ contact.getId() + ", but search result is "
							+ searchResult.getState());
		}

		// check State
		if (!searchResult.getState().equalsIgnoreCase(stateInfo[STATE_NAME])) {
			throw new TestCaseFailedException(
					"Sate of interstate contact error, should be "
							+ stateInfo[STATE_NAME] + ", but search result is "
							+ searchResult.getState());
		}

		// check status
		if (!searchResult.getStatus().equalsIgnoreCase(ACTIVE)) {
			throw new TestCaseFailedException(
					"Status of interstate contact error, should be " + ACTIVE
							+ ", but search result is "
							+ searchResult.getStatus());
		}

		// check emails
		boolean finalResult = true;
		boolean found = false;
		for (String s : searchResult.getEmails()) {

			found = false;
			for (String ss : emailsForAdd) {
				if (s.equalsIgnoreCase(ss)) {
					found = true;
					break;
				}

			}
			finalResult &= found;

		}
		if (!finalResult) {
			throw new TestCaseFailedException(
					"Emails of interstate contact error, should be "
							+ emailsForAdd.toString()
							+ ", but search result is "
							+ searchResult.getEmails().toString());
		}
		
	}

	private void clearInterstateContact(String stateID) {
		this.db.resetSchema(this.schema);
		logger.info("Changed schema to -->>" + this.schema);

		String update = "update D_INTERSTATE_CONTACT  set active_ind = 0 where state_province = "
				+ stateID;
		logger.info("Execute query: " + update);
		this.db.executeUpdate(update);

	}

	private String[] randomSelectStateFromDB() {
		this.db.resetSchema(this.schema);
		logger.info("Changed schema to -->>" + this.schema);
		String[] colNames = { "name", "id" };
		String query = "SELECT name, id FROM D_REF_STATE_PROVNC WHERE COUNTRY_ID="
				+ this.COUNTRY_USA + " ORDER BY NAME";
		logger.info("Execute query: " + query);

		List<String[]> result = this.db.executeQuery(query, colNames);
		int size = result.size();
		logger.info("All " + size + " States in DB");
		int seq = ((int) (Math.random() * 10000)) % size;
		logger.info("Got random number " + seq + ", will get NO." + seq
				+ " of States." + " State selected is -->"
				+ (result.get(seq))[0] + ":" + (result.get(seq))[1]);
		return result.get(seq);
	}

	private void getOperateUser() {
		this.db.resetSchema(this.schema);
		logger.info("Changed schema to -->>" + this.schema);
		String[] colNames = { "first_name", "last_name", "id" };
		String query = "select first_name, last_name, id FROM X_USER WHERE name = '"
				+ this.login.userName + "'";
		logger.info("Execute query: " + query);
		List<String[]> result = this.db.executeQuery(query, colNames);

		this.userfName = result.get(0)[0];
		this.userlName = result.get(0)[1];
		this.userID = result.get(0)[2];
	}

	private void getLocationID() {
		this.db.resetSchema(this.schema);
		logger.info("Changed schema to -->>" + this.schema);
		String[] colNames = { "id" };
		String query = "select id from d_loc where name like '" + LOCATION
				+ "' and type_id=7"; // 7: means park
		logger.info("Execute query: " + query);
		List<String[]> result = this.db.executeQuery(query, colNames);

		this.locationID = result.get(0)[0];

	}

	public void wrapParameters(Object[] param) {
		this.login.contract = "MS Contract";
		this.login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(new StringBuilder(String
				.valueOf(this.env)).append(".db.schema.prefix").toString())
				+ "MS";

		emailsForAdd.clear();
		emailsForAdd.add("QA-Test-Add" + Integer.toString((int) (Math.random() * 1000000))	+ "@active.com");
		emailsForAdd.add("QA-Test-Add" + Integer.toString((int) (Math.random() * 1000000))	+ "@active.com");

		emailsForEdit.clear();
		emailsForEdit.add("QA-Test-Edit"+ Integer.toString((int) (Math.random() * 1000000))	+ "@active.com");
		emailsForEdit.add("QA-Test-Edit"+ Integer.toString((int) (Math.random() * 1000000))	+ "@active.com");
		emailsForEdit.add("QA-Test-Edit"+ Integer.toString((int) (Math.random() * 1000000)) + "@active.com");
		emailsForEdit.add("QA-Test-Edit"+ Integer.toString((int) (Math.random() * 1000000))	+ "@active.com");
		
	}
}
