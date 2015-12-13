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
$app->get('/chamados_usuario/:id_usuario','getChamadosUser');
$app->get('/chamados/','getChamados');
$app->get('/chamados/:id','getChamado');
$app->get('/usuarios/','getUsuarios');
$app->get('/usuarios/:id','getUsuario');
$app->get('/usuario_login/:email','getLogin');
$app->post('/upd_usuario/','updateUsuario'); 
$app->post('/cad_usuario/','cadUsuario');
$app->post('/cad_chamado/','cadChamado');

$app->run();

function getLocais(){
    try{
        require 'db.php';
        $query = $database->select('locais',['id','nome'],['ORDER'=>'nome']);
        echo '{"locais":' . json_encode($query) . '}';
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
        echo '{"equipamentos":' . json_encode($query) . '}';
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }   
}

function getEquipamentos(){
    try{
        require 'db.php';
        $query = $database->select('equipamentos',['id','descricao','local_id']);
        echo '{"equipamentos":' . json_encode($query) . '}';
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
        echo '{"usuarios":' . json_encode($query) . '}';
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
        $app = \Slim\Slim::getInstance();
        $request = $app->request();
        $body = $request->getBody();
        $event = json_decode($body);  
        require 'db.php';
        $id = $database->update('usuarios',['senha'=>(string)$event->senha],['email'=>(string)$event->email]);
        echo '{"id":' . $id . '}';
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }       
}

function cadChamado(){
    try{        
        $app = \Slim\Slim::getInstance();
        $request = $app->request();
        $body = $request->getBody();
        $chamado = json_decode($body);       
        require 'db.php';
        $data = date('y-m-d');
        $id = $database->insert('chamados',
                                 ['descricao'=>(string)$chamado->descricao,
                                  'data_inicio'=>$data,
                                  'status'=>1,
                                  'usuario_id'=>(integer)$chamado->usuario_id,
                                  'equipamento_id'=>(integer)$chamado->equip_id]);
        echo '{"id":'. $id . '}';
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }       
}

function getChamadosUser($id_usuario){
    try{
        require 'db.php';
        $query = $database->select('chamados',
                                   ['[>]equipamentos'=>['equipamento_id'=>'id'],
                                    '[>]locais'=>['equipamentos.local_id'=>'id']
                                   ],
                                   ['chamados.id',
                                    'chamados.descricao',
                                    'chamados.data_inicio',
                                    'chamados.data_fim',
                                    'chamados.status',
                                    'chamados.usuario_id',
                                    'chamados.funcionario_id',
                                    'chamados.equipamento_id',
                                    'equipamentos.descricao(equipamento_descricao)',
                                    'locais.id(local_id)',
                                    'locais.nome(local_nome)'],
                                   ['usuario_id'=>$id_usuario,  
                                    'ORDER'=>'chamados.data_inicio DESC']);
        echo '{"chamados":' . json_encode($query) . '}';
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }
}

function getChamados(){
    try{
        require 'db.php';
        $query = $database->select('chamados',['id','descricao','data_inicio','data_fim','status','usuario_id','funcionario_id','equipamento_id'],['ORDER'=>'data_inicio']);
        echo '{"chamados":' . json_encode($query) . '}';
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