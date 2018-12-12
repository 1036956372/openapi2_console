/*
 * Powered By [wellcomframework]
 */

package com.wellcom.apimng.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.g4studio.common.web.BaseAction;
import org.g4studio.common.web.BaseActionForm;
import org.g4studio.core.metatype.Dto;
import org.g4studio.core.metatype.impl.BaseDto;
import org.g4studio.core.mvc.xstruts.action.ActionForm;
import org.g4studio.core.mvc.xstruts.action.ActionForward;
import org.g4studio.core.mvc.xstruts.action.ActionMapping;
import org.g4studio.core.orm.xibatis.common.util.PaginatedArrayList;
import org.g4studio.core.util.G4Utils;

import com.wellcom.apimng.service.MornitorNodeAppService;
import com.wellcom.util.FileMapContainerFactory;
import com.wellcom.util.FttpUtil;



/**
 * @author niwb
 * @version 1.0
 * @since 1.0
 */


public class MornitorNodeAppAction extends BaseAction {
	private Log log =LogFactory.getLog(MornitorNodeAppAction.class);
	private MornitorNodeAppService mornitorNodeAppService = (MornitorNodeAppService) super.getService("mornitorNodeAppService");
	
	protected static final ActionForward LIST_ACTION_FORWARD = new ActionForward("/apimng/mornitornodeapp.jsp");
	
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
		 List mornitorNodeAppList = g4Reader.queryForPage("MornitorNodeApp.queryMornitorNodeApps", dto);
		 Integer pageCount = (Integer) g4Reader.queryForObject("MornitorNodeApp.queryMornitorNodeAppsForPageCount", dto);
		 String jsonString = encodeList2PageJson(mornitorNodeAppList, pageCount, dataFormat);
		 write(jsonString, response);
		 
		 return mapping.findForward(null);
	}
	
	/** 
	 * 执行搜索 获取指定ip、指定文件夹下的目录及文件信息
	 **/
	public ActionForward listLogFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		
		BaseActionForm aForm = (BaseActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		//fttp路径
		String fttppath = java.net.URLDecoder.decode(dto.getAsString("fttppath"),"utf-8");
		String iphost = dto.getAsString("iphost");
		if (G4Utils.isEmpty(iphost)){
			iphost ="192.168.193.240";
		}
		String logpath = dto.getAsString("logpath");
		if (G4Utils.isEmpty(logpath)){
			logpath = "d:/appopenapi2/log/post/";
		}else {
			//logpath.replaceAll("\\", "/");
			if(!logpath.endsWith("/"))
			logpath = logpath +"/";
		}

		String start = dto.getAsString("start");
		int startInt =0;
		if (G4Utils.isNotEmpty(start)) {
			startInt = Integer.parseInt(start);
		}
		String limit = dto.getAsString("limit");
		int limitInt = 20;
		if (G4Utils.isNotEmpty(limit)) {
			limitInt = Integer.parseInt(limit);
		}
		int pageIndex = startInt/limitInt;
		List<BaseDto> fileList = null;
		//从缓存中获取指定文件夹下的目录及文件信息
		if (G4Utils.isNotEmpty(fttppath)) {
			fileList = FileMapContainerFactory.getDirFiles(fttppath);
		}else{
		    fileList = FileMapContainerFactory.getDirFiles(iphost, logpath);
		}
        //分页处理
		PaginatedArrayList paginatedList = new PaginatedArrayList(fileList, pageIndex, limitInt);
		//分页结果集
		List pageFileList = paginatedList.getPage();
		String jsonString = encodeList2PageJson(pageFileList, fileList.size(), null);
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
		 mornitorNodeAppService.saveMornitorNodeApp(inDto);
		 setOkTipMsg("操作成功", response);
		 return mapping.findForward(null);
	}
	
	
	/**
	 * 查看最新的行数的 日志文件信息
	 * 
	 **/
	public ActionForward tailfile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 BaseActionForm aForm = (BaseActionForm) form;
		 Dto inDto = aForm.getParamAsDto(request);
 		 //结束行数
		 Integer end = inDto.getAsInteger("end");
		 if(end == null)
			 end =0;
		 //读取行数
		 Integer num = inDto.getAsInteger("num");
		 if(num == null )
			 num =100;
		 String fttppath = java.net.URLDecoder.decode(inDto.getAsString("fttppath"),"utf-8");
		 String logdata = FttpUtil.tailFile(fttppath, end, num);
		 String numform = "<form>读取行数:<input type='text' id='num'/><input type='button' id='tj' value='查询' onclick='ListByNum()'/></form>";
		 String numformjs = "<script>function ListByNum(){"
				 +"if(document.getElementById('num').value!=''){"
				 +"var arr = window.location.href.split('?');"
				 +"var ar = arr[0];"
				 +"window.location.href=ar+'?fttppath='+GetQueryString('fttppath')+'&num='+document.getElementById('num').value;"
				 	+"}}"
				 +"function GetQueryString(name){"
			     +"var reg = new RegExp('(^|&)'+ name +'=([^&]*)(&|$)');"
			     +"var r = window.location.search.substr(1).match(reg);"
			     +"if(r!=null){return  r[2]}; return null;"
			     +"}</script>";
		 StringBuffer logdata_html = new StringBuffer();
		 logdata= logdata.replaceAll("&timestamp", "&amp;timestamp");
		 logdata= logdata.replaceAll("&current_page", "&amp;current_page");
		 //logdata_html.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />");
		 logdata_html.append("<body>");
		 logdata_html.append(numform);
		 logdata_html.append("<pre>");
		 logdata_html.append(logdata);
		 logdata_html.append("</pre></body>");
		 logdata_html.append(numformjs);
		 
		 
		 //response.setContentType("text/html;charset=utf8");
		 this.write(logdata_html.toString(), response);
		 
		 return mapping.findForward(null);
	}
	
	
	/**
	 *  日志文件下载
	 * 
	 **/
	@SuppressWarnings("unused")
	public ActionForward downfile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BaseActionForm aForm = (BaseActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		String fttppath = java.net.URLDecoder.decode(inDto.getAsString("fttppath"),"UTF-8");
		OutputStream out = null;
		try {
			Dto dto = FttpUtil.getFileBean(fttppath);
			 if(dto!=null){
				 response.setContentType("application/x-download");
				 String	fileName = URLEncoder.encode(dto.getAsString("fileName"), "UTF-8");
				 response.addHeader("Content-Disposition",
							"attachment; filename=" + fileName);
				 response.setContentLength(dto.getAsInteger("fileSize"));
				 out = response.getOutputStream();
				 out.write((byte[])dto.get("fileContent"));
			 }else{
				 throw new Exception("文件"+fttppath+"不存在!");
			 }
		} catch (Exception ex) {
//			ex.printStackTrace();
			log.error("后台处理异常,下载文件"+fttppath+"失败!",ex);
			 throw new Exception("后台处理异常,下载文件"+fttppath+"失败!");
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
		return mapping.findForward(null);
	}
	
	
	/**
	 * 更新对象
	 **/
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 BaseActionForm aForm = (BaseActionForm) form;
		 Dto inDto = aForm.getParamAsDto(request);
		 mornitorNodeAppService.updateMornitorNodeApp(inDto);
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
		mornitorNodeAppService.deleteMornitorNodeApp(inDto);
		setOkTipMsg("操作成功", response);
		return mapping.findForward(null);
	}
	
	
}

