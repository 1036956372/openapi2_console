<%@ page contentType="text/html; charset=utf-8"%>
<!-- 引入taglib.jsp后就可以使用G4UI标签库 -->
<%@ include file="/common/include/taglib.jsp"%>
<G4Studio:html title="${sysTitle}" showLoading="false" exportParams="true"
	isSubPage="false" exportExceptionWindow="true" exportUserinfo="true">
<G4Studio:import src="/resource/commonjs/extTabCloseMenu.js" />
<G4Studio:import src="/system/admin/js/index.js" />
<G4Studio:ext.codeStore fields="SEX" />
<G4Studio:body>
	<!-- 生成平台主体布局和菜单导航结构的标签,平台内部标签，二次开发不会使用该标签 -->
	<G4Studio:arm.Viewport northTitle="${sysTitle}" westTitle="${westTitle}" />
</G4Studio:body>
</G4Studio:html>