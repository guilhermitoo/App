package fatecriopreto.edu.br.appriori;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

    //componentes que seram manipulados no layout
    Button btnChamadoH;
    Button btnAcompanharH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("Home");

        //conectar elementos
        btnChamadoH = (Button) findViewById(R.id.btnChamadoH);
        btnAcompanharH = (Button) findViewById(R.id.btnAcompanharH);

        //evento botão chamado, traz activity para abrir um chamado
        btnChamadoH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chamado = new Intent (HomeActivity.this, ChamadoActivity.class);
                startActivity(chamado);
            }
        });

        //evento botão acompanhar, traz activity para acompanhar o chamado aberto
        btnAcompanharH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent acompanhar = new Intent (HomeActivity.this, AcompanharActivity.class);
                startActivity(acompanhar);
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
