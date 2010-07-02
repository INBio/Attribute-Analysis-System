<%-- 
    Document   : layer
    Created on : Jul 1, 2010, 10:13:17 AM
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
            //Validate mandatory field
            function validate(){
                var form = document.getElementById('attributes');
                var baseLayer = document.getElementById('base');
                if(baseLayer.value != 'unmapped'){
                    form.submit();
                }
                else{
                    alert('<fmt:message key="mandatory_values"/>');
                }
            }
        </script>
    </head>
    <body>
        <!-- Header -->
        <jsp:include page="/WEB-INF/jsp/header.jsp"/>
        <!-- Content -->
        <div id="contenido">
            <h2><fmt:message key="select_layers"/></h2><br>            

            <!-- Form that represents all the dwc attributes -->
            <form:form method="POST" commandName="attributes" cssStyle="margin:0">
                <a><fmt:message key="select_base"/>(*):</a>
                <form:select id="base" path="base" items="${tables}" cssClass="componentSize"/><br><br>
                <a><fmt:message key="select_layers_desc"/>:</a><br>
                <div id="tableDiv" style="width:400px">
                    <table class="contacts" cellspacing="0">
                        <c:forEach var="var" items="${tables}" begin="1">
                            <tr>
                                <td class="contact">${var}</td>
                                <td class="contact"><form:checkbox path="layers" value="${var}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <a href="config.htm" class="simple_link"><fmt:message key="back"/></a>
                <input type="button" class="simple_button" id="saveAttributes" value="<fmt:message key="save"/>" onclick="validate()"/>
                <br><br>
            </form:form>
            <!-- Form ends -->
            
        </div>
        <!-- Content ending -->
        <div id="footer">
            <fmt:message key="footer_text"/>
        </div>
    </body>
</html>
