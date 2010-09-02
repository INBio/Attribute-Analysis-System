<%-- 
    Document   : changePass
    Created on : Sep 1, 2010, 2:51:06 PM
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
            function validateParameter(){
                //Getting the form reference
                var form = document.getElementById('user');
                //Validating pass
                var pass = document.getElementById('password').value;
                var pass2 = document.getElementById('password2').value;
                if(pass == "" || pass2 == ""){
                    alert('<fmt:message key="pass_empty"/>');
                    return;
                }
                if(pass != pass2){
                    alert('<fmt:message key="equal_pass"/>');
                    return;
                }
                else{
                    form.submit();
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
                <form:form method="POST" commandName="user">
                    <br><br>

                    <label>
                        <b><fmt:message key="login_username"/>:  </b>${usuario}<br>
                    </label>

                    <br>

                    <label>
                        <b><fmt:message key="new_pass"/>:</b><br>
                        <form:password id="password" path="password" size="25"></form:password><br>
                    </label>

                    <br>

                    <label>
                        <b><fmt:message key="confirm_pass"/>:</b><br> 
                        <input type="password" id="password2" size="25"><br><br>
                    </label>

                    <input type="button" value="<fmt:message key="accept"/>"
                           onclick="validateParameter()" />

                    <br><br>
                </form:form>
            </div>

            <!-- ading space -->
            <br><br>

            <!-- Footer -->
            <div id="footer">
                <fmt:message key="footer_text"/>
            </div>
        </div>
        <!-- Content ending -->
    </body>
</html>
