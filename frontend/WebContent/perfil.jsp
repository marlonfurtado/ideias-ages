<%@ page import="br.com.ideiasages.authorization.Role" %>
<%@ page import="br.com.ideiasages.model.User" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:system pageTitle="Meu perfil" role="<%= Role.merge(Role.IDEALIZER, Role.ANALYST, Role.ADMINISTRATOR) %>">
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="./assets/js/perfil.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="row mt-15">
            <form id="form-perfil" class="form-horizontal">
                <div class="form-group">
                    <label class="col-md-4 control-label" for="name">Nome</label>
                    <div class="col-md-5">
                        <input id="name" name="name" type="text" placeholder="Seu nome" class="form-control input-md" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="email">E-mail</label>
                    <div class="col-md-5">
                        <input id="email" name="email" type="email"
                               placeholder="email@example.com" class="form-control input-md"
                               required>

                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="phone">Telefone</label>
                    <div class="col-md-5">
                        <input id="phone" name="phone" type="text" placeholder="(51) 9999-9999" class="form-control input-md">

                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="password">Senha</label>
                    <div class="col-md-5">
                        <input id="password" name="password" type="password"
                               placeholder="" class="form-control input-md">
                        <span class="help-block"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="password">Repetir Senha</label>
                    <div class="col-md-5">
                        <input id="password2" name="password2" type="password"
                               placeholder="" class="form-control input-md">
                        <span class="help-block"></span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-4"></div>
                    <div class="col-md-5">
                        <button id="btn-cadastrar" name="cadastrar"
                                class="btn btn-block btn-lg btn-ages-pr">Cadastrar
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </jsp:body>
</t:system>