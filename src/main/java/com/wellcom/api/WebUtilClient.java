package com.wellcom.api;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import com.alibaba.fastjson.JSON;
import com.wellcom.api.domain.DownStudentChange;
import com.wellcom.api.domain.ErrorMessage;
import com.wellcom.api.domain.RequestBean;
import com.wellcom.api.domain.StudentExamJd;
import com.wellcom.api.domain.Up_StuSignPhoto;
import com.wellcom.api.domain.Up_StudentInfo;
import com.wellcom.api.request.DefaultWellGetRequest;
import com.wellcom.api.request.DefaultWellPostRequest;
import com.wellcom.api.request.DownStudentChangeWellRequest;
import com.wellcom.api.request.Up_StuSignPhotoWellRequest;
import com.wellcom.api.request.Up_StudentInfoWellRequest;
import com.wellcom.api.request.app.MessageCodeWellRequest;
import com.wellcom.api.response.DefaultWellResponse;
import com.wellcom.api.response.DownStudentChangeWellResponse;
import com.wellcom.api.response.app.AppDefaultWellResponse;
import com.wellcom.api.util.AES;
import com.wellcom.api.util.RequestParametersHolder;
import com.wellcom.api.util.WebUtils;
import com.wellcom.api.util.WellHashMap;
import com.wellcom.api.util.WellUtils;
//import com.wellcom.openapi2.bean.appservice.ChangePosInfo;

public class WebUtilClient {

	private static final String APP_KEY = "app_key";
	private static final String FORMAT = "format";
	private static final String METHOD = "method";
	private static final String TIMESTAMP = "timestamp";
	private static final String VERSION = "v";
	private static final String SIGN = "sign";
	private static final String SIGN_METHOD = "sign_method";
	private static final String PARTNER_ID = "partner_id";
	private static final String SESSION = "session";
	private static final String CLIENTSERIAL = "clientSerial";
	private static final String RESULTDATA = "resultdata";
	
	
	private String serverUrl;
	private String appKey;
	private String appSecret;
	private String format = Constants.FORMAT_JSON;
	private String signMethod = Constants.SIGN_METHOD_MD5;
	private boolean needCheckRequest = true;
	
	
	public WebUtilClient(String serverUrl, String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.serverUrl = serverUrl;
	}

	public WebUtilClient(String serverUrl, String appKey, String appSecret,
			String format) {
		this(serverUrl, appKey, appSecret);
		this.format = format;
	}
	
