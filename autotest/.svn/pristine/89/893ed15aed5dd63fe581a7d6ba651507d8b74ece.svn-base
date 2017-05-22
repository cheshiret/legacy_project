package com.activenetwork.qa.awo.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTReturnTransactionInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.SSH2;
import com.activenetwork.qa.testapi.util.TestProperty;

public class TransmissionAndReturnFileUtil {
    public EFTTransmissionFileInfo tranFileInfo = new EFTTransmissionFileInfo();
    public EFTReturnFileInfo  returnFileInfo = new EFTReturnFileInfo();
    public String transmissionFileName;
    public String transmissionId;
    public String returnFileName;
    public String processedReturnFileName;
    public String returnFilePathProcessedPlaceOnApp;
    public String returnFilePathFailedPlaceOnApp;
    public String returnFilePathNonProPlaceOnApp;
    public String transmissionFilePathOnApp;
    public String localReturnPath;
    public String localTransPath;
    public String env;
 	private String host;
 	private String user;
 	private String password;
 	public TransmissionAndReturnFileUtil(){
 		
 	}
 	
    public TransmissionAndReturnFileUtil(String env, String localPath){
    	this.setEnvInfo(env,localPath);
    }
    
   public TransmissionAndReturnFileUtil(String env, String localPath, String transId){
	   this.setEnvInfo(env,localPath);
	   transmissionId = transId;
	   transmissionFileName = "MS"+ "*" + transmissionId +"-QA.nacha";
    } 
   
   public TransmissionAndReturnFileUtil(String env, String localPath, String transId,String transFile){
	   this.setEnvInfo(env,localPath);
	   transmissionId = transId;
	   transmissionFileName = transFile;
    } 
   
   private void setEnvInfo(String env, String localPath){
	   this.env = env;
	   host = TestProperty.getProperty(env+".orms.appsvr1");
	   user = TestProperty.getProperty(env+".appsvr.login");
	   password = TestProperty.getProperty(env+".appsvr.pw");
	   returnFilePathProcessedPlaceOnApp = "/home/finance/incoming/" + env +  "/eft/ms/returns/processed";
	   returnFilePathFailedPlaceOnApp = "/home/finance/incoming/" + env +  "/eft/ms/returns/failed";
	   returnFilePathNonProPlaceOnApp = "/home/finance/incoming/" + env +  "/eft/ms/returns/new";
	   transmissionFilePathOnApp = "/home/finance/incoming/" + env +  "/eft/ms/returns/failed";
       transmissionFilePathOnApp = "/home/finance/incoming/" + env +  "/eft/ms/transmissions";
       localTransPath = localPath + "/Transmission";
       localReturnPath = localPath + "/Return";
   }
   
    public void preparedForReturnJob(String entryDesc, String vendorName){
    	tranFileInfo.transFile = getTransmissionFileFromAppServer();
    	tranFileInfo.parserTransmissionFile();
		tranFileInfo.getDetailInfoOfBatch();
		returnFileName = generateReturnFile(entryDesc, vendorName); //"INVOICE","EFT Vendor"
		sendReturnFileToAppServ(returnFileName);
    }
        
    public void sendReturnFileToAppServ(String fileName){
      	 String fromFileInlocal = localReturnPath+"/"+ fileName;
      	 String toFileOnApp = returnFilePathNonProPlaceOnApp + "/" +fileName;
      	 SSH2 ssh = SSH2.getInstance();
      	 ssh.connect(user, password, host);

      	 ssh.scpTo(fromFileInlocal, toFileOnApp);
      	 ssh.disconnect();
    }
    /**
     * Get transmission file from App server and saved the same name as transmission file
     * @return
     */
    public String getTransmissionFileFromAppServer(){
   	 String transmissionFile = transmissionFilePathOnApp +"/" + transmissionFileName;
   	 File file = new File(localTransPath);
   	 if (!file.exists()) {
				boolean success = file.mkdir();
				if (!success) {
					throw new ItemNotFoundException(
					"Failed To Make Directory.");
				}
			}
   	 String localTransmissionFile = localTransPath +"/"+transmissionFileName;
   	 SSH2 ssh = SSH2.getInstance();
   	 ssh.connect(user, password, host);
   	 localTransmissionFile = ssh.scpFrom(transmissionFile, localTransmissionFile);
   	 ssh.disconnect();   	 
   	 return localTransmissionFile;   	 
    }
    
