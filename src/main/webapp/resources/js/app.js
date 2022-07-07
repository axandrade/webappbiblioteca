$(document).ready(function() {
	
	$('#sidebarCollapse').on('click', function() {
		$('#sidebar').toggleClass('active');
		$(this).toggleClass('active');
	});

	dataTable();
 	dataTableUsuario();
 	dataTableLivro();
	dataTableAutor();
	dataTableAutorSelected();
	dataTablePessoa();
	dataTableEmprestimo();
	dataInitMask();
	
});

function dataTable() {

	$(".dataTable").DataTable({
		bSort : false,
		pageLength : 10,
		searching : false,
		lengthChange : false,
		language : {
			"sEmptyTable" : "Nenhum registro encontrado",
			"sInfo" : "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			"sInfoEmpty" : "Mostrando 0 até 0 de 0 registros",
			"sInfoFiltered" : "(Filtrados de _MAX_ registros)",
			"sInfoPostFix" : "",
			"sInfoThousands" : ".",
			"sLengthMenu" : "_MENU_ resultados por página",
			"sLoadingRecords" : "Carregando...",
			"sProcessing" : "Processando...",
			"sZeroRecords" : "Nenhum registro encontrado",
			"sSearch" : "Pesquisar",
			"oPaginate" : {
				"sNext" : ">>",
				"sPrevious" : "<<",
				"sFirst" : "Primeiro",
				"sLast" : "Último"
			},
			"oAria" : {
				"sSortAscending" : ": Ordenar colunas de forma ascendente",
				"sSortDescending" : ": Ordenar colunas de forma descendente"
			}
		}
	});

}

function dataTableUsuario() {
	
	var table = $(".dataTable-usuario").DataTable({
		pageLength : 5,
		lengthChange : false,
		"columnDefs": [ {
			"targets": 4,
			"orderable": false
			} ],
		buttons : [ {
			extend : 'csvHtml5',
			text : 'CSV',
			title : 'report-csv'
		}, {
			extend : 'excelHtml5',
			text : 'EXCEL',
			title : 'report-excel'
		}, {
			extend : 'pdfHtml5',
			text : 'PDF',
			title : 'report-pdf'
		} ],
		language : {
			"sEmptyTable" : "Nenhum registro encontrado",
			"sInfo" : "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			"sInfoEmpty" : "Mostrando 0 até 0 de 0 registros",
			"sInfoFiltered" : "(Filtrados de _MAX_ registros)",
			"sInfoPostFix" : "",
			"sInfoThousands" : ".",
			"sLengthMenu" : "_MENU_ resultados por página",
			"sLoadingRecords" : "Carregando...",
			"sProcessing" : "Processando...",
			"sZeroRecords" : "Nenhum registro encontrado",
			"sSearch" : "Pesquisar",
			"oPaginate" : {
				"sNext" : ">>",
				"sPrevious" : "<<",
				"sFirst" : "Primeiro",
				"sLast" : "Último"
			},
			"oAria" : {
				"sSortAscending" : ": Ordenar colunas de forma ascendente",
				"sSortDescending" : ": Ordenar colunas de forma descendente"
			}
		}
	});

	table.buttons().container().html("").appendTo('.dataTables_wrapper .col-md-6:eq(0)');

}

function dataTableLivro() {

	var table = $(".dataTable-livro").DataTable({
		pageLength : 20,				// informa a quantidade de linhas por pagina na tabela
		lengthChange : false,		//opcao que libera a alteracao da qtd de linhas por pagina
		"columnDefs": [ 
			
			{
			"targets": 3,
			"orderable": false
			} 
			
			
			],
		buttons : [ {
			extend : 'csvHtml5',
			text : 'CSV',
			title : 'report-csv'
		}, {
			extend : 'excelHtml5',
			text : 'EXCEL',
			title : 'report-excel'
		}, {
			extend : 'pdfHtml5',
			text : 'PDF',
			title : 'report-pdf'
		} ],
		language : {
			"sEmptyTable" : "Nenhum registro encontrado",
			"sInfo" : "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			"sInfoEmpty" : "Mostrando 0 até 0 de 0 registros",
			"sInfoFiltered" : "(Filtrados de _MAX_ registros)",
			"sInfoPostFix" : "",
			"sInfoThousands" : ".",
			"sLengthMenu" : "_MENU_ resultados por página",
			"sLoadingRecords" : "Carregando...",
			"sProcessing" : "Processando...",
			"sZeroRecords" : "Nenhum registro encontrado",
			"sSearch" : "Pesquisar",
			"oPaginate" : {
				"sNext" : ">>",
				"sPrevious" : "<<",
				"sFirst" : "Primeiro",
				"sLast" : "Último"
			},
			"oAria" : {
				"sSortAscending" : ": Ordenar colunas de forma ascendente",
				"sSortDescending" : ": Ordenar colunas de forma descendente"
			}
		}
	});

	table.buttons().container().html("").appendTo('.dataTables_wrapper .col-md-6:eq(0)');

}

