<html>
    <link href="assets/css/moltran.min.css" rel="stylesheet" type="text/css">    
    <meta charset="utf-8">
<body>
<?php include_once 'header.phtml'; ?>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Chamados Fechados</h3></div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div style="overflow: auto; height: 480px">
                            <table class="table" >
                                <thead class="jumbotron">
                                    <tr>
                                        <th>ID</th>
                                        <th>Descrição</th>
                                        <th>Data Abertura</th>
                                        <th>Data Conclusão</th>
                                        <th>Usuário</th>
                                        <th>Funcionário</th>
                                        <th>Local</th>
                                        <th>Equipamento</th>
                                        <th>Status</th>
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
    
        function carregaLista(){
            $.ajax({
                url: "../backEnd/buscaChamados.php?tipo=F", method: "get", dataType: "json", success: function(data) {                
                    var html = "";

                    for($i=0; $i < data.length; $i++){
                            html += "<tr>  " +
                                        "<td>" + data[$i].id + " </td> " +
                                        "<td>" + data[$i].descricao + " </td> " +
                                        "<td width='130p'>" + formataData(data[$i].data_inicio) + " </td> " +
                                        "<td width='130p'>" + formataData(data[$i].data_fim) + " </td> " +
                                        "<td>" + data[$i].nome_usuario + " </td> " +
                                        "<td>" + data[$i].nome_funcionario + " </td> " +
                                        "<td>" + data[$i].equipamento_local + " </td> " +
                                        "<td>" + data[$i].equipamento_descricao + " </td> " +
                                        "<td>" + getStatus(data[$i].status) + "</td> " +
                                    "</tr> ";                    																		
                    }
                    $("tbody").html(html);
                }
            });
        }
    });
</script>
</html>
    
