<?php

require 'db.php';

if (isset($_GET['id'])){
	$id = $_GET['id'];
}
else{
	$id = '';
}

if ( empty($id) ){
	$query = $database->select('locais',['id','nome']);
}
else{
	$query = $database->select('locais',['id','nome'],['id'=>$id]);
}

$query = json_encode($query);

echo $query;