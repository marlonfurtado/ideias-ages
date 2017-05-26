<%@ page import="br.com.ideiasages.authorization.Role" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:system pageTitle="Cadastro de Ideia" role="<%= Role.IDEALIZER %>">
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="./assets/js/ideia/cadastro.js"></script>
    </jsp:attribute>

    <jsp:body>
        <p>Nesse espaço, você deve informar todas as mais relevantes informações sobre a sua ideia. Preencha com cautela e com a maior riqueza de detalhes possível.</p>
        <br />
        <p>É importante ressaltar que você não possui limitação de tempo para inserir a sua ideia. Você pode, inclusive, salvá-la como rascunho e continuar editando em um outro momento.</p>

        <div class="row mt-15">
            <form id="form-cadastro-ideia" class="form-horizontal">
                <div class="col-md-6 mt-15">
                    <label class="control-label" for="relacao">Qual sua relação com a PUCRS? *</label> <br />
                    <div class="">
                        <select name="relacao" id="relacao" class="form-control input-md">
                            <option value="0">Selecione uma opção abaixo</option>
                            <option value="Sou estudante/ex-estudante">Sou estudante/ex-estudante</option>
                            <option value="Sou professor/ex-professor">Sou professor/ex-professor</option>
                            <option value="Sou funcionário/ex-funcionário">Sou funcionário/ex-funcionário</option>
                            <option value="Outra">Outra</option>
                        </select>
                    </div>
                </div>

                <div class="col-md-6 mt-15">
                    <label class="control-label" for="area">Em qual área a sua ideia deveria ser aplicada? *</label> <br />
                    <div class="">
                        <select name="area" id="area" class="form-control input-md">
                            <option value="0">Selecione uma opção abaixo</option>
                            <option value="Auditoria">Auditoria</option>
                            <option value="Comercial">Comercial</option>
                            <option value="Comunicação">Comunicação</option>
                            <option value="Desenvolvimento Humano e Organizacional">Desenvolvimento Humano e Organizacional</option>
                            <option value="Industrial">Industrial</option>
                            <option value="Jurídico">Jurídico</option>
                            <option value="Logística">Logística</option>
                            <option value="Marketing">Marketing</option>
                            <option value="Relações Institucionais">Relações Institucionais</option>
                            <option value="Saúde Segurança e Meio Ambiente">Saúde Segurança e Meio Ambiente</option>
                            <option value="Suprimentos">Suprimentos</option>
                            <option value="Sustentabilidade">Sustentabilidade</option>
                            <option value="TI">TI</option>
                            <option value="Outra">Outra</option>
                        </select>

                    </div>
                </div>

                <div class="col-md-12 mt-15">
                    <label class="control-label" for="ideia">
                        Qual a sua ideia?
                        (Descreva o mais detalhadamente possível qual é a sua ideia, como ela deve ser implantada e outras informações que você julgue importante para a avaliação da ideia). *
                     </label> <br />
                    <div class="">
                        <textarea name="ideia" id="ideia" class="input-md form-control" required placeholder="Detalhe aqui qual a sua ideia."></textarea>
                    </div>
                </div>

                <div class="col-md-12 mt-15">
                    <label class="control-label" for="importancia">
                        Por que sua ideia é importante ou pode fazer a diferença?
                        Descreva os benefícios que sua sugestão irá trazer para a comunidade. *
                     </label> <br />
                    <div class="">
                        <textarea name="importancia" id="importancia" class="input-md form-control" required placeholder="Informe aqui a importância da sua ideia."></textarea>
                    </div>
                </div>

                <div class="col-md-12 mt-15">
                    <button id="btn-salvar" name="cadastrar" class="btn btn-lg btn-ages-pr">Salvar rascunho</button>

                    <button id="btn-salvar-enviar" name="cadastrar" class="btn btn-lg btn-ages-pr">Salvar rascunho e submeter para análise</button>
                </div>
            </form>
        </div>
    </jsp:body>
</t:system>