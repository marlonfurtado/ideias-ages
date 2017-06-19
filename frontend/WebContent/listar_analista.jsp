<%@ page import="br.com.ideiasages.authorization.Role" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:system pageTitle="Listagem de Analista" role="<%= Role.ADMINISTRATOR %>">
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="./assets/js/mustache.min.js"></script>
        <script type="text/javascript" src="./assets/js/analista/listar.js"></script>
        <script type="text/javascript" src="./assets/js/analista/status.js"></script>

        <script id="analystListTemplate" type="x-tmpl-mustache">
            <table id="analystListTable" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th width="25%">Nome</th>
                    <th width="25%">E-mail</th>
                    <th width="25%">CPF</th>
                    <th class="no-sort">Ações</th>
                </tr>
                </thead>
                <tbody>
                    {{#data}}
                        <tr>
                            <td>{{name}}</td>
                            <td>{{email}}</td>
                            <td>{{cpf}}</td>
                            <td>
							{{#active}}	<a href="javascript: void(0);" class='status label label-danger ' data-id='{{cpf}}' id="inativar{{cpf}}" title="Inativar">Inativar</a> {{/active}}
							{{^active}}	<a href="javascript: void(0);" class='status label btn-ages-pr ' data-id='{{cpf}}' id="ativar{{cpf}}" title="Ativar">Ativar</a> {{/active}}
							</td>
                        </tr>
                    {{/data}}
                </tbody>
            </table>
        </script>

        <script id="analystListEmptyTemplate" type="x-tmpl-mustache">
            <h4>Não há nenhum analista cadastrado.</h4>
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="row mt-15">
            <div class="col-md-12">
                <div id="analystListBody" class="table-responsive"></div>
            </div>
        </div>

    </jsp:body>
</t:system>
