package com.activenetwork.qa.awo.test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import com.activenetwork.qa.awo.apiclient.util.AwoCryptoUtil;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.BrandAttribute;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.StringProcessor;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.util.CryptoUtil;
import com.activenetwork.qa.testapi.util.Email;
import com.activenetwork.qa.testapi.util.FileUtil;

public class Test {
	public static void main(String[] args) {
		
		try {
//			List<String> f=FileUtil.listFiles("C:/Workspace/automation/functest4/functest4_awo/java/com/activenetwork/qa/awo/pages/web", ".java");
//			int total=0, pages=0;
//			for(String s:f) {
////				String filename=s;
//				List<String> r=FileUtil.scanFile(s, "RegularExpression.+(contractCode|topTabIndex|parkId).+");
//				
//				if(r.size()>0) {
//					System.out.println(s);
//					System.out.println("\ttotal="+r.size());
//					for(int i=0;i<r.size();i++) {
//						System.out.println("\t"+r.get(i));
//					}
//					total+=r.size();
//					pages++;
//				}
//									
//				
//			}
//			System.out.println("total modifications="+total+" total page classes:"+pages);
			
//			String s=AwoCryptoUtil.IdentifierDecrypt("PII.KEY.1|06cff70ef550e1d06be98e64c89dd81f");
//			System.out.println(s);
//			encode();
			
//			DecimalFormat df = new DecimalFormat("#.##");
//			df.setRoundingMode(RoundingMode.HALF_UP);
//			
//			System.out.println(df.format(2.365985));
//			AwoUtil.initAwo();
//			Email e= new Email();
//			e.connect();
//			String[] es=e.list("inbox");
//			for(String s:es) {
//				System.out.println(s);
//			}
			
//			System.out.println(CryptoUtil.encrypt("3B17d@d6"));
			
//			StringProcessor p=StringProcessor.booleanor(true, new String[]{"",null,"true","visible"});
			AwoUtil.initAwo();
			Object text=WebConfiguration.getInstance().getBrandXmlAttribute(Conf.rec_BrandXml, BrandAttribute.landSearchArea("NRSO"));
			System.out.println(text);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void encode() {
		System.out.println(CryptoUtil.encrypt("3B17d@d6"));
	}

}
