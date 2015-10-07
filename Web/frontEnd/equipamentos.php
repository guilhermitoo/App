<!DOCTYPE html>
<html>    
    <link href="assets/css/moltran.min.css" rel="stylesheet" type="text/css">
	<body>
        <?php include_once 'header.html'; ?>
		<div class='col-md-12'>			        
            <div class="col-md-12">
                <input type=hidden id="id" />
                <br /><label>Descrição</label>
                <input type="text" class="form-control" id="descricao" placeholder="Digite a descrição do equipamento"/>
                <br /><label>Local</label>
                <select data-toggle="tooltip" title="selecione o local do equipamento" class="select2-dropdown--below form-control" id="cbbLocais"><option></select>
               <br />
                <button id="btnSalvar" class="btn btn-success btn-rounded waves-effect waves-light m-b-5">Salvar</button>
                <a id="btnListar" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" href="listaEquipamentos.php">Listar</a>
            </div>										            
		</div>
        <?php include_once 'footer.html'; ?>
	</body>
	<script src="assets/js/mascara.js"></script>
	<script src="assets/js/moltran.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function(){
            
            // carrega o ComboBox dos locais
            $.ajax({
                url: "../backEnd/buscaLocais.php", method: 'get', dataType: 'json', success : function(data) {
                    var html = "";

                    // percorre o data
                    for($i=0; $i < data.length; $i++){
                        html += "<option value='"+data[$i].id+"'>"+data[$i].nome+"</option> <br />";
                    }
                    // preenche o cbblocais com as opções
                    $('#cbbLocais').html(html);
                }
            });
            
            $("#btnSalvar").click(function(r){
                // pega os dados dos campos
                $id = $("#id").val();
                $descricao = $("#descricao").val();
                $local_id = $("#cbbLocais").val();                                

                // atribui para o array, para enviar no post
                $dados = { id : $id , descricao : $descricao , local_id : $local_id };
                // faz o envio dos dados para o php
                $.ajax({
                    url: "../backEnd/CadEquipamentos.php", 
                    type: "POST", 
                    data: $dados,
                    success : function(data) { 
                        alert(data);
                        Limpar();
                    }
                });
            });
            
            function Limpar(){
                //limpa os campos
                $("#id").val('');
                $("#descricao").val('');
            }
        });
 
    </script>    
</html>