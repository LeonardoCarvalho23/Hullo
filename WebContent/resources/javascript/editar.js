$(document).ready(function (){
	
	var cidadeUsuario = $("#cidadeUsuario").val();
	var estadoUsuario = $("#estadoUsuario").val();
	
	$("#estado").change(function(){
		var cidades = $('#cidade');
		cidades.html('');
		obterCidades();
	});
	
	$("#estado").val(estadoUsuario);
	
	obterCidades();
	
	function obterCidades() {

		var estado = $("#estado").val();
		
		$.ajax({
			type : 'POST',
			url : window.location.href + '/cidades',
			contentType : 'application/json',
			data : JSON.stringify(estado),
			success : function(data){
				var cidades = $('#cidade');
				$.each(data, function(index, val){
					if(val.id_Cidade == cidadeUsuario){
						cidades.append(
								$("<option value='"+ val.id_Cidade +"' selected>"+ val.nm_cidade+"</option>")
								);
					}else{
						cidades.append(
								$("<option value='"+ val.id_Cidade +"'>"+ val.nm_cidade+"</option>")
								);	
					}
				});
				cidades.prop('disabled', false);
			}
		});
	}
	
	
	
});
