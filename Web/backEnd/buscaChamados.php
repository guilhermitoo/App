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
                              [ '[>]usuarios'=>['usuario_id'=>'id'],
                                '[>]funcionarios'=>['funcionario_id'=>'id'],
                                '[>]equipamentos'=>['equipamento_id'=>'id'],
                                '[>]locais'=>['equipamentos.local_id'=>'id']], //join
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
                              [ '[>]usuarios'=>['usuario_id'=>'id'],
                                '[>]funcionarios'=>['funcionario_id'=>'id'],
                                '[>]equipamentos'=>['equipamento_id'=>'id'],
                                '[>]locais'=>['equipamentos.local_id'=>'id']], //join
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

//select 	ch.id,
//        ch.descricao,
//        DATE_FORMAT(ch.data_inicio, '%d/%m/%Y') as data_inicio ,
//        DATE_FORMAT(ch.data_fim, '%d/%m/%Y') as data_fim ,
//        ch.status,
//        us.nome as nome_usuario,
//        fu.nome as nome_funcionario,
//        eq.descricao as equipamento_descricao,
//        lo.nome as equipamento_local        
//from chamados ch
//left join usuarios us on (ch.usuario_id = us.id)
//left join funcionarios fu on (ch.funcionario_id = fu.id)
//left join equipamentos eq on (ch.equipamento_id = eq.id)
//left join locais lo on (eq.local_id = lo.id)
//where ch.id = 