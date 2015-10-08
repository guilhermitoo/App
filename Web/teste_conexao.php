<?php

require "/backEnd/db.php";

if (isset($database)){
    echo "Banco conectado.";
}
else{
    echo "Falha ao conectar.";
}