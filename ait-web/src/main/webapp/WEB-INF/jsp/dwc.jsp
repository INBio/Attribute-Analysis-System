<%-- 
    Document   : configDwc
    Created on : Jun 22, 2010, 5:09:33 PM
    Author     : esmata
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tags.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css"
        href="<c:out value="${pageContext.request.contextPath}"/>/<spring:theme code='styleSheet'/>"/>
        <title><fmt:message key="title"/></title>
    </head>
    <body>
        <!-- Header -->
        <jsp:include page="/WEB-INF/jsp/header.jsp"/>
        <!-- Content -->
        <div id="contenido">
            <h2><fmt:message key="dwc_mapping"/></h2><br>

            <!-- Form that represents all the dwc attributes -->
            <form:form method="POST" commandName="attributes" cssStyle="margin:0">
                <table class="contacts" cellspacing="0">
                    <tr>
                        <th class="contactDept" ><fmt:message key="concept"/></th>
                        <th class="contactDept" ><fmt:message key="column"/></th>
                        <th class="contactDept" ><fmt:message key="type"/></th>
                    </tr>
                    <tr>
                        <td class="contact" width="33%">GlobalUniqueIdentifier</td>
                        <td class="contact" width="33%">
                            <form:select id="globaluniqueidentifier" path="globaluniqueidentifier" cssClass="componentSize">
                                <form:option value="0"><fmt:message key="drop_down_null_option"/></form:option>
                                <form:option value="1"><fmt:message key="taxonomical_criteria_title"/></form:option>
                                <form:option value="2"><fmt:message key="geografical_criteria_title"/></form:option>
                                <form:option value="3"><fmt:message key="indicators_criteria_title"/></form:option>
                            </form:select>
                        </td>
                        <td class="contact" width="34%">
                            <form:select id="datelastmodified" path="datelastmodified" cssClass="componentSize">
                                <form:option value="0"><fmt:message key="drop_down_null_option"/></form:option>
                                <form:option value="1"><fmt:message key="taxonomical_criteria_title"/></form:option>
                                <form:option value="2"><fmt:message key="geografical_criteria_title"/></form:option>
                                <form:option value="3"><fmt:message key="indicators_criteria_title"/></form:option>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="contact" width="33%">LastDateModified</td>
                        <td class="contact" width="33%"></td>
                        <td class="contact" width="34%"></td>
                    </tr>
                </table>
                <input type="submit" class="main_Button" id="saveAttributes" value="<fmt:message key="save"/>"/>
            </form:form>

        </div>
        <!-- Content ending -->
        <div id="footer">
            <fmt:message key="footer_text"/>
        </div>
    </body>
</html>
