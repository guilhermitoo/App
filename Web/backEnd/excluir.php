<?php

require 'db.php';
try{
    if(isset($_POST)){
        if(isset($_POST['id'])){
            $id = $_POST['id'];
        }
        if(isset($_POST['tabela'])){
            $tabela = $_POST['tabela'];
        }                

        if(empty($id) or empty($tabela)){
            echo 'Erro ao excluir';
        }else{
            $qtd = $database->delete($tabela, ["and" => ["id" => $id]]);
            if ($qtd > 0){
                echo 'Item id ' . $id . ' excluido com sucesso';
            }else{
                echo 'Erro ao excluir, verifique suas dependências';
            }
        }
    }
}catch(Exception $e){
    echo 'Erro ao excluir, mensagem: ' . $e;
}