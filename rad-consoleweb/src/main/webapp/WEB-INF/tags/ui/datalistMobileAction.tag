<%@ tag trimDirectiveWhitespaces="true" %>
<%@ tag import="net.purwana.rads.apps.datalist.service.JsonUtil"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<%@ attribute name="action" required="true" type="java.lang.Object" %>
<%@ attribute name="row" required="true" type="java.lang.Object" %>
<%@ attribute name="menuId" required="true" type="java.lang.Object" %>

<%= JsonUtil.buildMobileActionLink(action, row, menuId) %>