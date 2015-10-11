<?php

require 'db.php';

if(isset($_POST)){
    // pega a informação do parâmetro ID
    if(isset($_POST['id'])){
        $id = $_POST['id'];
    }else{
        $id = '';
    }
    // pega a informação do TIPO
    if(isset($_POST['tipo'])){
        $tipo = $_POST['tipo'];
    }else{
        $tipo = '';
    }
    // verifica se os campos foram informados
    if (empty($id) or empty($tipo)) {
        echo 'erro ao fechar o chamado: poucos parâmetros informados';
        exit;
    }else{
        if($tipo = 'R'){ // R de realizado
            $status = 2;
            $status_texto = 'realizado';
        }else if($tipo = 'C'){ // C de cancelado
            $status = 3;
            $status_texto = 'cancelado';
        }else{
            echo 'erro ao fechar o chamado: parâmetro TIPO incorreto. Deve ser informado R(realizado) ou C(cancelado)';
            exit;
        }
        // inicia a sessão
//        session_start();
        // pega o usuário logado
        $funcionario_id = 1; //$_SESSION['funcionario_id'];
        // pega a data atual (no mysql, a data deve ser inserida em ano-mes-dia
        $data = date('y-m-d');
        // agora faz o update no banco para modificar o status do chamado, 
        // atribuir a data de fechamento e atribuir o usuário que realizou o fechamento do chamado
        $qtd = $database->update('chamados',[
                                'data_fim'=>$data,
                                'status'=>$status,
                                'funcionario_id'=>$funcionario_id],[
                                'id'=>$id]);
        // verifica se realmente alterou
        if($qtd > 0){
            echo 'Chamado nº' . $id . ' ' . $status_texto . ' com sucesso.';
        }else{
            echo 'Erro ao fechar o chamado';
        }
    }
}else{
    echo 'requisição sem parâmetros';
}