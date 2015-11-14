package fatecriopreto.edu.br.appriori.model;

import java.util.Date;

import fatecriopreto.edu.br.appriori.util.StatusChamado;

/**
 * Created by reinaldo on 06/11/2015.
 */
public class Chamado {

    private int id;
    private String descricao;
    private Date dataInicio ;
    private Date dataFim;
    private StatusChamado status;
    private Usuario usuario;
    private Funcionario funcionario;
    private Equipamento equipamento;

    public Chamado() {
    }

    public Chamado(int id, String descricao, Date dataInicio, Date dataFim, StatusChamado status, Usuario usuario, Funcionario funcionario, Equipamento equipamento) {
        this.id = id;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.usuario = usuario;
        this.funcionario = funcionario;
        this.equipamento = equipamento;
    }
}
