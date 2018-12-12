package com.wellcom.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;



public class WellMapContainerFactory {
	static Logger logger = Logger.getLogger(WellMapContainerFactory.class.getName());

	/**
	 * 课程订单请求计数器
	 *  key   : classid
	 *  int  : num++
	 */
	private static final ConcurrentMap<String, AtomicInteger> classOrderReqCountMap          = new ConcurrentHashMap<String, AtomicInteger>();  
	
	
	/**
	 * 课程订单请求计数器
	 *  key   : classid+"_"+userid
	 *  long  : 时间戳
	 */
	private static final ConcurrentMap<String, Long>   classOrderUserReqCountMap          = new ConcurrentHashMap<String, Long>();  
	
	
	 
	/**
	 * 获得当前有多少请求正在约具体某堂课的数量
	 * @param classid 课程主键
	 * @return
	 */
	public static int getClassOrderReqCount(String classid){
		AtomicInteger atom = classOrderReqCountMap.putIfAbsent(classid, new AtomicInteger(0));
		return atom.get();
	}
	
	/**
	 * 请求计数器
	 * @param classid
	 */
	public static void incrementClassOrderReqCount(String classid){
		 classOrderReqCountMap.get(classid).incrementAndGet();
	}
	
	
	/**
	 * 防止学员重复提交预约同一个课程，检查缓存中是否存在请求线程
	 * 局部变量线程安全，实例属性线程不安全，静态类的静态属性就不安全，但是静态方法里的参数是安全的
	 * @param classid  课程主键
	 * @param userid   学员主键
	 * @return
	 */
	public static boolean isExistUserReqClassOrder(String classid, String userid){
		String key=classid +"_"+userid;
		if(classOrderUserReqCountMap.containsKey(key)){
			Long lasttime = classOrderUserReqCountMap.get(key);
			if(lasttime ==null || (System.currentTimeMillis() - lasttime) > 1000 * 60)  //超过1分钟的记录，认为请求已处理完毕
				   return  false;
			else 
					return true;
		}else{
			classOrderUserReqCountMap.put(key, System.currentTimeMillis());
			 return  false;
		}
		
	}
	
	/**
	 * 约课流程结束后，请求数量需要减一
	 * @param classid
	 */
	private static void decrementClassOrderReqCount(String classid){
		  if(classOrderReqCountMap.containsKey(classid)){
			 classOrderReqCountMap.get(classid).decrementAndGet();
		   }
	}
	
	
	/**
	 * 约课流程结束后,清除用户请求记录的缓存
	 * @param classid
	 * @param userid
	 */
	private static void removeclassOrderUserReqCountMap(String classid, String userid){
		String key=classid +"_"+userid;
		classOrderUserReqCountMap.remove(key);
	}	
	
	
	/**
	 * 约课流程结束后,清除缓存
	 * @param classid
	 * @param userid
	 */
	public static void clearCacheAfterFinshOrder(String classid, String userid){
		removeclassOrderUserReqCountMap(classid, userid);
		decrementClassOrderReqCount(classid);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(WellMapContainerFactory.getClassOrderReqCount("kc1"));
		System.out.println(WellMapContainerFactory.getClassOrderReqCount("kc1"));
		System.out.println(WellMapContainerFactory.getClassOrderReqCount("kc2"));
		System.out.println(WellMapContainerFactory.isExistUserReqClassOrder("kc1", "xy1"));
		System.out.println(WellMapContainerFactory.isExistUserReqClassOrder("kc1", "xy1"));
		System.out.println(WellMapContainerFactory.isExistUserReqClassOrder("kc1", "xy1"));
		
		WellMapContainerFactory.clearCacheAfterFinshOrder("kc1", "xy1");
		System.out.println(WellMapContainerFactory.isExistUserReqClassOrder("kc1", "xy1"));
	}

}
