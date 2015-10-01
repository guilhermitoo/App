<?php

require "db.php";

// arquivo realizará o cadastro de usuários a partir do método $_POST

if (isset($_POST)){
    // recebe os dados
    $nome = $_POST['nome'];
    $email = $_POST['email'];
    $senha = $_POST['senha'];
    
    
}