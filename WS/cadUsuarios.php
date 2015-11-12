<?php

// arquivo realizará o cadastro ou alteração de usuários a partir do método $_POST
try{
    if (isset($_POST)){        
        require "db.php";
        // recebe os dados
        if (isset($_POST['id'])){
            $id = $_POST['id'];
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
        
        // se algum dos dados não foram informados, não deixa cadastrar e retorna mensagem
        if (empty($nome) or empty($email) or empty($senha)){
            echo 'erro ao cadastrar: alguns campos estão em branco';
        }else{
            // se os campos estão preenchidos, prossegue
            if ($id == '')
            {
                $id = $database->insert('usuarios',[
                        'nome'=>$nome,                    
                        'email'=>$email,
                        'senha'=>$senha]);
                if ($id > 0){
                    echo 'Usuário nº' . $id . ' cadastrado com sucesso.';
                }else{
                    echo 'erro ao cadastrar';
                }
            }else{
                $database->update('usuarios',[
                        'nome'=>$nome,                    
                        'email'=>$email,
                        'senha'=>$senha],[
                        'id'=>$id]);
                echo 'Usuário nº' . $id . ' alterado com sucesso.';
            }
        }
    }
}catch(Exception $e){
    echo 'Erro ao cadastrar. Mensagem: ' . $e;
}