function dataTablePessoa() {

	var table = $(".dataTable-pessoa").DataTable({
		lengthChange : false,
		"columnDefs": [ {
			"targets": 4, //informa qual coluna eu não desejo ordenar
			"orderable": false
			} ],
		buttons : [ {
			extend : 'csvHtml5',
			text : 'CSV',
			title : 'report-csv'
		}, {
			extend : 'excelHtml5',
			text : 'EXCEL',
			title : 'report-excel'
		}, {
			extend : 'pdfHtml5',
			text : 'PDF',
			title : 'report-pdf'
		} ],
		language : {
			"sEmptyTable" : "Nenhum registro encontrado",
			"sInfo" : "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			"sInfoEmpty" : "Mostrando 0 até 0 de 0 registros",
			"sInfoFiltered" : "(Filtrados de _MAX_ registros)",
			"sInfoPostFix" : "",
			"sInfoThousands" : ".",
			"sLengthMenu" : "_MENU_ resultados por página",
			"sLoadingRecords" : "Carregando...",
			"sProcessing" : "Processando...",
			"sZeroRecords" : "Nenhum registro encontrado",
			"sSearch" : "Pesquisar",
			"oPaginate" : {
				"sNext" : ">>",
				"sPrevious" : "<<",
				"sFirst" : "Primeiro",
				"sLast" : "Último"
			},
			"oAria" : {
				"sSortAscending" : ": Ordenar colunas de forma ascendente",
				"sSortDescending" : ": Ordenar colunas de forma descendente"
			}
		}
	});

	table.buttons().container().html("").appendTo('.dataTables_wrapper .col-md-6:eq(0)');

}

function dataInitMask() {
		
	$('.cpf').mask('000.000.000-00', {reverse: true});
	$('.phone').mask('(00) 0 0000-0000');
	$('.cep').mask('00000-000');
	
}

function dataTableAutor() {

	var table = $(".dataTable-autor").DataTable({	
		dom: 'lBfrti',
		lengthChange : false,
        buttons: ['excel', 'pdf' ],
		language : {
			"sEmptyTable" : "Nenhum registro encontrado",
			"sInfo" : "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			"sInfoEmpty" : "Mostrando 0 até 0 de 0 registros",
			"sInfoFiltered" : "(Filtrados de _MAX_ registros)",
			"sInfoPostFix" : "",
			"sInfoThousands" : ".",
			"sLengthMenu" : "_MENU_ resultados por página",
			"sLoadingRecords" : "Carregando...",
			"sProcessing" : "Processando...",
			"sZeroRecords" : "Nenhum registro encontrado",
			"sSearch" : "Pesquisar",
			"oPaginate" : {
				"sNext" : ">>",
				"sPrevious" : "<<",
				"sFirst" : "Primeiro",
				"sLast" : "Último"
			},
			"oAria" : {
				"sSortAscending" : ": Ordenar colunas de forma ascendente",
				"sSortDescending" : ": Ordenar colunas de forma descendente"
			}
		}
	});

	

}

