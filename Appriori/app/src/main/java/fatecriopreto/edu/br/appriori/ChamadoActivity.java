package fatecriopreto.edu.br.appriori;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

import fatecriopreto.edu.br.appriori.data.WService;
import fatecriopreto.edu.br.appriori.util.CustomRequest;

public class ChamadoActivity extends Activity {

    //verificar como criar os elementos do snniper e como conecta-los
    Spinner spnLocalC;
    Spinner spnEquipamentoC;
    EditText edtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado);

        spnLocalC = (Spinner) findViewById(R.id.spnLocalC);
        spnEquipamentoC = (Spinner) findViewById(R.id.spnEquipamentoC);
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);

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
                            ArrayList<String> lista = new ArrayList<String>();
                            try {
                                JSONArray jsonArray = new JSONArray(jsonObject.optString("locais"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jObject = jsonArray.getJSONObject(i);

                                    lista.add(jObject.optString("nome"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            spnLocalC.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_gallery_item, lista));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.d("Error.Response", volleyError.getMessage());
                }
            });
            requestQueue.add(jsObjRequest);
        }catch(RuntimeException e){
            Toast.makeText(ChamadoActivity.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void ListarLocais(){

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
