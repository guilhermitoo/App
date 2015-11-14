<?php

// verifica se a conexão está aberta, se não estiver, abre a conexão
require "medoo.min.php";
    
if ( empty($database) and (!isset($database))){
    $database = new medoo();
}

?>