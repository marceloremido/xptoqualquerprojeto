function editaCamposFinanceiro(desc,valor1,action, idAnalitico,idDivAjax) {
	
	var valor = $("#"+valor1).val();
	var descricao = $("#"+desc).val();
	var chk = $("#chK"+idDivAjax).is(':checked');
	
	var confereBoolean = 1;
		
	if( chk  == false ){ confereBoolean = 0;}
	
	if(descricao == '' || descricao ==' ' || descricao == null){
		$("#"+desc).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}
	
	$.ajax({
		url : action+"?idAnalitico="+idAnalitico+"&valor="+valor+"&descricao="+descricao+"&chkFixoOutrosImpostos="+confereBoolean,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

// Edita checked fixo
function editaCheckedFixo(action, idAnalitico, idTabela, idDivAjax,codigo) {
	var codif = codigo;
	var chk = $("#chkEdita"+idDivAjax+idTabela).is(':checked');
	
	var confereBoolean = 1;
	if( chk  == false ){ confereBoolean = 0;}

	$.ajax({
		url : action+"?idAnalitico="+idAnalitico+"&idTabela="+idTabela+"&chkFixo="+confereBoolean+"&codigo="+codigo,
		success : function(data) {
			
		}
	});
};




function editaCamposFinanceiroDespesas(data,desc,valor1,action, idAnalitico,idDivAjax) {
	
	var chk = $("#chK"+idDivAjax).is(':checked');
	var confereBoolean = 1;
	if( chk  == false ){ confereBoolean = 0;}
	
	
	var valor = $("#"+valor1).val();
	var descricao = $("#"+desc).val();
	var datas = $("#"+data).val();
	
	if(datas == '' || datas ==' ' || datas == null){
		$("#"+data).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}
	if(descricao == '' || descricao ==' ' || descricao == null){
		$("#"+desc).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}

	
	$.ajax({
		url : action+"?idAnalitico="+idAnalitico+"&DataPgto="+datas+"&valor="+valor+"&descricao="+descricao+"&chkFixo="+confereBoolean,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

//Edita Valores 
function editaCamposAnalitico(action,campo,idTabela,tipoCampo,idDivAjax) {
	
	var valor = $("#"+campo).val();
	var valor2 = valor.replace("%","x1x2x3x");
	$.ajax({
		url : action+"?idTabela="+idTabela+"&valor="+valor2+"&tipoCampo="+tipoCampo,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

//Edita Valores Despesas
function editaCamposAnaliticoDespesas(action,campo,idTabela,tipoCampo,idDivAjax) {
	
	var valor = $("#"+campo).val();
	var valor2 = valor.replace("%","x1x2x3x");
	$.ajax({
		url : action+"?idTabela="+idTabela+"&valor="+valor2+"&tipoCampo="+tipoCampo,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

// ---------------------------------------------------------------------------------------- //
// MOVIMENTO FINANCEIRO
// Entrada de valores
function InsereDadosMovimentacao(ndnf, data,desc,valor1,action, idAnalitico,idDivAjax,idBanco) {
	var valor = $("#"+valor1).val();
	var descricao = $("#"+desc).val();
	var datas = $("#"+data).val();
	var ndnf1 = $("#"+ndnf).val();
	
	if(datas == '' || datas ==' ' || datas == null){
		$("#"+data).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}
	if(descricao == '' || descricao ==' ' || descricao == null){
		$("#"+desc).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}
	
	
	$.ajax({
		url : action+"?idAnalitico="+idAnalitico+"&DataPgto="+datas+"&valor="+valor+"&descricao="+descricao+"&ndnf="+$("#"+ndnf).val()+"&idBanco="+idBanco,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

//Edita Valores Entradas
function editaValoresEntradas(action,campo,idTabela,tipoCampo,idDivAjax,idBanco) {
	
	var valor = $("#"+campo).val();
	var valor2 = valor.replace("%","x1x2x3x");

	$.ajax({
		url : action+"?idTabela="+idTabela+"&valor="+valor2+"&tipoCampo="+tipoCampo+"&idBanco="+idBanco,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};


//Saida de valores
function insereDadosMovimentacaoSaidas(data,desc,valor1,action, idAnalitico,idDivAjax,idBanco) {
	var valor = $("#"+valor1).val();
	var descricao = $("#"+desc).val();
	var datas = $("#"+data).val();
	
	if(datas == '' || datas ==' ' || datas == null){
		$("#"+data).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}
	if(descricao == '' || descricao ==' ' || descricao == null){
		$("#"+desc).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}
	
	
	$.ajax({
		url : action+"?idAnalitico="+idAnalitico+"&DataPgto="+datas+"&valor="+valor+"&descricao="+descricao+"&idBanco="+idBanco,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

//Edita Valores Saidas
function editaValoresSaidas(action,campo,idTabela,tipoCampo,idDivAjax,idBanco) {
	
	var valor = $("#"+campo).val();
	var valor2 = valor.replace("%","x1x2x3x");
	
	$.ajax({
		url : action+"?idTabela="+idTabela+"&valor="+valor2+"&tipoCampo="+tipoCampo+"&idBanco="+idBanco,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};

//Edita Saldos em movimento financeiros Bancos
function editaSaldos(campo,idAnalitico,tipoCampo,idBanco) {
	
	var valor = $("#"+campo).val();
	
	$.ajax({
		url : "editaSaldosBancos?valor="+valor+"&idAnalitico="+idAnalitico+"&tipoCampo="+tipoCampo+"&idBanco="+idBanco,
		success : function(data) {
			location.reload();
		}
	});
};
// -------------------------------------------------------------------------------------------------------------- //
//Salva novo Emprestimo
function insereEmprestimos(data,desc,valor1,action, idAnalitico,idDivAjax,idBanco) {
	var valor = $("#"+valor1).val();
	var descricao = $("#"+desc).val();
	var datas = $("#"+data).val();
	var banco = $("#"+idBanco).val();
	
	
	/*console.log(datas);
	console.log(descricao);
	console.log(valor);
	console.log("Action: "+action);
	console.log(idAnalitico);
	console.log(idDivAjax);
	console.log(idBanco);*/
	
	
	console.log(action+"?idAnalitico="+idAnalitico+"&DataPgto="+datas+"&valor="+valor+"&descricao="+descricao+"&idBanco="+banco);
	
	
	if(datas == '' || datas ==' ' || datas == null){
		$("#"+data).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}
	if(descricao == '' || descricao ==' ' || descricao == null){
		$("#"+desc).css("border","1px solid red");
		alert("Coloque um valor");
		return false;
	}
	
	
	$.ajax({
		url : action+"?idAnalitico="+idAnalitico+"&DataPgto="+datas+"&valor="+valor+"&descricao="+descricao+"&idBanco="+banco,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};
//Edita Emprestimos
function editaEmprestimos(campo,idAnalitico,tipoCampo,idEmprestimo,idDivAjax) {
	
	var valor = $("#"+campo).val();
	
	$.ajax({
		url : "editaEmprestimo?valor="+valor+"&idAnalitico="+idAnalitico+"&tipoCampo="+tipoCampo+"&idEmprestimo="+idEmprestimo,
		success : function(data) {
			$("#"+idDivAjax).html(data);
		}
	});
};


