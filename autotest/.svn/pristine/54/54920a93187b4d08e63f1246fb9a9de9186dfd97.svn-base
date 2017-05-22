package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class UwpGoogleStateMapPage extends UwpPage {

	private static UwpGoogleStateMapPage _instance = null;

	public static UwpGoogleStateMapPage getInstance() {
		if (null == _instance)
			_instance = new UwpGoogleStateMapPage();

		return _instance;
	}

	public UwpGoogleStateMapPage() {
	}

	public boolean exists() {
		// return browser.checkHtmlObjectExists(".class","Html.DIV",".id",
		// "mapviewport") && this.isParkFlagFinishLoading();
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id",
				"mapviewport");
	}

	public Property[] subNavProp() {
		return Property.toPropertyArray(".id", "subnav");
	}

	public Property[] pinProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "pin");
	}
	
	public Property[] mapViewportProp(){
		return Property.toPropertyArray(".id", "mapviewport");
	}

	/**
	 * wait for the map finished the park flags loading
	 * 
	 * @return
	 */
	public boolean waitMapFinishLoading() {
		browser.waitExists();
		boolean toReturn = false;
		IHtmlObject[] objs;// = browser.getHtmlObject(".class", "Html.DIV",
							// ".id", "mapStatusMsg");
		if (this.checkNoCampMessageExists()) {
			return true;
		}

		String statusMsg = "";
		int counter = 0;
		do {
			objs = browser.getHtmlObject(".class", "Html.DIV", ".id",
					"mapStatusMsg");
			statusMsg = objs.length > 0 ? objs[0].getProperty(".text") : null;
			if (null != statusMsg && statusMsg.trim().length() == 0) {
				toReturn = true;
			}
			logger.info("Try " + counter
					+ " times to wait for park flags loading finish.");
			counter++;
			Browser.unregister(objs);
			Browser.sleep(1);
		} while (!toReturn && counter < 5);

		return toReturn;
	}

	/**
	 * Verify whether there is no campgrounds found message appears on page.
	 * 
	 * @return true - found; false - not found
	 */
	public boolean checkNoCampMessageExists() {
		boolean found = false;

		RegularExpression reg = new RegularExpression("No campgrounds found.+",
				false);
		IHtmlObject[] foundTOs = browser.getHtmlObject(".id", "mapmessage",
				".text", reg);
		if (null != foundTOs && foundTOs.length > 0) {
			found = true;
		}
		Browser.unregister(foundTOs);
		return found;
	}

	/**
	 * Verify whether too many parks in a area and need to zoom in.
	 * 
	 * @return - true - need zoom in; false - still can zoom out
	 */
	public boolean needZoomIn() {
		boolean found = false;
		IHtmlObject[] foundTOs = browser.getHtmlObject(".id", "mapmessage",
				".text",
				"Too many campgrounds in the area. Zoom in and try again.");
		if (foundTOs.length > 0) {
			found = true;
		}
		Browser.unregister(foundTOs);
		return found;
	}

	/**
	 * Retrieve the site flag object on map.
	 * 
	 * @return - TestObject
	 */
	public IHtmlObject[] getSiteFlagObject() {
		return browser.getHtmlObject(".class", "Html.IMG", ".src",
				new RegularExpression("images\\/maps\\/mm_\\d+_state\\.png",
						false));
	}

	public IHtmlObject[] getSiteFlagObjectForRec() {
		return browser.getHtmlObject(".class", "Html.IMG", ".src",
				new RegularExpression("marker[A-Z]+\\.png", false));
	}

	/**
	 * Wait for site flag objects loading.
	 */
	public void siteFlagWaitExists() {
		// browser.searchObjectWaitExists(".class", "Html.IMG", ".id",
		// siteFlagReg,"SiteFlag");
		browser.searchObjectWaitExists(".class", "Html.IMG", ".src",
				new RegularExpression(
						"images\\/maps\\/mm_\\d+_(state|county)\\.png", false),
				"SiteFlag");
	}

	public void siteFlagWaitExistsForRec() {
		browser.searchObjectWaitExists(".class", "Html.IMG", ".src",
				new RegularExpression("marker[A-Z]+\\.png", false), "SiteFlag");
	}

	/**
	 * Click on map search link.
	 */
	public void clickOnMapSearch() {
		browser.clickGuiObject(".id", "mapSearch");
	}

	/**
	 * Click on map browser link.
	 */
	public void clickOnMapBrowser() {
		browser.clickGuiObject(".id", "mapBrowse");
	}

	/**
	 * Click on link to go to map home.
	 */
	public void gotoMapHome() {
		browser.clickGuiObject(".text", "Map Home");
	}

	/**
	 * Retrieve the select state name.
	 * 
	 * @return - state name
	 */
	public String getSelectedState() {
		return browser.getDropdownListValue(".id", "pstate", 0);
	}

	/** Select state */
	public void selectState(String state) {
		browser.selectDropdownList(".id", "pstate", state);
	}

	/**
	 * Click on submit button to do map search.
	 */
	public void clickGo() {
		browser.clickGuiObject(".id", "nearbysubmit", ".type", "submit");
	}

	/**
	 * Click on link to show Satellite Map.
	 */
	public void showSatelliteMap() {
		browser.clickGuiObject(".text", "Satellite");
	}

	/**
	 * Click on link to show Hydrid Map.
	 */
	public void showHydridMap() {
		browser.clickGuiObject(".text", "Hybrid");
	}

	/**
	 * Click on link to show the Normal Map.
	 */
	public void showNormalMap() {
		browser.clickGuiObject(".text", "Map");
	}

	/**
	 * Wait for page to load after submit search.
	 */
	public void clickMapSearchWaitExists() {
		browser.searchObjectWaitExists(".type", "submit", ".id", "search");
	}

	/**
	 * Go to special area by given park name.
	 * 
	 * @param pName
	 *            - park name
	 */
	public void gotoSpecialAreaDetailsPage(String pName) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				pName);
		objs[0].click();
		Browser.unregister(objs);
	}

	/**
	 * Go to special area by given park name.
	 * 
	 * @param pName
	 *            - park name
	 */
	public void gotoSpecialAreaDetailsPageFromMap(String pName) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				pName);
		objs[objs.length - 1].click();// 1
		Browser.unregister(objs);
	}

	/** Wait for Enter Date pop up */
	public void enterDateWidgetWaitExists() {
		RegularExpression pattern = new RegularExpression("book(\\s)?(_)?(now|next)",
				false);
		browser.searchObjectWaitExists(".class", "Html.A", ".className",
				pattern);
	}

	/**
	 * Click on Enter Date/See Details link in pop up window. or click See
	 * Details button in Map Search section.
	 * */
	public void clickBookNowNext() {
		RegularExpression pattern = new RegularExpression("(book(\\s)?(_)?(now|next))|Enter Date",
				false);
		browser.clickGuiObject(".class", "Html.A", ".className", pattern, true);
	}

	/** zoom out the map */
	public void clickZoomOut() {
		browser.clickGuiObject(".class", "Html.DIV", ".title",
				new RegularExpression("Zoom Out", false));
	}

	/** zoom in the map */
	public void clickZoomIn() {
		browser.clickGuiObject(".class", "Html.DIV", ".title",
				new RegularExpression("Zoom In", false));
	}

	public void clickPanDown() {
		browser.clickGuiObject(".class", "Html.DIV", ".title", "Pan down");
	}

	/**
	 * Click on the park flag in map by given park title, the title will be
	 * 'park name' + ',' + 'contract code' such as "MONGAUP POND, NY", will
	 * automatic zoom out if park not found in current view.
	 * 
	 * @param title
	 *            - park name plus comma plus contract code
	 */
	public void clickMapFlagByParkTitle(String title) {
		IHtmlObject[] objs = null;
		// RegularExpression t=RegularExpression.convert(title,false);

		do {
			// objs = browser.getHtmlObject(".class", siteFlagReg,".title", t);
			objs = browser.getHtmlObject(".class", "Html.DIV", ".text", title);
			if (objs.length == 0) {
				this.clickZoomOut();
				this.waitMapFinishLoading();
			} else {
				break;
			}
		} while (!this.needZoomIn());

		if (objs.length == 0) {
			throw new ItemNotFoundException("Given park '" + title
					+ "' was not found after zooming out to limitation");
		} else {
			if (objs[0].getChildren().length > 0) {
				// objs[0].getChildren()[1].click();
				IHtmlObject imgObject[] = browser.getHtmlObject(".class",
						"Html.IMG", objs[0]);
				if (imgObject.length > 0) {
					imgObject[0].click();
				}
				Browser.unregister(imgObject);
			} else {
				throw new ItemNotFoundException("Can't find " + title
						+ " map flag.");
			}
		}
		Browser.unregister(objs);
	}

	/**
	 * Click on park name link form Map Browse section to go to park details
	 * page by given park title. the title will be 'park name' + ',' + 'contract
	 * code', such as "MONGAUP POND, NY" , will automatic zoom out if park not
	 * found in current view.
	 * 
	 * @param title
	 */
	public void clickParkFromMapBrowse(String title) {
		IHtmlObject[] objs = null;
		// if (!this.nearSearchIsExists()){
		RegularExpression titlePattern = RegularExpression
				.convert(title, false);
		do {
			objs = browser.getHtmlObject(".class", "Html.A", ".text",
					titlePattern);
			if (objs.length == 0) {
				this.clickZoomOut();
				this.waitMapFinishLoading();
			} else {
				break;
			}
		} while (!this.needZoomIn());

		// pan down 2 times to see if the park displayed on the Google map.
		if (this.needZoomIn() && objs.length == 0) {
			this.clickZoomIn();
			this.waitMapFinishLoading();
			this.clickPanDown();
			this.waitMapFinishLoading();
			for (int i = 0; i < 2; i++) {
				objs = browser.getHtmlObject(".class", "Html.A", ".text",
						titlePattern);
				if (objs.length == 0) {
					this.clickPanDown();
					this.waitMapFinishLoading();
				} else {
					break;
				}
			}
		}
		// } else {
		// this.setNear(title);
		// this.clickGo();
		// this.waitExists();
		// objs = browser.getHtmlObject(".class", "Html.A", ".text",
		// title.toUpperCase());
		// }

		if (objs.length == 0) {
			throw new ItemNotFoundException("Given park '" + title
					+ "' can't be found after zooming out to limitation");
		} else {
			objs[0].click();
		}
		Browser.unregister(objs);
	}

	/**
	 * Whether or not the specific park shows in Map Search section. the title
	 * will be 'park name' + ',' + 'contract code', such as "MONGAUP POND, NY"
	 * 
	 * @param title
	 * @return true - park display in map search section; false - not found
	 */
	public boolean isParkSearched(String title) {
		// Get the entire text of map search results section
		String resultText = browser.getObjectText(".id",
				"mapviewcampgroundlist").split("See Details")[0].trim();
		return title.equalsIgnoreCase(resultText);
	}

	// true - Map Search; false - Map Browse
	public boolean isMapSearch() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"^(searchcgrounds|search)", false));
	}

	/**
	 * Click on first A link from map when page is Map Search. Can not identify
	 * this object more clearly, only can specific the object in Map Browse
	 * */
	public void clickAFromMapSeachView() {
		browser.clickGuiObject(".id", "mtgt_unnamed_0");
	}

	/** Fill in near by field. */
	public void setNear(String addr) {
		browser.setTextField(".id", "landmarkName", addr);
	}

	public boolean nearSearchIsExists() {
		return browser.checkHtmlObjectExists(".id", "landmarkName");
	}

	public void clickMapSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Map Search");
	}

	public void clickAFromMapSeachViewInMap() {
		browser.clickGuiObject(".class", "Html.IMG", ".src",
				new RegularExpression("images/maps/markerA", false), 1);
	}

	public String getChosenParkFromleftMapBrowser() {
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id",
				"chosencampground");
		Property[] p2 = Property.toPropertyArray(".class", "Html.A", ".text",
				new RegularExpression(".*\\w.*", false));
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException("Can't find campground");
		}
		String text = objs[0].text();
		Browser.unregister(objs);
		return text;
	}

	/**
	 * Get top link value
	 * @return
	 */
	public String getTopLinkValue(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(subNavProp(), pinProp()));
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find any pin under sub nav.");
		}
		
		String value = objs[0].text();
		Browser.unregister(objs);
		return value;
	}
	
	public void waitForChosenCampground(){
		browser.searchObjectWaitExists(".class", "Html.DIV", ".id", "chosencampground");
	}
	
	public void clickTopParkLink(String contractCode, String parkID){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(subNavProp(), pinProp()));
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find any pin under sub nav.");
		}
		
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("/campgroundDetails\\.do\\?contractCode="+contractCode+"&parkId="+parkID, false));
		browser.clickGuiObject(p, true, 0, objs[0]);
	}
	
	public void clickParkPinImg(String contractCode, String parkID){
		Property[] p1 = Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("javascript:clickResult\\('"+contractCode+""+parkID+"'\\)", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG");
	    browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}
	
	public void waitForMapBubbleWidget(String contractCode, String parkID){
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("/campgroundDetails\\.do\\?contractCode="+contractCode+"&parkId="+parkID, false));
		browser.searchObjectWaitExists(Property.atList(mapViewportProp(), p));
	}
	
	public void clickParkNameOnMapBubbleWidget(String contractCode, String parkID){
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("/campgroundDetails\\.do\\?contractCode="+contractCode+"&parkId="+parkID, false));
		browser.clickGuiObject(Property.atList(mapViewportProp(), p), true, 0);
	}
	
	public void clickViewRegionalMapLinkOnMapBubbleWidget(String contractCode, String parkID){
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression(".*CampgroundMap&pageOrigin=mapBrowse&map=PARK&contractCode="+contractCode+"&parkId="+parkID, false));
		browser.clickGuiObject(Property.atList(mapViewportProp(), p), true, 0);
	}
}
