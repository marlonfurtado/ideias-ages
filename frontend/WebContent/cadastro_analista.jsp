<%@ page import="br.com.ideiasages.authorization.Role" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:system pageTitle="Cadastro de Analista" role="<%= Role.ADMINISTRATOR %>">
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="./assets/js/analista/cadastro.js"></script>
        <script type="text/javascript" src="./assets/js/modal.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="row mt-15">
            <form id="form-cadastro-analista" class="form-horizontal">
                <div class="form-group">
                    <label class="col-md-4 control-label" for="name">Nome *</label>
                    <div class="col-md-5">
                        <input id="name" name="name" type="text" placeholder="Seu nome" class="form-control input-md" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="cpf">CPF *</label>
                    <div class="col-md-5">
                        <input id="cpf" name="cpf" type="text" placeholder="Seu CPF"
                               class="form-control input-md" required>

                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="email">E-mail *</label>
                    <div class="col-md-5">
                        <input id="email" name="email" type="email"
                               placeholder="email@example.com" class="form-control input-md"
                               required>

                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="phone">Telefone</label>
                    <div class="col-md-5">
                        <input id="phone" name="phone" type="text" placeholder="(51) 99999-9999" class="form-control input-md">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="password">Senha *</label>
                    <div class="col-md-5">
                        <input id="password" name="password" type="password"
                               placeholder="ex: SuaSenha@1234" class="form-control input-md">
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