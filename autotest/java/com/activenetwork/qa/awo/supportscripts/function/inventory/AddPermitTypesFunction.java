package com.activenetwork.qa.awo.supportscripts.function.inventory;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeDetailInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeDetailInfo.AttributeInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date Nov 22, 2012
 */
public class AddPermitTypesFunction extends FunctionCase {

	private String facilityName;
	private PermitTypeDetailInfo permitDetailInfo = new PermitTypeDetailInfo();
	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo loginInfo = new LoginInfo();
	private boolean loggedin=false;
	private String contract = "";
	private InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();
	AwoDatabase connector = AwoDatabase.getInstance();
	String schema,permitTypeCode = "";

	@Override
	public void execute() {
		if (!loginInfo.contract.equals(contract) && loggedin && isBrowserOpened) {
			im.switchToContract(loginInfo.contract, loginInfo.location);
		} 
		if (!loggedin || !isBrowserOpened) { // Logged in
			im.loginInventoryManager(loginInfo);
			loggedin = true;
		}
		if (!invHmPg.exists()) {
			im.gotoInventoryHomePg();
		}
		contract = loginInfo.contract;
		
		insertPermitTypeNameIntoDB(loginInfo);
		if(!checkPermitTypeExist(facilityName)){
			im.gotoFacilityDetailsPg(facilityName);
			im.gotoEntranceListPage("Entrance Set-up");
			im.gotoPermitTypesListPage();
			String permitTypeID = im.addNewPermitType(permitDetailInfo);
			if (!permitTypeID.matches("\\d+")) {
				throw new ErrorOnPageException("Create Permit Type Fail.");
			} else {
				logger.info("Create Permit Type " + permitTypeID);
				newAddValue = permitTypeID;
			}
		}else{
			logger.info("Permit Type Code "+permitDetailInfo.permitTypeCode+" exists");
		}
		
	
	}
	
	private boolean checkPermitTypeExist(String location){
		connector.resetSchema(schema);
		
		String query = "select count(*) qty from P_PERMIT_TYPE where code='"+permitTypeCode+"' and loc_id in(select id from d_loc where upper(name) like upper('"+location+"%') and level_num=40) and active_ind=1";
		String qty = connector.executeQuery(query, "qty", 0);
		return Integer.parseInt(qty)>0;
	}

	@Override
	public void wrapParameters(Object[] param) {
		loginInfo = (LoginInfo)param[0];
		permitDetailInfo = (PermitTypeDetailInfo)param[1];
		facilityName=(String)param[2];
		permitTypeCode=(String)param[3];
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contractAbbr(loginInfo);
	}
	
	private void insertPermitTypeNameIntoDB(LoginInfo loginInfo) {
		connector.resetSchema(schema);

		String permitTypeName = getPermitTypeName();

		List<String> result = connector.executeQuery("SELECT NAME FROM P_PERMIT_TYPE_NAME WHERE NAME='" + permitTypeName + "'", "name");
		if (result.isEmpty()) {
			logger.info("Insert new permitTypeName=" + permitTypeName + " into DB");
			connector.executeUpdate("INSERT INTO P_PERMIT_TYPE_NAME VALUES (get_sequence('P_PERMIT_TYPE_NAME'), '" + permitTypeName + "')");
		} else {
			logger.warn("There is already an existing permitType name=" + permitTypeName);
		}
	}
	
	private String getPermitTypeName() {
		return permitDetailInfo.getAttributeInfo(permitDetailInfo.permitTypeName);
	}

	private String contractAbbr(LoginInfo loginInfo) {
		String contract = loginInfo.contract;
		return contract.replaceAll("\\s+.*", "").trim();
	}

	public void putDataToArray(PermitTypeDetailInfo permitDetailInfo,
			String name, String value) {
		PermitTypeDetailInfo.AttributeInfo attr;
		int len = 0;
		if (value.contains("{")) // multi array record.//Example: {[A
									// Guide][All][All]},{[A Guide][All][All]}
		{
			value = value.replace(",{", "");
			value = value.replace("{", "");
			String[] tmp = value.split("}");
			int arrayLen = tmp.length;
			for (int i = 0; i < arrayLen; i++) {
				String tmp_value = tmp[i];
				tmp_value = tmp_value.replace(",[", "");
				tmp_value = tmp_value.replace("[", "");
				String[] tmp2 = tmp_value.split("]");
				len = tmp2.length;
				attr = new AttributeInfo();
				attr.attrname = name;
				attr.attr = new String[len];
				for (int m = 0; m < len; m++) {
					attr.attr[m] = tmp2[m];
				}
				permitDetailInfo.attrArray.add(attr);

			}
			return;
		}
		if (value.contains("[")) // one array record with string[] //Example:
									// [Overnight Quota][1][Per Permit][On
									// Entry]
		{
			value = value.replace(",[", "");
			value = value.replace("[", "");
			String[] tmp1 = value.split("]");
			len = tmp1.length;
			attr = new AttributeInfo();
			attr.attrname = name;
			attr.attr = new String[len];
			for (int j = 0; j < len; j++) {
				attr.attr[j] = tmp1[j];
			}
			permitDetailInfo.attrArray.add(attr);
			return;
		}


		// this is for single string. //Example: Optional
		if (StringUtil.notEmpty(value)) {
			attr = new AttributeInfo();
			attr.attrname = name;
			attr.attr = new String[1];
			attr.attr[0] = value;
			permitDetailInfo.attrArray.add(attr);
		}

		return;
	}
	
	public static String arrayToString(String[] a, String separator) {
		if (a == null) {
			return "";
		}
		String result = "";
		if (a.length > 0) {
			result = a[0]; // start with the first element
			for (int i = 1; i < a.length; i++) {
				result = result + separator + a[i];
			}
		}
		return result;
	}
}
