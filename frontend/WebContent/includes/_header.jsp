<%@ page import="java.net.URLDecoder" %>
<%@ page import="br.com.ideiasages.authorization.Role" %>
<%@ page import="br.com.ideiasages.model.User" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%
    User userEntity = (User) request.getAttribute("user");
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
                <% if (userEntity.hasAccessToModule(Role.ADMINISTRATOR)) { %>
                    <li class="dropdown">
                        <a href="javascript: void(0);" class="dropdown-toggle" data-toggle="dropdown" title="Analistas">
                            Analistas <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="./listar_analista.jsp" title="Listar Analistas"><i class="glyphicon glyphicon-list"></i> Listagem</a></li>
                            <li><a href="./cadastro_analista.jsp" title="Novo Analista"><i class="glyphicon glyphicon-plus"></i> Novo</a></li>
                        </ul>
                    </li>
                <% } %>

                <% if (userEntity.hasAccessToModule(Role.ADMINISTRATOR) || userEntity.hasAccessToModule(Role.ANALYST)) { %>
                    <li class="dropdown">
                        <a href="javascript: void(0);" class="dropdown-toggle" data-toggle="dropdown" title="Idealizadores">
                            Idealizadores <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="./listar_idealizadores.jsp" title="Listar Idealizadores"><i class="glyphicon glyphicon-list"></i> Listagem</a></li>
                        </ul>
                    </li>
                <% } %>

                <li class="dropdown">
                    <a href="javascript: void(0);" class="dropdown-toggle" data-toggle="dropdown" title="Ideias">
                        Ideias <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="./listar_ideia.jsp" title="Listar Ideias"><i class="glyphicon glyphicon-list"></i> Listagem</a></li>

                        <% if (userEntity.hasAccessToModule(Role.IDEALIZER)) { %>
                            <li><a href="./cadastro_ideia.jsp" title="Nova Ideia"><i class="glyphicon glyphicon-plus"></i> Novo</a></li>
                        <% } %>
                    </ul>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="javascript: void(0);" class="dropdown-toggle" data-toggle="dropdown" title="Minhas configurações">
                        <span id="userNameContainer"><%=URLDecoder.decode(userEntity.getName()) %></span> <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="./perfil.jsp"><i class="glyphicon glyphicon-user"></i> Meu perfil</a></li>
                        <li><a href="javascript: void(0);" id="logoutAction"><i class="glyphicon glyphicon-off"></i> Sair</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>