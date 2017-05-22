/**
 * 
 */
package com.activenetwork.qa.awo.sql;

import java.util.List;

import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author zhou
 * @Date Nov 23, 2012
 */
public class ResetPWForVeriFoneSql {
	private static AwoDatabase db;

	public ResetPWForVeriFoneSql() {
		db = AwoDatabase.getInstance();
	}
	
	public void resetPassWord(String regID,String pw,String loc){
		String password=AwoUtil.ConvertToMD5(pw);
		
		String sql ="select * from D_LOC_REGISTRY where registry_id="+regID+" and loc_id='"+loc+"'";
		List<String> pws = db.executeQuery(sql, "PASSWORD");
		if(pws.size()<1){
			throw new ErrorOnDataException("Could find record for reg id ="+regID+"in the table D_LOC_REGISTRY...");
		}else{
			if(!password.equals(pws.get(0))){
				sql ="update D_LOC_REGISTRY set password='"+password+"' where registry_id="+regID+" and loc_id='"+loc+"'";
				db.executeUpdate(sql);
				System.out.println("Reset Pass word to :"+pw);
			}else{
				System.out.println("Pass word do not need to update, it is equals to :"+pw);
			}
		}
		
	}
}
