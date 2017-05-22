package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;


import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: This case was verify customer details page for different user permission, test below features under role 'HF HQ Role - Auto'
 * 			ViewCustomerProfile --- view customer profile 
 * 			ViewCustomerProfileMembership --- view customer profile membership
 * 			ModifyCustomerProfile --- modify customer profile 
 * 
 * 			CustomerProfileUnintentionalEditingGuard was defined in X_PROP table
 * 	
 * 			Steps			ViewCustomerProfile			ModifyCustomerProfile			ViewCustomerProfileMembership				CustomerProfileUnintentionalEditingGuard
 * 				1						No						Yes							Yes											False(cust member ship section was existed, not editable
 * 																																							edit btn was not existed)
 * 				2						Yes						No							Yes											True(cust member ship section was existed, not editable
 * 																																							cust profile not editable, Edit btn existed, not enabled)
 * 				3						Yes						Yes(modify cust addr)		No											True(cust member ship section was not existed
 * 																																							edit btn was existed, and enable
 * 																																							click Edit, cust profile not editable except addr)
 * 				4						Yes						Yes(modify cust addr)		No											False(cust member ship section was not existed
 * 																																							edit btn was not existed)
 * 				5						Yes						No							No											False(cust member ship section was not existed
 * 																																							edit btn was not existed
 * 																																							cust profile not editable)			
 * @Preconditions: 
 * @SPEC:  
 * 		Customer Profile Management UI - PCR 3079 Unintentional Editing [TC:041915] 
 * @Task#: Auto-1455
 * @author Jane
 * @Date  Feb 27, 2013
 */
public class VerifyCustDetailPgByDiffPermission extends LicenseManagerTestCase {

	private String roleName="HF HQ Role - Auto";
	private String viewCustProfileFeat="ViewCustomerProfile";
	private String modifyCustProfile="ModifyCustomerProfile";
	private String modifyCustAddr ="ModifyCustomerProfileAddress";
	private String viewCustMembership="ViewCustomerProfileMembership";
	
	private final String triggerName="CustomerProfileUnintentionalEditingGuard";
	private String appName, appID, roleID;
	private LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage.getInstance();
	
	public void execute() {
		String exception="";
		try {
			//Step1:
			setupViewEditTriggerFromDB(schema, triggerName, "Contract", "Boolean", false);
			assignOrUnassignFeature(schema, roleName, viewCustProfileFeat, appName, appID, false);
			assignOrUnassignFeatures(schema, roleName, modifyCustProfile, "LicenseManager", appID, true);
			assignOrUnassignFeature(schema, roleName, viewCustMembership, appName, appID, true);
			Browser.sleep(30);
			lm.loginLicenseManager(login);
			lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, cust.identifier.identifierType, cust.identifier.identifierNum);
			custDetailPg.verifyMemberShipSectionExisted(true);
			custDetailPg.verifyCustMembershipNotEditable();
			custDetailPg.verifyEditButtonExisted(false);
			lm.logOutLicenseManager();
			
			//Step2:
			setupViewEditTriggerFromDB(schema, triggerName, "Contract", "Boolean", true);
			assignOrUnassignFeature(schema, roleName, viewCustProfileFeat, appName, appID, true);
			assignOrUnassignFeatures(schema, roleName, modifyCustProfile, "LicenseManager", appID, false);
			assignOrUnassignFeature(schema, roleName, viewCustMembership, appName, appID, true);
			Browser.sleep(100);
			lm.loginLicenseManager(login);
			lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, cust.identifier.identifierType, cust.identifier.identifierNum);
			custDetailPg.verifyMemberShipSectionExisted(true);
			custDetailPg.verifyCustMembershipNotEditable();
			custDetailPg.verifyCustProfileNotEditable();
			custDetailPg.verifyEditButtonExisted(true);
			custDetailPg.verifyEditButtonEnabled(false);
			lm.logOutLicenseManager();
			
