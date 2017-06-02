<%@ page import="br.com.ideiasages.authorization.Role" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="br.com.ideiasages.model.User" %>
<%
    User userEntity = (User) request.getAttribute("user");
%>

<t:system pageTitle="Listagem de Idealizadores" role="<%= Role.merge(Role.ADMINISTRATOR, Role.ANALYST) %>">
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="./assets/js/mustache.min.js"></script>
        <script type="text/javascript" src="./assets/js/idealizador/listar.js"></script>
        <script type="text/javascript" src="./assets/js/idealizador/status.js"></script>
        <script id="idealizerListTemplate" type="x-tmpl-mustache">
            <table id="idealizer-table" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th width="25%">Nome</th>
                    <th width="25%">E-mail</th>
                    <th width="25%">CPF</th>
                    <th width="10%">Status</th>
                    <th class="no-sort">Ações</th>
                </tr>
                </thead>
                <tbody>
                    {{#data}}
                        <tr>
                            <td>{{name}}</td>
                            <td>{{email}}</td>
                            <td>{{cpf}}</td>
                            {{#active}}
                                <td>Ativo</td>
                 	           {{/active}}
                            {{^active}}
                                <td>Inativo</td>
                            {{/active}}
                            <td>
                               
                                 <a href="./editar_idealizador.jsp?id={{id}}" class='label label-primary' title="Editar" style="display: none">Editar</a>
                                {{#active}}	<a href="javascript: void(0);" class='status label label-danger ' data-id='{{cpf}}' id="inativar{{cpf}}" title="Inativar">Inativar</a> {{/active}}
							    {{^active}}	<a href="javascript: void(0);" class='status label btn-ages-pr ' data-id='{{cpf}}' id="ativar{{cpf}}" title="Ativar">Ativar</a> {{/active}}
                               
                           </td>
                        </tr>
                    {{/data}}
                </tbody>
            </table>
        </script>

        <script id="idealizerListEmptyTemplate" type="x-tmpl-mustache">
            <h4>Não há nenhum idealizador cadastrado.</h4>
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="row mt-15">
            <div class="col-md-12">
                <div id="idealizerListBody" class="table-responsive" ></div>
            </div>
        </div>

    </jsp:body>
</t:system>