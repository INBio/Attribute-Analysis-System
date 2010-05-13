<%-- 
    Document   : header
    Created on : 12/01/2010, 03:51:55 PM
    Author     : esmata
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <div style="-rave-layout: grid">
            <form:form>

                <div id="lang">
                    <!--<a href="spanish"><fmt:message key="properties_es"/></a>
                            <a href="english"><fmt:message key="properties_en"/></a>-->
                    <a class="link_home" href="welcome.htm"><fmt:message key="home_title"/></a>
                    <a class="link_about" href="about.htm"><fmt:message key="about_title"/></a>
                </div>
                <div id="banner-rep">
                    <div id="banner">
                        <div id="title">
                            <h1>
                                <fmt:message key="applicationName"/>
                            </h1>
                        </div>
                    </div>
                </div>

            </form:form>
        </div>
