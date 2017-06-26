<%@ tag description="Template for Content By Role" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ tag import="br.com.ideiasages.authorization.LoggedUser" %>

<%@ attribute name="role" required="true" type="java.lang.Long" %>

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
            <jsp:body>
                <section id="content-body">
                   <div class="row">
                   		<div class="col-md-12">
                   		<jsp:doBody />
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