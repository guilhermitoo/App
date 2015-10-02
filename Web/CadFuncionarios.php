<?php

// arquivo realizará o cadastro ou alteração de usuários a partir do método $_POST
try{
    if (isset($_POST)){        
        require "db.php";
        // recebe os dados
        if (isset($_POST['codigo'])){
            $id = $_POST['codigo'];
        }
        else{
            $id = '';
        }            
        if (isset($_POST['nome'])){
            $nome = $_POST['nome'];
        }
        if (isset($_POST['rg'])){
            $rg = $_POST['rg'];
        }
        if (isset($_POST['cpf'])){
            $cpf = $_POST['cpf'];
        }
        if (isset($_POST['email'])){
            $email = $_POST['email'];
        }
        if (isset($_POST['senha'])){
            $senha = $_POST['senha'];
        }                    
        
        if ($id == '')
        {
            $id = $database->insert('funcionarios',[
                    'nome'=>$nome,
                    'rg'=>$rg,
                    'cpf'=>$cpf,
                    'email'=>$email,
                    'senha'=>$senha]);
            echo 'Funcionário nº' . $id . ' cadastrado com sucesso.';
        }else{
            $database->update('funcionarios',[
                    'nome'=>$nome,
                    'rg'=>$rg,
                    'cpf'=>$cpf,
                    'email'=>$email,
                    'senha'=>$senha],[
                    'id'=>$id]);
            echo 'Funcionário nº' . $id . ' alterado com sucesso.';
        }
    }
}catch(Exception $e){
    echo 'Erro ao cadastrar. Mensagem: ' . $e;
}