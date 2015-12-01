package fatecriopreto.edu.br.appriori;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import fatecriopreto.edu.br.appriori.data.WService;
import fatecriopreto.edu.br.appriori.model.Usuario;
import fatecriopreto.edu.br.appriori.util.CustomRequest;

public class LoginActivity extends Activity {

    //componentes manipulados no layout
    EditText edtLogin;
    EditText edtSenhaL;
    Button   btnEntrarL;
    Button   btnCadastrarL;
    Button   btnEsqueciL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //"conectar" os elementos do layout com os atributos
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenhaL = (EditText) findViewById(R.id.edtSenhaL);
        btnEntrarL = (Button) findViewById(R.id.btnEntrarL);

        btnCadastrarL = (Button) findViewById(R.id.btnCadastrarL);
        btnEsqueciL = (Button) findViewById(R.id.btnEsqueciL);

        //evento para logar
        btnEntrarL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //conteudo da caixa de texto
                String login = edtLogin.getText().toString();
                String senha = edtSenhaL.getText().toString();

                //verifica se foi preenchido
                if (login.equals("") || senha.equals("")) {
                    Toast.makeText(LoginActivity.this, "Email e senha são obrigatórios", Toast.LENGTH_LONG).show();
                    return;
                }
                if (login.length() <= 1 || senha.length() <= 1) {
                    Toast.makeText(LoginActivity.this, "Email e senha devem ser maiores que 1 caractere", Toast.LENGTH_LONG).show();
                    return;
                }

                login(login, senha);
            }
        });

        btnCadastrarL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrar = new Intent (LoginActivity.this, CadastrarActivity.class);
                startActivity(cadastrar);
            }

            //verificar o que falta para chamar a activity cadastrar
        });

        btnEsqueciL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent esqueci = new Intent(LoginActivity.this, EsqueciActivity.class);
                startActivity(esqueci);
            }
        });

    }

    private void login(final String login, final String senha) {
        try {
            // função que verifica se o usuário digitado existe e a senha está correta
            WService ws = new WService();

            // monta a url do webservice
            final String link = ws.url + ws.login + login;

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            // faz a requisição para o webservice
            CustomRequest jsObjRequest = new CustomRequest(Request.Method.GET, link, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            // se o json retornado nao contem o objeto email, então não retornou registro
                            // assim exibe mensagem informando que o email nao foi cadastrado
                            if (!jsonObject.has("email")) {
                                Toast.makeText(LoginActivity.this, "Email não cadastrado!", Toast.LENGTH_LONG).show();
                                edtLogin.requestFocus();
                                return;
                            }
                            // instancia um usuario
                            Usuario u = new Usuario();
                            // passa os dados do usuario
                            u.setEmail(login);
                            u.setSenha(senha);
                            u.setNome(jsonObject.getString("nome").toString());
                            u.setId(jsonObject.getInt("id"));
                            // valida se a senha está correta, verificando a senha já encriptografada
                            if (!jsonObject.getString("senha").equalsIgnoreCase(u.getSenhaMD5())) {
                                // se a senha está incorreta
                                Toast.makeText(LoginActivity.this, "Senha incorreta!", Toast.LENGTH_LONG).show();
                                edtSenhaL.requestFocus();
                                return;
                            }

                            Log.d("USUARIOS", u.toString());

                            //logar

                            // Declaração de um objeto sharedpreferences da instância de SharedPreferences
                            SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences("usuario", MODE_PRIVATE);

                            // Cria um objeto chamado editor da instância de Editor a partir do método edit do objeto sharedpreferences
                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            // adiciona os dados do usuário logado no editor.
                            editor.putInt("id", u.getId());
                            editor.putString("nome", u.getNome());
                            editor.putString("email", u.getEmail());
                            editor.putString("senha", u.getSenha());

                            // Salva o que foi feito
                            editor.commit();

                            Intent home = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(home);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error.Response", volleyError.getMessage());
                }
            });
            requestQueue.add(jsObjRequest);
        }catch(RuntimeException e){
            Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    @Override
    protected void onResume(){
        super.onResume();
        Intent intent = getIntent();

        edtLogin.setText(intent.getStringExtra("email"));
        edtSenhaL.setText(intent.getStringExtra("senha"));
    }

}
