/*
 * Powered By [wellcomframework]
 */

package com.wellcom.apimng.web;

import java.util.List;

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
import com.wellcom.apimng.service.ApiBasicService;



/**
 * 负责API方法的跳转
 * @author niwb
 * @version 1.0
 * @since 1.0
 */


public class ApiBasicAction extends BaseAction {
	private ApiBasicService apiBasicService = (ApiBasicService) super.getService("apiBasicService");
	
	protected static final ActionForward LIST_ACTION_FORWARD = new ActionForward("/apimng/apibasic.jsp");
	
	protected final String dataFormat = "yyyy-MM-dd";
//	protected final String dataFormat = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 事件跟踪页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return LIST_ACTION_FORWARD;
	}

	
	/** 
	 * 执行搜索 
	 **/
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			  HttpServletResponse response) throws Exception {		
		  
		 BaseActionForm aForm = (BaseActionForm) form;
	     Dto dto = aForm.getParamAsDto(request);
		 List apiBasicList = g4Reader.queryForPage("ApiBasic.queryApiBasics", dto);
		 Integer pageCount = (Integer) g4Reader.queryForObject("ApiBasic.queryApiBasicsForPageCount", dto);
		 String jsonString = encodeList2PageJson(apiBasicList, pageCount, dataFormat);
		 write(jsonString, response);
		 
		 return mapping.findForward(null);
	}
	
	/** 
	 * 新增对象
	 **/
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		  
		 BaseActionForm aForm = (BaseActionForm) form;
	     Dto inDto = aForm.getParamAsDto(request);
		 apiBasicService.saveApiBasic(inDto);
		 setOkTipMsg("操作成功", response);
		 return mapping.findForward(null);
	}
	
	/**
	 * 更新对象
	 **/
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 BaseActionForm aForm = (BaseActionForm) form;
		 Dto inDto = aForm.getParamAsDto(request);
		 apiBasicService.updateApiBasic(inDto);
		 setOkTipMsg("操作成功", response);
		 return mapping.findForward(null);
	}
	
	
	/**
	 *删除对象
	 **/
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strChecked = request.getParameter("strChecked");
		Dto inDto = new BaseDto();
		inDto.put("strChecked", strChecked);
		apiBasicService.deleteApiBasic(inDto);
		setOkTipMsg("操作成功", response);
		return mapping.findForward(null);
	}
	
	
}

