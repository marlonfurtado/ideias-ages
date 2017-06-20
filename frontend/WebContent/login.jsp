<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Login">
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="./assets/js/jquery.mask.min.js"></script>
        <script type="text/javascript" src="./assets/js/login/login.js"></script>
        <script type="text/javascript" src="./assets/js/forgotPassword/forgotPassword.js"></script>
        <script type="text/javascript" src="./assets/js/modal.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="container" style="margin-top: 50px; width: 600px;">
            <div class="text-center">
                <img src="./assets/img/ages-completo.png" alt="Ideias AGES" />
            </div>
            <br />
            <form id="formLogin">
                <div class="form-group">
                    <label for="cpf" class="font-size-14"><i class="glyphicon glyphicon-user"></i> CPF</label>
                    <input type="text" placeholder="CPF" name="cpf" id="cpf" class="form-control" maxlength="11" />
                </div>

                <div class="form-group">
                    <label for="password" class="font-size-14"><i class="glyphicon glyphicon-lock"></i> Senha</label>
                    <input type="password" placeholder="Senha" name="password" id="password" class="form-control" />
                </div>
                
                <div class="form-group row">
               		<a id="forgotPasswordLink" name="forgotPasswordLink" class="font-size-14" href="./">Esqueci minha senha!</a>
               	</div>

                <div class="form-group row">
                    <div class="col-md-6">
                        <a class="btn btn-lg btn-block btn-default" href="./cadastro_idealizador.jsp">Cadastre-se aqui</a>
                    </div>
                    <div class="col-md-6">
                        <button type="submit" id="submitLogin" class="btn btn-lg btn-block btn-ages-pr">Login</button>
                    </div>
                </div>
            </form>
		</div>
    </jsp:body>
</t:wrapper>