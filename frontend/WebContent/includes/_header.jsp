<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid" id="header-menu">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Menu</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"><img src="./assets/img/ages-transparency.png" class="img-responsive" alt="Ideias AGES" title="Ideias AGES" border="0" /></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="#" title="Vagas"><i class="glyphicon glyphicon-"></i> Administradores </a></li>
                <li><a href="cadastro_analista.jsp" title="Clientes"><i class="glyphicon glyphicon-"></i> Analistas </a></li>
                <li><a href="#" title="Usuários"><i class="glyphicon glyphicon-"></i> Idealizadores </a></li>
                <li><a href="#" title="Blog"><i class="glyphicon glyphicon-"></i> Ideias </a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="javascript: void(0);" class="dropdown-toggle" data-toggle="dropdown" title="Minhas configurações">
                        <span id="userNameContainer"></span> <b class="caret"></b>
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