package com.wellcom.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import org.hibernate.validator.internal.util.logging.Log_.logger;













import com.alibaba.fastjson.JSON;
import com.wellcom.api.domain.Coach;
import com.wellcom.api.domain.ErrorMessage;
import com.wellcom.api.domain.RequestBean;
import com.wellcom.api.domain.School;
import com.wellcom.api.domain.SchoolCar;
import com.wellcom.api.domain.Student;
import com.wellcom.api.domain.StudentAppraisallinfo;
import com.wellcom.api.domain.StudentExam;
import com.wellcom.api.domain.StudentExamJd;
import com.wellcom.api.domain.StudentTrainPoint;
import com.wellcom.api.request.CarsWellRequest;
import com.wellcom.api.request.CoachsWellRequest;
import com.wellcom.api.request.DefaultWellPostRequest;
import com.wellcom.api.request.SchoolsWellRequest;
import com.wellcom.api.request.StudentExamJdWellRequest;
import com.wellcom.api.request.StudentExamsWellRequest;
import com.wellcom.api.request.StudentTrainPointsWellRequest;
import com.wellcom.api.request.StudentsAppraisallWellRequest;
import com.wellcom.api.request.StudentsWellRequest;
import com.wellcom.api.response.CarsWellResponse;
import com.wellcom.api.response.CoachsWellResponse;
import com.wellcom.api.response.DefaultWellResponse;
import com.wellcom.api.response.SchoolsWellResponse;
import com.wellcom.api.response.StudentExamJdWellResponse;
import com.wellcom.api.response.StudentExamsWellResponse;
import com.wellcom.api.response.StudentTrainPointsWellResponse;
import com.wellcom.api.response.StudentsAppraisallWellResponse;
import com.wellcom.api.response.StudentsWellResponse;
import com.wellcom.api.util.RequestParametersHolder;
import com.wellcom.api.util.WebUtils;
import com.wellcom.api.util.WellHashMap;
import com.wellcom.api.util.WellUtils;

/**
 * 基于JERSAY REST的OPENAPI客户端。
 * 
 * @author niwb
 *
 */
public  class  DefaultWellClient{
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
	
	
	
	public static final String GET_COACH_BYID = "well.coach.fullinfo.get";
	public static final String GET_INCCOACHS_BYFILTER = "well.coachs.increment.get";
	public static final String GET_COACHS_BYFILTER = "well.coachs.get";
	public static final String GET_SCHOOLCAR_BYID = "well.car.fullinfo.get";
	public static final String GET_INCSCHOOLCARS_BYFILTER = "well.cars.increment.get";
	public static final String GET_SCHOOLCARS_BYFILTER = "well.cars.get";
	public static final String GET_SCHOOL_BYID = "well.school.fullinfo.get";
	public static final String GET_INCSCHOOLS_BYFILTER = "well.schools.increment.get";
	public static final String GET_SCHOOLS_BYFILTER = "well.schools.get";
	public static final String GET_STUDENTAPPRAISALLINFO_BYID = "well.studentAppraisallinfo.fullinfo.get";
	//public static final String GET_INCSTUDENTAPPRAISALLINFOS_BYFILTER = "well.studentsAppraisallinfo.increment.get";
	public static final String GET_STUDENTAPPRAISALLINFOS_BYFILTER = "well.studentsAppraisallinfo.get";
	public static final String GET_STUDENTEXAM_BYID = "well.studentExam.fullinfo.get";
	//public static final String GET_INCSTUDENTEXAMS_BYFILTER = "well.studentsExam.increment.get";
	public static final String GET_STUDENTEXAMS_BYFILTER = "well.studentsExam.get";
	public static final String GET_STUDENTTRAINPOINT_BYID = "well.studentTrainPoint.fullinfo.get";
	//public static final String GET_INCSTUDENTTRAINPOINTS_BYFILTER = "well.studentsTrainPoint.increment.get";
	public static final String GET_STUDENTTRAINPOINTS_BYFILTER = "well.studentsTrainPoint.get";
	public static final String GET_STUDENT_BYID = "well.student.fullinfo.get";
	public static final String GET_INCSTUDENTS_BYFILTER = "well.students.increment.get";
	public static final String GET_STUDENTS_BYFILTER = "well.students.get";
	
	
	private String serverUrl;
	private String appKey;
	private String appSecret;
	private String format = Constants.FORMAT_JSON;
	private String signMethod = Constants.SIGN_METHOD_MD5;
	private boolean needCheckRequest = true;

	public DefaultWellClient(String serverUrl, String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.serverUrl = serverUrl;
	}

	public DefaultWellClient(String serverUrl, String appKey, String appSecret,
			String format) {
		this(serverUrl, appKey, appSecret);
		this.format = format;
	}

