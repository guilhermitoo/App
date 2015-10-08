<?php

require 'db.php';

if (isset($_GET['id'])){
	$id = $_GET['id'];
}
else{
	$id = '';
}

// le a variavel tipo, se tipo for igual a 'A' então lista abertos, se for igual a 'F', lista fechados
if (isset($_GET['tipo'])){
    $tipo = $_GET['tipo'];
}else{
    $tipo = 'A';
}

if ( empty($id)){
    if ( $tipo == 'A' ) { // lista chamados abertos
        $filtro = 'chamados.status';        
    }else{ // lista chamados fechados
        $filtro = 'chamados.status[>]';
    }    
    $query = $database->select('chamados', //tabela do select
                              [ '[><]usuarios'=>['usuario_id'=>'id'],
                                '[><]funcionarios'=>['funcionario_id'=>'id'],
                                '[><]equipamentos'=>['equipamento_id'=>'id'],
                                '[><]locais'=>['equipamentos.local_id'=>'id']], //join
                              ['chamados.id',
                               'chamados.descricao',
                               'chamados.data_inicio',
                               'chamados.data_fim',
                               'chamados.status',
                               'usuarios.nome(nome_usuario)',
                               'funcionarios.nome(nome_funcionario)',
                               'equipamentos.descricao(equipamento_descricao)',
                               'locais.nome(equipamento_local)'], //campos                                   
                              [ $filtro=>1,
                                'ORDER'=>'chamados.data_inicio']);
}else{
	$query = $database->select('chamados', //tabela do select
                              [ '[><]usuarios'=>['usuario_id'=>'id'],
                                '[><]funcionarios'=>['funcionario_id'=>'id'],
                                '[><]equipamentos'=>['equipamento_id'=>'id'],
                                '[><]locais'=>['equipamentos.local_id'=>'id']], //join
                              ['chamados.id',
                               'chamados.descricao',
                               'chamados.data_inicio',
                               'chamados.data_fim',
                               'chamados.status',
                               'usuarios.nome(nome_usuario)',
                               'funcionarios.nome(nome_funcionario)',
                               'equipamentos.descricao(equipamento_descricao)',
                               'locais.nome(equipamento_local)'], //campos                                   
                              ['chamados.id'=>$id]);
}

$query = json_encode($query);

echo $query;