<%@ page import="br.com.ideiasages.authorization.Role" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:system pageTitle="Detalhes da Ideia" role="<%= Role.merge(Role.IDEALIZER, Role.ANALYST, Role.ADMINISTRATOR) %>">
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="./assets/js/mustache.min.js"></script>
        <script type="text/javascript" src="./assets/js/util.js"></script>
			<script type="text/javascript"
			src="./assets/js/ideia/questions.js"></script>
        <script type="text/javascript" src="./assets/js/modal.js"></script>
        <script type="text/javascript" src="./assets/js/ideia/detalhes.js"></script>
        <script type="text/javascript" src="./assets/js/ideia/comentarios.js"></script>
    </jsp:attribute>

    <jsp:body>
        <input type="hidden" id="ideaId" value="0" />

        <p>Nesse espaço, você deve informar todas as mais relevantes informações sobre a sua ideia. Preencha com cautela e com a maior riqueza de detalhes possível.</p>
        <p>É importante ressaltar que você não possui limitação de tempo para inserir a sua ideia. Você pode, inclusive, salvá-la como rascunho e continuar editando em um outro momento.</p>
        <br />

        <div class="row mt-15">
            <form id="form-cadastro-ideia" class="form-horizontal">
                <div class="form-group">
                    <label class="col-md-3 control-label" for="title">Título *</label>
                    <div class="col-md-8">
                        <input id="title" name="title" type="text" placeholder="Título do projeto (máximo de 100 caracteres)" maxlength="100" class="form-control input-md" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label" for="goal">Objetivo *</label>
                    <div class="col-md-8">
                        <input id="goal" name="goal" type="text" placeholder="Objetivo do projeto (máximo de 100 caracteres)" maxlength="100" class="form-control input-md" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label" for="tags">Palavras-chave</label>
                    <div class="col-md-8">
                        <input id="tags" name="tags" type="text" placeholder="Utilize o formato 'Área; subárea; tema; tema correlato; referência' (máximo 100 caracteres)" maxlength="100" class="form-control input-md" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label" for="description">Descrição *</label>
                    <div class="col-md-8">
                        <textarea maxlength="3000" name="description" id="description" class="input-md form-control" required placeholder="Descreva o projeto com o máximo de detalhes (máximo de 3000 caracteres)."></textarea>
                    </div>
                </div>
            </form>
        </div>

        <div id="actionsContainer" class="row">
            <div class="row">
                <div class="col-md-8 col-md-offset-3">
                    <button type="button" style="display: none" name="btnSaveDraft" id="btnSaveDraft" class="btn btn-info">Salvar rascunho</button>
                    <button type="button" style="display: none" name="btnSaveAndSend" id="btnSaveAndSend" class="btn btn-success">Salvar e Enviar para análise</button>
                    <button type="button" style="display: none" name="btnApproveIdea" id="btnApproveIdea" class="btn btn-success">Aprovar Ideia</button>
                    <button type="button" style="display: none" name="btnPutIdeaUnderAnalysis" id="btnPutIdeaUnderAnalysis" class="btn btn-success">Colocar Ideia Em Análise</button>
                    <button type="button" style="display: none" name="btnRejectIdea" id="btnRejectIdea" class="btn btn-danger">Rejeitar Ideia</button>
                </div>
            </div>
        </div>
	 		<div id="questionsContainer">
	            <jsp:include page="includes/idea/_questions.jsp" />
	        </div>

        <div id="commentsContainer">
            <jsp:include page="includes/idea/_comments.jsp" />
        </div>
    </jsp:body>
</t:system>