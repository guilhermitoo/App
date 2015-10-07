<?php

require 'db.php';

if (isset($_GET['id'])){
	$id = $_GET['id'];
}
else{
	$id = '';
}
if ( empty($id) ){
	$query = $database->select('equipamentos',
                               ['[><]locais'=>['local_id'=>'id']],
                               ['equipamentos.id',
                                'equipamentos.descricao',
                                'locais.nome(local_nome)'],
                               ['ORDER'=>'equipamentos.descricao']);
}
else{
	$query = $database->select('equipamentos',
                               ['[>]locais'=>['local_id'=>'id']],
                               ['equipamentos.id',
                                'equipamentos.descricao',
                                'locais.nome(local_nome)'],
                               ['equipamentos.id'=>$id,
                                'ORDER'=>'equipamentos.descricao']);
}

$query = json_encode($query);

echo $query;