    public String getProcessedReturnFileFromAppServer(){      	
      	 String returnFileOnApp = returnFilePathProcessedPlaceOnApp + "/" + processedReturnFileName;      	 
      	 File file = new File(localReturnPath);
      	 if (!file.exists()) {
   				boolean success = file.mkdir();
   				if (!success) {
   					throw new ItemNotFoundException(
   					"Failed To Make Directory.");
   				}
   			}
      	 String localReturnFile = localReturnPath +"/"+processedReturnFileName;
      	 SSH2 ssh = SSH2.getInstance();
      	 ssh.connect(user, password, host);
      	 ssh.scpFrom(returnFileOnApp, localReturnFile);
      	 ssh.disconnect();
      	 return localReturnFile;
       }
    
    public String generateReturnFile(String description, String vendor){    
    	String returnfile;
    	String returnContent = tranFileInfo.fileHeaderRecord; //[Content]----Add file header record
    	int totalLine = 1;
    	int entryAddendaNum = 0;
    	int totalRountingNum = 0;
    	String debitAmount="", creditAmount="";
    	for(transimissionBatch batch:tranFileInfo.batches){  
    		if(batch.description.trim().equals(description)){
    			returnContent += batch.companyBatchHeaderRecord; //[Content]----Add batch header record
    			totalLine += 1;			
    			
    			int totalAmount = 0;
    			for(int i=0;i<batch.entryInfo.size();i++){
    				if(batch.entryInfo.get(i).get("vendorName").trim().equals(vendor)){
    					int transactionCode = Integer.parseInt(batch.entryInfo.get(i).get("transactionCode")) - 1;
    					String withAddendaRecord = "1";
    					String entryReturn = "6"  + transactionCode
		    							          + batch.entryInfo.get(i).get("bankAccountRountingNum")
		    							          + batch.entryInfo.get(i).get("TransitRoutingCheckDigit")
		    							          + batch.entryInfo.get(i).get("vendorBankAccount")
		    							          + batch.entryInfo.get(i).get("transAmount")
		    							          + batch.entryInfo.get(i).get("vendorIdenNum")
		    							          + batch.entryInfo.get(i).get("vendorName")
		    							          + batch.entryInfo.get(i).get("bankOfAmericaDraft")
		    							          + withAddendaRecord
		    							          + batch.entryInfo.get(i).get("traceNum");
    					String returnReason = "   "; //total size:3
    					String dateOfDeath = "000000";
    				    String addendaInfor = "                                         R01";  //total size:44
    					String tranceNumber = "000000000000000"; //total size:15
    					String addendaRecord = "799" + returnReason 
    							                     + batch.entryInfo.get(i).get("traceNum")
    							                     + dateOfDeath
    							                     + batch.entryInfo.get(i).get("bankAccountRountingNum")
    							                     + addendaInfor
    							                     + tranceNumber;
    					returnContent += entryReturn + addendaRecord; //[Content]----Add entry and addenda record
    					totalLine += 2;
    					entryAddendaNum += 2;
    					totalRountingNum += Integer.valueOf(batch.entryInfo.get(i).get("bankAccountRountingNum"));
    					totalAmount += Integer.valueOf(batch.entryInfo.get(i).get("transAmount"));
    				}
    			}
    			
    			if(description.equals("INVOICE")){
    				debitAmount = this.formatAccordingToSize(12,String.valueOf(totalAmount));
    				creditAmount = "000000000000";  //total size:12
    			}else{//remittance
    				creditAmount = this.formatAccordingToSize(12,String.valueOf(totalAmount));
    				debitAmount = "000000000000";  //total size:12
    			}
    			String reserved1 = "                   "; //total size:19
    			String reserved2 = "      "; //total size:6
    			String batchControlRecord = "8" + batch.serviceClass
    					                        + formatAccordingToSize(6, String.valueOf(entryAddendaNum))
    					                        + formatAccordingToSize(10, String.valueOf(totalRountingNum))
    					                        + debitAmount + creditAmount
    					                        + batch.companyIdentification
    					                        + reserved1 + reserved2
    					                        + batch.dfiNumber
    					                        + batch.batchNumber;
    			returnContent += batchControlRecord;  //[Content]----Add batch control record   
    			totalLine += 1;
    		}
    	}
    	String batchCount = "000001";
    	int blockCount = (int)Math.ceil((double)totalLine/(double)10);
    	String reserve = "                                       "; //total size:39
    	String fileControlRecord = "9" + batchCount
    			                       + formatAccordingToSize(6, String.valueOf(blockCount))
    			                       + formatAccordingToSize(8, String.valueOf(entryAddendaNum))
    			                       + formatAccordingToSize(10, String.valueOf(totalRountingNum))
    			                       + debitAmount + creditAmount
    			                       + reserve;
    	returnContent += fileControlRecord;  //[Content]----Add file control record
    	String fulfillLine = "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999"; //total size:94
    	for(int j=totalLine%10; j<10; j++){
    		returnContent += fulfillLine; //[Content]----Add fulfill line 
    	}
    	returnfile = transmissionFileName;
      	File file = new File(localReturnPath);
       	if (!file.exists()) {
    			boolean success = file.mkdir();
    			if (!success) {
    				throw new ItemNotFoundException(
    				"Failed To Make Directory.");
    			}
    		}
    	FileUtil.generateAndWriteFile(localReturnPath+"/"+ returnfile, returnContent);
    	return returnfile;
    }
    
