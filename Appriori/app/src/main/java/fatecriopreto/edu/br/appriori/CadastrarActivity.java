package fatecriopreto.edu.br.appriori;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fatecriopreto.edu.br.appriori.bean.Login;
import fatecriopreto.edu.br.appriori.data.DBAdapter;

public class CadastrarActivity extends Activity {

    //componentes manipulados no layout
    EditText edtNome;
    EditText edtEmail;
    EditText edtSenha;
    Button   btnSalvar;
    Button   btnCancelar;

    Login login;
    DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        //"conectar" os elementos do layout com os atributos
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnSalvar = (Button)  findViewById(R.id.btnSalvar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        //evento para salvar
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbAdapter = new DBAdapter(CadastrarActivity.this);
                dbAdapter.open();

                login = new Login();
                login.setNome(edtNome.getText().toString());
                login.setEmail(edtEmail.getText().toString());
                login.setSenha(edtSenha.getText().toString());

                dbAdapter.adicionar(login);
                dbAdapter.close();
                Intent entrar = new Intent (CadastrarActivity.this, HomeActivity.class);
                startActivity(entrar);

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cancelar = new Intent (CadastrarActivity.this, LoginActivity.class);
                startActivity(cancelar);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastrar, menu);
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
