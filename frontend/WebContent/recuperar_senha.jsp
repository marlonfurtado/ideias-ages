<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper pageTitle="Login">
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="./assets/js/jquery.mask.min.js"></script>
        <script type="text/javascript" src="./assets/js/forgotPassword/recoverPassword.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="container" style="margin-top: 50px; width: 600px;">
            <div class="row">
                <h1 id="titulo-cadastro-idealizador" class="blue-ages text-center">Troca de senha</h1>
                    <br>
                    <form id="form-cadastro-idealizador" class="form-horizontal">
                  
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="new-password">Nova senha</label>
                            <div class="col-md-6">
                                <input id="new-password" name="new-password" type="password" placeholder="Nova senha" class="form-control input-md" required="true" disabled>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="confirm-password">Confirmar senha</label>
                            <div class="col-md-6">
                                <input id="confirm-password" name="confirm-password" type="password" placeholder="Confirmar senha" class="form-control input-md" required="true" disabled>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        
                        <div class="form-group-row">
                            <div class="col-md-6">
		                        <a class="btn btn-lg btn-block btn-default" href="./login.jsp" >Voltar ao login</a>
		                    </div>
                            <div class="col-md-6">
                                <button id="btn-create-new-password" name="create-new-password" class="btn btn-block btn-lg btn-ages-pr" disabled>Trocar senha</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        
        <!-- Modal -->
		<div id="change-password-modal" class="modal fade" role="dialog">
			<div class="modal-dialog">
			    <!-- Modal content-->
			    <div class="modal-content">
					<div id="change-password-modal-body" class="modal-body"></div>
					<div class="modal-footer">
						<button id="modal-close" type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					</div>
				</div>
			</div>
		</div>
		
    </jsp:body>
</t:wrapper>