    /**
     * Format the string to fixed length, if it is shorter than the length, add "0" in its left
     * @param size
     * @param original
     * @return
     */
    private String formatAccordingToSize(int size, String original){
    	String formatted = "";
    	for(int j=original.length();j<size;j++){
    		formatted += "0";
    	}
    	if(original.length() > size){
    		formatted = original.substring(original.length()-size,original.length());
    	}else{
    		formatted += original;
    	}
    	
    	return formatted;
    }    
    
    public void setReturnFileName(String returnfile){
    	returnFileName = returnfile;
    }
    
    public void setProcessedReturnFileName(String returnfile, String returnJobId){
    	String returnFilePrefix = returnfile.split("\\.")[0];
    	String fileType = returnfile.split("\\.")[1];
    	processedReturnFileName = returnFilePrefix + "-" + returnJobId + "." + fileType;    	
    }
    
    public EFTReturnTransactionInfo getTransInfoAccordingtoReturnFileInfo(String returnJobId, String returnId, String returnfile, String desc){
    	this.setProcessedReturnFileName(returnfile, returnJobId);
    	EFTReturnTransactionInfo transactionInfo = new EFTReturnTransactionInfo();
    	returnFileInfo.returnFile = getProcessedReturnFileFromAppServer();
    	returnFileInfo.parserReturnFile();
    	returnFileInfo.getDetailInfoOfBatch();
      	 
      	int index = Integer.parseInt(returnId) - Integer.parseInt(returnJobId) -1;
      	 
      	for(returnBatch batch:returnFileInfo.batches){  
      		if(batch.description.trim().equals(desc)){
      			String companyBatchHeaderRecord = batch.companyBatchHeaderRecord;
      			String entryDetailRecord = batch.entryDetailRecord.get(index);
      	      	String addendaDetailRecord = batch.addendaDetailRecord.get(index);
      	      	
      	      transactionInfo.setReferenceNum(companyBatchHeaderRecord.substring(87, 94) 
    			        + "-" + entryDetailRecord.substring(79, 94));
      	      transactionInfo.setImmediateDestination(entryDetailRecord.substring(3, 12));
      	      transactionInfo.setCompanyIdentification(companyBatchHeaderRecord.substring(40,50).trim());
      	      transactionInfo.setStandardEntryClassCode(companyBatchHeaderRecord.substring(50,53));
      	      transactionInfo.setCompanyEntryDescription(companyBatchHeaderRecord.substring(53,63).trim());
      	      transactionInfo.setEffectiveEntryDate(companyBatchHeaderRecord.substring(69,75));
      	      transactionInfo.setOriginationDFIIdentification(companyBatchHeaderRecord.substring(79, 87));
      	      transactionInfo.setRDFITransitRoutingOrABANum(entryDetailRecord.substring(3, 11));
      	      transactionInfo.setCheckDigit(entryDetailRecord.substring(11, 12));
      	      transactionInfo.setOriginalEntryTranceNum(addendaDetailRecord.substring(6, 21));
      	      transactionInfo.setTransactionCode(entryDetailRecord.substring(1, 3));
      	      transactionInfo.setTransitRoutingNum(entryDetailRecord.substring(3, 11));
      	      transactionInfo.setAccountNum(entryDetailRecord.substring(12, 29));
      	      transactionInfo.setAmount("$" + Double.parseDouble(entryDetailRecord.substring(29, 39))/100);
      	      transactionInfo.setReturnReasonCode(addendaDetailRecord.substring(3, 6));
      	      transactionInfo.setIndividualId(entryDetailRecord.substring(39, 54));
      	      transactionInfo.setIndividualName(entryDetailRecord.substring(54, 76));
      	      transactionInfo.setAddendaTranceNum(addendaDetailRecord.substring(79, 94));
      	      transactionInfo.setAddendaInformation(addendaDetailRecord.substring(35, 79));
      	      transactionInfo.setReturnId(returnId);
      	    transactionInfo.setReturnJobId(returnJobId);
      		}
      	}
      	return transactionInfo;
}

    

}

