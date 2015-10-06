<?php

require 'db.php';

if (isset($_GET['id'])){
	$id = $_GET['id'];
}
else{
	$id = '';
}

if ( empty($id) ){
	$query = $database->select('equipamentos',['id','descricao','local_id']);
}
else{
	$query = $database->select('equipamentos',['id','descricao','local_id'],['id'=>$id]);
}

$query = json_encode($query);

echo $query;