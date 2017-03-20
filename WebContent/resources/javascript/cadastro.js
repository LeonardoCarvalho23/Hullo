$(document).ready(function (){
	
	$("#estado").change(function(){
		var cidades = $('#cidade');
		cidades.html('');
		obterCidades();
	});
	
	function obterCidades() {

		var estado = $("#estado").val();
		//estado = estado.replace("]", "}");
		//estado = estado.replace("[", "{");
		//estado = estado.replace("EstadoImpl", "");
		//estado = estado.replace(/=/g,":");
		
		//estado = "EstadoImpl : {id_estado:5, nm_estado=Bahia, sg_estado=BA}"
		
		
		$.ajax({
			type : 'POST',
			url : window.location.href + '/cidades',
			contentType : 'application/json',
			data : JSON.stringify(estado),
			success : function(data){
				var cidades = $('#cidade');
				$.each(data, function(index, val){
					cidades.append(
							$("<option value='"+ JSON.stringify(val) +"'>"+ val.nm_cidade+"</option>")
							);
				});
				cidades.prop('disabled', false);
			}
		});
	}
	
});
