<?php

require 'db.php';

if (isset($_GET['id'])){
	$id = $_GET['id'];
}
else{
	$id = '';
}

if ( empty($id) ){
	$query = $database->select('funcionarios',['id','nome','rg','cpf','email','senha']);
}
else{
	$query = $database->select('funcionarios',['id','nome','rg','cpf','email','senha'],['id'=>$id]);
}

$query = json_encode($query);

echo $query;