class returnBatch{	
	public String companyBatchHeaderRecord = "";
	public List<String> entryDetailRecord = new ArrayList<String>();
	public List<String> addendaDetailRecord = new ArrayList<String>();
	public ArrayList<HashMap<String,String>> entryInfo= new ArrayList<HashMap<String,String>>();
	public String  batchControlRecord = "";
	public int numOfEntry;
	public int numOfAddenda;	
	public String description;
	public String companyIdentification;
	public String batchNumber;
	public String dfiNumber;
	public String serviceClass;
	public void getBatchInfo(){
		description = companyBatchHeaderRecord.substring(53, 63);	
		companyIdentification = companyBatchHeaderRecord.substring(40, 50);			
		serviceClass = batchControlRecord.substring(1,4);
		dfiNumber = companyBatchHeaderRecord.substring(79, 87);
		batchNumber = companyBatchHeaderRecord.substring(87, 94);
	    }
	}

class EFTReturnFileInfo {
	public String returnFile = "";
	public String fileHeaderRecord = "";
	public String fileControlRecord = "";
	public int numOfBatch;
	public int numOfBlock;
	public ArrayList<returnBatch> batches = new ArrayList<returnBatch>();
	
	public void parserReturnFile(){
		String content = FileUtil.read(returnFile);
	    int i = 1;
	    String line = "";
	    returnBatch batch = new returnBatch();
        while((i*94-1) < content.length()){
        	    line = content.substring((i-1)*94, i*94);
	            if(line.startsWith("101")){    //It is the file Header Record
	            	this.fileHeaderRecord = line;
	            }
	            if(line.startsWith("5200")){  //It is the Company/Batch Header Record
	            	batch.companyBatchHeaderRecord = line;
	            }
	            if(line.startsWith("6")){ //It is a entry detail record
	            	batch.entryDetailRecord.add(line);   
	                      }
	            if(line.startsWith("799")){ //It is a entry detail record
	            	batch.addendaDetailRecord.add(line);   
	                      }
	            if(line.startsWith("8")){  //Company/Batch Control Record 
	            	batch.batchControlRecord = line;   
	            	batches.add(batch);
	            	batch = new returnBatch();
	            }
	            if(line.startsWith("9")){ //File Control Record,it is the end of file, except use line with '9' to 
	            	fileControlRecord = line;
	            }
	            i++;
	         }	              
	}
	
