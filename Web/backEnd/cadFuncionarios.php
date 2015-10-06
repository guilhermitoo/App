<?php

// arquivo realizará o cadastro ou alteração de funcionários a partir do método $_POST
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
        
        // se algum dos dados não foram informados, não deixa cadastrar e retorna mensagem
        if (empty($nome) or empty($rg) or empty($cpf) or empty($email) or empty($senha)){
            echo 'erro ao cadastrar: alguns campos estão em branco';
        }else{
            // se os campos estão preenchidos, prossegue
            // se o id não foi atribuido faz o insert
            if ($id == '')
            {
                $id = $database->insert('funcionarios',[
                        'nome'=>$nome,
                        'rg'=>$rg,
                        'cpf'=>$cpf,
                        'email'=>$email,
                        'senha'=>$senha]);
                if ($id > 0){
                    echo 'Funcionário nº' . $id . ' cadastrado com sucesso.';
                }else{
                    echo 'erro ao cadastrar';
                }            
            }else{
                // se o id foi atribuido, então é update
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
    }
}catch(Exception $e){
    echo 'Erro ao cadastrar. Mensagem: ' . $e;
}