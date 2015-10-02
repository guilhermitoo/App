<?php 
    // verifica se o usuário está logado, se não, redireciona para a tela de login

?>
<!DOCTYPE html>
<html>
<!-- Mirrored from moltran.coderthemes.com/dark/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 28 Sep 2015 21:15:11 GMT -->

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
    <meta name="author" content="Coderthemes">
    <link rel="shortcut icon" href="assets/images/favicon_1.ico">
    <title>APPriori - Iremos pesquisar o que tem de errado</title>
    <link href="assets/css/moltran.min.css" rel="stylesheet" type="text/css">
</head>

<body class="fixed-left">
    <div id="wrapper">        
        <div class="topbar">
            <div class="topbar-left">
                <div class="text-center"><a href="index.html" class="logo"><img src="" class="img-responsive" /></a></div>
            </div>
            <div class="navbar navbar-default" role="navigation">
                <div class="container">
                    <div class="">
                        <form class="navbar-form pull-left" role="search">
                            <button type="submit" class="btn btn-search"><i class="fa fa-search"></i></button>
                        </form>
                        <ul class="nav navbar-nav navbar-right pull-right">                           
                            <li class="">
                                <a href="pags/login.html"><i class="md md-settings-power"></i> Sair</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="left side-menu">
            <div class="sidebar-inner">
                
                <div id="sidebar-menu">
                    <ul>
                        <li><a href="index.php" class="waves-effect waves-light"><i class="md md-home"></i><span>Home</span></a></li>
                        <li class="active-menu"><a type="text" class="waves-light active">Cadastros</a>
                                <li><a class="waves-effect waves-light" id="equipamentos">Equipamento</a></li>                         
                                <li><a class="waves-effect waves-light" id="funcionarios">Funcionário</a></li>
                                <li><a class="waves-effect waves-light" id="locais">Local</a></li>
                        </li>
                        <li class="active-menu"><a type="text" class="waves-light active">Listagem Chamados</a>
                                <li><a class="waves-effect waves-light" id="chamadosAbertos">Abertos</a></li>
                                <li><a class="waves-effect waves-light" id="chamadosFechados">Fechados</a></li>
                        </li>
                    </ul>
                        
                   
                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="content-page">
            <div class="content">
                <div class="container" id="conteudo">
                </div>
            </div>
            <footer class="footer text-right">2015 © Float Group.</footer>
        </div>
        
    </div>
    <script>
        var resizefunc = [];
    </script>
    <script src="assets/js/moltran.min.js"></script>
    <script type="text/javascript">        
        $(document).ready(function($) {
            // link para menu de cadastros de equipamentos, funcionários e locais
            $("#equipamentos").click(function(){
                $("#conteudo").load("equipamentos.html");
            });
            $("#funcionarios").click(function(){
                $("#conteudo").load("funcionarios.html");
            });     $       
            $("#locais").click(function(){
                $("#conteudo").load("locais.html");
            });            
            // carrega a lista de chamados
            $("#chamadosAbertos").click(function(){
                $("#conteudo").load("chamadosAbertos.html");
            });
            $("#chamadosFechados").click(function(){
                $("#conteudo").load("chamadosFechados.html");
            });            
            // abre a lista de chamados abertos sempre que for para home
            $("#chamadosAbertos").click();
        });
        
    </script>
</body>
<!-- Mirrored from moltran.coderthemes.com/dark/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 28 Sep 2015 21:15:54 GMT -->

</html>