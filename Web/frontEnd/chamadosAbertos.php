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
                                    "<td>" + data[$i].id + " </td> " +
                                    "<td>" + data[$i].descricao + " </td> " +
                                    "<td>" + data[$i].data_inicio + " </td> " +
                                    "<td>" + data[$i].nome_usuario + " </td> " +
                                    "<td>" + data[$i].equipamento_local + " </td> " +
                                    "<td>" + data[$i].equipamento_descricao + " </td> " +
                                    "<td class='text-justify'>"+
                                        "<button data-toggle='tooltip' title='Realizado' class='btn btn-icon waves-effect waves-light btn-success m-b-2'><i class='fa fa-thumbs-o-up'></i></button> "+
                                        "<button data-toggle='tooltip' title='Cancelado' class='btn btn-icon waves-effect waves-light btn-danger m-b-2'><i class='fa fa-remove'></i></button></td>"+
                                    "</tr> ";     
                        // adicionar o código nos dois botões
                    }
                    $("tbody").html(html);
                }
            });
        }
    </script>
</html>
    