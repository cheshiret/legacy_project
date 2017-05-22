package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Dec 26, 2011
 */
public class ExploreAndMorePage extends RecgovCommonPage{
	private static ExploreAndMorePage _instance=null;

	public static ExploreAndMorePage getInstance() {
		if(null==_instance) {
			_instance=new ExploreAndMorePage();
		}

		return _instance;
	}

	public ExploreAndMorePage() {}

	/**
	 * Check whether this page exist according to the DIV with title (More Great Resources to Explore:)
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "temp-MoreLinks");
	}

	public void clickExploreWays(String title){
		RegularExpression reg = new RegularExpression(".*marketing\\.do\\?goto=acm\\/Explore_And_More.*", false);
		browser.clickGuiObject(".href", reg, ".text", new RegularExpression(title,true));
	}

	public void clickAutoTouring(){
		this.clickExploreWays("AutoTouring");
	}
	public void clickBiking(){
		this.clickExploreWays("Biking");
	}
	public void clickBoating(){
		this.clickExploreWays("Boating");
	}
	public void clickCamping(){
		this.clickExploreWays("Camping");
	}
	public void clickClimbing(){
		this.clickExploreWays("Climbing");
	}
	public void clickHiking(){
		this.clickExploreWays("Hiking");
	}
	public void clickHistoricCultualSites(){
		this.clickExploreWays("Historic & Cultural Sites");
	}
	public void clickHorsebackRiding(){
		this.clickExploreWays("Horseback Riding");
	}
	public void clickHunting(){
		this.clickExploreWays("Hunting");
	}
	public void clickOffHighwayVehicle(){
		this.clickExploreWays("Off Highway Vehicle");
	}
	public void clickVisitorCenters(){
		this.clickExploreWays("Visitor Centers");
	}
	public void clickWildlifeViewing(){
		this.clickExploreWays("Wildlife Viewing");
	}
	public void clickWinterSports(){
		this.clickExploreWays("Winter Sports");
	}

	public String[] getExploreWays(){
		Property[] p1=Property.toPropertyArray(".class","Html.TD",".className","temp-rightCol");
		RegularExpression reg = new RegularExpression(".*marketing\\.do\\?goto=acm\\/Explore_And_More.*", false);
		Property[] p2=Property.toPropertyArray(".href", reg);

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		String[] activities = new String[objs.length];

		if(objs.length>0){
			for(int i=0; i<objs.length; i++){
				activities[i] = objs[i].text().trim();
			}
		}else throw new ObjectNotFoundException("Explore ways object can't be found.");

		Browser.unregister(objs);
		return activities;
	}

}
