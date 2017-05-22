package com.activenetwork.qa.awo.supportscripts.support.lam;

import java.io.File;

import com.activenetwork.qa.awo.datacollection.legacy.web.AttractionInfo;
import com.activenetwork.qa.awo.keywords.web.Lam;
import com.activenetwork.qa.awo.supportscripts.SupportCase;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class LocalAttractionsSetup extends SupportCase
{
	/**
	 * Script Name   : <b>LocalAttractionsSetup</b>
	 * Generated     : <b>Mar 12, 2010 2:45:37 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/12
	 * @author QA
	 */
  	private AttractionInfo attraction=new AttractionInfo();
  	private String facilityID,email,password,url,picturePath;
//  	private String listID;
//  	private String result;
  	private Lam lam=Lam.getInstance();
  	private int count=0;
  	
	public void wrapParameters(Object[] args) {
	   	startpoint=527;
	   	endpoint=527;
	   	
	  	url="http://lam1.qa.reserveamerica.com";
//	   	url="uat-lam.reserveamerica.com";
//	   	url="http://localattractionsservice.reserveamerica.com/";
	  	email="qa_automation@reserveamerica.com ";
	  	password="orms1234";
	  	
	  	dataSource=casePath+"/"+caseName;
	  	picturePath="c:/activegolf/images/";
	  	logFile="c:/"+caseName+".log";
	   	
	   	logMsg = new String[5];
		logMsg[0] = "index";
		logMsg[1] = "facilityID";
		logMsg[2] = "listID";
		logMsg[3] = "result";
		logMsg[4] = "Note";
	}
  	
  	public void execute() {
  	  	lam.invokeLam(url,email,password);
  	  	if(verifyData(attraction)) {
  	  	  String[] msg=lam.createNewListIntoPaymentPage(attraction);
  	  	  logMsg[2]=msg[0];
  	  	  logMsg[3]="Successful";
  	  	  if(msg.length>1) {
  	  	    	if(logMsg[4].length()>0) {
  	  	    	  	logMsg[4]+=";";
  	  	    	}
  	  	    	logMsg[4]+=msg[1];
  	  	  }
  	  	  count++;
  	  	} 
  	  	
  	  	if(count!=0 && count % 30==0) {
  	  	  	browser.closeAllBrowsers();
  	  	}
  	}
  	
  	public void getNextData() {
  	  	facilityID=dpIter.dpString("FacilityID");
  	  	attraction.category="Sports and Recreations";
  	  	attraction.attractionName=dpIter.dpString("CourseName").replaceAll("\ufffd","'");
  	  	attraction.phone=dpIter.dpString("Phone");
  	  	attraction.streetName=dpIter.dpString("Address");
  	  	attraction.city=dpIter.dpString("City");
  	  	attraction.state=dpIter.dpString("State");
  	  	attraction.state=attraction.state.replaceAll("\\(\\w{2}\\)","").trim();
  	  	attraction.zip=dpIter.dpString("ZipCode");
  	  	attraction.description=dpIter.dpString("Description").replaceAll("\ufffd","'");
  	  	attraction.logoFile=dpIter.dpString("LogoURL");
  	  	attraction.pictureFile=dpIter.dpString("PhotoURL");
  	  	attraction.linkAddress=dpIter.dpString("FacilityPageURL");
  	  	attraction.linkDescription="Tee Times at "+attraction.attractionName;
  	  	attraction.acceptSpellingAsIs=true;
  	  	
  	  	int index=attraction.logoFile.lastIndexOf("/");
	  	if(index>0) {
	  	  	attraction.logoFile=attraction.logoFile.substring(index+1);
	  	}
	  	
	  	index=attraction.pictureFile.lastIndexOf("/");
	  	if(index>0) {
	  	  	attraction.pictureFile=attraction.pictureFile.substring(index+1);
	  	}
  	  	
  	  	if(attraction.logoFile.length()>0) {
  	  	  	attraction.logoFile=picturePath+attraction.logoFile;
  	  	}
  	  	if(attraction.pictureFile.length()>0) {
  	  	  	attraction.pictureFile=picturePath+attraction.pictureFile;
  	  	}   	  	
//  	  	System.out.println(attraction.description);
  	  	
  	  	logMsg[0] = cursor+"";
		logMsg[1] = facilityID;
		logMsg[2] = "unknown";
		logMsg[3] = "Failed";
		logMsg[4] = "none";
  	  	
  	}
  	
//  	private boolean resultFilter() {
//	  	if(result.equalsIgnoreCase("Skipped due to invalid zip code")) {
//		  	return true;
//		} else if (result.equalsIgnoreCase("Failed--Cannot get screen point for subitem")) {
//		  	return true;
//		} else if (result.equalsIgnoreCase("Failed--Oops page")) {
//		  	return true;
//		} else if (result.equalsIgnoreCase("Failed--Server down")) {
//		  	return true;
//		} else if (result.equalsIgnoreCase("Skipped due to attraction name >40 chars")) {
//		  	return true;
//		} 
//		
//		return false;
//  	}
  	
  	private boolean verifyData(AttractionInfo info) {
  	  	boolean valid=false;
  	  	String msg="";
  	  	
  	  	if(info.attractionName.length()>75) {
  	  	 	logMsg[3]="Skipped due to attraction name >75 chars";
  	  	} else if(info.description.length()>500) {
  	  	  	logMsg[3]="Skipped due to description >500 chars";
  	  	} else if(info.description.length()<1) {
	  	  	logMsg[3]="Skipped due to description empty";
  	  	} else if(!info.zip.matches("\\d{4,5}|\\w\\d\\w \\d\\w\\d")) {
	  	  	logMsg[3]="Skipped due to zip code \""+info.zip+"\" is not valid";
  	  	} else if(info.phone.length()<1) {
	  	  	logMsg[3]="Skipped due to phone number missing";
  	  	} else {
		  	if(info.zip.length()<5) {
		  	  	info.zip="0"+info.zip;
		  	  	msg=appendMsg(msg,"Add a leading 0 to zip");
		  	}
  	  	  	
	  	  	if(info.logoFile!=null && info.logoFile.length()>0 && !fileExists(info.logoFile)) {	
	  	  	  	msg=appendMsg(msg,"Logo not found");
		  	  	info.logoFile="";
		  	} 
	  	  	
	  	  	if(info.pictureFile!=null && info.pictureFile.length()>0 && !fileExists(info.pictureFile)) {
	  	  	  	msg=appendMsg(msg,"Picture not found");
		  	  	info.pictureFile="";
		  	} 
		  	
		  	if(info.linkDescription.length()>40) {
		  	  	info.linkDescription=info.attractionName;
		  	  	if(info.linkDescription.length()>40) {
		  	  	  	info.linkDescription=info.linkDescription.substring(0,40);
		  	  	  	msg=appendMsg(msg,"Link description > 40, use FacilityName and truncate to 40");
		  	  	} else {
		  	  	  	msg=appendMsg(msg,"Link description > 40, use FacilityName instead");
		  	  	}
			}
		  	
		  	if(msg.length()>0) {
		  	  logMsg[4]=msg;
		  	}
  	  	
  	  	  	valid=true;
  	  	}
  	  	
  	  	return valid;  	  	
  	}
  	
  	private boolean fileExists(String fullName) {
  	  	File file=new File(fullName);
  	  	
  	  	return file.isFile();
  	}
  	
  	private String appendMsg(String msg, String text) {
  	  	if(text.length()<1) {
  	  	  	return msg;
  	  	}
	  	if(msg.length()>0) {
	  	  	msg +=";";
	  	}
	  	msg +=text;
	  	
	  	return msg;
  	}
}

