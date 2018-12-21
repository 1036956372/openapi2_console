package qimingx.dbe.action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.g4studio.core.properties.PropertiesFactory;
import org.g4studio.core.properties.PropertiesFile;
import org.g4studio.core.properties.PropertiesHelper;
import org.g4studio.core.server.G4Server;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import qimingx.core.ProcessResult;
import qimingx.dbe.DBConnectionState;
import qimingx.dbe.DBEConfig;
import qimingx.dbe.action.bean.ConnectParamBean;
import qimingx.spring.BaseMultiActionController;

/**
 * @author Wangwei
 * 
 * 用于提供初始化、登录、注销 等系统功能的Action
 */
@Controller("initAction") 
public class InitActionController extends BaseMultiActionController {
	// logger
	private static final Log log = LogFactory
			.getLog(InitActionController.class);

	// 保存登录历史的cookie名称
	private static final String LOGIN_COOKIE_NAME = "dbelogin";

	// 保存登录历史条目的最大个数
	private static final int LOGIN_ITEM_LENGTH = 5;

	// System init，检查登录状态
	@RequestMapping("/init.mvc")
	public void init(HttpServletRequest req, HttpServletResponse resp) {
		log.debug("call InitAction.init~~");
		HttpSession sess = req.getSession();
		boolean isLogin = DBConnectionState.isConnection(sess);
		JSONObject json = new JSONObject();
		json.element("success", true);
		json.element("login", isLogin);
		String jsonString = json.toString();
		sendJSON(resp, jsonString);
	}

	// Support db types
	public void dbtypes(HttpServletRequest req, HttpServletResponse resp) {
		log.debug("call InitAction.dbtypes~~");
		String json = DBEConfig.getInstance().getDBTypeOptions();
		sendJSON(resp, json);
	}

	// 得到登陆历史
	public void history(HttpServletRequest req, HttpServletResponse resp) {
		log.debug("get login history~~");
		String historys = "";
		Cookie cookie = WebUtils.getCookie(req, LOGIN_COOKIE_NAME);
		if (cookie != null) {
			log.debug("读取到了登录历史...:" + cookie.getValue());

			String[] loginItems = cookie.getValue().split("&");
			for (String loginItem : loginItems) {
				ConnectParamBean param = null;
				try {
					param = ConnectParamBean.fromCookieValue(loginItem);
				} catch (Throwable e) {
					log.warn("ConnectParamBean.fromCookieValue.error:"
							+ e.getMessage() + "[" + loginItem + "]");
					continue;
				}

				if (historys.length() > 0) {
					historys += ",";
				}
				historys += param.getHistoryRecord();
			}

		}

		String json = null;
		if (historys != null && historys.length() > 0) {
			json = "{root:[" + historys + "]}";
		} else {
			json = "{root:[{ history:'没有历史登录记录~'}]}";
		}
		sendJSON(resp, json);
	}
	
	//loginSelf
	public void loginSelf(HttpServletRequest req,HttpServletResponse resp,ConnectParamBean param) {
		log.debug("call InitAction.loginSelf");
		ProcessResult<String> pr = null;
		PropertiesHelper pHelper = PropertiesFactory. getPropertiesHelper (PropertiesFile. JDBC );
		String dbName = null;
		//判断当前数据库类型，若为mysql,则首字母是m，若为oracle ，则首字母为o
		switch (pHelper.getValue("g4.jdbc.url").split(":")[1].charAt(0)) {
		case 'm':
			dbName="MySQL";
			break;
		case 'o':
			dbName="ORACLE10g";
			break;
		default:
			break;
		}
		Connection conn;
		try {
			conn = g4Reader.getConnection();
			pr = DBConnectionState.connect(dbName, conn, req.getSession());
		} catch (SQLException e1) {
			log.error("使用平台数据库连接失败");
		}
		JSONObject json = new JSONObject();
		if (pr.isSuccess()) {
			json.element("success", true);
		} else {
			json.element("success", false);
			json.element("msg", pr.getMessage());
		}
		sendJSON(resp, json.toString());
	}
	
	

