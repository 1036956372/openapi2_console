package com.wellcom.student.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.g4studio.common.web.BaseAction;
import org.g4studio.common.web.BaseActionForm;
import org.g4studio.core.metatype.Dto;
import org.g4studio.core.metatype.impl.BaseDto;
import org.g4studio.core.mvc.xstruts.action.ActionForm;
import org.g4studio.core.mvc.xstruts.action.ActionForward;
import org.g4studio.core.mvc.xstruts.action.ActionMapping;

import com.wellcom.student.service.StudentService;

public class StudentAction extends BaseAction {
	private StudentService studentService = (StudentService)super.getService("studentService");
	protected static final ActionForward LIST_ACTION_FORWARD = new ActionForward("/stumng/stuIndex.jsp");		//protected当前类，同包之中的类。子类可访问
	protected final String dataFormat = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 学生信息系统界面初始化
	 * */
	public ActionForward init(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
		return LIST_ACTION_FORWARD;
	}
	
	/**
	 * 搜索学生,分页搜索,若有参数则条件查找，无参则查找全部
	 * @throws IOException 
	 * @throws SQLException 
	 * */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
		BaseActionForm actionForm = (BaseActionForm)form;
		Dto inDto = actionForm.getParamAsDto(request);
		List studentsList = g4Reader.queryForPage("Students.queryInfo", inDto);
		Integer totalCount = (Integer) g4Reader.queryForObject("Students.queryStudentsForPageCount",inDto);
		String jsonString = encodeList2PageJson(studentsList, totalCount, dataFormat);
		write(jsonString,response);
		return mapping.findForward(null);
	}
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws IOException {
		BaseActionForm actionForm = (BaseActionForm) form;
		Dto inDto = actionForm.getParamAsDto(request);
		studentService.saveStuInfo(inDto);
		setOkTipMsg("操作成功",response);
		return mapping.findForward(null);
	}
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws IOException {
		BaseActionForm actionForm = (BaseActionForm) form;
		Dto inDto = actionForm.getParamAsDto(request);
		studentService.updateStuInfo(inDto);
		setOkTipMsg("操作成功",response);
		return mapping.findForward(null);
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws IOException {
		String checked = request.getParameter("checked");
		BaseDto inDto = new BaseDto();
		inDto.put("strChecked", checked);
		studentService.deleteStuInfo(inDto);
		setOkTipMsg("操作成功", response);
		return mapping.findForward(null);
	}
}
