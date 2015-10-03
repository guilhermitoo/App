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
        if (isset($_POST['email'])){
            $email = $_POST['email'];
        }
        if (isset($_POST['senha'])){
            $senha = $_POST['senha'];
        }                    
        
        if ($id == '')
        {
            $id = $database->insert('usuarios',[
                    'nome'=>$nome,                    
                    'email'=>$email,
                    'senha'=>$senha]);
            echo 'Usuário nº' . $id . ' cadastrado com sucesso.';
        }else{
            $database->update('usuarios',[
                    'nome'=>$nome,                    
                    'email'=>$email,
                    'senha'=>$senha],[
                    'id'=>$id]);
            echo 'Usuário nº' . $id . ' alterado com sucesso.';
        }
    }
}catch(Exception $e){
    echo 'Erro ao cadastrar. Mensagem: ' . $e;
}