	public static void main(String[] args) throws Exception {//192.168.193.152 192.168.193.238   appopenapi2
		// TODO Auto-generated method stub		  localhost:8080  192.168.193.73:27538 192.168.193.73:27540  192.168.193.73:27543
		
		WebUtilClient webUtilClient = new WebUtilClient("http://172.16.9.23:8081/appopenapi2/rest",
				"b332add55ce5470dbb2eb17c5df8bb43", "appSecret");
		
//		 Up_StudentInfo studentInfo_3=new Up_StudentInfo();
//		    studentInfo_3.setSchoolCode("jx000316");
//		    studentInfo_3.setStudentCode("330500xy01555737");
//		    studentInfo_3.setAddress("浙江长兴雉城镇");
//		    studentInfo_3.setAreaCode("330500");
//		    studentInfo_3.setPostCode("aaaa");
//		    studentInfo_3.setBirthday("2015-12-03 09:03:00");
//		    studentInfo_3.setCoachCode("jl030830");
//		    studentInfo_3.setCoachName("曾庆伟");
//		    studentInfo_3.setDriliceaplyDate("2015-12-03 09:03:00");
//		    studentInfo_3.setDrivecarType("C1");
//		    studentInfo_3.setEmail("dyf20081124@126.com");
//		    studentInfo_3.setStartTime("2005-12-4 00:00:00");
//		    studentInfo_3.setEndTime("2015-12-03 09:03:00");
//		    studentInfo_3.setFaceInfo("face");
//		    studentInfo_3.setGraduation("2012-10-15 00:00:00");
//		    studentInfo_3.setHomeAddress("weier");
//		    studentInfo_3.setIdNum("332626780106077");
//		    studentInfo_3.setStudentName("顾晓华");
//		    studentInfo_3.setStatus("04");
//		    studentInfo_3.setIdType("1");
//		    studentInfo_3.setNationality("aaaa");
//		    studentInfo_3.setSex("1");
//		    studentInfo_3.setStudentType("0");
//		    studentInfo_3.setTelNum("13868270455");
//		    studentInfo_3.setSource("1");
//		    studentInfo_3.setSignChannel("01");
//		    studentInfo_3.setTrainType("00");
//		    studentInfo_3.setNatives("0");
//		    studentInfo_3.setSignDate("2005-12-04 00:00:00");
//	 
//		    List<Object> list = new ArrayList<Object>();
//		    
//		    list.add(studentInfo_3);
//		    Up_StudentInfoWellRequest up_StudentInfoWellRequest=new Up_StudentInfoWellRequest();
//		    up_StudentInfoWellRequest.setApiMethodName("well.up.studentInfo.rec");
//		    up_StudentInfoWellRequest.setListObject(list);
//		    try {
//		    	DefaultWellResponse defaultWellResponse=webUtilClient.executePost(up_StudentInfoWellRequest);
//		    	String response=webUtilClient.executePostApp(up_StudentInfoWellRequest);
//		    	if(defaultWellResponse.isSuccess())
//		    	{
//		    		System.out.println("post 对象成功");
//		    		System.out.println(response);
//		    	}
//		    	
//		    	System.out.println("返回状态:"+defaultWellResponse.isSuccess());
//				System.out.println("返回状态码:"+defaultWellResponse.getCode());			
//				System.out.println("请求连接:"+defaultWellResponse.getLink());			
//				System.out.println("服务端序列号:"+defaultWellResponse.getHostSerial());
//				System.out.println("客户端序列号:"+defaultWellResponse.getClientSerial());
//				System.out.println("错误消息:"+defaultWellResponse.getMessage());
//			} catch (ApiException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
		
//        DefaultWellGetRequest defaultWellGetRequest=new DefaultWellGetRequest();
//		defaultWellGetRequest.putOtherTextParam("account", "18667145616");
//		defaultWellGetRequest.putOtherTextParam("password",AES.Encrypt("111111"));
//		defaultWellGetRequest.putOtherTextParam("usertype", "jl");
//		defaultWellGetRequest.putOtherTextParam("imei", "11111");
//		//defaultWellGetRequest.putOtherTextParam("coachcartype", "111111");
//		//defaultWellGetRequest.putOtherTextParam("smscode", "");
//		defaultWellGetRequest.setApiMethodName("well.app.sso.signin");
//		
//		WebUtilClient webUtilClient = new WebUtilClient("http://localhost:8080/appopenapi2/rest",
//				"b332add55ce5470dbb2eb17c5df8bb43", "appSecret");
//		DefaultWellGetRequest defaultWellGetRequest=new DefaultWellGetRequest();
//		defaultWellGetRequest.putOtherTextParam("account", "15257119736");
//		defaultWellGetRequest.putOtherTextParam("usertype", "xy");  
//		defaultWellGetRequest.putOtherTextParam("password", AesUtil.Encrytor("111111", "123456"));
//		defaultWellGetRequest.putOtherTextParam("imei", "1111");
//		defaultWellGetRequest.setApiMethodName("well.app.sso.signin");
		
//		defaultWellGetRequest.putOtherTextParam("account", "15257119736");
//		defaultWellGetRequest.putOtherTextParam("usertype", "xy");  
//		defaultWellGetRequest.putOtherTextParam("smscode", "354730");
//		defaultWellGetRequest.setApiMethodName("well.app.sso.unbindaccount");
		
//		defaultWellGetRequest.putOtherTextParam("account", "15257119736");
//		defaultWellGetRequest.putOtherTextParam("usertype", "xy");
//		defaultWellGetRequest.putOtherTextParam("smscode", "111111");
//		defaultWellGetRequest.setApiMethodName("well.app.sso.getsmscode");
	    
//	    try {
//	
//			String response=webUtilClient.executeApp(defaultWellGetRequest);
//			System.out.println("-------------------");
//			System.out.println(response);
//
//	    }catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/*CAS APP头像上传接口测试*/
		String jasonString="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"+
				"MMhIs7u2FQDG6Dsk...";
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("photo", jasonString);		
		DefaultWellPostRequest defaultWellPostRequest=new DefaultWellPostRequest();
		map.put("account", "15257119715");
		map.put("usertype", "xy");
		map.put("photoType", "jpg");
		defaultWellPostRequest.setJasonString(JSON.toJSONString(map));
		defaultWellPostRequest.setApiMethodName("well.app.sso.uploadphoto");
	    
	    try {
	
			String response=webUtilClient.executePostApp(defaultWellPostRequest);
			System.out.println("-------------------");
			System.out.println(response);

	    }catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*CAS APP短信接口测试*/
//		MessageCodeWellRequest  messageCodeWellRequest=new MessageCodeWellRequest();
//		messageCodeWellRequest.putOtherTextParam("ACCOUNT", "15257119736");
//		messageCodeWellRequest.putOtherTextParam("usertype", "xy");
//		messageCodeWellRequest.setApiMethodName("well.app.sso.getsmscode");
//	    
//	    try {
//			@SuppressWarnings("unchecked")
//			AppDefaultWellResponse appDefaultWellResponse=webUtilClient.execute(messageCodeWellRequest);
//		    
//		    if(appDefaultWellResponse.isSuccess()){
//			    List<Map<String,Object>> list1 = appDefaultWellResponse.getResultData(); 
//			    System.out.println("短信验证码:"+list1.get(0).get("smscode"));
//			}
//		    System.out.println("机构数:"+appDefaultWellResponse.getResultsize());
//			System.out.println("返回状态:"+appDefaultWellResponse.isSuccess());
//			System.out.println("返回状态码:"+appDefaultWellResponse.getCode());			
//			System.out.println("请求连接:"+appDefaultWellResponse.getLink());			
//			System.out.println("服务端序列号:"+appDefaultWellResponse.getHostSerial());
//			System.out.println("客户端序列号:"+appDefaultWellResponse.getClientSerial());
//			System.out.println("错误消息:"+appDefaultWellResponse.getMessage());
//	    }catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/*CAS APP密码重置接口测试*/
//		PasswordWellRequest  passwordWellRequest=new PasswordWellRequest();
//		passwordWellRequest.putOtherTextParam("account", "15257119736");
//		passwordWellRequest.putOtherTextParam("usertype", "xy");
//		passwordWellRequest.putOtherTextParam("smscode", "1356");
//		passwordWellRequest.putOtherTextParam("password", AesUtil.Encrytor("111111", "123456"));
//		passwordWellRequest.setApiMethodName("well.app.sso.resetpassword");
//	    
//	    try {
//			@SuppressWarnings("unchecked")
//			AppDefaultWellResponse appDefaultWellResponse=webUtilClient.execute(passwordWellRequest);
//		    
//		    if(appDefaultWellResponse.isSuccess()){
//			    List<Map<String,Object>> list1 = appDefaultWellResponse.getResultData(); 
//			    //System.out.println("短信验证码:"+list1.get(0).get("smscode"));
//			}
//		    System.out.println("机构数:"+appDefaultWellResponse.getResultsize());
//			System.out.println("返回状态:"+appDefaultWellResponse.isSuccess());
//			System.out.println("返回状态码:"+appDefaultWellResponse.getCode());			
//			System.out.println("请求连接:"+appDefaultWellResponse.getLink());			
//			System.out.println("服务端序列号:"+appDefaultWellResponse.getHostSerial());
//			System.out.println("客户端序列号:"+appDefaultWellResponse.getClientSerial());
//			System.out.println("错误消息:"+appDefaultWellResponse.getMessage());
//	    }catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/*CAS APP密码修改接口测试*/
//		PasswordWellRequest  passwordWellRequest=new PasswordWellRequest();
//		passwordWellRequest.putOtherTextParam("account", "15257119736");
//		passwordWellRequest.putOtherTextParam("usertype", "xy");
//		passwordWellRequest.putOtherTextParam("oldpassword", AesUtil.Encrytor("111111", "123456"));
//		passwordWellRequest.putOtherTextParam("password", AesUtil.Encrytor("1111", "123456"));
//		passwordWellRequest.setApiMethodName("well.app.sso.modifypassword");
//	    
//	    try {
//			@SuppressWarnings("unchecked")
//			AppDefaultWellResponse appDefaultWellResponse=webUtilClient.execute(passwordWellRequest);
//		    
//		    if(appDefaultWellResponse.isSuccess()){
//			    List<Map<String,Object>> list1 = appDefaultWellResponse.getResultData(); 
//			   // System.out.println(appDefaultWellResponse.getResultData());
//			    //System.out.println("短信验证码:"+list1.get(0).get("smscode"));
//			}
//		    System.out.println("机构数:"+appDefaultWellResponse.getResultsize());
//			System.out.println("返回状态:"+appDefaultWellResponse.isSuccess());
//			System.out.println("返回状态码:"+appDefaultWellResponse.getCode());			
//			System.out.println("请求连接:"+appDefaultWellResponse.getLink());			
//			System.out.println("服务端序列号:"+appDefaultWellResponse.getHostSerial());
//			System.out.println("客户端序列号:"+appDefaultWellResponse.getClientSerial());
//			System.out.println("错误消息:"+appDefaultWellResponse.getMessage());
//	    }catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
//		/*CAS APP用户登录接口测试*/
//		UserSignWellRequest  userSignWellRequest=new UserSignWellRequest();
//		userSignWellRequest.putOtherTextParam("account", "15257119736");
//		userSignWellRequest.putOtherTextParam("usertype", "xy");
//		userSignWellRequest.putOtherTextParam("imei", "1356");
//		userSignWellRequest.putOtherTextParam("password", AesUtil.Encrytor("1111", "123456"));
//		userSignWellRequest.setApiMethodName("well.app.sso.signin");
//	    
//	    try {
//			@SuppressWarnings("unchecked")
//			AppDefaultWellResponse appDefaultWellResponse=webUtilClient.execute(userSignWellRequest);
//		    
//		    if(appDefaultWellResponse.isSuccess()){
//			    List<Map<String,Object>> list1 = appDefaultWellResponse.getResultData(); 
//			    //System.out.println("短信验证码:"+list1.get(0).get("smscode"));
//			}
//		    System.out.println("机构数:"+appDefaultWellResponse.getResultsize());
//			System.out.println("返回状态:"+appDefaultWellResponse.isSuccess());
//			System.out.println("返回状态码:"+appDefaultWellResponse.getCode());			
//			System.out.println("请求连接:"+appDefaultWellResponse.getLink());			
//			System.out.println("服务端序列号:"+appDefaultWellResponse.getHostSerial());
//			System.out.println("客户端序列号:"+appDefaultWellResponse.getClientSerial());
//			System.out.println("错误消息:"+appDefaultWellResponse.getMessage());
//	    }catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		/*CAS APP用户注册接口测试*/
//		UserSignWellRequest  userSignWellRequest=new UserSignWellRequest();
//		userSignWellRequest.putOtherTextParam("account", "15257119715");
//		userSignWellRequest.putOtherTextParam("usertype", "xy");
//		userSignWellRequest.putOtherTextParam("smscode", "1356");
//		userSignWellRequest.putOtherTextParam("password", AesUtil.Encrytor("1111", "123456"));
//		userSignWellRequest.setApiMethodName("well.app.sso.signup");
//	    
//	    try {
//			@SuppressWarnings("unchecked")
//			AppDefaultWellResponse appDefaultWellResponse=webUtilClient.execute(userSignWellRequest);
//		    
//		    if(appDefaultWellResponse.isSuccess()){
//			    List<Map<String,Object>> list1 = appDefaultWellResponse.getResultData(); 
//			    //System.out.println("短信验证码:"+list1.get(0).get("smscode"));
//			}
//		    System.out.println("机构数:"+appDefaultWellResponse.getResultsize());
//			System.out.println("返回状态:"+appDefaultWellResponse.isSuccess());
//			System.out.println("返回状态码:"+appDefaultWellResponse.getCode());			
//			System.out.println("请求连接:"+appDefaultWellResponse.getLink());			
//			System.out.println("服务端序列号:"+appDefaultWellResponse.getHostSerial());
//			System.out.println("客户端序列号:"+appDefaultWellResponse.getClientSerial());
//			System.out.println("错误消息:"+appDefaultWellResponse.getMessage());
//	    }catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/*CAS APP用户绑定前信息获取接口测试*/
//		UserBindingWellRequest userBindingWellRequest=new UserBindingWellRequest();
//		userBindingWellRequest.putOtherTextParam("account", "15257119715");
//		userBindingWellRequest.putOtherTextParam("usertype", "xy");
//		userBindingWellRequest.putOtherTextParam("name", "钱惠惠");
//		userBindingWellRequest.putOtherTextParam("idcard","330522197002052925");
//		userBindingWellRequest.putOtherTextParam("drivecartype","C1");
//		userBindingWellRequest.setApiMethodName("well.app.sso.getbindinfo");
//	    
//	    try {
//			@SuppressWarnings("unchecked")
//			AppDefaultWellResponse appDefaultWellResponse=webUtilClient.execute(userBindingWellRequest);
//		    
//		    if(appDefaultWellResponse.isSuccess()){
//			    List<Map<String,Object>> list1 = appDefaultWellResponse.getResultData(); 
//		
//			}
//		    System.out.println("机构数:"+appDefaultWellResponse.getResultsize());
//			System.out.println("返回状态:"+appDefaultWellResponse.isSuccess());
//			System.out.println("返回状态码:"+appDefaultWellResponse.getCode());			
//			System.out.println("请求连接:"+appDefaultWellResponse.getLink());			
//			System.out.println("服务端序列号:"+appDefaultWellResponse.getHostSerial());
//			System.out.println("客户端序列号:"+appDefaultWellResponse.getClientSerial());
//			System.out.println("错误消息:"+appDefaultWellResponse.getMessage());
//	    }catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
//	}
//		/*CAS APP用户绑定接口测试*/
//		UserBindingWellRequest userBindingWellRequest=new UserBindingWellRequest();
//		userBindingWellRequest.putOtherTextParam("account", "15257119715");
//		userBindingWellRequest.putOtherTextParam("usertype", "xy");
//		userBindingWellRequest.putOtherTextParam("usercode", "33050001557050");
//		userBindingWellRequest.setApiMethodName("well.app.sso.bindaccount");
//	    
//	    try {
//			@SuppressWarnings("unchecked")
//			AppDefaultWellResponse appDefaultWellResponse=webUtilClient.execute(userBindingWellRequest);
//		    
//		    if(appDefaultWellResponse.isSuccess()){
//			    List<Map<String,Object>> list1 = appDefaultWellResponse.getResultData(); 
//			    //System.out.println("短信验证码:"+list1.get(0).get("smscode"));
//			}
//		    System.out.println("机构数:"+appDefaultWellResponse.getResultsize());
//			System.out.println("返回状态:"+appDefaultWellResponse.isSuccess());
//			System.out.println("返回状态码:"+appDefaultWellResponse.getCode());			
//			System.out.println("请求连接:"+appDefaultWellResponse.getLink());			
//			System.out.println("服务端序列号:"+appDefaultWellResponse.getHostSerial());
//			System.out.println("客户端序列号:"+appDefaultWellResponse.getClientSerial());
//			System.out.println("错误消息:"+appDefaultWellResponse.getMessage());
//	    }catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/*CAS APP用户解绑接口测试*/
//		UserBindingWellRequest userBindingWellRequest=new UserBindingWellRequest();
//		userBindingWellRequest.putOtherTextParam("account", "15257119715");
//		userBindingWellRequest.putOtherTextParam("usertype", "xy");
//		userBindingWellRequest.putOtherTextParam("smscode", "1356");
//		userBindingWellRequest.setApiMethodName("well.app.sso.unbindaccount");
//	    
//	    try {
//			@SuppressWarnings("unchecked")
//			AppDefaultWellResponse appDefaultWellResponse=webUtilClient.execute(userBindingWellRequest);
//		    
//		    if(appDefaultWellResponse.isSuccess()){
//			    List<Map<String,Object>> list1 = appDefaultWellResponse.getResultData(); 
//			    //System.out.println("短信验证码:"+list1.get(0).get("smscode"));
//			}
//		    System.out.println("机构数:"+appDefaultWellResponse.getResultsize());
//			System.out.println("返回状态:"+appDefaultWellResponse.isSuccess());
//			System.out.println("返回状态码:"+appDefaultWellResponse.getCode());			
//			System.out.println("请求连接:"+appDefaultWellResponse.getLink());			
//			System.out.println("服务端序列号:"+appDefaultWellResponse.getHostSerial());
//			System.out.println("客户端序列号:"+appDefaultWellResponse.getClientSerial());
//			System.out.println("错误消息:"+appDefaultWellResponse.getMessage());
//	    }catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		/*APP获取数据接口测试*/
//		DefaultWellGetRequest defaultWellGetRequest=new DefaultWellGetRequest();
//		defaultWellGetRequest.putOtherTextParam("areacode", "330201");
//		defaultWellGetRequest.putOtherTextParam("telphone", "18667145612");
//		defaultWellGetRequest.putOtherTextParam("apptype", "apptype");
//		defaultWellGetRequest.putOtherTextParam("password", "123456");
//		defaultWellGetRequest.setApiMethodName("well.app.standard.operation.business.getLoginInInfo.get");
//	    
//	    try {
//	
//			String response=webUtilClient.executeApp(defaultWellGetRequest);
//			System.out.println("-------------------");
//			System.out.println(response);
//
//	    }catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
	    /*APP 上传数据接口测试*/
//	    DefaultWellPostRequest  defaultWellPostRequest=new DefaultWellPostRequest();
//	    ChangePosInfo changePosInfo=new ChangePosInfo();
//	    changePosInfo.setApptype("11");
//	    changePosInfo.setAreacode("330206");
//	    changePosInfo.setCarcode("浙A66666");
//	    changePosInfo.setCarnum("222");
//	    changePosInfo.setObdcode("330206");
//	    changePosInfo.setObdstatus("1");
//	    changePosInfo.setPoscode("315823");
//	    changePosInfo.setPosstatus("1");
//	    changePosInfo.setSimcode("1111");
//	    changePosInfo.setTelphone("15257119736");
//	    changePosInfo.setUserid("33333");
//	    String jasonString=JSON.toJSONString(changePosInfo);
//	    defaultWellPostRequest.setJasonString(jasonString);
//	    
//	    defaultWellPostRequest.setApiMethodName("well.app.standard.operation.business.postChangePosInfo.post");
//	    
//	    try {
//	    	
//			String response=webUtilClient.executePostApp(defaultWellPostRequest);
//			System.out.println("-------------------");
//			System.out.println(response);
//
//	    }catch (ApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//				
	}
	
