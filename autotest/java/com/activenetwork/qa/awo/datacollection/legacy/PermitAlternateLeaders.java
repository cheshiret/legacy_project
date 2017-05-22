package com.activenetwork.qa.awo.datacollection.legacy;

public class PermitAlternateLeaders {
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

	private String[] phones = { "9052866600", "9052866600", "9052866600" };

	public String[] getPhones() {
		return phones;
	}

	public void setPhones(String[] phones) {
		this.phones = phones;
	}

	private int size = 3;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void initialParam(int rows){
		fNames=new String[rows];
		lNames=new String[rows];
		phones=new String[rows];
		size=rows;
		for(int i=0;i<rows;i++){
			fNames[i]="QA"+i;
			lNames[i]="TESTER"+i;
			phones[i]="905286660"+i;
		}
	}
}
