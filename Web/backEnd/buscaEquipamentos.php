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
                                'locais.nome(local_nome)']);
}
else{
	$query = $database->select('equipamentos',
                               ['[>]locais'=>['local_id'=>'id']],
                               ['equipamentos.id',
                                'equipamentos.descricao',
                                'locais.nome(local_nome)'],
                               ['equipamentos.id'=>$id]);
}

$query = json_encode($query);

echo $query;