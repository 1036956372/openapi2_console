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
import com.wellcom.apimng.service.ApiBasicFieldsService;



/**
 * @author niwb
 * @version 1.0
 * @since 1.0
 */


public class ApiBasicFieldsAction extends BaseAction {
	private ApiBasicFieldsService apiBasicFieldsService = (ApiBasicFieldsService) super.getService("apiBasicFieldsService");
	
	protected static final ActionForward LIST_ACTION_FORWARD = new ActionForward("/apimng/apibasicfields.jsp");
	
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
		 List apiBasicFieldsList = g4Reader.queryForPage("ApiBasicFields.queryApiBasicFieldss", dto);
		 Integer pageCount = (Integer) g4Reader.queryForObject("ApiBasicFields.queryApiBasicFieldssForPageCount", dto);
		 String jsonString = encodeList2PageJson(apiBasicFieldsList, pageCount, dataFormat);
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
		 apiBasicFieldsService.saveApiBasicFields(inDto);
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
		 apiBasicFieldsService.updateApiBasicFields(inDto);
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
		apiBasicFieldsService.deleteApiBasicFields(inDto);
		setOkTipMsg("操作成功", response);
		return mapping.findForward(null);
	}
	
	/**
	 *根据apiid删除对象
	 **/
	public ActionForward apiiddelete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strChecked = request.getParameter("strChecked");
		Dto inDto = new BaseDto();
		inDto.put("strChecked", strChecked);
		apiBasicFieldsService.apiiddeleteApiBasicFields(inDto);
		setOkTipMsg("操作成功", response);
		return mapping.findForward(null);
	}
	
	
}

