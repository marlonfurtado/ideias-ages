<%@ page import="br.com.ideiasages.authorization.Role" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:system pageTitle="Cadastro de Ideia" role="<%= Role.IDEALIZER %>">
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="./assets/js/ideia/cadastro.js"></script>
    </jsp:attribute>

    <jsp:body>
        <p>Nesse espaço, você deve informar todas as mais relevantes informações sobre a sua ideia. Preencha com cautela e com a maior riqueza de detalhes possível.</p>
        <p>É importante ressaltar que você não possui limitação de tempo para inserir a sua ideia. Você pode, inclusive, salvá-la como rascunho e continuar editando em um outro momento.</p>
        <br />

        <div class="row mt-15">
            <form id="form-cadastro-ideia" class="form-horizontal">
                <div class="form-group">
                    <label class="col-md-3 control-label" for="title">Título</label>
                    <div class="col-md-8">
                        <input id="title" name="title" type="text" placeholder="Título do projeto (máximo de 100 caracteres)" maxlength="100" class="form-control input-md" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label" for="goal">Objetivo</label>
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
                    <label class="col-md-3 control-label" for="description">Descrição</label>
                    <div class="col-md-8">
                        <textarea maxlength="3000" name="description" id="description" class="input-md form-control" required placeholder="Descreva o projeto com o máximo de detalhes (máximo de 3000 caracteres)."></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-8 col-md-offset-3">
                        <button type="button" name="btnSaveDraft" id="btnSaveDraft" class="btn btn-info" onclick="cadastrar()">Salvar rascunho</button>
                        <button type="button" name="btnSaveAndSend" id="btnSaveAndSend" class="btn btn-success" onclick="atualizarStatus()">Salvar e Enviar para análise</button>
                        <button type="button" name="btnCancel" id="btnCancel" class="btn btn-danger">Cancelar</button>
                    </div>
                </div>
            </form>
        </div>
    </jsp:body>
</t:system>