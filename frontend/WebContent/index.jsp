<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="br.com.ideiasages.authorization.Role"%>

<t:system pageTitle="Dashboard" role="<%=Role.merge(Role.ADMINISTRATOR, Role.ANALYST, Role.IDEALIZER)%>">
	
	<jsp:body>
        <h3>Bem-vindo ao projeto Ideias Ages!</h3>
    </jsp:body>
</t:system>