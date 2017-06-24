<%@ page import="br.com.ideiasages.authorization.Role"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>


<t:system pageTitle="Listagem de Ideias"
	role="<%= Role.merge(Role.IDEALIZER, Role.ANALYST, Role.ADMINISTRATOR) %>">
	<jsp:attribute name="scripts">
        <script type="text/javascript" src="./assets/js/mustache.min.js"></script>
        <script type="text/javascript" src="./assets/js/ideia/listar.js"></script>
        <script type="text/javascript" src="./assets/js/modal.js"></script>
        <script id="ideasListTemplate" type="x-tmpl-mustache">
            <table id="ideas-table" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th width="20%">Título</th>
                    <th width="20%">Palavras-chaves</th>
					<th width="15%">Status</th>                    
					<th width="15%">Data de Criação</th>
                    <th width="15%">Analista Vinculado</th>
                    <th class="no-sort">Ações</th>
                </tr>
                </thead>
                <tbody>
                    {{#data}}
                        <tr>
                            <td>{{title}}</td>
                            <td>{{tags}}</td>
							<td>{{status}}</td>
                            <td>{{creationDate}}</td>
							<td>{{analyst.name}}</td>
							<td><a href="./detalhes_ideia.jsp?id={{id}}" class='label label-primary' title="{{btnLabel}}">{{btnLabel}}</a></td>
                           </td>
                        </tr>
                    {{/data}}
                </tbody>
            </table>
        </script>

        <script id="ideasListEmptyTemplate" type="x-tmpl-mustache">
            <h4>Não há nenhuma ideia cadastrada no sistema.</h4>
        </script>
    </jsp:attribute>

	<jsp:body>
        <div class="row mt-15">
            <div class="col-md-12">
                <div id="ideasListBody" class="table-responsive">
                </div>
            </div>
        </div>

    </jsp:body>
</t:system>