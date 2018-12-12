<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<G4Studio:html title="部署程序信息登记表(mornitornodeapp)">
<G4Studio:import src="/apimng/js/mornitornodeapp.js" />
<G4Studio:ext.codeRender fields="SEX,LOCKED,USERTYPE,APPSTATUS,HOSTIP,APPNAMEID,APPENV"/>
<G4Studio:ext.codeStore fields="SEX,LOCKED,USERTYPE:3,APPSTATUS,HOSTIP,APPNAMEID,APPENV"/>
<G4Studio:body>
</G4Studio:body>
<G4Studio:ext.uiGrant/>
</G4Studio:html>