	// Login
	public void login(HttpServletRequest req, HttpServletResponse resp,
			ConnectParamBean param) {
		log.debug("call InitAction.login,param:" + param);
		
		//若登录用户为本系统用户，不新建connection，直接使用当前connection对象
		PropertiesHelper pHelper =
				PropertiesFactory. getPropertiesHelper (PropertiesFile. JDBC );
		ProcessResult<String> pr = null;
		if(param.getUrl().equals(pHelper.getValue("g4.jdbc.url"))&&param.getUser().equals(pHelper.getValue("g4.jdbc.username"))) {
			try {
				pr = DBConnectionState.connect(param.getDbtype(), g4Reader.getConnection(), req.getSession());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("使用平台数据库连接失败");
			}
					
		}else {
			pr = DBConnectionState.connect(param, req.getSession(true));
			
		}
		
		JSONObject json = new JSONObject();
		if (pr.isSuccess()) {
			json.element("success", true);
			storeConnectionParam(req, resp, param);
		} else {
			json.element("success", false);
			json.element("msg", pr.getMessage());
		}
		sendJSON(resp, json.toString());
	}

	// Logout~~
	public void logout(HttpServletRequest req, HttpServletResponse resp) {
		log.debug("call InitAction.logout~~");
		HttpSession sess = req.getSession();
		try {
			if (sess != null) {
				DBConnectionState dbcs = DBConnectionState.current(sess);
				if (dbcs != null) {
					//若当前session中的数据库连接对象是系统数据库连接对象，不能释放该数据库连接，把该dbcs从session中删除即可
					if(dbcs.getDbConnection()==g4Reader.getConnection()) {		
						sess.removeAttribute(DBConnectionState.KEY_CURRENT_STATE);
					}
					//若当前session中的数据库连接对象不是系统数据库连接对象，释放数据库连接，把该dbcs从session中删除
					else {
						dbcs.destroy(sess);
					}
					//不能销毁session，让它跳转到登录界面就行了
			//		sess.invalidate();
				}
			}
		}catch (SQLException e) {
			// TODO: handle exception
			logger.error("不能得到系统数据库连接，太可怕了");
		}
		

		JSONObject json = new JSONObject();
		json.element("success", true);
		sendJSON(resp, json.toString());
	}

	// Load：从登录历史中load上次登录信息
	public void load(HttpServletRequest req, HttpServletResponse resp) {
		log.debug("call InitAction.load~~");
		Cookie cookie = WebUtils.getCookie(req, LOGIN_COOKIE_NAME);
		if (cookie != null) {
			String[] loginItems = cookie.getValue().split("&");
			if (loginItems.length > 0) {
				JSONArray jsonData = new JSONArray();
				String value = loginItems[0];
				ConnectParamBean pb = ConnectParamBean.fromCookieValue(value);
				jsonData.add(JSONSerializer.toJSON(pb));

				JSONObject json = new JSONObject();
				json.element("success", true);
				json.element("data", jsonData);
				sendJSON(resp, json.toString());
			}
		}
	}

	// welcome,用于显示数据库属性信息
	public void welcome(HttpServletRequest req, HttpServletResponse resp) {
		log.debug("call InitAction.welcome~~");
		HttpSession sess = req.getSession(true);
		DBConnectionState dbcs = DBConnectionState.current(sess);
		if (dbcs != null) {
			JSON json = dbcs.getDBProperties();
			sendJSON(resp, json.toString());
		} else {
			JSONObject json = new JSONObject();
			json.element("success", true);
			sendJSON(resp, json.toString());
		}
	}

	// 存储链接参数 到登录历史中
	private void storeConnectionParam(HttpServletRequest req,
			HttpServletResponse resp, ConnectParamBean param) {
		// 当前登录信息 cookieValue
		String value = param.toCookieValue();

		// 检查是否已有cookie存在，以确定是创建新的登录历史 还是更新历史记录
		Cookie cookie = WebUtils.getCookie(req, LOGIN_COOKIE_NAME);
		if (cookie == null) {
			log.debug("无登录历史....");
			cookie = new Cookie(LOGIN_COOKIE_NAME, value);
		} else {
			log.debug("发现登录历史,准备重新生成登录历史列表~：");
			String[] historys = cookie.getValue().split("&");
			String current = value;
			int count = 1;
			for (String history : historys) {
				if (count == LOGIN_ITEM_LENGTH) {
					// 仅保存指定个数的登录历史..
					break;
				}
				if (!current.equalsIgnoreCase(history)) {
					value += "&";
					value += history;
					++count;
				}
			}
			cookie.setValue(value);
		}

		// 写出/更新Cookie值...
		cookie.setMaxAge(Integer.MAX_VALUE);
		resp.addCookie(cookie);
		log.debug("Store DBConnectionInfo To Cookie:" + value);
	}
}
