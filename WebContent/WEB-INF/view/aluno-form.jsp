<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Novo Aluno</title>
	<!--  Basic jquery and Bootstrap -->
		<script src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css" />
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
   		<script src="${pageContext.request.contextPath}/resources/javascript/cadastro.js"></script>
   	<!-- CSS Custom -->
   		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css?v=29" />
<script>		
$(function(){
    $('#datepicker').datepicker({ 
    	altField: '#usuario\\.data_nascimento_usuario', // é preciso usar dois \\ antes do ponto pra funcionar quando o id tem ponto no nome
    	altFormat: 'mm/dd/yy',
    	dateFormat: 'dd/mm/yy',
    	maxDate: new Date //funcao que impede datas futuras
    });
});
/* Portuguese initialisation for the jQuery UI date picker plugin. */
jQuery(function ($) {
    $.datepicker.regional['pt'] = {
        closeText: 'Fechar',
        prevText: '<Anterior',
        nextText: 'Seguinte',
        currentText: 'Hoje',
        monthNames: ['Janeiro', 'Fevereiro', 'Mar&ccedil;o', 'Abril', 'Maio', 'Junho',
        'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
        monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun',
        'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
        dayNames: ['Domingo', 'Segunda-feira', 'Ter&ccedil;a-feira', 'Quarta-feira', 'Quinta-feira', 'Sexta-feira', 'S&aacute;bado'],
        dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'S&aacute;b'],
        dayNamesMin: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'S&aacute;b'],
        weekHeader: 'Sem',
        dateFormat: 'dd/mm/yy',
        firstDay: 0,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ''
    };
    $.datepicker.setDefaults($.datepicker.regional['pt']);
});

function checkFuture(data){
	
	var date = new Date(data.split('/').reverse().join('/'));
	var novaData = new Date();
	
	if(date > novaData){
    	document.getElementById("datepicker").setCustomValidity('Data Inválida');
		document.getElementById("datepicker").style.color = "red";
		return false;
    }
	document.getElementById("datepicker").setCustomValidity('');
	document.getElementById("datepicker").style.color = "black";
	return true;
	
}

//verifica se senhas digitadas sao iguais
function checkPasswordMatch() {
    var password = $("#txtNewPassword").val();
    var confirmPassword = $("#txtConfirmPassword").val();
    if (password != confirmPassword){
    	alert ("Senhas não são iguais");
    	return false;
    }
    return true
}

//Verifica se CPF é válido
function TestaCPF(strCPF) {
	strCPF = strCPF.replace(/[^\d]+/g,'');
	var Soma, Resto, borda_original;
	Soma = 0;
	
	
	if (strCPF == "00000000000"){
		document.getElementById("cpf").setCustomValidity('CPF Inválido');
		document.getElementById("cpf").style.color = "red";
		return false;
	}
	
	for (i=1; i<=9; i++){
		Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
	}
	
	Resto = (Soma * 10) % 11;
	if ((Resto == 10) || (Resto == 11)){
		Resto = 0;
	}
	
	if (Resto != parseInt(strCPF.substring(9, 10))){
		document.getElementById("cpf").setCustomValidity('CPF Inválido');
		document.getElementById("cpf").style.color = "red";
		return false;
	}
	
	Soma = 0;
	for (i = 1; i <= 10; i++){
		Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
	}
	
	Resto = (Soma * 10) % 11;
	if ((Resto == 10) || (Resto == 11)){
		Resto = 0;
	}
	
	if (Resto != parseInt(strCPF.substring(10, 11))){
		document.getElementById("cpf").setCustomValidity('CPF Inválido');
		document.getElementById("cpf").style.color = "red";
		return false;
	}
	
	document.getElementById("cpf").setCustomValidity('');
	document.getElementById("cpf").style.color = "black";
	return true;
}
   

</script> 
	
</head>

