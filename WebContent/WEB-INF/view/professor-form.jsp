<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Novo Professor</title>

<!--  Basic jquery and Bootstrap -->
		<script src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css" />
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
   		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
   		<script src="${pageContext.request.contextPath}/resources/javascript/cadastro.js"></script>
   		<script>		
$(function(){
    $('#datepicker').datepicker({ 
    	altField: '#usuario\\.data_nascimento_usuario', // é preciso usar \\ antes do ponto quando o id tem ponto
    	altFormat: 'mm/dd/yy',
    	dateFormat: 'dd/mm/yy'
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
</script> 
</head>

<body>

<script type="text/javascript">

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
		document.getElementById("cpf").setCustomValidity('Invalid');
		alert("CPF INVALIDO");
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
		document.getElementById("cpf").setCustomValidity('Invalid');
		alert("CPF INVALIDO");
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
		document.getElementById("cpf").setCustomValidity('Invalid');
		alert("CPF INVALIDO");
		return false;
	}
	
	document.getElementById("cpf").setCustomValidity('');
	//alert("CPF VALIDO!");
	return true;
}

function validaCnpj(cnpj){
	cnpj = cnpj.replace(/[^\d]+/g,'');
	 
   // if(cnpj == '') return false;
     
    if (cnpj.length != 14)
        return false;
 
    // Elimina CNPJs invalidos conhecidos
    if (cnpj == "00000000000000" || 
        cnpj == "11111111111111" || 
        cnpj == "22222222222222" || 
        cnpj == "33333333333333" || 
        cnpj == "44444444444444" || 
        cnpj == "55555555555555" || 
        cnpj == "66666666666666" || 
        cnpj == "77777777777777" || 
        cnpj == "88888888888888" || 
        cnpj == "99999999999999"){
    	alert("CNPJ INVÁLIDO");
    	return false;
    }
        
         
    // Valida DVs
    tamanho = cnpj.length - 2
    numeros = cnpj.substring(0,tamanho);
    digitos = cnpj.substring(tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(0)){
    	alert("CNPJ INVÁLIDO");
    	return false;
    }
        
         
    tamanho = tamanho + 1;
    numeros = cnpj.substring(0,tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(1)){
    	alert("CNPJ INVÁLIDO");
    	return false;
    }
    else{
    	//alert ("CNPJ OK!");
    	return true;
    }
           
    
}
       
</script>

	<div id="wrapper">
		<div id="header">
			<h2>Novo Professor</h2>
		</div>
	</div>
	
	<div id="container">
	
		${errorMessage}
		
		<form:form action="newProfessor" modelAttribute="professorModel" method="POST" onsubmit="return checkPasswordMatch();">
					
					<label>*Nome:</label><form:input path="usuario.nome_usuario" required="true" maxlength="45"/>
					
					<p><label>*Sobrenome:</label>
					<form:input path="usuario.sobrenome_usuario" required="true" maxlength="45"/></p>
					
					<p><label>*CPF:</label>
					<form:input path="usuario.cpf_usuario" id = "cpf" onblur="TestaCPF(this.value)" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"
					 placeholder="xxx.xxx.xxx-xx" maxlength="14" size="14" required="true"
					  title="Digite um CPF válido no formato: xxx.xxx.xxx-xx" /></p>
						 
					<p><label>CNPJ:</label>
					<form:input path="usuario.cnpj_usuario" pattern="\d{2}\.\d{3}\.\d{3}/\d{4}-\d{2}" id = "cnpj" onblur="validaCnpj(this.value)"
						title="Digite um CNPJ válido no formato: xx.xxx.xxx/xxxx-xx"  
						maxlength="18" size="18"  placeholder="xx.xxx.xxx/xxxx-xx" /></p>
					
					<p><label>*Email:</label>
					<form:input type="email" path="usuario.email_usuario" required="true" maxlength="45"/><p>
					
					
					<p><label>*Senha:</label>
					<form:input type="password" path="usuario.senha_usuario" id = "txtNewPassword" required="true" maxlength="40"/></p>
					
					<p><label>*Confirme a Senha:</label>
					<input type="password" id = "txtConfirmPassword" required/></p>
					
					<p><label>*Sexo:</label>
					Feminino <form:radiobutton path="usuario.sexo_usuario" value="F" required="true"/>
					Masculino <form:radiobutton path="usuario.sexo_usuario" value="M" /></p>
					
					<p><label>*Data de Nascimento:</label>
					<input type="text" id="datepicker" name="datepicker" placeholder="dd/mm/aaaa" required />
					<input type="hidden" id="usuario.data_nascimento_usuario" name="usuario.data_nascimento_usuario" value=""/></p>
						
					<p><label>*Telefone:</label>
					<form:input path="usuario.telefone_usuario" required="true" maxlength="11" placeholder="DDDXXXXXXXX" tittle="Número incluindo DDD, sem traços"/></p>
					
					<p><label>Profissão:</label>
					<form:input path="usuario.profissao_usuario" maxlength="40"/></p>
					
					<p><label>*Estado:</label>
					<form:select path="" id="estado" multiple="false">
					<td><form:options items="${professorModel.estado}" itemLabel="nm_estado"/><td>
					</form:select></p>
						
					<p><label>*Cidade:</label>
					<form:select path="cidade" id="cidade" multiple="false">
					</form:select></p>
														
					<input type="submit" value="Salvar" class="add-button" />
						
					<input type="button" value="Voltar"
							onclick = "window.location.href='../'; return false;"
							class="add-button"
							/>		
					<br><br>
					*Campos de preenchimento obrigatório
						
		</form:form>
		<br>
	</div>
</body>

</html>






