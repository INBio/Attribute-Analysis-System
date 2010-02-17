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
                        <div id="lang">
                            <!--<a href="spanish" style="height: 24px; width: 46px"><fmt:message key="properties_es"/></a>
                            <a href="english" style="height: 24px; width: 46px"><fmt:message key="properties_en"/></a>-->
                            <a class="link_home" href="welcome.htm" style="height: 24px; width: 46px"><fmt:message key="home_title"/></a>
                            <a class="link_about" href="about.htm" style="height: 24px; width: 46px"><fmt:message key="about_title"/></a>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>