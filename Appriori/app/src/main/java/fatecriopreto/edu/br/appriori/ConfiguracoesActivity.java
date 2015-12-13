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
import android.widget.TextView;
import android.widget.Toast;

import fatecriopreto.edu.br.appriori.model.Usuario;

public class ConfiguracoesActivity extends AppCompatActivity {

    TextView txtNome;
    TextView txtEmail;
    TextView txtSenha;
    Button btnAlterar;

    Usuario user = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        txtNome = (TextView) findViewById(R.id.txtNome);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtSenha = (TextView) findViewById(R.id.txtSenha);
        btnAlterar = (Button) findViewById(R.id.btnAlteraSenha);

        // carrega as configurações do usuário logado e exibe nos campos da tela
        // Declaração de um objeto sharedpreferences da instância de SharedPreferences
        try {
            SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences("usuario", MODE_PRIVATE);

            user.setId(sharedpreferences.getInt("id", 0));
            user.setNome(sharedpreferences.getString("nome", ""));
            user.setEmail(sharedpreferences.getString("email", ""));
            user.setSenha(sharedpreferences.getString("senha", ""));

            txtNome.setText(user.getNome());
            txtEmail.setText(user.getEmail());
            txtSenha.setText(user.getSenha());
        }catch(Exception e){
            Toast.makeText(getActivity(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ao clicar no botão alterar, chama a activity AlteraSenha
                Intent alterar = new Intent (getActivity(), AlteraSenhaActivity.class);
                alterar.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(alterar);
            }
        });

    }

    private Context getActivity() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configuracoes, menu);
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
