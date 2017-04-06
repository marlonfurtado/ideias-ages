<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Login">
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="./assets/js/login/login.js"></script>
    </jsp:attribute>

    <jsp:body>
        <form class="form-signin">
            <h2 class="form-signin-heading">Please sign in</h2>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" id="inputEmail" class="form-control" placeholder="Email" required="" autofocus="">
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputSenha" class="form-control" placeholder="Senha" required="">
            <button id="btnLogin" class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
        </form>
    </jsp:body>
</t:wrapper>