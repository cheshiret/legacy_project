/*
 * $Id: InvMgrSiteDetailPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SiteAttributes;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author CGuo
 */
public class InvMgrSiteDetailPage extends InvMgrSiteDetailsCommonPage {

	/**
	 * Script Name : InvMgrSiteDetailPage Generated : Feb 10, 2005 2:07:27 PM
	 * Original Host : WinNT Version 5.1 Build 2600 (Service Pack 2)
	 * 
	 * @since 2005/02/10
	 */

	private static InvMgrSiteDetailPage _instance = null;

	public static InvMgrSiteDetailPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrSiteDetailPage();
		}

		return _instance;
	}

	protected InvMgrSiteDetailPage() {

	}

	protected Property[] sitesLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Sites");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression("Site Details", false));
	}

	/**
	 * Select categories
	 * 
	 * @param category
	 */
	public void selectLookingForCategory(String category) {

		String[] categories = category.split("[,;]");

		for (int i = 0; i < categories.length; i++) {

			browser.selectCheckBox(".id", "Looking For Category__"
					+ categories[i].trim());
		}
	}

	/**
	 * Select allowed pets
	 * 
	 * @param pet
	 */

	public void selectAllowedPets(String pet) {

		String[] pets = pet.split("[ ,;]");

		for (int i = 0; i < pets.length; i++) {
			browser.selectCheckBox(".id", "Pets Allowed__" + pets[i].trim());
		}
	}

	/**
	 * Select proximity to water
	 * 
	 * @param prox
	 */
	public void selectProximityToWater(String prox) {

		String[] proxs = prox.split("[ ,;]");

		for (int i = 0; i < proxs.length; i++) {
			browser.selectCheckBox(".id", "Proximity to Water__" + proxs[i]);
		}
	}

	/**
	 * Select site access
	 * 
	 * @param access
	 */
	public void selectSiteAccess(String access) {

		String[] accesses = access.split("[ ,;]");

		for (int i = 0; i < accesses.length; i++) {
			browser.selectCheckBox(".id", "Site Access__" + accesses[i]);
		}
	}

	/**
	 * Select bed types
	 * 
	 * @param types
	 */
	public void selectBedType(String types) {
		String[] type = types.split("[ ,;]");

		for (int i = 0; i < type.length; i++) {
			browser.selectCheckBox(".id", "Bed Type__" + type[i]);
		}
	}

	/**
	 * select supplies
	 * 
	 * @param supplies
	 */
	public void selectSupplies(String supplies) {
		String[] supply = supplies.split("[ ,;]");

		for (int i = 0; i < supply.length; i++) {
			browser.selectCheckBox(".id", "Supplies__" + supply[i]);
		}
	}

	/**
	 * select lightings
	 * 
	 * @param lightings
	 */
	public void selectLighting(String lightings) {
		String[] lighting = lightings.split("[ ,;]");

		for (int i = 0; i < lighting.length; i++) {
			browser.selectCheckBox(".id", "Lighting__" + lighting[i]);
		}
	}

	/**
	 * select multimedia
	 * 
	 * @param multimedia
	 */
	public void selectMultimedia(String multimedia) {
		String[] medias = multimedia.split("[ ,;]");
		for (int i = 0; i < medias.length; i++) {
			browser.selectCheckBox(".id", "Multimedia__" + medias[i]);
		}
	}

	public void setCheckInTime(String time) {
		boolean isAM = time.matches("^\\d{0,2}:{0,1}\\d{0,2}.*(AM|am)$");
		boolean isPM = time.matches("^\\d{0,2}:{0,1}\\d{0,2}.*(PM|pm)$");

		if (isAM) {
			browser.selectDropdownList(".id", "Checkin Time_ispm", "AM");
		} else if (isPM) {
			browser.selectDropdownList(".id", "Checkin Time_ispm", "PM");
		}

		browser.setTextField(".id", "Checkin Time", time.split("(AM|am|PM|pm)")[0]);
	}

	public void setCheckOutTime(String time) {
		boolean isAM = time.matches("^\\d{2}:\\d{2}.*(AM|am)$");
		boolean isPM = time.matches("^\\d{2}:\\d{2}.*(PM|pm)$");

		if (isAM) {
			browser.selectDropdownList(".id", "Checkout Time_ispm", "AM");
		} else if (isPM) {
			browser.selectDropdownList(".id", "Checkout Time_ispm", "PM");
		}

		browser.setTextField(".id", "Checkout Time", time.split("(AM|am|PM|pm)")[0]);
	}

	/**
	 * Set site attributes in site detail page
	 * 
	 * @param siteAttr
	 */
	public void setSiteInfoAndAttributes(SiteAttributes siteAttr) {

		// Set site info
		if (siteAttr.siteRelationType != null
				&& !siteAttr.siteRelationType.equals("")) {
			browser.selectDropdownList(".id", "SiteView.relationType",
					siteAttr.siteRelationType, true);
			this.waitLoading();
		}

		if (siteAttr.NSSGroup != null && !siteAttr.NSSGroup.equals("")) {
			browser.selectDropdownList(".id", "SiteView.parentProductID",
					siteAttr.NSSGroup);
			this.waitLoading();
		}
		if (siteAttr.siteCode != null && !siteAttr.siteCode.equals("")) {
			browser.setTextField(".id", "SiteView.siteCode", siteAttr.siteCode);
		}

		if (siteAttr.siteName != null && !siteAttr.siteName.equals("")) {
			browser.setTextField(".id", "SiteView.name", siteAttr.siteName);
		}

		if(!siteAttr.siteRelationType.equalsIgnoreCase("Non Site-Specific Child")){
			if (siteAttr.siteType != null && !siteAttr.siteType.equals("")) {
				browser.selectDropdownList(".id", "SiteView.siteTypeID",
						siteAttr.siteType);
				this.waitLoading();
			}
			if (siteAttr.rateType != null && !siteAttr.rateType.equals("")) {
				browser.selectDropdownList(".id", "SiteView.productRateType",
						siteAttr.rateType);
			}
			if (siteAttr.reservable != null && !siteAttr.reservable.equals("")) {
				browser.selectDropdownList(".id", "SiteView.reservable",
						siteAttr.reservable);
			}

			if (siteAttr.webVisible != null && !siteAttr.webVisible.equals("")) {
				browser.selectDropdownList(".id", "SiteView.webReservable",
						siteAttr.webVisible);
			}
		}
		

		if (siteAttr.description != null && !siteAttr.description.equals("")) {
			browser.setTextArea(".id", "SiteView.description",
					siteAttr.description);
		}

		

		if (siteAttr.parentLoop != null && !siteAttr.parentLoop.equals("")) {
			browser.selectDropdownList(".id", "SiteView.parentID",
					siteAttr.parentLoop);
		}

		// Set site attributes
		if(!siteAttr.siteRelationType.equalsIgnoreCase("Non Site-Specific Child")){
			if (siteAttr.adaAccessible != null
					&& !siteAttr.adaAccessible.equals("")) {
				browser.selectDropdownList(".id", "ADA Accessible",
						siteAttr.adaAccessible);
			}

			if (siteAttr.adaOccupantRequried != null
					&& !siteAttr.adaOccupantRequried.equals("")) {
				browser.selectDropdownList(".id", "Accessible Occupant Message",
						siteAttr.adaOccupantRequried);
			}
		}

		if (siteAttr.baseNumPeople != null
				&& siteAttr.baseNumPeople.length() > 0) {
			browser.setTextField("id", "Base Number of People",
					siteAttr.baseNumPeople);
		}

		if (siteAttr.minNumPeople != null && siteAttr.minNumPeople.length() > 0) {
			browser.setTextField(".id", "Minimum Number of People",
					siteAttr.minNumPeople);
		}

		if (siteAttr.maxNumPeople != null && siteAttr.maxNumPeople.length() > 0) {
			browser.setTextField(".id", "Maximum Number of People",
					siteAttr.maxNumPeople);
		}

		if (siteAttr.maxGroupCap != null && siteAttr.maxGroupCap.length() > 0) {
			browser.setTextField(".id", "Maximum Group Capacity",
					siteAttr.maxGroupCap);
		}

		if (siteAttr.baseNumVehicle != null
				&& siteAttr.baseNumVehicle.length() > 0) {
			browser.setTextField(".id", "Base Number of Vehicles",
					siteAttr.baseNumVehicle);
		}

		if (siteAttr.minNumVehicle != null
				&& siteAttr.minNumVehicle.length() > 0) {
			browser.setTextField(".id", "Minimum Number of Vehicles",
					siteAttr.minNumVehicle);
		}

		if (siteAttr.maxNumVehicle != null
				&& siteAttr.maxNumVehicle.length() > 0) {
			browser.setTextField(".id", "Maximum Number of Vehicles",
					siteAttr.maxNumVehicle);
		}

		if (siteAttr.maxVehicleLength != null
				&& siteAttr.maxVehicleLength.length() > 0) {
			browser.setTextField(".id", "Maximum Vehicle Length",
					siteAttr.maxVehicleLength);
		}

		if (siteAttr.checkInTime != null && siteAttr.checkInTime.length() > 0) {
			this.setCheckInTime(siteAttr.checkInTime);
		}

		if (siteAttr.checkOutTime != null && siteAttr.checkOutTime.length() > 0) {
			this.setCheckOutTime(siteAttr.checkOutTime);
		}

		if (siteAttr.siteLength != null && siteAttr.siteLength.length() > 0) {
			browser.setTextField(".id", "Site Length", siteAttr.siteLength);
		}

		if (siteAttr.siteWidth != null && siteAttr.siteWidth.length() > 0) {
			browser.setTextField(".id", "Site Width", siteAttr.siteWidth);
		}

		if (siteAttr.siteHeight != null && siteAttr.siteHeight.length() > 0) {
			browser.setTextField(".id", "Site Height/Overhead Clearance",
					siteAttr.siteHeight);
		}

		if (siteAttr.capacityOrSizeRating != null
				&& siteAttr.capacityOrSizeRating.length() > 0) {
			browser.selectDropdownList(".id", "Capacity/Size Rating",
					siteAttr.capacityOrSizeRating);
		}

		if (siteAttr.depth != null && siteAttr.depth.length() > 0) {
			browser.setTextField(".id", "Depth", siteAttr.depth);
		}

		if (siteAttr.drivewayLength != null
				&& siteAttr.drivewayLength.length() > 0) {
			browser.setTextField(".id", "Driveway Length",
					siteAttr.drivewayLength);
		}

		if (siteAttr.drivewayEntry != null
				&& !siteAttr.drivewayEntry.equals("")) {
			browser.selectDropdownList(".id", "Driveway Entry",
					siteAttr.drivewayEntry);
		}

		if (siteAttr.drivewayGrade != null
				&& !siteAttr.drivewayGrade.equals("")) {
			browser.selectDropdownList(".id", "Driveway Grade",
					siteAttr.drivewayGrade);
		}

		if (siteAttr.drivewaySurface != null
				&& !siteAttr.drivewaySurface.equals("")) {
			browser.selectDropdownList(".id", "Driveway Surface",
					siteAttr.drivewaySurface);
		}

		if (siteAttr.doubleDriveway != null
				&& !siteAttr.doubleDriveway.equals("")) {
			browser.selectDropdownList("id", "Double Driveway",
					siteAttr.doubleDriveway);
		}

		if (siteAttr.electricityHookup != null
				&& !siteAttr.electricityHookup.equals("")) {
			browser.selectDropdownList(".id", "Electricity Hookup",
					siteAttr.electricityHookup);
		}

		if (siteAttr.waterHookup != null && !siteAttr.waterHookup.equals("")) {
			browser.selectDropdownList(".id", "Water Hookup",
					siteAttr.waterHookup);
		}

		if (siteAttr.sewerHookup != null && !siteAttr.sewerHookup.equals("")) {
			browser.selectDropdownList(".id", "Sewer Hookup",
					siteAttr.sewerHookup);
		}

		if (siteAttr.fullHookup != null && !siteAttr.fullHookup.equals("")) {
			browser.selectDropdownList(".id", "Full Hookup",
					siteAttr.fullHookup);
		}

		if (siteAttr.hotWater != null && !siteAttr.hotWater.equals("")) {
			browser.selectDropdownList(".id", "Hot Water", siteAttr.hotWater);
		}

		if (siteAttr.showerOrBathType != null
				&& !siteAttr.showerOrBathType.equals("")) {
			browser.selectDropdownList(".id", "Shower/Bath Type",
					siteAttr.showerOrBathType);
		}

		if (siteAttr.toilet != null && !siteAttr.toilet.equals("")) {
			browser.selectDropdownList(".id", "Toilet", siteAttr.toilet);
		}

		if (siteAttr.bedType != null && !siteAttr.bedType.equals("")) {
			this.selectBedType(siteAttr.bedType);
		}

		if (siteAttr.maidOrCleaningService != null
				&& !siteAttr.maidOrCleaningService.equals("")) {
			browser.selectDropdownList(".id", "Maid/Cleaning Service",
					siteAttr.maidOrCleaningService);
		}

		if (siteAttr.numOfBedRoom != null && !siteAttr.numOfBedRoom.equals("")) {
			browser.selectDropdownList(".id", "Number of Bedrooms",
					siteAttr.numOfBedRoom);
		}

		if (siteAttr.numOfBeds != null && !siteAttr.numOfBeds.equals("")) {
			browser.selectDropdownList(".id", "Number of Beds",
					siteAttr.numOfBeds);
		}

		if (siteAttr.conditionRating != null
				&& !siteAttr.conditionRating.equals("")) {
			browser.selectDropdownList(".id", "Condition Rating",
					siteAttr.conditionRating);
		}

		if (siteAttr.cableTV != null && !siteAttr.cableTV.equals("")) {
			browser.selectDropdownList(".id", "Cable TV Access",
					siteAttr.cableTV);
		}

		if (siteAttr.DVD != null && !siteAttr.DVD.equals("")) {
			browser.selectDropdownList(".id", "DVD", siteAttr.DVD);
		}

		if (siteAttr.radio != null && !siteAttr.radio.equals("")) {
			browser.selectDropdownList(".id", "Radio", siteAttr.radio);
		}

		if (siteAttr.satelliteTVAccess != null
				&& !siteAttr.satelliteTVAccess.equals("")) {
			browser.selectDropdownList(".id", "Satellite TV Access",
					siteAttr.satelliteTVAccess);
		}

		if (siteAttr.television != null && !siteAttr.television.equals("")) {
			browser
					.selectDropdownList(".id", "Television",
							siteAttr.television);
		}

		if (siteAttr.VCR != null && !siteAttr.VCR.equals("")) {
			browser.selectDropdownList(".id", "VCR", siteAttr.VCR);
		}

		if (siteAttr.furnished != null && !siteAttr.furnished.equals("")) {
			browser.selectDropdownList(".id", "Furnished", siteAttr.furnished);
		}

		if (siteAttr.airCondition != null && !siteAttr.airCondition.equals("")) {
			browser.selectDropdownList(".id", "Air Conditioning",
					siteAttr.airCondition);
		}

		if (siteAttr.electricHeating != null
				&& !siteAttr.electricHeating.equals("")) {
			browser.selectDropdownList(".id", "Electric Heating",
					siteAttr.electricHeating);
		}

		if (siteAttr.gasHeating != null && !siteAttr.gasHeating.equals("")) {
			browser.selectDropdownList(".id", "Gas Heating",
					siteAttr.gasHeating);
		}

		if (siteAttr.oilHeating != null && !siteAttr.oilHeating.equals("")) {
			browser.selectDropdownList(".id", "Oil Heating",
					siteAttr.oilHeating);
		}

		if (siteAttr.propaneHeating != null
				&& !siteAttr.propaneHeating.equals("")) {
			browser.selectDropdownList(".id", "Propane Heating",
					siteAttr.propaneHeating);
		}

		if (siteAttr.woodHeating != null && !siteAttr.woodHeating.equals("")) {
			browser.selectDropdownList(".id", "Wood Heating",
					siteAttr.woodHeating);
		}

		if (siteAttr.woodStoveOrFirePlace != null
				&& !siteAttr.woodStoveOrFirePlace.equals("")) {
			browser.selectDropdownList(".id", "Woodstove/Fireplace",
					siteAttr.woodStoveOrFirePlace);
		}

		if (siteAttr.hikeDistance != null && siteAttr.hikeDistance.length() > 0) {
			browser.setTextField(".id", "Hike In Distance to Site",
					siteAttr.hikeDistance);
		}

		if (siteAttr.horseHitchingPost != null
				&& !siteAttr.horseHitchingPost.equals("")) {
			browser.selectDropdownList(".id", "Horse Hitching Post",
					siteAttr.horseHitchingPost);
		}

		if (siteAttr.horseStallOrCorral != null
				&& !siteAttr.horseStallOrCorral.equals("")) {
			browser.selectDropdownList(".id", "Horse Stall/Corral",
					siteAttr.horseStallOrCorral);
		}

		if (siteAttr.dishWasher != null && !siteAttr.dishWasher.equals("")) {
			browser.selectDropdownList(".id", "Dish-Washer",
					siteAttr.dishWasher);
		}

		if (siteAttr.foodLocker != null && !siteAttr.foodLocker.equals("")) {
			browser.selectDropdownList(".id", "Food Locker",
					siteAttr.foodLocker);
		}

		if (siteAttr.fridge != null && !siteAttr.fridge.equals("")) {
			browser.selectDropdownList(".id", "Fridge", siteAttr.fridge);
		}

		if (siteAttr.fridgeWithFreezer != null
				&& !siteAttr.fridgeWithFreezer.equals("")) {
			browser.selectDropdownList(".id", "Fridge with Freezer",
					siteAttr.fridgeWithFreezer);
		}

		if (siteAttr.microwave != null && !siteAttr.microwave.equals("")) {
			browser.selectDropdownList(".id", "Microwave", siteAttr.microwave);
		}

		if (siteAttr.stoveorfridge != null
				&& !siteAttr.stoveorfridge.equals("")) {
			browser.selectDropdownList(".id", "Stove/Fridge",
					siteAttr.stoveorfridge);
		}

		if (siteAttr.stoveOrOven != null && !siteAttr.stoveOrOven.equals("")) {
			browser.selectDropdownList(".id", "Stove/Oven",
					siteAttr.stoveOrOven);
		}

		if (siteAttr.suplies != null && !siteAttr.suplies.equals("")) {
			this.selectSupplies(siteAttr.suplies);
		}

		if (siteAttr.clothesDryer != null && !siteAttr.clothesDryer.equals("")) {
			browser.selectDropdownList(".id", "Clothes-Dryer",
					siteAttr.clothesDryer);
		}

		if (siteAttr.clothesLine != null && !siteAttr.clothesLine.equals("")) {
			browser.selectDropdownList(".id", "Clothes-Line",
					siteAttr.clothesLine);
		}

		if (siteAttr.clothesWasher != null
				&& !siteAttr.clothesWasher.equals("")) {
			browser.selectDropdownList(".id", "Clothes-Washer",
					siteAttr.clothesWasher);
		}

		if (siteAttr.learnToShelter != null
				&& !siteAttr.learnToShelter.equals("")) {
			browser.selectDropdownList(".id", "Lean To/Shelter",
					siteAttr.learnToShelter);
		}

		if (siteAttr.lighting != null && !siteAttr.lighting.equals("")) {
			this.selectLighting(siteAttr.lighting);
		}

		if (siteAttr.livingRoom != null && !siteAttr.livingRoom.equals("")) {
			browser.selectDropdownList(".id", "Living Room",
					siteAttr.livingRoom);
		}

		if (siteAttr.locationRating != null
				&& !siteAttr.locationRating.equals("")) {
			browser.selectDropdownList(".id", "Location Rating",
					siteAttr.locationRating);
		}

		if (siteAttr.lookingForCategory != null
				&& !siteAttr.lookingForCategory.equals("")) {
			this.selectLookingForCategory(siteAttr.lookingForCategory);
		}

		if (siteAttr.nonSmoking != null && !siteAttr.nonSmoking.equals("")) {
			browser.selectDropdownList(".id", "Non Smoking",
					siteAttr.nonSmoking);
		}

		if (siteAttr.numOfRoom != null && !siteAttr.numOfRoom.equals("")) {
			browser.selectDropdownList(".id", "Number of Rooms",
					siteAttr.numOfRoom);
		}

		if (siteAttr.petAllowed != null && !siteAttr.petAllowed.equals("")) {
			this.selectAllowedPets(siteAttr.petAllowed);
		}

		if (siteAttr.BBQ != null && !siteAttr.BBQ.equals("")) {
			browser.selectDropdownList(".id", "BBQ", siteAttr.BBQ);
		}

		if (siteAttr.campFire != null && !siteAttr.campFire.equals("")) {
			browser.selectDropdownList(".id", "Campfire Allowed",
					siteAttr.campFire);
		}

		if (siteAttr.firePit != null && !siteAttr.firePit.equals("")) {
			browser.selectDropdownList(".id", "Fire Pit", siteAttr.firePit);
		}

		if (siteAttr.grillsOrFireRing != null
				&& !siteAttr.grillsOrFireRing.equals("")) {
			browser.selectDropdownList(".id", "Grills/Fire Ring",
					siteAttr.grillsOrFireRing);
		}

		if (siteAttr.picnicTable != null && !siteAttr.picnicTable.equals("")) {
			browser.selectDropdownList(".id", "Picnic Table",
					siteAttr.picnicTable);
		}

		if (siteAttr.deck != null && !siteAttr.deck.equals("")) {
			browser.selectDropdownList(".id", "Deck", siteAttr.deck);
		}

		if (siteAttr.porch != null && !siteAttr.porch.equals("")) {
			browser.selectDropdownList(".id", "Porch", siteAttr.porch);
		}
		if (siteAttr.privacy != null && !siteAttr.privacy.equals("")) {
			browser.selectDropdownList(".id", "Privacy", siteAttr.privacy);
		}

		if (siteAttr.proximityToWater != null
				&& !siteAttr.proximityToWater.equals("")) {
			this.selectProximityToWater(siteAttr.proximityToWater);
		}

		if (siteAttr.quietArea != null && !siteAttr.quietArea.equals("")) {
			browser.selectDropdownList(".id", "Quiet Area", siteAttr.quietArea);
		}

		if (siteAttr.shade != null && !siteAttr.shade.equals("")) {
			browser.selectDropdownList(".id", "Shade", siteAttr.shade);
		}

		if (siteAttr.siteAccess != null && !siteAttr.siteAccess.equals("")) {
			this.selectSiteAccess(siteAttr.siteAccess);
		}

		if (siteAttr.siteRating != null && !siteAttr.siteRating.equals("")) {
			browser.selectDropdownList(".id", "Site Rating",
					siteAttr.siteRating);
		}

		if (siteAttr.siteReserveType != null
				&& !siteAttr.siteReserveType.equals("")) {
			browser.selectDropdownList(".id", "Site Reserve Type",
					siteAttr.siteReserveType);
		}

		if (siteAttr.faxHookup != null && !siteAttr.faxHookup.equals("")) {
			browser.selectDropdownList(".id", "Fax Hookup", siteAttr.faxHookup);
		}

		if (siteAttr.internetAccess != null
				&& !siteAttr.internetAccess.equals("")) {
			browser.selectDropdownList(".id", "Internet Access",
					siteAttr.internetAccess);
		}

		if (siteAttr.telephone != null && !siteAttr.telephone.equals("")) {
			browser.selectDropdownList(".id", "Telephone", siteAttr.telephone);
		}

		if (siteAttr.telephoneHookup != null
				&& !siteAttr.telephoneHookup.equals("")) {
			browser.selectDropdownList(".id", "Telephone Hookup",
					siteAttr.telephoneHookup);
		}

		if (siteAttr.platform != null && !siteAttr.platform.equals("")) {
			browser.selectDropdownList(".id", "Platform", siteAttr.platform);
		}

		if (siteAttr.tentPad != null && !siteAttr.tentPad.equals("")) {
			browser.selectDropdownList(".id", "Tent Pad", siteAttr.tentPad);
		}

		if (siteAttr.tentPadLength != null
				&& siteAttr.tentPadLength.length() > 0) {
			browser.setTextField(".id", "Tent Pad Length",
					siteAttr.tentPadLength);
		}

		if (siteAttr.tentPadWidth != null && siteAttr.tentPadWidth.length() > 0) {
			browser
					.setTextField(".id", "Tent Pad Width",
							siteAttr.tentPadWidth);
		}

		if (siteAttr.typeOfUse != null && siteAttr.typeOfUse.length() > 0) {
			browser
					.selectDropdownList(".id", "Type of Use",
							siteAttr.typeOfUse);
		}

		if (siteAttr.maxWidthOfCamper != null
				&& siteAttr.maxWidthOfCamper.length() > 0) {
			browser.setTextField(".id",
					"Max Width of Camper, includes popouts",
					siteAttr.maxWidthOfCamper);
		}

		if (siteAttr.linens != null && siteAttr.linens.length() > 0) {
			browser.selectDropdownList(".id", "Linens", siteAttr.linens);
		}

		if (siteAttr.numberOfBunkBeds != null
				&& siteAttr.numberOfBunkBeds.length() > 0) {
			browser.setTextField(".id", "Number of Bunk Beds",
					siteAttr.numberOfBunkBeds);
		}

		if (siteAttr.numberOfDoubleBeds != null
				&& siteAttr.numberOfDoubleBeds.length() > 0) {
			browser.setTextField(".id", "Number of Double Beds",
					siteAttr.numberOfDoubleBeds);
		}

		if (siteAttr.numberOfQueenBeds != null
				&& siteAttr.numberOfQueenBeds.length() > 0) {
			browser.setTextField(".id", "Number of Queen Beds",
					siteAttr.numberOfQueenBeds);
		}

		if (siteAttr.numberOfKingBeds != null
				&& siteAttr.numberOfKingBeds.length() > 0) {
			browser.setTextField(".id", "Number of King Beds",
					siteAttr.numberOfKingBeds);
		}

		if (siteAttr.numberOfSingleBeds != null
				&& siteAttr.numberOfSingleBeds.length() > 0) {
			browser.setTextField(".id", "Number of Single Beds",
					siteAttr.numberOfSingleBeds);
		}

		if (siteAttr.numberOfSofaSleepers != null
				&& siteAttr.numberOfSofaSleepers.length() > 0) {
			browser.setTextField(".id", "Number of Sofa Sleepers",
					siteAttr.numberOfSofaSleepers);
		}

		if (siteAttr.classOfCamping != null
				&& siteAttr.classOfCamping.length() > 0) {
			browser.setTextField(".id", "Class of Camping",
					siteAttr.classOfCamping);
		}

		if (siteAttr.multimedia != null && siteAttr.multimedia.length() > 0) {
			this.selectMultimedia(siteAttr.multimedia);
		}

		if (siteAttr.showers != null && siteAttr.showers.length() > 0) {
			browser.selectDropdownList(".id", "Showers", siteAttr.showers);
		}

		if (siteAttr.maidOrCleaningService != null
				&& siteAttr.maidOrCleaningService.length() > 0) {
			browser.selectDropdownList(".id", "Maid/Cleaning Service",
					siteAttr.maidOrCleaningService);
		}

		if (siteAttr.golfPackage != null && siteAttr.golfPackage.length() > 0) {
			browser.setTextField(".id", "Golf Package", siteAttr.golfPackage);
		}

		if (siteAttr.freezer != null && siteAttr.freezer.length() > 0) {
			browser.selectDropdownList(".id", "Freezer", siteAttr.freezer);
		}
	}

	

	/** Click OK button */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("O(K|k)", false));
	}

	/** Click Apply button */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	/**click sites tab*/
	public void clickSites() {
//		browser.clickGuiObject(".className", "tabanchor", ".text", "Sites");
		browser.clickGuiObject(sitesLink());
	}
	
	/**
	 * check if display error message
	 * 
	 * @return
	 */
	public boolean isErrorExist() {
		return browser.checkHtmlObjectExists(".className", "message")
				|| browser.checkHtmlObjectExists(".className","message msgerror")
				|| browser.checkHtmlObjectExists(".id","NOTSET");
	}

	public boolean isCheckInTimeEnabled(){
		return browser.checkHtmlObjectEnabled(".id", "Checkin Time");
	}
	
	public boolean isCheckOutTimeEnabled(){
		return browser.checkHtmlObjectEnabled(".id", "Checkout Time");
	}

	public String getCheckinTime() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "Checkin Time");
		String checkinTime = objs[0].getProperty(".value").toString();
		if("".equals(checkinTime)){
			checkinTime=" "+checkinTime;
		}
		checkinTime = checkinTime +this.getChenckinIspm();

		Browser.unregister(objs);
		return checkinTime;
	}
	
	public String getChenckinIspm(){
		int  ispm=0;
		String checkinIspm="";
		boolean isDisplayed = browser.checkHtmlObjectDisplayed(".id", "Checkin Time_ispm");
		if(isDisplayed){
			String flag = browser.getDropdownListValue(".id", "Checkin Time_ispm", 0);
			if(flag.equalsIgnoreCase("PM")){
				ispm = 1;
			}

			switch (ispm) {
			case 0:
				checkinIspm ="AM";
				break;
			case 1:
				checkinIspm ="PM";
				break;
			default:
				break;
			}
		}
		return checkinIspm;
	}
	
	public String getCheckoutTime() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "Checkout Time");
		String checkoutTime = objs[0].getProperty(".value").toString();
		checkoutTime = checkoutTime +this.getChenckoutIspm();

		Browser.unregister(objs);
		return checkoutTime;
	}

	public String getChenckoutIspm(){
		int  ispm=0;
		String checkoutIspm="";
		boolean isDisplayed = browser.checkHtmlObjectDisplayed(".id", "Checkout Time_ispm");
		if(isDisplayed){
			String flag = browser.getDropdownListValue(".id", "Checkout Time_ispm", 0);
			if(flag.equalsIgnoreCase("PM")){
				ispm = 1;
			}
			
			switch (ispm) {
			case 0:
				checkoutIspm ="AM";
				break;
			case 1:
				checkoutIspm ="PM";
				break;
			default:
				break;
			}
		}
		
		return checkoutIspm;
	}

	public void setDescription(String des) {
		browser.setTextArea(".id", "SiteView.description", des);
	}

	public String getDescription() {
		IHtmlObject[] objs = browser
				.getHtmlObject(".id", "SiteView.description");
		String description = objs[0].getProperty(".text").toString();

		Browser.unregister(objs);
		return description;
	}

	public void clickDeleteSite() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete this Site");
	}

	public void selectParentLoop(String loop) {
		browser.selectDropdownList(".id", "SiteView.parentID", loop);
	}

	
	public void clickValidateFees() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate Fees");
	}
	
	public void clickValidateRules() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate Rules");
	}
	
	/**click the button 'Delete this Site'*/
	public void clickDeleteThisSite(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete this Site");
	}

	/**Get warning message*/
	public String getWaringMessage() {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		String warningMes = obj[0].getProperty(".text").toString();
		Browser.unregister(obj);
		return warningMes;
	}
	
	/**
	 * Get the specific property of the object
	 * @param idValue: the value of .id property
	 * @param index
	 * @return
	 */
	public String getObjectProperty(String idValue,int index){
		IHtmlObject[] objs = browser.getHtmlObject(".id", idValue);
		String propertyValue = objs[index].getProperty(".disabled").toString();

		Browser.unregister(objs);
		return propertyValue;
	}
	
	/**
	 * Get the '.disabled' value of site info object in site detail page of inventory manager
	 * @param siteType: NSS, siteSpecific
	 * @return
	 */
	public List<String> getSiteInfoObjectsDisabledValue(String siteType){
		List<String> disabledValues = new ArrayList<String>();
		if(siteType.equals("NSS")){
//			disabledValues.add(this.getObjectProperty("SiteView.parentProductID",0));  //NSS Group1
			disabledValues.add(this.getObjectProperty("SiteView.siteTypeID",1));       //Site Type      
			disabledValues.add(this.getObjectProperty("SiteView.productRateType",1));  //Rate Type  
			disabledValues.add(this.getObjectProperty("SiteView.webReservable",0));        //Web Visible
		}else{
			disabledValues.add(this.getObjectProperty("SiteView.siteTypeID",0));       //Site Type      
			disabledValues.add(this.getObjectProperty("SiteView.productRateType",0));  //Rate Type  
		}
		
//		disabledValues.add(this.getObjectProperty("SiteView.relationType",0));         //Site Relation Type  
//		disabledValues.add(this.getObjectProperty("SiteView.siteCode",0));             //Site Code
//		disabledValues.add(this.getObjectProperty("SiteView.name",0));                 //Site Name
//		disabledValues.add(this.getObjectProperty("SiteView.description",0));          //Description
//		disabledValues.add(this.getObjectProperty("SiteView.parentID",0));             //Parent Loop
		disabledValues.add(this.getObjectProperty("SiteView.reservable",0));           //Reservable
//		disabledValues.add(this.getObjectProperty("SiteView.webReservable",0));        //Web Visible
		
		return disabledValues;
	}
	
	/**
	 * Click the button 'View Change Request Items'
	 */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
	
	/**
	 * Get .disabled property of specific object in change request mode
	 * @param idValue
	 * @return
	 */
	public String GetPropertyDisable(String idValue) {
		String disableValue = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", idValue);

        if(objs.length==1){
        	disableValue = objs[0].getProperty(".disabled");
        }else if(objs.length==2){
        	disableValue = objs[1].getProperty(".disabled");
        }else throw new ItemNotFoundException("Object doesn't exist.");
        
        Browser.unregister(objs);
        return disableValue;
	}
	
	/**
	 * Check 'Add Occupant Maximum' exist
	 * @return
	 */
	public boolean checkAddOccupantMaximum() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text", "Add Occupant Maximum");
	}
	
	/**
	 * Get .disabled property of 'Reservable' in change request mode
	 * @return
	 */
	public String GetReservableDisable() {
		String disableValue = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "SiteView.reservable");
		
        if(objs.length>0){
        	disableValue = objs[0].getProperty(".disabled");
        }else throw new ItemNotFoundException("Object doesn't exist.");
        
        Browser.unregister(objs);
        return disableValue;
	}
	
	/**
	 * Get .disabled property of 'Web Visible' in change request mode
	 * @return
	 */
	public String GetWebVisibleDisable() {
		String disableValue = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "SiteView.webReservable");
		
        if(objs.length>0){
        	disableValue = objs[0].getProperty(".disabled");
        }else throw new ItemNotFoundException("Object doesn't exist.");
        
        Browser.unregister(objs);
        return disableValue;
	}
	
	/**
	 * Get .disabled property of 'Add Occupant Maximum' in change request mode
	 * @return
	 */
	public String GetAddOccupantMaximumDisable() {
		String disableValue = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TABLE",".text", "Add Occupant Maximum");
		
        if(objs.length>0){
        	disableValue = objs[0].getProperty(".disabled");
        }else throw new ItemNotFoundException("Object doesn't exist.");
        
        Browser.unregister(objs);
        return disableValue;
	}
	
	/**
	 * Check button 'View Change Request Items' exist or not
	 * @return
	 */
	public boolean checkViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", rex);
	}
	
	/**
	 * Get the text of Site Table
	 * @return
	 */
	public String getSiteInfo(){
		String siteInfo = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Site Site ID.*|Site ID.*", false));
		if(objs.length>0){
			siteInfo = objs[0].getProperty(".text").toString();
		}else throw new ItemNotFoundException("Object doesn't exist.");
		
		return siteInfo;
	}
	
	/**
	 * Get the text of accessibleSite.
	 * @return
	 */
	public String getAccessibleSite(){
		String text = "";
		IHtmlObject[] objs = browser
		.getHtmlObject(".id", "ADA Accessible");
		if(objs.length>0){
			text= objs[0].getProperty(".value").toString();
		}
		return text;
	}
	/**
	 * Get the text of accessibleSite.
	 * @return
	 */
	public String getADAOccupantRequired(){
		String text = "";
		IHtmlObject[] objs = browser
		.getHtmlObject(".id", "Accessible Occupant Message");
		if(objs.length>0){
			text= objs[0].getProperty(".value").toString();
		}
		return text;
	}
	
	public void selectMVEFAttr(String value){
		browser.selectDropdownList(".id", "MVEF (Motor Vehicle Entrance Fee)", value);
	}
	
	public void updateMVEFAttr(String value){
		logger.info("Update site MVEF attribute to "+value);
		
		selectMVEFAttr(value);
		clickApply();
		this.waitLoading();
	}
	
	public void selectWaterHookup(String value){
		browser.selectDropdownList(".id", "Water Hookup", value);
	}
	
	public void updateWaterHookup(String value){
		logger.info("Update site Water Hookup attribute to "+value);
		
		selectWaterHookup(value);
		clickApply();
		this.waitLoading();
	}
	
	public void selectSiteType(String siteType){
		browser.selectDropdownList(".id", "SiteView.siteTypeID", siteType);
	}
}