<body>
	<div id="wrapper">
	<div id="header">
			<img class="logo" src="${pageContext.request.contextPath}/resources/images/logo200v2.png" width="100px" >
		</div>
	<div id="container">
		
		<form:form class="well form-horizontal" action="formAluno" modelAttribute="usuarioModel" method="POST" onsubmit="return checkPasswordMatch();" >
		<fieldset>
		${errorMessage}
			<!-- Nome do formulário -->
			<legend>Novo aluno</legend>
			
			<!-- Campo nome -->
			<div class="form-group">
		  		<label class="col-md-4 control-label">Nome</label>  
		  			<div class="col-md-4 inputGroupContainer">
		  				<div class="input-group">
		  				<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		  				<form:input placeholder="Nome" class="form-control" path="usuario.nome_usuario" required="true" maxlength="45"/>
		    			</div>
		  			</div>
			</div>
								
			<!-- Campo Sobrenome -->
			<div class="form-group">
		  		<label class="col-md-4 control-label">Sobrenome</label>  
		  			<div class="col-md-4 inputGroupContainer">
		  				<div class="input-group">
		  				<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		  				<form:input placeholder="Sobrenome" class="form-control" path="usuario.sobrenome_usuario" required="true" maxlength="45"/>
		    			</div>
		  			</div>
			</div>	
								
			<!-- Campo CPF -->
			<div class="form-group">
		  		<label class="col-md-4 control-label">CPF</label>  
		  			<div class="col-md-4 inputGroupContainer">
		  				<div class="input-group">
		  				<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		  				<form:input class="form-control" path="usuario.cpf_usuario" id = "cpf" onblur="TestaCPF(this.value)" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"
					 placeholder="xxx.xxx.xxx-xx" maxlength="14" size="14" required="true"
					  title="Digite um CPF válido no formato: xxx.xxx.xxx-xx" />
		    			</div>
		  			</div>
			</div>	
							
			<!-- Campo email -->
			<div class="form-group">
		  		<label class="col-md-4 control-label">E-mail</label>  
		  			<div class="col-md-4 inputGroupContainer">
		  				<div class="input-group">
		  				<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
		  				<form:input placeholder="E-mail" class="form-control" type="email" path="usuario.email_usuario" required="true" maxlength="45"/>
		    			</div>
		  			</div>
			</div>	

			<!-- Campo senha -->
			<div class="form-group">
		  		<label class="col-md-4 control-label">Senha</label>  
		  			<div class="col-md-4 inputGroupContainer">
		  				<div class="input-group">
		  				<span class="input-group-addon"><i class="glyphicon glyphicon-asterisk"></i></span>
		  				<form:input placeholder="Senha" class="form-control" type="password" path="usuario.senha_usuario" id="txtNewPassword" required="true" maxlength="40"/>
		    			</div>
		  			</div>
			</div>	

			<!-- Campo confirmação senha -->
			<div class="form-group">
		  		<label class="col-md-4 control-label">Confirme sua senha</label>  
		  			<div class="col-md-4 inputGroupContainer">
		  				<div class="input-group">
		  				<span class="input-group-addon"><i class="glyphicon glyphicon-asterisk"></i></span>
		  				<input placeholder="Confirme sua senha" class="form-control" type="password" id ="txtConfirmPassword" required/>
		    			</div>
		  			</div>
			</div>							

			<!-- Campo sexo -->
			<div class="form-group">
                        <label class="col-md-4 control-label">Sexo</label>
                        <div class="col-md-4">
                            <div class="radio">
                                <label>
                                    <form:radiobutton path="usuario.sexo_usuario" value="F" required="true"/> Feminino
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <form:radiobutton path="usuario.sexo_usuario" value="M" /> Masculino
                                </label>
                            </div>
                        </div>
                    </div>					

			<!-- Campo Data nascimento -->
			<div class="form-group">
		  		<label class="col-md-4 control-label">Data de nascimento</label>  
		  			<div class="col-md-4 inputGroupContainer">
		  				<div class="input-group">
		  				<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
		  				<input class="form-control" type="text" id="datepicker" name="datepicker" placeholder="dd/mm/aaaa" onblur="checkFuture(this.value)" required />
						<input type="hidden" id="usuario.data_nascimento_usuario" name="usuario.data_nascimento_usuario" value=""/>

		    			</div>
		  			</div>
			</div>						

			<!-- Campo Telefone -->
			<div class="form-group">
		  		<label class="col-md-4 control-label">Telefone</label>  
		  			<div class="col-md-4 inputGroupContainer">
		  				<div class="input-group">
		  				<span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
		  				<form:input class="form-control" path="usuario.telefone_usuario" required="true" placeholder="DDXXXXXXXXX" 
		  				pattern="[0-9]+$" maxlength="11" tittle="Número incluindo DDD, sem traços"/>
		    			</div>
		  			</div>
			</div>	

			<!-- Campo Profissão -->
			<div class="form-group">
		  		<label class="col-md-4 control-label">Profissão</label>  
		  			<div class="col-md-4 inputGroupContainer">
		  				<div class="input-group">
		  				<span class="input-group-addon"><i class="glyphicon glyphicon-briefcase"></i></span>
		  				<form:input class="form-control" placeholder="Profissão" path="usuario.profissao_usuario" maxlength="40"/>
		    			</div>
		  			</div>
			</div>					

			<!-- Campo Estado -->
			<div class="form-group">
		  		<label class="col-md-4 control-label">Estado</label>  
		  			<div class="col-md-4 inputGroupContainer">
		  				<div class="input-group">
		  				<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
		  					<form:select class="form-control" path="" id="estado" multiple="false">
							<form:options items="${usuarioModel.estado}" itemLabel="nm_estado"/>
							</form:select>
		    			</div>
		  			</div>
			</div>
			
			<!-- Campo Cidade -->
			<div class="form-group">
		  		<label class="col-md-4 control-label">Cidade</label>  
		  			<div class="col-md-4 inputGroupContainer">
		  				<div class="input-group">
		  				<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
							<form:select class="form-control" path="cidade" id="cidade" multiple="false"> </form:select>
		    			</div>
		  			</div>
			</div>	
								
			<!-- Botões -->
			<div class="form-group">
			  <label class="col-md-4 control-label"></label>
			  <div class="col-md-4">
			    <button type="submit" class="btn btn-primary" >Salvar </button>
			    <input type="button" class="btn btn-primary" value="Voltar" onclick="window.location.href='../'; return false;" />
			  </div>
			</div>
					
								
						
		</fieldset>
		</form:form>
		</div>
		<div id="footer"> <p style="display: block; margin: auto; padding: 10px;" align="center">Copyright © 2017 Hullo. Todos os direitos reservados.</p></div>
	</div>
</body>

</html>