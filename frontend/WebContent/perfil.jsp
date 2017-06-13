<%@ page import="br.com.ideiasages.authorization.Role"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:system pageTitle="Meu perfil"
	role="<%= Role.merge(Role.IDEALIZER, Role.ANALYST, Role.ADMINISTRATOR) %>">
	<jsp:attribute name="scripts">
        <script type="text/javascript" src="./assets/js/perfil.js"></script>
    </jsp:attribute>

	<jsp:body>
        <div class="row mt-15">
            <form id="form-perfil" class="form-horizontal">
                <div class="form-group">
                    <label class="col-md-4 control-label" for="name">Nome *</label>
                    <div class="col-md-5">
                        <input id="name" name="name" type="text"
							placeholder="Seu nome" class="form-control input-md">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-md-4 control-label" for="name">CPF</label>
                    <div class="col-md-5">
                        <input id="CPF" name="CPF" type="text"
							placeholder="CPF" class="form-control input-md" readonly>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="email">E-mail *</label>
                    <div class="col-md-5">
                        <input id="email" name="email" type="email"
							placeholder="email@example.com" class="form-control input-md">

                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="phone">Telefone</label>
                    <div class="col-md-5">
                        <input id="phone" name="phone" type="text"
							placeholder="(51) 99999-9999" class="form-control input-md">

                    </div>
                </div>

				
				<div class="form-group">
				<div class="col-md-4"></div>
				<div class="col-md-5">
						<a class="btn btn-block btn-lg
							btn-ages-pr btn-perfil-senha"
							onclick="$('#password-change').slideToggle()">Trocar Senha</a>
					</div>
					</div>
				<div id="password-change" style="display: none">
				<div class="form-group">
                    <label class="col-md-4 control-label" for="password">Senha Atual *</label>
                    <div class="col-md-5">
                        <input id="actual-password" name="password"
								type="password" placeholder="" class="form-control input-md">
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="password">Nova Senha *</label>
                    <div class="col-md-5">
                        <input id="new-password" name="password"
								type="password" placeholder="" class="form-control input-md">
                        <span class="help-block"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="password">Repetir Nova Senha *</label>
                    <div class="col-md-5">
                        <input id="confirm-password" name="password2"
								type="password" placeholder="" class="form-control input-md">
                        <span class="help-block"></span>
                    </div>
                </div>
				</div>
                <div class="form-group">
                    <div class="col-md-4"></div>
                    <div class="col-md-2">
                    <a id="btn-cancelar" class="btn btn-lg btn-block btn-default" href="./">Cancelar</a>
                    </div>
                    <div class="col-md-3">
                        <button id="btn-salvar" name="salvar"
							class="btn btn-block btn-lg btn-ages-pr">Salvar
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </jsp:body>
</t:system>