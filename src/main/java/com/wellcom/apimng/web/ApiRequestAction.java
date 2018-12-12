/*
 * Powered By [wellcomframework]
 */

package com.wellcom.apimng.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.g4studio.common.web.BaseAction;
import org.g4studio.common.web.BaseActionForm;
import org.g4studio.core.json.JsonHelper;
import org.g4studio.core.metatype.Dto;
import org.g4studio.core.metatype.impl.BaseDto;
import org.g4studio.core.mvc.xstruts.action.ActionForm;
import org.g4studio.core.mvc.xstruts.action.ActionForward;
import org.g4studio.core.mvc.xstruts.action.ActionMapping;

import com.wellcom.api.ApiException;
import com.wellcom.api.util.WebUtils;
import com.wellcom.apimng.service.ApiBasicService;



/**
 * @author niwb
 * @version 1.0
 * @since 1.0
 */


public class ApiRequestAction extends BaseAction {
	//private ApiBasicService apiBasicService = (ApiBasicService) super.getService("apiBasicService");
	
	protected static final ActionForward LIST_ACTION_FORWARD = new ActionForward("/apimng/apirequest.jsp");
	
	protected final String dataFormat = "yyyy-MM-dd";
//	protected final String dataFormat = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 事件跟踪页面初始化
	 * 
	 * 工号:20181202:应该是API命令调试工具->网关请求单笔调试吧
	 * 
	 * @param
	 * @return
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return LIST_ACTION_FORWARD;
	}
	/**
	 *get请求测试 
	 * 
	 *  
	 */
	public ActionForward doGet(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			  HttpServletResponse response) throws Exception {		
		String url = request.getParameter("urlrequest"); 
		System.out.println(url);
		String apisystemid = request.getParameter("apisystemid"); 
		Map<String,String> params=new HashMap<String,String>();
		String reponse ="";
		try{
			reponse=WebUtils.doGet(url, params);
			//System.out.println(reponse);
			write(reponse, response);
		}catch(Exception e){
			System.out.println(e.getMessage());
			//e.printStackTrace();
			reponse = e.getMessage() ;
			write(reponse, response);
		}
		
	
		return mapping.findForward(null);
	}
	
	/**
	 *post请求测试 
	 * 
	 *  
	 */
	public ActionForward doPost(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			  HttpServletResponse response) throws Exception {		
		String url = request.getParameter("urlrequest"); 
		System.out.println(url);
		String apisystemid = request.getParameter("apisystemid");
		String fields = request.getParameter("fields");
		Map<String,String> params=new HashMap<String,String>();
		String reponse ="";
		try{
			reponse=WebUtils.doPost(url, params, fields, 5000, 5000);
			//System.out.println(reponse);
			write(reponse, response);
		}catch(Exception e){
			System.out.println(e.getMessage());
			//e.printStackTrace();
			reponse = e.getMessage() ;
			write(reponse, response);
		}
		
	
		return mapping.findForward(null);
	}
		
}