	public void getDetailInfoOfBatch(){
		for(int i=0; i<batches.size(); i++){
			batches.get(i).getBatchInfo();
//			batches.get(i).getEntryInfo();
		}
	}
}

class transimissionBatch{	
	public String companyBatchHeaderRecord = "";
	public List<String> entryDetailRecord = new ArrayList<String>();
	public ArrayList<HashMap<String,String>> entryInfo= new ArrayList<HashMap<String,String>>();
	public String  batchControlRecord = "";
	public int numOfEntry;
	public int numOfAddenda;	
	public String description;
	public String companyIdentification;
	public String batchNumber;
	public String dfiNumber;
	public String serviceClass;
	public void getBatchInfo(){
		description = companyBatchHeaderRecord.substring(53, 63);	
		companyIdentification = companyBatchHeaderRecord.substring(40, 50);			
		serviceClass = batchControlRecord.substring(1,4);
		dfiNumber = companyBatchHeaderRecord.substring(79, 87);
		batchNumber = companyBatchHeaderRecord.substring(87, 94);
		
	}
	
	public void getEntryInfo(){
		for(int i=0;i<entryDetailRecord.size();i++){
			HashMap<String,String> temp = new HashMap<String,String>();
			temp.put("transactionCode",entryDetailRecord.get(i).substring(1, 3));
			temp.put("bankAccountRountingNum",entryDetailRecord.get(i).substring(3, 11));
			temp.put("TransitRoutingCheckDigit",entryDetailRecord.get(i).substring(11, 12));
			temp.put("vendorBankAccount",entryDetailRecord.get(i).substring(12, 29));
			temp.put("transAmount",entryDetailRecord.get(i).substring(29, 39));
			temp.put("vendorIdenNum",entryDetailRecord.get(i).substring(39, 54));
			temp.put("vendorName",entryDetailRecord.get(i).substring(54, 76));    
			temp.put("bankOfAmericaDraft",entryDetailRecord.get(i).substring(76, 78)); //blank
			temp.put("withAddendaRecord",entryDetailRecord.get(i).substring(78, 79));
			temp.put("traceNum",entryDetailRecord.get(i).substring(79, 94));
			entryInfo.add(temp);		
		}
	}
}
class EFTTransmissionFileInfo {
	public String transFile = "";
	public String fileHeaderRecord = "";
	public String fileControlRecord = "";
	public int numOfBatch;
	public int numOfBlock;
	public ArrayList<transimissionBatch> batches = new ArrayList<transimissionBatch>();
	
	public void parserTransmissionFile(){
		String content = FileUtil.read(transFile);
	    int i = 1;
	    String line = "";
	    transimissionBatch batch = new transimissionBatch();
        while((i*94-1) < content.length()){
	            line = content.substring((i-1)*94, i*94);
	            if(line.startsWith("101")){    //It is the file Header Record
	            	this.fileHeaderRecord = line;
	            }
	            if(line.startsWith("5200")){  //It is the Company/Batch Header Record
	            	batch.companyBatchHeaderRecord = line;
	            }
	            if(line.startsWith("6")){ //It is a entry detail record
	            	batch.entryDetailRecord.add(line);   
	                      }
	            if(line.startsWith("8")){  //Company/Batch Control Record 
	            	batch.batchControlRecord = line;   
	            	batches.add(batch);
	            	batch = new transimissionBatch();
	            }
	            if(line.startsWith("9")){ //File Control Record,it is the end of file, except use line with '9' to 
	            	fileControlRecord = line;
	            }
	            i++;
	         }	              
	}
	
	public void getDetailInfoOfBatch(){
		for(int i=0; i<batches.size(); i++){
			batches.get(i).getBatchInfo();
			batches.get(i).getEntryInfo();
		}
	}
}

