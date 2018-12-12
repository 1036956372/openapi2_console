/*
 * Powered By [wellcomframework]
 */

package com.wellcom.apimng.web;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import org.g4studio.core.util.G4Utils;

import static org.quartz.CronScheduleBuilder.cronSchedule;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import com.wellcom.apimng.service.QrtzJobDetailsService;
import com.wellcom.util.JobTools;



/**
 * @author niwb
 * @version 1.0
 * @since 1.0
 */


public class QrtzJobDetailsAction extends BaseAction {
//	private QrtzJobDetailsService qrtzJobDetailsService = (QrtzJobDetailsService) super.getService("qrtzJobDetailsService");
	protected static final ActionForward LIST_ACTION_FORWARD = new ActionForward("/apimng/qrtzjobdetails.jsp");
	
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
		 List<BaseDto> qrtzJobDetailsList = g4Reader.queryForPage("QrtzJobDetails.queryQrtzJobDetailss", dto);
		 for(BaseDto baseDto : qrtzJobDetailsList){
			 if(G4Utils.isNotEmpty(baseDto.getAsString("prev_fire_time")))   
			    baseDto.put("prev_fire_time", JobTools.fmtLongTimeToString(baseDto.getAsString("prev_fire_time")));
		    	
			 if(G4Utils.isNotEmpty(baseDto.getAsString("next_fire_time")))   
				    baseDto.put("next_fire_time", JobTools.fmtLongTimeToString(baseDto.getAsString("next_fire_time")));
		 }
		 Integer pageCount = (Integer) g4Reader.queryForObject("QrtzJobDetails.queryQrtzJobDetailssForPageCount", dto);
		 String jsonString = encodeList2PageJson(qrtzJobDetailsList, pageCount, dataFormat);
		 write(jsonString, response);
		 
		 return mapping.findForward(null);
	}
	
	/** 
	 * 新增任务
	 **/
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		  
		 BaseActionForm aForm = (BaseActionForm) form;
	     Dto inDto = aForm.getParamAsDto(request);
	     JobTools.saveJob(inDto);
		 setOkTipMsg("操作成功", response);
		 return mapping.findForward(null);
	}
	
	/** 
	 * 暂停任务
	 **/
	public ActionForward pause(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BaseActionForm aForm = (BaseActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		JobTools.pauseJob(inDto);
		setOkTipMsg("操作成功", response);
		return mapping.findForward(null);
	}
	
	/** 
	 * 恢复任务
	 **/
	public ActionForward resume(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BaseActionForm aForm = (BaseActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		JobTools.resumeJob(inDto);
		setOkTipMsg("操作成功", response);
		return mapping.findForward(null);
	}
	
	
	/** 
	 * 立即执行
	 **/
	public ActionForward runAtOnce(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BaseActionForm aForm = (BaseActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		JobTools.runAtJob(inDto);
		setOkTipMsg("操作成功", response);
		return mapping.findForward(null);
	}
	
	/** 
	 * 删除任务
	 **/
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BaseActionForm aForm = (BaseActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		JobTools.delJob(inDto);
		setOkTipMsg("操作成功", response);
		return mapping.findForward(null);
	}
	
}

