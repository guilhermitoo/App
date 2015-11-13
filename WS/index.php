<?php
 
require '\vendor\autoload.php';
 
$app = new \Slim\Slim();
 
$app->get('/',function () { echo "Hello, WORLD"; });
$app->get('/locais', 'getLocais');
$app->get('/locais/:id', 'getLocal');
$app->get('/equipamentos/:local_id','getEquipamentos');
$app->get('/equipamentos/:id', 'getEquipamento');
$app->get('/usuarios/','getUsuarios');
$app->get('/usuarios/:id','getUsuario');
$app->post('/usuarios/:nome/:email/:senha','cadUsuario');
$app->put('/usuarios/:nome/:email/:senha','updateUsuario'); 
$app->post('/chamados/:desc/:equip_id/:usuario_id','cadChamado');
$app->get('/chamados','getChamados');
$app->get('/chamados/:id','getChamado');

$app->run();

require 'db.php';

function getLocais(){
    try{
        $query = $database->select('locais',['id','nome'],['ORDER'=>'nome']);
        echo json_encode($query);
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }    
}

function getLocal($id){
    try{
        $query = $database->select('locais',['id','nome'],['id'=>$id]);
        echo json_encode($query);
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }
}

function getEquipamentos(){
    try{
        $query = $database->select('equipamentos',['id','descricao','local_id'],['ORDER'=>'descricao']);
        echo json_encode($query);
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }   
}

function getEquipamento($id){
    try{
        $query = $database->select('equipamentos',['id','descricao','local_id'],['id'=>$id]);
        echo json_encode($query);
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }
}

function getUsuarios(){
    try{
        $query = $database->select('usuarios',['id','nome','email','senha'],['ORDER'=>'nome']);
        echo json_encode($query);
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }       
}

function getUsuario($id){
    try{
        $query = $database->select('usuarios',['id','nome','email','senha'],['id'=>$id]);
        echo json_encode($query);
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }       
}

function cadUsuario($nome,$email,$senha){
    try{
        $data = date('y-m-d');
        $qtd = $database->insert('usuarios',
                                 ['nome'=>$nome,
                                  'email'=>$email,
                                  'senha'=>$senha]);
        echo '{"qtd":'+$qtd+'}';
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }       
}

function updateUsuario($nome,$email,$senha){
    try{
        $qtd = $database->update('usuarios',['nome'=>$nome,'email'=>$email,'senha'=>md5($senha)],['email'=>$email]);
        echo '{"qtd":'+$qtd+'}';
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }       
}

function cadChamado($desc,$equip_id,$usuario_id){
    try{
        $data = date('y-m-d');
        $qtd = $database->insert('chamados',
                                 ['descricao'=>$descricao,
                                  'data_inicio'=>$data,
                                  'status'=>1,
                                  'usuario_id'=>$usuario_id,
                                  'equipamento_id'=>$equip_id]);
        echo '{"qtd":'+$qtd+'}';
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }       
}

function getChamados(){
    try{
        $query = $database->select('chamados',['id','descricao','data_inicio','data_fim','status','usuario_id','funcionario_id','equipamento_id'],['ORDER'=>'data_inicio']);
        echo json_encode($query);
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }
}

function getChamado($id){
    try{
        $query = $database->select('chamados',['id','descricao','data_inicio','data_fim','status','usuario_id','funcionario_id','equipamento_id'],['id'=>$id]);
        echo json_encode($query);
    }catch(PDOException $e){
        echo '{"error":{"text":'. $e->getMessage() .'}}';
    }
}
 
?>