package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeDetailInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeDetailInfo.AttributeInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddPermitTypesFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 *
 * @author pzhu
 * @Date March 14, 2012
 */

public class AddPermitTypes extends SetupCase {
	private PermitTypeDetailInfo permitDetailInfo;
	private static AutomationLogger logger = AutomationLogger.getInstance();
	private LoginInfo loginInfo = new LoginInfo();
	private String facilityName,permitTypeCode;
	private AddPermitTypesFunction addPermitTypes = new AddPermitTypesFunction();
	@Override
	public void readDataFromDatabase() {
		loginInfo.contract = datasFromDB.get("contract");
		loginInfo.location = datasFromDB.get("location");
		permitTypeCode = datasFromDB.get("permittypecode");
		facilityName = datasFromDB.get("facilityname");
		permitDetailInfo = new PermitTypeDetailInfo();
		this.getData(PermitTypeDetailInfo.class, permitDetailInfo);
		logger.info("Print start---->>information of permitDetailInfo");
		for (PermitTypeDetailInfo.AttributeInfo a : permitDetailInfo.attrArray) {
			logger.info(a.attrname + ": " + "["
					+ arrayToString(a.attr, "][") + "]");

		}
		logger.info("Print end----<<information of permitDetailInfo");
	}

	@Override
	public void executeSetup() {
		addPermitTypes.execute(loginInfo, permitDetailInfo,facilityName,permitTypeCode);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_add_permit_types";
		queryDataSql = "";
		
		String env = TestProperty.getProperty("target_env");
		loginInfo.url = AwoUtil.getOrmsURL(env);
		loginInfo.userName = TestProperty.getProperty("orms.im.user");
		loginInfo.password = TestProperty.getProperty("orms.im.pw");
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

	public <T> T getData(Class<T> permitTypeObj,
			PermitTypeDetailInfo permitDetailInfo) {
		Object value = null;
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
						String valueName = f.getName();
						value = datasFromDB.get(valueName);

						if (value != null) {
							this.putDataToArray(permitDetailInfo, (String) f.get(instance),
									(String) value);
							// get value and put into permitDetailInfo
//							 f.set(instance, value);
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
