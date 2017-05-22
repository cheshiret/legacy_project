package com.activenetwork.qa.awo.datacollection.legacy;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Feb 26, 2012
 */
public class PermitGroupMembers {
	private String[] fNames = { "QA1", "QA2", "QA3" };

	public String[] getfNames() {
		return fNames;
	}

	public void setfNames(String[] fNames) {
		this.fNames = fNames;
	}

	private String[] lNames = { "TESTER1", "TESTER2", "TESTER3" };

	public String[] getlNames() {
		return lNames;
	}

	public void setlNames(String[] lNames) {
		this.lNames = lNames;
	}

	private String[] Comments = { fNames[0]+"_"+lNames[0], fNames[1]+"_"+lNames[1], fNames[2]+"_"+lNames[2]};

	public String[] getComments() {
		return Comments;
	}

	public void setComments(String[] comments) {
		Comments = comments;
	}

	private int size = 3;
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void initialParam(int groupSize){
		fNames=new String[groupSize];
		lNames=new String[groupSize];
		Comments=new String[groupSize];
		size=groupSize;
		for(int i=0;i<groupSize;i++){
			fNames[i]="QA"+i;
			lNames[i]="TESTER"+i;
			Comments[i]=fNames[i]+"_"+lNames[i];
		}
	}
	
}
