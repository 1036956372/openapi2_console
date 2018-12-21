/*
 * Powered By [wellcomframework]
 */

package com.wellcom.apimng.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.g4studio.common.util.WebUtils;
import org.g4studio.common.web.BaseAction;
import org.g4studio.common.web.BaseActionForm;
import org.g4studio.core.json.JsonHelper;
import org.g4studio.core.metatype.Dto;
import org.g4studio.core.metatype.impl.BaseDto;
import org.g4studio.core.mvc.xstruts.action.ActionForm;
import org.g4studio.core.mvc.xstruts.action.ActionForward;
import org.g4studio.core.mvc.xstruts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.wellcom.api.ApiDebugJson;
import com.wellcom.api.ApiException;
import com.wellcom.api.Fields;
import com.wellcom.api.WebUtilClient;
import com.wellcom.api.request.DefaultWellGetRequest;
import com.wellcom.api.request.DefaultWellPostRequest;
import com.wellcom.api.request.DownStudentChangeWellRequest;
import com.wellcom.api.request.StudentExamJdWellRequest;
import com.wellcom.api.request.Up_StuSignPhotoWellRequest;
import com.wellcom.api.util.AES;



/**	负责API命令调试工具
 * @author niwb
 * @version 1.0
 * @since 1.0
 */


public class ApiDebugAction extends BaseAction {
	//private ApiBasicService apiBasicService = (ApiBasicService) super.getService("apiBasicService");
	
	protected static final ActionForward LIST_ACTION_FORWARD = new ActionForward("/apimng/apidebug.jsp");
	
