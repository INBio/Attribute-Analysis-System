<%-- 
    Document   : connlayer
    Created on : Jul 1, 2010, 8:31:38 AM
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
        <script type="text/javascript">
            //Verifing mandatory data
            function verifyLayer(){
                var form = document.getElementById('connection');
                var driver = document.getElementById('driverClassName');
                var url = document.getElementById('url');
                var user = document.getElementById('username');
                var pass = document.getElementById('password');
                if(driver.value != 'unmapped' && url.value != '' && url.value != 'unmapped' && user.value != '' && user.value != 'unmapped'
                    && pass.value != '' && pass.value != 'unmapped'){
                    form.submit();
                }
                else{
                    alert('<fmt:message key="mandatory_values"/>');
                }
            }
        </script>
    </head>
    <body>
        <!-- Content -->
        <div id="contenido">
            <!-- Header -->
            <jsp:include page="/WEB-INF/jsp/header.jsp"/>

            <div id="content">
                <h2><fmt:message key="mapping_connection"/></h2><br>
                <!-- Form that represents all the layer attributes -->
                <form:form method="POST" commandName="connection" cssStyle="margin:0">
                    <div id="configConn" style="width:500px">
                        <table class="contacts" cellspacing="0">
                            <tr>
                                <td class="contact2" width="40%"><fmt:message key="driver_class_name"/>:</td>
                                <td class="contact2" width="60%">
                                    <form:select id="driverClassName" path="driverClassName" cssClass="sizeAll">
                                        <form:option value="unmapped"><fmt:message key="drop_down_null_option"/></form:option>
                                        <form:option value="org.postgresql.Driver"><fmt:message key="postgresDriver"/></form:option>
                                    </form:select>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact2" width="40%"><fmt:message key="db_url"/>:</td>
                                <td class="contact2" width="60%">
                                    <form:input id="url" path="url" cssClass="sizeAll"></form:input>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact2" width="40%"><fmt:message key="login_username"/>:</td>
                                <td class="contact2" width="60%">
                                    <form:input id="username" path="username" cssClass="sizeAll"></form:input>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact2" width="40%"><fmt:message key="login_password"/>:</td>
                                <td class="contact2" width="60%">
                                    <form:input id="password" path="password" cssClass="sizeAll"></form:input>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <!-- Button's acctions -->
                    <div id="buttons">
                        <a href="config.htm" class="simple_link"><fmt:message key="back"/></a>
                        <input type="button" class="simple_button" id="saveConnAttributes" value="<fmt:message key="test_conn"/>" onclick="verifyLayer()"/>
                    </div><br>
                </form:form>
            </div>

            <!-- Footer -->
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
        </div>
        <!-- Content ending -->
    </body>
</html>
