package fatecriopreto.edu.br.appriori;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import fatecriopreto.edu.br.appriori.bean.Login;
import fatecriopreto.edu.br.appriori.data.DBAdapter;
import fatecriopreto.edu.br.appriori.data.WService;
import fatecriopreto.edu.br.appriori.model.Usuario;

public class CadastrarActivity extends Activity {

    //componentes manipulados no layout
    EditText edtNome;
    EditText edtEmail;
    EditText edtSenha;
    EditText edtSenha2;
    Button   btnSalvar;
    Button   btnCancelar;

    Login login;
    DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        //"conectar" os elementos do layout com os atributos
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        edtSenha2 = (EditText) findViewById(R.id.edtSenha2);
        btnSalvar = (Button)  findViewById(R.id.btnSalvar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        //evento para salvar
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                dbAdapter = new DBAdapter(CadastrarActivity.this);
                dbAdapter.open();

                login = new Login();
                login.setNome(edtNome.getText().toString());
                login.setEmail(edtEmail.getText().toString());
                login.setSenha(edtSenha.getText().toString());

                dbAdapter.adicionar(login);
                dbAdapter.close();
                Intent entrar = new Intent (CadastrarActivity.this, HomeActivity.class);
                startActivity(entrar);
*/
                String nome,email,senha, senha2;
                nome = edtNome.getText().toString();
                email = edtEmail.getText().toString();
                senha = edtSenha.getText().toString();
                senha2 = edtSenha2.getText().toString();

                // se algum dos campos não for informado, exibe uma mensagem de aviso
                if (nome.equals("")||email.equals("")||senha.equals("")||senha2.equals("")){
                    Toast.makeText(CadastrarActivity.this, "Preencha todos os campos.", Toast.LENGTH_LONG).show();
                    return;
                }
                // verifica se as senhas coincidem
                if (!senha.equals(senha2)){
                    Toast.makeText(CadastrarActivity.this, "As senhas não coincidem.", Toast.LENGTH_LONG).show();
                    edtSenha.requestFocus();
                    return;
                }

                // instancia o usuário
                Usuario u = new Usuario();
                // atribui os valores para a instancia de usuario
                u.setNome(nome.toString());
                u.setEmail(email.toString());
                u.setSenha(senha.toString());
                // e então chama a função para cadastrar
                cadastrar(u);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelar = new Intent (CadastrarActivity.this, LoginActivity.class);
                startActivity(cancelar);
            }
        });

    }

    private void cadastrar( final Usuario user ){
        try{
            // função utilizada para cadastrar

            // após validar os dados, envia para o webservice utilizando a função de cadastro
            // função que verifica se o usuário digitado existe e a senha está correta
            WService ws = new WService();

            JSONObject js = new JSONObject();

            js.put("nome",user.getNome());
            js.put("email",user.getEmail());
            js.put("senha",user.getSenha());

            // monta a url do webservice
            final String link = ws.url + ws.cadastroUsuario;

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

            // faz a requisição para o webservice
            final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, link, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        //mPostCommentResponse.requestCompleted();
                        // verifica se o json retornado é o de erro
                        if ( jsonObject.has("error") )
                        {
                            try
                            {
                                // se for, exibe a mensagem retornada
                                JSONObject js = new JSONObject();
                                js = jsonObject.getJSONObject("error");
                                Toast.makeText(CadastrarActivity.this, "Erro ao cadastrar: " +
                                        js.getString("text").toString(), Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            try {
                                // se o retorno do webservice foi 0, ele tentou inserir mas o email já estava cadastrado
                                if ( jsonObject.getInt("id") == 0 ){
                                    Toast.makeText(CadastrarActivity.this, "Email já cadastrado", Toast.LENGTH_LONG).show();
                                    edtEmail.requestFocus();
                                }else {
                                    Toast.makeText(CadastrarActivity.this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                                    // cria a intent da activity login
                                    Intent voltar = new Intent (CadastrarActivity.this, LoginActivity.class);
                                    voltar.putExtra("email", user.getEmail().toString());
                                    voltar.putExtra("senha", user.getSenha().toString());
                                    startActivity(voltar);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastrarActivity.this, "Erro ao cadastrar: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            queue.add(getRequest);
        }catch(Exception e){
            Toast.makeText(CadastrarActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastrar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