	protected final String dataFormat = "yyyy-MM-dd";
//	protected final String dataFormat = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 事件跟踪监管驾校数据交换接口页面初始化
	 * 
	 * 工号:20181202：监管部门网关API命令调试
	 * 
	 * @param
	 * @return
	 */
	public ActionForward sys1init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("apiSystemid", "1");
		return LIST_ACTION_FORWARD;
	}
	
	/**
	 * 事件跟踪移动端页面初始化
	 * 
	 * 工号:20181202：点击移动端网管接口API命令调试
	 * 
	 * @param
	 * @return
	 */
	public ActionForward sys2init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("apiSystemid", "2");
		return LIST_ACTION_FORWARD;
	}
	
	/**
	 * 事件跟踪数据中心页面初始化
	 * 
	 * 工号:20181202：数据中心网关API命令调试调用此方法
	 * 
	 * @param
	 * @return
	 */
	public ActionForward sys0init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("apiSystemid", "0");
		return LIST_ACTION_FORWARD;
	}

	/**
	 *移动端网管接口API命令调试->API调试提交按钮
	 **/
	public ActionForward apidebug(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json = request.getParameter("json");
		System.out.println(json);
		ApiDebugJson u1 = JSON.parseObject(json,ApiDebugJson.class);  		//将传来的json转化为类对象
		int apicategory = u1.getApicategory();
		if(apicategory==2){
			if(u1.getHttpmethod().equals("get")){
				String httpurl=getParamValue("HTTP_APP_GATEWAY_URL", request);
				System.out.println(httpurl);
				System.out.println(u1.getHttpmethod()+apicategory);
				write(WebUtilClientGetRequest(u1,httpurl), response);
				System.out.println("这里若正确返回responceText,若错误返回msg");
			}else{
				String httpurl=getParamValue("HTTP_APP_GATEWAY_URL", request);
				System.out.println(u1.getHttpmethod()+apicategory);
				write(WebUtilClientPostRequest(u1,httpurl), response);
			}
		}
		else if(apicategory==1){
			if(u1.getHttpmethod().equals("get")){
				String httpurl=getParamValue("HTTP_MANAGERSECTOR_GATEWAY_URL", request);
				System.out.println(u1.getHttpmethod()+apicategory);
				write(WellCloudWebUtilClientGetRequest(u1,httpurl), response);
			}else{
				String httpurl=getParamValue("HTTP_MANAGERSECTOR_GATEWAY_URL", request);
				System.out.println(u1.getHttpmethod()+apicategory);
				write(WellCloudWebUtilClientPostRequest(u1,httpurl), response);
			}
		}
		else if(apicategory==0){
			if(u1.getHttpmethod().equals("get")){
				String httpurl=getParamValue("HTTP_DATACENTER_GATEWAY_URL", request);
				System.out.println(u1.getHttpmethod()+apicategory);
				write(DefaultWellClientGetRequest(u1,httpurl), response);
			}else{
				String httpurl=getParamValue("HTTP_DATACENTER_GATEWAY_URL", request);
				System.out.println(u1.getHttpmethod()+apicategory);
				write(DefaultWellClientPostRequest(u1,httpurl), response);
			}
		}
		
		return mapping.findForward(null);
	}
	
	/**
	 * 移动网关地址get请求
	 * @param u1
	 * @return
	 * @throws Exception
	 */
	public  static  String WebUtilClientGetRequest(ApiDebugJson u1 ,String httpurl) throws Exception{
		//"http://192.168.193.240:7001/appopenapi2/rest"
		WebUtilClient webUtilClient = new WebUtilClient(httpurl,
				u1.getAppkey(), u1.getAppsecret());
        DefaultWellGetRequest defaultWellGetRequest=new DefaultWellGetRequest();
        String responsed = "";
        List<Fields> fields = u1.getFields();
        for(int i=0; i<fields.size(); i++){
        	String fieldkey = fields.get(i).getFieldkey();
        	String fieldvalue = fields.get(i).getFieldvalue();
        	if(fieldkey.equals("password")||fieldkey.equals("oldpassword")){
        		defaultWellGetRequest.putOtherTextParam(fieldkey,AES.Encrypt(fieldvalue));
        	}else{
        		defaultWellGetRequest.putOtherTextParam(fieldkey,fieldvalue);
        	}
        }
		defaultWellGetRequest.setApiMethodName(u1.getApimethod());
		try{
			 responsed=webUtilClient.executeApp(defaultWellGetRequest);
			System.out.println("-------------------");
			System.out.println(responsed);
			
			//write(responsed, response);
		}catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsed = "{'msg':'请求出错'}" ;
			
		}
		return responsed;
		
	}
	
	/**
	 * 移动网关地址post请求
	 * @param u1
	 * @param httpurl 
	 * @return
	 * @throws Exception
	 */
	public  static  String WebUtilClientPostRequest(ApiDebugJson u1, String httpurl) throws Exception{
		//"http://192.168.193.240:7001/appopenapi2/rest"
		WebUtilClient webUtilClient = new WebUtilClient(httpurl,
				u1.getAppkey(), u1.getAppsecret());
		Map<String,String> map=new HashMap<String,String>();
		String response = "";
		//map.put("photo", jasonString);	
		DefaultWellPostRequest defaultWellPostRequest=new DefaultWellPostRequest();
		List<Fields> fields = u1.getFields();
        for(int i=0; i<fields.size(); i++){
        	String fieldkey = fields.get(i).getFieldkey();
        	String fieldvalue = fields.get(i).getFieldvalue();
        	if(fieldkey.equals("password")||fieldkey.equals("oldpassword")){
        		map.put(fieldkey,AES.Encrypt(fieldvalue));
        	}else{
        		map.put(fieldkey,fieldvalue);
        	}
        }
        defaultWellPostRequest.setJasonString(JSON.toJSONString(map));
	    defaultWellPostRequest.setApiMethodName(u1.getApimethod());
	    try{
	     response=webUtilClient.executePostApp(defaultWellPostRequest);
		System.out.println("-------------------");
		System.out.println(response);
		
		//write(responsed, response);
	    }catch (ApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		response = "{'msg':'请求出错'}" ;
		
	    }
		
		return response;
	}
	
	/**
	 * 监管部门网关地址get请求
	 * @param u1
	 * @param httpurl 
	 * @return
	 * @throws Exception
	 */
	
	public    String WellCloudWebUtilClientGetRequest(ApiDebugJson u1, String httpurl) throws Exception{
		//http://192.168.193.240:8999/openapi2/rest
		//String retportTitle = WebUtils.getParamValue("url", request);
		WebUtilClient webUtilClient = new WebUtilClient(httpurl,
				u1.getAppkey(), u1.getAppsecret());
		DownStudentChangeWellRequest downStudentChangeWellRequest=new DownStudentChangeWellRequest();
		String response ="";
        List<Fields> fields = u1.getFields();
        for(int i=0; i<fields.size(); i++){
        	String fieldkey = fields.get(i).getFieldkey();
        	String fieldvalue = fields.get(i).getFieldvalue();
        	if(fieldkey.equals("password")){
        		downStudentChangeWellRequest.putOtherTextParam(fieldkey,AES.Encrypt(fieldvalue));
        	}else{
        		downStudentChangeWellRequest.putOtherTextParam(fieldkey,fieldvalue);
        	}
        }
        downStudentChangeWellRequest.setApiMethodName(u1.getApimethod());
        try{
        	response=webUtilClient.executeApp(downStudentChangeWellRequest);
			System.out.println("-------------------");
			System.out.println(response);
			
			//write(responsed, response);
		}catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = "{'msg':'请求出错'}" ;
			
		}
	    
		return response;
		
	}
	/**
	 * 监管部门网关地址post请求
	 * @param u1
	 * @param httpurl 
	 * @return
	 * @throws Exception
	 */
	public  static  String WellCloudWebUtilClientPostRequest(ApiDebugJson u1, String httpurl) throws Exception{
		//"http://192.168.193.240:8999/openapi2/rest"
		WebUtilClient webUtilClient = new WebUtilClient(httpurl,
				u1.getAppkey(), u1.getAppsecret());
		Map<String,String> map=new HashMap<String,String>();
		List<Object> list = new ArrayList<Object>();
		String response = "";
		//map.put("photo", jasonString);	
		Up_StuSignPhotoWellRequest up_StuSignPhotoWellRequest=new Up_StuSignPhotoWellRequest();
		List<Fields> fields = u1.getFields();
        for(int i=0; i<fields.size(); i++){
        	String fieldkey = fields.get(i).getFieldkey();
        	String fieldvalue = fields.get(i).getFieldvalue();
        	if(fieldkey.equals("password")){
        		map.put(fieldkey,AES.Encrypt(fieldvalue));
        	}else{
        		map.put(fieldkey,fieldvalue);
        	}
        }
        list.add(map);
        //up_StuSignPhotoWellRequest.setJasonString(JSON.toJSONString(map));
        up_StuSignPhotoWellRequest.setApiMethodName(u1.getApimethod());
        up_StuSignPhotoWellRequest.setListObject(list);
	    try{
	     response=webUtilClient.executePostApp(up_StuSignPhotoWellRequest);
		System.out.println("-------------------");
		System.out.println(response);
		
		//write(responsed, response);
	    }catch (ApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		response = "{'msg':'请求出错'}" ;
		
	    }
		
		return response;
	}
	/**
	 * 数据中心网关地址get请求
	 * @param u1
	 * @param httpurl 
	 * @return
	 * @throws Exception
	 */
	
	public  static  String DefaultWellClientGetRequest(ApiDebugJson u1, String httpurl) throws Exception{
		//"http://192.168.193.238:8080/openapidataCenter/rest"
		WebUtilClient webUtilClient = new WebUtilClient(httpurl,
				u1.getAppkey(), u1.getAppsecret());
		StudentExamJdWellRequest studentExamJdWellRequest=new StudentExamJdWellRequest();
		String response ="";
        List<Fields> fields = u1.getFields();
        for(int i=0; i<fields.size(); i++){
        	String fieldkey = fields.get(i).getFieldkey();
        	String fieldvalue = fields.get(i).getFieldvalue();
        	if(fieldkey.equals("password")){
        		studentExamJdWellRequest.putOtherTextParam(fieldkey,AES.Encrypt(fieldvalue));
        	}else{
        		studentExamJdWellRequest.putOtherTextParam(fieldkey,fieldvalue);
        	}
        }
        studentExamJdWellRequest.setApiMethodName(u1.getApimethod());
        try{
        	response=webUtilClient.executeApp(studentExamJdWellRequest);
			System.out.println("-------------------");
			System.out.println(response);
			
			//write(responsed, response);
		}catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = "{'msg':'请求出错'}" ;
			
		}
	    
		return response;
		
	}
	/**
	 * 数据中心网关地址post请求
	 * @param u1
	 * @param httpurl 
	 * @return
	 * @throws Exception
	 */
	public  static  String DefaultWellClientPostRequest(ApiDebugJson u1, String httpurl) throws Exception{
		//"http://192.168.193.238:8080/openapidataCenter/rest"
		WebUtilClient webUtilClient = new WebUtilClient(httpurl,
				u1.getAppkey(), u1.getAppsecret());
		Map<String,String> map=new HashMap<String,String>();
		List<Object> list = new ArrayList<Object>();
		String response = "";
		//map.put("photo", jasonString);	
		DefaultWellPostRequest defaultWellPostRequest=new DefaultWellPostRequest();
		
		
		List<Fields> fields = u1.getFields();
        for(int i=0; i<fields.size(); i++){
        	String fieldkey = fields.get(i).getFieldkey();
        	String fieldvalue = fields.get(i).getFieldvalue();
        	map.put("bosid",UUID.randomUUID().toString().replaceAll("-", ""));
        	if(fieldkey.equals("password")){
        		map.put(fieldkey,AES.Encrypt(fieldvalue));
        	}else{
        		map.put(fieldkey,fieldvalue);
        	}
        }
        list.add(map);
        //up_StuSignPhotoWellRequest.setJasonString(JSON.toJSONString(map));
        defaultWellPostRequest.setApiMethodName(u1.getApimethod());
        defaultWellPostRequest.setListObject(list);
	    try{
	     response=webUtilClient.executePostApp(defaultWellPostRequest);
		System.out.println("-------------------");
		System.out.println(response);
		//write(responsed, response);
	    }catch (ApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		response = "{'msg':'请求出错'}" ;
		
	    }
		
		return response;
	}
}

