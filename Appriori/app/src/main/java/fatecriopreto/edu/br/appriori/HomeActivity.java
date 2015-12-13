package fatecriopreto.edu.br.appriori;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends Activity {

    //componentes que seram manipulados no layout
    Button btnChamadoH;
    Button btnAcompanharH;
    TextView txtTitulo;
    Button btnConfiguracoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //conectar elementos
        btnChamadoH = (Button) findViewById(R.id.btnChamadoH);
        btnAcompanharH = (Button) findViewById(R.id.btnAcompanharH);
        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        btnConfiguracoes = (Button) findViewById(R.id.btnConfiguracoes);

        // Declaração de um objeto sharedpreferences da instância de SharedPreferences
        SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences("usuario", MODE_PRIVATE);

        if ( sharedpreferences.contains("nome") ){
            String nomeUsuario = "Logado: " + sharedpreferences.getString("nome","");
            //define os titulos
            setTitle("Principal");
            txtTitulo.setText(nomeUsuario);
        }else{
            Intent login = new Intent (HomeActivity.this, LoginActivity.class);
            login.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(login);
        }

        //evento botão chamado, traz activity para abrir um chamado
        btnChamadoH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chamado = new Intent (HomeActivity.this, ChamadoActivity.class);
                chamado.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(chamado);
            }
        });

        //evento botão acompanhar, traz activity para acompanhar o chamado aberto
        btnAcompanharH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent acompanhar = new Intent (HomeActivity.this, AcompanharActivity.class);
                acompanhar.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(acompanhar);
            }
        });

        // evento do botão configurações, carrega as configurações do usuário
        btnConfiguracoes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent conf = new Intent (HomeActivity.this, ConfiguracoesActivity.class);
                //conf.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(conf);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
