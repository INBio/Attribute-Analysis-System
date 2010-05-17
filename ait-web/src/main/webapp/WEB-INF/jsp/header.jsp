<%-- 
    Document   : header
    Created on : 12/01/2010, 03:51:55 PM
    Author     : esmata
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <div style="-rave-layout: grid">
            <form:form>

                <div id="banner-rep">
                    <div id="banner">
                        <div id="title">
                            <h1>
                                <fmt:message key="applicationName"/>
                            </h1>
                        </div>
                    </div>
                </div>
                <div id="lang">
                    <!--<a href="spanish"><fmt:message key="properties_es"/></a>
                            <a href="english"><fmt:message key="properties_en"/></a>-->
                    <a class="link_home" href="welcome.htm"><fmt:message key="home_title"/></a>
                    <a class="link_about" href="about.htm"><fmt:message key="about_title"/></a>
                </div>

            </form:form>
        </div>


<!--
<div id="user-info">

 <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
  <spring:message code="m3s.login.welcome"/>: <sec:authentication property="principal.username"/> | <a href="<c:url value="/j_spring_security_logout"/>"><spring:message code='m3s.logout'/></a>
 </sec:authorize>

 <sec:authorize ifNotGranted="ROLE_ADMIN,ROLE_USER">
    <a href="<c:out value="${pageContext.request.contextPath}"/>/login.html" title="<spring:message code='m3s.login'/>"><spring:message code='m3s.login'/>
    </a> | <a href="<c:url value="/j_spring_security_logout"/>"><spring:message code='m3s.logout'/>
    </a>
  </sec:authorize>

</div>
-->
