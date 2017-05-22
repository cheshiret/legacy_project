/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @ScriptName LicenseYear.java
 * @Date:Mar 8, 2011
 * @Description:
 * @author asun
 */
public class LicenseYear {
	
	public String productCode = "";
	
	public String productName = "";
	
	public String id="";
	
	public String status="";
	
	public String locationClass="";
	
	public String licYear="";
	
	public String copyFromYear = "";
	
	public String newLicYear = "";
	
	public String numOfYearToAdd = "";
	
	public String sellFromDate="";
	
	public String sellFromTime="";
	
	public String sellToDate="";
	
	public String sellToTime="";
	
	public String validFromDate="";
	
	public String validFromTime="";
	
	public String validToDate="";
	
	public String validToTime="";
	
	public String sellFromAmPm = "";
	
	public String sellToAmPm = "";

	public String validFromAmPm = "";
	
	public String validToAmPm = "";
	
	public String createUser= "";
	
	public String createLocation = "";
	
	public String createTime = "";
	
	public String lastUpdatedUser = "";
	
	public String lastUpdatedLocation = "";
	
	public String lastUpdatedTime = "";
	
	public String prdCategory = ""; // For hunt license year
	
	public String assignedProd = ""; // For hunt license year
	
	public boolean equals(Object obj){
		if(!(obj instanceof Education)){
			return false;
		}

		LicenseYear ly = (LicenseYear)obj;
		
		boolean flag = true;
		if(""!=sellFromDate.trim() && sellFromDate.trim().length()>0){
			flag &= sellFromDate.trim().equals(ly.sellFromDate);
		}
		if(""!=sellFromTime.trim() && sellFromTime.trim().length()>0){
			flag &= sellFromTime.trim().equals(ly.sellFromTime);
		}
		if(""!=sellFromAmPm.trim() && sellFromAmPm.trim().length()>0){
			flag &= sellFromAmPm.trim().equals(ly.sellFromAmPm);
		}
		if(""!=sellFromAmPm.trim() && sellFromAmPm.trim().length()>0){
			flag &= sellFromAmPm.trim().equals(ly.sellFromAmPm);
		}
		if(""!=sellToDate.trim() && sellToDate.trim().length()>0){
			flag &= sellToDate.trim().equals(ly.sellToDate);
		}
		if(""!=sellToTime.trim() && sellToTime.trim().length()>0){
			flag &= sellToTime.trim().equals(ly.sellToTime);
		}
		if(""!=sellToAmPm.trim() && sellToAmPm.trim().length()>0){
			flag &= sellToAmPm.trim().equals(ly.sellToAmPm);
		}
		if(""!=validFromDate.trim() && validFromDate.trim().length()>0){
			flag &= validFromDate.trim().equals(ly.validFromDate);
		}
		if(""!=validFromTime.trim() && validFromTime.trim().length()>0){
			flag &= validFromTime.trim().equals(ly.validFromTime);
		}
		if(""!=validFromAmPm.trim() && validFromAmPm.trim().length()>0){
			flag &= validFromAmPm.trim().equals(ly.validFromAmPm);
		}
		if(""!=validToDate.trim() && validToDate.trim().length()>0){
			flag &= validToDate.trim().equals(ly.validToDate);
		}
		if(""!=validToTime.trim() && validToTime.trim().length()>0){
			flag &= validToTime.trim().equals(ly.validToTime);
		}
		if(""!=validToAmPm.trim() && validToAmPm.trim().length()>0){
			flag &= validToAmPm.trim().equals(ly.validToAmPm);
		}
		
		return flag;
	}
	
	public void calculateSellFromAndToDates(boolean isEndingAsEffective, String startEndDates[], String currentYearSellFromDate) {
		String startingYear = isEndingAsEffective ? String.valueOf(Integer.parseInt(this.licYear) - 1) : this.licYear;
		String endingYear = isEndingAsEffective ? this.licYear : String.valueOf(Integer.parseInt(this.licYear) + 1);
			
		if(StringUtil.notEmpty(currentYearSellFromDate) && Integer.parseInt(startingYear) <= DateFunctions.getCurrentYear()) {
			this.sellFromDate = currentYearSellFromDate;
		} else {
			this.sellFromDate =  startEndDates[0] + "/" + startingYear;
		}
		this.sellToDate = startEndDates[1] + "/" + endingYear;
	}
}
