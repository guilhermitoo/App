package fatecriopreto.edu.br.appriori.model;

/**
 * Created by reinaldo on 06/11/2015.
 */
public class Funcionario extends Usuario{

    private String rg;
    private char periodo;

    public Funcionario(String rg, char periodo) {
        this.rg = rg;
        this.periodo = periodo;
    }

    public Funcionario() {
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public char getPeriodo() {
        return periodo;
    }

    public void setPeriodo(char periodo) {
        this.periodo = periodo;
    }
}
