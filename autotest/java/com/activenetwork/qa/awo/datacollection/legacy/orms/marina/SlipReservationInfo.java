package com.activenetwork.qa.awo.datacollection.legacy.orms.marina;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;

/**
 * This data collection is for slip reservation information used in Slip reservation order detail page in marina Manager
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pchen
 * @Date  April 10, 2013
 */
public class SlipReservationInfo extends ReservationInfo{
	private String createdDate;
	private String createdBy;
	private String unissuedRefund;
	private String collectionStatus;
	
//	private List<List<String>> vehicleInfos;
	private List<OtherOccupant> otherOccupants = new ArrayList<SlipReservationInfo.OtherOccupant>();
	private SlipInfo slip = new SlipInfo();
	private ListInfo list = new ListInfo();
	
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUnissuedRefund() {
		return unissuedRefund;
	}
	public void setUnissuedRefund(String unissuedRefund) {
		this.unissuedRefund = unissuedRefund;
	}

	public String getCollectionStatus() {
		return collectionStatus;
	}
	public void setCollectionStatus(String collectionStatus) {
		this.collectionStatus = collectionStatus;
	}
//	/**
//	 * @return the vehicleInfos
//	 */
//	public List<List<String>> getVehicleInfos() {
//		return vehicleInfos;
//	}
//	/**
//	 * @param vehicleInfos the vehicleInfos to set
//	 */
//	public void setVehicleInfos(List<List<String>> vehicleInfos) {
//		this.vehicleInfos = vehicleInfos;
//	}
	
	public List<OtherOccupant> getOtherOccupants() {
		return otherOccupants;
	}
	public void setOtherOccupants(List<OtherOccupant> otherOccupants) {
		this.otherOccupants = otherOccupants;
	}
	/**
	 * @return the slip
	 */
	public SlipInfo getSlip() {
		return slip;
	}
	/**
	 * @param slip the slip to set
	 */
	public void setSlip(SlipInfo slip) {
		this.slip = slip;
	}
	
	public ListInfo getList() {
		return list;
	}
	
	public void setList(ListInfo list) {
		this.list = list;
	}
	
	public class OtherOccupant {
		private String firstName;
		private String lastName;
		
		public OtherOccupant() {}
		
		public OtherOccupant(String fName, String lName) {
			setFirstName(fName);
			setLastName(lName);
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		
		public boolean equals(Object obj) {
			if(!(obj instanceof OtherOccupant)) return false;
			
			OtherOccupant that = (OtherOccupant)obj;
			
			boolean result = true;
			result &= MiscFunctions.compareResult("Other Occupant - First Name", this.firstName, that.firstName);
			result &= MiscFunctions.compareResult("Other Occupant - Last Name", this.lastName, that.lastName);
			
			return result;
		}
	}
}
