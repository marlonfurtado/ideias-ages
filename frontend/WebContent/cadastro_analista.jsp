<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%--     <%@ page import="br.com.ideiasages.bo.UserBO" %>
    <%@ page import="br.com.ideiasages.dao.UserDAO" %>
    <%@ page import="br.com.ideiasages.IDEIAS_SCRIPT" %>
    <%@ page import="br.com.ideiasages.exception.NegocioException" %>
    <%@ page import="br.com.ideiasages.exception.PersistenciaException" %>
    <%@ page import="br.com.ideiasages.exception.ValidationException" %>
    <%@ page import="br.com.ideiasages.filter.LoginFilter" %>
    <%@ page import="br.com.ideiasages.model.User" %>
    <%@ page import="br.com.ideiasages.util.ConexaoUtil" %>
    <%@ page import="br.com.ideiasages.util.Constantes" %>
    <%@ page import="br.com.ideiasages.util.EncryptUtil" %>
    <%@ page import="br.com.ideiasages.util.ExportCSV" %>
    <%@ page import="br.com.ideiasages.util.LogParametrosSession" %>
    <%@ page import="br.com.ideiasages.util.MensagemConstantes" %>
    <%@ page import="br.com.ideiasages.util.SendMail" %>
    <%@ page import="br.com.ideiasages.util.Util" %>
    <%@ page import="br.com.ideiasages.validator.CPFValidator" %>
    <%@ page import="br.com.ideiasages.validator.DataValidator" %>
    <%@ page import="br.com.ideiasages.validator.LoginValidator" %>
    <%@ page import="br.com.ideiasages.validator.SenhaValidator" %>
    <%@ page import="br.com.ideiasages.validator.Validator" %>
    <%@ page import="br.com.ideiasages.controllers.APIController" %> --%>

<t:wrapper pageTitle="cadastro_analista">
	<jsp:attribute name="scripts">
		<script type="text/javascript" src="./assets/js/jquery.mask.min.js"></script>
        <script type="text/javascript" src="./assets/js/analista/cadastro.js"></script>
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
				  <input id="name" name="name" type="text" placeholder="Seu nome"
									class="form-control input-md" required>
				  </div>
				</div>

				<div class="form-group">
				  <label class="col-md-4 control-label" for="cpf">CPF</label>  
				  <div class="col-md-5">
				  <input id="cpf" name="cpf" type="text" placeholder="Seu CPF"
									class="form-control input-md" required>
				    
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
				  <input id="phone" name="phone" type="text"
									placeholder="(51) 9999-9999" class="form-control input-md" required>
				    
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
				  <label class="col-md-4 control-label" for="cadastrar"></label>
				  <div class="col-md-5">
				    <button id="btn-cadastrar" name="cadastrar"
									class="btn btn-block btn-ages-pr">Cadastrar</button>
				  </div>
				</div>
			</form>

		
			</div>
	</div>
</jsp:body>
</t:wrapper>
</html>