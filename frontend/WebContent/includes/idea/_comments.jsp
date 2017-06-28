<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<div class="row mt-15">
    <div class="col-md-12">
        <div class="clearfix">
            <h3 class="pull-left">Comentários</h3>
            <button class="btn btn-success mt-15 pull-right hide" id="openAddComment">Adicionar comentário</button>
        </div>
        <hr />
    </div>
</div>

<div class="row mt-15 hide" id="addComment">
    <form id="formAddComment">

        <div class="form-group">
            <div class="col-md-12">
                <div class="alert alert-info">
                    <p class="text-center">Insira abaixo um comentário complementar sobre a sua ideia. Lembre-se que você pode cadastrar no máximo 4 observações.</p>
                </div>
            </div>
        </div>

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

    <div class="col-md-12">
        <hr />
    </div>
</div>

<div class="row mt-15">
    <div class="col-md-12">
        <div id="commentsListBody">Carregando...</div>
    </div>
</div>

<script id="commentsListTemplate" type="x-tmpl-mustache">
    <article class='row'>
        {{#data}}
            <div class="col-md-6 col-xs-12">
                <div class="panel panel-default arrow left">
                    <div class="panel-body">
                        <div>
                            <div class='pull-right' style='width: 20px; height: 20px;'><i class='glyphicon glyphicon-comment' /></div>
                            <p>{{comment}}</p>
                        </div>
                    </div>
                </div>
            </div>
        {{/data}}
    </article>
</script>