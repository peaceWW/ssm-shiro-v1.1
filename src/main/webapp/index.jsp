<%-- <%@page import="java.net.URLEncoder"%> --%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 重定向到检索页面 -->
<%-- <%response.sendRedirect("warning.action"); %> --%>
<!-- jsp实现转发 -->
<%--<jsp:forward page="customerServiceWarning.html" />--%>

<script language="javascript">
    document.location = "${pageContext.request.contextPath}/admin/pagejump/index";
</script>
