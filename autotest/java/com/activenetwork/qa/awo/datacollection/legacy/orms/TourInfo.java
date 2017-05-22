/*
 * Created on Mar 5, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author QA
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TourInfo {
	public boolean isComboTour;

	public String tourCode = "";

	public String tourName = "";

	public String tourType = "";

	public String ticketTourName = "";

	public String description = "";

	public String count = "";

	public String longDescription = "";

	public boolean isSoldIndividual = false;

	public boolean availableSale = false;

	public String capacity = "";

	public String isActive = "";

	public String isAssigned = "";

	public String facilityName = "";

	public String duration = "";

	public String sortOrder = "";

	public String numOfSteps = "";

	public String distance = "";

	public String tourFeeClass = "";

	public String ticketCategory = "";

	public String rateType = "";

	public String soldIndividual = "";

	public String timeEntry = "Yes";

	public String timeConflict = "No";

	public String limitedCapacity = "Yes";
	
	public String multiDay = "No";
	
	public String validDays = "";
	
	public String entryDays = "";

	public String accessible = "";

	public String accessibility = "";

	public String amenities = "";

	public String directions = "";

	public String information = "";

	public String physicalEffort = "";

	public String days_hoursDescription = "";

	public Map<String, Boolean> daysOfWeek = new HashMap<String, Boolean>();

	public String tourimage = "";

	public String tourUrl = "";

	public String individualTicketTypes[];

	public String organizationTicketTypes[];

	public String isPreventTicketPrinting = "No"; 
	
	public String subTourCodes[]=new String[]{};//this is created for combotour
	
	public List<TicketType> minIndividualTickets;
	public List<TicketType> maxIndividualTickets;
	public List<TicketType> minOrgTickets;
	public List<TicketType> maxOrgTickets;
	
	public TourInfo() {
		daysOfWeek.put("Mon", false);
		daysOfWeek.put("Tue", false);
		daysOfWeek.put("Wed", false);
		daysOfWeek.put("Thu", false);
		daysOfWeek.put("Fri", false);
		daysOfWeek.put("Sat", false);
		daysOfWeek.put("Sun", false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean flag = false;

		if (!(obj instanceof TourInfo) || obj == null) {
			return flag;
		}
		TourInfo tour = (TourInfo) obj;

		if (!(this.tourCode.equals(tour.tourCode))) {
			return flag;
		}

		if (!(this.tourCode.equals(tour.tourCode))) {
			return flag;
		}

		if (!(this.ticketTourName.equals(tour.ticketTourName))) {
			return flag;
		}

		if (!(this.tourType.equals(tour.tourType))) {
			return flag;
		}

		if (!(this.tourFeeClass.equals(tour.tourFeeClass))) {
			return flag;
		}

		if (!(this.description.equals(tour.description))) {
			return flag;
		}

		if (!(this.ticketCategory.equals(tour.ticketCategory))) {
			return flag;
		}

		if (!(this.rateType.equals(tour.rateType))) {
			return flag;
		}

		if (!(this.count.equals(tour.count))) {
			return flag;
		}

		if (!(this.soldIndividual.equals(tour.soldIndividual))) {
			return flag;
		}

		if (!(this.duration.equals(tour.duration))) {
			return flag;
		}

		if (!(this.timeEntry.equals(tour.timeEntry))) {
			return flag;
		}

		if (!(this.timeConflict.equals(tour.timeConflict))) {
			return flag;
		}

		if (!(this.limitedCapacity.equals(tour.limitedCapacity))) {
			return flag;
		}

		if (!(this.capacity.equals(tour.capacity))) {
			return flag;
		}

		if (!(this.sortOrder.equals(tour.sortOrder))) {
			return flag;
		}

		if (!(this.accessible.equals(tour.accessible))) {
			return flag;
		}

		if (!(this.accessibility.equals(tour.accessibility))) {
			return flag;
		}

		if (!(this.directions.equals(tour.directions))) {
			return flag;
		}

		if (!(this.distance.equals(tour.distance))) {
			return flag;
		}

		if (!(this.information.equals(tour.information))) {
			return flag;
		}

		if (!(this.longDescription.equals(tour.longDescription))) {
			return flag;
		}

		if (!(this.numOfSteps.equals(tour.numOfSteps))) {
			return flag;
		}

		if (!(this.physicalEffort.equals(tour.physicalEffort))) {
			return flag;
		}

		if (!(this.days_hoursDescription.equals(tour.days_hoursDescription))) {
			return flag;
		}

		if (!(this.tourimage.equals(tour.tourimage))) {
			return flag;
		}

		if (!(this.tourUrl.equals(tour.tourUrl))) {
			return flag;
		}
		
		if (!this.daysOfWeek.get("Mon").equals(tour.daysOfWeek.get("Mon"))) {
			return flag;
		}

	    if (this.daysOfWeek.get("Tue") != null) {
			if (!this.daysOfWeek.get("Tue").equals(tour.daysOfWeek.get("Tue"))) {
				return flag;
			}
		} 

		if (this.daysOfWeek.get("Wed") != null) {
			if (!this.daysOfWeek.get("Wed").equals(tour.daysOfWeek.get("Wed"))) {
				return flag;
			}
		} 

		if (this.daysOfWeek.get("Thu") != null) {
			if (!this.daysOfWeek.get("Thu").equals(tour.daysOfWeek.get("Thu"))) {
				return flag;
			}
		} 

		if (this.daysOfWeek.get("Fri") != null) {
			if (!this.daysOfWeek.get("Fri").equals(tour.daysOfWeek.get("Fri"))) {
				return flag;
			}
		}

		if (this.daysOfWeek.get("Sat") != null) {
			if (!this.daysOfWeek.get("Sat").equals(tour.daysOfWeek.get("Sat"))) {
				return flag;
			}
		}

		if (this.daysOfWeek.get("Sun") != null) {
			if (!this.daysOfWeek.get("Sun").equals(tour.daysOfWeek.get("Sun"))) {
				return flag;
			}
		}

		return true;
	}
}
