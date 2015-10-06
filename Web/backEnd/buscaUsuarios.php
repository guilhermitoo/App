<?php

require 'db.php';

if (isset($_GET['id'])){
	$id = $_GET['id'];
}
else{
	$id = '';
}

if ( empty($id) ){
	$query = $database->select('usuarios',['id','nome','email','senha']);
}
else{
	$query = $database->select('usuarios',['id','nome','email','senha'],['id'=>$id]);
}

$query = json_encode($query);

echo $query;