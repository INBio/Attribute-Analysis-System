<%-- 
    Document   : indi
    Created on : Jul 8, 2010, 4:13:47 PM
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
            function verifyIndiMapping(){
                var form = document.getElementById('attributes');
                var iid = document.getElementById('indicator_id');
                var iname = document.getElementById('indicator_name');
                if(iid.value != 'unmapped' && iname.value != 'unmapped'){
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
                <h2><fmt:message key="indi_mapping"/></h2><br>
                <!-- Form that represents all the dwc attributes -->
                <form:form method="POST" commandName="attributes" cssStyle="margin:0">
                    <div id="configCols" style="width:450px">
                        <table class="contacts" cellspacing="0">
                            <tr>
                                <th class="contactDept" ><fmt:message key="concept"/></th>
                                <th class="contactDept" ><fmt:message key="column"/></th>
                            </tr>
                            <tr>
                                <td class="contact" width="50%">(*) <fmt:message key="indicator_id"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="indicator_id" path="indicator_id" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%">(*) <fmt:message key="indicator_name"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="indicator_name" path="indicator_name" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%"> <fmt:message key="indicator_description"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="indicator_description" path="indicator_description" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%"> <fmt:message key="indicator_applies_to_part"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="indicator_applies_to_part" path="indicator_applies_to_part" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%"> <fmt:message key="indicator_ancestor_id"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="indicator_ancestor_id" path="indicator_ancestor_id" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%"> <fmt:message key="indicator_references"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="indicator_references" path="indicator_references" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                        </table>
                        <a href="config.htm" class="simple_link"><fmt:message key="back"/></a>
                        <input type="button" class="simple_button" id="saveAttributes" value="<fmt:message key="save"/>" onclick="verifyIndiMapping()"/>
                        <br><br>
                    </div>
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