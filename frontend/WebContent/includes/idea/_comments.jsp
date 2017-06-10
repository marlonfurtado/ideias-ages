<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<div class="row mt-15">
    <div class="col-md-12">
        <div class="clearfix">
            <h3 class="pull-left">Comentários</h3>
            <button class="btn btn-success mt-15 pull-right" id="openAddComment">Adicionar comentário</button>
        </div>
        <hr />
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
        <p>Confira aqui a lista de comentários anexados na ideia.</p>
        <div id="commentsListBody">Carregando...</div>
    </div>
</div>

<script id="commentsListTemplate" type="x-tmpl-mustache">
    {{#data}}
        <ul style='margin-left: 20px;' class='list-unstyled'>
            <li><i class='glyphicon glyphicon-comment' /> {{comment}}</li>
        </ul>
    {{/data}}
</script>