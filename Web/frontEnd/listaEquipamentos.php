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
                                </tbody>
<!--<td style=" width: 50 "><button class="btn btn-info btn-rounded waves-effect waves-light m-b-5" id="btnEditar">Editar</button></td>-->
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
                                    "   <td>" + data[$i].id + " </td> " +
                                    "   <td>" + data[$i].descricao + " </td> " +
                                    "   <td>" + data[$i].local_nome + " </td> " +
                            "   </tr> ";                    																		
                }
                $('tbody').html(html);	
            }
        });
    });
</script>
</html>
    