<%@ page import="br.com.ideiasages.authorization.Role"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:content-role role="<%=Role.merge(Role.ANALYST, Role.ADMINISTRATOR, Role.IDEALIZER)%>">
	<div class="row mt-15">
		<div class="col-md-12">
			<h3>Dúvidas do Analista</h3>
			<hr />
		</div>
	</div>

		<div id="answersListBody"></div>

	<!-- Modal -->
	<div id="answerModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<form id="form-resposta-duvida" class="form-horizontal">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Responder dúvida</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<div class="col-md-12">
								<label for="idea-question-answer"
									id="question-description-required-simbol"> Resposta *
								</label>
								<textarea maxlength="1500" name="idea-question-answer"
									id="idea-question-answer" class="input-md form-control" required
									placeholder="Informe aqui uma resposta a dúvida do analista da idea (máximo de 1500 caracteres)."></textarea>
							</div>

							<div id="response" class="alert hide col-md-10 col-md-offset-1"></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-info" data-dismiss="modal">Cancelar</button>
							<button type="submit" class="btn btn-success">Enviar</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script id="answersListTemplate" type="x-tmpl-mustache">
		{{#data}}
			<div class="row">
				<div class="col-sm-1">
					<div class="thumbnail">
						<img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
					</div>
				</div>
				<div class="col-sm-11">
					<div class="panel panel-default">
						<div class="panel-heading">
							<strong>{{analyst.name}}</strong> <span class="text-muted">enviou uma dúvida:</span>
						</div>
						<div class="panel-body">
							<div>{{question}}</div>

							{{^answer}}
								<button class="btn btn-success mt-15 pull-right"
									data-toggle="modal"
									data-target="#answerModal">Responder dúvida</button>
							{{/answer}}

							{{#answer}}
								<br /> <br />
								<ul class="list-unstyled">
									<li>
										<div class="col-sm-1">
											<div class="thumbnail">
												<img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
											</div>
										</div>
										<div class="col-sm-11">
											<div class="panel panel-default">
												<div class="panel-heading">
													<strong>{{Você}}</strong> <span class="text-muted">respondeu:</span>
												</div>
												<div class="panel-body">{{answer}}</div>
											</div>
										</div>
									</li>
								</ul>
							{{/answer}}
						</div>
					</div>
				</div>
			</div>
		{{/data}}
	</script>
</t:content-role>