<html>
    <link href="assets/css/moltran.min.css" rel="stylesheet" type="text/css">    
<body>
<?php include_once 'header.html'; ?>
<div class="row">
</div>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Equipamentos</h3></div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Nome</th>
                                        <th>Local</th>                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>MAQLAB12</td>
                                        <td>Lab 4</td>
                                        <td style=" width: 50 "><button class="btn btn-info btn-rounded waves-effect waves-light m-b-5" id="btnEditar">Editar</button></td>
                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td>MAQLAB12</td>
                                        <td>Lab 3</td>
                                        <td style=" width: 50 "><button class="btn btn-info btn-rounded waves-effect waves-light m-b-5" id="btnEditar">Editar</button></td>
                                    </tr> 
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
        $.ajax({
            url: "../backEnd/buscaEquipamentos.php", method: "get", dataType: "json", success : function(data) {
                var html = "";

                // percorre o data
                for($i=0; $i < data.length; $i++){
                    html += " 	<tr>  " +
                                    "   <td>  " + data[$i].id + " </td> " +
                                    "   <td>  " + data[$i].nome + " </td> " +                                                                      
                                    "   <td>  " + data[$i].local_nome + " </td> " +  LEMBRAR DE BUSCAR O NOME DO LOCAL NO SELECT                                                                    
                                    "   </tr> ";
                    $('tbody').html(html);																			
                }
            }
        });
    });
</script>
</html>
    