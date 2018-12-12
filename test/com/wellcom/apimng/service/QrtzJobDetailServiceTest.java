package com.wellcom.apimng.service;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.g4studio.common.dao.Reader;
import org.g4studio.common.util.SpringBeanLoader;
import org.g4studio.core.metatype.Dto;
import org.g4studio.core.metatype.impl.BaseDto;
import org.springframework.context.ApplicationContext;

public class QrtzJobDetailServiceTest {
	private static ApplicationContext wac = null;
	static{
		try {
			wac = SpringBeanLoader.getApplicationContext();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		 Dto dto =  new BaseDto();
		
	       
		Reader g4Reader = (Reader)wac.getBean("g4Reader",Reader.class); 
	    List<BaseDto> qrtzJobDetailsList = g4Reader.queryForPage("QrtzJobDetails.queryQrtzJobDetailss", dto);
	     for(BaseDto baseDto : qrtzJobDetailsList){
	    	Set set = baseDto.entrySet();
			Iterator it=set.iterator();
			while(it.hasNext())
			{
				System.out.println("appKey:"+it.next());
			}
	     }
		
	}
}
