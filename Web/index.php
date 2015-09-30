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
    <link href="assets/vendor/sweetalert/dist/sweetalert.css" rel="stylesheet" type="text/css">
    <link href="assets/css/moltran.min.css" rel="stylesheet" type="text/css">
    <!--[if lt IE 9]><script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script><![endif]-->
    <script src="assets/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
</head>

<body class="fixed-left">
    <div id="wrapper">
        <div class="topbar">
            <div class="topbar-left">
                <div class="text-center"><a href="index.html" class="logo"><img src="logo-helpp1.png" class="img-responsive" /></a></div>
            </div>
            <div class="navbar navbar-default" role="navigation">
                <div class="container">
                    <div class="">
                        <div class="pull-left">
                            <button class="button-menu-mobile open-left"><i class="fa fa-bars"></i></button> <span class="clearfix"></span></div>
                        <form class="navbar-form pull-left" role="search">
                            <div class="form-group">
                                <input type="text" class="form-control search-bar" placeholder="Buscar...">
                            </div>
                            <button type="submit" class="btn btn-search"><i class="fa fa-search"></i></button>
                        </form>
                        <ul class="nav navbar-nav navbar-right pull-right">
                            
                            <li class="hidden-xs"><a href="#" id="btn-fullscreen" class="waves-effect"><i class="md md-crop-free"></i></a></li>
                           
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle profile" data-toggle="dropdown" aria-expanded="true"><img src="assets/images/users/avatar-1.jpg" alt="user-img" class="img-circle"></a>
                                <ul class="dropdown-menu">
                                    <li><a href="javascript:void(0)"><i class="md md-face-unlock"></i> Conta</a></li>
                                    <li><a href="javascript:void(0)"><i class="md md-settings"></i> Configurações</a></li>
                                    <li><a href="javascript:void(0)"><i class="md md-lock"></i> Bloquear Tela</a></li>
                                    <li><a href="pags/login.html"><i class="md md-settings-power"></i> Sair</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="left side-menu">
            <div class="sidebar-inner slimscrollleft">
                
                <div id="sidebar-menu">
                    <ul>
                        <li><a href="index.html" class="waves-effect waves-light active"><i class="md md-home"></i><span>Home</span></a></li>
                       
                                <li class="has_sub"><a href="javascript:void(0);" class="waves-effect waves-light"><span>Cadastros</span> <span class="pull-right"><i class="md md-add"></i></span></a>
                                    <ul style="">
                                        <li><a href="pags/cad-user.html"><span>Usuários</span></a></li>
                                        <li><a href="javascript:void(0);"><span>Funcionários</span></a></li>
                                        <li><a href="javascript:void(0);"><span>Equipamentos</span></a></li>
                                        <li><a href="javascript:void(0);"><span>Chamados</span></a></li>
                                    </ul>
                                </li>
                                
                            </ul>
                        </li>
                        
                   
                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="content-page">
            <div class="content">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12">
                            <h4 class="pull-left page-title">Chamados em Aberto</h4>
                            <ol class="breadcrumb pull-right">
                                <li><a href="#">Outros Chamados</a></li>
                                
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Chamados em Aberto</h3></div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="table-responsive">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Descrição</th>
                                                            <th>Usuário</th>
                                                            <th>Equipamento</th>
                                                            <th>Local</th>
                                                            <th>Data</th>
                                                            <th>Fechar Chamado</th>
                                                           
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>1</td>
                                                            <td>Maquina sem DEV C++</td>
                                                            <td>Guilherme Dias</td>
                                                            <td>MAQLAB12</td>
                                                            <td>Lab.4</td>
                                                            <td>xx/xx/xxxx</td>
                                                            <td class="text-justify"><button class="btn btn-icon waves-effect waves-light btn-success m-b-2"><i class="fa fa-thumbs-o-up"></i></button> <button class="btn btn-icon waves-effect waves-light btn-danger m-b-2"><i class="fa fa-remove"></i></button></td>
                                                        </tr>
                                                        <tr>
                                                             <td>1</td>
                                                            <td>Sem Ar Condicionado</td>
                                                            <td>Thaysa Avanço</td>
                                                            <td>MAQLAB13</td>
                                                            <td>Lab.2</td>
                                                            <td>xx/xx/xxxx</td>
                                                            <td class="text-justify"><button class="btn btn-icon waves-effect waves-light btn-success m-b-2"><i class="fa fa-thumbs-o-up"></i></button> <button class="btn btn-icon waves-effect waves-light btn-danger m-b-2"><i class="fa fa-remove"></i></button></td>
                                                        </tr>
                                                        
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                </div>
            </div>
            <footer class="footer text-right">2015 © Float Group.</footer>
        </div>
        
    </div>
    <script>
        var resizefunc = [];
    </script>
    <script src="assets/js/moltran.min.js"></script>
    <script src="assets/vendor/moment/moment.js"></script>
    <script src="assets/vendor/waypoints/lib/jquery.waypoints.js"></script>
    <script src="assets/plugins/counterup/jquery.counterup.min.js"></script>
    <script src="assets/vendor/sweetalert/dist/sweetalert.min.js"></script>
    <script src="assets/plugins/flot-chart/jquery.flot.js"></script>
    <script src="assets/plugins/flot-chart/jquery.flot.time.js"></script>
    <script src="assets/plugins/flot-chart/jquery.flot.tooltip.min.js"></script>
    <script src="assets/plugins/flot-chart/jquery.flot.resize.js"></script>
    <script src="assets/plugins/flot-chart/jquery.flot.pie.js"></script>
    <script src="assets/plugins/flot-chart/jquery.flot.selection.js"></script>
    <script src="assets/plugins/flot-chart/jquery.flot.stack.js"></script>
    <script src="assets/plugins/flot-chart/jquery.flot.crosshair.js"></script>
    <script src="assets/pages/jquery.todo.js"></script>
    <script src="assets/pages/jquery.chat.js"></script>
    <script src="assets/pages/jquery.dashboard.js"></script>
    <script type="text/javascript">
        /* ==============================================
                    Counter Up
                    =============================================== */
        jQuery(document).ready(function($) {
            $('.counter').counterUp({
                delay: 100,
                time: 1200
            });
        });
    </script>
</body>
<!-- Mirrored from moltran.coderthemes.com/dark/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 28 Sep 2015 21:15:54 GMT -->

</html>