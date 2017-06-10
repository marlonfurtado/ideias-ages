<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<div class="row mt-15">
    <div class="col-md-12">
        <h3>Comentários</h3>
        <hr />

        <button class="btn btn-success" id="openAddComment">Adicionar comentário</button>
    </div>
</div>

<div class="row mt-15 hide" id="addComment">
    <form id="formAddComment">
        <div class="form-group">
            <div class="col-md-12">
                <textarea id="addCommentText" class="input-md form-control"></textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-12">
                <input type="submit" class="btn btn-success mt-15" value="Salvar comentário" />
            </div>
        </div>
    </form>
</div>

<div class="row mt-15">
    <div class="col-md-12">
        <div id="commentsListBody">Carregando...</div>
    </div>
</div>

<script id="commentsListTemplate" type="x-tmpl-mustache">
    {{#data}}
        <ul>
            <li>{{comment}}</li>
        </ul>
    {{/data}}
</script>