	public static void main(String[] args) {	//  583673c489b144d7919b021f93e6e69a	 192.168.193.152   openapidataCenter
		
    DefaultWellClient defaultWellClient = new DefaultWellClient("http://localhost:8080/openapi2/rest",
					"b332add55ce5470dbb2eb17c5df8bb43", "appSecret");  
    
//    /*向服务端上传对象*/
    StudentExamJd studentExamJd1=new StudentExamJd();
    studentExamJd1.setBosid(UUID.randomUUID().toString().replaceAll("-", ""));
    studentExamJd1.setAreacode("3303");
    studentExamJd1.setCarbrand("浙C66668学");
    studentExamJd1.setStudentcode("1006");
    studentExamJd1.setStarttime("2015-05-25 00:00:00");
    studentExamJd1.setEndtime("2015-05-25 05:00:00");
    studentExamJd1.setSchoolcode("10002");
    studentExamJd1.setCoachcertificatenum("330327184542241243");
    studentExamJd1.setTrainscore("80");
    studentExamJd1.setTimelength("10");
    studentExamJd1.setTraincode("21");
    studentExamJd1.setTrainmode("3");
    studentExamJd1.setTraindistance("51");
    studentExamJd1.setTrainmaxspeed("44");
    studentExamJd1.setTrainresult("1");
    studentExamJd1.setInfieldtime("2");
    studentExamJd1.setCoachcode("222");
    studentExamJd1.setCollector("000");
    studentExamJd1.setStucertificatenum("330327184542241243");
    studentExamJd1.setPrice("222");
   
    StudentExamJd studentExamJd2=new StudentExamJd();
    studentExamJd2.setBosid(UUID.randomUUID().toString().replaceAll("-", ""));
    studentExamJd2.setAreacode("3303");
    studentExamJd2.setCarbrand("浙C66666学");
    studentExamJd2.setStudentcode("1007");
    studentExamJd2.setStarttime("2015-05-25 00:00:00");
    studentExamJd2.setEndtime("2015-05-25 05:00:00");
    studentExamJd2.setSchoolcode("10002");
    studentExamJd2.setCoachcertificatenum("330327184542241244");
    studentExamJd2.setTrainscore("80");
    studentExamJd2.setTimelength("10");
    studentExamJd2.setTraincode("21");
    studentExamJd2.setTrainmode("3");
    studentExamJd2.setTraindistance("51");
    studentExamJd2.setTrainmaxspeed("44");
    studentExamJd2.setTrainresult("1");
    studentExamJd2.setInfieldtime("2");
    studentExamJd2.setCoachcode("2222");
    studentExamJd2.setCollector("000");
    studentExamJd2.setStucertificatenum("330327184542241243");
    studentExamJd2.setPrice("222");
    List<Object> list = new ArrayList<Object>();
    
    list.add(studentExamJd1);
    list.add(studentExamJd2);
  
    
    DefaultWellPostRequest defaultWellPostRequest=new DefaultWellPostRequest();
    defaultWellPostRequest.setApiMethodName("well.studentExamJd.add");
    defaultWellPostRequest.setListObject(list);
    try {
    	DefaultWellResponse defaultWellResponse=defaultWellClient.executePost(defaultWellPostRequest);
    	if(defaultWellResponse.isSuccess())
    	{
    		System.out.println("post 对象成功");
    	}
    	
    	System.out.println("返回状态:"+defaultWellResponse.isSuccess());
		System.out.println("返回状态码:"+defaultWellResponse.getCode());			
		System.out.println("请求连接:"+defaultWellResponse.getLink());			
		System.out.println("服务端序列号:"+defaultWellResponse.getHostSerial());
		System.out.println("客户端序列号:"+defaultWellResponse.getClientSerial());
		System.out.println("错误消息:"+defaultWellResponse.getMessage());
	} catch (ApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
//    //全量增量调用
//    StudentExamJdWellRequest studentExamJdWellRequest=new StudentExamJdWellRequest();
//    studentExamJdWellRequest.putOtherTextParam("areacode", "3303");
//    studentExamJdWellRequest.putOtherTextParam("last_timestamp", "2015-10-20 11:10:00");
//    studentExamJdWellRequest.setApiMethodName("well.studentsExamJd.increment.get");
//    
//    try {
//	    StudentExamJdWellResponse studentExamJdWellResponse=defaultWellClient.execute(studentExamJdWellRequest);
//	    
//	    if(studentExamJdWellResponse.isSuccess()){
//		    List<StudentExamJd> list1 = studentExamJdWellResponse.getResultData();
//		    for(StudentExamJd s  : list1){
//		    	System.out.println("StudentExamJd:"+s);
//		    	
//		    }	    
//		}
//	    System.out.println("机构数:"+studentExamJdWellResponse.getResultsize());
//		System.out.println("返回状态:"+studentExamJdWellResponse.isSuccess());
//		System.out.println("返回状态码:"+studentExamJdWellResponse.getCode());			
//		System.out.println("请求连接:"+studentExamJdWellResponse.getLink());			
//		System.out.println("服务端序列号:"+studentExamJdWellResponse.getHostSerial());
//		System.out.println("客户端序列号:"+studentExamJdWellResponse.getClientSerial());
//		System.out.println("错误消息:"+studentExamJdWellResponse.getMessage());
//    }catch (ApiException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
    
   //
	/*SchoolsWellRequest schoolsWellRequest = new SchoolsWellRequest();
	schoolsWellRequest.putOtherTextParam("areacode", "3303");
	schoolsWellRequest.setPageSize(148);
	schoolsWellRequest.setCurrentPage(1);
	//schoolsWellRequest.setFields("studentcode,schoolname");
	try{
		SchoolsWellResponse schoolsWellResponse = defaultWellClient
				.execute(schoolsWellRequest);
		if(schoolsWellResponse.isSuccess()){
		    List<School> list = schoolsWellResponse.getResultData();
		    for(School s  : list){
		    	System.out.println("school:"+s);
		    	
		    }	    
		}
		System.out.println("机构数:"+schoolsWellResponse.getResultsize());
		System.out.println("返回状态:"+schoolsWellResponse.isSuccess());
		System.out.println("返回状态码:"+schoolsWellResponse.getCode());			
		System.out.println("请求连接:"+schoolsWellResponse.getLink());			
		System.out.println("服务端序列号:"+schoolsWellResponse.getHostSerial());
		System.out.println("客户端序列号:"+schoolsWellResponse.getClientSerial());
		System.out.println("错误消息:"+schoolsWellResponse.getMessage());

	} catch (ApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
		
		//System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		//localhost
		//192.168.193.152

       //192.168.193.152
		//120.26.201.219
		
		/*SchoolsWellRequest schoolsWellRequest = new SchoolsWellRequest();
		schoolsWellRequest.putOtherTextParam("areacode", "3303");
		School school1=new School();
		School school2=new School();
		
		List list=new ArrayList();
		list.add(school1);
		list.add(school2);
		//schoolsWellRequest.setFields("studentcode,schoolname");
		try {
			SchoolsWellResponse schoolsWellResponse = defaultWellClient.executePost(schoolsWellRequest, list);
			if(schoolsWellResponse.isSuccess()){ 
				System.out.println("---------------------------------");
			   	System.out.println(schoolsWellResponse);  
			}	
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/       
	
	
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
		// TODO Auto-generated method stub
//		T localResponse = null;
//		try {
//			localResponse = request.getResponseClass().newInstance();
//		} catch (InstantiationException e2) {
//			throw new ApiException(e2);
//		} catch (IllegalAccessException e3) {
//			throw new ApiException(e3);
//		}
//
//		if (this.needCheckRequest) {
//			try {
//				request.check();// if check failed,will throw ApiRuleException.
//			} catch (ApiRuleException e){
//				
//				localResponse.setCode(e.getCode());
//				localResponse.setMessage(e.getMessage());
//				localResponse.setStatus(e.getStatus());
//				localResponse.setDeveloperMessage(e.getDeveloperMessage());
//				localResponse.setLink(e.getLink());
//				return localResponse;
//			}
//		}
//
//		//URL请求参数处理
//		RequestParametersHolder requestHolder=queryParamHandler(request);
//		
//		Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
//		WebTarget target = client.target(serverUrl);
//		Iterator<Entry<String, String>> iterMust=requestHolder.getProtocalMustParams().entrySet().iterator();
//		while(iterMust.hasNext())
//		{
//			Map.Entry<String,String> entry=iterMust.next();
//			String key=entry.getKey();			
//			String value=entry.getValue();
//			target=target.queryParam(key, value);
//			
//		}		
//							
//		Iterator<Entry<String, String>> iterOpt=requestHolder.getProtocalOptParams().entrySet().iterator();
//		while(iterOpt.hasNext())
//		{
//			Map.Entry<String, String> entry=iterOpt.next();
//			String key=entry.getKey();
//			String value=entry.getValue();
//			target=target.queryParam(key, value);
//		}
//		
//		Iterator<Entry<String, String>> iterApp=requestHolder.getApplicationParams().entrySet().iterator();
//		while(iterApp.hasNext())
//		{
//			Map.Entry<String, String> entry=iterApp.next();
//			String key=entry.getKey();
//			String value=entry.getValue();
//			target=target.queryParam(key, value);
//		}		
//		
//		try {
//			Response res = target.request(MediaType.APPLICATION_JSON_TYPE).get();
//
//			//System.out.println(res.getStatus() + "");
// 			if (res.getStatus() == 200) {
//				
//				//System.out.println(res.readEntity(String.class));
//				//System.out.println();
//				//logger.info();
//			
//				localResponse = res.readEntity(request.getResponseClass());
//				// System.out.println(res.readEntity(StudentsWellResponse.class).getResultData().size()+"");
//			} else {
//				ErrorMessage errorMessage = res.readEntity(ErrorMessage.class);
//				localResponse.setCode(errorMessage.getCode()+"");
//				localResponse.setStatus(errorMessage.getStatus());
//				localResponse.setMessage(errorMessage.getMessage());
//				localResponse.setLink(errorMessage.getLink());
//				localResponse.setDeveloperMessage(errorMessage.getDeveloperMessage());
//			}
//		} catch (Exception e) {
//			throw new ApiException(e);
//		} finally {
//			client.close();
//		}
//
//		return localResponse;
		
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
	


	public String executeApp(WellRequest arg0) throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

	public String executePostApp(WellPostRequest arg0) throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends WellResponse> T executePost(WellPostRequest request)
			throws ApiException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
//		T localResponse = null;
//		try {
//			localResponse = (T) request.getResponseClass().newInstance();
//		} catch (InstantiationException e2) {
//			throw new ApiException(e2);
//		} catch (IllegalAccessException e3) {
//			throw new ApiException(e3);
//		}
//		
//
//		if (this.needCheckRequest) {
//			try {
//				request.check();// if check failed,will throw ApiRuleException.
//			} catch (ApiRuleException e){
//				
//				localResponse.setCode(e.getCode());
//				localResponse.setMessage(e.getMessage());
//				localResponse.setStatus(e.getStatus());
//				localResponse.setDeveloperMessage(e.getDeveloperMessage());
//				localResponse.setLink(e.getLink());
//				return localResponse;
//			}
//		}
//		
//		//对上传字段生成MD5校验码
//		String signed="";
//		try {
//
//			String resultdata=new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request.getListObject()).replaceAll(System.getProperty("line.separator"), "");			
//			Map<String,String> resultmap=new HashMap<String,String>();
//			resultmap.put(RESULTDATA, resultdata);
//			signed=WellUtils.signTopRequestNew(resultmap, appSecret, false);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 
//		
//		//URL中请求参数处理
//		RequestParametersHolder requestHolder=queryParamHandler(request);
//		
//		Client client = ClientBuilder.newClient();
//		WebTarget target = client.target(serverUrl);
//		Iterator<Entry<String, String>> iterMust=requestHolder.getProtocalMustParams().entrySet().iterator();
//		while(iterMust.hasNext())
//		{
//			Map.Entry<String,String> entry=iterMust.next();
//			String key=entry.getKey();			
//			String value=entry.getValue();
//			target=target.queryParam(key, value);
//			
//		}		
//							
//		Iterator<Entry<String, String>> iterOpt=requestHolder.getProtocalOptParams().entrySet().iterator();
//		while(iterOpt.hasNext())
//		{
//			Map.Entry<String, String> entry=iterOpt.next();
//			String key=entry.getKey();
//			String value=entry.getValue();
//			target=target.queryParam(key, value);
//		}
//		
//		Iterator<Entry<String, String>> iterApp=requestHolder.getApplicationParams().entrySet().iterator();
//		while(iterApp.hasNext())
//		{
//			Map.Entry<String, String> entry=iterApp.next();
//			String key=entry.getKey();
//			String value=entry.getValue();
//			target=target.queryParam(key, value);
//		}		
//		
//		try {
//			
//			//封装POST表单
//			RequestBean requestBean=new RequestBean();
//			requestBean.setList(request.getListObject());
//			requestBean.setSign(signed);
//			
//			Response res = target.request().post(Entity.entity(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(requestBean), MediaType.APPLICATION_JSON));		
//			if (res.getStatus() == 200){
//				localResponse = (T) res.readEntity(request.getResponseClass());
//			} else {
//				ErrorMessage errorMessage = res.readEntity(ErrorMessage.class);
//				localResponse.setCode(errorMessage.getCode()+"");
//				localResponse.setStatus(errorMessage.getStatus());
//				localResponse.setMessage(errorMessage.getMessage());
//				localResponse.setLink(errorMessage.getLink());
//				localResponse.setDeveloperMessage(errorMessage.getDeveloperMessage());
//			}
//		} catch (Exception e) {
//			throw new ApiException(e);
//		} finally {
//			client.close();
//		}
//
//		return localResponse;
		
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
				String resultdata=JSON.toJSONString(request.getListObject()).replaceAll(System.getProperty("line.separator"), "");			
				System.out.println(resultdata);
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
					
					List list=new ArrayList();
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

}
