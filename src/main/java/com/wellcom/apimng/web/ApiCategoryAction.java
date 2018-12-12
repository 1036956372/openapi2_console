/*
 * Powered By [wellcomframework]
 */

package com.wellcom.apimng.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.g4studio.common.web.BaseAction;
import org.g4studio.common.web.BaseActionForm;
import org.g4studio.core.json.JsonHelper;
import org.g4studio.core.metatype.Dto;
import org.g4studio.core.metatype.impl.BaseDto;
import org.g4studio.core.mvc.xstruts.action.ActionForm;
import org.g4studio.core.mvc.xstruts.action.ActionForward;
import org.g4studio.core.mvc.xstruts.action.ActionMapping;

import com.wellcom.apimng.service.ApiCategoryService;



/**
 * 负责API类别的跳转
 * @author niwb
 * @version 1.0
 * @since 1.0
 */


public class ApiCategoryAction extends BaseAction {
	private ApiCategoryService apiCategoryService = (ApiCategoryService) super.getService("apiCategoryService");
	
	protected static final ActionForward LIST_ACTION_FORWARD = new ActionForward("/apimng/apicategory.jsp");
	
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
		 List apiCategoryList = g4Reader.queryForPage("ApiCategory.queryApiCategorys", dto);
		 Integer pageCount = (Integer) g4Reader.queryForObject("ApiCategory.queryApiCategorysForPageCount", dto);		//在resources/app/apicategory.xml
		 String jsonString = encodeList2PageJson(apiCategoryList, pageCount, dataFormat);
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
		 apiCategoryService.saveApiCategory(inDto);
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
		 apiCategoryService.updateApiCategory(inDto);
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
		apiCategoryService.deleteApiCategory(inDto);
		setOkTipMsg("操作成功", response);
		return mapping.findForward(null);
	}
	
	
}

