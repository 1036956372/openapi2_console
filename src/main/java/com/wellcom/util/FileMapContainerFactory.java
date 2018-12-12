package com.wellcom.util;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


import org.apache.log4j.Logger;
import org.g4studio.core.metatype.impl.BaseDto;

import com.fourinone.FttpException;



public class FileMapContainerFactory {
	static Logger logger = Logger.getLogger(FileMapContainerFactory.class.getName());
	/**
	 * 监控对象Map
	 * key: MD5（文件夹路径）  
	 * value: List(BaseDto)   filename\filesize\filepath\isfile  文件属性信息
	 */
	private static final ConcurrentMap<String, List<BaseDto>> fileMap = new ConcurrentHashMap<String, List<BaseDto>>();
	/**
	 * 监控对象Map
	 * key: MD5（文件夹路径）
	 * value: 创建时间
	 */
	private static final ConcurrentMap<String, Long> fileCreateTimeMap = new ConcurrentHashMap<String, Long>();

	
	private static final String FTTP="FTTP://";
	
	/**
	 * 从缓存中获取远程文件集合
	 * @return
	 * @throws FttpException 
	 */
	public static List<BaseDto>  getDirFiles(String iphost, String  dir) throws FttpException{
		//"fttp://192.168.193.240/d:/appopenapi2/log/minutes/"
		String fttppath= FTTP+ iphost+"/"+dir;
		logger.info(fttppath);
		String fttppath_md5 = MD5Util.MD5(fttppath);
		List<BaseDto>  fileList = fileMap.get(fttppath_md5);
		Long createtime = fileCreateTimeMap.get(fttppath_md5);
		if(fileList == null || System.currentTimeMillis() - createtime > 1000*60*5){
			//获取远程文件信息
			fileList = FttpUtil.getFilesInDir(fttppath);
			fileMap.put(fttppath_md5, fileList);
			fileCreateTimeMap.put(fttppath_md5, System.currentTimeMillis());
		}
		
		return fileList;
	}
	
	/**
	 * 从缓存中获取远程文件集合
	 * @return
	 * @throws FttpException 
	 */
	public static List<BaseDto>  getDirFiles(String fttppath) throws FttpException{
		//"fttp://192.168.193.240/d:/appopenapi2/log/minutes/"
		logger.info(fttppath);
		String fttppath_md5 = MD5Util.MD5(fttppath);
		List<BaseDto>  fileList = fileMap.get(fttppath_md5);
		Long createtime = fileCreateTimeMap.get(fttppath_md5);
		if(fileList == null || System.currentTimeMillis() - createtime > 1000*60*5){
			//获取远程文件信息
			fileList = FttpUtil.getFilesInDir(fttppath);
			fileMap.put(fttppath_md5, fileList);
			fileCreateTimeMap.put(fttppath_md5, System.currentTimeMillis());
		}
		
		return fileList;
	}
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String logpath = "D:/logs/huzhou-new/app-jiekou/";
			//System.out.println(logpath.replace("\\", "/"));
			
			List<BaseDto> list = FileMapContainerFactory.getDirFiles("192.168.193.73", logpath);
		     System.out.println(list.size());
		} catch (FttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
