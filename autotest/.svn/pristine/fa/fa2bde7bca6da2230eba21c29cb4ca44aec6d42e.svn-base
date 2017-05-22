/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Sep 29, 2011
 */
public interface ILicMgrProductAgentAssignmentsPage {

	public abstract boolean exists();

	public abstract void checkAllLocationClasses();

	public abstract void uncheckAllLocationClasses();

	public abstract void clickAssignToStoresInSelectedLocationClasses();

	public abstract void clickUnassignFromStoresInSelectedLocationClasses();

	public abstract void clickLocationClassColName();

	public abstract void clickAssignedColName();

	public abstract void clickNumberOfAgentsInLocationClassColName();

	public abstract void clickNumberOfAgentsActivelyAssignedColName();

	/**
	 * Parse the Location Class Store Assignments table object and return the all LocationClassStoreAssignment records
	 * @return
	 */
	public abstract List<List<String>> getAllLocationClassStoreAssignmentsInfo();

	/**
	 * Get a record of location class store assignment identified by location class name
	 * @param locationClass
	 * @return
	 */
	public abstract List<String> getLocationClassStoreAssignmentInfo(
			String locationClass);

	/**
	 * Check location class store assignment record identified by location class name
	 * @param locationClassName
	 */
	public abstract void selectLocationClassCheckboxByName(String locationClassName);

	/**
	 * Get the all stores information in a location class
	 * @param locationClass
	 * @return
	 */
	public abstract List<StoreInfo> getStoreInfoByLocationClass(
			String locationClass);

	/**
	 * Get all not assigned location class records
	 * @return
	 */
	public abstract List<List<String>> getUnassignedLocationClassStoreRecords();

	/**
	 * Click number of agents in location class identified by location class
	 * @param locationClass
	 */
	public abstract void clickNumberOfAgentsInLocationClassByLocationName(
			String locationClass);

	/**
	 * Click number of agents actively assigned identified by location class
	 * @param locationClass
	 */
	public abstract void clickNumberOfAgentsActivelyAssignedByLocationName(
			String locationClass);
	
	public String getNumberOfAgentsActivelyAssignedByLocationName(String locationClass);
	
	public String getLocationClassWithMinNumOfAgents();

	public abstract void unassignAllAgentsThroughLocationClass();

}
