/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor.bond;

import java.util.Properties;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBondsSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.NotSupportedException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * @Description: Bond Expiry Email Notification
 * @Preconditions:
 * 			please run SQL firstly: setupBondExpiryEmailNotification.sql
 * 
 * 			1. SQL_1: insert into X_BOND_EXPIRY_NOTICE_CONF(ID, NOTICE_NUM, DAYS_TO_EXPIRY, DELETED) Values (get_sequence('X_BOND_EXPIRY_NOTICE_CONF'), 'AUTO-1146', 30, 0); 
 * 			2. SQL_2: INSERT INTO D_CONTACT (ID, type_id, f_name, l_name, m_name, suffix, h_phone, w_phone, m_phone, fax, email, website, primary_ind, phone_cnt_pref, pref_cnt_time)  values(get_sequence('D_CONTACT'), 5104, 'StateF', 'StateL', '', 'Auto', '', '601-432-2060', '',  '', 'AO.QAormstest@activenetwork.com', '',1,0,0); 
 *			3. SQL_3: update d_location_class set bond_required=1 where location_class_name='State Parks Agent'; 
 *				(In this case we use Vender[Name:Auto Vendor  Vender#:Auto555]) 
 *			4. Configure in orms.properties:
 *					orms.bond_expiry_notification.daemon.daily_schedule=12:05 
 *					orms.bond_expiry_notification.daemon.period=30
 *			5. Configure in service.properties:  
 * 					com.reserveamerica.licensing.store.impl.BondExpiryNotificationDaemon=on 
 * 
 * @SPEC: Bond Expiry Email Notification(PCR 2873 )
 * @Task#: AUTO-1146(TC032533,035842)
 * @Other: You can get Daemon log from table <select * from X_BOND_EXPIRY_NOTIFCN_LOG> 
 * @author pzhu
 * @Date  Aug 2, 2012
 */

public class ExpiryEmailNotification extends LicenseManagerTestCase {

	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private VendorBondInfo bondInfo;
	private LicMgrVendorBondsSubPage bondPg = LicMgrVendorBondsSubPage.getInstance();
	private String dateFormat = "EEE MMM d yyyy HH:mm a";
	private String bondPrefix = "ExpiryNotification";
	private String host, username, password, emailSubject, agentName, vendorName, subject, previousBondNum, bondExpiryDate, fName, lName, suffix, workPhone;
	private String[] emailContentCheckPoint = new String[4];
	
	public void execute() {

		switch (branch) {
		case 0:
			//disable all bond created by this case before.
			this.deactivatePreviousActiveBonds(this.bondPrefix,bondInfo.belongVendorNum );
			//check all precondition in DB are ready.
			this.checkAllPreconditionInDB();
			
			lm.loginLicenseManager(login);
			lm.gotoVendorDetailsPgFromVendorsQuickSearch(bondInfo.belongVendorNum);
			lm.gotoBondSubTabFromVendorDetailPg();
			
			//add a new bond
			bondInfo.id = lm.addVendorBond(bondInfo, true);
			logger.info("New created Bond ID is "+bondInfo.id);
			//assign to a agent.
			lm.changeAgentBondAssignment(lm.getAgentIDByNameFromDB(this.schema,this.agentName), bondInfo.bondNum, bondInfo.issuer);
			
			lm.logOutLicenseManager();
			
			putIntermediateData("bondID="+bondInfo.id+";bondNum="+bondInfo.bondNum+";bondExpiryDate="+bondInfo.effectiveTo);
			
			break;
			
		case 1://wait 24hours after step 0, then run this step 1.
			
			bondInfo.id = getIntermediateData("bondID");
			this.previousBondNum = getIntermediateData("bondNum");
			this.bondExpiryDate = getIntermediateData("bondExpiryDate"); 
			
			//check point: check all Email info.
			this.checkBondExpiryEmailNotification();
			
			
			//clear this Bond.
			lm.loginLicenseManager(login);
			lm.gotoVendorDetailsPgFromVendorsQuickSearch(bondInfo.belongVendorNum);
			lm.gotoBondSubTabFromVendorDetailPg();
			bondPg.deactiveBondByID(bondInfo.id);
			lm.logOutLicenseManager();
		
			break;
		default:
			throw new NotSupportedException("Branch#" + branch
					+ " is not supported.");
		}

	}

	/**
	 * 
	 */
	private void checkBondExpiryEmailNotification() {

		logger.info("Checking all information in Bond Expiry Email Notification.");
		Properties props[] = lm.getEmailFromMailBox(host, username, password, emailSubject, 0);
		String emailContent;
		boolean found = false;
		int i=1;
		for (Properties p: props)
		{
			emailContent = p.getProperty("text");
			StringUtil.replaceHtml(emailContent);
			if((emailContent.contains(this.subject))&&(emailContent.contains(this.previousBondNum)))//get Email created in previous step by this case. 
			{
				found = true;
				logger.info("Found Email NO."+i+" content is -->"+emailContent);
				this.checkEmailFrom(emailContent);
				this.checkEmailTo(emailContent);
				this.checkEmailSubject(emailContent);
				this.checkEmailContent(emailContent);
				this.checkMSIcon(emailContent);			
			}
			i++;
		}
		
		if(!found)
		{
			throw new TestCaseFailedException("Cannot find Bond Expiration Notification Email in MailBox->"+host+";" +
					" Email subject should contain->"+this.subject+"; BondNum should be->"+this.previousBondNum);
		}
		
		
		
	}

	/**
	 * 
	 */
	private void checkMSIcon(String email) {
		logger.info("Checking Mississippi icon in the email....");
		String tmp = email.substring(email.indexOf("src="));
		String imgURL = tmp.substring(tmp.indexOf("src=")+"src=".length(), tmp.indexOf(">"));
		//URL Example: "http://web03.qa.reserveamerica.com:5001/marketing/images/confletorms/logo_banner_MS_HF.gif"
		String regEx="\"http://.+:\\d+/marketing/images/confletorms/logo_banner_MS_HF.gif\"";   
        if(!Pattern.matches(regEx, imgURL))
        {
        	throw new  ErrorOnDataException("Checking(regular express check) Mississippi icon URL in the email error, ",regEx,imgURL);
        }
		logger.info("Checking(regular express check) Mississippi icon URL in the email successfully.");
	}

	/**
	 * 
	 */
	private void checkEmailContent(String email) {
		//Example of content:
		//		subject part: 
		//			<label>Subject: <strong>Expiration Notification - 3 Notice</strong></label></td></tr></table></p></div>
		//		
		//		content part:
		//			<div><p>C T BAIT SHOP, your current bond 2233175 is set to expire on Wed Aug 1 2012.	<br>Please contact Wautisha McBounds 0 at 601-432-2060 as soon as possible so that we can update your bond information and avoid inactivation of your License Agent Account.</p></div>
		//			<div><p>Thank you for your immediate attention to this matter.</p></div><div><p>Mississippi Department of Wildlife, Fisheries &amp; Parks</p></div>
		//			<div><p><img border="0" src="http://web03.qa.reserveamerica.com:5001/marketing/images/confletorms/logo_banner_MS_HF.gif"></p></div>
		logger.info("Checking Email content info....");
		String subjectAndContent = email.substring(email.indexOf("Subject:"));
		String onlyContent = StringUtil.replaceHtml(subjectAndContent.substring(subjectAndContent.indexOf("<div><p>")));
		
		this.emailContentCheckPoint[0] = this.vendorName;
		this.emailContentCheckPoint[1] = "your current bond " +
				this.previousBondNum +
				" is set to expire on " +
				DateFunctions.formatDate(this.bondExpiryDate, "MM/dd/yyyy", "EEE MMM dd yyyy"); //"Wed Aug 1 2012";
		this.emailContentCheckPoint[2] = "Please contact " +
				this.fName +
				" " +
				this.lName +
				" " +
				this.suffix +
				" at " +
				this.workPhone +
				" as soon as possible so that we can update your bond information and avoid inactivation of your License Agent Account";
        this.emailContentCheckPoint[3] = "Thank you for your immediate attention to this matter.Mississippi Department of Wildlife, Fisheries &amp; Parks";             
		
        for(String s: this.emailContentCheckPoint)
        {
        	if(!onlyContent.contains(s))
        	{
        		throw new  ErrorOnDataException("Checing Content of Bond Expiration Notification Email error, ",s," !!!We can not find this content in the Content");
        	}
        }
         		
		logger.info("Checking content of Bond Expiration Notification Email succussfully.");
		
		
	}

	/**
	 * 
	 */
	private void checkEmailSubject(String email) {
		logger.info("Checking Email Subject info....");
		String subject = email.substring(email.indexOf("Subject:"));
		
		//Example: <label>Subject: <strong>Expiration Notification - 3 Notice</strong></label>
		subject = subject.substring(subject.indexOf("Subject:")+"Subject:".length(), subject.indexOf("</label>"));
		
		String expected = "Expiration Notification - " +this.subject +" Notice";
		String inEmail = StringUtil.replaceHtml(subject).trim();
		if(!inEmail.equalsIgnoreCase(expected))
		{
			throw new ErrorOnDataException("Subjcet info of Bond Expiration Notification Email error, ",expected,inEmail);
		}
		logger.info("Check Email Subject info successfully.");
		
	}

	/**
	 * 
	 */
	private void checkEmailTo(String email) {
		logger.info("Checking Email To info....");
		String to = email.substring(email.indexOf("To:")+"To:".length(), email.indexOf("From:"));
		String expected = this.vendorName;
		String inEmail = StringUtil.replaceHtml(to).trim();
		if(!inEmail.equalsIgnoreCase(expected))
		{
			throw new ErrorOnDataException("To info of Bond Expiration Notification Email error, ",expected,inEmail);
		}
		logger.info("Check Email To info successfully.");
		
	}

	/**
	 * 
	 */
	private void checkEmailFrom(String email) {
		logger.info("Checking Email From info....");
		String from = email.substring(email.indexOf("From:")+"From:".length(), email.indexOf("Subject:"));
		String expected = login.location.split("/")[1];
		String inEmail = StringUtil.replaceHtml(from).trim();
		if(!inEmail.equalsIgnoreCase(expected))
		{
			throw new ErrorOnDataException("From info of Bond Expiration Notification Email error, ",expected,inEmail);
		}
		logger.info("Check Email From info successfully.");
	}

	/**
	 * 
	 */
	private void checkAllPreconditionInDB() {
		logger.info("Checking all Pre-condition(DB configure) for this cases.");
		
		db.resetSchema(schema);
 		logger.info("Changed schema to -->>"+schema);
 		
 		String sql1 = "select * from X_BOND_EXPIRY_NOTICE_CONF where notice_num = 'AUTO-1146' and days_to_expiry=30 and deleted =0";
 		if(db.executeQuery(sql1).size()<1)
 		{
 			throw new ErrorOnDataException("Cannot find DB record in table 'X_BOND_EXPIRY_NOTICE_CONF', Please execute following SQL--" +
 					"insert into X_BOND_EXPIRY_NOTICE_CONF(ID, NOTICE_NUM, DAYS_TO_EXPIRY, DELETED) Values (get_sequence('X_BOND_EXPIRY_NOTICE_CONF'), 'AUTO-1146', 30, 0)");
 		}
 		
 		String sql2 = "select * from D_CONTACT  where type_id=5104 and email='AO.QAormstest@activenetwork.com'";
 		if(db.executeQuery(sql2).size()<1)
 		{
 			throw new ErrorOnDataException("Cannot find DB record in table 'D_CONTACT', Please execute following SQL--" +
 					"INSERT INTO D_CONTACT (ID, type_id, f_name, l_name, m_name, suffix, h_phone, w_phone, m_phone, fax, email, website, primary_ind, phone_cnt_pref, pref_cnt_time)  values(get_sequence('D_CONTACT'), 5104, 'StateF', 'StateL', '', 'Auto', '', '601-432-2060', '',  '', 'AO.QAormstest@activenetwork.com', '',1,0,0)");
 		}
 		
 		String sql3 = "select * from d_location_class where location_class_name='State Parks Agent' and bond_required = 1";
 		if(db.executeQuery(sql3).size()<1)
 		{
 			throw new ErrorOnDataException("Cannot find DB record in table 'd_location_class', Please execute following SQL--" +
 					"update d_location_class set bond_required=1 where location_class_name='State Parks Agent'");
 		}
 		
 		logger.info("Check DB precondition successfully.");
		
	}

	private void deactivatePreviousActiveBonds(String bondNamePrefix, String vendorNum) {
		logger.info("Deactivate all previous bond records created by this case.");
		
		db.resetSchema(schema);
 		logger.info("Changed schema to -->>"+schema);
 		String sql = "update F_VENDOR_BOND set status = " +
 				"2" +
 				" where bond_num like '%" +
 				bondNamePrefix +
 				"%' and vendor_id in(select id from D_VENDOR where vendor_num='" +
 				vendorNum +
 				"')";
 		int num = db.executeUpdate(sql);
 		logger.info("Total "+num+" previous Bond records are deactivated...");
 				
	}

	
	
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		branch = 0;
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		bondInfo = new VendorBondInfo();
		bondInfo.status = OrmsConstants.ACTIVE_STATUS;
		bondInfo.belongVendorNum = "Auto555";// in location class 'State Parks Agent'
		bondInfo.issuer="Federated Mutual";
		bondInfo.type="Bond";
		bondInfo.bondNum=this.bondPrefix+Integer.toString((int)(Math.random()*10000000))+"_"+Integer.toString((int)(Math.random()*10000000));
		bondInfo.bondAmount="9.99";
		bondInfo.effectiveFrom=DateFunctions.getDateAfterToday(-1);
		bondInfo.effectiveTo=DateFunctions.getDateAfterToday(15);
		bondInfo.creationDateTime = DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)),dateFormat);
		bondInfo.creationUser = "Test-Auto, QA-Auto";
		
		//important info to access mailbox
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		emailSubject = "MDWFP License Agent Bond Expiration";
		
		this.agentName = "WAL-MART";
		this.vendorName = "Auto Vendor";
		this.subject = "AUTO-1146";
		
		//these data are created in precondition: SQL_2
		this.fName = "StateF";
		this.lName = "StateL";
		this.suffix = "Auto";
		this.workPhone = "601-432-2060";

		
		
	}
	
	
	
		
}
