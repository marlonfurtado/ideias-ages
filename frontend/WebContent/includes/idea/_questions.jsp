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
							<textarea maxlength="1500" name="idea-question-description"
								id="idea-question-description" class="input-md form-control" required
								placeholder="Informe aqui a sua dúvida (máximo de 1500 caracteres)."
								onmouseover="getToolTip(this)"></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" data-dismiss="modal">Cancelar</button>
						<button type="button" class="btn btn-success" onclick="enviar()">Enviar</button>
					</div>
				</div>
		</form>
	</div>
</div>