package fatecriopreto.edu.br.appriori.bean;

import java.io.Serializable;

/**
 * Created by thaysa on 13/10/15.
 */
public class Login implements Serializable {

    private long id;
    private String nome;
    private String email;
    private String senha;

    public Login(){}
    public Login(long id, String nome, String email, String senha){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void setId (int id) { this.id = id; }

    public long getId () { return this.id; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
