<%@ tag description="Template for System" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="pageTitle" required="true"%>
<%@ attribute name="scripts" fragment="true" %>

<%
    //TODO: This needs to be moved to a validation class
    Cookie[] cookies = request.getCookies();
    boolean logged = false;

    for (Cookie c : cookies) {
        switch (c.getName()) {
            case "userName":
            case "userRole":
                request.setAttribute(c.getName(), c.getValue());
                logged = true;
                break;
        }
    }

    //in case the user is not logged, redirect him to the login page without rendering the rest
    if (logged) {
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
                            <h2>${pageTitle}</h2>
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
        response.sendRedirect("login.jsp");
    }
    %>