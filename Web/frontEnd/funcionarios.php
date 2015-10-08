<!DOCTYPE html>
<html>    
    <link href="assets/css/moltran.min.css" rel="stylesheet" type="text/css">
	<body>
        <?php include_once 'header.html'; ?>
		<div class='col-md-12'>			        
            <div class="col-md-12">
                <input type=hidden id="id" />
                <br /><label>Nome</label>
                <input type="text" class="form-control" id="nome" placeholder="Digite seu nome..." maxlength="100"/>
                <br /><label>Registro Geral (RG)</label>
                <input type="text" class="form-control" id="rg" placeholder="EX: 12.345.678-9" onkeydown="formataRG(this,event);" maxlength="12"/>                
                <br /><label>Período de trabalho</label>
                <select class="select2-dropdown--below form-control" id="periodo">
                    <option value="M">Manhã</option>
                    <option value="T">Tarde</option>
                    <option value="N">Noite</option>
                </select>
                <br /><label>Email</label>
                <input type="text" class="form-control" id="email" placeholder="Digite seu email..." maxlength="50"/>			
                <br /><label>Senha</label>
                <input type="password" class="form-control" id="senha1" placeholder="Fique atento aos pilares, escolha sua senha..." maxlength="50"/>								                
                <br /><label>Confirmar senha</label>
                <input type="password" class="form-control" id="senha2" placeholder="Repita sua senha..." maxlength="50"/>								
                <br />
                <button id="btnSalvar" class="btn btn-success btn-rounded waves-effect waves-light m-b-5">Salvar</button>
                <a id="btnListar" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" href="listaFuncionarios.php">Listar</a>
                <a id="btnLimpar" class="btn btn-warning btn-rounded waves-effect waves-light m-b-5">Limpar</a>
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
                $rg = $("#rg").val();                
                $periodo = $("#periodo").val();
                $email = $("#email").val();
                $senha1 = $("#senha1").val();
                $senha2 = $("#senha2").val();
                // atribui para o array, para enviar no post
                $dados = { id : $id , nome : $nome , rg : $rg , periodo : $periodo , email : $email , senha1 : $senha1 , senha2 : $senha2 };
                // faz o envio dos dados para o php
                $.ajax({
                    url: "../backEnd/CadFuncionarios.php", 
                    type: "POST", 
                    data: $dados,
                    success : function(data) { 
                        alert(data);            
                    }
                });
            });                          
            $("#btnLimpar").click(function(r){
                //limpa os campos
                $("#id").val('');
                $("#nome").val('');
                $("#rg").val('');                
                $("#periodo").val('M');
                $("#email").val('');
                $("#senha1").val('');
                $("#senha2").val('');
            });
        });
    </script>    
</html>