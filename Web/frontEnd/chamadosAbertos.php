<html>
    <meta charset="utf-8">
    <link href="assets/css/moltran.min.css" rel="stylesheet" type="text/css">    
<body>
    <?php include_once 'header.html'; ?>    
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Chamados Abertos</h3></div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="jumbotron">
                                    <tr>
                                        <th>ID</th>
                                        <th>Descrição</th>
                                        <th>Data Abertura</th>                                        
                                        <th>Usuário</th>                                        
                                        <th>Local</th>
                                        <th>Equipamento</th>
                                        <th>Fechar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <?php include_once 'footer.html'; ?>
</body>
    <script src="assets/js/moltran.min.js"></script>
    <script src="assets/js/util.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            carregaLista();
        });

        function carregaLista(){
            $.ajax({
                url: "../backEnd/buscaChamados.php?tipo=A", method: "get", dataType: "json", success: function(data) {                
                    var html = "";

                    for($i=0; $i < data.length; $i++){
                            html += "<tr>  " +
                                    "<td data-id='"+data[$i].id+"'>" + data[$i].id + " </td> " +
                                    "<td>" + data[$i].descricao + " </td> " +
                                    "<td>" + data[$i].data_inicio + " </td> " +
                                    "<td>" + data[$i].nome_usuario + " </td> " +
                                    "<td>" + data[$i].equipamento_local + " </td> " +
                                    "<td>" + data[$i].equipamento_descricao + " </td> " +
                                    "<td class='text-justify'>"+
                                        "<button data-toggle='tooltip' title='Realizado' class='btn btn-icon waves-effect waves-light btn-success m-b-2 realizar'><i class='fa fa-thumbs-o-up'></i></button> "+
                                        "<button data-toggle='tooltip' title='Cancelado' class='btn btn-icon waves-effect waves-light btn-danger m-b-2 cancelar'><i class='fa fa-remove'></i></button></td>"+
                                    "</tr> ";     
                     
                    }
                    // adicionar o código nos dois botões
                    $(".realizar").bind("click", Realizar);
                    $(".cancelar").bind("click", Cancelar);
                    $("tbody").html(html);
                }
            });
        }
        
        // evento do click do botão realizar
        function Realizar(){
            // pega a tr que foi clicada (linha)
			var par = $(this).closest('tr');    
			// pega o id da linha clicada (utilizando data-)
			var id = par.find('td[data-id]').data('id');
            if(confirm("Deseja realmente realizar o chamado " + id + "?")){
                alert(fecharChamado(id,'R')); // fecharChamado retorna o texto se deu certo ou não.
            }
        }
        
        // evento do click do botão cancelar
        function Cancelar(){
            // pega a tr que foi clicada (linha)
			var par = $(this).closest('tr');    
			// pega o id da linha clicada (utilizando data-)
			var id = par.find('td[data-id]').data('id');
            if(confirm("Deseja realmente cancelar o chamado " + id + "?")){
                alert(fecharChamado(id,'C')); // fecharChamado retorna o texto se deu certo ou não.
            }
        }
    </script>
</html>
    