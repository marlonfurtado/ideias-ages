<%@ tag description="Template for System" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ tag import="br.com.ideiasages.authorization.LoggedUser" %>

<%@ attribute name="pageTitle" required="true"%>
<%@ attribute name="role" required="true" type="java.lang.Long" %>
<%@ attribute name="scripts" fragment="true" %>

<%
    LoggedUser userEntity = LoggedUser.getByCookiesAttributes(request.getCookies());

    //in case the user is not logged, redirect him to the login page
    if (userEntity.isValid()) {
        //store the user into request attributes
        request.setAttribute("user", userEntity);

        //check if the user is authorized
        if (userEntity.hasAccessToModule(role)) {
    %>
        <t:wrapper pageTitle="${pageTitle}">
            <jsp:attribute name="scripts">
                <script type="text/javascript" src="./assets/js/system.js"></script>
                <jsp:invoke fragment="scripts"/>
            </jsp:attribute>

            <jsp:attribute name="header">
              <jsp:include page="/includes/_header.jsp" />
            </jsp:attribute>

            <jsp:attribute name="footer">
              <jsp:include page="/includes/_footer.jsp" />
            </jsp:attribute>

            <jsp:body>
                <section id="body">
                    <div class="container-fluid">
                        <div class="clearfix">
                            <h2 id="h2PageTitle">${pageTitle}</h2>
                        </div>

                        <div class="panel panel-default panel-edit">
                            <div class="panel-body">
                                <jsp:doBody />
                            </div>
                        </div>
                    </div>
                </section>
            </jsp:body>
        </t:wrapper>
    <%
        }
        else {
            response.sendRedirect("not_authorized.jsp");
        }
    }
    else {
        response.sendRedirect("login.jsp");
    }
    %>