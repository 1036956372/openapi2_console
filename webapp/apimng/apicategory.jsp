<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<G4Studio:html title="Api类目表(apicategory)">
<G4Studio:import src="/apimng/js/apicategory.js" />
<G4Studio:ext.codeRender fields="SEX,LOCKED,USERTYPE,OPENAPI"/>
<G4Studio:ext.codeStore fields="SEX,LOCKED,USERTYPE:3,OPENAPI"/>
<G4Studio:body>
</G4Studio:body>
<G4Studio:ext.uiGrant/>
</G4Studio:html>