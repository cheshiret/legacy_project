package com.activenetwork.qa.awo.datacollection.legacy.orms;


import com.activenetwork.qa.awo.datacollection.legacy.TestData;
import com.activenetwork.qa.awo.util.MiscFunctions;

public class ChangeHistory extends TestData {

	/**product id**/
	public  String prdId="";

	/**product code*/
	public static String code="";

	/**product name*/
	public static String name="";

	/**product status*/
	public static String status="";

	/**change date*/
	public String changeDate="";

	/**this is corresponding to 'object' column */
	public String object="";

	/**type of change*/
	public String action="";

	/** changed feild*/
	public String field="";

	/**the value before change*/
	public String oldValue="";

	/**the value after change*/
	public String newValue="";

	/**the use who do those changes*/
	public String user="";

	/**the location where those changes occur**/
	public String location="";

	/**
	 * Initialize change history info
	 * @param changeDate
	 * @param object
	 * @param action
	 * @param field
	 * @param oldValue
	 * @param newValue
	 * @param user
	 * @param location
	 */
	public void setChangeHistoryInfo(String changeDate, String object, String action, String field, String oldValue,
			String newValue, String user, String location){
		this.changeDate = changeDate;
		this.object = object;
		this.action = action;
		this.field = field;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.user = user;
		this.location = location;
	}

	public boolean equals(Object obj){
//		RALogger logger = RALogger.getInstance();
		boolean result = true;

		if(!(obj instanceof ChangeHistory)){
			result &= false;
		}
		ChangeHistory history=(ChangeHistory)obj;
				
		result &= MiscFunctions.compareResult("History Date", history.changeDate, this.changeDate);
		result &= MiscFunctions.compareResult("History Object", history.object.toUpperCase(), this.object.toUpperCase());
		result &= MiscFunctions.compareResult("History Action", history.action.toUpperCase(), this.action.toUpperCase());
		result &= MiscFunctions.compareResult("History Field", history.field.trim().toUpperCase(), this.field.trim().toUpperCase());
		result &= MiscFunctions.compareResult("History Old Value", history.oldValue.trim().toUpperCase(), this.oldValue.trim().toUpperCase());
		result &= MiscFunctions.compareResult("History New Value", history.newValue.trim().toUpperCase(), this.newValue.trim().toUpperCase());
		result &= MiscFunctions.compareResult("History User", history.user.replaceAll(",\\s*", ",").toUpperCase(), this.user.replaceAll(",\\s*", ",").toUpperCase());
		result &= MiscFunctions.compareResult("History Location", history.location.toUpperCase(), this.location.toUpperCase());
		return result;	
	}
	public boolean equalsWithOutDate(Object obj){
		boolean resule = true;

		if(!(obj instanceof ChangeHistory)){
			resule &= false;
		}
		ChangeHistory history=(ChangeHistory)obj;
					
		resule &= MiscFunctions.compareResult("History Object", history.object.toUpperCase(), this.object.toUpperCase());
		resule &= MiscFunctions.compareResult("History Action", history.action.toUpperCase(), this.action.toUpperCase());
		resule &= MiscFunctions.compareResult("History Field", history.field.trim().toUpperCase(), this.field.trim().toUpperCase());
		resule &= MiscFunctions.compareResult("History Old Value", history.oldValue.trim().toUpperCase(), this.oldValue.trim().toUpperCase());
		resule &= MiscFunctions.compareResult("History New Value", history.newValue.trim().toUpperCase(), this.newValue.trim().toUpperCase());
		resule &= MiscFunctions.compareResult("History User", history.user.replace(", ", ",").toUpperCase(), this.user.replace(", ", ",").toUpperCase());
		resule &= MiscFunctions.compareResult("History Location", history.location.toUpperCase(), this.location.toUpperCase());
		return resule;	
	}
}

