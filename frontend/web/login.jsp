<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Login">
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="./assets/js/login/login.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="container" style="margin-top: 50px; width: 600px;">
            <div class="text-center">
                <img src="./assets/img/ages-completo.png" alt="Ideias AGES" />
            </div>
            <br />
            <form id="formLogin">
                <div class="form-group">
                    <label for="cpf"><i class="glyphicon glyphicon-user"></i> CPF</label>
                    <input type="text" placeholder="CPF" name="cpf" id="cpf" class="form-control" />
                </div>

                <div class="form-group">
                    <label for="senha"><i class="glyphicon glyphicon-lock"></i> Senha</label>
                    <input type="password" placeholder="Senha" name="senha" id="senha" class="form-control" />
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-lg btn-block btn-success">Autenticar no portal</button>
                </div>
            </form>
    </jsp:body>
</t:wrapper>