<%@ tag description="Template for System" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="pageTitle" required="true"%>

<t:wrapper pageTitle="${pageTitle}">
    <jsp:body>
        <jsp:include  page="/includes/_header.jsp" />

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

        <jsp:include page="/includes/_footer.jsp" />
    </jsp:body>
</t:wrapper>