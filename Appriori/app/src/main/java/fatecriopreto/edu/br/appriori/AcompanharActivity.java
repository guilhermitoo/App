package fatecriopreto.edu.br.appriori;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
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
            CustomRequest jsObjRequest = new CustomRequest(Request.Method.GET, link, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            List<listChamado> lista = new ArrayList<listChamado>();
                            try {
                                JSONArray jsonArray = new JSONArray(jsonObject.optString("chamados"));
                                listChamado lst = new listChamado();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jObject = jsonArray.getJSONObject(i);

                                    lst.setData_inicio(jObject.getString("data_inicio"));
                                    lst.setData_fim(jObject.getString("data_fim"));
                                    lst.setEquipamento(jObject.getString("equipamento_descricao"));
                                    lst.setStatus(jObject.getString("status"));

                                    lista.add(lst);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            ArrayAdapter<listChamado> dataAdapter = new ArrayAdapter<listChamado>(getActivity(),android.R.layout.simple_list_item_1, lista);
                            lsvChamados.setAdapter(dataAdapter);
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

    private class listChamado {
        public String getData_inicio() {
            return data_inicio;
        }

        public void setData_inicio(String data_inicio) {
            this.data_inicio = data_inicio;
        }

        public String getData_fim() {
            return data_fim;
        }

        public void setData_fim(String data_fim) {
            this.data_fim = data_fim;
        }

        public String getEquipamento() {
            return equipamento;
        }

        public void setEquipamento(String equipamento) {
            this.equipamento = equipamento;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        private String data_inicio;
        private String data_fim;
        private String equipamento;
        private String status;

        private listChamado() {
        }

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
