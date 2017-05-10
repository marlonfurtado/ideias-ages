<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%
    String role = (String) request.getAttribute("userRole");
    String name = (String) request.getAttribute("userName");
%>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid" id="header-menu">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Menu</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="./"><img src="./assets/img/ages-transparency.png" class="img-responsive" alt="Ideias AGES" title="Ideias AGES" border="0" /></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <% if (role.equals("admin")) { %>
                    <li><a href="cadastro_analista.jsp" title="Analistas">Analistas</a></li>
                <% } %>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="javascript: void(0);" class="dropdown-toggle" data-toggle="dropdown" title="Minhas configurações">
                        <span id="userNameContainer"><%=URLDecoder.decode(name) %></span> <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><i class="glyphicon glyphicon-user"></i> Meu perfil</a></li>
                        <li><a href="javascript: void(0);" id="logoutAction"><i class="glyphicon glyphicon-off"></i> Sair</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>