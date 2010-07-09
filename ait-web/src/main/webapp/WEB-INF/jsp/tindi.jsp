<%-- 
    Document   : tindi
    Created on : Jul 9, 2010, 12:47:34 PM
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
            function verifyTindiMapping(){
                var form = document.getElementById('attributes');
                var tii = document.getElementById('taxon_indicator_id');
                var tsn = document.getElementById('taxon_scientific_name');
                var ii = document.getElementById('indicator_id');
                if(tii.value != 'unmapped' && tsn.value != 'unmapped' && ii.value != 'unmapped'){
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
                <h2><fmt:message key="tindi_mapping"/></h2><br>
                <!-- Form that represents all the dwc attributes -->
                <form:form method="POST" commandName="attributes" cssStyle="margin:0">
                    <div id="configCols" style="width:450px">
                        <table class="contacts" cellspacing="0">
                            <tr>
                                <th class="contactDept" ><fmt:message key="concept"/></th>
                                <th class="contactDept" ><fmt:message key="column"/></th>
                            </tr>
                            <tr>
                                <td class="contact" width="50%">(*) <fmt:message key="taxon_indicator_id"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="taxon_indicator_id" path="taxon_indicator_id" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%">(*) <fmt:message key="taxon_scientific_name"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="taxon_scientific_name" path="taxon_scientific_name" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%">(*) <fmt:message key="indicator_id"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="indicator_id" path="indicator_id" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%"> <fmt:message key="taxon_indicator_certainty_level"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="taxon_indicator_certainty_level" path="taxon_indicator_certainty_level" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%"> <fmt:message key="taxon_indicator_evaluation_criteria"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="taxon_indicator_evaluation_criteria" path="taxon_indicator_evaluation_criteria" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%"> <fmt:message key="taxon_indicator_regionality"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="taxon_indicator_regionality" path="taxon_indicator_regionality" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%"> <fmt:message key="taxon_indicator_temporality"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="taxon_indicator_temporality" path="taxon_indicator_temporality" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%"> <fmt:message key="taxon_indicator_references"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="taxon_indicator_references" path="taxon_indicator_references" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%"> <fmt:message key="taxon_indicator_notes"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="taxon_indicator_notes" path="taxon_indicator_notes" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%"> <fmt:message key="taxon_indicator_valuer_person"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="taxon_indicator_valuer_person" path="taxon_indicator_valuer_person" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="contact" width="50%"> <fmt:message key="component_part"/></td>
                                <td class="contact" width="50%">
                                    <form:select id="component_part" path="component_part" items="${cols}" cssClass="componentSize"/>
                                </td>
                            </tr>
                        </table>
                        <a href="config.htm" class="simple_link"><fmt:message key="back"/></a>
                        <input type="button" class="simple_button" id="saveAttributes" value="<fmt:message key="save"/>" onclick="verifyTindiMapping()"/>
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
