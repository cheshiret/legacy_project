/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jwang8
 * @Date  Jun 20, 2012
 */
public class LicMgrTransferDetailPage extends LicMgrRegisterVehicleDetailsPage{
	private static LicMgrTransferDetailPage instance=null;
		
		public static LicMgrTransferDetailPage getInstance(){
			if(instance==null){
				instance=new LicMgrTransferDetailPage();
			}
			return instance;
		}
		
		public boolean exists() {
			return browser.checkHtmlObjectExists(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.customerNumber",false));
		}
		
	    /**
	     * set vehicle Title Boat Value;
	     * @param boatValue
	     */
	    public void setVehicleTitleBoatValue(String boatValue){
	    	browser.setTextField(".id", new RegularExpression("EditTransferedVehicleUI-\\d+\\.vehicleValue:CURRENCY_INPUT",false), boatValue);
	    }
	    /**
	     * set date of birth.
	     * @param dateOfBirth
	     */
	    public void setDateOfBirth(String dateOfBirth){
	    	browser.setTextField(".id", new RegularExpression("TitleLienView-\\d+\\.lienDate_ForDisplay",false), dateOfBirth);
	    }
	    /**
	     * set lien amount.
	     * @param amount
	     */
	    public void setLienAmount(String amount){
	    	browser.setTextField(".id", new RegularExpression("TitleLienView-\\d+\\.amount:ZERO_TO_NULL",false), amount);
	    }
	    
	    public void clickOkButton(){
	    	browser.clickGuiObject(".class", ".Html.A", ".text", "OK");
	    }
	    /*
	     * set MotorSerial info.
	     */
	    public void setMotorSerial(String serial){
	    	browser.setTextField(".id", new RegularExpression("VehicleDetailView-\\d+\\.serialNum",false), serial);
	    }
	  
	    /**
	     * select motor fuel info.
	     * @param motorFuel
	     */
	    public void selectMotorFuel(String motorFuel){
	    	IHtmlObject[] objs = browser.getHtmlObject(".class","Html.LABEL",".text", "Motor Fuel");
			String id = objs[0].getProperty("for");
			
			if(motorFuel.equals("")){
				browser.selectDropdownList(".id", id, 0);
			}else{
				browser.selectDropdownList(".id", id, motorFuel);
			}
			
			Browser.unregister(objs);
	    }
	    /**
	     * set transfer motor info.
	     * @param motor
	     */
	    public void setTransferMotorInfo(MotorInfo motor){
	    	this.setMotorSerial(motor.getSerialNum());
			this.setManufacturerName(motor.getManufacturerName());
			this.setModelYear(motor.getModelYear());
			this.setHorsePower(motor.getHorsePower());
			this.selectMotorFuel(motor.getMotorFuel());
	    }
	    
	    /**
	     * set transfer details info.
	     * @param vehicleInfo
	     */
		public void setTransferDetailsInfo(Vehicle vehicleInfo){
			if(null != ((BoatInfo)vehicleInfo).newCoOwners&& ((BoatInfo)vehicleInfo).newCoOwners.size()>0){
				this.setVehicleCoOwnerInfo(((BoatInfo)vehicleInfo).newCoOwners);
			}
			if(vehicleInfo instanceof BoatInfo) {
				if(null!=((BoatInfo)vehicleInfo).newMotors && ((BoatInfo)vehicleInfo).newMotors.size()>0){
					this.setVehicleMotorsInfo(((BoatInfo)vehicleInfo).newMotors);
				}
			}
			if(null != vehicleInfo.title.boatValue && vehicleInfo.title.boatValue.length()>0){
				this.setVehicleTitleBoatValue(vehicleInfo.title.boatValue);
			}
			
			if(null !=vehicleInfo.title.lienInfo){
				if(null != vehicleInfo.title.lienInfo.getDateOfLien() && vehicleInfo.title.lienInfo.getDateOfLien().length() >0){
					this.setDateOfBirth(vehicleInfo.title.lienInfo.getDateOfLien());
				}
				if(null != vehicleInfo.title.lienInfo.getLienAmount() && vehicleInfo.title.lienInfo.getLienAmount().length() >0){
					this.setLienAmount(vehicleInfo.title.lienInfo.getLienAmount());
				}
			}
			//TODO (Need add the lien company Name)
			//this.clickOK();
			//ajax.waitLoading();
			//this.waitExists();
			//if(null != vehicleInfo.transferMotor){
				
			//}
		}
}
