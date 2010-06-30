<%-- 
    Document   : plic
    Created on : Jun 24, 2010, 9:28:13 AM
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
            function verifyPlic(){
                var form = document.getElementById('attributes');
                var gui = document.getElementById('globaluniqueidentifier');
                var sn = document.getElementById('scientificname');
                var ic = document.getElementById('institutioncode');
                var dlm = document.getElementById('datelastmodified');
                var tri = document.getElementById('taxonrecordid');
                var lan = document.getElementById('language');
                var ctrs = document.getElementById('creators');
                if(gui.value != 'unmapped' && dlm.value != 'unmapped' && tri.value != 'unmapped' && ic.value != 'unmapped'
                    && lan.value != 'unmapped' && ctrs.value != 'unmapped' && sn.value != 'unmapped'){
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
            <h2><fmt:message key="plic_mapping"/></h2><br>

            <!-- Form that represents all the dwc attributes -->
            <form:form method="POST" commandName="attributes" cssStyle="margin:0">
                <div id="configCols1" style="width:450px;float:left;">
                    <table class="contacts" cellspacing="0">
                        <tr>
                            <th class="contactDept" ><fmt:message key="concept"/></th>
                            <th class="contactDept" ><fmt:message key="column"/></th>
                        </tr>
                        <tr>
                            <td class="contact" width="50%">(*) <fmt:message key="globaluniqueidentifier"/></td>
                            <td class="contact" width="50%">
                                <form:select id="globaluniqueidentifier" path="globaluniqueidentifier" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%">(*) <fmt:message key="scientificname"/></td>
                            <td class="contact" width="50%">
                                <form:select id="scientificname" path="scientificname" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%">(*) <fmt:message key="institutioncode"/></td>
                            <td class="contact" width="50%">
                                <form:select id="institutioncode" path="institutioncode" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%">(*) <fmt:message key="datelastmodified"/></td>
                            <td class="contact" width="50%">
                                <form:select id="datelastmodified" path="datelastmodified" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%">(*) <fmt:message key="taxonrecordid"/></td>
                            <td class="contact" width="50%">
                                <form:select id="taxonrecordid" path="taxonrecordid" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%">(*) <fmt:message key="language"/></td>
                            <td class="contact" width="50%">
                                <form:select id="language" path="language" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%">(*) <fmt:message key="creators"/></td>
                            <td class="contact" width="50%">
                                <form:select id="creators" path="creators" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="distribution"/></td>
                            <td class="contact" width="50%">
                                <form:select id="distribution" path="distribution" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="abstract1"/></td>
                            <td class="contact" width="50%">
                                <form:select id="abstract1" path="abstract1" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="dwc_kingdom"/></td>
                            <td class="contact" width="50%">
                                <form:select id="kingdomtaxon" path="kingdomtaxon" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="dwc_phylum"/></td>
                            <td class="contact" width="50%">
                                <form:select id="phylumtaxon" path="phylumtaxon" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="class1"/></td>
                            <td class="contact" width="50%">
                                <form:select id="classtaxon" path="classtaxon" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="orders"/></td>
                            <td class="contact" width="50%">
                                <form:select id="ordertaxon" path="ordertaxon" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="dwc_family"/></td>
                            <td class="contact" width="50%">
                                <form:select id="familytaxon" path="familytaxon" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="dwc_genus"/></td>
                            <td class="contact" width="50%">
                                <form:select id="genustaxon" path="genustaxon" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="synonyms"/></td>
                            <td class="contact" width="50%">
                                <form:select id="synonyms" path="synonyms" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="authoryearofscientificname"/></td>
                            <td class="contact" width="50%">
                                <form:select id="authoryearofscientificname" path="authoryearofscientificname" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="speciespublicationreference"/></td>
                            <td class="contact" width="50%">
                                <form:select id="speciespublicationreference" path="speciespublicationreference" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="commonnames"/></td>
                            <td class="contact" width="50%">
                                <form:select id="commonnames" path="commonnames" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="typification"/></td>
                            <td class="contact" width="50%">
                                <form:select id="typification" path="typification" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="contributors"/></td>
                            <td class="contact" width="50%">
                                <form:select id="contributors" path="contributors" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="datecreated"/></td>
                            <td class="contact" width="50%">
                                <form:select id="datecreated" path="datecreated" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="habit"/></td>
                            <td class="contact" width="50%">
                                <form:select id="habit" path="habit" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="lifecycle"/></td>
                            <td class="contact" width="50%">
                                <form:select id="lifecycle" path="lifecycle" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="reproduction"/></td>
                            <td class="contact" width="50%">
                                <form:select id="reproduction" path="reproduction" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="annualcycle"/></td>
                            <td class="contact" width="50%">
                                <form:select id="annualcycle" path="annualcycle" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="scientificdescription"/></td>
                            <td class="contact" width="50%">
                                <form:select id="scientificdescription" path="scientificdescription" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="briefdescription"/></td>
                            <td class="contact" width="50%">
                                <form:select id="briefdescription" path="briefdescription" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="feeding"/></td>
                            <td class="contact" width="50%">
                                <form:select id="feeding" path="feeding" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="behavior"/></td>
                            <td class="contact" width="50%">
                                <form:select id="behavior" path="behavior" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                    </table>
                    <a href="config.htm" class="simple_link"><fmt:message key="back"/></a>
                    <input type="button" class="simple_button" id="saveAttributes" value="<fmt:message key="save"/>" onclick="verifyPlic()"/>
                    <br><br><br>
                </div>
                <div id="configCols2" style="width:450px;margin:0 0 0 510px;">
                    <table class="contacts" cellspacing="0">
                        <tr>
                            <th class="contactDept" ><fmt:message key="concept"/></th>
                            <th class="contactDept" ><fmt:message key="column"/></th>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="interactions"/></td>
                            <td class="contact" width="50%">
                                <form:select id="interactions" path="interactions" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="chromosomicnumbern"/></td>
                            <td class="contact" width="50%">
                                <form:select id="chromosomicnumbern" path="chromosomicnumbern" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="moleculardata"/></td>
                            <td class="contact" width="50%">
                                <form:select id="moleculardata" path="moleculardata" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="populationbiology"/></td>
                            <td class="contact" width="50%">
                                <form:select id="populationbiology" path="populationbiology" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="threatstatus"/></td>
                            <td class="contact" width="50%">
                                <form:select id="threatstatus" path="threatstatus" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="legislation"/></td>
                            <td class="contact" width="50%">
                                <form:select id="legislation" path="legislation" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="habitat"/></td>
                            <td class="contact" width="50%">
                                <form:select id="habitat" path="habitat" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="territory"/></td>
                            <td class="contact" width="50%">
                                <form:select id="territory" path="territory" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="endemicity"/></td>
                            <td class="contact" width="50%">
                                <form:select id="endemicity" path="endemicity" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="theuses"/></td>
                            <td class="contact" width="50%">
                                <form:select id="theuses" path="theuses" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="themanagement"/></td>
                            <td class="contact" width="50%">
                                <form:select id="themanagement" path="themanagement" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="folklore"/></td>
                            <td class="contact" width="50%">
                                <form:select id="folklore" path="folklore" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="thereferences"/></td>
                            <td class="contact" width="50%">
                                <form:select id="thereferences" path="thereferences" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="unstructureddocumentation"/></td>
                            <td class="contact" width="50%">
                                <form:select id="unstructureddocumentation" path="unstructureddocumentation" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="otherinformationsources"/></td>
                            <td class="contact" width="50%">
                                <form:select id="otherinformationsources" path="otherinformationsources" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="papers"/></td>
                            <td class="contact" width="50%">
                                <form:select id="papers" path="papers" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="identificationkeys"/></td>
                            <td class="contact" width="50%">
                                <form:select id="identificationkeys" path="identificationkeys" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="migratorydata"/></td>
                            <td class="contact" width="50%">
                                <form:select id="migratorydata" path="migratorydata" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="ecologicalsignificance"/></td>
                            <td class="contact" width="50%">
                                <form:select id="ecologicalsignificance" path="ecologicalsignificance" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="unstructurednaturalhistory"/></td>
                            <td class="contact" width="50%">
                                <form:select id="unstructurednaturalhistory" path="unstructurednaturalhistory" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="invasivenessdata"/></td>
                            <td class="contact" width="50%">
                                <form:select id="invasivenessdata" path="invasivenessdata" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="targetaudiences"/></td>
                            <td class="contact" width="50%">
                                <form:select id="targetaudiences" path="targetaudiences" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="version"/></td>
                            <td class="contact" width="50%">
                                <form:select id="version" path="version" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="urlimage1"/></td>
                            <td class="contact" width="50%">
                                <form:select id="urlimage1" path="urlimage1" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="captionimage1"/></td>
                            <td class="contact" width="50%">
                                <form:select id="captionimage1" path="captionimage1" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="urlimage2"/></td>
                            <td class="contact" width="50%">
                                <form:select id="urlimage2" path="urlimage2" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="captionimage2"/></td>
                            <td class="contact" width="50%">
                                <form:select id="captionimage2" path="captionimage2" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="urlimage3"/></td>
                            <td class="contact" width="50%">
                                <form:select id="urlimage3" path="urlimage3" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="captionimage3"/></td>
                            <td class="contact" width="50%">
                                <form:select id="captionimage3" path="captionimage3" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>                        
                    </table>
                </div>
            </form:form>

        </div>
        <!-- Content ending -->
        <div id="footer">
            <fmt:message key="footer_text"/>
        </div>
    </body>
</html>
