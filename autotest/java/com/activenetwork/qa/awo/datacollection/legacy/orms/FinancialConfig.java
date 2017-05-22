package com.activenetwork.qa.awo.datacollection.legacy.orms;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Dec 6, 2011
 */
public class FinancialConfig {
	//for vendor
	public String EFTType="";
	
	public String EFTSchedule="";
	
	public String failedEFTEnforcement="";
	
	public String voidReturnChargeDays="";
	
	public String autoReturnVoidedDoc="";
	
	public boolean EFTInvoice=false;
	
	public boolean dailySalesActivity=false;
	
	public String[] reportNotificationEmail;
	
	//for agent
	public String[] paymentGroup;
	
	public String[] creaditCardPaymentTypes;
	
	public String[] deferredPaymentTypes;
	
	public String thresholdEnforcement = "";
	
	public String thresholdAmount = "";
	
	public String thresholdAction = "";
	
	public boolean revokeIfBondExpired;
	
	public String[] notificationEmails;
	
	public String[] notificationEmailsToRemove;
}
