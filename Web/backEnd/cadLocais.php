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
        
        // se algum dos dados não foram informados, não deixa cadastrar e retorna mensagem
        if (empty($nome)){
            echo 'erro ao cadastrar: alguns campos estão em branco';
        }else{
            // se os campos estão preenchidos, prossegue
            if ($id == '')
            {
                $id = $database->insert('locais',[
                        'nome'=>$nome]);
                if ($id > 0){
                    echo 'Local cadastrado com sucesso.';
                }else{
                    echo 'erro ao cadastrar.';
                }
            }else{
                $database->update('locais',[
                        'nome'=>$nome],[
                        'id'=>$id]);
                echo 'Local alterado com sucesso.';
            }
        }
    }
}catch(Exception $e){
    echo 'Erro ao cadastrar. Mensagem: ' . $e;
}