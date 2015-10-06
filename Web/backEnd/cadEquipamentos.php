<?php

// arquivo realizará o cadastro ou alteração de usuários a partir do método $_POST
try{
    if (isset($_POST)){        
        require "db.php";
        // recebe os dados do POST
        if (isset($_POST['id'])){
            $id = $_POST['id'];
        }
        else{
            $id = '';
        }            
        if (isset($_POST['descricao'])){
            $descricao = $_POST['descricao'];
        }
        if (isset($_POST['local_id'])){
            $local_id = $_POST['local_id'];
        }
        
        // se algum dos dados não foi informado, não deixa cadastrar e retorna mensagem
        if (empty($descricao) or empty($local_id)){
            echo 'erro ao cadastrar: alguns campos estão em branco';
        }else{
            // se os campos estão preenchidos, prossegue
            if ($id == '')
            {
                $id = $database->insert('equipamentos',[
                        'descricao'=>$descricao,
                        'local_id'=>$local_id]);
                if ($id > 0){
                    echo 'Equipamento cadastrado com sucesso.';
                }else{
                    echo 'erro ao cadastrar.';
                }
            }else{
                $database->update('equipamentos',[
                        'descricao'=>$nome,
                        'local_id'=>$local_id],[
                        'id'=>$id]);
                echo 'Equipamento alterado com sucesso.';
            }
        }
    }
}catch(Exception $e){
    echo 'Erro ao cadastrar. Mensagem: ' . $e;
}