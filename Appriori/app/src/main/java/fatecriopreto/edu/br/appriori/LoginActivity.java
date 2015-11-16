package fatecriopreto.edu.br.appriori;

import android.app.Activity;
import android.content.Intent;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fatecriopreto.edu.br.appriori.data.WService;
import fatecriopreto.edu.br.appriori.model.Usuario;

public class LoginActivity extends Activity {

    //componentes manipulados no layout
    EditText edtLogin;
    EditText edtSenhaL;
    Button   btnEntrarL;
    Button   btnCadastrarL;
    Button   btnEsqueciL;

    List<Usuario> usuarios = new ArrayList<Usuario>;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // carrega os usuarios do web service em um array
        carregaUsuarios();

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
                    Toast.makeText(LoginActivity.this, "Login e senha são obrigatórios", Toast.LENGTH_LONG).show();
                    return;
                }
                if (login.length() <= 1 || senha.length() <= 1) {
                    Toast.makeText(LoginActivity.this, "Usuario e senha devem ser maiores que 1 caractere", Toast.LENGTH_LONG).show();
                    return;
                }

                //logar
                Intent home = new  Intent (LoginActivity.this, HomeActivity.class);
                startActivity(home);
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

    private void login() {
        // função que verifica se o usuário digitado está no array que foi carregado do webService
    }

    public void carregaUsuarios(){
        WService ws = new WService();

        final String link = ws.url + ws.usuarios;

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest getRequest;
        getRequest = new JsonObjectRequest( Request.Method.GET, link, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject){
                        try{
                            JSONArray uLists = new JSONArray(jsonObject);
                            JSONObject uList = uLists.getJSONObject(0);
                            JSONArray uListArray = uList.getJSONArray("usuarios");
                            JSONObject usuario;

                            for (int i = 0; i < uListArray.length(); i++) {
                                usuario = new JSONObject(uListArray.getString(i));

                                Usuario objUsuario = new Usuario();
                                objUsuario.setId(usuario.getInt("id"));
                                objUsuario.setNome(usuario.getString("nome"));
                                objUsuario.setEmail(usuario.getString("email"));
                                objUsuario.setSenha(usuario.getString("senha"));

                                usuarios.add(objUsuario);

                                Log.d("USUARIOS",usuarios.toString());
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError){
                Log.d("Error.Response", volleyError.getMessage());
            }
        });
        queue.add(getRequest);
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
}
