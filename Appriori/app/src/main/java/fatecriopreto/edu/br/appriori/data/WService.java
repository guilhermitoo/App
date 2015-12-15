package fatecriopreto.edu.br.appriori.data;

/**
 * Created by Gui on 16/11/2015.
 */
public class WService {

    // url do serviço
    public String url;
    // caminhos do web service
    public String locais;
    public String equipamentos;
    public String equipamentosl;
    public String chamados;
    public String chamados_usuario;
    public String usuarios;
    public String login;
    public String updateUsuario;
    public String cadastroUsuario;
    public String cadastroChamado;

    public WService() {
        // LINK DO WEB SERVICE
        String note = "192.168.56.1";
        String ws = "appriori.no-ip.info:8090";

        url = "http://" + note + "/app/service";

        // CAMINHOS WEB SERVICE
        locais = "/locais/";
        equipamentos = "/equipamentos/";
        equipamentosl = "/equipamentos_local/";
        chamados = "/chamados/";
        chamados_usuario = "/chamados_usuario/";
        usuarios = "/usuarios/";
        login = "/usuario_login/";
        updateUsuario = "/upd_usuario   /";
        cadastroUsuario = "/cad_usuario/";
        cadastroChamado = "/cad_chamado/";
    }

}
