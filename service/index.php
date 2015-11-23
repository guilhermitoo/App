<?php

require '\vendor\autoload.php';
 
$app = new \Slim\Slim();

$app->response()->header('Content-Type', 'application/json;charset=utf-8');
$app->get('/',function () { echo "Olá, este serviço faz parte do aplicativo Appriori!"; });

$app->get('/locais/','getLocais');  
$app->get('/locais/:id', 'getLocal');
$app->get('/equipamentos_local/:local_id','getEquipamentosLocal');
$app->get('/equipamentos/','getEquipamentos');
$app->get('/equipamentos/:id','getEquipamento');
$app->get('/chamados/','getChamados');
$app->get('/chamados/:id','getChamado');
$app->get('/usuarios/','getUsuarios');
$app->get('/usuarios/:id','getUsuario');
$app->get('/usuario_login/:email','getLogin');
$app->put('/upd_usuario/','updateUsuario'); 
$app->post('/cad_usuario/','cadUsuario');
$app->post('/cad_chamado/','cadChamado');

$app->run();

function getLocais(){
    try{
        require 'db.php';
        $query = $database->select('locais',['id','nome'],['ORDER'=>'nome']);
        echo json_encode($query);
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }
}

function getLocal($id){
    try{
        require 'db.php';
        $query = $database->select('locais',['id','nome'],['id'=>$id]);
        if (sizeof($query)>0){
            $query = $query["0"];
            echo json_encode($query);
        }else{
            echo '{}';
        }
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }
}

function getEquipamentosLocal($local_id){
    try{
        require 'db.php';
        $query = $database->select('equipamentos',['id','descricao','local_id'],['local_id'=>$local_id]);
        echo json_encode($query);
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }   
}

function getEquipamentos(){
    try{
        require 'db.php';
        $query = $database->select('equipamentos',['id','descricao','local_id']);
        echo json_encode($query);
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }   
}

function getEquipamento($id){
    try{
        require 'db.php';
        $query = $database->select('equipamentos',['id','descricao','local_id'],['id'=>$id]);
        if (sizeof($query)>0){
            $query = $query["0"];
            echo json_encode($query);
        }else{
            echo '{}';
        }
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }
}

function getUsuarios(){
    try{
        require 'db.php';
        $query = $database->select('usuarios',['id','nome','email','senha'],['ORDER'=>'nome']);
        echo json_encode($query);
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }       
}

function getUsuario($id){
    try{
        require 'db.php';
        $query = $database->select('usuarios',['id','nome','email','senha'],['id'=>$id]);
        if (sizeof($query)>0){
            $query = $query["0"];
            echo json_encode($query);
        }else{
            echo '{}';
        }
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }       
}

function getLogin($email){
    try{
        require 'db.php';
        $query = $database->select('usuarios',['id','nome','email','senha'],['email'=>$email]);
        if (sizeof($query)>0){
            $query = $query["0"];        
            echo json_encode($query);
        }else{
            echo '{}';
        }
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }       
}

function cadUsuario(){
    try{
        $app = \Slim\Slim::getInstance();
        $request = $app->request();
        $body = $request->getBody();
        $event = json_decode($body);
//        $u = json_decode($request->getBody());  
        require 'db.php';
        $id = $database->insert('usuarios',
                                 ['nome'=>(string)$event->nome,
                                  'email'=>(string)$event->email,
                                  'senha'=>(string)$event->senha]);
        echo '{"id":'.$id.'}';
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }       
}

function updateUsuario(){
    try{
        $request = \Slim\Slim::getInstance()->request();
        $u = json_decode($request->getBody());  
        require 'db.php';
        $id = $database->update('usuarios',
                                 ['nome'=>$u->nome,
                                  'email'=>$u->email,
                                  'senha'=>$u->senha],
                                 ['email'=>$u->email]);
        echo '{"":'+$id+'}';
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }       
}

function cadChamado(){
    try{        
        $request = \Slim\Slim::getInstance()->request();
        $chamado = json_decode($request->getBody());        
        require 'db.php';
        $data = date('y-m-d');
        $id = $database->insert('chamados',
                                 ['descricao'=>$chamado->descricao,
                                  'data_inicio'=>$chamado->data,
                                  'status'=>1,
                                  'usuario_id'=>$chamado->usuario_id,
                                  'equipamento_id'=>$chamado->equip_id]);
        echo '{"id":'+$id+'}';
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }       
}

function getChamados(){
    try{
        require 'db.php';
        $query = $database->select('chamados',['id','descricao','data_inicio','data_fim','status','usuario_id','funcionario_id','equipamento_id'],['ORDER'=>'data_inicio']);
        echo json_encode($query);
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }
}

function getChamado($id){
    try{
        require 'db.php';
        $query = $database->select('chamados',['id','descricao','data_inicio','data_fim','status','usuario_id','funcionario_id','equipamento_id'],['id'=>$id]);
        if (sizeof($query)>0){
            $query = $query["0"];
            echo json_encode($query);
        }else{
            echo '{}';
        }
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }
}
 

?>