function dataTableAutorSelected() {

	var table = $(".dataTable-autor-selected").DataTable({
		lengthChange : false,				
		paging: false,
		searching: false,
		ordering: false,
		info: false,
		language : {
			"sEmptyTable" : "Nenhum registro encontrado",
			"sInfo" : "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			"sInfoEmpty" : "Mostrando 0 até 0 de 0 registros",
			"sInfoFiltered" : "(Filtrados de _MAX_ registros)",
			"sInfoPostFix" : "",
			"sInfoThousands" : ".",
			"sLengthMenu" : "_MENU_ resultados por página",
			"sLoadingRecords" : "Carregando...",
			"sProcessing" : "Processando...",
			"sZeroRecords" : "Nenhum registro encontrado",
			"sSearch" : "Pesquisar",
			"oPaginate" : {
				"sNext" : ">>",
				"sPrevious" : "<<",
				"sFirst" : "Primeiro",
				"sLast" : "Último"
			},
			"oAria" : {
				"sSortAscending" : ": Ordenar colunas de forma ascendente",
				"sSortDescending" : ": Ordenar colunas de forma descendente"
			}
		}
	});

	table.buttons().container().html("").appendTo('.dataTables_wrapper .col-md-6:eq(0)');

}

function dataTableEmprestimo() {

	var table = $(".dataTable-emprestimo").DataTable({
		pageLength : 5,				// informa a quantidade de linhas por pagina na tabela
		lengthChange : false,		//opcao que libera a alteracao da qtd de linhas por pagina
		"columnDefs": [ 			
			{
			"targets": 3,
			"orderable": false
			}, 
			{
			"targets": 4,
			"orderable": false
			}, 
			{
			"targets": 5,
			"orderable": false
			} 			
			
			],
		
		language : {
			"sEmptyTable" : "Nenhum registro encontrado",
			"sInfo" : "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			"sInfoEmpty" : "Mostrando 0 até 0 de 0 registros",
			"sInfoFiltered" : "(Filtrados de _MAX_ registros)",
			"sInfoPostFix" : "",
			"sInfoThousands" : ".",
			"sLengthMenu" : "_MENU_ resultados por página",
			"sLoadingRecords" : "Carregando...",
			"sProcessing" : "Processando...",
			"sZeroRecords" : "Nenhum registro encontrado",
			"sSearch" : "Pesquisar",
			"oPaginate" : {
				"sNext" : ">>",
				"sPrevious" : "<<",
				"sFirst" : "Primeiro",
				"sLast" : "Último"
			},
			"oAria" : {
				"sSortAscending" : ": Ordenar colunas de forma ascendente",
				"sSortDescending" : ": Ordenar colunas de forma descendente"
			}
		}
	});

	table.buttons().container().html("").appendTo('.dataTables_wrapper .col-md-6:eq(0)');

}


function dataTableFromJSF(data) {

	if (data.status == "begin") {
		$.LoadingOverlay("show");
	}

	if (data.status == "success") {
		dataTable();
		$.LoadingOverlay("hide");

	}
}


function dataTableUsuarioFromJSF(data) {

	if (data.status == "begin") {
		$.LoadingOverlay("show");
	}

	if (data.status == "success") {
		dataTableUsuario();
		$.LoadingOverlay("hide");

	}
}

function dataTableLivroFromJSF(data) {

	if (data.status == "begin") {
		$.LoadingOverlay("show");
	}

	if (data.status == "success") {
		dataTableLivro();
		$.LoadingOverlay("hide");

	}
}

function dataTablePessoaFromJSF(data) {

	if (data.status == "begin") {
		$.LoadingOverlay("show");
	}

	if (data.status == "success") {
		dataTablePessoa();
		$.LoadingOverlay("hide");

	}
}

function dataTableAutorFromJSF(data) {

	if (data.status == "begin") {
		$.LoadingOverlay("show");
	}

	if (data.status == "success") {
		dataTableAutor();
		$.LoadingOverlay("hide");

	}
}

function dataTableAutorSelectedFromJSF(data) {

	if (data.status == "begin") {
		$.LoadingOverlay("show");
	}

	if (data.status == "success") {
		dataTableAutorSelected();
		$.LoadingOverlay("hide");

	}
}


function loadingOverlay(action) {
	$.LoadingOverlay(action);
}

function dataTableEmprestimoFromJSF(data) {

	if (data.status == "begin") {
		$.LoadingOverlay("show");
	}

	if (data.status == "success") {
		dataTableEmprestimo();
		$.LoadingOverlay("hide");

	}
}



