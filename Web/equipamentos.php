<!DOCTYPE html>
<html>    
    <link href="assets/css/moltran.min.css" rel="stylesheet" type="text/css">
	<body>
        <?php include_once 'header.html'; ?>
		<div class='col-md-12'>			        
            <div class="col-md-12">
                <input type=hidden id="codigo" />
                <br /><label>Local</label>
                <input type="text" class="form-control" id="local" placeholder="Digite o local do erro encontrado"/>
                <br /><label>Descrição</label>
                <textarea class="form-control" rows="5" id="descricao" placeholder="Se tiver alguma observação, digite aqui..."></textarea>
               
                <button id="btnSalvar" class="btn btn-success btn-rounded waves-effect waves-light m-b-5">Salvar</button>
                <a id="btnListar" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" href="listaFuncionarios.php">Listar</a>
            </div>										            
		</div>
        <?php include_once 'footer.html'; ?>
	</body>
	<script src="assets/js/mascara.js"></script>
	<script src="assets/js/moltran.min.js"></script>
    <script type="text/javascript">
        /*
        
        $(document).ready(function(){
            $("#btnSalvar").click(function(r){
                // pega os dados dos campos
                $id = $("#codigo").val();
                $nome = $("#nome").val();
                $rg = $("#rg").val();
                $cpf = $("#cpf").val();
                $email = $("#email").val();
                $senha = $("#senha").val();                                
                // atribui para o array, para enviar no post
                $dados = { id : $id , nome : $nome , rg : $rg , cpf : $cpf , email : $email , senha : $senha };
                // faz o envio dos dados para o php
                $.ajax({
                    url: "CadFuncionarios.php", 
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
                $("#codigo").val('');
                $("#nome").val('');
                $("#rg").val('');
                $("#cpf").val('');
                $("#email").val('');
                $("#senha").val('');
            }
        });
        
        */ 
    </script>    
</html>