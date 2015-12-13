package fatecriopreto.edu.br.appriori;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fatecriopreto.edu.br.appriori.data.WService;
import fatecriopreto.edu.br.appriori.model.Chamado;
import fatecriopreto.edu.br.appriori.model.Equipamento;
import fatecriopreto.edu.br.appriori.model.Local;
import fatecriopreto.edu.br.appriori.util.CustomRequest;

public class AcompanharActivity extends Activity {

    ListView lsvChamados;

    //verificar de qual componente colocar para trazer as insformações do chamado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanhar);

        lsvChamados = (ListView) findViewById(R.id.lsvChamados);

        // Declaração de um objeto sharedpreferences da instância de SharedPreferences
        SharedPreferences sPreferences = getApplicationContext().getSharedPreferences("usuario", MODE_PRIVATE);

        // carrega os chamados do usuario logado
        try {
            WService ws = new WService();

            // monta a url do webservice
            final String link = ws.url + ws.chamados_usuario + sPreferences.getInt("id",0);

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            // faz a requisição para o webservice
            final CustomRequest jsObjRequest = new CustomRequest(Request.Method.GET, link, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            List<Chamado> lista = new ArrayList<Chamado>();
                            try {
                                JSONArray jsonArray = new JSONArray(jsonObject.optString("chamados"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    // instancia as classes
                                    Chamado ch = new Chamado();
                                    Equipamento e = new Equipamento();
                                    Local l = new Local();
                                    // pega o objeto json correto
                                    JSONObject jObject = jsonArray.getJSONObject(i);

                                    ch.setDataInicioStr(jObject.getString("data_inicio"));
                                    //ch.setDataFim((Date) jObject.get("data_fim"));

                                    e.setId(jObject.getInt("equipamento_id"));
                                    e.setDescricao(jObject.getString("equipamento_descricao"));
                                    //INSTACIA LOCAL E ATRIBUI AO EQUIPAMENTO, PARA EXIBIÇÃO NO ADAPTERLISTVIEW

                                    l.setId(jObject.getInt("local_id"));
                                    l.setNome(jObject.getString("local_nome"));
                                    e.setLocal(l);
                                    ch.setEquipamento(e);
                                    ch.setStatusInt(jObject.getInt("status"));
                                    ch.setDescricao(jObject.getString("descricao"));

                                    lista.add(ch);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            AdapterListView adapter = new AdapterListView(getActivity(), lista);
                            lsvChamados.setAdapter(adapter);
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

    private Context getActivity() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_acompanhar, menu);
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
