package com.activenetwork.qa.awo.datacollection.legacy.orms;
/**
 * @Description: pos supplier setup.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author jwang8
 * @Date  Mar 27, 2012
 */
public class PosSupplier {
    
    public String id = "";
    public String name = "";
    public String status = "";
   
    public String location = "";
    public String description = "";
    public String webSite = "";
    public String address = "";
    public Address orderAddress = new Address();
    public Address paymentAddress = new Address();
    public Poc primPoc = new Poc();
    public Poc otherPoc = new Poc();
    public String paymentTerms = "";
    public String paymentMethod = "";
    public String shippingMethod = "";
    public String fobPoint = "";
    public String freightTerms = "";
    public boolean isPaymentAddrSameOrderAddr = true;

    public String searchStatus = "";
    public String searchByStatus = "";
//    public String assigned = "";
    public boolean assigned = false;
    
    public class Poc{
    	public String lastName = "";
    	public String firstName = "";
    	public String phone = "";
    	public String fax = "";
    	public String email = "";
    }
}
