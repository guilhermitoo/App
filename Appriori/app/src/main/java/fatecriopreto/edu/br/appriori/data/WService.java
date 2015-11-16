package fatecriopreto.edu.br.appriori.data;

/**
 * Created by Gui on 16/11/2015.
 */
public class WService {

    // url do serviço
    public String url = "http://http://localhost/app/service";
    // caminhos do web service
    public String locais;
    public String equipamentos;
    public String equipamentosl;
    public String chamados;
    public String usuarios;
    public String updateUsuario;
    public String cadastroUsuario;
    public String cadastroChamado;

    public WService() {
        locais = "/locais";
        equipamentos = "/equipamentos";
        equipamentosl = "/equipamentos_local";
        chamados = "/chamados";
        usuarios = "/usuarios";
        updateUsuario = "/upd_usuario";
        cadastroUsuario = "/cad_usuario";
        cadastroChamado = "/cad_chamado";
    }

}
