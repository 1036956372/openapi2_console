package com.wellcom.util;

import java.io.UnsupportedEncodingException;

import com.fourinone.BeanContext;

public class FttpServer
{
	public static void main(String[] args)
	{
		String path = FttpServer.class.getResource("/").getPath() + "config.xml";
		//System.out.println("config文件路径:" + path);
		try {
			path = java.net.URLDecoder.decode(path,"utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BeanContext.setConfigFile(path);
		BeanContext.startFttpServer("172.16.9.23");
	}
}