<?php

require 'db.php';

if (isset($_GET['id'])){
	$id = $_GET['id'];
}
else{
	$id = '';
}

if ( empty($id) ){
	$query = $database->select('funcionarios',['id','nome','rg','periodo','email','senha'],['ORDER'=>'nome']);
}
else{
	$query = $database->select('funcionarios',['id','nome','rg','periodo','email','senha'],['id'=>$id]);
}

$query = json_encode($query);

echo $query;