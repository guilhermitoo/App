<?php

if(isset($_GET)){
    if (isset($_GET['email'])){
        $email = $_GET['email'];
    }
    else{
        $email = '';
    }   
    if (isset($_GET['senha'])){
        $senha = $_GET['senha'];
    }
    else{
        $senha = '';
    }
    
    // verifica se os valores e estão atribuidos
    if(!(empty($email) || empty($senha)))
    {      
        require_once 'db.php';
        // busca no banco MySQL os dados do usuário logado
        $query = $database->select('usuarios',['nome','email','senha'],['AND' => ['email'=>$email,'senha'=>$senha]]);			
        // conta quantos registros retornaram
        $conta = sizeof($query);
        //pega a linha 0 da busca
        $qry = $query["0"];
        if($conta>=1)
        {
            if ( ($email == $qry["email"]) && ($senha == $qry["senha"]) ){
                $qry = json_enconde($qry);
                return $qry;                
            }
            else{
                return false;         
            }
        }
        else
        {            
            return false;
        }
    }else{
        return false;
    }
}

?>