/**
 * SupportCenterSearchLocationsPage.java
 * Jul 26, 2011
 * QA
 */
package com.activenetwork.qa.awo.pages.orms.supportCenter;

import java.util.List;

import com.activenetwork.qa.testapi.util.Property;

/**
 * @author eliang
 * @Date Jul 26, 2011
 */
public class SupportCenterSearchLocationsPage extends SupportCenterPage {
	private static SupportCenterSearchLocationsPage _instance = null;

	public static SupportCenterSearchLocationsPage getInstance() {
		if (_instance == null) {
			_instance = new SupportCenterSearchLocationsPage();
		}
		return _instance;
	}

	protected Property[] searchLocations_OkayButton() {
		return Property.toPropertyArray(".class","FlexButton","id","buOkay");
	}
	
	protected Property[] searchLocations_LocationComboBox() {
		return Property.toPropertyArray(".class","FlexComboBox","id","acSearch");
	}
	
	protected Property[] searchLocations_AddButton() {
		return Property.toPropertyArray(".class","FlexButton","id","buAdd");
	}
	
	@Override
	public boolean exists() {
		return flex.checkFlexObjectExists(searchLocations_LocationComboBox() );
	}
	
	public void clickOkay() {
		flex.clickFlexObject(searchLocations_OkayButton());
	}

	public void setLocation(String location) {
		flex.setComboBox(searchLocations_LocationComboBox(), location);
	}

	public void selectLocation(String location) {
		flex.selectComboBox(searchLocations_LocationComboBox(), location);
	}

	public void clickAdd() {
		flex.clickFlexObject(searchLocations_AddButton());
	}

	public List<String> getLocation() {
		List<String> value = flex.getComboxBoxValues(searchLocations_LocationComboBox());
		return value;
	}

	public void selectLocationByPrefix(String locationPre, String location) {
		setLocation(locationPre);
		List<String> values = this.getLocation();
		int timeout = 0;
		while (values.size() == 1 && "".equals(values.get(0))
				&& timeout < SHORT_SLEEP) {
			values = this.getLocation();
			timeout++;
			if (values.size() > 1 || !"".equals(values.get(0))) {
				break;
			}
		}
		selectLocation(location);
		waitLoading();
		clickOkay();
	}

}
