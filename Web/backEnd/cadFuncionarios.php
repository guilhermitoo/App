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
        if (isset($_POST['periodo'])){
            $periodo = $_POST['periodo'];
        }
        if (isset($_POST['email'])){
            $email = $_POST['email'];
        }
        if (isset($_POST['senha1'])){
            $senha1 = md5($_POST['senha1']);
        }                    
        if (isset($_POST['senha2'])){
            $senha2 = md5($_POST['senha2']);
        }                    
        
        // se algum dos dados não foram informados, não deixa cadastrar e retorna mensagem
        if (empty($nome) or empty($rg) or empty($cpf) or empty($periodo) or empty($email) or empty($senha1) or empty($senha2)){
            echo 'erro ao cadastrar: alguns campos estão em branco';
        }else{
            // compara senha 1 e senha 2, para verificar se estão iguais
            if (! ($senha1 === $senha2) ){
                echo 'erro ao cadastrar: as senhas não conferem';
                exit;
            }
            // se os campos estão preenchidos, prossegue
            // se o id não foi atribuido faz o insert
            if ($id == '')
            {
                $id = $database->insert('funcionarios',[
                        'nome'=>$nome,
                        'rg'=>$rg,
                        'cpf'=>$cpf,
                        'periodo'=>$periodo,
                        'email'=>$email,
                        'senha'=>$senha1]);
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
                        'periodo'=>$periodo,
                        'email'=>$email,
                        'senha'=>$senha1],[
                        'id'=>$id]);
                echo 'Funcionário nº' . $id . ' alterado com sucesso.';
            }
        }
    }
}catch(Exception $e){
    echo 'Erro ao cadastrar. Mensagem: ' . $e;
}