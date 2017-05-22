$(document).ready(function (){
	
	obterCidades();
	
	$("#estado").change(function(){
		var cidades = $('#cidade');
		cidades.html('');
		obterCidades();
	});
	
	var online = $("#sessionVar").val();
	
	if(online == "false"){
		$('#status').bootstrapToggle('off');
		}
	
	    $('#status').change(function(){
	    	$("#o").trigger("click");
	    });
	
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
					cidades.append(
							$("<option value='"+ val.id_Cidade +"'>"+ val.nm_cidade+"</option>")
							);
				});
				cidades.prop('disabled', false);
			}
		});
	}
	
	
	
});
