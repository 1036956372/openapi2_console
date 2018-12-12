package com.wellcom.api;

import java.util.ArrayList;
import java.util.List;

public class ApiDebugJson {
	private int apicategory;
	private String apimethod;
	private String returnformat;
	private String httpmethod;
	private String appkey;
	private String appsecret;
	private List<Fields> fields = new ArrayList<Fields>();
	
	public int getApicategory() {
		return apicategory;
	}
	public void setApicategory(int apicategory) {
		this.apicategory = apicategory;
	}
	public String getApimethod() {
		return apimethod;
	}
	public void setApimethod(String apimethod) {
		this.apimethod = apimethod;
	}
	public String getReturnformat() {
		return returnformat;
	}
	public void setReturnformat(String returnformat) {
		this.returnformat = returnformat;
	}
	public String getHttpmethod() {
		return httpmethod;
	}
	public void setHttpmethod(String httpmethod) {
		this.httpmethod = httpmethod;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public List<Fields> getFields() { 
		return fields; 
	}
    public void setFields(List<Fields> fields) { 
    	this.fields = fields; 
    }
}

