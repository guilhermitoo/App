<?php
// função que verifica se tem algum usuário logado
function Logado(){
    session_start();

    if(isset($_SESSION['email']))
        $email = $_SESSION['email'];
    if(isset($_SESSION['senha']))
        $senha = $_SESSION['senha'];

    if(!(empty($email) || empty($senha)))
    {      
        require_once 'db.php';

        $query = $database->select('funcionarios',['email','senha'],['AND' => ['email'=>$email,'senha'=>$senha]]);			
        $conta = sizeof($query);
        //pega a linha 0 da busca
        $qry = $query["0"];

        if($conta>=1)
        {
            if ( ($email == $qry["email"]) && ($senha == $qry["senha"]) ){
                return true;                
                exit;
            }
            else{
                return false;
                exit;
            }        
        }
        else
        {
            unset($_SESSION['nome']);
            unset($_SESSION['email']);
            unset($_SESSION['senha']);
            return false;
            exit;
        }
    }
    else
    {
        return false;
        exit;
    }   
}

// função que valida se o login está correto e adiciona na session
function validaLogin($email,$senha){
    // conexão postgreSQL
    require_once 'db.php';

    // Aqui veificamos se o usuario e senha informados na função existem e estão corretos
    $query = $database->select('funcionarios',['id','nome','email','senha'],['AND' => ['email'=>utf8_encode($email),'senha'=>utf8_encode($senha)]]);			
    $qregistro = sizeof($query);
    $reg = $query["0"];
    if( $qregistro < 1 ){
       return false;
    } 
    else 
    {
        // Apos verificado ele grava o usuario e senha no vetor $_session[]
        session_start();
        $_SESSION['id']= $reg["id"];
        $_SESSION['nome']= $reg["nome"];
        $_SESSION['email']=$email;
        $_SESSION['senha']=$senha;
        return true;
    }
}

//function usuario() { // função que ve o usuário logado e retorna alguns dados do mesmo
//    session_start();
//    // verifica a sessão aberta
//    if(isset($_SESSION['email']))
//        $email = $_SESSION['email'];
//    if (! empty($email)) {
//        require_once 'db.php';
//        $query = $database->select('funcionarios',['id','nome','rg'],['AND' => ['email'=>utf8_encode($email)]]);        
//        return $query;
//    }else{
//        return '';
//    }
//}

?>