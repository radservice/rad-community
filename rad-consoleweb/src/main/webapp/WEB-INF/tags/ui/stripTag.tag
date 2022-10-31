<%@ tag trimDirectiveWhitespaces="true" %>
<%@ tag import="net.purwana.rads.commons.util.StringUtil"%>
<%@ tag import="net.purwana.rads.apps.app.service.AppUtil"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<%@ attribute name="html" required="true" %>
<%@ attribute name="relaxed" required="false" %>
<%@ attribute name="processHashVariable" required="false" %>
<%@ attribute name="appDef" required="false" type="net.purwana.rads.apps.app.model.AppDefinition" %>

<%= (!"true".equals(relaxed)) ? StringUtil.stripAllHtmlTag((("true".equals(processHashVariable)) ? AppUtil.processHashVariable(html, null, null, null, appDef) : html)) : StringUtil.stripHtmlRelaxed((("true".equals(processHashVariable)) ? AppUtil.processHashVariable(html, null, null, null, appDef) : html)) %>
