package fatecriopreto.edu.br.appriori.model;

/**
 * Created by reinaldo on 06/11/2015.
 */
public class Equipamento {

    private int id;
    private String descricao;
    private Local local;

    public Equipamento(int id, String descricao, Local local) {
        this.id = id;
        this.descricao = descricao;
        this.local = local;
    }

    public Equipamento() {
    }

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

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
}
