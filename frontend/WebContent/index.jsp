<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:system pageTitle="Listagem de Registros">
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <a href="#" class="btn btn-success"><span class="glyphicon glyphicon-plus-sign"></span> Criar</a>
            </div>
        </div>

        <div class="row mt-15">
            <div class="col-md-12">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th width="5%">#</th>
                            <th width="80%">Título</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr class="btn-default {{row_class}}">
                                <td>1</td>
                                <td>Titulo</td>
                                <td>
                                    <a href="/Blog/Edit/{{row.id}}" title="Editar">Editar</a>
                                    <a href="/Blog/Delete/{{row.id}}" title="Excluir">Excluir</a>
                                </td>
                            </tr>
                            <tr class="btn-default {{row_class}}">
                                <td>1</td>
                                <td>Titulo</td>
                                <td>
                                    <a href="/Blog/Edit/{{row.id}}" title="Editar">Editar</a>
                                    <a href="/Blog/Delete/{{row.id}}" title="Excluir">Excluir</a>
                                </td>
                            </tr>
                            <tr class="btn-default {{row_class}}">
                                <td>1</td>
                                <td>Titulo</td>
                                <td>
                                    <a href="/Blog/Edit/{{row.id}}" title="Editar">Editar</a>
                                    <a href="/Blog/Delete/{{row.id}}" title="Excluir">Excluir</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</t:system>