	/**
	 * 请求的参数处理
	 * 
	 * @author dingyf
	 *
	 */
	public  RequestParametersHolder  queryParamHandler(WellRequest<?> request) throws ApiException 
	{
		RequestParametersHolder requestHolder = new RequestParametersHolder();
		
		//添加应用级请求参数
		WellHashMap appParams = new WellHashMap(request.getTextParams());
		requestHolder.setApplicationParams(appParams);

		//添加协议级请求参数
		WellHashMap protocalMustParams = new WellHashMap();
		protocalMustParams.put(METHOD, request.getApiMethodName());
		protocalMustParams.put(VERSION, "2.0");
		protocalMustParams.put(APP_KEY, appKey);
		Long timestamp = request.getTimestamp();// 允许用户设置时间戳
		if (timestamp == null) {
			timestamp = System.currentTimeMillis();
		}
		protocalMustParams.put(TIMESTAMP, new Date(timestamp));		
		
		String Clientserial=request.getClientserial();
		if(Clientserial==null)
		{
			Clientserial=UUID.randomUUID().toString();
		}
		protocalMustParams.put(CLIENTSERIAL,Clientserial);
		requestHolder.setProtocalMustParams(protocalMustParams);

		WellHashMap protocalOptParams = new WellHashMap();
		protocalOptParams.put(FORMAT, format);
		protocalOptParams.put(SIGN_METHOD, signMethod);
		protocalOptParams.put(PARTNER_ID, Constants.SDK_VERSION);
		
		requestHolder.setProtocalOptParams(protocalOptParams);

		//添加签名参数
		try {
			if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
				protocalMustParams.put(SIGN, WellUtils.signTopRequestNew(
						requestHolder, appSecret, false));
			} else if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
				protocalMustParams.put(SIGN, WellUtils.signTopRequestNew(
						requestHolder, appSecret, true));
			} else {
				protocalMustParams.put(SIGN,
						WellUtils.signTopRequest(requestHolder, appSecret));
			}
		} catch (IOException e) {
			throw new ApiException(e);
		}
		
		return requestHolder;
	}
	
	
	/**
	 * GET请求
	 * 
	 * @author dingyf
	 *
	 */
	public <T extends WellResponse> T execute(WellRequest<T> request)
			throws ApiException {
		
		T localResponse = null;
		try {
			localResponse = request.getResponseClass().newInstance();
		} catch (InstantiationException e2) {
			throw new ApiException(e2);
		} catch (IllegalAccessException e3) {
			throw new ApiException(e3);
		}

		if (this.needCheckRequest) {
			try {
				request.check();// if check failed,will throw ApiRuleException.
			} catch (ApiRuleException e){
				
				localResponse.setCode(e.getCode());
				localResponse.setMessage(e.getMessage());
				localResponse.setStatus(e.getStatus());
				localResponse.setDeveloperMessage(e.getDeveloperMessage());
				localResponse.setLink(e.getLink());
				return localResponse;
			}
		}
		
		RequestParametersHolder requestHolder = new RequestParametersHolder();
		
		//添加应用级请求参数
		WellHashMap appParams = new WellHashMap(request.getTextParams());
		requestHolder.setApplicationParams(appParams);

		//添加协议级请求参数
		WellHashMap protocalMustParams = new WellHashMap();
		protocalMustParams.put(METHOD, request.getApiMethodName());
		protocalMustParams.put(VERSION, "2.0");
		protocalMustParams.put(APP_KEY, appKey);
		Long timestamp = request.getTimestamp();// 允许用户设置时间戳
		if (timestamp == null) {
			timestamp = System.currentTimeMillis();
		}
		protocalMustParams.put(TIMESTAMP, new Date(timestamp));
		
		//System.out.println("timestamp"+protocalMustParams.get(TIMESTAMP));		
		
		String Clientserial=request.getClientserial();
		if(Clientserial==null)
		{
			Clientserial=UUID.randomUUID().toString();
		}
		protocalMustParams.put(CLIENTSERIAL,Clientserial);
		requestHolder.setProtocalMustParams(protocalMustParams);

		WellHashMap protocalOptParams = new WellHashMap();
		protocalOptParams.put(FORMAT, format);
		protocalOptParams.put(SIGN_METHOD, signMethod);
		protocalOptParams.put(PARTNER_ID, Constants.SDK_VERSION);
		
		requestHolder.setProtocalOptParams(protocalOptParams);

		//添加签名参数
		try {
			if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
				protocalMustParams.put(SIGN, WellUtils.signTopRequestNew(
						requestHolder, appSecret, false));
			} else if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
				protocalMustParams.put(SIGN, WellUtils.signTopRequestNew(
						requestHolder, appSecret, true));
			} else {
				protocalMustParams.put(SIGN,
						WellUtils.signTopRequest(requestHolder, appSecret));
			}
		} catch (IOException e) {
			throw new ApiException(e);
		}
		
		Map<String,String> params=new HashMap<String,String>();
		Iterator<Entry<String, String>> iterMust=requestHolder.getProtocalMustParams().entrySet().iterator();
		while(iterMust.hasNext())
		{
			Map.Entry<String,String> entry=iterMust.next();
			String key=entry.getKey();			
			String value=entry.getValue();
			params.put(key, value);
			
		}		
							
		Iterator<Entry<String, String>> iterOpt=requestHolder.getProtocalOptParams().entrySet().iterator();
		while(iterOpt.hasNext())
		{
			Map.Entry<String, String> entry=iterOpt.next();
			String key=entry.getKey();
			String value=entry.getValue();
			params.put(key, value);
		}
		
		Iterator<Entry<String, String>> iterApp=requestHolder.getApplicationParams().entrySet().iterator();
		while(iterApp.hasNext())
		{
			Map.Entry<String, String> entry=iterApp.next();
			String key=entry.getKey();
			String value=entry.getValue();
			params.put(key, value);
		}
		
		try {
			String reponse=WebUtils.doGet(serverUrl, params, "UTF-8");
			localResponse=JSON.parseObject(reponse,request.getResponseClass());
			/*if(localResponse.getStatus()==200)
			{
				System.out.println("-----------------------------");
				System.out.println(reponse);
				System.out.println("-----------------------------");
			}*/
		} catch (IOException e) {
			// TODO Auto-generated catch block	
			//ObjectMapper mapper = new ObjectMapper();
			e.printStackTrace();
			try {
				//System.out.println(e.toString().substring(e.toString().indexOf(":")+1));
				//ErrorMessage apiException=JSON.parseObject(e.toString().substring(e.toString().indexOf(":")+1),ErrorMessage.class);
				localResponse.setCode("99");
				localResponse.setStatus(500);
				localResponse.setMessage(e.getMessage());
				localResponse.setLink(serverUrl);
				localResponse.setDeveloperMessage("服务器内部异常");			
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				localResponse.setCode("99");
				localResponse.setStatus(500);
				localResponse.setMessage(e1.getMessage());
				localResponse.setLink(serverUrl);
				localResponse.setDeveloperMessage("服务器内部异常");
			}
		}
		return localResponse;
		
	}
	
	
	/**
	 * POST请求
	 * 
	 * @author dingyf
	 *
	 */
	@SuppressWarnings("unchecked")
	public <T extends WellResponse> T executePost(WellPostRequest request)
			throws ApiException {
		// TODO Auto-generated method stub
		T localResponse = null;
		try {
			localResponse = (T) request.getResponseClass().newInstance();
		} catch (InstantiationException e2) {
			throw new ApiException(e2);
		} catch (IllegalAccessException e3) {
			throw new ApiException(e3);
		}
		

		if (this.needCheckRequest) {
			try {
				request.check();// if check failed,will throw ApiRuleException.
			} catch (ApiRuleException e){
				
				localResponse.setCode(e.getCode());
				localResponse.setMessage(e.getMessage());
				localResponse.setStatus(e.getStatus());
				localResponse.setDeveloperMessage(e.getDeveloperMessage());
				localResponse.setLink(e.getLink());
				return localResponse;
			}
		}
		
		//对上传字段生成MD5校验码
		String signed="";
		
		if(request.getListObject()==null || request.getListObject().size()==0)
		{
			if(request.getJasonString()!=null && !"".equals(request.getJasonString())){
				
				try {
					String resultdata="["+request.getJasonString()+"]";	
					Map<String,String> resultmap=new HashMap<String,String>();
					resultmap.put(RESULTDATA, resultdata);
					signed=WellUtils.signTopRequestNew(resultmap, appSecret, false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}else{
				//表示上传错误数据对象为空
				localResponse.setCode("3");
				localResponse.setStatus(400);
				localResponse.setLink(serverUrl);
				localResponse.setDeveloperMessage("上传对象为空");
				return localResponse;
			}
		}else{
			try {
				String resultdata=JSON.toJSONString(request.getListObject());			
				Map<String,String> resultmap=new HashMap<String,String>();
				resultmap.put(RESULTDATA, resultdata);
				signed=WellUtils.signTopRequestNew(resultmap, appSecret, false);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
		}
		
		RequestParametersHolder requestHolder = new RequestParametersHolder();
		
		//添加应用级请求参数
		WellHashMap appParams = new WellHashMap(request.getTextParams());
		requestHolder.setApplicationParams(appParams);

		//添加协议级请求参数
		WellHashMap protocalMustParams = new WellHashMap();
		protocalMustParams.put(METHOD, request.getApiMethodName());
		protocalMustParams.put(VERSION, "2.0");
		protocalMustParams.put(APP_KEY, appKey);
		Long timestamp = request.getTimestamp();// 允许用户设置时间戳
		if (timestamp == null) {
			timestamp = System.currentTimeMillis();
		}
		protocalMustParams.put(TIMESTAMP, new Date(timestamp));
		
		//System.out.println("timestamp"+protocalMustParams.get(TIMESTAMP));
		
		
		String Clientserial=request.getClientserial();
		if(Clientserial==null)
		{
			Clientserial=UUID.randomUUID().toString();
		}
		protocalMustParams.put(CLIENTSERIAL,Clientserial);
		requestHolder.setProtocalMustParams(protocalMustParams);

		WellHashMap protocalOptParams = new WellHashMap();
		protocalOptParams.put(FORMAT, format);
		protocalOptParams.put(SIGN_METHOD, signMethod);
		protocalOptParams.put(PARTNER_ID, Constants.SDK_VERSION);
		
		requestHolder.setProtocalOptParams(protocalOptParams);

		//添加签名参数
		try {
			if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
				protocalMustParams.put(SIGN, WellUtils.signTopRequestNew(
						requestHolder, appSecret, false));
			} else if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
				protocalMustParams.put(SIGN, WellUtils.signTopRequestNew(
						requestHolder, appSecret, true));
			} else {
				protocalMustParams.put(SIGN,
						WellUtils.signTopRequest(requestHolder, appSecret));
			}
		} catch (IOException e) {
			throw new ApiException(e);
		}
		
		Map<String,String> params=new HashMap<String,String>();
		Iterator<Entry<String, String>> iterMust=requestHolder.getProtocalMustParams().entrySet().iterator();
		while(iterMust.hasNext())
		{
			Map.Entry<String,String> entry=iterMust.next();
			String key=entry.getKey();			
			String value=entry.getValue();
			params.put(key, value);
			
		}		
							
		Iterator<Entry<String, String>> iterOpt=requestHolder.getProtocalOptParams().entrySet().iterator();
		while(iterOpt.hasNext())
		{
			Map.Entry<String, String> entry=iterOpt.next();
			String key=entry.getKey();
			String value=entry.getValue();
			params.put(key, value);
		}
		
		Iterator<Entry<String, String>> iterApp=requestHolder.getApplicationParams().entrySet().iterator();
		while(iterApp.hasNext())
		{
			Map.Entry<String, String> entry=iterApp.next();
			String key=entry.getKey();
			String value=entry.getValue();
			params.put(key, value);
		}
		
		
		try {
			//封装POST表单
			RequestBean requestBean=new RequestBean();
			if(request.getListObject()==null || request.getListObject().size()==0)
			{
				if(request.getJasonString()!=null && !"".equals(request.getJasonString())){
					
					List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
					Map<String,Object> map=(Map<String,Object>)JSON.parse(request.getJasonString());
					list.add(map);
					requestBean.setList(list);
					requestBean.setSign(signed);
				}
			}else{
				requestBean.setList(request.getListObject());
				requestBean.setSign(signed);
			}
			
			String reponse=WebUtils.doPost(serverUrl+"/post", params,JSON.toJSONString(requestBean),"UTF-8",30000,30000);
			localResponse=(T) JSON.parseObject(reponse,request.getResponseClass());
			/*if(localResponse.getStatus()==200)
			{
				System.out.println("-----------------------------");
				System.out.println(reponse);
				System.out.println("-----------------------------");
			}*/
		} catch (IOException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
			try {
				//System.out.println(e.toString().substring(e.toString().indexOf(":")+1));
				//ErrorMessage apiException=JSON.parseObject(e.toString().substring(e.toString().indexOf(":")+1),ErrorMessage.class);
				localResponse.setCode("99");
				localResponse.setStatus(500);
				localResponse.setMessage(e.getMessage());
				localResponse.setLink(serverUrl);
				localResponse.setDeveloperMessage("服务器内部异常");			
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				localResponse.setCode("99");
				localResponse.setStatus(500);
				localResponse.setMessage(e1.getMessage());
				localResponse.setLink(serverUrl);
				localResponse.setDeveloperMessage("服务器内部异常");
			}
		}
		return localResponse;			
	}

	public String executeApp(WellRequest request) throws ApiException {
		// TODO Auto-generated method stub
       RequestParametersHolder requestHolder = new RequestParametersHolder();
		
		//添加应用级请求参数
		WellHashMap appParams = new WellHashMap(request.getTextParams());
		requestHolder.setApplicationParams(appParams);

		//添加协议级请求参数
		WellHashMap protocalMustParams = new WellHashMap();
		protocalMustParams.put(METHOD, request.getApiMethodName());
		protocalMustParams.put(VERSION, "2.0");
		protocalMustParams.put(APP_KEY, appKey);
		Long timestamp = request.getTimestamp();// 允许用户设置时间戳
		if (timestamp == null) {
			timestamp = System.currentTimeMillis();
		}
		protocalMustParams.put(TIMESTAMP, new Date(timestamp));
		
		//System.out.println("timestamp"+protocalMustParams.get(TIMESTAMP));		
		
		String Clientserial=request.getClientserial();
		if(Clientserial==null)
		{
			Clientserial=UUID.randomUUID().toString().replace("-", "");
		}
		protocalMustParams.put(CLIENTSERIAL,Clientserial);
		requestHolder.setProtocalMustParams(protocalMustParams);

		WellHashMap protocalOptParams = new WellHashMap();
		protocalOptParams.put(FORMAT, format);
		protocalOptParams.put(SIGN_METHOD, signMethod);
		protocalOptParams.put(PARTNER_ID, Constants.SDK_VERSION);
		
		requestHolder.setProtocalOptParams(protocalOptParams);

		//添加签名参数
		try {
			if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
				protocalMustParams.put(SIGN, WellUtils.signTopRequestNew(
						requestHolder, appSecret, false));
			} else if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
				protocalMustParams.put(SIGN, WellUtils.signTopRequestNew(
						requestHolder, appSecret, true));
			} else {
				protocalMustParams.put(SIGN,
						WellUtils.signTopRequest(requestHolder, appSecret));
			}
		} catch (IOException e) {
			throw new ApiException(e);
		}
		
		Map<String,String> params=new HashMap<String,String>();
		Iterator<Entry<String, String>> iterMust=requestHolder.getProtocalMustParams().entrySet().iterator();
		while(iterMust.hasNext())
		{
			Map.Entry<String,String> entry=iterMust.next();
			String key=entry.getKey();			
			String value=entry.getValue();
			params.put(key, value);
			
		}		
							
		Iterator<Entry<String, String>> iterOpt=requestHolder.getProtocalOptParams().entrySet().iterator();
		while(iterOpt.hasNext())
		{
			Map.Entry<String, String> entry=iterOpt.next();
			String key=entry.getKey();
			String value=entry.getValue();
			params.put(key, value);
		}
		
		Iterator<Entry<String, String>> iterApp=requestHolder.getApplicationParams().entrySet().iterator();
		while(iterApp.hasNext())
		{
			Map.Entry<String, String> entry=iterApp.next();
			String key=entry.getKey();
			String value=entry.getValue();
			params.put(key, value);
		}
		String reponse="";
		try {
			reponse=WebUtils.doGet(serverUrl, params, "UTF-8");
			System.out.println(reponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block	
			//ObjectMapper mapper = new ObjectMapper();
			reponse=e.getMessage().toString();
		}
		return reponse;
	}

	public String executePostApp(WellPostRequest request) throws ApiException {
		// TODO Auto-generated method stub
		//对上传字段生成MD5校验码
		String signed="";
		
		if(request.getListObject()==null || request.getListObject().size()==0)
		{
			if(request.getJasonString()!=null && !"".equals(request.getJasonString())){
				
				try {
					String resultdata="["+request.getJasonString()+"]";	
					System.out.println("****************************");
					System.out.println(resultdata);
					Map<String,String> resultmap=new HashMap<String,String>();
					resultmap.put(RESULTDATA, resultdata);
					signed=WellUtils.signTopRequestNew(resultmap, appSecret, false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}else{
				//表示上传错误数据对象为空
				DefaultWellResponse localResponse=new DefaultWellResponse();
				localResponse.setCode("3");
				localResponse.setStatus(400);
				localResponse.setLink(serverUrl);
				localResponse.setDeveloperMessage("上传对象为空");
				return JSON.toJSONString(localResponse);
			}
		}else{
			try {
				String resultdata=JSON.toJSONString(request.getListObject());			
				Map<String,String> resultmap=new HashMap<String,String>();
				resultmap.put(RESULTDATA, resultdata);
				signed=WellUtils.signTopRequestNew(resultmap, appSecret, false);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
		}
		
		RequestParametersHolder requestHolder = new RequestParametersHolder();
		
		//添加应用级请求参数
		WellHashMap appParams = new WellHashMap(request.getTextParams());
		requestHolder.setApplicationParams(appParams);

		//添加协议级请求参数
		WellHashMap protocalMustParams = new WellHashMap();
		protocalMustParams.put(METHOD, request.getApiMethodName());
		protocalMustParams.put(VERSION, "2.0");
		protocalMustParams.put(APP_KEY, appKey);
		Long timestamp = request.getTimestamp();// 允许用户设置时间戳
		if (timestamp == null) {
			timestamp = System.currentTimeMillis();
		}
		protocalMustParams.put(TIMESTAMP, new Date(timestamp));
		
		//System.out.println("timestamp"+protocalMustParams.get(TIMESTAMP));
		
		
		String Clientserial=request.getClientserial();
		if(Clientserial==null)
		{
			Clientserial=UUID.randomUUID().toString();
		}
		protocalMustParams.put(CLIENTSERIAL,Clientserial);
		requestHolder.setProtocalMustParams(protocalMustParams);

		WellHashMap protocalOptParams = new WellHashMap();
		protocalOptParams.put(FORMAT, format);
		protocalOptParams.put(SIGN_METHOD, signMethod);
		protocalOptParams.put(PARTNER_ID, Constants.SDK_VERSION);
		
		requestHolder.setProtocalOptParams(protocalOptParams);

		//添加签名参数
		try {
			if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
				protocalMustParams.put(SIGN, WellUtils.signTopRequestNew(
						requestHolder, appSecret, false));
			} else if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
				protocalMustParams.put(SIGN, WellUtils.signTopRequestNew(
						requestHolder, appSecret, true));
			} else {
				protocalMustParams.put(SIGN,
						WellUtils.signTopRequest(requestHolder, appSecret));
			}
		} catch (IOException e) {
			throw new ApiException(e);
		}
		
		Map<String,String> params=new HashMap<String,String>();
		Iterator<Entry<String, String>> iterMust=requestHolder.getProtocalMustParams().entrySet().iterator();
		while(iterMust.hasNext())
		{
			Map.Entry<String,String> entry=iterMust.next();
			String key=entry.getKey();			
			String value=entry.getValue();
			params.put(key, value);
			
		}		
							
		Iterator<Entry<String, String>> iterOpt=requestHolder.getProtocalOptParams().entrySet().iterator();
		while(iterOpt.hasNext())
		{
			Map.Entry<String, String> entry=iterOpt.next();
			String key=entry.getKey();
			String value=entry.getValue();
			params.put(key, value);
		}
		
		Iterator<Entry<String, String>> iterApp=requestHolder.getApplicationParams().entrySet().iterator();
		while(iterApp.hasNext())
		{
			Map.Entry<String, String> entry=iterApp.next();
			String key=entry.getKey();
			String value=entry.getValue();
			params.put(key, value);
		}
		
		String response="";
		try {
			//封装POST表单
			RequestBean requestBean=new RequestBean();
			if(request.getListObject()==null || request.getListObject().size()==0)
			{
				if(request.getJasonString()!=null && !"".equals(request.getJasonString())){
					
					List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
					Map<String,Object> map=(Map<String,Object>)JSON.parse(request.getJasonString());
					list.add(map);
					requestBean.setList(list);
					requestBean.setSign(signed);
				}
			}else{
				requestBean.setList(request.getListObject());
				requestBean.setSign(signed);
			}
			System.out.println(JSON.toJSONString(requestBean));
			response=WebUtils.doPost(serverUrl+"/post", params,JSON.toJSONString(requestBean),"UTF-8",30000,30000);
			//System.out.println(localResponse);					
		} catch (IOException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
			try {
				//System.out.println(e.toString().substring(e.toString().indexOf(":")+1));
				//ErrorMessage apiException=JSON.parseObject(e.toString().substring(e.toString().indexOf(":")+1),ErrorMessage.class);
				DefaultWellResponse localResponse=new DefaultWellResponse();
				localResponse.setCode("99");
				localResponse.setStatus(500);
				localResponse.setMessage(e.getMessage());
				localResponse.setLink(serverUrl);
				localResponse.setDeveloperMessage("服务器内部异常");
				response=JSON.toJSONString(localResponse);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				DefaultWellResponse localResponse=new DefaultWellResponse();
				localResponse.setCode("99");
				localResponse.setStatus(500);
				localResponse.setMessage(e1.getMessage());
				localResponse.setLink(serverUrl);
				localResponse.setDeveloperMessage("服务器内部异常");
				response=JSON.toJSONString(localResponse);					
			}
		}
		return response;
	}
	
	
	

	
	

}
