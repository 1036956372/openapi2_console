<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<G4Studio:html title="Api命令调试管理(apidebug)">
<G4Studio:import src="/apimng/js/apidebug.js" />
<G4Studio:ext.codeRender fields="SEX,LOCKED,USERTYPE,HTTPMETHOD,RETURNFORMAT,ISMUST,OPENAPI"/>		<!-- 用于将字典数据(EACODE 表)生成render 函数(即将代码转为中文描述)，供表格组件使用 -->
<G4Studio:ext.codeStore fields="SEX,LOCKED,USERTYPE:3,HTTPMETHOD,RETURNFORMAT,ISMUST,OPENAPI"/>		<!-- 用于将字典数据(EACODE 表)生成Ext.data.SimpleStore 对象，供表格组件和下拉框组件使用。 -->
<G4Studio:body>
</G4Studio:body>
<G4Studio:script>
   var apiSystemid = '<G4Studio:out key="apiSystemid" scope="request"/>';			<!-- 1 -->					<!-- 向页面输出request作用域,session作用域,application作用域内存存储的变量 -->
</G4Studio:script>
<G4Studio:ext.uiGrant/>
</G4Studio:html>