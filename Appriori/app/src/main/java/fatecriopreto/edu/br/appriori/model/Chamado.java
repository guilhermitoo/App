package fatecriopreto.edu.br.appriori.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public void setDataInicioStr(String sData) {
        String dia,mes,ano,dataCorreta;
        // string recebida YYYY-MM-DD
        ano = sData.substring(0,4);
        mes = sData.substring(5,7);
        dia = sData.substring(8,10);
        dataCorreta = dia + "/" + mes + "/" + ano;

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date data = new Date();
        try{
            data = df.parse(dataCorreta);
        }catch (ParseException e){
            e.printStackTrace();
        }

        this.dataInicio = data;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public void setDataFimStr(String sData) {
        String dia,mes,ano,dataCorreta;
        // string recebida YYYY-MM-DD
        ano = sData.substring(0,4);
        mes = sData.substring(5,7);
        dia = sData.substring(8,10);
        dataCorreta = dia + "/" + mes + "/" + ano + " 00:00:00";

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

        Date data = new Date();
        try{
            data = df.parse(dataCorreta);
        }catch (ParseException e){
            e.printStackTrace();
        }

        this.dataInicio = data;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public Integer getStatusInt() {
        // função para retornar o valor numérico do status
        Integer i = 0;
        switch (status){
            case PENDENTE:{
                i = 1;
                break;
            }
            case REALIZADO: {
                i = 2;
                break;
            }
            case CANCELADO: {
                i = 3;
                break;
            }
        }
        return i;
    }

    public String getStatusString() {
        // função para retornar o valor numérico do status
        String s = "";
        switch (status){
            case PENDENTE:{
                s = "Pendente";
                break;
            }
            case REALIZADO: {
                s = "Realizado";
                break;
            }
            case CANCELADO: {
                s = "Cancelado";
                break;
            }
        }
        return s;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public void setStatusInt(Integer status) {
        switch (status) {
            case 1: {
                this.status = StatusChamado.PENDENTE;
                break;
            }
            case 2: {
                this.status = StatusChamado.REALIZADO;
                break;
            }
            case 3: {
                this.status = StatusChamado.CANCELADO;
                break;
            }
            default:
                this.status = null;
        }
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
    private Date dataInicio;
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
