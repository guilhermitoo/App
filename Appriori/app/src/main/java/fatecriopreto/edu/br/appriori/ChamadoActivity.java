package fatecriopreto.edu.br.appriori;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import fatecriopreto.edu.br.appriori.model.Chamado;
import fatecriopreto.edu.br.appriori.model.Equipamento;
import fatecriopreto.edu.br.appriori.model.Usuario;
import fatecriopreto.edu.br.appriori.util.CustomRequest;

public class ChamadoActivity extends Activity {

    //verificar como criar os elementos do snniper e como conecta-los
    Spinner spnLocalC;
    Spinner spnEquipamentoC;
    EditText edtDescricao;
    Button btnSalvar;
    Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado);

        spnLocalC = (Spinner) findViewById(R.id.spnLocalC);
        spnEquipamentoC = (Spinner) findViewById(R.id.spnEquipamentoC);
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        // carregar os locais no combo de locais
        try {
            // função que verifica se o usuário digitado existe e a senha está correta
            WService ws = new WService();

            // monta a url do webservice
            final String link = ws.url + ws.locais;

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            // faz a requisição para o webservice
            CustomRequest jsObjRequest = new CustomRequest(Request.Method.GET, link, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            List<spnObj> lista = new ArrayList<spnObj>();
                            try {
                                JSONArray jsonArray = new JSONArray(jsonObject.optString("locais"));
                                spnObj so = new spnObj();
                                so.setId(0);
                                so.setNome("Selecione um local...");
                                lista.add(so);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jObject = jsonArray.getJSONObject(i);

                                    spnObj so1 = new spnObj(jObject.optInt("id"),jObject.optString("nome"));

                                    lista.add(so1);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            ArrayAdapter<spnObj> dataAdapter = new ArrayAdapter<spnObj>(getActivity(),android.R.layout.simple_spinner_item, lista);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spnLocalC.setAdapter(dataAdapter);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.d("Error.Response", volleyError.getMessage());
                }
            });
            requestQueue.add(jsObjRequest);
        }catch(RuntimeException e){
            Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        spnLocalC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ListarEquipamentos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelar = new Intent(getActivity(), HomeActivity.class);
                startActivity(cancelar);
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // executa a função para salvar o chamado no banco
                // mas antes valida se os dados foram preenchidos
                spnObj so = new spnObj();

                so = (spnObj) spnLocalC.getSelectedItem();
                if ( so.getId() == 0 ){
                    Toast.makeText(getActivity(), "Selecione um local", Toast.LENGTH_LONG).show();
                    spnLocalC.requestFocus();
                    return;
                }
                so = (spnObj) spnEquipamentoC.getSelectedItem();
                if ( so.getId() == 0 ){
                    Toast.makeText(getActivity(), "Selecione o equipamento", Toast.LENGTH_LONG).show();
                    spnEquipamentoC.requestFocus();
                    return;
                }
                if ( edtDescricao.getText().equals("") ){
                    Toast.makeText(getActivity(), "Descreva o problema", Toast.LENGTH_LONG).show();
                    edtDescricao.requestFocus();
                    return;
                }
                // somente após validar é que executa a função
                cadastrarChamado();
            }
        });
    }

    private void cadastrarChamado(){
        // função que cadastra o chamado utilizando o webservice
        try {
            // pega o objeto selecionado
            spnObj objSelecionado = new spnObj();
            objSelecionado = (spnObj) spnEquipamentoC.getSelectedItem();
            //instancia o equipamento
            Equipamento e = new Equipamento();
            //atribui os dados do equipamento
            e.setId(objSelecionado.getId());
            // instancia o usuario
            Usuario user = new Usuario();
            // Declaração de um objeto sharedpreferences da instância de SharedPreferences
            SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences("usuario", MODE_PRIVATE);
            user.setId(sharedpreferences.getInt("id",0));

            // instancia um chamado
            Chamado chamado = new Chamado();
            // atribui os dados no chamado
            chamado.setEquipamento(e);
            chamado.setDescricao(edtDescricao.getText().toString());
            chamado.setUsuario(user);
            // envia os dados para o web service
            WService ws = new WService();
            JSONObject js = new JSONObject();

            js.put("descricao",chamado.getDescricao().toString());
            js.put("usuario_id",chamado.getUsuario().getId());
            js.put("equip_id",chamado.getEquipamento().getId());
            final String link = ws.url + ws.cadastroChamado;

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

            // faz a requisição para o webservice
            final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, link, js,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            //mPostCommentResponse.requestCompleted();
                            // verifica se o json retornado é o de erro
                            if ( jsonObject.has("error") ) {
                                try {
                                    // se for, exibe a mensagem retornada
                                    JSONObject js = new JSONObject();
                                    js = jsonObject.getJSONObject("error");
                                    Toast.makeText(getActivity(), "Erro ao cadastrar: " + js.getString("text").toString(), Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                try {
                                    // se o retorno do webservice foi 0, ele tentou inserir mas por algum motivo não foi inserido
                                    if ( jsonObject.getInt("id") == 0 ){
                                        Toast.makeText(getActivity(), "Erro ao registrar o chamado.", Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(getActivity(), "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                                        // cria a intent da activity login
                                        Intent voltarChamados = new Intent (getActivity(), AcompanharActivity.class);
                                        startActivity(voltarChamados);
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
            Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private Context getActivity() {
        return this;
    }

    private void ListarEquipamentos(){
        try {
            spnObj objSelecionado = new spnObj();
            objSelecionado = (spnObj) spnLocalC.getSelectedItem();

            // função que verifica se o usuário digitado existe e a senha está correta
            WService ws = new WService();

            // monta a url do webservice
            final String link = ws.url + ws.equipamentosl + objSelecionado.getId();

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            // faz a requisição para o webservice
            CustomRequest jsObjRequest = new CustomRequest(Request.Method.GET, link, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            List<spnObj> lista = new ArrayList<spnObj>();
                            try {
                                JSONArray jsonArray = new JSONArray(jsonObject.optString("equipamentos"));
                                spnObj so1 = new spnObj();
                                so1.setId(0);
                                so1.setNome("Selecione um Equipamento...");
                                lista.add(so1);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jObject = jsonArray.getJSONObject(i);

                                    spnObj so = new spnObj(jObject.optInt("id"),jObject.optString("descricao"));

                                    lista.add(so);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            ArrayAdapter<spnObj> dataAdapter = new ArrayAdapter<spnObj>(getActivity(),android.R.layout.simple_spinner_item, lista);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spnEquipamentoC.setAdapter(dataAdapter);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.d("Error.Response", volleyError.getMessage());
                }
            });
            requestQueue.add(jsObjRequest);
        }catch(RuntimeException e){
            Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public class spnObj{

        private int id;
        private String nome;

        public spnObj ( int id , String nome )
        {
            this.id = id;
            this.nome = nome;
        }

        public spnObj(){

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        @Override
        public String toString() {
            return this.nome;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chamado, menu);
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
