/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @ScriptName Education.java
 * @Date:Feb 12, 2011
 * @Description:this is create for customer education in license manager
 * @author asun
 */
public class Education implements Comparable<Education> {

	public String id="";

	public String status="";

	public String educationType="";

	public String educationNum="";

	public String educationDes=""; // Education Description for web
	
	public String state="";

	public String country="";

	public String createApp = "";

	public String createDate = "";

	public String createUser = "";

	@Override
	public int compareTo(Education o) {
		Education education=(Education)o;
		return this.educationNum.compareTo(education.educationNum);
	}

	@Override
	public int hashCode() {
		return this.educationNum.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = true;
		
		if(!(obj instanceof Education)){
			result &= false;
		}

		Education edu=(Education)obj;

		result &= MiscFunctions.compareResult("Education ID", this.id, edu.id);
		result &= MiscFunctions.compareResult("Education Status", this.status, edu.status);
		result &= MiscFunctions.compareResult("Education Type", this.educationType, edu.educationType);
		result &= MiscFunctions.compareResult("Education Num", this.educationNum, edu.educationNum);
		result &= MiscFunctions.compareResult("Education State", this.state, edu.state);
		result &= MiscFunctions.compareResult("Education Country", this.country, edu.country);
		result &= MiscFunctions.compareResult("Education Create Application", this.createApp, edu.createApp);
		result &= MiscFunctions.compareResult("Education Create User", this.createUser.replaceAll(" ", StringUtil.EMPTY), edu.createUser.replaceAll(" ", StringUtil.EMPTY));

		return result;
	}
}
