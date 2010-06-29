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
        <script type="text/javascript">
            //Verifing mandatory data
            function verify(){
                var form = document.getElementById('attributes');
                var gui = document.getElementById('globaluniqueidentifier');
                var dlm = document.getElementById('datelastmodified');
                var bor = document.getElementById('basisofrecord');
                var ic = document.getElementById('institutioncode');
                var cc = document.getElementById('collectioncode');
                var cn = document.getElementById('catalognumber');
                var sn = document.getElementById('scientificname');
                if(gui.value != 'unmapped' && dlm.value != 'unmapped' && bor.value != 'unmapped' && ic.value != 'unmapped'
                    && cc.value != 'unmapped' && cn.value != 'unmapped' && sn.value != 'unmapped'){
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
            <h2><fmt:message key="dwc_mapping"/></h2><br>

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
                            <td class="contact" width="50%">(*) <fmt:message key="datelastmodified"/></td>
                            <td class="contact" width="50%">
                                <form:select id="datelastmodified" path="datelastmodified" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%">(*) <fmt:message key="institutioncode"/></td>
                            <td class="contact" width="50%">
                                <form:select id="institutioncode" path="institutioncode" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%">(*) <fmt:message key="collectioncode"/></td>
                            <td class="contact" width="50%">
                                <form:select id="collectioncode" path="collectioncode" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%">(*) <fmt:message key="catalognumber"/></td>
                            <td class="contact" width="50%">
                                <form:select id="catalognumber" path="catalognumber" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="catalognumbernumeric"/></td>
                            <td class="contact" width="50%">
                                <form:select id="catalognumbernumeric" path="catalognumbernumeric" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%">(*) <fmt:message key="dwc_scientificname"/></td>
                            <td class="contact" width="50%">
                                <form:select id="scientificname" path="scientificname" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%">(*) <fmt:message key="basisofrecord"/></td>
                            <td class="contact" width="50%">
                                <form:select id="basisofrecord" path="basisofrecord" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="informationwithheld"/></td>
                            <td class="contact" width="50%">
                                <form:select id="informationwithheld" path="informationwithheld" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="highertaxon"/></td>
                            <td class="contact" width="50%">
                                <form:select id="highertaxon" path="highertaxon" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="kingdom"/></td>
                            <td class="contact" width="50%">
                                <form:select id="kingdom" path="kingdom" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="phylum"/></td>
                            <td class="contact" width="50%">
                                <form:select id="phylum" path="phylum" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="class"/></td>
                            <td class="contact" width="50%">
                                <form:select id="class1" path="class1" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="orders"/></td>
                            <td class="contact" width="50%">
                                <form:select id="orders" path="orders" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="family"/></td>
                            <td class="contact" width="50%">
                                <form:select id="family" path="family" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="genus"/></td>
                            <td class="contact" width="50%">
                                <form:select id="genus" path="genus" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="specificepithet"/></td>
                            <td class="contact" width="50%">
                                <form:select id="specificepithet" path="specificepithet" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="infraspecificepithet"/></td>
                            <td class="contact" width="50%">
                                <form:select id="infraspecificepithet" path="infraspecificepithet" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="infraspecificrank"/></td>
                            <td class="contact" width="50%">
                                <form:select id="infraspecificrank" path="infraspecificrank" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="authoryearofscientificname"/></td>
                            <td class="contact" width="50%">
                                <form:select id="authoryearofscientificname" path="authoryearofscientificname" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="nomenclaturalcode"/></td>
                            <td class="contact" width="50%">
                                <form:select id="nomenclaturalcode" path="nomenclaturalcode" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="identificationqualifier"/></td>
                            <td class="contact" width="50%">
                                <form:select id="identificationqualifier" path="identificationqualifier" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="identifiedby"/></td>
                            <td class="contact" width="50%">
                                <form:select id="identifiedby" path="identifiedby" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="dateidentified"/></td>
                            <td class="contact" width="50%">
                                <form:select id="dateidentified" path="dateidentified" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="typestatus"/></td>
                            <td class="contact" width="50%">
                                <form:select id="typestatus" path="typestatus" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="collectingmethod"/></td>
                            <td class="contact" width="50%">
                                <form:select id="collectingmethod" path="collectingmethod" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="validdistributionflag"/></td>
                            <td class="contact" width="50%">
                                <form:select id="validdistributionflag" path="validdistributionflag" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="collectornumber"/></td>
                            <td class="contact" width="50%">
                                <form:select id="collectornumber" path="collectornumber" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="fieldnumber"/></td>
                            <td class="contact" width="50%">
                                <form:select id="fieldnumber" path="fieldnumber" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="collector"/></td>
                            <td class="contact" width="50%">
                                <form:select id="collector" path="collector" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="earliestdatecollected"/></td>
                            <td class="contact" width="50%">
                                <form:select id="earliestdatecollected" path="earliestdatecollected" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="latestdatecollected"/></td>
                            <td class="contact" width="50%">
                                <form:select id="latestdatecollected" path="latestdatecollected" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="verbatimcollectingdate"/></td>
                            <td class="contact" width="50%">
                                <form:select id="verbatimcollectingdate" path="verbatimcollectingdate" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="dayofyear"/></td>
                            <td class="contact" width="50%">
                                <form:select id="dayofyear" path="dayofyear" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="fieldnotes"/></td>
                            <td class="contact" width="50%">
                                <form:select id="fieldnotes" path="fieldnotes" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="highergeography"/></td>
                            <td class="contact" width="50%">
                                <form:select id="highergeography" path="highergeography" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="continent"/></td>
                            <td class="contact" width="50%">
                                <form:select id="continent" path="continent" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="waterbody"/></td>
                            <td class="contact" width="50%">
                                <form:select id="waterbody" path="waterbody" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                    </table>                    
                    <a href="config.htm" class="simple_link"><fmt:message key="back"/></a>
                    <input type="button" class="simple_button" id="saveAttributes" value="<fmt:message key="save"/>" onclick="verify()"/>
                    <br><br><br>
                </div>
                <div id="configCols2" style="width:450px;margin:0 0 0 510px;">
                    <table class="contacts" cellspacing="0">
                        <tr>
                            <th class="contactDept" ><fmt:message key="concept"/></th>
                            <th class="contactDept" ><fmt:message key="column"/></th>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="islandgroup"/></td>
                            <td class="contact" width="50%">
                                <form:select id="islandgroup" path="islandgroup" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="island"/></td>
                            <td class="contact" width="50%">
                                <form:select id="island" path="island" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="country"/></td>
                            <td class="contact" width="50%">
                                <form:select id="country" path="country" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="stateprovince"/></td>
                            <td class="contact" width="50%">
                                <form:select id="stateprovince" path="stateprovince" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="county"/></td>
                            <td class="contact" width="50%">
                                <form:select id="county" path="county" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="locality"/></td>
                            <td class="contact" width="50%">
                                <form:select id="locality" path="locality" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="decimallongitude"/></td>
                            <td class="contact" width="50%">
                                <form:select id="decimallongitude" path="decimallongitude" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="verbatimlongitude"/></td>
                            <td class="contact" width="50%">
                                <form:select id="verbatimlongitude" path="verbatimlongitude" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="decimallatitude"/></td>
                            <td class="contact" width="50%">
                                <form:select id="decimallatitude" path="decimallatitude" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="verbatimlatitude"/></td>
                            <td class="contact" width="50%">
                                <form:select id="verbatimlatitude" path="verbatimlatitude" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="geodeticdatum"/></td>
                            <td class="contact" width="50%">
                                <form:select id="geodeticdatum" path="geodeticdatum" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="verbatimcoordinatesystem"/></td>
                            <td class="contact" width="50%">
                                <form:select id="verbatimcoordinatesystem" path="verbatimcoordinatesystem" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="georeferenceprotocol"/></td>
                            <td class="contact" width="50%">
                                <form:select id="georeferenceprotocol" path="georeferenceprotocol" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="coordinateuncertaintyinmeters"/></td>
                            <td class="contact" width="50%">
                                <form:select id="coordinateuncertaintyinmeters" path="coordinateuncertaintyinmeters" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="georeferenceremarks"/></td>
                            <td class="contact" width="50%">
                                <form:select id="georeferenceremarks" path="georeferenceremarks" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="footprintwkt"/></td>
                            <td class="contact" width="50%">
                                <form:select id="footprintwkt" path="footprintwkt" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="minimumelevationinmeters"/></td>
                            <td class="contact" width="50%">
                                <form:select id="minimumelevationinmeters" path="minimumelevationinmeters" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="maximumelevationinmeters"/></td>
                            <td class="contact" width="50%">
                                <form:select id="maximumelevationinmeters" path="maximumelevationinmeters" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="verbatimelevation"/></td>
                            <td class="contact" width="50%">
                                <form:select id="verbatimelevation" path="verbatimelevation" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="minimumdepthinmeters"/></td>
                            <td class="contact" width="50%">
                                <form:select id="minimumdepthinmeters" path="minimumdepthinmeters" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="maximumdepthinmeters"/></td>
                            <td class="contact" width="50%">
                                <form:select id="maximumdepthinmeters" path="maximumdepthinmeters" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="sex"/></td>
                            <td class="contact" width="50%">
                                <form:select id="sex" path="sex" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="lifestage"/></td>
                            <td class="contact" width="50%">
                                <form:select id="lifestage" path="lifestage" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="preparations"/></td>
                            <td class="contact" width="50%">
                                <form:select id="preparations" path="preparations" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="individualcount"/></td>
                            <td class="contact" width="50%">
                                <form:select id="individualcount" path="individualcount" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="genbanknum"/></td>
                            <td class="contact" width="50%">
                                <form:select id="genbanknum" path="genbanknum" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="othercatalognumbers"/></td>
                            <td class="contact" width="50%">
                                <form:select id="othercatalognumbers" path="othercatalognumbers" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="relatedcatalogitems"/></td>
                            <td class="contact" width="50%">
                                <form:select id="relatedcatalogitems" path="relatedcatalogitems" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="remarks"/></td>
                            <td class="contact" width="50%">
                                <form:select id="remarks" path="remarks" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="attributes"/></td>
                            <td class="contact" width="50%">
                                <form:select id="attributes" path="attributes" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="imageurl"/></td>
                            <td class="contact" width="50%">
                                <form:select id="imageurl" path="imageurl" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="relatedinformation"/></td>
                            <td class="contact" width="50%">
                                <form:select id="relatedinformation" path="relatedinformation" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="disposition"/></td>
                            <td class="contact" width="50%">
                                <form:select id="disposition" path="disposition" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="pointradiusspatialfit"/></td>
                            <td class="contact" width="50%">
                                <form:select id="pointradiusspatialfit" path="pointradiusspatialfit" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="footprintspatialfit"/></td>
                            <td class="contact" width="50%">
                                <form:select id="footprintspatialfit" path="footprintspatialfit" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="verbatimcoordinates"/></td>
                            <td class="contact" width="50%">
                                <form:select id="verbatimcoordinates" path="verbatimcoordinates" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="georeferencesources"/></td>
                            <td class="contact" width="50%">
                                <form:select id="georeferencesources" path="georeferencesources" items="${cols}" cssClass="componentSize"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contact" width="50%"><fmt:message key="georeferenceverificationstatus"/></td>
                            <td class="contact" width="50%">
                                <form:select id="georeferenceverificationstatus" path="georeferenceverificationstatus" items="${cols}" cssClass="componentSize"/>
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
