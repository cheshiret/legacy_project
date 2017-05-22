package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 28, 2012
 */
public class DatePeriodLicenseYearInfo {
	
	private String id;
	private String status;
	private String licenseYear;
	private List<Dates> dates = new ArrayList<DatePeriodLicenseYearInfo.Dates>();
	private String creationDateTime;
	private String creationUser;
	private String lastModifiedDateTime;
	private String lastModifiedUser;
	
	public class Dates {
		private String fromDate;
		private String toDate;
		private String category;
		public Dates(){}
		public Dates(String from, String to, String category){
			this.fromDate = from;
			this.toDate = to;
			this.category = category;
		}
		
		@Override
		public boolean equals(Object object) {
			AutomationLogger logger = AutomationLogger.getInstance();
			
			boolean result = true;
			if(!(object instanceof Dates)) {
				result &= false;
				logger.error("The Class is not " + Dates.class.getSimpleName());
			}
			
			Dates d = (Dates)object;
			result &= MiscFunctions.compareResult("Category", this.getCategory(), d.getCategory());
			result &= MiscFunctions.compareResult("From Date", this.getFromDate(), d.getFromDate());
			result &= MiscFunctions.compareResult("To Date", this.getToDate(), d.getToDate());
			
			return result;
		}
		
		public String getFromDate() {
			return fromDate;
		}
		public void setFromDate(String fromDate) {
			this.fromDate = fromDate;
		}
		public String getToDate() {
			return toDate;
		}
		public void setToDate(String toDate) {
			this.toDate = toDate;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLicenseYear() {
		return licenseYear;
	}

	public void setLicenseYear(String licenseYear) {
		this.licenseYear = licenseYear;
	}

	public List<Dates> getDates() {
		return dates;
	}

	public void setDates(List<Dates> dates) {
		this.dates = dates;
	}

	public void setDates(Dates... datesInfo) {
		this.dates = new ArrayList<Dates>();
		for (Dates date : datesInfo) {
			this.dates.add(date);
		}
	}
	
	public String getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(String creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public String getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(String lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public String getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}
	
	/** Get all dates info with the format: (Category: Jan 01-Dec 31)( Jan 01)*/
	public String getAllDatesInfo() {
		if (this.dates == null) {
			return "";
		}
		String info = "";
		for (int i = 0; i < this.dates.size(); i++) {
			Dates date = this.dates.get(i);
			info += "(" + (StringUtil.notEmpty(date.getCategory()) ? date.getCategory()+": " : "") + 
					date.getFromDate() + 
					(StringUtil.notEmpty(date.getToDate()) ? "-"+date.getToDate() : "") + ")";
		}
		return info;
	}
}
