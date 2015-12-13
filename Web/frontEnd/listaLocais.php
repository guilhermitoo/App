<html>
    <link href="assets/css/moltran.min.css" rel="stylesheet" type="text/css">    
<body>
<?php include_once 'header.phtml'; ?>
<div class="row">
</div>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Locais</h3></div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div style="overflow: auto; height: 480px">
                            <table class="table">
                                <thead class="jumbotron">
                                    <tr>
                                        <th>ID</th>
                                        <th>Nome</th>                                                                                
                                        <th></th>                                                                                
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
<?php include 'footer.html'; ?>
</body>
<script src="assets/js/moltran.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        // ao iniciar a tela, executa a função carrega lista
        carregaLista();
        
        function carregaLista(){
            $.ajax({
                url: "../backEnd/buscaLocais.php", method: 'get', dataType: 'json', success : function(data) {
                    var html = "";

                    // percorre o data
                    for($i=0; $i < data.length; $i++){
                        html += "<tr>" +
                                    "<td data-id='"+data[$i].id+"'>" + data[$i].id + "</td>" +
                                    "<td>" + data[$i].nome + " </td>" +
                                    "<td style='width: 50'><button class='btn btn-danger btn-xs waves-effect waves-light remover'>Excluir</button></td>" +
                                "</tr>";
                        $('tbody').html(html);		
                        $(".remover").bind("click", Excluir);
                    }
                }
            });
        }
        
        function Excluir(){            
            // pega a tr que foi clicada (linha)
			var par = $(this).closest('tr');    
			// pega o id da linha clicada (utilizando data-)
			var id = par.find('td[data-id]').data('id');       
            // verifica se o usuário REALMENTE deseja excluir o equipamento
            if(confirm("Deseja realmente excluir o local número " + id + "?")){
                var tabela = 'locais';
                // passa o id que será excluido e a tabela
                var dados = { id : id , tabela : tabela };            
                $.ajax({
                    url:"../backEnd/excluir.php", method: "POST", data: dados, success : function(data) {
                        alert(data);
                        carregaLista();
                    }
                });
            }
        }
    });    
</script>
</html>
    