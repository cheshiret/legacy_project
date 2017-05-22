package com.activenetwork.qa.awo.supportscripts.support.inventorymgr;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeDetailInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeDetailInfo.AttributeInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityPermitType.InvMgrPermitTypeListPage;
import com.activenetwork.qa.awo.supportscripts.AbstractSingleDatapoolSupportCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 *
 * @author pzhu
 * @Date March 14, 2012
 */

public class AddPermitTypes extends AbstractSingleDatapoolSupportCase {
	private String facilityName;
	private PermitTypeDetailInfo permitDetailInfo;
	private String previousContract;
	private boolean loggedIn;
	private InventoryManager im = InventoryManager.getInstance();
	private InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
	private InvMgrHomePage homePage = InvMgrHomePage.getInstance();
	private static AutomationLogger logger = AutomationLogger.getInstance();
	private InvMgrPermitTypeListPage permitTypeListPg = InvMgrPermitTypeListPage.getInstance();

	@Override
	protected void initRange() {
		// 4-5 16-20
		startpoint = 19;
		endpoint = 19;

		logMsg = new String[] {"ID", "Contracts", "Result" };
	}

	@Override
	protected void readDataPool(IDatapoolIterator dpIter) {
		facilityName = dpIter.dpString("facilityName");
		permitDetailInfo = new PermitTypeDetailInfo();
		this.getData(PermitTypeDetailInfo.class, dpIter, permitDetailInfo);

		logger.info("Print start---->>information of permitDetailInfo");
		for (AttributeInfo a : permitDetailInfo.attrArray) {
			logger.info(a.attrname + ": " + "["
					+ arrayToString(a.attr, "][") + "]");

		}
		logger.info("Print end----<<information of permitDetailInfo");

	}

	@Override
	public void execute(Orms orms) {


		try {

			LoginInfo loginInfo = orms.loginInfo;

			if (!loginInfo.contract.equals(previousContract) && loggedIn) {
				im.logoutInvManager();
				loggedIn = false;
			}

			if (!loggedIn) { // Logged in
				im = orms.loginInventoryManager();
				previousContract = loginInfo.contract;
				loggedIn = true;

			}

			insertPermitTypeNameIntoDB(loginInfo);

			if (!homePage.exists()) {
				topMenu.clickHome();
				homePage.waitLoading();
			}

			if(!permitTypeListPg.exists())  //if the page still stay in Permit Type list page, add directly.
			{
				im.gotoFacilityDetailsPg(facilityName);
				im.gotoEntranceListPage("Entrance Set-up");
				im.gotoPermitTypesListPage();
			}

			String permitTypeID  = im.addNewPermitType(permitDetailInfo);

			/*
			 * //now the new permit type has already been changed to YES state.
			 * if(!permitListPg.checkPermitTypeStatus(permitTypeID, "Yes",
			 * "NRRS")) { throw new ErrorOnDataException("permitTypeID "+
			 * permitTypeID+" status(Active) is not 'Yes'"); }
			 */
			setResult("Success, permit type ID is "+permitTypeID);
		} catch (Exception e) {
			logMsg[1] = permitDetailInfo.getAttributeInfo("Permit Type Code");
			setResult("Create the new permit type failed. Error -- "
					+ e.getMessage());

			logger.error(e);
			// Plz comment out this line when you debugging
			//browser.closeAllBrowsers();
			loggedIn = false;

			throw new RuntimeException(e);
		}
	}

	private void insertPermitTypeNameIntoDB(LoginInfo loginInfo) {
		AwoDatabase connector = AwoDatabase.getInstance();
		String schema = TestProperty.getProperty(env + ".db.schema.prefix") + contractAbbr(loginInfo);
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

	@SuppressWarnings({ "unchecked", "rawtypes"})
	public <T> T getData(Class<T> permitTypeObj, IDatapoolIterator dpIter,
			PermitTypeDetailInfo permitDetailInfo) {

		Object instance = null;
		try {
			instance = permitTypeObj.newInstance();
			Class clz = permitTypeObj;

			Field[] fs = clz.getFields();
			for (Field f : fs) {
				int mod = f.getModifiers();
				// Non static or final field
				if (!Modifier.isStatic(mod) && !Modifier.isFinal(mod)) {
					Class fieldType = f.getType();
					if (String.class.isAssignableFrom(fieldType)) {
						String valueName = (String) f.get(instance);
						Method dpMethod = null;
						Object value = null;
						try {
							dpMethod = dpIter.getClass().getMethod(
									"dp" + "String", String.class);
							value = dpMethod.invoke(dpIter, f.getName());

						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}

						if (value != null) {
							this.putDataToArray(permitDetailInfo, valueName,
									(String) value);
							// get value and put into permitDetailInfo
							// f.set(instance, value);
						}
					}
				}
			}

		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return (T) instance;
	}

	public void putDataToArray(PermitTypeDetailInfo permitDetailInfo,
			String name, String value) {
		AttributeInfo attr;
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
