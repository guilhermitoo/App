<!DOCTYPE html>
<html>    
    <link href="assets/css/moltran.min.css" rel="stylesheet" type="text/css">
	<body>
        <?php include_once 'header.html'; ?>
		<div class='col-md-12'>			        
            <div class="col-md-12">
                <input type=hidden id="codigo" />
                <br /><label>Local</label>
                <input type="text" class="form-control" id="nome" placeholder="Digite o nome do local"/>
                <br />
                <button id="btnSalvar" class="btn btn-success btn-rounded waves-effect waves-light m-b-5">Salvar</button>
                <a id="btnListar" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" href="listaLocais.php">Listar</a>
            </div>										            
		</div>
        <?php include_once 'footer.html'; ?>
	</body>
	<script src="assets/js/mascara.js"></script>
	<script src="assets/js/moltran.min.js"></script>
    <script type="text/javascript">        
        $(document).ready(function(){
            $("#btnSalvar").click(function(r){
                // pega os dados dos campos
                $id = $("#id").val();
                $nome = $("#nome").val();                             
                // atribui para o array, para enviar no post
                $dados = { id : $id , nome : $nome };
                // faz o envio dos dados para o php
                $.ajax({
                    url: "../backEnd/CadLocais.php", 
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