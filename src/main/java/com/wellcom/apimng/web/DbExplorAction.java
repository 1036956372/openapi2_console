package com.wellcom.apimng.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.g4studio.common.web.BaseAction;
import org.g4studio.core.mvc.xstruts.action.ActionForm;
import org.g4studio.core.mvc.xstruts.action.ActionForward;
import org.g4studio.core.mvc.xstruts.action.ActionMapping;

import qimingx.dbe.DBConnectionState;

public class DbExplorAction extends BaseAction {
	protected static final ActionForward LIST_ACTION_FORWARD = new ActionForward("/dbe/main.html");
	/**
	 * 事件跟踪页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*DBConnectionState dbcs = (DBConnectionState)request.getSession().getAttribute(DBConnectionState.KEY_CURRENT_STATE);
		System.out.println(dbcs.toString());*/
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
		return LIST_ACTION_FORWARD;
	}

}
