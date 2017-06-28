var modal = {
	show: function(title, value){
		
		var modal = "<div class='modal fade' id='myModal' role='dialog'>"+
	    "<div class='modal-dialog'>"+
	      "<div class='modal-content'>"+
	        "<div class='modal-header'>"+
	          "<button type='button' class='close' data-dismiss='modal'>×</button>"+
	          "<h4 class='modal-title'>"+title+"</h4>"+
	        "</div>"+
	        "<div class='modal-body'>"+
	          "<p>"+value+"</p>"+
	        "</div>"+
	        "<div class='modal-footer'>"+
	          "<button type='button' id='btn-fecharModal' class='btn btn-default' data-dismiss='modal' autofocus>Fechar</button>"+
	        "</div>"+
	      "</div>"+
	    "</div>"+
	  "</div>"; 
		$('body').find('.modal').remove();
		$('body').append(modal);
		$(".modal").modal('show');
	}

};
