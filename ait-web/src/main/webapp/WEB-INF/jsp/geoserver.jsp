<%-- 
    Document   : hello
    Created on : 11/01/2011, 11:13:15 AM
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
            function verifyValues(){
                var form = document.getElementById('values');
                var serverIpAddress = document.getElementById('serverIpAddress');
                var layersWorkSpace = document.getElementById('layersWorkSpace');
                if(serverIpAddress.value != '' && layersWorkSpace.value != ''){
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

               <h2><fmt:message key="geo_conf_title"/></h2><br>

                <form:form method="POST" commandName="values" cssStyle="margin:4px">
                    <div id="configConn" style="width:800px">
                        <table class="contacts" cellspacing="0">
                            <tr>
                                <td class="contact2" width="33%"><fmt:message key="geo_ip"/>:</td>
                                <td class="contact2" width="33%">
                                    <form:input id="serverIpAddress" path="serverIpAddress" cssClass="sizeAll"></form:input>
                                </td>
                                <td class="contact2" width="34%">e.g: http://255.255.255.255:80</td>
                            </tr>
                            <tr>
                                <td class="contact2" width="33%"><fmt:message key="geo_workspace"/>:</td>
                                <td class="contact2" width="33%">
                                    <form:input id="layersWorkSpace" path="layersWorkSpace" cssClass="sizeAll"></form:input>
                                </td>
                                <td class="contact2" width="34%">e.g: attributes:</td>
                            </tr>
                        </table>
                    </div>
                    <!-- Button's acctions -->
                    <div id="buttons">
                        <a href="config.htm" class="simple_link"><fmt:message key="back"/></a>
                        <input type="button" class="simple_button" id="saveGeoAttributes" value="<fmt:message key="save"/>" onclick="verifyValues()"/>
                    </div><br>
                </form:form>

            <!-- Footer -->
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
        </div>
        <!-- Content ending -->
    </body>
</html>