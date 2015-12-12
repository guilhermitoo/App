package fatecriopreto.edu.br.appriori;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import fatecriopreto.edu.br.appriori.data.WService;
import fatecriopreto.edu.br.appriori.model.Usuario;

public class AlteraSenhaActivity extends AppCompatActivity {

    EditText edtSenhaAntiga;
    EditText edtSenha;
    EditText edtSenha2;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_senha);

        edtSenhaAntiga = (EditText) findViewById(R.id.edtSenhaAntiga);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        edtSenha2 = (EditText) findViewById(R.id.edtSenha2);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        final Usuario user = new Usuario();


        try {
            SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences("usuario", MODE_PRIVATE);

            user.setId(sharedpreferences.getInt("id", 0));
            user.setNome(sharedpreferences.getString("nome", ""));
            user.setEmail(sharedpreferences.getString("email", ""));
            user.setSenha(sharedpreferences.getString("senha", ""));

        }catch(Exception e){
            Toast.makeText(getActivity(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ! user.getSenha().equalsIgnoreCase(edtSenhaAntiga.getText().toString()) ){
                    Toast.makeText(getActivity(), "Senha incorreta" , Toast.LENGTH_LONG).show();
                    edtSenhaAntiga.requestFocus();
                    return;
                }

                if ( ! edtSenha.getText().toString().equalsIgnoreCase(edtSenha2.getText().toString()) ) {
                    Toast.makeText(getActivity(), "As senhas não conferem" , Toast.LENGTH_LONG).show();
                    edtSenha2.requestFocus();
                    return;
                }
                // após as validações, tenta alterar os dados do usuário
                try{
                    WService ws = new WService();

                    JSONObject js = new JSONObject();

                    js.put("email",user.getEmail());
                    js.put("senha",edtSenha.getText().toString());

                    // monta a url do webservice
                    final String link = ws.url + ws.updateUsuario;

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
                                            Toast.makeText(getActivity(), "Erro na alteração de senha: " +
                                                    js.getString("text").toString(), Toast.LENGTH_LONG).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }else {
                                        try {
                                            // se o retorno do webservice foi 0, ele tentou alterar mas algum
                                            // erro impediu
                                            if ( jsonObject.getInt("id") == 0 ){
                                                Toast.makeText(getActivity(), "Erro na alteração da senha", Toast.LENGTH_LONG).show();

                                            }else {
                                                Toast.makeText(getActivity(), "Senha alterada com sucesso", Toast.LENGTH_LONG).show();
                                                // se deu certo, então altera a senha no shared preferences
                                                try {
                                                    SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences("usuario", MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                                    // remove senha antiga e adiciona a nova
                                                    editor.remove("senha");
                                                    editor.putString("senha",edtSenha.getText().toString());
                                                    // Salva o que foi feito
                                                    editor.commit();
                                                }catch(Exception e){
                                                    Toast.makeText(getActivity(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                                }
                                                // volta para a tela de configurações
                                                Intent voltar = new Intent(getActivity(), ConfiguracoesActivity.class);
                                                voltar.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
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
                            Toast.makeText(getActivity(), "Erro ao cadastrar: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(getRequest);
                }catch(Exception e){
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private Context getActivity() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_altera_senha, menu);
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
