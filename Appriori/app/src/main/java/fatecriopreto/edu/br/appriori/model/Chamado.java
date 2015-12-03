package fatecriopreto.edu.br.appriori.model;

import java.util.Date;

import fatecriopreto.edu.br.appriori.util.StatusChamado;

/**
 * Created by reinaldo on 06/11/2015.
 */
public class Chamado {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public Integer getStatusInt() {
        // função para retornar o valor numérico do status
        Integer i = 0;
        switch (status){
            case PENDENTE: i = 1;
            case REALIZADO: i = 2;
            case CANCELADO: i = 3;
        }
        return i;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

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
