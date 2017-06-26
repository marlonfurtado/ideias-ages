<%@ page import="br.com.ideiasages.authorization.Role"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:content-role role="<%=Role.merge(Role.ANALYST, Role.ADMINISTRATOR)%>">
	<div class="row mt-15">
		<div class="col-md-12">
			<div class="clearfix">
				<button class="btn btn-success mt-15 pull-right"
					id="openQuestionModal" data-toggle="modal"
					data-target="#questionModal">Faça uma pergunta ao
					idealizador</button>
			</div>
			<hr />
		</div>
	</div>
	<!-- Modal -->
	<div id="questionModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<form id="form-questionamento-ideia" class="form-horizontal">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Questionar idéia</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<div class="col-md-12">
								<label for="idea-question-description"
									id="question-description-required-simbol"> Descrição *
								</label>
								<textarea maxlength="1500" name="idea-question-description"
									id="idea-question-description" class="input-md form-control"
									required
									placeholder="Informe aqui a sua dúvida (máximo de 1500 caracteres)."
									onmouseover="getToolTip(this)"></textarea>
							</div>

							<div id="response" class="alert hide col-md-10 col-md-offset-1"></div>
						</div>
						<div class="modal-footer">
							<button type="button" id="btn-cancel-modal" class="btn btn-info"
								data-dismiss="modal">Cancelar</button>
							<button type="button" id="btn-send-modal" class="btn btn-success"
								onclick="enviar()">Enviar</button>
						</div>
					</div>
			</form>
		</div>
	</div>
</t:content-role>