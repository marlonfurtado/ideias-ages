<%@ tag description="Template for System" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="pageTitle" required="true"%>
<%@attribute name="scripts" fragment="true" %>

<t:wrapper pageTitle="${pageTitle}">
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="/projetos/ideias/assets/js/system.js"></script>
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