<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:site pageTitle="Cadastro">
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="/projetos/ideias/assets/js/jquery.mask.min.js"></script>
        <script type="text/javascript" src="/projetos/ideias/assets/js/idealizador/cadastro.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="row">
                <h2 id="titulo-cadastro-idealizador" class="blue-ages text-center">Cadastro de Idealizador</h2>
                    <br>
                    <form id="form-cadastro-idealizador" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="name">Nome</label>
                            <div class="col-md-5">
                                <input id="name" name="name" type="text" placeholder="Seu nome" class="form-control input-md" required="true">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label" for="cpf">CPF</label>
                            <div class="col-md-5">
                                <input id="cpf" name="cpf" type="text" placeholder="Seu CPF" class="form-control input-md" required="true">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label" for="email">E-mail</label>
                            <div class="col-md-5">
                                <input id="email" name="email" type="email" placeholder="email@example.com" class="form-control input-md" required="true">

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
                                <input id="password" name="password" type="password" placeholder="" class="form-control input-md" required="true">
                                <span class="help-block"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-4"></div>
                            <div class="col-md-5">
                                <button id="btn-cadastrar" name="cadastrar" class="btn btn-block btn-ages-pr">Cadastrar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>
</t:site>