			//Step3:
			assignOrUnassignFeature(schema, roleName, viewCustMembership, appName, appID, false);
			assignOrUnassignFeature(schema, roleName, modifyCustAddr, appName, appID, true);
			Browser.sleep(30);
			lm.loginLicenseManager(login);
			lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, cust.identifier.identifierType, cust.identifier.identifierNum);
			custDetailPg.verifyMemberShipSectionExisted(false);
			custDetailPg.verifyEditButtonExisted(true);
			custDetailPg.verifyEditButtonEnabled(true);
			custDetailPg.editCustInfoByClickEditBtn();
			custDetailPg.verifyCustProfileNotEditableExpectAddress();
			lm.logOutLicenseManager();
			
			//Step4:
			setupViewEditTriggerFromDB(schema, triggerName, "Contract", "Boolean", false);
			Browser.sleep(150);
			lm.loginLicenseManager(login);
			lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, cust.identifier.identifierType, cust.identifier.identifierNum);
			custDetailPg.verifyMemberShipSectionExisted(false);
			custDetailPg.verifyEditButtonExisted(false);
			lm.logOutLicenseManager();
			
			//Step5:
			assignOrUnassignFeature(schema, roleName, modifyCustAddr, appName, appID, false);
			Browser.sleep(30);
			lm.loginLicenseManager(login);
			lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, cust.identifier.identifierType, cust.identifier.identifierNum);
			custDetailPg.verifyMemberShipSectionExisted(false);
			custDetailPg.verifyEditButtonExisted(false);
			custDetailPg.verifyCustProfileNotEditable();
			lm.logOutLicenseManager();
		} catch (Exception e) {
			exception += e;
			e.printStackTrace();
		} finally {
			logger.info("Set role "+roleName+" with all "+modifyCustProfile+" feature.");
			assignOrUnassignFeatures(schema, roleName, modifyCustProfile, "LicenseManager", appID, true);
			setupViewEditTriggerFromDB(schema, triggerName, "Contract", "Boolean", false);
		}
		
		if(!StringUtil.isEmpty(exception))
			throw new TestCaseFailedException("Test Case failed. Please check log file.");
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		cust.customerClass = "Individual";
		cust.lName = "TEST-Verify";
		cust.fName = "QA-Tan";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		appID="19";
		appName="LicenseManager";
	}

	/**
	 * Assign or unassign feature to role
	 * @param schema
	 * @param roleName --- role Name
	 * @param feature --- feature Name
	 * @param appName --- app name(for assign feature to role)
	 * @param appID 
	 * @param isassigned
	 */
	private void assignOrUnassignFeature(String schema, String roleName, String feature, String appName, String appID, boolean isassigned) {
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		roleID=getRoleIDByRoleName(schema, roleName);
		String featID=getFeatIDByFeatName(schema, feature, appID);
		String query="select * from X_ROLE_FEAT " +
				"inner join X_ROLE on X_ROLE_FEAT.role_id=X_ROLE.id and X_ROLE.id='"+roleID+"' " +
				"inner join X_FEAT on X_ROLE_FEAT.feat_id=X_FEAT.feat_id and X_FEAT.feat_name='"+feature+"'";
		String[] cols=new String[]{"role_id", "feat_id"};
		List<String[]> results=db.executeQuery(query, cols);
		String update = "";
		if(isassigned) {
			if(results==null || results.size()<1){
				logger.info("Assign feature "+feature+" to Role "+roleName+" from DB");
				update="insert into X_ROLE_FEAT values ('"+roleID+"', '"+featID+"','"+appName+"."+feature+"')";
				db.executeUpdate(update);
			}else {
				logger.info("Feature "+feature+" has been already assigned to Role "+roleName);
			}
		}else {
			if(results==null || results.size()<1){
				logger.info("Feature "+feature+" has not been assigned to Role "+roleName);
			}else {
				logger.info("Unassign feature "+feature+" to Role "+roleName+" from DB");
				update="delete from X_ROLE_FEAT where role_id='"+results.get(0)[0]+"' and feat_id='"+results.get(0)[1]+"'";
				db.executeUpdate(update);
			}
		}
	}
	
	/**
	 * Assign or unassign features to role
	 * @param schema
	 * @param roleName --- role Name
	 * @param feature --- feature Name(will be used as %feature%)
	 * @param appName --- app name(for assign feature to role)
	 * @param appID
	 * @param isassigned
	 */
	private void assignOrUnassignFeatures(String schema, String roleName, String feature, String appName, String appID, boolean isassigned) {
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		roleID=getRoleIDByRoleName(schema, roleName);
		String query="select * from X_ROLE_FEAT " +
				"inner join X_ROLE on X_ROLE_FEAT.role_id=X_ROLE.id and X_ROLE.id='"+roleID+"' " +
				"inner join X_FEAT on X_ROLE_FEAT.feat_id=X_FEAT.feat_id and X_FEAT.feat_name like '%"+feature+"%' and X_FEAT.app_id='"+appID+"'";
		String[] cols=new String[]{"role_id", "feat_id", "feat_name"};
		List<String[]> results=db.executeQuery(query, cols);
		String update = "";
		if(isassigned) {
			if(results==null || results.size()<1){
				logger.info("Assign all features %"+feature+"% to Role "+roleName+" from DB");
				query="select * from X_FEAT where feat_name like '%"+feature+"%' and app_id='"+appID+"'";
				String[] cols1=new String[]{"feat_id", "feat_name"};
				List<String[]> featList=db.executeQuery(query, cols1);
				for(int i=0;i<featList.size();i++) {
					update="insert into X_ROLE_FEAT values ('"+roleID+"', '"+featList.get(i)[0]+"','"+appName+"."+featList.get(i)[1]+"')";
					db.executeUpdate(update);
				}
			}else {
				query="select * from X_FEAT where feat_name like '%"+feature+"%' and app_id='"+appID+"'";
				List<String> featList=db.executeQuery(query, "feat_id");
				if(featList.size()!=results.size()){
					logger.info("Some feature were not assigned to Role "+roleName);
					for(int i=0;i<results.size();i++) {
						String featAssigned=results.get(i)[1];
						if(featList.contains(featAssigned))
							featList.remove(featAssigned);
					}
					query="select * from X_FEAT where feat_id in ("+listToString(featList, true, ",")+")";
					String[] cols1=new String[]{"feat_id", "feat_name"};
					List<String[]> assignList=db.executeQuery(query, cols1);
					for(int i=0;i<assignList.size();i++) {
						update="insert into X_ROLE_FEAT values ('"+roleID+"', '"+assignList.get(i)[0]+"','"+appName+"."+assignList.get(i)[1]+"')";
						db.executeUpdate(update);
					}
				}
				logger.info("Features %"+feature+"% has been already assigned to Role "+roleName);
			}
		}else {
			if(results==null || results.size()<1){
				logger.info("Features %"+feature+"% has not been assigned to Role "+roleName);
			}else {
				logger.info("Unassign features %"+feature+"% to Role "+roleName+" from DB");
				for(int i=0;i<results.size();i++) {
					update="delete from X_ROLE_FEAT where role_id='"+roleID+"' and feat_id='"+results.get(i)[1]+"'";
					db.executeUpdate(update);
				}
			}
		}
	}
	
	private void setupViewEditTriggerFromDB(String schema, String name, String namespace, String type, boolean setup) {
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String query="select * from X_PROP where name='"+name+"' and namespace='"+namespace+"'";
		String[] cols=new String[]{"id", "value"};
		List<String[]> results=db.executeQuery(query, cols);
		String update = "";
		if(setup) {
			if(results==null || results.size()<1) {
				update="insert into X_PROP values(GET_SEQUENCE('X_PROP'), '"+name+"','"+namespace+"','"+type+"','"+setup+"')";
				db.executeUpdate(update);
			}else {
				String value=results.get(0)[1];
				if(!value.equals(String.valueOf(setup))) {
					update="update X_PROP set value='"+setup+"' where id="+results.get(0)[0];
					db.executeUpdate(update);
				}
				logger.info("Trigger "+name+" has been already setup on "+namespace+" level as "+setup);
			}
		}else {
			if(results==null || results.size()<1) {
				logger.info("Trigger "+name+" has been already setup on "+namespace+" level as "+setup);
			}else {
				update="update X_PROP set value='"+setup+"' where id="+results.get(0)[0];
				db.executeUpdate(update);
			}
		}
	}
	
	private String getRoleIDByRoleName(String schema, String roleName) {
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String query="select * from X_ROLE where name='"+roleName+"' ";
		List<String> tmp=db.executeQuery(query, "ID");
		if(tmp==null || tmp.size()<1)
			throw new ItemNotFoundException("Could not get role id by role name "+roleName);
		return tmp.get(0);
	}
	
	private String getFeatIDByFeatName(String schema, String feature, String app) {
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String query="select * from X_FEAT where feat_name='"+feature+"' and app_id='"+app+"'";
		List<String> tmp=db.executeQuery(query, "feat_id");
		if(tmp==null || tmp.size()<1)
			throw new ItemNotFoundException("Could not get feature id by feature name "+feature);
		return tmp.get(0);
	}
	
	private String listToString(List<String> texts, boolean withSingleQuote, String delimit) {
		StringBuffer text=new StringBuffer();
		
		for(int i=0;i<texts.size();i++) {
			if(withSingleQuote) {
				text.append("'");
			}
			text.append(texts.get(i));
			
			if(withSingleQuote) {
				text.append("'");
			}
			if(i<texts.size()-1) {
				text.append(delimit);
			}
		}
		return text.toString();
	
	}
}
